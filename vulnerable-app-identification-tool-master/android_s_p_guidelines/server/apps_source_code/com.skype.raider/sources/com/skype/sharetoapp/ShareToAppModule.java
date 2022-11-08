package com.skype.sharetoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.a;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.microsoft.react.a.e;
import com.microsoft.react.a.f;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ShareToAppModule extends ReactContextBaseJavaModule {
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList(IMAGE_EXTENSIONS_ARRAY);
    private static final String[] IMAGE_EXTENSIONS_ARRAY = new String[]{"png", "jpg", "jpeg", "gif"};
    private static final String MODULE_NAME = "ShareToApp";
    private static final String TAG = "ShareToAppModule";
    private static final String TEXT_MIME_TYPE = "text/plain";
    private static final List<String> VIDEO_EXTENSIONS = Arrays.asList(VIDEO_EXTENSIONS_ARRAY);
    private static final String[] VIDEO_EXTENSIONS_ARRAY = new String[]{"mp4", "mov"};
    private volatile boolean initialized = false;
    private Intent preInitializationIntent;
    private final ag reactContext;

    public ShareToAppModule(ag reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void startIntentProcessing() {
        this.initialized = true;
        if (this.preInitializationIntent != null) {
            processIntent(this.preInitializationIntent);
            this.preInitializationIntent = null;
        }
    }

    @ReactMethod
    public void close(String eventId) {
        ReactActivity activity = (ReactActivity) getCurrentActivity();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        activity.startActivity(intent);
    }

    public void processIntent(Intent intent) {
        if (this.initialized) {
            String type = intent.getType();
            String action = intent.getAction();
            if (TEXT_MIME_TYPE.equals(type)) {
                String shareText = intent.getStringExtra("android.intent.extra.TEXT");
                if (TextUtils.isEmpty(shareText)) {
                    FLog.i(TAG, "Attempt to share an empty text");
                    return;
                }
                ar data = new WritableNativeMap();
                data.putString("type", "text");
                data.putString("text", shareText);
                sendEvent(data);
                return;
            }
            ArrayList<Uri> mediaUris = new ArrayList();
            if (intent.hasExtra("android.intent.extra.STREAM")) {
                if ("android.intent.action.SEND_MULTIPLE".equals(action)) {
                    mediaUris = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
                } else {
                    mediaUris.add((Uri) intent.getParcelableExtra("android.intent.extra.STREAM"));
                }
            }
            if (mediaUris == null || mediaUris.isEmpty()) {
                FLog.i(TAG, "No Uri passed.");
                return;
            }
            Uri uri;
            Iterator it = mediaUris.iterator();
            while (it.hasNext()) {
                uri = (Uri) it.next();
                if (uri != null) {
                    if (Os.a(new File(uri.getPath()))) {
                    }
                }
                FLog.i(TAG, "Uri is null or not allowed.");
                return;
            }
            final ArrayList<String> localPathList = new ArrayList();
            final ArrayList<Uri> uriToFetch = new ArrayList();
            it = mediaUris.iterator();
            while (it.hasNext()) {
                uri = (Uri) it.next();
                String path = LocalFileResolver.b(this.reactContext, uri);
                if (path != null) {
                    localPathList.add(path);
                } else {
                    uriToFetch.add(uri);
                }
            }
            final String correctedType = getCorrectMimeType(mediaUris, type);
            if (localPathList.isEmpty()) {
                sendMediaEvent(localPathList, uriToFetch, correctedType);
                return;
            } else if (a.a(this.reactContext, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
                sendMediaEvent(localPathList, uriToFetch, correctedType);
                return;
            } else {
                requestPermission(new d(this) {
                    final /* synthetic */ ShareToAppModule d;

                    public final void invoke(Object... args) {
                        this.d.sendMediaEvent(localPathList, uriToFetch, correctedType);
                    }
                });
                return;
            }
        }
        this.preInitializationIntent = intent;
    }

    private void sendMediaEvent(ArrayList<String> localPathList, ArrayList<Uri> uriToFetch, String type) {
        ar data = new WritableNativeMap();
        data.putString("type", "media");
        aq media = new WritableNativeArray();
        ArrayList<String> pathToShare = new ArrayList();
        Iterator it = localPathList.iterator();
        while (it.hasNext()) {
            String path = (String) it.next();
            if (new File(path).exists()) {
                pathToShare.add(path);
            } else {
                FLog.w(TAG, "Path doesn't exist : " + path);
            }
        }
        it = uriToFetch.iterator();
        while (it.hasNext()) {
            Uri uri = (Uri) it.next();
            String localPath = LocalFileResolver.a(this.reactContext, uri);
            if (localPath == null || !new File(localPath).exists()) {
                FLog.w(TAG, "Failed to create local file for Uri: " + uri.getPath());
            } else {
                pathToShare.add(localPath);
            }
        }
        if (pathToShare.isEmpty()) {
            FLog.w(TAG, "All Uri in the intent are invalid ignoring");
            return;
        }
        it = pathToShare.iterator();
        while (it.hasNext()) {
            addPathToArray(media, (String) it.next());
        }
        data.putArray("data", media);
        sendEvent(data);
    }

    private void addPathToArray(aq media, @NonNull String path) {
        Uri mediaUri = Uri.parse(path);
        String extension = getFileExtension(path);
        boolean isImage = IMAGE_EXTENSIONS.contains(extension);
        boolean isVideo = VIDEO_EXTENSIONS.contains(extension);
        File sourceFile = new File(path);
        ar file = new WritableNativeMap();
        file.putString(ReactVideoViewManager.PROP_SRC_URI, path);
        file.putString("name", sourceFile.getName());
        file.putDouble("size", (double) sourceFile.length());
        f mediaSize;
        if (isImage) {
            file.putString("type", "image");
            mediaSize = e.a(mediaUri);
            file.putInt("height", mediaSize.a);
            file.putInt("width", mediaSize.b);
        } else if (isVideo) {
            file.putString("type", "video");
            mediaSize = e.b(mediaUri);
            file.putInt("height", mediaSize.a);
            file.putInt("width", mediaSize.b);
            String thumbnail = createVideoThumbnail(path);
            if (thumbnail != null) {
                File thumbnailFile = new File(thumbnail);
                if (thumbnailFile.exists()) {
                    file.putString("thumbnailUri", thumbnail);
                    file.putDouble("thumbnailSize", (double) thumbnailFile.length());
                    f thumbnailSize = e.a(Uri.parse(thumbnail));
                    file.putInt("thumbnailHeight", thumbnailSize.a);
                    file.putInt("thumbnailWidth", thumbnailSize.b);
                }
            }
        } else {
            file.putString("type", "file");
        }
        media.pushMap(file);
    }

    @Nullable
    private String getFileExtension(@NonNull String path) {
        int extensionIndex = path.lastIndexOf(".");
        if (extensionIndex == -1) {
            return null;
        }
        return path.substring(extensionIndex + 1).toLowerCase();
    }

    private String createVideoThumbnail(String path) {
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, 1);
        if (bitmap != null) {
            return LocalFileResolver.a(this.reactContext, bitmap);
        }
        return null;
    }

    private void requestPermission(final d permissionCheckCallback) {
        ReactActivity activity = (ReactActivity) getCurrentActivity();
        if (activity != null) {
            activity.a(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1, new com.facebook.react.modules.core.d(this) {
                final /* synthetic */ ShareToAppModule b;

                public final boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                    int index = Arrays.asList(permissions).indexOf("android.permission.READ_EXTERNAL_STORAGE");
                    if (index >= 0) {
                        if (grantResults[index] == 0) {
                            permissionCheckCallback.invoke(new Object[0]);
                        } else {
                            FLog.w(ShareToAppModule.TAG, "Permission prompt denied");
                        }
                    }
                    return true;
                }
            });
        }
    }

    private void sendEvent(ar data) {
        ((RCTDeviceEventEmitter) this.reactContext.a(RCTDeviceEventEmitter.class)).emit("shareToAppEvent", data);
    }

    private static String getCorrectMimeType(List<Uri> uris, String mimeType) {
        String webpExt = "webp";
        if (!mimeType.equals(MimeTypeMap.getSingleton().getMimeTypeFromExtension("jpeg"))) {
            return mimeType;
        }
        for (Uri a : uris) {
            if (LocalFileResolver.a(a, webpExt)) {
                return MimeTypeMap.getSingleton().getMimeTypeFromExtension(webpExt);
            }
        }
        return mimeType;
    }
}
