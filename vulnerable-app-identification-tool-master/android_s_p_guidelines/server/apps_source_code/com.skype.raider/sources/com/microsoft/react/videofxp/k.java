package com.microsoft.react.videofxp;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.an;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

final class k {
    private static final float[] l = new float[]{0.3f, 0.3f, 0.3f, 0.0f, 0.6f, 0.6f, 0.6f, 0.0f, 0.1f, 0.1f, 0.1f, 0.0f, 0.2f, 0.0f, -0.2f, 1.0f};
    private static final float[] m = new float[]{0.3f, 0.3f, 0.3f, 0.0f, 0.6f, 0.6f, 0.6f, 0.0f, 0.1f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] n = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private Bitmap A;
    private int B;
    private int C;
    private final float[] a;
    private FloatBuffer b;
    private float[] c;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int h;
    private float i;
    private int j;
    private float k;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private float y;
    private boolean z;

    public static int a(String lensMode) {
        int i = -1;
        switch (lensMode.hashCode()) {
            case -1008851410:
                if (lensMode.equals("orange")) {
                    i = 6;
                    break;
                }
                break;
            case -976943172:
                if (lensMode.equals("purple")) {
                    i = 7;
                    break;
                }
                break;
            case -905411385:
                if (lensMode.equals("grayscale")) {
                    i = 1;
                    break;
                }
                break;
            case -734239628:
                if (lensMode.equals("yellow")) {
                    i = 5;
                    break;
                }
                break;
            case 112785:
                if (lensMode.equals("red")) {
                    i = 2;
                    break;
                }
                break;
            case 3027034:
                if (lensMode.equals("blue")) {
                    i = 4;
                    break;
                }
                break;
            case 98619139:
                if (lensMode.equals("green")) {
                    i = 3;
                    break;
                }
                break;
            case 100313435:
                if (lensMode.equals("image")) {
                    i = 8;
                    break;
                }
                break;
            case 109324790:
                if (lensMode.equals("sepia")) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 0;
            default:
                throw new RuntimeException("Unexpected lens string " + lensMode);
        }
    }

