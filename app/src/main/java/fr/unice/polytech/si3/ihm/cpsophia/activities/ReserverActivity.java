package fr.unice.polytech.si3.ihm.cpsophia.activities;

import android.app.AlarmManager;
import android.app.ExpandableListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import fr.unice.polytech.si3.ihm.cpsophia.BottomNavigationManager;
import fr.unice.polytech.si3.ihm.cpsophia.ExpandableMagasinAdapter;
import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.CapSophia;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.EventManager;
import fr.unice.polytech.si3.ihm.cpsophia.notification.Receiver;

public class ReserverActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver);
        new BottomNavigationManager(this,getClass());
        getSupportActionBar().hide();
        ExpandableListView view = (ExpandableListView) findViewById(R.id.listReserve);
        ExpandableMagasinAdapter exp = new ExpandableMagasinAdapter(this, CapSophia.getMagasins(), EventManager.getDB());
        view.setAdapter(exp);
        AlarmManager alarms = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Receiver receiver = new Receiver();
        IntentFilter filter = new IntentFilter("ALARM_ACTION");
        registerReceiver(receiver, filter);

        Intent intent = new Intent("ALARM_ACTION");
        intent.putExtra("param", "My scheduled action");
        PendingIntent operation = PendingIntent.getBroadcast(this, 0, intent, 0);
        // I choose 3s after the launch of my application
        alarms.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+3000, operation) ;
    }
}
