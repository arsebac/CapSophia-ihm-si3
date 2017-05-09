package fr.unice.polytech.si3.ihm.cpsophia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.persistence.UserPreferences;

/**
 * @author Francois Melkonian
 */

public class MagasinsAdapter extends ArrayAdapter<Magasin> {
    public MagasinsAdapter(Context context, int resource, List<Magasin> objects) {
        super(context, resource, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Magasin magasin = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.magasin_item, null);
        }
        ((TextView) convertView.findViewById(R.id.magasinName)).setText(magasin.getName());
        if(magasin.isHaveImage()){
            ((ImageView) convertView.findViewById(R.id.logo)).setImageBitmap(magasin.getImage(parent.getContext()));
        }
        ((TextView) convertView.findViewById(R.id.desc)).setText(magasin.getDesc());
        final boolean followed = UserPreferences.isFollowed(magasin);
        convertView.findViewById(R.id.toggleNotification).setOnClickListener(new View.OnClickListener() {
            boolean activated = followed;
            @Override
            public void onClick(View view) {
                activated = !activated;
                if(activated){
                    UserPreferences.addFollow(magasin);
                }else{
                    UserPreferences.deFollow(magasin);
                }
            }
        });
        if(followed){
            ((Switch) convertView.findViewById(R.id.toggleNotification)).setChecked(true);
        }else{
            ((Switch) convertView.findViewById(R.id.toggleNotification)).setChecked(false);
        }
        return convertView;
    }
}
