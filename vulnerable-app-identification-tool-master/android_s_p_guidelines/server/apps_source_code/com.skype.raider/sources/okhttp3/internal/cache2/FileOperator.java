package okhttp3.internal.cache2;

import c.c;
import java.io.IOException;
import java.nio.channels.FileChannel;

final class FileOperator {
    private final FileChannel fileChannel;

    FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public final void write(long pos, c source, long byteCount) throws IOException {
        if (byteCount < 0 || byteCount > source.a()) {
            throw new IndexOutOfBoundsException();
        }
        while (byteCount > 0) {
            long bytesWritten = this.fileChannel.transferFrom(source, pos, byteCount);
            pos += bytesWritten;
            byteCount -= bytesWritten;
        }
    }

    public final void read(long pos, c sink, long byteCount) throws IOException {
        if (byteCount < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (byteCount > 0) {
            long bytesRead = this.fileChannel.transferTo(pos, byteCount, sink);
            pos += bytesRead;
            byteCount -= bytesRead;
        }
    }
}
