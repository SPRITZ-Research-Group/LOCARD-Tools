package defpackage;

import android.app.Application;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jp.naver.line.android.BuildConfig;
import jp.naver.line.android.b;
import kotlin.Metadata;
import kotlin.u;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\"\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0002J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0006H\u0002J\u001c\u0010)\u001a\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120,H\u0002J\u0010\u0010-\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010.\u001a\u00020\u0004H\u0007J\u0018\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020#2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u00101\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020#H\u0002J\u0018\u00102\u001a\b\u0012\u0004\u0012\u00020\u000603*\b\u0012\u0004\u0012\u00020\u000603H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u00020\u00048\u0006XD¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/linecorp/line/ad/LineAdSpecBuilder;", "", "()V", "CUSTOM_VIEW_DEFAULT_BACKGROUND_COLOR", "", "CUSTOM_VIEW_HEIGHT", "", "CUSTOM_VIEW_WIDTH", "SMART_CHANNEL_BADGE_ID", "SMART_CHANNEL_DESC_ID", "SMART_CHANNEL_DEVIDER_ID", "SMART_CHANNEL_ICON_ID", "SMART_CHANNEL_MIN_INTERVAL_IN_MILLIS", "", "SMART_CHANNEL_MUTE_BUTTON_EXPAND_TOUCH", "SMART_CHANNEL_MUTE_BUTTON_ID", "SMART_CHANNEL_TITLE_ID", "badgeAsset", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec$DynamicViewSpec;", "badgeBottomMargin", "badgeLeftMargin", "badgeTopMargin", "deviderAsset", "iconAsset", "keyOfSmartCH", "keyOfSmartCH$annotations", "getKeyOfSmartCH", "()Ljava/lang/String;", "longPressMuteName", "generateChatListSpec", "Lcom/linecorp/line/ad/handler/service/AdServiceSpec;", "application", "Landroid/app/Application;", "getDescAsset", "hasIcon", "", "getInventorySpec", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec;", "layoutSpec", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec$LayoutSpec;", "viewType", "getLayoutSpec", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec$DynamicLayoutSpec;", "spec", "", "getMuteClose", "getSmartChannelName", "getTitleAsset", "existDesc", "toRightMargin", "extractDataAsset", "", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
/* renamed from: dns */
public final class dns {
    public static final dns a = new dns();
    private static final String b = "dBHUjxB92S0";
    private static final dzd c;
    private static final dzd d;
    private static final dzd e;

    private static int a(boolean z) {
        return z ? 90 : 44;
    }

    public static final String b() {
        return "SmartCH";
    }

    static {
        eat eat = eat.Image;
        eav eav = eav.WrapContent;
        c = new dzd(1, 44, 44, eat, new eae(eav, eav, acom.a(u.a(eax.AlignWithParent, Integer.valueOf(0)), u.a(eay.Right, Integer.valueOf(0)), u.a(eaw.Right, Integer.valueOf(24)), u.a(eaw.Top, Integer.valueOf(23)), u.a(eaw.Bottom, Integer.valueOf(18)))), new dzl[]{new dzf(44, 44)});
        eat eat2 = eat.Text;
        eav eav2 = eav.WrapContent;
        d = new dzd(4, 0, 0, eat2, new eae(eav2, eav2, acom.a(u.a(eax.AlignWithParent, Integer.valueOf(0)), u.a(eaw.Left, Integer.valueOf(19)), u.a(eaw.Top, Integer.valueOf(18)), u.a(eaw.Bottom, Integer.valueOf(52)))), new dzl[]{new dzm(-65536, 1, 1.3f), new dzu(8.5f, 1), new dzy()});
        eat eat3 = eat.View;
        eav2 = eav.MatchParent;
        e = new dzd(6, 0, 0, eat3, new eae(eav2, eav2, acom.a()), new dzl[]{new dzb()});
    }

    private dns() {
    }

    public static final String a() {
        return b;
    }

    private static dzd a(boolean z, boolean z2) {
        int a = dns.a(z2);
        dzl[] dzlArr = new dzl[6];
        dzlArr[0] = new dzm();
        dzlArr[1] = new dzu();
        dzlArr[2] = new dzr();
        dzlArr[3] = new dzs(z ? 1 : 2);
        dzlArr[4] = new dzn();
        dzlArr[5] = new dzx();
        eat eat = eat.Text;
        eav eav = eav.WrapContent;
        return new dzd(2, 0, 0, eat, new eae(eav, eav, acom.a(u.a(eax.AlignWithParent, Integer.valueOf(0)), u.a(eay.Left, Integer.valueOf(0)), u.a(eaw.Left, Integer.valueOf(20)), u.a(eaw.Right, Integer.valueOf(a)), u.a(eaw.Top, Integer.valueOf(36)))), dzlArr);
    }

    private static dzd b(boolean z) {
        int a = dns.a(z);
        eat eat = eat.Text;
        eav eav = eav.WrapContent;
        return new dzd(3, 0, 0, eat, new eae(eav, eav, acom.a(u.a(eax.AlignWithParent, Integer.valueOf(0)), u.a(eay.Left, Integer.valueOf(0)), u.a(eaw.Left, Integer.valueOf(20)), u.a(eaw.Right, Integer.valueOf(a)), u.a(eaw.Top, Integer.valueOf(53)))), new dzl[]{new dzm(), new dzu(), new dzr(), new dzo("sans-serif", dzp.Normal), new dzs(1), new dzn(), new dzx()});
    }

