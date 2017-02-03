package moraes.danillo.teste2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.graphics.Palette;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danillom on 03/02/2017.
 */

public class ColorP {
    int color;
    float h[] = new float[3];
    int colors[] = new int[2];

    public ColorP (int mColor) {
        color = mColor;
        android.graphics.Color.colorToHSV(color, h);
    }

    public ColorP () {

    }

    public int getPalttecolor (float o) {
        int new_color;
        h[2] = o;

        new_color = android.graphics.Color.HSVToColor(h);

        return new_color;
    }

    public int[] getDarkColor () {
        if (h[2] >= 0.7f) {
            colors[0] = color;
            h[2] = h[2] - 0.1f;
            colors[1] = Color.HSVToColor(h);
        } else {
            colors[1] = color;
            h[2] = h[2] + 0.1f;
            colors[0] = Color.HSVToColor(h);
        }

        return colors;
    }

    public int[] getBitmapPalette (Bitmap bitmap) {
        int colors[] = new int[6];
        Palette palette;

        palette = Palette.from(bitmap).generate();
        int i_default = 0x000000;
        colors[0] = palette.getVibrantColor(i_default);
        colors[1] = palette.getLightVibrantColor(i_default);
        colors[2] = palette.getDarkVibrantColor(i_default);
        colors[3] = palette.getMutedColor(i_default);
        colors[4] = palette.getLightMutedColor(i_default);
        colors[5] = palette.getDarkMutedColor(i_default);

        return colors;
    }

    public List getSwatch (Bitmap bitmap) {
        List<Palette.Swatch> list;
        Palette palette;

        palette = Palette.from(bitmap).generate();

        list = palette.getSwatches();
        return list;
    }
}
