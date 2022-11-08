package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.ar;
import defpackage.bu;
import defpackage.hs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

final class ae {
    private static final int[] a = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    private static final ah b = (VERSION.SDK_INT >= 21 ? new ag() : null);
    private static final ah c = a();

    private static androidx.fragment.app.ah a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.fragment.app.ae.a():androidx.fragment.app.ah. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = "androidx.transition.j";	 Catch:{ Exception -> 0x0016 }
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0016 }
        r1 = 0;	 Catch:{ Exception -> 0x0016 }
        r2 = new java.lang.Class[r1];	 Catch:{ Exception -> 0x0016 }
        r0 = r0.getDeclaredConstructor(r2);	 Catch:{ Exception -> 0x0016 }
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0016 }
        r0 = r0.newInstance(r1);	 Catch:{ Exception -> 0x0016 }
        r0 = (androidx.fragment.app.ah) r0;	 Catch:{ Exception -> 0x0016 }
        return r0;
    L_0x0016:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.ae.a():androidx.fragment.app.ah");
    }

    static void a(o oVar, ArrayList<a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        o oVar2 = oVar;
        ArrayList arrayList3 = arrayList;
        ArrayList arrayList4 = arrayList2;
        int i3 = i2;
        boolean z2 = z;
        if (oVar2.l > 0) {
            SparseArray sparseArray = new SparseArray();
            for (int i4 = i; i4 < i3; i4++) {
                a aVar = (a) arrayList3.get(i4);
                if (((Boolean) arrayList4.get(i4)).booleanValue()) {
                    b(aVar, sparseArray, z2);
                } else {
                    a(aVar, sparseArray, z2);
                }
            }
            if (sparseArray.size() != 0) {
                View view = new View(oVar2.m.i());
                int size = sparseArray.size();
                int i5 = 0;
                while (i5 < size) {
                    SparseArray sparseArray2;
                    int i6;
                    int i7;
                    int i8;
                    int i9;
                    ArrayList<a> arrayList32;
                    ArrayList<Boolean> arrayList42;
                    int keyAt = sparseArray.keyAt(i5);
                    bu a = a(keyAt, arrayList32, arrayList42, i, i3);
                    af afVar = (af) sparseArray.valueAt(i5);
                    ArrayList arrayList5;
                    Fragment fragment;
                    Object obj;
                    bu b;
                    bu buVar;
                    Object a2;
                    if (z2) {
                        View view2 = oVar2.n.a() ? (ViewGroup) oVar2.n.a(keyAt) : null;
                        if (view2 != null) {
                            Fragment fragment2 = afVar.a;
                            Fragment fragment3 = afVar.d;
                            ah a3 = a(fragment3, fragment2);
                            if (a3 != null) {
                                boolean z3;
                                ArrayList arrayList6;
                                Fragment fragment4;
                                final ArrayList a4;
                                Fragment fragment5;
                                ArrayList a5;
                                ArrayList a6;
                                boolean z4 = afVar.b;
                                boolean z5 = afVar.e;
                                ArrayList arrayList7 = new ArrayList();
                                arrayList5 = new ArrayList();
                                sparseArray2 = sparseArray;
                                Object a7 = a(a3, fragment2, z4);
                                Object b2 = b(a3, fragment3, z5);
                                Fragment fragment6 = afVar.a;
                                i6 = i5;
                                fragment = afVar.d;
                                if (fragment6 != null) {
                                    i7 = size;
                                    fragment6.getView().setVisibility(0);
                                } else {
                                    i7 = size;
                                }
                                if (fragment6 == null || fragment == null) {
                                    z3 = z4;
                                    arrayList6 = arrayList7;
                                    fragment4 = fragment2;
                                } else {
                                    boolean z6 = afVar.b;
                                    if (a.isEmpty()) {
                                        z3 = z4;
                                        obj = null;
                                    } else {
                                        obj = a(a3, fragment6, fragment, z6);
                                        z3 = z4;
                                    }
                                    b = b(a3, a, obj, afVar);
                                    fragment4 = fragment2;
                                    bu a8 = a(a3, a, obj, afVar);
                                    if (a.isEmpty()) {
                                        if (b != null) {
                                            b.clear();
                                        }
                                        if (a8 != null) {
                                            a8.clear();
                                        }
                                        obj = null;
                                    } else {
                                        Object obj2 = obj;
                                        a(arrayList5, b, a.keySet());
                                        a(arrayList7, a8, a.values());
                                        obj = obj2;
                                    }
                                    if (a7 == null && b2 == null && obj == null) {
                                        arrayList6 = arrayList7;
                                    } else {
                                        Rect rect;
                                        View view3;
                                        a(fragment6, fragment, z6, b);
                                        if (obj != null) {
                                            arrayList7.add(view);
                                            a3.a(obj, view, arrayList5);
                                            buVar = a;
                                            arrayList6 = arrayList7;
                                            a(a3, obj, b2, b, afVar.e, afVar.f);
                                            Rect rect2 = new Rect();
                                            View a9 = a(a8, afVar, a7, z6);
                                            if (a9 != null) {
                                                a3.a(a7, rect2);
                                            }
                                            rect = rect2;
                                            view3 = a9;
                                        } else {
                                            arrayList6 = arrayList7;
                                            buVar = a;
                                            view3 = null;
                                            rect = view3;
                                        }
                                        final Fragment fragment7 = fragment6;
                                        final Fragment fragment8 = fragment;
                                        final boolean z7 = z6;
                                        final bu buVar2 = a8;
                                        final ah ahVar = a3;
                                        ai.a(view2, new Runnable() {
                                            public final void run() {
                                                ae.a(fragment7, fragment8, z7, buVar2);
                                                if (view3 != null) {
                                                    ah.a(view3, rect);
                                                }
                                            }
                                        });
                                        if (!(a7 == null && obj == null && b2 == null)) {
                                            a4 = a(a3, b2, fragment3, arrayList5, view);
                                            fragment5 = fragment4;
                                            arrayList7 = arrayList6;
                                            a5 = a(a3, a7, fragment5, arrayList7, view);
                                            a(a5, 4);
                                            a2 = a(a3, a7, b2, obj, fragment5, z3);
                                            if (a2 != null) {
                                                if (fragment3 != null && b2 != null && fragment3.mAdded && fragment3.mHidden && fragment3.mHiddenChanged) {
                                                    fragment3.setHideReplaced(true);
                                                    a3.b(b2, fragment3.getView(), a4);
                                                    ai.a(fragment3.mContainer, new Runnable() {
                                                        public final void run() {
                                                            ae.a(a4, 4);
                                                        }
                                                    });
                                                }
                                                a6 = ah.a(arrayList7);
                                                a3.a(a2, a7, a5, b2, a4, obj, arrayList7);
                                                a3.a((ViewGroup) view2, a2);
                                                a3.a(view2, arrayList5, arrayList7, a6, buVar);
                                                a(a5, 0);
                                                a3.a(obj, arrayList5, arrayList7);
                                            }
                                        }
                                    }
                                }
                                buVar = a;
                                obj = null;
                                a4 = a(a3, b2, fragment3, arrayList5, view);
                                fragment5 = fragment4;
                                arrayList7 = arrayList6;
                                a5 = a(a3, a7, fragment5, arrayList7, view);
                                a(a5, 4);
                                a2 = a(a3, a7, b2, obj, fragment5, z3);
                                if (a2 != null) {
                                    fragment3.setHideReplaced(true);
                                    a3.b(b2, fragment3.getView(), a4);
                                    ai.a(fragment3.mContainer, /* anonymous class already generated */);
                                    a6 = ah.a(arrayList7);
                                    a3.a(a2, a7, a5, b2, a4, obj, arrayList7);
                                    a3.a((ViewGroup) view2, a2);
                                    a3.a(view2, arrayList5, arrayList7, a6, buVar);
                                    a(a5, 0);
                                    a3.a(obj, arrayList5, arrayList7);
                                }
                            }
                        }
                        sparseArray2 = sparseArray;
                        i6 = i5;
                        i7 = size;
                    } else {
                        o oVar3 = oVar2;
                        sparseArray2 = sparseArray;
                        buVar = a;
                        i6 = i5;
                        i7 = size;
                        ViewGroup viewGroup = oVar3.n.a() ? (ViewGroup) oVar3.n.a(keyAt) : null;
                        if (viewGroup != null) {
                            Fragment fragment9 = afVar.a;
                            Fragment fragment10 = afVar.d;
                            ah a10 = a(fragment10, fragment9);
                            if (a10 != null) {
                                Fragment fragment11;
                                Fragment fragment12;
                                ArrayList arrayList8;
                                Object obj3;
                                af afVar2;
                                ArrayList arrayList9;
                                bu buVar3;
                                Object obj4;
                                Object obj5;
                                Object obj6;
                                ArrayList arrayList10;
                                final ah ahVar2;
                                final View view4;
                                final Fragment fragment13;
                                final ArrayList arrayList11;
                                final ArrayList arrayList12;
                                final ArrayList arrayList13;
                                AnonymousClass2 anonymousClass2;
                                AnonymousClass2 anonymousClass22;
                                bu buVar4;
                                boolean z8 = afVar.b;
                                boolean z9 = afVar.e;
                                Object a11 = a(a10, fragment9, z8);
                                Object b3 = b(a10, fragment10, z9);
                                ArrayList arrayList14 = new ArrayList();
                                ArrayList arrayList15 = new ArrayList();
                                Fragment fragment14 = afVar.a;
                                fragment = afVar.d;
                                if (fragment14 == null || fragment == null) {
                                    fragment11 = fragment9;
                                    fragment12 = fragment10;
                                    arrayList8 = arrayList14;
                                    a2 = b3;
                                    obj3 = a11;
                                    afVar2 = afVar;
                                    arrayList9 = arrayList15;
                                    i8 = i6;
                                    i9 = i7;
                                    buVar3 = buVar;
                                    obj4 = null;
                                } else {
                                    boolean z10 = afVar.b;
                                    if (buVar.isEmpty()) {
                                        obj5 = null;
                                    } else {
                                        obj5 = a(a10, fragment14, fragment, z10);
                                    }
                                    a = buVar;
                                    b = b(a10, a, obj5, afVar);
                                    if (a.isEmpty()) {
                                        obj5 = null;
                                    } else {
                                        Object obj7 = obj5;
                                        arrayList14.addAll(b.values());
                                        obj5 = obj7;
                                    }
                                    if (a11 == null && b3 == null && obj5 == null) {
                                        fragment11 = fragment9;
                                        fragment12 = fragment10;
                                        arrayList8 = arrayList14;
                                        a2 = b3;
                                        obj6 = a11;
                                        afVar2 = afVar;
                                        buVar3 = a;
                                        arrayList9 = arrayList15;
                                        obj4 = null;
                                        i8 = i6;
                                        i9 = i7;
                                        if (obj6 != null && obj4 == null && a2 == null) {
                                            i5 = i8 + 1;
                                            size = i9;
                                            sparseArray = sparseArray2;
                                            oVar2 = oVar;
                                            arrayList32 = arrayList;
                                            arrayList42 = arrayList2;
                                            i3 = i2;
                                            z2 = z;
                                        } else {
                                            arrayList5 = a(a10, a2, fragment12, arrayList8, view);
                                            if (arrayList5 == null || arrayList5.isEmpty()) {
                                                a2 = null;
                                            }
                                            a10.b(obj6, view);
                                            obj5 = a(a10, obj6, a2, obj4, fragment11, afVar2.b);
                                            if (obj5 != null) {
                                                arrayList10 = new ArrayList();
                                                a10.a(obj5, obj6, arrayList10, a2, arrayList5, obj4, arrayList9);
                                                b3 = obj6;
                                                ahVar2 = a10;
                                                view4 = view;
                                                fragment13 = fragment11;
                                                arrayList11 = arrayList9;
                                                arrayList12 = arrayList10;
                                                arrayList13 = arrayList5;
                                                anonymousClass2 = anonymousClass22;
                                                obj = a2;
                                                anonymousClass22 = new Runnable() {
                                                    public final void run() {
                                                        if (b3 != null) {
                                                            ahVar2.c(b3, view4);
                                                            arrayList12.addAll(ae.a(ahVar2, b3, fragment13, arrayList11, view4));
                                                        }
                                                        if (arrayList13 != null) {
                                                            if (obj != null) {
                                                                ArrayList arrayList = new ArrayList();
                                                                arrayList.add(view4);
                                                                ahVar2.b(obj, arrayList13, arrayList);
                                                            }
                                                            arrayList13.clear();
                                                            arrayList13.add(view4);
                                                        }
                                                    }
                                                };
                                                ai.a(viewGroup, anonymousClass2);
                                                buVar4 = buVar3;
                                                arrayList5 = arrayList9;
                                                ai.a(viewGroup, new androidx.fragment.app.ah.AnonymousClass2(a10, arrayList5, buVar4));
                                                a10.a(viewGroup, obj5);
                                                ai.a(viewGroup, new androidx.fragment.app.ah.AnonymousClass3(a10, arrayList5, buVar4));
                                            }
                                            i5 = i8 + 1;
                                            size = i9;
                                            sparseArray = sparseArray2;
                                            oVar2 = oVar;
                                            arrayList32 = arrayList;
                                            arrayList42 = arrayList2;
                                            i3 = i2;
                                            z2 = z;
                                        }
                                    } else {
                                        Rect rect3;
                                        ArrayList arrayList16;
                                        bu buVar5;
                                        boolean z11;
                                        Fragment fragment15;
                                        Fragment fragment16;
                                        a(fragment14, fragment, z10, b);
                                        if (obj5 != null) {
                                            fragment11 = fragment9;
                                            rect3 = new Rect();
                                            a10.a(obj5, view, arrayList14);
                                            arrayList16 = arrayList14;
                                            fragment12 = fragment10;
                                            buVar5 = a;
                                            z11 = z10;
                                            fragment15 = fragment;
                                            fragment16 = fragment14;
                                            bu buVar6 = b;
                                            arrayList32 = arrayList15;
                                            a(a10, obj5, b3, buVar6, afVar.e, afVar.f);
                                            if (a11 != null) {
                                                a10.a(a11, rect3);
                                            }
                                        } else {
                                            fragment11 = fragment9;
                                            fragment12 = fragment10;
                                            arrayList16 = arrayList14;
                                            buVar5 = a;
                                            z11 = z10;
                                            fragment15 = fragment;
                                            fragment16 = fragment14;
                                            arrayList32 = arrayList15;
                                            rect3 = null;
                                        }
                                        Object obj8 = b3;
                                        final ah ahVar3 = a10;
                                        Object obj9 = a11;
                                        final bu buVar7 = buVar5;
                                        af afVar3 = afVar;
                                        final Object obj10 = obj5;
                                        Object obj11 = obj5;
                                        final af afVar4 = afVar3;
                                        buVar3 = buVar5;
                                        afVar2 = afVar3;
                                        arrayList11 = arrayList32;
                                        obj3 = obj9;
                                        i8 = i6;
                                        final View view5 = view;
                                        arrayList9 = arrayList32;
                                        a2 = obj8;
                                        fragment14 = fragment16;
                                        arrayList8 = arrayList16;
                                        i9 = i7;
                                        final Fragment fragment17 = fragment15;
                                        final boolean z12 = z11;
                                        final ArrayList arrayList17 = arrayList8;
                                        final Object obj12 = obj3;
                                        final Rect rect4 = rect3;
                                        ai.a(viewGroup, new Runnable() {
                                            public final void run() {
                                                bu a = ae.a(ahVar3, buVar7, obj10, afVar4);
                                                if (a != null) {
                                                    arrayList11.addAll(a.values());
                                                    arrayList11.add(view5);
                                                }
                                                ae.a(fragment14, fragment17, z12, a);
                                                if (obj10 != null) {
                                                    ahVar3.a(obj10, arrayList17, arrayList11);
                                                    View a2 = ae.a(a, afVar4, obj12, z12);
                                                    if (a2 != null) {
                                                        ah.a(a2, rect4);
                                                    }
                                                }
                                            }
                                        });
                                        obj4 = obj11;
                                    }
                                }
                                obj6 = obj3;
                                if (obj6 != null) {
                                }
                                arrayList5 = a(a10, a2, fragment12, arrayList8, view);
                                a2 = null;
                                a10.b(obj6, view);
                                obj5 = a(a10, obj6, a2, obj4, fragment11, afVar2.b);
                                if (obj5 != null) {
                                    arrayList10 = new ArrayList();
                                    a10.a(obj5, obj6, arrayList10, a2, arrayList5, obj4, arrayList9);
                                    b3 = obj6;
                                    ahVar2 = a10;
                                    view4 = view;
                                    fragment13 = fragment11;
                                    arrayList11 = arrayList9;
                                    arrayList12 = arrayList10;
                                    arrayList13 = arrayList5;
                                    anonymousClass2 = anonymousClass22;
                                    obj = a2;
                                    anonymousClass22 = /* anonymous class already generated */;
                                    ai.a(viewGroup, anonymousClass2);
                                    buVar4 = buVar3;
                                    arrayList5 = arrayList9;
                                    ai.a(viewGroup, new androidx.fragment.app.ah.AnonymousClass2(a10, arrayList5, buVar4));
                                    a10.a(viewGroup, obj5);
                                    ai.a(viewGroup, new androidx.fragment.app.ah.AnonymousClass3(a10, arrayList5, buVar4));
                                }
                                i5 = i8 + 1;
                                size = i9;
                                sparseArray = sparseArray2;
                                oVar2 = oVar;
                                arrayList32 = arrayList;
                                arrayList42 = arrayList2;
                                i3 = i2;
                                z2 = z;
                            }
                        }
                    }
                    i8 = i6;
                    i9 = i7;
                    i5 = i8 + 1;
                    size = i9;
                    sparseArray = sparseArray2;
                    oVar2 = oVar;
                    arrayList32 = arrayList;
                    arrayList42 = arrayList2;
                    i3 = i2;
                    z2 = z;
                }
            }
        }
    }

    private static bu<String, String> a(int i, ArrayList<a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        bu<String, String> buVar = new bu();
        for (i3--; i3 >= i2; i3--) {
            a aVar = (a) arrayList.get(i3);
            if (aVar.c(i)) {
                boolean booleanValue = ((Boolean) arrayList2.get(i3)).booleanValue();
                if (aVar.r != null) {
                    ArrayList arrayList3;
                    ArrayList arrayList4;
                    int size = aVar.r.size();
                    if (booleanValue) {
                        arrayList3 = aVar.r;
                        arrayList4 = aVar.s;
                    } else {
                        ArrayList arrayList5 = aVar.r;
                        arrayList3 = aVar.s;
                        arrayList4 = arrayList5;
                    }
                    for (int i4 = 0; i4 < size; i4++) {
                        String str = (String) arrayList4.get(i4);
                        String str2 = (String) arrayList3.get(i4);
                        String str3 = (String) buVar.remove(str2);
                        if (str3 != null) {
                            buVar.put(str, str3);
                        } else {
                            buVar.put(str, str2);
                        }
                    }
                }
            }
        }
        return buVar;
    }

    private static ah a(Fragment fragment, Fragment fragment2) {
        Object sharedElementReturnTransition;
        List arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            exitTransition = fragment.getReturnTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            sharedElementReturnTransition = fragment2.getEnterTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
            sharedElementReturnTransition = fragment2.getReenterTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
            sharedElementReturnTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (b != null && a(b, arrayList)) {
            return b;
        }
        if (c != null && a(c, arrayList)) {
            return c;
        }
        if (b == null && c == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    private static boolean a(ah ahVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!ahVar.a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object a(ah ahVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        Object sharedElementReturnTransition;
        if (z) {
            sharedElementReturnTransition = fragment2.getSharedElementReturnTransition();
        } else {
            sharedElementReturnTransition = fragment.getSharedElementEnterTransition();
        }
        return ahVar.c(ahVar.b(sharedElementReturnTransition));
    }

    private static Object a(ah ahVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        Object reenterTransition;
        if (z) {
            reenterTransition = fragment.getReenterTransition();
        } else {
            reenterTransition = fragment.getEnterTransition();
        }
        return ahVar.b(reenterTransition);
    }

    private static Object b(ah ahVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        Object returnTransition;
        if (z) {
            returnTransition = fragment.getReturnTransition();
        } else {
            returnTransition = fragment.getExitTransition();
        }
        return ahVar.b(returnTransition);
    }

    private static void a(ArrayList<View> arrayList, bu<String, View> buVar, Collection<String> collection) {
        for (int size = buVar.size() - 1; size >= 0; size--) {
            View view = (View) buVar.c(size);
            if (collection.contains(hs.r(view))) {
                arrayList.add(view);
            }
        }
    }

    private static bu<String, View> b(ah ahVar, bu<String, String> buVar, Object obj, af afVar) {
        if (buVar.isEmpty() || obj == null) {
            buVar.clear();
            return null;
        }
        ar enterTransitionCallback;
        ArrayList arrayList;
        Fragment fragment = afVar.d;
        Map buVar2 = new bu();
        ahVar.a(buVar2, fragment.getView());
        a aVar = afVar.f;
        if (afVar.e) {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar.s;
        } else {
            enterTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar.r;
        }
        buVar2.a(arrayList);
        if (enterTransitionCallback != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = (String) arrayList.get(size);
                View view = (View) buVar2.get(str);
                if (view == null) {
                    buVar.remove(str);
                } else if (!str.equals(hs.r(view))) {
                    buVar.put(hs.r(view), (String) buVar.remove(str));
                }
            }
        } else {
            buVar.a(buVar2.keySet());
        }
        return buVar2;
    }

    static bu<String, View> a(ah ahVar, bu<String, String> buVar, Object obj, af afVar) {
        Fragment fragment = afVar.a;
        View view = fragment.getView();
        if (buVar.isEmpty() || obj == null || view == null) {
            buVar.clear();
            return null;
        }
        ar exitTransitionCallback;
        ArrayList arrayList;
        Map buVar2 = new bu();
        ahVar.a(buVar2, view);
        a aVar = afVar.c;
        if (afVar.b) {
            exitTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar.r;
        } else {
            exitTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar.s;
        }
        if (arrayList != null) {
            buVar2.a(arrayList);
            buVar2.a(buVar.values());
        }
        if (exitTransitionCallback != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = (String) arrayList.get(size);
                view = (View) buVar2.get(str);
                if (view == null) {
                    str = a((bu) buVar, str);
                    if (str != null) {
                        buVar.remove(str);
                    }
                } else if (!str.equals(hs.r(view))) {
                    str = a((bu) buVar, str);
                    if (str != null) {
                        buVar.put(str, hs.r(view));
                    }
                }
            }
        } else {
            for (int size2 = buVar.size() - 1; size2 >= 0; size2--) {
                if (!buVar2.containsKey((String) buVar.c(size2))) {
                    buVar.d(size2);
                }
            }
        }
        return buVar2;
    }

    private static String a(bu<String, String> buVar, String str) {
        int size = buVar.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(buVar.c(i))) {
                return (String) buVar.b(i);
            }
        }
        return null;
    }

    static View a(bu<String, View> buVar, af afVar, Object obj, boolean z) {
        a aVar = afVar.c;
        if (obj == null || buVar == null || aVar.r == null || aVar.r.isEmpty()) {
            return null;
        }
        Object obj2;
        if (z) {
            obj2 = (String) aVar.r.get(0);
        } else {
            obj2 = (String) aVar.s.get(0);
        }
        return (View) buVar.get(obj2);
    }

    private static void a(ah ahVar, Object obj, Object obj2, bu<String, View> buVar, boolean z, a aVar) {
        if (aVar.r != null && !aVar.r.isEmpty()) {
            Object obj3;
            if (z) {
                obj3 = (String) aVar.s.get(0);
            } else {
                obj3 = (String) aVar.r.get(0);
            }
            View view = (View) buVar.get(obj3);
            ahVar.a(obj, view);
            if (obj2 != null) {
                ahVar.a(obj2, view);
            }
        }
    }

    static void a(Fragment fragment, Fragment fragment2, boolean z, bu<String, View> buVar) {
        ar enterTransitionCallback;
        if (z) {
            enterTransitionCallback = fragment2.getEnterTransitionCallback();
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
        }
        if (enterTransitionCallback != null) {
            int i;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i2 = 0;
            if (buVar == null) {
                i = 0;
            } else {
                i = buVar.size();
            }
            while (i2 < i) {
                arrayList2.add(buVar.b(i2));
                arrayList.add(buVar.c(i2));
                i2++;
            }
        }
    }

    static ArrayList<View> a(ah ahVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        View view2 = fragment.getView();
        if (view2 != null) {
            ahVar.a(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        ahVar.a(obj, arrayList2);
        return arrayList2;
    }

    static void a(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((View) arrayList.get(size)).setVisibility(i);
            }
        }
    }

    private static Object a(ah ahVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean allowReturnTransitionOverlap = (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap();
        if (allowReturnTransitionOverlap) {
            return ahVar.a(obj2, obj, obj3);
        }
        return ahVar.b(obj2, obj, obj3);
    }

    private static void a(a aVar, SparseArray<af> sparseArray, boolean z) {
        int size = aVar.b.size();
        for (int i = 0; i < size; i++) {
            a(aVar, (b) aVar.b.get(i), (SparseArray) sparseArray, false, z);
        }
    }

    private static void b(a aVar, SparseArray<af> sparseArray, boolean z) {
        if (aVar.a.n.a()) {
            for (int size = aVar.b.size() - 1; size >= 0; size--) {
                a(aVar, (b) aVar.b.get(size), (SparseArray) sparseArray, true, z);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(a aVar, b bVar, SparseArray<af> sparseArray, boolean z, boolean z2) {
        Fragment fragment = bVar.b;
        if (fragment != null) {
            int i = fragment.mContainerId;
            if (i != 0) {
                boolean z3;
                Object obj;
                int i2 = z ? a[bVar.a] : bVar.a;
                boolean z4 = false;
                Object obj2 = 1;
                if (i2 != 1) {
                    switch (i2) {
                        case 3:
                        case 6:
                            if (!z2) {
                                break;
                            }
                            break;
                        case 4:
                            if (!z2) {
                                break;
                            }
                            break;
                        case 5:
                            if (z2) {
                                if (fragment.mHiddenChanged) {
                                    if (!fragment.mHidden) {
                                        break;
                                    }
                                }
                            }
                            z3 = fragment.mHidden;
                            break;
                            break;
                        case 7:
                            break;
                        default:
                            obj = null;
                            obj2 = null;
                            break;
                    }
                }
                Object obj3;
                af afVar;
                af afVar2;
                o oVar;
                if (z2) {
                    z3 = fragment.mIsNewlyAdded;
                    z4 = z3;
                    obj = null;
                    obj3 = null;
                    afVar = (af) sparseArray.get(i);
                    if (z4) {
                        afVar = a(afVar, (SparseArray) sparseArray, i);
                        afVar.a = fragment;
                        afVar.b = z;
                        afVar.c = aVar;
                    }
                    afVar2 = afVar;
                    if (!(z2 || r1 == null)) {
                        if (afVar2 != null && afVar2.d == fragment) {
                            afVar2.d = null;
                        }
                        oVar = aVar.a;
                        if (fragment.mState <= 0 && oVar.l > 0 && !aVar.t) {
                            oVar.d(fragment);
                            oVar.a(fragment, 1, 0, 0, false);
                        }
                    }
                    if (obj3 != null && (afVar2 == null || afVar2.d == null)) {
                        afVar2 = a(afVar2, (SparseArray) sparseArray, i);
                        afVar2.d = fragment;
                        afVar2.e = z;
                        afVar2.f = aVar;
                    }
                    if (!(z2 || r12 == null || afVar2 == null || afVar2.a != fragment)) {
                        afVar2.a = null;
                    }
                }
                if (!fragment.mAdded) {
                }
                z3 = false;
                z4 = z3;
                obj = null;
                obj3 = null;
                afVar = (af) sparseArray.get(i);
                if (z4) {
                    afVar = a(afVar, (SparseArray) sparseArray, i);
                    afVar.a = fragment;
                    afVar.b = z;
                    afVar.c = aVar;
                }
                afVar2 = afVar;
                afVar2.d = null;
                oVar = aVar.a;
                oVar.d(fragment);
                oVar.a(fragment, 1, 0, 0, false);
                afVar2 = a(afVar2, (SparseArray) sparseArray, i);
                afVar2.d = fragment;
                afVar2.e = z;
                afVar2.f = aVar;
                afVar2.a = null;
            }
        }
    }

    private static af a(af afVar, SparseArray<af> sparseArray, int i) {
        if (afVar != null) {
            return afVar;
        }
        afVar = new af();
        sparseArray.put(i, afVar);
        return afVar;
    }
}