    private static dzd b(Application application) {
        eat eat = eat.Image;
        eav eav = eav.WrapContent;
        return new dzd(5, 24, 23, eat, new eae(eav, eav, acom.a(u.a(eax.AlignWithParent, Integer.valueOf(0)), u.a(eay.Right, Integer.valueOf(0)), u.a(eaw.Right, Integer.valueOf(0)), u.a(eaw.Bottom, Integer.valueOf(62)))), new dzl[]{new dze(application.getResources().getIdentifier("popup_banner_img_x_04", "drawable", "com.linecorp.line.ad")), new dzf(9, 9)});
    }

    private static dza a(dzk dzk, int i) {
        String str = b;
        eai eai = eai.AllInOne;
        dzi dzj = new dzj(i);
        dyu dyu = new dyu(true, true, false, false, null, 124);
        ArrayList f = dyu.f();
        dyt dyt = new dyt();
        dyt.a = new dyr(dys.Click, false, new eaz(dpx.MuteClose));
        dyt.b = "infomute";
        dyt.b(false);
        dyt.n();
        dyt.c(false);
        dyt.d(false);
        f.add(dyt);
        dyt dyt2 = new dyt();
        dyt2.a = new dyr(dys.LongClick, true);
        dyt2.b = "longClickForMute";
        dyt2.c(1);
        dyt2.b(true);
        dyu.a(dyt2);
        dza dza = new dza(str, eai, dzk, dzj, dyu);
        dza.a();
        return dza;
    }

    private static dzc a(Map<Integer, dzd> map) {
        return new dzc(eaj.Relative, eav.MatchParent, eav.WrapContent, Color.parseColor("#f9fafd"), map);
    }

    private static Set<Integer> a(Set<Integer> set) {
        Collection arrayList = new ArrayList();
        for (Object next : set) {
            int intValue = ((Number) next).intValue();
            dpy dpy = dpx.Companion;
            if ((dpy.a(intValue) ^ 1) != 0) {
                arrayList.add(next);
            }
        }
        return acnz.m((List) arrayList);
    }

    public static eam a(Application application) {
        dxp dxp;
        ear ear = new ear(eal.MediaAccessor, 0);
        ear.b = BuildConfig.SMART_CH_HOST;
        ear.a = BuildConfig.SMART_CH_URL;
        eao eao = new eao();
        qce qce = b.g;
        if (qce != null) {
            switch (dnt.a[qce.ordinal()]) {
                case 1:
                    dxp = dxp.Beta;
                    break;
                case 2:
                    dxp = dxp.Staging;
                    break;
                case 3:
                    dxp = dxp.Release;
                    break;
                case 4:
                case 5:
                    dxp = dxp.Test;
                    break;
            }
        }
        dox.b("Abnormal situation is detected");
        dxp = dxp.Test;
        eam don = new don("SmartCH", ear, eao);
        Map a = acom.a(u.a(Integer.valueOf(dpx.ICON.a()), c), u.a(Integer.valueOf(dpx.TITLE.a()), dns.a(true, true)), u.a(Integer.valueOf(dpx.DESCRIPTION.a()), dns.b(true)), u.a(Integer.valueOf(dpx.BADGE.a()), d), u.a(Integer.valueOf(dpx.MuteClose.a()), dns.b(application)), u.a(Integer.valueOf(dpx.ITEM_DIVIDER.a()), e));
        Map a2 = acom.a(u.a(Integer.valueOf(dpx.TITLE.a()), dns.a(true, false)), u.a(Integer.valueOf(dpx.DESCRIPTION.a()), dns.b(false)), u.a(Integer.valueOf(dpx.BADGE.a()), d), u.a(Integer.valueOf(dpx.MuteClose.a()), dns.b(application)), u.a(Integer.valueOf(dpx.ITEM_DIVIDER.a()), e));
        Map a3 = acom.a(u.a(Integer.valueOf(dpx.ICON.a()), c), u.a(Integer.valueOf(dpx.TITLE.a()), dns.a(false, true)), u.a(Integer.valueOf(dpx.BADGE.a()), d), u.a(Integer.valueOf(dpx.MuteClose.a()), dns.b(application)), u.a(Integer.valueOf(dpx.ITEM_DIVIDER.a()), e));
        Map a4 = acom.a(u.a(Integer.valueOf(dpx.TITLE.a()), dns.a(false, false)), u.a(Integer.valueOf(dpx.BADGE.a()), d), u.a(Integer.valueOf(dpx.MuteClose.a()), dns.b(application)), u.a(Integer.valueOf(dpx.ITEM_DIVIDER.a()), e));
        ArrayList arrayList = new ArrayList();
        arrayList.add(dns.a((dzk) dns.a(a), 4));
        arrayList.add(dns.a((dzk) dns.a(a2), 5));
        arrayList.add(dns.a((dzk) dns.a(a3), 6));
        arrayList.add(dns.a((dzk) dns.a(a4), 7));
        don.e = arrayList;
        don.f = acol.a(u.a(b, new dwe(b)));
        don.g = dxp;
        don.d();
        don.a = dns.a(a.keySet());
        don.b = dns.a(a2.keySet());
        don.c = dns.a(a3.keySet());
        don.d = dns.a(a4.keySet());
        return don;
    }
}
