package fr.unice.polytech.si3.ihm.cpsophia.model.persistence;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ihm.cpsophia.model.CapSophia;
import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.MagasinType;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.Event;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.EventManager;
import fr.unice.polytech.si3.ihm.cpsophia.notification.Receiver;

/**
 * @author Francois Melkonian
 */

public class UserPreferences {
    private static final String PREFS_NAME = "user_preferences";
    private static final UserPreferences ourInstance = new UserPreferences();
    private static final List<Magasin> followedMagasin = new ArrayList<>();
    private static final List<MagasinType> followedType = new ArrayList<>();
    public static final List<Event> events = new ArrayList<>();
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

    public static void addEvent(Event event, Context context) {
        if(!events.contains(event)){
            events.add(event);
            addNotification(event,context);
        }
    }

    public static void removeEvent(Event event, Context context) {
        if(events.contains(event)){
            events.remove(event);
        }
        // TODO : supprimer l'event des alarmes

    }

    public static void addNotifications(List<Event >event, Context context){
        for (Event e :
                event) {
            addNotification(e, context);
        }

    }
    public static void addNotification(Event event, Context context){
        AlarmManager alarms = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Receiver receiver = new Receiver();
        IntentFilter filter = new IntentFilter("ALARM_ACTION");
        context.registerReceiver(receiver, filter);

        Intent intent = new Intent("ALARM_ACTION");
        intent.putExtra("event",event);
        PendingIntent operation = PendingIntent.getBroadcast(context, 0, intent, 0);
        // I choose 3s after the launch of my application
        alarms.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+3000, operation) ;
    }
}
