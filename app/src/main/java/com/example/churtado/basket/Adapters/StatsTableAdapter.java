package com.example.churtado.basket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.churtado.basket.DomainLayer.PlayerStats;
import com.example.churtado.basket.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by churtado on 19/12/2014.
 */
public class StatsTableAdapter extends ArrayAdapter<PlayerStats> {
    // View lookup cache
    private static class ViewHolder {
        TextView txtPlayerNumber;
        TextView txtPlayerName;
        TextView txtPlayerMinutes;
        TextView txtPlayerPoints;
        TextView txtPlayerRebounds;
        TextView txtPlayerVal;
    }

    public StatsTableAdapter(Context context, ArrayList<PlayerStats> lstPlayerStats) {
        super(context, 0, lstPlayerStats);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PlayerStats playerStats = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_stats_row, parent, false);
            viewHolder.txtPlayerNumber = (TextView) convertView.findViewById(R.id.txtPlayerNumber);
            viewHolder.txtPlayerName = (TextView) convertView.findViewById(R.id.txtPlayerName);
            viewHolder.txtPlayerMinutes = (TextView) convertView.findViewById(R.id.txtPlayerMinutes);
            viewHolder.txtPlayerPoints = (TextView) convertView.findViewById(R.id.txtPlayerPoints);
            viewHolder.txtPlayerRebounds = (TextView) convertView.findViewById(R.id.txtPlayerRebounds);
            viewHolder.txtPlayerVal = (TextView) convertView.findViewById(R.id.txtPlayerVal);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.txtPlayerNumber.setText(String.valueOf(playerStats.getPlayerNum()));
        viewHolder.txtPlayerName.setText(playerStats.getPlayerName());
        viewHolder.txtPlayerMinutes.setText(formatMinutes(playerStats.getMinutes()));
        viewHolder.txtPlayerPoints.setText(String.valueOf(playerStats.getTotalPoints()));
        int totalRebounds = playerStats.getDefRebounds() + playerStats.getOffRebounds();
        viewHolder.txtPlayerRebounds.setText(String.valueOf(totalRebounds));
        viewHolder.txtPlayerVal.setText(String.valueOf(playerStats.getValuation()));
        // Return the completed view to render on screen
        return convertView;
    }

    private String formatMinutes(long millisToFormat) {
        String minutes = String.format("%02d", millisToFormat/60000);
        int seconds = (int)( (millisToFormat%60000)/1000);
        String formattedMillis =  minutes + ":"+String.format("%02d",seconds);
        return formattedMillis;
    }
}
