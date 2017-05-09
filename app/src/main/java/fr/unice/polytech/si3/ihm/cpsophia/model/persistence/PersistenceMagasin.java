package fr.unice.polytech.si3.ihm.cpsophia.model.persistence;

/**
 * @author Francois Melkonian
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fr.unice.polytech.si3.ihm.cpsophia.model.Magasin;
import fr.unice.polytech.si3.ihm.cpsophia.model.MagasinType;

public class PersistenceMagasin {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "magasins.db";

    private static final String TABLE_MAGASIN = "table_magasin";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_DESC = "desc";
    private static final int NUM_COL_DESC = 1;
    private static final String COL_NAME = "name";
    private static final int NUM_COL_NAME = 2;

    private SQLiteDatabase bdd;

    private MagasinsSQLHelper maBaseSQLite;

    public PersistenceMagasin(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new MagasinsSQLHelper(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertLivre(Magasin magasin){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        values.put(COL_ID, magasin.getId());
        values.put(COL_DESC, magasin.getDesc());
        values.put(COL_NAME, magasin.getName());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_MAGASIN, null, values);
    }

//    public int updateLivre(int id, Livre livre){
//        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
//        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
//        ContentValues values = new ContentValues();
//        values.put(COL_ISBN, livre.getIsbn());
//        values.put(COL_TITRE, livre.getTitre());
//        return bdd.update(TABLE_MAGASIN, values, COL_ID + " = " +id, null);
//    }

    public int removeLivreWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_MAGASIN, COL_ID + " = " +id, null);
    }

//    public Livre getLivreWithTitre(String titre){
//        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
//        Cursor c = bdd.query(TABLE_MAGASIN, new String[] {COL_ID, COL_ISBN, COL_TITRE}, COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null);
//        return cursorToLivre(c);
//    }

    //Cette méthode permet de convertir un cursor en un livre
    private Magasin cursorToMagasin(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Magasin magasin = new Magasin(c.getInt(NUM_COL_ID),c.getString(NUM_COL_NAME),new ArrayList<MagasinType>(),c.getString(NUM_COL_DESC));
        c.close();

        //On retourne le livre
        return magasin;
    }
    private boolean getCount(){
        Cursor c = bdd.query(TABLE_MAGASIN, new String[] {COL_ID, COL_DESC, COL_NAME},null, null, null, null, null);
        return c.getCount() == 0;
    }
}