package fr.unice.polytech.si3.ihm.cpsophia.model.utils;

import java.util.Calendar;

/**
 * @author Francois Melkonian
 */

public class CalendarHelper {
    public static Calendar create(int month, int day, int hour){
        return create(month,day,hour,0,0);
    }

    private static Calendar create(int month,int day,int hour,int min,int secondes){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,month,day,hour,min,secondes);
        return calendar;
    }
}
