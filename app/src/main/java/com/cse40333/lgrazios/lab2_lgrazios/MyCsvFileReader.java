package com.cse40333.lgrazios.lab2_lgrazios;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Luigi on 3/1/17.
 */

public class MyCsvFileReader {
    Context context;

    public MyCsvFileReader(Context applicationContext) {
        this.context = applicationContext;
    }

    public ArrayList<Team> readCsvFile(String fileresource) {
        ArrayList<Team> games = new ArrayList<>();
        InputStream fin = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            fin = context.getAssets().open(fileresource);
            isr = new InputStreamReader(fin);
            reader = new BufferedReader(isr);
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] teamInfo = line.split(",");
                games.add(new Team(teamInfo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fin != null)
                    fin.close();
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        return games;
    }
}
