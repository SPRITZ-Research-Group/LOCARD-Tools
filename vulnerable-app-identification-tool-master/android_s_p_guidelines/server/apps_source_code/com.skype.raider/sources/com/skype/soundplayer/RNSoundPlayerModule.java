package com.skype.soundplayer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.microsoft.skype.a.a.b;
import java.util.Random;

public class RNSoundPlayerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNSoundPlayerModule";
    private static final Random random = new Random();
    private static final com.microsoft.skype.a.a soundPlayerQueue = com.microsoft.skype.a.a.a(TAG, b.DEFAULT);
    private RCTDeviceEventEmitter eventEmitter;
    private final SparseArray<RNSoundPlayer> soundPlayers = new SparseArray();

    private interface a {
        void a(RNSoundPlayer rNSoundPlayer);
    }

    public RNSoundPlayerModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "SoundPlayer";
    }

    @ReactMethod
    public void prepare(final String filename, int type, final ae promise) {
        final int causeId = random.nextInt();
        Object soundType = RNSoundType.a(type);
        FLog.i(TAG, "prepare: '%s', type: %s (causeId %x)", (Object) filename, soundType, Integer.valueOf(causeId));
        RNSoundPlayer.a(getReactApplicationContext(), filename, soundType, new b(this) {
            final /* synthetic */ RNSoundPlayerModule d;

            public final void a(@Nullable final RNSoundPlayer player, @Nullable RNSoundPlayerException error) {
                if (player != null) {
                    FLog.i(RNSoundPlayerModule.TAG, "[%d] prepared (causeId %x)", Integer.valueOf(player.f()), Integer.valueOf(causeId));
                    RNSoundPlayerModule.soundPlayerQueue.c(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void run() {
                            this.b.d.soundPlayers.put(player.f(), player);
                            Object map = new WritableNativeMap();
                            map.putInt("token", player.f());
                            map.putInt("duration", player.d());
                            promise.a(map);
                        }
                    });
                    return;
                }
                FLog.e(RNSoundPlayerModule.TAG, (Throwable) error, "Failed to prepare player [%s] (causeId %x)", filename, Integer.valueOf(causeId));
                promise.a((Throwable) error);
            }
        }, new a(this) {
            final /* synthetic */ RNSoundPlayerModule a;

            {
                this.a = this$0;
            }

            public final void a(@NonNull RNSoundPlayer player) {
                FLog.i(RNSoundPlayerModule.TAG, "[%d] completed", Integer.valueOf(player.f()));
                ar map = new WritableNativeMap();
                map.putInt("token", player.f());
                this.a.getEmitter().emit("onSoundPlayerEnded", map);
            }
        }, causeId);
    }

    @ReactMethod
    public void setDeviceId(String deviceId, ae promise) {
        promise.a(null);
    }

    @ReactMethod
    public void play(int token, boolean loop, boolean progressUpdates) {
        int causeId = random.nextInt();
        FLog.i(TAG, "play [%d] [loop=%b, progressUpdates=%b] (causeId %x)", Integer.valueOf(token), Boolean.valueOf(loop), Boolean.valueOf(progressUpdates), Integer.valueOf(causeId));
        final int i = token;
        final int i2 = causeId;
        final boolean z = progressUpdates;
        final boolean z2 = loop;
        playerForToken(token, new a(this) {
            final /* synthetic */ RNSoundPlayerModule e;

            public final void a(RNSoundPlayer player) {
                if (player == null) {
                    FLog.w(RNSoundPlayerModule.TAG, "Failed to play - token %d not found (causeId %x)", Integer.valueOf(i), Integer.valueOf(i2));
                    return;
                }
                c progress = null;
                if (z) {
                    progress = new c(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = this$1;
                        }

                        public final void a(@NonNull RNSoundPlayer player, int currentPosition, int duration) {
                            ar map = new WritableNativeMap();
                            map.putInt("token", player.f());
                            map.putInt("currentPosition", currentPosition);
                            map.putInt("bufferedDuration", duration);
                            this.a.e.getEmitter().emit("onSoundPlayerProgress", map);
                        }
                    };
                }
                player.a(z2, progress, i2);
            }
        });
    }

    @ReactMethod
    public void pause(final int token) {
        final int causeId = random.nextInt();
        FLog.i(TAG, "pause [%d]", Integer.valueOf(token));
        playerForToken(token, new a(this) {
            final /* synthetic */ RNSoundPlayerModule c;

            public final void a(RNSoundPlayer player) {
                if (player == null) {
                    FLog.w(RNSoundPlayerModule.TAG, "Failed to pause - token %d not found (causeId %x)", Integer.valueOf(token), Integer.valueOf(causeId));
                    return;
                }
                player.a(causeId);
            }
        });
    }

    @ReactMethod
    public void stop(final int token) {
        final int causeId = random.nextInt();
        FLog.i(TAG, "stop [%d]", Integer.valueOf(token));
        playerForToken(token, new a(this) {
            final /* synthetic */ RNSoundPlayerModule c;

            public final void a(RNSoundPlayer player) {
                if (player == null) {
                    FLog.w(RNSoundPlayerModule.TAG, "Failed to stop - token %d not found (causeId %x)", Integer.valueOf(token), Integer.valueOf(causeId));
                    return;
                }
                player.b(causeId);
            }
        });
    }

    @ReactMethod
    public void seek(final int token, final int position) {
        final int causeId = random.nextInt();
        FLog.i(TAG, "seek [%d]", Integer.valueOf(token));
        playerForToken(token, new a(this) {
            final /* synthetic */ RNSoundPlayerModule d;

            public final void a(RNSoundPlayer player) {
                if (player == null) {
                    FLog.w(RNSoundPlayerModule.TAG, "Failed to seek - token %d not found (causeId %x)", Integer.valueOf(token), Integer.valueOf(causeId));
                    return;
                }
                player.a(position, causeId);
            }
        });
    }

    @ReactMethod
    public void release(final int token) {
        final int causeId = random.nextInt();
        FLog.i(TAG, "release [%d]", Integer.valueOf(token));
        playerForToken(token, new a(this) {
            final /* synthetic */ RNSoundPlayerModule c;

            public final void a(RNSoundPlayer player) {
                if (player == null) {
                    FLog.i(RNSoundPlayerModule.TAG, "Failed to release - token %d not found (causeId %x)", Integer.valueOf(token), Integer.valueOf(causeId));
                    return;
                }
                this.c.soundPlayers.remove(token);
                player.b(causeId);
                player.a();
            }
        });
    }

    private void playerForToken(final int token, @NonNull final a callback) {
        soundPlayerQueue.c(new Runnable(this) {
            final /* synthetic */ RNSoundPlayerModule c;

            public final void run() {
                callback.a((RNSoundPlayer) this.c.soundPlayers.get(token));
            }
        });
    }

    private RCTDeviceEventEmitter getEmitter() {
        if (this.eventEmitter == null) {
            this.eventEmitter = (RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class);
        }
        return this.eventEmitter;
    }
}
