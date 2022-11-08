package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.d;
import com.facebook.react.uimanager.events.c;
import com.facebook.systrace.b;
import com.facebook.yoga.YogaDirection;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

public final class ai {
    protected final c a;
    private final ac b;
    private final ap c;
    private final al d;
    private final m e;
    private final int[] f;
    private final ag g;
    private double h;
    private double i;

    public ai(ag reactContext, List<ViewManager> viewManagers, c eventDispatcher) {
        this(reactContext, new ap(viewManagers), eventDispatcher);
    }

    private ai(ag reactContext, ap viewManagers, c eventDispatcher) {
        this(reactContext, viewManagers, new al(reactContext, new l(viewManagers)), eventDispatcher);
    }

    private ai(ag reactContext, ap viewManagers, al operationsQueue, c eventDispatcher) {
        this.b = new ac();
        this.f = new int[4];
        this.h = 0.0d;
        this.i = 0.0d;
        this.g = reactContext;
        this.c = viewManagers;
        this.d = operationsQueue;
        this.e = new m(this.d, this.b);
        this.a = eventDispatcher;
    }

    final al a() {
        return this.d;
    }

    public final void a(int nodeViewTag, int newWidth, int newHeight) {
        w cssNode = this.b.c(nodeViewTag);
        if (cssNode == null) {
            FLog.w("React", "Tried to update size of non-existent tag: " + nodeViewTag);
            return;
        }
        cssNode.a((float) newWidth);
        cssNode.b((float) newHeight);
        if (this.d.b()) {
            c(-1);
        }
    }

    public final double b() {
        return this.h;
    }

    public final double c() {
        return this.i;
    }

    public final void a(int tag, String className, am props) {
        if (this.c.a(className) == null) {
            throw new f("Got unknown view type: " + className);
        }
        w cssNode = this.b.c(tag);
        if (cssNode == null) {
            throw new f("Trying to update non-existent view with tag " + tag);
        } else if (props != null) {
            x styles = new x(props);
            ao.a(cssNode, styles);
            if (!cssNode.a()) {
                this.e.a(cssNode, styles);
            }
        }
    }

    public final void a(int tag, x props) {
        ap.b();
        this.d.a().a(tag, props);
    }

    public final void a(int viewTag, @Nullable al moveFrom, @Nullable al moveTo, @Nullable al addChildTags, @Nullable al addAtIndices, @Nullable al removeFrom) {
        w cssNodeToManage = this.b.c(viewTag);
        int numToMove = moveFrom == null ? 0 : moveFrom.size();
        int numToAdd = addChildTags == null ? 0 : addChildTags.size();
        int numToRemove = removeFrom == null ? 0 : removeFrom.size();
        if (numToMove != 0 && (moveTo == null || numToMove != moveTo.size())) {
            throw new f("Size of moveFrom != size of moveTo!");
        } else if (numToAdd == 0 || (addAtIndices != null && numToAdd == addAtIndices.size())) {
            int i;
            am[] viewsToAdd = new am[(numToMove + numToAdd)];
            int[] indicesToRemove = new int[(numToMove + numToRemove)];
            int[] tagsToRemove = new int[indicesToRemove.length];
            int[] tagsToDelete = new int[numToRemove];
            if (numToMove > 0) {
                a.a((Object) moveFrom);
                a.a((Object) moveTo);
                for (i = 0; i < numToMove; i++) {
                    int moveFromIndex = moveFrom.getInt(i);
                    int tagToMove = cssNodeToManage.c(moveFromIndex).A();
                    viewsToAdd[i] = new am(tagToMove, moveTo.getInt(i));
                    indicesToRemove[i] = moveFromIndex;
                    tagsToRemove[i] = tagToMove;
                }
            }
            if (numToAdd > 0) {
                a.a((Object) addChildTags);
                a.a((Object) addAtIndices);
                for (i = 0; i < numToAdd; i++) {
                    viewsToAdd[numToMove + i] = new am(addChildTags.getInt(i), addAtIndices.getInt(i));
                }
            }
            if (numToRemove > 0) {
                a.a((Object) removeFrom);
                for (i = 0; i < numToRemove; i++) {
                    int indexToRemove = removeFrom.getInt(i);
                    int tagToRemove = cssNodeToManage.c(indexToRemove).A();
                    indicesToRemove[numToMove + i] = indexToRemove;
                    tagsToRemove[numToMove + i] = tagToRemove;
                    tagsToDelete[i] = tagToRemove;
                }
            }
            Arrays.sort(viewsToAdd, am.a);
            Arrays.sort(indicesToRemove);
            int lastIndexRemoved = -1;
            for (i = indicesToRemove.length - 1; i >= 0; i--) {
                if (indicesToRemove[i] == lastIndexRemoved) {
                    throw new f("Repeated indices in Removal list for view tag: " + viewTag);
                }
                cssNodeToManage.b(indicesToRemove[i]);
                lastIndexRemoved = indicesToRemove[i];
            }
            for (am viewAtIndex : viewsToAdd) {
                w cssNodeToAdd = this.b.c(viewAtIndex.b);
                if (cssNodeToAdd == null) {
                    throw new f("Trying to add unknown view tag: " + viewAtIndex.b);
                }
                cssNodeToManage.a(cssNodeToAdd, viewAtIndex.c);
            }
            this.e.a(cssNodeToManage, tagsToRemove, viewsToAdd, tagsToDelete);
            for (int c : tagsToDelete) {
                w c2 = this.b.c(c);
                a(c2);
                c2.X();
            }
        } else {
            throw new f("Size of addChildTags != size of addAtIndices!");
        }
    }

