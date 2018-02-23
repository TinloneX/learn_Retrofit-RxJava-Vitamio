package org.tinlone.demo.rxjavasample.video;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.http.Api;
import org.tinlone.demo.rxjavasample.util.TLog;
import org.tinlone.demo.rxjavasample.util.Tips;
import org.tinlone.demo.rxjavasample.util.adapt.PermissionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class NetVideoActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView mVideoView;
    @BindView(R.id.mediaController)
    MediaController mMediaController;

    private long currentPosition = 0;
    private boolean needResume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_net_video);
        ButterKnife.bind(this);
        if (!Vitamio.isInitialized(this)) return;
        initVideo();
        checkPermission();
    }

    private void initVideo() {
        mVideoView.requestFocus();
        mVideoView.setBufferSize(1024 * 1024);
        mVideoView.setVideoChroma(MediaPlayer.VIDEOCHROMA_RGB565);
        mVideoView.setVideoPath(Api.VIDEO_URL);
        preparePlayVideo();
    }

    private void preparePlayVideo() {
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (currentPosition > 0) {
                    mVideoView.seekTo(currentPosition);
                } else {
                    mp.setPlaybackSpeed(1.0f);
                }
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        caching();
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        playing();
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
//                        Tips.snackyTest(NetVideoActivity.this, extra + "%");
                        break;
                }
                return false;
            }
        });

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Tips.snackyError(NetVideoActivity.this, "Error Code:" + what);
                return false;
            }
        });
    }

    private void caching() {
        TLog.i("mVideoView -- caching --");
        if (mVideoView.isPlaying()) {
            pause();
            needResume = true;
        }
    }

    private void playing() {
        TLog.i("mVideoView -- playing --");
        if (needResume) play();
    }

    private void play() {
        mVideoView.start();
    }

    private void pause() {
        mVideoView.pause();
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    private void checkPermission() {
        PermissionUtil.askPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionUtil.PermissionCallback() {
            @Override
            public void allowed() {
                play();
            }

            @Override
            public void denied() {

            }
        });

    }
}
