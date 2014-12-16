package com.example.churtado.basket.UILayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;

import com.example.churtado.basket.DomainLayer.GameStats;
import com.example.churtado.basket.DomainLayer.Player;
import com.example.churtado.basket.DomainLayer.PlayerStats;
import com.example.churtado.basket.DomainLayer.Season;
import com.example.churtado.basket.R;

import java.util.ArrayList;
import java.util.List;


public class GameConfig extends ActionBarActivity {
    //TODO:limit number pickers
    //TODO:can select if my team is home or guest
    //TODO:more configs to add: place where the game it's played, coaches..

    GameStats gameStats = GameStats.getInstance();
    Season season = Season.getInstance();
    List<PlayerStats> lstPlayerStatsHome;
    List<PlayerStats> lstPlayerStatsGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_config);
        //Initialize the two lists of players
        lstPlayerStatsHome = new ArrayList<PlayerStats>();
        lstPlayerStatsGuest = new ArrayList<PlayerStats>();
        //Set min and max values for the number pickers
        NumberPicker numQuarters = (NumberPicker)findViewById(R.id.quarterNumbers);
        NumberPicker quarterMinutes = (NumberPicker)findViewById(R.id.quarterMinutes);
        NumberPicker extraTimeMinutes = (NumberPicker)findViewById(R.id.extraTimeMinutes);
        numQuarters.setMinValue(0);
        numQuarters.setMaxValue(8);
        quarterMinutes.setMinValue(0);
        quarterMinutes.setMaxValue(20);
        extraTimeMinutes.setMinValue(0);
        extraTimeMinutes.setMaxValue(20);

        //StartGame button. Initialize the GameStats class with the parameters chosen
        Button btnStartGame = (Button)findViewById(R.id.btnStartGame);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NumberPicker numberOfQuarters = (NumberPicker)findViewById(R.id.quarterNumbers);
                NumberPicker minutesPerQuarter = (NumberPicker)findViewById(R.id.quarterMinutes);
                CheckBox extraTimes = (CheckBox)findViewById(R.id.chkExtraTime);
                NumberPicker minutesPerExtraTime = (NumberPicker)findViewById(R.id.extraTimeMinutes);
                EditText txtTeamGuestName = (EditText)findViewById(R.id.txtTeamGuestName);
                gameStats.initGameStats(season.getTeamName(), String.valueOf(txtTeamGuestName.getText()), numberOfQuarters.getValue(), minutesPerQuarter.getValue(), extraTimes.isChecked(), minutesPerExtraTime.getValue(), lstPlayerStatsHome, lstPlayerStatsGuest);
                Intent i = new Intent(getApplicationContext(), Game.class);
                startActivity(i);
            }
        });

        //Button to add home players
        Button btnAddHomePlayers = (Button)findViewById(R.id.btnHomePlayers);
        btnAddHomePlayers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Player> lstHomePlayers = Player.getAllPlayers(GameConfig.this, season.getTeamId());
                showHomePlayersCheckboxList(lstHomePlayers);
            }
        });

        //Button to add guest players
        Button btnAddGuestPlayer = (Button)findViewById(R.id.btnGuestPlayers);
        btnAddGuestPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Modal to enter the name and number of the player
                final EditText inputNumber = new EditText(GameConfig.this);
                inputNumber.setHint("Number");
                final EditText inputName = new EditText(GameConfig.this);
                inputName.setHint("Name");
                LinearLayout layout = new LinearLayout(GameConfig.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(inputNumber);
                layout.addView(inputName);
                new AlertDialog.Builder(GameConfig.this)
                        .setTitle("Guest Player")
                        .setMessage("Write the name and number of the guest player:")
                        .setView(layout)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                int playerNumber = Integer.valueOf(String.valueOf(inputNumber.getText()));
                                String playerName = String.valueOf(inputName.getText());
                                //Add guest player to the list
                                addGuestPlayerToList(playerNumber, playerName);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
                    }
                }).show();
            }
        });
    }

    private void addGuestPlayerToList(int playerNumber, String playerName) {
        //TODO:add only yhe player created each time
        //TODO:Control two players with the same number
        lstPlayerStatsGuest.add(new PlayerStats(0, playerNumber, playerName));
        //Set adapter to the list view of guest team
        ListView playersGuestListToShow = (ListView)findViewById(R.id.lstGuestPlayers);
        String[] strArrayListOfNames = new String[lstPlayerStatsGuest.size()];
        for(int i = 0; i < lstPlayerStatsGuest.size(); ++i) {
            strArrayListOfNames[i] = String.valueOf(lstPlayerStatsGuest.get(i).getPlayerNum()) + "-" + lstPlayerStatsGuest.get(i).getPlayerName();
        }
        ArrayAdapter<String> playersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, strArrayListOfNames);
        playersGuestListToShow.setAdapter(playersAdapter);
    }

    private void showHomePlayersCheckboxList(final List<Player> lstHomePlayers) {
        //TODO:persist the list of selected players. When the dialog is reopened, it has to have the selected players
        final CharSequence[] playersToShow = new CharSequence[lstHomePlayers.size()];
        for(int i = 0; i < lstHomePlayers.size(); ++i) {
            playersToShow[i] = String.valueOf(lstHomePlayers.get(i).getNumPlayer()) + " - " + lstHomePlayers.get(i).getNamePlayer();
        }
        // arraylist to keep the selected items
        final ArrayList selectedPlayers = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select your players");
        builder.setMultiChoiceItems(playersToShow, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected,boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedPlayers.add(indexSelected);
                        } else if (selectedPlayers.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            selectedPlayers.remove(Integer.valueOf(indexSelected));
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setLstPlayerStatsHome(lstHomePlayers, selectedPlayers);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Nothing to do
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setLstPlayerStatsHome(List<Player> lstHomePlayers, ArrayList selectedPlayers) {
        lstPlayerStatsHome = new ArrayList<PlayerStats>();
        String[] strArrayListOfNames = new String[selectedPlayers.size()];
        int position = 0;
        for(int i = 0; i < selectedPlayers.size(); ++i) {
            position = (int)selectedPlayers.get(i);
            lstPlayerStatsHome.add(new PlayerStats(lstHomePlayers.get(position).getPlayerId(), lstHomePlayers.get(position).getNumPlayer(), lstHomePlayers.get(position).getNamePlayer()));
            strArrayListOfNames[i] = String.valueOf(lstHomePlayers.get(position).getNumPlayer()) + "-" + lstHomePlayers.get(position).getNamePlayer();
        }
        fillListPlayersHome(strArrayListOfNames);
    }

    private void fillListPlayersHome(String[] strArrayListOfNames) {
        ListView playersHomeListToShow = (ListView)findViewById(R.id.lstHomePlayers);
        ArrayAdapter<String> playersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, strArrayListOfNames);
        playersHomeListToShow.setAdapter(playersAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_config, menu);
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
