package com.example.churtado.basket.UILayer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.R;

/**
 * Created by churtado on 17/12/2014.
 */
public class GameScoreBox {
    //TODO:stop timer when it's foul, switch... (if it is not stopped)
    GameStats gameStats = GameStats.getInstance();
    long amountOfMillisecondsPerQuarter;
    long amountOfMillisecondsPerExtraTime;
    boolean isStarted;
    boolean isPaused;
    long remainingMilliseconds;
    CountDownTimer cT = null;
    TextView txtGameTime = null;
    TextView txtGameQuarter = null;
    TextView txtHomeTeamPoints = null;
    TextView txtGuestTeamPoints = null;
    Button btnGameTimeStart = null;
    int currentQuarter;

    private static final GameScoreBox gameScoreBox = new GameScoreBox();
    public static GameScoreBox getInstance() {return gameScoreBox;}

    public void Initialize(View rootView) {

        btnGameTimeStart = (Button) rootView.findViewById(R.id.btnGameTimeStart);
        txtGameTime = (TextView) rootView.findViewById(R.id.txtGameTime);
        txtGameQuarter = (TextView) rootView.findViewById(R.id.txtGameQuarter);
        txtHomeTeamPoints = (TextView) rootView.findViewById(R.id.txtHomeTeamPoints);
        txtGuestTeamPoints = (TextView) rootView.findViewById(R.id.txtGuestTeamPoints);

        txtHomeTeamPoints.setText(String.valueOf(0));
        txtGuestTeamPoints.setText(String.valueOf(0));

        amountOfMillisecondsPerQuarter = gameStats.getMinutesPerQuarter()*60*1000;
        amountOfMillisecondsPerExtraTime = gameStats.getMinutesPerExtraTime()*60*1000;

        isStarted = false;
        isPaused = true;
        remainingMilliseconds = 0;

        currentQuarter = 1;
        setGameQuarter(currentQuarter);

        setTimeLeft(amountOfMillisecondsPerQuarter);

        createCountdownTimer(amountOfMillisecondsPerQuarter);

        btnGameTimeStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!isStarted) {
                    createCountdownTimer(amountOfMillisecondsPerQuarter);
                    setTimeLeft(amountOfMillisecondsPerQuarter);
                    setGameQuarter(currentQuarter);
                    cT.start();
                    isPaused = false;
                    isStarted = true;
                    btnGameTimeStart.setText("Pause");
                }
                else if(!isPaused) {
                    cT.cancel();
                    isPaused = true;
                    btnGameTimeStart.setText("Start");
                }
                else if(isStarted && isPaused) {
                    createCountdownTimer(remainingMilliseconds);
                    cT.start();
                    isPaused = false;
                    btnGameTimeStart.setText("Pause");
                }
            }
        });
    }

    public void updateHomeTeamScore() {
        txtHomeTeamPoints.setText(String.valueOf(gameStats.getTeamHomeTotalPoints()));
    }

    public void updateGuestTeamScore() {
        txtGuestTeamPoints.setText(String.valueOf(gameStats.getTeamGuestTotalPoints()));
    }

    private void setGameQuarter(int quarter) {
        if(quarter <= gameStats.getNumberOfQuarters()) txtGameQuarter.setText(String.valueOf(currentQuarter));
        else txtGameQuarter.setText("ET");
    }

    private void setTimeLeft(long millisUntilFinished) {
        String minutes = String.format("%02d", millisUntilFinished/60000);
        int seconds = (int)( (millisUntilFinished%60000)/1000);
        txtGameTime.setText(minutes + ":"+String.format("%02d",seconds));
        gameStats.setMillisPlayed(remainingMilliseconds - millisUntilFinished);
    }

    private void createCountdownTimer(long amountOfMillis) {
        cT =  new CountDownTimer(amountOfMillis, 1000) {

            public void onTick(long millisUntilFinished) {
                remainingMilliseconds = millisUntilFinished;
                setTimeLeft(remainingMilliseconds);
                updateHomeTeamScore();
                updateGuestTeamScore();
            }

            public void onFinish() {
                remainingMilliseconds = 0;
                isStarted = false;
                isPaused = true;
                setTimeLeft(0);
                currentQuarter += 1;
                btnGameTimeStart.setText("Start");
                cT.cancel();
            }
        };
    }
}
