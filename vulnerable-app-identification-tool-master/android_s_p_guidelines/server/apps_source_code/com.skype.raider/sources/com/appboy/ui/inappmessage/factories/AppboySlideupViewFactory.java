package com.appboy.ui.inappmessage.factories;

import android.app.Activity;
import android.content.Context;
import com.appboy.a;
import com.appboy.e.b;
import com.appboy.e.m;
import com.appboy.f.i;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageSlideupView;
import com.appboy.ui.support.FrescoLibraryUtils;

public class AppboySlideupViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageSlideupView createInAppMessageView(Activity activity, b inAppMessage) {
        Context applicationContext = activity.getApplicationContext();
        m inAppMessageSlideup = (m) inAppMessage;
        AppboyInAppMessageSlideupView view = (AppboyInAppMessageSlideupView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_slideup, null);
        view.inflateStubViews(inAppMessage);
        if (FrescoLibraryUtils.canUseFresco(applicationContext)) {
            view.setMessageSimpleDrawee(inAppMessage);
        } else {
            String imageUrl = view.getAppropriateImageUrl(inAppMessage);
            if (!i.b(imageUrl)) {
                a.a(applicationContext).g().a(applicationContext, imageUrl, view.getMessageImageView(), com.appboy.b.b.IN_APP_MESSAGE_SLIDEUP);
            }
        }
        view.setMessageBackgroundColor(inAppMessageSlideup.d());
        view.setMessage(inAppMessageSlideup.a());
        view.setMessageTextColor(inAppMessageSlideup.g());
        view.setMessageTextAlign(inAppMessageSlideup.G());
        view.setMessageIcon(inAppMessageSlideup.i(), inAppMessageSlideup.e(), inAppMessageSlideup.f());
        view.setMessageChevron(inAppMessageSlideup.F(), inAppMessageSlideup.n());
        view.resetMessageMargins(inAppMessage.r());
        return view;
    }
}
