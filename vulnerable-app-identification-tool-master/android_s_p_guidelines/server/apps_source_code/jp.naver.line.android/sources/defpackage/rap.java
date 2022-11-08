package defpackage;

import android.widget.Toast;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.chathistory.ChatHistoryActivity;
import jp.naver.line.android.activity.chathistory.z;
import jp.naver.line.android.activity.grouphome.OneToOneHomeGuideActivity;
import jp.naver.line.android.model.h;
import jp.naver.myhome.android.activity.write.PostWriteActivity;
import jp.naver.myhome.android.activity.write.WriteParams;
import jp.naver.myhome.android.model.x;
import jp.naver.myhome.android.view.w;

/* renamed from: rap */
final class rap {
    private final ChatHistoryActivity a;

    rap(ChatHistoryActivity chatHistoryActivity) {
        this.a = chatHistoryActivity;
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onStartNoteRequest(qtv qtv) {
        WriteParams a = qtv.a();
        if (a == null) {
            z b = this.a.f().b();
            String l = b != null ? b.l() : null;
            h n = b != null ? b.n() : null;
            if (l != null && n != null) {
                switch (n) {
                    case GROUP:
                        this.a.startActivity(jdi.a(this.a, l, true, w.TAB_NOTE, x.TALKROOM, false));
                        return;
                    case SINGLE:
                        x xVar = x.TALKROOM;
                        try {
                            wjk e = wjh.e(l);
                            if (e != null) {
                                this.a.startActivity(jdi.a(this.a, l, e.c, w.TAB_NOTE, xVar, false));
                                return;
                            } else {
                                OneToOneHomeGuideActivity.a(this.a, l, null);
                                return;
                            }
                        } catch (Throwable e2) {
                            vzb.g.d((Object) "getHome()", e2);
                            Toast.makeText(this.a, R.string.err_temporary_problem_occured, 0).show();
                            break;
                        }
                }
                return;
            }
            return;
        }
        if (!this.a.isFinishing()) {
            PostWriteActivity.a(this.a, -1, null, a);
        }
    }
}
