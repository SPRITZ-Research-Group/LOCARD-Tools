package com.skype.slimcore.video;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.j;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import com.skype.VideoImpl;
import com.skype.android.video.capture.BindingStillImageCapture;
import com.skype.android.video.capture.BindingStillImageCapture.Callback;
import com.skype.android.video.capture.StillImageCaptureBindingEvent;
import com.skype.slimcore.utils.Action1;
import com.skype.slimcore.utils.RNObjectHandleHelper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.Random;

public class StillCamera implements Callback {
    private final String a = ("StillCamera" + System.identityHashCode(this));
    private int b;
    private int c;
    private int d = -1;
    private ag e;
    private BindingStillImageCapture f;
    private final VideoImpl g;
    private Runnable h;
    private Runnable i;
    private int j;
    private Runnable k;
    private Runnable l;
    private int m = -1;
    private final Queue<a> n = new ArrayDeque(1);

    public enum OutputFormat {
        PNG,
        RAW
    }

    private static class a {
        private final boolean a;
        private final boolean b;
        private final OutputFormat c;
        private final Action1<am> d;
        private final Runnable e;
        private final int f;

        /* synthetic */ a(boolean x0, boolean x1, OutputFormat x2, Action1 x3, Runnable x4, int x5, byte b) {
            this(x0, x1, x2, x3, x4, x5);
        }

        private a(boolean flip, boolean cropToSquare, OutputFormat outputFormat, Action1<am> success, Runnable failure, int causeId) {
            this.a = flip;
            this.b = cropToSquare;
            this.c = outputFormat;
            this.d = success;
            this.e = failure;
            this.f = causeId;
        }
    }

    private static class b extends j<Void, Void> {
        private final String a = ("StillCamSave" + System.identityHashCode(this));
        private final ag b;
        private final int[] c;
        private final int d;
        private final int e;
        private final int f;
        private final String g;
        private final a h;

