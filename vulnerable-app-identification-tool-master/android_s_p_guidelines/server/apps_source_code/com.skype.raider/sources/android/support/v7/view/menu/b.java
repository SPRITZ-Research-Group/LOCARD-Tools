package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

@RestrictTo({a.LIBRARY_GROUP})
public abstract class b implements n {
    protected Context a;
    protected Context b;
    protected g c;
    protected LayoutInflater d;
    protected LayoutInflater e;
    protected o f;
    private n.a g;
    private int h;
    private int i;
    private int j;

    public abstract void a(i iVar, o.a aVar);

    public b(Context context, int menuLayoutRes, int itemLayoutRes) {
        this.a = context;
        this.d = LayoutInflater.from(context);
        this.h = menuLayoutRes;
        this.i = itemLayoutRes;
    }

    public void a(Context context, g menu) {
        this.b = context;
        this.e = LayoutInflater.from(this.b);
        this.c = menu;
    }

    public o a(ViewGroup root) {
        if (this.f == null) {
            this.f = (o) this.d.inflate(this.h, root, false);
            this.f.a(this.c);
            a(true);
        }
        return this.f;
    }

    public void a(boolean cleared) {
        ViewGroup parent = this.f;
        if (parent != null) {
            int childIndex = 0;
            if (this.c != null) {
                this.c.l();
                ArrayList<i> visibleItems = this.c.k();
                int itemCount = visibleItems.size();
                for (int i = 0; i < itemCount; i++) {
                    i item = (i) visibleItems.get(i);
                    if (c(item)) {
                        View convertView = parent.getChildAt(childIndex);
                        i oldItem = convertView instanceof o.a ? ((o.a) convertView).b() : null;
                        View itemView = a(item, convertView, parent);
                        if (item != oldItem) {
                            itemView.setPressed(false);
                            itemView.jumpDrawablesToCurrentState();
                        }
                        if (itemView != convertView) {
                            ViewGroup viewGroup = (ViewGroup) itemView.getParent();
                            if (viewGroup != null) {
                                viewGroup.removeView(itemView);
                            }
                            ((ViewGroup) this.f).addView(itemView, childIndex);
                        }
                        childIndex++;
                    }
                }
            }
            while (childIndex < parent.getChildCount()) {
                if (!a(parent, childIndex)) {
                    childIndex++;
                }
            }
        }
    }

    protected boolean a(ViewGroup parent, int childIndex) {
        parent.removeViewAt(childIndex);
        return true;
    }

    public final void a(n.a cb) {
        this.g = cb;
    }

    public final n.a c() {
        return this.g;
    }

    public View a(i item, View convertView, ViewGroup parent) {
        o.a itemView;
        if (convertView instanceof o.a) {
            itemView = (o.a) convertView;
        } else {
            itemView = (o.a) this.d.inflate(this.i, parent, false);
        }
        a(item, itemView);
        return (View) itemView;
    }

    public boolean c(i item) {
        return true;
    }

    public void a(g menu, boolean allMenusAreClosing) {
        if (this.g != null) {
            this.g.a(menu, allMenusAreClosing);
        }
    }

    public boolean a(t menu) {
        if (this.g != null) {
            return this.g.a(menu);
        }
        return false;
    }

    public boolean a() {
        return false;
    }

    public final boolean a(i item) {
        return false;
    }

    public final boolean b(i item) {
        return false;
    }

    public final int b() {
        return this.j;
    }

    public final void a(int id) {
        this.j = id;
    }
}
