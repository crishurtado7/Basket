package com.example.churtado.basket.DomainLayer;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;

/**
 * Created by churtado on 16/12/2014.
 */
public class GameStats {
    //region VARIABLES
    private String teamHome;
    private String teamGuest;
    private Date gameDate;

    private int teamHomeTotalPoints;
    private int teamGuestTotalPoints;

    private int numberOfQuarters;
    private int minutesPerQuarter;
    private boolean extraTimes;
    private int minutesPerExtraTime;

    private List<PlayerStats> lstPlayerStatsHome;
    private List<PlayerStats> lstPlayerStatsGuest;

    private List<Integer> lstPlayersOnCourtHome;
    private List<Integer> lstPlayersOnCourtGuest;

    private static final GameStats gameStats = new GameStats();
    public static GameStats getInstance() {return gameStats;}

    public List<Integer> getLstPlayersOnCourtHome() {
        return lstPlayersOnCourtHome;
    }

    public void setLstPlayersOnCourtHome(List<Integer> lstPlayersOnCourtHome) {
        this.lstPlayersOnCourtHome = lstPlayersOnCourtHome;
    }

    public List<Integer> getLstPlayersOnCourtGuest() {
        return lstPlayersOnCourtGuest;
    }

    public void setLstPlayersOnCourtGuest(List<Integer> lstPlayersOnCourtGuest) {
        this.lstPlayersOnCourtGuest = lstPlayersOnCourtGuest;
    }

    public String getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(String teamHome) {
        this.teamHome = teamHome;
    }

    public String getTeamGuest() {
        return teamGuest;
    }

    public void setTeamGuest(String teamGuest) {
        this.teamGuest = teamGuest;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public int getTeamHomeTotalPoints() {
        return teamHomeTotalPoints;
    }

    public void setTeamHomeTotalPoints(int teamHomeTotalPoints) {
        this.teamHomeTotalPoints = teamHomeTotalPoints;
    }

    public int getTeamGuestTotalPoints() {
        return teamGuestTotalPoints;
    }

    public void setTeamGuestTotalPoints(int teamGuestTotalPoints) {
        this.teamGuestTotalPoints = teamGuestTotalPoints;
    }

    public int getNumberOfQuarters() {
        return numberOfQuarters;
    }

    public void setNumberOfQuarters(int numberOfQuarters) {
        this.numberOfQuarters = numberOfQuarters;
    }

    public int getMinutesPerQuarter() {
        return minutesPerQuarter;
    }

    public void setMinutesPerQuarter(int minutesPerQuarter) {
        this.minutesPerQuarter = minutesPerQuarter;
    }

    public boolean isExtraTimes() {
        return extraTimes;
    }

    public void setExtraTimes(boolean extraTimes) {
        this.extraTimes = extraTimes;
    }

    public int getMinutesPerExtraTime() {
        return minutesPerExtraTime;
    }

    public void setMinutesPerExtraTime(int minutesPerExtraTime) {
        this.minutesPerExtraTime = minutesPerExtraTime;
    }

    public List<PlayerStats> getLstPlayerStatsHome() {
        return lstPlayerStatsHome;
    }

    public void setLstPlayerStatsHome(List<PlayerStats> lstPlayerStatsHome) {
        this.lstPlayerStatsHome = lstPlayerStatsHome;
    }

    public List<PlayerStats> getLstPlayerStatsGuest() {
        return lstPlayerStatsGuest;
    }

    public void setLstPlayerStatsGuest(List<PlayerStats> lstPlayerStatsGuest) {
        this.lstPlayerStatsGuest = lstPlayerStatsGuest;
    }
    //endregion

    public void initGameStats(String teamHome, String teamGuest, int numberOfQuarters, int minutesPerQuarter, boolean extraTimes, int minutesPerExtraTime, List<PlayerStats> lstPlayerStatsHome, List<PlayerStats> lstPlayerStatsGuest) {
        this.teamHome = teamHome;
        this.teamGuest = teamGuest;
        this.gameDate = getCurrentDate();
        this.teamHomeTotalPoints = 0;
        this.teamGuestTotalPoints = 0;
        this.numberOfQuarters = numberOfQuarters;
        this.minutesPerQuarter = minutesPerQuarter;
        this.extraTimes = extraTimes;
        this.minutesPerExtraTime = minutesPerExtraTime;
        this.lstPlayerStatsHome = lstPlayerStatsHome;
        this.lstPlayerStatsGuest = lstPlayerStatsGuest;
    }

    private Date getCurrentDate() {
        Calendar c = Calendar.getInstance();
        return new GregorianCalendar(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).getTime();
    }


}
