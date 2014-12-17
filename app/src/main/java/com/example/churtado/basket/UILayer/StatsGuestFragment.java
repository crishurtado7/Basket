package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.DomainLayer.PlayerStats;
import com.example.churtado.basket.R;

import java.util.List;

/**
 * Created by churtado on 17/12/2014.
 */
public class StatsGuestFragment extends Fragment {

    GameStats gameStats = GameStats.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stats_guest, container, false);

        fillTableGuest(rootView);
        return rootView;
    }

    private void fillTableGuest(View v) {
        //Get the list of guest players
        List<PlayerStats> lstGuestPlayerStats = gameStats.getLstPlayerStatsGuest();
        //Find the table layout
        final TableLayout tblPlayersStats = (TableLayout) v.findViewById(R.id.tblPlayersStatsGuest);
        for(int i = 0; i < lstGuestPlayerStats.size(); ++i) {
            //Create a new row to be added
            TableRow tbrPlayer = new TableRow(v.getContext());
            //Create the five textViews to show the info
            TextView txtPlayerNum = new TextView(v.getContext());
            txtPlayerNum.setText(String.valueOf(lstGuestPlayerStats.get(i).getPlayerNum()));
            txtPlayerNum.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView txtPlayerName = new TextView(v.getContext());
            txtPlayerName.setText(lstGuestPlayerStats.get(i).getPlayerName());
            txtPlayerName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView txtPlayerMinutes = new TextView(v.getContext());
            txtPlayerMinutes.setText(String.valueOf(lstGuestPlayerStats.get(i).getMinutes()));
            txtPlayerMinutes.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView txtPlayerPoints = new TextView(v.getContext());
            txtPlayerPoints.setText(String.valueOf(lstGuestPlayerStats.get(i).getTotalPoints()));
            txtPlayerPoints.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView txtPlayerRebounds = new TextView(v.getContext());
            txtPlayerRebounds.setText(String.valueOf(lstGuestPlayerStats.get(i).getDefRebounds()));
            txtPlayerRebounds.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView txtPlayerValuation = new TextView(v.getContext());
            txtPlayerValuation.setText(String.valueOf(lstGuestPlayerStats.get(i).getValuation()));
            txtPlayerValuation.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            tbrPlayer.addView(txtPlayerNum);
            tbrPlayer.addView(txtPlayerName);
            tbrPlayer.addView(txtPlayerMinutes);
            tbrPlayer.addView(txtPlayerPoints);
            tbrPlayer.addView(txtPlayerRebounds);
            tbrPlayer.addView(txtPlayerValuation);

            tbrPlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
            tblPlayersStats.addView(tbrPlayer, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
    //TODO:control size of each column
}
