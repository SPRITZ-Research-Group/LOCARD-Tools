package com.projectseptember.RNGL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.net.Uri.Builder;
import android.opengl.GLES20;
import android.opengl.GLException;
import android.util.Base64OutputStream;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.core.e;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.v;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.q;
import com.microsoft.react.videofxp.VideoFXPModule;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executor;

public class GLCanvas extends GLTextureView implements v, com.facebook.react.uimanager.v, n, Executor {
    private ai a;
    private RNGLContext b;
    private boolean c = true;
    private boolean d = true;
    private boolean e = false;
    private h f;
    private int g;
    private int h;
    private boolean i;
    private e j;
    private List<Uri> k = new ArrayList();
    private List<Uri> l = new ArrayList();
    private Map<Uri, g> m = new HashMap();
    private List<l> n = new ArrayList();
    private List<Bitmap> o = new ArrayList();
    private Map<Integer, i> p;
    private Map<Integer, f> q;
    private e r;
    private final Queue<Runnable> s = new LinkedList();
    private List<a> t = new ArrayList();
    private float u;
    private float v;
    private q w = q.AUTO;

    static /* synthetic */ boolean a(GLCanvas x0, int x1) {
        if (x0.j == null) {
            return true;
        }
        HashMap hashMap = new HashMap();
        h a = x0.a(x0.j, hashMap, x1);
        if (a == null) {
            return false;
        }
        Collection keySet = x0.m.keySet();
        Collection keySet2 = hashMap.keySet();
        Collection hashSet = new HashSet();
        hashSet.addAll(keySet);
        hashSet.removeAll(keySet2);
        x0.m = hashMap;
        x0.l.removeAll(hashSet);
        x0.f = a;
        return true;
    }

