package androidx.media;

import android.media.session.MediaSessionManager.RemoteUserInfo;
import android.os.Build.VERSION;

public final class m {
    n a;

    public m(String str, int i, int i2) {
        if (VERSION.SDK_INT >= 28) {
            this.a = new q(str, i, i2);
        } else {
            this.a = new s(str, i, i2);
        }
    }

    public m(RemoteUserInfo remoteUserInfo) {
        this.a = new q(remoteUserInfo);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof m) {
            return this.a.equals(((m) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
