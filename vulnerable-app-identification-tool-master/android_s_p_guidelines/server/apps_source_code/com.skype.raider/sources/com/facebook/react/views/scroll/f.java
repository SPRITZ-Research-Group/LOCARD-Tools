package com.facebook.react.views.scroll;

public enum f {
    BEGIN_DRAG("topScrollBeginDrag"),
    END_DRAG("topScrollEndDrag"),
    SCROLL("topScroll"),
    MOMENTUM_BEGIN("topMomentumScrollBegin"),
    MOMENTUM_END("topMomentumScrollEnd"),
    ANIMATION_END("topScrollAnimationEnd"),
    SCROLL_POSITION_SET("scrollPositionSet");
    
    private final String h;

    private f(String jsEventName) {
        this.h = jsEventName;
    }

    public final String a() {
        return this.h;
    }
}
