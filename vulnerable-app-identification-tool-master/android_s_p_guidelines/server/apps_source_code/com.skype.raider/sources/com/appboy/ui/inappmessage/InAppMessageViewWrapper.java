package com.appboy.ui.inappmessage;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.appboy.b.a.h;
import com.appboy.e.b;
import com.appboy.e.d;
import com.appboy.e.m;
import com.appboy.e.n;
import com.appboy.f.c;
import com.appboy.ui.inappmessage.listeners.IInAppMessageViewLifecycleListener;
import com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener.DismissCallbacks;
import com.appboy.ui.inappmessage.listeners.TouchAwareSwipeDismissTouchListener;
import com.appboy.ui.inappmessage.listeners.TouchAwareSwipeDismissTouchListener.ITouchListener;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageHtmlBaseView;
import com.appboy.ui.support.ViewUtils;
import java.util.List;

public class InAppMessageViewWrapper implements IInAppMessageViewWrapper {
    private static final String TAG = c.a(InAppMessageViewWrapper.class);
    private List<View> mButtons;
    private View mClickableInAppMessageView;
    private View mCloseButton;
    private final Animation mClosingAnimation;
    private Runnable mDismissRunnable;
    private final b mInAppMessage;
    private final View mInAppMessageView;
    private final IInAppMessageViewLifecycleListener mInAppMessageViewLifecycleListener;
    private boolean mIsAnimatingClose;
    private final Animation mOpeningAnimation;

    public InAppMessageViewWrapper(View inAppMessageView, b inAppMessage, IInAppMessageViewLifecycleListener inAppMessageViewLifecycleListener, Animation openingAnimation, Animation closingAnimation, View clickableInAppMessageView) {
        this.mInAppMessageView = inAppMessageView;
        this.mInAppMessage = inAppMessage;
        this.mInAppMessageViewLifecycleListener = inAppMessageViewLifecycleListener;
        this.mIsAnimatingClose = false;
        if (clickableInAppMessageView != null) {
            this.mClickableInAppMessageView = clickableInAppMessageView;
        } else {
            this.mClickableInAppMessageView = this.mInAppMessageView;
        }
        if (this.mInAppMessage instanceof m) {
            TouchAwareSwipeDismissTouchListener touchAwareSwipeListener = new TouchAwareSwipeDismissTouchListener(inAppMessageView, null, createDismissCallbacks());
            touchAwareSwipeListener.setTouchListener(createTouchAwareListener());
            this.mClickableInAppMessageView.setOnTouchListener(touchAwareSwipeListener);
        }
        this.mOpeningAnimation = openingAnimation;
        this.mClosingAnimation = closingAnimation;
        this.mClickableInAppMessageView.setOnClickListener(createClickListener());
    }

    public InAppMessageViewWrapper(View inAppMessageView, b inAppMessage, IInAppMessageViewLifecycleListener inAppMessageViewLifecycleListener, Animation openingAnimation, Animation closingAnimation, View clickableInAppMessageView, List<View> buttons, View closeButton) {
        this(inAppMessageView, inAppMessage, inAppMessageViewLifecycleListener, openingAnimation, closingAnimation, clickableInAppMessageView);
        if (closeButton != null) {
            this.mCloseButton = closeButton;
            this.mCloseButton.setOnClickListener(createCloseInAppMessageClickListener());
        }
        if (buttons != null) {
            this.mButtons = buttons;
            for (View onClickListener : this.mButtons) {
                onClickListener.setOnClickListener(createButtonClickListener());
            }
        }
    }

