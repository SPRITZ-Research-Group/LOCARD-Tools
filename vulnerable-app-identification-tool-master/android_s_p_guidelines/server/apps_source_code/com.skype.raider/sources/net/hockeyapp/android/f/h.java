package net.hockeyapp.android.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class h {
    private SharedPreferences a;
    private SharedPreferences b;

    private static class a {
        static final h a = new h();
    }

    /* synthetic */ h(byte b) {
        this();
    }

    private h() {
    }

    public static h a() {
        return a.a;
    }

    public final void a(Context context, String token) {
        if (context != null) {
            this.a = context.getSharedPreferences("net.hockeyapp.android.prefs_feedback_token", 0);
            if (this.a != null) {
                Editor editor = this.a.edit();
                editor.putString("net.hockeyapp.android.prefs_key_feedback_token", token);
                editor.apply();
            }
        }
    }

    public final String a(Context context) {
        if (context == null) {
            return null;
        }
        this.a = context.getSharedPreferences("net.hockeyapp.android.prefs_feedback_token", 0);
        if (this.a != null) {
            return this.a.getString("net.hockeyapp.android.prefs_key_feedback_token", null);
        }
        return null;
    }

    public final void a(Context context, String name, String email, String subject) {
        if (context != null) {
            this.b = context.getSharedPreferences("net.hockeyapp.android.prefs_name_email", 0);
            if (this.b != null) {
                Editor editor = this.b.edit();
                if (name == null || email == null || subject == null) {
                    editor.putString("net.hockeyapp.android.prefs_key_name_email", null);
                } else {
                    editor.putString("net.hockeyapp.android.prefs_key_name_email", String.format("%s|%s|%s", new Object[]{name, email, subject}));
                }
                editor.apply();
            }
        }
    }

    public final String b(Context context) {
        if (context == null) {
            return null;
        }
        this.b = context.getSharedPreferences("net.hockeyapp.android.prefs_name_email", 0);
        if (this.b != null) {
            return this.b.getString("net.hockeyapp.android.prefs_key_name_email", null);
        }
        return null;
    }
}
