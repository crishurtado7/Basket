package com.example.churtado.basket.UILayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.churtado.basket.DomainLayer.Season;
import com.example.churtado.basket.R;


public class SeasonMenu extends ActionBarActivity {

    Season season = Season.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_menu);

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setText("Season " + season.getYear() + " Team " + season.getTeamName());

        Button btnNewGame = (Button)findViewById(R.id.btnNewGame);
        Button btnEditTeam = (Button)findViewById(R.id.btnEditTeam);

        //If New Game is selected, change to configuration panel to start a new game
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Intent i = new Intent(getApplicationContext(), GameConfig.class);
                startActivity(i);
            }
        });

        //If Edit Team is selected, change to edit team
        btnEditTeam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Intent i = new Intent(getApplicationContext(), EditTeam.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_season_menu, menu);
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
