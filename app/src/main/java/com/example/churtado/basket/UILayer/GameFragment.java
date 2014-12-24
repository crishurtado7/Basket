package com.example.churtado.basket.UILayer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.churtado.basket.R;

/**
 * Created by churtado on 16/12/2014.
 */
public class GameFragment extends Fragment {
    View rootView = null;
    ProgressDialog spinner = null;

    //TODO:loading http://stackoverflow.com/questions/1979524/android-splashscreen
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_game, container, false);

        new AlertDialog.Builder(rootView.getContext())
                .setTitle("Information")
                .setMessage("Some information")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Do nothing
                    }
                })
                .show();

        new Thread(new Runnable() {
            public void run() {
                GameHomeTeamPlayers.getInstance().Initialize(rootView);
                GameGuestTeamPlayers.getInstance().Initialize(rootView);
                GameHomeTeamActions.getInstance().Initialize(rootView);
                GameGuestTeamActions.getInstance().Initialize(rootView);
            }
        }).start();

        GameScoreBox.getInstance().Initialize(rootView);

        return rootView;
    }
}
