package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.churtado.basket.R;

/**
 * Created by churtado on 17/12/2014.
 */
public class GameScoreBox extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_game_score_box, container, false);

        return rootView;
    }
}