    public final void a(int viewTag, al childrenTags) {
        w cssNodeToManage = this.b.c(viewTag);
        for (int i = 0; i < childrenTags.size(); i++) {
            w cssNodeToAdd = this.b.c(childrenTags.getInt(i));
            if (cssNodeToAdd == null) {
                throw new f("Trying to add unknown view tag: " + childrenTags.getInt(i));
            }
            cssNodeToManage.a(cssNodeToAdd, i);
        }
        this.e.a(cssNodeToManage, childrenTags);
    }

    public final void a(int oldTag, int newTag) {
        if (this.b.d(oldTag) || this.b.d(newTag)) {
            throw new f("Trying to add or replace a root tag!");
        }
        w oldNode = this.b.c(oldTag);
        if (oldNode == null) {
            throw new f("Trying to replace unknown view tag: " + oldTag);
        }
        w parent = oldNode.C();
        if (parent == null) {
            throw new f("Node is not attached to a parent: " + oldTag);
        }
        int oldIndex = parent.a(oldNode);
        if (oldIndex < 0) {
            throw new IllegalStateException("Didn't find child tag in parent");
        }
        aq tagsToAdd = new WritableNativeArray();
        tagsToAdd.pushInt(newTag);
        aq addAtIndices = new WritableNativeArray();
        addAtIndices.pushInt(oldIndex);
        aq indicesToRemove = new WritableNativeArray();
        indicesToRemove.pushInt(oldIndex);
        a(parent.A(), null, null, tagsToAdd, addAtIndices, indicesToRemove);
    }

    public final void b(int containerTag) {
        w containerNode = this.b.c(containerTag);
        if (containerNode == null) {
            throw new f("Trying to remove subviews of an unknown view tag: " + containerTag);
        }
        aq indicesToRemove = new WritableNativeArray();
        for (int childIndex = 0; childIndex < containerNode.y(); childIndex++) {
            indicesToRemove.pushInt(childIndex);
        }
        a(containerTag, null, null, null, null, indicesToRemove);
    }

    public final void a(int reactTag, float targetX, float targetY, d callback) {
        this.d.a(reactTag, targetX, targetY, callback);
    }

    public final void a(int reactTag, int ancestorReactTag, d callback) {
        w node = this.b.c(reactTag);
        w ancestorNode = this.b.c(ancestorReactTag);
        if (node == null || ancestorNode == null) {
            callback.invoke(Boolean.valueOf(false));
            return;
        }
        callback.invoke(Boolean.valueOf(node.e(ancestorNode)));
    }

    public final void a(int reactTag, d callback) {
        this.d.a(reactTag, callback);
    }

    public final void b(int reactTag, d callback) {
        this.d.b(reactTag, callback);
    }

