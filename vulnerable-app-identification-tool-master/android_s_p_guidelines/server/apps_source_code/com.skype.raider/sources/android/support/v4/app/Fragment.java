package android.support.v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.d;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.l;
import android.support.v4.view.e;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class Fragment implements android.arch.lifecycle.c, ComponentCallbacks, OnCreateContextMenuListener {
    static final Object USE_DEFAULT_TRANSITION = new Object();
    private static final l<String, Class<?>> sClassMap = new l();
    boolean mAdded;
    a mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    j mChildFragmentManager;
    k mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    j mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    h mHost;
    boolean mInLayout;
    int mIndex = -1;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    d mLifecycleRegistry = new d(this);
    q mLoaderManager;
    boolean mLoadersStarted;
    boolean mMenuVisible = true;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    SparseArray<Parcelable> mSavedViewState;
    int mState = 0;
    String mTag;
    Fragment mTarget;
    int mTargetIndex = -1;
    int mTargetRequestCode;
    boolean mUserVisibleHint = true;
    View mView;
    String mWho;

    public static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        final Bundle a;

        SavedState(Parcel in) {
            this.a = in.readBundle();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeBundle(this.a);
        }
    }

    static class a {
        View a;
        Animator b;
        int c;
        int d;
        int e;
        int f;
        ag g = null;
        ag h = null;
        boolean i;
        c j;
        boolean k;
        private Object l = null;
        private Object m = Fragment.USE_DEFAULT_TRANSITION;
        private Object n = null;
        private Object o = Fragment.USE_DEFAULT_TRANSITION;
        private Object p = null;
        private Object q = Fragment.USE_DEFAULT_TRANSITION;
        private Boolean r;
        private Boolean s;

        a() {
        }
    }

    public static class b extends RuntimeException {
        public b(String msg, Exception cause) {
            super(msg, cause);
        }
    }

    interface c {
        void a();

        void b();
    }

    public android.arch.lifecycle.b getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public static Fragment instantiate(Context context, String fname, @Nullable Bundle args) {
        try {
            Class<?> clazz = (Class) sClassMap.get(fname);
            if (clazz == null) {
                clazz = context.getClassLoader().loadClass(fname);
                sClassMap.put(fname, clazz);
            }
            Fragment f = (Fragment) clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
            if (args != null) {
                args.setClassLoader(f.getClass().getClassLoader());
                f.setArguments(args);
            }
            return f;
        } catch (ClassNotFoundException e) {
            throw new b("Unable to instantiate fragment " + fname + ": make sure class name exists, is public, and has an empty constructor that is public", e);
        } catch (InstantiationException e2) {
            throw new b("Unable to instantiate fragment " + fname + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (IllegalAccessException e3) {
            throw new b("Unable to instantiate fragment " + fname + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new b("Unable to instantiate fragment " + fname + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new b("Unable to instantiate fragment " + fname + ": calling Fragment constructor caused an exception", e5);
        }
    }

    static boolean isSupportFragmentClass(Context context, String fname) {
        try {
            Class<?> clazz = (Class) sClassMap.get(fname);
            if (clazz == null) {
                clazz = context.getClassLoader().loadClass(fname);
                sClassMap.put(fname, clazz);
            }
            return Fragment.class.isAssignableFrom(clazz);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    final void restoreViewState(Bundle savedInstanceState) {
        if (this.mSavedViewState != null) {
            this.mInnerView.restoreHierarchyState(this.mSavedViewState);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(savedInstanceState);
        if (!this.mCalled) {
            throw new ah("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    final void setIndex(int index, Fragment parent) {
        this.mIndex = index;
        if (parent != null) {
            this.mWho = parent.mWho + ":" + this.mIndex;
        } else {
            this.mWho = "android:fragment:" + this.mIndex;
        }
    }

    final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean equals(Object o) {
        return super.equals(o);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        android.support.v4.util.d.a(this, sb);
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" ");
            sb.append(this.mTag);
        }
        sb.append('}');
        return sb.toString();
    }

    public void setArguments(Bundle args) {
        if (this.mIndex < 0 || !isStateSaved()) {
            this.mArguments = args;
            return;
        }
        throw new IllegalStateException("Fragment already active and state has been saved");
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final boolean isStateSaved() {
        if (this.mFragmentManager == null) {
            return false;
        }
        return this.mFragmentManager.s;
    }

    public Context getContext() {
        return this.mHost == null ? null : this.mHost.b;
    }

    public final FragmentActivity getActivity() {
        return this.mHost == null ? null : (FragmentActivity) this.mHost.g();
    }

    public final Resources getResources() {
        if (this.mHost != null) {
            return this.mHost.b.getResources();
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final i getFragmentManager() {
        return this.mFragmentManager;
    }

    public final i getChildFragmentManager() {
        if (this.mChildFragmentManager == null) {
            instantiateChildFragmentManager();
            if (this.mState >= 5) {
                this.mChildFragmentManager.m();
            } else if (this.mState >= 4) {
                this.mChildFragmentManager.l();
            } else if (this.mState >= 2) {
                this.mChildFragmentManager.k();
            } else if (this.mState > 0) {
                this.mChildFragmentManager.j();
            }
        }
        return this.mChildFragmentManager;
    }

    i peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isVisible() {
        return (!isAdded() || isHidden() || this.mView == null || this.mView.getWindowToken() == null || this.mView.getVisibility() != 0) ? false : true;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    public void onHiddenChanged(boolean hidden) {
    }

    public void setRetainInstance(boolean retain) {
        this.mRetainInstance = retain;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
        return getLayoutInflater(savedInstanceState);
    }

    LayoutInflater performGetLayoutInflater(Bundle savedInstanceState) {
        this.mLayoutInflater = onGetLayoutInflater(savedInstanceState);
        return this.mLayoutInflater;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle savedFragmentState) {
        if (this.mHost == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater result = this.mHost.c();
        getChildFragmentManager();
        e.b(result, this.mChildFragmentManager);
        return result;
    }

    @CallSuper
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        this.mCalled = true;
        Activity hostActivity = this.mHost == null ? null : this.mHost.g();
        if (hostActivity != null) {
            this.mCalled = false;
            onInflate(hostActivity, attrs, savedInstanceState);
        }
    }

    @Deprecated
    @CallSuper
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        this.mCalled = true;
    }

    public void onAttachFragment(Fragment childFragment) {
    }

    @CallSuper
    public void onAttach(Context context) {
        this.mCalled = true;
        Activity hostActivity = this.mHost == null ? null : this.mHost.g();
        if (hostActivity != null) {
            this.mCalled = false;
            onAttach(hostActivity);
        }
    }

    @Deprecated
    @CallSuper
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return null;
    }

    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        return null;
    }

    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        boolean z = true;
        this.mCalled = true;
        restoreChildFragmentState(savedInstanceState);
        if (this.mChildFragmentManager != null) {
            if (this.mChildFragmentManager.l <= 0) {
                z = false;
            }
            if (!z) {
                this.mChildFragmentManager.j();
            }
        }
    }

    void restoreChildFragmentState(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Parcelable p = savedInstanceState.getParcelable("android:support:fragments");
            if (p != null) {
                if (this.mChildFragmentManager == null) {
                    instantiateChildFragmentManager();
                }
                this.mChildFragmentManager.a(p, this.mChildNonConfig);
                this.mChildNonConfig = null;
                this.mChildFragmentManager.j();
            }
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    @Nullable
    public View getView() {
        return this.mView;
    }

    @CallSuper
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        this.mCalled = true;
    }

    @CallSuper
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        this.mCalled = true;
    }

    @CallSuper
    public void onStart() {
        this.mCalled = true;
        if (!this.mLoadersStarted) {
            this.mLoadersStarted = true;
            if (!this.mCheckedForLoaderManager) {
                this.mCheckedForLoaderManager = true;
                this.mLoaderManager = this.mHost.a(this.mWho, this.mLoadersStarted);
            } else if (this.mLoaderManager != null) {
                this.mLoaderManager.b();
            }
        }
    }

    @CallSuper
    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
    }

    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
    }

    @CallSuper
    public void onConfigurationChanged(Configuration newConfig) {
        this.mCalled = true;
    }

    @CallSuper
    public void onPause() {
        this.mCalled = true;
    }

    @CallSuper
    public void onStop() {
        this.mCalled = true;
    }

    @CallSuper
    public void onLowMemory() {
        this.mCalled = true;
    }

    @CallSuper
    public void onDestroyView() {
        this.mCalled = true;
    }

    @CallSuper
    public void onDestroy() {
        this.mCalled = true;
        if (!this.mCheckedForLoaderManager) {
            this.mCheckedForLoaderManager = true;
            this.mLoaderManager = this.mHost.a(this.mWho, this.mLoadersStarted);
        }
        if (this.mLoaderManager != null) {
            this.mLoaderManager.g();
        }
    }

    void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
        this.mLoaderManager = null;
        this.mLoadersStarted = false;
        this.mCheckedForLoaderManager = false;
    }

    @CallSuper
    public void onDetach() {
        this.mCalled = true;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onDestroyOptionsMenu() {
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getActivity().onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item) {
        return false;
    }

    public Object getEnterTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.l;
    }

    public Object getReturnTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        if (this.mAnimationInfo.m == USE_DEFAULT_TRANSITION) {
            return getEnterTransition();
        }
        return this.mAnimationInfo.m;
    }

    public Object getExitTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.n;
    }

    public Object getReenterTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        if (this.mAnimationInfo.o == USE_DEFAULT_TRANSITION) {
            return getExitTransition();
        }
        return this.mAnimationInfo.o;
    }

    public Object getSharedElementEnterTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.p;
    }

    public Object getSharedElementReturnTransition() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        if (this.mAnimationInfo.q == USE_DEFAULT_TRANSITION) {
            return getSharedElementEnterTransition();
        }
        return this.mAnimationInfo.q;
    }

    public boolean getAllowEnterTransitionOverlap() {
        if (this.mAnimationInfo == null || this.mAnimationInfo.s == null) {
            return true;
        }
        return this.mAnimationInfo.s.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        if (this.mAnimationInfo == null || this.mAnimationInfo.r == null) {
            return true;
        }
        return this.mAnimationInfo.r.booleanValue();
    }

    public void startPostponedEnterTransition() {
        if (this.mFragmentManager == null || this.mFragmentManager.m == null) {
            ensureAnimationInfo().i = false;
        } else if (Looper.myLooper() != this.mFragmentManager.m.h().getLooper()) {
            this.mFragmentManager.m.h().postAtFrontOfQueue(new Runnable(this) {
                final /* synthetic */ Fragment a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    this.a.callStartTransitionListener();
                }
            });
        } else {
            callStartTransitionListener();
        }
    }

    private void callStartTransitionListener() {
        c listener;
        if (this.mAnimationInfo == null) {
            listener = null;
        } else {
            this.mAnimationInfo.i = false;
            listener = this.mAnimationInfo.j;
            this.mAnimationInfo.j = null;
        }
        if (listener != null) {
            listener.a();
        }
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.print(prefix);
        writer.print("mFragmentId=#");
        writer.print(Integer.toHexString(this.mFragmentId));
        writer.print(" mContainerId=#");
        writer.print(Integer.toHexString(this.mContainerId));
        writer.print(" mTag=");
        writer.println(this.mTag);
        writer.print(prefix);
        writer.print("mState=");
        writer.print(this.mState);
        writer.print(" mIndex=");
        writer.print(this.mIndex);
        writer.print(" mWho=");
        writer.print(this.mWho);
        writer.print(" mBackStackNesting=");
        writer.println(this.mBackStackNesting);
        writer.print(prefix);
        writer.print("mAdded=");
        writer.print(this.mAdded);
        writer.print(" mRemoving=");
        writer.print(this.mRemoving);
        writer.print(" mFromLayout=");
        writer.print(this.mFromLayout);
        writer.print(" mInLayout=");
        writer.println(this.mInLayout);
        writer.print(prefix);
        writer.print("mHidden=");
        writer.print(this.mHidden);
        writer.print(" mDetached=");
        writer.print(this.mDetached);
        writer.print(" mMenuVisible=");
        writer.print(this.mMenuVisible);
        writer.print(" mHasMenu=");
        writer.println(this.mHasMenu);
        writer.print(prefix);
        writer.print("mRetainInstance=");
        writer.print(this.mRetainInstance);
        writer.print(" mRetaining=");
        writer.print(this.mRetaining);
        writer.print(" mUserVisibleHint=");
        writer.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            writer.print(prefix);
            writer.print("mFragmentManager=");
            writer.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            writer.print(prefix);
            writer.print("mHost=");
            writer.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            writer.print(prefix);
            writer.print("mParentFragment=");
            writer.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            writer.print(prefix);
            writer.print("mArguments=");
            writer.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            writer.print(prefix);
            writer.print("mSavedFragmentState=");
            writer.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            writer.print(prefix);
            writer.print("mSavedViewState=");
            writer.println(this.mSavedViewState);
        }
        if (this.mTarget != null) {
            writer.print(prefix);
            writer.print("mTarget=");
            writer.print(this.mTarget);
            writer.print(" mTargetRequestCode=");
            writer.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            writer.print(prefix);
            writer.print("mNextAnim=");
            writer.println(getNextAnim());
        }
        if (this.mContainer != null) {
            writer.print(prefix);
            writer.print("mContainer=");
            writer.println(this.mContainer);
        }
        if (this.mView != null) {
            writer.print(prefix);
            writer.print("mView=");
            writer.println(this.mView);
        }
        if (this.mInnerView != null) {
            writer.print(prefix);
            writer.print("mInnerView=");
            writer.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            writer.print(prefix);
            writer.print("mAnimatingAway=");
            writer.println(getAnimatingAway());
            writer.print(prefix);
            writer.print("mStateAfterAnimating=");
            writer.println(getStateAfterAnimating());
        }
        if (this.mLoaderManager != null) {
            writer.print(prefix);
            writer.println("Loader Manager:");
            this.mLoaderManager.a(prefix + "  ", writer);
        }
        if (this.mChildFragmentManager != null) {
            writer.print(prefix);
            writer.println("Child " + this.mChildFragmentManager + ":");
            this.mChildFragmentManager.a(prefix + "  ", fd, writer, args);
        }
    }

    Fragment findFragmentByWho(String who) {
        if (who.equals(this.mWho)) {
            return this;
        }
        if (this.mChildFragmentManager != null) {
            return this.mChildFragmentManager.b(who);
        }
        return null;
    }

    void instantiateChildFragmentManager() {
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment has not been attached yet.");
        }
        this.mChildFragmentManager = new j();
        this.mChildFragmentManager.a(this.mHost, new f(this) {
            final /* synthetic */ Fragment a;

            {
                this.a = this$0;
            }

            @Nullable
            public final View a(int id) {
                if (this.a.mView != null) {
                    return this.a.mView.findViewById(id);
                }
                throw new IllegalStateException("Fragment does not have a view");
            }

            public final boolean a() {
                return this.a.mView != null;
            }

            public final Fragment a(Context context, String className, Bundle arguments) {
                return this.a.mHost.a(context, className, arguments);
            }
        }, this);
    }

    void performCreate(Bundle savedInstanceState) {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.i();
        }
        this.mState = 1;
        this.mCalled = false;
        onCreate(savedInstanceState);
        this.mIsCreated = true;
        if (this.mCalled) {
            this.mLifecycleRegistry.a(android.arch.lifecycle.b.a.ON_CREATE);
            return;
        }
        throw new ah("Fragment " + this + " did not call through to super.onCreate()");
    }

    View performCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.i();
        }
        this.mPerformedCreateView = true;
        return onCreateView(inflater, container, savedInstanceState);
    }

    void performActivityCreated(Bundle savedInstanceState) {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.i();
        }
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(savedInstanceState);
        if (!this.mCalled) {
            throw new ah("Fragment " + this + " did not call through to super.onActivityCreated()");
        } else if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.k();
        }
    }

    void performStart() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.i();
            this.mChildFragmentManager.f();
        }
        this.mState = 4;
        this.mCalled = false;
        onStart();
        if (this.mCalled) {
            if (this.mChildFragmentManager != null) {
                this.mChildFragmentManager.l();
            }
            if (this.mLoaderManager != null) {
                this.mLoaderManager.f();
            }
            this.mLifecycleRegistry.a(android.arch.lifecycle.b.a.ON_START);
            return;
        }
        throw new ah("Fragment " + this + " did not call through to super.onStart()");
    }

    void performResume() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.i();
            this.mChildFragmentManager.f();
        }
        this.mState = 5;
        this.mCalled = false;
        onResume();
        if (this.mCalled) {
            if (this.mChildFragmentManager != null) {
                this.mChildFragmentManager.m();
                this.mChildFragmentManager.f();
            }
            this.mLifecycleRegistry.a(android.arch.lifecycle.b.a.ON_RESUME);
            return;
        }
        throw new ah("Fragment " + this + " did not call through to super.onResume()");
    }

    void noteStateNotSaved() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.i();
        }
    }

    void performMultiWindowModeChanged(boolean isInMultiWindowMode) {
        onMultiWindowModeChanged(isInMultiWindowMode);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.a(isInMultiWindowMode);
        }
    }

    void performPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        onPictureInPictureModeChanged(isInPictureInPictureMode);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.b(isInPictureInPictureMode);
        }
    }

    void performConfigurationChanged(Configuration newConfig) {
        onConfigurationChanged(newConfig);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.a(newConfig);
        }
    }

    void performLowMemory() {
        onLowMemory();
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.s();
        }
    }

    boolean performCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        boolean show = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            show = true;
            onCreateOptionsMenu(menu, inflater);
        }
        if (this.mChildFragmentManager != null) {
            return show | this.mChildFragmentManager.a(menu, inflater);
        }
        return show;
    }

    boolean performPrepareOptionsMenu(Menu menu) {
        boolean show = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            show = true;
            onPrepareOptionsMenu(menu);
        }
        if (this.mChildFragmentManager != null) {
            return show | this.mChildFragmentManager.a(menu);
        }
        return show;
    }

    boolean performOptionsItemSelected(MenuItem item) {
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(item)) {
                return true;
            }
            if (this.mChildFragmentManager != null && this.mChildFragmentManager.a(item)) {
                return true;
            }
        }
        return false;
    }

    boolean performContextItemSelected(MenuItem item) {
        if (!this.mHidden) {
            if (onContextItemSelected(item)) {
                return true;
            }
            if (this.mChildFragmentManager != null && this.mChildFragmentManager.b(item)) {
                return true;
            }
        }
        return false;
    }

    void performOptionsMenuClosed(Menu menu) {
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible) {
                onOptionsMenuClosed(menu);
            }
            if (this.mChildFragmentManager != null) {
                this.mChildFragmentManager.b(menu);
            }
        }
    }

    void performSaveInstanceState(Bundle outState) {
        onSaveInstanceState(outState);
        if (this.mChildFragmentManager != null) {
            Parcelable p = this.mChildFragmentManager.h();
            if (p != null) {
                outState.putParcelable("android:support:fragments", p);
            }
        }
    }

    void performPause() {
        this.mLifecycleRegistry.a(android.arch.lifecycle.b.a.ON_PAUSE);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.n();
        }
        this.mState = 4;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new ah("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    void performStop() {
        this.mLifecycleRegistry.a(android.arch.lifecycle.b.a.ON_STOP);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.o();
        }
        this.mState = 3;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new ah("Fragment " + this + " did not call through to super.onStop()");
        }
    }

    void performReallyStop() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.p();
        }
        this.mState = 2;
        if (this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if (!this.mCheckedForLoaderManager) {
                this.mCheckedForLoaderManager = true;
                this.mLoaderManager = this.mHost.a(this.mWho, this.mLoadersStarted);
            }
            if (this.mLoaderManager == null) {
                return;
            }
            if (this.mHost.i()) {
                this.mLoaderManager.d();
            } else {
                this.mLoaderManager.c();
            }
        }
    }

    void performDestroyView() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.q();
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled) {
            if (this.mLoaderManager != null) {
                this.mLoaderManager.e();
            }
            this.mPerformedCreateView = false;
            return;
        }
        throw new ah("Fragment " + this + " did not call through to super.onDestroyView()");
    }

    void performDestroy() {
        this.mLifecycleRegistry.a(android.arch.lifecycle.b.a.ON_DESTROY);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.r();
        }
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (this.mCalled) {
            this.mChildFragmentManager = null;
            return;
        }
        throw new ah("Fragment " + this + " did not call through to super.onDestroy()");
    }

    void performDetach() {
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (!this.mCalled) {
            throw new ah("Fragment " + this + " did not call through to super.onDetach()");
        } else if (this.mChildFragmentManager == null) {
        } else {
            if (this.mRetaining) {
                this.mChildFragmentManager.r();
                this.mChildFragmentManager = null;
                return;
            }
            throw new IllegalStateException("Child FragmentManager of " + this + " was not  destroyed and this fragment is not retaining instance");
        }
    }

    void setOnStartEnterTransitionListener(c listener) {
        ensureAnimationInfo();
        if (listener != this.mAnimationInfo.j) {
            if (listener == null || this.mAnimationInfo.j == null) {
                if (this.mAnimationInfo.i) {
                    this.mAnimationInfo.j = listener;
                }
                if (listener != null) {
                    listener.b();
                    return;
                }
                return;
            }
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
    }

    private a ensureAnimationInfo() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new a();
        }
        return this.mAnimationInfo;
    }

    int getNextAnim() {
        if (this.mAnimationInfo == null) {
            return 0;
        }
        return this.mAnimationInfo.d;
    }

    void setNextAnim(int animResourceId) {
        if (this.mAnimationInfo != null || animResourceId != 0) {
            ensureAnimationInfo().d = animResourceId;
        }
    }

    int getNextTransition() {
        if (this.mAnimationInfo == null) {
            return 0;
        }
        return this.mAnimationInfo.e;
    }

    void setNextTransition(int nextTransition, int nextTransitionStyle) {
        if (this.mAnimationInfo != null || nextTransition != 0 || nextTransitionStyle != 0) {
            ensureAnimationInfo();
            this.mAnimationInfo.e = nextTransition;
            this.mAnimationInfo.f = nextTransitionStyle;
        }
    }

    int getNextTransitionStyle() {
        if (this.mAnimationInfo == null) {
            return 0;
        }
        return this.mAnimationInfo.f;
    }

    ag getEnterTransitionCallback() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.g;
    }

    ag getExitTransitionCallback() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.h;
    }

    View getAnimatingAway() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.a;
    }

    void setAnimatingAway(View view) {
        ensureAnimationInfo().a = view;
    }

    void setAnimator(Animator animator) {
        ensureAnimationInfo().b = animator;
    }

    Animator getAnimator() {
        if (this.mAnimationInfo == null) {
            return null;
        }
        return this.mAnimationInfo.b;
    }

    int getStateAfterAnimating() {
        if (this.mAnimationInfo == null) {
            return 0;
        }
        return this.mAnimationInfo.c;
    }

    void setStateAfterAnimating(int state) {
        ensureAnimationInfo().c = state;
    }

    boolean isPostponed() {
        if (this.mAnimationInfo == null) {
            return false;
        }
        return this.mAnimationInfo.i;
    }

    boolean isHideReplaced() {
        if (this.mAnimationInfo == null) {
            return false;
        }
        return this.mAnimationInfo.k;
    }

    void setHideReplaced(boolean replaced) {
        ensureAnimationInfo().k = replaced;
    }
}
