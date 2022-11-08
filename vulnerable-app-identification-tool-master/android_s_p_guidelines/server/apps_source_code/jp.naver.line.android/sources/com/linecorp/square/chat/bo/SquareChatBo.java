package com.linecorp.square.chat.bo;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import com.linecorp.rxeventbus.a;
import com.linecorp.square.SquareExecutor;
import com.linecorp.square.chat.bo.task.CreateSquareChatTask;
import com.linecorp.square.chat.bo.task.CreateSquareChatTask.RequestParam;
import com.linecorp.square.chat.bo.task.DeleteSquareChatTask;
import com.linecorp.square.chat.bo.task.DestroySquareMessageTask;
import com.linecorp.square.chat.bo.task.GetChatMaxMemberCountTask;
import com.linecorp.square.chat.bo.task.GetSquareChatMembersTask;
import com.linecorp.square.chat.bo.task.GetSquareChatTask;
import com.linecorp.square.chat.bo.task.GetSquareChatsTask;
import com.linecorp.square.chat.bo.task.GetSquareOneOnOneChatTask;
import com.linecorp.square.chat.bo.task.InviteIntoSquareChatTask;
import com.linecorp.square.chat.bo.task.JoinSquareChatTask;
import com.linecorp.square.chat.bo.task.LeaveSquareChatTask;
import com.linecorp.square.chat.bo.task.ReportSquareChatTask;
import com.linecorp.square.chat.bo.task.ReportSquareMessageTask;
import com.linecorp.square.chat.bo.task.RetrieveSquareChatBadgeStatusTask;
import com.linecorp.square.chat.bo.task.SearchSquareChatMembersTask;
import com.linecorp.square.chat.bo.task.SquareSendMessageTask;
import com.linecorp.square.chat.bo.task.UpdateChatMaxMemberCountTask;
import com.linecorp.square.chat.bo.task.UpdateChatProfileImageTask;
import com.linecorp.square.chat.bo.task.UpdateChatRoomNotificationTask;
import com.linecorp.square.chat.bo.task.UpdateSquareChatBadgeStatusTask;
import com.linecorp.square.chat.bo.task.UpdateSquareChatTask;
import com.linecorp.square.chat.bo.task.UpdateSquareChatTask.UpdateSquareChatRequestCreator;
import com.linecorp.square.chat.dao.SquareChatTableAdapterImpl;
import com.linecorp.square.chat.dao.SquareOneOnOneChatDao;
import com.linecorp.square.chat.db.model.SquareChatDto;
import com.linecorp.square.chat.db.schema.SquareChatSchema;
import com.linecorp.square.event.callback.RequestCallback;
import com.linecorp.square.event.callback.SquareRequestCallback;
import com.linecorp.square.group.bo.SquareGroupFeatureSetBo;
import com.linecorp.square.group.bo.SquareGroupMemberBo;
import com.linecorp.square.group.db.model.SquareGroupFeature;
import com.linecorp.square.group.db.model.SquareGroupFeatureSetDto;
import com.linecorp.square.group.ui.create.model.ProfileInfo;
import com.linecorp.square.protocol.thrift.DestroyMessagesResponse;
import com.linecorp.square.protocol.thrift.GetInvitationTicketUrlRequest;
import com.linecorp.square.protocol.thrift.GetInvitationTicketUrlResponse;
import com.linecorp.square.protocol.thrift.GetJoinableSquareChatsResponse;
import com.linecorp.square.protocol.thrift.GetSquareChatMembersRequest;
import com.linecorp.square.protocol.thrift.GetSquareChatMembersResponse;
import com.linecorp.square.protocol.thrift.InviteIntoSquareChatResponse;
import com.linecorp.square.protocol.thrift.ReportSquareChatRequest;
import com.linecorp.square.protocol.thrift.ReportType;
import com.linecorp.square.protocol.thrift.SearchSquareChatMembersRequest;
import com.linecorp.square.protocol.thrift.SearchSquareChatMembersResponse;
import com.linecorp.square.protocol.thrift.common.NotifiedMessageType;
import com.linecorp.square.protocol.thrift.common.SquareChatMember;
import com.linecorp.square.protocol.thrift.common.SquareChatMembershipState;
import com.linecorp.square.protocol.thrift.common.SquareChatStatus;
import com.linecorp.square.protocol.thrift.common.SquareChatStatusWithoutMessage;
import com.linecorp.square.protocol.thrift.common.SquareException;
import com.linecorp.square.protocol.thrift.common.SquareMember;
import defpackage.acry;
import defpackage.adxc;
import defpackage.adxj;
import defpackage.adxl;
import defpackage.adxq;
import defpackage.aefz;
import defpackage.oxw;
import defpackage.oyg;
import defpackage.oyz;
import defpackage.qaa;
import defpackage.qtj;
import defpackage.syl;
import defpackage.tdt;
import defpackage.tjm;
import defpackage.twj;
import defpackage.twm;
import defpackage.tyq;
import defpackage.tyr;
import defpackage.tys;
import defpackage.vkt;
import defpackage.vku;
import defpackage.vxk;
import defpackage.zce;
import java.util.Date;
import java.util.List;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.activity.chathistory.list.f;
import jp.naver.line.android.bo.g;
import jp.naver.line.android.db.main.model.c;
import jp.naver.line.android.model.ah;
import jp.naver.line.android.model.cz;

