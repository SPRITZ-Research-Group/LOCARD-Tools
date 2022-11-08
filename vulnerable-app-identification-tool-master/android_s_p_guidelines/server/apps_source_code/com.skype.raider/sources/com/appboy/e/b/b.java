package com.appboy.e.b;

import a.a.bg;
import com.appboy.e.e;
import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONObject;

public final class b implements e<JSONObject> {
    private static final String a = c.a(b.class);
    private final String b;
    private final String c;
    private final boolean d;
    private final bg e;
    private final String f;

    public final /* synthetic */ Object h() {
        return a();
    }

    public b(String message, String replyToEmail, boolean isReportingABug, bg device, String userId) {
        if (i.c(message)) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
        this.b = message;
        this.c = replyToEmail;
        this.d = isReportingABug;
        this.e = device;
        this.f = userId;
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", i.a(this.b));
            jSONObject.put("reply_to", this.c);
            jSONObject.put("is_bug", this.d);
            if (this.e != null) {
                jSONObject.put("device", this.e.a());
            }
            if (!i.b(this.f)) {
                jSONObject.put("user_id", this.f);
            }
        } catch (Throwable e) {
            c.d(a, "Caught exception creating feedback Json.", e);
        }
        return jSONObject;
    }
}
