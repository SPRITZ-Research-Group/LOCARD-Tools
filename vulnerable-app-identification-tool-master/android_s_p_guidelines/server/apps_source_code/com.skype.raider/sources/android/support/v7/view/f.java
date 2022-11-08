package android.support.v7.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.b;
import android.support.v4.util.l;
import android.support.v7.view.menu.p;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class f extends ActionMode {
    final Context a;
    final b b;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static class a implements android.support.v7.view.b.a {
        final Callback a;
        final Context b;
        final ArrayList<f> c = new ArrayList();
        final l<Menu, Menu> d = new l();

        public a(Context context, Callback supportCallback) {
            this.b = context;
            this.a = supportCallback;
        }

        public final boolean a(b mode, Menu menu) {
            return this.a.onCreateActionMode(b(mode), a(menu));
        }

        public final boolean b(b mode, Menu menu) {
            return this.a.onPrepareActionMode(b(mode), a(menu));
        }

        public final boolean a(b mode, MenuItem item) {
            return this.a.onActionItemClicked(b(mode), p.a(this.b, (b) item));
        }

        public final void a(b mode) {
            this.a.onDestroyActionMode(b(mode));
        }

        private Menu a(Menu menu) {
            Menu wrapper = (Menu) this.d.get(menu);
            if (wrapper != null) {
                return wrapper;
            }
            wrapper = p.a(this.b, (android.support.v4.internal.view.a) menu);
            this.d.put(menu, wrapper);
            return wrapper;
        }

        public final ActionMode b(b mode) {
            f wrapper;
            int count = this.c.size();
            for (int i = 0; i < count; i++) {
                wrapper = (f) this.c.get(i);
                if (wrapper != null && wrapper.b == mode) {
                    return wrapper;
                }
            }
            wrapper = new f(this.b, mode);
            this.c.add(wrapper);
            return wrapper;
        }
    }

    public f(Context context, b supportActionMode) {
        this.a = context;
        this.b = supportActionMode;
    }

    public final Object getTag() {
        return this.b.j();
    }

    public final void setTag(Object tag) {
        this.b.a(tag);
    }

    public final void setTitle(CharSequence title) {
        this.b.b(title);
    }

    public final void setSubtitle(CharSequence subtitle) {
        this.b.a(subtitle);
    }

    public final void invalidate() {
        this.b.d();
    }

    public final void finish() {
        this.b.c();
    }

    public final Menu getMenu() {
        return p.a(this.a, (android.support.v4.internal.view.a) this.b.b());
    }

    public final CharSequence getTitle() {
        return this.b.f();
    }

    public final void setTitle(int resId) {
        this.b.a(resId);
    }

    public final CharSequence getSubtitle() {
        return this.b.g();
    }

    public final void setSubtitle(int resId) {
        this.b.b(resId);
    }

    public final View getCustomView() {
        return this.b.i();
    }

    public final void setCustomView(View view) {
        this.b.a(view);
    }

    public final MenuInflater getMenuInflater() {
        return this.b.a();
    }

    public final boolean getTitleOptionalHint() {
        return this.b.k();
    }

    public final void setTitleOptionalHint(boolean titleOptional) {
        this.b.a(titleOptional);
    }

    public final boolean isTitleOptional() {
        return this.b.h();
    }
}
