package com.skype;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class LibraryLoaderHelper {
    static final /* synthetic */ boolean $assertionsDisabled = (!LibraryLoaderHelper.class.desiredAssertionStatus());
    private final String LIB_DIR = "lib";
    private final String TAG = "LibraryLoaderHelper";
    private final String[] allLibraries;
    private final Context context;
    private final ExecutorService executorService;
    private Future<?> loadTask;
    Map<String, Boolean> loadedLibraries;
    private boolean sLibrariesWereUnpacked = false;

    public LibraryLoaderHelper(Context context, String[] libraries) {
        this.context = context;
        this.allLibraries = libraries;
        this.loadedLibraries = new HashMap<String, Boolean>(libraries.length) {
        };
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void loadAllNativeLibraries() {
        if (this.loadTask == null) {
            this.loadTask = this.executorService.submit(new Runnable() {
                public final void run() {
                    if (LibraryLoaderHelper.this.unpackLibrariesOnce(LibraryLoaderHelper.this.context, LibraryLoaderHelper.this.allLibraries)) {
                        String[] access$000 = LibraryLoaderHelper.this.allLibraries;
                        int length = access$000.length;
                        int i = 0;
                        while (i < length) {
                            String eachLibrary = access$000[i];
                            if (LibraryLoaderHelper.this.tryLoadLibrary(LibraryLoaderHelper.this.context, eachLibrary)) {
                                LibraryLoaderHelper.this.loadedLibraries.put(eachLibrary, Boolean.valueOf(true));
                                i++;
                            } else {
                                return;
                            }
                        }
                        synchronized (LibraryLoaderHelper.this) {
                            LibraryLoaderHelper.this.notifyAll();
                        }
                    }
                }
            });
        }
    }

    public boolean loadNativeLibrary(String library) {
        if (!this.loadedLibraries.containsKey(library)) {
            return false;
        }
        if (!tryLoadLibrary(this.context, library) && this.loadTask == null) {
            loadAllNativeLibraries();
        }
        if (((Boolean) this.loadedLibraries.get(library)).booleanValue()) {
            return true;
        }
        try {
            this.loadTask.get();
            return ((Boolean) this.loadedLibraries.get(library)).booleanValue();
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e2) {
            return false;
        }
    }

    public void cleanup(String libraryName) {
        File libFile = getLibFile(this.context, libraryName);
        if (!libFile.exists()) {
            libFile.delete();
        }
    }

    boolean tryLoadLibrary(Context context, String library) {
        if ($assertionsDisabled || context != null) {
            File libFile = getLibFile(context, library);
            if (!libFile.exists()) {
                return false;
            }
            try {
                System.load(libFile.getAbsolutePath());
                this.loadedLibraries.put(library, Boolean.valueOf(true));
                return true;
            } catch (UnsatisfiedLinkError e) {
                return false;
            }
        }
        throw new AssertionError();
    }

    public File getLibDir(Context context) {
        return context.getDir("lib", 0);
    }

    private File getLibFile(Context context, String library) {
        return new File(getLibDir(context), System.mapLibraryName(library));
    }

    private boolean unpackLibrariesOnce(Context context, String[] libraries) {
        if (this.sLibrariesWereUnpacked) {
            return false;
        }
        this.sLibrariesWereUnpacked = true;
        File libDir = getLibDir(context);
        deleteDirectorySync(libDir);
        try {
            ZipFile zipFile = new ZipFile(new File(context.getApplicationInfo().sourceDir), 1);
            int length = libraries.length;
            int i = 0;
            while (i < length && extractLib(context, libDir, zipFile, libraries[i])) {
                i++;
            }
            try {
                zipFile.close();
                return true;
            } catch (IOException e) {
                new StringBuilder("Failed to close archive ").append(zipFile);
                deleteDirectorySync(libDir);
                return false;
            }
        } catch (IOException e2) {
            deleteDirectorySync(libDir);
            return false;
        }
    }

    private boolean extractLib(Context context, File libDir, ZipFile zipFile, String libName) {
        String jniNameInApk = "lib/" + Build.CPU_ABI2 + "/" + System.mapLibraryName(libName);
        ZipEntry entry = zipFile.getEntry(jniNameInApk);
        if (entry == null) {
            new StringBuilder().append(zipFile.getName()).append(" doesn't have file ").append(jniNameInApk);
            deleteDirectorySync(libDir);
            return false;
        }
        File outputFile = getLibFile(context, libName);
        new StringBuilder("Extracting native libraries into ").append(outputFile.getAbsolutePath());
        if ($assertionsDisabled || !outputFile.exists()) {
            try {
                if (outputFile.createNewFile()) {
                    copyToOutputStream(zipFile.getInputStream(entry), new FileOutputStream(outputFile));
                    setFileReadableExecutableAndOwnerOnlyWritable(outputFile);
                    return true;
                }
                throw new IOException("Could not create output file for " + outputFile);
            } catch (IOException e) {
                if (!outputFile.exists() || outputFile.delete()) {
                    return false;
                }
                new StringBuilder("Failed to delete ").append(outputFile.getAbsolutePath());
                return false;
            }
        }
        throw new AssertionError();
    }

    private void setFileReadableExecutableAndOwnerOnlyWritable(File outputFile) {
        outputFile.setReadable(true, false);
        outputFile.setExecutable(true, false);
        outputFile.setWritable(true);
    }

    private void copyToOutputStream(InputStream inputStream, FileOutputStream outputStream) throws IOException {
        try {
            byte[] buffer = new byte[16384];
            while (true) {
                int count = inputStream.read(buffer);
                if (count <= 0) {
                    break;
                }
                outputStream.write(buffer, 0, count);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th) {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Throwable th2) {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    void deleteWorkaroundLibrariesAsynchronously(final Context context) {
        new Thread() {
            public final void run() {
                LibraryLoaderHelper.this.deleteWorkaroundLibrariesSynchronously(context);
            }
        }.start();
    }

    public void deleteWorkaroundLibrariesSynchronously(Context context) {
        deleteDirectorySync(getLibDir(context));
    }

    private void deleteDirectorySync(File dir) {
        try {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.getName();
                    if (!file.delete()) {
                        new StringBuilder("Failed to remove ").append(file.getAbsolutePath());
                    }
                }
            }
            if (!dir.delete()) {
                new StringBuilder("Failed to remove ").append(dir.getAbsolutePath());
            }
        } catch (Exception e) {
        }
    }
}
