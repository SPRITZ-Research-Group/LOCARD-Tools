package com.skypecam.obscura.b;

public final class ad extends IllegalStateException {
    final Enum<?> a;
    final Enum<?> b;

    public <E extends Enum<E>> ad(E prevState, E nextState, Throwable cause) {
        super("[" + prevState.name() + "->" + nextState.name() + "]", cause);
        this.a = prevState;
        this.b = nextState;
    }
}
