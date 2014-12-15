package com.example.churtado.basket.UILayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.example.churtado.basket.DataLayer.SeasonsSQLiteHelper;
import com.example.churtado.basket.DomainLayer.Season;
import com.example.churtado.basket.R;

import java.util.List;


public class MainMenu extends ActionBarActivity {

    Season season = new Season();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnNewSeason = (Button)findViewById(R.id.btnNewSeason);
        Button btnLoadSeason = (Button)findViewById(R.id.btnLoadSeason);

        //If New Season -> insert team name + create a team in TeamsDB + create new Season with
        //this team and the actual year
        btnNewSeason.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                //Modal to enter the team name
                final EditText input = new EditText(MainMenu.this);
                new AlertDialog.Builder(MainMenu.this)
                        .setTitle("New Season")
                        .setMessage("Write the team's name to create a new season:")
                        .setView(input)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String teamName = String.valueOf(input.getText());
                                //Create the new season
                                season.createSeason(MainMenu.this, teamName);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
                    }
                }).show();
            }
        });

        //If Load Season -> load a list of the saved Seasons + select on and load it
        btnLoadSeason.setOnClickListener(new View.OnClickListener() {
             public void onClick(View arg0) {
                 //Modal with all the seasons stored
                 final List<Season> seasonsList = new Season().getAllSeasons(MainMenu.this);
                 String[] seasonsNames = new String[seasonsList.size()];
                 for(int i = 0; i < seasonsList.size(); ++i)
                     seasonsNames[i] = seasonsList.get(i).getTeamName() + " - " + seasonsList.get(i).getYear();
                 new AlertDialog.Builder(MainMenu.this)
                         .setTitle("Load Season")
                         .setItems(seasonsNames, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Load the selected season
                                season.loadSeason(MainMenu.this, seasonsList.get(which).getSeasonId());
                            }
                 }).show();
             }
         });
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
