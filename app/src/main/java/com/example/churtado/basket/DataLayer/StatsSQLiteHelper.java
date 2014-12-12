package com.example.churtado.basket.DataLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by churtado on 11/12/2014.
 */
public class StatsSQLiteHelper extends SQLiteOpenHelper {

    //SQL Sentence to create the table of Stats
    String sqlCreate = "CREATE TABLE Stats (seasonId INT, playerId INT, opponentName TEXT, date DATE, " +
                        "minutes DOUBLE, totalPoints INT, t2Done INT, t2Attempted INT, t3Done INT, t3Attempted INT," +
                        "tcDone INT, tcAttempted INT, tlDone INT, tlAttempted INT, defRebounds INT, offRebounds INT, assists INT, steals INT," +
                        "turnovers INT, blocks INT, receivedBlocks INT, committedFouls INT, receivedFouls INT, valoration INT)";

    public StatsSQLiteHelper(Context contexto, String nombre,
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
        db.execSQL("DROP TABLE IF EXISTS Stats");

        //Create the new version of the table
        db.execSQL(sqlCreate);
    }
}
