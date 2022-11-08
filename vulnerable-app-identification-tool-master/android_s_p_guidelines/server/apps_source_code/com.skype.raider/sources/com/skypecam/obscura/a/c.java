package com.skypecam.obscura.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.skypecam.obscura.e.g;

abstract class c implements Callback, h {
    protected final String a;
    private boolean b = true;
    private HandlerThread c;
    private Handler d;

    abstract boolean a(g gVar, Object obj);

    c(String name) {
        this.a = name;
        this.c = new HandlerThread(name);
        this.c.start();
        this.d = new Handler(this.c.getLooper(), this);
    }

    public final boolean handleMessage(Message msg) {
        g command = g.a(msg.what);
        boolean handled = a(command, msg.obj);
        if (command == g.RELEASE) {
            g.a().b(this.a, "handleMessage: RELEASE " + handled);
            this.c.quit();
        }
        return handled;
    }

    public boolean a(g command) {
        return b(command, null);
    }

    public boolean b(g command, Object data) {
        if (this.c == null) {
            return false;
        }
        if (command == g.RELEASE) {
            g.a().b(this.a, "send: RELEASE");
            return this.d.sendMessageAtFrontOfQueue(this.d.obtainMessage(command.ordinal(), data));
        }
        return this.d.sendMessage(this.d.obtainMessage(command.ordinal(), data));
    }

    protected final Handler c() {
        return this.d;
    }

    public void b() {
        g.a().b(this.a, "releaseAndWait sent:" + a(g.RELEASE) + " thread:" + (this.c != null) + " ownsThread:" + this.b);
        if (this.c != null && this.b) {
            try {
                this.c.join();
                g.a().b(this.a, "releaseAndWait: join done");
            } catch (InterruptedException e) {
                g.a().d(this.a, "releaseAndWait: " + e.getLocalizedMessage());
            } finally {
                g.a().b(this.a, "releaseAndWait: setting thread to null");
                this.c = null;
            }
        }
        g.a().b(this.a, "releaseAndWait: done");
    }
}
