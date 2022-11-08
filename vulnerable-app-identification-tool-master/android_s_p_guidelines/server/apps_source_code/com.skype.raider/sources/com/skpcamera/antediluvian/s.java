package com.skpcamera.antediluvian;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.net.Uri;
import android.opengl.GLES20;
import android.os.AsyncTask;
import android.os.ConditionVariable;
import com.adjust.sdk.Constants;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ar;
import com.skpcamera.SkypeCameraViewManager;
import com.skype.android.video.hw.utils.CodecUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public final class s implements k {
    private static volatile float a = 360.0f;
    private static volatile int b = 70;
    private boolean A;
    private int B;
    private int C;
    private int D;
    private int E;
    private boolean F;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private WeakReference<SkypeCameraView> f;
    private Set<k> g;
    private ae h;
    private File i;
    private String j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private x o;
    private i p;
    private o q;
    private int r;
    private m s;
    private MediaMuxer t;
    private ConditionVariable u;
    private boolean v = false;
    private int w;
    private int x;
    private long y = -1;
    private long z = -1;

    private class a extends AsyncTask<File, File, File> {
        final /* synthetic */ s a;

        private a(s sVar) {
            this.a = sVar;
        }

        /* synthetic */ a(s x0, byte b) {
            this(x0);
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((File[]) objArr);
        }

        private static File a(File... file) {
            v streamable = new v(file[0]);
            try {
                FLog.i("SkypeCamcorderCamera", "making streamable");
                return streamable.a();
            } catch (IOException e) {
                FLog.w("SkypeCamcorderCamera", "streamable IO exception " + e.toString());
                return file[0];
            }
        }
    }

    public s(Context context, SkypeCameraView view, boolean flip, float videoWidth, float videoHeight, float videoBitrate, float videoProfile, boolean enableVideoBFrame, ae promise, int causeId) {
        this.h = promise;
        this.k = flip;
        this.B = (int) videoWidth;
        this.C = (int) videoHeight;
        this.D = (int) videoBitrate;
        this.E = (int) videoProfile;
        this.F = enableVideoBFrame;
        this.g = new HashSet();
        a(view);
        this.i = new File(context.getCacheDir(), "scv_" + System.currentTimeMillis() + ".mp4");
        this.A = true;
        this.n = 0;
        g c = f.c();
        k aVar = new a(this, new b(c.b()));
        this.g.add(aVar);
        e fVar = new f(aVar, c);
        fVar.b(j.START, null);
        this.g.add(fVar);
        try {
            this.t = new MediaMuxer(this.i.getPath(), 0);
            this.u = new ConditionVariable();
            FLog.i("SkypeCamcorderCamera", "instantiated camcorder (causeId " + causeId + ")");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final void a(SkypeCameraView view) {
        this.f = new WeakReference(view);
    }

    public final void a(long timestamp, float[] matrix) {
        if (!this.d && !this.e && this.p != null && this.o != null) {
            this.o.d();
            GLES20.glViewport(0, 0, this.l, this.m);
            this.q.a(this.r, matrix);
            this.p.c();
            this.o.a(timestamp);
            this.o.e();
        }
    }

    public final void a(m eglCore, o fullFrameBlit, int textureId, int causeId) {
        int i;
        FLog.i("SkypeCamcorderCamera", "camcorder - surface created with texture ID " + textureId + " (causeId " + causeId + ")");
        this.s = eglCore;
        this.q = fullFrameBlit;
        this.r = textureId;
        boolean z = this.k;
        if (z) {
            try {
                i = this.C;
            } catch (IOException e) {
                FLog.w("SkypeCamcorderCamera", "initEncoder exception: " + e.getLocalizedMessage());
                c();
            }
        } else {
            i = this.B;
        }
        int i2 = z ? this.B : this.C;
        int d = t.b().d();
        int e2 = t.b().e();
        if (e2 / d > i2 / i) {
            i = (d * i2) / e2;
        } else {
            i2 = (e2 * i) / d;
        }
        if (i % 2 == 1 && i > 2) {
            i--;
        }
        this.l = i;
        if (i2 % 2 == 1 && i2 > 2) {
            i2--;
        }
        this.m = i2;
        FLog.i("SkypeCamcorderCamera", "encoder is " + this.l + " x " + this.m);
        this.p = new i(this.l, this.m, this.D * Constants.ONE_SECOND, this.E, this.F, t.b(), this);
        this.o = new x(this.s, this.p.a());
        for (k a : this.g) {
            a.a(j.START);
        }
    }

    public final void a(int causeId) {
        FLog.i("SkypeCamcorderCamera", "cancelling recording (causeId " + causeId + ")");
        this.d = true;
        if (this.g != null) {
            for (k component : this.g) {
                FLog.i("SkypeCamcorderCamera", "stopping " + component.getClass().getName() + "(causeId " + causeId + ")");
                component.b();
            }
            this.g.clear();
        }
        if (this.p != null) {
            FLog.i("SkypeCamcorderCamera", "shutting down circular encoder (causeId " + causeId + ")");
            this.p.b();
            this.p = null;
        }
        if (this.o != null) {
            FLog.i("SkypeCamcorderCamera", "releasing encoder surface (causeId " + causeId + ")");
            this.o.f();
            this.o = null;
        }
        d(causeId);
        FLog.i("SkypeCamcorderCamera", "cancel: done (causeId " + causeId + ")");
        a(false);
    }

    public final void b(int causeId) {
        FLog.i("SkypeCamcorderCamera", "finishing recording with " + this.g.size() + " components (causeId " + causeId + ")");
        this.e = true;
        if (this.g != null) {
            for (k component : this.g) {
                FLog.i("SkypeCamcorderCamera", "stopping " + component.getClass().getName() + "(causeId " + causeId + ")");
                component.a(j.STOP);
                component.b();
                FLog.i("SkypeCamcorderCamera", "done stopping " + component.getClass().getName() + "(causeId " + causeId + ")");
            }
            this.g.clear();
        }
        if (this.p != null) {
            FLog.i("SkypeCamcorderCamera", "saving circular encoder (causeId " + causeId + ")");
            this.p.a(this.i);
            FLog.i("SkypeCamcorderCamera", "shutting down circular encoder (causeId " + causeId + ")");
            this.p.b();
            this.p = null;
        }
        if (this.o != null) {
            FLog.i("SkypeCamcorderCamera", "releasing encoder surface (causeId " + causeId + ")");
            this.o.f();
            this.o = null;
        }
        d(causeId);
        FLog.i("SkypeCamcorderCamera", "finish: done (causeId " + causeId + ")");
    }

    public final void a() {
        FLog.i("SkypeCamcorderCamera", "camcorder file save complete");
        if (this.e) {
            this.j = a(this.i.toString());
            try {
                this.i = (File) new a().execute(new File[]{this.i}).get();
            } catch (InterruptedException e) {
                FLog.e("SkypeCamcorderCamera", "streamable interrupted exception " + e.toString());
            } catch (ExecutionException e2) {
                FLog.e("SkypeCamcorderCamera", "streamable execution exception " + e2.toString());
            }
            this.e = false;
            a(false);
            c();
            return;
        }
        throw new RuntimeException("camcorder file saving was not in progress");
    }

    private static String a(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        Bitmap bmp = mmr.getFrameAtTime(0, 2);
        c task = new c();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            float max = Math.max((float) bmp.getWidth(), (float) bmp.getHeight()) / a;
            if (max > 1.0f) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bmp, (int) Math.floor((double) (((float) bmp.getWidth()) / max)), (int) Math.floor((double) (((float) bmp.getHeight()) / max)), false);
                if (createScaledBitmap != bmp) {
                    bmp.recycle();
                }
                bmp = createScaledBitmap;
            }
            bmp.compress(CompressFormat.JPEG, b, stream);
            bmp.recycle();
            task.execute(new byte[][]{stream.toByteArray()});
            try {
                return (String) task.get();
            } catch (InterruptedException e) {
                FLog.e("SkypeCamcorderCamera", e.getLocalizedMessage());
            } catch (ExecutionException e2) {
                FLog.e("SkypeCamcorderCamera", e2.getLocalizedMessage());
            }
            FLog.e("SkypeCamcorderCamera", "camcorder thumbnail generation failed");
            return "";
        } catch (NullPointerException e3) {
            FLog.e("SkypeCamcorderCamera", "camcorder thumbnail generation failed " + e3.getLocalizedMessage());
            return "";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(MediaFormat format) {
        if (this.t != null) {
            String type = format.getString("mime");
            if (CodecUtils.MEDIA_TYPE.equalsIgnoreCase(type)) {
                FLog.i("SkypeCamcorderCamera", "add video track");
                this.x = this.t.addTrack(format);
                this.n++;
            } else if ("audio/mp4a-latm".equalsIgnoreCase(type)) {
                FLog.i("SkypeCamcorderCamera", "add audio track");
                this.w = this.t.addTrack(format);
                this.n++;
            }
            if (this.A) {
            }
            this.t.start();
            FLog.i("SkypeCamcorderCamera", "all tracks available, isMuxing -> true");
            this.v = true;
            this.u.open();
        }
    }

    private synchronized void d() {
        int causeId = t.c();
        FLog.i("SkypeCamcorderCamera", "onEndOfStream with causeId " + causeId);
        this.n--;
        if (this.t != null && this.n == 0) {
            FLog.i("SkypeCamcorderCamera", "mediaMuxer.release() (causeId " + causeId + ")");
            d(causeId);
        }
    }

    private void d(int causeId) {
        boolean z = false;
        if (this.t == null || !this.v) {
            String str = "SkypeCamcorderCamera";
            StringBuilder stringBuilder = new StringBuilder("no media muxer to release muxer:");
            if (this.t != null) {
                z = true;
            }
            FLog.w(str, stringBuilder.append(z).append(" muxing: ").append(this.v).append(" (causeId ").append(causeId).append(")").toString());
        } else {
            try {
                FLog.i("SkypeCamcorderCamera", "mediaMuxer.release() (causeId " + causeId + ")");
                this.t.release();
            } catch (IllegalStateException e) {
                FLog.e("SkypeCamcorderCamera", "mediaMuxer.release() exception : " + e.getLocalizedMessage() + " (causeId " + causeId + ")");
            } finally {
                this.t = null;
                this.v = false;
                FLog.i("SkypeCamcorderCamera", "releasing muxerStarted lock, isMuxing -> false (causeId " + causeId + ")");
                this.u.close();
            }
        }
        this.x = -1;
        this.w = -1;
    }

    public final void b() {
    }

    public final boolean a(j command) {
        return b(command, null);
    }

    public final boolean b(j command, Object data) {
        switch (command) {
            case WRITE_SAMPLE:
                r rVar = (r) data;
                if (this.d) {
                    return true;
                }
                if (this.u.block(10000)) {
                    int i;
                    long d = rVar.d();
                    long j = 0;
                    int i2;
                    if (CodecUtils.MEDIA_TYPE.equalsIgnoreCase(rVar.c())) {
                        i2 = this.x;
                        if (this.z == -1) {
                            this.z = d;
                            FLog.i("SkypeCamcorderCamera", "video started: " + d);
                        }
                        j = this.z;
                        i = i2;
                    } else if ("audio/mp4a-latm".equalsIgnoreCase(rVar.c())) {
                        i2 = this.w;
                        if (this.y == -1) {
                            this.y = d;
                            FLog.i("SkypeCamcorderCamera", "audio started: " + d);
                        }
                        j = this.y;
                        i = i2;
                    } else {
                        i = -1;
                    }
                    if (i >= 0) {
                        rVar.a().presentationTimeUs = d - j;
                        try {
                            this.t.writeSampleData(i, rVar.b(), rVar.a());
                        } catch (IllegalStateException e) {
                            FLog.e("SkypeCamcorderCamera", "writeSample " + i + " IllegalStateException: " + e.getLocalizedMessage());
                        }
                    }
                } else {
                    this.u.open();
                }
                if (this.c) {
                    return true;
                }
                a(true);
                return true;
            case CHANGE_MEDIA_FORMAT:
                a((MediaFormat) data);
                return true;
            case END_OF_STREAM:
                d();
                return true;
            default:
                FLog.w("SkypeCamcorderCamera", "unsupported command " + command.toString());
                return false;
        }
    }

    public static void c(int rate) {
        b = rate;
    }

    public static void a(float dimension) {
        a = dimension;
    }

    private void a(boolean isRecording) {
        SkypeCameraView myView;
        if (this.f != null) {
            myView = (SkypeCameraView) this.f.get();
        } else {
            myView = null;
        }
        if (myView != null) {
            ar event = new WritableNativeMap();
            event.putInt("isRecording", isRecording ? 1 : 0);
            event.putString(ReactVideoViewManager.PROP_SRC_URI, this.i.toString());
            SkypeCameraViewManager.sendEvent(SkypeCameraViewManager.RECORDING_CHANGE_EVENT_NAME, myView, event);
            this.c = isRecording;
        }
    }

    private void c() {
        Object map = new WritableNativeMap();
        String uri = Uri.fromFile(this.i).toString();
        if (uri.length() <= 0 || this.j.length() <= 0) {
            this.h.a(new Throwable("Recording failed"));
            return;
        }
        map.putString(ReactVideoViewManager.PROP_SRC_URI, uri);
        map.putString("thumbnailUri", this.j);
        map.putInt("width", this.l);
        map.putInt("height", this.m);
        this.h.a(map);
    }
}
