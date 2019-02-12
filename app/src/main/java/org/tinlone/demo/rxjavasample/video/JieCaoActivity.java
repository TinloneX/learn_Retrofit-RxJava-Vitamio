package org.tinlone.demo.rxjavasample.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.http.Api;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class JieCaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiecao);
        JCVideoPlayerStandard mPlayer = findViewById(R.id.jc_player);
        mPlayer.setUp(Api.VIDEO_URL,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "Experience");
        mPlayer.thumbImageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
