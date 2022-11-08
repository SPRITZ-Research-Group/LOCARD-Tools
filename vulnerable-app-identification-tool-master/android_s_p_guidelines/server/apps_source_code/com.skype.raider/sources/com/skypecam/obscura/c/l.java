package com.skypecam.obscura.c;

import android.opengl.GLES20;
import com.skypecam.obscura.e.g;
import java.nio.FloatBuffer;

public final class l {
    int[] a;
    int[] b;
    private a c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m = false;
    private int n = -1;
    private int o = -1;
    private int p = -1;
    private float[] q = new float[9];
    private float[] r;
    private float s;

    public enum a {
        TEXTURE_2D,
        TEXTURE_EXT,
        TEXTURE_EXT_BW,
        TEXTURE_EXT_FILT
    }

    public l(a programType) {
        this.c = programType;
        switch (programType) {
            case TEXTURE_2D:
                this.l = 3553;
                this.d = e.a("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
                break;
            case TEXTURE_EXT:
                this.l = 36197;
                this.d = e.a("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
                break;
            case TEXTURE_EXT_BW:
                this.l = 36197;
                this.d = e.a("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec4 tc = texture2D(sTexture, vTextureCoord);\n    float color = tc.r * 0.3 + tc.g * 0.59 + tc.b * 0.11;\n    gl_FragColor = vec4(color, color, color, 1.0);\n}\n");
                break;
            case TEXTURE_EXT_FILT:
                this.l = 36197;
                this.d = e.a("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\n#define KERNEL_SIZE 9\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float uKernel[KERNEL_SIZE];\nuniform vec2 uTexOffset[KERNEL_SIZE];\nuniform float uColorAdjust;\nvoid main() {\n    int i = 0;\n    vec4 sum = vec4(0.0);\n    if (vTextureCoord.x < vTextureCoord.y - 0.005) {\n        for (i = 0; i < KERNEL_SIZE; i++) {\n            vec4 texc = texture2D(sTexture, vTextureCoord + uTexOffset[i]);\n            sum += texc * uKernel[i];\n        }\n    sum += uColorAdjust;\n    } else if (vTextureCoord.x > vTextureCoord.y + 0.005) {\n        sum = texture2D(sTexture, vTextureCoord);\n    } else {\n        sum.r = 1.0;\n    }\n    gl_FragColor = sum;\n}\n");
                break;
            default:
                throw new RuntimeException("Unhandled type " + programType);
        }
        if (this.d == 0) {
            throw new RuntimeException("Unable to create program");
        }
        g.a().a("Grafika", "Created program " + this.d + " (" + programType + ")");
        this.j = GLES20.glGetAttribLocation(this.d, "aPosition");
        e.a(this.j, "aPosition");
        this.k = GLES20.glGetAttribLocation(this.d, "aTextureCoord");
        e.a(this.k, "aTextureCoord");
        this.e = GLES20.glGetUniformLocation(this.d, "uMVPMatrix");
        e.a(this.e, "uMVPMatrix");
        this.f = GLES20.glGetUniformLocation(this.d, "uTexMatrix");
        e.a(this.f, "uTexMatrix");
        this.g = GLES20.glGetUniformLocation(this.d, "uKernel");
        if (this.g < 0) {
            this.g = -1;
            this.h = -1;
            this.i = -1;
            return;
        }
        this.h = GLES20.glGetUniformLocation(this.d, "uTexOffset");
        e.a(this.h, "uTexOffset");
        this.i = GLES20.glGetUniformLocation(this.d, "uColorAdjust");
        e.a(this.i, "uColorAdjust");
        System.arraycopy(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f}, 0, this.q, 0, 9);
        this.s = 0.0f;
        this.r = new float[]{-0.00390625f, -0.00390625f, 0.0f, -0.00390625f, 0.00390625f, -0.00390625f, -0.00390625f, 0.0f, 0.0f, 0.0f, 0.00390625f, 0.0f, -0.00390625f, 0.00390625f, 0.0f, 0.00390625f, 0.00390625f, 0.00390625f};
    }

    final void a() {
        if (this.o != -1) {
            GLES20.glDeleteTextures(1, this.b, 0);
            this.o = -1;
        }
        if (this.p != -1) {
            GLES20.glDeleteBuffers(1, this.a, 0);
            this.p = -1;
        }
        if (this.n != -1) {
            GLES20.glDeleteProgram(this.n);
            this.n = -1;
        }
        this.m = false;
    }

    public final void a(float[] mvpMatrix, FloatBuffer vertexBuffer, int vertexCount, int coordsPerVertex, int vertexStride, float[] texMatrix, FloatBuffer texBuffer, int textureId, int texStride) {
        e.a("draw start");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(this.l, textureId);
        GLES20.glUseProgram(this.d);
        e.a("glUseProgram");
        GLES20.glUniformMatrix4fv(this.e, 1, false, mvpMatrix, 0);
        e.a("glUniformMatrix4fv");
        GLES20.glUniformMatrix4fv(this.f, 1, false, texMatrix, 0);
        e.a("glUniformMatrix4fv");
        GLES20.glEnableVertexAttribArray(this.j);
        e.a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.j, coordsPerVertex, 5126, false, vertexStride, vertexBuffer);
        e.a("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(this.k);
        e.a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.k, 2, 5126, false, texStride, texBuffer);
        e.a("glVertexAttribPointer");
        if (this.g >= 0) {
            GLES20.glUniform1fv(this.g, 9, this.q, 0);
            GLES20.glUniform2fv(this.h, 9, this.r, 0);
            GLES20.glUniform1f(this.i, this.s);
        }
        GLES20.glDrawArrays(5, 0, vertexCount);
        e.a("glDrawArrays");
        GLES20.glDisableVertexAttribArray(this.j);
        GLES20.glDisableVertexAttribArray(this.k);
        GLES20.glBindTexture(this.l, 0);
        GLES20.glUseProgram(0);
    }
}
