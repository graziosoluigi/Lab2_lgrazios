package com.cse40333.lgrazios.lab2_lgrazios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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

public class DetailActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;

    DetailActivity (){
    }


    public View getView (final Context context, Team GameInfo, ViewGroup parent){

        LayoutInflater detailInflater = LayoutInflater.from(context);

        final View detailView = detailInflater.inflate(R.layout.activity_detail, parent, false);

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

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File PictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureName = getPictureName();
                File imageFile = new File(PictureDirectory, pictureName);
                Uri pictureUri = Uri.fromFile(imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, PictureDirectory);
                //startActivityForResult(cameraIntent, CAMERA_REQUEST);
                startActivityForResult(cameraIntent, 0);
            }

        });

        return detailView;
    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "BestMoments" + timestamp + ".jpg";
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 0) { //CAMERA_REQUEST
               // Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                //File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                //String pictureDirectoryPath = data.getPath();
                //Uri imageUri = Uri.parse(pictureDirectoryPath);
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