public class SquareChatBo {
    GetChatMaxMemberCountTask A;
    UpdateChatMaxMemberCountTask B;
    GetSquareChatTask C;
    RetrieveSquareChatBadgeStatusTask D;
    UpdateSquareChatBadgeStatusTask E;
    SearchSquareChatMembersTask F;
    SquareExecutor a;
    vku b;
    tyr c;
    syl d;
    tyq e;
    tys f;
    SquareSendMessageTask g;
    CreateSquareChatTask h;
    UpdateSquareChatTask i;
    GetSquareChatsTask j;
    JoinSquareChatTask k;
    LeaveSquareChatTask l;
    InviteIntoSquareChatTask m;
    GetSquareChatMembersTask n;
    ReportSquareChatTask o;
    ReportSquareMessageTask p;
    UpdateChatProfileImageTask q;
    DeleteSquareChatTask r;
    g s;
    LineApplication t;
    DestroySquareMessageTask u;
    GetSquareOneOnOneChatTask v;
    SquareOneOnOneChatDao w;
    SquareGroupMemberBo x;
    UpdateChatRoomNotificationTask y;
    SquareGroupFeatureSetBo z;

    public final void a(RequestParam requestParam, ProfileInfo profileInfo, List<String> list, RequestCallback<SquareChatDto, Exception> requestCallback) {
        this.h.a(requestParam, profileInfo, list, requestCallback);
    }

    public final void a(String str, String str2, SquareRequestCallback<SquareChatDto> squareRequestCallback, SquareRequestCallback<Exception> squareRequestCallback2) {
        this.v.a(str, str2, squareRequestCallback, squareRequestCallback2);
    }

    public final oyz<SquareChatDto> a(String str, String str2, long j, int i) {
        return this.B.a(str, str2, j, i);
    }

    public final void a(String str, String str2, String str3, long j, RequestCallback<SquareChatDto, Throwable> requestCallback) {
        this.i.a(new UpdateSquareChatRequestCreator(str, str2, j).b(str3), requestCallback);
    }

    public final oyg<SquareChatDto> a(String str) {
        return this.C.b(str);
    }

    public final void a(zce zce, int i, RequestCallback<zce, Exception> requestCallback) {
        this.g.a(zce, i, requestCallback);
    }

    public final void b(String str, String str2, SquareRequestCallback<GetJoinableSquareChatsResponse> squareRequestCallback, SquareRequestCallback<Exception> squareRequestCallback2) {
        this.j.a(str, str2, squareRequestCallback, squareRequestCallback2);
    }

