package com.facebook.react.uimanager;

import com.facebook.infer.annotation.a;
import com.facebook.react.uimanager.annotations.ReactPropertyHolder;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaValue;
import com.facebook.yoga.YogaWrap;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nullable;

@ReactPropertyHolder
public class w {
    public static final Iterable<w> e = new ArrayList(0);
    private int a;
    @Nullable
    private String b;
    @Nullable
    private w c;
    @Nullable
    private ae d;
    private boolean f;
    private boolean g = true;
    @Nullable
    private ArrayList<w> h;
    @Nullable
    private w i;
    @Nullable
    private w j;
    private boolean k;
    private int l = 0;
    @Nullable
    private w m;
    @Nullable
    private ArrayList<w> n;
    private int o;
    private int p;
    private int q;
    private int r;
    private final ad s = new ad(0.0f);
    private final float[] t = new float[9];
    private final boolean[] u = new boolean[9];
    private final YogaNode v;

    public w() {
        if (a()) {
            this.v = null;
            return;
        }
        YogaNode node = (YogaNode) as.a().a();
        if (node == null) {
            node = new YogaNode(y.a());
        }
        this.v = node;
        Arrays.fill(this.t, Float.NaN);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean s() {
        return false;
    }

    public final String t() {
        return (String) a.a(this.b);
    }

    public final boolean u() {
        if (!(this.g || H())) {
            boolean z;
            if (this.v == null || !this.v.isDirty()) {
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final void v() {
        this.g = false;
        if (H() && this.v != null) {
            this.v.markLayoutSeen();
        }
    }

    public void i() {
        if (!this.g) {
            this.g = true;
            w parent = this.i;
            if (parent != null) {
                parent.i();
            }
        }
    }

    public final boolean w() {
        return this.g;
    }

    public final void x() {
        while (a()) {
            if (this.i != null) {
                this = this.i;
            } else {
                return;
            }
        }
        this.v.dirty();
    }

    public void a(w child, int i) {
        if (child.i != null) {
            throw new f("Tried to add child that already has a parent! Remove it from its parent first.");
        }
        if (this.h == null) {
            this.h = new ArrayList(4);
        }
        this.h.add(i, child);
        child.i = this;
        if (!(this.v == null || this.v.isMeasureDefined())) {
            YogaNode childYogaNode = child.v;
            if (childYogaNode == null) {
                throw new RuntimeException("Cannot add a child that doesn't have a YogaNode to a parent without a measure function! (Trying to add a '" + child.getClass().getSimpleName() + "' to a '" + getClass().getSimpleName() + "')");
            }
            this.v.addChildAt(childYogaNode, i);
        }
        i();
        int increase = child.c();
        this.l += increase;
        i(increase);
    }

    public final w b(int i) {
        if (this.h == null) {
            throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds: node has no children");
        }
        w removed = (w) this.h.remove(i);
        removed.i = null;
        if (!(this.v == null || this.v.isMeasureDefined())) {
            this.v.removeChildAt(i);
        }
        i();
        int decrease = removed.c();
        this.l -= decrease;
        i(-decrease);
        return removed;
    }

    public final int y() {
        return this.h == null ? 0 : this.h.size();
    }

    public final w c(int i) {
        if (this.h != null) {
            return (w) this.h.get(i);
        }
        throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds: node has no children");
    }

    public final int a(w child) {
        return this.h == null ? -1 : this.h.indexOf(child);
    }

    public final void z() {
        if (y() != 0) {
            int decrease = 0;
            for (int i = y() - 1; i >= 0; i--) {
                if (!(this.v == null || this.v.isMeasureDefined())) {
                    this.v.removeChildAt(i);
                }
                w toRemove = c(i);
                toRemove.i = null;
                decrease += toRemove.c();
                toRemove.X();
            }
            ((ArrayList) a.a(this.h)).clear();
            i();
            this.l -= decrease;
            i(-decrease);
        }
    }

    private void i(int delta) {
        if (M() != k.PARENT) {
            w parent = this.i;
            while (parent != null) {
                parent.l += delta;
                if (parent.M() != k.PARENT) {
                    parent = parent.i;
                } else {
                    return;
                }
            }
        }
    }

    public void a(m nativeViewHierarchyOptimizer) {
    }

    public void a(al uiViewOperationQueue) {
    }

    final boolean a(float absoluteX, float absoluteY, ViewManager viewManager, al uiViewOperationQueue, m nativeViewHierarchyOptimizer) {
        if (this.g) {
            a(uiViewOperationQueue);
        }
        if (!H()) {
            return false;
        }
        float layoutX = this.v.getLayoutX();
        float layoutY = this.v.getLayoutY();
        int newAbsoluteLeft = Math.round(absoluteX + layoutX);
        int newAbsoluteTop = Math.round(absoluteY + layoutY);
        int newAbsoluteRight = Math.round((absoluteX + layoutX) + this.v.getLayoutWidth());
        int newAbsoluteBottom = Math.round((absoluteY + layoutY) + this.v.getLayoutHeight());
        int newScreenX = Math.round(layoutX);
        int newScreenY = Math.round(layoutY);
        int newScreenWidth = newAbsoluteRight - newAbsoluteLeft;
        int newScreenHeight = newAbsoluteBottom - newAbsoluteTop;
        boolean layoutHasChanged = (newScreenX == this.o && newScreenY == this.p && newScreenWidth == this.q && newScreenHeight == this.r) ? false : true;
        this.o = newScreenX;
        this.p = newScreenY;
        this.q = newScreenWidth;
        this.r = newScreenHeight;
        boolean needsCustomLayout = false;
        if (!layoutHasChanged && (viewManager instanceof e)) {
            needsCustomLayout = ((e) viewManager).needsCustomLayoutForChildren();
        }
        if (layoutHasChanged || needsCustomLayout) {
            nativeViewHierarchyOptimizer.a(this);
        }
        return layoutHasChanged || needsCustomLayout;
    }

    public final int A() {
        return this.a;
    }

    public void a(int reactTag) {
        this.a = reactTag;
    }

    public final w B() {
        return (w) a.a(this.c);
    }

    final void b(w rootNode) {
        this.c = rootNode;
    }

    final void a(String viewClassName) {
        this.b = viewClassName;
    }

    @Nullable
    public final w C() {
        return this.i;
    }

    @Nullable
    public final w D() {
        return this.j != null ? this.j : this.m;
    }

    public final void c(@Nullable w layoutParent) {
        this.j = layoutParent;
    }

    public final ae E() {
        return (ae) a.a(this.d);
    }

    public void a(ae themedContext) {
        this.d = themedContext;
    }

    public final boolean F() {
        return this.f;
    }

    public final void G() {
        this.v.calculateLayout(Float.NaN, Float.NaN);
    }

    public final boolean H() {
        return this.v != null && this.v.hasNewLayout();
    }

    public final void b(w child, int nativeIndex) {
        boolean z = true;
        a.a(M() == k.PARENT);
        if (child.M() == k.NONE) {
            z = false;
        }
        a.a(z);
        if (this.n == null) {
            this.n = new ArrayList(4);
        }
        this.n.add(nativeIndex, child);
        child.m = this;
    }

    public final w d(int i) {
        a.a(this.n);
        w removed = (w) this.n.remove(i);
        removed.m = null;
        return removed;
    }

    public final void I() {
        if (this.n != null) {
            for (int i = this.n.size() - 1; i >= 0; i--) {
                ((w) this.n.get(i)).m = null;
            }
            this.n.clear();
        }
    }

    public final int J() {
        return this.n == null ? 0 : this.n.size();
    }

    public final int d(w nativeChild) {
        a.a(this.n);
        return this.n.indexOf(nativeChild);
    }

    @Nullable
    public final w K() {
        return this.m;
    }

    public final void b(boolean isLayoutOnly) {
        boolean z;
        boolean z2 = true;
        if (this.i == null) {
            z = true;
        } else {
            z = false;
        }
        a.a(z, "Must remove from no opt parent first");
        if (this.m == null) {
            z = true;
        } else {
            z = false;
        }
        a.a(z, "Must remove from native parent first");
        if (J() != 0) {
            z2 = false;
        }
        a.a(z2, "Must remove all native children first");
        this.k = isLayoutOnly;
    }

    public final boolean L() {
        return this.k;
    }

    public final k M() {
        if (a() || this.k) {
            return k.NONE;
        }
        return s() ? k.LEAF : k.PARENT;
    }

    private int c() {
        k kind = M();
        if (kind == k.NONE) {
            return this.l;
        }
        return kind == k.LEAF ? this.l + 1 : 1;
    }

    public final int f(w child) {
        int index = 0;
        boolean found = false;
        for (int i = 0; i < y(); i++) {
            w current = c(i);
            if (child == current) {
                found = true;
                break;
            }
            index += current.c();
        }
        if (found) {
            return index;
        }
        throw new RuntimeException("Child " + child.a + " was not a child of " + this.a);
    }

    public final float N() {
        return this.v.getLayoutX();
    }

    public final float O() {
        return this.v.getLayoutY();
    }

    public final float P() {
        return this.v.getLayoutWidth();
    }

    public final float Q() {
        return this.v.getLayoutHeight();
    }

    public int d() {
        return this.o;
    }

    public int e() {
        return this.p;
    }

    public int f() {
        return this.q;
    }

    public int g() {
        return this.r;
    }

    public final YogaDirection R() {
        return this.v.getLayoutDirection();
    }

    public final void a(YogaDirection direction) {
        this.v.setDirection(direction);
    }

    public final YogaValue S() {
        return this.v.getWidth();
    }

    public void a(float widthPx) {
        this.v.setWidth(widthPx);
    }

    public final void d(float percent) {
        this.v.setWidthPercent(percent);
    }

    public final void T() {
        this.v.setWidthAuto();
    }

    public final void e(float widthPx) {
        this.v.setMinWidth(widthPx);
    }

    public final void f(float percent) {
        this.v.setMinWidthPercent(percent);
    }

    public final void g(float widthPx) {
        this.v.setMaxWidth(widthPx);
    }

    public final void h(float percent) {
        this.v.setMaxWidthPercent(percent);
    }

    public final YogaValue U() {
        return this.v.getHeight();
    }

    public void b(float heightPx) {
        this.v.setHeight(heightPx);
    }

    public final void i(float percent) {
        this.v.setHeightPercent(percent);
    }

    public final void V() {
        this.v.setHeightAuto();
    }

    public final void j(float widthPx) {
        this.v.setMinHeight(widthPx);
    }

    public final void k(float percent) {
        this.v.setMinHeightPercent(percent);
    }

    public final void l(float widthPx) {
        this.v.setMaxHeight(widthPx);
    }

    public final void m(float percent) {
        this.v.setMaxHeightPercent(percent);
    }

    public void setFlex(float flex) {
        this.v.setFlex(flex);
    }

    public void setFlexGrow(float flexGrow) {
        this.v.setFlexGrow(flexGrow);
    }

    public void setFlexShrink(float flexShrink) {
        this.v.setFlexShrink(flexShrink);
    }

    public final void n(float flexBasis) {
        this.v.setFlexBasis(flexBasis);
    }

    public final void W() {
        this.v.setFlexBasisAuto();
    }

    public final void o(float percent) {
        this.v.setFlexBasisPercent(percent);
    }

    public final void p(float aspectRatio) {
        this.v.setAspectRatio(aspectRatio);
    }

    public final void a(YogaFlexDirection flexDirection) {
        this.v.setFlexDirection(flexDirection);
    }

    public final void a(YogaWrap wrap) {
        this.v.setWrap(wrap);
    }

    public final void a(YogaAlign alignSelf) {
        this.v.setAlignSelf(alignSelf);
    }

    public final void b(YogaAlign alignItems) {
        this.v.setAlignItems(alignItems);
    }

    public final void c(YogaAlign alignContent) {
        this.v.setAlignContent(alignContent);
    }

    public final void a(YogaJustify justifyContent) {
        this.v.setJustifyContent(justifyContent);
    }

    public final void a(YogaOverflow overflow) {
        this.v.setOverflow(overflow);
    }

    public final YogaValue e(int spacingType) {
        return this.v.getMargin(YogaEdge.fromInt(spacingType));
    }

    public final void a(YogaDisplay display) {
        this.v.setDisplay(display);
    }

    public final void d(int spacingType, float margin) {
        this.v.setMargin(YogaEdge.fromInt(spacingType), margin);
    }

    public final void e(int spacingType, float percent) {
        this.v.setMarginPercent(YogaEdge.fromInt(spacingType), percent);
    }

    public final void f(int spacingType) {
        this.v.setMarginAuto(YogaEdge.fromInt(spacingType));
    }

    public final float g(int spacingType) {
        return this.v.getLayoutPadding(YogaEdge.fromInt(spacingType));
    }

    public final YogaValue h(int spacingType) {
        return this.v.getPadding(YogaEdge.fromInt(spacingType));
    }

    public final void f(int spacingType, float padding) {
        this.s.a(spacingType, padding);
        h();
    }

    public void a(int spacingType, float padding) {
        this.t[spacingType] = padding;
        this.u[spacingType] = false;
        h();
    }

    public void b(int spacingType, float percent) {
        this.t[spacingType] = percent;
        this.u[spacingType] = !com.facebook.yoga.a.a(percent);
        h();
    }

    private void h() {
        int spacingType = 0;
        while (spacingType <= 8) {
            if (spacingType == 0 || spacingType == 2 || spacingType == 4 || spacingType == 5) {
                if (com.facebook.yoga.a.a(this.t[spacingType]) && com.facebook.yoga.a.a(this.t[6]) && com.facebook.yoga.a.a(this.t[8])) {
                    this.v.setPadding(YogaEdge.fromInt(spacingType), this.s.b(spacingType));
                }
                if (this.u[spacingType]) {
                    this.v.setPaddingPercent(YogaEdge.fromInt(spacingType), this.t[spacingType]);
                } else {
                    this.v.setPadding(YogaEdge.fromInt(spacingType), this.t[spacingType]);
                }
            } else if (spacingType == 1 || spacingType == 3) {
                if (com.facebook.yoga.a.a(this.t[spacingType]) && com.facebook.yoga.a.a(this.t[7]) && com.facebook.yoga.a.a(this.t[8])) {
                    this.v.setPadding(YogaEdge.fromInt(spacingType), this.s.b(spacingType));
                }
                if (this.u[spacingType]) {
                    this.v.setPadding(YogaEdge.fromInt(spacingType), this.t[spacingType]);
                } else {
                    this.v.setPaddingPercent(YogaEdge.fromInt(spacingType), this.t[spacingType]);
                }
            } else {
                if (com.facebook.yoga.a.a(this.t[spacingType])) {
                    this.v.setPadding(YogaEdge.fromInt(spacingType), this.s.b(spacingType));
                }
                if (this.u[spacingType]) {
                    this.v.setPaddingPercent(YogaEdge.fromInt(spacingType), this.t[spacingType]);
                } else {
                    this.v.setPadding(YogaEdge.fromInt(spacingType), this.t[spacingType]);
                }
            }
            spacingType++;
        }
    }

    public void c(int spacingType, float borderWidth) {
        this.v.setBorder(YogaEdge.fromInt(spacingType), borderWidth);
    }

    public final void g(int spacingType, float position) {
        this.v.setPosition(YogaEdge.fromInt(spacingType), position);
    }

    public final void h(int spacingType, float percent) {
        this.v.setPositionPercent(YogaEdge.fromInt(spacingType), percent);
    }

    public final void a(YogaPositionType positionType) {
        this.v.setPositionType(positionType);
    }

    public void setShouldNotifyOnLayout(boolean shouldNotifyOnLayout) {
        this.f = shouldNotifyOnLayout;
    }

    public final void a(YogaMeasureFunction measureFunction) {
        if (((measureFunction == null ? 1 : 0) ^ this.v.isMeasureDefined()) == 0 || y() == 0) {
            this.v.setMeasureFunction(measureFunction);
            return;
        }
        throw new RuntimeException("Since a node with a measure function does not add any native yoga children, it's not safe to transition to/from having a measure function unless a node has no children");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        a(sb, 0);
        return sb.toString();
    }

    private void a(StringBuilder result, int level) {
        int i;
        for (i = 0; i < level; i++) {
            result.append("__");
        }
        result.append(getClass().getSimpleName()).append(" ");
        if (this.v != null) {
            result.append(this.v.getLayoutWidth()).append(",").append(this.v.getLayoutHeight());
        } else {
            result.append("(virtual node)");
        }
        result.append("\n");
        if (y() != 0) {
            for (i = 0; i < y(); i++) {
                c(i).a(result, level + 1);
            }
        }
    }

    public final void X() {
        if (this.v != null) {
            this.v.reset();
            as.a().a(this.v);
        }
    }

    public Iterable<w> Y() {
        if (b()) {
            return e;
        }
        return this.h == null ? e : this.h;
    }

    public final boolean e(w ancestorNode) {
        for (w parentNode = this.i; parentNode != null; parentNode = parentNode.i) {
            if (parentNode == ancestorNode) {
                return true;
            }
        }
        return false;
    }
}
