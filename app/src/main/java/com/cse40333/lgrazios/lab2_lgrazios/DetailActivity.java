package com.cse40333.lgrazios.lab2_lgrazios;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Luigi on 2/15/17.
 */

public class DetailActivity {

    DetailActivity (){
    }


    public View getView (final Context context, Team GameInfo, ViewGroup parent){

        LayoutInflater detailInflater = LayoutInflater.from(context);

        View detailView = detailInflater.inflate(R.layout.activity_detail, parent, false);

        TextView GameDate = (TextView) detailView.findViewById(R.id.GameDateTextView);
        GameDate.setText(GameInfo.getLongDate());

        TextView GameLocation = (TextView) detailView.findViewById(R.id.GameLocationTextView);
        GameLocation.setText(GameInfo.getLocation());

        ImageView AwayIcon = (ImageView) detailView.findViewById(R.id.AwayIcon);
        String AwayDrawableName = GameInfo.getAwayIcon();

        int resIDAway = context.getResources().getIdentifier(AwayDrawableName , "drawable", context.getPackageName());
        AwayIcon.setImageResource(resIDAway);

        TextView AwayTeam = (TextView) detailView.findViewById(R.id.AwayTeam);
        AwayTeam.setText(GameInfo.getAwayTeam());

        TextView AwayMascot = (TextView) detailView.findViewById(R.id.AwayMascot);
        AwayMascot.setText(GameInfo.getAwayMascot());

        TextView AwayRecord = (TextView) detailView.findViewById(R.id.AwayRecord);
        AwayRecord.setText(GameInfo.getAwayRecord());

        ImageView HomeIcon = (ImageView) detailView.findViewById(R.id.HomeIcon);
        String HomeDrawableName = GameInfo.getHomeIcon();

        int resIDHome = context.getResources().getIdentifier(HomeDrawableName , "drawable", context.getPackageName());
        HomeIcon.setImageResource(resIDHome);

        TextView HomeTeam = (TextView) detailView.findViewById(R.id.HomeTeam);
        HomeTeam.setText(GameInfo.getHomeTeam());

        TextView HomeMascot = (TextView) detailView.findViewById(R.id.HomeMascot);
        HomeMascot.setText(GameInfo.getHomeMascot());

        TextView HomeRecord = (TextView) detailView.findViewById(R.id.HomeRecord);
        HomeRecord.setText(GameInfo.getHomeRecord());

        TextView Score = (TextView) detailView.findViewById(R.id.Score);
        Score.setText(GameInfo.getScore());

        TextView Time = (TextView) detailView.findViewById(R.id.Time);
        Time.setText(GameInfo.getTime());

        detailView.findViewById(R.id.CameraButton).setOnClickListener(new View.OnClickListener() {

            //perform camera open action
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                context.startActivity(i);
            }
        });

        return detailView;
    }

}
