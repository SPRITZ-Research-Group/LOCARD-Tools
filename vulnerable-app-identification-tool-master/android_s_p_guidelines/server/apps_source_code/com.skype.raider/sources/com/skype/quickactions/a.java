package com.skype.quickactions;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;

@TargetApi(25)
final class a {
    private ShortcutManager a;
    private Context b;

    a(Context applicationContext) {
        this.b = applicationContext;
    }

    final boolean a(al shortcutsArray) {
        try {
            FLog.i("QuickActionsHelper", "Received: " + shortcutsArray.toString());
            return this.a.setDynamicShortcuts(b(shortcutsArray));
        } catch (NullPointerException e) {
            return false;
        }
    }

    final void a(ShortcutManager shortcutManager) {
        Assert.assertNotNull(shortcutManager);
        this.a = shortcutManager;
    }

    private List<ShortcutInfo> b(al shortcutsArray) {
        List<ShortcutInfo> shortcutInfos = new ArrayList();
        for (int i = 0; i < shortcutsArray.size(); i++) {
            am map = shortcutsArray.getMap(i);
            String string = map.getString("id");
            String string2 = map.getString("stringKey");
            Builder rank = new Builder(this.b, string).setShortLabel(b(string2)).setLongLabel(b(string2)).setRank(i);
            int identifier = this.b.getResources().getIdentifier(map.getString("icon"), "mipmap", this.b.getPackageName());
            FLog.i("QuickActionsHelper", "Icon ID: " + identifier);
            shortcutInfos.add(rank.setIcon(Icon.createWithResource(this.b, identifier)).setIntent(a(map.getString(ReactVideoViewManager.PROP_SRC_URI))).build());
        }
        return shortcutInfos;
    }

    private static Intent a(String uriStr) {
        FLog.i("QuickActionsHelper", "Intent URL: " + uriStr);
        try {
            return new Intent("android.intent.action.VIEW", Uri.parse(uriStr));
        } catch (NullPointerException e) {
            FLog.e(e.toString(), "Invalid input uri: " + uriStr);
            throw e;
        }
    }

    private String b(String strKey) {
        int resourceId = this.b.getResources().getIdentifier(strKey, "string", this.b.getPackageName());
        FLog.i("QuickActionsHelper", "String ID: " + this.b.getResources().getString(resourceId));
        return this.b.getResources().getString(resourceId);
    }

    final boolean a() {
        if (this.a.getDynamicShortcuts().size() <= 0) {
            return false;
        }
        this.a.removeAllDynamicShortcuts();
        FLog.i("QuickActionsHelper", "Removed all shortcuts.");
        return true;
    }

    final aq b() {
        Object obj;
        if (VERSION.SDK_INT >= 26) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            throw new UnsupportedOperationException("Pinned Shortcuts Not Supported");
        }
        try {
            List<ShortcutInfo> pinnedShortcuts = this.a.getPinnedShortcuts();
            aq pinnedShortcutIds = new WritableNativeArray();
            for (int i = 0; i < pinnedShortcuts.size(); i++) {
                pinnedShortcutIds.pushString(((ShortcutInfo) pinnedShortcuts.get(i)).getId());
            }
            return pinnedShortcutIds;
        } catch (NullPointerException e) {
            throw e;
        }
    }
}
