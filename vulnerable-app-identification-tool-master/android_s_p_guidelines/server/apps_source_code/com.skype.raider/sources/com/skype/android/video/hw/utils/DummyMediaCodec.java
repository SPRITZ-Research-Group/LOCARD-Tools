package com.skype.android.video.hw.utils;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.Callback;
import android.media.MediaCodec.CodecException;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import com.skype.android.video.hw.Commons;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

public class DummyMediaCodec extends Callback {
    private static final int BITRATE = 2000000;
    private static final int ExynosDecoderDriver = 180315;
    private static final int FRAME_RATE = 30;
    private static final int IFRAME_INTERVAL = 1;
    private static final String KEY_DEC_VER = "-ext-dec-caps-vt-driver-version.number";
    private static final String KEY_ENC_MAXDS = "-ext-enc-caps-preprocess.max-downscale-factor";
    private static final String KEY_ENC_MAXLTR = "-ext-enc-caps-ltr.max-count";
    private static final String KEY_ENC_ROT = "-ext-enc-caps-preprocess.rotation";
    private static final String KEY_ENC_TLYER = "-ext-enc-caps-temporal-layers.max-p-count";
    private static final String KEY_ENC_VER = "-ext-enc-caps-vt-driver-version.number";
    public final CountDownLatch callbackCountDown = new CountDownLatch(1);
    private HandlerThread callbackThread;
    private Handler callbackhandler;
    private String extPrefix;
    private MediaCodec mDec;
    private boolean mDecConfigured;
    public MediaFormat mDecOutputFormat = null;
    private boolean mDone = false;
    private MediaCodec mEnc;
    public MediaFormat mEncOutputFormat = null;
    private long mInFrameCount = 0;
    private MediaFormat mInputFormat;
    private boolean mIsEncoderOnly;
    private boolean mIsExynos;
    private long mOutFrameCount = 0;
    private long pts = 0;

    DummyMediaCodec(boolean isEncoder, boolean isExynos) {
        this.mIsEncoderOnly = isEncoder;
        this.mIsExynos = isExynos;
    }

    public void init(int width, int height, String prefix) throws Exception {
        MediaFormat encFormat;
        Log.d(Commons.TAG, "Build: BOARD=" + Build.BOARD);
        Log.d(Commons.TAG, "Build: Device=" + Build.DEVICE);
        Log.d(Commons.TAG, "Build: PROD=" + Build.PRODUCT);
        Log.d(Commons.TAG, "init encoder");
        if (this.mInputFormat == null) {
            encFormat = MediaFormat.createVideoFormat(CodecUtils.MEDIA_TYPE, width, height);
        } else {
            encFormat = MediaFormat.createVideoFormat(CodecUtils.MEDIA_TYPE, this.mInputFormat.getInteger("width"), this.mInputFormat.getInteger("height"));
        }
        encFormat.setInteger("bitrate", BITRATE);
        int fps = 30;
        if (this.mInputFormat != null) {
            fps = this.mInputFormat.getInteger("frame-rate");
        }
        encFormat.setInteger("frame-rate", fps);
        encFormat.setInteger("i-frame-interval", 1);
        encFormat.setInteger("color-format", 21);
        this.extPrefix = prefix;
        encFormat.setInteger(this.extPrefix + KEY_ENC_VER, 0);
        encFormat.setInteger(this.extPrefix + KEY_ENC_TLYER, 0);
        encFormat.setInteger(this.extPrefix + KEY_ENC_MAXLTR, 0);
        encFormat.setInteger(this.extPrefix + KEY_ENC_MAXDS, 0);
        encFormat.setInteger(this.extPrefix + KEY_ENC_ROT, 0);
        Log.d(Commons.TAG, "Encoder format : " + this.mInputFormat);
        this.mEnc = MediaCodec.createEncoderByType(CodecUtils.MEDIA_TYPE);
        this.callbackThread = new HandlerThread("MediaCodecCallbackThread");
        this.callbackThread.start();
        this.callbackhandler = new Handler(this.callbackThread.getLooper());
        this.mEnc.setCallback(this, this.callbackhandler);
        Log.d(Commons.TAG, "configure encoder");
        this.mEnc.configure(encFormat, null, null, 1);
        Log.d(Commons.TAG, "configure encoder done");
        this.mEncOutputFormat = MediaFormat.createVideoFormat(CodecUtils.MEDIA_TYPE, width, height);
        this.mDec = MediaCodec.createDecoderByType(CodecUtils.MEDIA_TYPE);
        this.mDecConfigured = false;
    }

    public void start() {
        Log.d(Commons.TAG, "start encoder");
        this.mEnc.start();
    }

    private void _stop() {
        synchronized (this.mEnc) {
            if (this.mEnc != null) {
                try {
                    Log.d(Commons.TAG, "stopping encoder");
                    this.mEnc.stop();
                    this.mEnc.release();
                    this.mEnc = null;
                    Log.d(Commons.TAG, "stopped encoder");
                    this.callbackThread.quitSafely();
                    this.callbackThread.join();
                } catch (Exception e) {
                    Log.d(Commons.TAG, "Exception caught: " + e);
                }
            }
        }
    }

