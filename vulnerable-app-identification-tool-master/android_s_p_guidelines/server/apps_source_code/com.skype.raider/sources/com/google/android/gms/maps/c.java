package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.internal.c.s;
import com.google.android.gms.maps.a.ao;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class c {
    private final com.google.android.gms.maps.a.b a;
    private j b;

    public interface l {
        void a(Bitmap bitmap);
    }

    public interface h {
        boolean a(com.google.android.gms.maps.model.d dVar);
    }

    public interface j {
        void a(com.google.android.gms.maps.model.e eVar);
    }

    public interface k {
        void a(com.google.android.gms.maps.model.f fVar);
    }

    public interface d {
        void a(com.google.android.gms.maps.model.d dVar);
    }

    public interface e {
        void a(LatLng latLng);
    }

    public interface g {
        void a(LatLng latLng);
    }

    @Deprecated
    public interface c {
        void a(CameraPosition cameraPosition);
    }

    public interface f {
        void a();
    }

    public interface a {
        void a();

        void b();
    }

    public interface b {
        View a(com.google.android.gms.maps.model.d dVar);

        View b(com.google.android.gms.maps.model.d dVar);
    }

    public interface i {
        void c(com.google.android.gms.maps.model.d dVar);

        void d(com.google.android.gms.maps.model.d dVar);

        void e(com.google.android.gms.maps.model.d dVar);
    }

    private static final class m extends ao {
        private final a a;

        m(a aVar) {
            this.a = aVar;
        }

        public final void a() {
            this.a.a();
        }

        public final void b() {
            this.a.b();
        }
    }

    public c(com.google.android.gms.maps.a.b bVar) {
        this.a = (com.google.android.gms.maps.a.b) ab.a((Object) bVar);
    }

    public final CameraPosition a() {
        try {
            return this.a.a();
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final com.google.android.gms.maps.model.c a(CircleOptions circleOptions) {
        try {
            return new com.google.android.gms.maps.model.c(this.a.a(circleOptions));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final com.google.android.gms.maps.model.d a(MarkerOptions markerOptions) {
        try {
            s a = this.a.a(markerOptions);
            return a != null ? new com.google.android.gms.maps.model.d(a) : null;
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final com.google.android.gms.maps.model.e a(PolygonOptions polygonOptions) {
        try {
            return new com.google.android.gms.maps.model.e(this.a.a(polygonOptions));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final com.google.android.gms.maps.model.f a(PolylineOptions polylineOptions) {
        try {
            return new com.google.android.gms.maps.model.f(this.a.a(polylineOptions));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final com.google.android.gms.maps.model.h a(TileOverlayOptions tileOverlayOptions) {
        try {
            com.google.android.gms.internal.c.d a = this.a.a(tileOverlayOptions);
            return a != null ? new com.google.android.gms.maps.model.h(a) : null;
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(int i) {
        try {
            this.a.a(i);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(int i, int i2, int i3, int i4) {
        try {
            this.a.a(i, i2, i3, i4);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(a aVar) {
        try {
            this.a.a(aVar.a());
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(a aVar, int i, a aVar2) {
        try {
            this.a.a(aVar.a(), i, new m(aVar2));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(b bVar) {
        if (bVar == null) {
            try {
                this.a.a(null);
                return;
            } catch (RemoteException e) {
                throw new com.google.android.gms.maps.model.g(e);
            }
        }
        this.a.a(new r(bVar));
    }

    @Deprecated
    public final void a(@Nullable c cVar) {
        try {
            this.a.a(new w(cVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(@Nullable d dVar) {
        try {
            this.a.a(new q(dVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(@Nullable e eVar) {
        try {
            this.a.a(new x(eVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(f fVar) {
        try {
            this.a.a(new s(fVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(@Nullable g gVar) {
        try {
            this.a.a(new y(gVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(@Nullable h hVar) {
        try {
            this.a.a(new o(hVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(@Nullable i iVar) {
        if (iVar == null) {
            try {
                this.a.a(null);
                return;
            } catch (RemoteException e) {
                throw new com.google.android.gms.maps.model.g(e);
            }
        }
        this.a.a(new p(iVar));
    }

    public final void a(j jVar) {
        try {
            this.a.a(new t(jVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(k kVar) {
        try {
            this.a.a(new u(kVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(boolean z) {
        try {
            this.a.a(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final boolean a(@Nullable MapStyleOptions mapStyleOptions) {
        try {
            return this.a.a(mapStyleOptions);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final j b() {
        try {
            if (this.b == null) {
                this.b = new j(this.a.b());
            }
            return this.b;
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void b(a aVar) {
        try {
            this.a.b(aVar.a());
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final boolean b(boolean z) {
        try {
            return this.a.b(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final g c() {
        try {
            return new g(this.a.c());
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void c(boolean z) {
        try {
            this.a.d(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final void d(boolean z) {
        try {
            this.a.c(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void a(l lVar) {
        try {
            this.a.a(new v(lVar));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }
}
