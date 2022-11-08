package com.facebook.login.widget;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.internal.as;
import com.facebook.login.i;
import com.facebook.login.s;
import defpackage.amm;

public class c implements OnClickListener {
    final /* synthetic */ LoginButton b;

    protected c(LoginButton loginButton) {
        this.b = loginButton;
    }

    public void onClick(View view) {
        this.b.a(view);
        AccessToken a = AccessToken.a();
        int i = 0;
        if (AccessToken.b()) {
            Context context = this.b.getContext();
            final i a2 = a();
            if (this.b.b) {
                CharSequence string;
                CharSequence string2 = this.b.getResources().getString(s.com_facebook_loginview_log_out_action);
                CharSequence string3 = this.b.getResources().getString(s.com_facebook_loginview_cancel_action);
                Profile a3 = Profile.a();
                if (a3 == null || a3.c() == null) {
                    string = this.b.getResources().getString(s.com_facebook_loginview_logged_in_using_facebook);
                } else {
                    string = String.format(this.b.getResources().getString(s.com_facebook_loginview_logged_in_as), new Object[]{a3.c()});
                }
                Builder builder = new Builder(context);
                builder.setMessage(string).setCancelable(true).setPositiveButton(string2, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ c b;

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        a2.c();
                    }
                }).setNegativeButton(string3, null);
                builder.create().show();
            } else {
                a2.c();
            }
        } else {
            i a4 = a();
            if (as.PUBLISH.equals(this.b.e.c)) {
                if (this.b.b() != null) {
                    a4.b(this.b.b(), this.b.e.b);
                } else if (this.b.c() != null) {
                    a4.b(this.b.c(), this.b.e.b);
                } else {
                    a4.b(this.b.e(), this.b.e.b);
                }
            } else if (this.b.b() != null) {
                a4.a(this.b.b(), this.b.e.b);
            } else if (this.b.c() != null) {
                a4.a(this.b.c(), this.b.e.b);
            } else {
                a4.a(this.b.e(), this.b.e.b);
            }
        }
        amm a5 = amm.a(this.b.getContext());
        Bundle bundle = new Bundle();
        String str = "logging_in";
        if (a == null) {
            i = 1;
        }
        bundle.putInt(str, i);
        bundle.putInt("access_token_expired", AccessToken.b());
        a5.b(this.b.f, bundle);
    }

    protected i a() {
        i b = i.b();
        b.a(this.b.i());
        b.a(this.b.j());
        b.a(this.b.k());
        return b;
    }
}
