package com.microsoft.react.videofxp;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.skype.android.video.hw.utils.CodecUtils;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class o {
    public String a;
    public String b;
    private m c;
    private b d;
    private boolean e = false;
    private final ReentrantLock f = new ReentrantLock();
    private long g = 0;

    public final ar a(String sourcePath, String destinationPath, String overlayPath, String thumbnailPath, int lensMode, float lensIntensity, p options, a<r> progress) throws Exception {
        this.a = null;
        this.b = null;
        MediaFormat outputVideoFormat = b(a(sourcePath), a(destinationPath), a(overlayPath), a(thumbnailPath), lensMode, lensIntensity, options, progress);
        if (outputVideoFormat == null) {
            return null;
        }
        ar map = new WritableNativeMap();
        map.putString(ReactVideoViewManager.PROP_SRC_URI, destinationPath);
        map.putString("thumbnailUri", thumbnailPath);
        map.putInt("width", outputVideoFormat.getInteger("width"));
        map.putInt("height", outputVideoFormat.getInteger("height"));
        map.putInt("sourceFileSize", (int) new File(a(sourcePath)).length());
        map.putInt("fileSize", (int) new File(a(destinationPath)).length());
        map.putInt("estimatedFileSize", (int) this.g);
        return map;
    }

    public final void a() {
        this.f.lock();
        try {
            this.e = true;
            if (this.c != null) {
                this.c.a();
            }
            if (this.d != null) {
                this.d.a();
            }
            this.c = null;
            this.d = null;
        } finally {
            this.f.unlock();
        }
    }

    private static String a(String path) {
        return path != null ? path.replaceFirst("^[^/]*/+", "/") : null;
    }

    private MediaFormat b(String sourcePath, String destinationPath, String overlayPath, String thumbnailPath, int lensMode, float lensIntensity, p options, a<r> progress) throws Exception {
        Exception e;
        FLog.i("VideoFXPReencoder", "reencodeVideo source " + sourcePath + " destination " + destinationPath + " thumbnail " + thumbnailPath + " overlay " + overlayPath);
        MediaExtractor videoExtractor = null;
        MediaExtractor audioExtractor = null;
        MediaCodec audioDecoder = null;
        MediaCodec audioEncoder = null;
        Bitmap overlay = null;
        Exception exceptionCaught = null;
        if (overlayPath != null) {
            overlay = BitmapFactory.decodeFile(overlayPath);
            if (overlay != null) {
                FLog.i("VideoFXPReencoder", "overlay dimensions are " + overlay.getWidth() + " x " + overlay.getHeight());
            }
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        boolean isCancelled;
        MediaFormat outputVideoFormat;
        try {
            videoExtractor = b(sourcePath);
            int videoInputTrack = a(videoExtractor);
            if (videoInputTrack == -1) {
                this.a = "NoVideoTrack";
                this.b = "No video track";
                videoExtractor.release();
                if (overlay != null) {
                    overlay.recycle();
                }
                this.f.lock();
                try {
                    isCancelled = this.e;
                    if (isCancelled) {
                        this.a = "Cancelled";
                        this.b = "Reencode was cancelled";
                    }
                    atomicBoolean.get();
                    outputVideoFormat = null;
                    return null;
                } finally {
                    this.f.unlock();
                }
            } else {
                MediaFormat videoInputFormat = videoExtractor.getTrackFormat(videoInputTrack);
                FLog.i("VideoFXPReencoder", "input video format: " + videoInputFormat);
                this.g = (long) ((((double) (options.b() + options.a())) * ((double) (videoInputFormat.getLong("durationUs") / 1000000))) / 8.0d);
                long actualSize = new File(sourcePath).length();
                double ratio = actualSize > 0 ? (((double) actualSize) - ((double) this.g)) / ((double) actualSize) : 0.0d;
                FLog.i("VideoFXPReencoder", "Actual size: " + actualSize + ", estimated size:" + this.g + ", ratio:" + ratio);
                if (this.g > ((long) options.c)) {
                    this.a = "TooLargeFile";
                    this.b = "Reencoded file is too large: " + this.g + ", max: " + options.c;
                    videoExtractor.release();
                    if (overlay != null) {
                        overlay.recycle();
                    }
                    this.f.lock();
                    try {
                        isCancelled = this.e;
                        if (isCancelled) {
                            this.a = "Cancelled";
                            this.b = "Reencode was cancelled";
                        }
                        atomicBoolean.get();
                        outputVideoFormat = null;
                        return null;
                    } finally {
                        this.f.unlock();
                    }
                } else {
                    Object obj;
                    int i;
                    int i2;
                    if (overlay == null) {
                        if (ratio < options.b) {
                            this.a = "CompressionRatio";
                            this.b = "Estimated to actual file size ratio = " + ratio + ", min required = " + options.b;
                            videoExtractor.release();
                            if (overlay != null) {
                                overlay.recycle();
                            }
                            this.f.lock();
                            try {
                                isCancelled = this.e;
                                if (isCancelled) {
                                    this.a = "Cancelled";
                                    this.b = "Reencode was cancelled";
                                }
                                atomicBoolean.get();
                                outputVideoFormat = null;
                                return null;
                            } finally {
                                this.f.unlock();
                            }
                        }
                    }
                    int integer = videoInputFormat.getInteger("width");
                    int integer2 = videoInputFormat.getInteger("height");
                    if (integer2 > integer) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    switch (options.a) {
                        case SD:
                            i = 640;
                            break;
                        case HD:
                            i = 1280;
                            break;
                        case Custom:
                            i = options.e;
                            break;
                        default:
                            i = 640;
                            break;
                    }
                    switch (options.a) {
                        case SD:
                            i2 = 360;
                            break;
                        case HD:
                            i2 = 720;
                            break;
                        case Custom:
                            i2 = options.f;
                            break;
                        default:
                            i2 = 360;
                            break;
                    }
                    int b = options.b();
                    int i3 = obj != null ? i2 : i;
                    if (obj == null) {
                        i = i2;
                    }
                    Object obj2 = (VERSION.SDK_INT >= 23 && videoInputFormat.containsKey("rotation-degrees") && videoInputFormat.getInteger("rotation-degrees") % 180 == 90) ? 1 : null;
                    if (integer > i3 || integer2 > i) {
                        if (integer2 / integer > i / i3) {
                            integer2 = (integer * i) / integer2;
                            integer = i;
                        } else {
                            integer = (integer2 * i3) / integer;
                            integer2 = i3;
                        }
                        if (integer2 % 2 == 1 && integer2 > 2) {
                            integer2--;
                        }
                        if (integer % 2 == 1 && integer > 2) {
                            integer--;
                        }
                        int i4 = integer;
                        integer = integer2;
                        integer2 = i4;
                    }
                    String str = CodecUtils.MEDIA_TYPE;
                    if (obj2 != null) {
                        i = integer2;
                    } else {
                        i = integer;
                    }
                    if (obj2 == null) {
                        integer = integer2;
                    }
                    outputVideoFormat = MediaFormat.createVideoFormat(str, i, integer);
                    outputVideoFormat.setInteger("color-format", 2130708361);
                    outputVideoFormat.setInteger("bitrate", b);
                    outputVideoFormat.setInteger("frame-rate", 30);
                    outputVideoFormat.setInteger("i-frame-interval", 1);
                    outputVideoFormat.getInteger("bitrate");
                    FLog.i("VideoFXPReencoder", "output video format: " + outputVideoFormat);
                    if (overlay == null) {
                        overlay = Bitmap.createBitmap(videoInputFormat.getInteger("width"), videoInputFormat.getInteger("height"), Config.ARGB_8888);
                    } else if (videoInputFormat.containsKey("rotation-degrees")) {
                        int rotation = videoInputFormat.getInteger("rotation-degrees");
                        float rotate = 0.0f;
                        switch (rotation) {
                            case 90:
                                rotate = -90.0f;
                                break;
                            case 180:
                                rotate = 180.0f;
                                break;
                            case 270:
                                rotate = 90.0f;
                                break;
                        }
                        FLog.i("VideoFXPReencoder", "Rotating overlay to " + rotate + " because video rotated to " + rotation);
                        Matrix matrix = new Matrix();
                        matrix.postRotate(rotate);
                        Bitmap rotatedOverlay = Bitmap.createBitmap(overlay, 0, 0, overlay.getWidth(), overlay.getHeight(), matrix, true);
                        if (rotatedOverlay != overlay) {
                            overlay.recycle();
                        }
                        overlay = rotatedOverlay;
                    }
                    audioExtractor = b(sourcePath);
                    int audioInputTrack = b(audioExtractor);
                    if (audioInputTrack != -1) {
                        MediaFormat audioInputFormat = audioExtractor.getTrackFormat(audioInputTrack);
                        FLog.i("VideoFXPReencoder", "input audio format: " + audioInputFormat);
                        MediaFormat audioOutputFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", audioInputFormat.containsKey("sample-rate") ? audioInputFormat.getInteger("sample-rate") : 44100, audioInputFormat.containsKey("channel-count") ? audioInputFormat.getInteger("channel-count") : 1);
                        audioOutputFormat.setInteger("bitrate", options.a());
                        FLog.i("VideoFXPReencoder", "output audio format: " + audioInputFormat);
                        MediaCodec audioEncoder2 = MediaCodec.createEncoderByType("audio/mp4a-latm");
                        audioEncoder2.configure(audioOutputFormat, null, null, 1);
                        audioEncoder2.start();
                        try {
                            MediaCodec audioDecoder2 = MediaCodec.createDecoderByType("audio/mp4a-latm");
                            audioDecoder2.configure(audioInputFormat, null, null, 0);
                            audioDecoder2.start();
                            audioEncoder = audioEncoder2;
                            audioDecoder = audioDecoder2;
                        } catch (Exception e2) {
                            e = e2;
                            audioEncoder = audioEncoder2;
                            try {
                                FLog.e("VideoFXPReencoder", "Reencode failed " + e.getLocalizedMessage());
                                atomicBoolean.set(true);
                                exceptionCaught = e;
                                this.a = "ReencodeError";
                                this.b = "Reencode failed " + e.getLocalizedMessage();
                                if (videoExtractor != null) {
                                    videoExtractor.release();
                                }
                                if (audioExtractor != null) {
                                    audioExtractor.release();
                                }
                                if (audioDecoder != null) {
                                    audioDecoder.stop();
                                    audioDecoder.release();
                                }
                                if (audioEncoder != null) {
                                    audioEncoder.stop();
                                    audioEncoder.release();
                                }
                                if (overlay != null) {
                                    overlay.recycle();
                                }
                                throw exceptionCaught;
                            } catch (Throwable th) {
                                if (videoExtractor != null) {
                                    videoExtractor.release();
                                }
                                if (audioExtractor != null) {
                                    audioExtractor.release();
                                }
                                if (audioDecoder != null) {
                                    audioDecoder.stop();
                                    audioDecoder.release();
                                }
                                if (audioEncoder != null) {
                                    audioEncoder.stop();
                                    audioEncoder.release();
                                }
                                if (overlay != null) {
                                    overlay.recycle();
                                }
                                if (exceptionCaught != null) {
                                    this.f.lock();
                                    try {
                                        isCancelled = this.e;
                                        if (isCancelled) {
                                            this.a = "Cancelled";
                                            this.b = "Reencode was cancelled";
                                        }
                                        if (!atomicBoolean.get()) {
                                        }
                                        return null;
                                    } finally {
                                        this.f.unlock();
                                    }
                                } else {
                                    throw exceptionCaught;
                                }
                            }
                        } catch (Throwable th2) {
                            audioEncoder = audioEncoder2;
                            if (videoExtractor != null) {
                                videoExtractor.release();
                            }
                            if (audioExtractor != null) {
                                audioExtractor.release();
                            }
                            if (audioDecoder != null) {
                                audioDecoder.stop();
                                audioDecoder.release();
                            }
                            if (audioEncoder != null) {
                                audioEncoder.stop();
                                audioEncoder.release();
                            }
                            if (overlay != null) {
                                overlay.recycle();
                            }
                            if (exceptionCaught != null) {
                                throw exceptionCaught;
                            }
                            this.f.lock();
                            isCancelled = this.e;
                            if (isCancelled) {
                                this.a = "Cancelled";
                                this.b = "Reencode was cancelled";
                            }
                            if (atomicBoolean.get() || isCancelled) {
                                return null;
                            }
                            return outputVideoFormat;
                        }
                    }
                    try {
                        g muxerWrapper = new g(destinationPath);
                        final a<r> aVar = progress;
                        final m videoProcessor = new m(videoExtractor, thumbnailPath, muxerWrapper, outputVideoFormat, videoInputFormat, lensMode, lensIntensity, overlay, options, new a<n>(this) {
                            final /* synthetic */ o b;

                            public final /* synthetic */ void a(Object obj) {
                                aVar.a(new r(((n) obj).a(), this.b.g));
                            }
                        });
                        b bVar = new b(audioExtractor, audioDecoder, audioEncoder, muxerWrapper);
                        this.f.lock();
                        this.c = videoProcessor;
                        this.d = bVar;
                        this.f.unlock();
                        final AtomicBoolean atomicBoolean2 = atomicBoolean;
                        final b bVar2 = bVar;
                        UncaughtExceptionHandler anonymousClass2 = new UncaughtExceptionHandler(this) {
                            final /* synthetic */ o d;

                            public final void uncaughtException(Thread th, Throwable ex) {
                                try {
                                    FLog.e("VideoFXPReencoder", "Reencode interrupted thread=" + th.getName() + ", exception=" + ex.getLocalizedMessage());
                                    atomicBoolean2.set(true);
                                    if (videoProcessor != null) {
                                        videoProcessor.a();
                                    }
                                    if (bVar2 != null) {
                                        bVar2.a();
                                    }
                                } catch (Exception e) {
                                    FLog.e("VideoFXPReencoder", "Got exception in UncaughtExceptionHandler " + e.getLocalizedMessage());
                                }
                            }
                        };
                        Thread thread = new Thread(videoProcessor);
                        thread.setUncaughtExceptionHandler(anonymousClass2);
                        thread.start();
                        thread = new Thread(bVar);
                        thread.setUncaughtExceptionHandler(anonymousClass2);
                        thread.start();
                        FLog.i("VideoFXPReencoder", "Threads started");
                        try {
                            thread.join();
                            thread.join();
                        } catch (Exception e3) {
                            FLog.e("VideoFXPReencoder", "Threads interrupted " + e3.getLocalizedMessage());
                            atomicBoolean.set(true);
                            exceptionCaught = e3;
                        }
                        FLog.i("VideoFXPReencoder", "Done with processing!");
                        videoExtractor.release();
                        audioExtractor.release();
                        if (audioDecoder != null) {
                            audioDecoder.stop();
                            audioDecoder.release();
                        }
                        if (audioEncoder != null) {
                            audioEncoder.stop();
                            audioEncoder.release();
                        }
                        if (overlay != null) {
                            overlay.recycle();
                        }
                        if (exceptionCaught != null) {
                            throw exceptionCaught;
                        }
                        this.f.lock();
                        try {
                            isCancelled = this.e;
                            if (isCancelled) {
                                this.a = "Cancelled";
                                this.b = "Reencode was cancelled";
                            }
                            if (atomicBoolean.get() || isCancelled) {
                                return null;
                            }
                            return outputVideoFormat;
                        } finally {
                            this.f.unlock();
                        }
                    } catch (Exception e4) {
                        e3 = e4;
                        FLog.e("VideoFXPReencoder", "Reencode failed " + e3.getLocalizedMessage());
                        atomicBoolean.set(true);
                        exceptionCaught = e3;
                        this.a = "ReencodeError";
                        this.b = "Reencode failed " + e3.getLocalizedMessage();
                        if (videoExtractor != null) {
                            videoExtractor.release();
                        }
                        if (audioExtractor != null) {
                            audioExtractor.release();
                        }
                        if (audioDecoder != null) {
                            audioDecoder.stop();
                            audioDecoder.release();
                        }
                        if (audioEncoder != null) {
                            audioEncoder.stop();
                            audioEncoder.release();
                        }
                        if (overlay != null) {
                            overlay.recycle();
                        }
                        throw exceptionCaught;
                    } catch (Throwable th3) {
                        this.f.unlock();
                    }
                }
            }
        } catch (Exception e5) {
            e3 = e5;
            outputVideoFormat = null;
            FLog.e("VideoFXPReencoder", "Reencode failed " + e3.getLocalizedMessage());
            atomicBoolean.set(true);
            exceptionCaught = e3;
            this.a = "ReencodeError";
            this.b = "Reencode failed " + e3.getLocalizedMessage();
            if (videoExtractor != null) {
                videoExtractor.release();
            }
            if (audioExtractor != null) {
                audioExtractor.release();
            }
            if (audioDecoder != null) {
                audioDecoder.stop();
                audioDecoder.release();
            }
            if (audioEncoder != null) {
                audioEncoder.stop();
                audioEncoder.release();
            }
            if (overlay != null) {
                overlay.recycle();
            }
            throw exceptionCaught;
        } catch (Throwable th4) {
            outputVideoFormat = null;
            if (videoExtractor != null) {
                videoExtractor.release();
            }
            if (audioExtractor != null) {
                audioExtractor.release();
            }
            if (audioDecoder != null) {
                audioDecoder.stop();
                audioDecoder.release();
            }
            if (audioEncoder != null) {
                audioEncoder.stop();
                audioEncoder.release();
            }
            if (overlay != null) {
                overlay.recycle();
            }
            if (exceptionCaught != null) {
                throw exceptionCaught;
            }
            this.f.lock();
            isCancelled = this.e;
            if (isCancelled) {
                this.a = "Cancelled";
                this.b = "Reencode was cancelled";
            }
            if (atomicBoolean.get()) {
            }
            return null;
        }
    }

    private static MediaExtractor b(String source) throws IOException {
        MediaExtractor extractor = new MediaExtractor();
        extractor.setDataSource(source);
        FLog.i("VideoFXPReencoder", "createExtractor setDataSource" + source);
        return extractor;
    }

    private static int a(MediaExtractor extractor) {
        for (int index = 0; index < extractor.getTrackCount(); index++) {
            FLog.i("VideoFXPReencoder", "format for track " + index + " is " + extractor.getTrackFormat(index).getString("mime"));
            if (extractor.getTrackFormat(index).getString("mime").startsWith("video/")) {
                extractor.selectTrack(index);
                return index;
            }
        }
        return -1;
    }

    private static int b(MediaExtractor extractor) {
        for (int index = 0; index < extractor.getTrackCount(); index++) {
            FLog.i("VideoFXPReencoder", "format for track " + index + " is " + extractor.getTrackFormat(index).getString("mime"));
            if (extractor.getTrackFormat(index).getString("mime").startsWith("audio/")) {
                extractor.selectTrack(index);
                return index;
            }
        }
        return -1;
    }
}
