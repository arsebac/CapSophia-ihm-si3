package fr.unice.polytech.si3.ihm.cpsophia.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import fr.unice.polytech.si3.ihm.cpsophia.EventAdapter;
import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.Event;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.EventManager;

import java.util.ArrayList;

public class DetailMagasinActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_magasin);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		EventManager.init();
		Magasin magasin = (Magasin) getIntent().getSerializableExtra("magasin");
		((TextView) findViewById(R.id.name_magasin)).setText(magasin.getName());
		((ImageView) findViewById(R.id.icon_magasin)).setImageResource(magasin.getImageId());
		((TextView) findViewById(R.id.desc)).setText(magasin.getDesc());
		ListView v = (ListView) findViewById(R.id.list_event);
		System.out.println(EventManager.get(magasin));
		EventAdapter adapter = new EventAdapter(this, android.R.layout.simple_list_item_1, EventManager.get(magasin));
		v.setAdapter(adapter);

	}

}
