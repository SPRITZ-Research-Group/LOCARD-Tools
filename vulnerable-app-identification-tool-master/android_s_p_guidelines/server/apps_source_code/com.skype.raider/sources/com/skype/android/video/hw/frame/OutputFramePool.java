package com.skype.android.video.hw.frame;

import android.media.MediaCodec;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.frame.OutputFrame.FrameEvents;
import com.skype.android.video.hw.utils.DebugUtils;
import com.skype.android.video.hw.utils.Log;
import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class OutputFramePool implements Closeable {
    private final FrameEvents frameEventsHandler = new FrameEvents() {
        public void onFrameReleased(OutputFrame frame) {
            if (!OutputFramePool.this.frames.add(frame)) {
                throw new IllegalStateException("frame pool is full");
            }
        }

        public void onBufferReleased(int id) {
            MediaCodec mediaCodec = (MediaCodec) OutputFramePool.this.weakMediaCodec.get();
            if (mediaCodec != null) {
                try {
                    mediaCodec.releaseOutputBuffer(id, false);
                } catch (Exception e) {
                    Log.e(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + " ReleaseBuffer " + id + "failed with info " + e);
                }
            }
        }
    };
    private final Queue<OutputFrame> frames;
    private long nativeObj = createNativeObj(this);
    private boolean reconfig = false;
    private final WeakReference<MediaCodec> weakMediaCodec;

    private native long createNativeObj(OutputFramePool outputFramePool);

    private native void deleteNativeObj(long j);

    public void setReconfigFlag(boolean rec) {
        this.reconfig = rec;
    }

    public OutputFramePool(int poolSize, MediaCodec mediaCodec) {
        this.weakMediaCodec = new WeakReference(mediaCodec);
        this.frames = new ArrayBlockingQueue(poolSize);
        for (int i = 0; i < poolSize; i++) {
            this.frames.add(new OutputFrame(this.frameEventsHandler, this.nativeObj));
        }
    }

    public OutputFrame poll() {
        OutputFrame frame = (OutputFrame) this.frames.poll();
        if (frame != null) {
            frame.open();
        }
        return frame;
    }

    public int releaseBuffer(int id) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + " ReleaseBuffer " + id);
        }
        if (this.reconfig) {
            this.reconfig = false;
            return 0;
        }
        MediaCodec mediaCodec = (MediaCodec) this.weakMediaCodec.get();
        if (mediaCodec == null) {
            return 0;
        }
        try {
            mediaCodec.releaseOutputBuffer(id, false);
            return 0;
        } catch (Exception e) {
            Log.e(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + " ReleaseBuffer " + id + "failed with info " + e);
            return -1;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " [frames=" + this.frames + "]";
    }

    public void close() throws IOException {
        deleteNativeObj(this.nativeObj);
    }
}
