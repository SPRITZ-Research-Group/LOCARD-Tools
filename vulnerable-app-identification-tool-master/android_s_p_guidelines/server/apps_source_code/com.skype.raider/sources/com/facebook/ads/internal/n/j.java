package com.facebook.ads.internal.n;

import android.graphics.Color;
import android.graphics.Typeface;
import com.facebook.ads.internal.t.a;
import org.json.JSONObject;

public final class j {
    private Typeface a = Typeface.DEFAULT;
    private int b = -1;
    private int c = -16777216;
    private int d = -11643291;
    private int e = 0;
    private int f = -12420889;
    private int g = -12420889;
    private boolean h = a.d();
    private boolean i = a.e();

    public j(JSONObject jSONObject) {
        int parseColor = jSONObject.getBoolean("background_transparent") ? 0 : Color.parseColor(jSONObject.getString("background_color"));
        int parseColor2 = Color.parseColor(jSONObject.getString("title_text_color"));
        int parseColor3 = Color.parseColor(jSONObject.getString("description_text_color"));
        int parseColor4 = jSONObject.getBoolean("button_transparent") ? 0 : Color.parseColor(jSONObject.getString("button_color"));
        int parseColor5 = jSONObject.getBoolean("button_border_transparent") ? 0 : Color.parseColor(jSONObject.getString("button_border_color"));
        int parseColor6 = Color.parseColor(jSONObject.getString("button_text_color"));
        Typeface create = Typeface.create(jSONObject.getString("android_typeface"), 0);
        this.b = parseColor;
        this.c = parseColor2;
        this.d = parseColor3;
        this.e = parseColor4;
        this.g = parseColor5;
        this.f = parseColor6;
        this.a = create;
    }
}
