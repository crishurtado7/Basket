package com.example.churtado.basket.UILayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.churtado.basket.DomainLayer.Player;
import com.example.churtado.basket.DomainLayer.Season;
import com.example.churtado.basket.R;

import java.util.List;


public class EditTeam extends ActionBarActivity {

    Season season = Season.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);

        loadPlayers();
    }

    private void loadPlayers() {
        //Find the table layout
        final TableLayout tblPlayers = (TableLayout)findViewById(R.id.tblPlayers);
        //Get all the players from the team
        List<Player> players = Player.getAllPlayers(EditTeam.this, season.getTeamId());
        for(int i = 0; i < players.size(); ++i) {
            //Create a new row to be added
            TableRow tbrPlayer = new TableRow(this);
            //Create a hidden TextView to store the player id
            TextView txtPlayerId = new TextView(this);
            txtPlayerId.setText(String.valueOf(players.get(i)));
            txtPlayerId.setVisibility(View.INVISIBLE);
            //Create two TextView to show the info of each player
            TextView txtPlayerNumber = new TextView(this);
            txtPlayerNumber.setText(String.valueOf(players.get(i).getNumPlayer()));
            txtPlayerNumber.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            TextView txtPlayerName = new TextView(this);
            txtPlayerName.setText(players.get(i).getNamePlayer());
            txtPlayerName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            //Create a Button to edit the player
            Button btnEditPlayer = new Button(this);
            btnEditPlayer.setText("Edit");
            btnEditPlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            //TODO: Add onClick function
            //Create a Button to delete the player
            Button btnDeletePlayer = new Button(this);
            btnDeletePlayer.setText("Delete");
            btnDeletePlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            //TODO: Add onClick function
            //Add the controls to the row
            tbrPlayer.addView(txtPlayerNumber);
            tbrPlayer.addView(txtPlayerName);
            tbrPlayer.addView(btnEditPlayer);
            tbrPlayer.addView(btnDeletePlayer);
            //Add row to the layout
            tbrPlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
            tblPlayers.addView(tbrPlayer, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        //Create a new button to add new players
        TableRow tbrNewPlayer = new TableRow(this);
        Button btnAddPlayer = new Button(this);
        btnAddPlayer.setText("Add Player");
        btnAddPlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        //TODO: Add onClick function
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_team, menu);
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
