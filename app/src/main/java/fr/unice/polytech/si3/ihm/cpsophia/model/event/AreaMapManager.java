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
 * Classe permettant de gérer la carte avec plusieurs zones différentes
 * @author Francois Melkonian
 */

public class AreaMapManager {
    private Bitmap map;


    public AreaMapManager(Activity context) {
        map = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.color);
    }

    /**
     * Décode la position d'un clic sur la carte pour trouver sur quel magasin le clic a été effectué
     * @param view L'image sur laquelle le clic a été effectué
     * @param motionEvent les informations brutes du clic
     * @return l'identifiant du magasin sélectionné
     */
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

