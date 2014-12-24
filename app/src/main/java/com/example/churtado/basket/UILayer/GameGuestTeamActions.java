package com.example.churtado.basket.UILayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.DomainLayer.PlayerStats;
import com.example.churtado.basket.Enums.GameActions;
import com.example.churtado.basket.R;

import java.util.List;

/**
 * Created by churtado on 17/12/2014.
 */
public class GameGuestTeamActions {

    GameStats gameStats = GameStats.getInstance();

    private static final GameGuestTeamActions gameGuestTeamActions = new GameGuestTeamActions();
    public static GameGuestTeamActions getInstance() {return gameGuestTeamActions;}

    public void Initialize(final View rootView) {

        //Get all the buttons to set the proper actions
        Button btnT1DoneGuest = (Button) rootView.findViewById(R.id.btnT1DoneGuest);
        Button btnT2DoneGuest = (Button) rootView.findViewById(R.id.btnT2DoneGuest);
        Button btnT3DoneGuest = (Button) rootView.findViewById(R.id.btnT3DoneGuest);
        Button btnOffReboundGuest = (Button) rootView.findViewById(R.id.btnOffReboundGuest);
        Button btnT1FailedGuest = (Button) rootView.findViewById(R.id.btnT1FailedGuest);
        Button btnT2FailedGuest = (Button) rootView.findViewById(R.id.btnT2FailedGuest);
        Button btnT3FailedGuest = (Button) rootView.findViewById(R.id.btnT3FailedGuest);
        Button btnDefReboundGuest = (Button) rootView.findViewById(R.id.btnDefReboundGuest);
        //Button btnStealHome = (Button) rootView.findViewById(R.id.btnStealHome);
        Button btnTurnoverGuest = (Button) rootView.findViewById(R.id.btnTurnoverGuest);
        Button btnBlockGuest = (Button) rootView.findViewById(R.id.btnBlockGuest);
        Button btnCommittedFoulGuest = (Button) rootView.findViewById(R.id.btnCommittedFoulGuest);
        //TODO:change stats of players to list wiew and update that view after a button is pressed
        //Set the onclick function to each button
        btnT1DoneGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t1DoneGuest(rootView);
            }
        });
        btnT2DoneGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t2DoneGuest(rootView);
            }
        });
        btnT3DoneGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t3DoneGuest(rootView);
            }
        });
        btnT1FailedGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t1FailedGuest(rootView);
            }
        });
        btnT2FailedGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t2FailedGuest(rootView);
            }
        });
        btnT3FailedGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t3FailedGuest(rootView);
            }
        });
        btnOffReboundGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                offReboundGuest(rootView);
            }
        });
        btnDefReboundGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                defReboundGuest(rootView);
            }
        });
        btnTurnoverGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                turnoverGuest(rootView);
            }
        });
        btnCommittedFoulGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                committedFoulGuest(rootView);
            }
        });
        btnBlockGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                committedBlockGuest(rootView);
            }
        });
    }

    private CharSequence[] getListGuestPlayersOnCourt(View v, List<PlayerStats> lstPlayerStatsGuest, List<Integer> lstPlayersOnCourtGuest) {

        final CharSequence[] playersToShow = new CharSequence[lstPlayersOnCourtGuest.size()];
        for(int i = 0; i < lstPlayersOnCourtGuest.size(); ++i) {
            playersToShow[i] = String.valueOf(lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(i)).getPlayerNum()) + " - "
                    + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(i)).getPlayerName();
        }

        return playersToShow;
    }

    private void showMessage(View v, String message) {
        Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //TODO:update the fragment with the stats

    private void updateGuestTeamScore() {
        GameScoreBox.getInstance().updateGuestTeamScore();
    }

    private void updateGuestTeamStats() {
        if(gameStats.getStatsGuestTableAdapter() != null)gameStats.getStatsGuestTableAdapter().notifyDataSetChanged();
    }

    private void t1DoneGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who scored?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add point to the player and to the team
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.T1DONE, 0);
                gameStats.scoreTeamGuest(1);
                dialog.dismiss();
                updateGuestTeamScore();
                updateGuestTeamStats();
                showMessage(v, "1 point scored by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t2DoneGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who scored?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add point to the player and to the team
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.T2DONE, 0);
                gameStats.scoreTeamGuest(2);
                dialog.dismiss();
                updateGuestTeamScore();
                updateGuestTeamStats();
                showMessage(v, "2 points scored by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t3DoneGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who scored?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add point to the player and to the team
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.T3DONE, 0);
                gameStats.scoreTeamGuest(3);
                dialog.dismiss();
                updateGuestTeamScore();
                updateGuestTeamStats();
                showMessage(v, "3 points scored by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t1FailedGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who failed?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add failed shoot to the player
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.T1FAILED, 0);
                dialog.dismiss();
                updateGuestTeamStats();
                showMessage(v, "1 point shot failed by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t2FailedGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who failed?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add failed shoot to the player
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.T2FAILED, 0);
                dialog.dismiss();
                updateGuestTeamStats();
                showMessage(v, "2 point shot failed by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t3FailedGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who failed?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add failed shoot to the player
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.T3FAILED, 0);
                dialog.dismiss();
                updateGuestTeamStats();
                showMessage(v, "3 point shoot failed by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void offReboundGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who took the offensive rebound?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add offensive rebound to the player
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.OFF_REBOUND, 0);
                dialog.dismiss();
                updateGuestTeamStats();
                showMessage(v, "Offensive rebound by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void defReboundGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who took the defensive rebound?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add defensive rebound to the player
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.DEF_REBOUND, 0);
                dialog.dismiss();
                updateGuestTeamStats();
                showMessage(v, "Defensive rebound by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void turnoverGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who loosed the ball?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Store the player that loosed the ball
                final int whichPlayerTurnover = whichPlayer;
                dialog.dismiss();
                updateGuestTeamStats();
                //Show modal to select the player who stole the ball
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Who stole the ball?");
                //TODO:add option for anybody
                builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichPlayer) {
                        //Add steal to the home player and turnover to guest player
                        lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayerTurnover)).makeAction(GameActions.TURNOVER, 0);
                        lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.STEAL, 0);
                        dialog.dismiss();
                        showMessage(v, "Ball loosed by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
                    }
                });

                AlertDialog dialog2 = builder.create();
                dialog2.show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void committedBlockGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who blocked the ball?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Store the player that blocked the ball
                final int whichPlayerBlocked = whichPlayer;
                dialog.dismiss();
                updateGuestTeamStats();
                //Show modal to select the player who received the block
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Who received the block?");
                builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichPlayer) {
                        //Add received block to the home player and committed block to guest player
                        lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayerBlocked)).makeAction(GameActions.BLOCK, 0);
                        lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.RECEIVED_BLOCK, 0);
                        dialog.dismiss();
                        showMessage(v, "Ball blocked by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
                    }
                });

                AlertDialog dialog2 = builder.create();
                dialog2.show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void committedFoulGuest(final View v) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who make the foul?");
        builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Store the player that make the foul
                final int whichPlayerFoul = whichPlayer;
                dialog.dismiss();
                updateGuestTeamStats();
                //Show modal to select the player who received the foul
                //TODO:add option for anybody (+ store fouls made by the team - technical fouls)
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Who received the foul?");
                builder.setSingleChoiceItems(getListGuestPlayersOnCourt(v, lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichPlayer) {
                        //Add received foul to the home player and committed foul to guest player
                        lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayerFoul)).makeAction(GameActions.FOUL, 0);
                        lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.RECEIVED_FOUL, 0);
                        dialog.dismiss();
                        showMessage(v, "Foul number " + String.valueOf(lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getCommittedFouls()) + " by " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).getPlayerName() + "(Guest)");
                    }
                });

                AlertDialog dialog2 = builder.create();
                dialog2.show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
