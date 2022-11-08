package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.support.v4.internal.view.a;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

@RequiresApi(14)
class q extends c<a> implements Menu {
    q(Context context, a object) {
        super(context, object);
    }

    public MenuItem add(CharSequence title) {
        return a(((a) this.b).add(title));
    }

    public MenuItem add(int titleRes) {
        return a(((a) this.b).add(titleRes));
    }

    public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
        return a(((a) this.b).add(groupId, itemId, order, title));
    }

    public MenuItem add(int groupId, int itemId, int order, int titleRes) {
        return a(((a) this.b).add(groupId, itemId, order, titleRes));
    }

    public SubMenu addSubMenu(CharSequence title) {
        return a(((a) this.b).addSubMenu(title));
    }

    public SubMenu addSubMenu(int titleRes) {
        return a(((a) this.b).addSubMenu(titleRes));
    }

    public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
        return a(((a) this.b).addSubMenu(groupId, itemId, order, title));
    }

    public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
        return a(((a) this.b).addSubMenu(groupId, itemId, order, titleRes));
    }

    public int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
        MenuItem[] items = null;
        if (outSpecificItems != null) {
            items = new MenuItem[outSpecificItems.length];
        }
        int result = ((a) this.b).addIntentOptions(groupId, itemId, order, caller, specifics, intent, flags, items);
        if (items != null) {
            int z = items.length;
            for (int i = 0; i < z; i++) {
                outSpecificItems[i] = a(items[i]);
            }
        }
        return result;
    }

    public void removeItem(int id) {
        b(id);
        ((a) this.b).removeItem(id);
    }

    public void removeGroup(int groupId) {
        a(groupId);
        ((a) this.b).removeGroup(groupId);
    }

    public void clear() {
        a();
        ((a) this.b).clear();
    }

    public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {
        ((a) this.b).setGroupCheckable(group, checkable, exclusive);
    }

    public void setGroupVisible(int group, boolean visible) {
        ((a) this.b).setGroupVisible(group, visible);
    }

    public void setGroupEnabled(int group, boolean enabled) {
        ((a) this.b).setGroupEnabled(group, enabled);
    }

    public boolean hasVisibleItems() {
        return ((a) this.b).hasVisibleItems();
    }

    public MenuItem findItem(int id) {
        return a(((a) this.b).findItem(id));
    }

    public int size() {
        return ((a) this.b).size();
    }

    public MenuItem getItem(int index) {
        return a(((a) this.b).getItem(index));
    }

    public void close() {
        ((a) this.b).close();
    }

    public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
        return ((a) this.b).performShortcut(keyCode, event, flags);
    }

    public boolean isShortcutKey(int keyCode, KeyEvent event) {
        return ((a) this.b).isShortcutKey(keyCode, event);
    }

    public boolean performIdentifierAction(int id, int flags) {
        return ((a) this.b).performIdentifierAction(id, flags);
    }

    public void setQwertyMode(boolean isQwerty) {
        ((a) this.b).setQwertyMode(isQwerty);
    }
}
