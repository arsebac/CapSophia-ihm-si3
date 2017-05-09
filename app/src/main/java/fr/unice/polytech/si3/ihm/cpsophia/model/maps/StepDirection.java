package fr.unice.polytech.si3.ihm.cpsophia.model.maps;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Francois Melkonian
 */

public class StepDirection {
    private String distanceLabel,durationLabel;
    private int distanceValue,durationValue;
    private String desc;
    public StepDirection(JSONObject stepRaw) throws JSONException {
        JSONObject distanceJson = stepRaw.getJSONObject("distance");
        distanceLabel = distanceJson.getString("text");
        distanceValue = distanceJson.getInt("value");

        JSONObject durationJson = stepRaw.getJSONObject("duration");
        durationLabel = durationJson.getString("text");
        durationValue = durationJson.getInt("value");

        desc = explications(stepRaw.getString("html_instructions"));

    }
    private String explications(String encoded){
        String str = encoded.split(" ")[0];
        str = str.replace("\\","");
        String[] arr = str.split("u");
        String text = "";
        for(int i = 1; i < arr.length; i++){
            int hexVal = Integer.parseInt(arr[i], 16);
            text += (char)hexVal;
        }
        return text;
    }
}
