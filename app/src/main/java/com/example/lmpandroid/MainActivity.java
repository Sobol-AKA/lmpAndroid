package com.example.lmpandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView time,date,header;
    VideoView videov;
    ImageView imagev;
    WebView htmlv;
    Date now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.clockTextView);
        date = findViewById(R.id.dateTextView);
        header = findViewById(R.id.headerTextView);
        videov= findViewById(R.id.videoView);

        clockShow();
    }

    protected void clockShow(){

        final Handler timeHandler = new Handler(getMainLooper());
        final SimpleDateFormat dateDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        final SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss",Locale.UK);
        final OrgServer server = new OrgServer();
        timeHandler.post(new Runnable(){
            @Override
            public void run() {

                Date now = new Date();
                Period periodMorning = new Period("8:00","12:00");
                Period periodLunch = new Period("12:00", "17:00");
                Period periodEvening = new Period("14:00","17:30");
                String vidName = "video1.mp4";
                String imgName = "image1.jpg";

                date.setText(dateDateFormat.format(now));
                time.setText(timeDateFormat.format(now));
                header.setText("");

                if (periodLunch.inComing(now)) {
                    //vidName="video1.mp4";
                    //imgName="image1.jpg";
                    header.setText("Обед!!!");
                }

                if (periodEvening.inComing(now)) {
                    vidName="video2.mp4";
                    imgName="image2.jpg";
                    header.setText("До свидания!!!");
                }

                if (periodMorning.inComing(now)) {
                    vidName="video3.mp4";
                    imgName="image3.jpg";
                    header.setText("Добро пожаловать!!!");
                }
                if (server.vidExist(vidName)) videoplay(vidName);
                if (server.imgExist(imgName)) imgShow(imgName);
                timeHandler.postDelayed(this, 1000);
            }

        });
    }

    void videoplay(String filename){
        if (!videov.isPlaying()){
            Uri uri;
            String uripath="http://192.168.40.11/public/video/"+filename;
            uri = Uri.parse(uripath);
            videov.setVisibility(View.VISIBLE);
            videov.setVideoURI(uri);
            videov.start();
        }

    }
    void imgShow(String filename){

    }
}
