package androidx.lifecycle;

import defpackage.bl;

public final class aj {
    public static <X, Y> LiveData<Y> a(LiveData<X> liveData, final bl<X, Y> blVar) {
        final LiveData yVar = new y();
        yVar.a(liveData, new ac<X>() {
            public final void onChanged(X x) {
                yVar.b(blVar.a(x));
            }
        });
        return yVar;
    }

    public static <X, Y> LiveData<Y> b(LiveData<X> liveData, final bl<X, LiveData<Y>> blVar) {
        final LiveData yVar = new y();
        yVar.a(liveData, new ac<X>() {
            LiveData<Y> a;

            public final void onChanged(X x) {
                LiveData liveData = (LiveData) blVar.a(x);
                if (this.a != liveData) {
                    if (this.a != null) {
                        yVar.a(this.a);
                    }
                    this.a = liveData;
                    if (this.a != null) {
                        yVar.a(this.a, new ac<Y>(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public final void onChanged(Y y) {
                                yVar.b(y);
                            }
                        });
                    }
                }
            }
        });
        return yVar;
    }
}
