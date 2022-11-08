package kotlin.reflect.jvm.internal.impl.protobuf;

import com.google.android.gms.common.api.Api.BaseClientBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder;

public final class CodedInputStream {
    private final byte[] buffer;
    private final boolean bufferIsImmutable;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private boolean enableAliasing;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private RefillCallback refillCallback;
    private int sizeLimit;
    private int totalBytesRetired;

    interface RefillCallback {
        void onRefill();
    }

    public static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return new CodedInputStream(inputStream);
    }

    static CodedInputStream newInstance(LiteralByteString literalByteString) {
        CodedInputStream codedInputStream = new CodedInputStream(literalByteString);
        try {
            codedInputStream.pushLimit(literalByteString.size());
            return codedInputStream;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final int readTag() throws IOException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        this.lastTag = readRawVarint32();
        if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
            return this.lastTag;
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public final void checkLastTagWas(int i) throws InvalidProtocolBufferException {
        if (this.lastTag != i) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public final boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
        long readInt64;
        switch (WireFormat.getTagWireType(i)) {
            case 0:
                readInt64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            case 1:
                readInt64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(readInt64);
                return true;
            case 2:
                ByteString readBytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            case 3:
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                i = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                checkLastTagWas(i);
                codedOutputStream.writeRawVarint32(i);
                return true;
            case 4:
                return false;
            case 5:
                int readRawLittleEndian32 = readRawLittleEndian32();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public final void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
        int readTag;
        do {
            readTag = readTag();
            if (readTag == 0) {
                return;
            }
        } while (skipField(readTag, codedOutputStream));
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public final long readUInt64() throws IOException {
        return readRawVarint64();
    }

    public final long readInt64() throws IOException {
        return readRawVarint64();
    }

    public final int readInt32() throws IOException {
        return readRawVarint32();
    }

    public final long readFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public final int readFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public final boolean readBool() throws IOException {
        return readRawVarint64() != 0;
    }

    public final String readString() throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 <= this.bufferSize - this.bufferPos && readRawVarint32 > 0) {
            String str = new String(this.buffer, this.bufferPos, readRawVarint32, "UTF-8");
            this.bufferPos += readRawVarint32;
            return str;
        } else if (readRawVarint32 == 0) {
            return "";
        } else {
            return new String(readRawBytesSlowPath(readRawVarint32), "UTF-8");
        }
    }

    public final String readStringRequireUtf8() throws IOException {
        byte[] bArr;
        int readRawVarint32 = readRawVarint32();
        int i = this.bufferPos;
        if (readRawVarint32 <= this.bufferSize - i && readRawVarint32 > 0) {
            bArr = this.buffer;
            this.bufferPos = i + readRawVarint32;
        } else if (readRawVarint32 == 0) {
            return "";
        } else {
            bArr = readRawBytesSlowPath(readRawVarint32);
            i = 0;
        }
        if (Utf8.isValidUtf8(bArr, i, i + readRawVarint32)) {
            return new String(bArr, i, readRawVarint32, "UTF-8");
        }
        throw InvalidProtocolBufferException.invalidUtf8();
    }

    public final void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (this.recursionDepth < this.recursionLimit) {
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public final void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth < this.recursionLimit) {
            readRawVarint32 = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(readRawVarint32);
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public final <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth < this.recursionLimit) {
            readRawVarint32 = pushLimit(readRawVarint32);
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(readRawVarint32);
            return messageLite;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public final ByteString readBytes() throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 <= this.bufferSize - this.bufferPos && readRawVarint32 > 0) {
            ByteString boundedByteString = (this.bufferIsImmutable && this.enableAliasing) ? new BoundedByteString(this.buffer, this.bufferPos, readRawVarint32) : ByteString.copyFrom(this.buffer, this.bufferPos, readRawVarint32);
            this.bufferPos += readRawVarint32;
            return boundedByteString;
        } else if (readRawVarint32 == 0) {
            return ByteString.EMPTY;
        } else {
            return new LiteralByteString(readRawBytesSlowPath(readRawVarint32));
        }
    }

    public final int readUInt32() throws IOException {
        return readRawVarint32();
    }

    public final int readEnum() throws IOException {
        return readRawVarint32();
    }

    public final int readSFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public final long readSFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public final int readSInt32() throws IOException {
        return decodeZigZag32(readRawVarint32());
    }

    public final long readSInt64() throws IOException {
        return decodeZigZag64(readRawVarint64());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int readRawVarint32() throws IOException {
        int i = this.bufferPos;
        if (this.bufferSize != i) {
            byte[] bArr = this.buffer;
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= (byte) 0) {
                this.bufferPos = i2;
                return b;
            } else if (this.bufferSize - i2 >= 9) {
                int i3 = i2 + 1;
                i = b ^ (bArr[i2] << 7);
                long j = (long) i;
                if (j < 0) {
                    i = (int) (-128 ^ j);
                } else {
                    i2 = i3 + 1;
                    i ^= bArr[i3] << 14;
                    long j2 = (long) i;
                    if (j2 >= 0) {
                        i = (int) (16256 ^ j2);
                    } else {
                        i3 = i2 + 1;
                        i ^= bArr[i2] << 21;
                        j = (long) i;
                        if (j < 0) {
                            i = (int) (-2080896 ^ j);
                        } else {
                            i2 = i3 + 1;
                            byte b2 = bArr[i3];
                            i = (int) (((long) (i ^ (b2 << 28))) ^ 266354560);
                            if (b2 < (byte) 0) {
                                i3 = i2 + 1;
                                if (bArr[i2] < (byte) 0) {
                                    i2 = i3 + 1;
                                    if (bArr[i3] < (byte) 0) {
                                        i3 = i2 + 1;
                                        if (bArr[i2] < (byte) 0) {
                                            i2 = i3 + 1;
                                            if (bArr[i3] < (byte) 0) {
                                                i3 = i2 + 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i3 = i2;
                }
                this.bufferPos = i3;
                return i;
            }
        }
        return (int) readRawVarint64SlowPath();
    }

    public static int readRawVarint32(int i, InputStream inputStream) throws IOException {
        if ((i & 128) == 0) {
            return i;
        }
        int read;
        i &= 127;
        int i2 = 7;
        while (i2 < 32) {
            read = inputStream.read();
            if (read != -1) {
                i |= (read & 127) << i2;
                if ((read & 128) == 0) {
                    return i;
                }
                i2 += 7;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
        while (i2 < 64) {
            read = inputStream.read();
            if (read == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if ((read & 128) == 0) {
                return i;
            } else {
                i2 += 7;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long readRawVarint64() throws IOException {
        int i = this.bufferPos;
        if (this.bufferSize != i) {
            byte[] bArr = this.buffer;
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= (byte) 0) {
                this.bufferPos = i2;
                return (long) b;
            } else if (this.bufferSize - i2 >= 9) {
                long j;
                int i3 = i2 + 1;
                long j2 = (long) (b ^ (bArr[i2] << 7));
                if (j2 < 0) {
                    i = i3;
                    j = -128 ^ j2;
                } else {
                    i = i3 + 1;
                    j = ((long) (bArr[i3] << 14)) ^ j2;
                    if (j >= 0) {
                        j ^= 16256;
                    } else {
                        long j3;
                        int i4 = i + 1;
                        j ^= (long) (bArr[i] << 21);
                        if (j < 0) {
                            j3 = -2080896 ^ j;
                        } else {
                            i = i4 + 1;
                            j ^= ((long) bArr[i4]) << 28;
                            if (j >= 0) {
                                j ^= 266354560;
                            } else {
                                i4 = i + 1;
                                j ^= ((long) bArr[i]) << 35;
                                if (j < 0) {
                                    j3 = -34093383808L ^ j;
                                } else {
                                    i = i4 + 1;
                                    j ^= ((long) bArr[i4]) << 42;
                                    if (j >= 0) {
                                        j ^= 4363953127296L;
                                    } else {
                                        i4 = i + 1;
                                        j ^= ((long) bArr[i]) << 49;
                                        if (j < 0) {
                                            j3 = -558586000294016L ^ j;
                                        } else {
                                            i = i4 + 1;
                                            j = (j ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                            if (j < 0) {
                                                i4 = i + 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        j = j3;
                        i = i4;
                    }
                }
                this.bufferPos = i;
                return j;
            }
        }
        return readRawVarint64SlowPath();
    }

    final long readRawVarint64SlowPath() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readRawByte = readRawByte();
            j |= ((long) (readRawByte & 127)) << i;
            if ((readRawByte & 128) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public final int readRawLittleEndian32() throws IOException {
        int i = this.bufferPos;
        if (this.bufferSize - i < 4) {
            refillBuffer(4);
            i = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    public final long readRawLittleEndian64() throws IOException {
        int i = this.bufferPos;
        if (this.bufferSize - i < 8) {
            refillBuffer(8);
            i = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48));
    }

    private CodedInputStream(InputStream inputStream) {
        this.enableAliasing = false;
        this.currentLimit = BaseClientBuilder.API_PRIORITY_OTHER;
        this.recursionLimit = 64;
        this.sizeLimit = 67108864;
        this.refillCallback = null;
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.totalBytesRetired = 0;
        this.input = inputStream;
        this.bufferIsImmutable = false;
    }

    private CodedInputStream(LiteralByteString literalByteString) {
        this.enableAliasing = false;
        this.currentLimit = BaseClientBuilder.API_PRIORITY_OTHER;
        this.recursionLimit = 64;
        this.sizeLimit = 67108864;
        this.refillCallback = null;
        this.buffer = literalByteString.bytes;
        this.bufferPos = literalByteString.getOffsetIntoBytes();
        this.bufferSize = this.bufferPos + literalByteString.size();
        this.totalBytesRetired = -this.bufferPos;
        this.input = null;
        this.bufferIsImmutable = true;
    }

    public final int pushLimit(int i) throws InvalidProtocolBufferException {
        if (i >= 0) {
            i += this.totalBytesRetired + this.bufferPos;
            int i2 = this.currentLimit;
            if (i <= i2) {
                this.currentLimit = i;
                recomputeBufferSizeAfterLimit();
                return i2;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    private void recomputeBufferSizeAfterLimit() {
        this.bufferSize += this.bufferSizeAfterLimit;
        int i = this.totalBytesRetired + this.bufferSize;
        if (i > this.currentLimit) {
            this.bufferSizeAfterLimit = i - this.currentLimit;
            this.bufferSize -= this.bufferSizeAfterLimit;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    public final void popLimit(int i) {
        this.currentLimit = i;
        recomputeBufferSizeAfterLimit();
    }

    public final int getBytesUntilLimit() {
        if (this.currentLimit == BaseClientBuilder.API_PRIORITY_OTHER) {
            return -1;
        }
        return this.currentLimit - (this.totalBytesRetired + this.bufferPos);
    }

    public final boolean isAtEnd() throws IOException {
        return this.bufferPos == this.bufferSize && !tryRefillBuffer(1);
    }

    private void ensureAvailable(int i) throws IOException {
        if (this.bufferSize - this.bufferPos < i) {
            refillBuffer(i);
        }
    }

    private void refillBuffer(int i) throws IOException {
        if (!tryRefillBuffer(i)) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private boolean tryRefillBuffer(int i) throws IOException {
        StringBuilder stringBuilder;
        while (this.bufferPos + i > this.bufferSize) {
            if ((this.totalBytesRetired + this.bufferPos) + i > this.currentLimit) {
                return false;
            }
            if (this.refillCallback != null) {
                this.refillCallback.onRefill();
            }
            if (this.input != null) {
                int i2 = this.bufferPos;
                if (i2 > 0) {
                    if (this.bufferSize > i2) {
                        Object obj = this.buffer;
                        System.arraycopy(obj, i2, obj, 0, this.bufferSize - i2);
                    }
                    this.totalBytesRetired += i2;
                    this.bufferSize -= i2;
                    this.bufferPos = 0;
                }
                i2 = this.input.read(this.buffer, this.bufferSize, this.buffer.length - this.bufferSize);
                if (i2 == 0 || i2 < -1 || i2 > this.buffer.length) {
                    stringBuilder = new StringBuilder(102);
                    stringBuilder.append("InputStream#read(byte[]) returned invalid result: ");
                    stringBuilder.append(i2);
                    stringBuilder.append("\nThe InputStream implementation is buggy.");
                    throw new IllegalStateException(stringBuilder.toString());
                } else if (i2 > 0) {
                    this.bufferSize += i2;
                    if ((this.totalBytesRetired + i) - this.sizeLimit <= 0) {
                        recomputeBufferSizeAfterLimit();
                        if (this.bufferSize >= i) {
                            return true;
                        }
                    }
                    throw InvalidProtocolBufferException.sizeLimitExceeded();
                }
            }
            return false;
        }
        stringBuilder = new StringBuilder(77);
        stringBuilder.append("refillBuffer() called when ");
        stringBuilder.append(i);
        stringBuilder.append(" bytes were already available in buffer");
        throw new IllegalStateException(stringBuilder.toString());
    }

    public final byte readRawByte() throws IOException {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(1);
        }
        byte[] bArr = this.buffer;
        int i = this.bufferPos;
        this.bufferPos = i + 1;
        return bArr[i];
    }

    private byte[] readRawBytesSlowPath(int i) throws IOException {
        int i2;
        if (i <= 0) {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            throw InvalidProtocolBufferException.negativeSize();
        } else if ((this.totalBytesRetired + this.bufferPos) + i > this.currentLimit) {
            skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.bufferPos);
            throw InvalidProtocolBufferException.truncatedMessage();
        } else if (i < 4096) {
            Object obj = new byte[i];
            i2 = this.bufferSize - this.bufferPos;
            System.arraycopy(this.buffer, this.bufferPos, obj, 0, i2);
            this.bufferPos = this.bufferSize;
            i -= i2;
            ensureAvailable(i);
            System.arraycopy(this.buffer, 0, obj, i2, i);
            this.bufferPos = i;
            return obj;
        } else {
            i2 = this.bufferPos;
            int i3 = this.bufferSize;
            this.totalBytesRetired += this.bufferSize;
            this.bufferPos = 0;
            this.bufferSize = 0;
            i3 -= i2;
            int i4 = i - i3;
            List<byte[]> arrayList = new ArrayList();
            while (i4 > 0) {
                Object obj2 = new byte[Math.min(i4, 4096)];
                int i5 = 0;
                while (i5 < obj2.length) {
                    int read = this.input == null ? -1 : this.input.read(obj2, i5, obj2.length - i5);
                    if (read != -1) {
                        this.totalBytesRetired += read;
                        i5 += read;
                    } else {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                }
                i4 -= obj2.length;
                arrayList.add(obj2);
            }
            Object obj3 = new byte[i];
            System.arraycopy(this.buffer, i2, obj3, 0, i3);
            for (byte[] bArr : arrayList) {
                System.arraycopy(bArr, 0, obj3, i3, bArr.length);
                i3 += bArr.length;
            }
            return obj3;
        }
    }

    public final void skipRawBytes(int i) throws IOException {
        if (i > this.bufferSize - this.bufferPos || i < 0) {
            skipRawBytesSlowPath(i);
        } else {
            this.bufferPos += i;
        }
    }

    private void skipRawBytesSlowPath(int i) throws IOException {
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if ((this.totalBytesRetired + this.bufferPos) + i <= this.currentLimit) {
            int i2 = this.bufferSize - this.bufferPos;
            this.bufferPos = this.bufferSize;
            refillBuffer(1);
            while (true) {
                int i3 = i - i2;
                if (i3 > this.bufferSize) {
                    i2 += this.bufferSize;
                    this.bufferPos = this.bufferSize;
                    refillBuffer(1);
                } else {
                    this.bufferPos = i3;
                    return;
                }
            }
        } else {
            skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.bufferPos);
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }
}
