package com.appboy.ui.inappmessage.factories;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.RelativeLayout.LayoutParams;
import com.appboy.a;
import com.appboy.b.a.d;
import com.appboy.b.a.g;
import com.appboy.e.b;
import com.appboy.e.h;
import com.appboy.f.i;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageFullView;
import com.appboy.ui.support.FrescoLibraryUtils;
import com.appboy.ui.support.ViewUtils;

public class AppboyFullViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageFullView createInAppMessageView(Activity activity, b inAppMessage) {
        Context applicationContext = activity.getApplicationContext();
        h inAppMessageFull = (h) inAppMessage;
        boolean isGraphic = inAppMessageFull.F().equals(d.GRAPHIC);
        AppboyInAppMessageFullView view = getAppropriateFullView(activity, isGraphic);
        view.inflateStubViews(activity, inAppMessageFull);
        if (FrescoLibraryUtils.canUseFresco(applicationContext)) {
            view.setMessageSimpleDrawee(inAppMessageFull);
        } else {
            String imageUrl = view.getAppropriateImageUrl(inAppMessage);
            if (!i.b(imageUrl)) {
                a.a(applicationContext).g().a(applicationContext, imageUrl, view.getMessageImageView(), com.appboy.b.b.NO_BOUNDS);
            }
        }
        view.getFrameView().setOnClickListener(null);
        view.setMessageBackgroundColor(inAppMessageFull.d());
        view.setFrameColor(inAppMessageFull.L());
        view.setMessageButtons(inAppMessageFull.E());
        view.setMessageCloseButtonColor(inAppMessageFull.K());
        if (!isGraphic) {
            view.setMessage(inAppMessageFull.a());
            view.setMessageTextColor(inAppMessageFull.g());
            view.setMessageHeaderText(inAppMessageFull.I());
            view.setMessageHeaderTextColor(inAppMessageFull.J());
            view.setMessageHeaderTextAlignment(inAppMessageFull.M());
            view.setMessageTextAlign(inAppMessageFull.G());
            view.resetMessageMargins(inAppMessageFull.r());
        }
        resetLayoutParamsIfAppropriate(activity, inAppMessageFull, view);
        return view;
    }

    boolean resetLayoutParamsIfAppropriate(Activity activity, b inAppMessage, AppboyInAppMessageFullView view) {
        if (!ViewUtils.isRunningOnTablet(activity) || inAppMessage.t() == null || inAppMessage.t() == g.ANY) {
            return false;
        }
        int longEdge = view.getLongEdge();
        int shortEdge = view.getShortEdge();
        if (longEdge <= 0 || shortEdge <= 0) {
            return false;
        }
        LayoutParams layoutParams;
        if (inAppMessage.t() == g.LANDSCAPE) {
            layoutParams = new LayoutParams(longEdge, shortEdge);
        } else {
            layoutParams = new LayoutParams(shortEdge, longEdge);
        }
        layoutParams.addRule(13, -1);
        view.getMessageBackgroundObject().setLayoutParams(layoutParams);
        return true;
    }

    @SuppressLint({"InflateParams"})
    AppboyInAppMessageFullView getAppropriateFullView(Activity activity, boolean isGraphic) {
        if (isGraphic) {
            return (AppboyInAppMessageFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_full_graphic, null);
        }
        return (AppboyInAppMessageFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_full, null);
    }
}
