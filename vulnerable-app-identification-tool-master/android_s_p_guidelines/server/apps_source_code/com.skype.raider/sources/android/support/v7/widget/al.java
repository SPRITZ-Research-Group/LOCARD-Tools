package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.v4.widget.j;
import android.support.v7.appcompat.a.f;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

final class al extends j implements OnClickListener {
    private final SearchManager j = ((SearchManager) this.d.getSystemService("search"));
    private final SearchView k;
    private final SearchableInfo l;
    private final Context m;
    private final WeakHashMap<String, ConstantState> n;
    private final int o;
    private boolean p = false;
    private int q = 1;
    private ColorStateList r;
    private int s = -1;
    private int t = -1;
    private int u = -1;
    private int v = -1;
    private int w = -1;
    private int x = -1;

    private static final class a {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;

        public a(View v) {
            this.a = (TextView) v.findViewById(16908308);
            this.b = (TextView) v.findViewById(16908309);
            this.c = (ImageView) v.findViewById(16908295);
            this.d = (ImageView) v.findViewById(16908296);
            this.e = (ImageView) v.findViewById(f.edit_query);
        }
    }

    public al(Context context, SearchView searchView, SearchableInfo searchable, WeakHashMap<String, ConstantState> outsideDrawablesCache) {
        super(context, searchView.c());
        this.k = searchView;
        this.l = searchable;
        this.o = searchView.d();
        this.m = context;
        this.n = outsideDrawablesCache;
    }

    public final void a(int refineWhat) {
        this.q = refineWhat;
    }

    public final boolean hasStableIds() {
        return false;
    }

    public final Cursor a(CharSequence constraint) {
        String query = constraint == null ? "" : constraint.toString();
        if (this.k.getVisibility() != 0 || this.k.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor cursor;
            SearchableInfo searchableInfo = this.l;
            if (searchableInfo == null) {
                cursor = null;
            } else {
                String suggestAuthority = searchableInfo.getSuggestAuthority();
                if (suggestAuthority == null) {
                    cursor = null;
                } else {
                    String[] strArr;
                    Builder fragment = new Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
                    String suggestPath = searchableInfo.getSuggestPath();
                    if (suggestPath != null) {
                        fragment.appendEncodedPath(suggestPath);
                    }
                    fragment.appendPath("search_suggest_query");
                    String suggestSelection = searchableInfo.getSuggestSelection();
                    if (suggestSelection != null) {
                        strArr = new String[]{query};
                    } else {
                        fragment.appendPath(query);
                        strArr = null;
                    }
                    fragment.appendQueryParameter("limit", "50");
                    cursor = this.d.getContentResolver().query(fragment.build(), null, suggestSelection, strArr, null);
                }
            }
            if (cursor != null) {
                cursor.getCount();
                return cursor;
            }
        } catch (RuntimeException e) {
        }
        return null;
    }

