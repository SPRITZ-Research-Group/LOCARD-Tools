package com.google.android.exoplayer.text;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.util.Assertions;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public final class SubtitleParserHelper implements Callback {
    private IOException error;
    private final Handler handler;
    private final SubtitleParser parser;
    private boolean parsing;
    private Subtitle result;
    private SampleHolder sampleHolder;

    public SubtitleParserHelper(Looper looper, SubtitleParser subtitleParser) {
        this.handler = new Handler(looper, this);
        this.parser = subtitleParser;
        flush();
    }

    public final synchronized void flush() {
        this.sampleHolder = new SampleHolder(1);
        this.parsing = false;
        this.result = null;
        this.error = null;
    }

    public final synchronized boolean isParsing() {
        return this.parsing;
    }

    public final synchronized SampleHolder getSampleHolder() {
        return this.sampleHolder;
    }

    public final synchronized void startParseOperation() {
        Assertions.checkState(this.parsing ^ true);
        this.parsing = true;
        this.result = null;
        this.error = null;
        this.handler.obtainMessage(0, this.sampleHolder).sendToTarget();
    }

    public final synchronized Subtitle getAndClearResult() throws IOException {
        Subtitle subtitle;
        try {
            if (this.error == null) {
                subtitle = this.result;
                this.error = null;
                this.result = null;
            } else {
                throw this.error;
            }
        } catch (Throwable th) {
            this.error = null;
            this.result = null;
        }
        return subtitle;
    }

    public final boolean handleMessage(Message message) {
        IOException e;
        SampleHolder sampleHolder = (SampleHolder) message.obj;
        Subtitle subtitle = null;
        try {
            Subtitle parse = this.parser.parse(new ByteArrayInputStream(sampleHolder.data.array(), 0, sampleHolder.size), null, this.sampleHolder.timeUs);
            e = null;
            subtitle = parse;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (this.sampleHolder == sampleHolder) {
                this.result = subtitle;
                this.error = e;
                this.parsing = false;
            }
        }
        return true;
    }
}
