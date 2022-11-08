package com.skype4life.syncadapter;

import android.content.Context;
import com.skype.raider.R;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class k {
    private final Context a;
    private final Set<String> b;

    public k(Context context) {
        this.a = context;
        String[] strArr = new String[]{b(), e(), g(), d()};
        Object hashSet = new HashSet(4);
        Collections.addAll(hashSet, strArr);
        this.b = Collections.unmodifiableSet(hashSet);
    }

    public final Set<String> a() {
        return this.b;
    }

    public final String b() {
        return this.a.getString(R.string.contacts_audio_mimetype);
    }

    public final String c() {
        return this.a.getString(R.string.contacts_audio_action);
    }

    public final String d() {
        return this.a.getString(R.string.contacts_phone_mimetype);
    }

    public final String a(String phoneNumber) {
        return this.a.getString(R.string.contacts_phone_action, new Object[]{phoneNumber});
    }

    public final String e() {
        return this.a.getString(R.string.contacts_video_mimetype);
    }

    public final String f() {
        return this.a.getString(R.string.contacts_video_action);
    }

    public final String g() {
        return this.a.getString(R.string.contacts_message_mimetype);
    }

    public final String h() {
        return this.a.getString(R.string.contacts_message_action);
    }

    public final String i() {
        return this.a.getString(R.string.sync_adapter_authority);
    }

    public final String j() {
        return this.a.getString(R.string.sync_adapter_account_type);
    }

    public final String k() {
        return this.a.getString(R.string.app_name);
    }
}
