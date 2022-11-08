package com.google.android.gms.internal.clearcut;

public abstract class c<MessageType extends b<MessageType, BuilderType>, BuilderType extends c<MessageType, BuilderType>> implements bl {
    public final /* synthetic */ bl a(bk bkVar) {
        if (f().getClass().isInstance(bkVar)) {
            return a((b) bkVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public abstract BuilderType a();

    protected abstract BuilderType a(MessageType messageType);

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }
}
