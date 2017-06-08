package fr.unice.polytech.si3.ihm.cpsophia.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.unice.polytech.si3.ihm.cpsophia.BottomNavigationManager;
import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.utils.TrajetCalculator;

public class TrajetActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {


    private GoogleMap mMap;
    private boolean added;
    private MarkerOptions moi;
    private LatLng centreCommercial = new LatLng(43.6227876, 7.0469886);
    /*
        La récupération de la position de l'utilisateur prend du temps
     */
    private LatLng actual = new LatLng(43.6155179, 7.0699123);
    private View.OnClickListener launchCall = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_CALL);

            intent.setData(Uri.parse("tel:0497221198"));
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traj);
        findViewById(R.id.tel).setOnClickListener(launchCall);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        findViewById(R.id.pied).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runTrajet(false);
            }
        });
        findViewById(R.id.voiture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runTrajet(true);
            }
        });
        mapFragment.getMapAsync(this);
        new BottomNavigationManager(this, getClass());
    }

    private void runTrajet(boolean type) {
        new TrajetCalculator(findViewById(R.id.estimatedDiv), (TextView) findViewById(R.id.estimated), type).execute(actual);
        if (!added) {
            moi.position(actual);
            mMap.addMarker(moi);
            added = true;
            center();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        added = false;
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        moi = new MarkerOptions().title("Votre position").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double old_latitude = location.getLatitude();
                double old_longitude = location.getLongitude();
                actual = new LatLng(old_latitude, old_longitude);
                center();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                System.out.println(s);
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 100, 10, locationListener);

        mMap.addMarker(new MarkerOptions().position(centreCommercial).title("C@P SOPHIA"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centreCommercial, 15.0f));
    }

    public void center() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(moi.getPosition());
        builder.include(new LatLng(43.6227876, 7.0469886));
        LatLngBounds bounds = builder.build();
        mMap.setLatLngBoundsForCameraTarget(bounds);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.getTitle().equals("yolo"))
            System.out.println("l");
    }
}
