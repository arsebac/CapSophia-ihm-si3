package fr.unice.polytech.si3.ihm.cpsophia.model.utils;

import android.graphics.Color;

/**
 * @author Francois Melkonian
 */

public enum  MapColor {
    RED(Color.RED),GREEN(Color.GREEN),BLUE(Color.BLUE),
    CYAN_BLUE(1,255,255),YO(128,0,128),PINK(255,0,128),CLAIR_YELLOW(181,230,29);
    private int r,g,b;
    MapColor(int i, int i1, int i2) {
        r = i;
        g = i1;
        b = i2;
    }

    MapColor(int color) {
        r = Color.red(color);
        g = Color.green(color);
        b = Color.blue(color);
    }
    public static int get(int color){
        int i=0;
        for (MapColor m :
                values()) {
            if( same(m,color)){
                return i;
            }
            i++;
        }
        return 0;
    }
    private static boolean same(MapColor m, int color){
        boolean a = Math.abs(Color.red(color) - m.r) < 5;
        boolean b = Math.abs(Color.green(color) - m.g) < 5;
        boolean c = Math.abs(Color.blue(color) - m.b) < 5;
        return a&&b&&c;
    }
}
