package fr.unice.polytech.si3.ihm.cpsophia.activities;

import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.si3.ihm.cpsophia.BottomNavigationManager;
import fr.unice.polytech.si3.ihm.cpsophia.adapters.MagasinsAdapter;
import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.CapSophia;
import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.MagasinType;
import fr.unice.polytech.si3.ihm.cpsophia.model.persistence.UserPreferences;

public class MagasinsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView selectedFilter;
    private MagasinsAdapter selected;
    private Switch toggleFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasins);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        selectedFilter = ((TextView) findViewById(R.id.filtreSelected));
        toggleFilter = ((Switch) findViewById(R.id.toggleFilter));
        UserPreferences.load(this);
        ListView v = ((ListView) findViewById(R.id.magasinsList));
        selected = new MagasinsAdapter(this, android.R.layout.simple_list_item_1, CapSophia.getMagasins());
        v.setAdapter(selected);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setActualFilter(MagasinType.TOUT_LES_MAGASINS);
        new BottomNavigationManager(this,getClass());

    }
    private void setActualFilter(final MagasinType type){
        final boolean followedFilter = UserPreferences.isFollowed(type);
        toggleFilter.setChecked(followedFilter);
        if(followedFilter){
            toggleFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserPreferences.deFollow(type);
                    UserPreferences.save(getApplicationContext());
                }
            });
        }else{
            toggleFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserPreferences.addFollow(type);
                    UserPreferences.save(getApplicationContext());
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        MagasinType chosen = MagasinType.getChosen(id);
        selectedFilter.setText(chosen.toString());
        selected.clear();
        setActualFilter(chosen);
        List<Magasin> newSelected = CapSophia.getFiltredMagasin(chosen);
        selected.addAll(newSelected);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
