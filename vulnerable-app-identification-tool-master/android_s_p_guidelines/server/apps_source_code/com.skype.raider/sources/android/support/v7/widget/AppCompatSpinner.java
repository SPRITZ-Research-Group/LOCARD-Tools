package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.o;
import android.support.v7.appcompat.a.g;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.d;
import android.support.v7.view.menu.r;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

public class AppCompatSpinner extends Spinner implements o {
    private static final int[] a = new int[]{16843505};
    private final f b;
    private final Context c;
    private w d;
    private SpinnerAdapter e;
    private final boolean f;
    private b g;
    private int h;
    private final Rect i;

    private static class a implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter a;
        private ListAdapter b;

        public a(@Nullable SpinnerAdapter adapter, @Nullable Theme dropDownTheme) {
            this.a = adapter;
            if (adapter instanceof ListAdapter) {
                this.b = (ListAdapter) adapter;
            }
            if (dropDownTheme != null && VERSION.SDK_INT >= 23 && (adapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedAdapter = (ThemedSpinnerAdapter) adapter;
                if (themedAdapter.getDropDownViewTheme() != dropDownTheme) {
                    themedAdapter.setDropDownViewTheme(dropDownTheme);
                }
            }
        }

        public final int getCount() {
            return this.a == null ? 0 : this.a.getCount();
        }

        public final Object getItem(int position) {
            return this.a == null ? null : this.a.getItem(position);
        }

        public final long getItemId(int position) {
            return this.a == null ? -1 : this.a.getItemId(position);
        }

        public final View getView(int position, View convertView, ViewGroup parent) {
            return getDropDownView(position, convertView, parent);
        }

        public final View getDropDownView(int position, View convertView, ViewGroup parent) {
            if (this.a == null) {
                return null;
            }
            return this.a.getDropDownView(position, convertView, parent);
        }

        public final boolean hasStableIds() {
            return this.a != null && this.a.hasStableIds();
        }

        public final void registerDataSetObserver(DataSetObserver observer) {
            if (this.a != null) {
                this.a.registerDataSetObserver(observer);
            }
        }

        public final void unregisterDataSetObserver(DataSetObserver observer) {
            if (this.a != null) {
                this.a.unregisterDataSetObserver(observer);
            }
        }

        public final boolean areAllItemsEnabled() {
            ListAdapter adapter = this.b;
            if (adapter != null) {
                return adapter.areAllItemsEnabled();
            }
            return true;
        }

        public final boolean isEnabled(int position) {
            ListAdapter adapter = this.b;
            if (adapter != null) {
                return adapter.isEnabled(position);
            }
            return true;
        }

        public final int getItemViewType(int position) {
            return 0;
        }

        public final int getViewTypeCount() {
            return 1;
        }

        public final boolean isEmpty() {
            return getCount() == 0;
        }
    }

    private class b extends aa {
        ListAdapter a;
        final /* synthetic */ AppCompatSpinner b;
        private CharSequence h;
        private final Rect i = new Rect();

        public b(final AppCompatSpinner appCompatSpinner, Context context, AttributeSet attrs, int defStyleAttr) {
            this.b = appCompatSpinner;
            super(context, attrs, defStyleAttr);
            b((View) appCompatSpinner);
            h();
            d();
            a(new OnItemClickListener(this) {
                final /* synthetic */ b b;

                public final void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                    this.b.b.setSelection(position);
                    if (this.b.b.getOnItemClickListener() != null) {
                        this.b.b.performItemClick(v, position, this.b.a.getItemId(position));
                    }
                    this.b.e();
                }
            });
        }

        public final void a(ListAdapter adapter) {
            super.a(adapter);
            this.a = adapter;
        }

        public final CharSequence a() {
            return this.h;
        }

        public final void a(CharSequence hintText) {
            this.h = hintText;
        }

