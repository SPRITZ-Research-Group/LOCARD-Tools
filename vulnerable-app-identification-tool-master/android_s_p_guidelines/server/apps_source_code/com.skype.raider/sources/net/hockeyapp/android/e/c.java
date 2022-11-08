package net.hockeyapp.android.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import net.hockeyapp.android.UpdateActivity;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.h.d;
import net.hockeyapp.android.j;
import net.hockeyapp.android.m;
import org.json.JSONArray;

public final class c extends b {
    protected boolean f = false;
    private WeakReference<Activity> g = null;
    private AlertDialog h = null;

    static /* synthetic */ void a(c x0, Activity x1, JSONArray x2) {
        if (x1 != null) {
            FragmentTransaction beginTransaction = x1.getFragmentManager().beginTransaction();
            beginTransaction.setTransition(4097);
            Fragment findFragmentByTag = x1.getFragmentManager().findFragmentByTag(j.FRAGMENT_TAG);
            if (findFragmentByTag != null) {
                beginTransaction.remove(findFragmentByTag);
            }
            beginTransaction.addToBackStack(null);
            Class cls = j.class;
            if (x0.e != null) {
                cls = j.class;
            }
            try {
                ((DialogFragment) cls.getMethod("newInstance", new Class[]{String.class, String.class, Boolean.TYPE}).invoke(null, new Object[]{x2.toString(), x0.b, Boolean.valueOf(true)})).show(beginTransaction, j.FRAGMENT_TAG);
            } catch (Exception e) {
                e.f();
            }
        }
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        a((JSONArray) obj);
    }

    public c(WeakReference<Activity> weakActivity, String urlString, String appIdentifier) {
        super(weakActivity, urlString, appIdentifier);
        this.g = weakActivity;
        this.f = true;
    }

    protected final void a(final JSONArray updateInfo) {
        super.a(updateInfo);
        if (updateInfo != null && this.f) {
            final Activity activity = (Activity) this.g.get();
            if (activity != null && !activity.isFinishing()) {
                Builder builder = new Builder(activity);
                builder.setTitle(d.hockeyapp_update_dialog_title);
                if (this.d.booleanValue()) {
                    String d = net.hockeyapp.android.f.j.d((Context) activity);
                    Toast.makeText(activity, activity.getString(d.hockeyapp_update_mandatory_toast, new Object[]{d}), 1).show();
                    a(activity, updateInfo, Boolean.valueOf(true));
                    return;
                }
                builder.setMessage(d.hockeyapp_update_dialog_message);
                builder.setNegativeButton(d.hockeyapp_update_dialog_negative_button, new OnClickListener(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = this$0;
                    }

                    public final void onClick(DialogInterface dialog, int which) {
                        this.a.a();
                        if (this.a.e != null) {
                            m mVar = this.a.e;
                        }
                    }
                });
                builder.setOnCancelListener(new OnCancelListener(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = this$0;
                    }

                    public final void onCancel(DialogInterface dialog) {
                        this.a.a();
                        if (this.a.e != null) {
                            m mVar = this.a.e;
                        }
                    }
                });
                builder.setPositiveButton(d.hockeyapp_update_dialog_positive_button, new OnClickListener(this) {
                    final /* synthetic */ c c;

                    public final void onClick(DialogInterface dialog, int which) {
                        if (this.c.e != null) {
                            m mVar = this.c.e;
                        }
                        if (net.hockeyapp.android.f.j.a(activity).booleanValue()) {
                            c.a(this.c, activity, updateInfo);
                        } else {
                            this.c.a(activity, updateInfo, Boolean.valueOf(false));
                        }
                    }
                });
                this.h = builder.create();
                this.h.show();
            }
        }
    }

    private void a(Activity activity, JSONArray updateInfo, Boolean finish) {
        if (activity != null) {
            Class<? extends j> fragmentClass = j.class;
            if (this.e != null) {
                fragmentClass = j.class;
            }
            Intent intent = new Intent();
            intent.setClass(activity, UpdateActivity.class);
            intent.putExtra("fragmentClass", fragmentClass.getName());
            intent.putExtra(j.FRAGMENT_VERSION_INFO, updateInfo.toString());
            intent.putExtra(j.FRAGMENT_URL, this.b);
            intent.putExtra(j.FRAGMENT_DIALOG, false);
            activity.startActivity(intent);
            if (finish.booleanValue()) {
                activity.finish();
            }
        }
        a();
    }

    protected final void a() {
        super.a();
        this.g = null;
        this.h = null;
    }
}
