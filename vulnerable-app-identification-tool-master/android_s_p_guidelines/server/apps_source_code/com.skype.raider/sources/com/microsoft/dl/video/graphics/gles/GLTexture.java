package com.microsoft.dl.video.graphics.gles;

import android.opengl.GLES20;
import com.microsoft.dl.DiagUtils;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.PackageInfo;
import java.io.Closeable;

public class GLTexture implements Closeable {
    private final String a = DiagUtils.getObjName(this);
    private final Target b;
    private final int c;

    public enum Target {
        TEXTURE_2D(3553),
        TEXTURE_EXTERNAL_OES(36197);
        
        private final int a;

        private Target(int code) {
            this.a = code;
        }

        public final int getCode() {
            return this.a;
        }
    }

    public GLTexture(Target target) throws GLException {
        this.b = target;
        int[] iArr = new int[]{-1};
        GLES20.glGenTextures(1, iArr, 0);
        GLException.checkAfter("GLES20.glGenTextures()");
        this.c = iArr[0];
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, this.a + " created, name=" + this.c);
        }
    }

    public Target getTarget() {
        return this.b;
    }

    public int getName() {
        return this.c;
    }

    public void bind() throws GLException {
        GLES20.glBindTexture(this.b.getCode(), this.c);
        GLException.checkAfter("GLES20.glBindTexture()");
    }

    public void close() {
        try {
            GLES20.glDeleteTextures(1, new int[]{this.c}, 0);
            GLException.checkAfter("GLES20.glDeleteTextures()");
        } catch (GLException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.w(PackageInfo.TAG, this.a + "failed to delete texture, name=" + this.c, e);
            }
        }
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, this.a + " closed");
        }
    }
}
