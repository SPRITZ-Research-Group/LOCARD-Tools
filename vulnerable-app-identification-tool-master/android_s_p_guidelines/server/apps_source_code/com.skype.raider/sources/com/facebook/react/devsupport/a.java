package com.facebook.react.devsupport;

import android.content.Context;
import javax.annotation.Nullable;

public final class a {
    public static com.facebook.react.devsupport.a.a a(Context applicationContext, d reactInstanceCommandsHandler, @Nullable String packagerPathForJSBundleName, boolean enableOnCreate, @Nullable e redBoxHandler, int minNumShakes) {
        if (!enableOnCreate) {
            return new b();
        }
        try {
            return (com.facebook.react.devsupport.a.a) Class.forName("com.facebook.react.devsupport.DevSupportManagerImpl").getConstructor(new Class[]{Context.class, d.class, String.class, Boolean.TYPE, e.class, Integer.TYPE}).newInstance(new Object[]{applicationContext, reactInstanceCommandsHandler, packagerPathForJSBundleName, Boolean.valueOf(true), redBoxHandler, Integer.valueOf(minNumShakes)});
        } catch (Exception e) {
            throw new RuntimeException("Requested enabled DevSupportManager, but DevSupportManagerImpl class was not found or could not be created", e);
        }
    }
}
