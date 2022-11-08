package com.microsoft.dl.audio;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build.VERSION;
import com.microsoft.dl.utils.Log;

public class AudioHwOffload {
    private static AcousticEchoCanceler a;
    private static NoiseSuppressor b;
    private static AutomaticGainControl c;
    private int d = 3;
    public int recorderID = 0;

    public AudioHwOffload() {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "AudioHwOffload class instantiated");
        }
    }

    private static int a(int rate, int audioSource) {
        int id = 0;
        int bufSize = AudioRecord.getMinBufferSize(rate, 16, 2);
        if (bufSize < 0) {
            if (bufSize == -2) {
                Log.log(6, PackageInfo.TAG, "Get AudioRecord failed, sample rate not supported.");
                return 0;
            } else if (bufSize == -1) {
                Log.log(6, PackageInfo.TAG, "Get AudioRecord failed, device is busy.");
                return 0;
            } else {
                Log.log(6, PackageInfo.TAG, "Get AudioRecord failed, unknown error.");
                return 0;
            }
        } else if (VERSION.SDK_INT >= 16) {
            try {
                AudioRecord audioRecord = new AudioRecord(audioSource, rate, 16, 2, bufSize);
                if (audioRecord.getState() == 1) {
                    id = audioRecord.getAudioSessionId();
                } else {
                    Log.log(6, PackageInfo.TAG, "AudioRecorder failed to initialize, no audio session ID!");
                }
                audioRecord.release();
            } catch (RuntimeException e) {
                Log.log(6, PackageInfo.TAG, "Exception occured in new AudioRecord(): ", e);
            }
            return id;
        } else if (!Log.isLoggable(PackageInfo.TAG, 3)) {
            return 0;
        } else {
            Log.d(PackageInfo.TAG, "AudioRecord.getAudioSessionId not available in this version of OS");
            return 0;
        }
    }

    public int getAudioRecordSessionID(int rate, int channelConfig, boolean useVoiceComm) {
        int audioSource;
        if (useVoiceComm) {
            audioSource = 7;
        } else {
            audioSource = 0;
        }
        int ret = 0;
        int id1 = a(rate, audioSource);
        int id2 = a(rate, audioSource);
        if (!(id1 == 0 || id2 == 0 || id1 == id2)) {
            int step = id2 - id1;
            this.recorderID = id2 + step;
            id1 = a(rate, audioSource);
            if (id1 == this.recorderID) {
                this.recorderID += step;
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "Successfully got record sessionID=" + this.recorderID);
                }
                this.d = 3;
                return this.recorderID;
            }
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "record sessionID not reliable, ignoring it. Got (" + id1 + ") expected (" + this.recorderID + "). And will retry.");
            }
            int i = this.d;
            this.d = i - 1;
            if (i > 0) {
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "Retry to get sessionID for the " + (3 - this.d) + " time.");
                }
                ret = getAudioRecordSessionID(rate, channelConfig, useVoiceComm);
            } else {
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "Have tried to get record sessionID 3 times, and will give it up.");
                }
                this.d = 3;
                return 0;
            }
        }
        return ret;
    }

    public int getDefaultBuiltinEffect(int captureSessionID) {
        int audioOffloadCapabilityMask = 0;
        if (VERSION.SDK_INT < 16) {
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "Offload API not available in this version of OS");
            }
            return 0;
        }
        if (AcousticEchoCanceler.isAvailable()) {
            AcousticEchoCanceler create = AcousticEchoCanceler.create(captureSessionID);
            a = create;
            if (create != null) {
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "AEC offload isAvailable: isEnabledDefault()=" + a.getEnabled() + " isControlling=" + a.hasControl());
                }
                a.release();
                a = null;
                audioOffloadCapabilityMask = 1;
            } else {
                Log.log(5, PackageInfo.TAG, "AcousticEchoCanceler.create() returned null");
            }
        } else if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "AEC offload is NOT available");
        }
        if (NoiseSuppressor.isAvailable()) {
            NoiseSuppressor create2 = NoiseSuppressor.create(captureSessionID);
            b = create2;
            if (create2 != null) {
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "NS offload isAvailable: isEnabledDefault()=" + b.getEnabled() + " isControlling=" + b.hasControl());
                }
                b.release();
                b = null;
                audioOffloadCapabilityMask |= 4;
            } else {
                Log.log(5, PackageInfo.TAG, "NoiseSuppressor.create() returned null");
            }
        } else if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "NS offload is NOT available");
        }
        if (AutomaticGainControl.isAvailable()) {
            AutomaticGainControl create3 = AutomaticGainControl.create(captureSessionID);
            c = create3;
            if (create3 != null) {
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "AEC offload isAvailable: isEnabledDefault()=" + c.getEnabled() + " isControlling=" + c.hasControl());
                }
                c.release();
                c = null;
                audioOffloadCapabilityMask |= 2;
            } else {
                Log.log(5, PackageInfo.TAG, "AutomaticGainControl.create() returned null");
            }
        } else if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "AGC offload is NOT available");
        }
        return audioOffloadCapabilityMask;
    }

    public int enableAudioEffects(int captureSessionID, int hwOffload) {
        int retValue = 1;
        if (VERSION.SDK_INT >= 16) {
            boolean enableEffect;
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "EnableAudioEffect ID(" + captureSessionID + ") Offload(" + hwOffload + ")");
            }
            if (AcousticEchoCanceler.isAvailable()) {
                if (a != null) {
                    a.release();
                    a = null;
                }
                AcousticEchoCanceler create = AcousticEchoCanceler.create(captureSessionID);
                a = create;
                if (create != null) {
                    if (Log.isLoggable(PackageInfo.TAG, 3)) {
                        Log.d(PackageInfo.TAG, "AEC offload isAvailable: isEnabledDefault()=" + a.getEnabled() + " isControlling=" + a.hasControl());
                    }
                    enableEffect = (hwOffload & 1) != 0;
                    try {
                        a.setEnabled(enableEffect);
                        if (Log.isLoggable(PackageInfo.TAG, 3)) {
                            Log.d(PackageInfo.TAG, "AEC has been set to " + enableEffect + " - status IsEnabled(): " + a.getEnabled());
                        }
                    } catch (RuntimeException e) {
                        Log.log(6, PackageInfo.TAG, "Exception occured in AEC.setEnabled(" + enableEffect + "): ", e);
                        retValue = 0;
                    }
                } else {
                    Log.log(5, PackageInfo.TAG, "AcousticEchoCanceler.create() returned null");
                }
            } else if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "AEC is NOT available");
            }
            if (NoiseSuppressor.isAvailable()) {
                if (b != null) {
                    b.release();
                    b = null;
                }
                NoiseSuppressor create2 = NoiseSuppressor.create(captureSessionID);
                b = create2;
                if (create2 != null) {
                    if (Log.isLoggable(PackageInfo.TAG, 3)) {
                        Log.d(PackageInfo.TAG, "NS offload isAvailable: isEnabledDefault()=" + b.getEnabled() + " isControlling=" + b.hasControl());
                    }
                    enableEffect = (hwOffload & 4) != 0;
                    try {
                        b.setEnabled(enableEffect);
                        if (Log.isLoggable(PackageInfo.TAG, 3)) {
                            Log.d(PackageInfo.TAG, "NS has been set to " + enableEffect + " - status IsEnabled(): " + b.getEnabled());
                        }
                    } catch (RuntimeException e2) {
                        Log.log(6, PackageInfo.TAG, "Exception occured in NS.setEnabled(" + enableEffect + "): ", e2);
                        retValue = 0;
                    }
                } else {
                    Log.log(5, PackageInfo.TAG, "NoiseSuppressor.create() returned null");
                }
            } else if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "NS is NOT available");
            }
            if (AutomaticGainControl.isAvailable()) {
                if (c != null) {
                    c.release();
                    c = null;
                }
                AutomaticGainControl create3 = AutomaticGainControl.create(captureSessionID);
                c = create3;
                if (create3 != null) {
                    if (Log.isLoggable(PackageInfo.TAG, 3)) {
                        Log.d(PackageInfo.TAG, "AGC offload isAvailable: isEnabledDefault()=" + c.getEnabled() + " isControlling=" + c.hasControl());
                    }
                    enableEffect = (hwOffload & 2) != 0;
                    try {
                        c.setEnabled(enableEffect);
                        if (Log.isLoggable(PackageInfo.TAG, 3)) {
                            Log.d(PackageInfo.TAG, "AGC has been set to " + enableEffect + " - status IsEnabled(): " + c.getEnabled());
                        }
                    } catch (RuntimeException e22) {
                        Log.log(6, PackageInfo.TAG, "Exception occured in AGC.setEnabled(" + enableEffect + "): ", e22);
                        retValue = 0;
                    }
                } else {
                    Log.log(5, PackageInfo.TAG, "AutomaticGainControl.create() returned null");
                }
            } else if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "AGC is NOT available");
            }
            return retValue;
        } else if (!Log.isLoggable(PackageInfo.TAG, 3)) {
            return 0;
        } else {
            Log.d(PackageInfo.TAG, "OS version too old for enabling HW offloads");
            return 0;
        }
    }
}
