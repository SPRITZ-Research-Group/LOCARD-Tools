package com.linecorp.square.group.ui.main.presenter.impl;

import com.linecorp.square.group.event.UpdateSquareGroupEvent;
import java.util.concurrent.Callable;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$SquareMainPresenter$CjDnGsLE0JZf9Si3tj-DCmvt2Do implements Callable {
    private final /* synthetic */ SquareMainPresenter f$0;
    private final /* synthetic */ UpdateSquareGroupEvent f$1;

    public /* synthetic */ -$$Lambda$SquareMainPresenter$CjDnGsLE0JZf9Si3tj-DCmvt2Do(SquareMainPresenter squareMainPresenter, UpdateSquareGroupEvent updateSquareGroupEvent) {
        this.f$0 = squareMainPresenter;
        this.f$1 = updateSquareGroupEvent;
    }

    public final Object call() {
        return this.f$0.a(this.f$1);
    }
}
