package com.cse40333.lgrazios.lab2_lgrazios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
                {"Saturday, February 11, 6:00PM", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "florida_state",
                "Florida Sate", "Seminoles", "(21-5)", "fighting_irish", "Notre Dame", "Fighting Irish", "(19-7)", "72-84", "Final",
                "Feb. 11"},
                {"Monday, February 14, 7:00PM", "Silvio O. Conte Forum, Chestnut Hill, MA", "boston_college",
                 "Boston College", "Eagles", "(9-18)", "fighting_irish", "Notre Dame", "Fighting Irish", "(20-7)", "76-84", "Final",
                "Feb. 14"},
                {"Saturday, February 18, 12:00PM", "PNC Arena, Raleigh, NC", "north_carolina_state",
                "North Carolina State", "Tacos", "(14-12)", "fighting_irish", "Notre Dame", "Fighting Irish", "(20-7)", "0-0", "",
                "Feb. 18"},
                {"Sunday, February 26, 6:30PM", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "georgia_tech",
                "Georgia Tech", "Hornets", "(15-10)", "fighting_irish", "Notre Dame", "Fighting Irish", "(20-7)", "0-0", "",
                "Feb. 26"},
                {"Wednesday, March 1, 6:30PM", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "boston_college",
                "Boston College", "Eagles", "(9-18)", "fighting_irish", "Notre Dame", "Fighting Irish", "(20-7)", "0-0", "",
                "Mar. 1"},
                {"Saturday, March 4, 2:00PM", "KFC Yum! Center, Lousiville, KY", "louisville",
                "Louisville", "Cardinals", "(21-5)", "fighting_irish", "Notre Dame", "Fighting Irish", "(20-7)", "0-0", "",
                "Mar. 4"},
                {"Tuesday, March 7, 5:30PM", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "acc",
                 "ACC Tournament", "acc", "(0-0)", "fighting_irish", "Notre Dame", "Fighting Irish", "(20-7)", "0-0", "",
                 "Mar. 7"},
                {"Thursday, March 16, 8:30PM", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "ncaa",
                 "NCAA Tournament", "ncaa", "(0-0)", "fighting_irish", "Notre Dame", "Fighting Irish", "(20-7)", "0-0", "",
                 "Mar. 16"}
            };

        final ArrayList<Team> teamInfo = new ArrayList<Team>();
        for (int i = 0; i < s.length; i++) {
            teamInfo.add(new Team(s[i]));
        }

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
