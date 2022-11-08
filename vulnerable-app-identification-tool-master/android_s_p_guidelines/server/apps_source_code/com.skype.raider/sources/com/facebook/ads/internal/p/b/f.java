package com.facebook.ads.internal.p.b;

import android.content.Context;
import android.os.SystemClock;
import com.facebook.ads.internal.p.b.a.g;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class f {
    private final Object a;
    private final ExecutorService b;
    private final Map<String, g> c;
    private final ServerSocket d;
    private final int e;
    private final Thread f;
    private final c g;
    private boolean h;

    public static final class a {
        private File a;
        private com.facebook.ads.internal.p.b.a.c b = new com.facebook.ads.internal.p.b.a.f();
        private com.facebook.ads.internal.p.b.a.a c = new g();

        public a(Context context) {
            this.a = o.a(context);
        }
    }

    private class b implements Callable<Boolean> {
        final /* synthetic */ f a;

        private b(f fVar) {
            this.a = fVar;
        }

        /* synthetic */ b(f fVar, byte b) {
            this(fVar);
        }

        public final /* synthetic */ Object call() {
            return Boolean.valueOf(this.a.b());
        }
    }

    private class c implements Callable<Boolean> {
        final /* synthetic */ f a;
        private final String b;

        public c(f fVar, String str) {
            this.a = fVar;
            this.b = str;
        }

        public final /* synthetic */ Object call() {
            return Boolean.valueOf(this.a.c(this.b));
        }
    }

    private final class d implements Runnable {
        final /* synthetic */ f a;
        private final Socket b;

        public d(f fVar, Socket socket) {
            this.a = fVar;
            this.b = socket;
        }

        public final void run() {
            f.a(this.a, this.b);
        }
    }

    private final class e implements Runnable {
        final /* synthetic */ f a;
        private final CountDownLatch b;

        public e(f fVar, CountDownLatch countDownLatch) {
            this.a = fVar;
            this.b = countDownLatch;
        }

        public final void run() {
            this.b.countDown();
            f.a(this.a);
        }
    }

    public f(Context context) {
        this(new c(new a(context).a, new a(context).b, new a(context).c));
    }

    private f(c cVar) {
        Throwable e;
        this.a = new Object();
        this.b = Executors.newFixedThreadPool(8);
        this.c = new ConcurrentHashMap();
        this.g = (c) j.a(cVar);
        try {
            this.d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.e = this.d.getLocalPort();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f = new Thread(new e(this, countDownLatch));
            this.f.start();
            countDownLatch.await();
            a();
        } catch (IOException e2) {
            e = e2;
            this.b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        } catch (InterruptedException e3) {
            e = e3;
            this.b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    private void a() {
        int i = 0;
        int i2 = 300;
        while (true) {
            int i3 = i2;
            i2 = i;
            if (i2 < 3) {
                try {
                    this.h = ((Boolean) this.b.submit(new b()).get((long) i3, TimeUnit.MILLISECONDS)).booleanValue();
                    if (!this.h) {
                        SystemClock.sleep((long) i3);
                        i = i2 + 1;
                        i2 = i3 * 2;
                    } else {
                        return;
                    }
                } catch (InterruptedException e) {
                    new StringBuilder("Error pinging server [attempt: ").append(i2).append(", timeout: ").append(i3).append("]. ");
                    i = i2 + 1;
                    i2 = i3 * 2;
                } catch (ExecutionException e2) {
                    new StringBuilder("Error pinging server [attempt: ").append(i2).append(", timeout: ").append(i3).append("]. ");
                    i = i2 + 1;
                    i2 = i3 * 2;
                } catch (TimeoutException e3) {
                    new StringBuilder("Error pinging server [attempt: ").append(i2).append(", timeout: ").append(i3).append("]. ");
                    i = i2 + 1;
                    i2 = i3 * 2;
                }
            } else {
                new StringBuilder("Shutdown server... Error pinging server [attempts: ").append(i2).append(", max timeout: ").append(i3 / 2).append("].");
                c();
                return;
            }
        }
    }

    private boolean b() {
        h hVar = new h(d("ping"));
        boolean equals;
        try {
            byte[] bytes = "ping ok".getBytes();
            hVar.a(0);
            byte[] bArr = new byte[bytes.length];
            hVar.a(bArr);
            equals = Arrays.equals(bytes, bArr);
            new StringBuilder("Ping response: `").append(new String(bArr)).append("`, pinged? ").append(equals);
            return equals;
        } catch (l e) {
            equals = e;
            return false;
        } finally {
            hVar.b();
        }
    }

    private boolean c(String str) {
        h hVar = new h(d(str));
        try {
            hVar.a(0);
            while (true) {
                if (hVar.a(new byte[8192]) == -1) {
                    break;
                }
            }
            return true;
        } catch (l e) {
            return false;
        } finally {
            hVar.b();
        }
    }

    private int d() {
        int i;
        synchronized (this.a) {
            int i2 = 0;
            Iterator it = this.c.values().iterator();
            while (true) {
                i = i2;
                if (it.hasNext()) {
                    i2 = ((g) it.next()).b() + i;
                }
            }
        }
        return i;
    }

    private String d(String str) {
        return String.format("http://%s:%d/%s", new Object[]{"127.0.0.1", Integer.valueOf(this.e), m.a(str)});
    }

    private g e(String str) {
        g gVar;
        synchronized (this.a) {
            gVar = (g) this.c.get(str);
            if (gVar == null) {
                gVar = new g(str, this.g);
                this.c.put(str, gVar);
            }
        }
        return gVar;
    }

    public final boolean a(String str) {
        int i = 300;
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i2 < 3) {
                try {
                    if (((Boolean) this.b.submit(new c(this, str)).get()).booleanValue()) {
                        return true;
                    }
                    SystemClock.sleep((long) i3);
                    i = i2 + 1;
                    i2 = i3 * 2;
                    i3 = i2;
                } catch (InterruptedException e) {
                    new StringBuilder("Error precaching url [attempt: ").append(i2).append(", url: ").append(str).append("]. ");
                    i = i2 + 1;
                    i2 = i3 * 2;
                    i3 = i2;
                } catch (ExecutionException e2) {
                    new StringBuilder("Error precaching url [attempt: ").append(i2).append(", url: ").append(str).append("]. ");
                    i = i2 + 1;
                    i2 = i3 * 2;
                    i3 = i2;
                }
            } else {
                new StringBuilder("Shutdown server... Error precaching url [attempts: ").append(i2).append(", url: ").append(str).append("].");
                c();
                return false;
            }
        }
    }

    public final String b(String str) {
        return this.h ? d(str) : str;
    }

    private void c() {
        synchronized (this.a) {
            for (g a : this.c.values()) {
                a.a();
            }
            this.c.clear();
        }
        this.f.interrupt();
        try {
            if (!this.d.isClosed()) {
                this.d.close();
            }
        } catch (Throwable e) {
            l lVar = new l("Error shutting down proxy server", e);
        }
    }

    private static void a(Socket socket) {
        l lVar;
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException e) {
        } catch (Throwable e2) {
            lVar = new l("Error closing socket input stream", e2);
        }
        try {
            if (socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (Throwable e22) {
            lVar = new l("Error closing socket output stream", e22);
        }
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (Throwable e222) {
            lVar = new l("Error closing socket", e222);
        }
    }

    static /* synthetic */ void a(f fVar) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = fVar.d.accept();
                new StringBuilder("Accept new socket ").append(accept);
                fVar.b.submit(new d(fVar, accept));
            } catch (Throwable e) {
                l lVar = new l("Error during waiting connection", e);
                return;
            }
        }
    }

    static /* synthetic */ void a(f fVar, Socket socket) {
        Throwable e;
        l lVar;
        try {
            d a = d.a(socket.getInputStream());
            new StringBuilder("Request to cache proxy:").append(a);
            String b = m.b(a.a);
            if ("ping".equals(b)) {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
                outputStream.write("ping ok".getBytes());
            } else {
                fVar.e(b).a(a, socket);
            }
            a(socket);
            new StringBuilder("Opened connections: ").append(fVar.d());
        } catch (SocketException e2) {
            a(socket);
            new StringBuilder("Opened connections: ").append(fVar.d());
        } catch (l e3) {
            e = e3;
            try {
                lVar = new l("Error processing request", e);
            } finally {
                a(socket);
                new StringBuilder("Opened connections: ").append(fVar.d());
            }
        } catch (IOException e4) {
            e = e4;
            lVar = new l("Error processing request", e);
        }
    }
}
