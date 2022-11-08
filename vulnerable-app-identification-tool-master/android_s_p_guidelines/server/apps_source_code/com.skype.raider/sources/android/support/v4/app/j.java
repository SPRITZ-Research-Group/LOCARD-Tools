package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.v4.util.i;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class j extends i implements Factory2 {
    static final Interpolator E = new DecelerateInterpolator(2.5f);
    static final Interpolator F = new DecelerateInterpolator(1.5f);
    static final Interpolator G = new AccelerateInterpolator(2.5f);
    static final Interpolator H = new AccelerateInterpolator(1.5f);
    static boolean a = false;
    static Field q = null;
    SparseArray<Parcelable> A = null;
    ArrayList<h> B;
    k C;
    Runnable D = new Runnable(this) {
        final /* synthetic */ j a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.f();
        }
    };
    private final CopyOnWriteArrayList<i<Object, Boolean>> I = new CopyOnWriteArrayList();
    ArrayList<f> b;
    boolean c;
    int d = 0;
    final ArrayList<Fragment> e = new ArrayList();
    SparseArray<Fragment> f;
    ArrayList<b> g;
    ArrayList<Fragment> h;
    ArrayList<b> i;
    ArrayList<Integer> j;
    ArrayList<Object> k;
    int l = 0;
    h m;
    f n;
    Fragment o;
    Fragment p;
    boolean r;
    boolean s;
    boolean t;
    String u;
    boolean v;
    ArrayList<b> w;
    ArrayList<Boolean> x;
    ArrayList<Fragment> y;
    Bundle z = null;

    interface f {
        boolean a(ArrayList<b> arrayList, ArrayList<Boolean> arrayList2);
    }

    private static class b implements AnimationListener {
        private final AnimationListener a;

        /* synthetic */ b(AnimationListener x0, byte b) {
            this(x0);
        }

        private b(AnimationListener wrapped) {
            this.a = wrapped;
        }

        @CallSuper
        public void onAnimationStart(Animation animation) {
            if (this.a != null) {
                this.a.onAnimationStart(animation);
            }
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            if (this.a != null) {
                this.a.onAnimationEnd(animation);
            }
        }

        @CallSuper
        public void onAnimationRepeat(Animation animation) {
            if (this.a != null) {
                this.a.onAnimationRepeat(animation);
            }
        }
    }

    private static class a extends b {
        View a;

        a(View v, AnimationListener listener) {
            super(listener, (byte) 0);
            this.a = v;
        }

        @CallSuper
        public final void onAnimationEnd(Animation animation) {
            if (ViewCompat.D(this.a) || VERSION.SDK_INT >= 24) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = this$0;
                    }

                    public final void run() {
                        this.a.a.setLayerType(0, null);
                    }
                });
            } else {
                this.a.setLayerType(0, null);
            }
            super.onAnimationEnd(animation);
        }
    }

    private static class c {
        public final Animation a;
        public final Animator b;

        /* synthetic */ c(Animator x0, byte b) {
            this(x0);
        }

        /* synthetic */ c(Animation x0, byte b) {
            this(x0);
        }

        private c(Animation animation) {
            this.a = animation;
            this.b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        private c(Animator animator) {
            this.a = null;
            this.b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    private static class d extends AnimatorListenerAdapter {
        View a;

        d(View v) {
            this.a = v;
        }

        public final void onAnimationStart(Animator animation) {
            this.a.setLayerType(2, null);
        }

        public final void onAnimationEnd(Animator animation) {
            this.a.setLayerType(0, null);
            animation.removeListener(this);
        }
    }

    static class e {
        public static final int[] a = new int[]{16842755, 16842960, 16842961};
    }

    private class g implements f {
        final String a = null;
        final int b;
        final int c;
        final /* synthetic */ j d;

        g(j jVar, int id) {
            this.d = jVar;
            this.b = id;
            this.c = 1;
        }

        public final boolean a(ArrayList<b> records, ArrayList<Boolean> isRecordPop) {
            if (this.d.p != null && this.b < 0 && this.a == null) {
                i childManager = this.d.p.peekChildFragmentManager();
                if (childManager != null && childManager.c()) {
                    return false;
                }
            }
            return this.d.a((ArrayList) records, (ArrayList) isRecordPop, this.a, this.b, this.c);
        }
    }

    static class h implements c {
        private final boolean a;
        private final b b;
        private int c;

        h(b record, boolean isBack) {
            this.a = isBack;
            this.b = record;
        }

        public final void a() {
            this.c--;
            if (this.c == 0) {
                this.b.b.w();
            }
        }

        public final void b() {
            this.c++;
        }

        public final boolean c() {
            return this.c == 0;
        }

        public final void d() {
            boolean canceled;
            boolean z = false;
            if (this.c > 0) {
                canceled = true;
            } else {
                canceled = false;
            }
            j manager = this.b.b;
            int numAdded = manager.e.size();
            for (int i = 0; i < numAdded; i++) {
                Fragment fragment = (Fragment) manager.e.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (canceled && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            j jVar = this.b.b;
            b bVar = this.b;
            boolean z2 = this.a;
            if (!canceled) {
                z = true;
            }
            j.a(jVar, bVar, z2, z, true);
        }

        public final void e() {
            j.a(this.b.b, this.b, this.a, false, false);
        }
    }

    j() {
    }

    static /* synthetic */ void a(j x0, b x1, boolean x2, boolean x3, boolean x4) {
        if (x2) {
            x1.a(x4);
        } else {
            x1.d();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(x1);
        arrayList2.add(Boolean.valueOf(x2));
        if (x3) {
            l.a(x0, arrayList, arrayList2, 0, 1, true);
        }
        if (x4) {
            x0.a(x0.l, true);
        }
        if (x0.f != null) {
            int size = x0.f.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = (Fragment) x0.f.valueAt(i);
                if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && x1.b(fragment.mContainerId)) {
                    if (fragment.mPostponedAlpha > 0.0f) {
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    }
                    if (x4) {
                        fragment.mPostponedAlpha = 0.0f;
                    } else {
                        fragment.mPostponedAlpha = -1.0f;
                        fragment.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    private static boolean a(Animator anim) {
        if (anim == null) {
            return false;
        }
        int i;
        if (anim instanceof ValueAnimator) {
            PropertyValuesHolder[] values = ((ValueAnimator) anim).getValues();
            for (PropertyValuesHolder propertyName : values) {
                if ("alpha".equals(propertyName.getPropertyName())) {
                    return true;
                }
            }
        } else if (anim instanceof AnimatorSet) {
            List<Animator> animList = ((AnimatorSet) anim).getChildAnimations();
            for (i = 0; i < animList.size(); i++) {
                if (a((Animator) animList.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(RuntimeException ex) {
        ex.getMessage();
        PrintWriter pw = new PrintWriter(new android.support.v4.util.e("FragmentManager"));
        if (this.m != null) {
            try {
                this.m.a("  ", pw, new String[0]);
            } catch (Exception e) {
            }
        } else {
            try {
                a("  ", null, pw, new String[0]);
            } catch (Exception e2) {
            }
        }
        throw ex;
    }

    public final FragmentTransaction a() {
        return new b(this);
    }

    public final boolean b() {
        boolean updates = f();
        z();
        return updates;
    }

    public final boolean c() {
        v();
        return t();
    }

    public final void a(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Bad id: " + id);
        }
        a(new g(this, id), false);
    }

    private boolean t() {
        f();
        x();
        if (this.p != null) {
            i childManager = this.p.peekChildFragmentManager();
            if (childManager != null && childManager.c()) {
                return true;
            }
        }
        boolean executePop = a(this.w, this.x, null, -1, 0);
        if (executePop) {
            this.c = true;
            try {
                b(this.w, this.x);
            } finally {
                y();
            }
        }
        A();
        D();
        return executePop;
    }

    private Fragment a(Bundle bundle, String key) {
        int index = bundle.getInt(key, -1);
        if (index == -1) {
            return null;
        }
        Fragment f = (Fragment) this.f.get(index);
        if (f != null) {
            return f;
        }
        a(new IllegalStateException("Fragment no longer exists for key " + key + ": index " + index));
        return f;
    }

    public final List<Fragment> d() {
        if (this.e.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<Fragment> list;
        synchronized (this.e) {
            list = (List) this.e.clone();
        }
        return list;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        if (this.o != null) {
            android.support.v4.util.d.a(this.o, sb);
        } else {
            android.support.v4.util.d.a(this.m, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void a(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        int N;
        int i;
        Fragment f;
        b bs;
        String innerPrefix = prefix + "    ";
        if (this.f != null) {
            N = this.f.size();
            if (N > 0) {
                writer.print(prefix);
                writer.print("Active Fragments in ");
                writer.print(Integer.toHexString(System.identityHashCode(this)));
                writer.println(":");
                for (i = 0; i < N; i++) {
                    f = (Fragment) this.f.valueAt(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(f);
                    if (f != null) {
                        f.dump(innerPrefix, fd, writer, args);
                    }
                }
            }
        }
        N = this.e.size();
        if (N > 0) {
            writer.print(prefix);
            writer.println("Added Fragments:");
            for (i = 0; i < N; i++) {
                f = (Fragment) this.e.get(i);
                writer.print(prefix);
                writer.print("  #");
                writer.print(i);
                writer.print(": ");
                writer.println(f.toString());
            }
        }
        if (this.h != null) {
            N = this.h.size();
            if (N > 0) {
                writer.print(prefix);
                writer.println("Fragments Created Menus:");
                for (i = 0; i < N; i++) {
                    f = (Fragment) this.h.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(f.toString());
                }
            }
        }
        if (this.g != null) {
            N = this.g.size();
            if (N > 0) {
                writer.print(prefix);
                writer.println("Back Stack:");
                for (i = 0; i < N; i++) {
                    bs = (b) this.g.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(bs.toString());
                    bs.a(innerPrefix, writer);
                }
            }
        }
        synchronized (this) {
            if (this.i != null) {
                N = this.i.size();
                if (N > 0) {
                    writer.print(prefix);
                    writer.println("Back Stack Indices:");
                    for (i = 0; i < N; i++) {
                        bs = (b) this.i.get(i);
                        writer.print(prefix);
                        writer.print("  #");
                        writer.print(i);
                        writer.print(": ");
                        writer.println(bs);
                    }
                }
            }
            if (this.j != null && this.j.size() > 0) {
                writer.print(prefix);
                writer.print("mAvailBackStackIndices: ");
                writer.println(Arrays.toString(this.j.toArray()));
            }
        }
        if (this.b != null) {
            N = this.b.size();
            if (N > 0) {
                writer.print(prefix);
                writer.println("Pending Actions:");
                for (i = 0; i < N; i++) {
                    f r = (f) this.b.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(r);
                }
            }
        }
        writer.print(prefix);
        writer.println("FragmentManager misc state:");
        writer.print(prefix);
        writer.print("  mHost=");
        writer.println(this.m);
        writer.print(prefix);
        writer.print("  mContainer=");
        writer.println(this.n);
        if (this.o != null) {
            writer.print(prefix);
            writer.print("  mParent=");
            writer.println(this.o);
        }
        writer.print(prefix);
        writer.print("  mCurState=");
        writer.print(this.l);
        writer.print(" mStateSaved=");
        writer.print(this.s);
        writer.print(" mDestroyed=");
        writer.println(this.t);
        if (this.r) {
            writer.print(prefix);
            writer.print("  mNeedMenuInvalidate=");
            writer.println(this.r);
        }
        if (this.u != null) {
            writer.print(prefix);
            writer.print("  mNoTransactionsBecause=");
            writer.println(this.u);
        }
    }

    private static c a(float startScale, float endScale, float startAlpha, float endAlpha) {
        Animation set = new AnimationSet(false);
        ScaleAnimation scale = new ScaleAnimation(startScale, endScale, startScale, endScale, 1, 0.5f, 1, 0.5f);
        scale.setInterpolator(E);
        scale.setDuration(220);
        set.addAnimation(scale);
        AlphaAnimation alpha = new AlphaAnimation(startAlpha, endAlpha);
        alpha.setInterpolator(F);
        alpha.setDuration(220);
        set.addAnimation(alpha);
        return new c(set, (byte) 0);
    }

    private static c a(float start, float end) {
        Animation anim = new AlphaAnimation(start, end);
        anim.setInterpolator(F);
        anim.setDuration(220);
        return new c(anim, (byte) 0);
    }

    private c a(Fragment fragment, int transit, boolean enter, int transitionStyle) {
        int nextAnim = fragment.getNextAnim();
        Animation animation = fragment.onCreateAnimation(transit, enter, nextAnim);
        if (animation != null) {
            return new c(animation, (byte) 0);
        }
        Animator animator = fragment.onCreateAnimator(transit, enter, nextAnim);
        if (animator != null) {
            return new c(animator, (byte) 0);
        }
        if (nextAnim != 0) {
            boolean isAnim = "anim".equals(this.m.b.getResources().getResourceTypeName(nextAnim));
            boolean successfulLoad = false;
            if (isAnim) {
                try {
                    animation = AnimationUtils.loadAnimation(this.m.b, nextAnim);
                    if (animation != null) {
                        return new c(animation, (byte) 0);
                    }
                    successfulLoad = true;
                } catch (NotFoundException e) {
                    throw e;
                } catch (RuntimeException e2) {
                }
            }
            if (!successfulLoad) {
                try {
                    animator = AnimatorInflater.loadAnimator(this.m.b, nextAnim);
                    if (animator != null) {
                        return new c(animator, (byte) 0);
                    }
                } catch (RuntimeException e3) {
                    if (isAnim) {
                        throw e3;
                    }
                    animation = AnimationUtils.loadAnimation(this.m.b, nextAnim);
                    if (animation != null) {
                        return new c(animation, (byte) 0);
                    }
                }
            }
        }
        if (transit == 0) {
            return null;
        }
        int styleIndex;
        switch (transit) {
            case 4097:
                styleIndex = enter ? 1 : 2;
                break;
            case 4099:
                styleIndex = enter ? 5 : 6;
                break;
            case 8194:
                styleIndex = enter ? 3 : 4;
                break;
            default:
                styleIndex = -1;
                break;
        }
        if (styleIndex < 0) {
            return null;
        }
        switch (styleIndex) {
            case 1:
                return a(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return a(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return a(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return a(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return a(0.0f, 1.0f);
            case 6:
                return a(1.0f, 0.0f);
            default:
                if (transitionStyle == 0 && this.m.e()) {
                    transitionStyle = this.m.f();
                }
                if (transitionStyle == 0) {
                    return null;
                }
                return null;
        }
    }

    private static void a(View v, c anim) {
        Object obj = null;
        if (v != null && anim != null) {
            if (v != null && anim != null && VERSION.SDK_INT >= 19 && v.getLayerType() == 0 && ViewCompat.v(v)) {
                boolean z;
                if (anim.a instanceof AlphaAnimation) {
                    z = true;
                } else if (anim.a instanceof AnimationSet) {
                    List animations = ((AnimationSet) anim.a).getAnimations();
                    for (int i = 0; i < animations.size(); i++) {
                        if (animations.get(i) instanceof AlphaAnimation) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                } else {
                    z = a(anim.b);
                }
                if (z) {
                    int obj2 = 1;
                }
            }
            if (obj2 == null) {
                return;
            }
            if (anim.b != null) {
                anim.b.addListener(new d(v));
                return;
            }
            AnimationListener originalListener = a(anim.a);
            v.setLayerType(2, null);
            anim.a.setAnimationListener(new a(v, originalListener));
        }
    }

    private static AnimationListener a(Animation animation) {
        try {
            if (q == null) {
                Field declaredField = Animation.class.getDeclaredField("mListener");
                q = declaredField;
                declaredField.setAccessible(true);
            }
            return (AnimationListener) q.get(animation);
        } catch (NoSuchFieldException e) {
            return null;
        } catch (IllegalAccessException e2) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void a(final Fragment f, int newState, int transit, int transitionStyle, boolean keepActive) {
        if ((!f.mAdded || f.mDetached) && newState > 1) {
            newState = 1;
        }
        if (f.mRemoving && newState > f.mState) {
            if (f.mState == 0 && f.isInBackStack()) {
                newState = 1;
            } else {
                newState = f.mState;
            }
        }
        if (f.mDeferStart && f.mState < 4 && newState > 3) {
            newState = 3;
        }
        if (f.mState > newState) {
            if (f.mState > newState) {
                switch (f.mState) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        if (newState < 5) {
                            if (a) {
                                new StringBuilder("movefrom RESUMED: ").append(f);
                            }
                            f.performPause();
                            d(f, false);
                            break;
                        }
                        break;
                }
            }
        } else if (!f.mFromLayout || f.mInLayout) {
            if (!(f.getAnimatingAway() == null && f.getAnimator() == null)) {
                f.setAnimatingAway(null);
                f.setAnimator(null);
                a(f, f.getStateAfterAnimating(), 0, 0, true);
            }
            switch (f.mState) {
                case 0:
                    if (newState > 0) {
                        j jVar;
                        if (a) {
                            new StringBuilder("moveto CREATED: ").append(f);
                        }
                        if (f.mSavedFragmentState != null) {
                            f.mSavedFragmentState.setClassLoader(this.m.b.getClassLoader());
                            f.mSavedViewState = f.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            f.mTarget = a(f.mSavedFragmentState, "android:target_state");
                            if (f.mTarget != null) {
                                f.mTargetRequestCode = f.mSavedFragmentState.getInt("android:target_req_state", 0);
                            }
                            f.mUserVisibleHint = f.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                            if (!f.mUserVisibleHint) {
                                f.mDeferStart = true;
                                if (newState > 3) {
                                    newState = 3;
                                }
                            }
                        }
                        f.mHost = this.m;
                        f.mParentFragment = this.o;
                        if (this.o != null) {
                            jVar = this.o.mChildFragmentManager;
                        } else {
                            jVar = this.m.d;
                        }
                        f.mFragmentManager = jVar;
                        if (f.mTarget != null) {
                            if (this.f.get(f.mTarget.mIndex) != f.mTarget) {
                                throw new IllegalStateException("Fragment " + f + " declared target fragment " + f.mTarget + " that does not belong to this FragmentManager!");
                            } else if (f.mTarget.mState <= 0) {
                                a(f.mTarget, 1, 0, 0, true);
                            }
                        }
                        a(f, this.m.b, false);
                        f.mCalled = false;
                        f.onAttach(this.m.b);
                        if (f.mCalled) {
                            if (f.mParentFragment == null) {
                                this.m.a(f);
                            } else {
                                f.mParentFragment.onAttachFragment(f);
                            }
                            b(f, this.m.b, false);
                            if (f.mIsCreated) {
                                f.restoreChildFragmentState(f.mSavedFragmentState);
                                f.mState = 1;
                            } else {
                                a(f, f.mSavedFragmentState, false);
                                f.performCreate(f.mSavedFragmentState);
                                b(f, f.mSavedFragmentState, false);
                            }
                            f.mRetaining = false;
                            break;
                        }
                        throw new ah("Fragment " + f + " did not call through to super.onAttach()");
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        } else {
            return;
        }
        if (f.mState != newState) {
            new StringBuilder("moveToState: Fragment state for ").append(f).append(" not updated inline; expected state ").append(newState).append(" found ").append(f.mState);
            f.mState = newState;
        }
        a(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(f.mContainerId) + " (" + resName + ") for fragment " + f));
        f.mContainer = container;
        f.mView = f.performCreateView(f.performGetLayoutInflater(f.mSavedFragmentState), container, f.mSavedFragmentState);
        if (f.mView != null) {
            f.mInnerView = f.mView;
            f.mView.setSaveFromParentEnabled(false);
            if (container != null) {
                container.addView(f.mView);
            }
            if (f.mHidden) {
                f.mView.setVisibility(8);
            }
            f.onViewCreated(f.mView, f.mSavedFragmentState);
            a(f, f.mView, f.mSavedFragmentState, false);
            boolean z = f.mView.getVisibility() == 0 && f.mContainer != null;
            f.mIsNewlyAdded = z;
        } else {
            f.mInnerView = null;
        }
        f.performActivityCreated(f.mSavedFragmentState);
        c(f, f.mSavedFragmentState, false);
        if (f.mView != null) {
            f.restoreViewState(f.mSavedFragmentState);
        }
        f.mSavedFragmentState = null;
        if (newState > 2) {
            f.mState = 3;
        }
        if (newState > 3) {
            if (a) {
                new StringBuilder("moveto STARTED: ").append(f);
            }
            f.performStart();
            b(f, false);
        }
        if (newState > 4) {
            if (a) {
                new StringBuilder("moveto RESUMED: ").append(f);
            }
            f.performResume();
            c(f, false);
            f.mSavedFragmentState = null;
            f.mSavedViewState = null;
        }
        if (f.mState != newState) {
            new StringBuilder("moveToState: Fragment state for ").append(f).append(" not updated inline; expected state ").append(newState).append(" found ").append(f.mState);
            f.mState = newState;
        }
    }

    private void i(Fragment f) {
        a(f, this.l, 0, 0, false);
    }

    final void a(final Fragment f) {
        Fragment underFragment = null;
        if (f != null) {
            int indexOf;
            int nextState = this.l;
            if (f.mRemoving) {
                if (f.isInBackStack()) {
                    nextState = Math.min(nextState, 1);
                } else {
                    nextState = Math.min(nextState, 0);
                }
            }
            a(f, nextState, f.getNextTransition(), f.getNextTransitionStyle(), false);
            if (f.mView != null) {
                ViewGroup viewGroup = f.mContainer;
                View view = f.mView;
                if (viewGroup != null && view != null) {
                    for (indexOf = this.e.indexOf(f) - 1; indexOf >= 0; indexOf--) {
                        Fragment fragment = (Fragment) this.e.get(indexOf);
                        if (fragment.mContainer == viewGroup && fragment.mView != null) {
                            underFragment = fragment;
                            break;
                        }
                    }
                }
                if (underFragment != null) {
                    View underView = underFragment.mView;
                    ViewGroup container = f.mContainer;
                    int underIndex = container.indexOfChild(underView);
                    int viewIndex = container.indexOfChild(f.mView);
                    if (viewIndex < underIndex) {
                        container.removeViewAt(viewIndex);
                        container.addView(f.mView, underIndex);
                    }
                }
                if (f.mIsNewlyAdded && f.mContainer != null) {
                    if (f.mPostponedAlpha > 0.0f) {
                        f.mView.setAlpha(f.mPostponedAlpha);
                    }
                    f.mPostponedAlpha = 0.0f;
                    f.mIsNewlyAdded = false;
                    c anim = a(f, f.getNextTransition(), true, f.getNextTransitionStyle());
                    if (anim != null) {
                        a(f.mView, anim);
                        if (anim.a != null) {
                            f.mView.startAnimation(anim.a);
                        } else {
                            anim.b.setTarget(f.mView);
                            anim.b.start();
                        }
                    }
                }
            }
            if (f.mHiddenChanged) {
                if (f.mView != null) {
                    boolean z;
                    indexOf = f.getNextTransition();
                    if (f.mHidden) {
                        z = false;
                    } else {
                        z = true;
                    }
                    c a = a(f, indexOf, z, f.getNextTransitionStyle());
                    if (a == null || a.b == null) {
                        if (a != null) {
                            a(f.mView, a);
                            f.mView.startAnimation(a.a);
                            a.a.start();
                        }
                        int i = (!f.mHidden || f.isHideReplaced()) ? 0 : 8;
                        f.mView.setVisibility(i);
                        if (f.isHideReplaced()) {
                            f.setHideReplaced(false);
                        }
                    } else {
                        a.b.setTarget(f.mView);
                        if (!f.mHidden) {
                            f.mView.setVisibility(0);
                        } else if (f.isHideReplaced()) {
                            f.setHideReplaced(false);
                        } else {
                            final ViewGroup viewGroup2 = f.mContainer;
                            final View view2 = f.mView;
                            viewGroup2.startViewTransition(view2);
                            a.b.addListener(new AnimatorListenerAdapter(this) {
                                final /* synthetic */ j d;

                                public final void onAnimationEnd(Animator animation) {
                                    viewGroup2.endViewTransition(view2);
                                    animation.removeListener(this);
                                    if (f.mView != null) {
                                        f.mView.setVisibility(8);
                                    }
                                }
                            });
                        }
                        a(f.mView, a);
                        a.b.start();
                    }
                }
                if (f.mAdded && f.mHasMenu && f.mMenuVisible) {
                    this.r = true;
                }
                f.mHiddenChanged = false;
                f.onHiddenChanged(f.mHidden);
            }
        }
    }

    final void a(int newState, boolean always) {
        if (this.m == null && newState != 0) {
            throw new IllegalStateException("No activity");
        } else if (always || newState != this.l) {
            this.l = newState;
            if (this.f != null) {
                int i;
                Fragment f;
                boolean loadersRunning = false;
                int numAdded = this.e.size();
                for (i = 0; i < numAdded; i++) {
                    f = (Fragment) this.e.get(i);
                    a(f);
                    if (f.mLoaderManager != null) {
                        loadersRunning |= f.mLoaderManager.a();
                    }
                }
                int numActive = this.f.size();
                for (i = 0; i < numActive; i++) {
                    f = (Fragment) this.f.valueAt(i);
                    if (f != null && ((f.mRemoving || f.mDetached) && !f.mIsNewlyAdded)) {
                        a(f);
                        if (f.mLoaderManager != null) {
                            loadersRunning |= f.mLoaderManager.a();
                        }
                    }
                }
                if (!loadersRunning) {
                    u();
                }
                if (this.r && this.m != null && this.l == 5) {
                    this.m.d();
                    this.r = false;
                }
            }
        }
    }

    private void u() {
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); i++) {
                Fragment f = (Fragment) this.f.valueAt(i);
                if (f != null && f.mDeferStart) {
                    if (this.c) {
                        this.v = true;
                    } else {
                        f.mDeferStart = false;
                        a(f, this.l, 0, 0, false);
                    }
                }
            }
        }
    }

    final void b(Fragment f) {
        if (f.mIndex < 0) {
            int i = this.d;
            this.d = i + 1;
            f.setIndex(i, this.o);
            if (this.f == null) {
                this.f = new SparseArray();
            }
            this.f.put(f.mIndex, f);
            if (a) {
                new StringBuilder("Allocated fragment index ").append(f);
            }
        }
    }

    public final void a(Fragment fragment, boolean moveToStateNow) {
        if (a) {
            new StringBuilder("add: ").append(fragment);
        }
        b(fragment);
        if (!fragment.mDetached) {
            if (this.e.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            synchronized (this.e) {
                this.e.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.r = true;
            }
            if (moveToStateNow) {
                i(fragment);
            }
        }
    }

    public final void c(Fragment fragment) {
        if (a) {
            new StringBuilder("remove: ").append(fragment).append(" nesting=").append(fragment.mBackStackNesting);
        }
        boolean inactive;
        if (fragment.isInBackStack()) {
            inactive = false;
        } else {
            inactive = true;
        }
        if (!fragment.mDetached || inactive) {
            synchronized (this.e) {
                this.e.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.r = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public static void d(Fragment fragment) {
        boolean z = true;
        if (a) {
            new StringBuilder("hide: ").append(fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mHiddenChanged) {
                z = false;
            }
            fragment.mHiddenChanged = z;
        }
    }

    public static void e(Fragment fragment) {
        boolean z = false;
        if (a) {
            new StringBuilder("show: ").append(fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (!fragment.mHiddenChanged) {
                z = true;
            }
            fragment.mHiddenChanged = z;
        }
    }

    public final void f(Fragment fragment) {
        if (a) {
            new StringBuilder("detach: ").append(fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (a) {
                    new StringBuilder("remove from detach: ").append(fragment);
                }
                synchronized (this.e) {
                    this.e.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.r = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public final void g(Fragment fragment) {
        if (a) {
            new StringBuilder("attach: ").append(fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (this.e.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (a) {
                    new StringBuilder("add from attach: ").append(fragment);
                }
                synchronized (this.e) {
                    this.e.add(fragment);
                }
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.r = true;
                }
            }
        }
    }

    private Fragment c(int id) {
        int i;
        Fragment f;
        for (i = this.e.size() - 1; i >= 0; i--) {
            f = (Fragment) this.e.get(i);
            if (f != null && f.mFragmentId == id) {
                return f;
            }
        }
        if (this.f != null) {
            for (i = this.f.size() - 1; i >= 0; i--) {
                f = (Fragment) this.f.valueAt(i);
                if (f != null && f.mFragmentId == id) {
                    return f;
                }
            }
        }
        return null;
    }

    public final Fragment a(String tag) {
        int i;
        Fragment f;
        if (tag != null) {
            for (i = this.e.size() - 1; i >= 0; i--) {
                f = (Fragment) this.e.get(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        if (!(this.f == null || tag == null)) {
            for (i = this.f.size() - 1; i >= 0; i--) {
                f = (Fragment) this.f.valueAt(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        return null;
    }

    public final Fragment b(String who) {
        if (!(this.f == null || who == null)) {
            for (int i = this.f.size() - 1; i >= 0; i--) {
                Fragment f = (Fragment) this.f.valueAt(i);
                if (f != null) {
                    f = f.findFragmentByWho(who);
                    if (f != null) {
                        return f;
                    }
                }
            }
        }
        return null;
    }

    private void v() {
        if (this.s) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.u != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.u);
        }
    }

    public final boolean e() {
        return this.s;
    }

    public final void a(f action, boolean allowStateLoss) {
        if (!allowStateLoss) {
            v();
        }
        synchronized (this) {
            if (!this.t && this.m != null) {
                if (this.b == null) {
                    this.b = new ArrayList();
                }
                this.b.add(action);
                w();
            } else if (allowStateLoss) {
            } else {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    private void w() {
        boolean pendingReady = true;
        synchronized (this) {
            boolean postponeReady;
            if (this.B == null || this.B.isEmpty()) {
                postponeReady = false;
            } else {
                postponeReady = true;
            }
            if (this.b == null || this.b.size() != 1) {
                pendingReady = false;
            }
            if (postponeReady || pendingReady) {
                this.m.h().removeCallbacks(this.D);
                this.m.h().post(this.D);
            }
        }
    }

    public final int a(b bse) {
        synchronized (this) {
            int index;
            if (this.j == null || this.j.size() <= 0) {
                if (this.i == null) {
                    this.i = new ArrayList();
                }
                index = this.i.size();
                if (a) {
                    new StringBuilder("Setting back stack index ").append(index).append(" to ").append(bse);
                }
                this.i.add(bse);
                return index;
            }
            index = ((Integer) this.j.remove(this.j.size() - 1)).intValue();
            if (a) {
                new StringBuilder("Adding back stack index ").append(index).append(" with ").append(bse);
            }
            this.i.set(index, bse);
            return index;
        }
    }

    private void a(int index, b bse) {
        synchronized (this) {
            if (this.i == null) {
                this.i = new ArrayList();
            }
            int N = this.i.size();
            if (index < N) {
                if (a) {
                    new StringBuilder("Setting back stack index ").append(index).append(" to ").append(bse);
                }
                this.i.set(index, bse);
            } else {
                while (N < index) {
                    this.i.add(null);
                    if (this.j == null) {
                        this.j = new ArrayList();
                    }
                    this.j.add(Integer.valueOf(N));
                    N++;
                }
                if (a) {
                    new StringBuilder("Adding back stack index ").append(index).append(" with ").append(bse);
                }
                this.i.add(bse);
            }
        }
    }

    private void x() {
        if (this.c) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (Looper.myLooper() != this.m.h().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else {
            if (this.w == null) {
                this.w = new ArrayList();
                this.x = new ArrayList();
            }
            this.c = true;
            try {
                a(null, null);
            } finally {
                this.c = false;
            }
        }
    }

    private void y() {
        this.c = false;
        this.x.clear();
        this.w.clear();
    }

    public final boolean f() {
        x();
        boolean didSomething = false;
        while (c(this.w, this.x)) {
            this.c = true;
            try {
                b(this.w, this.x);
                y();
                didSomething = true;
            } catch (Throwable th) {
                y();
                throw th;
            }
        }
        A();
        D();
        return didSomething;
    }

    private void a(ArrayList<b> records, ArrayList<Boolean> isRecordPop) {
        int numPostponed = this.B == null ? 0 : this.B.size();
        int i = 0;
        while (i < numPostponed) {
            int index;
            h listener = (h) this.B.get(i);
            if (!(records == null || listener.a)) {
                index = records.indexOf(listener.b);
                if (index != -1 && ((Boolean) isRecordPop.get(index)).booleanValue()) {
                    listener.e();
                    i++;
                }
            }
            if (listener.c() || (records != null && listener.b.a((ArrayList) records, 0, records.size()))) {
                this.B.remove(i);
                i--;
                numPostponed--;
                if (!(records == null || listener.a)) {
                    index = records.indexOf(listener.b);
                    if (index != -1 && ((Boolean) isRecordPop.get(index)).booleanValue()) {
                        listener.e();
                    }
                }
                listener.d();
            }
            i++;
        }
    }

    private void b(ArrayList<b> records, ArrayList<Boolean> isRecordPop) {
        if (records != null && !records.isEmpty()) {
            if (isRecordPop == null || records.size() != isRecordPop.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            a((ArrayList) records, (ArrayList) isRecordPop);
            int numRecords = records.size();
            int startIndex = 0;
            int recordNum = 0;
            while (recordNum < numRecords) {
                if (!((b) records.get(recordNum)).u) {
                    if (startIndex != recordNum) {
                        a((ArrayList) records, (ArrayList) isRecordPop, startIndex, recordNum);
                    }
                    int reorderingEnd = recordNum + 1;
                    if (((Boolean) isRecordPop.get(recordNum)).booleanValue()) {
                        while (reorderingEnd < numRecords && ((Boolean) isRecordPop.get(reorderingEnd)).booleanValue() && !((b) records.get(reorderingEnd)).u) {
                            reorderingEnd++;
                        }
                    }
                    a((ArrayList) records, (ArrayList) isRecordPop, recordNum, reorderingEnd);
                    startIndex = reorderingEnd;
                    recordNum = reorderingEnd - 1;
                }
                recordNum++;
            }
            if (startIndex != numRecords) {
                a((ArrayList) records, (ArrayList) isRecordPop, startIndex, numRecords);
            }
        }
    }

    private void a(ArrayList<b> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        int recordNum;
        b record;
        boolean allowReordering = ((b) records.get(startIndex)).u;
        boolean addToBackStack = false;
        if (this.y == null) {
            this.y = new ArrayList();
        } else {
            this.y.clear();
        }
        this.y.addAll(this.e);
        Fragment oldPrimaryNav = this.p;
        for (recordNum = startIndex; recordNum < endIndex; recordNum++) {
            record = (b) records.get(recordNum);
            if (((Boolean) isRecordPop.get(recordNum)).booleanValue()) {
                oldPrimaryNav = record.b(this.y, oldPrimaryNav);
            } else {
                oldPrimaryNav = record.a(this.y, oldPrimaryNav);
            }
            if (addToBackStack || record.j) {
                addToBackStack = true;
            } else {
                addToBackStack = false;
            }
        }
        this.y.clear();
        if (!allowReordering) {
            l.a(this, records, isRecordPop, startIndex, endIndex, false);
        }
        b(records, isRecordPop, startIndex, endIndex);
        int postponeIndex = endIndex;
        if (allowReordering) {
            android.support.v4.util.b addedFragments = new android.support.v4.util.b();
            b(addedFragments);
            postponeIndex = a((ArrayList) records, (ArrayList) isRecordPop, startIndex, endIndex, addedFragments);
            a(addedFragments);
        }
        if (postponeIndex != startIndex && allowReordering) {
            l.a(this, records, isRecordPop, startIndex, postponeIndex, true);
            a(this.l, true);
        }
        for (recordNum = startIndex; recordNum < endIndex; recordNum++) {
            record = (b) records.get(recordNum);
            if (((Boolean) isRecordPop.get(recordNum)).booleanValue() && record.n >= 0) {
                int i = record.n;
                synchronized (this) {
                    this.i.set(i, null);
                    if (this.j == null) {
                        this.j = new ArrayList();
                    }
                    this.j.add(Integer.valueOf(i));
                }
                record.n = -1;
            }
            record.a();
        }
        if (addToBackStack) {
            B();
        }
    }

    private static void a(android.support.v4.util.b<Fragment> fragments) {
        int numAdded = fragments.size();
        for (int i = 0; i < numAdded; i++) {
            Fragment fragment = (Fragment) fragments.a(i);
            if (!fragment.mAdded) {
                View view = fragment.getView();
                fragment.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0f);
            }
        }
    }

    private int a(ArrayList<b> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex, android.support.v4.util.b<Fragment> added) {
        int postponeIndex = endIndex;
        int i = endIndex - 1;
        while (i >= startIndex) {
            boolean z;
            b record = (b) records.get(i);
            boolean isPop = ((Boolean) isRecordPop.get(i)).booleanValue();
            if (!record.e() || record.a((ArrayList) records, i + 1, endIndex)) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                if (this.B == null) {
                    this.B = new ArrayList();
                }
                c listener = new h(record, isPop);
                this.B.add(listener);
                record.a(listener);
                if (isPop) {
                    record.d();
                } else {
                    record.a(false);
                }
                postponeIndex--;
                if (i != postponeIndex) {
                    records.remove(i);
                    records.add(postponeIndex, record);
                }
                b((android.support.v4.util.b) added);
            }
            i--;
        }
        return postponeIndex;
    }

    private static void b(ArrayList<b> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        int i = startIndex;
        while (i < endIndex) {
            b record = (b) records.get(i);
            if (((Boolean) isRecordPop.get(i)).booleanValue()) {
                record.a(-1);
                record.a(i == endIndex + -1);
            } else {
                record.a(1);
                record.d();
            }
            i++;
        }
    }

    private void b(android.support.v4.util.b<Fragment> added) {
        if (this.l > 0) {
            int state = Math.min(this.l, 4);
            int numAdded = this.e.size();
            for (int i = 0; i < numAdded; i++) {
                Fragment fragment = (Fragment) this.e.get(i);
                if (fragment.mState < state) {
                    a(fragment, state, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (!(fragment.mView == null || fragment.mHidden || !fragment.mIsNewlyAdded)) {
                        added.add(fragment);
                    }
                }
            }
        }
    }

    private void z() {
        if (this.B != null) {
            while (!this.B.isEmpty()) {
                ((h) this.B.remove(0)).d();
            }
        }
    }

    private boolean c(ArrayList<b> records, ArrayList<Boolean> isPop) {
        boolean didSomething = false;
        synchronized (this) {
            if (this.b == null || this.b.size() == 0) {
                return false;
            }
            for (int i = 0; i < this.b.size(); i++) {
                didSomething |= ((f) this.b.get(i)).a(records, isPop);
            }
            this.b.clear();
            this.m.h().removeCallbacks(this.D);
            return didSomething;
        }
    }

    private void A() {
        if (this.v) {
            boolean loadersRunning = false;
            for (int i = 0; i < this.f.size(); i++) {
                Fragment f = (Fragment) this.f.valueAt(i);
                if (!(f == null || f.mLoaderManager == null)) {
                    loadersRunning |= f.mLoaderManager.a();
                }
            }
            if (!loadersRunning) {
                this.v = false;
                u();
            }
        }
    }

    private void B() {
        if (this.k != null) {
            for (int i = 0; i < this.k.size(); i++) {
                this.k.get(i);
            }
        }
    }

    final boolean a(ArrayList<b> records, ArrayList<Boolean> isRecordPop, String name, int id, int flags) {
        if (this.g == null) {
            return false;
        }
        if (name == null && id < 0 && (flags & 1) == 0) {
            int last = this.g.size() - 1;
            if (last < 0) {
                return false;
            }
            records.add(this.g.remove(last));
            isRecordPop.add(Boolean.valueOf(true));
        } else {
            int index = -1;
            if (name != null || id >= 0) {
                b bss;
                index = this.g.size() - 1;
                while (index >= 0) {
                    bss = (b) this.g.get(index);
                    if ((name != null && name.equals(bss.l)) || (id >= 0 && id == bss.n)) {
                        break;
                    }
                    index--;
                }
                if (index < 0) {
                    return false;
                }
                if ((flags & 1) != 0) {
                    index--;
                    while (index >= 0) {
                        bss = (b) this.g.get(index);
                        if ((name == null || !name.equals(bss.l)) && (id < 0 || id != bss.n)) {
                            break;
                        }
                        index--;
                    }
                }
            }
            if (index == this.g.size() - 1) {
                return false;
            }
            for (int i = this.g.size() - 1; i > index; i--) {
                records.add(this.g.remove(i));
                isRecordPop.add(Boolean.valueOf(true));
            }
        }
        return true;
    }

    final k g() {
        a(this.C);
        return this.C;
    }

    private static void a(k nonConfig) {
        if (nonConfig != null) {
            List<Fragment> fragments = nonConfig.a();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    fragment.mRetaining = true;
                }
            }
            List<k> children = nonConfig.b();
            if (children != null) {
                for (k a : children) {
                    a(a);
                }
            }
        }
    }

    private void C() {
        ArrayList<Fragment> fragments = null;
        ArrayList<k> childFragments = null;
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); i++) {
                Fragment f = (Fragment) this.f.valueAt(i);
                if (f != null) {
                    k child;
                    if (f.mRetainInstance) {
                        if (fragments == null) {
                            fragments = new ArrayList();
                        }
                        fragments.add(f);
                        f.mTargetIndex = f.mTarget != null ? f.mTarget.mIndex : -1;
                        if (a) {
                            new StringBuilder("retainNonConfig: keeping retained ").append(f);
                        }
                    }
                    if (f.mChildFragmentManager != null) {
                        f.mChildFragmentManager.C();
                        child = f.mChildFragmentManager.C;
                    } else {
                        child = f.mChildNonConfig;
                    }
                    if (childFragments == null && child != null) {
                        childFragments = new ArrayList(this.f.size());
                        for (int j = 0; j < i; j++) {
                            childFragments.add(null);
                        }
                    }
                    if (childFragments != null) {
                        childFragments.add(child);
                    }
                }
            }
        }
        if (fragments == null && childFragments == null) {
            this.C = null;
        } else {
            this.C = new k(fragments, childFragments);
        }
    }

    private void j(Fragment f) {
        if (f.mInnerView != null) {
            if (this.A == null) {
                this.A = new SparseArray();
            } else {
                this.A.clear();
            }
            f.mInnerView.saveHierarchyState(this.A);
            if (this.A.size() > 0) {
                f.mSavedViewState = this.A;
                this.A = null;
            }
        }
    }

    final Parcelable h() {
        int i;
        z();
        if (this.f == null) {
            i = 0;
        } else {
            i = this.f.size();
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                break;
            }
            Fragment fragment = (Fragment) this.f.valueAt(i3);
            if (fragment != null) {
                if (fragment.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragment.getStateAfterAnimating();
                    View animatingAway = fragment.getAnimatingAway();
                    fragment.setAnimatingAway(null);
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    a(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
            i2 = i3 + 1;
        }
        f();
        this.s = true;
        this.C = null;
        if (this.f == null || this.f.size() <= 0) {
            return null;
        }
        int i4;
        int N = this.f.size();
        FragmentState[] active = new FragmentState[N];
        boolean haveFragments = false;
        for (i4 = 0; i4 < N; i4++) {
            Fragment f = (Fragment) this.f.valueAt(i4);
            if (f != null) {
                if (f.mIndex < 0) {
                    a(new IllegalStateException("Failure saving state: active " + f + " has cleared index: " + f.mIndex));
                }
                haveFragments = true;
                FragmentState fs = new FragmentState(f);
                active[i4] = fs;
                if (f.mState <= 0 || fs.k != null) {
                    fs.k = f.mSavedFragmentState;
                } else {
                    Bundle bundle = null;
                    if (this.z == null) {
                        this.z = new Bundle();
                    }
                    f.performSaveInstanceState(this.z);
                    d(f, this.z, false);
                    if (!this.z.isEmpty()) {
                        bundle = this.z;
                        this.z = null;
                    }
                    if (f.mView != null) {
                        j(f);
                    }
                    if (f.mSavedViewState != null) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putSparseParcelableArray("android:view_state", f.mSavedViewState);
                    }
                    if (!f.mUserVisibleHint) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putBoolean("android:user_visible_hint", f.mUserVisibleHint);
                    }
                    fs.k = bundle;
                    if (f.mTarget != null) {
                        if (f.mTarget.mIndex < 0) {
                            a(new IllegalStateException("Failure saving state: " + f + " has target not in fragment manager: " + f.mTarget));
                        }
                        if (fs.k == null) {
                            fs.k = new Bundle();
                        }
                        bundle = fs.k;
                        String str = "android:target_state";
                        Fragment fragment2 = f.mTarget;
                        if (fragment2.mIndex < 0) {
                            a(new IllegalStateException("Fragment " + fragment2 + " is not currently in the FragmentManager"));
                        }
                        bundle.putInt(str, fragment2.mIndex);
                        if (f.mTargetRequestCode != 0) {
                            fs.k.putInt("android:target_req_state", f.mTargetRequestCode);
                        }
                    }
                }
                if (a) {
                    new StringBuilder("Saved state of ").append(f).append(": ").append(fs.k);
                }
            }
        }
        if (!haveFragments) {
            return null;
        }
        int[] added = null;
        BackStackState[] backStack = null;
        N = this.e.size();
        if (N > 0) {
            added = new int[N];
            for (i4 = 0; i4 < N; i4++) {
                added[i4] = ((Fragment) this.e.get(i4)).mIndex;
                if (added[i4] < 0) {
                    a(new IllegalStateException("Failure saving state: active " + this.e.get(i4) + " has cleared index: " + added[i4]));
                }
                if (a) {
                    new StringBuilder("saveAllState: adding fragment #").append(i4).append(": ").append(this.e.get(i4));
                }
            }
        }
        if (this.g != null) {
            N = this.g.size();
            if (N > 0) {
                backStack = new BackStackState[N];
                for (i4 = 0; i4 < N; i4++) {
                    backStack[i4] = new BackStackState((b) this.g.get(i4));
                    if (a) {
                        new StringBuilder("saveAllState: adding back stack #").append(i4).append(": ").append(this.g.get(i4));
                    }
                }
            }
        }
        Parcelable fms = new FragmentManagerState();
        fms.a = active;
        fms.b = added;
        fms.c = backStack;
        if (this.p != null) {
            fms.d = this.p.mIndex;
        }
        fms.e = this.d;
        C();
        return fms;
    }

    final void a(Parcelable state, k nonConfig) {
        if (state != null) {
            FragmentManagerState fms = (FragmentManagerState) state;
            if (fms.a != null) {
                List<Fragment> nonConfigFragments;
                int count;
                int i;
                Fragment f;
                FragmentState fs;
                List<k> childNonConfigs = null;
                if (nonConfig != null) {
                    nonConfigFragments = nonConfig.a();
                    childNonConfigs = nonConfig.b();
                    count = nonConfigFragments != null ? nonConfigFragments.size() : 0;
                    for (i = 0; i < count; i++) {
                        f = (Fragment) nonConfigFragments.get(i);
                        if (a) {
                            new StringBuilder("restoreAllState: re-attaching retained ").append(f);
                        }
                        int index = 0;
                        while (index < fms.a.length && fms.a[index].b != f.mIndex) {
                            index++;
                        }
                        if (index == fms.a.length) {
                            a(new IllegalStateException("Could not find active fragment with index " + f.mIndex));
                        }
                        fs = fms.a[index];
                        fs.l = f;
                        f.mSavedViewState = null;
                        f.mBackStackNesting = 0;
                        f.mInLayout = false;
                        f.mAdded = false;
                        f.mTarget = null;
                        if (fs.k != null) {
                            fs.k.setClassLoader(this.m.b.getClassLoader());
                            f.mSavedViewState = fs.k.getSparseParcelableArray("android:view_state");
                            f.mSavedFragmentState = fs.k;
                        }
                    }
                }
                this.f = new SparseArray(fms.a.length);
                i = 0;
                while (i < fms.a.length) {
                    fs = fms.a[i];
                    if (fs != null) {
                        k childNonConfig = null;
                        if (childNonConfigs != null && i < childNonConfigs.size()) {
                            childNonConfig = (k) childNonConfigs.get(i);
                        }
                        h hVar = this.m;
                        f fVar = this.n;
                        Fragment fragment = this.o;
                        if (fs.l == null) {
                            Context context = hVar.b;
                            if (fs.i != null) {
                                fs.i.setClassLoader(context.getClassLoader());
                            }
                            if (fVar != null) {
                                fs.l = fVar.a(context, fs.a, fs.i);
                            } else {
                                fs.l = Fragment.instantiate(context, fs.a, fs.i);
                            }
                            if (fs.k != null) {
                                fs.k.setClassLoader(context.getClassLoader());
                                fs.l.mSavedFragmentState = fs.k;
                            }
                            fs.l.setIndex(fs.b, fragment);
                            fs.l.mFromLayout = fs.c;
                            fs.l.mRestored = true;
                            fs.l.mFragmentId = fs.d;
                            fs.l.mContainerId = fs.e;
                            fs.l.mTag = fs.f;
                            fs.l.mRetainInstance = fs.g;
                            fs.l.mDetached = fs.h;
                            fs.l.mHidden = fs.j;
                            fs.l.mFragmentManager = hVar.d;
                            if (a) {
                                new StringBuilder("Instantiated fragment ").append(fs.l);
                            }
                        }
                        fs.l.mChildNonConfig = childNonConfig;
                        f = fs.l;
                        if (a) {
                            new StringBuilder("restoreAllState: active #").append(i).append(": ").append(f);
                        }
                        this.f.put(f.mIndex, f);
                        fs.l = null;
                    }
                    i++;
                }
                if (nonConfig != null) {
                    nonConfigFragments = nonConfig.a();
                    count = nonConfigFragments != null ? nonConfigFragments.size() : 0;
                    for (i = 0; i < count; i++) {
                        f = (Fragment) nonConfigFragments.get(i);
                        if (f.mTargetIndex >= 0) {
                            f.mTarget = (Fragment) this.f.get(f.mTargetIndex);
                            if (f.mTarget == null) {
                                new StringBuilder("Re-attaching retained fragment ").append(f).append(" target no longer exists: ").append(f.mTargetIndex);
                            }
                        }
                    }
                }
                this.e.clear();
                if (fms.b != null) {
                    for (i = 0; i < fms.b.length; i++) {
                        f = (Fragment) this.f.get(fms.b[i]);
                        if (f == null) {
                            a(new IllegalStateException("No instantiated fragment for index #" + fms.b[i]));
                        }
                        f.mAdded = true;
                        if (a) {
                            new StringBuilder("restoreAllState: added #").append(i).append(": ").append(f);
                        }
                        if (this.e.contains(f)) {
                            throw new IllegalStateException("Already added!");
                        }
                        synchronized (this.e) {
                            this.e.add(f);
                        }
                    }
                }
                if (fms.c != null) {
                    this.g = new ArrayList(fms.c.length);
                    for (i = 0; i < fms.c.length; i++) {
                        b bse = fms.c[i].a(this);
                        if (a) {
                            new StringBuilder("restoreAllState: back stack #").append(i).append(" (index ").append(bse.n).append("): ").append(bse);
                            PrintWriter pw = new PrintWriter(new android.support.v4.util.e("FragmentManager"));
                            bse.a("  ", pw, false);
                            pw.close();
                        }
                        this.g.add(bse);
                        if (bse.n >= 0) {
                            a(bse.n, bse);
                        }
                    }
                } else {
                    this.g = null;
                }
                if (fms.d >= 0) {
                    this.p = (Fragment) this.f.get(fms.d);
                }
                this.d = fms.e;
            }
        }
    }

    private void D() {
        if (this.f != null) {
            for (int i = this.f.size() - 1; i >= 0; i--) {
                if (this.f.valueAt(i) == null) {
                    this.f.delete(this.f.keyAt(i));
                }
            }
        }
    }

    public final void a(h host, f container, Fragment parent) {
        if (this.m != null) {
            throw new IllegalStateException("Already attached");
        }
        this.m = host;
        this.n = container;
        this.o = parent;
    }

    public final void i() {
        this.C = null;
        this.s = false;
        int addedCount = this.e.size();
        for (int i = 0; i < addedCount; i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public final void j() {
        this.s = false;
        d(1);
    }

    public final void k() {
        this.s = false;
        d(2);
    }

    public final void l() {
        this.s = false;
        d(4);
    }

    public final void m() {
        this.s = false;
        d(5);
    }

    public final void n() {
        d(4);
    }

    public final void o() {
        this.s = true;
        d(3);
    }

    public final void p() {
        d(2);
    }

    public final void q() {
        d(1);
    }

    public final void r() {
        this.t = true;
        f();
        d(0);
        this.m = null;
        this.n = null;
        this.o = null;
    }

    private void d(int nextState) {
        try {
            this.c = true;
            a(nextState, false);
            f();
        } finally {
            this.c = false;
        }
    }

    public final void a(boolean isInMultiWindowMode) {
        for (int i = this.e.size() - 1; i >= 0; i--) {
            Fragment f = (Fragment) this.e.get(i);
            if (f != null) {
                f.performMultiWindowModeChanged(isInMultiWindowMode);
            }
        }
    }

    public final void b(boolean isInPictureInPictureMode) {
        for (int i = this.e.size() - 1; i >= 0; i--) {
            Fragment f = (Fragment) this.e.get(i);
            if (f != null) {
                f.performPictureInPictureModeChanged(isInPictureInPictureMode);
            }
        }
    }

    public final void a(Configuration newConfig) {
        for (int i = 0; i < this.e.size(); i++) {
            Fragment f = (Fragment) this.e.get(i);
            if (f != null) {
                f.performConfigurationChanged(newConfig);
            }
        }
    }

    public final void s() {
        for (int i = 0; i < this.e.size(); i++) {
            Fragment f = (Fragment) this.e.get(i);
            if (f != null) {
                f.performLowMemory();
            }
        }
    }

    public final boolean a(Menu menu, MenuInflater inflater) {
        int i;
        Fragment f;
        boolean show = false;
        ArrayList<Fragment> newMenus = null;
        for (i = 0; i < this.e.size(); i++) {
            f = (Fragment) this.e.get(i);
            if (f != null && f.performCreateOptionsMenu(menu, inflater)) {
                show = true;
                if (newMenus == null) {
                    newMenus = new ArrayList();
                }
                newMenus.add(f);
            }
        }
        if (this.h != null) {
            for (i = 0; i < this.h.size(); i++) {
                f = (Fragment) this.h.get(i);
                if (newMenus == null || !newMenus.contains(f)) {
                    f.onDestroyOptionsMenu();
                }
            }
        }
        this.h = newMenus;
        return show;
    }

    public final boolean a(Menu menu) {
        boolean show = false;
        for (int i = 0; i < this.e.size(); i++) {
            Fragment f = (Fragment) this.e.get(i);
            if (f != null && f.performPrepareOptionsMenu(menu)) {
                show = true;
            }
        }
        return show;
    }

    public final boolean a(MenuItem item) {
        for (int i = 0; i < this.e.size(); i++) {
            Fragment f = (Fragment) this.e.get(i);
            if (f != null && f.performOptionsItemSelected(item)) {
                return true;
            }
        }
        return false;
    }

    public final boolean b(MenuItem item) {
        for (int i = 0; i < this.e.size(); i++) {
            Fragment f = (Fragment) this.e.get(i);
            if (f != null && f.performContextItemSelected(item)) {
                return true;
            }
        }
        return false;
    }

    public final void b(Menu menu) {
        for (int i = 0; i < this.e.size(); i++) {
            Fragment f = (Fragment) this.e.get(i);
            if (f != null) {
                f.performOptionsMenuClosed(menu);
            }
        }
    }

    public final void h(Fragment f) {
        if (f == null || (this.f.get(f.mIndex) == f && (f.mHost == null || f.getFragmentManager() == this))) {
            this.p = f;
            return;
        }
        throw new IllegalArgumentException("Fragment " + f + " is not an active fragment of FragmentManager " + this);
    }

    private void a(Fragment f, Context context, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).a(f, context, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void b(Fragment f, Context context, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).b(f, context, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void a(Fragment f, Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).a(f, savedInstanceState, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void b(Fragment f, Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).b(f, savedInstanceState, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void c(Fragment f, Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).c(f, savedInstanceState, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void a(Fragment f, View v, Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).a(f, v, savedInstanceState, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void b(Fragment f, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).b(f, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void c(Fragment f, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).c(f, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void d(Fragment f, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).d(f, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void e(Fragment f, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).e(f, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void d(Fragment f, Bundle outState, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).d(f, outState, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void f(Fragment f, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).f(f, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void g(Fragment f, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).g(f, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    private void h(Fragment f, boolean onlyRecursive) {
        if (this.o != null) {
            i parentManager = this.o.getFragmentManager();
            if (parentManager instanceof j) {
                ((j) parentManager).h(f, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            i p = (i) it.next();
            if (onlyRecursive) {
                ((Boolean) p.b).booleanValue();
            }
        }
    }

    public static int b(int transit) {
        switch (transit) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    public final View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (!"fragment".equals(name)) {
            return null;
        }
        String fname = attrs.getAttributeValue(null, "class");
        TypedArray a = context.obtainStyledAttributes(attrs, e.a);
        if (fname == null) {
            fname = a.getString(0);
        }
        int id = a.getResourceId(1, -1);
        String tag = a.getString(2);
        a.recycle();
        if (!Fragment.isSupportFragmentClass(this.m.b, fname)) {
            return null;
        }
        int containerId;
        if (parent != null) {
            containerId = parent.getId();
        } else {
            containerId = 0;
        }
        if (containerId == -1 && id == -1 && tag == null) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + fname);
        }
        Fragment fragment;
        if (id != -1) {
            fragment = c(id);
        } else {
            fragment = null;
        }
        if (fragment == null && tag != null) {
            fragment = a(tag);
        }
        if (fragment == null && containerId != -1) {
            fragment = c(containerId);
        }
        if (a) {
            new StringBuilder("onCreateView: id=0x").append(Integer.toHexString(id)).append(" fname=").append(fname).append(" existing=").append(fragment);
        }
        if (fragment == null) {
            int i;
            fragment = this.n.a(context, fname, null);
            fragment.mFromLayout = true;
            if (id != 0) {
                i = id;
            } else {
                i = containerId;
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = containerId;
            fragment.mTag = tag;
            fragment.mInLayout = true;
            fragment.mFragmentManager = this;
            fragment.mHost = this.m;
            fragment.onInflate(this.m.b, attrs, fragment.mSavedFragmentState);
            a(fragment, true);
        } else if (fragment.mInLayout) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(id) + ", tag " + tag + ", or parent id 0x" + Integer.toHexString(containerId) + " with another fragment for " + fname);
        } else {
            fragment.mInLayout = true;
            fragment.mHost = this.m;
            if (!fragment.mRetaining) {
                fragment.onInflate(this.m.b, attrs, fragment.mSavedFragmentState);
            }
        }
        if (this.l > 0 || !fragment.mFromLayout) {
            i(fragment);
        } else {
            a(fragment, 1, 0, 0, false);
        }
        if (fragment.mView == null) {
            throw new IllegalStateException("Fragment " + fname + " did not create a view.");
        }
        if (id != 0) {
            fragment.mView.setId(id);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(tag);
        }
        return fragment.mView;
    }

    public final View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }
}
