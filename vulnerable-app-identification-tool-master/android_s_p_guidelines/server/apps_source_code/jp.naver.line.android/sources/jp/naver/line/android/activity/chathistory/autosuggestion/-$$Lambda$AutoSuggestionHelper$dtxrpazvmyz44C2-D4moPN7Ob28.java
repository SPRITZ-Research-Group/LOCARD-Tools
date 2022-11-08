package jp.naver.line.android.activity.chathistory.autosuggestion;

import defpackage.rzk;
import jp.naver.line.android.stickershop.model.StickerInfo;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$AutoSuggestionHelper$dtxrpazvmyz44C2-D4moPN7Ob28 implements Runnable {
    private final /* synthetic */ AutoSuggestionHelper f$0;
    private final /* synthetic */ d f$1;
    private final /* synthetic */ StickerInfo f$2;
    private final /* synthetic */ rzk f$3;

    public /* synthetic */ -$$Lambda$AutoSuggestionHelper$dtxrpazvmyz44C2-D4moPN7Ob28(AutoSuggestionHelper autoSuggestionHelper, d dVar, StickerInfo stickerInfo, rzk rzk) {
        this.f$0 = autoSuggestionHelper;
        this.f$1 = dVar;
        this.f$2 = stickerInfo;
        this.f$3 = rzk;
    }

    public final void run() {
        this.f$0.a(this.f$1, this.f$2, this.f$3);
    }
}
