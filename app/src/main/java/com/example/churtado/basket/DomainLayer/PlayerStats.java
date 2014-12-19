package com.example.churtado.basket.DomainLayer;

import com.example.churtado.basket.Enums.GameActions;

/**
 * Created by churtado on 16/12/2014.
 */
public class PlayerStats {

    //region VARIABLES
    private int playerId;
    private int playerNum;
    private String playerName;

    private double minutes;
    private int totalPoints;
    private int t2Done;
    private int t2Attempted;
    private int t3Done;
    private int t3Attempted;
    private int tcDone;
    private int tcAttempted;
    private int tlDone;
    private int tlAttempted;
    private int defRebounds;
    private int offRebounds;
    private int assists;
    private int steals;
    private int turnovers;
    private int blocks;
    private int receivedBlocks;
    private int committedFouls;
    private int receivedFouls;
    private int valuation;

    public int getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getT2Done() {
        return t2Done;
    }

    public void setT2Done(int t2Done) {
        this.t2Done = t2Done;
    }

    public int getT2Attempted() {
        return t2Attempted;
    }

    public void setT2Attempted(int t2Attempted) {
        this.t2Attempted = t2Attempted;
    }

    public int getT3Done() {
        return t3Done;
    }

    public void setT3Done(int t3Done) {
        this.t3Done = t3Done;
    }

    public int getT3Attempted() {
        return t3Attempted;
    }

    public void setT3Attempted(int t3Attempted) {
        this.t3Attempted = t3Attempted;
    }

    public int getTcDone() {
        return tcDone;
    }

    public void setTcDone(int tcDone) {
        this.tcDone = tcDone;
    }

    public int getTcAttempted() {
        return tcAttempted;
    }

    public void setTcAttempted(int tcAttempted) {
        this.tcAttempted = tcAttempted;
    }

    public int getTlDone() {
        return tlDone;
    }

    public void setTlDone(int tlDone) {
        this.tlDone = tlDone;
    }

    public int getTlAttempted() {
        return tlAttempted;
    }

    public void setTlAttempted(int tlAttempted) {
        this.tlAttempted = tlAttempted;
    }

    public int getDefRebounds() {
        return defRebounds;
    }

    public void setDefRebounds(int defRebounds) {
        this.defRebounds = defRebounds;
    }

    public int getOffRebounds() {
        return offRebounds;
    }

    public void setOffRebounds(int offRebounds) {
        this.offRebounds = offRebounds;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getReceivedBlocks() {
        return receivedBlocks;
    }

    public void setReceivedBlocks(int receivedBlocks) {
        this.receivedBlocks = receivedBlocks;
    }

    public int getCommittedFouls() {
        return committedFouls;
    }

    public void setCommittedFouls(int committedFouls) {
        this.committedFouls = committedFouls;
    }

    public int getReceivedFouls() {
        return receivedFouls;
    }

    public void setReceivedFouls(int receivedFouls) {
        this.receivedFouls = receivedFouls;
    }

    public int getValuation() {
        return valuation;
    }

    public void setValuation(int valuation) {
        this.valuation = valuation;
    }

    //endregion

    public PlayerStats() {
        this.playerId = 0;
        this.playerNum = 0;
        this.playerName = "";
        this.minutes = 0.0;
        this.totalPoints = 0;
        this.t2Done = 0;
        this.t2Attempted = 0;
        this.t3Done = 0;
        this.t3Attempted = 0;
        this.tcDone = 0;
        this.tcAttempted = 0;
        this.tlDone = 0;
        this.tlAttempted = 0;
        this.defRebounds = 0;
        this.offRebounds = 0;
        this.assists = 0;
        this.steals = 0;
        this.turnovers = 0;
        this.blocks = 0;
        this.receivedBlocks = 0;
        this.committedFouls = 0;
        this.receivedFouls = 0;
        this.valuation = 0;
    }

    public PlayerStats(int playerId, int playerNum, String playerName) {
        this.playerId = playerId;
        this.playerNum = playerNum;
        this.playerName = playerName;
        this.minutes = 0.0;
        this.totalPoints = 0;
        this.t2Done = 0;
        this.t2Attempted = 0;
        this.t3Done = 0;
        this.t3Attempted = 0;
        this.tcDone = 0;
        this.tcAttempted = 0;
        this.tlDone = 0;
        this.tlAttempted = 0;
        this.defRebounds = 0;
        this.offRebounds = 0;
        this.assists = 0;
        this.steals = 0;
        this.turnovers = 0;
        this.blocks = 0;
        this.receivedBlocks = 0;
        this.committedFouls = 0;
        this.receivedFouls = 0;
        this.valuation = 0;
    }

    public void makeAction(GameActions action, double time) {
        switch (action) {
            case T1DONE:
                this.tlDone += 1;
                this.tlAttempted += 1;
                this.valuation += 1;
                break;
            case T1FAILED:
                this.tlAttempted += 1;
                this.valuation -= 1;
                break;
            case T2DONE:
                this.t2Done += 1;
                this.t2Attempted += 1;
                this.tcDone += 1;
                this.tcAttempted += 1;
                this.valuation += 2;
                break;
            case T2FAILED:
                this.t2Attempted += 1;
                this.tcAttempted += 1;
                this.valuation -= 1;
                break;
            case T3DONE:
                this.t3Done += 1;
                this.t3Attempted += 1;
                this.tcDone += 1;
                this.tcAttempted += 1;
                this.valuation += 3;
                break;
            case T3FAILED:
                this.t3Attempted += 1;
                this.tcAttempted += 1;
                this.valuation -= 1;
                break;
            case OFF_REBOUND:
                this.offRebounds += 1;
                this.valuation += 1;
                break;
            case DEF_REBOUND:
                this.defRebounds += 1;
                this.valuation += 1;
                break;
            case ASSIST:
                this.assists += 1;
                this.valuation += 1;
                break;
            case STEAL:
                this.steals += 1;
                this.valuation += 1;
                break;
            case TURNOVER:
                this.turnovers += 1;
                this.valuation -= 1;
                break;
            case BLOCK:
                this.blocks += 1;
                this.valuation += 1;
                break;
            case RECEIVED_BLOCK:
                this.receivedBlocks += 1;
                this.valuation -= 1;
                break;
            case FOUL:
                this.committedFouls += 1;
                this.valuation -= 1;
                break;
            case RECEIVED_FOUL:
                this.receivedFouls += 1;
                this.valuation += 1;
                break;
            //TODO: update minutes
            //TODO:how to update minutes in stats view?
            case SWITCH_IN:
                break;
            case SWITCH_OUT:
                break;
        }
    }
}
