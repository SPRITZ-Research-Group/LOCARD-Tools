package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertController.RecycleListView;
import android.support.v7.app.AlertController.a.AnonymousClass1;
import android.support.v7.app.AlertController.a.AnonymousClass2;
import android.support.v7.app.AlertController.a.AnonymousClass3;
import android.support.v7.app.AlertController.a.AnonymousClass4;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
    final AlertController a = new AlertController(getContext(), this, getWindow());

    public static class a {
        private final android.support.v7.app.AlertController.a a;
        private final int b;

        public a(@NonNull Context context) {
            this(context, AlertDialog.a(context, 0));
        }

        public a(@NonNull Context context, @StyleRes int themeResId) {
            this.a = new android.support.v7.app.AlertController.a(new ContextThemeWrapper(context, AlertDialog.a(context, themeResId)));
            this.b = themeResId;
        }

        @NonNull
        public final Context a() {
            return this.a.a;
        }

        public final a a(@Nullable CharSequence title) {
            this.a.f = title;
            return this;
        }

        public final a a(@Nullable View customTitleView) {
            this.a.g = customTitleView;
            return this;
        }

        public final a b(@Nullable CharSequence message) {
            this.a.h = message;
            return this;
        }

        public final a a(@Nullable Drawable icon) {
            this.a.d = icon;
            return this;
        }

        public final a a(CharSequence text, OnClickListener listener) {
            this.a.i = text;
            this.a.j = listener;
            return this;
        }

        public final a b(CharSequence text, OnClickListener listener) {
            this.a.k = text;
            this.a.l = listener;
            return this;
        }

        public final a b() {
            this.a.o = false;
            return this;
        }

        public final a a(OnKeyListener onKeyListener) {
            this.a.r = onKeyListener;
            return this;
        }

        public final a a(CharSequence[] items, OnClickListener listener) {
            this.a.s = items;
            this.a.u = listener;
            return this;
        }

        public final a a(ListAdapter adapter, OnClickListener listener) {
            this.a.t = adapter;
            this.a.u = listener;
            return this;
        }

        public final AlertDialog c() {
            AlertDialog alertDialog = new AlertDialog(this.a.a, this.b);
            android.support.v7.app.AlertController.a aVar = this.a;
            AlertController alertController = alertDialog.a;
            if (aVar.g != null) {
                alertController.a(aVar.g);
            } else {
                if (aVar.f != null) {
                    alertController.a(aVar.f);
                }
                if (aVar.d != null) {
                    alertController.a(aVar.d);
                }
                if (aVar.c != 0) {
                    alertController.b(aVar.c);
                }
                if (aVar.e != 0) {
                    alertController.b(alertController.c(aVar.e));
                }
            }
            if (aVar.h != null) {
                alertController.b(aVar.h);
            }
            if (aVar.i != null) {
                alertController.a(-1, aVar.i, aVar.j, null);
            }
            if (aVar.k != null) {
                alertController.a(-2, aVar.k, aVar.l, null);
            }
            if (aVar.m != null) {
                alertController.a(-3, aVar.m, aVar.n, null);
            }
            if (!(aVar.s == null && aVar.H == null && aVar.t == null)) {
                ListAdapter listAdapter;
                RecycleListView recycleListView = (RecycleListView) aVar.b.inflate(alertController.l, null);
                Object listAdapter2;
                if (!aVar.D) {
                    int i;
                    if (aVar.E) {
                        i = alertController.n;
                    } else {
                        i = alertController.o;
                    }
                    if (aVar.H != null) {
                        listAdapter2 = new SimpleCursorAdapter(aVar.a, i, aVar.H, new String[]{aVar.I}, new int[]{16908308});
                    } else if (aVar.t != null) {
                        listAdapter2 = aVar.t;
                    } else {
                        listAdapter2 = new c(aVar.a, i, aVar.s);
                    }
                } else if (aVar.H == null) {
                    listAdapter2 = new AnonymousClass1(aVar, aVar.a, alertController.m, aVar.s, recycleListView);
                } else {
                    listAdapter2 = new AnonymousClass2(aVar, aVar.a, aVar.H, recycleListView, alertController);
                }
                alertController.j = listAdapter2;
                alertController.k = aVar.F;
                if (aVar.u != null) {
                    recycleListView.setOnItemClickListener(new AnonymousClass3(aVar, alertController));
                } else if (aVar.G != null) {
                    recycleListView.setOnItemClickListener(new AnonymousClass4(aVar, recycleListView, alertController));
                }
                if (aVar.K != null) {
                    recycleListView.setOnItemSelectedListener(aVar.K);
                }
                if (aVar.E) {
                    recycleListView.setChoiceMode(1);
                } else if (aVar.D) {
                    recycleListView.setChoiceMode(2);
                }
                alertController.b = recycleListView;
            }
            if (aVar.w != null) {
                if (aVar.B) {
                    alertController.a(aVar.w, aVar.x, aVar.y, aVar.z, aVar.A);
                } else {
                    alertController.b(aVar.w);
                }
            } else if (aVar.v != 0) {
                alertController.a(aVar.v);
            }
            alertDialog.setCancelable(this.a.o);
            if (this.a.o) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.a.p);
            alertDialog.setOnDismissListener(this.a.q);
            if (this.a.r != null) {
                alertDialog.setOnKeyListener(this.a.r);
            }
            return alertDialog;
        }
    }

    protected AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, a(context, themeResId));
    }

    static int a(@NonNull Context context, @StyleRes int resid) {
        if (((resid >>> 24) & 255) > 0) {
            return resid;
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.support.v7.appcompat.a.a.alertDialogTheme, outValue, true);
        return outValue.resourceId;
    }

    public void setTitle(CharSequence title) {
        super.setTitle(title);
        this.a.a(title);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.a.a();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean z;
        AlertController alertController = this.a;
        if (alertController.i == null || !alertController.i.a(event)) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean z;
        AlertController alertController = this.a;
        if (alertController.i == null || !alertController.i.a(event)) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
