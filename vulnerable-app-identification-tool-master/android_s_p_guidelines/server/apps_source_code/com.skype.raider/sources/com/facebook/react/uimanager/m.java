package com.facebook.react.uimanager;

import android.util.SparseBooleanArray;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.al;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.yoga.YogaOverflow;
import java.util.Locale;
import javax.annotation.Nullable;

public final class m {
    private final al a;
    private final ac b;
    private final SparseBooleanArray c = new SparseBooleanArray();

    private static class a {
        public final w a;
        public final int b;

        a(w node, int index) {
            this.a = node;
            this.b = index;
        }
    }

    public m(al uiViewOperationQueue, ac shadowNodeRegistry) {
        this.a = uiViewOperationQueue;
        this.b = shadowNodeRegistry;
    }

    public final void a(w node, ae themedContext, @Nullable x initialProps) {
        boolean isLayoutOnly = node.t().equals(ReactViewManager.REACT_CLASS) && a(initialProps);
        node.b(isLayoutOnly);
        if (node.M() != k.NONE) {
            this.a.a(themedContext, node.A(), node.t(), initialProps);
        }
    }

    public final void a(w node, x props) {
        Object obj = (!node.L() || a(props)) ? null : 1;
        if (obj != null) {
            b(node, props);
        } else if (!node.L()) {
            this.a.a(node.A(), props);
        }
    }

    public final void a(w nodeToManage, int[] tagsToRemove, am[] viewsToAdd, int[] tagsToDelete) {
        for (int tagToRemove : tagsToRemove) {
            boolean delete = false;
            for (int i : tagsToDelete) {
                if (i == tagToRemove) {
                    delete = true;
                    break;
                }
            }
            a(this.b.c(tagToRemove), delete);
        }
        for (am toAdd : viewsToAdd) {
            a(nodeToManage, this.b.c(toAdd.b), toAdd.c);
        }
    }

    public final void a(w nodeToManage, al childrenTags) {
        for (int i = 0; i < childrenTags.size(); i++) {
            a(nodeToManage, this.b.c(childrenTags.getInt(i)), i);
        }
    }

    public final void a(w node) {
        c(node);
    }

    public final void b(w node) {
        if (node.L()) {
            b(node, null);
        }
    }

    public final void a() {
        this.c.clear();
    }

    private void a(w parent, w child, int index) {
        int indexInNativeChildren = parent.f(parent.c(index));
        if (parent.M() != k.PARENT) {
            a result;
            int i = indexInNativeChildren;
            w wVar = parent;
            while (wVar.M() != k.PARENT) {
                w C = wVar.C();
                if (C == null) {
                    result = null;
                    break;
                } else {
                    i = (i + (wVar.M() == k.LEAF ? 1 : 0)) + C.f(wVar);
                    wVar = C;
                }
            }
            result = new a(wVar, i);
            if (result != null) {
                parent = result.a;
                indexInNativeChildren = result.b;
            } else {
                return;
            }
        }
        if (child.M() != k.NONE) {
            b(parent, child, indexInNativeChildren);
        } else {
            c(parent, child, indexInNativeChildren);
        }
    }

    private void a(w nodeToRemove, boolean shouldDelete) {
        if (nodeToRemove.M() != k.PARENT) {
            for (int i = nodeToRemove.y() - 1; i >= 0; i--) {
                a(nodeToRemove.c(i), shouldDelete);
            }
        }
        w nativeNodeToRemoveFrom = nodeToRemove.K();
        if (nativeNodeToRemoveFrom != null) {
            nativeNodeToRemoveFrom.d(nativeNodeToRemoveFrom.d(nodeToRemove));
            this.a.a(nativeNodeToRemoveFrom.A(), new int[]{nativeNodeToRemoveFrom.d(nodeToRemove)}, null, shouldDelete ? new int[]{nodeToRemove.A()} : null);
        }
    }

    private void b(w parent, w child, int index) {
        parent.b(child, index);
        this.a.a(parent.A(), null, new am[]{new am(child.A(), index)}, null);
        if (child.M() != k.PARENT) {
            c(parent, child, index + 1);
        }
    }

    private void c(w nativeParent, w child, int index) {
        boolean z;
        if (child.M() != k.PARENT) {
            z = true;
        } else {
            z = false;
        }
        com.facebook.infer.annotation.a.a(z);
        int currentIndex = index;
        for (int i = 0; i < child.y(); i++) {
            w grandchild = child.c(i);
            if (grandchild.K() == null) {
                z = true;
            } else {
                z = false;
            }
            com.facebook.infer.annotation.a.a(z);
            int grandchildCountBefore = nativeParent.J();
            if (grandchild.M() == k.NONE) {
                c(nativeParent, grandchild, currentIndex);
            } else {
                b(nativeParent, grandchild, currentIndex);
            }
            currentIndex += nativeParent.J() - grandchildCountBefore;
        }
    }

    private void c(w node) {
        int tag = node.A();
        if (!this.c.get(tag)) {
            this.c.put(tag, true);
            w parent = node.C();
            int x = node.d();
            int y = node.e();
            while (parent != null && parent.M() != k.PARENT) {
                if (!parent.a()) {
                    x += Math.round(parent.N());
                    y += Math.round(parent.O());
                }
                parent = parent.C();
            }
            a(node, x, y);
        }
    }

    private void a(w toUpdate, int x, int y) {
        if (toUpdate.M() == k.NONE || toUpdate.K() == null) {
            for (int i = 0; i < toUpdate.y(); i++) {
                w child = toUpdate.c(i);
                int childTag = child.A();
                if (!this.c.get(childTag)) {
                    this.c.put(childTag, true);
                    a(child, child.d() + x, child.e() + y);
                }
            }
            return;
        }
        this.a.a(toUpdate.D().A(), toUpdate.A(), x, y, toUpdate.f(), toUpdate.g());
    }

    private void b(w node, @Nullable x props) {
        boolean z = false;
        w parent = node.C();
        if (parent == null) {
            node.b(false);
            return;
        }
        int i;
        int childIndex = parent.a(node);
        parent.b(childIndex);
        a(node, false);
        node.b(false);
        this.a.a(node.B().E(), node.A(), node.t(), props);
        parent.a(node, childIndex);
        a(parent, node, childIndex);
        for (i = 0; i < node.y(); i++) {
            a(node, node.c(i), i);
        }
        if (this.c.size() == 0) {
            z = true;
        }
        com.facebook.infer.annotation.a.a(z);
        c(node);
        for (i = 0; i < node.y(); i++) {
            c(node.c(i));
        }
        this.c.clear();
    }

    private static boolean a(@Nullable x props) {
        if (props == null) {
            return true;
        }
        if (props.a("collapsable") && !props.a("collapsable", true)) {
            return false;
        }
        if (props.a("overflow")) {
            String overflow = props.c("overflow");
            if (!(overflow == null || YogaOverflow.valueOf(overflow.toUpperCase(Locale.US)) == YogaOverflow.HIDDEN)) {
                return false;
            }
        }
        ReadableMapKeySetIterator keyIterator = props.a.keySetIterator();
        while (keyIterator.hasNextKey()) {
            if (!ar.a(props.a, keyIterator.nextKey())) {
                return false;
            }
        }
        return true;
    }
}