    public void open(Activity activity) {
        final FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView().findViewById(16908290);
        int frameLayoutHeight = frameLayout.getHeight();
        final int displayHeight = ViewUtils.getDisplayHeight(activity);
        if (frameLayoutHeight == 0) {
            ViewTreeObserver viewTreeObserver = frameLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        c.b(InAppMessageViewWrapper.TAG, "Detected root view height of " + frameLayout.getHeight() + ", display height of " + displayHeight + " in onGlobalLayout");
                        frameLayout.removeView(InAppMessageViewWrapper.this.mInAppMessageView);
                        InAppMessageViewWrapper.this.open(frameLayout, displayHeight);
                        ViewUtils.removeOnGlobalLayoutListenerSafe(frameLayout.getViewTreeObserver(), this);
                    }
                });
                return;
            }
            return;
        }
        c.b(TAG, "Detected root view height of " + frameLayoutHeight + ", display height of " + displayHeight);
        open(frameLayout, displayHeight);
    }

    private void open(FrameLayout frameLayout, int displayHeight) {
        this.mInAppMessageViewLifecycleListener.beforeOpened(this.mInAppMessageView, this.mInAppMessage);
        c.b(TAG, "Adding In-app message view to root FrameLayout.");
        frameLayout.addView(this.mInAppMessageView, getLayoutParams(frameLayout, displayHeight));
        if (this.mInAppMessage.l()) {
            c.b(TAG, "In-app message view will animate into the visible area.");
            setAndStartAnimation(true);
            return;
        }
        c.b(TAG, "In-app message view will be placed instantly into the visible area.");
        if (this.mInAppMessage.q() == com.appboy.b.a.c.AUTO_DISMISS) {
            addDismissRunnable();
        }
        ViewUtils.setFocusableInTouchModeAndRequestFocus(this.mInAppMessageView);
        announceForAccessibilityIfNecessary();
        this.mInAppMessageViewLifecycleListener.afterOpened(this.mInAppMessageView, this.mInAppMessage);
    }

    private void announceForAccessibilityIfNecessary() {
        if (VERSION.SDK_INT <= 16) {
            return;
        }
        if (this.mInAppMessageView instanceof IInAppMessageImmersiveView) {
            this.mInAppMessageView.announceForAccessibility(this.mInAppMessage.a());
        } else if (this.mInAppMessageView instanceof AppboyInAppMessageHtmlBaseView) {
            this.mInAppMessageView.announceForAccessibility("In-app message displayed.");
        }
    }

    private LayoutParams getLayoutParams(FrameLayout frameLayout, int displayHeight) {
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        if (this.mInAppMessage instanceof m) {
            layoutParams.gravity = ((m) this.mInAppMessage).E() == h.TOP ? 48 : 80;
        }
        if (displayHeight > 0 && displayHeight == frameLayout.getHeight()) {
            int topVisibleCoordinate = ViewUtils.getTopVisibleCoordinate(frameLayout);
            c.b(TAG, "Detected status bar height of " + topVisibleCoordinate + ".");
            layoutParams.setMargins(0, topVisibleCoordinate, 0, 0);
        }
        return layoutParams;
    }

    public void close() {
        this.mInAppMessageView.removeCallbacks(this.mDismissRunnable);
        this.mInAppMessageViewLifecycleListener.beforeClosed(this.mInAppMessageView, this.mInAppMessage);
        if (this.mInAppMessage.m()) {
            this.mIsAnimatingClose = true;
            setAndStartAnimation(false);
            return;
        }
        closeInAppMessageView();
    }

    public View getInAppMessageView() {
        return this.mInAppMessageView;
    }

    public b getInAppMessage() {
        return this.mInAppMessage;
    }

    public boolean getIsAnimatingClose() {
        return this.mIsAnimatingClose;
    }

    private OnClickListener createClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                if (InAppMessageViewWrapper.this.mInAppMessage instanceof d) {
                    d inAppMessageImmersive = (d) InAppMessageViewWrapper.this.mInAppMessage;
                    if (inAppMessageImmersive.E() == null || inAppMessageImmersive.E().size() == 0) {
                        InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
                        return;
                    }
                    return;
                }
                InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            }
        };
    }

    private OnClickListener createButtonClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                d inAppMessageImmersive = (d) InAppMessageViewWrapper.this.mInAppMessage;
                for (int i = 0; i < InAppMessageViewWrapper.this.mButtons.size(); i++) {
                    if (view.getId() == ((View) InAppMessageViewWrapper.this.mButtons.get(i)).getId()) {
                        InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onButtonClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), (n) inAppMessageImmersive.E().get(i), inAppMessageImmersive);
                        return;
                    }
                }
            }
        };
    }

    private OnClickListener createCloseInAppMessageClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
            }
        };
    }

    private void addDismissRunnable() {
        if (this.mDismissRunnable == null) {
            this.mDismissRunnable = new Runnable() {
                public void run() {
                    AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
                }
            };
            this.mInAppMessageView.postDelayed(this.mDismissRunnable, (long) this.mInAppMessage.c());
        }
    }

    private DismissCallbacks createDismissCallbacks() {
        return new DismissCallbacks() {
            public boolean canDismiss(Object token) {
                return true;
            }

            public void onDismiss(View view, Object token) {
                InAppMessageViewWrapper.this.mInAppMessage.a(false);
                AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
            }
        };
    }

    private ITouchListener createTouchAwareListener() {
        return new ITouchListener() {
            public void onTouchStartedOrContinued() {
                InAppMessageViewWrapper.this.mInAppMessageView.removeCallbacks(InAppMessageViewWrapper.this.mDismissRunnable);
            }

            public void onTouchEnded() {
                if (InAppMessageViewWrapper.this.mInAppMessage.q() == com.appboy.b.a.c.AUTO_DISMISS) {
                    InAppMessageViewWrapper.this.addDismissRunnable();
                }
            }
        };
    }

    private void setAndStartAnimation(boolean opening) {
        Animation animation;
        if (opening) {
            animation = this.mOpeningAnimation;
        } else {
            animation = this.mClosingAnimation;
        }
        animation.setAnimationListener(createAnimationListener(opening));
        this.mInAppMessageView.clearAnimation();
        this.mInAppMessageView.setAnimation(animation);
        animation.startNow();
        this.mInAppMessageView.invalidate();
    }

    private AnimationListener createAnimationListener(boolean opening) {
        return opening ? new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (InAppMessageViewWrapper.this.mInAppMessage.q() == com.appboy.b.a.c.AUTO_DISMISS) {
                    InAppMessageViewWrapper.this.addDismissRunnable();
                }
                c.b(InAppMessageViewWrapper.TAG, "In-app message animated into view.");
                ViewUtils.setFocusableInTouchModeAndRequestFocus(InAppMessageViewWrapper.this.mInAppMessageView);
                InAppMessageViewWrapper.this.announceForAccessibilityIfNecessary();
                InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.afterOpened(InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        } : new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                InAppMessageViewWrapper.this.mInAppMessageView.clearAnimation();
                InAppMessageViewWrapper.this.mInAppMessageView.setVisibility(8);
                InAppMessageViewWrapper.this.closeInAppMessageView();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        };
    }

    private void closeInAppMessageView() {
        c.b(TAG, "Closing in-app message view");
        ViewUtils.removeViewFromParent(this.mInAppMessageView);
        if (this.mInAppMessageView instanceof AppboyInAppMessageHtmlBaseView) {
            AppboyInAppMessageHtmlBaseView inAppMessageHtmlBaseView = this.mInAppMessageView;
            if (inAppMessageHtmlBaseView.getMessageWebView() != null) {
                c.b(TAG, "Called destroy on the AppboyInAppMessageHtmlBaseView WebView");
                inAppMessageHtmlBaseView.getMessageWebView().destroy();
            }
        }
        this.mInAppMessageViewLifecycleListener.afterClosed(this.mInAppMessage);
    }
}
