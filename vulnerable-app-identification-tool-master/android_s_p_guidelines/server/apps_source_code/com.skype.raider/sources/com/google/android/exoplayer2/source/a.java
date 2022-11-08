package com.google.android.exoplayer2.source;

public final class a implements h {
    private final h[] a;

    public a(h[] loaders) {
        this.a = loaders;
    }

    public final long a() {
        long nextLoadPositionUs = Long.MAX_VALUE;
        for (h a : this.a) {
            long loaderNextLoadPositionUs = a.a();
            if (loaderNextLoadPositionUs != Long.MIN_VALUE) {
                nextLoadPositionUs = Math.min(nextLoadPositionUs, loaderNextLoadPositionUs);
            }
        }
        return nextLoadPositionUs == Long.MAX_VALUE ? Long.MIN_VALUE : nextLoadPositionUs;
    }

    public final boolean a(long positionUs) {
        boolean madeProgress = false;
        boolean madeProgressThisIteration;
        do {
            madeProgressThisIteration = false;
            long nextLoadPositionUs = a();
            if (nextLoadPositionUs == Long.MIN_VALUE) {
                break;
            }
            for (h loader : this.a) {
                if (loader.a() == nextLoadPositionUs) {
                    madeProgressThisIteration |= loader.a(positionUs);
                }
            }
            madeProgress |= madeProgressThisIteration;
        } while (madeProgressThisIteration);
        return madeProgress;
    }
}
