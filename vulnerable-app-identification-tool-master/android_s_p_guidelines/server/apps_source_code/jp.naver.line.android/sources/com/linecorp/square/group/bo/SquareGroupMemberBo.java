package com.linecorp.square.group.bo;

import android.net.Uri;
import android.text.TextUtils;
import com.linecorp.square.SquareExecutor;
import com.linecorp.square.common.user.SquareUserDataCache;
import com.linecorp.square.event.callback.RequestCallback;
import com.linecorp.square.group.SquareGroupConsts;
import com.linecorp.square.group.bo.builder.UpdateSquareMemberRequestBuilder;
import com.linecorp.square.group.bo.model.RecentlyJoinedSquareGroupMemberResponse;
import com.linecorp.square.group.bo.task.ApproveSquareGroupMembersTask;
import com.linecorp.square.group.bo.task.GetSquareGroupMemberObservable;
import com.linecorp.square.group.bo.task.GetSquareGroupMemberTask;
import com.linecorp.square.group.bo.task.ManageDefaultMemberProfileTask;
import com.linecorp.square.group.bo.task.RejectSquareGroupMembersTask;
import com.linecorp.square.group.bo.task.ReportSquareGroupMemberTask;
import com.linecorp.square.group.bo.task.SearchSquareMembersTask;
import com.linecorp.square.group.bo.task.UpdateSquareGroupMemberTask;
import com.linecorp.square.group.bo.task.UpdateSquareGroupMembersTask;
import com.linecorp.square.group.dao.SquareGroupDao;
import com.linecorp.square.group.dao.SquareGroupMemberDao;
import com.linecorp.square.group.db.model.SquareGroupDto;
import com.linecorp.square.group.db.model.SquareGroupMemberDto;
import com.linecorp.square.group.db.model.SquareGroupMemberDto.Builder;
import com.linecorp.square.group.db.model.SquareGroupMemberRole;
import com.linecorp.square.protocol.thrift.ApproveSquareMembersResponse;
import com.linecorp.square.protocol.thrift.RejectSquareMembersResponse;
import com.linecorp.square.protocol.thrift.ReportSquareMemberRequest;
import com.linecorp.square.protocol.thrift.ReportType;
import com.linecorp.square.protocol.thrift.SearchSquareMembersRequest;
import com.linecorp.square.protocol.thrift.SearchSquareMembersResponse;
import com.linecorp.square.protocol.thrift.UpdateSquareMemberRequest;
import com.linecorp.square.protocol.thrift.UpdateSquareMembersRequest;
import com.linecorp.square.protocol.thrift.UpdateSquareMembersResponse;
import com.linecorp.square.protocol.thrift.common.SquareMember;
import com.linecorp.square.protocol.thrift.common.SquareMemberAttribute;
import com.linecorp.square.protocol.thrift.common.SquarePreferenceAttribute;
import com.linecorp.square.util.ObsUtils;
import defpackage.acry;
import defpackage.adnj;
import defpackage.adxc;
import defpackage.adxd;
import defpackage.adxf;
import defpackage.adxj;
import defpackage.adxm;
import defpackage.adxq;
import defpackage.aefz;
import defpackage.oxw;
import defpackage.oyc;
import defpackage.oyg;
import defpackage.oyz;
import defpackage.qaa;
import defpackage.vkt;
import defpackage.vku;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import jp.naver.line.android.obs.model.ObjectInfo;
import org.apache.http.HttpHeaders;

