package com.cse40333.lgrazios.lab2_lgrazios;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Team> teamInfo = new ArrayList<Team>();
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DatabaseHelper(getApplicationContext());
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 0, 1);

        MyCsvFileReader myCsvFileReader = new MyCsvFileReader(getApplicationContext());

        teamInfo.clear();
        teamInfo = myCsvFileReader.readCsvFile("schedule.txt");


        for (Team tmp : teamInfo) {
            dbHelper.insertData(tmp);
            //everything is successfully inserted!
        }

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

        // Initialize toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("ND Athletics");

        teamInfo.clear();
        teamInfo = dbHelper.returnTeams();

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, teamInfo);



        ListView schedulelistView = (ListView) findViewById(R.id.scheduleListView);
        schedulelistView.setAdapter(scheduleAdapter);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Write code here to open the activity that will show details of the game event,i.e. if
                // you click on Florida State, you should see details of the match between Florida State
                // and Notre Dame
                DetailActivity detailActivity = new DetailActivity();
                View  detailActivityView = detailActivity.getView(getBaseContext(), dbHelper.returnTeams().get(position), parent);
                setContentView(detailActivityView);
            }

        };



        schedulelistView.setOnItemClickListener (clickListener);
    }

    String gameSchedule() {
        StringBuilder sb = new StringBuilder();
        for (Team team : dbHelper.returnTeams()) {
            sb.append(team.getGameString() + "\n");
        }
        return sb.toString();
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BasketBall Matches");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, gameSchedule());
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        }

        else if (res_id == R.id.sync) {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Sync is not yet implemented", Snackbar.LENGTH_LONG);
            // get snackbar view
            View snackbarView = snackbar.getView();
            TextView tv1 = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            tv1.setTextColor(Color.WHITE);

            snackbar.setAction("Try Again", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar snackbar2 = Snackbar.make(coordinatorLayout, "Wait for the next few labs. Thank you for your patience", Snackbar.LENGTH_LONG);
                    View snackbarView2 = snackbar2.getView();
                    TextView tv2 = (TextView) snackbarView2.findViewById(android.support.design.R.id.snackbar_text);
                    tv2.setTextColor(Color.WHITE);
                    snackbar2.show();
                }
            });
            snackbar.show();
        }

        else if (res_id == R.id.settings) {
            View v = findViewById(R.id.scheduleListView);
            registerForContextMenu(v);
            openContextMenu(v);
            unregisterForContextMenu(v);
        }
        return true;
    }
}
