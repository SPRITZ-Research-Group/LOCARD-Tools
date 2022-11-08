package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.app.m.AnonymousClass7;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

final class l {
    private static final int[] a = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};

    static class a {
        public Fragment a;
        public boolean b;
        public b c;
        public Fragment d;
        public boolean e;
        public b f;

        a() {
        }
    }

    static void a(j fragmentManager, ArrayList<b> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex, boolean isReordered) {
        if (fragmentManager.l > 0 && VERSION.SDK_INT >= 21) {
            int i;
            SparseArray<a> transitioningFragments = new SparseArray();
            for (i = startIndex; i < endIndex; i++) {
                b record = (b) records.get(i);
                if (((Boolean) isRecordPop.get(i)).booleanValue()) {
                    b(record, (SparseArray) transitioningFragments, isReordered);
                } else {
                    a(record, (SparseArray) transitioningFragments, isReordered);
                }
            }
            if (transitioningFragments.size() != 0) {
                View view = new View(fragmentManager.m.b);
                int numContainers = transitioningFragments.size();
                for (i = 0; i < numContainers; i++) {
                    int containerId = transitioningFragments.keyAt(i);
                    android.support.v4.util.a<String, String> nameOverrides = a(containerId, (ArrayList) records, (ArrayList) isRecordPop, startIndex, endIndex);
                    a containerTransition = (a) transitioningFragments.valueAt(i);
                    boolean z;
                    ArrayList arrayList;
                    Object b;
                    Object obj;
                    View b2;
                    final ArrayList b3;
                    if (isReordered) {
                        ViewGroup viewGroup;
                        if (fragmentManager.n.a()) {
                            viewGroup = (ViewGroup) fragmentManager.n.a(containerId);
                        } else {
                            View viewGroup2 = null;
                        }
                        if (viewGroup2 != null) {
                            Object obj2;
                            Fragment fragment = containerTransition.a;
                            Fragment fragment2 = containerTransition.d;
                            boolean z2 = containerTransition.b;
                            z = containerTransition.e;
                            arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            Object a = a(fragment, z2);
                            b = b(fragment2, z);
                            final Fragment fragment3 = containerTransition.a;
                            final Fragment fragment4 = containerTransition.d;
                            if (fragment3 != null) {
                                fragment3.getView().setVisibility(0);
                            }
                            if (fragment3 == null || fragment4 == null) {
                                obj2 = null;
                            } else {
                                Object obj3;
                                final boolean z3 = containerTransition.b;
                                if (nameOverrides.isEmpty()) {
                                    obj = null;
                                } else {
                                    obj = a(fragment3, fragment4, z3);
                                }
                                android.support.v4.util.a b4 = b((android.support.v4.util.a) nameOverrides, obj, containerTransition);
                                final android.support.v4.util.a c = c(nameOverrides, obj, containerTransition);
                                if (nameOverrides.isEmpty()) {
                                    obj3 = null;
                                    if (b4 != null) {
                                        b4.clear();
                                    }
                                    if (c != null) {
                                        c.clear();
                                    }
                                } else {
                                    a(arrayList2, b4, nameOverrides.keySet());
                                    a(arrayList, c, nameOverrides.values());
                                    obj3 = obj;
                                }
                                if (a == null && b == null && obj3 == null) {
                                    obj2 = null;
                                } else {
                                    Rect rect;
                                    b(fragment3, fragment4, z3, b4);
                                    if (obj3 != null) {
                                        arrayList.add(view);
                                        m.a(obj3, view, arrayList2);
                                        a(obj3, b, b4, containerTransition.e, containerTransition.f);
                                        rect = new Rect();
                                        b2 = b(c, containerTransition, a, z3);
                                        if (b2 != null) {
                                            m.a(a, rect);
                                        }
                                    } else {
                                        rect = null;
                                        b2 = null;
                                    }
                                    ab.a(viewGroup2, new Runnable() {
                                        public final void run() {
                                            l.b(fragment3, fragment4, z3, c);
                                            if (b2 != null) {
                                                m.a(b2, rect);
                                            }
                                        }
                                    });
                                    obj2 = obj3;
                                }
                            }
                            if (a != null || obj2 != null || b != null) {
                                b3 = b(b, fragment2, arrayList2, view);
                                ArrayList b5 = b(a, fragment, arrayList, view);
                                a(b5, 4);
                                obj = a(a, b, obj2, fragment, z2);
                                if (obj != null) {
                                    if (fragment2 != null && b != null && fragment2.mAdded && fragment2.mHidden && fragment2.mHiddenChanged) {
                                        fragment2.setHideReplaced(true);
                                        ((Transition) b).addListener(new android.support.v4.app.m.AnonymousClass2(fragment2.getView(), b3));
                                        ab.a(fragment2.mContainer, new Runnable() {
                                            public final void run() {
                                                l.a(b3, 4);
                                            }
                                        });
                                    }
                                    ArrayList a2 = m.a(arrayList);
                                    m.a(obj, a, b5, b, b3, obj2, arrayList);
                                    m.a(viewGroup2, obj);
                                    m.a(viewGroup2, arrayList2, arrayList, a2, nameOverrides);
                                    a(b5, 0);
                                    m.a(obj2, arrayList2, arrayList);
                                }
                            }
                        }
                    } else {
                        View view2;
                        if (fragmentManager.n.a()) {
                            view2 = (ViewGroup) fragmentManager.n.a(containerId);
                        } else {
                            view2 = null;
                        }
                        if (view2 != null) {
                            Object obj4;
                            Fragment fragment5 = containerTransition.a;
                            Fragment fragment6 = containerTransition.d;
                            z = containerTransition.b;
                            boolean z4 = containerTransition.e;
                            final Object a3 = a(fragment5, z);
                            Object b6 = b(fragment6, z4);
                            final ArrayList arrayList3 = new ArrayList();
                            b3 = new ArrayList();
                            final Fragment fragment7 = containerTransition.a;
                            final Fragment fragment8 = containerTransition.d;
                            if (fragment7 == null || fragment8 == null) {
                                obj4 = null;
                            } else {
                                Object obj5;
                                final boolean z5 = containerTransition.b;
                                if (nameOverrides.isEmpty()) {
                                    obj = null;
                                } else {
                                    obj = a(fragment7, fragment8, z5);
                                }
                                android.support.v4.util.a b7 = b((android.support.v4.util.a) nameOverrides, obj, containerTransition);
                                if (nameOverrides.isEmpty()) {
                                    obj5 = null;
                                } else {
                                    arrayList3.addAll(b7.values());
                                    obj5 = obj;
                                }
                                if (a3 == null && b6 == null && obj5 == null) {
                                    obj4 = null;
                                } else {
                                    Rect rect2;
                                    b(fragment7, fragment8, z5, b7);
                                    if (obj5 != null) {
                                        rect2 = new Rect();
                                        m.a(obj5, view, arrayList3);
                                        a(obj5, b6, b7, containerTransition.e, containerTransition.f);
                                        if (a3 != null) {
                                            m.a(a3, rect2);
                                        }
                                    } else {
                                        rect2 = null;
                                    }
                                    final android.support.v4.util.a<String, String> aVar = nameOverrides;
                                    final a aVar2 = containerTransition;
                                    b2 = view;
                                    ab.a(view2, new Runnable() {
                                        public final void run() {
                                            android.support.v4.util.a inSharedElements = l.c(aVar, obj5, aVar2);
                                            if (inSharedElements != null) {
                                                b3.addAll(inSharedElements.values());
                                                b3.add(b2);
                                            }
                                            l.b(fragment7, fragment8, z5, inSharedElements);
                                            if (obj5 != null) {
                                                m.a(obj5, arrayList3, b3);
                                                View inEpicenterView = l.b(inSharedElements, aVar2, a3, z5);
                                                if (inEpicenterView != null) {
                                                    m.a(inEpicenterView, rect2);
                                                }
                                            }
                                        }
                                    });
                                    obj4 = obj5;
                                }
                            }
                            if (a3 != null || obj4 != null || b6 != null) {
                                arrayList = b(b6, fragment6, arrayList3, view);
                                if (arrayList == null || arrayList.isEmpty()) {
                                    b6 = null;
                                }
                                if (a3 != null) {
                                    ((Transition) a3).addTarget(view);
                                }
                                Object a4 = a(a3, b6, obj4, fragment5, containerTransition.b);
                                if (a4 != null) {
                                    ArrayList arrayList4 = new ArrayList();
                                    m.a(a4, a3, arrayList4, b6, arrayList, obj4, b3);
                                    final Object obj6 = a3;
                                    final View view3 = view;
                                    final Fragment fragment9 = fragment5;
                                    final ArrayList arrayList5 = arrayList4;
                                    final ArrayList arrayList6 = arrayList;
                                    b = b6;
                                    ab.a(view2, new Runnable() {
                                        public final void run() {
                                            if (obj6 != null) {
                                                Object obj = obj6;
                                                View view = view3;
                                                if (obj != null) {
                                                    ((Transition) obj).removeTarget(view);
                                                }
                                                arrayList5.addAll(l.b(obj6, fragment9, b3, view3));
                                            }
                                            if (arrayList6 != null) {
                                                if (b != null) {
                                                    ArrayList<View> tempExiting = new ArrayList();
                                                    tempExiting.add(view3);
                                                    m.b(b, arrayList6, tempExiting);
                                                }
                                                arrayList6.clear();
                                                arrayList6.add(view3);
                                            }
                                        }
                                    });
                                    ab.a(view2, new android.support.v4.app.m.AnonymousClass4(b3, nameOverrides));
                                    m.a((ViewGroup) view2, a4);
                                    ab.a(view2, new AnonymousClass7(b3, nameOverrides));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static android.support.v4.util.a<String, String> a(int containerId, ArrayList<b> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        android.support.v4.util.a<String, String> nameOverrides = new android.support.v4.util.a();
        for (int recordNum = endIndex - 1; recordNum >= startIndex; recordNum--) {
            b record = (b) records.get(recordNum);
            if (record.b(containerId)) {
                boolean isPop = ((Boolean) isRecordPop.get(recordNum)).booleanValue();
                if (record.s != null) {
                    ArrayList<String> targets;
                    ArrayList<String> sources;
                    int numSharedElements = record.s.size();
                    if (isPop) {
                        targets = record.s;
                        sources = record.t;
                    } else {
                        sources = record.s;
                        targets = record.t;
                    }
                    for (int i = 0; i < numSharedElements; i++) {
                        String sourceName = (String) sources.get(i);
                        String targetName = (String) targets.get(i);
                        String previousTarget = (String) nameOverrides.remove(targetName);
                        if (previousTarget != null) {
                            nameOverrides.put(sourceName, previousTarget);
                        } else {
                            nameOverrides.put(sourceName, targetName);
                        }
                    }
                }
            }
        }
        return nameOverrides;
    }

    @RequiresApi(21)
    private static Object a(Fragment inFragment, Fragment outFragment, boolean isPop) {
        if (inFragment == null || outFragment == null) {
            return null;
        }
        Object sharedElementReturnTransition;
        if (isPop) {
            sharedElementReturnTransition = outFragment.getSharedElementReturnTransition();
        } else {
            sharedElementReturnTransition = inFragment.getSharedElementEnterTransition();
        }
        sharedElementReturnTransition = m.a(sharedElementReturnTransition);
        if (sharedElementReturnTransition == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) sharedElementReturnTransition);
        return transitionSet;
    }

    @RequiresApi(21)
    private static Object a(Fragment inFragment, boolean isPop) {
        if (inFragment == null) {
            return null;
        }
        Object reenterTransition;
        if (isPop) {
            reenterTransition = inFragment.getReenterTransition();
        } else {
            reenterTransition = inFragment.getEnterTransition();
        }
        return m.a(reenterTransition);
    }

    @RequiresApi(21)
    private static Object b(Fragment outFragment, boolean isPop) {
        if (outFragment == null) {
            return null;
        }
        Object returnTransition;
        if (isPop) {
            returnTransition = outFragment.getReturnTransition();
        } else {
            returnTransition = outFragment.getExitTransition();
        }
        return m.a(returnTransition);
    }

    private static void a(ArrayList<View> views, android.support.v4.util.a<String, View> sharedElements, Collection<String> nameOverridesSet) {
        for (int i = sharedElements.size() - 1; i >= 0; i--) {
            View view = (View) sharedElements.c(i);
            if (nameOverridesSet.contains(ViewCompat.r(view))) {
                views.add(view);
            }
        }
    }

    @RequiresApi(21)
    private static android.support.v4.util.a<String, View> b(android.support.v4.util.a<String, String> nameOverrides, Object sharedElementTransition, a fragments) {
        if (nameOverrides.isEmpty() || sharedElementTransition == null) {
            nameOverrides.clear();
            return null;
        }
        ag sharedElementCallback;
        ArrayList<String> names;
        Fragment outFragment = fragments.d;
        Map outSharedElements = new android.support.v4.util.a();
        m.a(outSharedElements, outFragment.getView());
        b outTransaction = fragments.f;
        if (fragments.e) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
            names = outTransaction.t;
        } else {
            sharedElementCallback = outFragment.getExitTransitionCallback();
            names = outTransaction.s;
        }
        outSharedElements.a(names);
        if (sharedElementCallback != null) {
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = (String) names.get(i);
                View view = (View) outSharedElements.get(name);
                if (view == null) {
                    nameOverrides.remove(name);
                } else if (!name.equals(ViewCompat.r(view))) {
                    nameOverrides.put(ViewCompat.r(view), (String) nameOverrides.remove(name));
                }
            }
            return outSharedElements;
        }
        nameOverrides.a(outSharedElements.keySet());
        return outSharedElements;
    }

    @RequiresApi(21)
    private static android.support.v4.util.a<String, View> c(android.support.v4.util.a<String, String> nameOverrides, Object sharedElementTransition, a fragments) {
        Fragment inFragment = fragments.a;
        View fragmentView = inFragment.getView();
        if (nameOverrides.isEmpty() || sharedElementTransition == null || fragmentView == null) {
            nameOverrides.clear();
            return null;
        }
        ag sharedElementCallback;
        ArrayList<String> names;
        Map inSharedElements = new android.support.v4.util.a();
        m.a(inSharedElements, fragmentView);
        b inTransaction = fragments.c;
        if (fragments.b) {
            sharedElementCallback = inFragment.getExitTransitionCallback();
            names = inTransaction.s;
        } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
            names = inTransaction.t;
        }
        if (names != null) {
            inSharedElements.a(names);
        }
        if (sharedElementCallback != null) {
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = (String) names.get(i);
                View view = (View) inSharedElements.get(name);
                String key;
                if (view == null) {
                    key = a((android.support.v4.util.a) nameOverrides, name);
                    if (key != null) {
                        nameOverrides.remove(key);
                    }
                } else if (!name.equals(ViewCompat.r(view))) {
                    key = a((android.support.v4.util.a) nameOverrides, name);
                    if (key != null) {
                        nameOverrides.put(key, ViewCompat.r(view));
                    }
                }
            }
            return inSharedElements;
        }
        for (int size = nameOverrides.size() - 1; size >= 0; size--) {
            if (!inSharedElements.containsKey((String) nameOverrides.c(size))) {
                nameOverrides.d(size);
            }
        }
        return inSharedElements;
    }

    private static String a(android.support.v4.util.a<String, String> map, String value) {
        int numElements = map.size();
        for (int i = 0; i < numElements; i++) {
            if (value.equals(map.c(i))) {
                return (String) map.b(i);
            }
        }
        return null;
    }

    private static View b(android.support.v4.util.a<String, View> inSharedElements, a fragments, Object enterTransition, boolean inIsPop) {
        b inTransaction = fragments.c;
        if (enterTransition == null || inSharedElements == null || inTransaction.s == null || inTransaction.s.isEmpty()) {
            return null;
        }
        String targetName;
        if (inIsPop) {
            targetName = (String) inTransaction.s.get(0);
        } else {
            targetName = (String) inTransaction.t.get(0);
        }
        return (View) inSharedElements.get(targetName);
    }

    @RequiresApi(21)
    private static void a(Object sharedElementTransition, Object exitTransition, android.support.v4.util.a<String, View> outSharedElements, boolean outIsPop, b outTransaction) {
        if (outTransaction.s != null && !outTransaction.s.isEmpty()) {
            String sourceName;
            if (outIsPop) {
                sourceName = (String) outTransaction.t.get(0);
            } else {
                sourceName = (String) outTransaction.s.get(0);
            }
            View outEpicenterView = (View) outSharedElements.get(sourceName);
            m.a(sharedElementTransition, outEpicenterView);
            if (exitTransition != null) {
                m.a(exitTransition, outEpicenterView);
            }
        }
    }

    private static void b(Fragment inFragment, Fragment outFragment, boolean isPop, android.support.v4.util.a<String, View> sharedElements) {
        ag enterTransitionCallback;
        if (isPop) {
            enterTransitionCallback = outFragment.getEnterTransitionCallback();
        } else {
            enterTransitionCallback = inFragment.getEnterTransitionCallback();
        }
        if (enterTransitionCallback != null) {
            ArrayList<View> views = new ArrayList();
            ArrayList<String> names = new ArrayList();
            int count = sharedElements == null ? 0 : sharedElements.size();
            for (int i = 0; i < count; i++) {
                names.add(sharedElements.b(i));
                views.add(sharedElements.c(i));
            }
        }
    }

    @RequiresApi(21)
    private static ArrayList<View> b(Object transition, Fragment fragment, ArrayList<View> sharedElements, View nonExistentView) {
        ArrayList<View> viewList = null;
        if (transition != null) {
            viewList = new ArrayList();
            View root = fragment.getView();
            if (root != null) {
                m.a((ArrayList) viewList, root);
            }
            if (sharedElements != null) {
                viewList.removeAll(sharedElements);
            }
            if (!viewList.isEmpty()) {
                viewList.add(nonExistentView);
                m.a(transition, (ArrayList) viewList);
            }
        }
        return viewList;
    }

    private static void a(ArrayList<View> views, int visibility) {
        if (views != null) {
            for (int i = views.size() - 1; i >= 0; i--) {
                ((View) views.get(i)).setVisibility(visibility);
            }
        }
    }

    @RequiresApi(21)
    private static Object a(Object enterTransition, Object exitTransition, Object sharedElementTransition, Fragment inFragment, boolean isPop) {
        boolean overlap = true;
        if (!(enterTransition == null || exitTransition == null || inFragment == null)) {
            overlap = isPop ? inFragment.getAllowReturnTransitionOverlap() : inFragment.getAllowEnterTransitionOverlap();
        }
        Object transitionSet;
        if (overlap) {
            transitionSet = new TransitionSet();
            if (exitTransition != null) {
                transitionSet.addTransition((Transition) exitTransition);
            }
            if (enterTransition != null) {
                transitionSet.addTransition((Transition) enterTransition);
            }
            if (sharedElementTransition == null) {
                return transitionSet;
            }
            transitionSet.addTransition((Transition) sharedElementTransition);
            return transitionSet;
        }
        Transition transition = null;
        Transition transition2 = (Transition) exitTransition;
        Transition transition3 = (Transition) enterTransition;
        Transition transition4 = (Transition) sharedElementTransition;
        if (transition2 != null && transition3 != null) {
            transition = new TransitionSet().addTransition(transition2).addTransition(transition3).setOrdering(1);
        } else if (transition2 != null) {
            transition = transition2;
        } else if (transition3 != null) {
            transition = transition3;
        }
        if (transition4 == null) {
            return transition;
        }
        transitionSet = new TransitionSet();
        if (transition != null) {
            transitionSet.addTransition(transition);
        }
        transitionSet.addTransition(transition4);
        return transitionSet;
    }

    private static void a(b transaction, SparseArray<a> transitioningFragments, boolean isReordered) {
        int numOps = transaction.c.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            a(transaction, (a) transaction.c.get(opNum), (SparseArray) transitioningFragments, false, isReordered);
        }
    }

    private static void b(b transaction, SparseArray<a> transitioningFragments, boolean isReordered) {
        if (transaction.b.n.a()) {
            for (int opNum = transaction.c.size() - 1; opNum >= 0; opNum--) {
                a(transaction, (a) transaction.c.get(opNum), (SparseArray) transitioningFragments, true, isReordered);
            }
        }
    }

    private static void a(b transaction, a op, SparseArray<a> transitioningFragments, boolean isPop, boolean isReorderedTransaction) {
        Fragment fragment = op.b;
        if (fragment != null) {
            int containerId = fragment.mContainerId;
            if (containerId != 0) {
                boolean setLastIn = false;
                boolean wasRemoved = false;
                boolean setFirstOut = false;
                boolean wasAdded = false;
                switch (isPop ? a[op.a] : op.a) {
                    case 1:
                    case 7:
                        setLastIn = isReorderedTransaction ? fragment.mIsNewlyAdded : (fragment.mAdded || fragment.mHidden) ? false : true;
                        wasAdded = true;
                        break;
                    case 3:
                    case 6:
                        setFirstOut = isReorderedTransaction ? !fragment.mAdded && fragment.mView != null && fragment.mView.getVisibility() == 0 && fragment.mPostponedAlpha >= 0.0f : fragment.mAdded && !fragment.mHidden;
                        wasRemoved = true;
                        break;
                    case 4:
                        setFirstOut = isReorderedTransaction ? fragment.mHiddenChanged && fragment.mAdded && fragment.mHidden : fragment.mAdded && !fragment.mHidden;
                        wasRemoved = true;
                        break;
                    case 5:
                        setLastIn = isReorderedTransaction ? fragment.mHiddenChanged && !fragment.mHidden && fragment.mAdded : fragment.mHidden;
                        wasAdded = true;
                        break;
                }
                a containerTransition = (a) transitioningFragments.get(containerId);
                if (setLastIn) {
                    containerTransition = a(containerTransition, (SparseArray) transitioningFragments, containerId);
                    containerTransition.a = fragment;
                    containerTransition.b = isPop;
                    containerTransition.c = transaction;
                }
                if (!isReorderedTransaction && wasAdded) {
                    if (containerTransition != null && containerTransition.d == fragment) {
                        containerTransition.d = null;
                    }
                    j manager = transaction.b;
                    if (fragment.mState <= 0 && manager.l > 0 && !transaction.u) {
                        manager.b(fragment);
                        manager.a(fragment, 1, 0, 0, false);
                    }
                }
                if (setFirstOut && (containerTransition == null || containerTransition.d == null)) {
                    containerTransition = a(containerTransition, (SparseArray) transitioningFragments, containerId);
                    containerTransition.d = fragment;
                    containerTransition.e = isPop;
                    containerTransition.f = transaction;
                }
                if (!isReorderedTransaction && wasRemoved && containerTransition != null && containerTransition.a == fragment) {
                    containerTransition.a = null;
                }
            }
        }
    }

    private static a a(a containerTransition, SparseArray<a> transitioningFragments, int containerId) {
        if (containerTransition != null) {
            return containerTransition;
        }
        containerTransition = new a();
        transitioningFragments.put(containerId, containerTransition);
        return containerTransition;
    }
}
