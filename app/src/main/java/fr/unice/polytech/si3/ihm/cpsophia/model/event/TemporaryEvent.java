package fr.unice.polytech.si3.ihm.cpsophia.model.event;

import java.util.Calendar;

import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;

/**
 * @author Francois Melkonian
 */

public class TemporaryEvent extends Event {
    private Calendar date;
    private Calendar dtend;
    public TemporaryEvent(String name, Magasin magasin,Calendar dtstart,Calendar dtend) {
        super(name, magasin);
        date = dtstart;
        this.dtend = dtend;
    }

    @Override
    public String getStart() {
        return date.get(Calendar.HOUR_OF_DAY)+"h"+date.get(Calendar.MINUTE);
    }
    @Override
    public String getEnd() {
        int i = dtend.get(Calendar.MINUTE);
        return dtend.get(Calendar.HOUR_OF_DAY)+"h"+ (i<9?"":i);
    }
    @Override
    public String getDay() {
        String[] mois = {"janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre"};
        return date.get(Calendar.DAY_OF_MONTH)+ " "+ mois[date.get(Calendar.MONTH)];
    }

    @Override
    public boolean hasEnd() {
        return true;
    }
}