    public final void a(int tag, int ancestorTag, d errorCallback, d successCallback) {
        try {
            int[] iArr = this.f;
            w c = this.b.c(tag);
            w c2 = this.b.c(ancestorTag);
            if (c == null || c2 == null) {
                StringBuilder stringBuilder = new StringBuilder("Tag ");
                if (c != null) {
                    tag = ancestorTag;
                }
                throw new f(stringBuilder.append(tag).append(" does not exist").toString());
            }
            if (c != c2) {
                for (w C = c.C(); C != c2; C = C.C()) {
                    if (C == null) {
                        throw new f("Tag " + ancestorTag + " is not an ancestor of tag " + tag);
                    }
                }
            }
            a(c, c2, iArr);
            float relativeX = p.b((float) this.f[0]);
            float relativeY = p.b((float) this.f[1]);
            float width = p.b((float) this.f[2]);
            float height = p.b((float) this.f[3]);
            successCallback.invoke(Float.valueOf(relativeX), Float.valueOf(relativeY), Float.valueOf(width), Float.valueOf(height));
        } catch (f e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    public final void a(int tag, d errorCallback, d successCallback) {
        try {
            int[] iArr = this.f;
            w c = this.b.c(tag);
            if (c == null) {
                throw new f("No native view for tag " + tag + " exists!");
            }
            w C = c.C();
            if (C == null) {
                throw new f("View with tag " + tag + " doesn't have a parent!");
            }
            a(c, C, iArr);
            float relativeX = p.b((float) this.f[0]);
            float relativeY = p.b((float) this.f[1]);
            float width = p.b((float) this.f[2]);
            float height = p.b((float) this.f[3]);
            successCallback.invoke(Float.valueOf(relativeX), Float.valueOf(relativeY), Float.valueOf(width), Float.valueOf(height));
        } catch (f e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    public final void c(int batchId) {
        double nanoTime;
        int i = 0;
        b.a();
        try {
            com.facebook.systrace.a.a("UIImplementation.updateViewHierarchy");
            int a = this.b.a();
            int i2 = 0;
            while (i2 < a) {
                w c = this.b.c(this.b.e(i2));
                b.a();
                c.A();
                try {
                    c(c);
                    com.facebook.systrace.a.a();
                    i2++;
                } catch (Throwable th) {
                    com.facebook.systrace.a.a();
                    throw th;
                }
            }
            while (i < a) {
                w c2 = this.b.c(this.b.e(i));
                b.a();
                c2.A();
                nanoTime = (double) System.nanoTime();
                c2.G();
                com.facebook.systrace.a.a();
                this.i = ((((double) System.nanoTime()) - nanoTime) / 1000000.0d) + this.i;
                this.h += 1.0d;
                b.a();
                c2.A();
                a(c2, 0.0f, 0.0f);
                com.facebook.systrace.a.a();
                i++;
            }
            com.facebook.systrace.a.a();
            this.e.a();
            this.d.c(batchId);
            com.facebook.systrace.a.a();
        } catch (Throwable th2) {
            com.facebook.systrace.a.a();
            throw th2;
        }
    }

    public final void a(com.facebook.react.a.a animation) {
        this.d.a(animation);
    }

    public final void b(int reactTag, int animationID, d onSuccess) {
        a(reactTag, "addAnimation");
        this.d.a(reactTag, animationID, onSuccess);
    }

    public final void b(int reactTag, int animationID) {
        a(reactTag, "removeAnimation");
        this.d.b(animationID);
    }

    public final void a(boolean enabled) {
        this.d.a(enabled);
    }

    public final void a(am config) {
        this.d.a(config);
    }

    public final void a(int reactTag, boolean blockNativeResponder) {
        a(reactTag, "setJSResponder");
        w node = this.b.c(reactTag);
        while (node.M() == k.NONE) {
            node = node.C();
        }
        this.d.a(node.A(), reactTag, blockNativeResponder);
    }

    public final void d() {
        this.d.c();
    }

    public final void a(int reactTag, int commandId, al commandArgs) {
        a(reactTag, "dispatchViewManagerCommand");
        this.d.a(reactTag, commandId, commandArgs);
    }

    public final void a(int reactTag, al items, d error, d success) {
        a(reactTag, "showPopupMenu");
        this.d.a(reactTag, items, error, success);
    }

    public final void c(int tag, int eventType) {
        this.d.a(tag, eventType);
    }

    public final void e() {
        this.d.d();
    }

    public final void f() {
        this.d.e();
    }

    public final void g() {
        for (String clazz : this.c.a()) {
            this.c.a(clazz).onHostDestroy();
        }
    }

    public final void a(@Nullable com.facebook.react.uimanager.debug.a listener) {
        this.d.a(listener);
    }

    private void a(w node, w ancestor, int[] outputBuffer) {
        int offsetX = 0;
        int offsetY = 0;
        if (node != ancestor) {
            offsetX = Math.round(node.N());
            offsetY = Math.round(node.O());
            for (w current = node.C(); current != ancestor; current = current.C()) {
                a.a((Object) current);
                b(current);
                offsetX += Math.round(current.N());
                offsetY += Math.round(current.O());
            }
            b(ancestor);
        }
        outputBuffer[0] = offsetX;
        outputBuffer[1] = offsetY;
        outputBuffer[2] = node.f();
        outputBuffer[3] = node.g();
    }

    private void a(int reactTag, String operationNameForExceptionMessage) {
        if (this.b.c(reactTag) == null) {
            throw new f("Unable to execute operation " + operationNameForExceptionMessage + " on view with tag: " + reactTag + ", since the view does not exists");
        }
    }

    private void b(w node) {
        ViewManager viewManager = (ViewManager) a.a(this.c.a(node.t()));
        if (viewManager instanceof e) {
            e viewManagerWithChildren = (e) viewManager;
            if (viewManagerWithChildren != null && viewManagerWithChildren.needsCustomLayoutForChildren()) {
                throw new f("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (" + node.t() + "). Use measure instead.");
            }
            return;
        }
        throw new f("Trying to use view " + node.t() + " as a parent, but its Manager doesn't extends ViewGroupManager");
    }

    private void c(w cssNode) {
        if (cssNode.u()) {
            for (int i = 0; i < cssNode.y(); i++) {
                c(cssNode.c(i));
            }
            cssNode.a(this.e);
        }
    }

    private void a(w cssNode, float absoluteX, float absoluteY) {
        if (cssNode.u()) {
            for (w cssChild : cssNode.Y()) {
                a(cssChild, cssNode.N() + absoluteX, cssNode.O() + absoluteY);
            }
            int tag = cssNode.A();
            if (!this.b.d(tag)) {
                if (cssNode.a(absoluteX, absoluteY, this.c.a(cssNode.t()), this.d, this.e) && cssNode.F()) {
                    this.a.a(o.a(tag, cssNode.d(), cssNode.e(), cssNode.f(), cssNode.g()));
                }
            }
            cssNode.v();
        }
    }

    public final void a(ah block) {
        this.d.a(block);
    }

    public final int d(int reactTag) {
        if (this.b.d(reactTag)) {
            return reactTag;
        }
        w node = this.b.c(reactTag);
        int rootTag = 0;
        if (node != null) {
            rootTag = node.B().A();
        } else {
            FLog.w("React", "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + reactTag);
        }
        return rootTag;
    }

    public final void a(SizeMonitoringFrameLayout rootView, int tag, int width, int height, ae context) {
        w rootCSSNode = new w();
        com.facebook.react.modules.i18nmanager.a.a();
        if (com.facebook.react.modules.i18nmanager.a.a(this.g)) {
            rootCSSNode.a(YogaDirection.RTL);
        }
        rootCSSNode.a("Root");
        rootCSSNode.a(tag);
        rootCSSNode.a(context);
        rootCSSNode.a((float) width);
        rootCSSNode.b((float) height);
        this.b.a(rootCSSNode);
        this.d.a(tag, rootView);
    }

    public final void a(int rootViewTag) {
        this.b.a(rootViewTag);
        this.d.a(rootViewTag);
    }

    public final void a(int tag, String className, int rootViewTag, am props) {
        w cssNode = this.c.a(className).createShadowNodeInstance();
        w rootNode = this.b.c(rootViewTag);
        cssNode.a(tag);
        cssNode.a(className);
        cssNode.b(rootNode);
        cssNode.a(rootNode.E());
        this.b.b(cssNode);
        x styles = null;
        if (props != null) {
            styles = new x(props);
            ao.a(cssNode, styles);
        }
        if (!cssNode.a()) {
            this.e.a(cssNode, cssNode.E(), styles);
        }
    }

    private void a(w nodeToRemove) {
        nodeToRemove.I();
        this.b.b(nodeToRemove.A());
        for (int i = nodeToRemove.y() - 1; i >= 0; i--) {
            a(nodeToRemove.c(i));
        }
        nodeToRemove.z();
    }
}
