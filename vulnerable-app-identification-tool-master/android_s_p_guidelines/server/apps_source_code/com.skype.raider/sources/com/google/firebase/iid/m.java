package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.util.l;
import android.util.Log;
import com.google.android.gms.c.h;
import com.google.android.gms.c.j;
import com.google.firebase.iid.zzi.a;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;

final class m {
    private static int a = 0;
    private static PendingIntent b;
    @GuardedBy("responseCallbacks")
    private final l<String, h<Bundle>> c = new l();
    private final Context d;
    private final f e;
    private Messenger f;
    private Messenger g;
    private zzi h;

    public m(Context context, f fVar) {
        this.d = context;
        this.e = fVar;
        this.f = new Messenger(new n(this, Looper.getMainLooper()));
    }

    private static synchronized String a() {
        String num;
        synchronized (m.class) {
            int i = a;
            a = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    private static synchronized void a(Context context, Intent intent) {
        synchronized (m.class) {
            if (b == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                b = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", b);
        }
    }

    static /* synthetic */ void a(m mVar, Message message) {
        if (message != null && (message.obj instanceof Intent)) {
            Intent intent = (Intent) message.obj;
            intent.setExtrasClassLoader(new a());
            if (intent.hasExtra("google.messenger")) {
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof zzi) {
                    mVar.h = (zzi) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    mVar.g = (Messenger) parcelableExtra;
                }
            }
            intent = (Intent) message.obj;
            String action = intent.getAction();
            String valueOf;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                CharSequence stringExtra = intent.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("unregistered");
                }
                String str;
                if (stringExtra == null) {
                    action = intent.getStringExtra("error");
                    if (action == null) {
                        valueOf = String.valueOf(intent.getExtras());
                        new StringBuilder(String.valueOf(valueOf).length() + 49).append("Unexpected response, no error or registration id ").append(valueOf);
                        return;
                    }
                    String valueOf2;
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        str = "Received InstanceID error ";
                        valueOf2 = String.valueOf(action);
                        if (valueOf2.length() != 0) {
                            str.concat(valueOf2);
                        } else {
                            valueOf2 = new String(str);
                        }
                    }
                    if (action.startsWith("|")) {
                        String[] split = action.split("\\|");
                        if (split.length <= 2 || !"ID".equals(split[1])) {
                            valueOf = "Unexpected structured response ";
                            action = String.valueOf(action);
                            if (action.length() != 0) {
                                valueOf.concat(action);
                                return;
                            } else {
                                action = new String(valueOf);
                                return;
                            }
                        }
                        valueOf2 = split[2];
                        action = split[3];
                        if (action.startsWith(":")) {
                            action = action.substring(1);
                        }
                        mVar.a(valueOf2, intent.putExtra("error", action).getExtras());
                        return;
                    }
                    synchronized (mVar.c) {
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 < mVar.c.size()) {
                                mVar.a((String) mVar.c.b(i2), intent.getExtras());
                                i = i2 + 1;
                            }
                        }
                    }
                    return;
                }
                Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                if (matcher.matches()) {
                    action = matcher.group(1);
                    str = matcher.group(2);
                    Bundle extras = intent.getExtras();
                    extras.putString("registration_id", str);
                    mVar.a(action, extras);
                } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    valueOf = "Unexpected response string: ";
                    action = String.valueOf(stringExtra);
                    if (action.length() != 0) {
                        valueOf.concat(action);
                    } else {
                        action = new String(valueOf);
                    }
                }
            } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
                valueOf = "Unexpected response action: ";
                action = String.valueOf(action);
                if (action.length() != 0) {
                    valueOf.concat(action);
                } else {
                    action = new String(valueOf);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a(String str, Bundle bundle) {
        synchronized (this.c) {
            h hVar = (h) this.c.remove(str);
            if (hVar == null) {
                String str2 = "Missing callback for ";
                String valueOf = String.valueOf(str);
                if (valueOf.length() != 0) {
                    str2.concat(valueOf);
                } else {
                    valueOf = new String(str2);
                }
            } else {
                hVar.a((Object) bundle);
            }
        }
    }

    private final Bundle b(Bundle bundle) throws IOException {
        Bundle c = c(bundle);
        if (c == null || !c.containsKey("google.messenger")) {
            return c;
        }
        c = c(bundle);
        return (c == null || !c.containsKey("google.messenger")) ? c : null;
    }

    private final Bundle c(Bundle bundle) throws IOException {
        String a = a();
        h hVar = new h();
        synchronized (this.c) {
            this.c.put(a, hVar);
        }
        if (this.e.a() == 0) {
            throw new IOException("MISSING_INSTANCEID_SERVICE");
        }
        Bundle bundle2;
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.e.a() == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        a(this.d, intent);
        intent.putExtra("kid", new StringBuilder(String.valueOf(a).length() + 5).append("|ID|").append(a).append("|").toString());
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(intent.getExtras());
            new StringBuilder(String.valueOf(valueOf).length() + 8).append("Sending ").append(valueOf);
        }
        intent.putExtra("google.messenger", this.f);
        if (!(this.g == null && this.h == null)) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                if (this.g != null) {
                    this.g.send(obtain);
                } else {
                    this.h.a(obtain);
                }
            } catch (RemoteException e) {
            }
            bundle2 = (Bundle) j.a(hVar.a(), 30000, TimeUnit.MILLISECONDS);
            synchronized (this.c) {
                this.c.remove(a);
            }
            return bundle2;
        }
        if (this.e.a() == 2) {
            this.d.sendBroadcast(intent);
        } else {
            this.d.startService(intent);
        }
        try {
            bundle2 = (Bundle) j.a(hVar.a(), 30000, TimeUnit.MILLISECONDS);
            synchronized (this.c) {
                this.c.remove(a);
            }
            return bundle2;
        } catch (InterruptedException e2) {
            throw new IOException("TIMEOUT");
        } catch (TimeoutException e3) {
            throw new IOException("TIMEOUT");
        } catch (Throwable e4) {
            throw new IOException(e4);
        } catch (Throwable th) {
            synchronized (this.c) {
                this.c.remove(a);
            }
        }
    }

    final Bundle a(Bundle bundle) throws IOException {
        Exception e;
        if (this.e.d() < 12000000) {
            return b(bundle);
        }
        try {
            return (Bundle) j.a(ai.a(this.d).b(bundle));
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(e);
            new StringBuilder(String.valueOf(valueOf).length() + 22).append("Error making request: ").append(valueOf);
        }
        return ((e.getCause() instanceof d) && ((d) e.getCause()).a() == 4) ? b(bundle) : null;
    }
}
