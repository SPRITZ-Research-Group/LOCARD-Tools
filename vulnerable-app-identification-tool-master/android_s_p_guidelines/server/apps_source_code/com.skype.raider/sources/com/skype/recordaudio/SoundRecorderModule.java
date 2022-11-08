package com.skype.recordaudio;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import java.io.File;
import java.io.IOException;

public class SoundRecorderModule extends ReactContextBaseJavaModule {
    private static int MAX_AMPLITUDE = 32767;
    private static String TAG = "SoundRecorderModule";
    private Integer error;
    private MediaRecorder recorder;
    private String recordingFilename;
    private a soundRecorderListener = new a();

    private class a implements OnErrorListener {
        final /* synthetic */ SoundRecorderModule a;

        private a(SoundRecorderModule soundRecorderModule) {
            this.a = soundRecorderModule;
        }

        /* synthetic */ a(SoundRecorderModule x0, byte b) {
            this(x0);
        }

        public final void onError(MediaRecorder mr, int what, int extra) {
            FLog.e(SoundRecorderModule.TAG, "onError " + what);
            this.a.error = Integer.valueOf(what);
        }
    }

    public SoundRecorderModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "SoundRecorder";
    }

    public static String getFileExt(String fileName) {
        int index = fileName.lastIndexOf(".");
        return index == -1 ? "" : fileName.substring(index + 1, fileName.length());
    }

    @ReactMethod
    public void startRecord(String filename, d failureCb, d successCb) {
        FLog.i(TAG, "startRecord");
        if (this.recorder != null) {
            invokeFailureCallbackWithReason(failureCb, "Another recording already in the progress");
        } else if (TextUtils.isEmpty(filename)) {
            invokeFailureCallbackWithReason(failureCb, "Your file does not have a name");
        } else {
            String extension = getFileExt(filename);
            if (TextUtils.isEmpty(extension)) {
                invokeFailureCallbackWithReason(failureCb, "Your file does not have a valid extension");
            } else if (extension.equals("m4a")) {
                this.recordingFilename = getReactApplicationContext().getCacheDir() + "/" + filename;
                this.recorder = new MediaRecorder();
                this.recorder.setAudioSource(1);
                this.recorder.setOutputFormat(2);
                this.recorder.setAudioEncoder(3);
                this.recorder.setAudioSamplingRate(44100);
                this.recorder.setAudioEncodingBitRate(96000);
                this.recorder.setOutputFile(this.recordingFilename);
                this.recorder.setOnErrorListener(this.soundRecorderListener);
                try {
                    this.recorder.prepare();
                    try {
                        this.recorder.start();
                        successCb.invoke(new Object[0]);
                    } catch (IllegalStateException e) {
                        invokeFailureCallbackWithReason(failureCb, "Failed to start MediaRecorder");
                    }
                } catch (IOException e2) {
                    invokeFailureCallbackWithReason(failureCb, "Failed to prepare MediaRecorder");
                } catch (IllegalStateException e3) {
                    invokeFailureCallbackWithReason(failureCb, "Failed to prepare MediaRecorder");
                }
            } else {
                invokeFailureCallbackWithReason(failureCb, "File should have .m4a extension");
            }
        }
    }

    private void invokeFailureCallbackWithReason(d failureCb, String reason) {
        FLog.e(TAG, reason);
        new WritableNativeMap().putString("message", reason);
        failureCb.invoke(map);
    }

    @ReactMethod
    public void stopRecord(d failureCb, d successCb) {
        FLog.i(TAG, "stopRecord");
        if (this.recorder != null) {
            try {
                this.recorder.stop();
            } catch (Throwable ex) {
                this.error = Integer.valueOf(-1);
                FLog.e(TAG, "stopRecord", ex);
            } catch (Throwable ex2) {
                this.error = Integer.valueOf(-1);
                FLog.e(TAG, "stopRecord", ex2);
            }
            this.recorder.release();
            this.recorder = null;
            if (this.error != null || this.recordingFilename == null) {
                invokeFailureCallbackWithReason(failureCb, "Error occurred during recording");
                this.error = null;
                return;
            }
            int size = (int) new File(this.recordingFilename).length();
            FLog.i(TAG, "Size of " + this.recordingFilename + " is " + Integer.toString(size) + " bytes");
            ar map = new WritableNativeMap();
            map.putString("filePath", this.recordingFilename);
            map.putInt("fileSize", size);
            successCb.invoke(map);
            this.recordingFilename = null;
            return;
        }
        invokeFailureCallbackWithReason(failureCb, "Recording not in progress. Can not be stopped.");
    }

    @ReactMethod
    public void getStats(d failureCb, d successCb) {
        if (this.recorder != null) {
            ar map = new WritableNativeMap();
            int amplitude = this.recorder.getMaxAmplitude();
            double normalized = ((double) amplitude) / ((double) MAX_AMPLITUDE);
            map.putDouble("average", normalized);
            FLog.i(TAG, "getStats " + normalized + " " + amplitude);
            successCb.invoke(map);
            return;
        }
        invokeFailureCallbackWithReason(failureCb, "Recording not in progress. Can not get stats.");
    }
}
