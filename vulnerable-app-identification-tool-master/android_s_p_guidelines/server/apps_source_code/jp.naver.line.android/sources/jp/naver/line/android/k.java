package jp.naver.line.android;

import android.content.Context;
import kotlin.Metadata;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\f8GX\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u00048FX\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Ljp/naver/line/android/LineApplication$Companion;", "", "()V", "TAG", "", "application", "Ljp/naver/line/android/LineApplication;", "lineInitializationManager", "Ljp/naver/line/android/initialization/LineInitializationManager;", "getLineInitializationManager", "()Ljp/naver/line/android/initialization/LineInitializationManager;", "versionCode", "", "versionCode$annotations", "getVersionCode", "()I", "versionName", "versionName$annotations", "getVersionName", "()Ljava/lang/String;", "from", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class k {
    private k() {
    }

    public /* synthetic */ k(byte b) {
        this();
    }

    public static LineApplication a(Context context) {
        context.getApplicationContext();
        context = context.getApplicationContext();
        if (context != null) {
            return (LineApplication) context;
        }
        throw new v("null cannot be cast to non-null type jp.naver.line.android.LineApplication");
    }
}
