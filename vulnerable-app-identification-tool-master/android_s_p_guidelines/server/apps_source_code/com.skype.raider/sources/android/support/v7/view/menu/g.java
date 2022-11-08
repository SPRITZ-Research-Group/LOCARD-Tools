package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class g implements android.support.v4.internal.view.a {
    private static final int[] d = new int[]{1, 4, 5, 3, 2, 0};
    CharSequence a;
    Drawable b;
    View c;
    private final Context e;
    private final Resources f;
    private boolean g;
    private boolean h;
    private a i;
    private ArrayList<i> j;
    private ArrayList<i> k;
    private boolean l;
    private ArrayList<i> m;
    private ArrayList<i> n;
    private boolean o;
    private int p = 0;
    private ContextMenuInfo q;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private ArrayList<i> w = new ArrayList();
    private CopyOnWriteArrayList<WeakReference<n>> x = new CopyOnWriteArrayList();
    private i y;
    private boolean z;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public interface a {
        void a(g gVar);

        boolean a(g gVar, MenuItem menuItem);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public interface b {
        boolean a(i iVar);
    }

    public g(Context context) {
        boolean z = true;
        this.e = context;
        this.f = context.getResources();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = true;
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = true;
        if (this.f.getConfiguration().keyboard == 1 || !this.f.getBoolean(android.support.v7.appcompat.a.b.abc_config_showMenuShortcutsWhenKeyboardPresent)) {
            z = false;
        }
        this.h = z;
    }

    public final g a() {
        this.p = 1;
        return this;
    }

    public final void a(n presenter) {
        a(presenter, this.e);
    }

    public final void a(n presenter, Context menuContext) {
        this.x.add(new WeakReference(presenter));
        presenter.a(menuContext, this);
        this.o = true;
    }

    public final void b(n presenter) {
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<n> ref = (WeakReference) it.next();
            n item = (n) ref.get();
            if (item == null || item == presenter) {
                this.x.remove(ref);
            }
        }
    }

    public final void c(Bundle outStates) {
        SparseArray<Parcelable> viewStates = null;
        int itemCount = size();
        for (int i = 0; i < itemCount; i++) {
            MenuItem item = getItem(i);
            View v = item.getActionView();
            if (!(v == null || v.getId() == -1)) {
                if (viewStates == null) {
                    viewStates = new SparseArray();
                }
                v.saveHierarchyState(viewStates);
                if (item.isActionViewExpanded()) {
                    outStates.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((t) item.getSubMenu()).c(outStates);
            }
        }
        if (viewStates != null) {
            outStates.putSparseParcelableArray(b(), viewStates);
        }
    }

    public final void d(Bundle states) {
        if (states != null) {
            SparseArray<Parcelable> viewStates = states.getSparseParcelableArray(b());
            int itemCount = size();
            for (int i = 0; i < itemCount; i++) {
                MenuItem item = getItem(i);
                View v = item.getActionView();
                if (!(v == null || v.getId() == -1)) {
                    v.restoreHierarchyState(viewStates);
                }
                if (item.hasSubMenu()) {
                    ((t) item.getSubMenu()).d(states);
                }
            }
            int expandedId = states.getInt("android:menu:expandedactionview");
            if (expandedId > 0) {
                MenuItem itemToExpand = findItem(expandedId);
                if (itemToExpand != null) {
                    itemToExpand.expandActionView();
                }
            }
        }
    }

    protected String b() {
        return "android:menu:actionviewstates";
    }

    public void a(a cb) {
        this.i = cb;
    }

    public MenuItem add(CharSequence title) {
        return a(0, 0, 0, title);
    }

    public MenuItem add(int titleRes) {
        return a(0, 0, 0, this.f.getString(titleRes));
    }

    public MenuItem add(int group, int id, int categoryOrder, CharSequence title) {
        return a(group, id, categoryOrder, title);
    }

    public MenuItem add(int group, int id, int categoryOrder, int title) {
        return a(group, id, categoryOrder, this.f.getString(title));
    }

    public SubMenu addSubMenu(CharSequence title) {
        return addSubMenu(0, 0, 0, title);
    }

    public SubMenu addSubMenu(int titleRes) {
        return addSubMenu(0, 0, 0, this.f.getString(titleRes));
    }

    public SubMenu addSubMenu(int group, int id, int categoryOrder, CharSequence title) {
        i item = (i) a(group, id, categoryOrder, title);
        t subMenu = new t(this.e, this, item);
        item.a(subMenu);
        return subMenu;
    }

    public SubMenu addSubMenu(int group, int id, int categoryOrder, int title) {
        return addSubMenu(group, id, categoryOrder, this.f.getString(title));
    }

    public int addIntentOptions(int group, int id, int categoryOrder, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
        PackageManager pm = this.e.getPackageManager();
        List<ResolveInfo> lri = pm.queryIntentActivityOptions(caller, specifics, intent, 0);
        int N = lri != null ? lri.size() : 0;
        if ((flags & 1) == 0) {
            removeGroup(group);
        }
        for (int i = 0; i < N; i++) {
            Intent intent2;
            ResolveInfo ri = (ResolveInfo) lri.get(i);
            if (ri.specificIndex < 0) {
                intent2 = intent;
            } else {
                intent2 = specifics[ri.specificIndex];
            }
            Intent rintent = new Intent(intent2);
            rintent.setComponent(new ComponentName(ri.activityInfo.applicationInfo.packageName, ri.activityInfo.name));
            MenuItem item = add(group, id, categoryOrder, ri.loadLabel(pm)).setIcon(ri.loadIcon(pm)).setIntent(rintent);
            if (outSpecificItems != null && ri.specificIndex >= 0) {
                outSpecificItems[ri.specificIndex] = item;
            }
        }
        return N;
    }

    public void removeGroup(int group) {
        int size = size();
        int i = 0;
        while (i < size) {
            if (((i) this.j.get(i)).getGroupId() == group) {
                break;
            }
            i++;
        }
        i = -1;
        if (i >= 0) {
            int maxRemovable = this.j.size() - i;
            int numRemoved = 0;
            while (true) {
                int numRemoved2 = numRemoved;
                numRemoved = numRemoved2 + 1;
                if (numRemoved2 >= maxRemovable || ((i) this.j.get(i)).getGroupId() != group) {
                    a(true);
                } else {
                    a(i, false);
                }
            }
            a(true);
        }
    }

    private void a(int index, boolean updateChildrenOnMenuViews) {
        if (index >= 0 && index < this.j.size()) {
            this.j.remove(index);
            if (updateChildrenOnMenuViews) {
                a(true);
            }
        }
    }

    public void clear() {
        if (this.y != null) {
            b(this.y);
        }
        this.j.clear();
        a(true);
    }

    final void a(MenuItem item) {
        int group = item.getGroupId();
        int N = this.j.size();
        g();
        for (int i = 0; i < N; i++) {
            MenuItem curItem = (i) this.j.get(i);
            if (curItem.getGroupId() == group && curItem.g() && curItem.isCheckable()) {
                boolean z;
                if (curItem == item) {
                    z = true;
                } else {
                    z = false;
                }
                curItem.b(z);
            }
        }
        h();
    }

    public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {
        int N = this.j.size();
        for (int i = 0; i < N; i++) {
            i item = (i) this.j.get(i);
            if (item.getGroupId() == group) {
                item.a(exclusive);
                item.setCheckable(checkable);
            }
        }
    }

    public void setGroupVisible(int group, boolean visible) {
        int N = this.j.size();
        boolean changedAtLeastOneItem = false;
        for (int i = 0; i < N; i++) {
            i item = (i) this.j.get(i);
            if (item.getGroupId() == group && item.c(visible)) {
                changedAtLeastOneItem = true;
            }
        }
        if (changedAtLeastOneItem) {
            a(true);
        }
    }

    public void setGroupEnabled(int group, boolean enabled) {
        int N = this.j.size();
        for (int i = 0; i < N; i++) {
            i item = (i) this.j.get(i);
            if (item.getGroupId() == group) {
                item.setEnabled(enabled);
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.z) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (((i) this.j.get(i)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int id) {
        int size = size();
        for (int i = 0; i < size; i++) {
            i item = (i) this.j.get(i);
            if (item.getItemId() == id) {
                return item;
            }
            if (item.hasSubMenu()) {
                MenuItem possibleItem = item.getSubMenu().findItem(id);
                if (possibleItem != null) {
                    return possibleItem;
                }
            }
        }
        return null;
    }

    public int size() {
        return this.j.size();
    }

    public MenuItem getItem(int index) {
        return (MenuItem) this.j.get(index);
    }

    public boolean isShortcutKey(int keyCode, KeyEvent event) {
        return a(keyCode, event) != null;
    }

    public void setQwertyMode(boolean isQwerty) {
        this.g = isQwerty;
        a(false);
    }

    boolean c() {
        return this.g;
    }

    public boolean d() {
        return this.h;
    }

    public final Context e() {
        return this.e;
    }

    boolean a(g menu, MenuItem item) {
        return this.i != null && this.i.a(menu, item);
    }

    public final void f() {
        if (this.i != null) {
            this.i.a(this);
        }
    }

    private static int a(ArrayList<i> items, int ordering) {
        for (int i = items.size() - 1; i >= 0; i--) {
            if (((i) items.get(i)).c() <= ordering) {
                return i + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
        MenuItem item = a(keyCode, event);
        boolean handled = false;
        if (item != null) {
            handled = a(item, null, flags);
        }
        if ((flags & 2) != 0) {
            b(true);
        }
        return handled;
    }

    private void a(List<i> items, int keyCode, KeyEvent event) {
        boolean qwerty = c();
        int modifierState = event.getModifiers();
        KeyData possibleChars = new KeyData();
        if (event.getKeyData(possibleChars) || keyCode == 67) {
            int N = this.j.size();
            for (int i = 0; i < N; i++) {
                i item = (i) this.j.get(i);
                if (item.hasSubMenu()) {
                    ((g) item.getSubMenu()).a((List) items, keyCode, event);
                }
                char shortcutChar = qwerty ? item.getAlphabeticShortcut() : item.getNumericShortcut();
                if (!(((69647 & modifierState) == (69647 & (qwerty ? item.getAlphabeticModifiers() : item.getNumericModifiers())) ? 1 : null) == null || shortcutChar == 0 || ((shortcutChar != possibleChars.meta[0] && shortcutChar != possibleChars.meta[2] && (!qwerty || shortcutChar != 8 || keyCode != 67)) || !item.isEnabled()))) {
                    items.add(item);
                }
            }
        }
    }

    private i a(int keyCode, KeyEvent event) {
        List items = this.w;
        items.clear();
        a(items, keyCode, event);
        if (items.isEmpty()) {
            return null;
        }
        int metaState = event.getMetaState();
        KeyData possibleChars = new KeyData();
        event.getKeyData(possibleChars);
        int size = items.size();
        if (size == 1) {
            return (i) items.get(0);
        }
        boolean qwerty = c();
        for (int i = 0; i < size; i++) {
            char shortcutChar;
            i item = (i) items.get(i);
            if (qwerty) {
                shortcutChar = item.getAlphabeticShortcut();
            } else {
                shortcutChar = item.getNumericShortcut();
            }
            if ((shortcutChar == possibleChars.meta[0] && (metaState & 2) == 0) || ((shortcutChar == possibleChars.meta[2] && (metaState & 2) != 0) || (qwerty && shortcutChar == 8 && keyCode == 67))) {
                return item;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int id, int flags) {
        return a(findItem(id), null, flags);
    }

    public final boolean a(MenuItem item, int flags) {
        return a(item, null, flags);
    }

    public final boolean a(MenuItem item, n preferredPresenter, int flags) {
        boolean z = false;
        i itemImpl = (i) item;
        if (itemImpl == null || !itemImpl.isEnabled()) {
            return false;
        }
        boolean providerHasSubMenu;
        boolean invoked = itemImpl.b();
        android.support.v4.view.b provider = itemImpl.a();
        if (provider == null || !provider.e()) {
            providerHasSubMenu = false;
        } else {
            providerHasSubMenu = true;
        }
        if (itemImpl.l()) {
            invoked |= itemImpl.expandActionView();
            if (!invoked) {
                return invoked;
            }
            b(true);
            return invoked;
        } else if (itemImpl.hasSubMenu() || providerHasSubMenu) {
            if ((flags & 4) == 0) {
                b(false);
            }
            if (!itemImpl.hasSubMenu()) {
                itemImpl.a(new t(this.e, this, itemImpl));
            }
            t subMenu = (t) itemImpl.getSubMenu();
            if (providerHasSubMenu) {
                provider.a((SubMenu) subMenu);
            }
            if (!this.x.isEmpty()) {
                if (preferredPresenter != null) {
                    z = preferredPresenter.a(subMenu);
                }
                Iterator it = this.x.iterator();
                boolean z2 = z;
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    n nVar = (n) weakReference.get();
                    if (nVar == null) {
                        this.x.remove(weakReference);
                    } else {
                        if (z2) {
                            z = z2;
                        } else {
                            z = nVar.a(subMenu);
                        }
                        z2 = z;
                    }
                }
                z = z2;
            }
            invoked |= r5;
            if (invoked) {
                return invoked;
            }
            b(true);
            return invoked;
        } else if ((flags & 1) != 0) {
            return invoked;
        } else {
            b(true);
            return invoked;
        }
    }

    public final void b(boolean closeAllMenus) {
        if (!this.v) {
            this.v = true;
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference<n> ref = (WeakReference) it.next();
                n presenter = (n) ref.get();
                if (presenter == null) {
                    this.x.remove(ref);
                } else {
                    presenter.a(this, closeAllMenus);
                }
            }
            this.v = false;
        }
    }

    public void close() {
        b(true);
    }

    public void a(boolean structureChanged) {
        if (this.r) {
            this.s = true;
            if (structureChanged) {
                this.t = true;
                return;
            }
            return;
        }
        if (structureChanged) {
            this.l = true;
            this.o = true;
        }
        if (!this.x.isEmpty()) {
            g();
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                n nVar = (n) weakReference.get();
                if (nVar == null) {
                    this.x.remove(weakReference);
                } else {
                    nVar.a(structureChanged);
                }
            }
            h();
        }
    }

    public final void g() {
        if (!this.r) {
            this.r = true;
            this.s = false;
            this.t = false;
        }
    }

    public final void h() {
        this.r = false;
        if (this.s) {
            this.s = false;
            a(this.t);
        }
    }

    final void i() {
        this.l = true;
        a(true);
    }

    final void j() {
        this.o = true;
        a(true);
    }

    @NonNull
    public final ArrayList<i> k() {
        if (!this.l) {
            return this.k;
        }
        this.k.clear();
        int itemsSize = this.j.size();
        for (int i = 0; i < itemsSize; i++) {
            i item = (i) this.j.get(i);
            if (item.isVisible()) {
                this.k.add(item);
            }
        }
        this.l = false;
        this.o = true;
        return this.k;
    }

    public final void l() {
        ArrayList<i> visibleItems = k();
        if (this.o) {
            boolean flagged = false;
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference<n> ref = (WeakReference) it.next();
                n presenter = (n) ref.get();
                if (presenter == null) {
                    this.x.remove(ref);
                } else {
                    flagged |= presenter.a();
                }
            }
            if (flagged) {
                this.m.clear();
                this.n.clear();
                int itemsSize = visibleItems.size();
                for (int i = 0; i < itemsSize; i++) {
                    i item = (i) visibleItems.get(i);
                    if (item.h()) {
                        this.m.add(item);
                    } else {
                        this.n.add(item);
                    }
                }
            } else {
                this.m.clear();
                this.n.clear();
                this.n.addAll(k());
            }
            this.o = false;
        }
    }

    public final ArrayList<i> m() {
        l();
        return this.m;
    }

    public final ArrayList<i> n() {
        l();
        return this.n;
    }

    public void clearHeader() {
        this.b = null;
        this.a = null;
        this.c = null;
        a(false);
    }

    private void a(int titleRes, CharSequence title, int iconRes, Drawable icon, View view) {
        Resources r = this.f;
        if (view != null) {
            this.c = view;
            this.a = null;
            this.b = null;
        } else {
            if (titleRes > 0) {
                this.a = r.getText(titleRes);
            } else if (title != null) {
                this.a = title;
            }
            if (iconRes > 0) {
                this.b = android.support.v4.content.a.a(this.e, iconRes);
            } else if (icon != null) {
                this.b = icon;
            }
            this.c = null;
        }
        a(false);
    }

    protected final g a(CharSequence title) {
        a(0, title, 0, null, null);
        return this;
    }

    protected final g a(int titleRes) {
        a(titleRes, null, 0, null, null);
        return this;
    }

    protected final g a(Drawable icon) {
        a(0, null, 0, icon, null);
        return this;
    }

    protected final g b(int iconRes) {
        a(0, null, iconRes, null, null);
        return this;
    }

    protected final g a(View view) {
        a(0, null, 0, null, view);
        return this;
    }

    public g o() {
        return this;
    }

    final boolean p() {
        return this.u;
    }

    public boolean a(i item) {
        if (this.x.isEmpty()) {
            return false;
        }
        boolean expanded = false;
        g();
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<n> ref = (WeakReference) it.next();
            n presenter = (n) ref.get();
            if (presenter == null) {
                this.x.remove(ref);
            } else {
                expanded = presenter.a(item);
                if (expanded) {
                    break;
                }
            }
        }
        h();
        if (!expanded) {
            return expanded;
        }
        this.y = item;
        return expanded;
    }

    public boolean b(i item) {
        if (this.x.isEmpty() || this.y != item) {
            return false;
        }
        boolean collapsed = false;
        g();
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<n> ref = (WeakReference) it.next();
            n presenter = (n) ref.get();
            if (presenter == null) {
                this.x.remove(ref);
            } else {
                collapsed = presenter.b(item);
                if (collapsed) {
                    break;
                }
            }
        }
        h();
        if (!collapsed) {
            return collapsed;
        }
        this.y = null;
        return collapsed;
    }

    public final i q() {
        return this.y;
    }

    public final void c(boolean override) {
        this.z = override;
    }

    public final void a(Bundle outState) {
        if (!this.x.isEmpty()) {
            SparseArray sparseArray = new SparseArray();
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                n nVar = (n) weakReference.get();
                if (nVar == null) {
                    this.x.remove(weakReference);
                } else {
                    int b = nVar.b();
                    if (b > 0) {
                        Parcelable d = nVar.d();
                        if (d != null) {
                            sparseArray.put(b, d);
                        }
                    }
                }
            }
            outState.putSparseParcelableArray("android:menu:presenters", sparseArray);
        }
    }

    public final void b(Bundle state) {
        SparseArray sparseParcelableArray = state.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray != null && !this.x.isEmpty()) {
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                n nVar = (n) weakReference.get();
                if (nVar == null) {
                    this.x.remove(weakReference);
                } else {
                    int b = nVar.b();
                    if (b > 0) {
                        Parcelable parcelable = (Parcelable) sparseParcelableArray.get(b);
                        if (parcelable != null) {
                            nVar.a(parcelable);
                        }
                    }
                }
            }
        }
    }

    protected final MenuItem a(int group, int id, int categoryOrder, CharSequence title) {
        int i = (-65536 & categoryOrder) >> 16;
        if (i < 0 || i >= d.length) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        int ordering = (d[i] << 16) | (65535 & categoryOrder);
        i item = new i(this, group, id, categoryOrder, ordering, title, this.p);
        if (this.q != null) {
            item.a(this.q);
        }
        this.j.add(a(this.j, ordering), item);
        a(true);
        return item;
    }

    public void removeItem(int id) {
        int i;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((i) this.j.get(i2)).getItemId() == id) {
                i = i2;
                break;
            }
        }
        i = -1;
        a(i, true);
    }
}
