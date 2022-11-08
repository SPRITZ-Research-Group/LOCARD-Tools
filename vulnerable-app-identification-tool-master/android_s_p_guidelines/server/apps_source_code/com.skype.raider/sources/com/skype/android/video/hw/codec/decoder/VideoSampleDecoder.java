package com.skype.android.video.hw.codec.decoder;

import com.skype.android.video.hw.frame.InputFrame;
import com.skype.android.video.hw.frame.OutputFrame;
import java.nio.ByteBuffer;

public class VideoSampleDecoder extends AbstractVideoDecoder {
    private ByteBuffer[] inputBuffers;
    private final InputFrame inputFrame = new InputFrame();

    public VideoSampleDecoder(String codecName) {
        super(codecName);
    }

    public void start() {
        super.start();
        this.inputBuffers = this.mediaCodec.getInputBuffers();
    }

    public InputFrame getInputFrame(long timeoutUS) {
        int inputBufferIndex = this.mediaCodec.dequeueInputBuffer(timeoutUS);
        if (inputBufferIndex < 0) {
            throw new IllegalStateException("no input buffers available");
        }
        ByteBuffer inputBuffer = this.inputBuffers[inputBufferIndex];
        inputBuffer.clear();
        this.inputFrame.initialize(inputBufferIndex, inputBuffer);
        return this.inputFrame;
    }

    public OutputFrame decode(InputFrame inputFrame, long timeoutUs) {
        this.mediaCodec.queueInputBuffer(inputFrame.getId(), 0, inputFrame.getSize(), inputFrame.getTimestamp(), 0);
        return super.drainCodec(timeoutUs);
    }

    public void signalEOS(long timeStamp, long timeoutUS) {
        int inputBufferIndex = this.mediaCodec.dequeueInputBuffer(timeoutUS);
        if (inputBufferIndex < 0) {
            throw new IllegalStateException("no input buffers available");
        }
        this.inputBuffers[inputBufferIndex].clear();
        this.mediaCodec.queueInputBuffer(inputBufferIndex, 0, 0, timeStamp, 4);
    }

    public OutputFrame flushOneFrame(long timeoutUs) {
        return super.drainCodec(timeoutUs);
    }
}
