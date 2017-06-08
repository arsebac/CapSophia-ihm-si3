package fr.unice.polytech.si3.ihm.cpsophia.model.utils;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Permet d'utiliser l'API Google Map Direction pour trouver le temp que va mettre la personne pour aller au centre commercial
 * Selon si il est à pied ou en voiture
 *
 * @author Francois Melkonian
 */

public class TrajetCalculator extends AsyncTask<LatLng, Void, String> {
    private View hiddenParent;
    private TextView label;
    private boolean isDriving;

    public TrajetCalculator(View hiddenParent, TextView label, boolean isDriving) {
        this.hiddenParent = hiddenParent;
        this.label = label;
        this.isDriving = isDriving;
    }

    @Override
    protected void onPostExecute(String s) {
        hiddenParent.setVisibility(View.VISIBLE);
        try {
            JSONArray o = new JSONObject(s).getJSONArray("routes");
            int secs = o.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("duration").getInt("value");
            int min = secs / 60;
            int sec = secs % 60;
            label.setText(min + "m" + sec);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(LatLng... lats) {
        try {
            LatLng departure = lats[0];
            String url = "https://maps.googleapis.com/maps/api/directions/json";
            url += "?origin=" + departure.latitude + "," + departure.longitude;
            url += "&destination=2+Rue+Soutrane,+06560+Valbonne+france&traffic_model=best_guess&key=AIzaSyB4FVN3OHgRHtGdW9MfGpt8WOsMif-sklk";
            url += "&departure_time=" + new Date().getTime();
            if (!isDriving) url += "&mode=walking";
            URL finalUrl = new URL(url);
            String res = "";
            HttpURLConnection urlConnection = (HttpURLConnection)
                    finalUrl.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                res += line + "\n";
            }
            return res;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }
}
