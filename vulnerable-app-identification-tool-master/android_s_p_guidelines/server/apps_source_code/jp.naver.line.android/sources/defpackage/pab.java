package defpackage;

/* renamed from: pab */
public final class pab extends IllegalStateException {
    private static final long serialVersionUID = 1644750035281290266L;

    public pab(Throwable th) {
        super("The exception could not be delivered to the consumer because it has already canceled/disposed the flow or the exception has nowhere to go to begin with. Further reading: https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling | ".concat(String.valueOf(th)), th);
    }
}
