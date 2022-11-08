package defpackage;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fJ\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u000fJ\u0016\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000fJ\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010%\u001a\u00020&J\u0010\u0010'\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u000fJ\t\u0010(\u001a\u00020)HÖ\u0001J\u000e\u0010*\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fJ\u000e\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u0010J\u001d\u0010-\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020/¢\u0006\u0002\u00100J\t\u00101\u001a\u00020\u000fHÖ\u0001J\u000e\u00102\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fJ#\u00102\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u000f2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000f04¢\u0006\u0002\u00105R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/linecorp/line/ad/handler/AdContext;", "", "env", "Lcom/linecorp/line/ad/handler/MediaEnvironment;", "accessor", "Lcom/linecorp/line/ad/handler/MediaAccessor;", "(Lcom/linecorp/line/ad/handler/MediaEnvironment;Lcom/linecorp/line/ad/handler/MediaAccessor;)V", "getAccessor", "()Lcom/linecorp/line/ad/handler/MediaAccessor;", "dataManager", "Lcom/linecorp/line/ad/core/datamanager/AdDataManager;", "getEnv", "()Lcom/linecorp/line/ad/handler/MediaEnvironment;", "servicesHolder", "", "", "Lcom/linecorp/line/ad/core/renderer/AdServiceManager;", "activityDestoryed", "", "id", "bindRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "(Ljava/lang/String;Landroidx/recyclerview/widget/RecyclerView;)Lkotlin/Unit;", "clearCache", "serviceId", "key", "clearCacheDirect", "component1", "component2", "copy", "dataManagerLogPublishSubject", "Lio/reactivex/subjects/PublishSubject;", "Lcom/linecorp/line/ad/handler/AdLog;", "equals", "", "other", "getLocale", "Lcom/linecorp/line/ad/core/datamanager/model/request/meta/AdEnvironment$LocaleInfo;", "getService", "hashCode", "", "leaveService", "registerService", "serviceManager", "sendHeaderEvent", "rect", "Landroid/graphics/Rect;", "(Ljava/lang/String;Landroid/graphics/Rect;)Lkotlin/Unit;", "toString", "updateAd", "keys", "", "(Ljava/lang/String;[Ljava/lang/String;)Lkotlin/Unit;", "line-android-ladsdk_release"}, k = 1, mv = {1, 1, 13})
/* renamed from: dxm */
public final class dxm {
    private final dqe a = new dqe(this.d, new drr(this.c), this.c.a().a(), new dqz(this.c.a().a()));
    private final Map<String, dvy> b = new LinkedHashMap();
    private final dxs c;
    private final dxq d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof dxm) {
                dxm dxm = (dxm) obj;
                if (acry.a(this.c, dxm.c)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        dxs dxs = this.c;
        int i = 0;
        int hashCode = (dxs != null ? dxs.hashCode() : 0) * 31;
        dxq dxq = this.d;
        if (dxq != null) {
            i = dxq.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("AdContext(env=");
        stringBuilder.append(this.c);
        stringBuilder.append(", accessor=");
        stringBuilder.append(this.d);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public dxm(dxs dxs, dxq dxq) {
        this.c = dxs;
        this.d = dxq;
    }

    public final dxs c() {
        return this.c;
    }

    public final dxq d() {
        return this.d;
    }

    public final void a(dvy dvy) {
        dvy.a(this.a);
        this.b.put(dvy.f(), dvy);
        this.a.a(dvy.e());
        if (dvy.d()) {
            this.a.b(dvy.f());
        }
    }

    public final dvy a(String str) {
        return (dvy) this.b.get(str);
    }

    public final void b(String str) {
        dvy dvy = (dvy) this.b.get(str);
        if (dvy != null) {
            dvy.h();
        }
    }

    public final void c(String str) {
        this.a.a(str);
    }

    public final void d(String str) {
        dvy dvy = (dvy) this.b.get(str);
        if (dvy != null) {
            dvy.i();
        }
    }

    public final dru a() {
        String a = this.a.b().a().a();
        if (a == null) {
            a = "";
        }
        dru a2 = this.a.b().e().a();
        String b = a2 != null ? a2.b() : null;
        if (b == null) {
            b = "";
        }
        return new dru(a, b);
    }

    public final qaq<dxn> b() {
        return this.a.a();
    }

    public final y a(String str, RecyclerView recyclerView) {
        dvy dvy = (dvy) this.b.get(str);
        if (dvy == null) {
            return null;
        }
        dvy.a(recyclerView);
        return y.a;
    }

    public final y a(String str, Rect rect) {
        dvy dvy = (dvy) this.b.get(str);
        if (dvy == null) {
            return null;
        }
        dvy.a(rect);
        return y.a;
    }
}
