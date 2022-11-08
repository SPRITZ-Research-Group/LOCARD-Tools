package com.imagepicker;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.webkit.MimeTypeMap;
import com.adjust.sdk.Constants;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.a;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.d;
import com.imagepicker.c.b;
import com.imagepicker.c.e;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

public class ImagePickerModule extends ReactContextBaseJavaModule implements a {
    public static final int REQUEST_LAUNCH_IMAGE_CAPTURE = 13001;
    public static final int REQUEST_LAUNCH_IMAGE_LIBRARY = 13002;
    public static final int REQUEST_LAUNCH_VIDEO_CAPTURE = 13004;
    public static final int REQUEST_LAUNCH_VIDEO_LIBRARY = 13003;
    public static final int REQUEST_PERMISSIONS_FOR_CAMERA = 14001;
    public static final int REQUEST_PERMISSIONS_FOR_LIBRARY = 14002;
    protected d callback;
    protected Uri cameraCaptureURI;
    private final int dialogThemeId;
    private com.imagepicker.a.a imageConfig = new com.imagepicker.a.a(null, null, 0, 0, 100, 0, false);
    private com.facebook.react.modules.core.d listener = new com.facebook.react.modules.core.d(this) {
        final /* synthetic */ ImagePickerModule a;

        {
            this.a = this$0;
        }

        public final boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            boolean permissionsGranted = true;
            for (int i = 0; i < permissions.length; i++) {
                boolean granted;
                if (grantResults[i] == 0) {
                    granted = true;
                } else {
                    granted = false;
                }
                if (permissionsGranted && granted) {
                    permissionsGranted = true;
                } else {
                    permissionsGranted = false;
                }
            }
            if (this.a.callback == null || this.a.options == null) {
                return false;
            }
            if (permissionsGranted) {
                switch (requestCode) {
                    case ImagePickerModule.REQUEST_PERMISSIONS_FOR_CAMERA /*14001*/:
                        this.a.launchCamera(this.a.options, this.a.callback);
                        break;
                    case ImagePickerModule.REQUEST_PERMISSIONS_FOR_LIBRARY /*14002*/:
                        this.a.launchImageLibrary(this.a.options, this.a.callback);
                        break;
                }
                return true;
            }
            this.a.responseHelper.b(this.a.callback, "Permissions weren't granted");
            return false;
        }
    };
    private Boolean noData = Boolean.valueOf(false);
    private am options;
    private Boolean pickVideo = Boolean.valueOf(false);
    private final ag reactContext;
    private c responseHelper = new c();
    @Deprecated
    private int videoDurationLimit = 0;
    @Deprecated
    private int videoQuality = 1;

    public ImagePickerModule(ag reactContext, @StyleRes int dialogThemeId) {
        super(reactContext);
        this.dialogThemeId = dialogThemeId;
        this.reactContext = reactContext;
        this.reactContext.a((a) this);
    }

    public String getName() {
        return "ImagePickerManager";
    }

    @ReactMethod
    public void showImagePicker(am options, d callback) {
        if (getCurrentActivity() == null) {
            this.responseHelper.b(callback, "can't find current Activity");
            return;
        }
        this.callback = callback;
        this.options = options;
        this.imageConfig = new com.imagepicker.a.a(null, null, 0, 0, 100, 0, false);
        e.a(this, options, new e.a(this) {
            final /* synthetic */ ImagePickerModule a;

            {
                this.a = this$0;
            }

            public final void a(@NonNull ImagePickerModule module) {
                if (module != null) {
                    module.launchCamera();
                }
            }

            public final void b(@NonNull ImagePickerModule module) {
                if (module != null) {
                    module.launchImageLibrary();
                }
            }

            public final void c(@NonNull ImagePickerModule module) {
                if (module != null) {
                    module.doOnCancel();
                }
            }

            public final void a(@NonNull ImagePickerModule module, @NonNull String action) {
                if (module != null) {
                    module.invokeCustomButton(action);
                }
            }
        }).show();
    }

    public void doOnCancel() {
        this.responseHelper.a(this.callback);
    }

    public void launchCamera() {
        launchCamera(this.options, this.callback);
    }

    @ReactMethod
    public void launchCamera(am options, d callback) {
        if (isCameraAvailable()) {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                this.responseHelper.b(callback, "can't find current Activity");
                return;
            }
            this.options = options;
            if (permissionsCheck(currentActivity, callback, REQUEST_PERMISSIONS_FOR_CAMERA)) {
                int requestCode;
                Intent cameraIntent;
                parseOptions(this.options);
                if (this.pickVideo.booleanValue()) {
                    requestCode = REQUEST_LAUNCH_VIDEO_CAPTURE;
                    cameraIntent = new Intent("android.media.action.VIDEO_CAPTURE");
                    cameraIntent.putExtra("android.intent.extra.videoQuality", this.videoQuality);
                    if (this.videoDurationLimit > 0) {
                        cameraIntent.putExtra("android.intent.extra.durationLimit", this.videoDurationLimit);
                    }
                } else {
                    requestCode = REQUEST_LAUNCH_IMAGE_CAPTURE;
                    cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                    this.imageConfig = this.imageConfig.a(b.a(this.reactContext, this.options, false));
                    this.cameraCaptureURI = com.imagepicker.c.d.a(this.reactContext, this.imageConfig.a);
                    if (this.cameraCaptureURI == null) {
                        this.responseHelper.b(callback, "Couldn't get file path for photo");
                        return;
                    }
                    cameraIntent.putExtra("output", this.cameraCaptureURI);
                }
                if (cameraIntent.resolveActivity(this.reactContext.getPackageManager()) == null) {
                    this.responseHelper.b(callback, "Cannot launch camera");
                    return;
                }
                this.callback = callback;
                try {
                    currentActivity.startActivityForResult(cameraIntent, requestCode);
                    return;
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    this.responseHelper.b(callback, "Cannot launch camera");
                    return;
                }
            }
            return;
        }
        this.responseHelper.b(callback, "Camera not available");
    }

    public void launchImageLibrary() {
        launchImageLibrary(this.options, this.callback);
    }

    @ReactMethod
    public void launchImageLibrary(am options, d callback) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            this.responseHelper.b(callback, "can't find current Activity");
            return;
        }
        this.options = options;
        if (permissionsCheck(currentActivity, callback, REQUEST_PERMISSIONS_FOR_LIBRARY)) {
            int requestCode;
            Intent libraryIntent;
            parseOptions(this.options);
            if (this.pickVideo.booleanValue()) {
                requestCode = REQUEST_LAUNCH_VIDEO_LIBRARY;
                libraryIntent = new Intent("android.intent.action.PICK");
                libraryIntent.setType("video/*");
            } else {
                requestCode = REQUEST_LAUNCH_IMAGE_LIBRARY;
                libraryIntent = new Intent("android.intent.action.GET_CONTENT");
                libraryIntent.setType("image/*");
                libraryIntent.putExtra("android.intent.extra.LOCAL_ONLY", false);
            }
            if (libraryIntent.resolveActivity(this.reactContext.getPackageManager()) == null) {
                this.responseHelper.b(callback, "Cannot launch photo library");
                return;
            }
            this.callback = callback;
            try {
                currentActivity.startActivityForResult(libraryIntent, requestCode);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                this.responseHelper.b(callback, "Cannot launch photo library");
            }
        }
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (!passResult(requestCode)) {
            this.responseHelper.a();
            if (resultCode != -1) {
                b.a(requestCode, this.imageConfig);
                this.responseHelper.a(this.callback);
                this.callback = null;
                return;
            }
            Uri uri = null;
            switch (requestCode) {
                case REQUEST_LAUNCH_IMAGE_CAPTURE /*13001*/:
                    uri = this.cameraCaptureURI;
                    break;
                case REQUEST_LAUNCH_IMAGE_LIBRARY /*13002*/:
                    uri = data.getData();
                    String realPath = getRealPathFromURI(uri);
                    boolean isUrl = !TextUtils.isEmpty(realPath) && Patterns.WEB_URL.matcher(realPath).matches();
                    if (realPath == null || isUrl) {
                        try {
                            File file = createFileFromURI(uri);
                            realPath = file.getAbsolutePath();
                            uri = Uri.fromFile(file);
                        } catch (Exception e) {
                            this.responseHelper.a("error", "Could not read photo");
                            this.responseHelper.a(ReactVideoViewManager.PROP_SRC_URI, uri.toString());
                            this.responseHelper.b(this.callback);
                            this.callback = null;
                            return;
                        }
                    }
                    this.imageConfig = this.imageConfig.a(new File(realPath));
                    break;
                case REQUEST_LAUNCH_VIDEO_LIBRARY /*13003*/:
                    this.responseHelper.a(ReactVideoViewManager.PROP_SRC_URI, data.getData().toString());
                    this.responseHelper.a("path", getRealPathFromURI(data.getData()));
                    this.responseHelper.b(this.callback);
                    this.callback = null;
                    return;
                case REQUEST_LAUNCH_VIDEO_CAPTURE /*13004*/:
                    String path = getRealPathFromURI(data.getData());
                    this.responseHelper.a(ReactVideoViewManager.PROP_SRC_URI, data.getData().toString());
                    this.responseHelper.a("path", path);
                    b.a(this.reactContext, path);
                    this.responseHelper.b(this.callback);
                    this.callback = null;
                    return;
            }
            b.a result = b.a(this.responseHelper, this.imageConfig);
            if (result.b != null) {
                b.a(requestCode, this.imageConfig);
                this.responseHelper.b(this.callback, result.b.getMessage());
                this.callback = null;
                return;
            }
            Object obj;
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.imageConfig.a.getAbsolutePath(), options);
            int initialWidth = options.outWidth;
            int initialHeight = options.outHeight;
            updatedResultResponse(uri, this.imageConfig.a.getAbsolutePath());
            com.imagepicker.a.a aVar = this.imageConfig;
            int i = result.a;
            if (((initialWidth >= aVar.c || aVar.c <= 0) && aVar.c != 0) || !(((initialHeight < aVar.d && aVar.d > 0) || aVar.d == 0) && aVar.e == 100 && (aVar.f == 0 || i == aVar.f))) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj != null) {
                this.responseHelper.a("width", initialWidth);
                this.responseHelper.a("height", initialHeight);
                b.a(this.reactContext, this.imageConfig.a.getAbsolutePath());
            } else {
                this.imageConfig = b.a(this.reactContext, this.options, this.imageConfig, initialWidth, initialHeight, requestCode);
                if (this.imageConfig.b == null) {
                    b.a(requestCode, this.imageConfig);
                    this.responseHelper.a("error", "Can't resize the image");
                } else {
                    uri = Uri.fromFile(this.imageConfig.b);
                    BitmapFactory.decodeFile(this.imageConfig.b.getAbsolutePath(), options);
                    this.responseHelper.a("width", options.outWidth);
                    this.responseHelper.a("height", options.outHeight);
                    updatedResultResponse(uri, this.imageConfig.b.getAbsolutePath());
                    b.a(this.reactContext, this.imageConfig.b.getAbsolutePath());
                }
            }
            if (this.imageConfig.g && requestCode == 13001) {
                b.b rolloutResult = b.a(this.imageConfig);
                if (rolloutResult.b == null) {
                    this.imageConfig = rolloutResult.a;
                    updatedResultResponse(Uri.fromFile(this.imageConfig.a()), this.imageConfig.a().getAbsolutePath());
                } else {
                    b.a(requestCode, this.imageConfig);
                    this.responseHelper.a("error", "Error moving image to camera roll: " + rolloutResult.b.getMessage());
                    return;
                }
            }
            this.responseHelper.b(this.callback);
            this.callback = null;
            this.options = null;
        }
    }

    public void invokeCustomButton(@NonNull String action) {
        this.responseHelper.a(this.callback, action);
    }

    public void onNewIntent(Intent intent) {
    }

    public Context getContext() {
        return getReactApplicationContext();
    }

    @StyleRes
    public int getDialogThemeId() {
        return this.dialogThemeId;
    }

    @NonNull
    public Activity getActivity() {
        return getCurrentActivity();
    }

    private boolean passResult(int requestCode) {
        return this.callback == null || ((this.cameraCaptureURI == null && requestCode == REQUEST_LAUNCH_IMAGE_CAPTURE) || !(requestCode == REQUEST_LAUNCH_IMAGE_CAPTURE || requestCode == REQUEST_LAUNCH_IMAGE_LIBRARY || requestCode == REQUEST_LAUNCH_VIDEO_LIBRARY || requestCode == REQUEST_LAUNCH_VIDEO_CAPTURE));
    }

    private void updatedResultResponse(@Nullable Uri uri, @NonNull String path) {
        this.responseHelper.a(ReactVideoViewManager.PROP_SRC_URI, uri.toString());
        this.responseHelper.a("path", path);
        if (!this.noData.booleanValue()) {
            this.responseHelper.a("data", getBase64StringFromFile(path));
        }
        putExtraFileInfo(path, this.responseHelper);
    }

    private boolean permissionsCheck(@NonNull Activity activity, @NonNull d callback, @NonNull int requestCode) {
        int i;
        boolean i2;
        int writePermission = android.support.v4.content.a.a((Context) activity, "android.permission.WRITE_EXTERNAL_STORAGE");
        int cameraPermission = android.support.v4.content.a.a((Context) activity, "android.permission.CAMERA");
        if (writePermission == 0 && cameraPermission == 0) {
            i2 = 1;
        } else {
            i2 = false;
        }
        if (i2 != 0) {
            return true;
        }
        if (android.support.v4.app.a.a(activity, "android.permission.WRITE_EXTERNAL_STORAGE") && android.support.v4.app.a.a(activity, "android.permission.CAMERA")) {
            i2 = true;
        } else {
            i2 = false;
        }
        if (Boolean.valueOf(i2).booleanValue()) {
            com.imagepicker.b.b.a(this, this.options, new com.imagepicker.b.b.a(this) {
                final /* synthetic */ ImagePickerModule a;

                {
                    this.a = this$0;
                }

                public final void a(WeakReference<ImagePickerModule> moduleInstance) {
                    ImagePickerModule module = (ImagePickerModule) moduleInstance.get();
                    if (module != null) {
                        module.doOnCancel();
                    }
                }

                public final void b(WeakReference<ImagePickerModule> moduleInstance) {
                    ImagePickerModule module = (ImagePickerModule) moduleInstance.get();
                    if (module != null) {
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", module.getContext().getPackageName(), null));
                        Activity innerActivity = module.getActivity();
                        if (innerActivity != null) {
                            innerActivity.startActivityForResult(intent, 1);
                        }
                    }
                }
            }).show();
            return false;
        }
        String[] PERMISSIONS = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
        if (activity instanceof ReactActivity) {
            ((ReactActivity) activity).a(PERMISSIONS, requestCode, this.listener);
            return false;
        } else if (activity instanceof com.imagepicker.b.a) {
            android.support.v4.app.a.a(activity, PERMISSIONS, requestCode);
            return false;
        } else {
            throw new UnsupportedOperationException(new StringBuilder(activity.getClass().getSimpleName()).append(" must implement ").append(com.imagepicker.b.a.class.getSimpleName()).toString());
        }
    }

    private boolean isCameraAvailable() {
        return this.reactContext.getPackageManager().hasSystemFeature("android.hardware.camera") || this.reactContext.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    @NonNull
    private String getRealPathFromURI(@NonNull Uri uri) {
        Uri uri2 = null;
        Context context = this.reactContext;
        String[] split;
        if ((VERSION.SDK_INT >= 19 ? 1 : 0) == 0 || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
                    return uri.getLastPathSegment();
                }
                if (!new StringBuilder(context.getPackageName()).append(".provider").toString().equals(uri.getAuthority())) {
                    return com.imagepicker.c.d.a(context, uri, null, null);
                }
                File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), uri.getLastPathSegment());
                if (file.exists()) {
                    return file.toString();
                }
                return null;
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            } else {
                return null;
            }
        } else if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
            split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
            return null;
        } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
            return com.imagepicker.c.d.a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (!"com.android.providers.media.documents".equals(uri.getAuthority())) {
            return null;
        } else {
            Object obj = DocumentsContract.getDocumentId(uri).split(":")[0];
            if ("image".equals(obj)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(obj)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(obj)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return com.imagepicker.c.d.a(context, uri2, "_id=?", new String[]{split[1]});
        }
    }

    private File createFileFromURI(Uri uri) throws Exception {
        File file = new File(this.reactContext.getExternalCacheDir(), "photo-" + uri.getLastPathSegment());
        InputStream input = this.reactContext.getContentResolver().openInputStream(uri);
        OutputStream output = new FileOutputStream(file);
        try {
            byte[] buffer = new byte[4096];
            while (true) {
                int read = input.read(buffer);
                if (read == -1) {
                    break;
                }
                output.write(buffer, 0, read);
            }
            output.flush();
            return file;
        } finally {
            output.close();
            input.close();
        }
    }

    private String getBase64StringFromFile(String absoluteFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(absoluteFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while (true) {
            try {
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                output.write(buffer, 0, bytesRead);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return Base64.encodeToString(output.toByteArray(), 2);
    }

    private void putExtraFileInfo(@NonNull String path, @NonNull c responseHelper) {
        try {
            File f = new File(path);
            responseHelper.a("fileSize", (double) f.length());
            responseHelper.a("fileName", f.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        if (extension != null) {
            responseHelper.a("type", MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension));
        }
    }

    private void parseOptions(am options) {
        int i;
        int i2;
        int i3;
        boolean z;
        this.noData = Boolean.valueOf(false);
        if (options.hasKey("noData")) {
            this.noData = Boolean.valueOf(options.getBoolean("noData"));
        }
        com.imagepicker.a.a aVar = this.imageConfig;
        if (options.hasKey("maxWidth")) {
            i = options.getInt("maxWidth");
        } else {
            i = 0;
        }
        if (options.hasKey("maxHeight")) {
            i2 = options.getInt("maxHeight");
        } else {
            i2 = 0;
        }
        int i4 = 100;
        if (options.hasKey("quality")) {
            i4 = (int) (options.getDouble("quality") * 100.0d);
        }
        if (options.hasKey("rotation")) {
            i3 = options.getInt("rotation");
        } else {
            i3 = 0;
        }
        if (options.hasKey("storageOptions")) {
            am map = options.getMap("storageOptions");
            if (map.hasKey("cameraRoll")) {
                z = map.getBoolean("cameraRoll");
                this.imageConfig = new com.imagepicker.a.a(aVar.a, aVar.b, i, i2, i4, i3, z);
                this.pickVideo = Boolean.valueOf(false);
                if (options.hasKey("mediaType") && options.getString("mediaType").equals("video")) {
                    this.pickVideo = Boolean.valueOf(true);
                }
                this.videoQuality = 1;
                if (options.hasKey("videoQuality") && options.getString("videoQuality").equals(Constants.LOW)) {
                    this.videoQuality = 0;
                }
                this.videoDurationLimit = 0;
                if (options.hasKey("durationLimit")) {
                    this.videoDurationLimit = options.getInt("durationLimit");
                }
            }
        }
        z = false;
        this.imageConfig = new com.imagepicker.a.a(aVar.a, aVar.b, i, i2, i4, i3, z);
        this.pickVideo = Boolean.valueOf(false);
        this.pickVideo = Boolean.valueOf(true);
        this.videoQuality = 1;
        this.videoQuality = 0;
        this.videoDurationLimit = 0;
        if (options.hasKey("durationLimit")) {
            this.videoDurationLimit = options.getInt("durationLimit");
        }
    }
}
