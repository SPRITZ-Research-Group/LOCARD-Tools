package com.skype.android.event;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

public class ListenerErrorReporter implements Callback {
    private static final Handler a = new Handler(Looper.getMainLooper(), new ListenerErrorReporter());

    public boolean handleMessage(Message msg) {
        Object object = msg.obj;
        if (!(object instanceof Error)) {
            return false;
        }
        throw ((Error) object);
    }

    public static void a(ListenerError listenerError) {
        Message msg = new Message();
        msg.what = 1229867602;
        msg.obj = listenerError;
        a.sendMessage(msg);
    }
}
