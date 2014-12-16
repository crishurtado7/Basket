package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.churtado.basket.R;

/**
 * Created by churtado on 16/12/2014.
 */
public class StatsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stats, container, false);
        fillTable(rootView);
        return rootView;
    }

    private void fillTable(View v) {
        //Find the table layout
        final TableLayout tblPlayersStats = (TableLayout) v.findViewById(R.id.tblPlayersStats);
        //Create a new row to be added
        TableRow tbrPlayer = new TableRow(v.getContext());
        /* Create a Button to be the row-content. */
        TextView txtNumber = new TextView(v.getContext());
        txtNumber.setText("Dynamic Button");
        txtNumber.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

        TextView txtNumber2 = new TextView(v.getContext());
        txtNumber2.setText("Dynamic Button2");
        txtNumber2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        /* Add Button to row. */
        tbrPlayer.addView(txtNumber);
        tbrPlayer.addView(txtNumber2);
        /* Add row to TableLayout. */
        //tr.setBackgroundResource(R.drawable.sf_gradient_03);
        tbrPlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
        tblPlayersStats.addView(tbrPlayer, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }
}
