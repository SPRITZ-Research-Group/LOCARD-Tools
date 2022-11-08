package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import jp.naver.line.android.activity.chathistory.ChatHistoryActivity;
import jp.naver.line.android.activity.chathistory.z;
import jp.naver.line.android.model.h;

/* renamed from: rar */
final class rar {
    private final ChatHistoryActivity a;

    rar(ChatHistoryActivity chatHistoryActivity) {
        this.a = chatHistoryActivity;
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onVoipButtonClicked(qtw qtw) {
        CharSequence a = a();
        h b = b();
        if (!TextUtils.isEmpty(a) && b != h.SQUARE_GROUP) {
            boolean z = false;
            String a2;
            if (qtw == qtw.VIDEO_CALL || qtw == qtw.VOICE_CALL) {
                if (b() == h.SINGLE) {
                    a2 = a();
                    if (a2 != null) {
                        Context context = this.a;
                        if (qtw == qtw.VIDEO_CALL) {
                            z = true;
                        }
                        sua.a(context, a2, z);
                    }
                }
                return;
            }
            if (qtw == qtw.CHAT_LIVE_CASTER && b() != h.SINGLE) {
                a2 = a();
                if (a2 != null) {
                    ChatHistoryActivity chatHistoryActivity = this.a;
                    if (qtw == qtw.CHAT_LIVE_CASTER) {
                        z = true;
                    }
                    six.a(chatHistoryActivity, a2, z);
                }
            }
        }
    }

    private String a() {
        z b = this.a.f().b();
        if (b == null) {
            return null;
        }
        return b.l();
    }

    private h b() {
        z b = this.a.f().b();
        if (b == null) {
            return null;
        }
        return b.n();
    }
}
