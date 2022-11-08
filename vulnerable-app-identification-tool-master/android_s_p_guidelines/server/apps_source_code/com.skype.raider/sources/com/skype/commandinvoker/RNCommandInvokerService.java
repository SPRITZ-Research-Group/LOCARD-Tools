package com.skype.commandinvoker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.skype.commandinvoker.RNCommandInvokerPackage.CommandProxyExecutor;
import java.util.Locale;
import java.util.Random;

public class RNCommandInvokerService extends Service {
    private static final Random a = new Random();

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String command = intent.getAction().toLowerCase(Locale.getDefault());
            boolean z = true;
            switch (command.hashCode()) {
                case -1224574323:
                    if (command.equals("hangup")) {
                        z = true;
                        break;
                    }
                    break;
                case -1097329270:
                    if (command.equals("logout")) {
                        z = true;
                        break;
                    }
                    break;
                case -840405966:
                    if (command.equals("unmute")) {
                        z = true;
                        break;
                    }
                    break;
                case 3045982:
                    if (command.equals("call")) {
                        z = true;
                        break;
                    }
                    break;
                case 3198785:
                    if (command.equals("help")) {
                        z = false;
                        break;
                    }
                    break;
                case 3363353:
                    if (command.equals("mute")) {
                        z = true;
                        break;
                    }
                    break;
                case 103149417:
                    if (command.equals("login")) {
                        z = true;
                        break;
                    }
                    break;
                case 1658166656:
                    if (command.equals("answer_inc_call")) {
                        z = true;
                        break;
                    }
                    break;
            }
            Object stringExtra;
            Object format;
            final ar writableNativeMap;
            final ar writableNativeMap2;
            boolean booleanExtra;
            switch (z) {
                case false:
                    String str = "RNCommandInvokerService";
                    FLog.i(str, "Supported commands:\n\n" + String.format("adb -s <device id> shell am startservice -n %s/%s -a %s --es %s <msa email> --es %s <pwd>\n", new Object[]{getPackageName(), RNCommandInvokerService.class.getName(), "login", "msa_id", "pwd"}) + "\tLogs the specified user into the app, and logs out any other user. No-op if user is already logged in\n\n" + String.format("adb -s <device id> shell am startservice -n %s/%s -a %s", new Object[]{getPackageName(), RNCommandInvokerService.class.getName(), "logout"}) + "\n\tLogs out current user\n\n" + String.format("adb -s <device id> shell am startservice -n %s/%s -a %s --es %s <skypeId> --ez %s <true|false>\n", new Object[]{getPackageName(), RNCommandInvokerService.class.getName(), "call", "to", "video"}) + "\tInitiate outgoing call\n\n" + String.format("adb -s <device id> shell am startservice -n %s/%s -a %s --ez %s <true|false> --ei %s <seconds to wait before answering>\n", new Object[]{getPackageName(), RNCommandInvokerService.class.getName(), "answer_inc_call", "video", "react_time_in_sec"}) + "\tAnswer next incoming call call\n\n" + String.format("adb -s <device id> shell am startservice -n %s/%s -a %s", new Object[]{getPackageName(), RNCommandInvokerService.class.getName(), "hangup"}) + "\n\tHangup ongoing call\n\n" + String.format("adb -s <device id> shell am startservice -n %s/%s -a %s", new Object[]{getPackageName(), RNCommandInvokerService.class.getName(), "mute"}) + "\n\tMute ongoing call\n\n" + String.format("adb -s <device id> shell am startservice -n %s/%s -a %s", new Object[]{getPackageName(), RNCommandInvokerService.class.getName(), "unmute"}) + "\n\tUnmute ongoing call\n\n");
                    break;
                case true:
                    stringExtra = intent.getStringExtra("msa_id");
                    String stringExtra2 = intent.getStringExtra("pwd");
                    format = String.format("%x", new Object[]{Integer.valueOf(a.nextInt())});
                    FLog.i("RNCommandInvokerService", "handleLogin msa: %s causeId: %s", stringExtra, format);
                    writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putString("user", stringExtra);
                    writableNativeMap.putString("password", stringExtra2);
                    writableNativeMap.putString("causeId", format);
                    RNCommandInvokerPackage.a(format, new CommandProxyExecutor(this) {
                        final /* synthetic */ RNCommandInvokerService b;

                        public final void a(RNCommandInvokerModule commandModule) {
                            commandModule.sendEvent("CommandInvoker-LoginEvent", writableNativeMap);
                        }
                    });
                    break;
                case true:
                    stringExtra = String.format("%x", new Object[]{Integer.valueOf(a.nextInt())});
                    FLog.i("RNCommandInvokerService", "handleLogout causeId: %s", stringExtra);
                    writableNativeMap2 = new WritableNativeMap();
                    writableNativeMap2.putString("causeId", stringExtra);
                    RNCommandInvokerPackage.a(stringExtra, new CommandProxyExecutor(this) {
                        final /* synthetic */ RNCommandInvokerService b;

                        public final void a(RNCommandInvokerModule commandModule) {
                            commandModule.sendEvent("CommandInvoker-LogoutEvent", writableNativeMap2);
                        }
                    });
                    break;
                case true:
                    stringExtra = intent.getStringExtra("to");
                    booleanExtra = intent.getBooleanExtra("video", false);
                    format = String.format("%x", new Object[]{Integer.valueOf(a.nextInt())});
                    FLog.i("RNCommandInvokerService", "handleStartCall skypeId: %s video: %b causeId: %s", stringExtra, Boolean.valueOf(booleanExtra), format);
                    writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putString("skypeId", stringExtra);
                    writableNativeMap.putBoolean("video", booleanExtra);
                    writableNativeMap.putString("causeId", format);
                    RNCommandInvokerPackage.a(format, new CommandProxyExecutor(this) {
                        final /* synthetic */ RNCommandInvokerService b;

                        public final void a(RNCommandInvokerModule commandModule) {
                            commandModule.sendEvent("CommandInvoker-StartCallEvent", writableNativeMap);
                        }
                    });
                    break;
                case true:
                    int intExtra = intent.getIntExtra("react_time_in_sec", 3);
                    booleanExtra = intent.getBooleanExtra("video", false);
                    format = String.format("%x", new Object[]{Integer.valueOf(a.nextInt())});
                    FLog.i("RNCommandInvokerService", "handleAnswerCall reactTime: %d video: %b causeId: %s", Integer.valueOf(intExtra), Boolean.valueOf(booleanExtra), format);
                    writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putInt("userReactTime", intExtra);
                    writableNativeMap.putBoolean("video", booleanExtra);
                    writableNativeMap.putString("causeId", format);
                    RNCommandInvokerPackage.a(format, new CommandProxyExecutor(this) {
                        final /* synthetic */ RNCommandInvokerService b;

                        public final void a(RNCommandInvokerModule commandModule) {
                            commandModule.sendEvent("CommandInvoker-AnswerCallEvent", writableNativeMap);
                        }
                    });
                    break;
                case true:
                    stringExtra = String.format("%x", new Object[]{Integer.valueOf(a.nextInt())});
                    FLog.i("RNCommandInvokerService", "handleHangupCall causeId: %s", stringExtra);
                    writableNativeMap2 = new WritableNativeMap();
                    writableNativeMap2.putString("causeId", stringExtra);
                    RNCommandInvokerPackage.a(stringExtra, new CommandProxyExecutor(this) {
                        final /* synthetic */ RNCommandInvokerService b;

                        public final void a(RNCommandInvokerModule commandModule) {
                            commandModule.sendEvent("CommandInvoker-EndCallEvent", writableNativeMap2);
                        }
                    });
                    break;
                case true:
                    a(true);
                    break;
                case true:
                    a(false);
                    break;
                default:
                    FLog.w("RNCommandInvokerService", "Unknown command: %s", command);
                    break;
            }
        }
        return 2;
    }

    private void a(boolean mute) {
        Object causeId = String.format("%x", new Object[]{Integer.valueOf(a.nextInt())});
        FLog.i("RNCommandInvokerService", "handleMuteUnmuteCall %b causeId: %s", Boolean.valueOf(mute), causeId);
        final ar params = new WritableNativeMap();
        params.putString("causeId", causeId);
        params.putBoolean("mute", mute);
        RNCommandInvokerPackage.a(causeId, new CommandProxyExecutor(this) {
            final /* synthetic */ RNCommandInvokerService b;

            public final void a(RNCommandInvokerModule commandModule) {
                commandModule.sendEvent("CommandInvoker-MuteUnmuteCallEvent", params);
            }
        });
    }
}
