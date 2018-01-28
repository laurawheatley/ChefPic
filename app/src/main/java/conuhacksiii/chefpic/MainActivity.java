package conuhacksiii.chefpic;

import android.Manifest;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;



public class MainActivity extends AppCompatActivity {

    private Camera myCamera = null;
    private CameraView myCameraView = null;
    private VisualRecognizer visualRecognizer = null;
    private RecipeSearch rs;

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
                System.out.println("Nothing");
                return;
            }

            try {
                System.out.print(pictureFile);
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                new WatsonAsyncTask().execute(pictureFile.getPath());
                //String result = visualRecognizer.classifyImage(pictureFile.getPath());
                //System.out.print(result);
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rs = new RecipeSearch();

        try {
            myCamera = Camera.open();
        } catch (Exception e){
            Log.d("Error", "Couldn't get camera: " + e.getMessage());
        }

        if (myCamera != null){

            //creating a surfaceView on which to display camera
            myCameraView = new CameraView(this, myCamera);
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);

            //adding surfaceView to layout
            camera_view.addView(myCameraView);

            //create instance of VisualRecognizer
            visualRecognizer = new VisualRecognizer();
        }
    }

    public void onCapture(View v){

      //  myCamera.takePicture(null, null, mPicture);
       // RecipeSearch rs = new RecipeSearch();
        rs.findRecipes("steak");



    }

    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "ChefPic");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    class WatsonAsyncTask extends AsyncTask<String, Void, Void> {

        private String result = "";

        protected Void doInBackground(String... path) {
            try {
                result = visualRecognizer.classifyImage(path[0]);
                System.out.print(result);
            }
            catch(FileNotFoundException e) {}
            return null;
        }
    }
}
