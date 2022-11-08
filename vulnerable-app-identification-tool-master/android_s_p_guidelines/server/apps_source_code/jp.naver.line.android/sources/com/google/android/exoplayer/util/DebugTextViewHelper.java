package com.google.android.exoplayer.util;

import android.widget.TextView;
import com.google.android.exoplayer.CodecCounters;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.upstream.BandwidthMeter;

public final class DebugTextViewHelper implements Runnable {
    private static final int REFRESH_INTERVAL_MS = 1000;
    private final Provider debuggable;
    private final TextView textView;

    public interface Provider {
        BandwidthMeter getBandwidthMeter();

        CodecCounters getCodecCounters();

        long getCurrentPosition();

        Format getFormat();
    }

    public DebugTextViewHelper(Provider provider, TextView textView) {
        this.debuggable = provider;
        this.textView = textView;
    }

    public final void start() {
        stop();
        run();
    }

    public final void stop() {
        this.textView.removeCallbacks(this);
    }

    public final void run() {
        this.textView.setText(getRenderString());
        this.textView.postDelayed(this, 1000);
    }

    private String getRenderString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTimeString());
        stringBuilder.append(" ");
        stringBuilder.append(getQualityString());
        stringBuilder.append(" ");
        stringBuilder.append(getBandwidthString());
        stringBuilder.append(" ");
        stringBuilder.append(getVideoCodecCountersString());
        return stringBuilder.toString();
    }

    private String getTimeString() {
        StringBuilder stringBuilder = new StringBuilder("ms(");
        stringBuilder.append(this.debuggable.getCurrentPosition());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private String getQualityString() {
        Format format = this.debuggable.getFormat();
        if (format == null) {
            return "id:? br:? h:?";
        }
        StringBuilder stringBuilder = new StringBuilder("id:");
        stringBuilder.append(format.id);
        stringBuilder.append(" br:");
        stringBuilder.append(format.bitrate);
        stringBuilder.append(" h:");
        stringBuilder.append(format.height);
        return stringBuilder.toString();
    }

    private String getBandwidthString() {
        BandwidthMeter bandwidthMeter = this.debuggable.getBandwidthMeter();
        if (bandwidthMeter == null || bandwidthMeter.getBitrateEstimate() == -1) {
            return "bw:?";
        }
        StringBuilder stringBuilder = new StringBuilder("bw:");
        stringBuilder.append(bandwidthMeter.getBitrateEstimate() / 1000);
        return stringBuilder.toString();
    }

    private String getVideoCodecCountersString() {
        CodecCounters codecCounters = this.debuggable.getCodecCounters();
        if (codecCounters == null) {
            return "";
        }
        return codecCounters.getDebugString();
    }
}
