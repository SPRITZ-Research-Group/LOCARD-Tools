package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.g;
import android.support.v7.appcompat.a.j;
import android.support.v7.widget.aq;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

@RestrictTo({a.LIBRARY_GROUP})
public class ListMenuItemView extends LinearLayout implements o.a {
    private i a;
    private ImageView b;
    private RadioButton c;
    private TextView d;
    private CheckBox e;
    private TextView f;
    private ImageView g;
    private Drawable h;
    private int i;
    private Context j;
    private boolean k;
    private Drawable l;
    private int m;
    private LayoutInflater n;
    private boolean o;

    public ListMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.a.a.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        aq a = aq.a(getContext(), attrs, j.MenuView, defStyleAttr, 0);
        this.h = a.a(j.MenuView_android_itemBackground);
        this.i = a.g(j.MenuView_android_itemTextAppearance, -1);
        this.k = a.a(j.MenuView_preserveIconSpacing, false);
        this.j = context;
        this.l = a.a(j.MenuView_subMenuArrow);
        a.a();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewCompat.a((View) this, this.h);
        this.d = (TextView) findViewById(f.title);
        if (this.i != -1) {
            this.d.setTextAppearance(this.j, this.i);
        }
        this.f = (TextView) findViewById(f.shortcut);
        this.g = (ImageView) findViewById(f.submenuarrow);
        if (this.g != null) {
            this.g.setImageDrawable(this.l);
        }
    }

    public final void a(i itemData) {
        int i = 0;
        this.a = itemData;
        this.m = 0;
        setVisibility(itemData.isVisible() ? 0 : 8);
        setTitle(itemData.a((o.a) this));
        setCheckable(itemData.isCheckable());
        setShortcut(itemData.f(), itemData.d());
        setIcon(itemData.getIcon());
        setEnabled(itemData.isEnabled());
        boolean hasSubMenu = itemData.hasSubMenu();
        if (this.g != null) {
            ImageView imageView = this.g;
            if (!hasSubMenu) {
                i = 8;
            }
            imageView.setVisibility(i);
        }
        setContentDescription(itemData.getContentDescription());
    }

    public void setForceShowIcon(boolean forceShow) {
        this.o = forceShow;
        this.k = forceShow;
    }

    public void setTitle(CharSequence title) {
        if (title != null) {
            this.d.setText(title);
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
            }
        } else if (this.d.getVisibility() != 8) {
            this.d.setVisibility(8);
        }
    }

    public final i b() {
        return this.a;
    }

    public void setCheckable(boolean checkable) {
        if (checkable || this.c != null || this.e != null) {
            CompoundButton compoundButton;
            CompoundButton otherCompoundButton;
            if (this.a.g()) {
                if (this.c == null) {
                    a();
                }
                compoundButton = this.c;
                otherCompoundButton = this.e;
            } else {
                if (this.e == null) {
                    d();
                }
                compoundButton = this.e;
                otherCompoundButton = this.c;
            }
            if (checkable) {
                int newVisibility;
                compoundButton.setChecked(this.a.isChecked());
                if (checkable) {
                    newVisibility = 0;
                } else {
                    newVisibility = 8;
                }
                if (compoundButton.getVisibility() != newVisibility) {
                    compoundButton.setVisibility(newVisibility);
                }
                if (otherCompoundButton != null && otherCompoundButton.getVisibility() != 8) {
                    otherCompoundButton.setVisibility(8);
                    return;
                }
                return;
            }
            if (this.e != null) {
                this.e.setVisibility(8);
            }
            if (this.c != null) {
                this.c.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean checked) {
        CompoundButton compoundButton;
        if (this.a.g()) {
            if (this.c == null) {
                a();
            }
            compoundButton = this.c;
        } else {
            if (this.e == null) {
                d();
            }
            compoundButton = this.e;
        }
        compoundButton.setChecked(checked);
    }

    public void setShortcut(boolean showShortcut, char shortcutKey) {
        int newVisibility = (showShortcut && this.a.f()) ? 0 : 8;
        if (newVisibility == 0) {
            this.f.setText(this.a.e());
        }
        if (this.f.getVisibility() != newVisibility) {
            this.f.setVisibility(newVisibility);
        }
    }

    public void setIcon(Drawable icon) {
        boolean showIcon;
        if (this.a.a.p() || this.o) {
            showIcon = true;
        } else {
            showIcon = false;
        }
        if (!showIcon && !this.k) {
            return;
        }
        if (this.b != null || icon != null || this.k) {
            if (this.b == null) {
                this.b = (ImageView) e().inflate(g.abc_list_menu_item_icon, this, false);
                addView(this.b, 0);
            }
            if (icon != null || this.k) {
                ImageView imageView = this.b;
                if (!showIcon) {
                    icon = null;
                }
                imageView.setImageDrawable(icon);
                if (this.b.getVisibility() != 0) {
                    this.b.setVisibility(0);
                    return;
                }
                return;
            }
            this.b.setVisibility(8);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.b != null && this.k) {
            LayoutParams lp = getLayoutParams();
            LinearLayout.LayoutParams iconLp = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            if (lp.height > 0 && iconLp.width <= 0) {
                iconLp.width = lp.height;
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void a() {
        this.c = (RadioButton) e().inflate(g.abc_list_menu_item_radio, this, false);
        addView(this.c);
    }

    private void d() {
        this.e = (CheckBox) e().inflate(g.abc_list_menu_item_checkbox, this, false);
        addView(this.e);
    }

    public final boolean c() {
        return false;
    }

    private LayoutInflater e() {
        if (this.n == null) {
            this.n = LayoutInflater.from(getContext());
        }
        return this.n;
    }
}
