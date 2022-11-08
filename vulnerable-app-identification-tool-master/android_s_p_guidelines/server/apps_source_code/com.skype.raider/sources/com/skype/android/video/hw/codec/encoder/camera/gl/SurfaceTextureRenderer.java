package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.utils.Log;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class SurfaceTextureRenderer implements Closeable {
    private static final String PIXEL_SHADER_CROSS_HATCH = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    \tvec3 clr = texture2D(sTexture, vTextureCoord).rgb;\n     vec3 resClr = vec3(1.0);     float luma = dot(clr, vec3(0.299, 0.587, 0.114));\n\t\tif (luma < 0.8) {\n         if (mod(gl_FragCoord.x + gl_FragCoord.y, 10.0) == 0.0) {\t\t\t\tresClr = vec3(0.0);\n         }\n\t\t}\n\t\tif (luma < 0.7) {\n         if (mod(gl_FragCoord.x - gl_FragCoord.y, 10.0) == 0.0) {\t\t\t\tresClr = vec3(0.0);\n         }\n\t\t}\n\t\tif (luma < 0.6) {\n         if (mod(gl_FragCoord.x + gl_FragCoord.y - 5.0, 10.0) == 0.0) {\t\t\t\tresClr = vec3(0.0);\n         }\n\t\t}\n\t\tif (luma < 0.5) {\n         if (mod(gl_FragCoord.x - gl_FragCoord.y - 5.0, 10.0) == 0.0) {\t\t\t\tresClr = vec3(0.0);\n         }\n\t\t}\n\t\tif (luma < 0.4) {\n         if (mod(gl_FragCoord.x + gl_FragCoord.y - 2.0, 10.0) == 0.0) {\t\t\t\tresClr = vec3(0.0);\n         }\n\t\t}\n\t\tif (luma < 0.3) {\n         if (mod(gl_FragCoord.x - gl_FragCoord.y - 2.0, 10.0) == 0.0) {\t\t\t\tresClr = vec3(0.0);\n         }\n\t\t}\n\t\tgl_FragColor = vec4(resClr, 1.0);\n}\n";
    private static final String PIXEL_SHADER_DEFAULT = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    private static final String PIXEL_SHADER_PORTRAIT_HIGH = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec3  clr  = texture2D(sTexture, vTextureCoord).rgb;\n    float grey = dot(vec3(0.299, 0.587, 0.114), clr);\n    grey = 0.5 + sin(3.1415926 * (grey + 1.5))/2.0;    float dst = distance(vTextureCoord, vec2(0.5, 0.5))/length(vec2(0.5, 0.5));    vec3 res  = mix(clr, vec3(grey), dst * dst) + vec3(dst*dst*dst) + 0.3;    gl_FragColor = vec4(res, 1.0);\n}\n";
    private static final String PIXEL_SHADER_POSTERIZE = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec3  clr  = texture2D(sTexture, vTextureCoord).rgb;\n    clr = floor(vec3(10) * pow(clr, vec3(0.625)))/vec3(10);\n    gl_FragColor = vec4(clr, 1.0);\n}\n";
    private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    private Program glProgram;
    private final MVPMatrix mvpMatrix;
    private int sourceTextureId;
    private final STMatrix stMatrix;
    private final TriangleVertices triangleVertices;

    private static abstract class AbstractUniformMatrix {
        protected final float[] data = new float[16];
        private final int idx;

        public AbstractUniformMatrix(Program glProgram, String varName) throws GLException {
            this.idx = glProgram.getUniformLocation(varName);
        }

        public void makeIdentity() {
            Matrix.setIdentityM(this.data, 0);
        }

        public void getFrom(SurfaceTexture surfaceTexture) {
            surfaceTexture.getTransformMatrix(this.data);
        }

        public void setup() throws GLException {
            GLES20.glUniformMatrix4fv(this.idx, 1, false, this.data, 0);
            SurfaceTextureRenderer.validate("Failed to setup matrix");
        }
    }

    private static class MVPMatrix extends AbstractUniformMatrix {
        public MVPMatrix(Program glProgram) throws GLException {
            super(glProgram, "uMVPMatrix");
        }

        public void transform(Resolution sourceResolution, Resolution targetResolution, float fitFactor, int rotationAngle, boolean isHorizFlipped, boolean isVertFlipped) {
            int a;
            int b;
            if ((((getLong(sourceResolution) != sourceResolution.getWidth()) != (getLong(targetResolution) != targetResolution.getWidth())) != isPortrait(rotationAngle) ? 1 : null) != null) {
                a = getShort(sourceResolution) * getShort(targetResolution);
                b = getLong(sourceResolution) * getLong(targetResolution);
            } else {
                a = getLong(targetResolution) * getShort(sourceResolution);
                b = getLong(sourceResolution) * getShort(targetResolution);
            }
            float ratioFit = ((float) Math.min(a, b)) / ((float) Math.max(a, b));
            float magnify = fitFactor + ((1.0f - fitFactor) / ratioFit);
            if ((a > b) != isPortrait(rotationAngle)) {
                Matrix.scaleM(this.data, 0, ratioFit, 1.0f, 1.0f);
            } else {
                Matrix.scaleM(this.data, 0, 1.0f, ratioFit, 1.0f);
            }
            Matrix.scaleM(this.data, 0, magnify, magnify, 1.0f);
            if (isHorizFlipped || isVertFlipped) {
                Matrix.scaleM(this.data, 0, isHorizFlipped ? -1.0f : 1.0f, isVertFlipped ? -1.0f : 1.0f, 1.0f);
            }
            Matrix.rotateM(this.data, 0, (float) rotationAngle, 0.0f, 0.0f, 1.0f);
        }

        private static boolean isPortrait(int angle) {
            return angle % 180 != 0;
        }

        private static int getLong(Resolution res) {
            return Math.max(res.getWidth(), res.getHeight());
        }

        private static int getShort(Resolution res) {
            return Math.min(res.getWidth(), res.getHeight());
        }
    }

    private static class STMatrix extends AbstractUniformMatrix {
        public STMatrix(Program glProgram) throws GLException {
            super(glProgram, "uSTMatrix");
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

        public void setup() throws GLException {
            setupVertexAttrib(0, this.idxDataPosOffset, 3);
            setupVertexAttrib(3, this.idxDataUVOffset, 2);
        }

        private void setupVertexAttrib(int pos, int index, int size) throws GLException {
            this.data.position(pos);
            GLES20.glVertexAttribPointer(index, size, 5126, false, 20, this.data);
            SurfaceTextureRenderer.validate("Failed to define vertex attribute data");
            GLES20.glEnableVertexAttribArray(index);
            SurfaceTextureRenderer.validate("Failed to enable vertex attribute array");
        }
    }

    public SurfaceTextureRenderer() throws GLException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Creating");
        }
        this.glProgram = new Program(VERTEX_SHADER, PIXEL_SHADER_DEFAULT);
        this.stMatrix = new STMatrix(this.glProgram);
        this.mvpMatrix = new MVPMatrix(this.glProgram);
        this.triangleVertices = new TriangleVertices(this.glProgram);
        this.sourceTextureId = createTexture();
        bindTexture(this.sourceTextureId);
        setGlParamFloat(10241, 9729.0f);
        setGlParamFloat(10240, 9729.0f);
        setGlParamInt(10242, 33071);
        setGlParamInt(10243, 33071);
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Created");
        }
    }

    public int getSourceTextureId() {
        return this.sourceTextureId;
    }

    public void draw(SurfaceTexture sourceTexture, Resolution sourceResolution, Resolution targetResolution, float fitFactor, int rotationAngle, boolean isHorizFlipped, boolean isVertFlipped) throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ".draw() enter: " + sourceResolution + " -> " + targetResolution + " @" + rotationAngle + " deg" + (isHorizFlipped ? ", H-flip" : "") + (isVertFlipped ? ", V-flip" : ""));
        }
        this.glProgram.install();
        GLES20.glActiveTexture(33984);
        bindTexture(this.sourceTextureId);
        this.stMatrix.getFrom(sourceTexture);
        this.mvpMatrix.makeIdentity();
        this.mvpMatrix.transform(sourceResolution, targetResolution, fitFactor, rotationAngle, isHorizFlipped, isVertFlipped);
        this.stMatrix.setup();
        this.mvpMatrix.setup();
        this.triangleVertices.setup();
        setViewPort(targetResolution);
        render();
        bindTexture(0);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ".draw() leave");
        }
    }

    private static void setViewPort(Resolution resolution) throws GLException {
        GLES20.glScissor(0, 0, resolution.getWidth(), resolution.getHeight());
        validate("Failed to set scissor");
        GLES20.glViewport(0, 0, resolution.getWidth(), resolution.getHeight());
        validate("Failed to set viewport");
    }

    public void close() {
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

    private static void validate(String msg) throws GLException {
        int error = GLES20.glGetError();
        if (error != 0) {
            throw new GLException(msg, error);
        }
    }

    private static int createTexture() throws GLException {
        int[] textures = new int[1];
        GLES20.glGenTextures(1, textures, 0);
        return textures[0];
    }

    private static void bindTexture(int textureId) throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, SurfaceTextureRenderer.class.getSimpleName() + ": Binding texture " + textureId);
        }
        GLES20.glBindTexture(36197, textureId);
        validate("Failed to bind texture");
    }

    private static void setGlParamFloat(int name, float val) throws GLException {
        GLES20.glTexParameterf(36197, name, val);
        validate("Failed to set GLES20 float parameter");
    }

    private static void setGlParamInt(int name, int val) throws GLException {
        GLES20.glTexParameteri(36197, name, val);
        validate("Failed to set GLES20 int parameter");
    }

    private void render() throws GLException {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, SurfaceTextureRenderer.class.getSimpleName() + ": rendering a frame");
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16640);
        GLES20.glDrawArrays(5, 0, 4);
        validate("Failed to draw arrays");
    }
}
