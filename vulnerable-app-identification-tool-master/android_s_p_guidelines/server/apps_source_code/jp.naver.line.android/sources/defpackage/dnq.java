package defpackage;

import android.app.Activity;
import android.app.Application;
import com.linecorp.legy.external.network.a;
import com.linecorp.legy.external.network.d;
import java.util.ArrayList;
import jp.naver.line.android.b;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u0010H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/linecorp/line/ad/LineAdModuleBuilder;", "", "()V", "CHANNEL_ID", "", "kotlin.jvm.PlatformType", "appVersion", "getAppVersion", "()Ljava/lang/String;", "userAgent", "getUserAgent", "build", "Lcom/linecorp/line/ad/handler/AdContext;", "application", "Landroid/app/Application;", "enabledMoreMenuAd", "", "enabledSmartChannel", "SmartCHChannelTokenProviderImpl", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
/* renamed from: dnq */
public final class dnq {
    public static final dnq a = new dnq();
    private static final String b = ter.SMART_CHANNEL.a(b.g);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/line/ad/core/datamanager/network/ConnectionStatus;", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: dnq$a */
    final class a extends acrz implements acqq<dvf> {
        final /* synthetic */ Application a;

        a(Application application) {
            this.a = application;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            d a = com.linecorp.legy.external.network.a.a().a(this.a.getApplicationContext());
            if (!a.a() || !a.b()) {
                return dvf.UNKNOWN;
            }
            if (a.c()) {
                return dvf.WIFI;
            }
            return dvf.CELLULAR_UNKNOWN;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: dnq$b */
    final class b extends acrz implements acqq<Integer> {
        final /* synthetic */ Application a;

        b(Application application) {
            this.a = application;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            d a = a.a().a(this.a.getApplicationContext());
            int i = 0;
            if (a.a() && a.b()) {
                boolean c = tjl.c(this.a.getApplicationContext());
                boolean d = tjl.d(this.a.getApplicationContext());
                if (c && d) {
                    i = 3;
                } else if (d) {
                    i = 2;
                } else if (c) {
                    i = 1;
                }
            }
            return Integer.valueOf(i);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "activity", "Landroid/app/Activity;", "uri", "", "useExternal", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: dnq$c */
    final class c extends acrz implements acrg<Activity, String, Boolean, Boolean> {
        public static final c a = new c();

        c() {
            super(3);
        }

        public final /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            Activity activity = (Activity) obj;
            String str = (String) obj2;
            boolean booleanValue = ((Boolean) obj3).booleanValue();
            StringBuilder stringBuilder = new StringBuilder(":");
            stringBuilder.append(str);
            stringBuilder.append(':');
            stringBuilder.append(booleanValue);
            dox.a();
            return Boolean.valueOf(dng.a(activity, str, booleanValue));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: dnq$d */
    final class d extends acrz implements acqq<String> {
        public static final d a = new d();

        d() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            uob uob = uoa.a;
            return uoa.h.a().m();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: dnq$e */
    final class e extends acrz implements acqq<String> {
        public static final e a = new e();

        e() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            uob uob = uoa.a;
            Object g = uoa.h.a().g();
            return g == null ? utk.i() : g;
        }
    }

    private dnq() {
    }

    public static dxm a(Application application) {
        dxt dxt = new dxt(application.getApplicationContext(), application.getApplicationContext().getPackageName(), utk.e());
        dxv dxv = new dxv(d.a, e.a);
        dxs dxs = new dxs();
        dxs.a = dxt;
        dxs.b = dxv;
        dxs.c = new dxu(utk.g());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new dyi());
        arrayList.add(new dnn());
        return new dxm(dxs, new dxq(new a(application), new b(application), arrayList, c.a, new dnr(sil.a())));
    }

    public static final boolean a() {
        return uuk.INSTANCE.settings.bR;
    }
}
