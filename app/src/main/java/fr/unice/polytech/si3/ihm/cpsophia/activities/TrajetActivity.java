package fr.unice.polytech.si3.ihm.cpsophia.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.unice.polytech.si3.ihm.cpsophia.BottomNavigationManager;
import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.utils.TrajetCalculator;

public class TrajetActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private boolean added;
    private MarkerOptions moi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traj);
        findViewById(R.id.tel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:0497221198"));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        new BottomNavigationManager(this, getClass());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        added = false;
        mMap = googleMap;
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

         moi = new MarkerOptions().title("Votre position");
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener(){
            @Override
            public void onLocationChanged(Location location) {
                double old_latitude = location.getLatitude();
                double old_longitude = location.getLongitude();
                LatLng actual = new LatLng(old_latitude, old_longitude);
                moi.position(actual);
                if(!added){
                    mMap.addMarker(moi);
                    added =true;
                    new TrajetCalculator(findViewById(R.id.estimatedDiv), (TextView) findViewById(R.id.estimated),findViewById(R.id.itineraireButton)).execute(actual);
                }
                center();
            }
            @Override public void onStatusChanged(String s, int i, Bundle bundle) { } @Override  public void onProviderEnabled(String s) {            }            @Override            public void onProviderDisabled(String s) {            }        };
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 100, 10, locationListener);

        LatLng sydney = new LatLng(43.6227876, 7.0469886);
        System.out.println("sydney : "+sydney);
        mMap.addMarker(new MarkerOptions().position(sydney).title("C@P SOPHIA"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 9.0f));
    }
    public void center(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(moi.getPosition());
        builder.include(new LatLng(43.6227876, 7.0469886));
        LatLngBounds bounds = builder.build();
        mMap.setLatLngBoundsForCameraTarget(bounds);
    }
}
