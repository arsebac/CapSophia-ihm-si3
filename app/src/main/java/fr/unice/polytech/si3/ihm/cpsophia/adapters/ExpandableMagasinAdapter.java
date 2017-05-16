package fr.unice.polytech.si3.ihm.cpsophia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.Event;
import fr.unice.polytech.si3.ihm.cpsophia.model.persistence.UserPreferences;


/**
 * @author Francois Melkonian
 */

public class ExpandableMagasinAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<Magasin> parentDataSource;
    private final Map<Magasin, List<Event>> childDataSource;

    public ExpandableMagasinAdapter(Context context, List<Magasin> childParent, Map<Magasin, List<Event>> child) {

        this.context = context;

        this.parentDataSource = childParent;

        this.childDataSource = child;

    }

    @Override
    public int getGroupCount() {
        return parentDataSource.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childDataSource.get(parentDataSource.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {

        return parentDataSource.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.childDataSource.get(parentDataSource.get(i)).get(i1);

    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.event_menu_magasin, viewGroup, false);
        }
        Magasin parentHeader = (Magasin) getGroup(i);
        ((TextView)view.findViewById(R.id.magasinName)).setText(parentHeader.getName());
        if(parentHeader.isHaveImage()){
            ((ImageView)view.findViewById(R.id.logo)).setImageBitmap(parentHeader.getImage(context));
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){

            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.event_item, viewGroup, false);

        }
        final Event event = (Event)getChild(i, i1);
        ((Switch) view.findViewById(R.id.participate)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println("c moii");
                if(b){
                    UserPreferences.addEvent(event,context);
                }else{
                    UserPreferences.removeEvent(event,context);
                }
            }
        });

        ((TextView) view.findViewById(R.id.eventName)).setText(event.getName());
        ((TextView) view.findViewById(R.id.day)).setText(event.getDay());
        if(event.hasEnd()){
            ((TextView) view.findViewById(R.id.dtstart)).setText(event.getStart());
            ((TextView) view.findViewById(R.id.dtEnd)).setText(event.getEnd());
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
