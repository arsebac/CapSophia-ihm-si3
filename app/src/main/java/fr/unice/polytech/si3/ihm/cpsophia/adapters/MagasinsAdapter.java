package fr.unice.polytech.si3.ihm.cpsophia.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.si3.ihm.cpsophia.R;
import fr.unice.polytech.si3.ihm.cpsophia.activities.DetailMagasinActivity;
import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.EventManager;
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
        if (magasin.isHaveImage()) {
            ((ImageView) convertView.findViewById(R.id.logo)).setImageBitmap(magasin.getImage(parent.getContext()));
        }
        ((TextView) convertView.findViewById(R.id.desc)).setText(magasin.getDesc());
        final boolean followed = UserPreferences.isFollowed(magasin);
        ((Switch) convertView.findViewById(R.id.toggleNotification)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    UserPreferences.addFollow(magasin);
                    UserPreferences.addNotifications(EventManager.get(magasin), getContext());
                } else {
                    UserPreferences.deFollow(magasin);
                }
                UserPreferences.save(getContext());
            }
        });
        if (followed) {
            ((Switch) convertView.findViewById(R.id.toggleNotification)).setChecked(true);
        } else {
            ((Switch) convertView.findViewById(R.id.toggleNotification)).setChecked(false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DetailMagasinActivity.class);
                i.putExtra("magasin", magasin);
                getContext().startActivity(i);

            }
        });
        return convertView;
    }
}
