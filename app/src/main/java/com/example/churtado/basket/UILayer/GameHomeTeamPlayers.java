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
public class GameHomeTeamPlayers extends Fragment {

    GameStats gameStats = GameStats.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_game_home_team_players, container, false);

        TextView txtHomeTeamName = (TextView) rootView.findViewById(R.id.txtHomeTeamName);
        txtHomeTeamName.setText("Team A: " + gameStats.getTeamHome());

        setPlayerNumbers(rootView);

        return rootView;
    }

    //TODO:set initial five players
    private void setPlayerNumbers(View v) {
        List<PlayerStats> lstHomePlayers = gameStats.getLstPlayerStatsHome();
        List<Integer> lstSelectedHomePlayers = gameStats.getLstPlayersOnCourtHome();
        //Get the buttons for each player
        Button btnHomePlayer1 = (Button) v.findViewById(R.id.btnHomePlayer1);
        Button btnHomePlayer2 = (Button) v.findViewById(R.id.btnHomePlayer2);
        Button btnHomePlayer3 = (Button) v.findViewById(R.id.btnHomePlayer3);
        Button btnHomePlayer4 = (Button) v.findViewById(R.id.btnHomePlayer4);
        Button btnHomePlayer5 = (Button) v.findViewById(R.id.btnHomePlayer5);
        //Set one number for each button, with the selected players
        btnHomePlayer1.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(0)).getPlayerNum()));
        btnHomePlayer2.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(1)).getPlayerNum()));
        btnHomePlayer3.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(2)).getPlayerNum()));
        btnHomePlayer4.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(3)).getPlayerNum()));
        btnHomePlayer5.setText(String.valueOf(lstHomePlayers.get(lstSelectedHomePlayers.get(4)).getPlayerNum()));
    }
}
