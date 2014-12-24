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
 * Created by churtado on 16/12/2014.
 */
public class StatsFragment extends Fragment {
    FragmentTabHost tabHost;

    public StatsFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tabHost = new FragmentTabHost(getActivity());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        tabHost.addTab(tabHost.newTabSpec("home").setIndicator("Home"),
                StatsHomeFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("guest").setIndicator("Guest"),
                StatsGuestFragment.class, null);

        return tabHost;
    }

    public void onDestroyView() {
        super.onDestroyView();
        tabHost = null;
    }
}
