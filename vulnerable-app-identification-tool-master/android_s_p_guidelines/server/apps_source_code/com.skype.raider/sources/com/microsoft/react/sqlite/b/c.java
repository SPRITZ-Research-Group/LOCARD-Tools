package com.microsoft.react.sqlite.b;

import java.io.IOException;
import java.io.Writer;

public final class c extends Writer {
    private StringBuilder a = new StringBuilder(16);

    public final /* synthetic */ Writer append(CharSequence charSequence) throws IOException {
        return a(charSequence);
    }

    public final /* synthetic */ Writer append(CharSequence charSequence, int i, int i2) throws IOException {
        return a(charSequence, i, i2);
    }

    public final /* synthetic */ Appendable append(CharSequence charSequence) throws IOException {
        return a(charSequence);
    }

    public final /* synthetic */ Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
        return a(charSequence, i, i2);
    }

    public c(byte b) {
    }

    private c a(CharSequence csq) {
        if (csq == null) {
            csq = "null";
        }
        write(csq.toString());
        return this;
    }

    private c a(CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = "null";
        }
        String output = csq.subSequence(start, end).toString();
        write(output, 0, output.length());
        return this;
    }

    public final void close() throws IOException {
    }

    public final void flush() {
    }

    public final String toString() {
        return this.a.toString();
    }

    public final void write(char[] chars, int offset, int count) {
        if (count != 0) {
            this.a.append(chars, offset, count);
        }
    }

    public final void write(int oneChar) {
        this.a.append((char) oneChar);
    }

    public final void write(String str) {
        this.a.append(str);
    }

    public final void write(String str, int offset, int count) {
        this.a.append(str.substring(offset, offset + count));
    }

    public final /* synthetic */ Writer append(char c) throws IOException {
        write((int) c);
        return this;
    }

    public final /* synthetic */ Appendable append(char c) throws IOException {
        write((int) c);
        return this;
    }
}
