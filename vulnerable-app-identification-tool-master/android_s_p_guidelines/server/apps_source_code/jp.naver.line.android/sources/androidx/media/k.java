package androidx.media;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.h;
import androidx.media.h.AnonymousClass1;
import androidx.media.h.AnonymousClass2;
import androidx.media.h.AnonymousClass3;
import androidx.media.h.AnonymousClass4;
import androidx.media.h.AnonymousClass5;
import androidx.media.h.AnonymousClass6;
import androidx.media.h.AnonymousClass7;
import androidx.media.h.AnonymousClass8;
import androidx.media.h.AnonymousClass9;

final class k extends Handler {
    final /* synthetic */ MediaBrowserServiceCompat a;
    private final h b = new h(this.a);

    k(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        this.a = mediaBrowserServiceCompat;
    }

    public final void handleMessage(Message message) {
        Bundle data = message.getData();
        Bundle bundle;
        h hVar;
        String string;
        i jVar;
        h hVar2;
        h hVar3;
        Bundle bundle2;
        Object string2;
        ResultReceiver resultReceiver;
        switch (message.what) {
            case 1:
                bundle = data.getBundle("data_root_hints");
                MediaSessionCompat.ensureClassLoader(bundle);
                hVar = this.b;
                string = data.getString("data_package_name");
                int i = data.getInt("data_calling_pid");
                int i2 = data.getInt("data_calling_uid");
                jVar = new j(message.replyTo);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = hVar.a;
                Object obj = null;
                if (string != null) {
                    String[] packagesForUid = mediaBrowserServiceCompat.getPackageManager().getPackagesForUid(i2);
                    int length = packagesForUid.length;
                    int i3 = 0;
                    while (i3 < length) {
                        if (packagesForUid[i3].equals(string)) {
                            obj = 1;
                        } else {
                            i3++;
                        }
                    }
                }
                if (obj != null) {
                    hVar.a.d.a(new AnonymousClass1(hVar, jVar, string, i, i2, bundle));
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder("Package/uid mismatch: uid=");
                stringBuilder.append(i2);
                stringBuilder.append(" package=");
                stringBuilder.append(string);
                throw new IllegalArgumentException(stringBuilder.toString());
            case 2:
                hVar2 = this.b;
                hVar2.a.d.a(new AnonymousClass2(hVar2, new j(message.replyTo)));
                return;
            case 3:
                Bundle bundle3 = data.getBundle("data_options");
                MediaSessionCompat.ensureClassLoader(bundle3);
                hVar = this.b;
                string = data.getString("data_media_item_id");
                IBinder a = h.a(data, "data_callback_token");
                hVar.a.d.a(new AnonymousClass3(hVar, new j(message.replyTo), string, a, bundle3));
                return;
            case 4:
                hVar3 = this.b;
                String string3 = data.getString("data_media_item_id");
                IBinder a2 = h.a(data, "data_callback_token");
                hVar3.a.d.a(new AnonymousClass4(hVar3, new j(message.replyTo), string3, a2));
                return;
            case 5:
                hVar3 = this.b;
                Object string4 = data.getString("data_media_item_id");
                ResultReceiver resultReceiver2 = (ResultReceiver) data.getParcelable("data_result_receiver");
                i jVar2 = new j(message.replyTo);
                if (!TextUtils.isEmpty(string4) && resultReceiver2 != null) {
                    hVar3.a.d.a(new AnonymousClass5(hVar3, jVar2, string4, resultReceiver2));
                    return;
                }
                return;
            case 6:
                bundle = data.getBundle("data_root_hints");
                MediaSessionCompat.ensureClassLoader(bundle);
                hVar = this.b;
                hVar.a.d.a(new AnonymousClass6(hVar, new j(message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle));
                return;
            case 7:
                hVar2 = this.b;
                hVar2.a.d.a(new AnonymousClass7(hVar2, new j(message.replyTo)));
                return;
            case 8:
                bundle2 = data.getBundle("data_search_extras");
                MediaSessionCompat.ensureClassLoader(bundle2);
                hVar = this.b;
                string2 = data.getString("data_search_query");
                resultReceiver = (ResultReceiver) data.getParcelable("data_result_receiver");
                jVar = new j(message.replyTo);
                if (!TextUtils.isEmpty(string2) && resultReceiver != null) {
                    hVar.a.d.a(new AnonymousClass8(hVar, jVar, string2, bundle2, resultReceiver));
                    return;
                }
                return;
            case 9:
                bundle2 = data.getBundle("data_custom_action_extras");
                MediaSessionCompat.ensureClassLoader(bundle2);
                hVar = this.b;
                string2 = data.getString("data_custom_action");
                resultReceiver = (ResultReceiver) data.getParcelable("data_result_receiver");
                jVar = new j(message.replyTo);
                if (!TextUtils.isEmpty(string2) && resultReceiver != null) {
                    hVar.a.d.a(new AnonymousClass9(hVar, jVar, string2, bundle2, resultReceiver));
                    return;
                }
                return;
            default:
                StringBuilder stringBuilder2 = new StringBuilder("Unhandled message: ");
                stringBuilder2.append(message);
                stringBuilder2.append("\n  Service version: 2\n  Client version: ");
                stringBuilder2.append(message.arg1);
                Log.w("MBServiceCompat", stringBuilder2.toString());
                return;
        }
    }

    public final boolean sendMessageAtTime(Message message, long j) {
        Bundle data = message.getData();
        data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
        data.putInt("data_calling_uid", Binder.getCallingUid());
        data.putInt("data_calling_pid", Binder.getCallingPid());
        return super.sendMessageAtTime(message, j);
    }

    private void a(Runnable runnable) {
        if (Thread.currentThread() == getLooper().getThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }
}