        public final void c() {
            boolean wasShowing = f();
            b();
            o();
            super.c();
            this.c.setChoiceMode(1);
            int selectedItemPosition = this.b.getSelectedItemPosition();
            u uVar = this.c;
            if (f() && uVar != null) {
                uVar.a(false);
                uVar.setSelection(selectedItemPosition);
                if (uVar.getChoiceMode() != 0) {
                    uVar.setItemChecked(selectedItemPosition, true);
                }
            }
            if (!wasShowing) {
                ViewTreeObserver vto = this.b.getViewTreeObserver();
                if (vto != null) {
                    final OnGlobalLayoutListener layoutListener = new OnGlobalLayoutListener(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = this$1;
                        }

                        public final void onGlobalLayout() {
                            if (this.a.a(this.a.b)) {
                                this.a.b();
                                super.c();
                                return;
                            }
                            this.a.e();
                        }
                    };
                    vto.addOnGlobalLayoutListener(layoutListener);
                    a(new OnDismissListener(this) {
                        final /* synthetic */ b b;

                        public final void onDismiss() {
                            ViewTreeObserver vto = this.b.b.getViewTreeObserver();
                            if (vto != null) {
                                vto.removeGlobalOnLayoutListener(layoutListener);
                            }
                        }
                    });
                }
            }
        }

        final boolean a(View view) {
            return ViewCompat.D(view) && view.getGlobalVisibleRect(this.i);
        }

        final void b() {
            Drawable background = this.g.getBackground();
            int hOffset = 0;
            if (background != null) {
                background.getPadding(this.b.i);
                if (ax.a(this.b)) {
                    hOffset = this.b.i.right;
                } else {
                    hOffset = -this.b.i.left;
                }
            } else {
                Rect b = this.b.i;
                this.b.i.right = 0;
                b.left = 0;
            }
            int spinnerPaddingLeft = this.b.getPaddingLeft();
            int spinnerPaddingRight = this.b.getPaddingRight();
            int spinnerWidth = this.b.getWidth();
            if (this.b.h == -2) {
                int contentWidth = this.b.a((SpinnerAdapter) this.a, this.g.getBackground());
                int contentWidthLimit = (this.b.getContext().getResources().getDisplayMetrics().widthPixels - this.b.i.left) - this.b.i.right;
                if (contentWidth > contentWidthLimit) {
                    contentWidth = contentWidthLimit;
                }
                d(Math.max(contentWidth, (spinnerWidth - spinnerPaddingLeft) - spinnerPaddingRight));
            } else if (this.b.h == -1) {
                d((spinnerWidth - spinnerPaddingLeft) - spinnerPaddingRight);
            } else {
                d(this.b.h);
            }
            if (ax.a(this.b)) {
                hOffset += (spinnerWidth - spinnerPaddingRight) - n();
            } else {
                hOffset += spinnerPaddingLeft;
            }
            a(hOffset);
        }
    }

    public AppCompatSpinner(Context context) {
        this(context, null);
    }

    public AppCompatSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.a.a.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, (byte) 0);
    }

    private AppCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, byte b) {
        this(context, attrs, defStyleAttr, -1);
    }

    private AppCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        Context dVar;
        AppCompatSpinner this;
        super(context, attrs, defStyleAttr);
        this.i = new Rect();
        aq a = aq.a(context, attrs, j.Spinner, defStyleAttr, 0);
        this.b = new f(this);
        int popupThemeResId = a.g(j.Spinner_popupTheme, 0);
        if (popupThemeResId != 0) {
            dVar = new d(context, popupThemeResId);
            this = this;
        } else if (VERSION.SDK_INT < 23) {
            dVar = context;
            this = this;
        } else {
            dVar = null;
            this = this;
        }
        this.c = dVar;
        if (this.c != null) {
            if (VERSION.SDK_INT >= 11) {
                TypedArray aa = null;
                try {
                    aa = context.obtainStyledAttributes(attrs, a, defStyleAttr, 0);
                    if (aa.hasValue(0)) {
                        mode = aa.getInt(0, 0);
                    }
                    if (aa != null) {
                        aa.recycle();
                    }
                } catch (Exception e) {
                    if (aa != null) {
                        aa.recycle();
                    }
                } catch (Throwable th) {
                    if (aa != null) {
                        aa.recycle();
                    }
                }
            } else {
                mode = 1;
            }
            if (mode == 1) {
                final b popup = new b(this, this.c, attrs, defStyleAttr);
                aq pa = aq.a(this.c, attrs, j.Spinner, defStyleAttr, 0);
                this.h = pa.f(j.Spinner_android_dropDownWidth, -2);
                popup.a(pa.a(j.Spinner_android_popupBackground));
                popup.a(a.d(j.Spinner_android_prompt));
                pa.a();
                this.g = popup;
                this.d = new w(this, this) {
                    final /* synthetic */ AppCompatSpinner b;

                    public final r a() {
                        return popup;
                    }

                    public final boolean b() {
                        if (!this.b.g.f()) {
                            this.b.g.c();
                        }
                        return true;
                    }
                };
            }
        }
        CharSequence[] entries = a.g(j.Spinner_android_entries);
        if (entries != null) {
            SpinnerAdapter adapter = new ArrayAdapter(context, 17367048, entries);
            adapter.setDropDownViewResource(g.support_simple_spinner_dropdown_item);
            setAdapter(adapter);
        }
        a.a();
        this.f = true;
        if (this.e != null) {
            setAdapter(this.e);
            this.e = null;
        }
        this.b.a(attrs, defStyleAttr);
    }

    public Context getPopupContext() {
        if (this.g != null) {
            return this.c;
        }
        if (VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    public void setPopupBackgroundDrawable(Drawable background) {
        if (this.g != null) {
            this.g.a(background);
        } else if (VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(background);
        }
    }

    public void setPopupBackgroundResource(@DrawableRes int resId) {
        setPopupBackgroundDrawable(android.support.v7.content.res.b.b(getPopupContext(), resId));
    }

    public Drawable getPopupBackground() {
        if (this.g != null) {
            return this.g.g.getBackground();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    public void setDropDownVerticalOffset(int pixels) {
        if (this.g != null) {
            this.g.b(pixels);
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(pixels);
        }
    }

    public int getDropDownVerticalOffset() {
        if (this.g != null) {
            return this.g.m();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    public void setDropDownHorizontalOffset(int pixels) {
        if (this.g != null) {
            this.g.a(pixels);
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(pixels);
        }
    }

    public int getDropDownHorizontalOffset() {
        if (this.g != null) {
            return this.g.l();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    public void setDropDownWidth(int pixels) {
        if (this.g != null) {
            this.h = pixels;
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(pixels);
        }
    }

    public int getDropDownWidth() {
        if (this.g != null) {
            return this.h;
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    public void setAdapter(SpinnerAdapter adapter) {
        if (this.f) {
            super.setAdapter(adapter);
            if (this.g != null) {
                this.g.a(new a(adapter, (this.c == null ? getContext() : this.c).getTheme()));
                return;
            }
            return;
        }
        this.e = adapter;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.g != null && this.g.f()) {
            this.g.e();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.d == null || !this.d.onTouch(this, event)) {
            return super.onTouchEvent(event);
        }
        return true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.g != null && MeasureSpec.getMode(widthMeasureSpec) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), MeasureSpec.getSize(widthMeasureSpec)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        if (this.g == null) {
            return super.performClick();
        }
        if (!this.g.f()) {
            this.g.c();
        }
        return true;
    }

    public void setPrompt(CharSequence prompt) {
        if (this.g != null) {
            this.g.a(prompt);
        } else {
            super.setPrompt(prompt);
        }
    }

    public CharSequence getPrompt() {
        return this.g != null ? this.g.a() : super.getPrompt();
    }

    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (this.b != null) {
            this.b.a(resId);
        }
    }

    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
        if (this.b != null) {
            this.b.a();
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList tint) {
        if (this.b != null) {
            this.b.a(tint);
        }
    }

    @Nullable
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final ColorStateList a() {
        return this.b != null ? this.b.b() : null;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable Mode tintMode) {
        if (this.b != null) {
            this.b.a(tintMode);
        }
    }

    @Nullable
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final Mode d() {
        return this.b != null ? this.b.c() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.b != null) {
            this.b.d();
        }
    }

    final int a(SpinnerAdapter adapter, Drawable background) {
        if (adapter == null) {
            return 0;
        }
        int width = 0;
        View itemView = null;
        int itemType = 0;
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int start = Math.max(0, getSelectedItemPosition());
        int end = Math.min(adapter.getCount(), start + 15);
        for (int i = Math.max(0, start - (15 - (end - start))); i < end; i++) {
            int positionType = adapter.getItemViewType(i);
            if (positionType != itemType) {
                itemType = positionType;
                itemView = null;
            }
            itemView = adapter.getView(i, itemView, this);
            if (itemView.getLayoutParams() == null) {
                itemView.setLayoutParams(new LayoutParams(-2, -2));
            }
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            width = Math.max(width, itemView.getMeasuredWidth());
        }
        if (background == null) {
            return width;
        }
        background.getPadding(this.i);
        return width + (this.i.left + this.i.right);
    }
}
