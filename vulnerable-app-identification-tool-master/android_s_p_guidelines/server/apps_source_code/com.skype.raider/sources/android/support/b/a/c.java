package android.support.b.a;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class c extends h implements b {
    final Callback a;
    private a c;
    private Context d;
    private ArgbEvaluator e;
    private AnimatorListener f;
    private ArrayList<Object> g;

    private static class a extends ConstantState {
        int a;
        i b;
        AnimatorSet c;
        android.support.v4.util.a<Animator, String> d;
        private ArrayList<Animator> e;

        public final Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public final Drawable newDrawable(Resources res) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public final int getChangingConfigurations() {
            return this.a;
        }

        public final void a() {
            if (this.c == null) {
                this.c = new AnimatorSet();
            }
            this.c.playTogether(this.e);
        }
    }

    @RequiresApi(24)
    private static class b extends ConstantState {
        private final ConstantState a;

        public b(ConstantState state) {
            this.a = state;
        }

        public final Drawable newDrawable() {
            c drawableCompat = new c();
            drawableCompat.b = this.a.newDrawable();
            drawableCompat.b.setCallback(drawableCompat.a);
            return drawableCompat;
        }

        public final Drawable newDrawable(Resources res) {
            c drawableCompat = new c();
            drawableCompat.b = this.a.newDrawable(res);
            drawableCompat.b.setCallback(drawableCompat.a);
            return drawableCompat;
        }

        public final Drawable newDrawable(Resources res, Theme theme) {
            c drawableCompat = new c();
            drawableCompat.b = this.a.newDrawable(res, theme);
            drawableCompat.b.setCallback(drawableCompat.a);
            return drawableCompat;
        }

        public final boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        public final int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }
    }

    public final /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public final /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public final /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public final /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public final /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public final /* bridge */ /* synthetic */ boolean getPadding(Rect x0) {
        return super.getPadding(x0);
    }

    public final /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public final /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public final /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public final /* bridge */ /* synthetic */ void setChangingConfigurations(int x0) {
        super.setChangingConfigurations(x0);
    }

    public final /* bridge */ /* synthetic */ void setColorFilter(int x0, Mode x1) {
        super.setColorFilter(x0, x1);
    }

    public final /* bridge */ /* synthetic */ void setFilterBitmap(boolean x0) {
        super.setFilterBitmap(x0);
    }

    public final /* bridge */ /* synthetic */ void setHotspot(float x0, float x1) {
        super.setHotspot(x0, x1);
    }

    public final /* bridge */ /* synthetic */ void setHotspotBounds(int x0, int x1, int x2, int x3) {
        super.setHotspotBounds(x0, x1, x2, x3);
    }

    public final /* bridge */ /* synthetic */ boolean setState(int[] x0) {
        return super.setState(x0);
    }

    c() {
        this(null, (byte) 0);
    }

    private c(@Nullable Context context) {
        this(context, (byte) 0);
    }

    private c(@Nullable Context context, byte b) {
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = new Callback(this) {
            final /* synthetic */ c a;

            {
                this.a = this$0;
            }

            public final void invalidateDrawable(Drawable who) {
                this.a.invalidateSelf();
            }

            public final void scheduleDrawable(Drawable who, Runnable what, long when) {
                this.a.scheduleSelf(what, when);
            }

            public final void unscheduleDrawable(Drawable who, Runnable what) {
                this.a.unscheduleSelf(what);
            }
        };
        this.d = context;
        this.c = new a();
    }

    public final Drawable mutate() {
        if (this.b != null) {
            this.b.mutate();
        }
        return this;
    }

    public static c a(Context context, Resources r, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
        c drawable = new c(context);
        drawable.inflate(r, parser, attrs, theme);
        return drawable;
    }

    public final ConstantState getConstantState() {
        if (this.b == null || VERSION.SDK_INT < 24) {
            return null;
        }
        return new b(this.b.getConstantState());
    }

    public final int getChangingConfigurations() {
        if (this.b != null) {
            return this.b.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.c.a;
    }

    public final void draw(Canvas canvas) {
        if (this.b != null) {
            this.b.draw(canvas);
            return;
        }
        this.c.b.draw(canvas);
        if (this.c.c.isStarted()) {
            invalidateSelf();
        }
    }

    protected final void onBoundsChange(Rect bounds) {
        if (this.b != null) {
            this.b.setBounds(bounds);
        } else {
            this.c.b.setBounds(bounds);
        }
    }

    protected final boolean onStateChange(int[] state) {
        if (this.b != null) {
            return this.b.setState(state);
        }
        return this.c.b.setState(state);
    }

    protected final boolean onLevelChange(int level) {
        if (this.b != null) {
            return this.b.setLevel(level);
        }
        return this.c.b.setLevel(level);
    }

    public final int getAlpha() {
        if (this.b != null) {
            return android.support.v4.graphics.drawable.a.c(this.b);
        }
        return this.c.b.getAlpha();
    }

    public final void setAlpha(int alpha) {
        if (this.b != null) {
            this.b.setAlpha(alpha);
        } else {
            this.c.b.setAlpha(alpha);
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
        } else {
            this.c.b.setColorFilter(colorFilter);
        }
    }

    public final void setTint(int tint) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, tint);
        } else {
            this.c.b.setTint(tint);
        }
    }

    public final void setTintList(ColorStateList tint) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, tint);
        } else {
            this.c.b.setTintList(tint);
        }
    }

    public final void setTintMode(Mode tintMode) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, tintMode);
        } else {
            this.c.b.setTintMode(tintMode);
        }
    }

    public final boolean setVisible(boolean visible, boolean restart) {
        if (this.b != null) {
            return this.b.setVisible(visible, restart);
        }
        this.c.b.setVisible(visible, restart);
        return super.setVisible(visible, restart);
    }

    public final boolean isStateful() {
        if (this.b != null) {
            return this.b.isStateful();
        }
        return this.c.b.isStateful();
    }

    public final int getOpacity() {
        if (this.b != null) {
            return this.b.getOpacity();
        }
        return this.c.b.getOpacity();
    }

    public final int getIntrinsicWidth() {
        if (this.b != null) {
            return this.b.getIntrinsicWidth();
        }
        return this.c.b.getIntrinsicWidth();
    }

    public final int getIntrinsicHeight() {
        if (this.b != null) {
            return this.b.getIntrinsicHeight();
        }
        return this.c.b.getIntrinsicHeight();
    }

    public final boolean isAutoMirrored() {
        if (this.b != null) {
            return android.support.v4.graphics.drawable.a.b(this.b);
        }
        return this.c.b.isAutoMirrored();
    }

    public final void setAutoMirrored(boolean mirrored) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, mirrored);
        } else {
            this.c.b.setAutoMirrored(mirrored);
        }
    }

    public final void inflate(Resources res, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, res, parser, attrs, theme);
            return;
        }
        int eventType = parser.getEventType();
        int innerDepth = parser.getDepth() + 1;
        while (eventType != 1 && (parser.getDepth() >= innerDepth || eventType != 3)) {
            if (eventType == 2) {
                String tagName = parser.getName();
                TypedArray a;
                if ("animated-vector".equals(tagName)) {
                    a = android.support.v4.content.res.b.a(res, theme, attrs, a.e);
                    int drawableRes = a.getResourceId(0, 0);
                    if (drawableRes != 0) {
                        i vectorDrawable = i.a(res, drawableRes, theme);
                        vectorDrawable.a();
                        vectorDrawable.setCallback(this.a);
                        if (this.c.b != null) {
                            this.c.b.setCallback(null);
                        }
                        this.c.b = vectorDrawable;
                    }
                    a.recycle();
                } else if ("target".equals(tagName)) {
                    a = res.obtainAttributes(attrs, a.f);
                    String target = a.getString(0);
                    int id = a.getResourceId(1, 0);
                    if (id != 0) {
                        if (this.d != null) {
                            Animator objectAnimator;
                            Context context = this.d;
                            if (VERSION.SDK_INT >= 24) {
                                objectAnimator = AnimatorInflater.loadAnimator(context, id);
                            } else {
                                objectAnimator = e.a(context, context.getResources(), context.getTheme(), id);
                            }
                            objectAnimator.setTarget(this.c.b.a(target));
                            if (VERSION.SDK_INT < 21) {
                                a(objectAnimator);
                            }
                            if (this.c.e == null) {
                                this.c.e = new ArrayList();
                                this.c.d = new android.support.v4.util.a();
                            }
                            this.c.e.add(objectAnimator);
                            this.c.d.put(objectAnimator, target);
                        } else {
                            a.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                    a.recycle();
                } else {
                    continue;
                }
            }
            eventType = parser.next();
        }
        this.c.a();
    }

    public final void inflate(Resources res, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        inflate(res, parser, attrs, null);
    }

    public final void applyTheme(Theme t) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, t);
        }
    }

    public final boolean canApplyTheme() {
        if (this.b != null) {
            return android.support.v4.graphics.drawable.a.d(this.b);
        }
        return false;
    }

    private void a(Animator animator) {
        if (animator instanceof AnimatorSet) {
            List<Animator> childAnimators = ((AnimatorSet) animator).getChildAnimations();
            if (childAnimators != null) {
                for (int i = 0; i < childAnimators.size(); i++) {
                    a((Animator) childAnimators.get(i));
                }
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnim = (ObjectAnimator) animator;
            String propertyName = objectAnim.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.e == null) {
                    this.e = new ArgbEvaluator();
                }
                objectAnim.setEvaluator(this.e);
            }
        }
    }

    public final boolean isRunning() {
        if (this.b != null) {
            return ((AnimatedVectorDrawable) this.b).isRunning();
        }
        return this.c.c.isRunning();
    }

    public final void start() {
        if (this.b != null) {
            ((AnimatedVectorDrawable) this.b).start();
        } else if (!this.c.c.isStarted()) {
            this.c.c.start();
            invalidateSelf();
        }
    }

    public final void stop() {
        if (this.b != null) {
            ((AnimatedVectorDrawable) this.b).stop();
        } else {
            this.c.c.end();
        }
    }
}
