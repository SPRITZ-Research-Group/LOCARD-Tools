package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.d;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.an;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

final class g {
    private static final Class<?>[] a = new Class[]{Context.class, AttributeSet.class};
    private static final int[] b = new int[]{16843375};
    private static final String[] c = new String[]{"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> d = new android.support.v4.util.a();
    private final Object[] e = new Object[2];

    private static class a implements OnClickListener {
        private final View a;
        private final String b;
        private Method c;
        private Context d;

        public a(@NonNull View hostView, @NonNull String methodName) {
            this.a = hostView;
            this.b = methodName;
        }

        public final void onClick(@NonNull View v) {
            if (this.c == null) {
                String str;
                Context context = this.a.getContext();
                while (context != null) {
                    try {
                        if (!context.isRestricted()) {
                            Method method = context.getClass().getMethod(this.b, new Class[]{View.class});
                            if (method != null) {
                                this.c = method;
                                this.d = context;
                            }
                        }
                    } catch (NoSuchMethodException e) {
                    }
                    if (context instanceof ContextWrapper) {
                        context = ((ContextWrapper) context).getBaseContext();
                    } else {
                        context = null;
                    }
                }
                int id = this.a.getId();
                if (id == -1) {
                    str = "";
                } else {
                    str = " with id '" + this.a.getContext().getResources().getResourceEntryName(id) + "'";
                }
                throw new IllegalStateException("Could not find method " + this.b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.a.getClass() + str);
            }
            try {
                this.c.invoke(this.d, new Object[]{v});
            } catch (IllegalAccessException e2) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
            } catch (InvocationTargetException e3) {
                throw new IllegalStateException("Could not execute method for android:onClick", e3);
            }
        }
    }

    g() {
    }

    public final View a(View parent, String name, @NonNull Context context, @NonNull AttributeSet attrs, boolean inheritContext, boolean readAndroidTheme, boolean wrapContext) {
        int resourceId;
        int resourceId2;
        Context originalContext = context;
        if (inheritContext && parent != null) {
            context = parent.getContext();
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, j.View, 0, 0);
        if (readAndroidTheme) {
            resourceId = obtainStyledAttributes.getResourceId(j.View_android_theme, 0);
        } else {
            resourceId = 0;
        }
        if (resourceId == 0) {
            resourceId2 = obtainStyledAttributes.getResourceId(j.View_theme, 0);
        } else {
            resourceId2 = resourceId;
        }
        obtainStyledAttributes.recycle();
        if (!(resourceId2 == 0 || ((context instanceof d) && ((d) context).a() == resourceId2))) {
            context = new d(context, resourceId2);
        }
        if (wrapContext) {
            context = an.a(context);
        }
        View view = null;
        resourceId = -1;
        switch (name.hashCode()) {
            case -1946472170:
                if (name.equals("RatingBar")) {
                    resourceId = 11;
                    break;
                }
                break;
            case -1455429095:
                if (name.equals("CheckedTextView")) {
                    resourceId = 8;
                    break;
                }
                break;
            case -1346021293:
                if (name.equals("MultiAutoCompleteTextView")) {
                    resourceId = 10;
                    break;
                }
                break;
            case -938935918:
                if (name.equals("TextView")) {
                    resourceId = 0;
                    break;
                }
                break;
            case -937446323:
                if (name.equals("ImageButton")) {
                    resourceId = 5;
                    break;
                }
                break;
            case -658531749:
                if (name.equals("SeekBar")) {
                    resourceId = 12;
                    break;
                }
                break;
            case -339785223:
                if (name.equals("Spinner")) {
                    resourceId = 4;
                    break;
                }
                break;
            case 776382189:
                if (name.equals("RadioButton")) {
                    resourceId = 7;
                    break;
                }
                break;
            case 1125864064:
                if (name.equals("ImageView")) {
                    resourceId = 1;
                    break;
                }
                break;
            case 1413872058:
                if (name.equals("AutoCompleteTextView")) {
                    resourceId = 9;
                    break;
                }
                break;
            case 1601505219:
                if (name.equals("CheckBox")) {
                    resourceId = 6;
                    break;
                }
                break;
            case 1666676343:
                if (name.equals("EditText")) {
                    resourceId = 3;
                    break;
                }
                break;
            case 2001146706:
                if (name.equals("Button")) {
                    resourceId = 2;
                    break;
                }
                break;
        }
        switch (resourceId) {
            case 0:
                view = new AppCompatTextView(context, attrs);
                break;
            case 1:
                view = new AppCompatImageView(context, attrs);
                break;
            case 2:
                view = new AppCompatButton(context, attrs);
                break;
            case 3:
                view = new AppCompatEditText(context, attrs);
                break;
            case 4:
                view = new AppCompatSpinner(context, attrs);
                break;
            case 5:
                view = new AppCompatImageButton(context, attrs);
                break;
            case 6:
                view = new AppCompatCheckBox(context, attrs);
                break;
            case 7:
                view = new AppCompatRadioButton(context, attrs);
                break;
            case 8:
                view = new AppCompatCheckedTextView(context, attrs);
                break;
            case 9:
                view = new AppCompatAutoCompleteTextView(context, attrs);
                break;
            case 10:
                view = new AppCompatMultiAutoCompleteTextView(context, attrs);
                break;
            case 11:
                view = new AppCompatRatingBar(context, attrs);
                break;
            case 12:
                view = new AppCompatSeekBar(context, attrs);
                break;
        }
        if (view == null && originalContext != context) {
            view = a(context, name, attrs);
        }
        if (view != null) {
            Context context2 = view.getContext();
            if ((context2 instanceof ContextWrapper) && (VERSION.SDK_INT < 15 || ViewCompat.E(view))) {
                TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(attrs, b);
                String string = obtainStyledAttributes2.getString(0);
                if (string != null) {
                    view.setOnClickListener(new a(view, string));
                }
                obtainStyledAttributes2.recycle();
            }
        }
        return view;
    }

    private View a(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }
        try {
            this.e[0] = context;
            this.e[1] = attrs;
            View view;
            if (-1 == name.indexOf(46)) {
                for (String a : c) {
                    view = a(context, name, a);
                    if (view != null) {
                        return view;
                    }
                }
                this.e[0] = null;
                this.e[1] = null;
                return null;
            }
            view = a(context, name, null);
            this.e[0] = null;
            this.e[1] = null;
            return view;
        } catch (Exception e) {
            return null;
        } finally {
            this.e[0] = null;
            this.e[1] = null;
        }
    }

    private View a(Context context, String name, String prefix) throws ClassNotFoundException, InflateException {
        Constructor<? extends View> constructor = (Constructor) d.get(name);
        if (constructor == null) {
            try {
                constructor = context.getClassLoader().loadClass(prefix != null ? prefix + name : name).asSubclass(View.class).getConstructor(a);
                d.put(name, constructor);
            } catch (Exception e) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.e);
    }
}
