package com.skype.audiomanager;

public interface DeviceObserver<T, T1, T2> {
    void a(T2 t2);

    void a(T t, T1 t1, T2 t2);
}