    public final void a(String str, RequestCallback<SquareChatDto, Exception> requestCallback) {
        this.k.a(str, requestCallback);
    }

    public final void a(List<String> list, String str, RequestCallback<InviteIntoSquareChatResponse, Exception> requestCallback) {
        this.m.a(list, str, requestCallback);
    }

    public final oyz<GetSquareChatMembersResponse> a(String str, String str2) {
        GetSquareChatMembersTask getSquareChatMembersTask = this.n;
        vkt vkt = getSquareChatMembersTask.b;
        if (vkt == null) {
            acry.a("squareNewServiceClient");
        }
        oyz a = vkt.a(new GetSquareChatMembersRequest(str, str2));
        SquareExecutor squareExecutor = getSquareChatMembersTask.a;
        if (squareExecutor == null) {
            acry.a("squareExecutor");
        }
        return a.b(qaa.a(squareExecutor.c()));
    }

    public final oyz<SearchSquareChatMembersResponse> a(SearchSquareChatMembersRequest searchSquareChatMembersRequest) {
        SearchSquareChatMembersTask searchSquareChatMembersTask = this.F;
        vkt vkt = searchSquareChatMembersTask.b;
        if (vkt == null) {
            acry.a("squareNewServiceClient");
        }
        oyz a = vkt.a(searchSquareChatMembersRequest);
        SquareExecutor squareExecutor = searchSquareChatMembersTask.a;
        if (squareExecutor == null) {
            acry.a("squareExecutor");
        }
        return a.b(qaa.a(squareExecutor.c()));
    }

    public final void b(String str, RequestCallback<GetInvitationTicketUrlResponse, Exception> requestCallback) {
        adxc a = adxc.a(this.b.a(new GetInvitationTicketUrlRequest(str))).b(aefz.a(this.a.c())).a(adxq.a());
        requestCallback.getClass();
        a.a(new -$$Lambda$3MYpD1hwXJVfMAdc_xaPmbnFyqI(requestCallback), new -$$Lambda$SquareChatBo$aXFk8xGht6ZxGoNngVQNfF4te0g(requestCallback));
    }

    public final oxw a(String str, String str2, ReportType reportType) {
        vkt vkt = this.o.a;
        if (vkt == null) {
            acry.a("squareNewServiceClient");
        }
        return vkt.a(new ReportSquareChatRequest(str, str2, reportType)).h().b(qaa.b());
    }

    public final oxw a(String str, String str2, Long l, ReportType reportType) {
        return this.p.a(str, str2, l.longValue(), reportType);
    }

    public final void a(jp.naver.line.android.model.g gVar, RequestCallback<Void, Exception> requestCallback) {
        this.l.a(gVar.v(), requestCallback);
    }

    public final void a(String str, zce zce, String str2, String str3, NotifiedMessageType notifiedMessageType, int i) {
        SquareChatBo squareChatBo = this;
        zce zce2 = zce;
        if (zce2 != null) {
            c cVar = new c(zce2.k);
            String str4 = str;
            String a = g.a(squareChatBo.t, ah.a(zce2, cVar, str4, false), TextUtils.isEmpty(str2), zce2.j, str2);
            tdt a2 = g.a(cVar);
            Date date = r3;
            Date date2 = new Date(zce2.e);
            squareChatBo.e.a(new SquareChatDto(str4, null, null, null, a, date, false, false, false, null, null, null, 0, 0, i, null, null, null, false, 0, null, null, null, null, null, str3, null, null, a2, BaseClientBuilder.API_PRIORITY_OTHER, SquareChatSchema.NotifiedMessageType.a(notifiedMessageType)), "last_message", "last_message_meta_data", "unread_message_count", "last_created_time", "latest_mentioned_position", "is_archived", "notified_message_type");
            return;
        }
        squareChatBo.e.a(new SquareChatDto(str, null, null, null, null, null, false, false, false, null, null, null, 0, 0, i, null, null, null, false, 0, null, null, null, null, null, null, null, null, null, BaseClientBuilder.API_PRIORITY_OTHER, null), "unread_message_count");
    }

