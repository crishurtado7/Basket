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
public class GameHomeTeamActions extends Fragment {

    GameStats gameStats = GameStats.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_game_home_team_actions, container, false);

        //Get all the buttons to set the proper actions
        Button btnT1DoneHome = (Button) rootView.findViewById(R.id.btnT1DoneHome);
        Button btnT2DoneHome = (Button) rootView.findViewById(R.id.btnT2DoneHome);
        Button btnT3DoneHome = (Button) rootView.findViewById(R.id.btnT3DoneHome);
        Button btnOffReboundHome = (Button) rootView.findViewById(R.id.btnOffReboundHome);
        Button btnT1FailedHome = (Button) rootView.findViewById(R.id.btnT1FailedHome);
        Button btnT2FailedHome = (Button) rootView.findViewById(R.id.btnT2FailedHome);
        Button btnT3FailedHome = (Button) rootView.findViewById(R.id.btnT3FailedHome);
        Button btnDefReboundHome = (Button) rootView.findViewById(R.id.btnDefReboundHome);
        //Button btnStealHome = (Button) rootView.findViewById(R.id.btnStealHome);
        Button btnTurnoverHome = (Button) rootView.findViewById(R.id.btnTurnoverHome);
        Button btnBlockHome = (Button) rootView.findViewById(R.id.btnBlockHome);
        Button btnCommittedFoulHome = (Button) rootView.findViewById(R.id.btnCommittedFoulHome);
        //TODO:change stats of players to list wiew and update that view after a button is pressed
        //Set the onclick function to each button
        btnT1DoneHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t1DoneHome(rootView);
            }
        });
        btnT2DoneHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t2DoneHome(rootView);
            }
        });
        btnT3DoneHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t3DoneHome(rootView);
            }
        });
        btnT1FailedHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t1FailedHome(rootView);
            }
        });
        btnT2FailedHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t2FailedHome(rootView);
            }
        });
        btnT3FailedHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t3FailedHome(rootView);
            }
        });
        btnOffReboundHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                offReboundHome(rootView);
            }
        });
        btnDefReboundHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                defReboundHome(rootView);
            }
        });
        btnTurnoverHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                turnoverHome(rootView);
            }
        });
        btnCommittedFoulHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                committedFoulHome(rootView);
            }
        });
        btnBlockHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                committedBlockHome(rootView);
            }
        });

        return rootView;

    }

    private CharSequence[] getListHomePlayersOnCourt(List<PlayerStats> lstPlayerStatsHome, List<Integer> lstPlayersOnCourtHome) {

        final CharSequence[] playersToShow = new CharSequence[lstPlayersOnCourtHome.size()];
        for(int i = 0; i < lstPlayersOnCourtHome.size(); ++i)
            playersToShow[i] = String.valueOf(lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(i)).getPlayerNum()) + " - "
                    + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(i)).getPlayerName();

        return playersToShow;
    }

    private void showMessage(View v, String message) {
        Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //TODO:update the fragment with the stats

    private void updateHomeTeamScore() {
        ((Game)getActivity()).updateHomeScoreBoxFragment();
    }

    private void t1DoneHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who scored?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add point to the player and to the team
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.T1DONE, 0);
                gameStats.scoreTeamHome(1);
                dialog.dismiss();
                updateHomeTeamScore();
                showMessage(v, "1 point scored by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t2DoneHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who scored?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add point to the player and to the team
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.T2DONE, 0);
                gameStats.scoreTeamHome(2);
                dialog.dismiss();
                updateHomeTeamScore();
                showMessage(v, "2 points scored by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t3DoneHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who scored?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add point to the player and to the team
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.T3DONE, 0);
                gameStats.scoreTeamHome(3);
                dialog.dismiss();
                updateHomeTeamScore();
                showMessage(v, "3 points scored by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t1FailedHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who failed?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add failed shoot to the player
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.T1FAILED, 0);
                dialog.dismiss();
                showMessage(v, "1 point shot failed by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t2FailedHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who failed?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add failed shoot to the player
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.T2FAILED, 0);
                dialog.dismiss();
                showMessage(v, "2 point shot failed by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void t3FailedHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who failed?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add failed shoot to the player
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.T3FAILED, 0);
                dialog.dismiss();
                showMessage(v, "3 point shoot failed by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void offReboundHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who took the offensive rebound?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add offensive rebound to the player
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.OFF_REBOUND, 0);
                dialog.dismiss();
                showMessage(v, "Offensive rebound by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void defReboundHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who took the defensive rebound?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Add defensive rebound to the player
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).makeAction(GameActions.DEF_REBOUND, 0);
                dialog.dismiss();
                showMessage(v, "Defensive rebound by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void turnoverHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who loosed the ball?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Store the player that loosed the ball
                final int whichPlayerTurnover = whichPlayer;
                dialog.dismiss();

                //Show modal to select the player who stole the ball
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Who stole the ball?");
                //TODO:add option for anybody
                builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichPlayer) {
                        //Add steal to the guest player and turnover to home player
                        lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayerTurnover)).makeAction(GameActions.TURNOVER, 0);
                        lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.STEAL, 0);
                        dialog.dismiss();
                        showMessage(v, "Ball loosed by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
                    }
                });

                AlertDialog dialog2 = builder.create();
                dialog2.show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void committedBlockHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who blocked the ball?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Store the player that blocked the ball
                final int whichPlayerBlocked = whichPlayer;
                dialog.dismiss();

                //Show modal to select the player who received the block
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Who received the block?");
                builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichPlayer) {
                        //Add received block to the guest player and committed block to home player
                        lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayerBlocked)).makeAction(GameActions.BLOCK, 0);
                        lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.RECEIVED_BLOCK, 0);
                        dialog.dismiss();
                        showMessage(v, "Ball blocked by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
                    }
                });

                AlertDialog dialog2 = builder.create();
                dialog2.show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void committedFoulHome(final View v) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();

        //Show modal to select the player
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Who make the foul?");
        builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsHome, lstPlayersOnCourtHome), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Store the player that make the foul
                final int whichPlayerFoul = whichPlayer;
                dialog.dismiss();

                //Show modal to select the player who received the foul
                //TODO:add option for anybody (+ store fouls made by the team - technical fouls)
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Who received the foul?");
                builder.setSingleChoiceItems(getListHomePlayersOnCourt(lstPlayerStatsGuest, lstPlayersOnCourtGuest), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichPlayer) {
                        //Add received foul to the guest player and committed foul to home player
                        lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayerFoul)).makeAction(GameActions.FOUL, 0);
                        lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(whichPlayer)).makeAction(GameActions.RECEIVED_FOUL, 0);
                        dialog.dismiss();
                        showMessage(v, "Foul number " + String.valueOf(lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getCommittedFouls()) + " by " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(whichPlayer)).getPlayerName() + "(Home)");
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
