package maa.gameboyfilter.GameBoyFilter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

public class BitmapHelper {
    private static final float DEFAULT_CONTRAST = 1.0f;
    private float contrast = 0.0f;
    private float brightness = DEFAULT_CONTRAST;

    public BitmapHelper() {
    }

    public Bitmap getResized(Bitmap bm, int newWidth, boolean discard) {
        if (bm == null) {
            return null;
        }
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scale = ((float) newWidth) / ((float) width);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return discard ? Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false) : Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }

    public Bitmap ContrastBrightness(Bitmap bmp, float contrast, float brightness) {
        ColorMatrix cm = new ColorMatrix(new float[]{contrast, 0.0f, 0.0f, 0.0f, brightness, 0.0f, contrast, 0.0f, 0.0f, brightness, 0.0f, 0.0f, contrast, 0.0f, brightness, 0.0f, 0.0f, 0.0f, DEFAULT_CONTRAST, 0.0f});
        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0.0f, 0.0f, paint);
        return ret;
    }
    public Bitmap changeColor(Bitmap myBitmap, int color) {
        Bitmap resultBitmap = myBitmap.copy(myBitmap.getConfig(), true);
        Paint paint = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        paint.setColorFilter(filter);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, paint);
        return resultBitmap;
    }
}
