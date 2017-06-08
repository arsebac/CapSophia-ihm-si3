package fr.unice.polytech.si3.ihm.cpsophia.model.persistence;

import android.app.Activity;

import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.activities.MagasinsActivity;
import fr.unice.polytech.si3.ihm.cpsophia.activities.PlanActivity;
import fr.unice.polytech.si3.ihm.cpsophia.activities.ReserverActivity;
import fr.unice.polytech.si3.ihm.cpsophia.activities.TrajetActivity;

/**
 * @author Francois Melkonian
 */

public enum MenuEnum {
    RESERVE(R.id.reserve, ReserverActivity.class),
    INTEREST_LIST(R.id.interest_list, MagasinsActivity.class),
    SUR_PLACE(R.id.irl, PlanActivity.class),
    TRAJET(R.id.golo, TrajetActivity.class);
    int resid;
    private Class<? extends Activity> controClass;

    MenuEnum(int id, Class<? extends Activity> controller) {
        resid = id;
        controClass = controller;
    }

    public static Class<?> getController(int id) {
        for (MenuEnum menu :
                values()) {
            if (menu.resid == id) {
                Class<? extends Activity> controClass = menu.controClass;
                return controClass;
            }
        }
        return null;
    }

    public static int getResId(Class<?> c) {
        for (MenuEnum menu :
                values()) {
            if (menu.controClass == c) return menu.resid;
        }
        return 0;
    }
}
