package com.skype.hockeyapp;

import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.Writer;

public class CompositeWriter extends Writer {
    Writer[] a;

    public CompositeWriter(Writer... writers) {
        this.a = writers;
    }

    public void close() throws IOException {
        for (Writer writer : this.a) {
            try {
                writer.close();
            } catch (Throwable e) {
                FLog.w("CompositeWriter", "close failed", e);
            }
        }
    }

    public void flush() throws IOException {
        for (Writer writer : this.a) {
            try {
                writer.flush();
            } catch (Throwable e) {
                FLog.w("CompositeWriter", "flush failed", e);
            }
        }
    }

    public void write(char[] buf, int offset, int count) throws IOException {
        for (Writer writer : this.a) {
            try {
                writer.write(buf, offset, count);
            } catch (Throwable e) {
                FLog.w("CompositeWriter", "write failed", e);
            }
        }
    }
}
