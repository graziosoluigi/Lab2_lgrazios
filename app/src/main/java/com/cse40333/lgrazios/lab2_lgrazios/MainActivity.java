package com.cse40333.lgrazios.lab2_lgrazios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[][] s = new String[][]{
                {"florida_state", "Florida State", "Feb 11"},
                {"boston_college", "Boston College", "Feb 14"},
                {"north_carolina_state", "North Carolina State", "Feb 18"},
                {"georgia_tech", "Georgia Tech", "Feb 26"},
                {"boston_college", "Boston College", "March 1"},
                {"louisville", "Louisville", "March 4"},
                {"acc", "ACC Tournament", "March 7"},
                {"ncaa", "NCAA Tournament", "March 16"}
            };

        ArrayList<String[]> teamInfo = new ArrayList<String[]>();
        for (int i = 0; i < s.length; i++) {
            teamInfo.add(s[i]);
        }

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, teamInfo);

        ListView schedulelistView = (ListView) findViewById(R.id.scheduleListView);
        schedulelistView.setAdapter(scheduleAdapter);
    }
}
