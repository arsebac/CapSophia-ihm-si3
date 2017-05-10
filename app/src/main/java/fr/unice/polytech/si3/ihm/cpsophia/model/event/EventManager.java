package fr.unice.polytech.si3.ihm.cpsophia.model.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ihm.cpsophia.model.CapSophia;
import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.utils.CalendarHelper;

/**
 * Données statiques des évènements de notre centre commercial
 *
 * @author Francois Melkonian
 */

public class EventManager {
	private static Map<Magasin, List<Event>> events = new HashMap<>();
	private EventManager ourInstance = new EventManager();

	private EventManager() {
	}

	public static void init() {
		for (Magasin mag :
				CapSophia.getMagasins()) {
			events.put(mag, new ArrayList<Event>());
		}
		Magasin magasinById = CapSophia.getMagasinById(1);
		List<Event> event = events.get(magasinById);
		event.add(new TemporaryEvent("10% sur les pizza sans tomates !", magasinById, CalendarHelper.create(4, 21, 20), CalendarHelper.create(4, 21, 22)));
		event.add(new TemporaryEvent("10% sur les pizza sans tomates !", magasinById, CalendarHelper.create(4, 21, 20), CalendarHelper.create(4, 21, 22)));
		event.add(new TemporaryEvent("10% sur les pizza sans tomates !", magasinById, CalendarHelper.create(4, 21, 20), CalendarHelper.create(4, 21, 22)));
		event.add(new TemporaryEvent("10% sur les pizza sans tomates !", magasinById, CalendarHelper.create(4, 21, 20), CalendarHelper.create(4, 21, 22)));
		event.add(new TemporaryEvent("10% sur les pizza sans tomates !", magasinById, CalendarHelper.create(4, 21, 20), CalendarHelper.create(4, 21, 22)));
		event.add(new TemporaryEvent("Les ingrédients des plats servis seront choisis par référendum", magasinById, CalendarHelper.create(4, 22, 14), CalendarHelper.create(4, 22, 18)));
	}

	/**
	 * Récupère les évènements d'un magasin en particulier
	 *
	 * @param magasin le magasin choisi
	 * @return la liste des évènements
	 */
	public static List<Event> get(Magasin magasin) {
		if (events.containsKey(magasin)) {
			return events.get(magasin);
		}
		return new ArrayList<>();
	}

	/**
	 * Récupère tous les évènements du centre commercial
	 * @return la liste des évènements pour chaque magasin
	 */
	public static Map<Magasin, List<Event>> getDB() {
		init();
		return events;
	}
}
