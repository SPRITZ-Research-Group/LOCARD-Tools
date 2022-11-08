package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.RestrictTo;
import android.support.v4.view.f;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.menu.i;
import android.support.v7.widget.t;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class g extends MenuInflater {
    static final Class<?>[] a;
    static final Class<?>[] b;
    final Object[] c;
    final Object[] d = this.c;
    Context e;
    private Object f;

    private static class a implements OnMenuItemClickListener {
        private static final Class<?>[] a = new Class[]{MenuItem.class};
        private Object b;
        private Method c;

        public a(Object realOwner, String methodName) {
            this.b = realOwner;
            Class<?> c = realOwner.getClass();
            try {
                this.c = c.getMethod(methodName, a);
            } catch (Exception e) {
                InflateException ex = new InflateException("Couldn't resolve menu item onClick handler " + methodName + " in class " + c.getName());
                ex.initCause(e);
                throw ex;
            }
        }

        public final boolean onMenuItemClick(MenuItem item) {
            try {
                if (this.c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.c.invoke(this.b, new Object[]{item})).booleanValue();
                }
                this.c.invoke(this.b, new Object[]{item});
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class b {
        private String A;
        private String B;
        private CharSequence C;
        private CharSequence D;
        private ColorStateList E = null;
        private Mode F = null;
        android.support.v4.view.b a;
        final /* synthetic */ g b;
        private Menu c;
        private int d;
        private int e;
        private int f;
        private int g;
        private boolean h;
        private boolean i;
        private boolean j;
        private int k;
        private int l;
        private CharSequence m;
        private CharSequence n;
        private int o;
        private char p;
        private int q;
        private char r;
        private int s;
        private int t;
        private boolean u;
        private boolean v;
        private boolean w;
        private int x;
        private int y;
        private String z;

        public b(g gVar, Menu menu) {
            this.b = gVar;
            this.c = menu;
            a();
        }

        public final void a() {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = true;
            this.i = true;
        }

        public final void a(AttributeSet attrs) {
            TypedArray a = this.b.e.obtainStyledAttributes(attrs, j.MenuGroup);
            this.d = a.getResourceId(j.MenuGroup_android_id, 0);
            this.e = a.getInt(j.MenuGroup_android_menuCategory, 0);
            this.f = a.getInt(j.MenuGroup_android_orderInCategory, 0);
            this.g = a.getInt(j.MenuGroup_android_checkableBehavior, 0);
            this.h = a.getBoolean(j.MenuGroup_android_visible, true);
            this.i = a.getBoolean(j.MenuGroup_android_enabled, true);
            a.recycle();
        }

        public final void b(AttributeSet attrs) {
            boolean z = true;
            TypedArray a = this.b.e.obtainStyledAttributes(attrs, j.MenuItem);
            this.k = a.getResourceId(j.MenuItem_android_id, 0);
            this.l = (-65536 & a.getInt(j.MenuItem_android_menuCategory, this.e)) | (65535 & a.getInt(j.MenuItem_android_orderInCategory, this.f));
            this.m = a.getText(j.MenuItem_android_title);
            this.n = a.getText(j.MenuItem_android_titleCondensed);
            this.o = a.getResourceId(j.MenuItem_android_icon, 0);
            this.p = a(a.getString(j.MenuItem_android_alphabeticShortcut));
            this.q = a.getInt(j.MenuItem_alphabeticModifiers, 4096);
            this.r = a(a.getString(j.MenuItem_android_numericShortcut));
            this.s = a.getInt(j.MenuItem_numericModifiers, 4096);
            if (a.hasValue(j.MenuItem_android_checkable)) {
                this.t = a.getBoolean(j.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.t = this.g;
            }
            this.u = a.getBoolean(j.MenuItem_android_checked, false);
            this.v = a.getBoolean(j.MenuItem_android_visible, this.h);
            this.w = a.getBoolean(j.MenuItem_android_enabled, this.i);
            this.x = a.getInt(j.MenuItem_showAsAction, -1);
            this.B = a.getString(j.MenuItem_android_onClick);
            this.y = a.getResourceId(j.MenuItem_actionLayout, 0);
            this.z = a.getString(j.MenuItem_actionViewClass);
            this.A = a.getString(j.MenuItem_actionProviderClass);
            if (this.A == null) {
                z = false;
            }
            if (z && this.y == 0 && this.z == null) {
                this.a = (android.support.v4.view.b) a(this.A, g.b, this.b.d);
            } else {
                this.a = null;
            }
            this.C = a.getText(j.MenuItem_contentDescription);
            this.D = a.getText(j.MenuItem_tooltipText);
            if (a.hasValue(j.MenuItem_iconTintMode)) {
                this.F = t.a(a.getInt(j.MenuItem_iconTintMode, -1), this.F);
            } else {
                this.F = null;
            }
            if (a.hasValue(j.MenuItem_iconTint)) {
                this.E = a.getColorStateList(j.MenuItem_iconTint);
            } else {
                this.E = null;
            }
            a.recycle();
            this.j = false;
        }

        private static char a(String shortcutString) {
            if (shortcutString == null) {
                return 0;
            }
            return shortcutString.charAt(0);
        }

        private void a(MenuItem item) {
            item.setChecked(this.u).setVisible(this.v).setEnabled(this.w).setCheckable(this.t > 0).setTitleCondensed(this.n).setIcon(this.o);
            if (this.x >= 0) {
                item.setShowAsAction(this.x);
            }
            if (this.B != null) {
                if (this.b.e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                item.setOnMenuItemClickListener(new a(this.b.a(), this.B));
            }
            if (this.t >= 2) {
                if (item instanceof i) {
                    ((i) item).a(true);
                } else if (item instanceof android.support.v7.view.menu.j) {
                    ((android.support.v7.view.menu.j) item).b();
                }
            }
            boolean actionViewSpecified = false;
            if (this.z != null) {
                item.setActionView((View) a(this.z, g.a, this.b.c));
                actionViewSpecified = true;
            }
            if (this.y > 0 && !actionViewSpecified) {
                item.setActionView(this.y);
            }
            if (this.a != null) {
                f.a(item, this.a);
            }
            f.a(item, this.C);
            f.b(item, this.D);
            f.b(item, this.p, this.q);
            f.a(item, this.r, this.s);
            if (this.F != null) {
                f.a(item, this.F);
            }
            if (this.E != null) {
                f.a(item, this.E);
            }
        }

        public final void b() {
            this.j = true;
            a(this.c.add(this.d, this.k, this.l, this.m));
        }

        public final SubMenu c() {
            this.j = true;
            SubMenu subMenu = this.c.addSubMenu(this.d, this.k, this.l, this.m);
            a(subMenu.getItem());
            return subMenu;
        }

        public final boolean d() {
            return this.j;
        }

        private <T> T a(String className, Class<?>[] constructorSignature, Object[] arguments) {
            try {
                Constructor<?> constructor = this.b.e.getClassLoader().loadClass(className).getConstructor(constructorSignature);
                constructor.setAccessible(true);
                return constructor.newInstance(arguments);
            } catch (Exception e) {
                return null;
            }
        }
    }

    static {
        Class[] clsArr = new Class[]{Context.class};
        a = clsArr;
        b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.e = context;
        this.c = new Object[]{context};
    }

    public final void inflate(int menuRes, Menu menu) {
        if (menu instanceof android.support.v4.internal.view.a) {
            XmlResourceParser parser = null;
            try {
                parser = this.e.getResources().getLayout(menuRes);
                a(parser, Xml.asAttributeSet(parser), menu);
                if (parser != null) {
                    parser.close();
                }
            } catch (XmlPullParserException e) {
                throw new InflateException("Error inflating menu XML", e);
            } catch (IOException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (Throwable th) {
                if (parser != null) {
                    parser.close();
                }
            }
        } else {
            super.inflate(menuRes, menu);
        }
    }

    private void a(XmlPullParser parser, AttributeSet attrs, Menu menu) throws XmlPullParserException, IOException {
        b menuState = new b(this, menu);
        int eventType = parser.getEventType();
        boolean lookingForEndOfUnknownTag = false;
        String unknownTagName = null;
        while (eventType != 2) {
            eventType = parser.next();
            if (eventType == 1) {
                break;
            }
        }
        String tagName = parser.getName();
        if (tagName.equals("menu")) {
            eventType = parser.next();
            boolean reachedEndOfMenu = false;
            while (!reachedEndOfMenu) {
                switch (eventType) {
                    case 1:
                        throw new RuntimeException("Unexpected end of document");
                    case 2:
                        if (!lookingForEndOfUnknownTag) {
                            tagName = parser.getName();
                            if (!tagName.equals("group")) {
                                if (!tagName.equals("item")) {
                                    if (!tagName.equals("menu")) {
                                        lookingForEndOfUnknownTag = true;
                                        unknownTagName = tagName;
                                        break;
                                    }
                                    a(parser, attrs, menuState.c());
                                    break;
                                }
                                menuState.b(attrs);
                                break;
                            }
                            menuState.a(attrs);
                            break;
                        }
                        break;
                    case 3:
                        tagName = parser.getName();
                        if (!lookingForEndOfUnknownTag || !tagName.equals(unknownTagName)) {
                            if (!tagName.equals("group")) {
                                if (!tagName.equals("item")) {
                                    if (!tagName.equals("menu")) {
                                        break;
                                    }
                                    reachedEndOfMenu = true;
                                    break;
                                } else if (!menuState.d()) {
                                    if (menuState.a != null && menuState.a.e()) {
                                        menuState.c();
                                        break;
                                    } else {
                                        menuState.b();
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            menuState.a();
                            break;
                        }
                        lookingForEndOfUnknownTag = false;
                        unknownTagName = null;
                        break;
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
            return;
        }
        throw new RuntimeException("Expecting menu, got " + tagName);
    }

    final Object a() {
        if (this.f == null) {
            Object obj = this.e;
            while (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
                obj = ((ContextWrapper) obj).getBaseContext();
            }
            this.f = obj;
        }
        return this.f;
    }
}