        protected final /* synthetic */ void a() {
            Object createBitmap = Bitmap.createBitmap(this.c, this.d, this.e, Config.ARGB_8888);
            ar writableNativeMap;
            if (createBitmap == null) {
                FLog.e(this.a, "rejecting promise (no bitmap) for still capture causeId: %x", Integer.valueOf(this.h.f));
                this.h.e.run();
            } else if (this.h.c == OutputFormat.PNG) {
                File a = a(createBitmap);
                createBitmap.recycle();
                if (a == null) {
                    FLog.e(this.a, "rejecting promise (no file) for still capture causeId: %x", Integer.valueOf(this.h.f));
                    this.h.e.run();
                    return;
                }
                FLog.i(this.a, "resolving promise for still capture %d x %d causeId: %x", Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.h.f));
                String uri = Uri.fromFile(a).toString();
                writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString(ReactVideoViewManager.PROP_SRC_URI, uri);
                writableNativeMap.putString("data", this.g);
                writableNativeMap.putInt("width", this.d);
                writableNativeMap.putInt("height", this.e);
                this.h.d.a(writableNativeMap);
            } else {
                FLog.i(this.a, "resolving promise for still capture %d x %d causeId: %x", Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.h.f));
                RNObjectHandleHelper a2 = RNObjectHandleHelper.a();
                String a3 = a2.a(createBitmap);
                FLog.d(this.a, "RNObjectHandleHelper key sets:" + a2.b().toString());
                writableNativeMap = new WritableNativeMap();
                writableNativeMap.putInt("width", createBitmap.getWidth());
                writableNativeMap.putInt("height", createBitmap.getHeight());
                writableNativeMap.putBoolean("mirrored", false);
                writableNativeMap.putInt("angle", 0);
                writableNativeMap.putString(PropertiesEntry.COLUMN_NAME_KEY, a3);
                this.h.d.a(writableNativeMap);
            }
        }

        public b(ag context, int width, int height, int angle, byte[] colors, a captureRequest) {
            int widthRotated;
            int heightRotated;
            super(context);
            FLog.i(this.a, "SaveImage init frame:%d x %d causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(captureRequest.f));
            this.b = context;
            this.f = angle % 360;
            this.h = captureRequest;
            int viewWidth = width;
            int viewHeight = height;
            if (this.h.b) {
                viewWidth = Math.min(width, height);
                viewHeight = viewWidth;
            }
            if (this.f % 180 == 0) {
                this.d = viewWidth;
                this.e = viewHeight;
                widthRotated = width;
                heightRotated = height;
            } else {
                this.d = viewHeight;
                this.e = viewWidth;
                widthRotated = height;
                heightRotated = width;
            }
            this.c = new int[(this.d * this.e)];
            byte[] bytes = new byte[this.c.length];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int i2;
                    int i3;
                    Object obj;
                    boolean d = captureRequest.a;
                    switch (angle % 360) {
                        case 90:
                            i2 = (widthRotated - j) - 1;
                            i3 = i;
                            break;
                        case 180:
                            i3 = j;
                            i2 = i;
                            break;
                        case 270:
                            i3 = (heightRotated - i) - 1;
                            i2 = j;
                            break;
                        default:
                            i2 = (widthRotated - i) - 1;
                            i3 = (heightRotated - j) - 1;
                            break;
                    }
                    if (d) {
                        i2 = (widthRotated - i2) - 1;
                    }
                    Point p = new Point(i2, i3);
                    i2 = this.d;
                    i3 = this.e;
                    p.offset(-((int) Math.ceil((double) (Math.max(widthRotated - i2, 0) / 2))), -((int) Math.ceil((double) (Math.max(heightRotated - i3, 0) / 2))));
                    i2 = this.d;
                    i3 = this.e;
                    if (p.x < 0 || p.x >= i2 || p.y < 0 || p.y >= i3) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        int t = (this.d * p.y) + p.x;
                        if (t >= this.c.length) {
                            FLog.e(this.a, "TRANSFORM FAIL i:%d j:%d a:%d t:%d", Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(angle), Integer.valueOf(t));
                        }
                        int s = (j * width) + i;
                        this.c[t] = ((((colors[(s * 4) + 3] & 255) << 24) | ((colors[s * 4] & 255) << 16)) | ((colors[(s * 4) + 1] & 255) << 8)) | (colors[(s * 4) + 2] & 255);
                        bytes[t] = (byte) this.c[t];
                    }
                }
            }
            this.g = Base64.encodeToString(bytes, 0);
        }

        private File a(Bitmap bmp) {
            FileNotFoundException e;
            Throwable th;
            FLog.i(this.a, "SaveImage saveBitmap causeId: %x", Integer.valueOf(this.h.f));
            try {
                File target = File.createTempFile("SKP_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + new Random().nextInt(Constants.ONE_SECOND), ".jpg");
                Object destination = target.toString();
                BufferedOutputStream stream = null;
                try {
                    BufferedOutputStream stream2 = new BufferedOutputStream(new FileOutputStream(destination));
                    try {
                        bmp.compress(CompressFormat.JPEG, 100, stream2);
                        FLog.i(this.a, "saveBitmap saved %s causeId: %x", destination, Integer.valueOf(this.h.f));
                        try {
                            stream2.close();
                            FLog.i(this.a, "bos closed");
                            stream = stream2;
                            return target;
                        } catch (IOException e2) {
                            FLog.e(this.a, e2.getLocalizedMessage());
                            stream = stream2;
                            return target;
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        stream = stream2;
                        try {
                            FLog.e(this.a, "saveBitmap exception %s causeId: %x", e.getLocalizedMessage(), Integer.valueOf(this.h.f));
                            if (stream != null) {
                                return null;
                            }
                            try {
                                stream.close();
                                FLog.i(this.a, "bos closed");
                                return null;
                            } catch (IOException e22) {
                                FLog.e(this.a, e22.getLocalizedMessage());
                                return null;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (stream != null) {
                                try {
                                    stream.close();
                                    FLog.i(this.a, "bos closed");
                                } catch (IOException e222) {
                                    FLog.e(this.a, e222.getLocalizedMessage());
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        stream = stream2;
                        if (stream != null) {
                            stream.close();
                            FLog.i(this.a, "bos closed");
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e4) {
                    e = e4;
                    FLog.e(this.a, "saveBitmap exception %s causeId: %x", e.getLocalizedMessage(), Integer.valueOf(this.h.f));
                    if (stream != null) {
                        return null;
                    }
                    stream.close();
                    FLog.i(this.a, "bos closed");
                    return null;
                }
            } catch (IOException e2222) {
                FLog.e(this.a, "saveBitmap exception %s causeId: %x", e2222.getLocalizedMessage(), Integer.valueOf(this.h.f));
                return null;
            }
        }
    }

    public StillCamera(VideoImpl video, ag context, Runnable success, Runnable failure, int causeId) {
        FLog.i(this.a, "init causeId: %x", Integer.valueOf(causeId));
        this.e = context;
        this.h = success;
        this.i = failure;
        this.j = causeId;
        this.f = new StillImageCaptureBindingEvent(this);
        this.b = this.f.getNativeBindingType();
        this.c = (int) this.f.getNativeBindingEvent();
        FLog.i(this.a, "binding type: %d event: %d causeId: %x", Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(causeId));
        this.g = video;
        this.g.createBinding(this.b, this.c);
        FLog.i(this.a, "called createBinding causeId: %x", Integer.valueOf(causeId));
    }

    public final int a() {
        return this.g.getObjectID();
    }

    public final void a(boolean flip, boolean cropToSquare, OutputFormat outputFormat, Action1<am> success, Runnable failure, int causeId) {
        if (this.d == -1) {
            FLog.e(this.a, "captureStill with no binding, rejecting causeId: %x", Integer.valueOf(causeId));
            failure.run();
            return;
        }
        synchronized (this.n) {
            FLog.i(this.a, "captureStill causeId: %x", Integer.valueOf(causeId));
            this.n.add(new a(flip, cropToSquare, outputFormat, success, failure, causeId, (byte) 0));
            this.f.captureStillImage();
        }
    }

    public final void a(Runnable success, Runnable failure, int causeId) {
        if (this.m != -1) {
            FLog.e(this.a, "dispose: already in progress w/causeId %x  causeId: %x", Integer.valueOf(this.m), Integer.valueOf(causeId));
        } else if (this.d != -1) {
            FLog.i(this.a, "dispose causeId: %x", Integer.valueOf(causeId));
            this.m = causeId;
            this.k = success;
            this.l = failure;
            this.g.releaseBinding(this.d);
        } else {
            FLog.i(this.a, "dispose with no binding causeId: %x", Integer.valueOf(causeId));
            success.run();
        }
    }

    public void onBindingCreated(long l) {
        this.d = (int) l;
        FLog.i(this.a, "onBindingCreated causeId: %x", Integer.valueOf(this.j));
        this.h.run();
    }

    public void onBindingReleased() {
        FLog.i(this.a, "onBindingReleased causeId: %x", Integer.valueOf(this.m));
        this.d = -1;
        this.m = -1;
        this.k.run();
    }

    public void onBindingFailed() {
        if (this.j != -1) {
            FLog.e(this.a, "onBindingFailed (init) causeId: %x", Integer.valueOf(this.j));
            this.j = -1;
            this.i.run();
        } else if (this.m != -1) {
            FLog.e(this.a, "onBindingFailed (dispose) causeId: %x", Integer.valueOf(this.m));
            this.m = -1;
            this.l.run();
        }
    }

    public void onStillImageCaptureCompleted(byte[] colors, int width, int height, int angle) {
        synchronized (this.n) {
            a captureRequest = (a) this.n.remove();
            FLog.i(this.a, "onStillImageCaptureCompleted %d x %d (%d deg) causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(angle), Integer.valueOf(captureRequest.f));
            if (colors == null) {
                FLog.e(this.a, "rejecting promise (no pixel buffer) for still capture causeId: %x", Integer.valueOf(captureRequest.f));
                captureRequest.e.run();
                return;
            }
            new b(this.e, width, height, angle + 180, colors, captureRequest).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
