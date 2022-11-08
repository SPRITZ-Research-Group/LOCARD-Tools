package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;

@RestrictTo({a.LIBRARY_GROUP})
abstract class d extends c {
    boolean mStartedActivityFromFragment;

    d() {
    }

    @RequiresApi(16)
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (!(this.mStartedActivityFromFragment || requestCode == -1)) {
            c.checkForValidRequestCode(requestCode);
        }
        super.startActivityForResult(intent, requestCode, options);
    }

    @RequiresApi(16)
    public void startIntentSenderForResult(IntentSender intent, int requestCode, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws SendIntentException {
        if (!(this.mStartedIntentSenderFromFragment || requestCode == -1)) {
            c.checkForValidRequestCode(requestCode);
        }
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }
}
