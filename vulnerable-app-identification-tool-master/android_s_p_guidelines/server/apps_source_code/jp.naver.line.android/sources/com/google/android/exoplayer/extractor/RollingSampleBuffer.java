package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.upstream.Allocation;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;

final class RollingSampleBuffer {
    private static final int INITIAL_SCRATCH_SIZE = 32;
    private final int allocationLength;
    private final Allocator allocator;
    private final LinkedBlockingDeque<Allocation> dataQueue = new LinkedBlockingDeque();
    private final SampleExtrasHolder extrasHolder = new SampleExtrasHolder();
    private final InfoQueue infoQueue = new InfoQueue();
    private Allocation lastAllocation;
    private int lastAllocationOffset = this.allocationLength;
    private final ParsableByteArray scratch = new ParsableByteArray(32);
    private long totalBytesDropped;
    private long totalBytesWritten;

    final class InfoQueue {
        private static final int SAMPLE_CAPACITY_INCREMENT = 1000;
        private int absoluteReadIndex;
        private int capacity = 1000;
        private byte[][] encryptionKeys = new byte[this.capacity][];
        private int[] flags = new int[this.capacity];
        private long[] offsets = new long[this.capacity];
        private int queueSize;
        private int relativeReadIndex;
        private int relativeWriteIndex;
        private int[] sizes = new int[this.capacity];
        private long[] timesUs = new long[this.capacity];

        public final void clear() {
            this.absoluteReadIndex = 0;
            this.relativeReadIndex = 0;
            this.relativeWriteIndex = 0;
            this.queueSize = 0;
        }

        public final int getWriteIndex() {
            return this.absoluteReadIndex + this.queueSize;
        }

        public final long discardUpstreamSamples(int i) {
            int writeIndex = getWriteIndex() - i;
            boolean z = writeIndex >= 0 && writeIndex <= this.queueSize;
            Assertions.checkArgument(z);
            if (writeIndex != 0) {
                this.queueSize -= writeIndex;
                this.relativeWriteIndex = ((this.relativeWriteIndex + this.capacity) - writeIndex) % this.capacity;
                return this.offsets[this.relativeWriteIndex];
            } else if (this.absoluteReadIndex == 0) {
                return 0;
            } else {
                writeIndex = (this.relativeWriteIndex == 0 ? this.capacity : this.relativeWriteIndex) - 1;
                return this.offsets[writeIndex] + ((long) this.sizes[writeIndex]);
            }
        }

        public final int getReadIndex() {
            return this.absoluteReadIndex;
        }

        public final synchronized boolean peekSample(SampleHolder sampleHolder, SampleExtrasHolder sampleExtrasHolder) {
            if (this.queueSize == 0) {
                return false;
            }
            sampleHolder.timeUs = this.timesUs[this.relativeReadIndex];
            sampleHolder.size = this.sizes[this.relativeReadIndex];
            sampleHolder.flags = this.flags[this.relativeReadIndex];
            sampleExtrasHolder.offset = this.offsets[this.relativeReadIndex];
            sampleExtrasHolder.encryptionKeyId = this.encryptionKeys[this.relativeReadIndex];
            return true;
        }

