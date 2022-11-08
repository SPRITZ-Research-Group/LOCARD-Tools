package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.c.g;
import com.google.android.gms.c.j;
import com.google.firebase.iid.ai;
import com.google.firebase.iid.o;
import com.google.firebase.iid.zzb;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FirebaseMessagingService extends zzb {
    private static final Queue<String> b = new ArrayDeque(10);

    static void a(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    static boolean b(Bundle bundle) {
        return bundle == null ? false : "1".equals(bundle.getString("google.c.a.e"));
    }

    protected final Intent a(Intent intent) {
        return o.a().b();
    }

    @WorkerThread
    public void a(RemoteMessage remoteMessage) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(Intent intent) {
        Object obj;
        int i = -1;
        Object obj2 = null;
        String action = intent.getAction();
        if (action == null) {
            action = "";
        }
        switch (action.hashCode()) {
            case 75300319:
                if (action.equals("com.google.firebase.messaging.NOTIFICATION_DISMISS")) {
                    int obj3 = 1;
                    break;
                }
            case 366519424:
                if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
                    obj3 = null;
                    break;
                }
            default:
                obj3 = -1;
                break;
        }
        String valueOf;
        switch (obj3) {
            case null:
                g a;
                Object stringExtra = intent.getStringExtra("google.message_id");
                if (TextUtils.isEmpty(stringExtra)) {
                    a = j.a();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("google.message_id", stringExtra);
                    a = ai.a((Context) this).a(bundle);
                }
                if (TextUtils.isEmpty(stringExtra)) {
                    stringExtra = null;
                } else if (b.contains(stringExtra)) {
                    if (Log.isLoggable("FirebaseMessaging", 3)) {
                        String str = "Received duplicate message: ";
                        valueOf = String.valueOf(stringExtra);
                        if (valueOf.length() != 0) {
                            str.concat(valueOf);
                        } else {
                            valueOf = new String(str);
                        }
                    }
                    int i2 = 1;
                } else {
                    if (b.size() >= 10) {
                        b.remove();
                    }
                    b.add(stringExtra);
                    stringExtra = null;
                }
                if (stringExtra == null) {
                    valueOf = intent.getStringExtra("message_type");
                    if (valueOf == null) {
                        valueOf = "gcm";
                    }
                    switch (valueOf.hashCode()) {
                        case -2062414158:
                            if (valueOf.equals("deleted_messages")) {
                                i = 1;
                                break;
                            }
                            break;
                        case 102161:
                            if (valueOf.equals("gcm")) {
                                i = 0;
                                break;
                            }
                            break;
                        case 814694033:
                            if (valueOf.equals("send_error")) {
                                i = 3;
                                break;
                            }
                            break;
                        case 814800675:
                            if (valueOf.equals("send_event")) {
                                i = 2;
                                break;
                            }
                            break;
                    }
                    switch (i) {
                        case 0:
                            if (b(intent.getExtras())) {
                                e.a(intent);
                            }
                            Bundle extras = intent.getExtras();
                            if (extras == null) {
                                extras = new Bundle();
                            }
                            extras.remove("android.support.content.wakelockid");
                            if ("1".equals(d.a(extras, "gcm.n.e")) || d.a(extras, "gcm.n.icon") != null) {
                                obj2 = 1;
                            }
                            if (obj2 != null) {
                                if (!d.a((Context) this).a(extras)) {
                                    if (b(extras)) {
                                        e.d(intent);
                                    }
                                }
                            }
                            a(new RemoteMessage(extras));
                            break;
                        case 1:
                            break;
                        case 2:
                            intent.getStringExtra("google.message_id");
                            break;
                        case 3:
                            if (intent.getStringExtra("google.message_id") == null) {
                                intent.getStringExtra("message_id");
                            }
                            c cVar = new c(intent.getStringExtra("error"));
                            break;
                        default:
                            String str2 = "Received message with unknown type: ";
                            valueOf = String.valueOf(valueOf);
                            if (valueOf.length() == 0) {
                                valueOf = new String(str2);
                                break;
                            } else {
                                str2.concat(valueOf);
                                break;
                            }
                    }
                }
                try {
                    j.a(a, 1, TimeUnit.SECONDS);
                    return;
                } catch (ExecutionException e) {
                    obj3 = e;
                    break;
                } catch (InterruptedException e2) {
                    obj3 = e2;
                    break;
                } catch (TimeoutException e3) {
                    obj3 = e3;
                    break;
                }
            case 1:
                if (b(intent.getExtras())) {
                    e.c(intent);
                    return;
                }
                return;
            default:
                action = "Unknown intent action: ";
                valueOf = String.valueOf(intent.getAction());
                if (valueOf.length() != 0) {
                    action.concat(valueOf);
                    return;
                } else {
                    valueOf = new String(action);
                    return;
                }
        }
        action = String.valueOf(obj3);
        new StringBuilder(String.valueOf(action).length() + 20).append("Message ack failed: ").append(action);
    }

    public final boolean c(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException e) {
            }
        }
        if (b(intent.getExtras())) {
            e.b(intent);
        }
        return true;
    }
}
