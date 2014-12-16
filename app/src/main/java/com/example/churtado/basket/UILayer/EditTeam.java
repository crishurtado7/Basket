package com.example.churtado.basket.UILayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.churtado.basket.DomainLayer.Player;
import com.example.churtado.basket.DomainLayer.Season;
import com.example.churtado.basket.R;

import java.util.List;


public class EditTeam extends ActionBarActivity {

    Season season = Season.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);

        loadPlayers();
    }

    private void loadPlayers() {
        //TODO:separate addPlayers button from the table layout. Only delete or update the selected row, not all the table
        //TODO:only one player with the same number in the same team
        //TODO:scroll in list
        //Find the table layout
        final TableLayout tblPlayers = (TableLayout)findViewById(R.id.tblPlayers);
        tblPlayers.removeAllViews();
        //Get all the players from the team
        List<Player> players = Player.getAllPlayers(EditTeam.this, season.getTeamId());
        for(int i = 0; i < players.size(); ++i) {
            //Create a new row to be added
            TableRow tbrPlayer = new TableRow(this);
            //Create a hidden TextView to store the player id
            TextView txtPlayerId = new TextView(this);
            txtPlayerId.setText(String.valueOf(players.get(i).getPlayerId()));
            txtPlayerId.setVisibility(View.GONE);
            //Create two TextView to show the info of each player
            TextView txtPlayerNumber = new TextView(this);
            txtPlayerNumber.setText(String.valueOf(players.get(i).getNumPlayer()));
            txtPlayerNumber.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            TextView txtPlayerName = new TextView(this);
            txtPlayerName.setText(players.get(i).getNamePlayer());
            txtPlayerName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            //Create a Button to edit the player
            Button btnEditPlayer = new Button(this);
            btnEditPlayer.setText("Edit");
            btnEditPlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            btnEditPlayer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //Get all the row with the player's info
                    TableRow tbrSelectedPlayer = (TableRow)(((Button)arg0).getParent());
                    TextView txtPlayerId = (TextView)tbrSelectedPlayer.getChildAt(0);
                    TextView txtPlayerNum = (TextView)tbrSelectedPlayer.getChildAt(1);
                    TextView txtPlayerName = (TextView)tbrSelectedPlayer.getChildAt(2);
                    String strPlayerId = String.valueOf(txtPlayerId.getText());
                    final int playerId = Integer.valueOf(strPlayerId);
                    //Modal to enter the name and number of the player
                    final EditText inputNumber = new EditText(EditTeam.this);
                    inputNumber.setText(txtPlayerNum.getText());
                    final EditText inputName = new EditText(EditTeam.this);
                    inputName.setText(txtPlayerName.getText());
                    LinearLayout layout = new LinearLayout(EditTeam.this);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.addView(inputNumber);
                    layout.addView(inputName);
                    new AlertDialog.Builder(EditTeam.this)
                            .setTitle("Update Player")
                            .setMessage("Write the new name and/or number to update the player:")
                            .setView(layout)
                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    int playerNumber = Integer.valueOf(String.valueOf(inputNumber.getText()));
                                    String playerName = String.valueOf(inputName.getText());
                                    //Update the player
                                    Player playerToUpdate = new Player(playerId, season.getTeamId(), playerNumber, playerName);
                                    playerToUpdate.updatePlayer(EditTeam.this);
                                    loadPlayers();
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Do nothing.
                        }
                    }).show();
                }
            });
            //Create a Button to delete the player
            Button btnDeletePlayer = new Button(this);
            btnDeletePlayer.setText("Delete");
            btnDeletePlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            btnDeletePlayer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //Get all the row with the player's info
                    TableRow tbrSelectedPlayer = (TableRow)(((Button)arg0).getParent());
                    TextView txtPlayerId = (TextView)tbrSelectedPlayer.getChildAt(0);
                    final int playerId = Integer.valueOf(String.valueOf(txtPlayerId.getText()));
                    //Modal to confirm player's deletion
                    new AlertDialog.Builder(EditTeam.this)
                            .setTitle("Delete Player")
                            .setMessage("Are you sure you want to delete this player?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Delete the player
                                    Player playerToDelete = new Player(playerId, season.getTeamId(), 0, "");
                                    playerToDelete.deletePlayer(EditTeam.this);
                                    loadPlayers();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Do nothing.
                        }
                    }).show();
                }
            });
            //Add the controls to the row
            tbrPlayer.addView(txtPlayerId);
            tbrPlayer.addView(txtPlayerNumber);
            tbrPlayer.addView(txtPlayerName);
            tbrPlayer.addView(btnEditPlayer);
            tbrPlayer.addView(btnDeletePlayer);
            //Add row to the layout
            tbrPlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
            tblPlayers.addView(tbrPlayer, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        //Create a new button to add new players
        //TODO:Check that the user enters the number and the name
        //TODO:Check that the number is only integers and name only numbers
        TableRow tbrNewPlayer = new TableRow(this);
        final Button btnAddPlayer = new Button(this);
        btnAddPlayer.setText("Add Player");
        btnAddPlayer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        //Click function to add a new player
        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Modal to enter the name and number of the player
                final EditText inputNumber = new EditText(EditTeam.this);
                inputNumber.setHint("Number");
                final EditText inputName = new EditText(EditTeam.this);
                inputName.setHint("Name");
                LinearLayout layout = new LinearLayout(EditTeam.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(inputNumber);
                layout.addView(inputName);
                new AlertDialog.Builder(EditTeam.this)
                        .setTitle("New Player")
                        .setMessage("Write the name and number of the new player:")
                        .setView(layout)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                int playerNumber = Integer.valueOf(String.valueOf(inputNumber.getText()));
                                String playerName = String.valueOf(inputName.getText());
                                //Create the new player
                                Player newPlayer = new Player(season.getTeamId(), playerNumber, playerName);
                                newPlayer.createPlayer(EditTeam.this);
                                loadPlayers();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
                    }
                }).show();
            }
        });
        tblPlayers.addView(btnAddPlayer, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_team, menu);
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
