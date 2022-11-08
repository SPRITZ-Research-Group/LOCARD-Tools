package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.appboy.b.a.i;
import com.appboy.e.n;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageImmersiveView;
import com.appboy.ui.support.ViewUtils;
import java.util.List;

public abstract class AppboyInAppMessageImmersiveBaseView extends AppboyInAppMessageBaseView implements IInAppMessageImmersiveView {
    public abstract View getFrameView();

    public abstract List<View> getMessageButtonViews();

    public abstract View getMessageButtonsView();

    public abstract TextView getMessageHeaderTextView();

    public abstract TextView getMessageTextView();

    public AppboyInAppMessageImmersiveBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMessageButtons(List<n> messageButtons) {
        InAppMessageViewUtils.setButtons(getMessageButtonViews(), getMessageButtonsView(), getContext().getResources().getColor(R.color.com_appboy_inappmessage_button_bg_light), messageButtons);
        InAppMessageViewUtils.resetButtonSizesIfNecessary(getMessageButtonViews(), messageButtons);
    }

    public void setMessageCloseButtonColor(int color) {
        InAppMessageViewUtils.setViewBackgroundColorFilter(getMessageCloseButtonView(), color, getContext().getResources().getColor(R.color.com_appboy_inappmessage_button_close_light));
    }

    public void setMessageHeaderTextColor(int color) {
        InAppMessageViewUtils.setTextViewColor(getMessageHeaderTextView(), color);
    }

    public void setMessageHeaderText(String text) {
        getMessageHeaderTextView().setText(text);
    }

    public void setMessageHeaderTextAlignment(i textAlign) {
        InAppMessageViewUtils.setTextAlignment(getMessageHeaderTextView(), textAlign);
    }

    public void setFrameColor(Integer color) {
        InAppMessageViewUtils.setFrameColor(getFrameView(), color);
    }

    public void resetMessageMargins(boolean imageRetrievalSuccessful) {
        super.resetMessageMargins(imageRetrievalSuccessful);
        if (com.appboy.f.i.c(getMessageTextView().getText().toString())) {
            ViewUtils.removeViewFromParent(getMessageTextView());
        }
        if (com.appboy.f.i.c(getMessageHeaderTextView().getText().toString())) {
            ViewUtils.removeViewFromParent(getMessageHeaderTextView());
        }
        InAppMessageViewUtils.resetMessageMarginsIfNecessary(getMessageTextView(), getMessageHeaderTextView());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        InAppMessageViewUtils.closeInAppMessageOnKeycodeBack();
        return true;
    }
}
