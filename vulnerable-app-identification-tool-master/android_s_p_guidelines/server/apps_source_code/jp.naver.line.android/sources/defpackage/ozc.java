package defpackage;

/* renamed from: ozc */
public interface ozc<T> {
    void onError(Throwable th);

    void onSubscribe(ozn ozn);

    void onSuccess(T t);
}
