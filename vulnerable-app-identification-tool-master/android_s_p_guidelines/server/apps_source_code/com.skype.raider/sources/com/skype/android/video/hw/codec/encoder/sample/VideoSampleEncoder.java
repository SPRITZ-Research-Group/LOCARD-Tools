package com.skype.android.video.hw.codec.encoder.sample;

import com.skype.android.video.hw.codec.encoder.AbstractVideoEncoder;
import com.skype.android.video.hw.frame.InputFrame;
import com.skype.android.video.hw.frame.OutputFrame;
import java.nio.ByteBuffer;

public class VideoSampleEncoder extends AbstractVideoEncoder {
    private ByteBuffer[] inputBuffers;
    private final InputFrame inputFrame = new InputFrame();

    public VideoSampleEncoder(String codecName) {
        super(codecName);
    }

    public void start() {
        super.start();
        this.inputBuffers = this.mediaCodec.getInputBuffers();
    }

    public InputFrame getInputFrame(long timeoutUs) {
        int inputBufferIndex = this.mediaCodec.dequeueInputBuffer(timeoutUs);
        if (inputBufferIndex < 0) {
            throw new IllegalStateException("no input buffers available");
        }
        ByteBuffer inputBuffer = this.inputBuffers[inputBufferIndex];
        inputBuffer.clear();
        this.inputFrame.initialize(inputBufferIndex, inputBuffer);
        return this.inputFrame;
    }

    public OutputFrame encode(InputFrame inputFrame, long timeoutUs, Boolean drainOnly) {
        if (!drainOnly.booleanValue()) {
            this.mediaCodec.queueInputBuffer(inputFrame.getId(), 0, inputFrame.getSize(), inputFrame.getTimestamp(), 0);
        }
        return super.drainCodec(timeoutUs);
    }
}
