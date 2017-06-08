package fr.unice.polytech.si3.ihm.cpsophia.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.unice.polytech.si3.ihm.cpsophia.model.event.Event;

/**
 * @author Francois Melkonian
 */

public class Receiver extends BroadcastReceiver {
    private Event event;

    public Receiver() {
    }

    public Receiver(Event event) {
        this.event = event;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        EventNotification.notify(context, event.getMagasin().getName(), event.getName());
    }
}
