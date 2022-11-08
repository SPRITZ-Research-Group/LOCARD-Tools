package jp.naver.line.android.bo;

import android.app.Activity;
import android.text.TextUtils;
import defpackage.svb;
import defpackage.tzi;
import defpackage.tzo;
import defpackage.vkx;
import java.util.List;
import jp.naver.line.android.db.main.model.am;
import jp.naver.line.android.util.at;
import jp.naver.line.android.util.au;
import jp.naver.myhome.android.api.g;

public final class al {
    public static void a(final Activity activity, final String str, final g<String> gVar) {
        au auVar = au.BASEACTIVITY;
        at.c().execute(new Runnable() {
            public final void run() {
                try {
                    final String e = vkx.a().e(str);
                    if (TextUtils.isEmpty(e)) {
                        throw new Exception("group invitation ticket is empty");
                    }
                    tzi.a(str, false, 0, true, e);
                    if (!(activity == null || gVar == null)) {
                        activity.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public final void run() {
                                gVar.a(e);
                            }
                        });
                    }
                } catch (final Exception e2) {
                    if (!(activity == null || gVar == null)) {
                        activity.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public final void run() {
                                gVar.a(e2);
                            }
                        });
                    }
                }
            }
        });
    }

    public static List<String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        tzo tzo = new tzo();
        return tzo.a(str, true, true);
    }

    public static boolean b(String str) {
        am c = tzi.c(str);
        return c != null && c.e() == svb.MEMBER;
    }

    public static boolean c(String str) {
        am c = tzi.c(str);
        return c != null && c.e() == svb.ON_INVITATION;
    }
}
