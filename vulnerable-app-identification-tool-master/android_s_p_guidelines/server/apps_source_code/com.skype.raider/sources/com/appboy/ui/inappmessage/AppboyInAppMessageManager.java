package com.appboy.ui.inappmessage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import com.appboy.a;
import com.appboy.b.a.e;
import com.appboy.b.a.g;
import com.appboy.e.b;
import com.appboy.e.h;
import com.appboy.e.j;
import com.appboy.e.l;
import com.appboy.e.m;
import com.appboy.f.c;
import com.appboy.ui.inappmessage.factories.AppboyFullViewFactory;
import com.appboy.ui.inappmessage.factories.AppboyHtmlFullViewFactory;
import com.appboy.ui.inappmessage.factories.AppboyInAppMessageAnimationFactory;
import com.appboy.ui.inappmessage.factories.AppboyModalViewFactory;
import com.appboy.ui.inappmessage.factories.AppboySlideupViewFactory;
import com.appboy.ui.inappmessage.listeners.AppboyDefaultHtmlInAppMessageActionListener;
import com.appboy.ui.inappmessage.listeners.AppboyDefaultInAppMessageManagerListener;
import com.appboy.ui.inappmessage.listeners.AppboyInAppMessageViewLifecycleListener;
import com.appboy.ui.inappmessage.listeners.AppboyInAppMessageWebViewClientListener;
import com.appboy.ui.inappmessage.listeners.IHtmlInAppMessageActionListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageManagerListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageViewLifecycleListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.ui.support.ViewUtils;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressLint({"StaticFieldLeak"})
public final class AppboyInAppMessageManager {
    private static final String TAG = c.a(AppboyInAppMessageManager.class);
    private static volatile AppboyInAppMessageManager sInstance = null;
    private Activity mActivity;
    private Context mApplicationContext;
    private b mCarryoverInAppMessage;
    private IInAppMessageManagerListener mCustomControlInAppMessageManagerListener;
    private IHtmlInAppMessageActionListener mCustomHtmlInAppMessageActionListener;
    private IInAppMessageAnimationFactory mCustomInAppMessageAnimationFactory;
    private IInAppMessageManagerListener mCustomInAppMessageManagerListener;
    private IInAppMessageViewFactory mCustomInAppMessageViewFactory;
    private IHtmlInAppMessageActionListener mDefaultHtmlInAppMessageActionListener = new AppboyDefaultHtmlInAppMessageActionListener();
    private IInAppMessageManagerListener mDefaultInAppMessageManagerListener = new AppboyDefaultInAppMessageManagerListener();
    private AtomicBoolean mDisplayingInAppMessage = new AtomicBoolean(false);
    private IInAppMessageAnimationFactory mInAppMessageAnimationFactory = new AppboyInAppMessageAnimationFactory();
    private com.appboy.c.b<com.appboy.c.c> mInAppMessageEventSubscriber;
    private IInAppMessageViewFactory mInAppMessageFullViewFactory = new AppboyFullViewFactory();
    private IInAppMessageViewFactory mInAppMessageHtmlFullViewFactory = new AppboyHtmlFullViewFactory(this.mInAppMessageWebViewClientListener);
    private IInAppMessageViewFactory mInAppMessageModalViewFactory = new AppboyModalViewFactory();
    private IInAppMessageViewFactory mInAppMessageSlideupViewFactory = new AppboySlideupViewFactory();
    private final Stack<b> mInAppMessageStack = new Stack();
    private final IInAppMessageViewLifecycleListener mInAppMessageViewLifecycleListener = new AppboyInAppMessageViewLifecycleListener();
    private IInAppMessageViewWrapper mInAppMessageViewWrapper;
    private final IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener = new AppboyInAppMessageWebViewClientListener();
    private Integer mOriginalOrientation;
    private b mUnRegisteredInAppMessage;

    public static AppboyInAppMessageManager getInstance() {
        if (sInstance == null) {
            synchronized (AppboyInAppMessageManager.class) {
                if (sInstance == null) {
                    sInstance = new AppboyInAppMessageManager();
                }
            }
        }
        return sInstance;
    }

    public final void ensureSubscribedToInAppMessageEvents(Context context) {
        if (this.mInAppMessageEventSubscriber == null) {
            c.b(TAG, "Subscribing in-app message event subscriber");
            this.mInAppMessageEventSubscriber = createInAppMessageEventSubscriber();
            a.a(context).a(this.mInAppMessageEventSubscriber);
        }
    }

