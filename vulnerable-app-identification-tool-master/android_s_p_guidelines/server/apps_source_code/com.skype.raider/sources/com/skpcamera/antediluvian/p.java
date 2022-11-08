package com.skpcamera.antediluvian;

import android.opengl.GLES20;
import android.opengl.Matrix;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public final class p {
    public static final float[] a;

    static {
        float[] fArr = new float[16];
        a = fArr;
        Matrix.setIdentityM(fArr, 0);
    }

    public static int a(String vertexSource, String fragmentSource) {
        int vertexShader = b(35633, vertexSource);
        if (vertexShader == 0) {
            return 0;
        }
        int pixelShader = b(35632, fragmentSource);
        if (pixelShader == 0) {
            return 0;
        }
        int program = GLES20.glCreateProgram();
        a("glCreateProgram");
        GLES20.glAttachShader(program, vertexShader);
        a("glAttachShader");
        GLES20.glAttachShader(program, pixelShader);
        a("glAttachShader");
        GLES20.glLinkProgram(program);
        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(program, 35714, linkStatus, 0);
        if (linkStatus[0] == 1) {
            return program;
        }
        GLES20.glGetProgramInfoLog(program);
        GLES20.glDeleteProgram(program);
        return 0;
    }

    private static int b(int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        a("glCreateShader type=" + shaderType);
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, 35713, compiled, 0);
        if (compiled[0] != 0) {
            return shader;
        }
        new StringBuilder("Could not compile shader ").append(shaderType).append(":");
        new StringBuilder(" ").append(GLES20.glGetShaderInfoLog(shader));
        GLES20.glDeleteShader(shader);
        return 0;
    }

    public static void a(String op) {
        int error = GLES20.glGetError();
        if (error != 0) {
            new StringBuilder().append(op).append(": glError 0x").append(Integer.toHexString(error));
        }
    }

    public static void a(int location, String label) {
        if (location < 0) {
            throw new RuntimeException("Unable to locate '" + label + "' in program");
        }
    }

    public static FloatBuffer a(float[] coords) {
        ByteBuffer bb = ByteBuffer.allocateDirect(coords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(coords);
        fb.position(0);
        return fb;
    }
}
