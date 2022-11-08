package com.skpcamera.antediluvian;

import com.facebook.common.logging.FLog;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public final class v {
    private static String a = "free";
    private static String b = "junk";
    private static String c = "mdat";
    private static String d = "moov";
    private static String e = "pnot";
    private static String f = "skip";
    private static String g = "wide";
    private static String h = "pict";
    private static String i = "uuid";
    private static String j = "ftyp";
    private static String k = "stco";
    private static String l = "co64";
    private File m;
    private byte[] n;
    private long o = 0;
    private byte[] p;
    private long q;

    public v(File vim) {
        this.m = vim;
    }

    public final File a() throws IOException {
        File file = this.m;
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        if (a(randomAccessFile)) {
            b(randomAccessFile);
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            a(randomAccessFile, randomAccessFile2);
            randomAccessFile.close();
            randomAccessFile2.close();
        }
        return file;
    }

    private boolean a(RandomAccessFile f) throws IOException {
        long atom_size = 0;
        String atom_type = "";
        byte[] byte_type = new byte[4];
        do {
            long prev = f.getFilePointer();
            if (prev < f.length()) {
                atom_size = (long) f.readInt();
                f.readFully(byte_type);
                atom_type = new String(byte_type).toLowerCase();
                FLog.i("SkypeStreamableVim", "read in " + atom_type + " at " + prev);
                if (atom_size == 1) {
                    atom_size = f.readLong();
                }
                f.seek(prev);
                if (atom_type.equals(j)) {
                    this.o = atom_size;
                    FLog.i("SkypeStreamableVim", "ftyp atom size is " + this.o);
                    this.n = new byte[((int) this.o)];
                    if (f.read(this.n) == -1) {
                    }
                } else {
                    FLog.i("SkypeStreamableVim", "skipping " + atom_size);
                    f.skipBytes((int) atom_size);
                }
                if (!atom_type.equals(a) && !atom_type.equals(b) && !atom_type.equals(c) && !atom_type.equals(d) && !atom_type.equals(e) && !atom_type.equals(f) && !atom_type.equals(g) && !atom_type.equals(h) && !atom_type.equals(i) && !atom_type.equals(j)) {
                    FLog.w("SkypeStreamableVim", "encountered non-QT top-level atom " + (atom_type == j ? " match " : " non"));
                }
            }
            if (atom_type.equals(d)) {
                FLog.i("SkypeStreamableVim", "seeking (load moov) " + (prev - atom_size));
                f.seek(prev - atom_size);
                this.q = atom_size;
                this.p = new byte[((int) this.q)];
                f.readFully(this.p);
                return true;
            }
            FLog.i("SkypeStreamableVim", "last atom was not a moov atom but " + atom_type);
            f.close();
            return false;
        } while (atom_size >= 8);
        f.close();
        throw new IOException("Could not load mp4 file (unscannable atom size)");
    }

    private void b(RandomAccessFile f) throws IOException {
        FLog.i("SkypeStreamableVim", "about to patch");
        int i = 4;
        while (((long) i) < this.q - 4) {
            String atom_type = new String(Arrays.copyOfRange(this.p, i, i + 4)).toLowerCase();
            long atom_size;
            int offset_count;
            int j;
            long current_offset;
            if (atom_type.equals(k)) {
                FLog.i("SkypeStreamableVim", "patching STCO atom");
                atom_size = (long) ((int) a(Arrays.copyOfRange(this.p, i - 4, i)));
                if ((((long) i) + atom_size) - 4 > this.q) {
                    f.close();
                    throw new IOException("Could not reindex mp4 file (bad STCO atom size)");
                }
                offset_count = (int) a(Arrays.copyOfRange(this.p, i + 8, i + 12));
                for (j = 0; j < offset_count; j++) {
                    current_offset = ((long) ((int) a(Arrays.copyOfRange(this.p, (i + 12) + (j * 4), (i + 16) + (j * 4))))) + this.q;
                    this.p[((i + 12) + (j * 4)) + 0] = (byte) ((int) ((current_offset >> 24) & 255));
                    this.p[((i + 12) + (j * 4)) + 1] = (byte) ((int) ((current_offset >> 16) & 255));
                    this.p[((i + 12) + (j * 4)) + 2] = (byte) ((int) ((current_offset >> 8) & 255));
                    this.p[((i + 12) + (j * 4)) + 3] = (byte) ((int) ((current_offset >> null) & 255));
                }
                i = (int) (((long) i) + (atom_size - 4));
            } else if (atom_type.equals(l)) {
                FLog.i("SkypeStreamableVim", "patching co64 atom");
                atom_size = (long) ((int) a(Arrays.copyOfRange(this.p, i - 4, i)));
                if ((((long) i) + atom_size) - 4 > this.q) {
                    FLog.i("SkypeStreamableVim", "bad atom size");
                    f.close();
                    throw new IOException("Could not reindex mp4 file (bad CO64 atom size)");
                }
                offset_count = (int) a(Arrays.copyOfRange(this.p, i + 8, i + 12));
                for (j = 0; j < offset_count; j++) {
                    current_offset = ((long) ((int) a(Arrays.copyOfRange(this.p, (i + 12) + (j * 8), (i + 16) + (j * 8))))) + this.q;
                    this.p[((i + 12) + (j * 8)) + 0] = (byte) ((int) ((current_offset >> 56) & 255));
                    this.p[((i + 12) + (j * 8)) + 1] = (byte) ((int) ((current_offset >> 48) & 255));
                    this.p[((i + 12) + (j * 8)) + 2] = (byte) ((int) ((current_offset >> 40) & 255));
                    this.p[((i + 12) + (j * 8)) + 3] = (byte) ((int) ((current_offset >> 32) & 255));
                    this.p[((i + 12) + (j * 8)) + 4] = (byte) ((int) ((current_offset >> 24) & 255));
                    this.p[((i + 12) + (j * 8)) + 5] = (byte) ((int) ((current_offset >> 16) & 255));
                    this.p[((i + 12) + (j * 8)) + 6] = (byte) ((int) ((current_offset >> 8) & 255));
                    this.p[((i + 12) + (j * 8)) + 7] = (byte) ((int) ((current_offset >> null) & 255));
                }
                i = (int) (((long) i) + (atom_size - 4));
            } else {
                continue;
            }
            i++;
        }
    }

    private boolean a(RandomAccessFile f, RandomAccessFile out) throws IOException {
        byte[] temp_buf = new byte[((int) this.q)];
        f.seek((long) ((int) this.o));
        f.read(temp_buf);
        FLog.i("SkypeStreamableVim", "read to " + f.getFilePointer() + " into " + this.q);
        out.seek(this.o);
        FLog.i("SkypeStreamableVim", "out pointer is at " + out.getFilePointer());
        int to_go = (int) (out.length() - out.getFilePointer());
        int temp_len = Math.min((int) this.q, to_go);
        boolean temp = false;
        while (to_go > 0) {
            if (temp) {
                a(f, out, temp_buf, temp_len);
            } else {
                a(f, out, this.p, temp_len);
            }
            to_go = (int) (out.length() - out.getFilePointer());
            temp_len = Math.min(temp_len, to_go);
            temp = !temp;
        }
        return true;
    }

    private static int a(RandomAccessFile in, RandomAccessFile out, byte[] buffer, int n) throws IOException {
        out.write(buffer, 0, n);
        return in.read(buffer);
    }

    private static long a(byte[] b) {
        long r = 0;
        for (int i = 0; i < b.length; i++) {
            r += (long) ((b[i] & 255) << (((b.length - i) - 1) * 8));
        }
        return r;
    }
}
