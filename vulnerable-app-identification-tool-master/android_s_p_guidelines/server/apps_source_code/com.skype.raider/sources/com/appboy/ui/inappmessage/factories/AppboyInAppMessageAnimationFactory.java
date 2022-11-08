package com.appboy.ui.inappmessage.factories;

import android.content.res.Resources;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.appboy.b.a.h;
import com.appboy.e.b;
import com.appboy.e.m;
import com.appboy.ui.inappmessage.IInAppMessageAnimationFactory;
import com.appboy.ui.support.AnimationUtils;

public class AppboyInAppMessageAnimationFactory implements IInAppMessageAnimationFactory {
    private final int mShortAnimationDurationMillis = Resources.getSystem().getInteger(17694720);

    public Animation getOpeningAnimation(b inAppMessage) {
        if (!(inAppMessage instanceof m)) {
            return AnimationUtils.setAnimationParams(new AlphaAnimation(0.0f, 1.0f), (long) this.mShortAnimationDurationMillis, true);
        }
        if (((m) inAppMessage).E() == h.TOP) {
            return AnimationUtils.createVerticalAnimation(-1.0f, 0.0f, (long) this.mShortAnimationDurationMillis, false);
        }
        return AnimationUtils.createVerticalAnimation(1.0f, 0.0f, (long) this.mShortAnimationDurationMillis, false);
    }

    public Animation getClosingAnimation(b inAppMessage) {
        if (!(inAppMessage instanceof m)) {
            return AnimationUtils.setAnimationParams(new AlphaAnimation(1.0f, 0.0f), (long) this.mShortAnimationDurationMillis, false);
        }
        if (((m) inAppMessage).E() == h.TOP) {
            return AnimationUtils.createVerticalAnimation(0.0f, -1.0f, (long) this.mShortAnimationDurationMillis, false);
        }
        return AnimationUtils.createVerticalAnimation(0.0f, 1.0f, (long) this.mShortAnimationDurationMillis, false);
    }
}
