package net.hockeyapp.android.f;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public final class i {
    private static final char[] a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private boolean b = false;
    private boolean c = false;
    private File d;
    private OutputStream e;
    private String f;

    public i(File tempFile) {
        this.d = tempFile;
        try {
            this.e = new FileOutputStream(this.d);
        } catch (IOException e) {
            e.f();
        }
        StringBuilder buffer = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            buffer.append(a[rand.nextInt(a.length)]);
        }
        this.f = buffer.toString();
    }

    public final String a() {
        return this.f;
    }

    public final void b() throws IOException {
        if (!this.c) {
            this.e.write(("--" + this.f + "\r\n").getBytes());
        }
        this.c = true;
    }

    public final void c() {
        if (!this.b) {
            try {
                this.e.write(("\r\n--" + this.f + "--\r\n").getBytes());
                this.e.flush();
                this.e.close();
                this.e = null;
            } catch (IOException e) {
                e.f();
            }
            this.b = true;
        }
    }

    public final void a(String key, String value) throws IOException {
        b();
        this.e.write(("Content-Disposition: form-data; name=\"" + key + "\"\r\n").getBytes());
        this.e.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes());
        this.e.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes());
        this.e.write(value.getBytes());
        this.e.write(("\r\n--" + this.f + "\r\n").getBytes());
    }

    public final void a(String key, String fileName, InputStream fin, String type, boolean lastFile) throws IOException {
        b();
        try {
            type = "Content-Type: " + type + "\r\n";
            this.e.write(("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + fileName + "\"\r\n").getBytes());
            this.e.write(type.getBytes());
            this.e.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] tmp = new byte[4096];
            while (true) {
                int l = fin.read(tmp);
                if (l == -1) {
                    break;
                }
                this.e.write(tmp, 0, l);
            }
            this.e.flush();
            if (lastFile) {
                c();
            } else {
                this.e.write(("\r\n--" + this.f + "\r\n").getBytes());
            }
        } finally {
            try {
                fin.close();
            } catch (IOException e) {
            }
        }
    }

    public final long d() {
        c();
        return this.d.length();
    }

    public final void a(OutputStream out) throws IOException {
        c();
        FileInputStream fileInputStream = new FileInputStream(this.d);
        BufferedOutputStream outputStream = new BufferedOutputStream(out);
        byte[] tmp = new byte[4096];
        while (true) {
            int l = fileInputStream.read(tmp);
            if (l != -1) {
                outputStream.write(tmp, 0, l);
            } else {
                fileInputStream.close();
                outputStream.flush();
                outputStream.close();
                this.d.delete();
                this.d = null;
                return;
            }
        }
    }
}
