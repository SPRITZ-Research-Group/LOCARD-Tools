package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.opengl.GLES20;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.utils.Log;
import java.io.Closeable;

class Program implements Closeable {
    private Shader fragmentShader;
    private int id;
    private Shader vertexShader;

    public Program(String vertexShaderSource, String fragmentShaderSource) throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Creating new GL program ");
        }
        int glCreateProgram = GLES20.glCreateProgram();
        this.id = glCreateProgram;
        if (glCreateProgram == 0) {
            throw new GLException("Failed to create GL program.", GLES20.glGetError());
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": GL program " + this.id + " created");
        }
        this.vertexShader = new Shader(35633, vertexShaderSource);
        this.fragmentShader = new Shader(35632, fragmentShaderSource);
        attachShader(this.vertexShader);
        attachShader(this.fragmentShader);
        link();
    }

    public void close() {
        if (this.vertexShader != null) {
            this.vertexShader.close();
            this.vertexShader = null;
        }
        if (this.fragmentShader != null) {
            this.fragmentShader.close();
            this.fragmentShader = null;
        }
        if (this.id != 0) {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Deleting GL program " + this.id);
            }
            GLES20.glDeleteProgram(this.id);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": GL program " + this.id + " deleted");
            }
            if (GLES20.glGetError() != 0 && Log.isLoggable(Commons.TAG, 5)) {
                Log.w(Commons.TAG, getClass().getSimpleName() + ": Failed to delete GL program " + this.id);
            }
            this.id = 0;
        }
    }

    public void install() throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Installing GL program " + this.id);
        }
        GLES20.glUseProgram(this.id);
        int error = GLES20.glGetError();
        if (error != 0) {
            throw new GLException("Failed install GL program " + this.id, error);
        } else if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": GL program " + this.id + " installed");
        }
    }

    public int getAttribLocation(String name) throws GLException {
        int loc = GLES20.glGetAttribLocation(this.id, name);
        if (loc >= 0) {
            return loc;
        }
        throw new GLException("Failed get location of the attribute '" + name + "' within GL program " + this.id);
    }

    public int getUniformLocation(String name) throws GLException {
        int loc = GLES20.glGetUniformLocation(this.id, name);
        if (loc >= 0) {
            return loc;
        }
        throw new GLException("Failed get location of the uniform '" + name + "' within GL program " + this.id);
    }

    private void attachShader(Shader shader) throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Attaching shader " + shader.getId() + " to GL program " + this.id);
        }
        GLES20.glAttachShader(this.id, shader.getId());
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Shader " + shader.getId() + " attached to GL program " + this.id);
        }
        int error = GLES20.glGetError();
        if (error != 0) {
            throw new GLException("Failed to attach shader" + shader.getId() + " to GL program " + this.id, error);
        }
    }

    private void link() throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Linking GL program " + this.id);
        }
        GLES20.glLinkProgram(this.id);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": GL program " + this.id + " linkled");
        }
        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(this.id, 35714, linkStatus, 0);
        if (linkStatus[0] != 1) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Failed to link GL program " + this.id + "\n" + GLES20.glGetProgramInfoLog(this.id));
            }
            throw new GLException("Failed to link GL program" + this.id);
        }
    }
}
