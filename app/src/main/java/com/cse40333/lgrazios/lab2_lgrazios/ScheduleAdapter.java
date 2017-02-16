package com.cse40333.lgrazios.lab2_lgrazios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luigi on 2/8/17.
 */

class ScheduleAdapter extends ArrayAdapter<Team> {
    ScheduleAdapter (Context context, ArrayList<Team> schedule) {
        super(context, R.layout.schedule_item, (ArrayList<Team>) schedule);
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);

        Team matchItem = getItem(position);
        TextView teamName = (TextView) scheduleView.findViewById(R.id.teamName);
        teamName.setText(matchItem.getAwayTeam());

        TextView teamDate = (TextView) scheduleView.findViewById(R.id.date);
        teamDate.setText(matchItem.getShortDate());

        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
        String mDrawableName = matchItem.getAwayIcon();

        int resID = getContext().getResources().getIdentifier(mDrawableName , "drawable", getContext().getPackageName());
        teamLogo.setImageResource(resID );


        return scheduleView;
    }
}
