package conuhacksiii.chefpic;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by laura on 2018-01-27.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder myHolder;
    private Camera myCamera;

    public CameraView(Context context, Camera camera){
        super(context);

        myCamera = camera;
        myCamera.setDisplayOrientation(90);

        myHolder = getHolder();
        myHolder.addCallback(this);
        myHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try{
            //upon creation of surface, we set the camera to draw images in surface
            myCamera.setPreviewDisplay(surfaceHolder);
            myCamera.startPreview();
        }catch(IOException e) {
            Log.d("Error", "Camera error on surfaceCreated" + e.getMessage());
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

        //checking to see if surface is ready to accept data
        if(myHolder.getSurface() == null){
            return;
        }

        try{
            myCamera.stopPreview();
        } catch(Exception e){
            //TRYING CAMERA AND CAMERA NOT RUNNING CASE
        }

        //doing what we're doing in surfaceCreated (i.e. recreating)
        try{
            myCamera.setPreviewDisplay(myHolder);
            myCamera.startPreview();
        }catch(IOException e){
            Log.d("Error", "Camera error on surfaceChanged" + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //our app has only one screen, so we'll destroy the camera in the surface
        //if you are using with more screens, please move this code to your activity
        myCamera.stopPreview();
        myCamera.release();
        myCamera = null;
    }
}
