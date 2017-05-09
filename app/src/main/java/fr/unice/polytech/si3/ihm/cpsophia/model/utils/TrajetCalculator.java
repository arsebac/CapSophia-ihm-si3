package fr.unice.polytech.si3.ihm.cpsophia.model.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @author Francois Melkonian
 */

public class TrajetCalculator extends AsyncTask<LatLng, Void, String> {
    private final Button button;
    private View hiddenParent;
    private TextView label;

    @Override
    protected void onPostExecute(String s) {
        hiddenParent.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);

        System.out.println(s);
        try {
            JSONArray o = new JSONObject(s).getJSONArray("routes");
            int secs = o.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("duration").getInt("value");
            System.out.println(secs);
            int min = secs/60;
            int sec = secs%60;
            label.setText(min+"m"+sec);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public TrajetCalculator(View hiddenParent, TextView label, View viewById) {
        this.hiddenParent = hiddenParent;
        this.label = label;
        this.button = (Button) viewById;
    }

    @Override
    protected String doInBackground(LatLng... lats) {
        try {
            LatLng departure = lats[0];
            String url = "https://maps.googleapis.com/maps/api/directions/json";
            url += "?origin=" + departure.latitude + "," + departure.longitude;
            url += "&destination=2+Rue+Soutrane,+06560+Valbonne+france&traffic_model=best_guess&key=AIzaSyB4FVN3OHgRHtGdW9MfGpt8WOsMif-sklk";
            url += "&departure_time=" + new Date().getTime();
            URL finalUrl = new URL(url);
            String res ="";
            HttpURLConnection urlConnection = (HttpURLConnection)
                    finalUrl.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            while((line = reader.readLine()) != null){
                res += line + "\n";
            }
            return res;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }
}
