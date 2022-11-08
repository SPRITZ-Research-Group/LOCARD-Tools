package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DialogFragment extends Fragment implements OnCancelListener, OnDismissListener {
    int a = 0;
    int b = 0;
    boolean c = true;
    boolean d = true;
    int e = -1;
    Dialog f;
    boolean g;
    boolean h;
    boolean i;

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    private @interface DialogStyle {
    }

    public void a(i manager, String tag) {
        this.h = false;
        this.i = true;
        FragmentTransaction ft = manager.a();
        ft.a(this, tag);
        ft.b();
    }

    public final void a() {
        b(false);
    }

    private void b(boolean allowStateLoss) {
        if (!this.h) {
            this.h = true;
            this.i = false;
            if (this.f != null) {
                this.f.dismiss();
                this.f = null;
            }
            this.g = true;
            if (this.e >= 0) {
                getFragmentManager().a(this.e);
                this.e = -1;
                return;
            }
            FragmentTransaction ft = getFragmentManager().a();
            ft.a(this);
            if (allowStateLoss) {
                ft.c();
            } else {
                ft.b();
            }
        }
    }

    public final void a(boolean cancelable) {
        this.c = cancelable;
        if (this.f != null) {
            this.f.setCancelable(cancelable);
        }
    }

    public final void b() {
        this.d = false;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (!this.i) {
            this.h = false;
        }
    }

    public void onDetach() {
        super.onDetach();
        if (!this.i && !this.h) {
            this.h = true;
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.d = this.mContainerId == 0;
        if (savedInstanceState != null) {
            this.a = savedInstanceState.getInt("android:style", 0);
            this.b = savedInstanceState.getInt("android:theme", 0);
            this.c = savedInstanceState.getBoolean("android:cancelable", true);
            this.d = savedInstanceState.getBoolean("android:showsDialog", this.d);
            this.e = savedInstanceState.getInt("android:backStackId", -1);
        }
    }

    public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
        if (!this.d) {
            return super.onGetLayoutInflater(savedInstanceState);
        }
        this.f = c();
        if (this.f == null) {
            return (LayoutInflater) this.mHost.b.getSystemService("layout_inflater");
        }
        Dialog dialog = this.f;
        switch (this.a) {
            case 1:
            case 2:
                break;
            case 3:
                dialog.getWindow().addFlags(24);
                break;
        }
        dialog.requestWindowFeature(1);
        return (LayoutInflater) this.f.getContext().getSystemService("layout_inflater");
    }

    @NonNull
    public Dialog c() {
        return new Dialog(getActivity(), this.b);
    }

    public void onCancel(DialogInterface dialog) {
    }

    public void onDismiss(DialogInterface dialog) {
        if (!this.g) {
            b(true);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.d) {
            View view = getView();
            if (view != null) {
                if (view.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.f.setContentView(view);
            }
            Activity activity = getActivity();
            if (activity != null) {
                this.f.setOwnerActivity(activity);
            }
            this.f.setCancelable(this.c);
            this.f.setOnCancelListener(this);
            this.f.setOnDismissListener(this);
            if (savedInstanceState != null) {
                Bundle dialogState = savedInstanceState.getBundle("android:savedDialogState");
                if (dialogState != null) {
                    this.f.onRestoreInstanceState(dialogState);
                }
            }
        }
    }

    public void onStart() {
        super.onStart();
        if (this.f != null) {
            this.g = false;
            this.f.show();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.f != null) {
            Bundle dialogState = this.f.onSaveInstanceState();
            if (dialogState != null) {
                outState.putBundle("android:savedDialogState", dialogState);
            }
        }
        if (this.a != 0) {
            outState.putInt("android:style", this.a);
        }
        if (this.b != 0) {
            outState.putInt("android:theme", this.b);
        }
        if (!this.c) {
            outState.putBoolean("android:cancelable", this.c);
        }
        if (!this.d) {
            outState.putBoolean("android:showsDialog", this.d);
        }
        if (this.e != -1) {
            outState.putInt("android:backStackId", this.e);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.f != null) {
            this.f.hide();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.f != null) {
            this.g = true;
            this.f.dismiss();
            this.f = null;
        }
    }
}
