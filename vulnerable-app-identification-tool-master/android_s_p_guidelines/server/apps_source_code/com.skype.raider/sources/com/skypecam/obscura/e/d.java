package com.skypecam.obscura.e;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class d {
    public final File a;
    public final int b;
    public final int c;
    private String d;

    public d(File file) {
        this.a = file;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        this.b = options.outWidth;
        this.c = options.outHeight;
    }

    public final void a() throws IOException {
        RandomAccessFile f = new RandomAccessFile(this.a, "r");
        byte[] data = new byte[((int) f.length())];
        f.readFully(data);
        f.close();
        this.d = Base64.encodeToString(data, 0);
    }
}
