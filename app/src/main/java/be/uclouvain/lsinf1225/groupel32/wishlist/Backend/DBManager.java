//BASE DE DONNÉES REGROUPANT L'ENSEMBLE DES PRODUITS PRÉSENTS DANS CETTE APPLICATIONS.

package be.uclouvain.lsinf1225.groupel32.wishlist.Backend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBManager<dbManager> extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Produits.db";
    private static final  String PRODUITS_TABLE_NAME= "tblVentes";
    private static final int DATABASE_VERSION = 4;


    public DBManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sqlCreateTable="create table "+PRODUITS_TABLE_NAME+" ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "designation TEXT NOT NULL, "
                + "prix REAL NOT NULL, "
                + "qte REAL NOT NULL"
                + ")";
        sqLiteDatabase.execSQL(sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        String sqlDropTable ="DROP TABLE IF EXISTS "+PRODUITS_TABLE_NAME;
        sqLiteDatabase.execSQL(sqlDropTable);
        onCreate(sqLiteDatabase);
    }

    public void insert(String designation, double prix, double qte){
        String sqlInsert="insert into " + PRODUITS_TABLE_NAME + " (designation, prix,  qte) values('"
                + designation + "'," + prix + "," + qte  +")";
        this.getWritableDatabase().execSQL(sqlInsert);
    }


    public List<Article> afficherDetail() {
        List<Article> details = new ArrayList<>();
        String sqlSelect = "select * from " + PRODUITS_TABLE_NAME + " order by id";
        Cursor cursor = this.getReadableDatabase().rawQuery(sqlSelect, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Article detail = new Article(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3));
            details.add(detail);
            cursor.moveToNext();
        }
        cursor.close();
        return details;
    }
}