        public final synchronized long moveToNextSample() {
            this.queueSize--;
            int i = this.relativeReadIndex;
            this.relativeReadIndex = i + 1;
            this.absoluteReadIndex++;
            if (this.relativeReadIndex == this.capacity) {
                this.relativeReadIndex = 0;
            }
            if (this.queueSize > 0) {
                return this.offsets[this.relativeReadIndex];
            }
            return ((long) this.sizes[i]) + this.offsets[i];
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final synchronized long skipToKeyframeBefore(long j) {
            if (this.queueSize != 0 && j >= this.timesUs[this.relativeReadIndex]) {
                if (j > this.timesUs[(this.relativeWriteIndex == 0 ? this.capacity : this.relativeWriteIndex) - 1]) {
                    return -1;
                }
                int i = this.relativeReadIndex;
                int i2 = -1;
                int i3 = 0;
                while (i != this.relativeWriteIndex && this.timesUs[i] <= j) {
                    if ((this.flags[i] & 1) != 0) {
                        i2 = i3;
                    }
                    i = (i + 1) % this.capacity;
                    i3++;
                }
                if (i2 == -1) {
                    return -1;
                }
                this.queueSize -= i2;
                this.relativeReadIndex = (this.relativeReadIndex + i2) % this.capacity;
                this.absoluteReadIndex += i2;
                return this.offsets[this.relativeReadIndex];
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final synchronized void commitSample(long j, int i, long j2, int i2, byte[] bArr) {
            this.timesUs[this.relativeWriteIndex] = j;
            this.offsets[this.relativeWriteIndex] = j2;
            this.sizes[this.relativeWriteIndex] = i2;
            this.flags[this.relativeWriteIndex] = i;
            this.encryptionKeys[this.relativeWriteIndex] = bArr;
            this.queueSize++;
            if (this.queueSize == this.capacity) {
                int i3 = this.capacity + 1000;
                Object obj = new long[i3];
                Object obj2 = new long[i3];
                Object obj3 = new int[i3];
                Object obj4 = new int[i3];
                Object obj5 = new byte[i3][];
                int i4 = this.capacity - this.relativeReadIndex;
                System.arraycopy(this.offsets, this.relativeReadIndex, obj, 0, i4);
                System.arraycopy(this.timesUs, this.relativeReadIndex, obj2, 0, i4);
                System.arraycopy(this.flags, this.relativeReadIndex, obj3, 0, i4);
                System.arraycopy(this.sizes, this.relativeReadIndex, obj4, 0, i4);
                System.arraycopy(this.encryptionKeys, this.relativeReadIndex, obj5, 0, i4);
                int i5 = this.relativeReadIndex;
                System.arraycopy(this.offsets, 0, obj, i4, i5);
                System.arraycopy(this.timesUs, 0, obj2, i4, i5);
                System.arraycopy(this.flags, 0, obj3, i4, i5);
                System.arraycopy(this.sizes, 0, obj4, i4, i5);
                System.arraycopy(this.encryptionKeys, 0, obj5, i4, i5);
                this.offsets = obj;
                this.timesUs = obj2;
                this.flags = obj3;
                this.sizes = obj4;
                this.encryptionKeys = obj5;
                this.relativeReadIndex = 0;
                this.relativeWriteIndex = this.capacity;
                this.queueSize = this.capacity;
                this.capacity = i3;
                return;
            }
            this.relativeWriteIndex++;
            if (this.relativeWriteIndex == this.capacity) {
                this.relativeWriteIndex = 0;
            }
        }
    }

    final class SampleExtrasHolder {
        public byte[] encryptionKeyId;
        public long offset;

        private SampleExtrasHolder() {
        }
    }

    public RollingSampleBuffer(Allocator allocator) {
        this.allocator = allocator;
        this.allocationLength = allocator.getIndividualAllocationLength();
    }

    public final void clear() {
        this.infoQueue.clear();
        while (!this.dataQueue.isEmpty()) {
            this.allocator.release((Allocation) this.dataQueue.remove());
        }
        this.totalBytesDropped = 0;
        this.totalBytesWritten = 0;
        this.lastAllocation = null;
        this.lastAllocationOffset = this.allocationLength;
    }

    public final int getWriteIndex() {
        return this.infoQueue.getWriteIndex();
    }

    public final void discardUpstreamSamples(int i) {
        this.totalBytesWritten = this.infoQueue.discardUpstreamSamples(i);
        dropUpstreamFrom(this.totalBytesWritten);
    }

    private void dropUpstreamFrom(long j) {
        int i = (int) (j - this.totalBytesDropped);
        int i2 = i / this.allocationLength;
        i %= this.allocationLength;
        int size = (this.dataQueue.size() - i2) - 1;
        if (i == 0) {
            size++;
        }
        for (i2 = 0; i2 < size; i2++) {
            this.allocator.release((Allocation) this.dataQueue.removeLast());
        }
        this.lastAllocation = (Allocation) this.dataQueue.peekLast();
        if (i == 0) {
            i = this.allocationLength;
        }
        this.lastAllocationOffset = i;
    }

    public final int getReadIndex() {
        return this.infoQueue.getReadIndex();
    }

    public final boolean peekSample(SampleHolder sampleHolder) {
        return this.infoQueue.peekSample(sampleHolder, this.extrasHolder);
    }

    public final void skipSample() {
        dropDownstreamTo(this.infoQueue.moveToNextSample());
    }

    public final boolean skipToKeyframeBefore(long j) {
        j = this.infoQueue.skipToKeyframeBefore(j);
        if (j == -1) {
            return false;
        }
        dropDownstreamTo(j);
        return true;
    }

    public final boolean readSample(SampleHolder sampleHolder) {
        if (!this.infoQueue.peekSample(sampleHolder, this.extrasHolder)) {
            return false;
        }
        if (sampleHolder.isEncrypted()) {
            readEncryptionData(sampleHolder, this.extrasHolder);
        }
        if (sampleHolder.data == null || sampleHolder.data.capacity() < sampleHolder.size) {
            sampleHolder.replaceBuffer(sampleHolder.size);
        }
        if (sampleHolder.data != null) {
            readData(this.extrasHolder.offset, sampleHolder.data, sampleHolder.size);
        }
        dropDownstreamTo(this.infoQueue.moveToNextSample());
        return true;
    }

    private void readEncryptionData(SampleHolder sampleHolder, SampleExtrasHolder sampleExtrasHolder) {
        int readUnsignedShort;
        long j = sampleExtrasHolder.offset;
        readData(j, this.scratch.data, 1);
        j++;
        int i = 0;
        byte b = this.scratch.data[0];
        Object obj = (b & 128) != 0 ? 1 : null;
        int i2 = b & 127;
        if (sampleHolder.cryptoInfo.iv == null) {
            sampleHolder.cryptoInfo.iv = new byte[16];
        }
        readData(j, sampleHolder.cryptoInfo.iv, i2);
        j += (long) i2;
        if (obj != null) {
            readData(j, this.scratch.data, 2);
            j += 2;
            this.scratch.setPosition(0);
            readUnsignedShort = this.scratch.readUnsignedShort();
        } else {
            readUnsignedShort = 1;
        }
        int[] iArr = sampleHolder.cryptoInfo.numBytesOfClearData;
        if (iArr == null || iArr.length < readUnsignedShort) {
            iArr = new int[readUnsignedShort];
        }
        int[] iArr2 = iArr;
        iArr = sampleHolder.cryptoInfo.numBytesOfEncryptedData;
        if (iArr == null || iArr.length < readUnsignedShort) {
            iArr = new int[readUnsignedShort];
        }
        int[] iArr3 = iArr;
        if (obj != null) {
            i2 = readUnsignedShort * 6;
            ensureCapacity(this.scratch, i2);
            readData(j, this.scratch.data, i2);
            j += (long) i2;
            this.scratch.setPosition(0);
            while (i < readUnsignedShort) {
                iArr2[i] = this.scratch.readUnsignedShort();
                iArr3[i] = this.scratch.readUnsignedIntToInt();
                i++;
            }
        } else {
            iArr2[0] = 0;
            iArr3[0] = sampleHolder.size - ((int) (j - sampleExtrasHolder.offset));
        }
        sampleHolder.cryptoInfo.set(readUnsignedShort, iArr2, iArr3, sampleExtrasHolder.encryptionKeyId, sampleHolder.cryptoInfo.iv, 1);
        int i3 = (int) (j - sampleExtrasHolder.offset);
        sampleExtrasHolder.offset += (long) i3;
        sampleHolder.size -= i3;
    }

    private void readData(long j, ByteBuffer byteBuffer, int i) {
        while (i > 0) {
            dropDownstreamTo(j);
            int i2 = (int) (j - this.totalBytesDropped);
            int min = Math.min(i, this.allocationLength - i2);
            Allocation allocation = (Allocation) this.dataQueue.peek();
            byteBuffer.put(allocation.data, allocation.translateOffset(i2), min);
            j += (long) min;
            i -= min;
        }
    }

    private void readData(long j, byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            dropDownstreamTo(j);
            int i3 = (int) (j - this.totalBytesDropped);
            int min = Math.min(i - i2, this.allocationLength - i3);
            Allocation allocation = (Allocation) this.dataQueue.peek();
            System.arraycopy(allocation.data, allocation.translateOffset(i3), bArr, i2, min);
            j += (long) min;
            i2 += min;
        }
    }

    private void dropDownstreamTo(long j) {
        int i = ((int) (j - this.totalBytesDropped)) / this.allocationLength;
        for (int i2 = 0; i2 < i; i2++) {
            this.allocator.release((Allocation) this.dataQueue.remove());
            this.totalBytesDropped += (long) this.allocationLength;
        }
    }

    private static void ensureCapacity(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.limit() < i) {
            parsableByteArray.reset(new byte[i], i);
        }
    }

