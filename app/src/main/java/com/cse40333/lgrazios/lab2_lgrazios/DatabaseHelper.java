package com.cse40333.lgrazios.lab2_lgrazios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Luigi on 4/8/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase database;
    private static String DATABASE_NAME = "DBName";
    private static final int DATABASE_VERSION = 1;

    //Table Name
    private static final String TEAMS_NAME = "teams";


    //column names
    private static final String TEAM_COL_ID = "_id";
    private static final String LONG_DATE = "l_date";
    private static final String LOCATION = "loc";
    private static final String AWAY_ICON = "a_icon";
    private static final String AWAY_TEAM = "a_team";
    private static final String AWAY_MASCOT = "a_masc";
    private static final String AWAY_RECORD = "a_rec";
    private static final String HOME_ICON = "h_icon";
    private static final String HOME_TEAM = "h_team";
    private static final String HOME_MASCOT = "h_masc";
    private static final String HOME_RECORD = "h_rec";
    private static final String SCORE = "score";
    private static final String TIME = "time";
    private static final String SHORT_DATE = "s_date";

    public static String IMAGES_NAME = "images";
    public static String IMAGE_COL_ID = "_id";
    public static String IMAGE_TEAM_COL_ID = "image_team_id";
    public static String IMAGE_SRC = "image_src";
    public static String IMAGE_URI = "image_uri";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        String T_DATABASE_CREATE = "CREATE TABLE " + TEAMS_NAME + " ( "
                + TEAM_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LONG_DATE + " TEXT,"
                + LOCATION + " TEXT,"
                + AWAY_ICON + " TEXT,"
                + AWAY_TEAM + " TEXT,"
                + AWAY_MASCOT + " TEXT,"
                + AWAY_RECORD + " TEXT,"
                + HOME_ICON + " TEXT,"
                + HOME_TEAM + " TEXT,"
                + HOME_MASCOT + " TEXT,"
                + HOME_RECORD + " TEXT,"
                + SCORE + " TEXT,"
                + TIME + " TEXT,"
                + SHORT_DATE + " TEXT )";
        database.execSQL(T_DATABASE_CREATE);

        database.execSQL("CREATE TABLE " + IMAGES_NAME + " ("
                + IMAGE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IMAGE_TEAM_COL_ID + " TEXT, "
                + IMAGE_SRC + " BLOB, "
                + IMAGE_URI + " TEXT"
                + ");");

    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){

        //Drop older table if existed
        database.execSQL("DROP TABLE IF EXISTS " + TEAMS_NAME);

        database.execSQL("DROP TABLE IF EXISTS " + IMAGES_NAME);

        //create tables again
        onCreate(database);
    }

    public void insertData(Team team) {
        SQLiteDatabase database = getWritableDatabase();

        //insert into Team table
        ContentValues contentValues = new ContentValues();
        contentValues.put(LONG_DATE, team.getLongDate());
        contentValues.put(LOCATION, team.getLocation());
        contentValues.put(AWAY_ICON, team.getAwayIcon());
        contentValues.put(AWAY_TEAM, team.getAwayTeam());
        contentValues.put(AWAY_MASCOT, team.getAwayMascot());
        contentValues.put(AWAY_RECORD, team.getAwayRecord());
        contentValues.put(HOME_ICON, team.getHomeIcon());
        contentValues.put(HOME_TEAM, team.getHomeTeam());
        contentValues.put(HOME_MASCOT, team.getHomeMascot());
        contentValues.put(HOME_RECORD, team.getHomeRecord());
        contentValues.put(SCORE, team.getScore());
        contentValues.put(TIME, team.getTime());
        contentValues.put(SHORT_DATE, team.getShortDate());


        long ret = database.insert(TEAMS_NAME, null, contentValues);

        if (ret > 0) {
            System.out.println("successfully inserted");
        } else {
            System.out.println("insert unsuccessful");
        }

        database.close();
    }

    public void insertDataImages(String tableName, ContentValues contentValues) {
        SQLiteDatabase database = getWritableDatabase();

        long ret = database.insert(IMAGES_NAME, null, contentValues);

        if (ret > 0) {
            System.out.println("successfully inserted");
        } else {
            System.out.println("insert unsuccessful");
        }

        database.close();
    }

    public void deleteData(int _id) {
        database = getWritableDatabase();
        database.delete(TEAMS_NAME, " _id = ?", new String[]{Integer.toString(_id)});
        database.close();
    }


    public ArrayList<Team> returnTeams() {
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TEAMS_NAME, new String[]{});

        ArrayList<Team> games = new ArrayList<Team>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    String [] input = new String [13];

                    input[0] = (cursor.getString(cursor.getColumnIndex(LONG_DATE)));
                    input[1] = (cursor.getString(cursor.getColumnIndex(LOCATION)));
                    input[2] = (cursor.getString(cursor.getColumnIndex(AWAY_ICON)));
                    input[3] = (cursor.getString(cursor.getColumnIndex(AWAY_TEAM)));
                    input[4] = (cursor.getString(cursor.getColumnIndex(AWAY_MASCOT)));
                    input[5] = (cursor.getString(cursor.getColumnIndex(AWAY_RECORD)));
                    input[6] = (cursor.getString(cursor.getColumnIndex(HOME_ICON)));
                    input[7] = (cursor.getString(cursor.getColumnIndex(HOME_TEAM)));
                    input[8] = (cursor.getString(cursor.getColumnIndex(HOME_MASCOT)));
                    input[9] = (cursor.getString(cursor.getColumnIndex(HOME_RECORD)));
                    input[10] = (cursor.getString(cursor.getColumnIndex(SCORE)));
                    input[11] = (cursor.getString(cursor.getColumnIndex(TIME)));
                    input[12] = (cursor.getString(cursor.getColumnIndex(SHORT_DATE)));

                    Team team = new Team(input);

                    games.add(team);

                }while (cursor.moveToNext());
            }
        }

        cursor.close();

        return games;
    }

    public Cursor getSelectEntries(String tablename, String[] columns, String where, String[] args) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tablename, columns, where, args, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public long get_id(String team_name){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TEAMS_NAME +
                " WHERE " + AWAY_TEAM + "=" + "'" + team_name + "'", null);

        long team_id = 0;

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    team_id = (cursor.getLong(cursor.getColumnIndex(TEAM_COL_ID)));
                }while (cursor.moveToNext());
            }
        }

        cursor.close();

        return team_id;
    }
}