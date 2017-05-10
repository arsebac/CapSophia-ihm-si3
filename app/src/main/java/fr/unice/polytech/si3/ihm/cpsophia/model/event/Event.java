package fr.unice.polytech.si3.ihm.cpsophia.model.event;

import android.graphics.Bitmap;

import java.util.Calendar;

import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;

/**
 * Représente un évènement proposé par un magasin
 * @author Francois Melkonian
 */

public abstract class Event {
    private String name;
    private Magasin magasin;
    private int resIdImage;

    public Event(String name, Magasin magasin) {
        this.name = name;
        this.magasin = magasin;
    }

    public String getName() {
        return name;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public abstract String getDay();
    public abstract boolean hasEnd();

    public String getStart(){ return "";}
    public String getEnd(){ return "";}
}
