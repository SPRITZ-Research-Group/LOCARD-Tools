package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class c {
    private static final Object f = new Object();
    private static c g;
    private final Context a;
    private final HashMap<BroadcastReceiver, ArrayList<b>> b = new HashMap();
    private final HashMap<String, ArrayList<b>> c = new HashMap();
    private final ArrayList<a> d = new ArrayList();
    private final Handler e;

    private static final class a {
        final Intent a;
        final ArrayList<b> b;

        a(Intent _intent, ArrayList<b> _receivers) {
            this.a = _intent;
            this.b = _receivers;
        }
    }

    private static final class b {
        final IntentFilter a;
        final BroadcastReceiver b;
        boolean c;
        boolean d;

        b(IntentFilter _filter, BroadcastReceiver _receiver) {
            this.a = _filter;
            this.b = _receiver;
        }

        public final String toString() {
            StringBuilder builder = new StringBuilder(128);
            builder.append("Receiver{");
            builder.append(this.b);
            builder.append(" filter=");
            builder.append(this.a);
            if (this.d) {
                builder.append(" DEAD");
            }
            builder.append("}");
            return builder.toString();
        }
    }

    public static c a(Context context) {
        c cVar;
        synchronized (f) {
            if (g == null) {
                g = new c(context.getApplicationContext());
            }
            cVar = g;
        }
        return cVar;
    }

    private c(Context context) {
        this.a = context;
        this.e = new Handler(this, context.getMainLooper()) {
            final /* synthetic */ c a;

            public final void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        this.a.a();
                        return;
                    default:
                        super.handleMessage(msg);
                        return;
                }
            }
        };
    }

    public final void a(BroadcastReceiver receiver, IntentFilter filter) {
        synchronized (this.b) {
            b entry = new b(filter, receiver);
            ArrayList<b> filters = (ArrayList) this.b.get(receiver);
            if (filters == null) {
                filters = new ArrayList(1);
                this.b.put(receiver, filters);
            }
            filters.add(entry);
            for (int i = 0; i < filter.countActions(); i++) {
                String action = filter.getAction(i);
                ArrayList<b> entries = (ArrayList) this.c.get(action);
                if (entries == null) {
                    entries = new ArrayList(1);
                    this.c.put(action, entries);
                }
                entries.add(entry);
            }
        }
    }

    public final void a(BroadcastReceiver receiver) {
        synchronized (this.b) {
            ArrayList<b> filters = (ArrayList) this.b.remove(receiver);
            if (filters == null) {
                return;
            }
            for (int i = filters.size() - 1; i >= 0; i--) {
                b filter = (b) filters.get(i);
                filter.d = true;
                for (int j = 0; j < filter.a.countActions(); j++) {
                    String action = filter.a.getAction(j);
                    ArrayList<b> receivers = (ArrayList) this.c.get(action);
                    if (receivers != null) {
                        for (int k = receivers.size() - 1; k >= 0; k--) {
                            b rec = (b) receivers.get(k);
                            if (rec.b == receiver) {
                                rec.d = true;
                                receivers.remove(k);
                            }
                        }
                        if (receivers.size() <= 0) {
                            this.c.remove(action);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(Intent intent) {
        synchronized (this.b) {
            String action = intent.getAction();
            String type = intent.resolveTypeIfNeeded(this.a.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean debug = (intent.getFlags() & 8) != 0;
            if (debug) {
                new StringBuilder("Resolving type ").append(type).append(" scheme ").append(scheme).append(" of intent ").append(intent);
            }
            ArrayList<b> entries = (ArrayList) this.c.get(intent.getAction());
            if (entries != null) {
                int i;
                if (debug) {
                    new StringBuilder("Action list: ").append(entries);
                }
                ArrayList<b> receivers = null;
                for (i = 0; i < entries.size(); i++) {
                    b receiver = (b) entries.get(i);
                    if (debug) {
                        new StringBuilder("Matching against filter ").append(receiver.a);
                    }
                    if (!receiver.c) {
                        int match = receiver.a.match(action, type, scheme, data, categories, "LocalBroadcastManager");
                        if (match >= 0) {
                            if (debug) {
                                new StringBuilder("  Filter matched!  match=0x").append(Integer.toHexString(match));
                            }
                            if (receivers == null) {
                                receivers = new ArrayList();
                            }
                            receivers.add(receiver);
                            receiver.c = true;
                        }
                    } else if (!debug) {
                    }
                }
                if (receivers != null) {
                    for (i = 0; i < receivers.size(); i++) {
                        ((b) receivers.get(i)).c = false;
                    }
                    this.d.add(new a(intent, receivers));
                    if (!this.e.hasMessages(1)) {
                        this.e.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
        }
    }

    public final void b(Intent intent) {
        if (a(intent)) {
            a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() {
        while (true) {
            synchronized (this.b) {
                int N = this.d.size();
                if (N <= 0) {
                    return;
                }
                a[] brs = new a[N];
                this.d.toArray(brs);
                this.d.clear();
            }
        }
    }
}
