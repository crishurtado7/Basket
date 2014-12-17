package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.DomainLayer.PlayerStats;
import com.example.churtado.basket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by churtado on 17/12/2014.
 */
public class GameStatsView extends Fragment {

    GameStats gameStats = GameStats.getInstance();
    List<Pair<String, Integer>> maxScorers;

    //TODO:adapter changes in listview: http://www.vogella.com/tutorials/AndroidListView/article.html
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_game_stats_view, container, false);

        maxScorers = new ArrayList<Pair<String, Integer>>();

        updateMaxScorers(rootView);

        return rootView;
    }

    public void updateMaxScorers(View v) {
        List<PlayerStats> lstHomePlayers = gameStats.getLstPlayerStatsHome();
        List<PlayerStats> lstGuestPlayers = gameStats.getLstPlayerStatsGuest();

        for(int i = 0; i < lstHomePlayers.size(); ++i)
            maxScorers.add(new Pair<String, Integer>(lstHomePlayers.get(i).getPlayerName(), lstHomePlayers.get(i).getTotalPoints()));

        for(int i = 0; i < lstGuestPlayers.size(); ++i)
            maxScorers.add(new Pair<String, Integer>(lstGuestPlayers.get(i).getPlayerName(), lstGuestPlayers.get(i).getTotalPoints()));
        final ArrayList<String> arrayMaxScorers = new ArrayList<String>();
        for(int i = 0; i < maxScorers.size(); ++i) {
            arrayMaxScorers.add(maxScorers.get(i).first + "-" + maxScorers.get(i).second);
        }
        final ArrayAdapter adapter = new ArrayAdapter(v.getContext(),android.R.layout.simple_list_item_1, arrayMaxScorers);

        ListView lstView = (ListView) v.findViewById(R.id.lstMaxScorers);
        lstView.setAdapter(adapter);

    }
}
