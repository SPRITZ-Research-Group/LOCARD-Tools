package com.skpcamera.antediluvian;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.facebook.common.logging.FLog;

abstract class e implements Callback, k {
    protected final String a;
    private boolean b = true;
    private HandlerThread c;
    private Handler d;

    abstract boolean a(j jVar, Object obj);

    e(String name) {
        this.a = name;
        this.c = new HandlerThread(name);
        this.c.start();
        this.d = new Handler(this.c.getLooper(), this);
    }

    public final boolean handleMessage(Message msg) {
        j command = j.a(msg.what);
        boolean handled = a(command, msg.obj);
        if (command == j.RELEASE) {
            FLog.i(this.a, "handleMessage: RELEASE " + handled);
            this.c.quit();
        }
        return handled;
    }

    public final boolean a(j command) {
        return b(command, null);
    }

    public final boolean b(j command, Object data) {
        if (this.c == null) {
            return false;
        }
        if (command == j.RELEASE) {
            FLog.i(this.a, "send: RELEASE");
            return this.d.sendMessageAtFrontOfQueue(this.d.obtainMessage(command.ordinal(), data));
        }
        return this.d.sendMessage(this.d.obtainMessage(command.ordinal(), data));
    }

    protected final Handler a() {
        return this.d;
    }

    public final void b() {
        FLog.i(this.a, "releaseAndWait sent:%b thread:%b ownsThread:%b", Boolean.valueOf(b(j.RELEASE, null)), Boolean.valueOf(this.c != null), Boolean.valueOf(this.b));
        if (this.c != null && this.b) {
            try {
                this.c.join();
                FLog.i(this.a, "releaseAndWait: join done");
            } catch (InterruptedException e) {
                FLog.e(this.a, "releaseAndWait: " + e.getLocalizedMessage());
            } finally {
                FLog.i(this.a, "releaseAndWait: setting thread to null");
                this.c = null;
            }
        }
        FLog.i(this.a, "releaseAndWait: done");
    }
}
