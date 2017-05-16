package fr.unice.polytech.si3.ihm.cpsophia.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.Event;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author Francois Melkonian
 */

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Event event = (Event) intent.getSerializableExtra("event");
        Toast.makeText(context, event.getName(),Toast.LENGTH_SHORT).show();
        System.out.println("YOLOLOLOLO");

        Intent intent2 = new Intent(context, NotificationReceiver.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);


        Notification n  = new Notification.Builder(context)
                .setContentTitle("Un évènement de "+event.getMagasin().getName()+" va commencer !")
                .setContentText(event.getName())
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();


        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
