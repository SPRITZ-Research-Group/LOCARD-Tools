package com.skpcamera.antediluvian;

import android.opengl.GLES20;
import java.nio.FloatBuffer;

public final class w {
    private a a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private float[] k = new float[9];
    private float[] l;
    private float m;

    public enum a {
        TEXTURE_2D,
        TEXTURE_EXT,
        TEXTURE_EXT_BW,
        TEXTURE_EXT_FILT
    }

    public w(a programType) {
        this.a = programType;
        switch (programType) {
            case TEXTURE_2D:
                this.j = 3553;
                this.b = p.a("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
                break;
            case TEXTURE_EXT:
                this.j = 36197;
                this.b = p.a("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
                break;
            case TEXTURE_EXT_BW:
                this.j = 36197;
                this.b = p.a("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec4 tc = texture2D(sTexture, vTextureCoord);\n    float color = tc.r * 0.3 + tc.g * 0.59 + tc.b * 0.11;\n    gl_FragColor = vec4(color, color, color, 1.0);\n}\n");
                break;
            case TEXTURE_EXT_FILT:
                this.j = 36197;
                this.b = p.a("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\n#define KERNEL_SIZE 9\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float uKernel[KERNEL_SIZE];\nuniform vec2 uTexOffset[KERNEL_SIZE];\nuniform float uColorAdjust;\nvoid main() {\n    int i = 0;\n    vec4 sum = vec4(0.0);\n    if (vTextureCoord.x < vTextureCoord.y - 0.005) {\n        for (i = 0; i < KERNEL_SIZE; i++) {\n            vec4 texc = texture2D(sTexture, vTextureCoord + uTexOffset[i]);\n            sum += texc * uKernel[i];\n        }\n    sum += uColorAdjust;\n    } else if (vTextureCoord.x > vTextureCoord.y + 0.005) {\n        sum = texture2D(sTexture, vTextureCoord);\n    } else {\n        sum.r = 1.0;\n    }\n    gl_FragColor = sum;\n}\n");
                break;
            default:
                throw new RuntimeException("Unhandled type " + programType);
        }
        if (this.b == 0) {
            throw new RuntimeException("Unable to create program");
        }
        new StringBuilder("Created program ").append(this.b).append(" (").append(programType).append(")");
        this.h = GLES20.glGetAttribLocation(this.b, "aPosition");
        p.a(this.h, "aPosition");
        this.i = GLES20.glGetAttribLocation(this.b, "aTextureCoord");
        p.a(this.i, "aTextureCoord");
        this.c = GLES20.glGetUniformLocation(this.b, "uMVPMatrix");
        p.a(this.c, "uMVPMatrix");
        this.d = GLES20.glGetUniformLocation(this.b, "uTexMatrix");
        p.a(this.d, "uTexMatrix");
        this.e = GLES20.glGetUniformLocation(this.b, "uKernel");
        if (this.e < 0) {
            this.e = -1;
            this.f = -1;
            this.g = -1;
            return;
        }
        this.f = GLES20.glGetUniformLocation(this.b, "uTexOffset");
        p.a(this.f, "uTexOffset");
        this.g = GLES20.glGetUniformLocation(this.b, "uColorAdjust");
        p.a(this.g, "uColorAdjust");
        System.arraycopy(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f}, 0, this.k, 0, 9);
        this.m = 0.0f;
        this.l = new float[]{-0.00390625f, -0.00390625f, 0.0f, -0.00390625f, 0.00390625f, -0.00390625f, -0.00390625f, 0.0f, 0.0f, 0.0f, 0.00390625f, 0.0f, -0.00390625f, 0.00390625f, 0.0f, 0.00390625f, 0.00390625f, 0.00390625f};
    }

    public final int a() {
        int[] textures = new int[1];
        GLES20.glGenTextures(1, textures, 0);
        p.a("glGenTextures");
        int texId = textures[0];
        GLES20.glBindTexture(this.j, texId);
        p.a("glBindTexture " + texId);
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        p.a("glTexParameter");
        return texId;
    }

    public final void a(float[] mvpMatrix, FloatBuffer vertexBuffer, int vertexCount, int coordsPerVertex, int vertexStride, float[] texMatrix, FloatBuffer texBuffer, int textureId, int texStride) {
        p.a("draw start");
        GLES20.glUseProgram(this.b);
        p.a("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(this.j, textureId);
        GLES20.glUniformMatrix4fv(this.c, 1, false, mvpMatrix, 0);
        p.a("glUniformMatrix4fv");
        GLES20.glUniformMatrix4fv(this.d, 1, false, texMatrix, 0);
        p.a("glUniformMatrix4fv");
        GLES20.glEnableVertexAttribArray(this.h);
        p.a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.h, coordsPerVertex, 5126, false, vertexStride, vertexBuffer);
        p.a("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(this.i);
        p.a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.i, 2, 5126, false, texStride, texBuffer);
        p.a("glVertexAttribPointer");
        if (this.e >= 0) {
            GLES20.glUniform1fv(this.e, 9, this.k, 0);
            GLES20.glUniform2fv(this.f, 9, this.l, 0);
            GLES20.glUniform1f(this.g, this.m);
        }
        GLES20.glDrawArrays(5, 0, vertexCount);
        p.a("glDrawArrays");
        GLES20.glDisableVertexAttribArray(this.h);
        GLES20.glDisableVertexAttribArray(this.i);
        GLES20.glBindTexture(this.j, 0);
        GLES20.glUseProgram(0);
    }
}
