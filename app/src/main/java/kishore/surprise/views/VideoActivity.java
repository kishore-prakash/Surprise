package kishore.surprise.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import kishore.surprise.R;
import kishore.surprise.util.GMailSender;
import kishore.surprise.util.MyData;

public class VideoActivity extends Activity  {

    private final String TAG = VideoActivity.class.getName();

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = (VideoView) findViewById(R.id.videoView);

        String LINK = "android.resource://"+ getPackageName() +"/"+R.raw.birthday;
        Uri uri = Uri.parse(LINK);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    String body = MyData.instance.getStoredData();
                    Log.e(TAG, body);
                    SharedPreferences sharedpreferences = getSharedPreferences("kishore.surprise", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("kishore.surprise", body);
                    editor.commit();
                    GMailSender sender = new GMailSender();
                    sender.sendMail("Surprise app answers",
                            body,
                            "kishore.p606@gmail.com",
                            "kishore.p606@gmail.com");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        },0);

        videoView.start();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Screen12.class);
        startActivity(intent);
    }

}
