package jp.naver.line.android;

import com.google.android.exoplayer.util.MimeTypes;
import defpackage.acry;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ljp/naver/line/android/LineApplication$LineApplicationKeeper;", "", "()V", "application", "Ljp/naver/line/android/LineApplication;", "application$annotations", "getApplication", "()Ljp/naver/line/android/LineApplication;", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class l {
    public static final l a = new l();

    private l() {
    }

    public static final LineApplication a() {
        LineApplication u = LineApplication.s;
        if (u == null) {
            acry.a(MimeTypes.BASE_TYPE_APPLICATION);
        }
        return u;
    }
}
