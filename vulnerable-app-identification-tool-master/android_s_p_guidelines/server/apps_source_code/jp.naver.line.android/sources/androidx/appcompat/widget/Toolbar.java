package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.aa;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.p;
import androidx.customview.view.AbsSavedState;
import defpackage.av;
import defpackage.he;
import defpackage.hi;
import defpackage.hs;
import defpackage.v;
import defpackage.w;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends ViewGroup {
    private static final String TAG = "Toolbar";
    private aa mActionMenuPresenterCallback;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private bf mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private bv mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private m mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final n mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    bw mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    private bx mWrapper;

    public class LayoutParams extends androidx.appcompat.app.ActionBar.LayoutParams {
        int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = 0;
        }

        public LayoutParams() {
            super(-2);
            this.b = 0;
            this.a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((androidx.appcompat.app.ActionBar.LayoutParams) layoutParams);
            this.b = 0;
            this.b = layoutParams.b;
        }

        public LayoutParams(androidx.appcompat.app.ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super((android.view.ViewGroup.LayoutParams) marginLayoutParams);
            this.b = 0;
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }
    }

    public class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        int a;
        boolean b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
            this.b = parcel.readInt() != 0;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
        }
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, defpackage.m.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList();
        this.mHiddenViews = new ArrayList();
        this.mTempMargins = new int[2];
        this.mMenuViewItemClickListener = new n(this) {
            final /* synthetic */ Toolbar a;

            {
                this.a = r1;
            }

            public final boolean a(MenuItem menuItem) {
                return this.a.mOnMenuItemClickListener != null ? this.a.mOnMenuItemClickListener.a(menuItem) : false;
            }
        };
        this.mShowOverflowMenuRunnable = new Runnable(this) {
            final /* synthetic */ Toolbar a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.showOverflowMenu();
            }
        };
        bu a = bu.a(getContext(), attributeSet, v.Toolbar, i, 0);
        this.mTitleTextAppearance = a.g(v.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = a.g(v.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = a.c(v.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = a.c(v.Toolbar_buttonGravity, 48);
        int d = a.d(v.Toolbar_titleMargin, 0);
        if (a.h(v.Toolbar_titleMargins)) {
            d = a.d(v.Toolbar_titleMargins, d);
        }
        this.mTitleMarginBottom = d;
        this.mTitleMarginTop = d;
        this.mTitleMarginEnd = d;
        this.mTitleMarginStart = d;
        d = a.d(v.Toolbar_titleMarginStart, -1);
        if (d >= 0) {
            this.mTitleMarginStart = d;
        }
        d = a.d(v.Toolbar_titleMarginEnd, -1);
        if (d >= 0) {
            this.mTitleMarginEnd = d;
        }
        d = a.d(v.Toolbar_titleMarginTop, -1);
        if (d >= 0) {
            this.mTitleMarginTop = d;
        }
        d = a.d(v.Toolbar_titleMarginBottom, -1);
        if (d >= 0) {
            this.mTitleMarginBottom = d;
        }
        this.mMaxButtonHeight = a.e(v.Toolbar_maxButtonHeight, -1);
        d = a.d(v.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int d2 = a.d(v.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int e = a.e(v.Toolbar_contentInsetLeft, 0);
        int e2 = a.e(v.Toolbar_contentInsetRight, 0);
        ensureContentInsets();
        this.mContentInsets.b(e, e2);
        if (!(d == Integer.MIN_VALUE && d2 == Integer.MIN_VALUE)) {
            this.mContentInsets.a(d, d2);
        }
        this.mContentInsetStartWithNavigation = a.d(v.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = a.d(v.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.mCollapseIcon = a.a(v.Toolbar_collapseIcon);
        this.mCollapseDescription = a.c(v.Toolbar_collapseContentDescription);
        CharSequence c = a.c(v.Toolbar_title);
        if (!TextUtils.isEmpty(c)) {
            setTitle(c);
        }
        c = a.c(v.Toolbar_subtitle);
        if (!TextUtils.isEmpty(c)) {
            setSubtitle(c);
        }
        this.mPopupContext = getContext();
        setPopupTheme(a.g(v.Toolbar_popupTheme, 0));
        Drawable a2 = a.a(v.Toolbar_navigationIcon);
        if (a2 != null) {
            setNavigationIcon(a2);
        }
        c = a.c(v.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(c)) {
            setNavigationContentDescription(c);
        }
        a2 = a.a(v.Toolbar_logo);
        if (a2 != null) {
            setLogo(a2);
        }
        c = a.c(v.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(c)) {
            setLogoDescription(c);
        }
        if (a.h(v.Toolbar_titleTextColor)) {
            setTitleTextColor(a.b(v.Toolbar_titleTextColor, -1));
        }
        if (a.h(v.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a.b(v.Toolbar_subtitleTextColor, -1));
        }
        a.a();
    }

    public void setPopupTheme(int i) {
        if (this.mPopupTheme != i) {
            this.mPopupTheme = i;
            if (i == 0) {
                this.mPopupContext = getContext();
                return;
            }
            this.mPopupContext = new ContextThemeWrapper(getContext(), i);
        }
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public void setTitleMargin(int i, int i2, int i3, int i4) {
        this.mTitleMarginStart = i;
        this.mTitleMarginTop = i2;
        this.mTitleMarginEnd = i3;
        this.mTitleMarginBottom = i4;
        requestLayout();
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public void setTitleMarginStart(int i) {
        this.mTitleMarginStart = i;
        requestLayout();
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    public void setTitleMarginTop(int i) {
        this.mTitleMarginTop = i;
        requestLayout();
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public void setTitleMarginEnd(int i) {
        this.mTitleMarginEnd = i;
        requestLayout();
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public void setTitleMarginBottom(int i) {
        this.mTitleMarginBottom = i;
        requestLayout();
    }

    public void onRtlPropertiesChanged(int i) {
        if (VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        ensureContentInsets();
        bf bfVar = this.mContentInsets;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        bfVar.a(z);
    }

    public void setLogo(int i) {
        setLogo(w.b(getContext(), i));
    }

    public boolean canShowOverflowMenu() {
        return getVisibility() == 0 && this.mMenuView != null && this.mMenuView.b();
    }

    public boolean isOverflowMenuShowing() {
        return this.mMenuView != null && this.mMenuView.h();
    }

    public boolean isOverflowMenuShowPending() {
        return this.mMenuView != null && this.mMenuView.i();
    }

    public boolean showOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.f();
    }

    public boolean hideOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.g();
    }

    public void setMenu(l lVar, ActionMenuPresenter actionMenuPresenter) {
        if (lVar != null || this.mMenuView != null) {
            ensureMenuView();
            l e = this.mMenuView.e();
            if (e != lVar) {
                if (e != null) {
                    e.removeMenuPresenter(this.mOuterActionMenuPresenter);
                    e.removeMenuPresenter(this.mExpandedMenuPresenter);
                }
                if (this.mExpandedMenuPresenter == null) {
                    this.mExpandedMenuPresenter = new bv(this);
                }
                actionMenuPresenter.a(true);
                if (lVar != null) {
                    lVar.addMenuPresenter(actionMenuPresenter, this.mPopupContext);
                    lVar.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
                } else {
                    actionMenuPresenter.initForMenu(this.mPopupContext, null);
                    this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
                    actionMenuPresenter.updateMenuView(true);
                    this.mExpandedMenuPresenter.updateMenuView(true);
                }
                this.mMenuView.setPopupTheme(this.mPopupTheme);
                this.mMenuView.setPresenter(actionMenuPresenter);
                this.mOuterActionMenuPresenter = actionMenuPresenter;
            }
        }
    }

    public void dismissPopupMenus() {
        if (this.mMenuView != null) {
            this.mMenuView.j();
        }
    }

    public boolean isTitleTruncated() {
        if (this.mTitleTextView == null) {
            return false;
        }
        Layout layout = this.mTitleTextView.getLayout();
        if (layout == null) {
            return false;
        }
        int lineCount = layout.getLineCount();
        for (int i = 0; i < lineCount; i++) {
            if (layout.getEllipsisCount(i) > 0) {
                return true;
            }
        }
        return false;
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            ensureLogoView();
            if (!isChildOrHidden(this.mLogoView)) {
                addSystemView(this.mLogoView, true);
            }
        } else if (this.mLogoView != null && isChildOrHidden(this.mLogoView)) {
            removeView(this.mLogoView);
            this.mHiddenViews.remove(this.mLogoView);
        }
        if (this.mLogoView != null) {
            this.mLogoView.setImageDrawable(drawable);
        }
    }

    public Drawable getLogo() {
        return this.mLogoView != null ? this.mLogoView.getDrawable() : null;
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureLogoView();
        }
        if (this.mLogoView != null) {
            this.mLogoView.setContentDescription(charSequence);
        }
    }

    public CharSequence getLogoDescription() {
        return this.mLogoView != null ? this.mLogoView.getContentDescription() : null;
    }

    private void ensureLogoView() {
        if (this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(getContext());
        }
    }

    public boolean hasExpandedActionView() {
        return (this.mExpandedMenuPresenter == null || this.mExpandedMenuPresenter.b == null) ? false : true;
    }

    public void collapseActionView() {
        p pVar = this.mExpandedMenuPresenter == null ? null : this.mExpandedMenuPresenter.b;
        if (pVar != null) {
            pVar.collapseActionView();
        }
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                this.mTitleTextView = new AppCompatTextView(context);
                this.mTitleTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TruncateAt.END);
                if (this.mTitleTextAppearance != 0) {
                    this.mTitleTextView.setTextAppearance(context, this.mTitleTextAppearance);
                }
                if (this.mTitleTextColor != 0) {
                    this.mTitleTextView.setTextColor(this.mTitleTextColor);
                }
            }
            if (!isChildOrHidden(this.mTitleTextView)) {
                addSystemView(this.mTitleTextView, true);
            }
        } else if (this.mTitleTextView != null && isChildOrHidden(this.mTitleTextView)) {
            removeView(this.mTitleTextView);
            this.mHiddenViews.remove(this.mTitleTextView);
        }
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                this.mSubtitleTextView = new AppCompatTextView(context);
                this.mSubtitleTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TruncateAt.END);
                if (this.mSubtitleTextAppearance != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, this.mSubtitleTextAppearance);
                }
                if (this.mSubtitleTextColor != 0) {
                    this.mSubtitleTextView.setTextColor(this.mSubtitleTextColor);
                }
            }
            if (!isChildOrHidden(this.mSubtitleTextView)) {
                addSystemView(this.mSubtitleTextView, true);
            }
        } else if (this.mSubtitleTextView != null && isChildOrHidden(this.mSubtitleTextView)) {
            removeView(this.mSubtitleTextView);
            this.mHiddenViews.remove(this.mSubtitleTextView);
        }
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setTitleTextAppearance(Context context, int i) {
        this.mTitleTextAppearance = i;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextAppearance(context, i);
        }
    }

    public void setSubtitleTextAppearance(Context context, int i) {
        this.mSubtitleTextAppearance = i;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextAppearance(context, i);
        }
    }

    public void setTitleTextColor(int i) {
        this.mTitleTextColor = i;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextColor(i);
        }
    }

    public void setSubtitleTextColor(int i) {
        this.mSubtitleTextColor = i;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextColor(i);
        }
    }

    public CharSequence getNavigationContentDescription() {
        return this.mNavButtonView != null ? this.mNavButtonView.getContentDescription() : null;
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureNavButtonView();
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(w.b(getContext(), i));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            ensureNavButtonView();
            if (!isChildOrHidden(this.mNavButtonView)) {
                addSystemView(this.mNavButtonView, true);
            }
        } else if (this.mNavButtonView != null && isChildOrHidden(this.mNavButtonView)) {
            removeView(this.mNavButtonView);
            this.mHiddenViews.remove(this.mNavButtonView);
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setImageDrawable(drawable);
        }
    }

    public Drawable getNavigationIcon() {
        return this.mNavButtonView != null ? this.mNavButtonView.getDrawable() : null;
    }

    public void setNavigationOnClickListener(OnClickListener onClickListener) {
        ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public Menu getMenu() {
        ensureMenu();
        return this.mMenuView.d();
    }

    public void setOverflowIcon(Drawable drawable) {
        ensureMenu();
        this.mMenuView.setOverflowIcon(drawable);
    }

    public Drawable getOverflowIcon() {
        ensureMenu();
        return this.mMenuView.a();
    }

    private void ensureMenu() {
        ensureMenuView();
        if (this.mMenuView.e() == null) {
            l lVar = (l) this.mMenuView.d();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new bv(this);
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            lVar.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    private void ensureMenuView() {
        if (this.mMenuView == null) {
            this.mMenuView = new ActionMenuView(getContext());
            this.mMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            android.view.ViewGroup.LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.a = 8388613 | (this.mButtonGravity & 112);
            this.mMenuView.setLayoutParams(generateDefaultLayoutParams);
            addSystemView(this.mMenuView, false);
        }
    }

    private MenuInflater getMenuInflater() {
        return new av(getContext());
    }

    public void inflateMenu(int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    public void setOnMenuItemClickListener(bw bwVar) {
        this.mOnMenuItemClickListener = bwVar;
    }

    public void setContentInsetsRelative(int i, int i2) {
        ensureContentInsets();
        this.mContentInsets.a(i, i2);
    }

    public int getContentInsetStart() {
        return this.mContentInsets != null ? this.mContentInsets.c() : 0;
    }

    public int getContentInsetEnd() {
        return this.mContentInsets != null ? this.mContentInsets.d() : 0;
    }

    public void setContentInsetsAbsolute(int i, int i2) {
        ensureContentInsets();
        this.mContentInsets.b(i, i2);
    }

    public int getContentInsetLeft() {
        return this.mContentInsets != null ? this.mContentInsets.a() : 0;
    }

    public int getContentInsetRight() {
        return this.mContentInsets != null ? this.mContentInsets.b() : 0;
    }

    public int getContentInsetStartWithNavigation() {
        if (this.mContentInsetStartWithNavigation != Integer.MIN_VALUE) {
            return this.mContentInsetStartWithNavigation;
        }
        return getContentInsetStart();
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public int getContentInsetEndWithActions() {
        if (this.mContentInsetEndWithActions != Integer.MIN_VALUE) {
            return this.mContentInsetEndWithActions;
        }
        return getContentInsetEnd();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0));
        }
        return getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        Object obj;
        if (this.mMenuView != null) {
            l e = this.mMenuView.e();
            if (e != null && e.hasVisibleItems()) {
                obj = 1;
                if (obj == null) {
                    return Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
                }
                return getContentInsetEnd();
            }
        }
        obj = null;
        if (obj == null) {
            return getContentInsetEnd();
        }
        return Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
    }

    public int getCurrentContentInsetLeft() {
        if (hs.g(this) == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (hs.g(this) == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    private void ensureNavButtonView() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(getContext(), null, defpackage.m.toolbarNavigationButtonStyle);
            android.view.ViewGroup.LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.a = 8388611 | (this.mButtonGravity & 112);
            this.mNavButtonView.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            this.mCollapseButtonView = new AppCompatImageButton(getContext(), null, defpackage.m.toolbarNavigationButtonStyle);
            this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            android.view.ViewGroup.LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.a = 8388611 | (this.mButtonGravity & 112);
            generateDefaultLayoutParams.b = 2;
            this.mCollapseButtonView.setLayoutParams(generateDefaultLayoutParams);
            this.mCollapseButtonView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ Toolbar a;

                {
                    this.a = r1;
                }

                public final void onClick(View view) {
                    this.a.collapseActionView();
                }
            });
        }
    }

    private void addSystemView(View view, boolean z) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        } else if (checkLayoutParams(layoutParams)) {
            layoutParams = (LayoutParams) layoutParams;
        } else {
            layoutParams = generateLayoutParams(layoutParams);
        }
        layoutParams.b = 1;
        if (!z || this.mExpandedActionView == null) {
            addView(view, layoutParams);
            return;
        }
        view.setLayoutParams(layoutParams);
        this.mHiddenViews.add(view);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        if (!(this.mExpandedMenuPresenter == null || this.mExpandedMenuPresenter.b == null)) {
            savedState.a = this.mExpandedMenuPresenter.b.getItemId();
        }
        savedState.b = isOverflowMenuShowing();
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            Menu e = this.mMenuView != null ? this.mMenuView.e() : null;
            if (!(savedState.a == 0 || this.mExpandedMenuPresenter == null || e == null)) {
                MenuItem findItem = e.findItem(savedState.a);
                if (findItem != null) {
                    findItem.expandActionView();
                }
            }
            if (savedState.b) {
                postShowOverflowMenu();
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    private void postShowOverflowMenu() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    private void measureChildConstrained(View view, int i, int i2, int i3, int i4, int i5) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        i = getChildMeasureSpec(i, (((getPaddingLeft() + getPaddingRight()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin) + i2, marginLayoutParams.width);
        i2 = getChildMeasureSpec(i3, (((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin) + i4, marginLayoutParams.height);
        i3 = MeasureSpec.getMode(i2);
        if (i3 != 1073741824 && i5 >= 0) {
            if (i3 != 0) {
                i5 = Math.min(MeasureSpec.getSize(i2), i5);
            }
            i2 = MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(i, i2);
    }

    private int measureChildCollapseMargins(View view, int i, int i2, int i3, int i4, int[] iArr) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + max) + i2, marginLayoutParams.width), getChildMeasureSpec(i3, (((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin) + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private boolean shouldCollapse() {
        if (!this.mCollapsible) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (shouldLayout(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int measuredWidth;
        int max;
        int combineMeasuredStates;
        int measuredHeight;
        int combineMeasuredStates2;
        int[] iArr = this.mTempMargins;
        if (cd.a(this)) {
            i3 = 1;
            i4 = 0;
        } else {
            i3 = 0;
            i4 = 1;
        }
        if (shouldLayout(r7.mNavButtonView)) {
            measureChildConstrained(r7.mNavButtonView, i, 0, i2, 0, r7.mMaxButtonHeight);
            measuredWidth = r7.mNavButtonView.getMeasuredWidth() + getHorizontalMargins(r7.mNavButtonView);
            max = Math.max(0, r7.mNavButtonView.getMeasuredHeight() + getVerticalMargins(r7.mNavButtonView));
            combineMeasuredStates = View.combineMeasuredStates(0, r7.mNavButtonView.getMeasuredState());
        } else {
            measuredWidth = 0;
            max = 0;
            combineMeasuredStates = 0;
        }
        if (shouldLayout(r7.mCollapseButtonView)) {
            measureChildConstrained(r7.mCollapseButtonView, i, 0, i2, 0, r7.mMaxButtonHeight);
            measuredWidth = r7.mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(r7.mCollapseButtonView);
            max = Math.max(max, r7.mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(r7.mCollapseButtonView));
            combineMeasuredStates = View.combineMeasuredStates(combineMeasuredStates, r7.mCollapseButtonView.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max2 = Math.max(currentContentInsetStart, measuredWidth) + 0;
        iArr[i3] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (shouldLayout(r7.mMenuView)) {
            measureChildConstrained(r7.mMenuView, i, max2, i2, 0, r7.mMaxButtonHeight);
            measuredWidth = r7.mMenuView.getMeasuredWidth() + getHorizontalMargins(r7.mMenuView);
            max = Math.max(max, r7.mMenuView.getMeasuredHeight() + getVerticalMargins(r7.mMenuView));
            combineMeasuredStates = View.combineMeasuredStates(combineMeasuredStates, r7.mMenuView.getMeasuredState());
        } else {
            measuredWidth = 0;
        }
        currentContentInsetStart = getCurrentContentInsetEnd();
        i3 = max2 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[i4] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (shouldLayout(r7.mExpandedActionView)) {
            i3 += measureChildCollapseMargins(r7.mExpandedActionView, i, i3, i2, 0, iArr);
            max = Math.max(max, r7.mExpandedActionView.getMeasuredHeight() + getVerticalMargins(r7.mExpandedActionView));
            combineMeasuredStates = View.combineMeasuredStates(combineMeasuredStates, r7.mExpandedActionView.getMeasuredState());
        }
        if (shouldLayout(r7.mLogoView)) {
            i3 += measureChildCollapseMargins(r7.mLogoView, i, i3, i2, 0, iArr);
            max = Math.max(max, r7.mLogoView.getMeasuredHeight() + getVerticalMargins(r7.mLogoView));
            combineMeasuredStates = View.combineMeasuredStates(combineMeasuredStates, r7.mLogoView.getMeasuredState());
        }
        i4 = getChildCount();
        max2 = max;
        max = i3;
        for (i3 = 0; i3 < i4; i3++) {
            View childAt = getChildAt(i3);
            if (((LayoutParams) childAt.getLayoutParams()).b == 0 && shouldLayout(childAt)) {
                max += measureChildCollapseMargins(childAt, i, max, i2, 0, iArr);
                max2 = Math.max(max2, childAt.getMeasuredHeight() + getVerticalMargins(childAt));
                combineMeasuredStates = View.combineMeasuredStates(combineMeasuredStates, childAt.getMeasuredState());
            }
        }
        i3 = r7.mTitleMarginTop + r7.mTitleMarginBottom;
        i4 = r7.mTitleMarginStart + r7.mTitleMarginEnd;
        if (shouldLayout(r7.mTitleTextView)) {
            measureChildCollapseMargins(r7.mTitleTextView, i, max + i4, i2, i3, iArr);
            measuredWidth = r7.mTitleTextView.getMeasuredWidth() + getHorizontalMargins(r7.mTitleTextView);
            measuredHeight = r7.mTitleTextView.getMeasuredHeight() + getVerticalMargins(r7.mTitleTextView);
            combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, r7.mTitleTextView.getMeasuredState());
            combineMeasuredStates = measuredWidth;
        } else {
            combineMeasuredStates2 = combineMeasuredStates;
            combineMeasuredStates = 0;
            measuredHeight = 0;
        }
        if (shouldLayout(r7.mSubtitleTextView)) {
            int i5 = measuredHeight + i3;
            i3 = combineMeasuredStates2;
            combineMeasuredStates = Math.max(combineMeasuredStates, measureChildCollapseMargins(r7.mSubtitleTextView, i, max + i4, i2, i5, iArr));
            measuredHeight += r7.mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(r7.mSubtitleTextView);
            combineMeasuredStates2 = View.combineMeasuredStates(i3, r7.mSubtitleTextView.getMeasuredState());
        } else {
            i3 = combineMeasuredStates2;
        }
        max += combineMeasuredStates;
        measuredWidth = Math.max(max2, measuredHeight) + (getPaddingTop() + getPaddingBottom());
        int i6 = i;
        currentContentInsetStart = View.resolveSizeAndState(Math.max(max + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i6, -16777216 & combineMeasuredStates2);
        measuredWidth = View.resolveSizeAndState(Math.max(measuredWidth, getSuggestedMinimumHeight()), i2, combineMeasuredStates2 << 16);
        if (shouldCollapse()) {
            measuredWidth = 0;
        }
        setMeasuredDimension(currentContentInsetStart, measuredWidth);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int layoutChildRight;
        int currentContentInsetLeft;
        int currentContentInsetRight;
        int max;
        boolean shouldLayout;
        boolean shouldLayout2;
        int i6;
        int i7;
        LayoutParams layoutParams;
        int i8;
        LayoutParams layoutParams2;
        LayoutParams layoutParams3;
        int i9;
        Object obj;
        int i10;
        int i11;
        int i12;
        LayoutParams layoutParams4;
        int i13;
        Toolbar toolbar = this;
        Object obj2 = hs.g(this) == 1 ? 1 : null;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i14 = width - paddingRight;
        int[] iArr = toolbar.mTempMargins;
        iArr[1] = 0;
        iArr[0] = 0;
        int n = hs.n(this);
        n = n >= 0 ? Math.min(n, i4 - i2) : 0;
        if (!shouldLayout(toolbar.mNavButtonView)) {
            i5 = paddingLeft;
        } else if (obj2 != null) {
            layoutChildRight = layoutChildRight(toolbar.mNavButtonView, i14, iArr, n);
            i5 = paddingLeft;
            if (shouldLayout(toolbar.mCollapseButtonView)) {
                if (obj2 == null) {
                    layoutChildRight = layoutChildRight(toolbar.mCollapseButtonView, layoutChildRight, iArr, n);
                } else {
                    i5 = layoutChildLeft(toolbar.mCollapseButtonView, i5, iArr, n);
                }
            }
            if (shouldLayout(toolbar.mMenuView)) {
                if (obj2 == null) {
                    i5 = layoutChildLeft(toolbar.mMenuView, i5, iArr, n);
                } else {
                    layoutChildRight = layoutChildRight(toolbar.mMenuView, layoutChildRight, iArr, n);
                }
            }
            currentContentInsetLeft = getCurrentContentInsetLeft();
            currentContentInsetRight = getCurrentContentInsetRight();
            iArr[0] = Math.max(0, currentContentInsetLeft - i5);
            iArr[1] = Math.max(0, currentContentInsetRight - (i14 - layoutChildRight));
            max = Math.max(i5, currentContentInsetLeft);
            i14 = Math.min(layoutChildRight, i14 - currentContentInsetRight);
            if (shouldLayout(toolbar.mExpandedActionView)) {
                if (obj2 == null) {
                    i14 = layoutChildRight(toolbar.mExpandedActionView, i14, iArr, n);
                } else {
                    max = layoutChildLeft(toolbar.mExpandedActionView, max, iArr, n);
                }
            }
            if (shouldLayout(toolbar.mLogoView)) {
                if (obj2 == null) {
                    i14 = layoutChildRight(toolbar.mLogoView, i14, iArr, n);
                } else {
                    max = layoutChildLeft(toolbar.mLogoView, max, iArr, n);
                }
            }
            shouldLayout = shouldLayout(toolbar.mTitleTextView);
            shouldLayout2 = shouldLayout(toolbar.mSubtitleTextView);
            if (shouldLayout) {
                i6 = paddingRight;
                i7 = 0;
            } else {
                layoutParams = (LayoutParams) toolbar.mTitleTextView.getLayoutParams();
                i6 = paddingRight;
                i7 = ((layoutParams.topMargin + toolbar.mTitleTextView.getMeasuredHeight()) + layoutParams.bottomMargin) + 0;
            }
            if (shouldLayout2) {
                i8 = width;
            } else {
                layoutParams2 = (LayoutParams) toolbar.mSubtitleTextView.getLayoutParams();
                i8 = width;
                i7 += (layoutParams2.topMargin + toolbar.mSubtitleTextView.getMeasuredHeight()) + layoutParams2.bottomMargin;
            }
            if (!shouldLayout || shouldLayout2) {
                layoutParams3 = (LayoutParams) (shouldLayout ? toolbar.mTitleTextView : toolbar.mSubtitleTextView).getLayoutParams();
                layoutParams2 = (LayoutParams) (shouldLayout2 ? toolbar.mSubtitleTextView : toolbar.mTitleTextView).getLayoutParams();
                if ((shouldLayout || toolbar.mTitleTextView.getMeasuredWidth() <= 0) && (!shouldLayout2 || toolbar.mSubtitleTextView.getMeasuredWidth() <= 0)) {
                    i9 = paddingLeft;
                    obj = null;
                } else {
                    i9 = paddingLeft;
                    obj = 1;
                }
                paddingLeft = toolbar.mGravity & 112;
                i10 = n;
                if (paddingLeft != 48) {
                    i11 = max;
                    paddingTop = (getPaddingTop() + layoutParams3.topMargin) + toolbar.mTitleMarginTop;
                } else if (paddingLeft == 80) {
                    paddingLeft = (((height - paddingTop) - paddingBottom) - i7) / 2;
                    i11 = max;
                    if (paddingLeft >= layoutParams3.topMargin + toolbar.mTitleMarginTop) {
                        paddingLeft = layoutParams3.topMargin + toolbar.mTitleMarginTop;
                    } else {
                        height = (((height - paddingBottom) - i7) - paddingLeft) - paddingTop;
                        if (height < layoutParams3.bottomMargin + toolbar.mTitleMarginBottom) {
                            paddingLeft = Math.max(0, paddingLeft - ((layoutParams2.bottomMargin + toolbar.mTitleMarginBottom) - height));
                        }
                    }
                    paddingTop += paddingLeft;
                } else {
                    i11 = max;
                    paddingTop = (((height - paddingBottom) - layoutParams2.bottomMargin) - toolbar.mTitleMarginBottom) - i7;
                }
                if (obj2 == null) {
                    if (obj == null) {
                        i7 = toolbar.mTitleMarginStart;
                        i12 = 1;
                    } else {
                        i12 = 1;
                        i7 = 0;
                    }
                    i7 -= iArr[i12];
                    i14 -= Math.max(0, i7);
                    iArr[i12] = Math.max(0, -i7);
                    if (shouldLayout) {
                        max = i14;
                    } else {
                        layoutParams4 = (LayoutParams) toolbar.mTitleTextView.getLayoutParams();
                        max = i14 - toolbar.mTitleTextView.getMeasuredWidth();
                        i7 = toolbar.mTitleTextView.getMeasuredHeight() + paddingTop;
                        toolbar.mTitleTextView.layout(max, paddingTop, i14, i7);
                        max -= toolbar.mTitleMarginEnd;
                        paddingTop = i7 + layoutParams4.bottomMargin;
                    }
                    if (shouldLayout2) {
                        i7 = i14;
                    } else {
                        layoutParams4 = (LayoutParams) toolbar.mSubtitleTextView.getLayoutParams();
                        paddingTop += layoutParams4.topMargin;
                        toolbar.mSubtitleTextView.layout(i14 - toolbar.mSubtitleTextView.getMeasuredWidth(), paddingTop, i14, toolbar.mSubtitleTextView.getMeasuredHeight() + paddingTop);
                        i7 = i14 - toolbar.mTitleMarginEnd;
                        i12 = layoutParams4.bottomMargin;
                    }
                    if (obj != null) {
                        i14 = Math.min(max, i7);
                    }
                    max = i11;
                } else {
                    if (obj == null) {
                        i13 = toolbar.mTitleMarginStart;
                        paddingRight = 0;
                    } else {
                        paddingRight = 0;
                        i13 = 0;
                    }
                    i12 = i13 - iArr[paddingRight];
                    max = i11 + Math.max(paddingRight, i12);
                    iArr[paddingRight] = Math.max(paddingRight, -i12);
                    if (shouldLayout) {
                        i7 = max;
                    } else {
                        layoutParams4 = (LayoutParams) toolbar.mTitleTextView.getLayoutParams();
                        i7 = toolbar.mTitleTextView.getMeasuredWidth() + max;
                        width = toolbar.mTitleTextView.getMeasuredHeight() + paddingTop;
                        toolbar.mTitleTextView.layout(max, paddingTop, i7, width);
                        i7 += toolbar.mTitleMarginEnd;
                        paddingTop = width + layoutParams4.bottomMargin;
                    }
                    if (shouldLayout2) {
                        width = max;
                    } else {
                        layoutParams4 = (LayoutParams) toolbar.mSubtitleTextView.getLayoutParams();
                        paddingTop += layoutParams4.topMargin;
                        width = toolbar.mSubtitleTextView.getMeasuredWidth() + max;
                        toolbar.mSubtitleTextView.layout(max, paddingTop, width, toolbar.mSubtitleTextView.getMeasuredHeight() + paddingTop);
                        width += toolbar.mTitleMarginEnd;
                        i12 = layoutParams4.bottomMargin;
                    }
                    if (obj != null) {
                        max = Math.max(i7, width);
                    }
                    addCustomViewsWithGravity(toolbar.mTempViews, 3);
                    i12 = toolbar.mTempViews.size();
                    i7 = max;
                    for (max = 0; max < i12; max++) {
                        i7 = layoutChildLeft((View) toolbar.mTempViews.get(max), i7, iArr, i10);
                    }
                    n = i10;
                    addCustomViewsWithGravity(toolbar.mTempViews, 5);
                    i12 = toolbar.mTempViews.size();
                    for (max = 0; max < i12; max++) {
                        i14 = layoutChildRight((View) toolbar.mTempViews.get(max), i14, iArr, n);
                    }
                    addCustomViewsWithGravity(toolbar.mTempViews, 1);
                    i12 = getViewListMeasuredWidth(toolbar.mTempViews, iArr);
                    max = (i9 + (((i8 - i9) - i6) / 2)) - (i12 / 2);
                    i12 += max;
                    if (max >= i7) {
                        i7 = i12 <= i14 ? max - (i12 - i14) : max;
                    }
                    i12 = toolbar.mTempViews.size();
                    for (paddingRight = 
/*
Method generation error in method: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r7_17 'paddingRight' int) = (r7_8 'paddingRight' int), (r7_16 'paddingRight' int), (r7_16 'paddingRight' int) binds: {(r7_8 'paddingRight' int)=B:45:0x0128, (r7_16 'paddingRight' int)=B:98:0x0294, (r7_16 'paddingRight' int)=B:99:0x0296} in method: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:183)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:128)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:143)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:124)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:186)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 34 more

*/

                    private int getViewListMeasuredWidth(List<View> list, int[] iArr) {
                        int i = iArr[0];
                        int i2 = iArr[1];
                        int size = list.size();
                        int i3 = i2;
                        i2 = 0;
                        int i4 = 0;
                        while (i2 < size) {
                            View view = (View) list.get(i2);
                            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                            int i5 = layoutParams.leftMargin - i;
                            i = layoutParams.rightMargin - i3;
                            i3 = Math.max(0, i5);
                            int max = Math.max(0, i);
                            i5 = Math.max(0, -i5);
                            i4 += (i3 + view.getMeasuredWidth()) + max;
                            i2++;
                            i3 = Math.max(0, -i);
                            i = i5;
                        }
                        return i4;
                    }

                    private int layoutChildLeft(View view, int i, int[] iArr, int i2) {
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        int i3 = layoutParams.leftMargin - iArr[0];
                        i += Math.max(0, i3);
                        iArr[0] = Math.max(0, -i3);
                        int childTop = getChildTop(view, i2);
                        i2 = view.getMeasuredWidth();
                        view.layout(i, childTop, i + i2, view.getMeasuredHeight() + childTop);
                        return i + (i2 + layoutParams.rightMargin);
                    }

                    private int layoutChildRight(View view, int i, int[] iArr, int i2) {
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        int i3 = layoutParams.rightMargin - iArr[1];
                        i -= Math.max(0, i3);
                        iArr[1] = Math.max(0, -i3);
                        int childTop = getChildTop(view, i2);
                        i2 = view.getMeasuredWidth();
                        view.layout(i - i2, childTop, i, view.getMeasuredHeight() + childTop);
                        return i - (i2 + layoutParams.leftMargin);
                    }

                    private int getChildTop(View view, int i) {
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        int measuredHeight = view.getMeasuredHeight();
                        i = i > 0 ? (measuredHeight - i) / 2 : 0;
                        int childVerticalGravity = getChildVerticalGravity(layoutParams.a);
                        if (childVerticalGravity == 48) {
                            return getPaddingTop() - i;
                        }
                        if (childVerticalGravity == 80) {
                            return (((getHeight() - getPaddingBottom()) - measuredHeight) - layoutParams.bottomMargin) - i;
                        }
                        i = getPaddingTop();
                        childVerticalGravity = getPaddingBottom();
                        int height = getHeight();
                        int i2 = (((height - i) - childVerticalGravity) - measuredHeight) / 2;
                        if (i2 < layoutParams.topMargin) {
                            i2 = layoutParams.topMargin;
                        } else {
                            height = (((height - childVerticalGravity) - measuredHeight) - i2) - i;
                            if (height < layoutParams.bottomMargin) {
                                i2 = Math.max(0, i2 - (layoutParams.bottomMargin - height));
                            }
                        }
                        return i + i2;
                    }

                    private int getChildVerticalGravity(int i) {
                        i &= 112;
                        return (i == 16 || i == 48 || i == 80) ? i : this.mGravity & 112;
                    }

                    private void addCustomViewsWithGravity(List<View> list, int i) {
                        Object obj = hs.g(this) == 1 ? 1 : null;
                        int childCount = getChildCount();
                        i = he.a(i, hs.g(this));
                        list.clear();
                        View childAt;
                        if (obj != null) {
                            for (childCount--; childCount >= 0; childCount--) {
                                childAt = getChildAt(childCount);
                                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                                if (layoutParams.b == 0 && shouldLayout(childAt) && getChildHorizontalGravity(layoutParams.a) == i) {
                                    list.add(childAt);
                                }
                            }
                            return;
                        }
                        for (int i2 = 0; i2 < childCount; i2++) {
                            childAt = getChildAt(i2);
                            LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                            if (layoutParams2.b == 0 && shouldLayout(childAt) && getChildHorizontalGravity(layoutParams2.a) == i) {
                                list.add(childAt);
                            }
                        }
                    }

                    private int getChildHorizontalGravity(int i) {
                        int g = hs.g(this);
                        i = he.a(i, g) & 7;
                        if (i == 1 || i == 3 || i == 5) {
                            return i;
                        }
                        return g == 1 ? 5 : 3;
                    }

                    private boolean shouldLayout(View view) {
                        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
                    }

                    private int getHorizontalMargins(View view) {
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                        return hi.a(marginLayoutParams) + hi.b(marginLayoutParams);
                    }

                    private int getVerticalMargins(View view) {
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                    }

                    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
                        return new LayoutParams(getContext(), attributeSet);
                    }

                    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
                        if (layoutParams instanceof LayoutParams) {
                            return new LayoutParams((LayoutParams) layoutParams);
                        }
                        if (layoutParams instanceof androidx.appcompat.app.ActionBar.LayoutParams) {
                            return new LayoutParams((androidx.appcompat.app.ActionBar.LayoutParams) layoutParams);
                        }
                        if (layoutParams instanceof MarginLayoutParams) {
                            return new LayoutParams((MarginLayoutParams) layoutParams);
                        }
                        return new LayoutParams(layoutParams);
                    }

                    protected LayoutParams generateDefaultLayoutParams() {
                        return new LayoutParams();
                    }

                    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
                        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
                    }

                    private static boolean isCustomView(View view) {
                        return ((LayoutParams) view.getLayoutParams()).b == 0;
                    }

                    public ao getWrapper() {
                        if (this.mWrapper == null) {
                            this.mWrapper = new bx(this, true);
                        }
                        return this.mWrapper;
                    }

                    void removeChildrenForExpandedActionView() {
                        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                            View childAt = getChildAt(childCount);
                            if (!(((LayoutParams) childAt.getLayoutParams()).b == 2 || childAt == this.mMenuView)) {
                                removeViewAt(childCount);
                                this.mHiddenViews.add(childAt);
                            }
                        }
                    }

                    void addChildrenForExpandedActionView() {
                        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
                            addView((View) this.mHiddenViews.get(size));
                        }
                        this.mHiddenViews.clear();
                    }

                    private boolean isChildOrHidden(View view) {
                        return view.getParent() == this || this.mHiddenViews.contains(view);
                    }

                    public void setCollapsible(boolean z) {
                        this.mCollapsible = z;
                        requestLayout();
                    }

                    public void setMenuCallbacks(aa aaVar, m mVar) {
                        this.mActionMenuPresenterCallback = aaVar;
                        this.mMenuBuilderCallback = mVar;
                        if (this.mMenuView != null) {
                            this.mMenuView.setMenuCallbacks(aaVar, mVar);
                        }
                    }

                    private void ensureContentInsets() {
                        if (this.mContentInsets == null) {
                            this.mContentInsets = new bf();
                        }
                    }

                    ActionMenuPresenter getOuterActionMenuPresenter() {
                        return this.mOuterActionMenuPresenter;
                    }

                    Context getPopupContext() {
                        return this.mPopupContext;
                    }
                }
