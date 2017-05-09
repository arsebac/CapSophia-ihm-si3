package fr.unice.polytech.si3.ihm.cpsophia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.Event;
import fr.unice.polytech.si3.ihm.cpsophia.model.persistence.UserPreferences;

/**
 * @author Francois Melkonian
 */


/**
 * @author Francois Melkonian
 */

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(Context context, int resource, List<Event> objects) {
        super(context, resource, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Event event = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.event_item_mini, null);
        }
        ((TextView) convertView.findViewById(R.id.eventName)).setText(event.getName());
        ((TextView) convertView.findViewById(R.id.day)).setText(event.getDay());
        if(event.hasEnd()){
            ((TextView) convertView.findViewById(R.id.dtstart)).setText(event.getStart());
            ((TextView) convertView.findViewById(R.id.dtEnd)).setText(event.getEnd());
        }
        return convertView;
    }
}
