package com.projectseptember.RNGL;

import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public final class i {
    private final String a;
    private final String b;
    private final String c;
    private Map<String, Integer> d;
    private int e;
    private int[] f;
    private int g;
    private Map<String, Integer> h;
    private Integer i;
    private RNGLContext j;
    private j k;

    public i(k data, Integer id, RNGLContext rnglContext) {
        this.a = data.a;
        this.b = data.b;
        this.c = data.c;
        this.i = id;
        this.j = rnglContext;
    }

    protected final void finalize() throws Throwable {
        super.finalize();
        if (this.f != null) {
            GLES20.glDeleteProgram(this.e);
            GLES20.glDeleteBuffers(1, this.f, 0);
        }
    }

    public final void a(String msg) {
        throw new j(this.a, msg);
    }

    public final void a() {
        c();
        if (!GLES20.glIsProgram(this.e)) {
            a("not a program");
        }
        GLES20.glUseProgram(this.e);
        GLES20.glBindBuffer(34962, this.f[0]);
        GLES20.glEnableVertexAttribArray(this.g);
        GLES20.glVertexAttribPointer(this.g, 2, 5126, false, 0, 0);
    }

    public final void a(String name, Integer i) {
        GLES20.glUniform1i(((Integer) this.h.get(name)).intValue(), i.intValue());
    }

    public final void a(String name, Float f) {
        GLES20.glUniform1f(((Integer) this.h.get(name)).intValue(), f.floatValue());
    }

    public final void a(String name, FloatBuffer buf, int type) {
        switch (type) {
            case 35664:
                GLES20.glUniform2fv(((Integer) this.h.get(name)).intValue(), 1, buf);
                return;
            case 35665:
                GLES20.glUniform3fv(((Integer) this.h.get(name)).intValue(), 1, buf);
                return;
            case 35666:
                GLES20.glUniform4fv(((Integer) this.h.get(name)).intValue(), 1, buf);
                return;
            case 35674:
                GLES20.glUniformMatrix2fv(((Integer) this.h.get(name)).intValue(), 1, false, buf);
                return;
            case 35675:
                GLES20.glUniformMatrix3fv(((Integer) this.h.get(name)).intValue(), 1, false, buf);
                return;
            case 35676:
                GLES20.glUniformMatrix4fv(((Integer) this.h.get(name)).intValue(), 1, false, buf);
                return;
            default:
                a("Unsupported case: uniform '" + name + "' type: " + type);
                return;
        }
    }

    public final void a(String name, IntBuffer buf, int type) {
        switch (type) {
            case 35667:
            case 35671:
                GLES20.glUniform2iv(((Integer) this.h.get(name)).intValue(), 1, buf);
                return;
            case 35668:
            case 35672:
                GLES20.glUniform3iv(((Integer) this.h.get(name)).intValue(), 1, buf);
                return;
            case 35669:
            case 35673:
                GLES20.glUniform4iv(((Integer) this.h.get(name)).intValue(), 1, buf);
                return;
            default:
                a("Unsupported case: uniform '" + name + "' type: " + type);
                return;
        }
    }

    public final Map<String, Integer> b() {
        return this.d;
    }

    private int a(String code, int shaderType) {
        int shaderHandle = GLES20.glCreateShader(shaderType);
        GLES20.glShaderSource(shaderHandle, code);
        GLES20.glCompileShader(shaderHandle);
        int[] compileSuccess = new int[1];
        GLES20.glGetShaderiv(shaderHandle, 35713, compileSuccess, 0);
        if (compileSuccess[0] != 0) {
            return shaderHandle;
        }
        a(GLES20.glGetShaderInfoLog(shaderHandle));
        return -1;
    }

    private boolean d() {
        return (this.f == null || this.h == null) ? false : true;
    }

    public final boolean c() {
        if (!d()) {
            if (this.k != null) {
                throw this.k;
            }
            try {
                int a = a(this.b, 35633);
                if (a != -1) {
                    int a2 = a(this.c, 35632);
                    if (a2 != -1) {
                        this.e = GLES20.glCreateProgram();
                        GLES20.glAttachShader(this.e, a);
                        GLES20.glAttachShader(this.e, a2);
                        GLES20.glLinkProgram(this.e);
                        int[] iArr = new int[1];
                        GLES20.glGetProgramiv(this.e, 35714, iArr, 0);
                        if (iArr[0] == 0) {
                            a(GLES20.glGetProgramInfoLog(this.e));
                        }
                        GLES20.glUseProgram(this.e);
                        GLES20.glValidateProgram(this.e);
                        iArr = new int[1];
                        GLES20.glGetProgramiv(this.e, 35715, iArr, 0);
                        if (iArr[0] == 0) {
                            GLES20.glGetProgramInfoLog(this.e);
                            a(GLES20.glGetProgramInfoLog(this.e));
                        }
                        Map hashMap = new HashMap();
                        Map hashMap2 = new HashMap();
                        int[] iArr2 = new int[1];
                        int[] iArr3 = new int[1];
                        int[] iArr4 = new int[1];
                        GLES20.glGetProgramiv(this.e, 35718, iArr2, 0);
                        for (int i = 0; i < iArr2[0]; i++) {
                            String glGetActiveUniform = GLES20.glGetActiveUniform(this.e, i, iArr4, 0, iArr3, 0);
                            int glGetUniformLocation = GLES20.glGetUniformLocation(this.e, glGetActiveUniform);
                            hashMap.put(glGetActiveUniform, Integer.valueOf(iArr3[0]));
                            hashMap2.put(glGetActiveUniform, Integer.valueOf(glGetUniformLocation));
                        }
                        this.d = hashMap;
                        this.h = hashMap2;
                        this.g = GLES20.glGetAttribLocation(this.e, "position");
                        this.f = new int[1];
                        GLES20.glGenBuffers(1, this.f, 0);
                        GLES20.glBindBuffer(34962, this.f[0]);
                        float[] fArr = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f};
                        Buffer asFloatBuffer = ByteBuffer.allocateDirect(48).order(ByteOrder.nativeOrder()).asFloatBuffer();
                        asFloatBuffer.put(fArr).position(0);
                        GLES20.glBufferData(34962, 48, asFloatBuffer, 35044);
                    }
                }
                this.j.shaderSucceedToCompile(this.i, this.d);
            } catch (j e) {
                this.k = e;
                this.j.shaderFailedToCompile(this.i, e);
                throw e;
            }
        }
        return d();
    }
}
