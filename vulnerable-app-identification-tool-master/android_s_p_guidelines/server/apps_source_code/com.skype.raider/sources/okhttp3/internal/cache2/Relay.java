package okhttp3.internal.cache2;

import c.c;
import c.f;
import c.t;
import c.u;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;

final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    static final f PREFIX_CLEAN = f.a("OkHttp cache v1\n");
    static final f PREFIX_DIRTY = f.a("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    final c buffer = new c();
    final long bufferMaxSize;
    boolean complete;
    RandomAccessFile file;
    private final f metadata;
    int sourceCount;
    t upstream;
    final c upstreamBuffer = new c();
    long upstreamPos;
    Thread upstreamReader;

    class RelaySource implements t {
        private FileOperator fileOperator = new FileOperator(Relay.this.file.getChannel());
        private long sourcePos;
        private final u timeout = new u();

        RelaySource() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(c sink, long byteCount) throws IOException {
            if (this.fileOperator == null) {
                throw new IllegalStateException("closed");
            }
            synchronized (Relay.this) {
                long upstreamPos;
                while (true) {
                    long j = this.sourcePos;
                    upstreamPos = Relay.this.upstreamPos;
                    if (j == upstreamPos) {
                        if (!Relay.this.complete) {
                            if (Relay.this.upstreamReader == null) {
                                break;
                            }
                            this.timeout.waitUntilNotified(Relay.this);
                        } else {
                            return -1;
                        }
                    }
                    break;
                }
                if (source == 2) {
                    long bytesToRead = Math.min(byteCount, upstreamPos - this.sourcePos);
                    this.fileOperator.read(Relay.FILE_HEADER_SIZE + this.sourcePos, sink, bytesToRead);
                    this.sourcePos += bytesToRead;
                    return bytesToRead;
                }
                c cVar;
                try {
                    t tVar = Relay.this.upstream;
                    cVar = Relay.this.upstreamBuffer;
                    long upstreamBytesRead = tVar.read(cVar, Relay.this.bufferMaxSize);
                    if (upstreamBytesRead == -1) {
                        Relay.this.commit(upstreamPos);
                        Relay.this.upstreamReader = null;
                        Relay.this.notifyAll();
                        return -1;
                    }
                    long bytesRead = Math.min(upstreamBytesRead, byteCount);
                    Relay.this.upstreamBuffer.a(sink, 0, bytesRead);
                    this.sourcePos += bytesRead;
                    this.fileOperator.write(Relay.FILE_HEADER_SIZE + upstreamPos, Relay.this.upstreamBuffer.v(), upstreamBytesRead);
                    synchronized (Relay.this) {
                        Relay.this.buffer.write(Relay.this.upstreamBuffer, upstreamBytesRead);
                        if (Relay.this.buffer.a() > Relay.this.bufferMaxSize) {
                            Relay.this.buffer.h(Relay.this.buffer.a() - Relay.this.bufferMaxSize);
                        }
                        Relay relay = Relay.this;
                        relay.upstreamPos += upstreamBytesRead;
                    }
                    synchronized (Relay.this) {
                        Relay.this.upstreamReader = null;
                        Relay.this.notifyAll();
                    }
                    return bytesRead;
                } finally {
                    cVar = Relay.this;
                    synchronized (cVar) {
                        Relay.this.upstreamReader = null;
                        Relay.this.notifyAll();
                    }
                }
            }
        }

        public u timeout() {
            return this.timeout;
        }

        public void close() throws IOException {
            if (this.fileOperator != null) {
                this.fileOperator = null;
                Closeable fileToClose = null;
                synchronized (Relay.this) {
                    Relay relay = Relay.this;
                    relay.sourceCount--;
                    if (Relay.this.sourceCount == 0) {
                        fileToClose = Relay.this.file;
                        Relay.this.file = null;
                    }
                }
                if (fileToClose != null) {
                    Util.closeQuietly(fileToClose);
                }
            }
        }
    }

    private Relay(RandomAccessFile file, t upstream, long upstreamPos, f metadata, long bufferMaxSize) {
        this.file = file;
        this.upstream = upstream;
        this.complete = upstream == null;
        this.upstreamPos = upstreamPos;
        this.metadata = metadata;
        this.bufferMaxSize = bufferMaxSize;
    }

    public static Relay edit(File file, t upstream, f metadata, long bufferMaxSize) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Relay result = new Relay(randomAccessFile, upstream, 0, metadata, bufferMaxSize);
        randomAccessFile.setLength(0);
        result.writeHeader(PREFIX_DIRTY, -1, -1);
        return result;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
        c header = new c();
        fileOperator.read(0, header, FILE_HEADER_SIZE);
        if (header.d((long) PREFIX_CLEAN.h()).equals(PREFIX_CLEAN)) {
            long upstreamSize = header.k();
            long metadataSize = header.k();
            c metadataBuffer = new c();
            fileOperator.read(FILE_HEADER_SIZE + upstreamSize, metadataBuffer, metadataSize);
            return new Relay(randomAccessFile, null, upstreamSize, metadataBuffer.p(), 0);
        }
        throw new IOException("unreadable cache file");
    }

    private void writeHeader(f prefix, long upstreamSize, long metadataSize) throws IOException {
        c header = new c();
        header.a(prefix);
        header.i(upstreamSize);
        header.i(metadataSize);
        if (header.a() != FILE_HEADER_SIZE) {
            throw new IllegalArgumentException();
        }
        new FileOperator(this.file.getChannel()).write(0, header, FILE_HEADER_SIZE);
    }

    private void writeMetadata(long upstreamSize) throws IOException {
        c metadataBuffer = new c();
        metadataBuffer.a(this.metadata);
        new FileOperator(this.file.getChannel()).write(FILE_HEADER_SIZE + upstreamSize, metadataBuffer, (long) this.metadata.h());
    }

    final void commit(long upstreamSize) throws IOException {
        writeMetadata(upstreamSize);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, upstreamSize, (long) this.metadata.h());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    final boolean isClosed() {
        return this.file == null;
    }

    public final f metadata() {
        return this.metadata;
    }

    public final t newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }
}
