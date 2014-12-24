package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.churtado.basket.Adapters.StatsTableAdapter;
import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.DomainLayer.PlayerStats;
import com.example.churtado.basket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by churtado on 17/12/2014.
 */
public class StatsGuestFragment extends Fragment {

    GameStats gameStats = GameStats.getInstance();
    StatsTableAdapter adapter = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_stats_guest, container, false);

        new Thread(new Runnable() {
            public void run() {
                fillTableGuest(rootView);
            }
        }).start();

        return rootView;
    }

    private void fillTableGuest(View v) {
        //Construct the data source
        ArrayList<PlayerStats> lstGuestPlayerStats = (ArrayList)gameStats.getLstPlayerStatsGuest();
        //Create the adapter to convert the array to views
        adapter = new StatsTableAdapter(v.getContext(), lstGuestPlayerStats);
        //Attach the adapter to the list view
        ListView tblPlayersStatsGuest = (ListView) v.findViewById(R.id.tblPlayersStatsGuest);
        tblPlayersStatsGuest.setAdapter(adapter);
        gameStats.setStatsGuestTableAdapter(adapter);
    }
}
