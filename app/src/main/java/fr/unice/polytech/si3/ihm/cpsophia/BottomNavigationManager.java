package fr.unice.polytech.si3.ihm.cpsophia;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import fr.unice.polytech.si3.ihm.cpsophia.model.persistence.MenuEnum;

/**
 * @author Francois Melkonian
 */

public class BottomNavigationManager {
    private Activity context;
    private BottomNavigationView view;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            Class<?> controller = MenuEnum.getController(id);
            Intent intent = new Intent(context, controller);
            context.startActivity(intent);
            context.finish();
            return true;
        }

    };

    public BottomNavigationManager(Activity context, Class<?> contro) {
        this.context = context;
        view = (BottomNavigationView) context.findViewById(R.id.navigation);
        int id = MenuEnum.getResId(contro);
        view.findViewById(id).performClick();
        view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
}
