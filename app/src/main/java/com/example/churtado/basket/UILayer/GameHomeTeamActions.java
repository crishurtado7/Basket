package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.churtado.basket.R;

/**
 * Created by churtado on 17/12/2014.
 */
public class GameHomeTeamActions extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_game_home_team_actions, container, false);

        //Get all the buttons to set the proper actions
        Button btnT1DoneHome = (Button) rootView.findViewById(R.id.btnT1DoneHome);
        Button btnT2DoneHome = (Button) rootView.findViewById(R.id.btnT2DoneHome);
        Button btnT3DoneHome = (Button) rootView.findViewById(R.id.btnT3DoneHome);
        Button btnOffReboundHome = (Button) rootView.findViewById(R.id.btnOffReboundHome);
        Button btnT1FailedHome = (Button) rootView.findViewById(R.id.btnT1FailedHome);
        Button btnT2FailedHome = (Button) rootView.findViewById(R.id.btnT2FailedHome);
        Button btnT3FailedHome = (Button) rootView.findViewById(R.id.btnT3FailedHome);
        Button btnDefReboundHome = (Button) rootView.findViewById(R.id.btnDefReboundHome);
        Button btnStealHome = (Button) rootView.findViewById(R.id.btnStealHome);
        Button btnTurnoverHome = (Button) rootView.findViewById(R.id.btnTurnoverHome);
        Button btnBlockHome = (Button) rootView.findViewById(R.id.btnBlockHome);
        Button btnCommittedFoulHome = (Button) rootView.findViewById(R.id.btnCommittedFoulHome);
        //Set the onclick function to each button
        btnT1DoneHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t1DoneHome(rootView);
            }
        });

        return rootView;
    }

    private void t1DoneHome(View v) {
        //Show modal to select the player

        //Add point to the player and to the team
    }

    private void t2DoneHome(View v) {

    }

    private void t3DoneHome(View v) {

    }

    private void t1FailedHome(View v) {

    }

    private void t2FailedHome(View v) {

    }

    private void t3FailedHome(View v) {

    }

    private void offReboundHome(View v) {

    }

    private void defReboundHome(View v) {

    }

    private void stealHome(View v) {

    }

    private void turnoverHome(View v) {

    }

    private void committedBlockHome(View v) {

    }

    private void committedFoulHome(View v) {

    }
}
