package okhttp3.internal.io;

import c.l;
import c.s;
import c.t;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() {
        public t source(File file) throws FileNotFoundException {
            return l.a(file);
        }

        public s sink(File file) throws FileNotFoundException {
            try {
                return l.b(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return l.b(file);
            }
        }

        public s appendingSink(File file) throws FileNotFoundException {
            try {
                return l.c(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return l.c(file);
            }
        }

        public void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        public boolean exists(File file) {
            return file.exists();
        }

        public long size(File file) {
            return file.length();
        }

        public void rename(File from, File to) throws IOException {
            delete(to);
            if (!from.renameTo(to)) {
                throw new IOException("failed to rename " + from + " to " + to);
            }
        }

        public void deleteContents(File directory) throws IOException {
            File[] files = directory.listFiles();
            if (files == null) {
                throw new IOException("not a readable directory: " + directory);
            }
            int length = files.length;
            int i = 0;
            while (i < length) {
                File file = files[i];
                if (file.isDirectory()) {
                    deleteContents(file);
                }
                if (file.delete()) {
                    i++;
                } else {
                    throw new IOException("failed to delete " + file);
                }
            }
        }
    };

    s appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File file) throws IOException;

    boolean exists(File file);

    void rename(File file, File file2) throws IOException;

    s sink(File file) throws FileNotFoundException;

    long size(File file);

    t source(File file) throws FileNotFoundException;
}
