package com.example.churtado.basket.DataLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by churtado on 11/12/2014.
 */
public class SeasonsSQLiteHelper extends SQLiteOpenHelper {

    //SQL Sentence to create the table of Seasons
    String sqlCreate = "CREATE TABLE Seasons (id INTEGER PRIMARY KEY, teamId INT, year INT, totalMatches INT, totalWins INT)";

    public SeasonsSQLiteHelper(Context contexto, String nombre,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Execute the SQL sentence to create the table
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Delete the old version of the table
        db.execSQL("DROP TABLE IF EXISTS Seasons");

        //Create the new version of the table
        db.execSQL(sqlCreate);
    }
}
