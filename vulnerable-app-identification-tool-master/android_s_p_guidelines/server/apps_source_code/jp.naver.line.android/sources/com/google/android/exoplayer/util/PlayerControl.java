package com.google.android.exoplayer.util;

import android.widget.MediaController.MediaPlayerControl;
import com.google.android.exoplayer.ExoPlayer;

public class PlayerControl implements MediaPlayerControl {
    private final ExoPlayer exoPlayer;

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public PlayerControl(ExoPlayer exoPlayer) {
        this.exoPlayer = exoPlayer;
    }

    public int getAudioSessionId() {
        throw new UnsupportedOperationException();
    }

    public int getBufferPercentage() {
        return this.exoPlayer.getBufferedPercentage();
    }

    public int getCurrentPosition() {
        if (this.exoPlayer.getDuration() == -1) {
            return 0;
        }
        return (int) this.exoPlayer.getCurrentPosition();
    }

    public int getDuration() {
        if (this.exoPlayer.getDuration() == -1) {
            return 0;
        }
        return (int) this.exoPlayer.getDuration();
    }

    public boolean isPlaying() {
        return this.exoPlayer.getPlayWhenReady();
    }

    public void start() {
        this.exoPlayer.setPlayWhenReady(true);
    }

    public void pause() {
        this.exoPlayer.setPlayWhenReady(false);
    }

    public void seekTo(int i) {
        long j;
        if (this.exoPlayer.getDuration() == -1) {
            j = 0;
        } else {
            j = (long) Math.min(Math.max(0, i), getDuration());
        }
        this.exoPlayer.seekTo(j);
    }
}