    public final void registerInAppMessageManager(Activity activity) {
        c.b(TAG, "registerInAppMessageManager called");
        this.mActivity = activity;
        if (this.mActivity != null && this.mApplicationContext == null) {
            this.mApplicationContext = this.mActivity.getApplicationContext();
        }
        if (this.mCarryoverInAppMessage != null) {
            c.b(TAG, "Requesting display of carryover in-app message.");
            this.mCarryoverInAppMessage.x();
            displayInAppMessage(this.mCarryoverInAppMessage, true);
            this.mCarryoverInAppMessage = null;
        } else if (this.mUnRegisteredInAppMessage != null) {
            c.b(TAG, "Adding previously unregistered in-app message.");
            addInAppMessage(this.mUnRegisteredInAppMessage);
            this.mUnRegisteredInAppMessage = null;
        }
        ensureSubscribedToInAppMessageEvents(this.mApplicationContext);
    }

    public final void unregisterInAppMessageManager(Activity activity) {
        c.b(TAG, "unregisterInAppMessageManager called");
        if (this.mInAppMessageViewWrapper != null) {
            ViewUtils.removeViewFromParent(this.mInAppMessageViewWrapper.getInAppMessageView());
            if (this.mInAppMessageViewWrapper.getIsAnimatingClose()) {
                this.mInAppMessageViewLifecycleListener.afterClosed(this.mInAppMessageViewWrapper.getInAppMessage());
                this.mCarryoverInAppMessage = null;
            } else {
                this.mCarryoverInAppMessage = this.mInAppMessageViewWrapper.getInAppMessage();
            }
            this.mInAppMessageViewWrapper = null;
        } else {
            this.mCarryoverInAppMessage = null;
        }
        this.mActivity = null;
        this.mDisplayingInAppMessage.set(false);
    }

    public final void addInAppMessage(b inAppMessage) {
        this.mInAppMessageStack.push(inAppMessage);
        requestDisplayInAppMessage();
    }

