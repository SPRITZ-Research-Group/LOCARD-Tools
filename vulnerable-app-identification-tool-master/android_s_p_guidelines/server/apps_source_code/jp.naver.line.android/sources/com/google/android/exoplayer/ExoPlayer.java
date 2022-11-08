package com.google.android.exoplayer;

import android.os.Looper;

public interface ExoPlayer {
    public static final int STATE_BUFFERING = 3;
    public static final int STATE_ENDED = 5;
    public static final int STATE_IDLE = 1;
    public static final int STATE_PREPARING = 2;
    public static final int STATE_READY = 4;
    public static final long UNKNOWN_TIME = -1;

    public interface ExoPlayerComponent {
        void handleMessage(int i, Object obj) throws ExoPlaybackException;
    }

    public final class Factory {
        public static final int DEFAULT_MIN_BUFFER_MS = 2500;
        public static final int DEFAULT_MIN_REBUFFER_MS = 5000;

        private Factory() {
        }

        public static ExoPlayer newInstance(int i, int i2, int i3) {
            return new ExoPlayerImpl(i, i2, i3);
        }

        public static ExoPlayer newInstance(int i) {
            return new ExoPlayerImpl(i, DEFAULT_MIN_BUFFER_MS, 5000);
        }
    }

    public interface Listener {
        void onPlayWhenReadyCommitted();

        void onPlayerError(ExoPlaybackException exoPlaybackException);

        void onPlayerStateChanged(boolean z, int i);
    }

    void addListener(Listener listener);

    void blockingSendMessage(ExoPlayerComponent exoPlayerComponent, int i, Object obj);

    int getBufferedPercentage();

    long getBufferedPosition();

    long getCurrentPosition();

    long getDuration();

    boolean getPlayWhenReady();

    Looper getPlaybackLooper();

    int getPlaybackState();

    boolean getRendererEnabled(int i);

    boolean getRendererHasMedia(int i);

    boolean isPlayWhenReadyCommitted();

    void prepare(TrackRenderer... trackRendererArr);

    void release();

    void removeListener(Listener listener);

    void seekTo(long j);

    void sendMessage(ExoPlayerComponent exoPlayerComponent, int i, Object obj);

    void setPlayWhenReady(boolean z);

    void setRendererEnabled(int i, boolean z);

    void stop();
}
