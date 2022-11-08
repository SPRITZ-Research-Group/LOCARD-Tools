package com.skype.android.video.hw.frame;

import java.nio.ByteBuffer;

public class InputFrame {
    private ByteBuffer data;
    private int id;
    private int size;
    private long timestamp;

    public void initialize(int id, ByteBuffer data) {
        this.id = id;
        this.data = data;
        this.timestamp = 0;
        this.size = -1;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return this.id;
    }

    public ByteBuffer getData() {
        return this.data;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
