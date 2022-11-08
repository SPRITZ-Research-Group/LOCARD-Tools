package com.appboy.ui.inappmessage;

import android.view.animation.Animation;
import com.appboy.e.b;

public interface IInAppMessageAnimationFactory {
    Animation getClosingAnimation(b bVar);

    Animation getOpeningAnimation(b bVar);
}
