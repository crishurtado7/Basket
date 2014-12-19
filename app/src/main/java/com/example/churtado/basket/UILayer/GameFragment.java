package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.churtado.basket.R;

/**
 * Created by churtado on 16/12/2014.
 */
public class GameFragment extends Fragment {
    View rootView = null;
    //TODO:loading http://stackoverflow.com/questions/1979524/android-splashscreen
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_game, container, false);

        return rootView;
    }
}
