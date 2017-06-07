package fr.unice.polytech.si3.ihm.cpsophia.model;

import fr.unice.polytech.si3.ihm.cpsophia.R;

/**
 * @author Francois Melkonian
 */


public enum MagasinType {
    FEMME(R.id.nav_femme),HOMME(R.id.nav_homme),ENFANT(R.id.nav_enfants),TOUT_LES_MAGASINS(R.id.nav_all),CONSOMMABLE(R.id.conso);
    private int id;
    MagasinType(int type){
        id=type;
    }
    public static MagasinType getChosen(int id){
        for (MagasinType mag :
                MagasinType.values()) {
            if (mag.id == id) return mag;
        }
        return null;
    }
}
