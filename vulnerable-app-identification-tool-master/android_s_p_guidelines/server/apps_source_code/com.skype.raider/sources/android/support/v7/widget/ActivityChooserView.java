package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.a.d;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.g;
import android.support.v7.appcompat.a.h;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.menu.r;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class ActivityChooserView extends ViewGroup {
    final a a;
    final FrameLayout b;
    final FrameLayout c;
    android.support.v4.view.b d;
    final DataSetObserver e;
    OnDismissListener f;
    boolean g;
    int h;
    private final b i;
    private final LinearLayoutCompat j;
    private final Drawable k;
    private final ImageView l;
    private final ImageView m;
    private final int n;
    private final OnGlobalLayoutListener o;
    private aa p;
    private boolean q;
    private int r;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static class InnerLayout extends LinearLayoutCompat {
        private static final int[] a = new int[]{16842964};

        public InnerLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            aq a = aq.a(context, attrs, a);
            setBackgroundDrawable(a.a(0));
            a.a();
        }
    }

    private class a extends BaseAdapter {
        final /* synthetic */ ActivityChooserView a;
        private d b;
        private int c = 4;
        private boolean d;
        private boolean e;
        private boolean f;

        a(ActivityChooserView activityChooserView) {
            this.a = activityChooserView;
        }

        public final void a(d dataModel) {
            d oldDataModel = this.a.a.b;
            if (oldDataModel != null && this.a.isShown()) {
                oldDataModel.unregisterObserver(this.a.e);
            }
            this.b = dataModel;
            if (dataModel != null && this.a.isShown()) {
                dataModel.registerObserver(this.a.e);
            }
            notifyDataSetChanged();
        }

        public final int getItemViewType(int position) {
            if (this.f && position == getCount() - 1) {
                return 1;
            }
            return 0;
        }

        public final int getViewTypeCount() {
            return 3;
        }

        public final int getCount() {
            int activityCount = this.b.a();
            if (!(this.d || this.b.b() == null)) {
                activityCount--;
            }
            int count = Math.min(activityCount, this.c);
            if (this.f) {
                return count + 1;
            }
            return count;
        }

        public final Object getItem(int position) {
            switch (getItemViewType(position)) {
                case 0:
                    if (!(this.d || this.b.b() == null)) {
                        position++;
                    }
                    return this.b.a(position);
                case 1:
                    return null;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public final long getItemId(int position) {
            return (long) position;
        }

        public final View getView(int position, View convertView, ViewGroup parent) {
            switch (getItemViewType(position)) {
                case 0:
                    if (convertView == null || convertView.getId() != f.list_item) {
                        convertView = LayoutInflater.from(this.a.getContext()).inflate(g.abc_activity_chooser_view_list_item, parent, false);
                    }
                    PackageManager packageManager = this.a.getContext().getPackageManager();
                    ResolveInfo activity = (ResolveInfo) getItem(position);
                    ((ImageView) convertView.findViewById(f.icon)).setImageDrawable(activity.loadIcon(packageManager));
                    ((TextView) convertView.findViewById(f.title)).setText(activity.loadLabel(packageManager));
                    if (this.d && position == 0 && this.e) {
                        convertView.setActivated(true);
                    } else {
                        convertView.setActivated(false);
                    }
                    return convertView;
                case 1:
                    if (convertView == null || convertView.getId() != 1) {
                        convertView = LayoutInflater.from(this.a.getContext()).inflate(g.abc_activity_chooser_view_list_item, parent, false);
                        convertView.setId(1);
                        ((TextView) convertView.findViewById(f.title)).setText(this.a.getContext().getString(h.abc_activity_chooser_view_see_all));
                    }
                    return convertView;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public final int a() {
            int oldMaxActivityCount = this.c;
            this.c = Integer.MAX_VALUE;
            int contentWidth = 0;
            View itemView = null;
            int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            for (int i = 0; i < count; i++) {
                itemView = getView(i, itemView, null);
                itemView.measure(widthMeasureSpec, heightMeasureSpec);
                contentWidth = Math.max(contentWidth, itemView.getMeasuredWidth());
            }
            this.c = oldMaxActivityCount;
            return contentWidth;
        }

        public final void a(int maxActivityCount) {
            if (this.c != maxActivityCount) {
                this.c = maxActivityCount;
                notifyDataSetChanged();
            }
        }

        public final ResolveInfo b() {
            return this.b.b();
        }

        public final void a(boolean showFooterView) {
            if (this.f != showFooterView) {
                this.f = showFooterView;
                notifyDataSetChanged();
            }
        }

        public final int c() {
            return this.b.a();
        }

        public final int d() {
            return this.b.c();
        }

        public final d e() {
            return this.b;
        }

        public final void a(boolean showDefaultActivity, boolean highlightDefaultActivity) {
            if (this.d != showDefaultActivity || this.e != highlightDefaultActivity) {
                this.d = showDefaultActivity;
                this.e = highlightDefaultActivity;
                notifyDataSetChanged();
            }
        }

        public final boolean f() {
            return this.d;
        }
    }

    private class b implements OnClickListener, OnLongClickListener, OnItemClickListener, OnDismissListener {
        final /* synthetic */ ActivityChooserView a;

        b(ActivityChooserView activityChooserView) {
            this.a = activityChooserView;
        }

        public final void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (((a) parent.getAdapter()).getItemViewType(position)) {
                case 0:
                    this.a.b();
                    if (!this.a.g) {
                        if (!this.a.a.f()) {
                            position++;
                        }
                        Intent launchIntent = this.a.a.e().b(position);
                        if (launchIntent != null) {
                            launchIntent.addFlags(524288);
                            this.a.getContext().startActivity(launchIntent);
                            return;
                        }
                        return;
                    } else if (position > 0) {
                        this.a.a.e().c(position);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    this.a.a(Integer.MAX_VALUE);
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public final void onClick(View view) {
            if (view == this.a.c) {
                this.a.b();
                Intent launchIntent = this.a.a.e().b(this.a.a.e().a(this.a.a.b()));
                if (launchIntent != null) {
                    launchIntent.addFlags(524288);
                    this.a.getContext().startActivity(launchIntent);
                }
            } else if (view == this.a.b) {
                this.a.g = false;
                this.a.a(this.a.h);
            } else {
                throw new IllegalArgumentException();
            }
        }

        public final boolean onLongClick(View view) {
            if (view == this.a.c) {
                if (this.a.a.getCount() > 0) {
                    this.a.g = true;
                    this.a.a(this.a.h);
                }
                return true;
            }
            throw new IllegalArgumentException();
        }

        public final void onDismiss() {
            if (this.a.f != null) {
                this.a.f.onDismiss();
            }
            if (this.a.d != null) {
                this.a.d.a(false);
            }
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public ActivityChooserView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.e = new DataSetObserver(this) {
            final /* synthetic */ ActivityChooserView a;

            {
                this.a = this$0;
            }

            public final void onChanged() {
                super.onChanged();
                this.a.a.notifyDataSetChanged();
            }

            public final void onInvalidated() {
                super.onInvalidated();
                this.a.a.notifyDataSetInvalidated();
            }
        };
        this.o = new OnGlobalLayoutListener(this) {
            final /* synthetic */ ActivityChooserView a;

            {
                this.a = this$0;
            }

            public final void onGlobalLayout() {
                if (!this.a.c()) {
                    return;
                }
                if (this.a.isShown()) {
                    this.a.d().c();
                    if (this.a.d != null) {
                        this.a.d.a(true);
                        return;
                    }
                    return;
                }
                this.a.d().e();
            }
        };
        this.h = 4;
        TypedArray attributesArray = context.obtainStyledAttributes(attrs, j.ActivityChooserView, defStyle, 0);
        this.h = attributesArray.getInt(j.ActivityChooserView_initialActivityCount, 4);
        Drawable expandActivityOverflowButtonDrawable = attributesArray.getDrawable(j.ActivityChooserView_expandActivityOverflowButtonDrawable);
        attributesArray.recycle();
        LayoutInflater.from(getContext()).inflate(g.abc_activity_chooser_view, this, true);
        this.i = new b(this);
        this.j = (LinearLayoutCompat) findViewById(f.activity_chooser_view_content);
        this.k = this.j.getBackground();
        this.c = (FrameLayout) findViewById(f.default_activity_button);
        this.c.setOnClickListener(this.i);
        this.c.setOnLongClickListener(this.i);
        this.m = (ImageView) this.c.findViewById(f.image);
        FrameLayout expandButton = (FrameLayout) findViewById(f.expand_activities_button);
        expandButton.setOnClickListener(this.i);
        expandButton.setAccessibilityDelegate(new AccessibilityDelegate(this) {
            final /* synthetic */ ActivityChooserView a;

            {
                this.a = this$0;
            }

            public final void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                android.support.v4.view.accessibility.b.a(info).q();
            }
        });
        expandButton.setOnTouchListener(new w(this, expandButton) {
            final /* synthetic */ ActivityChooserView a;

            public final r a() {
                return this.a.d();
            }

            protected final boolean b() {
                this.a.a();
                return true;
            }

            protected final boolean c() {
                this.a.b();
                return true;
            }
        });
        this.b = expandButton;
        this.l = (ImageView) expandButton.findViewById(f.image);
        this.l.setImageDrawable(expandActivityOverflowButtonDrawable);
        this.a = new a(this);
        this.a.registerDataSetObserver(new DataSetObserver(this) {
            final /* synthetic */ ActivityChooserView a;

            {
                this.a = this$0;
            }

            public final void onChanged() {
                super.onChanged();
                this.a.e();
            }
        });
        Resources resources = context.getResources();
        this.n = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(d.abc_config_prefDialogWidth));
    }

    public void setActivityChooserModel(d dataModel) {
        this.a.a(dataModel);
        if (d().f()) {
            b();
            a();
        }
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.l.setImageDrawable(drawable);
    }

    public void setExpandActivityOverflowButtonContentDescription(int resourceId) {
        this.l.setContentDescription(getContext().getString(resourceId));
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setProvider(android.support.v4.view.b provider) {
        this.d = provider;
    }

    public final boolean a() {
        if (d().f() || !this.q) {
            return false;
        }
        this.g = false;
        a(this.h);
        return true;
    }

    final void a(int maxActivityCount) {
        if (this.a.e() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        boolean defaultActivityButtonShown;
        getViewTreeObserver().addOnGlobalLayoutListener(this.o);
        if (this.c.getVisibility() == 0) {
            defaultActivityButtonShown = true;
        } else {
            defaultActivityButtonShown = false;
        }
        int activityCount = this.a.c();
        int maxActivityCountOffset;
        if (defaultActivityButtonShown) {
            maxActivityCountOffset = 1;
        } else {
            maxActivityCountOffset = 0;
        }
        if (maxActivityCount == Integer.MAX_VALUE || activityCount <= maxActivityCount + maxActivityCountOffset) {
            this.a.a(false);
            this.a.a(maxActivityCount);
        } else {
            this.a.a(true);
            this.a.a(maxActivityCount - 1);
        }
        aa popupWindow = d();
        if (!popupWindow.f()) {
            if (this.g || !defaultActivityButtonShown) {
                this.a.a(true, defaultActivityButtonShown);
            } else {
                this.a.a(false, false);
            }
            popupWindow.d(Math.min(this.a.a(), this.n));
            popupWindow.c();
            if (this.d != null) {
                this.d.a(true);
            }
            popupWindow.c.setContentDescription(getContext().getString(h.abc_activitychooserview_choose_application));
            popupWindow.c.setSelector(new ColorDrawable(0));
        }
    }

    public final boolean c() {
        return d().f();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        d dataModel = this.a.e();
        if (dataModel != null) {
            dataModel.registerObserver(this.e);
        }
        this.q = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d dataModel = this.a.e();
        if (dataModel != null) {
            dataModel.unregisterObserver(this.e);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.o);
        }
        if (d().f()) {
            b();
        }
        this.q = false;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View child = this.j;
        if (this.c.getVisibility() != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), ErrorDialogData.SUPPRESSED);
        }
        measureChild(child, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(child.getMeasuredWidth(), child.getMeasuredHeight());
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        this.j.layout(0, 0, right - left, bottom - top);
        if (!d().f()) {
            b();
        }
    }

    public void setOnDismissListener(OnDismissListener listener) {
        this.f = listener;
    }

    public void setInitialActivityCount(int itemCount) {
        this.h = itemCount;
    }

    public void setDefaultActionButtonContentDescription(int resourceId) {
        this.r = resourceId;
    }

    final aa d() {
        if (this.p == null) {
            this.p = new aa(getContext());
            this.p.a(this.a);
            this.p.b((View) this);
            this.p.h();
            this.p.a(this.i);
            this.p.a(this.i);
        }
        return this.p;
    }

    final void e() {
        if (this.a.getCount() > 0) {
            this.b.setEnabled(true);
        } else {
            this.b.setEnabled(false);
        }
        int activityCount = this.a.c();
        int historySize = this.a.d();
        if (activityCount == 1 || (activityCount > 1 && historySize > 0)) {
            this.c.setVisibility(0);
            ResolveInfo activity = this.a.b();
            PackageManager packageManager = getContext().getPackageManager();
            this.m.setImageDrawable(activity.loadIcon(packageManager));
            if (this.r != 0) {
                CharSequence label = activity.loadLabel(packageManager);
                this.c.setContentDescription(getContext().getString(this.r, new Object[]{label}));
            }
        } else {
            this.c.setVisibility(8);
        }
        if (this.c.getVisibility() == 0) {
            this.j.setBackgroundDrawable(this.k);
        } else {
            this.j.setBackgroundDrawable(null);
        }
    }

    public final boolean b() {
        if (d().f()) {
            d().e();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.o);
            }
        }
        return true;
    }
}
