package com.skypecam.obscura.b;

import android.os.Handler;
import android.os.Message;
import com.skypecam.obscura.e.g;
import com.skypecam.obscura.e.h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class a<Condition extends Enum<Condition>, State extends Enum<State>> extends Handler {
    public static boolean a = false;
    public static final Runnable b = new Runnable() {
        public final void run() {
            throw new c();
        }
    };
    public static final Runnable c = new Runnable() {
        public final void run() {
        }
    };
    public static final Executor k = new Executor() {
        public final void execute(Runnable runnable) {
            runnable.run();
        }
    };
    protected final String d;
    final LinkedHashMap<EnumSet<Condition>, State> e;
    final EnumMap<State, EnumSet<Condition>> f;
    final EnumSet<Condition> g;
    final EnumSet<Condition> h;
    final EnumSet<Condition> i;
    protected List<a<?>> j = new ArrayList();
    private long l = 0;
    private State m;
    private final EnumMap<State, EnumMap<State, State>> n;
    private final EnumMap<State, EnumMap<State, Runnable>> o;
    private final List<q> p = new CopyOnWriteArrayList();
    private final Class<State> q;
    private final AtomicInteger r = new AtomicInteger(0);
    private final Runnable s = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = this$0;
        }

        public final void run() {
            Iterator it = this.a.j.iterator();
            while (it.hasNext()) {
                it.next();
            }
            this.a.postDelayed(this, this.a.l);
        }
    };
    private boolean t = false;

    public class a<Item> {
        final Condition a;
        final b b;
        final boolean c = true;
        final /* synthetic */ a d;
        private Item e;
        private Item f;

        public a(Condition this$0, b trigger, boolean filter) {
            this.d = this$0;
            this.a = trigger;
            this.b = filter;
            this$0.j.add(this);
        }

        public final Item a() {
            return this.f;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(Object obj, boolean set) {
            if (!this.b.a(obj)) {
                return false;
            }
            if (set) {
                if (this.e != obj) {
                    this.d.i.remove(this.a);
                    this.e = obj;
                }
            } else if (this.e == obj) {
                this.e = null;
                this.d.i.remove(this.a);
            }
            return true;
        }

        public final void a(State nextState) {
            if (this.f != this.e && this.f != null) {
                a aVar = this.d;
                if (!((EnumSet) aVar.f.get(nextState)).contains(this.a)) {
                    b(this.f);
                    this.f = null;
                }
            }
        }

        public final void b() {
            if (this.f == null && this.e != null) {
                a(this.e);
                this.f = this.e;
                if (this.c) {
                    this.d.i.add(this.a);
                }
            }
        }

        public void a(Item item) {
        }

        public void b(Item item) {
        }
    }

    public interface b {
        boolean a(Object obj);
    }

    protected abstract void a(EnumSet<Condition> enumSet);

    protected abstract void b();

    protected abstract void c();

    public a(String tag, Class<State> stateClass, Class<Condition> conditionClass, Condition... initials) throws b {
        this.d = tag;
        this.q = stateClass;
        this.n = new EnumMap(stateClass);
        this.o = new EnumMap(stateClass);
        this.e = new LinkedHashMap();
        this.f = new EnumMap(this.q);
        this.g = EnumSet.noneOf(conditionClass);
        Collections.addAll(this.g, initials);
        this.h = EnumSet.copyOf(this.g);
        this.i = EnumSet.copyOf(this.g);
        b();
        c();
        this.m = b(this.h);
    }

    protected final void a(EnumSet<Condition> conditions, State state) {
        this.e.put(conditions, state);
        this.f.put(state, conditions);
    }

    private State a(State from, State to) {
        return (Enum) a(this.n, (Enum) from, (Enum) to);
    }

    private Runnable b(State from, State to) {
        if (from == to) {
            return b;
        }
        return (Runnable) a(this.o, (Enum) from, (Enum) to);
    }

    protected final void a(Collection<State> froms, State to, Runnable with) {
        for (State from : froms) {
            a((Enum) from, (Enum) to, with);
        }
    }

    protected final void a(State from, State to, Runnable with) {
        if (a((Enum) from, (Enum) to) != null) {
            throw new IllegalStateException("There is an indirect route from " + from + " to " + to);
        } else if (with == null) {
            g.a().b(this.d, from + " -> " + to + " NULL");
            throw new AssertionError();
        } else {
            a(this.o, (Enum) from).put(to, with);
        }
    }

    protected final void a(State from, State to, State via) {
        if (b((Enum) from, (Enum) to) != null) {
            throw new IllegalStateException("There is a direct transition from " + from + " to " + to);
        }
        a(this.n, (Enum) from).put(to, via);
    }

    protected final void a(State from, Collection<State> tos, State via) {
        for (State to : tos) {
            a((Enum) from, (Enum) to, (Enum) via);
        }
    }

    protected final void a(Collection<State> froms, State to, State via) {
        for (State from : froms) {
            a((Enum) from, (Enum) to, (Enum) via);
        }
    }

    private static <R> R a(EnumMap<State, EnumMap<State, R>> tree, State from, State to) {
        EnumMap<State, R> table = (EnumMap) tree.get(from);
        return table == null ? null : table.get(to);
    }

    private <R> EnumMap<State, R> a(EnumMap<State, EnumMap<State, R>> tree, State from) {
        EnumMap<State, R> table = (EnumMap) tree.get(from);
        if (table != null) {
            return table;
        }
        table = new EnumMap(this.q);
        tree.put(from, table);
        return table;
    }

    protected final void a(Class<State> stateEnum, State... omitted) throws IllegalStateException {
        EnumSet<State> uncovered = EnumSet.allOf(stateEnum);
        uncovered.removeAll(Arrays.asList(omitted));
        uncovered.removeAll(this.e.values());
        if (uncovered.size() > 0) {
            throw new IllegalStateException("Impossible to enter states " + uncovered);
        }
    }

    private State b(EnumSet<Condition> flags) {
        a((EnumSet) flags);
        for (Entry<EnumSet<Condition>, State> mapping : this.e.entrySet()) {
            if (flags.containsAll((Collection) mapping.getKey())) {
                return (Enum) mapping.getValue();
            }
        }
        throw new IllegalStateException("Flag combination " + this.i + " designates no valid State");
    }

    protected final void a(Condition condition, boolean set) {
        g.a().b(this.d, condition + "=" + set);
        if (set) {
            this.i.add(condition);
        } else {
            this.i.remove(condition);
        }
    }

    private State c(State prevState, State nextState) {
        while (true) {
            Runnable transit = b((Enum) prevState, nextState);
            if (transit != null) {
                try {
                    g.a().b(this.d, "Direct transition: " + prevState + " -> " + nextState);
                    transit.run();
                    b(nextState);
                    a();
                    return nextState;
                } catch (RuntimeException rte) {
                    throw new ad(prevState, nextState, rte);
                }
            }
            State intermediate = a((Enum) prevState, nextState);
            if (intermediate != null) {
                g.a().b(this.d, "Routed transition: " + prevState + " -> " + intermediate + " -> " + nextState);
                nextState = intermediate;
            } else {
                throw new IllegalStateException("There is no route from " + prevState + " to " + nextState);
            }
        }
    }

    private void a() {
        for (a b : this.j) {
            b.b();
        }
    }

    private void b(State nextState) {
        for (a a : this.j) {
            a.a((Enum) nextState);
        }
    }

    public final void b(Condition obj, boolean set) {
        int i;
        if (set) {
            i = 1;
        } else {
            i = 0;
        }
        a(Message.obtain(this, 51946, i, 0, obj));
    }

    private void a(Message msg) {
        this.r.incrementAndGet();
        try {
            super.sendMessage(msg);
        } catch (RuntimeException e) {
        }
    }

    public final void handleMessage(Message msg) {
        Object obj = msg.obj;
        boolean z = msg.arg1 == 1;
        if (obj instanceof q) {
            this.t = true;
        } else {
            for (a a : this.j) {
                if (a.a(obj, z)) {
                    break;
                }
            }
            try {
                a((Enum) obj, z);
            } catch (ClassCastException e) {
                g.a().c(this.d, "Unrecognized message: " + obj.getClass().getName());
            }
        }
        if (this.r.decrementAndGet() < 0) {
            g.a().d(this.d, "More messages processed than read");
        }
        if (!hasMessages(51946)) {
            b(this.m);
            a();
            g.a().b(this.d, "Collected: " + this.i + " in " + this.m);
            while (true) {
                Enum enumR = this.m;
                State nextState = b(this.i);
                if (enumR.equals(nextState)) {
                    break;
                }
                try {
                    g.a().b(this.d, "Reduced: " + this.i + " yielding " + nextState);
                    this.m = c(this.m, nextState);
                    this.h.clear();
                    this.h.addAll(this.i);
                    g.a().b(this.d, "Flushing internal state: " + this.m);
                    b(this.m);
                } catch (Throwable use) {
                    try {
                        Throwable throwable;
                        Throwable cause = use.getCause();
                        if (cause == null) {
                            throwable = use;
                        } else {
                            throwable = cause;
                        }
                        h a2 = g.a();
                        String str = this.d;
                        String message = use.getMessage();
                        if (message == null) {
                            message = "no message";
                        }
                        a2.a(str, message, throwable);
                    } catch (c e2) {
                        g.a().b(this.d, "Stay in " + this.m);
                    }
                }
                this.t = true;
            }
            if (this.t && !hasMessages(51946)) {
                a(this.m);
            }
        }
    }

    public final void a(q listener) {
        if (listener == null) {
            g.a().b(this.d, "addListener null");
            throw new AssertionError();
        }
        this.p.add(listener);
        a((Object) listener, true);
    }

    public final void b(q listener) {
        this.p.remove(listener);
    }

    protected final void a(Object feedback) {
        g.a().b(this.d, "Feedback: " + feedback);
        for (q listener : this.p) {
            a(listener, feedback, true);
        }
    }

    private void b(Object feedback) {
        g.a().b(this.d, "Transient feedback: " + feedback);
        for (q listener : this.p) {
            a(listener, feedback, false);
        }
    }

    private void a(q listener, Object feedback, boolean strong) {
        if (strong) {
            if (!listener.a(feedback)) {
                return;
            }
        } else if (!listener.b(feedback)) {
            return;
        }
        this.p.remove(listener);
    }

    public final void a(final State dream) {
        try {
            q anonymousClass5 = new p(this) {
                final /* synthetic */ a b;

                protected final boolean a_(Object feedback) {
                    return feedback == dream;
                }
            };
            a(anonymousClass5);
            anonymousClass5.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException(this.m.name());
        }
    }

    public final void a(Object obj, boolean set) {
        int i;
        if (set) {
            i = 1;
        } else {
            i = 0;
        }
        a(Message.obtain(this, 51946, i, 0, obj));
    }
}
