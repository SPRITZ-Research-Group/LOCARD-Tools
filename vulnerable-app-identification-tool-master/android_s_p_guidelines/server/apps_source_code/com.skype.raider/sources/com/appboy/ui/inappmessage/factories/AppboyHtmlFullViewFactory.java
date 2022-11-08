package com.appboy.ui.inappmessage.factories;

import android.app.Activity;
import com.appboy.e.b;
import com.appboy.e.j;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;
import com.appboy.ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageHtmlFullView;

public class AppboyHtmlFullViewFactory implements IInAppMessageViewFactory {
    private IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener;

    public AppboyHtmlFullViewFactory(IInAppMessageWebViewClientListener inAppMessageWebViewClientListener) {
        this.mInAppMessageWebViewClientListener = inAppMessageWebViewClientListener;
    }

    public AppboyInAppMessageHtmlFullView createInAppMessageView(Activity activity, b inAppMessage) {
        AppboyInAppMessageHtmlFullView view = (AppboyInAppMessageHtmlFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_html_full, null);
        view.setWebViewContent(inAppMessage.a(), ((j) inAppMessage).E());
        view.setInAppMessageWebViewClient(new InAppMessageWebViewClient(activity.getApplicationContext(), inAppMessage, this.mInAppMessageWebViewClientListener));
        return view;
    }
}
