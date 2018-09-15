package maa.gameboyfilter.GameBoyFilter;

import android.graphics.Bitmap;
import android.support.v4.view.MotionEventCompat;

public class GameBoyDither {
    protected int[][] ditherMatrix;
    protected double errorFactor;
    protected int scaleHeight;
    protected int scaleWidth;
    protected int thresholdColor;

    public Bitmap ditherImage(Bitmap image) {
        return null;
    }

    public int getLuminescentGrayscale(int color) {
        return (int) (((0.2126d * ((double) ((color >> 16) & MotionEventCompat.ACTION_MASK))) + (0.7152d * ((double) ((color >> 8) & MotionEventCompat.ACTION_MASK)))) + (0.0722d * ((double) (color & MotionEventCompat.ACTION_MASK))));
    }
}