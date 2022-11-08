package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import java.util.ArrayList;

public final class aq {
    private Activity a;
    private Intent b = new Intent().setAction("android.intent.action.SEND");
    private CharSequence c;
    private ArrayList<String> d;
    private ArrayList<String> e;
    private ArrayList<String> f;
    private ArrayList<Uri> g;

    public static aq a(Activity activity) {
        return new aq(activity);
    }

    private aq(Activity activity) {
        this.a = activity;
        this.b.putExtra("androidx.core.app.EXTRA_CALLING_PACKAGE", activity.getPackageName());
        this.b.putExtra("androidx.core.app.EXTRA_CALLING_ACTIVITY", activity.getComponentName());
        this.b.addFlags(524288);
    }

    private void a(String str, ArrayList<String> arrayList) {
        Object stringArrayExtra = this.b.getStringArrayExtra(str);
        int length = stringArrayExtra != null ? stringArrayExtra.length : 0;
        Object obj = new String[(arrayList.size() + length)];
        arrayList.toArray(obj);
        if (stringArrayExtra != null) {
            System.arraycopy(stringArrayExtra, 0, obj, arrayList.size(), length);
        }
        this.b.putExtra(str, obj);
    }

    public final void a() {
        Activity activity = this.a;
        if (this.d != null) {
            a("android.intent.extra.EMAIL", this.d);
            this.d = null;
        }
        if (this.e != null) {
            a("android.intent.extra.CC", this.e);
            this.e = null;
        }
        if (this.f != null) {
            a("android.intent.extra.BCC", this.f);
            this.f = null;
        }
        Object obj = 1;
        if (this.g == null || this.g.size() <= 1) {
            obj = null;
        }
        boolean equals = this.b.getAction().equals("android.intent.action.SEND_MULTIPLE");
        if (obj == null && equals) {
            this.b.setAction("android.intent.action.SEND");
            if (this.g == null || this.g.isEmpty()) {
                this.b.removeExtra("android.intent.extra.STREAM");
            } else {
                this.b.putExtra("android.intent.extra.STREAM", (Parcelable) this.g.get(0));
            }
            this.g = null;
        }
        if (!(obj == null || equals)) {
            this.b.setAction("android.intent.action.SEND_MULTIPLE");
            if (this.g == null || this.g.isEmpty()) {
                this.b.removeExtra("android.intent.extra.STREAM");
            } else {
                this.b.putParcelableArrayListExtra("android.intent.extra.STREAM", this.g);
            }
        }
        activity.startActivity(Intent.createChooser(this.b, this.c));
    }

    public final aq a(CharSequence charSequence) {
        this.c = charSequence;
        return this;
    }

    public final aq a(String str) {
        this.b.setType(str);
        return this;
    }

    public final aq b(CharSequence charSequence) {
        this.b.putExtra("android.intent.extra.TEXT", charSequence);
        return this;
    }
}
