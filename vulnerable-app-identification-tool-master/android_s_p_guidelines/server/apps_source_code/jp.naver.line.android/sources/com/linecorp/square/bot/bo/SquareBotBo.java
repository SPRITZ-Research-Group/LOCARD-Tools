package com.linecorp.square.bot.bo;

import com.linecorp.square.SquareExecutor;
import com.linecorp.square.bot.dao.SquareBotDao;
import com.linecorp.square.bot.db.model.SquareBotDto;
import defpackage.acry;
import defpackage.eoq;
import defpackage.eow;
import defpackage.oyg;
import defpackage.oym;
import defpackage.oyz;
import defpackage.pak;
import defpackage.qaa;
import defpackage.vks;
import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/linecorp/square/bot/bo/SquareBotBo;", "", "()V", "squareBotDao", "Lcom/linecorp/square/bot/dao/SquareBotDao;", "getSquareBotDao", "()Lcom/linecorp/square/bot/dao/SquareBotDao;", "setSquareBotDao", "(Lcom/linecorp/square/bot/dao/SquareBotDao;)V", "squareBotServiceClient", "Ljp/naver/line/android/thrift/client/SquareBotServiceClient;", "getSquareBotServiceClient", "()Ljp/naver/line/android/thrift/client/SquareBotServiceClient;", "setSquareBotServiceClient", "(Ljp/naver/line/android/thrift/client/SquareBotServiceClient;)V", "squareExecutor", "Lcom/linecorp/square/SquareExecutor;", "getSquareExecutor", "()Lcom/linecorp/square/SquareExecutor;", "setSquareExecutor", "(Lcom/linecorp/square/SquareExecutor;)V", "getFromServerAndSave", "Lio/reactivex/Single;", "Lcom/linecorp/square/bot/db/model/SquareBotDto;", "botMid", "", "getSquareBot", "Lio/reactivex/Maybe;", "getSquareBotFromLocal", "saveToDb", "squareBotResponse", "Lcom/linecorp/line/bot/plum/protocol/square/GetSquareBotResponse;", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class SquareBotBo {
    public SquareExecutor a;
    public vks b;
    public SquareBotDao c;

    public final SquareBotDao a() {
        SquareBotDao squareBotDao = this.c;
        if (squareBotDao == null) {
            acry.a("squareBotDao");
        }
        return squareBotDao;
    }

    public final oyg<SquareBotDto> a(String str) {
        oym b = b(str);
        vks vks = this.b;
        if (vks == null) {
            acry.a("squareBotServiceClient");
        }
        oyz a = vks.a(new eoq(str)).a((pak) new SquareBotBo$sam$io_reactivex_functions_Function$0(new SquareBotBo$getFromServerAndSave$1(this)));
        SquareExecutor squareExecutor = this.a;
        if (squareExecutor == null) {
            acry.a("squareExecutor");
        }
        return oyg.a(b, (oym) a.b(qaa.a(squareExecutor.c())).j()).f();
    }

    public final oyg<SquareBotDto> b(String str) {
        oyg b = oyg.b((Callable) new SquareBotBo$getSquareBotFromLocal$1(this, str));
        SquareExecutor squareExecutor = this.a;
        if (squareExecutor == null) {
            acry.a("squareExecutor");
        }
        return b.b(qaa.a(squareExecutor.b()));
    }

    public static final /* synthetic */ oyz a(SquareBotBo squareBotBo, eow eow) {
        oyz b = oyz.b((Callable) new SquareBotBo$saveToDb$1(squareBotBo, eow));
        SquareExecutor squareExecutor = squareBotBo.a;
        if (squareExecutor == null) {
            acry.a("squareExecutor");
        }
        return b.b(qaa.a(squareExecutor.a()));
    }
}
