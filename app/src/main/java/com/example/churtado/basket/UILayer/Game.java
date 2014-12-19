package com.example.churtado.basket.UILayer;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.example.churtado.basket.Adapters.StatsPagerAdapter;
import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.R;


public class Game extends FragmentActivity {

    GameStats gameStats = GameStats.getInstance();
    ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initilization
        viewPager = (ViewPager)findViewById(R.id.pager);
        StatsPagerAdapter statsAdapter = new StatsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(statsAdapter);

    }

    private String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    public void updateHomeScoreBoxFragment() {
        String fragmentName = makeFragmentName(viewPager.getId(), 0);
        GameFragment gameFragment = (GameFragment)getSupportFragmentManager().findFragmentByTag(fragmentName);
        GameScoreBox gameScoreBox = (GameScoreBox)gameFragment.getChildFragmentManager().findFragmentById(R.id.frgScoreBox);
        gameScoreBox.updateHomeTeamScore();
    }

    public void updateGuestScoreBoxFragment() {
        String fragmentName = makeFragmentName(viewPager.getId(), 0);
        GameFragment gameFragment = (GameFragment)getSupportFragmentManager().findFragmentByTag(fragmentName);
        GameScoreBox gameScoreBox = (GameScoreBox)gameFragment.getChildFragmentManager().findFragmentById(R.id.frgScoreBox);
        gameScoreBox.updateGuestTeamScore();
    }

}
