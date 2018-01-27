package conuhacksiii.chefpic;

import android.view.SurfaceHolder;

/**
 * Created by laura on 2018-01-27.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback{

    private surfaceHolder myHolder;
    private Camera myCamera;

    public CameraView(Context context, Camera camera){
        super(context);

        myCamera = camera;
        myCamera.setDisplayOrientation(90);

        myHolder = getHolder();
        myHolder.addCallback(this);
        myHolder.setType(SurfaceHolder.SURFACE_TYPE , _NORMAL);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
