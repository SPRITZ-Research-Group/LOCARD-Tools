package com.skype.sharetoapp;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.facebook.common.logging.FLog;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

public class LocalFileResolver {
    private static final Uri a = Uri.parse("content://downloads/public_downloads");
    private static final String[] b = new String[]{"_data"};

    public static String a(Context context, Uri uri) {
        String resultPath;
        Throwable e;
        Throwable th;
        InputStream inputStream = null;
        BufferedOutputStream outStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            if (inputStream == null && uri.getPath().startsWith("http")) {
                inputStream = new URL(uri.getPath()).openStream();
            }
            File outDir = a(context);
            if (inputStream == null || !(outDir.isDirectory() || outDir.mkdirs())) {
                resultPath = null;
            } else {
                String baseName = UUID.randomUUID().toString();
                if (!TextUtils.isEmpty(null)) {
                    String str = null;
                    str.indexOf(42);
                }
                String str2 = "webp";
                String mimeType = context.getContentResolver().getType(uri);
                if (mimeType.equals(MimeTypeMap.getSingleton().getMimeTypeFromExtension("jpeg")) && a(uri, str2)) {
                    mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str2);
                }
                if (mimeType != null) {
                    String ext = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
                    if (ext != null) {
                        baseName = baseName + "." + ext;
                    }
                }
                File outFile = new File(outDir, baseName);
                BufferedOutputStream outStream2 = new BufferedOutputStream(new FileOutputStream(outFile));
                try {
                    byte[] buf = new byte[2048];
                    while (true) {
                        int len = inputStream.read(buf);
                        if (len <= 0) {
                            break;
                        }
                        outStream2.write(buf, 0, len);
                    }
                    resultPath = outFile.getAbsolutePath();
                    outStream = outStream2;
                } catch (Exception e2) {
                    e = e2;
                    outStream = outStream2;
                    try {
                        FLog.i("LocalFileResolver", "Failed to copy file to Shared folder", e);
                        resultPath = null;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable e3) {
                                FLog.i("LocalFileResolver", "Failed to close inputStream", e3);
                            }
                        }
                        if (outStream != null) {
                            try {
                                outStream.close();
                            } catch (Throwable e32) {
                                FLog.i("LocalFileResolver", "Failed to close outStream", e32);
                            }
                        }
                        return resultPath;
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable e322) {
                                FLog.i("LocalFileResolver", "Failed to close inputStream", e322);
                            }
                        }
                        if (outStream != null) {
                            try {
                                outStream.close();
                            } catch (Throwable e3222) {
                                FLog.i("LocalFileResolver", "Failed to close outStream", e3222);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outStream = outStream2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outStream != null) {
                        outStream.close();
                    }
                    throw th;
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e32222) {
                    FLog.i("LocalFileResolver", "Failed to close inputStream", e32222);
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (Throwable e322222) {
                    FLog.i("LocalFileResolver", "Failed to close outStream", e322222);
                }
            }
        } catch (Exception e4) {
            e322222 = e4;
            FLog.i("LocalFileResolver", "Failed to copy file to Shared folder", e322222);
            resultPath = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
            return resultPath;
        }
        return resultPath;
    }

    public static String a(Context context, Bitmap bitmap) {
        Throwable e;
        Throwable th;
        File outDir = a(context);
        if (!outDir.isDirectory() && !outDir.mkdirs()) {
            return null;
        }
        File outFile = new File(outDir, UUID.randomUUID().toString() + ".jpg");
        String filePath = outFile.getAbsolutePath();
        BufferedOutputStream out = null;
        try {
            BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(outFile));
            try {
                bitmap.compress(CompressFormat.JPEG, 100, out2);
                try {
                    out2.close();
                    out = out2;
                    return filePath;
                } catch (Throwable e2) {
                    FLog.i("LocalFileResolver", "Failed to close outStream", e2);
                    out = out2;
                    return filePath;
                }
            } catch (Exception e3) {
                e2 = e3;
                out = out2;
                try {
                    FLog.i("LocalFileResolver", "Failed to save a bitmap", e2);
                    if (out != null) {
                        return null;
                    }
                    try {
                        out.close();
                        return null;
                    } catch (Throwable e22) {
                        FLog.i("LocalFileResolver", "Failed to close outStream", e22);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (out != null) {
                        try {
                            out.close();
                        } catch (Throwable e222) {
                            FLog.i("LocalFileResolver", "Failed to close outStream", e222);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                out = out2;
                if (out != null) {
                    out.close();
                }
                throw th;
            }
        } catch (Exception e4) {
            e222 = e4;
            FLog.i("LocalFileResolver", "Failed to save a bitmap", e222);
            if (out != null) {
                return null;
            }
            out.close();
            return null;
        }
    }

    @Nullable
    public static String b(Context context, Uri uri) {
        String scheme = uri.getScheme();
        if (DocumentsContract.isDocumentUri(context, uri)) {
            return c(context, uri);
        }
        if ("content".equalsIgnoreCase(scheme)) {
            return a(context, uri, null, null);
        }
        if ("file".equalsIgnoreCase(scheme)) {
            return uri.getPath();
        }
        return null;
    }

    private static String c(Context context, Uri uri) {
        String path = null;
        String authority = uri.getAuthority();
        String[] parts;
        if ("com.android.externalstorage.documents".equals(authority)) {
            parts = DocumentsContract.getDocumentId(uri).split(":");
            if (parts.length >= 2) {
                String partition = parts[0];
                String filename = parts[1];
                if ("primary".equalsIgnoreCase(partition)) {
                    path = Environment.getExternalStorageDirectory() + "/" + filename;
                }
            }
        } else if ("com.android.providers.downloads.documents".equals(authority)) {
            String id = DocumentsContract.getDocumentId(uri);
            try {
                path = a(context, ContentUris.withAppendedId(a, Long.parseLong(id)), null, null);
            } catch (Throwable e) {
                if (id.startsWith("raw:")) {
                    return id.replaceFirst("raw:", "");
                }
                FLog.i("LocalFileResolver", "Failed to parse documentId to long", e);
                return null;
            }
        } else if ("com.android.providers.media.documents".equals(authority)) {
            parts = DocumentsContract.getDocumentId(uri).split(":");
            if (parts.length >= 2) {
                String contentType = parts[0];
                String contentId = parts[1];
                Uri contentUri = null;
                if ("image".equals(contentType)) {
                    contentUri = Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(contentType)) {
                    contentUri = Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(contentType)) {
                    contentUri = Audio.Media.EXTERNAL_CONTENT_URI;
                }
                path = a(context, contentUri, "_id=?", new String[]{contentId});
            }
        }
        return path;
    }

    @NonNull
    private static File a(Context context) {
        return new File(context.getExternalCacheDir(), "Shared");
    }

    private static String a(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, b, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(b[0]);
                if (columnIndex < 0) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                String data = cursor.getString(columnIndex);
                if (data != null && new File(data).exists()) {
                    if (cursor == null) {
                        return data;
                    }
                    cursor.close();
                    return data;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable e) {
            FLog.i("LocalFileResolver", "Failed to resolve uri", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    public static boolean a(Uri uri, String extension) {
        for (String endsWith : uri.getPathSegments()) {
            if (endsWith.endsWith("." + extension)) {
                return true;
            }
        }
        return false;
    }
}
