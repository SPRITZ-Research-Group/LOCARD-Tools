package com.facebook.ads.internal.r;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.q.d.b;
import org.json.JSONObject;

public final class j {
    private final a a;
    @Nullable
    private final Long b;
    @Nullable
    private final String c;
    @Nullable
    private final String d;

    private enum a {
        ID,
        CREATIVE,
        NONE
    }

    public j(Context context, String str, h hVar) {
        if (TextUtils.isEmpty(null)) {
            this.a = a.NONE;
            this.b = null;
            this.d = null;
            this.c = null;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(null);
            switch (a.valueOf(jSONObject.getString("type").toUpperCase())) {
                case ID:
                    this.a = a.ID;
                    this.b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.d = jSONObject.getString("device_id");
                    this.c = null;
                    break;
                case CREATIVE:
                    this.a = a.CREATIVE;
                    this.b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.d = jSONObject.getString("device_id");
                    this.c = new JSONObject(jSONObject.getString("payload")).toString();
                    break;
                default:
                    throw new d(a.BID_PAYLOAD_ERROR, "Unsupported BidPayload type " + jSONObject.getString("type"));
            }
            if (!jSONObject.getString("sdk_version").equals("4.99.1")) {
                throw new d(a.BID_IMPRESSION_MISMATCH, String.format("Bid %d for SDK version %s being used on SDK version %s", new Object[]{this.b, jSONObject.getString("sdk_version"), "4.99.1"}));
            } else if (!jSONObject.getString("resolved_placement_id").equals(str)) {
                throw new d(a.BID_IMPRESSION_MISMATCH, String.format("Bid %d for placement %s being used on placement %s", new Object[]{this.b, jSONObject.getString("resolved_placement_id"), str}));
            } else if (jSONObject.getInt("template") != hVar.a()) {
                throw new d(a.BID_IMPRESSION_MISMATCH, String.format("Bid %d for template %s being used on template %s", new Object[]{this.b, Integer.valueOf(jSONObject.getInt("template")), hVar}));
            }
        } catch (Throwable e) {
            com.facebook.ads.internal.q.d.a.a(context, "api", b.d, e);
            throw new d(a.BID_PAYLOAD_ERROR, "Invalid BidPayload", e);
        }
    }

    public final void a(String str) {
        if (!this.d.equals(str)) {
            throw new d(a.BID_IMPRESSION_MISMATCH, String.format("Bid %d for IDFA %s being used on IDFA %s", new Object[]{this.b, this.d, str}));
        }
    }

    public final boolean a() {
        return this.a == a.CREATIVE;
    }

    @Nullable
    public final String b() {
        return this.c;
    }

    public final boolean c() {
        return this.a != a.NONE;
    }

    @Nullable
    public final String d() {
        return this.b == null ? null : this.b.toString();
    }
}
