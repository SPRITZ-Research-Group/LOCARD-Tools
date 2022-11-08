package com.linecorp.square;

import android.app.AlarmManager;
import com.google.android.exoplayer.util.MimeTypes;
import com.linecorp.rxeventbus.a;
import com.linecorp.square.bot.bo.SquareBotBo;
import com.linecorp.square.chat.bo.SquareChatBo;
import com.linecorp.square.common.bo.ServiceLocalizationManagerWrapper;
import com.linecorp.square.common.bo.SquareFeatureBo;
import com.linecorp.square.common.bo.SquareNotificationBo;
import com.linecorp.square.event.bo.SquareSubscriptionManager;
import com.linecorp.square.event.bo.chat.SquareChatEventBo;
import com.linecorp.square.group.bo.IntegratedFavoriteBo;
import com.linecorp.square.group.bo.SquareGeneralSettingsBo;
import com.linecorp.square.group.bo.SquareGroupAuthorityBo;
import com.linecorp.square.group.bo.SquareGroupBo;
import com.linecorp.square.group.bo.SquareGroupMemberBo;
import com.linecorp.square.group.bo.SquareGroupMemberRelationBo;
import com.linecorp.square.group.dao.SquareGroupDao;
import defpackage.des;
import defpackage.dfx;
import defpackage.kyl;
import defpackage.kym;
import defpackage.kyo;
import defpackage.svo;
import defpackage.svs;
import defpackage.swv;
import defpackage.syl;
import defpackage.tyq;
import defpackage.tyr;
import defpackage.tys;
import defpackage.udx;
import defpackage.usr;
import defpackage.uuk;
import defpackage.vks;
import defpackage.vkt;
import defpackage.vku;
import defpackage.vkx;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.bo.g;
import jp.naver.line.android.common.c;
import jp.naver.line.android.l;
import jp.naver.line.android.util.at;
import jp.naver.line.android.util.au;

public class SquareContext {
    public static final String a;
    kyl b;
    AtomicBoolean c = new AtomicBoolean(false);

    class SquareModule {
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SquareConsts.a);
        stringBuilder.append(".context");
        a = stringBuilder.toString();
    }

    public SquareContext() {
        Throwable e;
        try {
            LineApplication a = l.a();
            vku E = vkx.E();
            vkt F = vkx.F();
            vks G = vkx.G();
            dfx d = des.a().d();
            ScheduledExecutorService b = at.b(au.BASEACTIVITY);
            c a2 = c.a();
            a a3 = a.a();
            swv b2 = a.f().b(true);
            tyr b3 = b2.b();
            syl c = b2.c();
            g d2 = b2.d();
            tyq a4 = b2.d().a();
            tys e2 = d2.e();
            usr a5 = a.f().a();
            try {
                AlarmManager alarmManager = (AlarmManager) a.getSystemService("alarm");
                tys tys = e2;
                svs a6 = svs.a(svo.SQUARE);
                uuk a7 = uuk.a();
                this.b = new kym().a(new kyo().a("squareServiceClient", E).a("squareNewServiceClient", F).a("squareBotServiceClient", G).a("sessionStatusNotifier", d).a("scheduledExecutorService", b).a("applicationForegroundEventMonitor", a2).a("eventBus", a3).a("chatHistoryDao", b3).a("messageDataManager", b2).a("newChatHistoryDao", c).a("chatBO", d2).a("chatDao", a4).a(MimeTypes.BASE_TYPE_APPLICATION, a).a("alarmManager", alarmManager).a("readCountManager", a5).a("chatSettingDao", tys).a("squareFeatureBo", new SquareFeatureBo(new ServiceLocalizationManagerWrapper(uuk.a()))).a("chatAnnouncementBo", a6).a("serviceLocalizationManager", a7)).a(Injectable_SquareModule.a()).a();
            } catch (Exception e3) {
                e = e3;
                SquareContext squareContext = this;
                udx.b(e, "LINEAND-18265", "Failed initializing SquareContext", "SquareContext");
            }
        } catch (Exception e4) {
            e = e4;
            udx.b(e, "LINEAND-18265", "Failed initializing SquareContext", "SquareContext");
        }
    }

    public final void a() {
        SquareSubscriptionManager g = g();
        if (this.c.compareAndSet(false, true)) {
            g.a();
        }
    }

    public final kyl b() {
        return this.b;
    }

    public final SquareGroupBo c() {
        return (SquareGroupBo) this.b.a("squareGroupBo");
    }

    public final SquareGroupMemberBo d() {
        return (SquareGroupMemberBo) this.b.a("squareGroupMemberBo");
    }

    public final SquareGroupMemberRelationBo e() {
        return (SquareGroupMemberRelationBo) this.b.a("squareGroupMemberRelationBo");
    }

    public final IntegratedFavoriteBo f() {
        return (IntegratedFavoriteBo) this.b.a("integratedFavoriteBo");
    }

    public final SquareSubscriptionManager g() {
        return (SquareSubscriptionManager) this.b.a("squareSubscriptionManager");
    }

    public final SquareChatBo h() {
        return (SquareChatBo) this.b.a("squareChatBo");
    }

    public final SquareChatEventBo i() {
        return (SquareChatEventBo) this.b.a("squareChatEventBo");
    }

    public final SquareGroupAuthorityBo j() {
        return (SquareGroupAuthorityBo) this.b.a("squareGroupAuthorityBo");
    }

    public final SquareMigration k() {
        return (SquareMigration) this.b.a("squareMigration");
    }

    public final SquareExecutor l() {
        return (SquareExecutor) this.b.a("squareExecutor");
    }

    public final SquareNotificationBo m() {
        return (SquareNotificationBo) this.b.a("squareNotificationBo");
    }

    public final SquareFeatureBo n() {
        return (SquareFeatureBo) this.b.a("squareFeatureBo");
    }

    public final SquareGeneralSettingsBo o() {
        return (SquareGeneralSettingsBo) this.b.a("squareGeneralSettingsBo");
    }

    public final SquareBotBo p() {
        return (SquareBotBo) this.b.a("squareBotBo");
    }

    public final SquareGroupDao q() {
        return (SquareGroupDao) this.b.a("squareGroupDao");
    }
}
