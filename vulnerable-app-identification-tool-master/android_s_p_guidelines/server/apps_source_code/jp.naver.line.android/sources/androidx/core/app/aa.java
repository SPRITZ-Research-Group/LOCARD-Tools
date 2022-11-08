package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioAttributes.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.RemoteViews;
import defpackage.dw;
import java.util.ArrayList;

public final class aa {
    String A;
    Bundle B;
    int C;
    int D;
    Notification E;
    RemoteViews F;
    RemoteViews G;
    RemoteViews H;
    String I;
    int J;
    String K;
    long L;
    int M;
    Notification N;
    @Deprecated
    public ArrayList<String> O;
    public Context a;
    public ArrayList<x> b;
    ArrayList<x> c;
    CharSequence d;
    CharSequence e;
    PendingIntent f;
    PendingIntent g;
    RemoteViews h;
    Bitmap i;
    CharSequence j;
    int k;
    int l;
    boolean m;
    boolean n;
    ac o;
    CharSequence p;
    CharSequence[] q;
    int r;
    int s;
    boolean t;
    String u;
    boolean v;
    String w;
    boolean x;
    boolean y;
    boolean z;

    public aa(Context context, String str) {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.m = true;
        this.x = false;
        this.C = 0;
        this.D = 0;
        this.J = 0;
        this.M = 0;
        this.N = new Notification();
        this.a = context;
        this.I = str;
        this.N.when = System.currentTimeMillis();
        this.N.audioStreamType = -1;
        this.l = 0;
        this.O = new ArrayList();
    }

    @Deprecated
    public aa(Context context) {
        this(context, null);
    }

    public final aa a(long j) {
        this.N.when = j;
        return this;
    }

    public final aa a(int i) {
        this.N.icon = i;
        return this;
    }

    public final aa a(CharSequence charSequence) {
        this.d = e(charSequence);
        return this;
    }

    public final aa b(CharSequence charSequence) {
        this.e = e(charSequence);
        return this;
    }

    public final aa c(CharSequence charSequence) {
        this.p = e(charSequence);
        return this;
    }

    public final aa a(PendingIntent pendingIntent) {
        this.f = pendingIntent;
        return this;
    }

    public final aa b(PendingIntent pendingIntent) {
        this.N.deleteIntent = pendingIntent;
        return this;
    }

    public final aa d(CharSequence charSequence) {
        this.N.tickerText = e(charSequence);
        return this;
    }

    public final aa a(Uri uri) {
        this.N.sound = uri;
        this.N.audioStreamType = -1;
        if (VERSION.SDK_INT >= 21) {
            this.N.audioAttributes = new Builder().setContentType(4).setUsage(5).build();
        }
        return this;
    }

    public final aa a(long[] jArr) {
        this.N.vibrate = jArr;
        return this;
    }

    public final aa a() {
        this.N.ledARGB = -16711936;
        this.N.ledOnMS = 600;
        this.N.ledOffMS = 5000;
        int i = (this.N.ledOnMS == 0 || this.N.ledOffMS == 0) ? 0 : 1;
        this.N.flags = i | (this.N.flags & -2);
        return this;
    }

    public final aa b() {
        this.x = true;
        return this;
    }

    public final aa a(String str) {
        this.A = str;
        return this;
    }

    public final aa b(int i) {
        this.l = i;
        return this;
    }

    public final aa b(String str) {
        this.u = str;
        return this;
    }

    public final aa b(boolean z) {
        this.v = z;
        return this;
    }

    public final aa a(Bundle bundle) {
        if (bundle != null) {
            if (this.B == null) {
                this.B = new Bundle(bundle);
            } else {
                this.B.putAll(bundle);
            }
        }
        return this;
    }

    public final Bundle c() {
        if (this.B == null) {
            this.B = new Bundle();
        }
        return this.B;
    }

    public final aa a(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this.b.add(new x(i, charSequence, pendingIntent));
        return this;
    }

    public final aa a(ac acVar) {
        if (this.o != acVar) {
            this.o = acVar;
            if (this.o != null) {
                this.o.a(this);
            }
        }
        return this;
    }

    public final aa c(int i) {
        this.C = i;
        return this;
    }

    public final aa a(Notification notification) {
        this.E = notification;
        return this;
    }

    public final aa c(String str) {
        this.I = str;
        return this;
    }

    public final aa d() {
        this.M = 2;
        return this;
    }

    public final Notification e() {
        return new ae(this).b();
    }

    protected static CharSequence e(CharSequence charSequence) {
        if (charSequence == null) {
            return charSequence;
        }
        if (charSequence.length() > 5120) {
            charSequence = charSequence.subSequence(0, 5120);
        }
        return charSequence;
    }

    public final aa a(Bitmap bitmap) {
        if (bitmap != null && VERSION.SDK_INT < 27) {
            Resources resources = this.a.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(dw.compat_notification_large_icon_max_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(dw.compat_notification_large_icon_max_height);
            if (bitmap.getWidth() > dimensionPixelSize || bitmap.getHeight() > dimensionPixelSize2) {
                double d = (double) dimensionPixelSize;
                double max = (double) Math.max(1, bitmap.getWidth());
                Double.isNaN(d);
                Double.isNaN(max);
                d /= max;
                max = (double) dimensionPixelSize2;
                double max2 = (double) Math.max(1, bitmap.getHeight());
                Double.isNaN(max);
                Double.isNaN(max2);
                double min = Math.min(d, max / max2);
                double width = (double) bitmap.getWidth();
                Double.isNaN(width);
                int ceil = (int) Math.ceil(width * min);
                max = (double) bitmap.getHeight();
                Double.isNaN(max);
                bitmap = Bitmap.createScaledBitmap(bitmap, ceil, (int) Math.ceil(max * min), true);
            }
        }
        this.i = bitmap;
        return this;
    }

    public final aa a(boolean z) {
        Notification notification;
        if (z) {
            notification = this.N;
            notification.flags |= 16;
        } else {
            notification = this.N;
            notification.flags &= -17;
        }
        return this;
    }
}
