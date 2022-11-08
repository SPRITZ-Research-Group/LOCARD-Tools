package androidx.multidex;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

final class k {
    static long a(File file) throws IOException {
        long j = "r";
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, j);
        try {
            j = a(randomAccessFile, a(randomAccessFile));
            return j;
        } finally {
            randomAccessFile.close();
        }
    }

    private static l a(RandomAccessFile randomAccessFile) throws IOException, ZipException {
        long length = randomAccessFile.length() - 22;
        long j = 0;
        if (length >= 0) {
            long j2 = length - 65536;
            if (j2 >= 0) {
                j = j2;
            }
            int reverseBytes = Integer.reverseBytes(101010256);
            while (true) {
                randomAccessFile.seek(length);
                if (randomAccessFile.readInt() != reverseBytes) {
                    length--;
                    if (length < j) {
                        throw new ZipException("End Of Central Directory signature not found");
                    }
                } else {
                    randomAccessFile.skipBytes(2);
                    randomAccessFile.skipBytes(2);
                    randomAccessFile.skipBytes(2);
                    randomAccessFile.skipBytes(2);
                    l lVar = new l();
                    lVar.b = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                    lVar.a = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                    return lVar;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder("File too short to be a zip file: ");
        stringBuilder.append(randomAccessFile.length());
        throw new ZipException(stringBuilder.toString());
    }

    private static long a(RandomAccessFile randomAccessFile, l lVar) throws IOException {
        CRC32 crc32 = new CRC32();
        long j = lVar.b;
        randomAccessFile.seek(lVar.a);
        byte[] bArr = new byte[16384];
        int read = randomAccessFile.read(bArr, 0, (int) Math.min(16384, j));
        while (read != -1) {
            crc32.update(bArr, 0, read);
            j -= (long) read;
            if (j == 0) {
                break;
            }
            read = randomAccessFile.read(bArr, 0, (int) Math.min(16384, j));
        }
        return crc32.getValue();
    }
}
