package defpackage;

import android.view.View;
import android.view.ViewStub;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.widget.stickersticoninput.sticker.PopupStickerView;
import com.linecorp.widget.stickersticoninput.sticker.StickerView;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.chathistory.ChatHistoryActivity;
import jp.naver.line.android.activity.chathistory.messageinput.x;

/* renamed from: ras */
public final class ras {
    private final ChatHistoryActivity a;
    private final View b;
    private PopupStickerView c;

    public ras(ChatHistoryActivity chatHistoryActivity, View view) {
        this.a = chatHistoryActivity;
        this.b = view;
    }

    public final void a() {
        c();
    }

    public final boolean b() {
        return c();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onRequestShowPopupSticker(rav rav) {
        if (this.c == null) {
            this.c = (PopupStickerView) ((ViewStub) this.b.findViewById(R.id.chathistory_popup_sticker_viewstub)).inflate();
        }
        StickerView k;
        if (mnt.a(this.c)) {
            k = rav.b.k();
            if (k != null) {
                k.a(rav.c);
            }
            return;
        }
        k = rav.b.k();
        if (k != null) {
            k.a(true, rav.c);
        }
        this.c.a(rav.g, new rat(this, rav));
        if (rav.d) {
            x P = this.a.P();
            if (P != null) {
                P.h();
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onRequestClosePopupSticker(rau rau) {
        c();
    }

    private boolean c() {
        return this.c != null && this.c.b();
    }
}
