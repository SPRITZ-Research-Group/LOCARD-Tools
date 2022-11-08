package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.h;
import com.google.android.gms.maps.model.j;
import java.net.MalformedURLException;
import java.net.URL;

public class AirMapUrlTile extends AirMapFeature {
    private TileOverlayOptions a;
    private h b;
    private a c;
    private String d;
    private float e;

    class a extends j {
        final /* synthetic */ AirMapUrlTile a;
        private String c;

        public a(AirMapUrlTile this$0, String urlTemplate) {
            this.a = this$0;
            this.c = urlTemplate;
        }

        public final synchronized URL a(int x, int y, int zoom) {
            try {
            } catch (MalformedURLException e) {
                throw new AssertionError(e);
            }
            return new URL(this.c.replace("{x}", Integer.toString(x)).replace("{y}", Integer.toString(y)).replace("{z}", Integer.toString(zoom)));
        }

        public final void a(String urlTemplate) {
            this.c = urlTemplate;
        }
    }

    public AirMapUrlTile(Context context) {
        super(context);
    }

    public void setUrlTemplate(String urlTemplate) {
        this.d = urlTemplate;
        if (this.c != null) {
            this.c.a(urlTemplate);
        }
        if (this.b != null) {
            this.b.b();
        }
    }

    public void setZIndex(float zIndex) {
        this.e = zIndex;
        if (this.b != null) {
            this.b.a(zIndex);
        }
    }

    public final Object a() {
        return this.b;
    }

    public final void b() {
        this.b.a();
    }

    public final void a(c map) {
        if (this.a == null) {
            TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
            tileOverlayOptions.a(this.e);
            this.c = new a(this, this.d);
            tileOverlayOptions.a(this.c);
            this.a = tileOverlayOptions;
        }
        this.b = map.a(this.a);
    }
}
