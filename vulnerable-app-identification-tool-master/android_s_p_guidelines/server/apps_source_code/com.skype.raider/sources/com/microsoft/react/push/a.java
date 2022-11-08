package com.microsoft.react.push;

import android.graphics.Color;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.am;

public class a implements d {
    private static final String b = a.class.getSimpleName();
    private boolean c;
    private int d;
    private String e;
    private String f;

    public a(am details) {
        if (!details.hasKey("icon")) {
            return;
        }
        if (details.getType("icon") == ReadableType.String) {
            this.e = details.getString("icon");
        } else if (details.getType("icon") == ReadableType.Map) {
            String string;
            String colorString;
            am map = details.getMap("icon");
            if (map.hasKey("initials")) {
                string = map.getString("initials");
            } else {
                string = null;
            }
            this.f = string;
            if (map.hasKey("color")) {
                colorString = map.getString("color");
            } else {
                colorString = null;
            }
            if (colorString != null) {
                try {
                    this.d = Color.parseColor(colorString);
                } catch (Throwable e) {
                    FLog.e(b, "invalid color for icon", e);
                }
            }
            boolean z = map.hasKey("isGroupConversation") && map.getBoolean("isGroupConversation");
            this.c = z;
        }
    }

    public final boolean a() {
        return this.c;
    }

    public final int b() {
        return this.d;
    }

    public final String c() {
        return this.e;
    }

    public final String d() {
        return this.f;
    }
}
