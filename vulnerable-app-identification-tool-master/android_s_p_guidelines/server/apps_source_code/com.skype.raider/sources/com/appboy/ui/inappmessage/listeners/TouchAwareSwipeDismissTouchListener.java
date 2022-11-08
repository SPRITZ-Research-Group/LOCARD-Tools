package com.appboy.ui.inappmessage.listeners;

import android.view.MotionEvent;
import android.view.View;
import com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener.DismissCallbacks;

public class TouchAwareSwipeDismissTouchListener extends SwipeDismissTouchListener {
    private ITouchListener mTouchListener;

    public interface ITouchListener {
        void onTouchEnded();

        void onTouchStartedOrContinued();
    }

    public TouchAwareSwipeDismissTouchListener(View view, Object token, DismissCallbacks callbacks) {
        super(view, token, callbacks);
    }

    public void setTouchListener(ITouchListener touchListener) {
        this.mTouchListener = touchListener;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.mTouchListener != null) {
                    this.mTouchListener.onTouchStartedOrContinued();
                    break;
                }
                break;
            case 1:
            case 3:
                if (this.mTouchListener != null) {
                    this.mTouchListener.onTouchEnded();
                    break;
                }
                break;
        }
        return super.onTouch(view, motionEvent);
    }
}
