package addon.greenrobot.eventbus;

final class u {
    final Object a;
    final r b;
    volatile boolean c = true;

    u(Object obj, r rVar) {
        this.a = obj;
        this.b = rVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        if (this.a == uVar.a && this.b.equals(uVar.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() + this.b.f.hashCode();
    }
}
