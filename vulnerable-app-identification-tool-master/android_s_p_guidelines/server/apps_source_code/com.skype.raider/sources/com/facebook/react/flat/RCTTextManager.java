package com.facebook.react.flat;

public final class RCTTextManager extends FlatViewManager {
    static final String REACT_CLASS = "RCTText";

    public final /* bridge */ /* synthetic */ void removeAllViews(t tVar) {
        super.removeAllViews(tVar);
    }

    public final /* bridge */ /* synthetic */ void setBackgroundColor(t tVar, int i) {
        super.setBackgroundColor(tVar, i);
    }

    public final String getName() {
        return "RCTText";
    }

    public final ab createShadowNodeInstance() {
        return new ab();
    }

    public final Class<ab> getShadowNodeClass() {
        return ab.class;
    }
}
