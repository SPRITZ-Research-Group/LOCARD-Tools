package com.appboy.ui.inappmessage.factories;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import com.appboy.a;
import com.appboy.b.a.d;
import com.appboy.e.b;
import com.appboy.e.l;
import com.appboy.f.i;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageModalView;
import com.appboy.ui.support.FrescoLibraryUtils;

public class AppboyModalViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageModalView createInAppMessageView(Activity activity, b inAppMessage) {
        Context applicationContext = activity.getApplicationContext();
        l inAppMessageModal = (l) inAppMessage;
        boolean isGraphic = inAppMessageModal.F().equals(d.GRAPHIC);
        AppboyInAppMessageModalView view = getAppropriateModalView(activity, isGraphic);
        view.inflateStubViews(activity, inAppMessageModal);
        if (FrescoLibraryUtils.canUseFresco(applicationContext)) {
            view.setMessageSimpleDrawee(inAppMessageModal, activity);
        } else {
            String imageUrl = view.getAppropriateImageUrl(inAppMessage);
            if (!i.b(imageUrl)) {
                a.a(applicationContext).g().a(applicationContext, imageUrl, view.getMessageImageView(), com.appboy.b.b.IN_APP_MESSAGE_MODAL);
            }
        }
        view.getFrameView().setOnClickListener(null);
        view.setMessageBackgroundColor(inAppMessage.d());
        view.setFrameColor(inAppMessageModal.L());
        view.setMessageButtons(inAppMessageModal.E());
        view.setMessageCloseButtonColor(inAppMessageModal.K());
        if (!isGraphic) {
            view.setMessage(inAppMessage.a());
            view.setMessageTextColor(inAppMessage.g());
            view.setMessageHeaderText(inAppMessageModal.I());
            view.setMessageHeaderTextColor(inAppMessageModal.J());
            view.setMessageIcon(inAppMessage.i(), inAppMessage.e(), inAppMessage.f());
            view.setMessageHeaderTextAlignment(inAppMessageModal.M());
            view.setMessageTextAlign(inAppMessageModal.G());
            view.resetMessageMargins(inAppMessage.r());
        }
        return view;
    }

    @SuppressLint({"InflateParams"})
    AppboyInAppMessageModalView getAppropriateModalView(Activity activity, boolean isGraphic) {
        if (isGraphic) {
            return (AppboyInAppMessageModalView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_modal_graphic, null);
        }
        return (AppboyInAppMessageModalView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_modal, null);
    }
}
