package com.rx.contextmenuandroid;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.a;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.d;

public class ContextMenuModule extends ReactContextBaseJavaModule {
    public ContextMenuModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ContextMenuAndroid";
    }

    @ReactMethod
    public void show(String title, al actions, final d buttonsCallback) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            throw new RuntimeException("MainActivity doesn't exist, you have a lifecycle issue in application");
        }
        int len = actions.size();
        final a[] items = new a[len];
        CharSequence[] labels = new CharSequence[len];
        for (int i = 0; i < len; i++) {
            am action = actions.getMap(i);
            String text = action.getString("text");
            items[i] = new a(text, action.getString("command"));
            labels[i] = text;
        }
        a builder = new a(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.a((CharSequence) title);
        }
        builder.a(labels, new OnClickListener(this) {
            final /* synthetic */ ContextMenuModule c;

            public final void onClick(DialogInterface dialog, int which) {
                buttonsCallback.invoke(items[which].c);
            }
        });
        AppCompatDialog dialog = builder.c();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().getAttributes().windowAnimations = c.a.DialogAnimation;
        dialog.setOnShowListener(new OnShowListener(this) {
            final /* synthetic */ ContextMenuModule a;

            {
                this.a = this$0;
            }

            public final void onShow(DialogInterface dialog) {
                this.a.setEllipsizeForAllTextViews((ViewGroup) ((AlertDialog) dialog).getWindow().getDecorView());
            }
        });
        dialog.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ ContextMenuModule b;

            public final void onCancel(DialogInterface dialog) {
                buttonsCallback.invoke("cancel");
            }
        });
        dialog.show();
    }

    private void setEllipsizeForAllTextViews(ViewGroup viewGroup) {
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = viewGroup.getChildAt(i);
                if (child instanceof TextView) {
                    TextView textView = (TextView) child;
                    textView.setSingleLine();
                    textView.setEllipsize(TruncateAt.END);
                } else if (child instanceof ViewGroup) {
                    setEllipsizeForAllTextViews((ViewGroup) child);
                }
            }
        }
    }
}
