package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.opengl.GLES20;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.utils.Log;
import java.io.Closeable;

class Shader implements Closeable {
    private int id;

    public Shader(int type, String source) throws GLException {
        try {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Creating new shader");
            }
            int glCreateShader = GLES20.glCreateShader(type);
            this.id = glCreateShader;
            if (glCreateShader == 0) {
                throw new GLException("Failed to create shader.", GLES20.glGetError());
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Shader " + this.id + " created");
            }
            loadSourceCode(source);
            compile();
        } catch (GLException e) {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Constructor failed");
            }
            close();
            throw e;
        }
    }

    public void close() {
        if (this.id != 0) {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Deleting shader " + this.id);
            }
            GLES20.glDeleteShader(this.id);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Shader " + this.id + " deleted");
            }
            if (GLES20.glGetError() != 0 && Log.isLoggable(Commons.TAG, 5)) {
                Log.w(Commons.TAG, getClass().getSimpleName() + ": Failed to delete shader " + this.id);
            }
            this.id = 0;
        }
    }

    public int getId() {
        return this.id;
    }

    private void loadSourceCode(String source) throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Loading shader " + this.id + " source code\n" + source);
        }
        GLES20.glShaderSource(this.id, source);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Shader " + this.id + " source code loaded");
        }
        int error = GLES20.glGetError();
        if (error != 0) {
            throw new GLException("Failed to load shader code.", error);
        }
    }

    private void compile() throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Compiling shader " + this.id);
        }
        GLES20.glCompileShader(this.id);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Shader " + this.id + " compiled");
        }
        int error = GLES20.glGetError();
        if (error != 0) {
            throw new GLException("Failed to compile shader.", error);
        }
    }
}
