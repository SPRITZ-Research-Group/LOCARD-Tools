package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.Notification.DecoratedCustomViewStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import com.brentvatne.react.ReactVideoViewManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class NotificationCompat {
    static final p a;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    public static class a extends android.support.v4.app.y.a {
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public static final android.support.v4.app.y.a.a e = new android.support.v4.app.y.a.a() {
        };
        final Bundle a;
        public int b;
        public CharSequence c;
        public PendingIntent d;
        private final ac[] f;
        private final ac[] g;
        private boolean h;

        public static final class a {
            private final int a;
            private final CharSequence b;
            private final PendingIntent c;
            private boolean d;
            private final Bundle e;
            private ArrayList<ac> f;

            public a(int icon, CharSequence title, PendingIntent intent) {
                this(icon, title, intent, new Bundle());
            }

            private a(int icon, CharSequence title, PendingIntent intent, Bundle extras) {
                this.d = true;
                this.a = icon;
                this.b = d.e(title);
                this.c = intent;
                this.e = extras;
                this.f = null;
                this.d = true;
            }

            public final a a(Bundle extras) {
                this.e.putAll(extras);
                return this;
            }

            public final a a(ac remoteInput) {
                if (this.f == null) {
                    this.f = new ArrayList();
                }
                this.f.add(remoteInput);
                return this;
            }

            public final a a() {
                ac[] dataOnlyInputsArr;
                ac[] textInputsArr;
                List<ac> dataOnlyInputs = new ArrayList();
                List<ac> textInputs = new ArrayList();
                if (this.f != null) {
                    Iterator it = this.f.iterator();
                    while (it.hasNext()) {
                        Object obj;
                        ac input = (ac) it.next();
                        if (input.e() || (!(input.c() == null || input.c().length == 0) || input.d() == null || input.d().isEmpty())) {
                            obj = null;
                        } else {
                            obj = 1;
                        }
                        if (obj != null) {
                            dataOnlyInputs.add(input);
                        } else {
                            textInputs.add(input);
                        }
                    }
                }
                if (dataOnlyInputs.isEmpty()) {
                    dataOnlyInputsArr = null;
                } else {
                    dataOnlyInputsArr = (ac[]) dataOnlyInputs.toArray(new ac[dataOnlyInputs.size()]);
                }
                if (textInputs.isEmpty()) {
                    textInputsArr = null;
                } else {
                    textInputsArr = (ac[]) textInputs.toArray(new ac[textInputs.size()]);
                }
                return new a(this.a, this.b, this.c, this.e, textInputsArr, dataOnlyInputsArr, this.d);
            }
        }

        public a(int icon, CharSequence title, PendingIntent intent) {
            this(icon, title, intent, new Bundle(), null, null, true);
        }

        a(int icon, CharSequence title, PendingIntent intent, Bundle extras, ac[] remoteInputs, ac[] dataOnlyRemoteInputs, boolean allowGeneratedReplies) {
            this.b = icon;
            this.c = d.e(title);
            this.d = intent;
            if (extras == null) {
                extras = new Bundle();
            }
            this.a = extras;
            this.f = remoteInputs;
            this.g = dataOnlyRemoteInputs;
            this.h = allowGeneratedReplies;
        }

        public final int a() {
            return this.b;
        }

        public final CharSequence b() {
            return this.c;
        }

        public final PendingIntent c() {
            return this.d;
        }

        public final Bundle d() {
            return this.a;
        }

        public final boolean e() {
            return this.h;
        }

        public final /* bridge */ /* synthetic */ android.support.v4.app.ae.a[] f() {
            return this.g;
        }

        public final /* bridge */ /* synthetic */ android.support.v4.app.ae.a[] g() {
            return this.f;
        }
    }

    public static abstract class q {
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        protected d d;
        CharSequence e;
        CharSequence f;
        boolean g = false;

        public final void a(d builder) {
            if (this.d != builder) {
                this.d = builder;
                if (this.d != null) {
                    this.d.a(this);
                }
            }
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public void a(t builder) {
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public RemoteViews a() {
            return null;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public RemoteViews b() {
            return null;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public RemoteViews c() {
            return null;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public void a(Bundle extras) {
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final RemoteViews a(int resId) {
            Resources res = this.d.a.getResources();
            RemoteViews contentView = new RemoteViews(this.d.a.getPackageName(), resId);
            boolean showLine3 = false;
            boolean showLine2 = false;
            boolean minPriority = this.d.j < -1;
            if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 21) {
                if (minPriority) {
                    contentView.setInt(android.support.a.a.d.notification_background, "setBackgroundResource", android.support.a.a.c.notification_bg_low);
                    contentView.setInt(android.support.a.a.d.icon, "setBackgroundResource", android.support.a.a.c.notification_template_icon_low_bg);
                } else {
                    contentView.setInt(android.support.a.a.d.notification_background, "setBackgroundResource", android.support.a.a.c.notification_bg);
                    contentView.setInt(android.support.a.a.d.icon, "setBackgroundResource", android.support.a.a.c.notification_template_icon_bg);
                }
            }
            if (this.d.g != null) {
                if (VERSION.SDK_INT >= 16) {
                    contentView.setViewVisibility(android.support.a.a.d.icon, 0);
                    contentView.setImageViewBitmap(android.support.a.a.d.icon, this.d.g);
                } else {
                    contentView.setViewVisibility(android.support.a.a.d.icon, 8);
                }
                if (this.d.L.icon != 0) {
                    int backgroundSize = res.getDimensionPixelSize(android.support.a.a.b.notification_right_icon_size);
                    int iconSize = backgroundSize - (res.getDimensionPixelSize(android.support.a.a.b.notification_small_icon_background_padding) * 2);
                    if (VERSION.SDK_INT >= 21) {
                        contentView.setImageViewBitmap(android.support.a.a.d.right_icon, a(this.d.L.icon, backgroundSize, iconSize, this.d.B));
                    } else {
                        contentView.setImageViewBitmap(android.support.a.a.d.right_icon, a(this.d.L.icon, -1, 0));
                    }
                    contentView.setViewVisibility(android.support.a.a.d.right_icon, 0);
                }
            } else if (this.d.L.icon != 0) {
                contentView.setViewVisibility(android.support.a.a.d.icon, 0);
                if (VERSION.SDK_INT >= 21) {
                    contentView.setImageViewBitmap(android.support.a.a.d.icon, a(this.d.L.icon, res.getDimensionPixelSize(android.support.a.a.b.notification_large_icon_width) - res.getDimensionPixelSize(android.support.a.a.b.notification_big_circle_margin), res.getDimensionPixelSize(android.support.a.a.b.notification_small_icon_size_as_large), this.d.B));
                } else {
                    contentView.setImageViewBitmap(android.support.a.a.d.icon, a(this.d.L.icon, -1, 0));
                }
            }
            if (this.d.b != null) {
                contentView.setTextViewText(android.support.a.a.d.title, this.d.b);
            }
            if (this.d.c != null) {
                contentView.setTextViewText(android.support.a.a.d.text, this.d.c);
                showLine3 = true;
            }
            boolean hasRightSide = VERSION.SDK_INT < 21 && this.d.g != null;
            if (this.d.h != null) {
                contentView.setTextViewText(android.support.a.a.d.info, this.d.h);
                contentView.setViewVisibility(android.support.a.a.d.info, 0);
                showLine3 = true;
                hasRightSide = true;
            } else if (this.d.i > 0) {
                if (this.d.i > res.getInteger(android.support.a.a.e.status_bar_notification_info_maxnum)) {
                    contentView.setTextViewText(android.support.a.a.d.info, res.getString(android.support.a.a.g.status_bar_notification_info_overflow));
                } else {
                    contentView.setTextViewText(android.support.a.a.d.info, NumberFormat.getIntegerInstance().format((long) this.d.i));
                }
                contentView.setViewVisibility(android.support.a.a.d.info, 0);
                showLine3 = true;
                hasRightSide = true;
            } else {
                contentView.setViewVisibility(android.support.a.a.d.info, 8);
            }
            if (this.d.n != null && VERSION.SDK_INT >= 16) {
                contentView.setTextViewText(android.support.a.a.d.text, this.d.n);
                if (this.d.c != null) {
                    contentView.setTextViewText(android.support.a.a.d.text2, this.d.c);
                    contentView.setViewVisibility(android.support.a.a.d.text2, 0);
                    showLine2 = true;
                } else {
                    contentView.setViewVisibility(android.support.a.a.d.text2, 8);
                }
            }
            if (showLine2 && VERSION.SDK_INT >= 16) {
                contentView.setViewPadding(android.support.a.a.d.line1, 0, 0, 0, 0);
            }
            if (this.d.f() != 0) {
                if (!this.d.l || VERSION.SDK_INT < 16) {
                    contentView.setViewVisibility(android.support.a.a.d.time, 0);
                    contentView.setLong(android.support.a.a.d.time, "setTime", this.d.f());
                } else {
                    contentView.setViewVisibility(android.support.a.a.d.chronometer, 0);
                    contentView.setLong(android.support.a.a.d.chronometer, "setBase", this.d.f() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                    contentView.setBoolean(android.support.a.a.d.chronometer, "setStarted", true);
                }
                hasRightSide = true;
            }
            contentView.setViewVisibility(android.support.a.a.d.right_side, hasRightSide ? 0 : 8);
            contentView.setViewVisibility(android.support.a.a.d.line3, showLine3 ? 0 : 8);
            return contentView;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final Bitmap a(int iconId, int color) {
            return a(iconId, color, 0);
        }

        private Bitmap a(int iconId, int color, int size) {
            int width;
            int height;
            Drawable drawable = this.d.a.getResources().getDrawable(iconId);
            if (size == 0) {
                width = drawable.getIntrinsicWidth();
            } else {
                width = size;
            }
            if (size == 0) {
                height = drawable.getIntrinsicHeight();
            } else {
                height = size;
            }
            Bitmap resultBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            drawable.setBounds(0, 0, width, height);
            if (color != 0) {
                drawable.mutate().setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_IN));
            }
            drawable.draw(new Canvas(resultBitmap));
            return resultBitmap;
        }

        private Bitmap a(int iconId, int size, int iconSize, int color) {
            int i = android.support.a.a.c.notification_icon_background;
            if (color == 0) {
                color = 0;
            }
            Bitmap coloredBitmap = a(i, color, size);
            Canvas canvas = new Canvas(coloredBitmap);
            Drawable icon = this.d.a.getResources().getDrawable(iconId).mutate();
            icon.setFilterBitmap(true);
            int inset = (size - iconSize) / 2;
            icon.setBounds(inset, inset, iconSize + inset, iconSize + inset);
            icon.setColorFilter(new PorterDuffColorFilter(-1, Mode.SRC_ATOP));
            icon.draw(canvas);
            return coloredBitmap;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final void a(RemoteViews outerView, RemoteViews innerView) {
            float f = 1.3f;
            outerView.setViewVisibility(android.support.a.a.d.title, 8);
            outerView.setViewVisibility(android.support.a.a.d.text2, 8);
            outerView.setViewVisibility(android.support.a.a.d.text, 8);
            outerView.removeAllViews(android.support.a.a.d.notification_main_column);
            outerView.addView(android.support.a.a.d.notification_main_column, innerView.clone());
            outerView.setViewVisibility(android.support.a.a.d.notification_main_column, 0);
            if (VERSION.SDK_INT >= 21) {
                int i = android.support.a.a.d.notification_main_column_container;
                Resources resources = this.d.a.getResources();
                int dimensionPixelSize = resources.getDimensionPixelSize(android.support.a.a.b.notification_top_pad);
                int dimensionPixelSize2 = resources.getDimensionPixelSize(android.support.a.a.b.notification_top_pad_large_text);
                float f2 = resources.getConfiguration().fontScale;
                if (f2 < 1.0f) {
                    f = 1.0f;
                } else if (f2 <= 1.3f) {
                    f = f2;
                }
                f = (f - 1.0f) / 0.29999995f;
                outerView.setViewPadding(i, 0, Math.round((f * ((float) dimensionPixelSize2)) + ((1.0f - f) * ((float) dimensionPixelSize))), 0, 0);
            }
        }
    }

    public static class b extends q {
        private Bitmap a;
        private Bitmap b;
        private boolean c;

        public final b a(CharSequence title) {
            this.e = d.e(title);
            return this;
        }

        public final b b(CharSequence cs) {
            this.f = d.e(cs);
            this.g = true;
            return this;
        }

        public final b a(Bitmap b) {
            this.a = b;
            return this;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final void a(t builder) {
            if (VERSION.SDK_INT >= 16) {
                z.a(builder, this.e, this.g, this.f, this.a, this.b, this.c);
            }
        }
    }

    public static class c extends q {
        private CharSequence a;

        public final c a(CharSequence title) {
            this.e = d.e(title);
            return this;
        }

        public final c b(CharSequence cs) {
            this.f = d.e(cs);
            this.g = true;
            return this;
        }

        public final c c(CharSequence cs) {
            this.a = d.e(cs);
            return this;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final void a(t builder) {
            if (VERSION.SDK_INT >= 16) {
                z.a(builder, this.e, this.g, this.f, this.a);
            }
        }
    }

    public static class d {
        Bundle A;
        int B;
        int C;
        Notification D;
        RemoteViews E;
        RemoteViews F;
        RemoteViews G;
        String H;
        int I;
        String J;
        long K;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public Notification L;
        public ArrayList<String> M;
        private int N;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public Context a;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public CharSequence b;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public CharSequence c;
        PendingIntent d;
        PendingIntent e;
        RemoteViews f;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public Bitmap g;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public CharSequence h;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public int i;
        int j;
        boolean k;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public boolean l;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public q m;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public CharSequence n;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public CharSequence[] o;
        int p;
        int q;
        boolean r;
        String s;
        boolean t;
        String u;
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public ArrayList<a> v;
        boolean w;
        boolean x;
        boolean y;
        String z;

        public d(@NonNull Context context, @NonNull String channelId) {
            this.k = true;
            this.v = new ArrayList();
            this.w = false;
            this.B = 0;
            this.C = 0;
            this.I = 0;
            this.N = 0;
            this.L = new Notification();
            this.a = context;
            this.H = channelId;
            this.L.when = System.currentTimeMillis();
            this.L.audioStreamType = -1;
            this.j = 0;
            this.M = new ArrayList();
        }

        @Deprecated
        public d(Context context) {
            this(context, null);
        }

        public final d a(long when) {
            this.L.when = when;
            return this;
        }

        public final d a() {
            this.k = false;
            return this;
        }

        public final d a(int icon) {
            this.L.icon = icon;
            return this;
        }

        public final d a(CharSequence title) {
            this.b = e(title);
            return this;
        }

        public final d b(CharSequence text) {
            this.c = e(text);
            return this;
        }

        public final d c(CharSequence text) {
            this.n = e(text);
            return this;
        }

        public final d a(PendingIntent intent) {
            this.d = intent;
            return this;
        }

        public final d b(PendingIntent intent) {
            this.L.deleteIntent = intent;
            return this;
        }

        public final d d(CharSequence tickerText) {
            this.L.tickerText = e(tickerText);
            return this;
        }

        public final d a(Uri sound) {
            this.L.sound = sound;
            this.L.audioStreamType = -1;
            return this;
        }

        public final d a(long[] pattern) {
            this.L.vibrate = pattern;
            return this;
        }

        public final d a(boolean onlyAlertOnce) {
            a(8, onlyAlertOnce);
            return this;
        }

        public final d b() {
            a(16, true);
            return this;
        }

        public final d c() {
            this.w = true;
            return this;
        }

        public final d a(String category) {
            this.z = category;
            return this;
        }

        private void a(int mask, boolean value) {
            Notification notification;
            if (value) {
                notification = this.L;
                notification.flags |= mask;
                return;
            }
            notification = this.L;
            notification.flags &= mask ^ -1;
        }

        public final d b(int pri) {
            this.j = pri;
            return this;
        }

        public final d b(String groupKey) {
            this.s = groupKey;
            return this;
        }

        public final d d() {
            this.t = true;
            return this;
        }

        public final d a(int icon, CharSequence title, PendingIntent intent) {
            this.v.add(new a(icon, title, intent));
            return this;
        }

        public final d a(a action) {
            this.v.add(action);
            return this;
        }

        public final d a(q style) {
            if (this.m != style) {
                this.m = style;
                if (this.m != null) {
                    this.m.a(this);
                }
            }
            return this;
        }

        public final d c(@ColorInt int argb) {
            this.B = argb;
            return this;
        }

        public final d d(int visibility) {
            this.C = visibility;
            return this;
        }

        public final d a(Notification n) {
            this.D = n;
            return this;
        }

        public final d a(RemoteViews contentView) {
            this.F = contentView;
            return this;
        }

        public final d c(@NonNull String channelId) {
            this.H = channelId;
            return this;
        }

        public final d e(int groupAlertBehavior) {
            this.N = groupAlertBehavior;
            return this;
        }

        public final Notification e() {
            p pVar = NotificationCompat.a;
            e eVar = new e();
            return pVar.a(this);
        }

        protected static CharSequence e(CharSequence cs) {
            if (cs != null && cs.length() > 5120) {
                return cs.subSequence(0, 5120);
            }
            return cs;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final long f() {
            return this.k ? this.L.when : 0;
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected static class e {
        protected e() {
        }

        public static Notification a(d b, t builder) {
            RemoteViews styleContentView = b.m != null ? b.m.a() : null;
            Notification n = builder.b();
            if (styleContentView != null) {
                n.contentView = styleContentView;
            } else if (b.E != null) {
                n.contentView = b.E;
            }
            if (VERSION.SDK_INT >= 16 && b.m != null) {
                RemoteViews styleBigContentView = b.m.b();
                if (styleBigContentView != null) {
                    n.bigContentView = styleBigContentView;
                }
            }
            if (VERSION.SDK_INT >= 21 && b.m != null) {
                RemoteViews styleHeadsUpContentView = b.m.c();
                if (styleHeadsUpContentView != null) {
                    n.headsUpContentView = styleHeadsUpContentView;
                }
            }
            return n;
        }
    }

    public static class f extends q {
        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final void a(t builder) {
            if (VERSION.SDK_INT >= 24) {
                builder.a().setStyle(new DecoratedCustomViewStyle());
            }
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final RemoteViews a() {
            if (VERSION.SDK_INT < 24 && this.d.E != null) {
                return a(this.d.E, false);
            }
            return null;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final RemoteViews b() {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews innerView;
            RemoteViews bigContentView = this.d.F;
            if (bigContentView != null) {
                innerView = bigContentView;
            } else {
                innerView = this.d.E;
            }
            if (innerView != null) {
                return a(innerView, true);
            }
            return null;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final RemoteViews c() {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews innerView;
            RemoteViews headsUp = this.d.G;
            if (headsUp != null) {
                innerView = headsUp;
            } else {
                innerView = this.d.E;
            }
            if (headsUp != null) {
                return a(innerView, true);
            }
            return null;
        }

        private RemoteViews a(RemoteViews innerView, boolean showActions) {
            RemoteViews remoteViews = a(android.support.a.a.f.notification_template_custom_big);
            remoteViews.removeAllViews(android.support.a.a.d.actions);
            boolean actionsVisible = false;
            if (showActions && this.d.v != null) {
                int numActions = Math.min(this.d.v.size(), 3);
                if (numActions > 0) {
                    actionsVisible = true;
                    for (int i = 0; i < numActions; i++) {
                        Object obj;
                        a aVar = (a) this.d.v.get(i);
                        if (aVar.d == null) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        RemoteViews button = new RemoteViews(this.d.a.getPackageName(), obj != null ? android.support.a.a.f.notification_action_tombstone : android.support.a.a.f.notification_action);
                        button.setImageViewBitmap(android.support.a.a.d.action_image, a(aVar.b, this.d.a.getResources().getColor(android.support.a.a.a.notification_action_color_filter)));
                        button.setTextViewText(android.support.a.a.d.action_text, aVar.c);
                        if (obj == null) {
                            button.setOnClickPendingIntent(android.support.a.a.d.action_container, aVar.d);
                        }
                        if (VERSION.SDK_INT >= 15) {
                            button.setContentDescription(android.support.a.a.d.action_container, aVar.c);
                        }
                        remoteViews.addView(android.support.a.a.d.actions, button);
                    }
                }
            }
            int actionVisibility = actionsVisible ? 0 : 8;
            remoteViews.setViewVisibility(android.support.a.a.d.actions, actionVisibility);
            remoteViews.setViewVisibility(android.support.a.a.d.action_divider, actionVisibility);
            a(remoteViews, innerView);
            return remoteViews;
        }
    }

    public static class g extends q {
        private ArrayList<CharSequence> a = new ArrayList();

        public final g a(CharSequence cs) {
            this.f = d.e(cs);
            this.g = true;
            return this;
        }

        public final g b(CharSequence cs) {
            this.a.add(d.e(cs));
            return this;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final void a(t builder) {
            if (VERSION.SDK_INT >= 16) {
                z.a(builder, this.e, this.g, this.f, this.a);
            }
        }
    }

    public static class h extends q {
        CharSequence a;
        CharSequence b;
        List<a> c = new ArrayList();

        public static final class a {
            private final CharSequence a;
            private final long b;
            private final CharSequence c;
            private Bundle d = new Bundle();
            private String e;
            private Uri f;

            public a(CharSequence text, long timestamp, CharSequence sender) {
                this.a = text;
                this.b = timestamp;
                this.c = sender;
            }

            public final CharSequence a() {
                return this.a;
            }

            public final long b() {
                return this.b;
            }

            public final CharSequence c() {
                return this.c;
            }

            public final String d() {
                return this.e;
            }

            public final Uri e() {
                return this.f;
            }

            static Bundle[] a(List<a> messages) {
                Bundle[] bundles = new Bundle[messages.size()];
                int N = messages.size();
                for (int i = 0; i < N; i++) {
                    a aVar = (a) messages.get(i);
                    Bundle bundle = new Bundle();
                    if (aVar.a != null) {
                        bundle.putCharSequence("text", aVar.a);
                    }
                    bundle.putLong("time", aVar.b);
                    if (aVar.c != null) {
                        bundle.putCharSequence("sender", aVar.c);
                    }
                    if (aVar.e != null) {
                        bundle.putString("type", aVar.e);
                    }
                    if (aVar.f != null) {
                        bundle.putParcelable(ReactVideoViewManager.PROP_SRC_URI, aVar.f);
                    }
                    if (aVar.d != null) {
                        bundle.putBundle("extras", aVar.d);
                    }
                    bundles[i] = bundle;
                }
                return bundles;
            }
        }

        h() {
        }

        public h(@NonNull CharSequence userDisplayName) {
            this.a = userDisplayName;
        }

        public final h a(CharSequence conversationTitle) {
            this.b = conversationTitle;
            return this;
        }

        public final h a(CharSequence text, long timestamp, CharSequence sender) {
            this.c.add(new a(text, timestamp, sender));
            if (this.c.size() > 25) {
                this.c.remove(0);
            }
            return this;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final void a(t builder) {
            a message;
            if (VERSION.SDK_INT >= 24) {
                List<CharSequence> texts = new ArrayList();
                List<Long> timestamps = new ArrayList();
                List<CharSequence> senders = new ArrayList();
                List<String> dataMimeTypes = new ArrayList();
                List<Uri> dataUris = new ArrayList();
                for (a message2 : this.c) {
                    texts.add(message2.a());
                    timestamps.add(Long.valueOf(message2.b()));
                    senders.add(message2.c());
                    dataMimeTypes.add(message2.d());
                    dataUris.add(message2.e());
                }
                w.a(builder, this.a, this.b, texts, timestamps, senders, dataMimeTypes, dataUris);
                return;
            }
            a latestIncomingMessage = d();
            if (this.b != null) {
                builder.a().setContentTitle(this.b);
            } else if (latestIncomingMessage != null) {
                builder.a().setContentTitle(latestIncomingMessage.c());
            }
            if (latestIncomingMessage != null) {
                CharSequence a;
                Builder a2 = builder.a();
                if (this.b != null) {
                    a = a(latestIncomingMessage);
                } else {
                    a = latestIncomingMessage.a();
                }
                a2.setContentText(a);
            }
            if (VERSION.SDK_INT >= 16) {
                CharSequence completeMessage = new SpannableStringBuilder();
                boolean showNames = this.b != null || e();
                for (int i = this.c.size() - 1; i >= 0; i--) {
                    message2 = (a) this.c.get(i);
                    CharSequence line = showNames ? a(message2) : message2.a();
                    if (i != this.c.size() - 1) {
                        completeMessage.insert(0, "\n");
                    }
                    completeMessage.insert(0, line);
                }
                z.a(builder, null, false, null, completeMessage);
            }
        }

        @Nullable
        private a d() {
            for (int i = this.c.size() - 1; i >= 0; i--) {
                a message = (a) this.c.get(i);
                if (!TextUtils.isEmpty(message.c())) {
                    return message;
                }
            }
            if (this.c.isEmpty()) {
                return null;
            }
            return (a) this.c.get(this.c.size() - 1);
        }

        private boolean e() {
            for (int i = this.c.size() - 1; i >= 0; i--) {
                if (((a) this.c.get(i)).c() == null) {
                    return true;
                }
            }
            return false;
        }

        private CharSequence a(a message) {
            boolean afterLollipop;
            android.support.v4.text.a bidi = android.support.v4.text.a.a();
            SpannableStringBuilder sb = new SpannableStringBuilder();
            if (VERSION.SDK_INT >= 21) {
                afterLollipop = true;
            } else {
                afterLollipop = false;
            }
            int color = afterLollipop ? -16777216 : -1;
            CharSequence replyName = message.c();
            if (TextUtils.isEmpty(message.c())) {
                replyName = this.a == null ? "" : this.a;
                if (afterLollipop && this.d.B != 0) {
                    color = this.d.B;
                }
            }
            CharSequence senderText = bidi.a(replyName);
            sb.append(senderText);
            sb.setSpan(new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(color), null), sb.length() - senderText.length(), sb.length(), 33);
            sb.append("  ").append(bidi.a(message.a() == null ? "" : message.a()));
            return sb;
        }

        public final void a(Bundle extras) {
            super.a(extras);
            if (this.a != null) {
                extras.putCharSequence("android.selfDisplayName", this.a);
            }
            if (this.b != null) {
                extras.putCharSequence("android.conversationTitle", this.b);
            }
            if (!this.c.isEmpty()) {
                extras.putParcelableArray("android.messages", a.a(this.c));
            }
        }
    }

    interface p {
        Notification a(d dVar);
    }

    static class o implements p {

        public static class a implements t {
            private Builder a;

            a(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate) {
                this.a = new Builder(context).setWhen(n.when).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setProgress(progressMax, progress, progressIndeterminate);
            }

            public final Builder a() {
                return this.a;
            }

            public final Notification b() {
                return this.a.getNotification();
            }
        }

        o() {
        }

        public Notification a(d b) {
            return e.a(b, new a(b.a, b.L, b.b, b.c, b.h, b.f, b.i, b.d, b.e, b.g, b.p, b.q, b.r));
        }
    }

    @RequiresApi(16)
    static class i extends o {
        i() {
        }

        public Notification a(d b) {
            t builder = new android.support.v4.app.z.a(b.a, b.L, b.b, b.c, b.h, b.f, b.i, b.d, b.e, b.g, b.p, b.q, b.r, b.l, b.j, b.n, b.w, b.A, b.s, b.t, b.u, b.E, b.F);
            NotificationCompat.a(builder, b.v);
            if (b.m != null) {
                b.m.a(builder);
            }
            Notification notification = e.a(b, builder);
            if (b.m != null) {
                Bundle extras = NotificationCompat.a(notification);
                if (extras != null) {
                    b.m.a(extras);
                }
            }
            return notification;
        }
    }

    @RequiresApi(19)
    static class j extends i {
        j() {
        }

        public Notification a(d b) {
            t builder = new android.support.v4.app.aa.a(b.a, b.L, b.b, b.c, b.h, b.f, b.i, b.d, b.e, b.g, b.p, b.q, b.r, b.k, b.l, b.j, b.n, b.w, b.M, b.A, b.s, b.t, b.u, b.E, b.F);
            NotificationCompat.a(builder, b.v);
            if (b.m != null) {
                b.m.a(builder);
            }
            return e.a(b, builder);
        }
    }

    @RequiresApi(20)
    static class k extends j {
        k() {
        }

        public Notification a(d b) {
            t builder = new android.support.v4.app.u.a(b.a, b.L, b.b, b.c, b.h, b.f, b.i, b.d, b.e, b.g, b.p, b.q, b.r, b.k, b.l, b.j, b.n, b.w, b.M, b.A, b.s, b.t, b.u, b.E, b.F, b.N);
            NotificationCompat.a(builder, b.v);
            if (b.m != null) {
                b.m.a(builder);
            }
            Notification notification = e.a(b, builder);
            if (b.m != null) {
                b.m.a(NotificationCompat.a(notification));
            }
            return notification;
        }
    }

    @RequiresApi(21)
    static class l extends k {
        l() {
        }

        public Notification a(d b) {
            t builder = new android.support.v4.app.v.a(b.a, b.L, b.b, b.c, b.h, b.f, b.i, b.d, b.e, b.g, b.p, b.q, b.r, b.k, b.l, b.j, b.n, b.w, b.z, b.M, b.A, b.B, b.C, b.D, b.s, b.t, b.u, b.E, b.F, b.G, b.N);
            NotificationCompat.a(builder, b.v);
            if (b.m != null) {
                b.m.a(builder);
            }
            Notification notification = e.a(b, builder);
            if (b.m != null) {
                b.m.a(NotificationCompat.a(notification));
            }
            return notification;
        }
    }

    @RequiresApi(24)
    static class m extends l {
        m() {
        }

        public Notification a(d b) {
            t builder = new android.support.v4.app.w.a(b.a, b.L, b.b, b.c, b.h, b.f, b.i, b.d, b.e, b.g, b.p, b.q, b.r, b.k, b.l, b.j, b.n, b.w, b.z, b.M, b.A, b.B, b.C, b.D, b.s, b.t, b.u, b.o, b.E, b.F, b.G, b.N);
            NotificationCompat.a(builder, b.v);
            if (b.m != null) {
                b.m.a(builder);
            }
            Notification notification = e.a(b, builder);
            if (b.m != null) {
                b.m.a(NotificationCompat.a(notification));
            }
            return notification;
        }
    }

    @RequiresApi(26)
    static class n extends m {
        n() {
        }

        public final Notification a(d b) {
            t builder = new android.support.v4.app.x.a(b.a, b.L, b.b, b.c, b.h, b.f, b.i, b.d, b.e, b.g, b.p, b.q, b.r, b.k, b.l, b.j, b.n, b.w, b.z, b.M, b.A, b.B, b.C, b.D, b.s, b.t, b.u, b.o, b.E, b.F, b.G, b.H, b.I, b.J, b.K, b.x, b.y, b.N);
            NotificationCompat.a(builder, b.v);
            if (b.m != null) {
                b.m.a(builder);
            }
            Notification notification = e.a(b, builder);
            if (b.m != null) {
                b.m.a(NotificationCompat.a(notification));
            }
            return notification;
        }
    }

    static void a(s builder, ArrayList<a> actions) {
        Iterator it = actions.iterator();
        while (it.hasNext()) {
            builder.a((a) it.next());
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            a = new n();
        } else if (VERSION.SDK_INT >= 24) {
            a = new m();
        } else if (VERSION.SDK_INT >= 21) {
            a = new l();
        } else if (VERSION.SDK_INT >= 20) {
            a = new k();
        } else if (VERSION.SDK_INT >= 19) {
            a = new j();
        } else if (VERSION.SDK_INT >= 16) {
            a = new i();
        } else {
            a = new o();
        }
    }

    public static Bundle a(Notification notification) {
        if (VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (VERSION.SDK_INT >= 16) {
            return z.a(notification);
        }
        return null;
    }
}
