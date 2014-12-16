package com.example.churtado.basket.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.churtado.basket.UILayer.Game;
import com.example.churtado.basket.UILayer.GameFragment;
import com.example.churtado.basket.UILayer.StatsFragment;

/**
 * Created by churtado on 16/12/2014.
 */
public class StatsPagerAdapter extends FragmentPagerAdapter {

    public StatsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // Game fragment activity
                return new GameFragment();
            case 1:
                // Stats fragment activity
                return new StatsFragment();
        }
        return null;
    }

    public int getCount() {
        return 2;
    }
}
