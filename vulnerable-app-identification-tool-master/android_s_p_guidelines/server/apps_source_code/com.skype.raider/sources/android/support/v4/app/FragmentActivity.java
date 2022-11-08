package android.support.v4.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.util.l;
import android.support.v4.util.m;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class FragmentActivity extends d implements android.support.v4.app.a.a, android.support.v4.app.a.b {
    boolean mCreated;
    final g mFragments = g.a(new a(this));
    final Handler mHandler = new Handler(this) {
        final /* synthetic */ FragmentActivity a;

        {
            this.a = this$0;
        }

        public final void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (this.a.mStopped) {
                        this.a.doReallyStop(false);
                        return;
                    }
                    return;
                case 2:
                    this.a.onResumeFragments();
                    this.a.mFragments.o();
                    return;
                default:
                    super.handleMessage(msg);
                    return;
            }
        }
    };
    int mNextCandidateRequestIndex;
    m<String> mPendingFragmentActivityResults;
    boolean mReallyStopped = true;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped = true;

    class a extends h<FragmentActivity> {
        final /* synthetic */ FragmentActivity a;

        public a(FragmentActivity this$0) {
            this.a = this$0;
            super(this$0);
        }

        public final void a(String prefix, PrintWriter writer, String[] args) {
            this.a.dump(prefix, null, writer, args);
        }

        public final boolean b() {
            return !this.a.isFinishing();
        }

        public final LayoutInflater c() {
            return this.a.getLayoutInflater().cloneInContext(this.a);
        }

        public final void d() {
            this.a.supportInvalidateOptionsMenu();
        }

        public final boolean e() {
            return this.a.getWindow() != null;
        }

        public final int f() {
            Window w = this.a.getWindow();
            return w == null ? 0 : w.getAttributes().windowAnimations;
        }

        public final void a(Fragment fragment) {
            this.a.onAttachFragment(fragment);
        }

        @Nullable
        public final View a(int id) {
            return this.a.findViewById(id);
        }

        public final boolean a() {
            Window w = this.a.getWindow();
            return (w == null || w.peekDecorView() == null) ? false : true;
        }
    }

    static final class b {
        Object a;
        k b;
        l<String, p> c;

        b() {
        }
    }

    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @RequiresApi(16)
    public /* bridge */ /* synthetic */ void startActivityForResult(Intent intent, int i, @Nullable Bundle bundle) {
        super.startActivityForResult(intent, i, bundle);
    }

    public /* bridge */ /* synthetic */ void startIntentSenderForResult(IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4) throws SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @RequiresApi(16)
    public /* bridge */ /* synthetic */ void startIntentSenderForResult(IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, Bundle bundle) throws SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.mFragments.c();
        int requestIndex = requestCode >> 16;
        if (requestIndex != 0) {
            requestIndex--;
            String who = (String) this.mPendingFragmentActivityResults.a(requestIndex);
            this.mPendingFragmentActivityResults.b(requestIndex);
            if (who != null) {
                Fragment targetFragment = this.mFragments.a(who);
                if (targetFragment != null) {
                    targetFragment.onActivityResult(65535 & requestCode, resultCode, data);
                    return;
                }
                return;
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
        i fragmentManager = this.mFragments.a();
        boolean isStateSaved = fragmentManager.e();
        if (isStateSaved && VERSION.SDK_INT <= 25) {
            return;
        }
        if (isStateSaved || !fragmentManager.c()) {
            super.onBackPressed();
        }
    }

    @CallSuper
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        this.mFragments.a(isInMultiWindowMode);
    }

    @CallSuper
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        this.mFragments.b(isInPictureInPictureMode);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mFragments.a(newConfig);
    }

    public android.arch.lifecycle.b getLifecycle() {
        return super.getLifecycle();
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.mFragments.b();
        super.onCreate(savedInstanceState);
        b nc = (b) getLastNonConfigurationInstance();
        if (nc != null) {
            this.mFragments.a(nc.c);
        }
        if (savedInstanceState != null) {
            this.mFragments.a(savedInstanceState.getParcelable("android:support:fragments"), nc != null ? nc.b : null);
            if (savedInstanceState.containsKey("android:support:next_request_index")) {
                this.mNextCandidateRequestIndex = savedInstanceState.getInt("android:support:next_request_index");
                int[] requestCodes = savedInstanceState.getIntArray("android:support:request_indicies");
                String[] fragmentWhos = savedInstanceState.getStringArray("android:support:request_fragment_who");
                if (!(requestCodes == null || fragmentWhos == null || requestCodes.length != fragmentWhos.length)) {
                    this.mPendingFragmentActivityResults = new m(requestCodes.length);
                    for (int i = 0; i < requestCodes.length; i++) {
                        this.mPendingFragmentActivityResults.a(requestCodes[i], fragmentWhos[i]);
                    }
                }
            }
        }
        if (this.mPendingFragmentActivityResults == null) {
            this.mPendingFragmentActivityResults = new m();
            this.mNextCandidateRequestIndex = 0;
        }
        this.mFragments.f();
    }

    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId == 0) {
            return super.onCreatePanelMenu(featureId, menu) | this.mFragments.a(menu, getMenuInflater());
        }
        return super.onCreatePanelMenu(featureId, menu);
    }

    final View dispatchFragmentsOnCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return this.mFragments.a(parent, name, context, attrs);
    }

    protected void onDestroy() {
        super.onDestroy();
        doReallyStop(false);
        this.mFragments.m();
        this.mFragments.q();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.n();
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (super.onMenuItemSelected(featureId, item)) {
            return true;
        }
        switch (featureId) {
            case 0:
                return this.mFragments.a(item);
            case 6:
                return this.mFragments.b(item);
            default:
                return false;
        }
    }

    public void onPanelClosed(int featureId, Menu menu) {
        switch (featureId) {
            case 0:
                this.mFragments.b(menu);
                break;
        }
        super.onPanelClosed(featureId, menu);
    }

    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            onResumeFragments();
        }
        this.mFragments.j();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.c();
    }

    public void onStateNotSaved() {
        this.mFragments.c();
    }

    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.mFragments.o();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        onResumeFragments();
        this.mFragments.o();
    }

    protected void onResumeFragments() {
        this.mFragments.i();
    }

    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        if (featureId != 0 || menu == null) {
            return super.onPreparePanel(featureId, view, menu);
        }
        return onPrepareOptionsPanel(view, menu) | this.mFragments.a(menu);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public final Object onRetainNonConfigurationInstance() {
        if (this.mStopped) {
            doReallyStop(true);
        }
        Object custom = onRetainCustomNonConfigurationInstance();
        k fragments = this.mFragments.e();
        l<String, p> loaders = this.mFragments.s();
        if (fragments == null && loaders == null && custom == null) {
            return null;
        }
        Object nci = new b();
        nci.a = custom;
        nci.b = fragments;
        nci.c = loaders;
        return nci;
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        markState(getSupportFragmentManager(), android.arch.lifecycle.b.b.CREATED);
        Parcelable p = this.mFragments.d();
        if (p != null) {
            outState.putParcelable("android:support:fragments", p);
        }
        if (this.mPendingFragmentActivityResults.a() > 0) {
            outState.putInt("android:support:next_request_index", this.mNextCandidateRequestIndex);
            int[] requestCodes = new int[this.mPendingFragmentActivityResults.a()];
            String[] fragmentWhos = new String[this.mPendingFragmentActivityResults.a()];
            for (int i = 0; i < this.mPendingFragmentActivityResults.a(); i++) {
                requestCodes[i] = this.mPendingFragmentActivityResults.c(i);
                fragmentWhos[i] = (String) this.mPendingFragmentActivityResults.d(i);
            }
            outState.putIntArray("android:support:request_indicies", requestCodes);
            outState.putStringArray("android:support:request_fragment_who", fragmentWhos);
        }
    }

    protected void onStart() {
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(1);
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.g();
        }
        this.mFragments.c();
        this.mFragments.o();
        this.mFragments.p();
        this.mFragments.h();
        this.mFragments.r();
    }

    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        markState(getSupportFragmentManager(), android.arch.lifecycle.b.b.CREATED);
        this.mHandler.sendEmptyMessage(1);
        this.mFragments.k();
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        writer.print(prefix);
        writer.print("Local FragmentActivity ");
        writer.print(Integer.toHexString(System.identityHashCode(this)));
        writer.println(" State:");
        String innerPrefix = prefix + "  ";
        writer.print(innerPrefix);
        writer.print("mCreated=");
        writer.print(this.mCreated);
        writer.print("mResumed=");
        writer.print(this.mResumed);
        writer.print(" mStopped=");
        writer.print(this.mStopped);
        writer.print(" mReallyStopped=");
        writer.println(this.mReallyStopped);
        this.mFragments.a(innerPrefix, fd, writer);
        this.mFragments.a().a(prefix, fd, writer, args);
    }

    void doReallyStop(boolean retaining) {
        if (!this.mReallyStopped) {
            this.mReallyStopped = true;
            this.mRetaining = retaining;
            this.mHandler.removeMessages(1);
            onReallyStop();
        } else if (retaining) {
            this.mFragments.p();
            this.mFragments.c(true);
        }
    }

    void onReallyStop() {
        this.mFragments.c(this.mRetaining);
        this.mFragments.l();
    }

    public void onAttachFragment(Fragment fragment) {
    }

    public i getSupportFragmentManager() {
        return this.mFragments.a();
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        if (!(this.mStartedActivityFromFragment || requestCode == -1)) {
            c.checkForValidRequestCode(requestCode);
        }
        super.startActivityForResult(intent, requestCode);
    }

    public final void validateRequestPermissionsRequestCode(int requestCode) {
        if (!this.mRequestedPermissionsFromFragment && requestCode != -1) {
            c.checkForValidRequestCode(requestCode);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int index = (requestCode >> 16) & 65535;
        if (index != 0) {
            index--;
            String who = (String) this.mPendingFragmentActivityResults.a(index);
            this.mPendingFragmentActivityResults.b(index);
            if (who != null) {
                Fragment frag = this.mFragments.a(who);
                if (frag != null) {
                    frag.onRequestPermissionsResult(requestCode & 65535, permissions, grantResults);
                }
            }
        }
    }

    private static void markState(i manager, android.arch.lifecycle.b.b state) {
        for (Fragment fragment : manager.d()) {
            if (fragment != null) {
                fragment.mLifecycleRegistry.a(state);
                markState(fragment.getChildFragmentManager(), state);
            }
        }
    }
}
