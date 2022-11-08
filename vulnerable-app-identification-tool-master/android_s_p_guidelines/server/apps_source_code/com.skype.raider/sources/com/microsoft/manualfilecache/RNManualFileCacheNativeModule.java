package com.microsoft.manualfilecache;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Environment;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.internal.a;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RNManualFileCacheNativeModule extends ReactContextBaseJavaModule {
    private static final String CURRENT_CACHE_FOLDER_URI = "CurrentCacheFolderURI";
    public static final String DEFAULT_CACHE_NAME = "DefaultCacheName";
    private static final String PREFIX_FILE = "file:";
    private static final String RNManualFileCacheDefaultCacheName = "RNManualFileCache";
    private static final String TAG = "RNManualFileCache";

    RNManualFileCacheNativeModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "RNManualFileCache";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put(DEFAULT_CACHE_NAME, "RNManualFileCache");
        try {
            String cacheFolderPath = Uri.fromFile(createCacheFolder("RNManualFileCache")).toString();
            if (!cacheFolderPath.endsWith("/")) {
                cacheFolderPath = cacheFolderPath + "/";
            }
            constants.put(CURRENT_CACHE_FOLDER_URI, cacheFolderPath);
        } catch (Throwable e) {
            FLog.e("RNManualFileCache", "Failed to create cache folder", e);
        }
        return constants;
    }

    @SuppressLint({"NewApi"})
    @ReactMethod
    public void save(String cacheName, String sourcePath, String name, boolean asCopy, ae promise) {
        try {
            File destinationFile = new File(createCacheFolder(cacheName), name);
            Uri sourceUri = Uri.parse(sourcePath);
            if (destinationFile.exists()) {
                FLog.d("RNManualFileCache", "File already exists so skipping the save for " + name);
            } else if (!Objects.equals(sourceUri.getScheme(), "content")) {
                try {
                    File sourceFile = getFileFromPath(sourcePath);
                    if (asCopy) {
                        try {
                            copyFile(sourceFile, destinationFile);
                        } catch (Exception e) {
                            promise.a("save_failed", "Unable to copy file to destination " + destinationFile.getAbsolutePath(), e);
                            return;
                        }
                    } else if (!sourceFile.renameTo(destinationFile)) {
                        FLog.d("RNManualFileCache", "Failed to move " + sourceFile.getAbsolutePath() + " to " + destinationFile.getAbsolutePath());
                    }
                } catch (Exception e2) {
                    promise.a("invalid_source_path", "Cannot find file at " + sourcePath, e2);
                    return;
                }
            } else if (asCopy) {
                try {
                    copyFile(getReactApplicationContext().getContentResolver().openInputStream(sourceUri), new FileOutputStream(destinationFile));
                } catch (IOException e3) {
                    promise.a("save_failed", "Unable to copy file to destination " + destinationFile.getAbsolutePath(), e3);
                    return;
                }
            } else {
                promise.a("save_failed", "Move not supported for content:// scheme.");
                return;
            }
            promise.a(getNativeBlob(destinationFile));
        } catch (IOException e32) {
            promise.a("create_cache_folder_failed", "Failed to create the cache folder", e32);
        }
    }

    private ar getNativeBlob(File file) {
        String uri = Uri.fromFile(file).getPath();
        ar blob = new WritableNativeMap();
        blob.putString(ReactVideoViewManager.PROP_SRC_URI, uri);
        blob.putDouble("size", (double) file.length());
        return blob;
    }

    @ReactMethod
    public void remove(String cacheName, String name, ae promise) {
        try {
            File destinationFile = new File(createCacheFolder(cacheName), name);
            boolean existed = destinationFile.exists();
            if (!existed || destinationFile.delete()) {
                promise.a(Boolean.valueOf(existed));
                return;
            }
            FLog.d("RNManualFileCache", "Failed to delete " + name);
            promise.a("delete_failed", "Cannot delete file from cache", null);
        } catch (Throwable e) {
            FLog.e("RNManualFileCache", "Failed to remove " + name, e);
            promise.a("create_cache_folder_failed", "Failed to create the cache folder", e);
        }
    }

    @ReactMethod
    public void getAllCachedFileNames(String cacheName, ae promise) {
        try {
            File[] files = createCacheFolder(cacheName).listFiles();
            Object names = new WritableNativeArray();
            for (File name : files) {
                names.pushString(name.getName());
            }
            promise.a(names);
        } catch (IOException e) {
            promise.a("create_cache_folder_failed", "Failed to create the cache folder", e);
        }
    }

    @ReactMethod
    public void copyToDownloadFolder(String sourcePath, String fileName, ae promise) {
        try {
            File destinationFile = getDestinationFileInDownloadFolder(fileName);
            Uri sourceUri = Uri.parse(sourcePath);
            if (Objects.equals(sourceUri.getScheme(), "content")) {
                try {
                    copyFile(getReactApplicationContext().getContentResolver().openInputStream(sourceUri), new FileOutputStream(destinationFile));
                } catch (IOException e) {
                    promise.a("copy_downloads_failed", "Unable to copy file to destination " + destinationFile.getAbsolutePath(), e);
                    return;
                }
            }
            try {
                copyFile(getFileFromPath(sourcePath), destinationFile);
            } catch (Exception e2) {
                promise.a("copy_downloads_failed", "Cannot copy file to destination " + e2.toString(), e2);
                return;
            }
            promise.a(getNativeBlob(destinationFile));
        } catch (IOException e3) {
            promise.a("copy_failed", "Cannot get destination file for copy", e3);
        }
    }

    @ReactMethod
    public void copyToUri(String sourcePath, String targetUri, ae promise) {
        promise.a("copy_to_uri_failed", "Not supported on android");
    }

    @ReactMethod
    public void removeFromDownloadFolder(String filePath, ae promise) {
        try {
            File destinationFile = getFileFromPath(filePath);
            boolean existed = destinationFile.exists();
            if (!existed || destinationFile.delete()) {
                promise.a(Boolean.valueOf(existed));
                return;
            }
            FLog.d("RNManualFileCache", "Failed to delete " + filePath);
            promise.a("delete_failed", "Cannot delete file from public folder", null);
        } catch (Exception e) {
            promise.a("invalid_source_path", "Cannot find file at " + filePath, e);
        }
    }

    @ReactMethod
    public void evictFromImageCache(String cacheName, String name, ae promise) {
        promise.a("evict_from_image_cache", "Not implemented");
    }

    private File getDestinationFileInDownloadFolder(String fileName) throws IOException {
        File outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File temp = new File(outputDir, fileName);
        if (temp.exists()) {
            int dotIndex = fileName.lastIndexOf(46);
            String baseFileName = dotIndex == -1 ? fileName : fileName.substring(0, dotIndex);
            String dotExtension = dotIndex == -1 ? "" : fileName.substring(dotIndex);
            int i = 1;
            while (temp.exists()) {
                temp = new File(outputDir, baseFileName + "-" + i + dotExtension);
                i++;
            }
        }
        return temp;
    }

    private void copyFile(File sourceFile, File destinationFile) throws IOException {
        copyFile(new FileInputStream(sourceFile), new FileOutputStream(destinationFile));
    }

    private void copyFile(InputStream input, OutputStream output) throws IOException {
        try {
            a.a(input, output);
            output.flush();
        } finally {
            closeQuietly(input);
            closeQuietly(output);
        }
    }

    private File getFileFromPath(String sourcePath) throws Exception {
        try {
            return stringToFile(sourcePath);
        } catch (Exception e) {
            FLog.d("RNManualFileCache", "Failed to open source file " + sourcePath + ": " + e);
            throw e;
        }
    }

    private File createCacheFolder(String cacheName) throws IOException {
        File cacheDir = new File(getReactApplicationContext().getCacheDir(), cacheName);
        if (!cacheDir.isDirectory()) {
            if (cacheDir.exists() && !cacheDir.delete()) {
                throw new IOException("Unable to delete existing file with same name as cache dir " + cacheName);
            } else if (!cacheDir.mkdir()) {
                throw new IOException("Unable to create cache dir with name " + cacheName);
            }
        }
        return cacheDir;
    }

    private IOException closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                return e;
            }
        }
        return null;
    }

    private File stringToFile(String string) throws URISyntaxException {
        if (string.startsWith(PREFIX_FILE)) {
            return new File(Uri.parse(string).getPath());
        }
        return new File(string);
    }
}
