package com.cse40333.lgrazios.lab2_lgrazios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.CAMERA_SERVICE;

/**
 * Created by Luigi on 2/15/17.
 */

public class DetailActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    private static final String AUTHORITY= BuildConfig.APPLICATION_ID+".provider";
    private static Context mContext;
    private static final int CAMERA_REQUEST = 1888;

    DetailActivity (){
    }

    @Override
    public void onCreate (Bundle bundle){
        super.onCreate(bundle);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        mContext = this;

        setContentView(R.layout.activity_detail);

        final DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        final Team GameInfo = (Team) dbHelper.returnTeams().get(getIntent().getIntExtra("Game", 0));

        //dbHelper.returnTeams().get(
        TextView GameDate = (TextView) findViewById(R.id.GameDateTextView);
        GameDate.setText(GameInfo.getLongDate());

        TextView GameLocation = (TextView) findViewById(R.id.GameLocationTextView);
        GameLocation.setText(GameInfo.getLocation());

        ImageView AwayIcon = (ImageView) findViewById(R.id.AwayIcon);
        String AwayDrawableName = GameInfo.getAwayIcon();

        int resIDAway = DetailActivity.this.getResources().getIdentifier(AwayDrawableName , "drawable", DetailActivity.this.getPackageName());
        AwayIcon.setImageResource(resIDAway);

        TextView AwayTeam = (TextView) findViewById(R.id.AwayTeam);
        AwayTeam.setText(GameInfo.getAwayTeam());

        TextView AwayMascot = (TextView) findViewById(R.id.AwayMascot);
        AwayMascot.setText(GameInfo.getAwayMascot());

        TextView AwayRecord = (TextView) findViewById(R.id.AwayRecord);
        AwayRecord.setText(GameInfo.getAwayRecord());

        ImageView HomeIcon = (ImageView) findViewById(R.id.HomeIcon);
        String HomeDrawableName = GameInfo.getHomeIcon();

        int resIDHome = DetailActivity.this.getResources().getIdentifier(HomeDrawableName , "drawable", DetailActivity.this.getPackageName());
        HomeIcon.setImageResource(resIDHome);

        TextView HomeTeam = (TextView) findViewById(R.id.HomeTeam);
        HomeTeam.setText(GameInfo.getHomeTeam());

        TextView HomeMascot = (TextView) findViewById(R.id.HomeMascot);
        HomeMascot.setText(GameInfo.getHomeMascot());

        TextView HomeRecord = (TextView) findViewById(R.id.HomeRecord);
        HomeRecord.setText(GameInfo.getHomeRecord());

        TextView Score = (TextView) findViewById(R.id.Score);
        Score.setText(GameInfo.getScore());

        TextView Time = (TextView) findViewById(R.id.Time);
        Time.setText(GameInfo.getTime());

        Button image_gallery = (Button) findViewById(R.id.CameraButton);
        image_gallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent gallery_activity_intent = new Intent(getApplicationContext(), GalleryActivity.class);

                long team_id = dbHelper.get_id(GameInfo.getAwayTeam());
                gallery_activity_intent.putExtra("id", team_id);

                startActivity(gallery_activity_intent);
            }
        });


    }

    public static Context getContext(){
        return mContext;
    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "BestMoments" + timestamp + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {

                Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                String pictureDirectoryPath = pictureDirectory.getPath();

                Uri imageUri = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    ImageView imgView = (ImageView) findViewById(R.id.photo_taken);
                    imgView.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
