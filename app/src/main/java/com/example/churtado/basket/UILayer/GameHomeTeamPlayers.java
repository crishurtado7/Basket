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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by churtado on 17/12/2014.
 */
public class GameHomeTeamPlayers extends Fragment {

    GameStats gameStats = GameStats.getInstance();

    private Button btnHomePlayer1;
    private Button btnHomePlayer2;
    private Button btnHomePlayer3;
    private Button btnHomePlayer4;
    private Button btnHomePlayer5;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_game_home_team_players, container, false);

        TextView txtHomeTeamName = (TextView) rootView.findViewById(R.id.txtHomeTeamName);
        txtHomeTeamName.setText("Team A: " + gameStats.getTeamHome());

        setPlayerNumbers(rootView);

        return rootView;
    }

    private void setPlayerNumbers(final View v) {
        List<PlayerStats> lstHomePlayers = gameStats.getLstPlayerStatsHome();
        List<Integer> lstSelectedHomePlayers = gameStats.getLstPlayersOnCourtHome();
        //Get the buttons for each player
        btnHomePlayer1 = (Button) v.findViewById(R.id.btnHomePlayer1);
        btnHomePlayer2 = (Button) v.findViewById(R.id.btnHomePlayer2);
        btnHomePlayer3 = (Button) v.findViewById(R.id.btnHomePlayer3);
        btnHomePlayer4 = (Button) v.findViewById(R.id.btnHomePlayer4);
        btnHomePlayer5 = (Button) v.findViewById(R.id.btnHomePlayer5);
        //Set one number for each button, with the selected players
        btnHomePlayer1.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(0)).getPlayerNum()));
        btnHomePlayer2.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(1)).getPlayerNum()));
        btnHomePlayer3.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(2)).getPlayerNum()));
        btnHomePlayer4.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(3)).getPlayerNum()));
        btnHomePlayer5.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(4)).getPlayerNum()));
        //Set the click listeners to each button for switching players
        btnHomePlayer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerHome(v, 0, btnHomePlayer1);
            }
        });
        btnHomePlayer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerHome(v, 1, btnHomePlayer2);
            }
        });
        btnHomePlayer3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerHome(v, 2, btnHomePlayer3);
            }
        });
        btnHomePlayer4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerHome(v, 3, btnHomePlayer4);
            }
        });
        btnHomePlayer5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerHome(v, 4, btnHomePlayer5);
            }
        });
    }

    private void showMessage(View v, String message) {
        Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void switchPlayerHome(final View v, final int indexOfSelectedPlayerToSwitch, final Button btnPressed) {
        final List<PlayerStats> lstPlayerStatsHome = gameStats.getLstPlayerStatsHome();
        final List<Integer> lstPlayersOnCourtHome = gameStats.getLstPlayersOnCourtHome();
        final List<Integer> lstPlayersNotPlaying = new ArrayList<Integer>();

        CharSequence[] playersToShow;
        if(lstPlayerStatsHome.size() <= 5) playersToShow = new CharSequence[]{};
        else {
            playersToShow = new CharSequence[lstPlayerStatsHome.size() - 5];
            int position = 0;
            for (int i = 0; i < lstPlayerStatsHome.size(); ++i) {
                if (!lstPlayersOnCourtHome.contains(i)) {
                    playersToShow[position] = String.valueOf(lstPlayerStatsHome.get(i).getPlayerNum()) + " - "
                            + lstPlayerStatsHome.get(i).getPlayerName();
                    ++position;
                    lstPlayersNotPlaying.add(i);
                }
            }
        }

        //Show modal to select the player to switch
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Switch:");
        builder.setSingleChoiceItems(playersToShow, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichPlayer) {
                //Switch players
                String namePlayerOut = lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(indexOfSelectedPlayerToSwitch)).getPlayerName();
                lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(indexOfSelectedPlayerToSwitch)).makeAction(GameActions.SWITCH_OUT, 0);
                lstPlayerStatsHome.get(lstPlayersNotPlaying.get(whichPlayer)).makeAction(GameActions.SWITCH_IN, 0);
                lstPlayersOnCourtHome.set(indexOfSelectedPlayerToSwitch, lstPlayersNotPlaying.get(whichPlayer));
                btnPressed.setText(String.valueOf(lstPlayerStatsHome.get(lstPlayersNotPlaying.get(whichPlayer)).getPlayerNum()));
                showMessage(v, "Switch(Home). " + lstPlayerStatsHome.get(lstPlayersOnCourtHome.get(indexOfSelectedPlayerToSwitch)).getPlayerName() + " IN."
                        + namePlayerOut + " OUT");
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