public class SquareGroupMemberBo {
    private static final String o;
    SquareExecutor a;
    SquareGroupDao b;
    SquareGroupMemberDao c;
    SquareUserDataCache d;
    vku e;
    RejectSquareGroupMembersTask f;
    ApproveSquareGroupMembersTask g;
    UpdateSquareGroupMemberTask h;
    UpdateSquareGroupMembersTask i;
    GetSquareGroupMemberObservable j;
    GetSquareGroupMemberTask k;
    ManageDefaultMemberProfileTask l;
    SearchSquareMembersTask m;
    ReportSquareGroupMemberTask n;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SquareGroupConsts.a);
        stringBuilder.append(".SquareGroupMemberBo");
        o = stringBuilder.toString();
    }

    public final oyc<SquareGroupMemberDto> a(String str) {
        return this.k.a(str, false);
    }

    public final oyc<SquareGroupMemberDto> a(String str, boolean z) {
        return this.k.a(str, z);
    }

    public final oyz<SearchSquareMembersResponse> a(SearchSquareMembersRequest searchSquareMembersRequest) {
        SearchSquareMembersTask searchSquareMembersTask = this.m;
        vkt vkt = searchSquareMembersTask.b;
        if (vkt == null) {
            acry.a("squareNewServiceClient");
        }
        oyz a = vkt.a(searchSquareMembersRequest);
        SquareExecutor squareExecutor = searchSquareMembersTask.a;
        if (squareExecutor == null) {
            acry.a("squareExecutor");
        }
        return a.b(qaa.a(squareExecutor.c()));
    }

    public final oyz<ApproveSquareMembersResponse> a(String str, List<String> list) {
        return this.g.a(str, list);
    }

    public final oyz<RejectSquareMembersResponse> b(String str, List<String> list) {
        return this.f.a(str, list);
    }

    public final oyz<SquareGroupMemberDto> a(SquareGroupMemberDto squareGroupMemberDto, UpdateSquareMemberRequest updateSquareMemberRequest) {
        return this.h.a(squareGroupMemberDto, updateSquareMemberRequest);
    }

    public final oyz<UpdateSquareMembersResponse> a(UpdateSquareMembersRequest updateSquareMembersRequest) {
        return this.i.a(updateSquareMembersRequest);
    }

    public final oyz<SquareGroupMemberDto> a(SquareGroupMemberDto squareGroupMemberDto) {
        squareGroupMemberDto = SquareGroupMemberDto.a(squareGroupMemberDto).a(SquareGroupMemberRole.MEMBER).a();
        return a(squareGroupMemberDto, new UpdateSquareMemberRequestBuilder(squareGroupMemberDto, SquareMemberAttribute.ROLE).a());
    }

    public final SquareGroupMemberDto b(String str) {
        return this.c.b(str);
    }

    public final SquareGroupMemberDto a(String str, long j) throws Exception {
        SquareGroupMemberDto squareGroupMemberDto = (SquareGroupMemberDto) this.d.a(str);
        if (squareGroupMemberDto != null) {
            return squareGroupMemberDto;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Exception[] exceptionArr = new Exception[]{null};
        SquareGroupMemberDto[] squareGroupMemberDtoArr = new SquareGroupMemberDto[]{null};
        this.j.a(str, false).a(new -$$Lambda$SquareGroupMemberBo$rzpOkG3RoXkRYI2eMHfjO9qOB_Y(squareGroupMemberDtoArr, countDownLatch), new -$$Lambda$SquareGroupMemberBo$aC3eldu7zzA9K9-YXinemqL5RGc(exceptionArr, countDownLatch));
        countDownLatch.await(j, TimeUnit.MILLISECONDS);
        if (squareGroupMemberDtoArr[0] != null) {
            return squareGroupMemberDtoArr[0];
        }
        if (exceptionArr[0] != null) {
            throw exceptionArr[0];
        }
        throw new Exception(HttpHeaders.TIMEOUT);
    }

    private static /* synthetic */ void a(SquareGroupMemberDto[] squareGroupMemberDtoArr, CountDownLatch countDownLatch, SquareGroupMemberDto squareGroupMemberDto) {
        squareGroupMemberDtoArr[0] = squareGroupMemberDto;
        countDownLatch.countDown();
    }

    private static /* synthetic */ void a(Exception[] exceptionArr, CountDownLatch countDownLatch, Throwable th) {
        exceptionArr[0] = (Exception) th;
        countDownLatch.countDown();
    }

    public final void a(SquareMember squareMember) {
        if (this.c.a(squareMember.a, SquareGroupMemberDto.a(squareMember, null)) == 0) {
            this.c.a(SquareGroupMemberDto.a(squareMember, null));
        }
    }

    public final void a(final String str, final Uri uri, final RequestCallback<ObjectInfo, Throwable> requestCallback) {
        adxc.a(new adxd<ObjectInfo>(this) {
            final /* synthetic */ SquareGroupMemberBo c;

            public /* synthetic */ void call(Object obj) {
                adxm adxm = (adxm) obj;
                try {
                    adxm.a((Object) ObsUtils.a("member", str, uri.getPath()));
                    adxm.aY_();
                } catch (Throwable e) {
                    adxm.a(e);
                }
            }
        }).b(aefz.a(this.a.f())).a(adxq.a()).a(new adxf<ObjectInfo>(this) {
            final /* synthetic */ SquareGroupMemberBo b;

            public final void aY_() {
            }

            public final void a(Throwable th) {
                requestCallback.b(th);
            }
        });
    }

    public final oyc<SquareGroupMemberDto> c(String str) {
        return a(str).b(new -$$Lambda$SquareGroupMemberBo$9KWKgQFxMNbiDznYRoAnQ7JC0KQ());
    }

    private /* synthetic */ adnj b(SquareGroupMemberDto squareGroupMemberDto) throws Exception {
        Builder a = SquareGroupMemberDto.a(squareGroupMemberDto);
        long j = 0;
        if (squareGroupMemberDto.A() <= 0) {
            j = System.currentTimeMillis();
        }
        squareGroupMemberDto = a.a(j).a();
        return a(squareGroupMemberDto, new UpdateSquareMemberRequestBuilder(squareGroupMemberDto, SquareMemberAttribute.PREFERENCE).a(SquarePreferenceAttribute.FAVORITE).a()).i();
    }

    public final oyg<RecentlyJoinedSquareGroupMemberResponse> a() {
        return this.l.a();
    }

    public final oxw a(String str, String str2, ReportType reportType) {
        vkt vkt = this.n.a;
        if (vkt == null) {
            acry.a("squareNewServiceClient");
        }
        ReportSquareMemberRequest reportSquareMemberRequest = new ReportSquareMemberRequest(str, reportType);
        reportSquareMemberRequest.d = str2;
        return vkt.a(reportSquareMemberRequest).h().b(qaa.b());
    }

    public final adxj<SquareGroupMemberDto> d(String str) {
        return adxj.a(new -$$Lambda$SquareGroupMemberBo$b6hKpU3b8dpfWmPeJg_2aqo7nS0(this, str)).b(aefz.a(this.a.b()));
    }

    private /* synthetic */ SquareGroupMemberDto e(String str) throws Exception {
        SquareGroupDto c = SquareGroupDao.c(str);
        return (c == null || TextUtils.isEmpty(c.p())) ? null : b(c.p());
    }
}
