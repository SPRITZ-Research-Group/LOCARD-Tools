package com.microsoft.react.videofxp;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public final class i {
    public static boolean a = false;
    private static final int b = a(new byte[]{(byte) 102, (byte) 114, (byte) 101, (byte) 101});
    private static final int c = a(new byte[]{(byte) 106, (byte) 117, (byte) 110, (byte) 107});
    private static final int d = a(new byte[]{(byte) 109, (byte) 100, (byte) 97, (byte) 116});
    private static final int e = a(new byte[]{(byte) 109, (byte) 111, (byte) 111, (byte) 118});
    private static final int f = a(new byte[]{(byte) 112, (byte) 110, (byte) 111, (byte) 116});
    private static final int g = a(new byte[]{(byte) 115, (byte) 107, (byte) 105, (byte) 112});
    private static final int h = a(new byte[]{(byte) 119, (byte) 105, (byte) 100, (byte) 101});
    private static final int i = a(new byte[]{(byte) 80, (byte) 73, (byte) 67, (byte) 84});
    private static final int j = a(new byte[]{(byte) 102, (byte) 116, (byte) 121, (byte) 112});
    private static final int k = a(new byte[]{(byte) 117, (byte) 117, (byte) 105, (byte) 100});
    private static final int l = a(new byte[]{(byte) 99, (byte) 109, (byte) 111, (byte) 118});
    private static final int m = a(new byte[]{(byte) 115, (byte) 116, (byte) 99, (byte) 111});
    private static final int n = a(new byte[]{(byte) 99, (byte) 111, (byte) 54, (byte) 52});

    public static class b extends Exception {
        /* synthetic */ b(String x0, byte b) {
            this(x0);
        }

        private b(String detailMessage) {
            super(detailMessage);
        }
    }

    public static class a extends b {
        /* synthetic */ a(String x0, byte b) {
            this(x0);
        }

        private a(String detailMessage) {
            super(detailMessage, (byte) 0);
        }
    }

    public static class c extends b {
        /* synthetic */ c(String x0, byte b) {
            this(x0);
        }

        private c(String detailMessage) {
            super(detailMessage, (byte) 0);
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                a("Failed to close file: ", new Object[0]);
                if (a) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int a(long uint32) throws c {
        if (uint32 <= 2147483647L && uint32 >= 0) {
            return (int) uint32;
        }
        throw new c("uint32 value is too large", (byte) 0);
    }

    private static int a(byte[] byteArray) {
        return ByteBuffer.wrap(byteArray).order(ByteOrder.BIG_ENDIAN).getInt();
    }

    private static void a(String format, Object... args) {
        if (a) {
            System.err.println("QtFastStart: " + String.format(format, args));
        }
    }

    private static boolean a(FileChannel infile, ByteBuffer buffer) throws IOException {
        buffer.clear();
        int size = infile.read(buffer);
        buffer.flip();
        return size == buffer.capacity();
    }

    public static boolean a(File in, File out) throws IOException, a, c {
        Throwable th;
        boolean ret = false;
        Closeable inStream = null;
        Closeable outStream = null;
        try {
            Closeable inStream2 = new FileInputStream(in);
            try {
                FileChannel infile = inStream2.getChannel();
                Closeable outStream2 = new FileOutputStream(out);
                try {
                    ret = a(infile, outStream2.getChannel());
                    a(inStream2);
                    a(outStream2);
                    if (!ret) {
                        out.delete();
                    }
                    return ret;
                } catch (Throwable th2) {
                    th = th2;
                    outStream = outStream2;
                    inStream = inStream2;
                    a(inStream);
                    a(outStream);
                    if (!ret) {
                        out.delete();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inStream = inStream2;
                a(inStream);
                a(outStream);
                if (ret) {
                    out.delete();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            a(inStream);
            a(outStream);
            if (ret) {
                out.delete();
            }
            throw th;
        }
    }

    private static boolean a(FileChannel infile, FileChannel outfile) throws IOException, a, c {
        ByteBuffer atomBytes = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN);
        int atomType = 0;
        long atomSize = 0;
        ByteBuffer ftypAtom = null;
        long startOffset = 0;
        while (a(infile, atomBytes)) {
            atomSize = ((long) atomBytes.getInt()) & 4294967295L;
            atomType = atomBytes.getInt();
            if (atomType == j) {
                int ftypAtomSize = a(atomSize);
                ftypAtom = ByteBuffer.allocate(ftypAtomSize).order(ByteOrder.BIG_ENDIAN);
                atomBytes.rewind();
                ftypAtom.put(atomBytes);
                if (infile.read(ftypAtom) < ftypAtomSize - 8) {
                    break;
                }
                ftypAtom.flip();
                startOffset = infile.position();
            } else if (atomSize == 1) {
                atomBytes.clear();
                if (!a(infile, atomBytes)) {
                    break;
                }
                atomSize = atomBytes.getLong();
                if (atomSize < 0) {
                    throw new c("uint64 value is too large", (byte) 0);
                }
                infile.position((infile.position() + atomSize) - 16);
            } else {
                infile.position((infile.position() + atomSize) - 8);
            }
            if (a) {
                a("%c%c%c%c %10d %d", Integer.valueOf((atomType >> 24) & 255), Integer.valueOf((atomType >> 16) & 255), Integer.valueOf((atomType >> 8) & 255), Integer.valueOf((atomType >> 0) & 255), Long.valueOf(infile.position() - atomSize), Long.valueOf(atomSize));
            }
            if (atomType == b || atomType == c || atomType == d || atomType == e || atomType == f || atomType == g || atomType == h || atomType == i || atomType == k || atomType == j) {
                if (atomSize < 8) {
                    break;
                }
            }
            a("encountered non-QT top-level atom (is this a QuickTime file?)", new Object[0]);
            break;
        }
        if (atomType != e) {
            a("last atom in file was not a moov atom", new Object[0]);
            return false;
        }
        Object obj;
        int moovAtomSize = a(atomSize);
        long lastOffset = infile.size() - ((long) moovAtomSize);
        ByteBuffer moovAtom = ByteBuffer.allocate(moovAtomSize).order(ByteOrder.BIG_ENDIAN);
        moovAtom.clear();
        int read = infile.read(moovAtom, lastOffset);
        moovAtom.flip();
        if (read == moovAtom.capacity()) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            throw new a("failed to read moov atom", (byte) 0);
        } else if (moovAtom.getInt(12) == l) {
            throw new c("this utility does not support compressed moov atoms yet", (byte) 0);
        } else {
            while (moovAtom.remaining() >= 8) {
                int atomHead = moovAtom.position();
                atomType = moovAtom.getInt(atomHead + 4);
                if (atomType != m && atomType != n) {
                    moovAtom.position(moovAtom.position() + 1);
                } else if ((((long) moovAtom.getInt(atomHead)) & 4294967295L) > ((long) moovAtom.remaining())) {
                    throw new a("bad atom size", (byte) 0);
                } else {
                    moovAtom.position(atomHead + 12);
                    if (moovAtom.remaining() < 4) {
                        throw new a("malformed atom", (byte) 0);
                    }
                    int offsetCount = moovAtom.getInt();
                    int i;
                    if (offsetCount < 0) {
                        throw new c("uint32 value is too large", (byte) 0);
                    } else if (atomType == m) {
                        a("patching stco atom...", new Object[0]);
                        if (moovAtom.remaining() < offsetCount * 4) {
                            throw new a("bad atom size/element count", (byte) 0);
                        }
                        i = 0;
                        while (i < offsetCount) {
                            int currentOffset = moovAtom.getInt(moovAtom.position());
                            int newOffset = currentOffset + moovAtomSize;
                            if (currentOffset >= 0 || newOffset < 0) {
                                moovAtom.putInt(newOffset);
                                i++;
                            } else {
                                throw new c("This is bug in original qt-faststart.c: stco atom should be extended to co64 atom as new offset value overflows uint32, but is not implemented.", (byte) 0);
                            }
                        }
                        continue;
                    } else if (atomType == n) {
                        a("patching co64 atom...", new Object[0]);
                        if (moovAtom.remaining() < offsetCount * 8) {
                            throw new a("bad atom size/element count", (byte) 0);
                        }
                        for (i = 0; i < offsetCount; i++) {
                            long currentOffset2 = moovAtom.getLong(moovAtom.position());
                            moovAtom.putLong(((long) moovAtomSize) + currentOffset2);
                        }
                    } else {
                        continue;
                    }
                }
            }
            infile.position(startOffset);
            if (ftypAtom != null) {
                a("writing ftyp atom...", new Object[0]);
                ftypAtom.rewind();
                outfile.write(ftypAtom);
            }
            a("writing moov atom...", new Object[0]);
            moovAtom.rewind();
            outfile.write(moovAtom);
            a("copying rest of file...", new Object[0]);
            infile.transferTo(startOffset, lastOffset - startOffset, outfile);
            return true;
        }
    }
}
