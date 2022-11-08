package defpackage;

import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\b\u0016\u0018\u00002\u00020\u0001:\u0006012345B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R*\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/¨\u00066"}, d2 = {"Lcom/linecorp/line/ad/handler/service/AdServiceSpec;", "", "serviceId", "", "netSpec", "Lcom/linecorp/line/ad/handler/service/AdServiceSpec$NetworkSpec;", "confSpec", "Lcom/linecorp/line/ad/handler/service/AdServiceSpec$ConfigSpec;", "(Ljava/lang/String;Lcom/linecorp/line/ad/handler/service/AdServiceSpec$NetworkSpec;Lcom/linecorp/line/ad/handler/service/AdServiceSpec$ConfigSpec;)V", "getConfSpec", "()Lcom/linecorp/line/ad/handler/service/AdServiceSpec$ConfigSpec;", "dataReceiver", "", "Lcom/linecorp/line/ad/core/renderer/inventory/AdItemLoader;", "getDataReceiver", "()Ljava/util/Map;", "setDataReceiver", "(Ljava/util/Map;)V", "inventoryList", "Ljava/util/ArrayList;", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec;", "Lkotlin/collections/ArrayList;", "getInventoryList", "()Ljava/util/ArrayList;", "setInventoryList", "(Ljava/util/ArrayList;)V", "itemArrangements", "Lcom/linecorp/line/ad/handler/service/AdServiceSpec$ItemArrangements;", "getItemArrangements", "()Lcom/linecorp/line/ad/handler/service/AdServiceSpec$ItemArrangements;", "setItemArrangements", "(Lcom/linecorp/line/ad/handler/service/AdServiceSpec$ItemArrangements;)V", "getNetSpec", "()Lcom/linecorp/line/ad/handler/service/AdServiceSpec$NetworkSpec;", "phase", "Lcom/linecorp/line/ad/handler/LassPhase;", "getPhase", "()Lcom/linecorp/line/ad/handler/LassPhase;", "setPhase", "(Lcom/linecorp/line/ad/handler/LassPhase;)V", "preloading", "", "getPreloading", "()Z", "setPreloading", "(Z)V", "getServiceId", "()Ljava/lang/String;", "ArrangeMethod", "ConfigSpec", "DynamicIndexAndGap", "ItemArrangements", "NetworkSpec", "StaticIndexArray", "line-android-ladsdk_release"}, k = 1, mv = {1, 1, 13})
/* renamed from: eam */
public class eam {
    private boolean a;
    private eaq b;
    private final String c;
    private final ear d;
    public ArrayList<dza> e;
    public Map<String, dwe> f;
    public dxp g;
    private final eao h;

    public eam(String str, ear ear, eao eao) {
        this.c = str;
        this.d = ear;
        this.h = eao;
    }

    public final String f() {
        return this.c;
    }

    public final ear g() {
        return this.d;
    }

    public final eao h() {
        return this.h;
    }

    public final ArrayList<dza> a() {
        ArrayList<dza> arrayList = this.e;
        if (arrayList == null) {
            acry.a("inventoryList");
        }
        return arrayList;
    }

    public final Map<String, dwe> b() {
        Map<String, dwe> map = this.f;
        if (map == null) {
            acry.a("dataReceiver");
        }
        return map;
    }

    public final boolean c() {
        return this.a;
    }

    public final void d() {
        this.a = false;
    }

    public final eaq e() {
        return this.b;
    }
}
