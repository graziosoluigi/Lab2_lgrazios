package com.cse40333.lgrazios.lab2_lgrazios;

/**
 * Created by Luigi on 2/16/17.
 */

public class Team {
    String LongDate, Location, AwayIcon, AwayTeam, AwayMascot, AwayRecord,
            HomeIcon, HomeTeam, HomeMascot, HomeRecord, Score, Time, ShortDate;

    Team(String[] Info){
        LongDate = Info[0];
        Location = Info[1];
        AwayIcon = Info[2];
        AwayTeam = Info[3];
        AwayMascot = Info[4];
        AwayRecord = Info[5];
        HomeIcon = Info[6];
        HomeTeam = Info[7];
        HomeMascot = Info[8];
        HomeRecord = Info[9];
        Score = Info[10];
        Time = Info[11];
        ShortDate = Info[12];
    }

    public String getLongDate() {
        return LongDate;
    }

    public String getLocation() {
        return Location;
    }

    public String getAwayIcon() {
        return AwayIcon;
    }

    public String getAwayTeam() {
        return AwayTeam;
    }

    public String getAwayMascot() {
        return AwayMascot;
    }

    public String getAwayRecord() {
        return AwayRecord;
    }

    public String getHomeIcon() {
        return HomeIcon;
    }

    public String getHomeTeam() {
        return HomeTeam;
    }

    public String getHomeMascot() {
        return HomeMascot;
    }

    public String getHomeRecord() {
        return HomeRecord;
    }

    public String getScore() {
        return Score;
    }

    public String getTime() {
        return Time;
    }

    public String getShortDate() {
        return ShortDate;
    }
}
