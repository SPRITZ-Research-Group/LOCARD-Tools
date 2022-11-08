package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.n.k;
import com.facebook.ads.internal.q.a.u;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ah extends j {
    private final ai c;
    private k d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private View j;
    @Nullable
    private g k;
    private List<View> l;
    private a m = a.ALL;

    public enum a {
        ALL("all"),
        NONE("none"),
        MANUAL("manual");
        
        private final String d;

        private a(String str) {
            this.d = str;
        }

        public final String toString() {
            return this.d;
        }
    }

    public ah(Context context, k kVar, com.facebook.ads.internal.s.a aVar, ai aiVar) {
        super(context, kVar, aVar);
        this.c = aiVar;
    }

    private String b(View view) {
        try {
            return c(view).toString();
        } catch (JSONException e) {
            return "Json exception";
        }
    }

    private JSONObject c(View view) {
        boolean z = true;
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("id", Integer.valueOf(view.getId()));
        jSONObject.putOpt("class", view.getClass());
        jSONObject.putOpt("origin", String.format("{x:%d, y:%d}", new Object[]{Integer.valueOf(view.getTop()), Integer.valueOf(view.getLeft())}));
        jSONObject.putOpt("size", String.format("{h:%d, w:%d}", new Object[]{Integer.valueOf(view.getHeight()), Integer.valueOf(view.getWidth())}));
        if (this.l == null || !this.l.contains(view)) {
            z = false;
        }
        jSONObject.putOpt("clickable", Boolean.valueOf(z));
        Object obj = "unknown";
        if (view instanceof Button) {
            obj = "button";
        } else if (view instanceof TextView) {
            obj = "text";
        } else if (view instanceof ImageView) {
            obj = "image";
        } else if (view instanceof MediaView) {
            obj = "mediaview";
        } else if (view instanceof ViewGroup) {
            obj = "viewgroup";
        }
        jSONObject.putOpt("type", obj);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            JSONArray jSONArray = new JSONArray();
            while (i < viewGroup.getChildCount()) {
                jSONArray.put(c(viewGroup.getChildAt(i)));
                i++;
            }
            jSONObject.putOpt("list", jSONArray);
        }
        return jSONObject;
    }

    private String d(View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return "";
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            createBitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            view.draw(new Canvas(createBitmap));
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, this.c.i(), byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception e) {
            return "";
        }
    }

    public final void a(View view) {
        this.j = view;
    }

    public final void a(a aVar) {
        this.m = aVar;
    }

    public final void a(g gVar) {
        this.k = gVar;
    }

    public final void a(k kVar) {
        this.d = kVar;
    }

    public final void a(List<View> list) {
        this.l = list;
    }

    protected final void a(Map<String, String> map) {
        if (this.c != null) {
            if (this.d != null) {
                map.put("nti", String.valueOf(this.d.a()));
            }
            if (this.e) {
                map.put("nhs", Boolean.TRUE.toString());
            }
            if (this.f) {
                map.put("nmv", Boolean.TRUE.toString());
            }
            if (this.g) {
                map.put("nmvap", Boolean.TRUE.toString());
            }
            if (this.j != null && this.c.g()) {
                map.put("view", b(this.j));
            }
            if (this.j != null && this.c.f()) {
                map.put("snapshot", d(this.j));
            }
            if (this.h) {
                map.put("niv", Boolean.TRUE.toString());
            }
            if (this.m != null) {
                map.put("precache_media", this.m.toString());
            }
            if (this.i) {
                map.put("ucvr", Boolean.TRUE.toString());
            }
            if (this.k != null) {
                map.put("namw", String.valueOf((int) (((float) this.k.getWidth()) / u.b)));
                map.put("namh", String.valueOf((int) (((float) this.k.getHeight()) / u.b)));
            }
            this.c.a((Map) map);
        }
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public final void b(boolean z) {
        this.f = z;
    }

    public final void c(boolean z) {
        this.g = z;
    }

    public final void d(boolean z) {
        this.h = z;
    }

    public final void e(boolean z) {
        this.i = z;
    }
}