    public final void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        c(a());
    }

    public final void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        c(a());
    }

    private static void c(android.database.Cursor r2) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.utils.BlockUtils.getNextBlock(BlockUtils.java:289)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:143)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:654)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        if (r2 == 0) goto L_0x0011;
    L_0x0002:
        r0 = r2.getExtras();
    L_0x0006:
        if (r0 == 0) goto L_0x0010;
    L_0x0008:
        r1 = "in_progress";
        r1 = r0.getBoolean(r1);
        if (r1 == 0) goto L_0x0010;
    L_0x0010:
        return;
    L_0x0011:
        r0 = 0;
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.al.c(android.database.Cursor):void");
    }

    public final void a(Cursor c) {
        if (!this.p) {
            try {
                super.a(c);
                if (c != null) {
                    this.s = c.getColumnIndex("suggest_text_1");
                    this.t = c.getColumnIndex("suggest_text_2");
                    this.u = c.getColumnIndex("suggest_text_2_url");
                    this.v = c.getColumnIndex("suggest_icon_1");
                    this.w = c.getColumnIndex("suggest_icon_2");
                    this.x = c.getColumnIndex("suggest_flags");
                }
            } catch (Exception e) {
            }
        } else if (c != null) {
            c.close();
        }
    }

    public final View a(Context context, Cursor cursor, ViewGroup parent) {
        View v = super.a(context, cursor, parent);
        v.setTag(new a(v));
        ((ImageView) v.findViewById(f.edit_query)).setImageResource(this.o);
        return v;
    }

    public final void a(View view, Cursor cursor) {
        Drawable drawable;
        a views = (a) view.getTag();
        int flags = 0;
        if (this.x != -1) {
            flags = cursor.getInt(this.x);
        }
        if (views.a != null) {
            a(views.a, a(cursor, this.s));
        }
        if (views.b != null) {
            CharSequence text2 = a(cursor, this.u);
            if (text2 != null) {
                if (this.r == null) {
                    TypedValue typedValue = new TypedValue();
                    this.d.getTheme().resolveAttribute(android.support.v7.appcompat.a.a.textColorSearchUrl, typedValue, true);
                    this.r = this.d.getResources().getColorStateList(typedValue.resourceId);
                }
                CharSequence text22 = new SpannableString(text2);
                text22.setSpan(new TextAppearanceSpan(null, 0, 0, this.r, null), 0, text2.length(), 33);
                text2 = text22;
            } else {
                text2 = a(cursor, this.t);
            }
            if (TextUtils.isEmpty(text2)) {
                if (views.a != null) {
                    views.a.setSingleLine(false);
                    views.a.setMaxLines(2);
                }
            } else if (views.a != null) {
                views.a.setSingleLine(true);
                views.a.setMaxLines(1);
            }
            a(views.b, text2);
        }
        if (views.c != null) {
            ImageView imageView = views.c;
            if (this.v == -1) {
                drawable = null;
            } else {
                drawable = a(cursor.getString(this.v));
                if (drawable == null) {
                    ComponentName searchActivity = this.l.getSearchActivity();
                    String flattenToShortString = searchActivity.flattenToShortString();
                    if (this.n.containsKey(flattenToShortString)) {
                        ConstantState constantState = (ConstantState) this.n.get(flattenToShortString);
                        if (constantState == null) {
                            drawable = null;
                        } else {
                            drawable = constantState.newDrawable(this.m.getResources());
                        }
                    } else {
                        Drawable a = a(searchActivity);
                        this.n.put(flattenToShortString, a == null ? null : a.getConstantState());
                        drawable = a;
                    }
                    if (drawable == null) {
                        drawable = this.d.getPackageManager().getDefaultActivityIcon();
                    }
                }
            }
            a(imageView, drawable, 4);
        }
        if (views.d != null) {
            ImageView imageView2 = views.d;
            if (this.w == -1) {
                drawable = null;
            } else {
                drawable = a(cursor.getString(this.w));
            }
            a(imageView2, drawable, 8);
        }
        if (this.q == 2 || (this.q == 1 && (flags & 1) != 0)) {
            views.e.setVisibility(0);
            views.e.setTag(views.a.getText());
            views.e.setOnClickListener(this);
            return;
        }
        views.e.setVisibility(8);
    }

    public final void onClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof CharSequence) {
            this.k.a((CharSequence) tag);
        }
    }

    private static void a(TextView v, CharSequence text) {
        v.setText(text);
        if (TextUtils.isEmpty(text)) {
            v.setVisibility(8);
        } else {
            v.setVisibility(0);
        }
    }

    private static void a(ImageView v, Drawable drawable, int nullVisibility) {
        v.setImageDrawable(drawable);
        if (drawable == null) {
            v.setVisibility(nullVisibility);
            return;
        }
        v.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    public final CharSequence b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        CharSequence query = a(cursor, "suggest_intent_query");
        if (query != null) {
            return query;
        }
        if (this.l.shouldRewriteQueryFromData()) {
            String data = a(cursor, "suggest_intent_data");
            if (data != null) {
                return data;
            }
        }
        if (this.l.shouldRewriteQueryFromText()) {
            String text1 = a(cursor, "suggest_text_1");
            if (text1 != null) {
                return text1;
            }
        }
        return null;
    }

    public final View getView(int position, View convertView, ViewGroup parent) {
        try {
            return super.getView(position, convertView, parent);
        } catch (RuntimeException e) {
            View v = a(this.d, this.c, parent);
            if (v == null) {
                return v;
            }
            ((a) v.getTag()).a.setText(e.toString());
            return v;
        }
    }

    public final View getDropDownView(int position, View convertView, ViewGroup parent) {
        try {
            return super.getDropDownView(position, convertView, parent);
        } catch (RuntimeException e) {
            View v = b(this.d, this.c, parent);
            if (v == null) {
                return v;
            }
            ((a) v.getTag()).a.setText(e.toString());
            return v;
        }
    }

    private Drawable a(String drawableId) {
        if (drawableId == null || drawableId.isEmpty() || "0".equals(drawableId)) {
            return null;
        }
        Drawable drawable;
        try {
            int resourceId = Integer.parseInt(drawableId);
            String drawableUri = "android.resource://" + this.m.getPackageName() + "/" + resourceId;
            drawable = b(drawableUri);
            if (drawable != null) {
                return drawable;
            }
            drawable = android.support.v4.content.a.a(this.m, resourceId);
            a(drawableUri, drawable);
            return drawable;
        } catch (NumberFormatException e) {
            drawable = b(drawableId);
            if (drawable != null) {
                return drawable;
            }
            drawable = a(Uri.parse(drawableId));
            a(drawableId, drawable);
            return drawable;
        } catch (NotFoundException e2) {
            return null;
        }
    }

    private Drawable a(Uri uri) {
        InputStream stream;
        try {
            if ("android.resource".equals(uri.getScheme())) {
                return b(uri);
            }
            stream = this.m.getContentResolver().openInputStream(uri);
            if (stream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            Drawable createFromStream = Drawable.createFromStream(stream, null);
            try {
                stream.close();
                return createFromStream;
            } catch (IOException e) {
                new StringBuilder("Error closing icon stream for ").append(uri);
                return createFromStream;
            }
        } catch (NotFoundException e2) {
            throw new FileNotFoundException("Resource does not exist: " + uri);
        } catch (FileNotFoundException fnfe) {
            new StringBuilder("Icon not found: ").append(uri).append(", ").append(fnfe.getMessage());
            return null;
        } catch (Throwable th) {
            try {
                stream.close();
            } catch (IOException e3) {
                new StringBuilder("Error closing icon stream for ").append(uri);
            }
        }
    }

    private Drawable b(String resourceUri) {
        ConstantState cached = (ConstantState) this.n.get(resourceUri);
        if (cached == null) {
            return null;
        }
        return cached.newDrawable();
    }

    private void a(String resourceUri, Drawable drawable) {
        if (drawable != null) {
            this.n.put(resourceUri, drawable.getConstantState());
        }
    }

    private Drawable a(ComponentName component) {
        PackageManager pm = this.d.getPackageManager();
        try {
            ActivityInfo activityInfo = pm.getActivityInfo(component, 128);
            int iconId = activityInfo.getIconResource();
            if (iconId == 0) {
                return null;
            }
            Drawable drawable = pm.getDrawable(component.getPackageName(), iconId, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            new StringBuilder("Invalid icon resource ").append(iconId).append(" for ").append(component.flattenToShortString());
            return null;
        } catch (NameNotFoundException e) {
            e.toString();
            return null;
        }
    }

    public static String a(Cursor cursor, String columnName) {
        return a(cursor, cursor.getColumnIndex(columnName));
    }

    private static String a(Cursor cursor, int col) {
        String str = null;
        if (col == -1) {
            return str;
        }
        try {
            return cursor.getString(col);
        } catch (Exception e) {
            return str;
        }
    }

    private Drawable b(Uri uri) throws FileNotFoundException {
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources r = this.d.getPackageManager().getResourcesForApplication(authority);
            List<String> path = uri.getPathSegments();
            if (path == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int id;
            int len = path.size();
            if (len == 1) {
                try {
                    id = Integer.parseInt((String) path.get(0));
                } catch (NumberFormatException e) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else if (len == 2) {
                id = r.getIdentifier((String) path.get(1), (String) path.get(0), authority);
            } else {
                throw new FileNotFoundException("More than two path segments: " + uri);
            }
            if (id != 0) {
                return r.getDrawable(id);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (NameNotFoundException e2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }
}
