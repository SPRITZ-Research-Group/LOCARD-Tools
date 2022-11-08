package android.support.v4.app;

import android.os.Build.VERSION;
import android.support.v4.util.e;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class b extends FragmentTransaction implements f {
    static final boolean a = (VERSION.SDK_INT >= 21);
    final j b;
    ArrayList<a> c = new ArrayList();
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    boolean j;
    boolean k = true;
    String l;
    boolean m;
    int n = -1;
    int o;
    CharSequence p;
    int q;
    CharSequence r;
    ArrayList<String> s;
    ArrayList<String> t;
    boolean u = false;
    ArrayList<Runnable> v;

    static final class a {
        int a;
        Fragment b;
        int c;
        int d;
        int e;
        int f;

        a() {
        }

        a(int cmd, Fragment fragment) {
            this.a = cmd;
            this.b = fragment;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.n >= 0) {
            sb.append(" #");
            sb.append(this.n);
        }
        if (this.l != null) {
            sb.append(" ");
            sb.append(this.l);
        }
        sb.append("}");
        return sb.toString();
    }

    public final void a(String prefix, PrintWriter writer) {
        a(prefix, writer, true);
    }

    public final void a(String prefix, PrintWriter writer, boolean full) {
        if (full) {
            writer.print(prefix);
            writer.print("mName=");
            writer.print(this.l);
            writer.print(" mIndex=");
            writer.print(this.n);
            writer.print(" mCommitted=");
            writer.println(this.m);
            if (this.h != 0) {
                writer.print(prefix);
                writer.print("mTransition=#");
                writer.print(Integer.toHexString(this.h));
                writer.print(" mTransitionStyle=#");
                writer.println(Integer.toHexString(this.i));
            }
            if (!(this.d == 0 && this.e == 0)) {
                writer.print(prefix);
                writer.print("mEnterAnim=#");
                writer.print(Integer.toHexString(this.d));
                writer.print(" mExitAnim=#");
                writer.println(Integer.toHexString(this.e));
            }
            if (!(this.f == 0 && this.g == 0)) {
                writer.print(prefix);
                writer.print("mPopEnterAnim=#");
                writer.print(Integer.toHexString(this.f));
                writer.print(" mPopExitAnim=#");
                writer.println(Integer.toHexString(this.g));
            }
            if (!(this.o == 0 && this.p == null)) {
                writer.print(prefix);
                writer.print("mBreadCrumbTitleRes=#");
                writer.print(Integer.toHexString(this.o));
                writer.print(" mBreadCrumbTitleText=");
                writer.println(this.p);
            }
            if (!(this.q == 0 && this.r == null)) {
                writer.print(prefix);
                writer.print("mBreadCrumbShortTitleRes=#");
                writer.print(Integer.toHexString(this.q));
                writer.print(" mBreadCrumbShortTitleText=");
                writer.println(this.r);
            }
        }
        if (!this.c.isEmpty()) {
            writer.print(prefix);
            writer.println("Operations:");
            new StringBuilder().append(prefix).append("    ");
            int numOps = this.c.size();
            for (int opNum = 0; opNum < numOps; opNum++) {
                String cmdStr;
                a op = (a) this.c.get(opNum);
                switch (op.a) {
                    case 0:
                        cmdStr = "NULL";
                        break;
                    case 1:
                        cmdStr = "ADD";
                        break;
                    case 2:
                        cmdStr = "REPLACE";
                        break;
                    case 3:
                        cmdStr = "REMOVE";
                        break;
                    case 4:
                        cmdStr = "HIDE";
                        break;
                    case 5:
                        cmdStr = "SHOW";
                        break;
                    case 6:
                        cmdStr = "DETACH";
                        break;
                    case 7:
                        cmdStr = "ATTACH";
                        break;
                    case 8:
                        cmdStr = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        cmdStr = "UNSET_PRIMARY_NAV";
                        break;
                    default:
                        cmdStr = "cmd=" + op.a;
                        break;
                }
                writer.print(prefix);
                writer.print("  Op #");
                writer.print(opNum);
                writer.print(": ");
                writer.print(cmdStr);
                writer.print(" ");
                writer.println(op.b);
                if (full) {
                    if (!(op.c == 0 && op.d == 0)) {
                        writer.print(prefix);
                        writer.print("enterAnim=#");
                        writer.print(Integer.toHexString(op.c));
                        writer.print(" exitAnim=#");
                        writer.println(Integer.toHexString(op.d));
                    }
                    if (op.e != 0 || op.f != 0) {
                        writer.print(prefix);
                        writer.print("popEnterAnim=#");
                        writer.print(Integer.toHexString(op.e));
                        writer.print(" popExitAnim=#");
                        writer.println(Integer.toHexString(op.f));
                    }
                }
            }
        }
    }

    public b(j manager) {
        this.b = manager;
    }

    final void a(a op) {
        this.c.add(op);
        op.c = this.d;
        op.d = this.e;
        op.e = this.f;
        op.f = this.g;
    }

    public final FragmentTransaction a(Fragment fragment, String tag) {
        b(0, fragment, tag);
        return this;
    }

    public final FragmentTransaction a(int containerViewId, Fragment fragment, String tag) {
        b(containerViewId, fragment, tag);
        return this;
    }

    private void b(int containerViewId, Fragment fragment, String tag) {
        Class fragmentClass = fragment.getClass();
        int modifiers = fragmentClass.getModifiers();
        if (fragmentClass.isAnonymousClass() || !Modifier.isPublic(modifiers) || (fragmentClass.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + fragmentClass.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        fragment.mFragmentManager = this.b;
        if (tag != null) {
            if (fragment.mTag == null || tag.equals(fragment.mTag)) {
                fragment.mTag = tag;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + tag);
            }
        }
        if (containerViewId != 0) {
            if (containerViewId == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + tag + " to container view with no id");
            } else if (fragment.mFragmentId == 0 || fragment.mFragmentId == containerViewId) {
                fragment.mFragmentId = containerViewId;
                fragment.mContainerId = containerViewId;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + containerViewId);
            }
        }
        a(new a(1, fragment));
    }

    public final FragmentTransaction a(Fragment fragment) {
        a(new a(3, fragment));
        return this;
    }

    public final FragmentTransaction b(Fragment fragment) {
        a(new a(6, fragment));
        return this;
    }

    public final FragmentTransaction c(Fragment fragment) {
        a(new a(7, fragment));
        return this;
    }

    final void a(int amt) {
        if (this.j) {
            if (j.a) {
                new StringBuilder("Bump nesting in ").append(this).append(" by ").append(amt);
            }
            int numOps = this.c.size();
            for (int opNum = 0; opNum < numOps; opNum++) {
                a op = (a) this.c.get(opNum);
                if (op.b != null) {
                    Fragment fragment = op.b;
                    fragment.mBackStackNesting += amt;
                    if (j.a) {
                        new StringBuilder("Bump nesting of ").append(op.b).append(" to ").append(op.b.mBackStackNesting);
                    }
                }
            }
        }
    }

    public final void a() {
        if (this.v != null) {
            int N = this.v.size();
            for (int i = 0; i < N; i++) {
                ((Runnable) this.v.get(i)).run();
            }
            this.v = null;
        }
    }

    public final int b() {
        return b(false);
    }

    public final int c() {
        return b(true);
    }

    private int b(boolean allowStateLoss) {
        if (this.m) {
            throw new IllegalStateException("commit already called");
        }
        if (j.a) {
            new StringBuilder("Commit: ").append(this);
            PrintWriter pw = new PrintWriter(new e("FragmentManager"));
            a("  ", pw);
            pw.close();
        }
        this.m = true;
        if (this.j) {
            this.n = this.b.a(this);
        } else {
            this.n = -1;
        }
        this.b.a((f) this, allowStateLoss);
        return this.n;
    }

    public final boolean a(ArrayList<b> records, ArrayList<Boolean> isRecordPop) {
        if (j.a) {
            new StringBuilder("Run: ").append(this);
        }
        records.add(this);
        isRecordPop.add(Boolean.valueOf(false));
        if (this.j) {
            j jVar = this.b;
            if (jVar.g == null) {
                jVar.g = new ArrayList();
            }
            jVar.g.add(this);
        }
        return true;
    }

    final boolean b(int containerId) {
        int numOps = this.c.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            int fragContainer;
            a op = (a) this.c.get(opNum);
            if (op.b != null) {
                fragContainer = op.b.mContainerId;
            } else {
                fragContainer = 0;
            }
            if (fragContainer != 0 && fragContainer == containerId) {
                return true;
            }
        }
        return false;
    }

    final boolean a(ArrayList<b> records, int startIndex, int endIndex) {
        if (endIndex == startIndex) {
            return false;
        }
        int numOps = this.c.size();
        int lastContainer = -1;
        for (int opNum = 0; opNum < numOps; opNum++) {
            int container;
            a op = (a) this.c.get(opNum);
            if (op.b != null) {
                container = op.b.mContainerId;
            } else {
                container = 0;
            }
            if (!(container == 0 || container == lastContainer)) {
                lastContainer = container;
                for (int i = startIndex; i < endIndex; i++) {
                    b record = (b) records.get(i);
                    int numThoseOps = record.c.size();
                    for (int thoseOpIndex = 0; thoseOpIndex < numThoseOps; thoseOpIndex++) {
                        int i2;
                        a thatOp = (a) record.c.get(thoseOpIndex);
                        if (thatOp.b != null) {
                            i2 = thatOp.b.mContainerId;
                        } else {
                            i2 = 0;
                        }
                        if (i2 == container) {
                            return true;
                        }
                    }
                }
                continue;
            }
        }
        return false;
    }

    final void d() {
        int numOps = this.c.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            a op = (a) this.c.get(opNum);
            Fragment f = op.b;
            if (f != null) {
                f.setNextTransition(this.h, this.i);
            }
            switch (op.a) {
                case 1:
                    f.setNextAnim(op.c);
                    this.b.a(f, false);
                    break;
                case 3:
                    f.setNextAnim(op.d);
                    this.b.c(f);
                    break;
                case 4:
                    f.setNextAnim(op.d);
                    j.d(f);
                    break;
                case 5:
                    f.setNextAnim(op.c);
                    j.e(f);
                    break;
                case 6:
                    f.setNextAnim(op.d);
                    this.b.f(f);
                    break;
                case 7:
                    f.setNextAnim(op.c);
                    this.b.g(f);
                    break;
                case 8:
                    this.b.h(f);
                    break;
                case 9:
                    this.b.h(null);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.a);
            }
            if (!(this.u || op.a == 1 || f == null)) {
                this.b.a(f);
            }
        }
        if (!this.u) {
            this.b.a(this.b.l, true);
        }
    }

    final void a(boolean moveToState) {
        for (int opNum = this.c.size() - 1; opNum >= 0; opNum--) {
            a op = (a) this.c.get(opNum);
            Fragment f = op.b;
            if (f != null) {
                f.setNextTransition(j.b(this.h), this.i);
            }
            switch (op.a) {
                case 1:
                    f.setNextAnim(op.f);
                    this.b.c(f);
                    break;
                case 3:
                    f.setNextAnim(op.e);
                    this.b.a(f, false);
                    break;
                case 4:
                    f.setNextAnim(op.e);
                    j.e(f);
                    break;
                case 5:
                    f.setNextAnim(op.f);
                    j.d(f);
                    break;
                case 6:
                    f.setNextAnim(op.e);
                    this.b.g(f);
                    break;
                case 7:
                    f.setNextAnim(op.f);
                    this.b.f(f);
                    break;
                case 8:
                    this.b.h(null);
                    break;
                case 9:
                    this.b.h(f);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.a);
            }
            if (!(this.u || op.a == 3 || f == null)) {
                this.b.a(f);
            }
        }
        if (!this.u && moveToState) {
            this.b.a(this.b.l, true);
        }
    }

    final Fragment a(ArrayList<Fragment> added, Fragment oldPrimaryNav) {
        int opNum = 0;
        while (opNum < this.c.size()) {
            a op = (a) this.c.get(opNum);
            switch (op.a) {
                case 1:
                case 7:
                    added.add(op.b);
                    break;
                case 2:
                    Fragment f = op.b;
                    int containerId = f.mContainerId;
                    boolean alreadyAdded = false;
                    for (int i = added.size() - 1; i >= 0; i--) {
                        Fragment old = (Fragment) added.get(i);
                        if (old.mContainerId == containerId) {
                            if (old == f) {
                                alreadyAdded = true;
                            } else {
                                if (old == oldPrimaryNav) {
                                    this.c.add(opNum, new a(9, old));
                                    opNum++;
                                    oldPrimaryNav = null;
                                }
                                a removeOp = new a(3, old);
                                removeOp.c = op.c;
                                removeOp.e = op.e;
                                removeOp.d = op.d;
                                removeOp.f = op.f;
                                this.c.add(opNum, removeOp);
                                added.remove(old);
                                opNum++;
                            }
                        }
                    }
                    if (!alreadyAdded) {
                        op.a = 1;
                        added.add(f);
                        break;
                    }
                    this.c.remove(opNum);
                    opNum--;
                    break;
                case 3:
                case 6:
                    added.remove(op.b);
                    if (op.b != oldPrimaryNav) {
                        break;
                    }
                    this.c.add(opNum, new a(9, op.b));
                    opNum++;
                    oldPrimaryNav = null;
                    break;
                case 8:
                    this.c.add(opNum, new a(9, oldPrimaryNav));
                    opNum++;
                    oldPrimaryNav = op.b;
                    break;
                default:
                    break;
            }
            opNum++;
        }
        return oldPrimaryNav;
    }

    final Fragment b(ArrayList<Fragment> added, Fragment oldPrimaryNav) {
        for (int opNum = 0; opNum < this.c.size(); opNum++) {
            a op = (a) this.c.get(opNum);
            switch (op.a) {
                case 1:
                case 7:
                    added.remove(op.b);
                    break;
                case 3:
                case 6:
                    added.add(op.b);
                    break;
                case 8:
                    oldPrimaryNav = null;
                    break;
                case 9:
                    oldPrimaryNav = op.b;
                    break;
                default:
                    break;
            }
        }
        return oldPrimaryNav;
    }

    final boolean e() {
        for (int opNum = 0; opNum < this.c.size(); opNum++) {
            if (b((a) this.c.get(opNum))) {
                return true;
            }
        }
        return false;
    }

    final void a(c listener) {
        for (int opNum = 0; opNum < this.c.size(); opNum++) {
            a op = (a) this.c.get(opNum);
            if (b(op)) {
                op.b.setOnStartEnterTransitionListener(listener);
            }
        }
    }

    private static boolean b(a op) {
        Fragment fragment = op.b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }
}
