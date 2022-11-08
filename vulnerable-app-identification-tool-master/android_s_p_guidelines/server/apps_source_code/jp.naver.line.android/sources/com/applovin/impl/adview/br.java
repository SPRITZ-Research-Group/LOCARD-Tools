package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

class br implements OnPreparedListener {
    final /* synthetic */ az a;

    br(az azVar) {
        this.a = azVar;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.a.E = new WeakReference(mediaPlayer);
        float b = (float) (this.a.i() ^ 1);
        mediaPlayer.setVolume(b, b);
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.a.computedLengthSeconds = (int) TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getDuration());
        this.a.videoView.setVideoSize(videoWidth, videoHeight);
        mediaPlayer.setDisplay(this.a.videoView.getHolder());
        mediaPlayer.setOnErrorListener(new bs(this));
        if (this.a.s == 0) {
            this.a.q();
            this.a.k();
            this.a.v();
            this.a.u();
            this.a.playVideo();
            this.a.F();
        }
    }
}