    public final boolean requestDisplayInAppMessage() {
        try {
            if (this.mActivity == null) {
                if (this.mInAppMessageStack.empty()) {
                    c.b(TAG, "No activity is currently registered to receive in-app messages and the in-app message stack is empty. Doing nothing.");
                } else {
                    c.f(TAG, "No activity is currently registered to receive in-app messages. Saving in-app message as unregistered in-app message. It will automatically be displayed when the next activity registers to receive in-app messages.");
                    this.mUnRegisteredInAppMessage = (b) this.mInAppMessageStack.pop();
                }
                return false;
            } else if (this.mDisplayingInAppMessage.get()) {
                c.b(TAG, "A in-app message is currently being displayed. Ignoring request to display in-app message.");
                return false;
            } else if (this.mInAppMessageStack.isEmpty()) {
                c.b(TAG, "The in-app message stack is empty. No in-app message will be displayed.");
                return false;
            } else {
                InAppMessageOperation inAppMessageOperation;
                final b inAppMessage = (b) this.mInAppMessageStack.pop();
                if (inAppMessage.D()) {
                    c.b(TAG, "Using the control in-app message manager listener.");
                    inAppMessageOperation = getControlInAppMessageManagerListener().beforeInAppMessageDisplayed(inAppMessage);
                } else {
                    inAppMessageOperation = getInAppMessageManagerListener().beforeInAppMessageDisplayed(inAppMessage);
                }
                switch (inAppMessageOperation) {
                    case DISPLAY_NOW:
                        c.b(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISPLAY_NOW. The in-app message will be displayed.");
                        new Handler(this.mApplicationContext.getMainLooper()).post(new Runnable() {
                            public void run() {
                                new AppboyAsyncInAppMessageDisplayer().execute(new b[]{inAppMessage});
                            }
                        });
                        return true;
                    case DISPLAY_LATER:
                        c.b(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISPLAY_LATER. The in-app message will be pushed back onto the stack.");
                        this.mInAppMessageStack.push(inAppMessage);
                        return false;
                    case DISCARD:
                        c.b(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISCARD. The in-app message will not be displayed and will not be put back on the stack.");
                        return false;
                    default:
                        c.g(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned null instead of a InAppMessageOperation. Ignoring the in-app message. Please check the IInAppMessageStackBehaviour implementation.");
                        return false;
                }
            }
        } catch (Exception e) {
            c.d(TAG, "Error running requestDisplayInAppMessage", e);
            return false;
        }
    }

    public final void hideCurrentlyDisplayingInAppMessage(boolean dismissed) {
        IInAppMessageViewWrapper inAppMessageWrapperView = this.mInAppMessageViewWrapper;
        if (inAppMessageWrapperView != null) {
            if (dismissed) {
                this.mInAppMessageViewLifecycleListener.onDismissed(inAppMessageWrapperView.getInAppMessageView(), inAppMessageWrapperView.getInAppMessage());
            }
            inAppMessageWrapperView.close();
        }
    }

    public final IInAppMessageManagerListener getInAppMessageManagerListener() {
        return this.mCustomInAppMessageManagerListener != null ? this.mCustomInAppMessageManagerListener : this.mDefaultInAppMessageManagerListener;
    }

    public final IInAppMessageManagerListener getControlInAppMessageManagerListener() {
        return this.mCustomControlInAppMessageManagerListener != null ? this.mCustomControlInAppMessageManagerListener : this.mDefaultInAppMessageManagerListener;
    }

    public final IHtmlInAppMessageActionListener getHtmlInAppMessageActionListener() {
        return this.mCustomHtmlInAppMessageActionListener != null ? this.mCustomHtmlInAppMessageActionListener : this.mDefaultHtmlInAppMessageActionListener;
    }

    public final Activity getActivity() {
        return this.mActivity;
    }

    public final Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public final void resetAfterInAppMessageClose() {
        c.a(TAG, "Resetting after in-app message close.");
        this.mInAppMessageViewWrapper = null;
        this.mDisplayingInAppMessage.set(false);
        if (this.mActivity != null && this.mOriginalOrientation != null) {
            c.b(TAG, "Setting requested orientation to original orientation " + this.mOriginalOrientation);
            ViewUtils.setActivityRequestedOrientation(this.mActivity, this.mOriginalOrientation.intValue());
            this.mOriginalOrientation = null;
        }
    }

    private IInAppMessageAnimationFactory getInAppMessageAnimationFactory() {
        return this.mCustomInAppMessageAnimationFactory != null ? this.mCustomInAppMessageAnimationFactory : this.mInAppMessageAnimationFactory;
    }

    private IInAppMessageViewFactory getInAppMessageViewFactory(b inAppMessage) {
        if (this.mCustomInAppMessageViewFactory != null) {
            return this.mCustomInAppMessageViewFactory;
        }
        if (inAppMessage instanceof m) {
            return this.mInAppMessageSlideupViewFactory;
        }
        if (inAppMessage instanceof l) {
            return this.mInAppMessageModalViewFactory;
        }
        if (inAppMessage instanceof h) {
            return this.mInAppMessageFullViewFactory;
        }
        if (inAppMessage instanceof j) {
            return this.mInAppMessageHtmlFullViewFactory;
        }
        return null;
    }

    final boolean displayInAppMessage(b inAppMessage, boolean isCarryOver) {
        if (this.mDisplayingInAppMessage.compareAndSet(false, true)) {
            try {
                if (this.mActivity == null) {
                    this.mCarryoverInAppMessage = inAppMessage;
                    throw new Exception("No activity is currently registered to receive in-app messages. Registering in-app message as carry-over in-app message. It will automatically be displayed when the next activity registers to receive in-app messages.");
                }
                if (isCarryOver) {
                    c.b(TAG, "Not checking expiration status for carry-over in-app message.");
                } else {
                    long inAppMessageExpirationTimestamp = inAppMessage.v();
                    if (inAppMessageExpirationTimestamp > 0) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis > inAppMessageExpirationTimestamp) {
                            throw new Exception("In-app message is expired. Doing nothing. Expiration: $" + inAppMessageExpirationTimestamp + ". Current time: " + currentTimeMillis);
                        }
                    }
                    c.b(TAG, "Expiration timestamp not defined. Continuing.");
                }
                if (!verifyOrientationStatus(inAppMessage)) {
                    throw new Exception("Current orientation did not match specified orientation for in-app message. Doing nothing.");
                } else if (inAppMessage.D()) {
                    c.b(TAG, "Not displaying control in-app message. Logging impression and ending display execution.");
                    inAppMessage.A();
                    resetAfterInAppMessageClose();
                    return true;
                } else {
                    IInAppMessageViewFactory inAppMessageViewFactory = getInAppMessageViewFactory(inAppMessage);
                    if (inAppMessageViewFactory == null) {
                        inAppMessage.a(e.DISPLAY_VIEW_GENERATION);
                        throw new Exception("ViewFactory from getInAppMessageViewFactory was null.");
                    }
                    View inAppMessageView = inAppMessageViewFactory.createInAppMessageView(this.mActivity, inAppMessage);
                    if (inAppMessageView == null) {
                        inAppMessage.a(e.DISPLAY_VIEW_GENERATION);
                        throw new Exception("The in-app message view returned from the IInAppMessageViewFactory was null. The in-app message will not be displayed and will not be put back on the stack.");
                    } else if (inAppMessageView.getParent() != null) {
                        inAppMessage.a(e.DISPLAY_VIEW_GENERATION);
                        throw new Exception("The in-app message view returned from the IInAppMessageViewFactory already has a parent. This is a sign that the view is being reused. The IInAppMessageViewFactory method createInAppMessageViewmust return a new view without a parent. The in-app message will not be displayed and will not be put back on the stack.");
                    } else {
                        Animation openingAnimation = getInAppMessageAnimationFactory().getOpeningAnimation(inAppMessage);
                        Animation closingAnimation = getInAppMessageAnimationFactory().getClosingAnimation(inAppMessage);
                        if (inAppMessageView instanceof IInAppMessageImmersiveView) {
                            c.b(TAG, "Creating view wrapper for immersive in-app message.");
                            IInAppMessageImmersiveView inAppMessageViewImmersive = (IInAppMessageImmersiveView) inAppMessageView;
                            this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(inAppMessageView, inAppMessage, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, inAppMessageViewImmersive.getMessageClickableView(), inAppMessageViewImmersive.getMessageButtonViews(), inAppMessageViewImmersive.getMessageCloseButtonView());
                        } else if (inAppMessageView instanceof IInAppMessageView) {
                            c.b(TAG, "Creating view wrapper for base in-app message.");
                            b bVar = inAppMessage;
                            this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(inAppMessageView, bVar, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, ((IInAppMessageView) inAppMessageView).getMessageClickableView());
                        } else {
                            c.b(TAG, "Creating view wrapper for in-app message.");
                            this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(inAppMessageView, inAppMessage, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, inAppMessageView);
                        }
                        this.mInAppMessageViewWrapper.open(this.mActivity);
                        return true;
                    }
                }
            } catch (Exception e) {
                c.d(TAG, "Could not display in-app message", e);
                resetAfterInAppMessageClose();
                return false;
            }
        }
        c.b(TAG, "A in-app message is currently being displayed. Adding in-app message back on the stack.");
        this.mInAppMessageStack.push(inAppMessage);
        return false;
    }

    @SuppressLint({"InlinedApi"})
    final boolean verifyOrientationStatus(b inAppMessage) {
        if (ViewUtils.isRunningOnTablet(this.mActivity)) {
            c.b(TAG, "Running on tablet. In-app message can be displayed in any orientation.");
            return true;
        }
        g preferredOrientation = inAppMessage.t();
        if (preferredOrientation == null) {
            c.b(TAG, "No orientation specified. In-app message can be displayed in any orientation.");
            return true;
        } else if (preferredOrientation == g.ANY) {
            c.b(TAG, "Any orientation specified. In-app message can be displayed in any orientation.");
            return true;
        } else if (!currentOrientationIsValid(this.mActivity.getResources().getConfiguration().orientation, preferredOrientation)) {
            return false;
        } else {
            if (this.mOriginalOrientation != null) {
                return true;
            }
            c.b(TAG, "Requesting orientation lock.");
            this.mOriginalOrientation = Integer.valueOf(this.mActivity.getRequestedOrientation());
            ViewUtils.setActivityRequestedOrientation(this.mActivity, 14);
            return true;
        }
    }

    private boolean currentOrientationIsValid(int currentScreenOrientation, g preferredOrientation) {
        if (currentScreenOrientation == 2 && preferredOrientation == g.LANDSCAPE) {
            c.b(TAG, "Current and preferred orientation are landscape.");
            return true;
        } else if (currentScreenOrientation == 1 && preferredOrientation == g.PORTRAIT) {
            c.b(TAG, "Current and preferred orientation are portrait.");
            return true;
        } else {
            c.b(TAG, "Current orientation " + currentScreenOrientation + " and preferred orientation " + preferredOrientation + " don't match");
            return false;
        }
    }

    private com.appboy.c.b<com.appboy.c.c> createInAppMessageEventSubscriber() {
        return new com.appboy.c.b<com.appboy.c.c>() {
            public void trigger(com.appboy.c.c event) {
                if (!AppboyInAppMessageManager.this.getInAppMessageManagerListener().onInAppMessageReceived(event.a())) {
                    AppboyInAppMessageManager.this.addInAppMessage(event.a());
                }
            }
        };
    }
}