    public final void a(String str, f fVar, RequestCallback<DestroyMessagesResponse, Throwable> requestCallback) {
        this.u.a(str, fVar.r().c(), requestCallback);
    }

    public final void a(SquareChatDto squareChatDto, ProfileInfo profileInfo, RequestCallback<SquareChatDto, Throwable> requestCallback) {
        if (profileInfo.a()) {
            this.q.a(squareChatDto, profileInfo, requestCallback);
        } else if (TextUtils.isEmpty(squareChatDto.x())) {
            requestCallback.b(new SquareException());
        } else if (profileInfo.b == null) {
            requestCallback.b(new SquareException());
        } else {
            this.i.a(new UpdateSquareChatRequestCreator(squareChatDto.x(), squareChatDto.v(), squareChatDto.H()).a(profileInfo.b.d), requestCallback);
        }
    }

    public final void a() {
        syl.a(twj.a(twm.SQUARE));
        this.e.j(null);
    }

    public final void a(List<String> list, final RequestCallback<Void, Exception> requestCallback) {
        adxj.a(new -$$Lambda$SquareChatBo$0RXiex6MV0U01IhZUyDw5YXOuKA(this, list)).b(aefz.a(this.a.a())).a(adxq.a()).a(new adxl<Void>(this) {
            final /* synthetic */ SquareChatBo b;

            public final void a(Throwable th) {
                requestCallback.b((Exception) th);
            }
        });
    }

    private /* synthetic */ Void a(List list) throws Exception {
        SQLiteDatabase a = twj.a(twm.SQUARE);
        try {
            a.beginTransaction();
            for (String str : list) {
                syl.a(a, str);
                this.e.j(str);
                this.e.a(str, true);
            }
            a.setTransactionSuccessful();
            return null;
        } finally {
            a.endTransaction();
        }
    }

    public final void c(String str, final RequestCallback<Void, Exception> requestCallback) {
        adxj.a(new -$$Lambda$SquareChatBo$RL91PbgT20LJboNlhNoonmZFiPM(this, str)).b(aefz.a(this.a.a())).a(adxq.a()).a(new adxl<Void>(this) {
            final /* synthetic */ SquareChatBo b;

            public final void a(Throwable th) {
                requestCallback.b((Exception) th);
            }
        });
    }

    private /* synthetic */ Void h(String str) throws Exception {
        SQLiteDatabase a = twj.a(twm.SQUARE);
        try {
            a.beginTransaction();
            syl.a(a, str);
            this.e.j(str);
            a.setTransactionSuccessful();
            return null;
        } finally {
            a.endTransaction();
        }
    }

    public final cz b(String str) {
        str = SquareOneOnOneChatDao.b(str);
        if (str == null) {
            return null;
        }
        return this.x.b(str);
    }

    public final void a(String str, long j, RequestCallback<Void, Throwable> requestCallback) {
        this.r.a(str, j, requestCallback);
    }

    public final void c(String str) {
        this.e.a().d().a(SquareChatSchema.d, null).a(SquareChatSchema.c, null).a(SquareChatSchema.t, Long.valueOf(0)).a(SquareChatSchema.d.a(), new String[]{str}).a();
    }

    public final void a(String str, boolean z, RequestCallback<Boolean, Throwable> requestCallback) {
        this.y.a(str, z, requestCallback);
    }

    public final void d(String str) {
        for (jp.naver.line.android.model.g v : this.e.a().h(str)) {
            tys.e(v.v());
        }
        this.e.a().c().a(SquareChatSchema.d.a(), new String[]{str}).a();
    }

    public final String e(String str) {
        return this.e.a().i(str);
    }

