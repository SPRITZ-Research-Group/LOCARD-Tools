package com.microsoft.react.mediapicker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Media;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.microsoft.react.a.c;
import com.microsoft.react.a.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MediaPickerModule extends ReactContextBaseJavaModule {
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String FILE_URI_PREFIX = "file://";
    private static final String RECENT_THUMBNAIL_PATH_KEY = "thumbnail";
    private static final String RECENT_THUMBNAIL_TIMESTAMP_KEY = "timestamp";
    private static final String RN_CLASS = "MediaPickerModule";
    private static final Map<Integer, Map<String, List<c>>> mediaFilesCache = new HashMap();
    private static int nextToken = 0;
    private ContentObserver recentAssetChangedObserver;
    private String recentlyNotifiedAssetThumbnailPath;

    private class a {
        String a;
        String b;
        Long c;
        String d;
        Integer e;
        final /* synthetic */ MediaPickerModule f;

        private a(MediaPickerModule mediaPickerModule) {
            this.f = mediaPickerModule;
        }

        /* synthetic */ a(MediaPickerModule x0, byte b) {
            this(x0);
        }
    }

    public MediaPickerModule(ag reactContext) {
        super(reactContext);
    }

    public void onCatalystInstanceDestroy() {
        unregisterFromMediaStoreUpdates();
    }

    public String getName() {
        return "MediaPicker";
    }

    @ReactMethod
    public void open(ae promise) {
        nextToken++;
        FLog.i(RN_CLASS, String.format("open token %d", new Object[]{Integer.valueOf(nextToken)}));
        mediaFilesCache.put(Integer.valueOf(nextToken), new HashMap());
        promise.a(Integer.valueOf(nextToken));
    }

    @ReactMethod
    public void close(int token) {
        FLog.i(RN_CLASS, String.format("close token %d", new Object[]{Integer.valueOf(token)}));
        mediaFilesCache.remove(Integer.valueOf(token));
    }

    @ReactMethod
    public void getPhotos(int token, am params, ae promise) {
        if (mediaFilesCache.get(Integer.valueOf(token)) == null) {
            promise.a(new Throwable("getPhotos token not found"));
            return;
        }
        boolean photoOnly;
        boolean disableGifs;
        String albumName;
        List<c> mediaFiles;
        int last;
        List<c> mediaFilesPage;
        boolean z;
        int first = params.getInt("first");
        int count = params.getInt("count");
        if (params.hasKey("photoOnly")) {
            if (params.getBoolean("photoOnly")) {
                photoOnly = true;
                if (params.hasKey("disableGifs")) {
                    if (params.getBoolean("disableGifs")) {
                        disableGifs = true;
                        albumName = params.hasKey("groupName") ? params.getString("groupName") : "";
                        if (albumName.equals("All Photos")) {
                            albumName = "";
                        }
                        FLog.i(RN_CLASS, String.format("getPhotos, token: %d, startingIndex: %d, count: %d, photoOnly: %b, disableGifs: %b, albumName: %s", new Object[]{Integer.valueOf(token), Integer.valueOf(first), Integer.valueOf(count), Boolean.valueOf(photoOnly), Boolean.valueOf(disableGifs), albumName}));
                        if (mediaFilesCache.get(Integer.valueOf(token)) != null || ((Map) mediaFilesCache.get(Integer.valueOf(token))).get(albumName) == null) {
                            mediaFiles = e.a(getReactApplicationContext(), photoOnly, disableGifs, albumName);
                            ((Map) mediaFilesCache.get(Integer.valueOf(token))).put(albumName, mediaFiles);
                        } else {
                            mediaFiles = (List) ((Map) mediaFilesCache.get(Integer.valueOf(token))).get(albumName);
                        }
                        last = Math.min(first + count, mediaFiles.size());
                        mediaFilesPage = mediaFiles.subList(first, last);
                        if (last >= mediaFiles.size() - 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        resolveGetPhotos(promise, mediaFilesPage, z);
                    }
                }
                disableGifs = false;
                if (params.hasKey("groupName")) {
                }
                if (albumName.equals("All Photos")) {
                    albumName = "";
                }
                FLog.i(RN_CLASS, String.format("getPhotos, token: %d, startingIndex: %d, count: %d, photoOnly: %b, disableGifs: %b, albumName: %s", new Object[]{Integer.valueOf(token), Integer.valueOf(first), Integer.valueOf(count), Boolean.valueOf(photoOnly), Boolean.valueOf(disableGifs), albumName}));
                if (mediaFilesCache.get(Integer.valueOf(token)) != null) {
                }
                if (photoOnly) {
                }
                mediaFiles = e.a(getReactApplicationContext(), photoOnly, disableGifs, albumName);
                ((Map) mediaFilesCache.get(Integer.valueOf(token))).put(albumName, mediaFiles);
                last = Math.min(first + count, mediaFiles.size());
                mediaFilesPage = mediaFiles.subList(first, last);
                if (last >= mediaFiles.size() - 1) {
                    z = false;
                } else {
                    z = true;
                }
                resolveGetPhotos(promise, mediaFilesPage, z);
            }
        }
        photoOnly = false;
        if (params.hasKey("disableGifs")) {
            if (params.getBoolean("disableGifs")) {
                disableGifs = true;
                if (params.hasKey("groupName")) {
                }
                if (albumName.equals("All Photos")) {
                    albumName = "";
                }
                FLog.i(RN_CLASS, String.format("getPhotos, token: %d, startingIndex: %d, count: %d, photoOnly: %b, disableGifs: %b, albumName: %s", new Object[]{Integer.valueOf(token), Integer.valueOf(first), Integer.valueOf(count), Boolean.valueOf(photoOnly), Boolean.valueOf(disableGifs), albumName}));
                if (mediaFilesCache.get(Integer.valueOf(token)) != null) {
                }
                if (photoOnly) {
                }
                mediaFiles = e.a(getReactApplicationContext(), photoOnly, disableGifs, albumName);
                ((Map) mediaFilesCache.get(Integer.valueOf(token))).put(albumName, mediaFiles);
                last = Math.min(first + count, mediaFiles.size());
                mediaFilesPage = mediaFiles.subList(first, last);
                if (last >= mediaFiles.size() - 1) {
                    z = true;
                } else {
                    z = false;
                }
                resolveGetPhotos(promise, mediaFilesPage, z);
            }
        }
        disableGifs = false;
        if (params.hasKey("groupName")) {
        }
        if (albumName.equals("All Photos")) {
            albumName = "";
        }
        FLog.i(RN_CLASS, String.format("getPhotos, token: %d, startingIndex: %d, count: %d, photoOnly: %b, disableGifs: %b, albumName: %s", new Object[]{Integer.valueOf(token), Integer.valueOf(first), Integer.valueOf(count), Boolean.valueOf(photoOnly), Boolean.valueOf(disableGifs), albumName}));
        if (mediaFilesCache.get(Integer.valueOf(token)) != null) {
        }
        if (photoOnly) {
        }
        mediaFiles = e.a(getReactApplicationContext(), photoOnly, disableGifs, albumName);
        ((Map) mediaFilesCache.get(Integer.valueOf(token))).put(albumName, mediaFiles);
        last = Math.min(first + count, mediaFiles.size());
        mediaFilesPage = mediaFiles.subList(first, last);
        if (last >= mediaFiles.size() - 1) {
            z = false;
        } else {
            z = true;
        }
        resolveGetPhotos(promise, mediaFilesPage, z);
    }

    @ReactMethod
    public void getRecentAssetThumbnail(ae promise) {
        try {
            Object data = getRecentAssetThumbnailData();
            if (data != null) {
                promise.a(data);
            }
        } catch (SecurityException e) {
            promise.a(ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get recent photo: need READ_EXTERNAL_STORAGE permission", e);
        }
    }

    private ar getRecentAssetThumbnailData() {
        ar result = new WritableNativeMap();
        List<c> mediaFiles = e.a(getReactApplicationContext(), true, false, "", 1, true);
        if (mediaFiles.size() > 0 && ((c) mediaFiles.get(0)).b != null) {
            c firstMediaFile = (c) mediaFiles.get(0);
            result.putString(RECENT_THUMBNAIL_PATH_KEY, firstMediaFile.b.a.toString());
            result.putDouble(RECENT_THUMBNAIL_TIMESTAMP_KEY, (double) firstMediaFile.a.f);
            return result;
        } else if (mediaFiles.size() != 0) {
            return null;
        } else {
            result.putString(RECENT_THUMBNAIL_PATH_KEY, "");
            result.putDouble(RECENT_THUMBNAIL_TIMESTAMP_KEY, 0.0d);
            return result;
        }
    }

    @ReactMethod
    public void setRecentAssetThumbnailChangedEventEnabled(boolean enabled) {
        if (enabled) {
            registerForMediaStoreUpdates();
        } else {
            unregisterFromMediaStoreUpdates();
        }
    }

    private void registerForMediaStoreUpdates() {
        if (this.recentAssetChangedObserver == null) {
            this.recentAssetChangedObserver = new ContentObserver(this, new Handler()) {
                final /* synthetic */ MediaPickerModule a;

                public final void onChange(boolean selfChange) {
                    super.onChange(selfChange);
                    try {
                        ar data = this.a.getRecentAssetThumbnailData();
                        if (data != null) {
                            String thumbnail = data.getString(MediaPickerModule.RECENT_THUMBNAIL_PATH_KEY);
                            if (!thumbnail.equals(this.a.recentlyNotifiedAssetThumbnailPath)) {
                                ((RCTDeviceEventEmitter) this.a.getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(MediaPickerViewManager.RECENT_ASSET_THUMBNAIL_CHANGED_EVENT_NAME, data);
                                this.a.recentlyNotifiedAssetThumbnailPath = thumbnail;
                            }
                        }
                    } catch (SecurityException e) {
                        FLog.i(MediaPickerModule.RN_CLASS, "Can't access recent thumbnail data");
                    }
                }
            };
            Context context = getReactApplicationContext();
            context.getContentResolver().registerContentObserver(Media.INTERNAL_CONTENT_URI, true, this.recentAssetChangedObserver);
            context.getContentResolver().registerContentObserver(Media.EXTERNAL_CONTENT_URI, true, this.recentAssetChangedObserver);
            context.getContentResolver().registerContentObserver(Images.Media.INTERNAL_CONTENT_URI, true, this.recentAssetChangedObserver);
            context.getContentResolver().registerContentObserver(Images.Media.EXTERNAL_CONTENT_URI, true, this.recentAssetChangedObserver);
        }
    }

    private void unregisterFromMediaStoreUpdates() {
        Context context = getReactApplicationContext();
        if (this.recentAssetChangedObserver != null) {
            context.getContentResolver().unregisterContentObserver(this.recentAssetChangedObserver);
        }
    }

    @ReactMethod
    public void selectPhoto(int token, String uri, ae promise) {
        Map<String, List<c>> cachedMediaFiles = (Map) mediaFilesCache.get(Integer.valueOf(token));
        if (cachedMediaFiles == null) {
            promise.a(new Throwable("selectPhoto token not found"));
            return;
        }
        for (Entry value : cachedMediaFiles.entrySet()) {
            for (c mediaFile : (List) value.getValue()) {
                if (mediaFile.a.a.toString().equalsIgnoreCase(uri)) {
                    resolveSelectPhoto(promise, mediaFile);
                    return;
                }
            }
        }
        promise.a(new Throwable("selectPhoto media not found " + uri));
    }

    private void resolveSelectPhoto(ae promise, c mediaFile) {
        promise.a(e.a(getReactApplicationContext(), mediaFile, true));
    }

    private void resolveGetPhotos(ae promise, Collection<c> mediaFiles, boolean hasNextPage) {
        Object result = new WritableNativeMap();
        ar pageInfo = new WritableNativeMap();
        pageInfo.putBoolean("has_next_page", hasNextPage);
        if (mediaFiles.isEmpty()) {
            result.putArray("edges", new WritableNativeArray());
        } else {
            Context reactApplicationContext = getReactApplicationContext();
            aq edges = new WritableNativeArray();
            for (c a : mediaFiles) {
                edges.pushMap(e.a(reactApplicationContext, a, false));
            }
            result.putArray("edges", edges);
        }
        result.putMap("page_info", pageInfo);
        promise.a(result);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @ReactMethod
    public void getAlbumInfo(int token, am params, ae promise) {
        if (mediaFilesCache.get(Integer.valueOf(token)) == null) {
            promise.a(new Throwable("getAlbumInfo token not found"));
            return;
        }
        boolean photoOnly;
        boolean disableGifs;
        Uri uri;
        String[] projection;
        String selection;
        String sortOrder;
        ContentResolver contentResolver;
        List<a> buckets;
        Cursor cursor;
        int bucketIdColumnIndex;
        int bucketDisplayNemaColumnIndex;
        int mediaIdColumnIndex;
        int countColumnIndex;
        MediaPickerModule mediaPickerModule;
        a bucket;
        aq result;
        ar map;
        if (params.hasKey("photoOnly")) {
            if (params.getBoolean("photoOnly")) {
                photoOnly = true;
                if (params.hasKey("disableGifs")) {
                    if (params.getBoolean("disableGifs")) {
                        disableGifs = true;
                        FLog.i(RN_CLASS, String.format("getAlbumInfo for token %d", new Object[]{Integer.valueOf(token)}));
                        uri = Files.getContentUri("external");
                        projection = new String[]{"max(_id) as latest", "bucket_id", "bucket_display_name", "count(*) as totalCount"};
                        selection = "(media_type=1";
                        if (!photoOnly) {
                            selection = selection + " OR media_type=3";
                        }
                        selection = selection + ")";
                        if (disableGifs) {
                            selection = selection + " AND mime_type!='image/gif'";
                        }
                        selection = selection + ") GROUP BY bucket_id, (bucket_display_name";
                        sortOrder = "bucket_display_name ASC";
                        contentResolver = getReactApplicationContext().getContentResolver();
                        buckets = new ArrayList();
                        cursor = null;
                        cursor = contentResolver.query(uri, projection, selection, null, sortOrder);
                        bucketIdColumnIndex = cursor.getColumnIndex("bucket_id");
                        bucketDisplayNemaColumnIndex = cursor.getColumnIndex("bucket_display_name");
                        mediaIdColumnIndex = cursor.getColumnIndex("latest");
                        countColumnIndex = cursor.getColumnIndex("totalCount");
                        while (cursor.moveToNext()) {
                            mediaPickerModule = this;
                            bucket = new a();
                            bucket.a = cursor.getString(bucketIdColumnIndex);
                            bucket.b = cursor.getString(bucketDisplayNemaColumnIndex);
                            bucket.c = Long.valueOf(cursor.getLong(mediaIdColumnIndex));
                            bucket.e = Integer.valueOf((int) cursor.getLong(countColumnIndex));
                            buckets.add(bucket);
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        populateBucketsMediaUrl(buckets);
                        addCompositeBucket(buckets);
                        result = new WritableNativeArray();
                        for (a bucket2 : buckets) {
                            map = new WritableNativeMap();
                            map.putString("name", bucket2.b);
                            map.putInt("count", bucket2.e.intValue());
                            if (bucket2.d == null) {
                                map.putString("thumbnailUri", bucket2.d.replaceAll(" ", "%20"));
                            }
                            result.pushMap(map);
                        }
                        promise.a((Object) result);
                    }
                }
                disableGifs = false;
                FLog.i(RN_CLASS, String.format("getAlbumInfo for token %d", new Object[]{Integer.valueOf(token)}));
                uri = Files.getContentUri("external");
                projection = new String[]{"max(_id) as latest", "bucket_id", "bucket_display_name", "count(*) as totalCount"};
                selection = "(media_type=1";
                if (photoOnly) {
                    selection = selection + " OR media_type=3";
                }
                selection = selection + ")";
                if (disableGifs) {
                    selection = selection + " AND mime_type!='image/gif'";
                }
                selection = selection + ") GROUP BY bucket_id, (bucket_display_name";
                sortOrder = "bucket_display_name ASC";
                contentResolver = getReactApplicationContext().getContentResolver();
                buckets = new ArrayList();
                cursor = null;
                cursor = contentResolver.query(uri, projection, selection, null, sortOrder);
                bucketIdColumnIndex = cursor.getColumnIndex("bucket_id");
                bucketDisplayNemaColumnIndex = cursor.getColumnIndex("bucket_display_name");
                mediaIdColumnIndex = cursor.getColumnIndex("latest");
                countColumnIndex = cursor.getColumnIndex("totalCount");
                while (cursor.moveToNext()) {
                    mediaPickerModule = this;
                    bucket2 = new a();
                    bucket2.a = cursor.getString(bucketIdColumnIndex);
                    bucket2.b = cursor.getString(bucketDisplayNemaColumnIndex);
                    bucket2.c = Long.valueOf(cursor.getLong(mediaIdColumnIndex));
                    bucket2.e = Integer.valueOf((int) cursor.getLong(countColumnIndex));
                    buckets.add(bucket2);
                }
                if (cursor != null) {
                    cursor.close();
                }
                populateBucketsMediaUrl(buckets);
                addCompositeBucket(buckets);
                result = new WritableNativeArray();
                for (a bucket22 : buckets) {
                    map = new WritableNativeMap();
                    map.putString("name", bucket22.b);
                    map.putInt("count", bucket22.e.intValue());
                    if (bucket22.d == null) {
                        map.putString("thumbnailUri", bucket22.d.replaceAll(" ", "%20"));
                    }
                    result.pushMap(map);
                }
                promise.a((Object) result);
            }
        }
        photoOnly = false;
        if (params.hasKey("disableGifs")) {
            if (params.getBoolean("disableGifs")) {
                disableGifs = true;
                FLog.i(RN_CLASS, String.format("getAlbumInfo for token %d", new Object[]{Integer.valueOf(token)}));
                uri = Files.getContentUri("external");
                projection = new String[]{"max(_id) as latest", "bucket_id", "bucket_display_name", "count(*) as totalCount"};
                selection = "(media_type=1";
                if (photoOnly) {
                    selection = selection + " OR media_type=3";
                }
                selection = selection + ")";
                if (disableGifs) {
                    selection = selection + " AND mime_type!='image/gif'";
                }
                selection = selection + ") GROUP BY bucket_id, (bucket_display_name";
                sortOrder = "bucket_display_name ASC";
                contentResolver = getReactApplicationContext().getContentResolver();
                buckets = new ArrayList();
                cursor = null;
                cursor = contentResolver.query(uri, projection, selection, null, sortOrder);
                bucketIdColumnIndex = cursor.getColumnIndex("bucket_id");
                bucketDisplayNemaColumnIndex = cursor.getColumnIndex("bucket_display_name");
                mediaIdColumnIndex = cursor.getColumnIndex("latest");
                countColumnIndex = cursor.getColumnIndex("totalCount");
                while (cursor.moveToNext()) {
                    mediaPickerModule = this;
                    bucket22 = new a();
                    bucket22.a = cursor.getString(bucketIdColumnIndex);
                    bucket22.b = cursor.getString(bucketDisplayNemaColumnIndex);
                    bucket22.c = Long.valueOf(cursor.getLong(mediaIdColumnIndex));
                    bucket22.e = Integer.valueOf((int) cursor.getLong(countColumnIndex));
                    buckets.add(bucket22);
                }
                if (cursor != null) {
                    cursor.close();
                }
                populateBucketsMediaUrl(buckets);
                addCompositeBucket(buckets);
                result = new WritableNativeArray();
                for (a bucket222 : buckets) {
                    map = new WritableNativeMap();
                    map.putString("name", bucket222.b);
                    map.putInt("count", bucket222.e.intValue());
                    if (bucket222.d == null) {
                        map.putString("thumbnailUri", bucket222.d.replaceAll(" ", "%20"));
                    }
                    result.pushMap(map);
                }
                promise.a((Object) result);
            }
        }
        disableGifs = false;
        FLog.i(RN_CLASS, String.format("getAlbumInfo for token %d", new Object[]{Integer.valueOf(token)}));
        uri = Files.getContentUri("external");
        projection = new String[]{"max(_id) as latest", "bucket_id", "bucket_display_name", "count(*) as totalCount"};
        selection = "(media_type=1";
        if (photoOnly) {
            selection = selection + " OR media_type=3";
        }
        selection = selection + ")";
        if (disableGifs) {
            selection = selection + " AND mime_type!='image/gif'";
        }
        selection = selection + ") GROUP BY bucket_id, (bucket_display_name";
        sortOrder = "bucket_display_name ASC";
        contentResolver = getReactApplicationContext().getContentResolver();
        buckets = new ArrayList();
        cursor = null;
        try {
            cursor = contentResolver.query(uri, projection, selection, null, sortOrder);
            bucketIdColumnIndex = cursor.getColumnIndex("bucket_id");
            bucketDisplayNemaColumnIndex = cursor.getColumnIndex("bucket_display_name");
            mediaIdColumnIndex = cursor.getColumnIndex("latest");
            countColumnIndex = cursor.getColumnIndex("totalCount");
            while (cursor.moveToNext()) {
                mediaPickerModule = this;
                bucket222 = new a();
                bucket222.a = cursor.getString(bucketIdColumnIndex);
                bucket222.b = cursor.getString(bucketDisplayNemaColumnIndex);
                bucket222.c = Long.valueOf(cursor.getLong(mediaIdColumnIndex));
                bucket222.e = Integer.valueOf((int) cursor.getLong(countColumnIndex));
                buckets.add(bucket222);
            }
            if (cursor != null) {
                cursor.close();
            }
            populateBucketsMediaUrl(buckets);
            addCompositeBucket(buckets);
            result = new WritableNativeArray();
            for (a bucket2222 : buckets) {
                map = new WritableNativeMap();
                map.putString("name", bucket2222.b);
                map.putInt("count", bucket2222.e.intValue());
                if (bucket2222.d == null) {
                    map.putString("thumbnailUri", bucket2222.d.replaceAll(" ", "%20"));
                }
                result.pushMap(map);
            }
            promise.a((Object) result);
        } catch (Exception ex) {
            FLog.e(RN_CLASS, "Failed to query for album, query: " + selection, (Object[]) new Object[]{ex.toString()});
            promise.a(new Throwable("Failed to get album info."));
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void addCompositeBucket(List<a> buckets) {
        int count = 0;
        long mediaId = -1;
        String mediaUrl = null;
        for (a bucket : buckets) {
            count += bucket.e.intValue();
            if (mediaId <= bucket.c.longValue()) {
                mediaId = bucket.c.longValue();
                mediaUrl = bucket.d;
            }
        }
        a allBucket = new a();
        allBucket.b = "All Photos";
        allBucket.e = Integer.valueOf(count);
        allBucket.c = Long.valueOf(mediaId);
        allBucket.d = mediaUrl;
        buckets.add(0, allBucket);
    }

    private void populateBucketsMediaUrl(List<a> buckets) {
        List<Long> mediaIds = new ArrayList();
        Map<Long, a> map = new HashMap();
        for (a bucket : buckets) {
            mediaIds.add(bucket.c);
            map.put(bucket.c, bucket);
        }
        String mediaIdsString = TextUtils.join(",", mediaIds);
        Cursor cursor = null;
        try {
            cursor = getReactApplicationContext().getContentResolver().query(Files.getContentUri("external"), new String[]{"_id", "_data"}, "_id in (" + mediaIdsString + ")", null, null);
            int idColumnIndex = cursor.getColumnIndex("_id");
            int dataColumnIndex = cursor.getColumnIndex("_data");
            while (cursor.moveToNext()) {
                a aVar = (a) map.get(Long.valueOf(cursor.getLong(idColumnIndex)));
                aVar.d = new StringBuilder(FILE_URI_PREFIX).append(cursor.getString(dataColumnIndex)).toString();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
