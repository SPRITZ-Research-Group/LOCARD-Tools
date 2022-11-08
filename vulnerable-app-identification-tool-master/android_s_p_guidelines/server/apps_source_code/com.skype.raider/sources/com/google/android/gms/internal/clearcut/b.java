package com.google.android.gms.internal.clearcut;

public abstract class b<MessageType extends b<MessageType, BuilderType>, BuilderType extends c<MessageType, BuilderType>> implements bk {
    private static boolean zzey = false;
    protected int zzex = 0;

    public final h a() {
        try {
            m c = h.c(d());
            a(c.b());
            return c.a();
        } catch (Throwable e) {
            String str = "ByteString";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder((String.valueOf(name).length() + 62) + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    void a(int i) {
        throw new UnsupportedOperationException();
    }

    int b() {
        throw new UnsupportedOperationException();
    }
}
