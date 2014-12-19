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
public class GameGuestTeamPlayers extends Fragment {

    GameStats gameStats = GameStats.getInstance();

    private Button btnGuestPlayer1;
    private Button btnGuestPlayer2;
    private Button btnGuestPlayer3;
    private Button btnGuestPlayer4;
    private Button btnGuestPlayer5;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_game_guest_team_players, container, false);

        TextView txtGuestTeamName = (TextView) rootView.findViewById(R.id.txtGuestTeamName);
        txtGuestTeamName.setText("Team B: " + gameStats.getTeamGuest());

        setPlayerNumbers(rootView);

        return rootView;
    }

    private void setPlayerNumbers(final View v) {
        List<PlayerStats> lstGuestPlayers = gameStats.getLstPlayerStatsGuest();
        List<Integer> lstSelectedGuestPlayers = gameStats.getLstPlayersOnCourtGuest();
        //Get the buttons for each player
        btnGuestPlayer1 = (Button) v.findViewById(R.id.btnGuestPlayer1);
        btnGuestPlayer2 = (Button) v.findViewById(R.id.btnGuestPlayer2);
        btnGuestPlayer3 = (Button) v.findViewById(R.id.btnGuestPlayer3);
        btnGuestPlayer4 = (Button) v.findViewById(R.id.btnGuestPlayer4);
        btnGuestPlayer5 = (Button) v.findViewById(R.id.btnGuestPlayer5);
        //Set one number for each button, with the selected players
        btnGuestPlayer1.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(0)).getPlayerNum()));
        btnGuestPlayer2.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(1)).getPlayerNum()));
        btnGuestPlayer3.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(2)).getPlayerNum()));
        btnGuestPlayer4.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(3)).getPlayerNum()));
        btnGuestPlayer5.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(4)).getPlayerNum()));
        //Set the click listeners to each button for switching players
        btnGuestPlayer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerGuest(v, 0, btnGuestPlayer1);
            }
        });
        btnGuestPlayer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerGuest(v, 1, btnGuestPlayer2);
            }
        });
        btnGuestPlayer3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerGuest(v, 2, btnGuestPlayer3);
            }
        });
        btnGuestPlayer4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerGuest(v, 3, btnGuestPlayer4);
            }
        });
        btnGuestPlayer5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switchPlayerGuest(v, 4, btnGuestPlayer5);
            }
        });
    }

    private void showMessage(View v, String message) {
        Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void switchPlayerGuest(final View v, final int indexOfSelectedPlayerToSwitch, final Button btnPressed) {
        final List<PlayerStats> lstPlayerStatsGuest = gameStats.getLstPlayerStatsGuest();
        final List<Integer> lstPlayersOnCourtGuest = gameStats.getLstPlayersOnCourtGuest();
        final List<Integer> lstPlayersNotPlaying = new ArrayList<Integer>();

        CharSequence[] playersToShow;
        if(lstPlayerStatsGuest.size() <= 5) playersToShow = new CharSequence[]{};
        else {
            playersToShow = new CharSequence[lstPlayerStatsGuest.size() - 5];
            int position = 0;
            for (int i = 0; i < lstPlayerStatsGuest.size(); ++i) {
                if (!lstPlayersOnCourtGuest.contains(i)) {
                    playersToShow[position] = String.valueOf(lstPlayerStatsGuest.get(i).getPlayerNum()) + " - "
                            + lstPlayerStatsGuest.get(i).getPlayerName();
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
                String namePlayerOut = lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(indexOfSelectedPlayerToSwitch)).getPlayerName();
                lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(indexOfSelectedPlayerToSwitch)).makeAction(GameActions.SWITCH_OUT, 0);
                lstPlayerStatsGuest.get(lstPlayersNotPlaying.get(whichPlayer)).makeAction(GameActions.SWITCH_IN, 0);
                lstPlayersOnCourtGuest.set(indexOfSelectedPlayerToSwitch, lstPlayersNotPlaying.get(whichPlayer));
                btnPressed.setText(String.valueOf(lstPlayerStatsGuest.get(lstPlayersNotPlaying.get(whichPlayer)).getPlayerNum()));
                showMessage(v, "Switch(Guest). " + lstPlayerStatsGuest.get(lstPlayersOnCourtGuest.get(indexOfSelectedPlayerToSwitch)).getPlayerName() + " IN."
                        + namePlayerOut + " OUT");
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
