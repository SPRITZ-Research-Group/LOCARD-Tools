package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.internal.g;

public abstract class j implements OnClickListener {
    public static j a(Activity activity, Intent intent, int i) {
        return new ak(intent, activity, i);
    }

    public static j a(@NonNull g gVar, Intent intent) {
        return new al(intent, gVar);
    }

    protected abstract void a();

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            a();
        } catch (ActivityNotFoundException e) {
        } finally {
            dialogInterface.dismiss();
        }
    }
}
