package jp.naver.line.android.activity.main;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import jp.naver.line.android.common.view.listview.PopupListView;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$x$cdaicpzjAoiOPF-V7v0-rDMpPmY implements OnItemClickListener {
    private final /* synthetic */ OnItemClickListener f$0;
    private final /* synthetic */ PopupListView f$1;

    public /* synthetic */ -$$Lambda$x$cdaicpzjAoiOPF-V7v0-rDMpPmY(OnItemClickListener onItemClickListener, PopupListView popupListView) {
        this.f$0 = onItemClickListener;
        this.f$1 = popupListView;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        x.a(this.f$0, this.f$1, adapterView, view, i, j);
    }
}
