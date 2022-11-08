package android.support.v4.app;

import android.app.RemoteInput;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.util.HashSet;
import java.util.Set;

public final class ac extends android.support.v4.app.ae.a {
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static final android.support.v4.app.ae.a.a a = new android.support.v4.app.ae.a.a() {
    };
    private static final b h;
    private final String b;
    private final CharSequence c;
    private final CharSequence[] d;
    private final boolean e;
    private final Bundle f;
    private final Set<String> g;

    public static final class a {
        private final String a;
        private CharSequence b;
        private CharSequence[] c;
        private boolean d = true;
        private Bundle e = new Bundle();
        private final Set<String> f = new HashSet();

        public a(String resultKey) {
            this.a = resultKey;
        }

        public final a a(CharSequence label) {
            this.b = label;
            return this;
        }

        public final ac a() {
            return new ac(this.a, this.b, this.c, this.d, this.e, this.f);
        }
    }

    interface b {
        Bundle a(Intent intent);
    }

    @RequiresApi(20)
    static class c implements b {
        c() {
        }

        public final Bundle a(Intent intent) {
            return RemoteInput.getResultsFromIntent(intent);
        }
    }

    static class d implements b {
        d() {
        }

        public final Bundle a(Intent intent) {
            return null;
        }
    }

    @RequiresApi(16)
    static class e implements b {
        e() {
        }

        public final Bundle a(Intent intent) {
            Intent intent2;
            ClipData clipData = intent.getClipData();
            if (clipData == null) {
                intent2 = null;
            } else {
                ClipDescription description = clipData.getDescription();
                if (!description.hasMimeType("text/vnd.android.intent")) {
                    intent2 = null;
                } else if (description.getLabel().equals("android.remoteinput.results")) {
                    intent2 = clipData.getItemAt(0).getIntent();
                } else {
                    intent2 = null;
                }
            }
            if (intent2 == null) {
                return null;
            }
            return (Bundle) intent2.getExtras().getParcelable("android.remoteinput.resultsData");
        }
    }

    ac(String resultKey, CharSequence label, CharSequence[] choices, boolean allowFreeFormTextInput, Bundle extras, Set<String> allowedDataTypes) {
        this.b = resultKey;
        this.c = label;
        this.d = choices;
        this.e = allowFreeFormTextInput;
        this.f = extras;
        this.g = allowedDataTypes;
    }

    public final String a() {
        return this.b;
    }

    public final CharSequence b() {
        return this.c;
    }

    public final CharSequence[] c() {
        return this.d;
    }

    public final Set<String> d() {
        return this.g;
    }

    public final boolean e() {
        return this.e;
    }

    public final Bundle f() {
        return this.f;
    }

    public static Bundle a(Intent intent) {
        return h.a(intent);
    }

    static {
        if (VERSION.SDK_INT >= 20) {
            h = new c();
        } else if (VERSION.SDK_INT >= 16) {
            h = new e();
        } else {
            h = new d();
        }
    }
}
