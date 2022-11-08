package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.appboy.ui.inappmessage.IInAppMessageView;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;

public abstract class AppboyInAppMessageHtmlBaseView extends RelativeLayout implements IInAppMessageView {
    public abstract WebView getMessageWebView();

    public AppboyInAppMessageHtmlBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public View getMessageClickableView() {
        return this;
    }

    public void setWebViewContent(String htmlBody, String assetDirectoryUrl) {
        getMessageWebView().loadDataWithBaseURL("file://" + assetDirectoryUrl + "/", htmlBody, "text/html", "utf-8", null);
    }

    public void setInAppMessageWebViewClient(InAppMessageWebViewClient inAppMessageWebViewClient) {
        getMessageWebView().setWebViewClient(inAppMessageWebViewClient);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        InAppMessageViewUtils.closeInAppMessageOnKeycodeBack();
        return true;
    }
}
