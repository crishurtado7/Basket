package com.example.churtado.basket.DomainLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.churtado.basket.DataLayer.SeasonsSQLiteHelper;
import com.example.churtado.basket.DataLayer.TeamsSQLiteHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by churtado on 15/12/2014.
 */
public class Season {
    private int seasonId;
    private int teamId;
    private int year;
    private int totalMatches;
    private int totalWins;

    private String teamName;

    private static final Season season = new Season();
    public static Season getInstance() {return season;}

    public Season() {
        this.seasonId = 0;
        this.teamId = 0;
        this.year = 0;
        this.totalMatches = 0;
        this.totalWins = 0;
        this.teamName = "";
    }

    public Season(int seasonId, int teamId, int year, int totalMatches, int totalWins, String teamName) {
        this.seasonId = seasonId;
        this.teamId = teamId;
        this.year = year;
        this.totalMatches = totalMatches;
        this.totalWins = totalWins;
        this.teamName = teamName;
    }

    public int getSeasonId() {
        return this.seasonId;
    }

    public int getTeamId() {
        return this.teamId;
    }

    public int getYear() {
        return this.year;
    }

    public int getTotalMatches() {
        return this.totalMatches;
    }

    public int getTotalWins() {
        return this.totalWins;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public void createSeason(Context context, String teamName) {
        final TeamsSQLiteHelper teamsHelper = new TeamsSQLiteHelper(context, "Teams", null, 1);
        final SeasonsSQLiteHelper seasonsHelper = new SeasonsSQLiteHelper(context, "Seasons", null, 1);

        //Check if exists a Team with name = teamName
        SQLiteDatabase teamsDB = teamsHelper.getWritableDatabase();
        String[] fields = new String[] {"id, name"};
        String[] args = new String[] {teamName};

        Cursor c = teamsDB.query("Teams", fields, "name=?", args, null, null, null);
        int teamId = -1;

        //If the team doesn't exist, create a new one
        if (!c.moveToFirst()) {
            //Create the new team
            ContentValues newTeam = new ContentValues();
            newTeam.put("name", teamName);

            //Insert the register in the db
            teamId = (int)teamsDB.insert("Teams", null, newTeam);
            teamsDB.close();
        }

        //Get the current year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        //Create the new season with the teamId and the current year
        SQLiteDatabase seasonsDB = seasonsHelper.getWritableDatabase();

        if(seasonsDB != null) {
            //Create the new season
            ContentValues newSeason = new ContentValues();
            newSeason.put("teamId", teamId);
            newSeason.put("year", currentYear);
            newSeason.put("totalMatches", 0);
            newSeason.put("totalWins", 0);

            int seasonId = (int)seasonsDB.insert("Seasons", null, newSeason);
            seasonsDB.close();

            this.seasonId = seasonId;
            this.teamId = teamId;
            this.year = currentYear;
            this.totalMatches = 0;
            this.totalWins = 0;
            this.teamName = teamName;
        }
    }

    public void loadSeason(Context context, int seasonId) {
        final SeasonsSQLiteHelper seasonsHelper = new SeasonsSQLiteHelper(context, "Seasons", null, 1);
        final TeamsSQLiteHelper teamsHelper = new TeamsSQLiteHelper(context, "Teams", null, 1);

        //Get the season with id = seasonId
        SQLiteDatabase seasonsDB = seasonsHelper.getReadableDatabase();
        String[] fields = new String[] {"id, teamId, year, totalMatches, totalWins"};
        String[] args = new String[] {String.valueOf(seasonId)};

        Cursor c = seasonsDB.query("Seasons", fields, "id=?", args, null, null, null);

        if(c.moveToFirst()) {
            this.seasonId = seasonId;
            this.teamId = c.getInt(1);
            this.year = c.getInt(2);
            this.totalMatches = c.getInt(3);
            this.totalWins = c.getInt(4);

            //Get the team's name
            SQLiteDatabase teamsDB = teamsHelper.getReadableDatabase();
            fields = new String[] {"name"};
            args = new String[] {String.valueOf(this.teamId)};

            c = teamsDB.query("Teams", fields, "id=?", args, null, null, null);
            c.moveToFirst();
            this.teamName = c.getString(0);

            seasonsDB.close();
            teamsDB.close();
        }
    }

    public static List<Season> getAllSeasons(Context context) {
        List<Season> seasons = new ArrayList<Season>();
        final SeasonsSQLiteHelper seasonsHelper = new SeasonsSQLiteHelper(context, "Seasons", null, 1);
        final TeamsSQLiteHelper teamsHelper = new TeamsSQLiteHelper(context, "Teams", null, 1);

        SQLiteDatabase seasonsDB = seasonsHelper.getReadableDatabase();
        SQLiteDatabase teamsDB = teamsHelper.getReadableDatabase();

        String[] fields = new String[] {"name"};
        String[] args = new String[1];
        //Select all the seasons stored
        Cursor c = seasonsDB.rawQuery("SELECT * FROM Seasons", null);
        if(c.moveToFirst()) {
            do {
                args[0] = String.valueOf(c.getInt(1));

                Cursor cTeam = teamsDB.query("Teams", fields, "id=?", args, null, null, null);
                String s = "";
                if(cTeam.moveToFirst()) s = cTeam.getString(0);

                seasons.add(new Season(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4), cTeam.getString(0)));
            } while(c.moveToNext());
        }
        seasonsDB.close();
        teamsDB.close();
        return seasons;
    }
}
