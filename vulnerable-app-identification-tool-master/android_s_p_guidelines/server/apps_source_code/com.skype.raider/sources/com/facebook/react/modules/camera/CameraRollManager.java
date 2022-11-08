package com.facebook.react.modules.camera;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.text.TextUtils;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.j;
import com.facebook.react.bridge.n;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@ReactModule(name = "CameraRollManager")
public class CameraRollManager extends ReactContextBaseJavaModule {
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    public static final boolean IS_JELLY_BEAN_OR_LATER;
    protected static final String NAME = "CameraRollManager";
    private static final String[] PROJECTION;
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";

    private static class a extends j<Void, Void> {
        private final Context a;
        private final int b;
        @Nullable
        private final String c;
        @Nullable
        private final String d;
        @Nullable
        private final al e;
        private final ae f;
        @Nullable
        private final String g;

        /* synthetic */ a(ai x0, int x1, String x2, String x3, al x4, String x5, ae x6, byte b) {
            this(x0, x1, x2, x3, x4, x5, x6);
        }

        private a(ai context, int first, @Nullable String after, @Nullable String groupName, @Nullable al mimeTypes, @Nullable String assetType, ae promise) {
            super(context);
            this.a = context;
            this.b = first;
            this.c = after;
            this.d = groupName;
            this.e = mimeTypes;
            this.f = promise;
            this.g = assetType;
        }