    public k(int lensMode, float lensIntensity, Bitmap overlay) {
        this.a = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
        this.c = new float[16];
        this.d = new float[16];
        this.e = new float[16];
        this.f = new float[16];
        this.g = new float[16];
        this.h = 0;
        this.i = 0.0f;
        this.j = 0;
        this.k = 0.0f;
        this.p = -12345;
        this.C = -12345;
        FLog.i("TextureRender", "TextureRender %d %f %b", Integer.valueOf(lensMode), Float.valueOf(lensIntensity), Boolean.valueOf(overlay != null));
        this.b = ByteBuffer.allocateDirect(this.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.b.put(this.a).position(0);
        c(lensMode, lensIntensity);
        this.z = true;
        this.A = overlay;
    }

    public k() {
        this.a = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
        this.c = new float[16];
        this.d = new float[16];
        this.e = new float[16];
        this.f = new float[16];
        this.g = new float[16];
        this.h = 0;
        this.i = 0.0f;
        this.j = 0;
        this.k = 0.0f;
        this.p = -12345;
        this.C = -12345;
        FLog.i("TextureRender", "TextureRender");
        this.b = ByteBuffer.allocateDirect(this.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.b.put(this.a).position(0);
        this.z = false;
    }

    public final int a() {
        FLog.i("TextureRender", "TextureRender returning " + this.p);
        return this.p;
    }

    public final int b() {
        int mode = this.y >= 0.5f ? this.h : this.j;
        FLog.i("TextureRender", "lens mode is " + mode);
        return mode;
    }

    public final float c() {
        float intensity = this.y >= 0.5f ? this.i : this.k;
        FLog.i("TextureRender", "lens intensity is " + intensity);
        return intensity;
    }

    private void c(int lensMode, float intensity) {
        boolean z = intensity >= 0.0f && intensity <= 1.0f;
        an.a(z, "intensity must be between 0 and 1");
        int i;
        float[] fArr;
        float f;
        switch (lensMode) {
            case 1:
                this.e = (float[]) l.clone();
                for (i = 0; i < this.e.length; i++) {
                    this.e[i] = (l[i] * intensity) + (n[i] * (1.0f - intensity));
                }
                return;
            case 2:
                this.e = (float[]) m.clone();
                for (i = 0; i < this.e.length; i++) {
                    this.e[i] = (m[i] * intensity) + (n[i] * (1.0f - intensity));
                }
                return;
            case 3:
                this.e = (float[]) n.clone();
                fArr = this.e;
                float f2 = 1.0f - intensity;
                this.e[10] = f2;
                fArr[5] = f2;
                return;
            case 4:
                this.e = (float[]) n.clone();
                fArr = this.e;
                f = 1.0f - intensity;
                this.e[10] = f;
                fArr[0] = f;
                return;
            case 5:
                this.e = (float[]) n.clone();
                fArr = this.e;
                f = 1.0f - intensity;
                this.e[5] = f;
                fArr[0] = f;
                return;
            case 6:
                this.e = (float[]) n.clone();
                this.e[10] = 1.0f - intensity;
                return;
            case 7:
                this.e = (float[]) n.clone();
                this.e[5] = 1.0f - (0.5f * intensity);
                this.e[10] = 1.0f - intensity;
                return;
            case 8:
                this.e = (float[]) n.clone();
                this.e[5] = 1.0f - (0.5f * intensity);
                return;
            default:
                this.e = (float[]) n.clone();
                return;
        }
    }

    public final void a(int lensMode, float intensity) {
        FLog.i("TextureRender", "setLensModeLeft " + lensMode + " " + intensity);
        boolean z = intensity >= 0.0f && intensity <= 1.0f;
        an.a(z, "intensity must be between 0 and 1");
        int i;
        float[] fArr;
        float f;
        switch (lensMode) {
            case 1:
                this.f = (float[]) l.clone();
                for (i = 0; i < this.f.length; i++) {
                    this.f[i] = (l[i] * intensity) + (n[i] * (1.0f - intensity));
                }
                break;
            case 2:
                this.f = (float[]) m.clone();
                for (i = 0; i < this.f.length; i++) {
                    this.f[i] = (m[i] * intensity) + (n[i] * (1.0f - intensity));
                }
                break;
            case 3:
                this.f = (float[]) n.clone();
                fArr = this.f;
                float f2 = 1.0f - intensity;
                this.f[10] = f2;
                fArr[5] = f2;
                break;
            case 4:
                this.f = (float[]) n.clone();
                fArr = this.f;
                f = 1.0f - intensity;
                this.f[10] = f;
                fArr[0] = f;
                break;
            case 5:
                this.f = (float[]) n.clone();
                fArr = this.f;
                f = 1.0f - intensity;
                this.f[5] = f;
                fArr[0] = f;
                break;
            case 6:
                this.f = (float[]) n.clone();
                this.f[10] = 1.0f - intensity;
                break;
            case 7:
                this.f = (float[]) n.clone();
                this.f[5] = 1.0f - (0.5f * intensity);
                this.f[10] = 1.0f - intensity;
                break;
            case 8:
                this.f = (float[]) n.clone();
                this.f[5] = 1.0f - (0.5f * intensity);
                break;
            default:
                this.f = (float[]) n.clone();
                break;
        }
        this.h = lensMode;
        this.i = intensity;
    }

    public final void b(int lensMode, float intensity) {
        FLog.i("TextureRender", "setLensModeRight " + lensMode + " " + intensity);
        boolean z = intensity >= 0.0f && intensity <= 1.0f;
        an.a(z, "intensity must be between 0 and 1");
        int i;
        float[] fArr;
        float f;
        switch (lensMode) {
            case 1:
                this.g = (float[]) l.clone();
                for (i = 0; i < this.g.length; i++) {
                    this.g[i] = (l[i] * intensity) + (n[i] * (1.0f - intensity));
                }
                break;
            case 2:
                this.g = (float[]) m.clone();
                for (i = 0; i < this.g.length; i++) {
                    this.g[i] = (m[i] * intensity) + (n[i] * (1.0f - intensity));
                }
                break;
            case 3:
                this.g = (float[]) n.clone();
                fArr = this.g;
                float f2 = 1.0f - intensity;
                this.g[10] = f2;
                fArr[5] = f2;
                break;
            case 4:
                this.g = (float[]) n.clone();
                fArr = this.g;
                f = 1.0f - intensity;
                this.g[10] = f;
                fArr[0] = f;
                break;
            case 5:
                this.g = (float[]) n.clone();
                fArr = this.g;
                f = 1.0f - intensity;
                this.g[5] = f;
                fArr[0] = f;
                break;
            case 6:
                this.g = (float[]) n.clone();
                this.g[10] = 1.0f - intensity;
                break;
            case 7:
                this.g = (float[]) n.clone();
                this.g[5] = 1.0f - (0.5f * intensity);
                this.g[10] = 1.0f - intensity;
                break;
            case 8:
                Matrix.setIdentityM(this.g, 0);
                this.g[5] = 1.0f - (0.5f * intensity);
                break;
            default:
                this.g = (float[]) n.clone();
                break;
        }
        this.j = lensMode;
        this.k = intensity;
    }

    public final void a(float x) {
        FLog.i("TextureRender", "setLensOffsetX " + x);
        this.y = x;
    }

    public final void a(SurfaceTexture st) {
        boolean z = (this.C == -12345 && this.z) ? false : true;
        an.a(z, "Must have overlay texture if reencoding");
        if (this.C <= -12345 || this.z) {
            z = true;
        } else {
            z = false;
        }
        an.a(z, "Cannot have overlay texture if not reencoding");
        b("onDrawFrame start");
        st.getTransformMatrix(this.d);
        GLES20.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
        GLES20.glClear(16640);
        GLES20.glUseProgram(this.o);
        b("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.p);
        this.b.position(0);
        GLES20.glVertexAttribPointer(this.s, 3, 5126, false, 20, this.b);
        b("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.s);
        b("glEnableVertexAttribArray maPositionHandle");
        this.b.position(3);
        GLES20.glVertexAttribPointer(this.t, 2, 5126, false, 20, this.b);
        b("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.t);
        b("glEnableVertexAttribArray maTextureHandle");
        Matrix.setIdentityM(this.c, 0);
        GLES20.glUniformMatrix4fv(this.q, 1, false, this.c, 0);
        GLES20.glUniformMatrix4fv(this.r, 1, false, this.d, 0);
        if (this.z) {
            GLES20.glUniformMatrix4fv(this.u, 1, false, this.e, 0);
            b("glUniformMatrix4fv color");
            GLES20.glActiveTexture(33985);
            GLES20.glBindTexture(3553, this.C);
            b("glBindTexture overlay");
            GLES20.glUniform1i(this.B, 1);
        } else {
            GLES20.glUniformMatrix4fv(this.v, 1, false, this.f, 0);
            b("glUniformMatrix4fv color left");
            GLES20.glUniformMatrix4fv(this.w, 1, false, this.g, 0);
            b("glUniformMatrix4fv color right");
            GLES20.glUniform1f(this.x, this.y);
            b("glUniform1f lens offset x");
        }
        GLES20.glDrawArrays(5, 0, 4);
        b("glDrawArrays");
        GLES20.glFinish();
    }

    public final void d() {
        boolean useOverlay;
        String str;
        int i;
        if (this.A != null) {
            useOverlay = true;
        } else {
            useOverlay = false;
        }
        String str2 = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
        if (useOverlay) {
            str = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform sampler2D sOverlay;\nuniform mat4 uColorTransform;\nvoid main() {\n vec4 tex = texture2D(sTexture, vTextureCoord);\n vec4 texOver = texture2D(sOverlay, vTextureCoord);\n gl_FragColor = mix(uColorTransform * tex, texOver, texOver.a);\n gl_FragColor.a = tex.a;\n}\n";
        } else {
            str = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform mat4 uColorTransformLeft;\nuniform mat4 uColorTransformRight;\nuniform float x;\nvoid main() {\n vec4 tex = texture2D(sTexture, vTextureCoord);\n vec4 right = vec4(step(x, vTextureCoord.x));\n gl_FragColor = uColorTransformLeft * tex * (vec4(1.0) - right)\n  + uColorTransformRight * tex * right;\n gl_FragColor.a = tex.a;\n}\n";
        }
        int a = a(35633, str2);
        if (a == 0) {
            i = 0;
        } else {
            int a2 = a(35632, str);
            if (a2 == 0) {
                i = 0;
            } else {
                i = GLES20.glCreateProgram();
                b("glCreateProgram");
                if (i == 0) {
                    FLog.e("TextureRender", "Could not create program");
                }
                GLES20.glAttachShader(i, a);
                b("glAttachShader");
                GLES20.glAttachShader(i, a2);
                b("glAttachShader");
                GLES20.glLinkProgram(i);
                int[] iArr = new int[1];
                GLES20.glGetProgramiv(i, 35714, iArr, 0);
                if (iArr[0] != 1) {
                    FLog.e("TextureRender", "Could not link program: ");
                    FLog.e("TextureRender", GLES20.glGetProgramInfoLog(i));
                    GLES20.glDeleteProgram(i);
                    i = 0;
                }
            }
        }
        this.o = i;
        if (this.o == 0) {
            throw new RuntimeException("failed creating program");
        }
        this.s = GLES20.glGetAttribLocation(this.o, "aPosition");
        b("glGetAttribLocation aPosition");
        if (this.s == -1) {
            throw new RuntimeException("Could not get attrib location for aPosition");
        }
        this.t = GLES20.glGetAttribLocation(this.o, "aTextureCoord");
        b("glGetAttribLocation aTextureCoord");
        if (this.t == -1) {
            throw new RuntimeException("Could not get attrib location for aTextureCoord");
        }
        this.q = GLES20.glGetUniformLocation(this.o, "uMVPMatrix");
        b("glGetUniformLocation uMVPMatrix");
        if (this.q == -1) {
            throw new RuntimeException("Could not get attrib location for uMVPMatrix");
        }
        this.r = GLES20.glGetUniformLocation(this.o, "uSTMatrix");
        b("glGetUniformLocation uSTMatrix");
        if (this.r == -1) {
            throw new RuntimeException("Could not get attrib location for uSTMatrix");
        }
        int texCt;
        if (useOverlay) {
            this.u = GLES20.glGetUniformLocation(this.o, "uColorTransform");
            b("glGetUniformLocation uColorTransform");
            if (this.u == -1) {
                throw new RuntimeException("Could not get attrib location for uColorTransform");
            }
        }
        FLog.i("TextureRender", "Setting up uniforms with no overlay");
        this.v = GLES20.glGetUniformLocation(this.o, "uColorTransformLeft");
        b("glGetUniformLocation uColorTransformLeft");
        if (this.v == -1) {
            throw new RuntimeException("Could not get attrib location for uColorTransformLeft");
        }
        this.w = GLES20.glGetUniformLocation(this.o, "uColorTransformRight");
        b("glGetUniformLocation uColorTransformRight");
        if (this.w == -1) {
            throw new RuntimeException("Could not get attrib location for uColorTransformRight");
        }
        this.x = GLES20.glGetUniformLocation(this.o, "x");
        b("glGetUniformLocation x");
        if (this.x == -1) {
            throw new RuntimeException("Could not get attrib location for x");
        }
        GLES20.glDisable(2929);
        if (useOverlay) {
            texCt = 2;
        } else {
            texCt = 1;
        }
        int[] textures = new int[texCt];
        GLES20.glGenTextures(texCt, textures, 0);
        this.p = textures[0];
        GLES20.glBindTexture(36197, this.p);
        b("glBindTexture mTextureID");
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        b("glTexParameter");
        if (useOverlay) {
            this.B = GLES20.glGetUniformLocation(this.o, "sOverlay");
            b("glGetUniformLocation sOverlay");
            if (this.B == -1) {
                throw new RuntimeException("Could not get attrib location for sOverlay");
            }
            this.C = textures[1];
            GLES20.glBindTexture(3553, this.C);
            GLES20.glTexParameteri(3553, 10241, 9728);
            GLES20.glTexParameteri(3553, 10240, 9728);
            b("glTexParameter overlay");
            GLUtils.texImage2D(3553, 0, this.A, 0);
            b("texImage2D overlay");
            this.A.recycle();
            this.A = null;
        }
    }

    private static int a(int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        b("glCreateShader type=" + shaderType);
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, 35713, compiled, 0);
        if (compiled[0] != 0) {
            return shader;
        }
        FLog.e("TextureRender", "Could not compile shader " + shaderType + ":");
        FLog.e("TextureRender", " " + GLES20.glGetShaderInfoLog(shader));
        GLES20.glDeleteShader(shader);
        return 0;
    }

    public static void b(String op) {
        int error = GLES20.glGetError();
        if (error != 0) {
            FLog.e("TextureRender", op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }

    public static void a(String filename, int width, int height, float rescaleFactor) {
        int i;
        IOException ioe;
        Throwable th;
        ByteBuffer buf = ByteBuffer.allocateDirect((width * height) * 4);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        GLES20.glReadPixels(0, 0, width, height, 6408, 5121, buf);
        Object row = new byte[(width * 4)];
        FLog.i("TextureRender", "starting flip");
        for (i = 0; i < height / 2; i++) {
            buf.get(row);
            System.arraycopy(buf.array(), buf.limit() - buf.position(), buf.array(), buf.position() - row.length, row.length);
            System.arraycopy(row, 0, buf.array(), buf.limit() - buf.position(), row.length);
        }
        FLog.i("TextureRender", "done flip");
        buf.rewind();
        int pixelCount = width * height;
        int[] colors = new int[pixelCount];
        buf.asIntBuffer().get(colors);
        for (i = 0; i < pixelCount; i++) {
            int c = colors[i];
            colors[i] = ((-16711936 & c) | ((16711680 & c) >> 16)) | ((c & 255) << 16);
        }
        FileOutputStream fos = null;
        try {
            FileOutputStream fos2 = new FileOutputStream(filename);
            try {
                Bitmap createBitmap = Bitmap.createBitmap(colors, width, height, Config.ARGB_8888);
                Bitmap bmp = Bitmap.createScaledBitmap(createBitmap, (int) Math.floor((double) (((float) createBitmap.getWidth()) * rescaleFactor)), (int) Math.floor((double) (((float) createBitmap.getHeight()) * rescaleFactor)), false);
                if (createBitmap != bmp) {
                    createBitmap.recycle();
                }
                FLog.i("TextureRender", "Thumbnail bitmap compress " + bmp.compress(CompressFormat.JPEG, 70, fos2));
                bmp.recycle();
                try {
                    fos2.close();
                    FLog.i("TextureRender", "Saved thumbnail " + width + "x" + height + " frame as '" + filename + "'");
                } catch (Throwable ioe2) {
                    throw new RuntimeException("Failed to close file " + filename, ioe2);
                }
            } catch (IOException e) {
                ioe = e;
                fos = fos2;
                try {
                    throw new RuntimeException("Failed to write file " + filename, ioe);
                } catch (Throwable th2) {
                    th = th2;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (Throwable ioe22) {
                            throw new RuntimeException("Failed to close file " + filename, ioe22);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                if (fos != null) {
                    fos.close();
                }
                throw th;
            }
        } catch (IOException e2) {
            ioe = e2;
            throw new RuntimeException("Failed to write file " + filename, ioe);
        }
    }
}
