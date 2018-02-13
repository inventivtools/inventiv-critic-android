package io.inventiv.critic.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class Screenshots {

    private static final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CriticScreenshots";

    public static File captureActivity(Activity activity) {
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Bitmap bitmap = getScreenShot(rootView);
        return store(bitmap, "last_screen.bmp");
    }

    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap cache = screenView.getDrawingCache();
        if(cache == null) {
            screenView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            screenView.layout(0, 0, screenView.getMeasuredWidth(), screenView.getMeasuredHeight());
            screenView.buildDrawingCache();
            cache = screenView.getDrawingCache();
        }
        Bitmap bitmap = Bitmap.createBitmap(cache);
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static File store(Bitmap bm, String fileName){
        File dir = new File(dirPath);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
