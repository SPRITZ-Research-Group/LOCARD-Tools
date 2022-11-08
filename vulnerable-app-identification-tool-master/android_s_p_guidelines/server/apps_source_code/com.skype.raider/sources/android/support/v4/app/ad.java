package android.support.v4.app;

import android.app.RemoteInput;
import android.app.RemoteInput.Builder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ae.a;

@RequiresApi(20)
final class ad {
    static RemoteInput[] a(a[] srcArray) {
        if (srcArray == null) {
            return null;
        }
        RemoteInput[] result = new RemoteInput[srcArray.length];
        for (int i = 0; i < srcArray.length; i++) {
            a src = srcArray[i];
            result[i] = new Builder(src.a()).setLabel(src.b()).setChoices(src.c()).setAllowFreeFormInput(src.e()).addExtras(src.f()).build();
        }
        return result;
    }
}
