package defpackage;

/* renamed from: ozz */
public final class ozz extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    private ozz(String str, Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        super(str, th);
    }

    public ozz(Throwable th) {
        this("The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | ".concat(String.valueOf(th)), th);
    }
}
