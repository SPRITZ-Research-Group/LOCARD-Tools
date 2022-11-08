package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class b {
    private static final int[] a = new int[]{0, 4, 8};
    private static SparseIntArray c;
    private HashMap<Integer, c> b = new HashMap();

    private static String b(int i) {
        switch (i) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        c = sparseIntArray;
        sparseIntArray.append(f.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
        c.append(f.ConstraintSet_layout_constraintLeft_toRightOf, 26);
        c.append(f.ConstraintSet_layout_constraintRight_toLeftOf, 29);
        c.append(f.ConstraintSet_layout_constraintRight_toRightOf, 30);
        c.append(f.ConstraintSet_layout_constraintTop_toTopOf, 36);
        c.append(f.ConstraintSet_layout_constraintTop_toBottomOf, 35);
        c.append(f.ConstraintSet_layout_constraintBottom_toTopOf, 4);
        c.append(f.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
        c.append(f.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
        c.append(f.ConstraintSet_layout_editor_absoluteX, 6);
        c.append(f.ConstraintSet_layout_editor_absoluteY, 7);
        c.append(f.ConstraintSet_layout_constraintGuide_begin, 17);
        c.append(f.ConstraintSet_layout_constraintGuide_end, 18);
        c.append(f.ConstraintSet_layout_constraintGuide_percent, 19);
        c.append(f.ConstraintSet_android_orientation, 27);
        c.append(f.ConstraintSet_layout_constraintStart_toEndOf, 32);
        c.append(f.ConstraintSet_layout_constraintStart_toStartOf, 33);
        c.append(f.ConstraintSet_layout_constraintEnd_toStartOf, 10);
        c.append(f.ConstraintSet_layout_constraintEnd_toEndOf, 9);
        c.append(f.ConstraintSet_layout_goneMarginLeft, 13);
        c.append(f.ConstraintSet_layout_goneMarginTop, 16);
        c.append(f.ConstraintSet_layout_goneMarginRight, 14);
        c.append(f.ConstraintSet_layout_goneMarginBottom, 11);
        c.append(f.ConstraintSet_layout_goneMarginStart, 15);
        c.append(f.ConstraintSet_layout_goneMarginEnd, 12);
        c.append(f.ConstraintSet_layout_constraintVertical_weight, 40);
        c.append(f.ConstraintSet_layout_constraintHorizontal_weight, 39);
        c.append(f.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
        c.append(f.ConstraintSet_layout_constraintVertical_chainStyle, 42);
        c.append(f.ConstraintSet_layout_constraintHorizontal_bias, 20);
        c.append(f.ConstraintSet_layout_constraintVertical_bias, 37);
        c.append(f.ConstraintSet_layout_constraintDimensionRatio, 5);
        c.append(f.ConstraintSet_layout_constraintLeft_creator, 75);
        c.append(f.ConstraintSet_layout_constraintTop_creator, 75);
        c.append(f.ConstraintSet_layout_constraintRight_creator, 75);
        c.append(f.ConstraintSet_layout_constraintBottom_creator, 75);
        c.append(f.ConstraintSet_layout_constraintBaseline_creator, 75);
        c.append(f.ConstraintSet_android_layout_marginLeft, 24);
        c.append(f.ConstraintSet_android_layout_marginRight, 28);
        c.append(f.ConstraintSet_android_layout_marginStart, 31);
        c.append(f.ConstraintSet_android_layout_marginEnd, 8);
        c.append(f.ConstraintSet_android_layout_marginTop, 34);
        c.append(f.ConstraintSet_android_layout_marginBottom, 2);
        c.append(f.ConstraintSet_android_layout_width, 23);
        c.append(f.ConstraintSet_android_layout_height, 21);
        c.append(f.ConstraintSet_android_visibility, 22);
        c.append(f.ConstraintSet_android_alpha, 43);
        c.append(f.ConstraintSet_android_elevation, 44);
        c.append(f.ConstraintSet_android_rotationX, 45);
        c.append(f.ConstraintSet_android_rotationY, 46);
        c.append(f.ConstraintSet_android_rotation, 60);
        c.append(f.ConstraintSet_android_scaleX, 47);
        c.append(f.ConstraintSet_android_scaleY, 48);
        c.append(f.ConstraintSet_android_transformPivotX, 49);
        c.append(f.ConstraintSet_android_transformPivotY, 50);
        c.append(f.ConstraintSet_android_translationX, 51);
        c.append(f.ConstraintSet_android_translationY, 52);
        c.append(f.ConstraintSet_android_translationZ, 53);
        c.append(f.ConstraintSet_layout_constraintWidth_default, 54);
        c.append(f.ConstraintSet_layout_constraintHeight_default, 55);
        c.append(f.ConstraintSet_layout_constraintWidth_max, 56);
        c.append(f.ConstraintSet_layout_constraintHeight_max, 57);
        c.append(f.ConstraintSet_layout_constraintWidth_min, 58);
        c.append(f.ConstraintSet_layout_constraintHeight_min, 59);
        c.append(f.ConstraintSet_layout_constraintCircle, 61);
        c.append(f.ConstraintSet_layout_constraintCircleRadius, 62);
        c.append(f.ConstraintSet_layout_constraintCircleAngle, 63);
        c.append(f.ConstraintSet_android_id, 38);
        c.append(f.ConstraintSet_layout_constraintWidth_percent, 69);
        c.append(f.ConstraintSet_layout_constraintHeight_percent, 70);
        c.append(f.ConstraintSet_chainUseRtl, 71);
        c.append(f.ConstraintSet_barrierDirection, 72);
        c.append(f.ConstraintSet_constraint_referenced_ids, 73);
        c.append(f.ConstraintSet_barrierAllowsGoneWidgets, 74);
    }

    public final void a(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.b.clear();
        int i = 0;
        while (i < childCount) {
            View childAt = constraintLayout.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (id != -1) {
                if (!this.b.containsKey(Integer.valueOf(id))) {
                    this.b.put(Integer.valueOf(id), new c());
                }
                c cVar = (c) this.b.get(Integer.valueOf(id));
                cVar.a(id, layoutParams);
                cVar.J = childAt.getVisibility();
                if (VERSION.SDK_INT >= 17) {
                    cVar.U = childAt.getAlpha();
                    cVar.X = childAt.getRotation();
                    cVar.Y = childAt.getRotationX();
                    cVar.Z = childAt.getRotationY();
                    cVar.aa = childAt.getScaleX();
                    cVar.ab = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (!(((double) pivotX) == 0.0d && ((double) pivotY) == 0.0d)) {
                        cVar.ac = pivotX;
                        cVar.ad = pivotY;
                    }
                    cVar.ae = childAt.getTranslationX();
                    cVar.af = childAt.getTranslationY();
                    if (VERSION.SDK_INT >= 21) {
                        cVar.ag = childAt.getTranslationZ();
                        if (cVar.V) {
                            cVar.W = childAt.getElevation();
                        }
                    }
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    cVar.ar = barrier.b();
                    cVar.au = barrier.c();
                    cVar.as = barrier.a();
                }
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public final void a(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.b.clear();
        int i = 0;
        while (i < childCount) {
            View childAt = constraints.getChildAt(i);
            Constraints.LayoutParams layoutParams = (Constraints.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (id != -1) {
                if (!this.b.containsKey(Integer.valueOf(id))) {
                    this.b.put(Integer.valueOf(id), new c());
                }
                c cVar = (c) this.b.get(Integer.valueOf(id));
                if (childAt instanceof ConstraintHelper) {
                    c.a(cVar, (ConstraintHelper) childAt, id, layoutParams);
                }
                cVar.a(id, layoutParams);
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public final void b(ConstraintLayout constraintLayout) {
        c(constraintLayout);
        constraintLayout.setConstraintSet(null);
    }

    final void c(ConstraintLayout constraintLayout) {
        View childAt;
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.b.keySet());
        int i = 0;
        while (i < childCount) {
            childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (id != -1) {
                if (this.b.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    c cVar = (c) this.b.get(Integer.valueOf(id));
                    if (childAt instanceof Barrier) {
                        cVar.at = 1;
                    }
                    if (cVar.at != -1 && cVar.at == 1) {
                        View view = (Barrier) childAt;
                        view.setId(id);
                        view.setType(cVar.as);
                        view.setAllowsGoneWidget(cVar.ar);
                        if (cVar.au != null) {
                            view.setReferencedIds(cVar.au);
                        } else if (cVar.av != null) {
                            cVar.au = a(view, cVar.av);
                            view.setReferencedIds(cVar.au);
                        }
                    }
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    cVar.a(layoutParams);
                    childAt.setLayoutParams(layoutParams);
                    childAt.setVisibility(cVar.J);
                    if (VERSION.SDK_INT >= 17) {
                        childAt.setAlpha(cVar.U);
                        childAt.setRotation(cVar.X);
                        childAt.setRotationX(cVar.Y);
                        childAt.setRotationY(cVar.Z);
                        childAt.setScaleX(cVar.aa);
                        childAt.setScaleY(cVar.ab);
                        if (!Float.isNaN(cVar.ac)) {
                            childAt.setPivotX(cVar.ac);
                        }
                        if (!Float.isNaN(cVar.ad)) {
                            childAt.setPivotY(cVar.ad);
                        }
                        childAt.setTranslationX(cVar.ae);
                        childAt.setTranslationY(cVar.af);
                        if (VERSION.SDK_INT >= 21) {
                            childAt.setTranslationZ(cVar.ag);
                            if (cVar.V) {
                                childAt.setElevation(cVar.W);
                            }
                        }
                    }
                }
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            c cVar2 = (c) this.b.get(num);
            if (cVar2.at != -1 && cVar2.at == 1) {
                childAt = new Barrier(constraintLayout.getContext());
                childAt.setId(num.intValue());
                if (cVar2.au != null) {
                    childAt.setReferencedIds(cVar2.au);
                } else if (cVar2.av != null) {
                    cVar2.au = a(childAt, cVar2.av);
                    childAt.setReferencedIds(cVar2.au);
                }
                childAt.setType(cVar2.as);
                ViewGroup.LayoutParams a = ConstraintLayout.a();
                childAt.d();
                cVar2.a(a);
                constraintLayout.addView(childAt, a);
            }
            if (cVar2.a) {
                childAt = new Guideline(constraintLayout.getContext());
                childAt.setId(num.intValue());
                ViewGroup.LayoutParams a2 = ConstraintLayout.a();
                cVar2.a(a2);
                constraintLayout.addView(childAt, a2);
            }
        }
    }

    public final void a(int i, int i2, int i3, int i4, int i5) {
        if (!this.b.containsKey(Integer.valueOf(i))) {
            this.b.put(Integer.valueOf(i), new c());
        }
        c cVar = (c) this.b.get(Integer.valueOf(i));
        StringBuilder stringBuilder;
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    cVar.h = i3;
                    cVar.i = -1;
                } else if (i4 == 2) {
                    cVar.i = i3;
                    cVar.h = -1;
                } else {
                    stringBuilder = new StringBuilder("Left to ");
                    stringBuilder.append(b(i4));
                    stringBuilder.append(" undefined");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
                cVar.D = i5;
                return;
            case 2:
                if (i4 == 1) {
                    cVar.j = i3;
                    cVar.k = -1;
                } else if (i4 == 2) {
                    cVar.k = i3;
                    cVar.j = -1;
                } else {
                    stringBuilder = new StringBuilder("right to ");
                    stringBuilder.append(b(i4));
                    stringBuilder.append(" undefined");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
                cVar.E = i5;
                return;
            case 3:
                if (i4 == 3) {
                    cVar.l = i3;
                    cVar.m = -1;
                    cVar.p = -1;
                } else if (i4 == 4) {
                    cVar.m = i3;
                    cVar.l = -1;
                    cVar.p = -1;
                } else {
                    stringBuilder = new StringBuilder("right to ");
                    stringBuilder.append(b(i4));
                    stringBuilder.append(" undefined");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
                cVar.F = i5;
                return;
            case 4:
                if (i4 == 4) {
                    cVar.o = i3;
                    cVar.n = -1;
                    cVar.p = -1;
                } else if (i4 == 3) {
                    cVar.n = i3;
                    cVar.o = -1;
                    cVar.p = -1;
                } else {
                    stringBuilder = new StringBuilder("right to ");
                    stringBuilder.append(b(i4));
                    stringBuilder.append(" undefined");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
                cVar.G = i5;
                return;
            case 5:
                if (i4 == 5) {
                    cVar.p = i3;
                    cVar.o = -1;
                    cVar.n = -1;
                    cVar.l = -1;
                    cVar.m = -1;
                    return;
                }
                stringBuilder = new StringBuilder("right to ");
                stringBuilder.append(b(i4));
                stringBuilder.append(" undefined");
                throw new IllegalArgumentException(stringBuilder.toString());
            case 6:
                if (i4 == 6) {
                    cVar.r = i3;
                    cVar.q = -1;
                } else if (i4 == 7) {
                    cVar.q = i3;
                    cVar.r = -1;
                } else {
                    stringBuilder = new StringBuilder("right to ");
                    stringBuilder.append(b(i4));
                    stringBuilder.append(" undefined");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
                cVar.I = i5;
                return;
            case 7:
                if (i4 == 7) {
                    cVar.t = i3;
                    cVar.s = -1;
                } else if (i4 == 6) {
                    cVar.s = i3;
                    cVar.t = -1;
                } else {
                    stringBuilder = new StringBuilder("right to ");
                    stringBuilder.append(b(i4));
                    stringBuilder.append(" undefined");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
                cVar.H = i5;
                return;
            default:
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(b(i2));
                stringBuilder2.append(" to ");
                stringBuilder2.append(b(i4));
                stringBuilder2.append(" unknown");
                throw new IllegalArgumentException(stringBuilder2.toString());
        }
    }

    public final void a(int i, int i2) {
        if (this.b.containsKey(Integer.valueOf(i))) {
            c cVar = (c) this.b.get(Integer.valueOf(i));
            switch (i2) {
                case 1:
                    cVar.i = -1;
                    cVar.h = -1;
                    cVar.D = -1;
                    cVar.K = -1;
                    return;
                case 2:
                    cVar.k = -1;
                    cVar.j = -1;
                    cVar.E = -1;
                    cVar.M = -1;
                    return;
                case 3:
                    cVar.m = -1;
                    cVar.l = -1;
                    cVar.F = -1;
                    cVar.L = -1;
                    return;
                case 4:
                    cVar.n = -1;
                    cVar.o = -1;
                    cVar.G = -1;
                    cVar.N = -1;
                    return;
                case 5:
                    cVar.p = -1;
                    return;
                case 6:
                    cVar.q = -1;
                    cVar.r = -1;
                    cVar.I = -1;
                    cVar.P = -1;
                    return;
                case 7:
                    cVar.s = -1;
                    cVar.t = -1;
                    cVar.H = -1;
                    cVar.O = -1;
                    return;
                default:
                    throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public final void a(int i, float f) {
        a(i).v = f;
    }

    public final void b(int i, int i2) {
        a(i).c = i2;
    }

    public final void c(int i, int i2) {
        a(i).b = i2;
    }

    private c a(int i) {
        if (!this.b.containsKey(Integer.valueOf(i))) {
            this.b.put(Integer.valueOf(i), new c());
        }
        return (c) this.b.get(Integer.valueOf(i));
    }

    public final void a(Context context, int i) {
        XmlPullParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                    int i2 = 0;
                    c cVar = new c();
                    TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, f.ConstraintSet);
                    int indexCount = obtainStyledAttributes.getIndexCount();
                    while (i2 < indexCount) {
                        int index = obtainStyledAttributes.getIndex(i2);
                        int i3 = c.get(index);
                        switch (i3) {
                            case 1:
                                cVar.p = a(obtainStyledAttributes, index, cVar.p);
                                break;
                            case 2:
                                cVar.G = obtainStyledAttributes.getDimensionPixelSize(index, cVar.G);
                                break;
                            case 3:
                                cVar.o = a(obtainStyledAttributes, index, cVar.o);
                                break;
                            case 4:
                                cVar.n = a(obtainStyledAttributes, index, cVar.n);
                                break;
                            case 5:
                                cVar.w = obtainStyledAttributes.getString(index);
                                break;
                            case 6:
                                cVar.A = obtainStyledAttributes.getDimensionPixelOffset(index, cVar.A);
                                break;
                            case 7:
                                cVar.B = obtainStyledAttributes.getDimensionPixelOffset(index, cVar.B);
                                break;
                            case 8:
                                cVar.H = obtainStyledAttributes.getDimensionPixelSize(index, cVar.H);
                                break;
                            case 9:
                                cVar.t = a(obtainStyledAttributes, index, cVar.t);
                                break;
                            case 10:
                                cVar.s = a(obtainStyledAttributes, index, cVar.s);
                                break;
                            case 11:
                                cVar.N = obtainStyledAttributes.getDimensionPixelSize(index, cVar.N);
                                break;
                            case 12:
                                cVar.O = obtainStyledAttributes.getDimensionPixelSize(index, cVar.O);
                                break;
                            case 13:
                                cVar.K = obtainStyledAttributes.getDimensionPixelSize(index, cVar.K);
                                break;
                            case 14:
                                cVar.M = obtainStyledAttributes.getDimensionPixelSize(index, cVar.M);
                                break;
                            case 15:
                                cVar.P = obtainStyledAttributes.getDimensionPixelSize(index, cVar.P);
                                break;
                            case 16:
                                cVar.L = obtainStyledAttributes.getDimensionPixelSize(index, cVar.L);
                                break;
                            case 17:
                                cVar.e = obtainStyledAttributes.getDimensionPixelOffset(index, cVar.e);
                                break;
                            case 18:
                                cVar.f = obtainStyledAttributes.getDimensionPixelOffset(index, cVar.f);
                                break;
                            case 19:
                                cVar.g = obtainStyledAttributes.getFloat(index, cVar.g);
                                break;
                            case 20:
                                cVar.u = obtainStyledAttributes.getFloat(index, cVar.u);
                                break;
                            case 21:
                                cVar.c = obtainStyledAttributes.getLayoutDimension(index, cVar.c);
                                break;
                            case 22:
                                cVar.J = obtainStyledAttributes.getInt(index, cVar.J);
                                cVar.J = a[cVar.J];
                                break;
                            case 23:
                                cVar.b = obtainStyledAttributes.getLayoutDimension(index, cVar.b);
                                break;
                            case 24:
                                cVar.D = obtainStyledAttributes.getDimensionPixelSize(index, cVar.D);
                                break;
                            case 25:
                                cVar.h = a(obtainStyledAttributes, index, cVar.h);
                                break;
                            case 26:
                                cVar.i = a(obtainStyledAttributes, index, cVar.i);
                                break;
                            case 27:
                                cVar.C = obtainStyledAttributes.getInt(index, cVar.C);
                                break;
                            case 28:
                                cVar.E = obtainStyledAttributes.getDimensionPixelSize(index, cVar.E);
                                break;
                            case 29:
                                cVar.j = a(obtainStyledAttributes, index, cVar.j);
                                break;
                            case 30:
                                cVar.k = a(obtainStyledAttributes, index, cVar.k);
                                break;
                            case 31:
                                cVar.I = obtainStyledAttributes.getDimensionPixelSize(index, cVar.I);
                                break;
                            case 32:
                                cVar.q = a(obtainStyledAttributes, index, cVar.q);
                                break;
                            case 33:
                                cVar.r = a(obtainStyledAttributes, index, cVar.r);
                                break;
                            case 34:
                                cVar.F = obtainStyledAttributes.getDimensionPixelSize(index, cVar.F);
                                break;
                            case 35:
                                cVar.m = a(obtainStyledAttributes, index, cVar.m);
                                break;
                            case 36:
                                cVar.l = a(obtainStyledAttributes, index, cVar.l);
                                break;
                            case 37:
                                cVar.v = obtainStyledAttributes.getFloat(index, cVar.v);
                                break;
                            case 38:
                                cVar.d = obtainStyledAttributes.getResourceId(index, cVar.d);
                                break;
                            case 39:
                                cVar.R = obtainStyledAttributes.getFloat(index, cVar.R);
                                break;
                            case 40:
                                cVar.Q = obtainStyledAttributes.getFloat(index, cVar.Q);
                                break;
                            case 41:
                                cVar.S = obtainStyledAttributes.getInt(index, cVar.S);
                                break;
                            case 42:
                                cVar.T = obtainStyledAttributes.getInt(index, cVar.T);
                                break;
                            case 43:
                                cVar.U = obtainStyledAttributes.getFloat(index, cVar.U);
                                break;
                            case 44:
                                cVar.V = true;
                                cVar.W = obtainStyledAttributes.getDimension(index, cVar.W);
                                break;
                            case 45:
                                cVar.Y = obtainStyledAttributes.getFloat(index, cVar.Y);
                                break;
                            case 46:
                                cVar.Z = obtainStyledAttributes.getFloat(index, cVar.Z);
                                break;
                            case 47:
                                cVar.aa = obtainStyledAttributes.getFloat(index, cVar.aa);
                                break;
                            case 48:
                                cVar.ab = obtainStyledAttributes.getFloat(index, cVar.ab);
                                break;
                            case 49:
                                cVar.ac = obtainStyledAttributes.getFloat(index, cVar.ac);
                                break;
                            case 50:
                                cVar.ad = obtainStyledAttributes.getFloat(index, cVar.ad);
                                break;
                            case 51:
                                cVar.ae = obtainStyledAttributes.getDimension(index, cVar.ae);
                                break;
                            case 52:
                                cVar.af = obtainStyledAttributes.getDimension(index, cVar.af);
                                break;
                            case 53:
                                cVar.ag = obtainStyledAttributes.getDimension(index, cVar.ag);
                                break;
                            default:
                                switch (i3) {
                                    case 60:
                                        cVar.X = obtainStyledAttributes.getFloat(index, cVar.X);
                                        break;
                                    case 61:
                                        cVar.x = a(obtainStyledAttributes, index, cVar.x);
                                        break;
                                    case 62:
                                        cVar.y = obtainStyledAttributes.getDimensionPixelSize(index, cVar.y);
                                        break;
                                    case 63:
                                        cVar.z = obtainStyledAttributes.getFloat(index, cVar.z);
                                        break;
                                    default:
                                        StringBuilder stringBuilder;
                                        switch (i3) {
                                            case 69:
                                                cVar.ap = obtainStyledAttributes.getFloat(index, 1.0f);
                                                break;
                                            case 70:
                                                cVar.aq = obtainStyledAttributes.getFloat(index, 1.0f);
                                                break;
                                            case 71:
                                                Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                break;
                                            case 72:
                                                cVar.as = obtainStyledAttributes.getInt(index, cVar.as);
                                                break;
                                            case 73:
                                                cVar.av = obtainStyledAttributes.getString(index);
                                                break;
                                            case 74:
                                                cVar.ar = obtainStyledAttributes.getBoolean(index, cVar.ar);
                                                break;
                                            case 75:
                                                stringBuilder = new StringBuilder("unused attribute 0x");
                                                stringBuilder.append(Integer.toHexString(index));
                                                stringBuilder.append("   ");
                                                stringBuilder.append(c.get(index));
                                                Log.w("ConstraintSet", stringBuilder.toString());
                                                break;
                                            default:
                                                stringBuilder = new StringBuilder("Unknown attribute 0x");
                                                stringBuilder.append(Integer.toHexString(index));
                                                stringBuilder.append("   ");
                                                stringBuilder.append(c.get(index));
                                                Log.w("ConstraintSet", stringBuilder.toString());
                                                break;
                                        }
                                }
                        }
                        i2++;
                    }
                    obtainStyledAttributes.recycle();
                    if (name.equalsIgnoreCase("Guideline")) {
                        cVar.a = true;
                    }
                    this.b.put(Integer.valueOf(cVar.d), cVar);
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static int a(TypedArray typedArray, int i, int i2) {
        i2 = typedArray.getResourceId(i, i2);
        return i2 == -1 ? typedArray.getInt(i, -1) : i2;
    }

    private static int[] a(android.view.View r9, java.lang.String r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.constraintlayout.widget.b.a(android.view.View, java.lang.String):int[]. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = ",";
        r10 = r10.split(r0);
        r0 = r9.getContext();
        r1 = r10.length;
        r1 = new int[r1];
        r2 = 0;
        r3 = 0;
        r4 = 0;
    L_0x0010:
        r5 = r10.length;
        if (r3 >= r5) goto L_0x0064;
    L_0x0013:
        r5 = r10[r3];
        r5 = r5.trim();
        r6 = androidx.constraintlayout.widget.e.class;	 Catch:{ Exception -> 0x0025 }
        r6 = r6.getField(r5);	 Catch:{ Exception -> 0x0025 }
        r7 = 0;	 Catch:{ Exception -> 0x0025 }
        r6 = r6.getInt(r7);	 Catch:{ Exception -> 0x0025 }
        goto L_0x0026;
    L_0x0025:
        r6 = 0;
    L_0x0026:
        if (r6 != 0) goto L_0x0036;
    L_0x0028:
        r6 = r0.getResources();
        r7 = "id";
        r8 = r0.getPackageName();
        r6 = r6.getIdentifier(r5, r7, r8);
    L_0x0036:
        if (r6 != 0) goto L_0x005c;
    L_0x0038:
        r7 = r9.isInEditMode();
        if (r7 == 0) goto L_0x005c;
    L_0x003e:
        r7 = r9.getParent();
        r7 = r7 instanceof androidx.constraintlayout.widget.ConstraintLayout;
        if (r7 == 0) goto L_0x005c;
    L_0x0046:
        r7 = r9.getParent();
        r7 = (androidx.constraintlayout.widget.ConstraintLayout) r7;
        r5 = r7.a(r5);
        if (r5 == 0) goto L_0x005c;
    L_0x0052:
        r7 = r5 instanceof java.lang.Integer;
        if (r7 == 0) goto L_0x005c;
    L_0x0056:
        r5 = (java.lang.Integer) r5;
        r6 = r5.intValue();
    L_0x005c:
        r5 = r4 + 1;
        r1[r4] = r6;
        r3 = r3 + 1;
        r4 = r5;
        goto L_0x0010;
    L_0x0064:
        r9 = r10.length;
        if (r4 == r9) goto L_0x006b;
    L_0x0067:
        r1 = java.util.Arrays.copyOf(r1, r4);
    L_0x006b:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.b.a(android.view.View, java.lang.String):int[]");
    }
}