    public GLCanvas(ae context, e executorSupplier) {
        super(context);
        this.a = context;
        this.r = executorSupplier;
        this.b = (RNGLContext) context.b(RNGLContext.class);
        DisplayMetrics dm = this.a.getResources().getDisplayMetrics();
        this.v = dm.density;
        this.u = dm.density;
        setRenderer(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
        e();
    }

    private f a(Integer id, int causeId) {
        if (!this.q.containsKey(id)) {
            Point size = g();
            this.q.put(id, new f(this, size.x, size.y, causeId));
        }
        return (f) this.q.get(id);
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                FLog.w("RNGLCanvas", "close failed", e);
            }
        }
    }

    private boolean f() {
        for (Uri uri : this.k) {
            if (!this.l.contains(uri)) {
                return true;
            }
        }
        return false;
    }

    public void setNbContentTextures(int n) {
        this.h = n;
        e();
    }

    public void setRenderId(int renderId) {
        if (this.h > 0) {
            if (!f()) {
                b();
            }
            e();
        }
    }

    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        e();
    }

    public void setAutoRedraw(boolean autoRedraw) {
        this.i = autoRedraw;
    }

    public void setData(e data, int causeId) {
        this.j = data;
        this.f = null;
        if (!f()) {
            b();
        }
        a(causeId);
    }

    public void setImagesToPreload(al imagesToPreloadRA, int causeId) {
        List<Uri> imagesToPreload = new ArrayList();
        for (int i = 0; i < imagesToPreloadRA.size(); i++) {
            imagesToPreload.add(a(imagesToPreloadRA.getMap(i).getString(ReactVideoViewManager.PROP_SRC_URI)));
        }
        this.k = imagesToPreload;
        a(causeId);
    }

    public void execute(Runnable runnable) {
        synchronized (this.s) {
            this.s.add(runnable);
            e();
        }
    }

    public final void a(final int causeId) {
        execute(new Runnable(this) {
            final /* synthetic */ GLCanvas b;

            public final void run() {
                try {
                    if (!GLCanvas.a(this.b, causeId)) {
                        this.b.a(causeId);
                    }
                } catch (j e) {
                }
            }
        });
    }

    private Point g() {
        Display display = this.a.j().getWindowManager().getDefaultDisplay();
        Point rect = new Point();
        display.getSize(rect);
        return rect;
    }

    public final int b() {
        int count;
        List<Bitmap> bitmaps = new ArrayList();
        ViewGroup parent = (ViewGroup) getParent();
        if (parent == null) {
            count = 0;
        } else {
            count = parent.getChildCount() - 1;
        }
        for (int i = 0; i < count; i++) {
            Object createBitmap;
            View view = parent.getChildAt(i);
            if (view instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) view;
                if (group.getChildCount() == 1) {
                    view = group.getChildAt(0);
                }
            }
            int width = view.getWidth();
            int height = view.getHeight();
            if (width <= 0 || height <= 0) {
                createBitmap = Bitmap.createBitmap(2, 2, Config.ARGB_8888);
            } else {
                if (view.getDrawingCache() == null) {
                    view.setDrawingCacheEnabled(true);
                }
                Bitmap drawingCache = view.getDrawingCache();
                if (drawingCache == null) {
                    FLog.e("RNGLCanvas", "view.getDrawingCache() is null. view=" + view);
                    view.setDrawingCacheEnabled(false);
                    createBitmap = Bitmap.createBitmap(2, 2, Config.ARGB_8888);
                } else {
                    Matrix matrix = new Matrix();
                    matrix.postScale(1.0f, -1.0f);
                    createBitmap = Bitmap.createBitmap(drawingCache, 0, 0, drawingCache.getWidth(), drawingCache.getHeight(), matrix, true);
                    view.setDrawingCacheEnabled(false);
                }
            }
            bitmaps.add(createBitmap);
        }
        this.o = bitmaps;
        return count;
    }

    private void b(int n, int causeId) {
        int length = this.n.size();
        if (length != n) {
            if (n < length) {
                this.n = this.n.subList(0, n);
                return;
            }
            Point size = g();
            for (int i = this.n.size(); i < n; i++) {
                this.n.add(new l(this, size.x, size.y, causeId));
            }
        }
    }

    private Uri a(String src) {
        Uri uri = null;
        if (src == null) {
            return null;
        }
        try {
            uri = Uri.parse(src);
            if (uri.getScheme() == null) {
                uri = null;
            }
        } catch (Exception e) {
        }
        if (uri != null) {
            return uri;
        }
        Context context = this.a;
        if (src == null || src.isEmpty()) {
            return null;
        }
        return new Builder().scheme("res").path(String.valueOf(context.getResources().getIdentifier(src.toLowerCase().replace("-", "_"), "drawable", context.getPackageName()))).build();
    }

    private h a(e data, HashMap<Uri, g> images, int causeId) {
        i shader;
        Map<Uri, g> prevImages = this.m;
        Integer num = data.a;
        if (!this.p.containsKey(num)) {
            k shader2 = this.b.getShader(num);
            if (shader2 == null) {
                shader = null;
                if (shader != null || !shader.c()) {
                    return null;
                }
                h node;
                String uniformName;
                Map<String, Integer> uniformsInteger = new HashMap();
                Map<String, Float> uniformsFloat = new HashMap();
                Map<String, IntBuffer> uniformsIntBuffer = new HashMap();
                Map<String, FloatBuffer> uniformsFloatBuffer = new HashMap();
                Map<String, l> textures = new HashMap();
                List<h> contextChildren = new ArrayList();
                List<h> children = new ArrayList();
                for (e child : data.g) {
                    node = a(child, (HashMap) images, causeId);
                    if (node == null) {
                        return null;
                    }
                    contextChildren.add(node);
                }
                for (e child2 : data.h) {
                    node = a(child2, (HashMap) images, causeId);
                    if (node == null) {
                        return null;
                    }
                    children.add(node);
                }
                Map<String, Integer> uniformTypes = shader.b();
                int units = 0;
                ReadableMapKeySetIterator iterator = data.b.keySetIterator();
                while (iterator.hasNextKey()) {
                    uniformName = iterator.nextKey();
                    int type = ((Integer) uniformTypes.get(uniformName)).intValue();
                    am dataUniforms = data.b;
                    if (type != 35678 && type != 35680) {
                        switch (type) {
                            case 5124:
                                uniformsInteger.put(uniformName, Integer.valueOf(dataUniforms.getInt(uniformName)));
                                break;
                            case 5126:
                                uniformsFloat.put(uniformName, Float.valueOf((float) dataUniforms.getDouble(uniformName)));
                                break;
                            case 35664:
                            case 35665:
                            case 35666:
                            case 35674:
                            case 35675:
                            case 35676:
                                al arr = dataUniforms.getArray(uniformName);
                                if (b(type) != arr.size()) {
                                    shader.a("uniform '" + uniformName + "': Invalid array size: " + arr.size() + ". Expected: " + b(type) + " (causeId " + causeId + ")");
                                }
                                uniformsFloatBuffer.put(uniformName, a(arr));
                                break;
                            case 35667:
                            case 35668:
                            case 35669:
                            case 35671:
                            case 35672:
                            case 35673:
                                al arr2 = dataUniforms.getArray(uniformName);
                                if (b(type) != arr2.size()) {
                                    shader.a("uniform '" + uniformName + "': Invalid array size: " + arr2.size() + ". Expected: " + b(type) + " (causeId " + causeId + ")");
                                }
                                uniformsIntBuffer.put(uniformName, b(arr2));
                                break;
                            case 35670:
                                uniformsInteger.put(uniformName, Integer.valueOf(dataUniforms.getBoolean(uniformName) ? 1 : 0));
                                break;
                            default:
                                shader.a("uniform '" + uniformName + "': type not supported: " + type + " (causeId " + causeId + ")");
                                break;
                        }
                    }
                    int units2 = units + 1;
                    uniformsInteger.put(uniformName, Integer.valueOf(units));
                    Point size;
                    if (dataUniforms.isNull(uniformName)) {
                        size = g();
                        l lVar = new l(this, size.x, size.y, causeId);
                        lVar.c(causeId);
                        textures.put(uniformName, lVar);
                        units = units2;
                    } else {
                        am value = dataUniforms.getMap(uniformName);
                        String t = value.getString("type");
                        if (t.equals("content")) {
                            int id = value.getInt("id");
                            if (id >= this.n.size()) {
                                b(id + 1, causeId);
                            }
                            textures.put(uniformName, this.n.get(id));
                            units = units2;
                        } else {
                            if (t.equals("fbo")) {
                                textures.put(uniformName, a(Integer.valueOf(value.getInt("id")), causeId).a.get(0));
                                units = units2;
                            } else {
                                if (t.equals(ReactVideoViewManager.PROP_SRC_URI)) {
                                    Object obj;
                                    String str;
                                    Uri src;
                                    g image;
                                    final Uri uri;
                                    final int i;
                                    String str2 = null;
                                    if (value.hasKey("isStatic")) {
                                        if (value.getBoolean("isStatic")) {
                                            obj = 1;
                                            if (value.hasKey("path")) {
                                                str2 = value.getString("path");
                                            }
                                            if (str2 == null && obj == null) {
                                                str = str2;
                                            } else {
                                                str = value.getString(ReactVideoViewManager.PROP_SRC_URI);
                                            }
                                            src = a(str);
                                            if (src == null) {
                                                shader.a("texture uniform '" + uniformName + "': Invalid uri format '" + value + "' (causeId " + causeId + ")");
                                            }
                                            image = (g) images.get(src);
                                            if (image == null) {
                                                image = (g) prevImages.get(src);
                                                if (image != null) {
                                                    FLog.i("RNGLCanvas", "Image %s from prevImages (causeId %x)", (Object) src, Integer.valueOf(causeId));
                                                    images.put(src, image);
                                                }
                                            }
                                            if (image == null) {
                                                size = g();
                                                uri = src;
                                                i = causeId;
                                                image = new g(this, this.r.c(), new Runnable(this) {
                                                    final /* synthetic */ GLCanvas c;

                                                    public final void run() {
                                                        GLCanvas.a(this.c, uri, i);
                                                    }
                                                }, size.x, size.y, causeId);
                                                image.a(src, causeId);
                                                images.put(src, image);
                                            }
                                            textures.put(uniformName, image.a());
                                            units = units2;
                                        }
                                    }
                                    obj = null;
                                    if (value.hasKey("path")) {
                                        str2 = value.getString("path");
                                    }
                                    if (str2 == null) {
                                    }
                                    str = value.getString(ReactVideoViewManager.PROP_SRC_URI);
                                    src = a(str);
                                    if (src == null) {
                                        shader.a("texture uniform '" + uniformName + "': Invalid uri format '" + value + "' (causeId " + causeId + ")");
                                    }
                                    image = (g) images.get(src);
                                    if (image == null) {
                                        image = (g) prevImages.get(src);
                                        if (image != null) {
                                            FLog.i("RNGLCanvas", "Image %s from prevImages (causeId %x)", (Object) src, Integer.valueOf(causeId));
                                            images.put(src, image);
                                        }
                                    }
                                    if (image == null) {
                                        size = g();
                                        uri = src;
                                        i = causeId;
                                        image = new g(this, this.r.c(), /* anonymous class already generated */, size.x, size.y, causeId);
                                        image.a(src, causeId);
                                        images.put(src, image);
                                    }
                                    textures.put(uniformName, image.a());
                                    units = units2;
                                } else {
                                    shader.a("texture uniform '" + uniformName + "': Unexpected type '" + type + "' (causeId " + causeId + ")");
                                    units = units2;
                                }
                            }
                        }
                    }
                }
                Object maxTextureUnits = new int[1];
                GLES20.glGetIntegerv(34930, maxTextureUnits, 0);
                if (units > maxTextureUnits[0]) {
                    shader.a("Maximum number of texture reach. got " + units + " >= max " + maxTextureUnits + " (causeId " + causeId + ")");
                }
                for (String uniformName2 : uniformTypes.keySet()) {
                    if (!(uniformsFloat.containsKey(uniformName2) || uniformsInteger.containsKey(uniformName2) || uniformsFloatBuffer.containsKey(uniformName2) || uniformsIntBuffer.containsKey(uniformName2))) {
                        shader.a("All defined uniforms must be provided. Missing '" + uniformName2 + "' (causeId " + causeId + ")");
                    }
                }
                return new h(shader, uniformsInteger, uniformsFloat, uniformsIntBuffer, uniformsFloatBuffer, textures, Integer.valueOf((int) (data.c.doubleValue() * data.e.doubleValue())), Integer.valueOf((int) (data.d.doubleValue() * data.e.doubleValue())), data.f, contextChildren, children, causeId);
            }
            this.p.put(num, new i(shader2, num, this.b));
        }
        shader = (i) this.p.get(num);
        if (shader != null) {
        }
        return null;
    }

    private static FloatBuffer a(al array) {
        int size = array.size();
        FloatBuffer buf = ByteBuffer.allocateDirect(size * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        for (int i = 0; i < size; i++) {
            buf.put((float) array.getDouble(i));
        }
        buf.position(0);
        return buf;
    }

    private static IntBuffer b(al array) {
        int size = array.size();
        IntBuffer buf = ByteBuffer.allocateDirect(size * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        for (int i = 0; i < size; i++) {
            buf.put(array.getInt(i));
        }
        buf.position(0);
        return buf;
    }

    private static int b(int type) {
        switch (type) {
            case 35664:
            case 35667:
            case 35671:
                return 2;
            case 35665:
            case 35668:
            case 35672:
                return 3;
            case 35666:
            case 35669:
            case 35673:
            case 35674:
                return 4;
            case 35675:
                return 9;
            case 35676:
                return 16;
            default:
                throw new Error("Invalid array type: " + type);
        }
    }

    private void a(h renderData) {
        int causeId = renderData.l;
        int w = renderData.g.intValue();
        int h = renderData.h.intValue();
        for (h child : renderData.j) {
            a(child);
        }
        for (h child2 : renderData.k) {
            a(child2);
        }
        if (renderData.i.intValue() == -1) {
            FLog.i("RNGLCanvas", "recRender glBindFramebuffer default FBO %d (causeId %x)", Integer.valueOf(this.g), Integer.valueOf(causeId));
            GLES20.glBindFramebuffer(36160, this.g);
            GLES20.glViewport(0, 0, w, h);
            GLES20.glBlendFunc(1, 771);
        } else {
            f fbo = a(renderData.i, causeId);
            fbo.a(w, h, causeId);
            fbo.b(causeId);
            GLES20.glBlendFunc(770, 771);
        }
        renderData.a.a();
        for (String uniformName : renderData.f.keySet()) {
            ((l) renderData.f.get(uniformName)).a(((Integer) renderData.b.get(uniformName)).intValue(), causeId);
        }
        Map<String, Integer> uniformTypes = renderData.a.b();
        for (String uniformName2 : renderData.b.keySet()) {
            renderData.a.a(uniformName2, (Integer) renderData.b.get(uniformName2));
        }
        for (String uniformName22 : renderData.c.keySet()) {
            renderData.a.a(uniformName22, (Float) renderData.c.get(uniformName22));
        }
        for (String uniformName222 : renderData.e.keySet()) {
            renderData.a.a(uniformName222, (FloatBuffer) renderData.e.get(uniformName222), ((Integer) uniformTypes.get(uniformName222)).intValue());
        }
        for (String uniformName2222 : renderData.d.keySet()) {
            renderData.a.a(uniformName2222, (IntBuffer) renderData.d.get(uniformName2222), ((Integer) uniformTypes.get(uniformName2222)).intValue());
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        GLES20.glDrawArrays(4, 0, 6);
    }

    public final void a(a config) {
        FLog.i("RNGLCanvas", "requestCaptureFrame");
        for (a equals : this.t) {
            if (equals.equals(config)) {
                e();
                return;
            }
        }
        this.t.add(config);
        e();
    }

    private static Bitmap c(int w, int h) {
        int[] bitmapBuffer = new int[(w * h)];
        IntBuffer intBuffer = IntBuffer.wrap(bitmapBuffer);
        intBuffer.position(0);
        int[] bufferLine = new int[w];
        try {
            GLES20.glReadPixels(0, 0, w, h, 6408, 5121, intBuffer);
            int count = (h + 1) / 2;
            for (int i = 0; i < count; i++) {
                int j;
                int offset1 = i * w;
                int offset2 = ((h - i) - 1) * w;
                boolean swap = offset1 != offset2;
                if (swap) {
                    System.arraycopy(bitmapBuffer, offset2, bufferLine, 0, w);
                }
                for (j = 0; j < w; j++) {
                    bitmapBuffer[offset2 + j] = c(bitmapBuffer[offset1 + j]);
                }
                if (swap) {
                    for (j = 0; j < w; j++) {
                        bitmapBuffer[offset1 + j] = c(bufferLine[j]);
                    }
                }
            }
            try {
                return Bitmap.createBitmap(bitmapBuffer, w, h, Config.ARGB_8888);
            } catch (Throwable f) {
                FLog.e("RNGLCanvas", "failed to create bitmap with exception ", f);
                return null;
            }
        } catch (GLException e) {
            return null;
        }
    }

    private static int c(int texturePixel) {
        return ((-16711936 & texturePixel) | ((texturePixel << 16) & 16711680)) | ((texturePixel >> 16) & 255);
    }

    public final q c_() {
        return this.w;
    }

    final void a(q pointerEvents) {
        this.w = pointerEvents;
    }

    public void setPixelRatio(float pixelRatio) {
        this.u = pixelRatio;
        getWidth();
        getHeight();
        e();
    }

    public final boolean a(int width, int height) {
        String str;
        Closeable byteArrayOutputStream;
        Throwable e;
        Closeable closeable;
        ar writableNativeMap;
        ar writableNativeMap2;
        Throwable th;
        final int causeId = GLCanvasManager.causeId();
        FLog.i("RNGLCanvas", "render %d x %d with causeId %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
        Queue queue = this.s;
        synchronized (queue) {
            while (!queue.isEmpty()) {
                ((Runnable) queue.poll()).run();
            }
        }
        if (this.n.size() != this.h) {
            b(this.h, causeId);
        }
        if (f()) {
            FLog.i("RNGLCanvas", "render haveRemainingToPreload render");
            if (this.d) {
                FLog.i("RNGLCanvas", "render neverRendered glClear (causeId %x)", Integer.valueOf(causeId));
                this.d = false;
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                GLES20.glClear(16384);
            }
            return false;
        }
        this.d = false;
        boolean shouldRenderNow = this.e || this.i || this.h == 0;
        if (this.h > 0) {
            this.a.a(new Runnable(this) {
                final /* synthetic */ GLCanvas b;

                public final void run() {
                    FLog.i("RNGLCanvas", "render syncContentBitmaps deferredRendering %b (causeId %x)", Boolean.valueOf(this.b.e), Integer.valueOf(causeId));
                    this.b.b();
                    if (!this.b.e) {
                        this.b.e = true;
                        this.b.e();
                    }
                }
            });
        }
        if (shouldRenderNow) {
            FLog.i("RNGLCanvas", "render shouldRenderNow (causeId %x)", Integer.valueOf(causeId));
            h hVar = this.f;
            if (hVar != null) {
                int i = hVar.l;
                int min = Math.min(this.n.size(), this.o.size());
                for (int i2 = 0; i2 < min; i2++) {
                    ((l) this.n.get(i2)).a((Bitmap) this.o.get(i2), i);
                }
                int[] iArr = new int[1];
                GLES20.glGetIntegerv(36006, iArr, 0);
                this.g = iArr[0];
                GLES20.glEnable(3042);
                a(hVar);
                GLES20.glDisable(3042);
                GLES20.glBindFramebuffer(36160, this.g);
                GLES20.glBindBuffer(34962, 0);
                if (this.c && !f()) {
                    this.c = false;
                    ((RCTEventEmitter) ((ai) getContext()).a(RCTEventEmitter.class)).receiveEvent(getId(), "load", new WritableNativeMap());
                }
            }
            this.e = false;
            if (this.t.size() > 0) {
                FLog.i("RNGLCanvas", "capture");
                Bitmap c = c(getWidth(), getHeight());
                RCTEventEmitter rCTEventEmitter = (RCTEventEmitter) ((ai) getContext()).a(RCTEventEmitter.class);
                for (a aVar : this.t) {
                    String str2;
                    String str3 = aVar.d != null ? "file://" + aVar.d : null;
                    boolean equals = aVar.b.equals("png");
                    Object obj = (equals || !(aVar.b.equals("jpg") || aVar.b.equals("jpeg"))) ? null : 1;
                    Object obj2 = (!equals && obj == null && aVar.b.equals("webm")) ? 1 : null;
                    boolean equals2 = aVar.a.equals("base64");
                    Object obj3 = (equals2 || !aVar.a.equals("file")) ? null : 1;
                    CompressFormat compressFormat = equals ? CompressFormat.PNG : obj != null ? CompressFormat.JPEG : obj2 != null ? CompressFormat.WEBP : null;
                    int doubleValue = (int) (100.0d * aVar.e.doubleValue());
                    if (c == null) {
                        str2 = "Failed to generate bitmap";
                        str = null;
                    } else if (compressFormat == null) {
                        str2 = "Unsupported capture type '" + aVar.b + "'";
                        str = null;
                    } else if (equals2) {
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream(32768);
                            try {
                                byteArrayOutputStream.write("data:image/png;base64,".getBytes());
                                Closeable base64OutputStream = new Base64OutputStream(null, 0);
                                try {
                                    c.compress(compressFormat, doubleValue, base64OutputStream);
                                    str2 = byteArrayOutputStream.toString();
                                    a(base64OutputStream);
                                    a(byteArrayOutputStream);
                                    str = str2;
                                    str2 = null;
                                } catch (Exception e2) {
                                    e = e2;
                                    closeable = byteArrayOutputStream;
                                    byteArrayOutputStream = base64OutputStream;
                                    try {
                                        FLog.w("RNGLCanvas", "can't capture stream", e);
                                        str2 = "Could not capture as base64: " + e.getMessage();
                                        a(byteArrayOutputStream);
                                        a(closeable);
                                        str = null;
                                        writableNativeMap = new WritableNativeMap();
                                        writableNativeMap2 = new WritableNativeMap();
                                        writableNativeMap2.putString("format", aVar.a);
                                        writableNativeMap2.putString("type", aVar.b);
                                        writableNativeMap2.putString("filePath", aVar.c);
                                        writableNativeMap2.putDouble("quality", aVar.e.doubleValue());
                                        writableNativeMap.putMap("config", writableNativeMap2);
                                        if (str2 != null) {
                                            if (str3 == null) {
                                                FLog.w("RNGLCanvas", "using backup result");
                                                str = str3;
                                            } else {
                                                writableNativeMap.putString("error", str2);
                                                FLog.w("RNGLCanvas", str2);
                                            }
                                        }
                                        if (str != null) {
                                            writableNativeMap.putString("result", str);
                                            FLog.i("RNGLCanvas", str);
                                        }
                                        rCTEventEmitter.receiveEvent(getId(), "captureFrame", writableNativeMap);
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    closeable = byteArrayOutputStream;
                                    byteArrayOutputStream = base64OutputStream;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                closeable = byteArrayOutputStream;
                                byteArrayOutputStream = null;
                                FLog.w("RNGLCanvas", "can't capture stream", e);
                                str2 = "Could not capture as base64: " + e.getMessage();
                                a(byteArrayOutputStream);
                                a(closeable);
                                str = null;
                                writableNativeMap = new WritableNativeMap();
                                writableNativeMap2 = new WritableNativeMap();
                                writableNativeMap2.putString("format", aVar.a);
                                writableNativeMap2.putString("type", aVar.b);
                                writableNativeMap2.putString("filePath", aVar.c);
                                writableNativeMap2.putDouble("quality", aVar.e.doubleValue());
                                writableNativeMap.putMap("config", writableNativeMap2);
                                if (str2 != null) {
                                    if (str3 == null) {
                                        writableNativeMap.putString("error", str2);
                                        FLog.w("RNGLCanvas", str2);
                                    } else {
                                        FLog.w("RNGLCanvas", "using backup result");
                                        str = str3;
                                    }
                                }
                                if (str != null) {
                                    writableNativeMap.putString("result", str);
                                    FLog.i("RNGLCanvas", str);
                                }
                                rCTEventEmitter.receiveEvent(getId(), "captureFrame", writableNativeMap);
                            } catch (Throwable th4) {
                                th = th4;
                                closeable = byteArrayOutputStream;
                                byteArrayOutputStream = null;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            closeable = null;
                            byteArrayOutputStream = null;
                            FLog.w("RNGLCanvas", "can't capture stream", e);
                            str2 = "Could not capture as base64: " + e.getMessage();
                            a(byteArrayOutputStream);
                            a(closeable);
                            str = null;
                            writableNativeMap = new WritableNativeMap();
                            writableNativeMap2 = new WritableNativeMap();
                            writableNativeMap2.putString("format", aVar.a);
                            writableNativeMap2.putString("type", aVar.b);
                            writableNativeMap2.putString("filePath", aVar.c);
                            writableNativeMap2.putDouble("quality", aVar.e.doubleValue());
                            writableNativeMap.putMap("config", writableNativeMap2);
                            if (str2 != null) {
                                if (str3 == null) {
                                    writableNativeMap.putString("error", str2);
                                    FLog.w("RNGLCanvas", str2);
                                } else {
                                    FLog.w("RNGLCanvas", "using backup result");
                                    str = str3;
                                }
                            }
                            if (str != null) {
                                writableNativeMap.putString("result", str);
                                FLog.i("RNGLCanvas", str);
                            }
                            rCTEventEmitter.receiveEvent(getId(), "captureFrame", writableNativeMap);
                        } catch (Throwable th5) {
                            th = th5;
                            closeable = null;
                            byteArrayOutputStream = null;
                        }
                    } else if (obj3 != null) {
                        try {
                            byteArrayOutputStream = new BufferedOutputStream(new FileOutputStream(aVar.c));
                            try {
                                c.compress(compressFormat, doubleValue, byteArrayOutputStream);
                                str2 = "file://" + aVar.c;
                                a(byteArrayOutputStream);
                                str = str2;
                                str2 = null;
                            } catch (Exception e5) {
                                e = e5;
                                closeable = byteArrayOutputStream;
                                try {
                                    FLog.w("RNGLCanvas", "failed to record image to file", e);
                                    str2 = "Could not write file: " + e.getMessage();
                                    a(closeable);
                                    str = null;
                                    writableNativeMap = new WritableNativeMap();
                                    writableNativeMap2 = new WritableNativeMap();
                                    writableNativeMap2.putString("format", aVar.a);
                                    writableNativeMap2.putString("type", aVar.b);
                                    writableNativeMap2.putString("filePath", aVar.c);
                                    writableNativeMap2.putDouble("quality", aVar.e.doubleValue());
                                    writableNativeMap.putMap("config", writableNativeMap2);
                                    if (str2 != null) {
                                        if (str3 == null) {
                                            FLog.w("RNGLCanvas", "using backup result");
                                            str = str3;
                                        } else {
                                            writableNativeMap.putString("error", str2);
                                            FLog.w("RNGLCanvas", str2);
                                        }
                                    }
                                    if (str != null) {
                                        writableNativeMap.putString("result", str);
                                        FLog.i("RNGLCanvas", str);
                                    }
                                    rCTEventEmitter.receiveEvent(getId(), "captureFrame", writableNativeMap);
                                } catch (Throwable th6) {
                                    th = th6;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                closeable = byteArrayOutputStream;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            closeable = null;
                            FLog.w("RNGLCanvas", "failed to record image to file", e);
                            str2 = "Could not write file: " + e.getMessage();
                            a(closeable);
                            str = null;
                            writableNativeMap = new WritableNativeMap();
                            writableNativeMap2 = new WritableNativeMap();
                            writableNativeMap2.putString("format", aVar.a);
                            writableNativeMap2.putString("type", aVar.b);
                            writableNativeMap2.putString("filePath", aVar.c);
                            writableNativeMap2.putDouble("quality", aVar.e.doubleValue());
                            writableNativeMap.putMap("config", writableNativeMap2);
                            if (str2 != null) {
                                if (str3 == null) {
                                    writableNativeMap.putString("error", str2);
                                    FLog.w("RNGLCanvas", str2);
                                } else {
                                    FLog.w("RNGLCanvas", "using backup result");
                                    str = str3;
                                }
                            }
                            if (str != null) {
                                writableNativeMap.putString("result", str);
                                FLog.i("RNGLCanvas", str);
                            }
                            rCTEventEmitter.receiveEvent(getId(), "captureFrame", writableNativeMap);
                        } catch (Throwable th8) {
                            th = th8;
                            closeable = null;
                        }
                    } else {
                        str2 = "Unsupported capture format '" + aVar.a + "'";
                        str = null;
                    }
                    writableNativeMap = new WritableNativeMap();
                    writableNativeMap2 = new WritableNativeMap();
                    writableNativeMap2.putString("format", aVar.a);
                    writableNativeMap2.putString("type", aVar.b);
                    writableNativeMap2.putString("filePath", aVar.c);
                    writableNativeMap2.putDouble("quality", aVar.e.doubleValue());
                    writableNativeMap.putMap("config", writableNativeMap2);
                    if (str2 != null) {
                        if (str3 == null) {
                            FLog.w("RNGLCanvas", "using backup result");
                            str = str3;
                        } else {
                            writableNativeMap.putString("error", str2);
                            FLog.w("RNGLCanvas", str2);
                        }
                    }
                    if (str != null) {
                        writableNativeMap.putString("result", str);
                        FLog.i("RNGLCanvas", str);
                    }
                    rCTEventEmitter.receiveEvent(getId(), "captureFrame", writableNativeMap);
                }
                this.t = new ArrayList();
            }
        }
        return true;
        a(closeable);
        throw th;
        a(byteArrayOutputStream);
        a(closeable);
        throw th;
    }

    public final void c() {
        int causeId = GLCanvasManager.causeId();
        FLog.i("RNGLCanvas", "attach with causeId %x", Integer.valueOf(causeId));
        this.q = new HashMap();
        this.p = new HashMap();
        this.m = new HashMap();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.l = new ArrayList();
        this.f = null;
        a(causeId);
        this.a.a((v) this);
    }

    public final void d() {
        int causeId = GLCanvasManager.causeId();
        FLog.i("RNGLCanvas", "detach with causeId %x", Integer.valueOf(causeId));
        List<Uri> uriToRemove = new ArrayList();
        for (Uri imageUri : this.m.keySet()) {
            ((g) this.m.get(imageUri)).a().a(causeId);
            uriToRemove.add(imageUri);
        }
        for (Uri imageUri2 : uriToRemove) {
            this.m.remove(imageUri2);
        }
        List<Integer> fboToRemove = new ArrayList();
        for (Integer intValue : this.q.keySet()) {
            int fboId = intValue.intValue();
            ((f) this.q.get(Integer.valueOf(fboId))).a(causeId);
            fboToRemove.add(Integer.valueOf(fboId));
        }
        for (Integer intValue2 : fboToRemove) {
            this.q.remove(Integer.valueOf(intValue2.intValue()));
        }
        this.a.b((v) this);
    }

    public void onHostResume() {
        FLog.i("RNGLCanvas", "onHostResume");
        e();
    }

    public void onHostPause() {
    }

    public void onHostDestroy() {
    }

    static /* synthetic */ void a(GLCanvas x0, Uri x1, int x2) {
        int i;
        FLog.i("RNGLCanvas", "onImageLoad (causeId %x)", Integer.valueOf(x2));
        x0.l.add(x1);
        int i2 = 0;
        Iterator it = x0.k.iterator();
        while (true) {
            i = i2;
            if (!it.hasNext()) {
                break;
            }
            if (x0.l.contains((Uri) it.next())) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
        }
        i2 = x0.k.size();
        double d = ((double) i) / ((double) i2);
        ar writableNativeMap = new WritableNativeMap();
        String str = VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY;
        if (Double.isNaN(d)) {
            d = 0.0d;
        }
        writableNativeMap.putDouble(str, d);
        writableNativeMap.putInt("loaded", i);
        writableNativeMap.putInt("total", i2);
        ((RCTEventEmitter) ((ai) x0.getContext()).a(RCTEventEmitter.class)).receiveEvent(x0.getId(), VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, writableNativeMap);
        x0.c = true;
        x0.a(x2);
    }
}
