package androidx.appcompat.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import com.google.android.exoplayer.SampleSource;
import java.lang.ref.WeakReference;

final class e extends Handler {
    private WeakReference<DialogInterface> a;

    public e(DialogInterface dialogInterface) {
        this.a = new WeakReference(dialogInterface);
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            switch (i) {
                case SampleSource.SAMPLE_READ /*-3*/:
                case -2:
                case -1:
                    ((OnClickListener) message.obj).onClick((DialogInterface) this.a.get(), message.what);
                    return;
            }
        }
        ((DialogInterface) message.obj).dismiss();
    }
}
