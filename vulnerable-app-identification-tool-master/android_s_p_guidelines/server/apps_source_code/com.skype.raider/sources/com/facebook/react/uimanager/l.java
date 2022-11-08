package com.facebook.react.uimanager;

import android.content.res.Resources;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import com.facebook.react.a.c;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.an;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.a.f;
import com.facebook.react.uimanager.a.g;
import com.facebook.systrace.b;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class l {
    private static final String a = l.class.getSimpleName();
    private final c b;
    private final SparseArray<View> c;
    private final SparseArray<ViewManager> d;
    private final SparseBooleanArray e;
    private final ap f;
    private final com.facebook.react.d.a g;
    private final RootViewManager h;
    private final f i;
    private boolean j;

    private static class a implements OnDismissListener, OnMenuItemClickListener {
        final d a;
        boolean b;

        /* synthetic */ a(d x0, byte b) {
            this(x0);
        }

        private a(d success) {
            this.b = false;
            this.a = success;
        }

        public final void onDismiss(PopupMenu menu) {
            if (!this.b) {
                this.a.invoke("dismissed");
                this.b = true;
            }
        }

        public final boolean onMenuItemClick(MenuItem item) {
            if (this.b) {
                return false;
            }
            this.a.invoke("itemSelected", Integer.valueOf(item.getOrder()));
            this.b = true;
            return true;
        }
    }

    public l(ap viewManagers) {
        this(viewManagers, new RootViewManager());
    }

    private l(ap viewManagers, RootViewManager manager) {
        this.g = new com.facebook.react.d.a();
        this.i = new f();
        this.b = new c();
        this.f = viewManagers;
        this.c = new SparseArray();
        this.d = new SparseArray();
        this.e = new SparseBooleanArray();
        this.h = manager;
    }

    public final synchronized View a(int tag) {
        View view;
        view = (View) this.c.get(tag);
        if (view == null) {
            throw new f("Trying to resolve view with tag " + tag + " which doesn't exist");
        }
        return view;
    }

    private synchronized ViewManager c(int tag) {
        ViewManager viewManager;
        viewManager = (ViewManager) this.d.get(tag);
        if (viewManager == null) {
            throw new f("ViewManager for tag " + tag + " could not be found");
        }
        return viewManager;
    }

    public final c a() {
        return this.b;
    }

    public final void a(boolean enabled) {
        this.j = enabled;
    }

    public final synchronized void a(int tag, x props) {
        ap.b();
        try {
            c(tag).updateProperties(a(tag), props);
        } catch (f e) {
        }
    }

    public final synchronized void a(int tag, Object extraData) {
        ap.b();
        c(tag).updateExtraData(a(tag), extraData);
    }

    public final synchronized void a(int parentTag, int tag, int x, int y, int width, int height) {
        ap.b();
        b.a();
        try {
            View viewToUpdate = a(tag);
            viewToUpdate.measure(MeasureSpec.makeMeasureSpec(width, ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(height, ErrorDialogData.SUPPRESSED));
            if (this.e.get(parentTag)) {
                a(viewToUpdate, x, y, width, height);
            } else {
                ViewManager parentViewManager = (ViewManager) this.d.get(parentTag);
                if (parentViewManager instanceof e) {
                    e parentViewManagerWithChildren = (e) parentViewManager;
                    if (!(parentViewManagerWithChildren == null || parentViewManagerWithChildren.needsCustomLayoutForChildren())) {
                        a(viewToUpdate, x, y, width, height);
                    }
                } else {
                    throw new f("Trying to use view with tag " + parentTag + " as a parent, but its Manager doesn't implement IViewManagerWithChildren");
                }
            }
            com.facebook.systrace.a.a();
        } catch (Throwable th) {
            com.facebook.systrace.a.a();
        }
    }

    private void a(View viewToUpdate, int x, int y, int width, int height) {
        if (this.j && this.i.a(viewToUpdate)) {
            this.i.a(viewToUpdate, x, y, width, height);
        } else {
            viewToUpdate.layout(x, y, x + width, y + height);
        }
    }

    public final synchronized void a(ae themedContext, int tag, String className, @Nullable x initialProps) {
        ap.b();
        b.a();
        try {
            ViewManager viewManager = this.f.a(className);
            View view = viewManager.createView(themedContext, this.g);
            this.c.put(tag, view);
            this.d.put(tag, viewManager);
            view.setId(tag);
            if (initialProps != null) {
                viewManager.updateProperties(view, initialProps);
            }
            com.facebook.systrace.a.a();
        } catch (Throwable th) {
            com.facebook.systrace.a.a();
        }
    }

    private static String a(ViewGroup viewToManage, ViewGroupManager viewManager, @Nullable int[] indicesToRemove, @Nullable am[] viewsToAdd, @Nullable int[] tagsToDelete) {
        int index;
        int innerOffset;
        StringBuilder stringBuilder = new StringBuilder();
        if (viewToManage != null) {
            stringBuilder.append("View tag:" + viewToManage.getId() + "\n");
            stringBuilder.append("  children(" + viewManager.getChildCount(viewToManage) + "): [\n");
            for (index = 0; index < viewManager.getChildCount(viewToManage); index += 16) {
                innerOffset = 0;
                while (index + innerOffset < viewManager.getChildCount(viewToManage) && innerOffset < 16) {
                    stringBuilder.append(viewManager.getChildAt(viewToManage, index + innerOffset).getId() + ",");
                    innerOffset++;
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append(" ],\n");
        }
        if (indicesToRemove != null) {
            stringBuilder.append("  indicesToRemove(" + indicesToRemove.length + "): [\n");
            for (index = 0; index < indicesToRemove.length; index += 16) {
                innerOffset = 0;
                while (index + innerOffset < indicesToRemove.length && innerOffset < 16) {
                    stringBuilder.append(indicesToRemove[index + innerOffset] + ",");
                    innerOffset++;
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append(" ],\n");
        }
        if (viewsToAdd != null) {
            stringBuilder.append("  viewsToAdd(" + viewsToAdd.length + "): [\n");
            for (index = 0; index < viewsToAdd.length; index += 16) {
                innerOffset = 0;
                while (index + innerOffset < viewsToAdd.length && innerOffset < 16) {
                    stringBuilder.append("[" + viewsToAdd[index + innerOffset].c + "," + viewsToAdd[index + innerOffset].b + "],");
                    innerOffset++;
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append(" ],\n");
        }
        if (tagsToDelete != null) {
            stringBuilder.append("  tagsToDelete(" + tagsToDelete.length + "): [\n");
            for (index = 0; index < tagsToDelete.length; index += 16) {
                innerOffset = 0;
                while (index + innerOffset < tagsToDelete.length && innerOffset < 16) {
                    stringBuilder.append(tagsToDelete[index + innerOffset] + ",");
                    innerOffset++;
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append(" ]\n");
        }
        return stringBuilder.toString();
    }

    public final synchronized void a(int tag, @Nullable int[] indicesToRemove, @Nullable am[] viewsToAdd, @Nullable int[] tagsToDelete) {
        ap.b();
        final ViewGroup viewToManage = (ViewGroup) this.c.get(tag);
        final ViewGroupManager viewManager = (ViewGroupManager) c(tag);
        if (viewToManage == null) {
            throw new f("Trying to manageChildren view with tag " + tag + " which doesn't exist\n detail: " + a(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
        }
        int i;
        int lastIndexToRemove = viewManager.getChildCount(viewToManage);
        if (indicesToRemove != null) {
            i = indicesToRemove.length - 1;
            while (i >= 0) {
                int indexToRemove = indicesToRemove[i];
                if (indexToRemove < 0) {
                    throw new f("Trying to remove a negative view index:" + indexToRemove + " view tag: " + tag + "\n detail: " + a(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                } else if (indexToRemove >= viewManager.getChildCount(viewToManage)) {
                    throw new f("Trying to remove a view index above child count " + indexToRemove + " view tag: " + tag + "\n detail: " + a(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                } else if (indexToRemove >= lastIndexToRemove) {
                    throw new f("Trying to remove an out of order view index:" + indexToRemove + " view tag: " + tag + "\n detail: " + a(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                } else {
                    View viewToRemove = viewManager.getChildAt(viewToManage, indexToRemove);
                    if (this.j && this.i.a(viewToRemove)) {
                        if (a(tagsToDelete, viewToRemove.getId())) {
                            lastIndexToRemove = indexToRemove;
                            i--;
                        }
                    }
                    viewManager.removeViewAt(viewToManage, indexToRemove);
                    lastIndexToRemove = indexToRemove;
                    i--;
                }
            }
        }
        if (viewsToAdd != null) {
            for (am viewAtIndex : viewsToAdd) {
                View viewToAdd = (View) this.c.get(viewAtIndex.b);
                if (viewToAdd == null) {
                    throw new f("Trying to add unknown view tag: " + viewAtIndex.b + "\n detail: " + a(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                }
                viewManager.addView(viewToManage, viewToAdd, viewAtIndex.c);
            }
        }
        if (tagsToDelete != null) {
            for (int tagToDelete : tagsToDelete) {
                final View viewToDestroy = (View) this.c.get(tagToDelete);
                if (viewToDestroy == null) {
                    throw new f("Trying to destroy unknown view tag: " + tagToDelete + "\n detail: " + a(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                }
                if (this.j && this.i.a(viewToDestroy)) {
                    this.i.a(viewToDestroy, new g(this) {
                        final /* synthetic */ l d;

                        public final void a() {
                            viewManager.removeView(viewToManage, viewToDestroy);
                            this.d.a(viewToDestroy);
                        }
                    });
                } else {
                    a(viewToDestroy);
                }
            }
        }
    }

    private static boolean a(@Nullable int[] array, int ele) {
        if (array == null) {
            return false;
        }
        for (int i : array) {
            if (i == ele) {
                return true;
            }
        }
        return false;
    }

    public final synchronized void a(int tag, SizeMonitoringFrameLayout view) {
        a(tag, (ViewGroup) view);
    }

    private synchronized void a(int tag, ViewGroup view) {
        if (view.getId() != -1) {
            throw new f("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
        }
        this.c.put(tag, view);
        this.d.put(tag, this.h);
        this.e.put(tag, true);
        view.setId(tag);
    }

    protected final synchronized void a(View view) {
        ap.b();
        if (!this.e.get(view.getId())) {
            c(view.getId()).onDropViewInstance(view);
        }
        ViewManager viewManager = (ViewManager) this.d.get(view.getId());
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            for (int i = viewGroupManager.getChildCount(viewGroup) - 1; i >= 0; i--) {
                View child = viewGroupManager.getChildAt(viewGroup, i);
                if (this.c.get(child.getId()) != null) {
                    a(child);
                }
            }
            viewGroupManager.removeAllViews(viewGroup);
        }
        this.c.remove(view.getId());
        this.d.remove(view.getId());
    }

    public final synchronized void b(int rootViewTag) {
        ap.b();
        if (!this.e.get(rootViewTag)) {
            an.a("View with tag " + rootViewTag + " is not registered as a root view");
        }
        a((View) this.c.get(rootViewTag));
        this.e.delete(rootViewTag);
    }

    public final synchronized void a(int tag, int[] outputBuffer) {
        ap.b();
        View v = (View) this.c.get(tag);
        if (v == null) {
            throw new n("No native view for " + tag + " currently exists");
        }
        View rootView = (View) ab.a(v);
        if (rootView == null) {
            throw new n("Native view " + tag + " is no longer on screen");
        }
        rootView.getLocationInWindow(outputBuffer);
        int rootX = outputBuffer[0];
        int rootY = outputBuffer[1];
        v.getLocationInWindow(outputBuffer);
        outputBuffer[0] = outputBuffer[0] - rootX;
        outputBuffer[1] = outputBuffer[1] - rootY;
        outputBuffer[2] = v.getWidth();
        outputBuffer[3] = v.getHeight();
    }

    public final synchronized void b(int tag, int[] outputBuffer) {
        ap.b();
        View v = (View) this.c.get(tag);
        if (v == null) {
            throw new n("No native view for " + tag + " currently exists");
        }
        v.getLocationOnScreen(outputBuffer);
        Resources resources = v.getContext().getResources();
        int statusBarId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarId > 0) {
            outputBuffer[1] = outputBuffer[1] - ((int) resources.getDimension(statusBarId));
        }
        outputBuffer[2] = v.getWidth();
        outputBuffer[3] = v.getHeight();
    }

    public final synchronized int a(int reactTag, float touchX, float touchY) {
        View view;
        ap.b();
        view = (View) this.c.get(reactTag);
        if (view == null) {
            throw new n("Could not find view with tag " + reactTag);
        }
        return af.a(touchX, touchY, (ViewGroup) view);
    }

    public final synchronized void a(int reactTag, int initialReactTag, boolean blockNativeResponder) {
        if (blockNativeResponder) {
            View view = (View) this.c.get(reactTag);
            if (initialReactTag == reactTag || !(view instanceof ViewParent)) {
                if (this.e.get(reactTag)) {
                    an.a("Cannot block native responder on " + reactTag + " that is a root view");
                }
                this.g.a(initialReactTag, view.getParent());
            } else {
                this.g.a(initialReactTag, (ViewParent) view);
            }
        } else {
            this.g.a(initialReactTag, null);
        }
    }

    public final void b() {
        this.g.a();
    }

    final void a(am config) {
        this.i.a(config);
    }

    final void c() {
        this.i.a();
    }

    final synchronized void a(int reactTag, com.facebook.react.a.a animation, @Nullable final d animationCallback) {
        ap.b();
        View view = (View) this.c.get(reactTag);
        final int animationId = animation.b();
        if (view != null) {
            animation.a(new com.facebook.react.a.b(this) {
                final /* synthetic */ l c;

                public final void a() {
                    com.facebook.infer.annotation.a.a(this.c.b.b(animationId), "Animation was already removed somehow!");
                    if (animationCallback != null) {
                        animationCallback.invoke(Boolean.valueOf(false));
                    }
                }
            });
            animation.a(view);
        } else {
            throw new f("View with tag " + reactTag + " not found");
        }
    }

    public final synchronized void a(int reactTag, int commandId, @Nullable al args) {
        ap.b();
        View view = (View) this.c.get(reactTag);
        if (view == null) {
            throw new f("Trying to send command to a non-existing view with tag " + reactTag);
        }
        c(reactTag).receiveCommand(view, commandId, args);
    }

    public final synchronized void a(int reactTag, al items, d success, d error) {
        ap.b();
        View anchor = (View) this.c.get(reactTag);
        if (anchor == null) {
            error.invoke("Can't display popup. Could not find view with tag " + reactTag);
        } else {
            View view = (View) this.c.get(reactTag);
            if (view == null) {
                throw new n("Could not find view with tag " + reactTag);
            }
            PopupMenu popupMenu = new PopupMenu((ae) view.getContext(), anchor);
            Menu menu = popupMenu.getMenu();
            for (int i = 0; i < items.size(); i++) {
                menu.add(0, 0, i, items.getString(i));
            }
            a handler = new a(success, (byte) 0);
            popupMenu.setOnMenuItemClickListener(handler);
            popupMenu.setOnDismissListener(handler);
            popupMenu.show();
        }
    }

    public final void a(int tag, int eventType) {
        View view = (View) this.c.get(tag);
        if (view == null) {
            throw new n("Could not find view with tag " + tag);
        }
        a.a(view, eventType);
    }
}
