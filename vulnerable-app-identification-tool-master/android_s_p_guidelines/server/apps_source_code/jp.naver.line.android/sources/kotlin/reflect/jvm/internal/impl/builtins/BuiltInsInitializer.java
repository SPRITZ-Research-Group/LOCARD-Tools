package kotlin.reflect.jvm.internal.impl.builtins;

import defpackage.acqq;
import defpackage.acry;

public final class BuiltInsInitializer<T extends KotlinBuiltIns> {
    private final acqq<T> constructor;
    private Throwable initializationFailed;
    private volatile boolean initializing;
    private volatile T instance;

    public BuiltInsInitializer(acqq<? extends T> acqq) {
        this.constructor = acqq;
    }

    private final synchronized void initialize() {
        if (this.instance != null) {
            return;
        }
        Throwable th;
        if (this.initializationFailed != null) {
            StringBuilder stringBuilder = new StringBuilder("Built-in library initialization failed previously: ");
            th = this.initializationFailed;
            if (th == null) {
                acry.a();
            }
            stringBuilder.append(th);
            throw new IllegalStateException(stringBuilder.toString(), this.initializationFailed);
        } else if (this.initializing) {
            throw new IllegalStateException("Built-in library initialization loop");
        } else {
            this.initializing = true;
            try {
                this.instance = (KotlinBuiltIns) this.constructor.invoke();
                this.initializing = false;
            } catch (Throwable th2) {
                this.initializing = false;
            }
        }
    }

    public final T get() {
        T t;
        if (this.initializing) {
            synchronized (this) {
                t = this.instance;
                if (t != null) {
                } else {
                    throw new AssertionError("Built-ins are not initialized (note: We are under the same lock as initializing and instance)");
                }
            }
            return t;
        }
        if (this.instance == null) {
            initialize();
        }
        t = this.instance;
        if (t == null) {
            acry.a();
        }
        return t;
    }
}
