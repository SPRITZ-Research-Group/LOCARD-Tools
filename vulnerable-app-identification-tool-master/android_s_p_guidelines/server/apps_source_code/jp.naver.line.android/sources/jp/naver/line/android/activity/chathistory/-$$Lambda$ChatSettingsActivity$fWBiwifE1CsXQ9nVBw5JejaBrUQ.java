package jp.naver.line.android.activity.chathistory;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.List;
import jp.naver.line.android.customview.settings.SettingButton;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$ChatSettingsActivity$fWBiwifE1CsXQ9nVBw5JejaBrUQ implements OnClickListener {
    private final /* synthetic */ ChatSettingsActivity f$0;
    private final /* synthetic */ SettingButton f$1;
    private final /* synthetic */ List f$2;

    public /* synthetic */ -$$Lambda$ChatSettingsActivity$fWBiwifE1CsXQ9nVBw5JejaBrUQ(ChatSettingsActivity chatSettingsActivity, SettingButton settingButton, List list) {
        this.f$0 = chatSettingsActivity;
        this.f$1 = settingButton;
        this.f$2 = list;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f$0.a(this.f$1, this.f$2, dialogInterface, i);
    }
}
