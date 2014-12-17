package com.example.churtado.basket.UILayer;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.churtado.basket.Adapters.StatsPagerAdapter;
import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.R;


public class Game extends FragmentActivity {

    GameStats gameStats = GameStats.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initilization
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        StatsPagerAdapter statsAdapter = new StatsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(statsAdapter);

    }
}
