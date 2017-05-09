package fr.unice.polytech.si3.ihm.cpsophia.model.event;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.utils.MapColor;

/**
 * @author Francois Melkonian
 */

public class AreaMapManager {
    private final Activity context;
    private Bitmap map;

    public AreaMapManager(Activity context) {
        this.context = context;
        map = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.color);
    }

    public int decode(View view, MotionEvent motionEvent) {
        final int evX = (int) motionEvent.getX();
        final int evY = (int) motionEvent.getY();
        int x =map.getWidth() *  evX / view.getWidth();
        int y =map.getHeight() *  evY / view.getHeight();
        int pixel = map.getPixel(x,y);
        Log.d("PixelApp", "decode: "+Color.red(pixel)+","+Color.green(pixel)+","+Color.blue(pixel));
        return MapColor.get(pixel);
    }
}