    public final boolean a(SquareChatDto squareChatDto) {
        if (TextUtils.isEmpty(squareChatDto.x())) {
            return false;
        }
        if (squareChatDto.t() && ((SquareGroupFeatureSetDto) this.z.a(squareChatDto.x()).e(new -$$Lambda$SquareChatBo$jnT_dOdhVy453fx1khOOD35SupI(squareChatDto)).e()).c() != SquareGroupFeature.ON) {
            return false;
        }
        return true;
    }

    public final void b(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            long a = tjm.a(str2, 0);
            if (a != 0) {
                this.e.a(str, a);
            }
        }
    }

    public final oyg<Integer> f(String str) {
        return this.A.a(str);
    }

    public final oyz<vxk> g(String str) {
        return this.D.a(str);
    }

    public final oxw a(a aVar, qtj qtj) {
        return this.E.a(aVar, qtj);
    }

    public final oxw a(a aVar) {
        return this.E.a(aVar);
    }

    public final void a(String str, SquareMember squareMember) {
        SquareOneOnOneChatDao.a(str, squareMember.a);
        this.x.a(squareMember);
    }

    public final void a(SquareChatMember squareChatMember) {
        SquareChatBo squareChatBo = this;
        SquareChatMember squareChatMember2 = squareChatMember;
        String str = squareChatMember2.b;
        if (squareChatMember2.d == SquareChatMembershipState.LEFT) {
            squareChatBo.e.k(str);
            squareChatBo.f.f(str);
            return;
        }
        long j = squareChatMember2.c;
        long j2 = j;
        SquareChatDto squareChatDto = r2;
        SquareChatDto squareChatDto2 = (SquareChatDto) squareChatBo.e.g(str);
        long j3 = j;
        String str2 = str;
        SquareChatDto squareChatDto3 = new SquareChatDto(str, null, null, null, null, null, false, false, false, null, null, null, 0, 0, 0, null, null, null, false, j2, null, null, null, null, squareChatMember2.a, null, null, null, null, BaseClientBuilder.API_PRIORITY_OTHER, null);
        squareChatDto3 = squareChatDto2;
        if (squareChatDto3 == null) {
            jp.naver.line.android.model.g gVar = squareChatDto;
            squareChatBo.f.a(str2, squareChatMember2.e);
            squareChatBo.e.a(gVar);
        } else if (squareChatDto3.b(j3)) {
            squareChatBo.f.a(str2, squareChatMember2.e);
            squareChatBo.e.a((jp.naver.line.android.model.g) squareChatDto, "my_member_mid", "member_rev");
        } else {
            str = str2;
            SquareChatTableAdapterImpl a = squareChatBo.e.a();
            a.d().a(SquareChatSchema.w, squareChatMember2.a).a(SquareChatSchema.b.a(), new String[]{str}).a();
        }
    }

    public final void a(String str, SquareChatStatusWithoutMessage squareChatStatusWithoutMessage) {
        SquareChatStatusWithoutMessage squareChatStatusWithoutMessage2 = squareChatStatusWithoutMessage;
        this.e.a(new SquareChatDto(str, null, null, null, null, null, false, false, false, null, null, null, 0, squareChatStatusWithoutMessage2.a, squareChatStatusWithoutMessage2.b, null, null, null, false, 0, null, null, null, null, null, squareChatStatusWithoutMessage2.d, null, null, null, BaseClientBuilder.API_PRIORITY_OTHER, SquareChatSchema.NotifiedMessageType.a(squareChatStatusWithoutMessage2.e)), "unread_message_count", "chat_member_count", "latest_mentioned_position", "notified_message_type");
    }

    public final void a(String str, SquareChatStatus squareChatStatus) {
        if (squareChatStatus.a == null || squareChatStatus.a.a == null) {
            a(str, squareChatStatus.c);
            return;
        }
        a(str, squareChatStatus.a.a, squareChatStatus.b, squareChatStatus.c.d, squareChatStatus.c.e, squareChatStatus.c.b);
    }
}
