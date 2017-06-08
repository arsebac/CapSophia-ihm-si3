package fr.unice.polytech.si3.ihm.cpsophia.model.utils;

import java.util.Calendar;

/**
 * @author Francois Melkonian
 */

public class CalendarHelper {
    public static Calendar create(int month, int day, int hour) {
        return create(month, day, hour, 0, 0);
    }

    public static Calendar create(int month, int day, int hour, int min, int secondes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.SECOND, secondes);
        return calendar;
    }
}
