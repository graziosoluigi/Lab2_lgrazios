package com.cse40333.lgrazios.lab2_lgrazios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            getView();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //onBackPressed();

    }

    @Override
    public void onBackPressed() {
        try {
            getView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getView() throws IOException {
        setContentView(R.layout.activity_main);

        String[][] s = new String[][]{};

        MyCsvFileReader myCsvFileReader = new MyCsvFileReader(getApplicationContext());

        final ArrayList<Team> teamInfo = myCsvFileReader.readCsvFile("schedule.txt");

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, teamInfo);



        ListView schedulelistView = (ListView) findViewById(R.id.scheduleListView);
        schedulelistView.setAdapter(scheduleAdapter);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Write code here to open the activity that will show details of the game event,i.e. if
                // you click on Florida State, you should see details of the match between Florida State
                // and Notre Dame
                DetailActivity detailActivity = new DetailActivity();
                View  detailActivityView = detailActivity.getView(getBaseContext(), teamInfo.get(position), parent);
                setContentView(detailActivityView);
            }

        };



        schedulelistView.setOnItemClickListener (clickListener);
    }
}
