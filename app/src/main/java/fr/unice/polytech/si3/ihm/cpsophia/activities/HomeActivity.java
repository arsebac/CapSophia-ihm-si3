package fr.unice.polytech.si3.ihm.cpsophia.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.unice.polytech.si3.ihm.cpsophia.BottomNavigationManager;
import fr.unice.polytech.si3.ihm.cpsophia.adapters.EventAdapter;
import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.CapSophia;
import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.AreaMapManager;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.Event;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.EventManager;

public class HomeActivity extends AppCompatActivity {


    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ListView v = (ListView) findViewById(R.id.listActivites);
        adapter = new EventAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<Event>(),true);
        v.setAdapter(adapter);
        final AreaMapManager mapManager = new AreaMapManager(this);
        new BottomNavigationManager(this,getClass());
        EventManager.init();
        findViewById(R.id.plan).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int idMagasin = mapManager.decode(view,motionEvent);
                System.out.println("Magasin choisi : "+idMagasin);
                if (idMagasin==0){
                    FrameLayout parent = ((FrameLayout) findViewById(R.id.info));
                    parent.removeAllViews();
                    adapter.clear();
                }else {
                    Magasin magasin = CapSophia.getMagasinById(idMagasin);
                    adapter.clear();
                    adapter.addAll(EventManager.get(magasin));
                    displayDetail(magasin);
                }
                return false;
            }
        });

    }
    private void displayDetail(Magasin magasin){
        FrameLayout parent = ((FrameLayout) findViewById(R.id.info));
        parent.removeAllViews();
        View v = View.inflate(this,R.layout.magasin_item,null);
        v.findViewById(R.id.infosLayout).setVisibility(View.INVISIBLE);
        ((TextView) v.findViewById(R.id.magasinName)).setText(magasin.getName());
        ((TextView) v.findViewById(R.id.desc)).setText(magasin.getDesc());
        if(magasin.isHaveImage()){
            ((ImageView) v.findViewById(R.id.logo)).setImageBitmap(magasin.getImage(this));
        }
        parent.addView(v);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

}