    public void stop() {
        this.mDone = true;
        if (this.mOutFrameCount == this.mInFrameCount) {
            _stop();
        }
    }

    public void onInputBufferAvailable(MediaCodec codec, int index) {
        boolean z = true;
        if (!this.mDone) {
            ByteBuffer inBuf = this.mEnc.getInputBuffer(index);
            this.mEnc.queueInputBuffer(index, 0, inBuf.capacity(), this.pts, 0);
            Log.d(Commons.TAG, "input: TS=" + this.pts + " size=" + inBuf.capacity());
            this.mInFrameCount++;
            this.pts += 33000;
            if (this.mDecConfigured) {
                int status = this.mDec.dequeueOutputBuffer(new BufferInfo(), 10);
                if (status == -2) {
                    this.mDecOutputFormat = this.mDec.getOutputFormat();
                    String str = Commons.TAG;
                    StringBuilder append = new StringBuilder().append(this.mIsExynos).append(" ").append(VERSION.SDK_INT).append(" ").append(!this.mDecOutputFormat.containsKey(new StringBuilder().append(this.extPrefix).append(KEY_ENC_VER).toString())).append(" ");
                    if (this.mDecOutputFormat.containsKey(this.extPrefix + KEY_DEC_VER)) {
                        z = false;
                    }
                    Log.d(str, append.append(z).toString());
                    if (this.mIsExynos && !((VERSION.SDK_INT != 26 && VERSION.SDK_INT != 27) || this.mDecOutputFormat.containsKey(this.extPrefix + KEY_ENC_VER) || this.mDecOutputFormat.containsKey(this.extPrefix + KEY_DEC_VER))) {
                        Log.i(Commons.TAG, "Exynos decoder workaround, manually set version number");
                        this.mDecOutputFormat.setInteger(this.extPrefix + KEY_DEC_VER, ExynosDecoderDriver);
                    }
                    this.callbackCountDown.countDown();
                    Log.d(Commons.TAG, "Decoder: output format = " + this.mDecOutputFormat);
                    Log.d(Commons.TAG, "stopping decoder");
                    this.mDec.stop();
                    this.mDec.release();
                    Log.d(Commons.TAG, "stopped decoder");
                    this.mDec = null;
                    this.mDecConfigured = false;
                    stop();
                } else if (status > 0) {
                    Log.d(Commons.TAG, "Decoder: got output");
                    this.mDec.releaseOutputBuffer(status, false);
                }
            }
        }
    }

    public void onOutputBufferAvailable(MediaCodec codec, int index, BufferInfo info) {
        Log.d(Commons.TAG, "output: TS=" + info.presentationTimeUs + " flags=" + info.flags + " len=" + info.size);
        ByteBuffer outBuf = this.mEnc.getOutputBuffer(index);
        if (!this.mIsEncoderOnly && this.mDecConfigured && (info.flags & 2) == 0) {
            int bufId = this.mDec.dequeueInputBuffer(10);
            if (bufId >= 0) {
                ByteBuffer inBuf = this.mDec.getInputBuffer(bufId);
                Log.d(Commons.TAG, "Decoder: got input");
                inBuf.clear();
                inBuf.put(outBuf);
                this.mDec.queueInputBuffer(bufId, 0, info.size, info.presentationTimeUs, info.flags);
                Log.d(Commons.TAG, "Decoder: queued input ts=" + info.presentationTimeUs + " len=" + info.size);
            }
        }
        this.mEnc.releaseOutputBuffer(index, false);
        this.mOutFrameCount++;
        if (this.mDone && this.mOutFrameCount == this.mInFrameCount) {
            _stop();
        }
    }

    public void onError(MediaCodec codec, CodecException e) {
        Log.e(Commons.TAG, "ERROR : " + e.toString());
        this.callbackCountDown.countDown();
    }

    public void onOutputFormatChanged(MediaCodec codec, MediaFormat format) {
        this.mEncOutputFormat = format;
        Log.d(Commons.TAG, "enc output format : " + format);
        if (this.mIsEncoderOnly) {
            this.mDone = true;
            this.callbackCountDown.countDown();
            return;
        }
        format.setInteger(this.extPrefix + "-ext-dec-picture-order.enable", 1);
        format.setInteger(this.extPrefix + "-ext-dec-low-latency.enable", 1);
        format.setInteger(this.extPrefix + KEY_DEC_VER, 0);
        if (this.mIsExynos && (VERSION.SDK_INT == 26 || VERSION.SDK_INT == 27)) {
            format.setInteger(this.extPrefix + KEY_ENC_VER, 0);
        }
        this.mDec.configure(format, null, null, 0);
        this.mDec.start();
        this.mDecConfigured = true;
        Log.d(Commons.TAG, "Decoder: configured");
    }
}
