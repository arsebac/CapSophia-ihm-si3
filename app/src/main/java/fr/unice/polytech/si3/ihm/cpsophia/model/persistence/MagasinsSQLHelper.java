package fr.unice.polytech.si3.ihm.cpsophia.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Francois Melkonian
 */

public class MagasinsSQLHelper extends SQLiteOpenHelper {
    private static final String TABLE_MAGASIN = "table_magasin";
    private static final String COL_ID = "id";
    private static final String COL_DESC = "desc";
    private static final String COL_NAME = "name";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_MAGASIN + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DESC + " TEXT NOT NULL, "
            + COL_NAME + " TEXT NOT NULL);";

    public MagasinsSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MAGASIN + ";");
        onCreate(sqLiteDatabase);
    }
}
