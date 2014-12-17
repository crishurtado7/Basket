package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.DomainLayer.PlayerStats;
import com.example.churtado.basket.R;

import java.util.List;

/**
 * Created by churtado on 17/12/2014.
 */
public class GameGuestTeamPlayers extends Fragment {

    GameStats gameStats = GameStats.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_game_guest_team_players, container, false);

        TextView txtGuestTeamName = (TextView) rootView.findViewById(R.id.txtGuestTeamName);
        txtGuestTeamName.setText("Team B: " + gameStats.getTeamGuest());

        setPlayerNumbers(rootView);

        return rootView;
    }

    //TODO:set initial five players
    private void setPlayerNumbers(View v) {
        List<PlayerStats> lstGuestPlayers = gameStats.getLstPlayerStatsGuest();
        List<Integer> lstSelectedGuestPlayers = gameStats.getLstPlayersOnCourtGuest();
        //Get the buttons for each player
        Button btnGuestPlayer1 = (Button) v.findViewById(R.id.btnGuestPlayer1);
        Button btnGuestPlayer2 = (Button) v.findViewById(R.id.btnGuestPlayer2);
        Button btnGuestPlayer3 = (Button) v.findViewById(R.id.btnGuestPlayer3);
        Button btnGuestPlayer4 = (Button) v.findViewById(R.id.btnGuestPlayer4);
        Button btnGuestPlayer5 = (Button) v.findViewById(R.id.btnGuestPlayer5);
        //Set one number for each button, with the selected players
        btnGuestPlayer1.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(0)).getPlayerNum()));
        btnGuestPlayer2.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(1)).getPlayerNum()));
        btnGuestPlayer3.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(2)).getPlayerNum()));
        btnGuestPlayer4.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(3)).getPlayerNum()));
        btnGuestPlayer5.setText(String.valueOf(lstGuestPlayers.get(lstSelectedGuestPlayers.get(4)).getPlayerNum()));
    }
}
