package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.opengl.EGL14;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.view.Surface;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.utils.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class VirtualSourceGenerator {
    private static final int[] EGL_CONTEXT_ATTRIBUTES = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
    private static final String PIXEL_SHADER = "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n";
    private Context context;
    private EGLSurface dummySurface;
    private EGLDisplay eglDisplay;
    private EGLSurface eglSurface;
    private Program glProgram;
    private MVPMatrix mvpMatrix;
    private Context sharedContext;
    private Resolution sourceResolution;
    private Surface surface;
    private final Object surfaceMutex = new Object();
    private Resolution targetResolution;
    private int textureId;
    private TriangleVertices triangleVertices;

    private static abstract class AbstractUniformMatrix {
        protected final float[] data = new float[16];
        private final int idx;

        public AbstractUniformMatrix(Program glProgram, String varName) throws GLException {
            this.idx = glProgram.getUniformLocation(varName);
        }

        public void makeIdentity() {
            Matrix.setIdentityM(this.data, 0);
        }

        public void setup() throws GLException {
            GLES20.glUniformMatrix4fv(this.idx, 1, false, this.data, 0);
            VirtualSourceGenerator.validate("Failed to setup matrix");
        }
    }

    private static class MVPMatrix extends AbstractUniformMatrix {
        private static final float AR_COMPARISON_TOLERANCE = 0.01f;

        public MVPMatrix(Program glProgram) throws GLException {
            super(glProgram, "uMVPMatrix");
        }

        public void transform(Resolution sourceResolution, Resolution targetResolution, int rotationAngle, boolean isHorizFlipped, boolean isVertFlipped) {
            boolean isPortrait = isPortrait(rotationAngle);
            float targetAR = getAR(targetResolution, isPortrait);
            float sourceAR = getAR(sourceResolution, false);
            if (Math.abs(targetAR - sourceAR) > AR_COMPARISON_TOLERANCE) {
                float scaleX = 1.0f;
                float scaleY = 1.0f;
                if (targetAR < sourceAR) {
                    if (isPortrait) {
                        scaleY = targetAR;
                    } else {
                        scaleX = targetAR;
                    }
                } else if (isPortrait) {
                    scaleX = sourceAR;
                } else {
                    scaleY = sourceAR;
                }
                Matrix.scaleM(this.data, 0, scaleX, scaleY, 1.0f);
            }
            if (isHorizFlipped || isVertFlipped) {
                Matrix.scaleM(this.data, 0, isHorizFlipped ? -1.0f : 1.0f, isVertFlipped ? -1.0f : 1.0f, 1.0f);
            }
            Matrix.rotateM(this.data, 0, (float) rotationAngle, 0.0f, 0.0f, 1.0f);
        }

        private static boolean isPortrait(int angle) {
            return angle % 180 != 0;
        }

        private static float getAR(Resolution resolution, boolean swapDimensions) {
            if (swapDimensions) {
                return ((float) resolution.getHeight()) / ((float) resolution.getWidth());
            }
            return ((float) resolution.getWidth()) / ((float) resolution.getHeight());
        }
    }

    private static class TriangleVertices {
        private static final int FLOAT_SIZE_BYTES = 4;
        private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
        private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 20;
        private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
        private static final float[] triangleVerticesData = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
        private final FloatBuffer data = ByteBuffer.allocateDirect(triangleVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        private final int idxDataPosOffset;
        private final int idxDataUVOffset;

        public TriangleVertices(Program glProgram) throws GLException {
            this.data.put(triangleVerticesData).position(0);
            this.idxDataPosOffset = glProgram.getAttribLocation("aPosition");
            this.idxDataUVOffset = glProgram.getAttribLocation("aTextureCoord");
        }

        private void setupVertexAttrib(int pos, int index, int size) throws GLException {
            this.data.position(pos);
            GLES20.glVertexAttribPointer(index, size, 5126, false, 20, this.data);
            VirtualSourceGenerator.validate("Failed to define vertex attribute data");
            GLES20.glEnableVertexAttribArray(index);
            VirtualSourceGenerator.validate("Failed to enable vertex attribute array");
        }

        public void setup() throws GLException {
            setupVertexAttrib(0, this.idxDataPosOffset, 3);
            setupVertexAttrib(3, this.idxDataUVOffset, 2);
        }
    }

    public void setup() throws GLException {
        createEGLDisplay();
        this.sharedContext = new Context(this.eglDisplay, EGL_CONTEXT_ATTRIBUTES);
        this.dummySurface = EGL14.eglCreatePbufferSurface(this.sharedContext.getEGLDisplay(), this.sharedContext.getEGLConfig(), new int[]{12375, 640, 12374, 480, 12344}, 0);
        EGL14.eglMakeCurrent(this.sharedContext.getEGLDisplay(), this.dummySurface, this.dummySurface, this.sharedContext.getEGLContext());
        this.glProgram = new Program(VERTEX_SHADER, PIXEL_SHADER);
        this.mvpMatrix = new MVPMatrix(this.glProgram);
        this.triangleVertices = new TriangleVertices(this.glProgram);
        this.textureId = createTexture();
        GLES20.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);
        GLES20.glDisable(2929);
        GLES20.glEnable(3553);
        EGL14.eglMakeCurrent(this.sharedContext.getEGLDisplay(), EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
    }

    public void close() throws GLException {
        if (this.glProgram != null) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Closing");
            }
            this.glProgram.close();
            this.glProgram = null;
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Closed");
            }
        }
    }

    public void setResolution(Resolution resolution) {
        this.sourceResolution = resolution;
        this.targetResolution = resolution;
    }

    public void setSurface(Surface s) {
        synchronized (this.surfaceMutex) {
            if (this.surface != null) {
                this.surface.release();
            }
            this.surface = s;
        }
    }

    private void createEGLDisplay() throws GLException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Creating EGL display");
        }
        this.eglDisplay = EGL14.eglGetDisplay(0);
        if (this.eglDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new GLException("Failed to get EGL14 display.", EGL14.eglGetError());
        }
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Initializing EGL");
        }
        int[] eglVersion = new int[2];
        if (!EGL14.eglInitialize(this.eglDisplay, eglVersion, 0, eglVersion, 1)) {
            throw new GLException("Failed to initialize EGL.", EGL14.eglGetError());
        } else if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": EGL initialized: version " + eglVersion[0] + "." + eglVersion[1]);
        }
    }

    private static void bindTexture(int textureId) throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, VirtualSourceGenerator.class.getSimpleName() + ": Binding texture " + textureId);
        }
        GLES20.glBindTexture(3553, textureId);
        validate("Failed to bind texture ", Integer.valueOf(textureId));
    }

    private static void validate(Object... msgStringSeq) throws GLException {
        int error = GLES20.glGetError();
        if (error != 0) {
            throw new GLException(cat(msgStringSeq), error);
        }
    }

    private static String cat(Object... seq) {
        StringBuilder str = new StringBuilder();
        for (Object o : seq) {
            str.append(o);
        }
        return str.toString();
    }

    public void createContextIfNotExist() throws GLException {
        if (this.context == null) {
            this.context = new Context(this.sharedContext, EGL_CONTEXT_ATTRIBUTES);
            this.eglSurface = EGL14.eglCreateWindowSurface(this.context.getEGLDisplay(), this.context.getEGLConfig(), this.surface, new int[]{12344}, 0);
        }
    }

    private int createTexture() throws GLException {
        int[] textureHandle = new int[1];
        GLES20.glGenTextures(1, textureHandle, 0);
        if (textureHandle[0] != 0) {
            return textureHandle[0];
        }
        throw new RuntimeException("Error loading texture.");
    }

    private static void setViewPort(Resolution resolution) throws GLException {
        int y = (16 - (resolution.getHeight() % 16)) % 16;
        GLES20.glScissor(0, y, resolution.getWidth(), resolution.getHeight());
        validate("Failed to set scissor to ", resolution);
        GLES20.glViewport(0, y, resolution.getWidth(), resolution.getHeight());
        validate("Failed to set viewport to ", resolution);
    }

    private void loadTexture(Resolution sourceResolution, int textureId, ByteBuffer frameData) {
        GLES20.glBindTexture(3553, textureId);
        GLES20.glTexParameteri(3553, 10241, 9728);
        GLES20.glTexParameteri(3553, 10240, 9728);
        GLES20.glTexImage2D(3553, 0, 6408, sourceResolution.getWidth(), sourceResolution.getHeight(), 0, 6408, 5121, frameData);
        frameData.position(0);
    }

    public void renderOneFrame(ByteBuffer frameData, long timestamp) throws GLException {
        createContextIfNotExist();
        EGL14.eglMakeCurrent(this.context.getEGLDisplay(), this.eglSurface, this.eglSurface, this.context.getEGLContext());
        validate("Failed to eglMakeCurrent");
        loadTexture(this.sourceResolution, this.textureId, frameData);
        this.mvpMatrix.makeIdentity();
        this.mvpMatrix.transform(this.sourceResolution, this.targetResolution, 0, false, true);
        this.triangleVertices.setup();
        setViewPort(this.targetResolution);
        validate("Failed to set uViewport to ", this.targetResolution);
        render();
        bindTexture(0);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ".draw() leave");
        }
        if (timestamp < 0 && Log.isLoggable(Commons.TAG, 6)) {
            Log.e(Commons.TAG, "Provided timestamp is negative but must be positive for MediaCodec: " + timestamp);
        }
        EGLExt.eglPresentationTimeANDROID(this.context.getEGLDisplay(), this.eglSurface, timestamp * 1000);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, SurfaceTextureRenderer.class.getSimpleName() + ": current frame surface timestamp " + (timestamp * 1000) + " ns");
        }
        EGL14.eglSwapBuffers(this.context.getEGLDisplay(), this.eglSurface);
        EGLUtils.validate("Failed to swap buffers.");
    }

    private void render() throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, SurfaceTextureRenderer.class.getSimpleName() + ": rendering a frame");
        }
        GLES20.glClear(16384);
        this.glProgram.install();
        GLES20.glUniform1i(this.glProgram.getUniformLocation("sTexture"), 0);
        this.mvpMatrix.setup();
        GLES20.glActiveTexture(33984);
        bindTexture(this.textureId);
        GLES20.glDrawArrays(5, 0, 4);
        validate("Failed to draw arrays");
    }
}
