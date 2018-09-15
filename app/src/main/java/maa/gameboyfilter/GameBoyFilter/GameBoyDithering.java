package maa.gameboyfilter.GameBoyFilter;

import android.graphics.Bitmap;
import android.graphics.Color;

public class GameBoyDithering {
    static final int ordered2By2Bayer = 1;
    int threshold = 255;

    public GameBoyDithering() {

    }

    public GameBoyDithering(int threshold) {
        this.threshold = threshold;
    }

    public Bitmap ordered2By2Bayer(Bitmap src) {
        Bitmap out = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        int[][] matrix = new int[][]{new int[]{1, 3}, new int[]{4, 2}};
        int width = src.getWidth();
        int height = src.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = src.getPixel(x, y);
                int alpha = Color.alpha(pixel);
                int gray = Color.red(pixel);
                if (gray + ((matrix[x % 2][y % 2] * gray) / 5) < this.threshold) {
                    gray = 0;
                } else {
                    gray = 255;
                }
                out.setPixel(x, y, Color.argb(alpha, gray, gray, gray));
            }
        }
        return out;
    }


}