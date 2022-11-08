package com.linecorp.square.group.ui.settings.presenter.impl;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.List;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$SquareSettingsManageAuthorityPresenter$OGJankdZXK4PJBYuu5ewidQYfjA implements OnClickListener {
    private final /* synthetic */ SquareSettingsManageAuthorityPresenter f$0;
    private final /* synthetic */ List f$1;
    private final /* synthetic */ OnSelectRoleDialogListener f$2;

    public /* synthetic */ -$$Lambda$SquareSettingsManageAuthorityPresenter$OGJankdZXK4PJBYuu5ewidQYfjA(SquareSettingsManageAuthorityPresenter squareSettingsManageAuthorityPresenter, List list, OnSelectRoleDialogListener onSelectRoleDialogListener) {
        this.f$0 = squareSettingsManageAuthorityPresenter;
        this.f$1 = list;
        this.f$2 = onSelectRoleDialogListener;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f$0.a(this.f$1, this.f$2, dialogInterface, i);
    }
}
