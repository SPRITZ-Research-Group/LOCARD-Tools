package com.facebook.react.modules.dialog;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.i;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.an;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.v;
import com.facebook.react.common.e;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "DialogManagerAndroid")
public class DialogModule extends ReactContextBaseJavaModule implements v {
    static final String ACTION_BUTTON_CLICKED = "buttonClicked";
    static final String ACTION_DISMISSED = "dismissed";
    static final Map<String, Object> CONSTANTS = e.a(ACTION_BUTTON_CLICKED, ACTION_BUTTON_CLICKED, ACTION_DISMISSED, ACTION_DISMISSED, KEY_BUTTON_POSITIVE, Integer.valueOf(-1), KEY_BUTTON_NEGATIVE, Integer.valueOf(-2), KEY_BUTTON_NEUTRAL, Integer.valueOf(-3));
    static final String FRAGMENT_TAG = "com.facebook.catalyst.react.dialog.DialogModule";
    static final String KEY_BUTTON_NEGATIVE = "buttonNegative";
    static final String KEY_BUTTON_NEUTRAL = "buttonNeutral";
    static final String KEY_BUTTON_POSITIVE = "buttonPositive";
    static final String KEY_CANCELABLE = "cancelable";
    static final String KEY_ITEMS = "items";
    static final String KEY_MESSAGE = "message";
    static final String KEY_TITLE = "title";
    static final String NAME = "DialogManagerAndroid";
    private boolean mIsInForeground;

    class a implements OnClickListener, OnDismissListener {
        final /* synthetic */ DialogModule a;
        private final d b;
        private boolean c = false;

        public a(DialogModule this$0, d callback) {
            this.a = this$0;
            this.b = callback;
        }

        public final void onClick(DialogInterface dialog, int which) {
            if (!this.c && this.a.getReactApplicationContext().b()) {
                this.b.invoke(DialogModule.ACTION_BUTTON_CLICKED, Integer.valueOf(which));
                this.c = true;
            }
        }

        public final void onDismiss(DialogInterface dialog) {
            if (!this.c && this.a.getReactApplicationContext().b()) {
                this.b.invoke(DialogModule.ACTION_DISMISSED);
                this.c = true;
            }
        }
    }

    private class b {
        final /* synthetic */ DialogModule a;
        @Nullable
        private final FragmentManager b;
        @Nullable
        private final i c;
        @Nullable
        private Object d;

        private boolean b() {
            return this.c != null;
        }

        public b(DialogModule dialogModule, i supportFragmentManager) {
            this.a = dialogModule;
            this.b = null;
            this.c = supportFragmentManager;
        }

        public b(DialogModule dialogModule, FragmentManager fragmentManager) {
            this.a = dialogModule;
            this.b = fragmentManager;
            this.c = null;
        }

        public final void a() {
            ap.b();
            an.a(this.a.mIsInForeground, "showPendingAlert() can't be sent in background");
            if (this.d != null) {
                c();
                if (b()) {
                    ((b) this.d).a(this.c, DialogModule.FRAGMENT_TAG);
                } else {
                    ((a) this.d).show(this.b, DialogModule.FRAGMENT_TAG);
                }
                this.d = null;
            }
        }

        private void c() {
            if (!this.a.mIsInForeground) {
                return;
            }
            if (b()) {
                b oldFragment = (b) this.c.a(DialogModule.FRAGMENT_TAG);
                if (oldFragment != null) {
                    oldFragment.a();
                    return;
                }
                return;
            }
            a oldFragment2 = (a) this.b.findFragmentByTag(DialogModule.FRAGMENT_TAG);
            if (oldFragment2 != null) {
                oldFragment2.dismiss();
            }
        }

        public final void a(Bundle arguments, d actionCallback) {
            ap.b();
            c();
            a actionListener = actionCallback != null ? new a(this.a, actionCallback) : null;
            if (b()) {
                b alertFragment = new b(actionListener, arguments);
                if (this.a.mIsInForeground) {
                    if (arguments.containsKey(DialogModule.KEY_CANCELABLE)) {
                        alertFragment.a(arguments.getBoolean(DialogModule.KEY_CANCELABLE));
                    }
                    alertFragment.a(this.c, DialogModule.FRAGMENT_TAG);
                    return;
                }
                this.d = alertFragment;
                return;
            }
            a alertFragment2 = new a(actionListener, arguments);
            if (this.a.mIsInForeground) {
                if (arguments.containsKey(DialogModule.KEY_CANCELABLE)) {
                    alertFragment2.setCancelable(arguments.getBoolean(DialogModule.KEY_CANCELABLE));
                }
                alertFragment2.show(this.b, DialogModule.FRAGMENT_TAG);
                return;
            }
            this.d = alertFragment2;
        }
    }

    public DialogModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return NAME;
    }

    public Map<String, Object> getConstants() {
        return CONSTANTS;
    }

    public void initialize() {
        getReactApplicationContext().a((v) this);
    }

    public void onHostPause() {
        this.mIsInForeground = false;
    }

    public void onHostDestroy() {
    }

    public void onHostResume() {
        this.mIsInForeground = true;
        b fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper != null) {
            fragmentManagerHelper.a();
        } else {
            FLog.w(DialogModule.class, "onHostResume called but no FragmentManager found");
        }
    }

    @ReactMethod
    public void showAlert(am options, d errorCallback, final d actionCallback) {
        final b fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper == null) {
            errorCallback.invoke("Tried to show an alert while not attached to an Activity");
            return;
        }
        final Bundle args = new Bundle();
        if (options.hasKey(KEY_TITLE)) {
            args.putString(KEY_TITLE, options.getString(KEY_TITLE));
        }
        if (options.hasKey(KEY_MESSAGE)) {
            args.putString(KEY_MESSAGE, options.getString(KEY_MESSAGE));
        }
        if (options.hasKey(KEY_BUTTON_POSITIVE)) {
            args.putString("button_positive", options.getString(KEY_BUTTON_POSITIVE));
        }
        if (options.hasKey(KEY_BUTTON_NEGATIVE)) {
            args.putString("button_negative", options.getString(KEY_BUTTON_NEGATIVE));
        }
        if (options.hasKey(KEY_BUTTON_NEUTRAL)) {
            args.putString("button_neutral", options.getString(KEY_BUTTON_NEUTRAL));
        }
        if (options.hasKey(KEY_ITEMS)) {
            al items = options.getArray(KEY_ITEMS);
            CharSequence[] itemsArray = new CharSequence[items.size()];
            for (int i = 0; i < items.size(); i++) {
                itemsArray[i] = items.getString(i);
            }
            args.putCharSequenceArray(KEY_ITEMS, itemsArray);
        }
        if (options.hasKey(KEY_CANCELABLE)) {
            args.putBoolean(KEY_CANCELABLE, options.getBoolean(KEY_CANCELABLE));
        }
        ap.a(new Runnable(this) {
            final /* synthetic */ DialogModule d;

            public final void run() {
                fragmentManagerHelper.a(args, actionCallback);
            }
        });
    }

    @Nullable
    private b getFragmentManagerHelper() {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            return null;
        }
        if (activity instanceof FragmentActivity) {
            return new b(this, ((FragmentActivity) activity).getSupportFragmentManager());
        }
        return new b(this, activity.getFragmentManager());
    }
}
