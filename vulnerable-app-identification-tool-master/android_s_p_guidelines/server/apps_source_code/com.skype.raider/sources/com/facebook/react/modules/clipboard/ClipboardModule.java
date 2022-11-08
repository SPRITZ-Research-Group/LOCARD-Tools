package com.facebook.react.modules.clipboard;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.react.bridge.ContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "Clipboard")
public class ClipboardModule extends ContextBaseJavaModule {
    public ClipboardModule(Context context) {
        super(context);
    }

    public String getName() {
        return "Clipboard";
    }

    private ClipboardManager getClipboardService() {
        Context context = getContext();
        getContext();
        return (ClipboardManager) context.getSystemService("clipboard");
    }

    @ReactMethod
    public void getString(ae promise) {
        try {
            ClipboardManager clipboard = getClipboardService();
            ClipData clipData = clipboard.getPrimaryClip();
            if (clipData == null) {
                promise.a((Object) "");
            } else if (clipData.getItemCount() > 0) {
                promise.a(clipboard.getPrimaryClip().getItemAt(0).getText());
            } else {
                promise.a((Object) "");
            }
        } catch (Throwable e) {
            promise.a(e);
        }
    }

    @SuppressLint({"DeprecatedMethod"})
    @ReactMethod
    public void setString(String text) {
        if (VERSION.SDK_INT >= 11) {
            getClipboardService().setPrimaryClip(ClipData.newPlainText(null, text));
            return;
        }
        getClipboardService().setText(text);
    }
}