        protected final /* synthetic */ void a() {
            StringBuilder stringBuilder = new StringBuilder("1");
            List arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.c)) {
                stringBuilder.append(" AND datetaken < ?");
                arrayList.add(this.c);
            }
            if (!TextUtils.isEmpty(this.d)) {
                stringBuilder.append(" AND bucket_display_name = ?");
                arrayList.add(this.d);
            }
            if (this.e != null && this.e.size() > 0) {
                stringBuilder.append(" AND mime_type IN (");
                for (int i = 0; i < this.e.size(); i++) {
                    stringBuilder.append("?,");
                    arrayList.add(this.e.getString(i));
                }
                stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ")");
            }
            Object writableNativeMap = new WritableNativeMap();
            ContentResolver contentResolver = this.a.getContentResolver();
            Cursor query;
            try {
                Uri uri = (this.g == null || !this.g.equals("Videos")) ? Media.EXTERNAL_CONTENT_URI : Video.Media.EXTERNAL_CONTENT_URI;
                query = contentResolver.query(uri, CameraRollManager.PROJECTION, stringBuilder.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), "datetaken DESC, date_modified DESC LIMIT " + (this.b + 1));
                if (query == null) {
                    this.f.a(CameraRollManager.ERROR_UNABLE_TO_LOAD, "Could not get photos");
                    return;
                }
                CameraRollManager.putEdges(contentResolver, query, writableNativeMap, this.b);
                CameraRollManager.putPageInfo(query, writableNativeMap, this.b);
                query.close();
                this.f.a(writableNativeMap);
            } catch (Throwable e) {
                this.f.a(CameraRollManager.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get photos: need READ_EXTERNAL_STORAGE permission", e);
            } catch (Throwable th) {
                query.close();
                this.f.a(writableNativeMap);
            }
        }
    }

    private static class b extends j<Void, Void> {
        private final Context a;
        private final Uri b;
        private final ae c;

        protected final /* synthetic */ void a() {
            FileChannel channel;
            Throwable e;
            Throwable th;
            FileChannel fileChannel = null;
            File file = new File(this.b.getPath());
            try {
                File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                externalStoragePublicDirectory.mkdirs();
                if (externalStoragePublicDirectory.isDirectory()) {
                    String substring;
                    String substring2;
                    int i;
                    File file2;
                    FileChannel channel2;
                    File file3 = new File(externalStoragePublicDirectory, file.getName());
                    String name = file.getName();
                    if (name.indexOf(46) >= 0) {
                        substring = name.substring(0, name.lastIndexOf(46));
                        substring2 = name.substring(name.lastIndexOf(46));
                        i = 0;
                        file2 = file3;
                    } else {
                        substring2 = "";
                        substring = name;
                        file2 = file3;
                        i = 0;
                    }
                    while (!file2.createNewFile()) {
                        StringBuilder append = new StringBuilder().append(substring).append("_");
                        int i2 = i + 1;
                        file3 = new File(externalStoragePublicDirectory, append.append(i).append(substring2).toString());
                        i = i2;
                        file2 = file3;
                    }
                    channel = new FileInputStream(file).getChannel();
                    try {
                        channel2 = new FileOutputStream(file2).getChannel();
                    } catch (IOException e2) {
                        e = e2;
                        fileChannel = channel;
                        channel = null;
                        try {
                            this.c.a(e);
                            try {
                                fileChannel.close();
                            } catch (Throwable e3) {
                                FLog.e("React", "Could not close input channel", e3);
                            }
                            if (channel != null) {
                                return;
                            }
                        } catch (Throwable th2) {
                            e3 = th2;
                            FileChannel fileChannel2 = channel;
                            channel = fileChannel;
                            fileChannel = fileChannel2;
                            try {
                                channel.close();
                            } catch (Throwable e4) {
                                FLog.e("React", "Could not close input channel", e4);
                            }
                            try {
                                fileChannel.close();
                            } catch (Throwable e42) {
                                FLog.e("React", "Could not close output channel", e42);
                            }
                            throw e3;
                        }
                    } catch (Throwable th3) {
                        e3 = th3;
                        channel.close();
                        fileChannel.close();
                        throw e3;
                    }
                    try {
                        channel2.transferFrom(channel, 0, channel.size());
                        channel.close();
                        channel2.close();
                        MediaScannerConnection.scanFile(this.a, new String[]{file2.getAbsolutePath()}, null, new OnScanCompletedListener(this) {
                            final /* synthetic */ b a;

                            {
                                this.a = this$0;
                            }

                            public final void onScanCompleted(String path, Uri uri) {
                                if (uri != null) {
                                    this.a.c.a(uri.toString());
                                } else {
                                    this.a.c.a(CameraRollManager.ERROR_UNABLE_TO_SAVE, "Could not add image to gallery");
                                }
                            }
                        });
                        if (channel != null && channel.isOpen()) {
                            try {
                                channel.close();
                            } catch (Throwable e422) {
                                FLog.e("React", "Could not close input channel", e422);
                            }
                        }
                        if (channel2 != null && channel2.isOpen()) {
                            try {
                                channel2.close();
                                return;
                            } catch (Throwable e32) {
                                FLog.e("React", "Could not close output channel", e32);
                                return;
                            }
                        }
                        return;
                    } catch (Throwable e5) {
                        th = e5;
                        fileChannel = channel;
                        channel = channel2;
                        e32 = th;
                        this.c.a(e32);
                        fileChannel.close();
                        if (channel != null) {
                        }
                        return;
                    } catch (Throwable e52) {
                        th = e52;
                        fileChannel = channel2;
                        e32 = th;
                        channel.close();
                        fileChannel.close();
                        throw e32;
                    }
                }
                this.c.a(CameraRollManager.ERROR_UNABLE_TO_LOAD, "External media storage directory not available");
            } catch (IOException e6) {
                e32 = e6;
                channel = null;
                this.c.a(e32);
                if (fileChannel != null && fileChannel.isOpen()) {
                    fileChannel.close();
                }
                if (channel != null && channel.isOpen()) {
                    try {
                        channel.close();
                    } catch (Throwable e322) {
                        FLog.e("React", "Could not close output channel", e322);
                    }
                }
            } catch (Throwable th4) {
                e322 = th4;
                channel = null;
                if (channel != null && channel.isOpen()) {
                    channel.close();
                }
                if (fileChannel != null && fileChannel.isOpen()) {
                    fileChannel.close();
                }
                throw e322;
            }
        }

        public b(ai context, Uri uri, ae promise) {
            super(context);
            this.a = context;
            this.b = uri;
            this.c = promise;
        }
    }

    static {
        boolean z = VERSION.SDK_INT >= 16;
        IS_JELLY_BEAN_OR_LATER = z;
        if (z) {
            PROJECTION = new String[]{"_id", "mime_type", "bucket_display_name", "datetaken", "width", "height", "longitude", "latitude"};
        } else {
            PROJECTION = new String[]{"_id", "mime_type", "bucket_display_name", "datetaken", "longitude", "latitude"};
        }
    }

    public CameraRollManager(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void saveToCameraRoll(String uri, String type, ae promise) {
        new b(getReactApplicationContext(), Uri.parse(uri), promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getPhotos(am params, ae promise) {
        String after;
        String groupName;
        String assetType;
        al mimeTypes;
        int first = params.getInt("first");
        if (params.hasKey("after")) {
            after = params.getString("after");
        } else {
            after = null;
        }
        if (params.hasKey("groupName")) {
            groupName = params.getString("groupName");
        } else {
            groupName = null;
        }
        if (params.hasKey("assetType")) {
            assetType = params.getString("assetType");
        } else {
            assetType = null;
        }
        if (params.hasKey("mimeTypes")) {
            mimeTypes = params.getArray("mimeTypes");
        } else {
            mimeTypes = null;
        }
        if (params.hasKey("groupTypes")) {
            throw new n("groupTypes is not supported on Android");
        }
        new a(getReactApplicationContext(), first, after, groupName, mimeTypes, assetType, promise, (byte) 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static void putPageInfo(Cursor photos, ar response, int limit) {
        ar pageInfo = new WritableNativeMap();
        pageInfo.putBoolean("has_next_page", limit < photos.getCount());
        if (limit < photos.getCount()) {
            photos.moveToPosition(limit - 1);
            pageInfo.putString("end_cursor", photos.getString(photos.getColumnIndex("datetaken")));
        }
        response.putMap("page_info", pageInfo);
    }

    private static void putEdges(ContentResolver resolver, Cursor photos, ar response, int limit) {
        int widthIndex;
        int heightIndex;
        aq edges = new WritableNativeArray();
        photos.moveToFirst();
        int idIndex = photos.getColumnIndex("_id");
        int mimeTypeIndex = photos.getColumnIndex("mime_type");
        int groupNameIndex = photos.getColumnIndex("bucket_display_name");
        int dateTakenIndex = photos.getColumnIndex("datetaken");
        if (IS_JELLY_BEAN_OR_LATER) {
            widthIndex = photos.getColumnIndex("width");
        } else {
            widthIndex = -1;
        }
        if (IS_JELLY_BEAN_OR_LATER) {
            heightIndex = photos.getColumnIndex("height");
        } else {
            heightIndex = -1;
        }
        int longitudeIndex = photos.getColumnIndex("longitude");
        int latitudeIndex = photos.getColumnIndex("latitude");
        int i = 0;
        while (i < limit && !photos.isAfterLast()) {
            ar edge = new WritableNativeMap();
            ar node = new WritableNativeMap();
            if (putImageInfo(resolver, photos, node, idIndex, widthIndex, heightIndex)) {
                putBasicNodeInfo(photos, node, mimeTypeIndex, groupNameIndex, dateTakenIndex);
                putLocationInfo(photos, node, longitudeIndex, latitudeIndex);
                edge.putMap("node", node);
                edges.pushMap(edge);
            } else {
                i--;
            }
            photos.moveToNext();
            i++;
        }
        response.putArray("edges", edges);
    }

    private static void putBasicNodeInfo(Cursor photos, ar node, int mimeTypeIndex, int groupNameIndex, int dateTakenIndex) {
        node.putString("type", photos.getString(mimeTypeIndex));
        node.putString("group_name", photos.getString(groupNameIndex));
        node.putDouble("timestamp", ((double) photos.getLong(dateTakenIndex)) / 1000.0d);
    }

    private static boolean putImageInfo(ContentResolver resolver, Cursor photos, ar node, int idIndex, int widthIndex, int heightIndex) {
        ar image = new WritableNativeMap();
        Uri photoUri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, photos.getString(idIndex));
        image.putString(ReactVideoViewManager.PROP_SRC_URI, photoUri.toString());
        float width = -1.0f;
        float height = 0.0f;
        if (IS_JELLY_BEAN_OR_LATER) {
            width = (float) photos.getInt(widthIndex);
            height = (float) photos.getInt(heightIndex);
        }
        if (width <= 0.0f || height <= 0.0f) {
            try {
                AssetFileDescriptor photoDescriptor = resolver.openAssetFileDescriptor(photoUri, "r");
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(photoDescriptor.getFileDescriptor(), null, options);
                photoDescriptor.close();
                width = (float) options.outWidth;
                height = (float) options.outHeight;
            } catch (Throwable e) {
                FLog.e("React", "Could not get width/height for " + photoUri.toString(), e);
                return false;
            }
        }
        image.putDouble("width", (double) width);
        image.putDouble("height", (double) height);
        node.putMap("image", image);
        return true;
    }

    private static void putLocationInfo(Cursor photos, ar node, int longitudeIndex, int latitudeIndex) {
        double longitude = photos.getDouble(longitudeIndex);
        double latitude = photos.getDouble(latitudeIndex);
        if (longitude > 0.0d || latitude > 0.0d) {
            ar location = new WritableNativeMap();
            location.putDouble("longitude", longitude);
            location.putDouble("latitude", latitude);
            node.putMap("location", location);
        }
    }
}
