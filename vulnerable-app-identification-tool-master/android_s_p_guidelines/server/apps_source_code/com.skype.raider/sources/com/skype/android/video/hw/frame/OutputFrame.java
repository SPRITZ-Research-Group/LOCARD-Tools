package com.skype.android.video.hw.frame;

import android.util.Log;
import com.skype.android.video.hw.Commons;
import java.io.Closeable;
import java.nio.ByteBuffer;

public class OutputFrame implements Closeable {
    private final FrameEvents frameEventsHandler;
    private boolean isCodecConfig;
    private boolean isEndOfStream;
    private boolean isOpen = false;
    private boolean isOutputFormatChanged;
    private boolean isTimedOut;
    private long nativeBufferLockObj;
    private final FrameBufferQueue queue = new FrameBufferQueue();
    private long timestamp;

    public class FrameBuffer implements Closeable {
        private ByteBuffer data;
        private int id = -1;
        private int offset = -1;
        private int size = -1;

        void assign(int id, ByteBuffer data, int offset, int size) {
            if (this.id >= 0) {
                throw new IllegalStateException("not closed");
            }
            this.id = id;
            this.data = data;
            this.offset = offset;
            this.size = size;
        }

        public ByteBuffer getData() {
            return this.data;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getSize() {
            return this.size;
        }

        public int getId() {
            return this.id;
        }

        public void releaseFrameBuffer() {
            OutputFrame.this.frameEventsHandler.onBufferReleased(this.id);
        }

        public void close() {
            if (this.id < 0) {
                throw new IllegalStateException("closed");
            }
            this.id = -1;
            this.data = null;
            this.offset = -1;
            this.size = -1;
        }

        public String toString() {
            return getClass().getSimpleName() + " [id=" + this.id + ", size=" + this.size + "]";
        }
    }

    private class FrameBufferQueue {
        private FrameBuffer[] buffers;
        private int headIndex;
        private int size;
        private int tailIndex;

        public FrameBufferQueue() {
            allocate(1);
        }

        public void push(int id, ByteBuffer data, int dataOffset, int dataSize) {
            if (this.size == this.buffers.length) {
                allocate(this.buffers.length * 2);
            }
            this.buffers[this.tailIndex].assign(id, data, dataOffset, dataSize);
            this.tailIndex = advance(this.tailIndex);
            this.size++;
        }

        public FrameBuffer pop() {
            if (isEmpty()) {
                return null;
            }
            FrameBuffer buf = this.buffers[this.headIndex];
            this.headIndex = advance(this.headIndex);
            this.size--;
            return buf;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        private int advance(int pos) {
            pos++;
            if (pos == this.buffers.length) {
                return 0;
            }
            return pos;
        }

        private void allocate(int capacity) {
            if (capacity <= this.size) {
                throw new IllegalArgumentException("capacity must be greater than actual size");
            }
            int j;
            if (Log.isLoggable(Commons.TAG, 3)) {
                new StringBuilder().append(getClass().getSimpleName()).append(": Allocating space for ").append(capacity).append(" frames, current size: ").append(this.size);
            }
            FrameBuffer[] newBuffers = new FrameBuffer[capacity];
            int i = this.headIndex;
            for (j = 0; j < this.size; j++) {
                newBuffers[j] = this.buffers[i];
                i = advance(i);
            }
            for (j = this.size; j < capacity; j++) {
                newBuffers[j] = new FrameBuffer();
            }
            this.buffers = newBuffers;
            this.headIndex = 0;
            this.tailIndex = this.size;
        }

        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(getClass().getSimpleName());
            str.append(" [");
            int i = this.headIndex;
            while (this.size > 0) {
                str.append(this.buffers[i].toString());
                i = advance(i);
            }
            str.append("]");
            return str.toString();
        }
    }

    public interface FrameEvents {
        void onBufferReleased(int i);

        void onFrameReleased(OutputFrame outputFrame);
    }

    OutputFrame(FrameEvents frameEventsHandler, long nativeBufferLockObj) {
        this.frameEventsHandler = frameEventsHandler;
        this.nativeBufferLockObj = nativeBufferLockObj;
    }

    public long getNativeLockingObj() {
        return this.nativeBufferLockObj;
    }

    public boolean isTimedOut() {
        return this.isTimedOut;
    }

    public void setTimedOut(boolean isTimedOut) {
        this.isTimedOut = isTimedOut;
    }

    public boolean isOutputFormatChanged() {
        return this.isOutputFormatChanged;
    }

    public void setOutputFormatChanged(boolean isOutputFormatChanged) {
        this.isOutputFormatChanged = isOutputFormatChanged;
    }

    public boolean isCodecConfig() {
        return this.isCodecConfig;
    }

    public void setCodecConfig(boolean isCodecConfig) {
        this.isCodecConfig = isCodecConfig;
    }

    public boolean isEndOfStream() {
        return this.isEndOfStream;
    }

    public void setEndOfStream(boolean isEndOfStream) {
        this.isEndOfStream = isEndOfStream;
    }

    public boolean hasBuffers() {
        return !this.queue.isEmpty();
    }

    public void pushBuffer(int id, ByteBuffer data, int offset, int size) {
        this.queue.push(id, data, offset, size);
    }

    public FrameBuffer popBuffer() {
        return this.queue.pop();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void open() {
        if (this.isOpen) {
            throw new IllegalStateException("not closed");
        }
        this.isOpen = true;
    }

    public void close() {
        if (this.isOpen) {
            while (!this.queue.isEmpty()) {
                FrameBuffer buf = this.queue.pop();
                if (Log.isLoggable(Commons.TAG, 5)) {
                    new StringBuilder().append(getClass().getSimpleName()).append(": A buffer was not closed: ").append(buf);
                }
                buf.close();
            }
            this.frameEventsHandler.onFrameReleased(this);
            this.isOpen = false;
            this.timestamp = 0;
            this.isTimedOut = false;
            this.isOutputFormatChanged = false;
            this.isCodecConfig = false;
            this.isEndOfStream = false;
            return;
        }
        throw new IllegalStateException("already closed");
    }

    public String toString() {
        return getClass().getSimpleName() + " [isOpen=" + this.isOpen + ", timestamp=" + this.timestamp + ", queue=" + this.queue + "]";
    }
}
