package com.example.churtado.basket.UILayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.R;


public class Game extends ActionBarActivity {

    GameStats gameStats = GameStats.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView txtHello = (TextView)findViewById(R.id.txtHello);
        txtHello.setText("Game view. Team A: " + gameStats.getTeamHome() +
                            " Team B: " + gameStats.getTeamGuest() +
                            " Number of players team A: " + String.valueOf(gameStats.getLstPlayerStatsHome().size()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
