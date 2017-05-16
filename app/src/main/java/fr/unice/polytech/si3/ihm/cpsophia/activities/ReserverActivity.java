package fr.unice.polytech.si3.ihm.cpsophia.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import fr.unice.polytech.si3.ihm.cpsophia.BottomNavigationManager;
import fr.unice.polytech.si3.ihm.cpsophia.adapters.ExpandableMagasinAdapter;
import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.CapSophia;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.EventManager;

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

    }
}
