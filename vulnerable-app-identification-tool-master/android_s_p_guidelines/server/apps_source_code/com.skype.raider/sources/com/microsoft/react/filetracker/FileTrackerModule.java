package com.microsoft.react.filetracker;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileTrackerModule extends ReactContextBaseJavaModule {
    private static final String FILE_NAME = "fileName";
    private static final String FILE_SIZE_IN_BYTES = "fileSizeInBytes";
    private static final String FILE_URI = "fileUri";
    private static final String LAST_UPDATED_TIME = "lastUpdatedTime";
    private static final String MODULE_NAME = "FileTracker";
    private static final String TAG = "RNFileTrackerModule";
    private Context context;

    public FileTrackerModule(ag reactContext) {
        super(reactContext);
        this.context = reactContext.getApplicationContext();
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public static void fileExists(am options, ae promise) {
        if (options == null) {
            promise.a("OPTIONS_NULL", "File cannot be tracked, options not provided");
            return;
        }
        String fileUri = null;
        if (options.hasKey(FILE_URI)) {
            fileUri = options.getString(FILE_URI);
        }
        if (fileUri == null || fileUri.isEmpty()) {
            promise.a("FILE_URI_EMPTY", "File cannot be tracked, file uri empty");
            return;
        }
        double fileSize = Double.MIN_VALUE;
        if (options.hasKey(FILE_SIZE_IN_BYTES)) {
            fileSize = options.getDouble(FILE_SIZE_IN_BYTES);
        }
        double timeStamp = Double.MIN_VALUE;
        if (options.hasKey(LAST_UPDATED_TIME)) {
            timeStamp = options.getDouble(LAST_UPDATED_TIME);
        }
        File file = new File(fileUri);
        if (!file.exists()) {
            promise.a(Boolean.valueOf(false));
        } else if (fileSize == Double.MIN_VALUE && timeStamp == Double.MIN_VALUE) {
            promise.a(Boolean.valueOf(true));
        } else if (fileSize == Double.MIN_VALUE) {
            if (((double) file.lastModified()) == timeStamp) {
                promise.a(Boolean.valueOf(true));
            } else {
                promise.a(Boolean.valueOf(false));
            }
        } else if (timeStamp == Double.MIN_VALUE) {
            if (((double) file.length()) == fileSize) {
                promise.a(Boolean.valueOf(true));
            } else {
                promise.a(Boolean.valueOf(false));
            }
        } else if (((double) file.length()) == fileSize && ((double) file.lastModified()) == timeStamp) {
            promise.a(Boolean.valueOf(true));
        } else {
            promise.a(Boolean.valueOf(false));
        }
    }

    @ReactMethod
    public void getFileInformation(String fileUri, ae promise) {
        if (fileUri == null || fileUri.isEmpty()) {
            promise.a("FILE_URI_EMPTY", "File cannot be tracked, file uri empty");
            return;
        }
        File file = new File(fileUri);
        Object map = new WritableNativeMap();
        if (file.exists()) {
            map.putDouble(FILE_SIZE_IN_BYTES, (double) file.length());
            map.putDouble(LAST_UPDATED_TIME, (double) file.lastModified());
            promise.a(map);
            return;
        }
        promise.a("FILE_DOES_NOT_EXISTS", "file doesn't exist at location");
    }

    @ReactMethod
    public void addMediaToSkypeAlbum(String fileUri, ae promise) {
        if (TextUtils.isEmpty(fileUri)) {
            promise.a("FILE_URI_EMPTY", "File cannot be tracked, file uri empty");
            return;
        }
        String realPath = getRealPathFromURI(Uri.parse(fileUri));
        File inputFile = new File(realPath);
        File outputFile = new File(getPicturesFolder() + File.separator + getFileName(realPath));
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            outputFile.createNewFile();
            inChannel = new FileInputStream(inputFile).getChannel();
            outChannel = new FileOutputStream(outputFile).getChannel();
            if (inChannel == null || outChannel == null) {
                FLog.i(TAG, "movePhotoToSkypePicturesFolder: null channels");
                promise.a("CANNOT_MOVE_FILE", "Channels are empty");
            } else {
                try {
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                    inChannel.close();
                    outChannel.close();
                    inputFile.delete();
                    MediaScannerConnection.scanFile(this.context, new String[]{inputFile.getPath()}, null, null);
                    MediaScannerConnection.scanFile(this.context, new String[]{outputFile.getPath()}, new String[]{getMimeType(outputFile.getPath())}, null);
                    promise.a(outputFile.getAbsolutePath());
                } catch (IOException ex) {
                    FLog.i(TAG, "movePhotoToSkypePicturesFolder: transferTo failed " + ex.getMessage());
                    promise.a("CANNOT_MOVE_FILE", "Cannot transfer file " + ex.getMessage());
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException ex2) {
                    FLog.i(TAG, "movePhotoToSkypePicturesFolder: failed to close channels " + ex2.getMessage());
                    return;
                }
            }
            if (outChannel != null) {
                outChannel.close();
            }
        } catch (IOException ex22) {
            FLog.i(TAG, "movePhotoToSkypePicturesFolder: Source or destination file error " + ex22.getMessage());
            promise.a("EMPTY_FILE", "Source or destination file error " + ex22.getMessage());
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException ex222) {
                    FLog.i(TAG, "movePhotoToSkypePicturesFolder: failed to close channels " + ex222.getMessage());
                    return;
                }
            }
            if (outChannel != null) {
                outChannel.close();
            }
        } catch (Throwable th) {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException ex2222) {
                    FLog.i(TAG, "movePhotoToSkypePicturesFolder: failed to close channels " + ex2222.getMessage());
                }
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public String getRealPathFromURI(Uri contentUri) {
        if (new File(contentUri.getPath()).exists()) {
            return contentUri.getPath();
        }
        Cursor cursor = null;
        try {
            cursor = this.context.getContentResolver().query(contentUri, new String[]{"_data"}, null, null, null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                FLog.i(TAG, "getRealPathFromURI: not found in cursor");
                return "";
            }
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            if (cursor == null) {
                return string;
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private File getPicturesFolder() {
        File outputDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Skype");
        if (!outputDir.isDirectory()) {
            outputDir.mkdirs();
        }
        return outputDir;
    }

    private String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        return new File(filePath).getName();
    }

    private String getMimeType(String url) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return null;
    }
}
