package fr.unice.polytech.si3.ihm.cpsophia.model.persistence;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ihm.cpsophia.model.CapSophia;
import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.MagasinType;

/**
 * @author Francois Melkonian
 */

public class UserPreferences {
    private static final String PREFS_NAME = "user_preferences";
    private static final UserPreferences ourInstance = new UserPreferences();
    private static final List<Magasin> followedMagasin = new ArrayList<>();
    private static final List<MagasinType> followedType = new ArrayList<>();
    static UserPreferences getInstance() {
        return ourInstance;
    }

    private UserPreferences() {
    }
    public static void addFollow(Magasin mag){
        if(!followedMagasin.contains(mag))
            followedMagasin.add(mag);
    }
    public static void deFollow(Magasin mag){
        if(followedMagasin.contains(mag)){
            followedMagasin.remove(mag);
        }
    }
    public static boolean isFollowed(Magasin mag){
        return followedMagasin.contains(mag);
    }

    public static boolean isFollowed(MagasinType type) {
        return followedType.contains(type);
    }

    public static void deFollow(MagasinType type) {
        if(followedType.contains(type)){
            followedType.remove(type);
        }
    }
    public static void addFollow(MagasinType type){
        if(!followedType.contains(type))
            followedType.add(type);
    }
    public static void save(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("if_exist", true);
        for (Magasin mag :
                CapSophia.getMagasins()) {
            editor.putBoolean("mag_" + mag.getId(), UserPreferences.isFollowed(mag));
        }
        editor.apply();
    }
    public static void load(Activity context){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("if_exist", false);

        if (silent) {
            for (Magasin mag : CapSophia.getMagasins()) {
                boolean followed = settings.getBoolean("mag_" + mag.getId(), false);
                if (followed) {
                    UserPreferences.addFollow(mag);
                }
            }
        }
    }
}
