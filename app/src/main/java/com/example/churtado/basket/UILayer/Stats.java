package com.example.churtado.basket.UILayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import com.example.churtado.basket.R;


public class Stats extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        fillTable();
    }

    private void fillTable() {
        //Find the table layout
        final TableLayout tblPlayersStats = (TableLayout)findViewById(R.id.tblPlayersStats);
        //Create a new row to be added
        TableRow tbrPlayer = new TableRow(this);
        /* Create a Button to be the row-content. */
        TextView txtNumber = new TextView(this);
        txtNumber.setText("Dynamic Button");
        txtNumber.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

        TextView txtNumber2 = new TextView(this);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
