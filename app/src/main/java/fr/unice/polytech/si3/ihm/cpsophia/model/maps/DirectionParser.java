package fr.unice.polytech.si3.ihm.cpsophia.model.maps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Francois Melkonian
 */

public class DirectionParser {
    private JSONObject responseRaw;
    private JSONArray routes;
    private boolean isComplete;
    public DirectionParser(String response) {
        try {
            this.responseRaw = new JSONObject(response);
            isComplete = true;
            routes = responseRaw.getJSONArray("routes");
        } catch (JSONException e) {
            e.printStackTrace();
            isComplete = false;
        }
    }
}
