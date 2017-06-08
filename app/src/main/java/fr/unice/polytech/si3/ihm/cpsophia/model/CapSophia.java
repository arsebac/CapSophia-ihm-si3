package fr.unice.polytech.si3.ihm.cpsophia.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.unice.polytech.si3.ihm.cpsophia.R;

/**
 * Permet de représenter les données du centre commercial :
 * Les magasins présents, leur localisation, ...
 *
 * @author Francois Melkonian
 */

public class CapSophia {
    private static final String PREFS_NAME = "user_preferences";
    private static List<Magasin> magasins = new ArrayList<>();

    static {
        Magasin e = new Magasin(0, "H&M Homme", Collections.singletonList(MagasinType.HOMME), "H&M saura vous dénicher le meilleur prix pour vos chaussures", R.drawable.hh_logo);
        magasins.add(
                e
        );
        magasins.add(new Magasin(1, "Suwway Restauration", Arrays.asList(MagasinType.FEMME, MagasinType.CONSOMMABLE, MagasinType.ENFANT, MagasinType.HOMME), "Créez votre plat de pâtes ou pizza personnalisé", R.drawable.subway_icon));
        magasins.add(new Magasin(2, "Camaieu", Arrays.asList(MagasinType.ENFANT, MagasinType.FEMME), "Camaïeu est une entreprise de distribution textile focalisée sur le segment de la femme de 20 à 60 ans avec un positionnement mode milieu de gamme, dirigée par Élisabeth Cunin", R.drawable.camaieu_logo));
        magasins.add(new Magasin(3, "Celio", Collections.singletonList(MagasinType.HOMME), "Celio est une entreprise française de prêt-à-porter masculin fondée par Maurice Grosman et son épouse. Elle fait partie des principaux acteurs de son secteur.", R.drawable.celio_icon));
        magasins.add(new Magasin(4, "Restaurant Pacher", Arrays.asList(MagasinType.FEMME, MagasinType.CONSOMMABLE, MagasinType.ENFANT, MagasinType.HOMME), "Restaurant Pacher saura vous servir la meilleur nourriture en boite que vous ayaient jamais gouté", R.drawable.restaurant_icone));
        magasins.add(new Magasin(5, "Apple", Collections.singletonList(MagasinType.FEMME), "Magasin Apple aux lignes modernes et épurées où sont vendus des iPhones, des iPads et plus encore.", R.drawable.apple_icon));
        magasins.add(new Magasin(6, "Moustache club", Collections.singletonList(MagasinType.HOMME), "Venez admirer des moustaches toute la journée !", R.drawable.moustache_logo));
        magasins.add(new Magasin(7, "Centre pokémon", Collections.singletonList(MagasinType.ENFANT), "Achetez et jouez avec le meilleur jeu de carte jamais crée (selon Pikachu)", R.drawable.pokemon_logo));
    }

    private CapSophia ourInstance = new CapSophia();

    private CapSophia() {
    }

    /**
     * Retourne la liste des magasins du type filtre
     *
     * @param filtre le type des magasins à retourner
     */
    public static List<Magasin> getFiltredMagasin(MagasinType filtre) {
        if (filtre == MagasinType.TOUT_LES_MAGASINS) return magasins;
        List<Magasin> result = new ArrayList<>();
        for (Magasin mag : magasins) {
            if (mag.isType(filtre)) {
                result.add(mag);
            }
        }
        return result;
    }

    public static Magasin getMagasinById(int id) {
        for (Magasin mag :
                magasins) {
            if (mag.getId() == id) return mag;
        }
        return null;
    }

    public static List<Magasin> getMagasins() {
        return new ArrayList<>(magasins);
    }

    public static int size() {
        return magasins.size();
    }

}