    public final long getWritePosition() {
        return this.totalBytesWritten;
    }

    public final int appendData(DataSource dataSource, int i, boolean z) throws IOException {
        int read = dataSource.read(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), prepareForAppend(i));
        if (read != -1) {
            this.lastAllocationOffset += read;
            this.totalBytesWritten += (long) read;
            return read;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    public final int appendData(ExtractorInput extractorInput, int i, boolean z) throws IOException, InterruptedException {
        int read = extractorInput.read(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), prepareForAppend(i));
        if (read != -1) {
            this.lastAllocationOffset += read;
            this.totalBytesWritten += (long) read;
            return read;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    public final void appendData(ParsableByteArray parsableByteArray, int i) {
        while (i > 0) {
            int prepareForAppend = prepareForAppend(i);
            parsableByteArray.readBytes(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), prepareForAppend);
            this.lastAllocationOffset += prepareForAppend;
            this.totalBytesWritten += (long) prepareForAppend;
            i -= prepareForAppend;
        }
    }

    public final void commitSample(long j, int i, long j2, int i2, byte[] bArr) {
        this.infoQueue.commitSample(j, i, j2, i2, bArr);
    }

    private int prepareForAppend(int i) {
        if (this.lastAllocationOffset == this.allocationLength) {
            this.lastAllocationOffset = 0;
            this.lastAllocation = this.allocator.allocate();
            this.dataQueue.add(this.lastAllocation);
        }
        return Math.min(i, this.allocationLength - this.lastAllocationOffset);
    }
}
