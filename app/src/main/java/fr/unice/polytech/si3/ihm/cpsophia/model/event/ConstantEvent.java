package fr.unice.polytech.si3.ihm.cpsophia.model.event;

import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;

/**
 * Représente une proposition d'un magasin n'étant pas limité dans le temps
 *
 * @author Francois Melkonian
 */

public class ConstantEvent extends Event {
    public ConstantEvent(String name, Magasin magasin) {
        super(name, magasin);

    }


    @Override
    public String getDay() {
        return "Tout les jours";
    }

    @Override
    public boolean hasEnd() {
        return false;
    }
}
