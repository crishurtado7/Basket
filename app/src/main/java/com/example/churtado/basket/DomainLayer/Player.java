package com.example.churtado.basket.DomainLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.churtado.basket.DataLayer.PlayersSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by churtado on 15/12/2014.
 */
public class Player {
    private int playerId;
    private int teamId;
    private int numPlayer;
    private String namePlayer;

    public Player() {
        this.playerId = 0;
        this.teamId = 0;
        this.numPlayer = 0;
        this.namePlayer = "";
    }

    public Player(int teamId, int numPlayer, String namePlayer) {
        this.playerId = 0;
        this.teamId = teamId;
        this.numPlayer = numPlayer;
        this.namePlayer = namePlayer;
    }

    public Player(int playerId, int teamId, int numPlayer, String namePlayer) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.numPlayer = numPlayer;
        this.namePlayer = namePlayer;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public int getTeamId() {
        return this.teamId;
    }

    public int getNumPlayer() {
        return this.numPlayer;
    }

    public String getNamePlayer() {
        return this.namePlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    private boolean playerExists(Context context, int playerId) {
        final PlayersSQLiteHelper playersHelper = new PlayersSQLiteHelper(context, "Players", null, 1);

        SQLiteDatabase playersDB = playersHelper.getReadableDatabase();
        String[] fields = new String[] {"id"};
        String[] args = new String[] {String.valueOf(playerId)};
        //Select all the players from the teamId stored
        Cursor cPlayer = playersDB.query("Players", fields, "id=?", args, null, null, null);

        if(cPlayer.moveToFirst()) return true;
        else return false;
    }

    public void createPlayer(Context context) {
        final PlayersSQLiteHelper playersHelper = new PlayersSQLiteHelper(context, "Players", null, 1);

        SQLiteDatabase playersDB = playersHelper.getReadableDatabase();

        //Create the object containing
        ContentValues newPlayer = new ContentValues();
        newPlayer.put("teamId", this.teamId);
        newPlayer.put("numPlayer", this.numPlayer);
        newPlayer.put("namePlayer", this.namePlayer);

        //Insert the new player in the DB
        int playerId = (int)playersDB.insert("Players", null, newPlayer);
        this.playerId = playerId;
    }

    public void updatePlayer(Context context){
        final PlayersSQLiteHelper playersHelper = new PlayersSQLiteHelper(context, "Players", null, 1);

        SQLiteDatabase playersDB = playersHelper.getReadableDatabase();

        //Put the new values of the player
        ContentValues newPlayerValues = new ContentValues();
        newPlayerValues.put("numPlayer", this.numPlayer);
        newPlayerValues.put("namePlayer", this.namePlayer);

        //Update the player in the DB
        String[] args = new String[]{String.valueOf(this.playerId)};
        playersDB.update("Players", newPlayerValues, "id=?", args);
    }

    public void deletePlayer(Context context) {
        final PlayersSQLiteHelper playersHelper = new PlayersSQLiteHelper(context, "Players", null, 1);

        SQLiteDatabase playersDB = playersHelper.getReadableDatabase();

        //Delete the selected player from the DB
        String[] args = new String[]{String.valueOf(this.playerId)};
        playersDB.execSQL("DELETE FROM Players WHERE id=?", args);
    }

    public static List<Player> getAllPlayers(Context context, int teamId) {
        List<Player> players = new ArrayList<Player>();
        final PlayersSQLiteHelper playersHelper = new PlayersSQLiteHelper(context, "Players", null, 1);

        SQLiteDatabase playersDB = playersHelper.getReadableDatabase();
        String[] fields = new String[] {"id, teamId, numPlayer, namePlayer"};
        String[] args = new String[] {String.valueOf(teamId)};
        //Select all the players from the teamId stored
        Cursor cPlayers = playersDB.query("Players", fields, "teamId=?", args, null, null, null);
        if(cPlayers.moveToFirst()) {
            do {
                players.add(new Player(cPlayers.getInt(0), cPlayers.getInt(1), cPlayers.getInt(2), cPlayers.getString(3)));
            } while(cPlayers.moveToNext());
        }
        playersDB.close();
        return players;
    }
}
