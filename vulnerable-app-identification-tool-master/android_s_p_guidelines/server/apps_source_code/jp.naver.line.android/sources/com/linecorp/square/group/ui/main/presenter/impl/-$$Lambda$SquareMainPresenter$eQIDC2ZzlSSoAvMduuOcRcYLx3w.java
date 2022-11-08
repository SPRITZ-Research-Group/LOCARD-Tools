package com.linecorp.square.group.ui.main.presenter.impl;

import com.linecorp.square.group.event.UpdateSquareGroupMemberEvent;
import java.util.concurrent.Callable;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$SquareMainPresenter$eQIDC2ZzlSSoAvMduuOcRcYLx3w implements Callable {
    private final /* synthetic */ SquareMainPresenter f$0;
    private final /* synthetic */ UpdateSquareGroupMemberEvent f$1;

    public /* synthetic */ -$$Lambda$SquareMainPresenter$eQIDC2ZzlSSoAvMduuOcRcYLx3w(SquareMainPresenter squareMainPresenter, UpdateSquareGroupMemberEvent updateSquareGroupMemberEvent) {
        this.f$0 = squareMainPresenter;
        this.f$1 = updateSquareGroupMemberEvent;
    }

    public final Object call() {
        return this.f$0.a(this.f$1);
    }
}
