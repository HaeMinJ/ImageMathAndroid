package com.haemin.imagemathstudent.VideoDetailView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.haemin.imagemathstudent.R;

public class VideoPlayActivity extends AppCompatActivity {

    String videoUrl = "";
    public static void start(Context context, String videoUrl) {
        Intent starter = new Intent(context, VideoPlayActivity.class);
        starter.putExtra("videoUrl", videoUrl);
        context.startActivity(starter);
    }
    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;

    private Boolean playWhenReady = true;
    private int currentWindow = 0;
    private Long playbackPosition = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        hide();

        Intent fromOutside = getIntent();
        videoUrl = fromOutside.getStringExtra("videoUrl");
        exoPlayerView = findViewById(R.id.exoPlayerView);

        initializePlayer();
        player.addListener(new Player.EventListener() {

            /**
             * @param playWhenReady - Whether playback will proceed when ready.
             * @param playbackState - One of the STATE constants.
             */
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                switch (playbackState) {

                    case Player.STATE_IDLE: // 1
                        //재생 실패
                        break;
                    case Player.STATE_BUFFERING: // 2
                        // 재생 준비
                        break;
                    case Player.STATE_READY: // 3
                        // 재생 준비 완료
                        break;
                    case Player.STATE_ENDED: // 4
                        // 재생 마침
                        break;
                    default:
                        break;
                }
            }
        });


    }
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void initializePlayer() {
        if (player == null) {

            player = ExoPlayerFactory.newSimpleInstance(this.getApplicationContext());

            //플레이어 연결
            exoPlayerView.setPlayer(player);

        }

        String sample = videoUrl;

        MediaSource mediaSource = buildMediaSource(Uri.parse(sample));

        //prepare
        player.prepare(mediaSource, true, false);

        //start,stop
        player.setPlayWhenReady(playWhenReady);
    }
    private MediaSource buildMediaSource(Uri uri) {

        String userAgent = Util.getUserAgent(this, "blackJin");

        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                .createMediaSource(uri);
    }
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();

            exoPlayerView.setPlayer(null);
            player.release();
            player = null;

        }
    }
}
