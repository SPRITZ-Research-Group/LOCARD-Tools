package com.facebook.react.modules.statusbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.l;
import com.facebook.react.common.e;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.p;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "StatusBarManager")
public class StatusBarModule extends ReactContextBaseJavaModule {
    private static final String HEIGHT_KEY = "HEIGHT";

    public StatusBarModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "StatusBarManager";
    }

    @Nullable
    public Map<String, Object> getConstants() {
        Context context = getReactApplicationContext();
        int heightResId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return e.a(HEIGHT_KEY, Float.valueOf(heightResId > 0 ? p.b((float) context.getResources().getDimensionPixelSize(heightResId)) : 0.0f));
    }

    @ReactMethod
    public void setColor(int color, boolean animated) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.w("React", "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 21) {
            final boolean z = animated;
            final int i = color;
            ap.a(new l(this, getReactApplicationContext()) {
                final /* synthetic */ StatusBarModule d;

                @TargetApi(21)
                public final void a() {
                    if (z) {
                        int curColor = activity.getWindow().getStatusBarColor();
                        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(curColor), Integer.valueOf(i)});
                        colorAnimation.addUpdateListener(new AnimatorUpdateListener(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = this$1;
                            }

                            public final void onAnimationUpdate(ValueAnimator animator) {
                                activity.getWindow().setStatusBarColor(((Integer) animator.getAnimatedValue()).intValue());
                            }
                        });
                        colorAnimation.setDuration(300).setStartDelay(0);
                        colorAnimation.start();
                        return;
                    }
                    activity.getWindow().setStatusBarColor(i);
                }
            });
        }
    }

    @ReactMethod
    public void setTranslucent(final boolean translucent) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.w("React", "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 21) {
            ap.a(new l(this, getReactApplicationContext()) {
                final /* synthetic */ StatusBarModule c;

                @TargetApi(21)
                public final void a() {
                    View decorView = activity.getWindow().getDecorView();
                    if (translucent) {
                        decorView.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = this$1;
                            }

                            public final WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                                return defaultInsets.replaceSystemWindowInsets(defaultInsets.getSystemWindowInsetLeft(), 0, defaultInsets.getSystemWindowInsetRight(), defaultInsets.getSystemWindowInsetBottom());
                            }
                        });
                    } else {
                        decorView.setOnApplyWindowInsetsListener(null);
                    }
                    ViewCompat.t(decorView);
                }
            });
        }
    }

    @ReactMethod
    public void setHidden(final boolean hidden) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.w("React", "StatusBarModule: Ignored status bar change, current activity is null.");
        } else {
            ap.a(new Runnable(this) {
                final /* synthetic */ StatusBarModule c;

                public final void run() {
                    if (hidden) {
                        activity.getWindow().addFlags(1024);
                        activity.getWindow().clearFlags(2048);
                        return;
                    }
                    activity.getWindow().addFlags(2048);
                    activity.getWindow().clearFlags(1024);
                }
            });
        }
    }

    @ReactMethod
    public void setStyle(final String style) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.w("React", "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 23) {
            ap.a(new Runnable(this) {
                final /* synthetic */ StatusBarModule c;

                @TargetApi(23)
                public final void run() {
                    activity.getWindow().getDecorView().setSystemUiVisibility(style.equals("dark-content") ? 8192 : 0);
                }
            });
        }
    }
}
