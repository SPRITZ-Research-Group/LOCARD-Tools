package androidx.transition;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import defpackage.bu;
import defpackage.by;
import defpackage.eo;
import defpackage.hs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.xmlpull.v1.XmlPullParser;

public abstract class Transition implements Cloneable {
    static final boolean DBG = false;
    private static final int[] DEFAULT_MATCH_ORDER = new int[]{2, 1, 3, 4};
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() {
        public final Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    };
    private static ThreadLocal<bu<Animator, ab>> sRunningAnimators = new ThreadLocal();
    private ArrayList<Animator> mAnimators = new ArrayList();
    boolean mCanRemoveViews = false;
    ArrayList<Animator> mCurrentAnimators = new ArrayList();
    long mDuration = -1;
    private an mEndValues = new an();
    private ArrayList<am> mEndValuesList;
    private boolean mEnded = false;
    private ad mEpicenterCallback;
    private TimeInterpolator mInterpolator = null;
    private ArrayList<ae> mListeners = null;
    private int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    private String mName = getClass().getName();
    private bu<String, String> mNameOverrides;
    private int mNumInstances = 0;
    TransitionSet mParent = null;
    private PathMotion mPathMotion = STRAIGHT_PATH_MOTION;
    private boolean mPaused = false;
    ai mPropagation;
    private ViewGroup mSceneRoot = null;
    private long mStartDelay = -1;
    private an mStartValues = new an();
    private ArrayList<am> mStartValuesList;
    private ArrayList<View> mTargetChildExcludes = null;
    private ArrayList<View> mTargetExcludes = null;
    private ArrayList<Integer> mTargetIdChildExcludes = null;
    private ArrayList<Integer> mTargetIdExcludes = null;
    ArrayList<Integer> mTargetIds = new ArrayList();
    private ArrayList<String> mTargetNameExcludes = null;
    private ArrayList<String> mTargetNames = null;
    private ArrayList<Class> mTargetTypeChildExcludes = null;
    private ArrayList<Class> mTargetTypeExcludes = null;
    private ArrayList<Class> mTargetTypes = null;
    ArrayList<View> mTargets = new ArrayList();

    private static boolean isValidMatch(int i) {
        return i > 0 && i <= 4;
    }

    public abstract void captureEndValues(am amVar);

    public abstract void captureStartValues(am amVar);

    public Animator createAnimator(ViewGroup viewGroup, am amVar, am amVar2) {
        return null;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public Transition(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, aa.c);
        XmlPullParser xmlPullParser = (XmlResourceParser) attributeSet;
        long a = (long) eo.a(obtainStyledAttributes, xmlPullParser, "duration", 1, -1);
        if (a >= 0) {
            setDuration(a);
        }
        long a2 = (long) eo.a(obtainStyledAttributes, xmlPullParser, "startDelay", 2, -1);
        if (a2 > 0) {
            setStartDelay(a2);
        }
        int b = eo.b(obtainStyledAttributes, xmlPullParser, "interpolator", 0);
        if (b > 0) {
            setInterpolator(AnimationUtils.loadInterpolator(context, b));
        }
        String c = eo.c(obtainStyledAttributes, xmlPullParser, "matchOrder", 3);
        if (c != null) {
            setMatchOrder(parseMatchOrder(c));
        }
        obtainStyledAttributes.recycle();
    }

    private static int[] parseMatchOrder(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        Object obj = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if (MATCH_ID_STR.equalsIgnoreCase(trim)) {
                obj[i] = 3;
            } else if (MATCH_INSTANCE_STR.equalsIgnoreCase(trim)) {
                obj[i] = 1;
            } else if (MATCH_NAME_STR.equalsIgnoreCase(trim)) {
                obj[i] = 2;
            } else if (MATCH_ITEM_ID_STR.equalsIgnoreCase(trim)) {
                obj[i] = 4;
            } else if (trim.isEmpty()) {
                Object obj2 = new int[(obj.length - 1)];
                System.arraycopy(obj, 0, obj2, 0, i);
                i--;
                obj = obj2;
            } else {
                StringBuilder stringBuilder = new StringBuilder("Unknown match type in matchOrder: '");
                stringBuilder.append(trim);
                stringBuilder.append("'");
                throw new InflateException(stringBuilder.toString());
            }
            i++;
        }
        return obj;
    }

    public Transition setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public Transition setStartDelay(long j) {
        this.mStartDelay = j;
        return this;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public Transition setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    public void setMatchOrder(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            this.mMatchOrder = DEFAULT_MATCH_ORDER;
            return;
        }
        int i = 0;
        while (i < iArr.length) {
            if (!isValidMatch(iArr[i])) {
                throw new IllegalArgumentException("matches contains invalid value");
            } else if (alreadyContains(iArr, i)) {
                throw new IllegalArgumentException("matches contains a duplicate value");
            } else {
                i++;
            }
        }
        this.mMatchOrder = (int[]) iArr.clone();
    }

    private static boolean alreadyContains(int[] iArr, int i) {
        int i2 = iArr[i];
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    private void matchInstances(bu<View, am> buVar, bu<View, am> buVar2) {
        for (int size = buVar.size() - 1; size >= 0; size--) {
            View view = (View) buVar.b(size);
            if (view != null && isValidTarget(view)) {
                am amVar = (am) buVar2.remove(view);
                if (!(amVar == null || amVar.b == null || !isValidTarget(amVar.b))) {
                    this.mStartValuesList.add((am) buVar.d(size));
                    this.mEndValuesList.add(amVar);
                }
            }
        }
    }

    private void matchItemIds(bu<View, am> buVar, bu<View, am> buVar2, by<View> byVar, by<View> byVar2) {
        int b = byVar.b();
        for (int i = 0; i < b; i++) {
            View view = (View) byVar.c(i);
            if (view != null && isValidTarget(view)) {
                View view2 = (View) byVar2.a(byVar.b(i));
                if (view2 != null && isValidTarget(view2)) {
                    am amVar = (am) buVar.get(view);
                    am amVar2 = (am) buVar2.get(view2);
                    if (!(amVar == null || amVar2 == null)) {
                        this.mStartValuesList.add(amVar);
                        this.mEndValuesList.add(amVar2);
                        buVar.remove(view);
                        buVar2.remove(view2);
                    }
                }
            }
        }
    }

    private void matchIds(bu<View, am> buVar, bu<View, am> buVar2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View view = (View) sparseArray.valueAt(i);
            if (view != null && isValidTarget(view)) {
                View view2 = (View) sparseArray2.get(sparseArray.keyAt(i));
                if (view2 != null && isValidTarget(view2)) {
                    am amVar = (am) buVar.get(view);
                    am amVar2 = (am) buVar2.get(view2);
                    if (!(amVar == null || amVar2 == null)) {
                        this.mStartValuesList.add(amVar);
                        this.mEndValuesList.add(amVar2);
                        buVar.remove(view);
                        buVar2.remove(view2);
                    }
                }
            }
        }
    }

    private void matchNames(bu<View, am> buVar, bu<View, am> buVar2, bu<String, View> buVar3, bu<String, View> buVar4) {
        int size = buVar3.size();
        for (int i = 0; i < size; i++) {
            View view = (View) buVar3.c(i);
            if (view != null && isValidTarget(view)) {
                View view2 = (View) buVar4.get(buVar3.b(i));
                if (view2 != null && isValidTarget(view2)) {
                    am amVar = (am) buVar.get(view);
                    am amVar2 = (am) buVar2.get(view2);
                    if (!(amVar == null || amVar2 == null)) {
                        this.mStartValuesList.add(amVar);
                        this.mEndValuesList.add(amVar2);
                        buVar.remove(view);
                        buVar2.remove(view2);
                    }
                }
            }
        }
    }

    private void addUnmatched(bu<View, am> buVar, bu<View, am> buVar2) {
        for (int i = 0; i < buVar.size(); i++) {
            am amVar = (am) buVar.c(i);
            if (isValidTarget(amVar.b)) {
                this.mStartValuesList.add(amVar);
                this.mEndValuesList.add(null);
            }
        }
        for (int i2 = 0; i2 < buVar2.size(); i2++) {
            am amVar2 = (am) buVar2.c(i2);
            if (isValidTarget(amVar2.b)) {
                this.mEndValuesList.add(amVar2);
                this.mStartValuesList.add(null);
            }
        }
    }

    private void matchStartAndEnd(an anVar, an anVar2) {
        bu buVar = new bu(anVar.a);
        bu buVar2 = new bu(anVar2.a);
        for (int i : this.mMatchOrder) {
            switch (i) {
                case 1:
                    matchInstances(buVar, buVar2);
                    break;
                case 2:
                    matchNames(buVar, buVar2, anVar.d, anVar2.d);
                    break;
                case 3:
                    matchIds(buVar, buVar2, anVar.b, anVar2.b);
                    break;
                case 4:
                    matchItemIds(buVar, buVar2, anVar.c, anVar2.c);
                    break;
                default:
                    break;
            }
        }
        addUnmatched(buVar, buVar2);
    }

    protected void createAnimators(ViewGroup viewGroup, an anVar, an anVar2, ArrayList<am> arrayList, ArrayList<am> arrayList2) {
        Transition transition = this;
        ViewGroup viewGroup2 = viewGroup;
        bu runningAnimators = getRunningAnimators();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        long j = Long.MAX_VALUE;
        int i = 0;
        while (i < size) {
            int i2;
            int i3;
            am amVar = (am) arrayList.get(i);
            am amVar2 = (am) arrayList2.get(i);
            if (!(amVar == null || amVar.c.contains(transition))) {
                amVar = null;
            }
            if (!(amVar2 == null || amVar2.c.contains(transition))) {
                amVar2 = null;
            }
            if (!(amVar == null && amVar2 == null)) {
                Object obj = (amVar == null || amVar2 == null || isTransitionRequired(amVar, amVar2)) ? 1 : null;
                if (obj != null) {
                    Animator createAnimator = createAnimator(viewGroup2, amVar, amVar2);
                    if (createAnimator != null) {
                        View view;
                        am amVar3;
                        Object obj2;
                        Animator animator;
                        if (amVar2 != null) {
                            am amVar4;
                            view = amVar2.b;
                            String[] transitionProperties = getTransitionProperties();
                            if (view != null && transitionProperties != null && transitionProperties.length > 0) {
                                amVar4 = new am();
                                amVar4.b = view;
                                animator = createAnimator;
                                i2 = size;
                                amVar3 = (am) anVar2.a.get(view);
                                if (amVar3 != null) {
                                    size = 0;
                                    while (size < transitionProperties.length) {
                                        i3 = i;
                                        am amVar5 = amVar3;
                                        amVar4.a.put(transitionProperties[size], amVar3.a.get(transitionProperties[size]));
                                        size++;
                                        i = i3;
                                        amVar3 = amVar5;
                                        ArrayList<am> arrayList3 = arrayList2;
                                    }
                                }
                                i3 = i;
                                int size2 = runningAnimators.size();
                                for (int i4 = 0; i4 < size2; i4++) {
                                    ab abVar = (ab) runningAnimators.get((Animator) runningAnimators.b(i4));
                                    if (abVar.c != null && abVar.a == view && abVar.b.equals(getName()) && abVar.c.equals(amVar4)) {
                                        amVar3 = amVar4;
                                        obj2 = null;
                                        break;
                                    }
                                }
                            } else {
                                animator = createAnimator;
                                i2 = size;
                                i3 = i;
                                amVar4 = null;
                            }
                            amVar3 = amVar4;
                            obj2 = animator;
                        } else {
                            animator = createAnimator;
                            i2 = size;
                            i3 = i;
                            view = amVar.b;
                            obj2 = animator;
                            amVar3 = null;
                        }
                        if (obj2 != null) {
                            if (transition.mPropagation != null) {
                                long a = transition.mPropagation.a(viewGroup2, transition, amVar, amVar2);
                                sparseIntArray.put(transition.mAnimators.size(), (int) a);
                                j = Math.min(a, j);
                            }
                            long j2 = j;
                            runningAnimators.put(obj2, new ab(view, getName(), this, ba.b(viewGroup), amVar3));
                            transition.mAnimators.add(obj2);
                            j = j2;
                        }
                        i = i3 + 1;
                        size = i2;
                    }
                }
            }
            i2 = size;
            i3 = i;
            i = i3 + 1;
            size = i2;
        }
        if (j != 0) {
            for (int i5 = 0; i5 < sparseIntArray.size(); i5++) {
                Animator animator2 = (Animator) transition.mAnimators.get(sparseIntArray.keyAt(i5));
                animator2.setStartDelay((((long) sparseIntArray.valueAt(i5)) - j) + animator2.getStartDelay());
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean isValidTarget(View view) {
        int id = view.getId();
        if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf(id))) {
            return false;
        }
        if (this.mTargetExcludes != null && this.mTargetExcludes.contains(view)) {
            return false;
        }
        if (this.mTargetTypeExcludes != null) {
            int size = this.mTargetTypeExcludes.size();
            for (int i = 0; i < size; i++) {
                if (((Class) this.mTargetTypeExcludes.get(i)).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.mTargetNameExcludes != null && hs.r(view) != null && this.mTargetNameExcludes.contains(hs.r(view))) {
            return false;
        }
        if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && ((this.mTargetTypes == null || this.mTargetTypes.isEmpty()) && (this.mTargetNames == null || this.mTargetNames.isEmpty()))) || this.mTargetIds.contains(Integer.valueOf(id)) || this.mTargets.contains(view)) {
            return true;
        }
        if (this.mTargetNames != null && this.mTargetNames.contains(hs.r(view))) {
            return true;
        }
        if (this.mTargetTypes != null) {
            for (id = 0; id < this.mTargetTypes.size(); id++) {
                if (((Class) this.mTargetTypes.get(id)).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static bu<Animator, ab> getRunningAnimators() {
        bu<Animator, ab> buVar = (bu) sRunningAnimators.get();
        if (buVar != null) {
            return buVar;
        }
        buVar = new bu();
        sRunningAnimators.set(buVar);
        return buVar;
    }

    protected void runAnimators() {
        start();
        bu runningAnimators = getRunningAnimators();
        Iterator it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator animator = (Animator) it.next();
            if (runningAnimators.containsKey(animator)) {
                start();
                runAnimator(animator, runningAnimators);
            }
        }
        this.mAnimators.clear();
        end();
    }

    private void runAnimator(Animator animator, final bu<Animator, ab> buVar) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ Transition b;

                public final void onAnimationStart(Animator animator) {
                    this.b.mCurrentAnimators.add(animator);
                }

                public final void onAnimationEnd(Animator animator) {
                    buVar.remove(animator);
                    this.b.mCurrentAnimators.remove(animator);
                }
            });
            animate(animator);
        }
    }

    public Transition addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    public Transition addTarget(int i) {
        if (i != 0) {
            this.mTargetIds.add(Integer.valueOf(i));
        }
        return this;
    }

    public Transition addTarget(String str) {
        if (this.mTargetNames == null) {
            this.mTargetNames = new ArrayList();
        }
        this.mTargetNames.add(str);
        return this;
    }

    public Transition addTarget(Class cls) {
        if (this.mTargetTypes == null) {
            this.mTargetTypes = new ArrayList();
        }
        this.mTargetTypes.add(cls);
        return this;
    }

    public Transition removeTarget(View view) {
        this.mTargets.remove(view);
        return this;
    }

    public Transition removeTarget(int i) {
        if (i != 0) {
            this.mTargetIds.remove(Integer.valueOf(i));
        }
        return this;
    }

    public Transition removeTarget(String str) {
        if (this.mTargetNames != null) {
            this.mTargetNames.remove(str);
        }
        return this;
    }

    public Transition removeTarget(Class cls) {
        if (this.mTargetTypes != null) {
            this.mTargetTypes.remove(cls);
        }
        return this;
    }

    private static <T> ArrayList<T> excludeObject(ArrayList<T> arrayList, T t, boolean z) {
        if (t == null) {
            return arrayList;
        }
        if (z) {
            return ac.a(arrayList, t);
        }
        return ac.b(arrayList, t);
    }

    public Transition excludeTarget(View view, boolean z) {
        this.mTargetExcludes = excludeView(this.mTargetExcludes, view, z);
        return this;
    }

    public Transition excludeTarget(int i, boolean z) {
        this.mTargetIdExcludes = excludeId(this.mTargetIdExcludes, i, z);
        return this;
    }

    public Transition excludeTarget(String str, boolean z) {
        this.mTargetNameExcludes = excludeObject(this.mTargetNameExcludes, str, z);
        return this;
    }

    public Transition excludeChildren(View view, boolean z) {
        this.mTargetChildExcludes = excludeView(this.mTargetChildExcludes, view, z);
        return this;
    }

    public Transition excludeChildren(int i, boolean z) {
        this.mTargetIdChildExcludes = excludeId(this.mTargetIdChildExcludes, i, z);
        return this;
    }

    private ArrayList<Integer> excludeId(ArrayList<Integer> arrayList, int i, boolean z) {
        if (i <= 0) {
            return arrayList;
        }
        if (z) {
            return ac.a(arrayList, Integer.valueOf(i));
        }
        return ac.b(arrayList, Integer.valueOf(i));
    }

    private ArrayList<View> excludeView(ArrayList<View> arrayList, View view, boolean z) {
        if (view == null) {
            return arrayList;
        }
        if (z) {
            return ac.a(arrayList, view);
        }
        return ac.b(arrayList, view);
    }

    public Transition excludeTarget(Class cls, boolean z) {
        this.mTargetTypeExcludes = excludeType(this.mTargetTypeExcludes, cls, z);
        return this;
    }

    public Transition excludeChildren(Class cls, boolean z) {
        this.mTargetTypeChildExcludes = excludeType(this.mTargetTypeChildExcludes, cls, z);
        return this;
    }

    private ArrayList<Class> excludeType(ArrayList<Class> arrayList, Class cls, boolean z) {
        if (cls == null) {
            return arrayList;
        }
        if (z) {
            return ac.a(arrayList, cls);
        }
        return ac.b(arrayList, cls);
    }

    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    public List<View> getTargets() {
        return this.mTargets;
    }

    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    public List<Class> getTargetTypes() {
        return this.mTargetTypes;
    }

    void captureValues(ViewGroup viewGroup, boolean z) {
        int i;
        int i2;
        View view;
        clearValues(z);
        if ((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && ((this.mTargetNames == null || this.mTargetNames.isEmpty()) && (this.mTargetTypes == null || this.mTargetTypes.isEmpty()))) {
            for (i = 0; i < this.mTargetIds.size(); i++) {
                View findViewById = viewGroup.findViewById(((Integer) this.mTargetIds.get(i)).intValue());
                if (findViewById != null) {
                    am amVar = new am();
                    amVar.b = findViewById;
                    if (z) {
                        captureStartValues(amVar);
                    } else {
                        captureEndValues(amVar);
                    }
                    amVar.c.add(this);
                    capturePropagationValues(amVar);
                    if (z) {
                        addViewValues(this.mStartValues, findViewById, amVar);
                    } else {
                        addViewValues(this.mEndValues, findViewById, amVar);
                    }
                }
            }
            for (i2 = 0; i2 < this.mTargets.size(); i2++) {
                view = (View) this.mTargets.get(i2);
                am amVar2 = new am();
                amVar2.b = view;
                if (z) {
                    captureStartValues(amVar2);
                } else {
                    captureEndValues(amVar2);
                }
                amVar2.c.add(this);
                capturePropagationValues(amVar2);
                if (z) {
                    addViewValues(this.mStartValues, view, amVar2);
                } else {
                    addViewValues(this.mEndValues, view, amVar2);
                }
            }
        } else {
            captureHierarchy(viewGroup, z);
        }
        if (!z && this.mNameOverrides != null) {
            i2 = this.mNameOverrides.size();
            ArrayList arrayList = new ArrayList(i2);
            for (i = 0; i < i2; i++) {
                arrayList.add(this.mStartValues.d.remove((String) this.mNameOverrides.b(i)));
            }
            for (int i3 = 0; i3 < i2; i3++) {
                view = (View) arrayList.get(i3);
                if (view != null) {
                    this.mStartValues.d.put((String) this.mNameOverrides.c(i3), view);
                }
            }
        }
    }

    private static void addViewValues(an anVar, View view, am amVar) {
        anVar.a.put(view, amVar);
        int id = view.getId();
        if (id >= 0) {
            if (anVar.b.indexOfKey(id) >= 0) {
                anVar.b.put(id, null);
            } else {
                anVar.b.put(id, view);
            }
        }
        String r = hs.r(view);
        if (r != null) {
            if (anVar.d.containsKey(r)) {
                anVar.d.put(r, null);
            } else {
                anVar.d.put(r, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (anVar.c.d(itemIdAtPosition) >= 0) {
                    view = (View) anVar.c.a(itemIdAtPosition);
                    if (view != null) {
                        hs.a(view, false);
                        anVar.c.b(itemIdAtPosition, null);
                    }
                    return;
                }
                hs.a(view, true);
                anVar.c.b(itemIdAtPosition, view);
            }
        }
    }

    void clearValues(boolean z) {
        if (z) {
            this.mStartValues.a.clear();
            this.mStartValues.b.clear();
            this.mStartValues.c.c();
            return;
        }
        this.mEndValues.a.clear();
        this.mEndValues.b.clear();
        this.mEndValues.c.c();
    }

    private void captureHierarchy(View view, boolean z) {
        if (view != null) {
            int id = view.getId();
            if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf(id))) {
                return;
            }
            if (this.mTargetExcludes == null || !this.mTargetExcludes.contains(view)) {
                int size;
                if (this.mTargetTypeExcludes != null) {
                    size = this.mTargetTypeExcludes.size();
                    int i = 0;
                    while (i < size) {
                        if (!((Class) this.mTargetTypeExcludes.get(i)).isInstance(view)) {
                            i++;
                        } else {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    am amVar = new am();
                    amVar.b = view;
                    if (z) {
                        captureStartValues(amVar);
                    } else {
                        captureEndValues(amVar);
                    }
                    amVar.c.add(this);
                    capturePropagationValues(amVar);
                    if (z) {
                        addViewValues(this.mStartValues, view, amVar);
                    } else {
                        addViewValues(this.mEndValues, view, amVar);
                    }
                }
                if ((view instanceof ViewGroup) && (this.mTargetIdChildExcludes == null || !this.mTargetIdChildExcludes.contains(Integer.valueOf(id)))) {
                    if (this.mTargetChildExcludes == null || !this.mTargetChildExcludes.contains(view)) {
                        if (this.mTargetTypeChildExcludes != null) {
                            id = this.mTargetTypeChildExcludes.size();
                            size = 0;
                            while (size < id) {
                                if (!((Class) this.mTargetTypeChildExcludes.get(size)).isInstance(view)) {
                                    size++;
                                } else {
                                    return;
                                }
                            }
                        }
                        ViewGroup viewGroup = (ViewGroup) view;
                        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                            captureHierarchy(viewGroup.getChildAt(i2), z);
                        }
                    }
                }
            }
        }
    }

    public am getTransitionValues(View view, boolean z) {
        if (this.mParent != null) {
            return this.mParent.getTransitionValues(view, z);
        }
        return (am) (z ? this.mStartValues : this.mEndValues).a.get(view);
    }

    am getMatchedTransitionValues(View view, boolean z) {
        if (this.mParent != null) {
            return this.mParent.getMatchedTransitionValues(view, z);
        }
        ArrayList arrayList = z ? this.mStartValuesList : this.mEndValuesList;
        am amVar = null;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            am amVar2 = (am) arrayList.get(i2);
            if (amVar2 == null) {
                return null;
            }
            if (amVar2.b == view) {
                i = i2;
                break;
            }
        }
        if (i >= 0) {
            amVar = (am) (z ? this.mEndValuesList : this.mStartValuesList).get(i);
        }
        return amVar;
    }

    public void pause(View view) {
        if (!this.mEnded) {
            int i;
            bu runningAnimators = getRunningAnimators();
            int size = runningAnimators.size();
            bk b = ba.b(view);
            size--;
            while (true) {
                i = 0;
                if (size < 0) {
                    break;
                }
                ab abVar = (ab) runningAnimators.c(size);
                if (abVar.a != null && b.equals(abVar.d)) {
                    Animator animator = (Animator) runningAnimators.b(size);
                    if (VERSION.SDK_INT >= 19) {
                        animator.pause();
                    } else {
                        ArrayList listeners = animator.getListeners();
                        if (listeners != null) {
                            int size2 = listeners.size();
                            while (i < size2) {
                                AnimatorListener animatorListener = (AnimatorListener) listeners.get(i);
                                if (animatorListener instanceof b) {
                                    ((b) animatorListener).onAnimationPause(animator);
                                }
                                i++;
                            }
                        }
                    }
                }
                size--;
            }
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size3 = arrayList.size();
                while (i < size3) {
                    ((ae) arrayList.get(i)).b();
                    i++;
                }
            }
            this.mPaused = true;
        }
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                bu runningAnimators = getRunningAnimators();
                int size = runningAnimators.size();
                bk b = ba.b(view);
                for (size--; size >= 0; size--) {
                    ab abVar = (ab) runningAnimators.c(size);
                    if (abVar.a != null && b.equals(abVar.d)) {
                        Animator animator = (Animator) runningAnimators.b(size);
                        if (VERSION.SDK_INT >= 19) {
                            animator.resume();
                        } else {
                            ArrayList listeners = animator.getListeners();
                            if (listeners != null) {
                                int size2 = listeners.size();
                                for (int i = 0; i < size2; i++) {
                                    AnimatorListener animatorListener = (AnimatorListener) listeners.get(i);
                                    if (animatorListener instanceof b) {
                                        ((b) animatorListener).onAnimationResume(animator);
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.mListeners != null && this.mListeners.size() > 0) {
                    ArrayList arrayList = (ArrayList) this.mListeners.clone();
                    int size3 = arrayList.size();
                    for (size = 0; size < size3; size++) {
                        ((ae) arrayList.get(size)).c();
                    }
                }
            }
            this.mPaused = false;
        }
    }

    void playTransition(ViewGroup viewGroup) {
        this.mStartValuesList = new ArrayList();
        this.mEndValuesList = new ArrayList();
        matchStartAndEnd(this.mStartValues, this.mEndValues);
        bu runningAnimators = getRunningAnimators();
        int size = runningAnimators.size();
        bk b = ba.b(viewGroup);
        for (size--; size >= 0; size--) {
            Animator animator = (Animator) runningAnimators.b(size);
            if (animator != null) {
                ab abVar = (ab) runningAnimators.get(animator);
                if (!(abVar == null || abVar.a == null || !b.equals(abVar.d))) {
                    am amVar = abVar.c;
                    View view = abVar.a;
                    am transitionValues = getTransitionValues(view, true);
                    am matchedTransitionValues = getMatchedTransitionValues(view, true);
                    Object obj = (!(transitionValues == null && matchedTransitionValues == null) && abVar.e.isTransitionRequired(amVar, matchedTransitionValues)) ? 1 : null;
                    if (obj != null) {
                        if (animator.isRunning() || animator.isStarted()) {
                            animator.cancel();
                        } else {
                            runningAnimators.remove(animator);
                        }
                    }
                }
            }
        }
        createAnimators(viewGroup, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        runAnimators();
    }

    public boolean isTransitionRequired(am amVar, am amVar2) {
        if (!(amVar == null || amVar2 == null)) {
            String[] transitionProperties = getTransitionProperties();
            if (transitionProperties != null) {
                for (String isValueChanged : transitionProperties) {
                    if (isValueChanged(amVar, amVar2, isValueChanged)) {
                        return true;
                    }
                }
            } else {
                for (String isValueChanged2 : amVar.a.keySet()) {
                    if (isValueChanged(amVar, amVar2, isValueChanged2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isValueChanged(am amVar, am amVar2, String str) {
        Object obj = amVar.a.get(str);
        Object obj2 = amVar2.a.get(str);
        if ((obj != null || obj2 != null) && (obj == null || obj2 == null || !obj.equals(obj2))) {
            return true;
        }
        return false;
    }

    protected void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ Transition a;

            {
                this.a = r1;
            }

            public final void onAnimationEnd(Animator animator) {
                this.a.end();
                animator.removeListener(this);
            }
        });
        animator.start();
    }

    protected void start() {
        if (this.mNumInstances == 0) {
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((ae) arrayList.get(i)).d();
                }
            }
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    protected void end() {
        this.mNumInstances--;
        if (this.mNumInstances == 0) {
            int i;
            View view;
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((ae) arrayList.get(i2)).a(this);
                }
            }
            for (i = 0; i < this.mStartValues.c.b(); i++) {
                view = (View) this.mStartValues.c.c(i);
                if (view != null) {
                    hs.a(view, false);
                }
            }
            for (i = 0; i < this.mEndValues.c.b(); i++) {
                view = (View) this.mEndValues.c.c(i);
                if (view != null) {
                    hs.a(view, false);
                }
            }
            this.mEnded = true;
        }
    }

    void forceToEnd(ViewGroup viewGroup) {
        bu runningAnimators = getRunningAnimators();
        int size = runningAnimators.size();
        if (viewGroup != null) {
            bk b = ba.b(viewGroup);
            for (size--; size >= 0; size--) {
                ab abVar = (ab) runningAnimators.c(size);
                if (abVar.a != null && b.equals(abVar.d)) {
                    ((Animator) runningAnimators.b(size)).end();
                }
            }
        }
    }

    protected void cancel() {
        for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
            ((Animator) this.mCurrentAnimators.get(size)).cancel();
        }
        if (this.mListeners != null && this.mListeners.size() > 0) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size2 = arrayList.size();
            for (int i = 0; i < size2; i++) {
                ((ae) arrayList.get(i)).a();
            }
        }
    }

    public Transition addListener(ae aeVar) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(aeVar);
        return this;
    }

    public Transition removeListener(ae aeVar) {
        if (this.mListeners == null) {
            return this;
        }
        this.mListeners.remove(aeVar);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public void setPathMotion(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = pathMotion;
        }
    }

    public PathMotion getPathMotion() {
        return this.mPathMotion;
    }

    public void setEpicenterCallback(ad adVar) {
        this.mEpicenterCallback = adVar;
    }

    public ad getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public Rect getEpicenter() {
        if (this.mEpicenterCallback == null) {
            return null;
        }
        return this.mEpicenterCallback.a();
    }

    public void setPropagation(ai aiVar) {
        this.mPropagation = aiVar;
    }

    public ai getPropagation() {
        return this.mPropagation;
    }

    void capturePropagationValues(am amVar) {
        if (!(this.mPropagation == null || amVar.a.isEmpty())) {
            String[] a = this.mPropagation.a();
            if (a != null) {
                Object obj = null;
                for (Object containsKey : a) {
                    if (!amVar.a.containsKey(containsKey)) {
                        break;
                    }
                }
                obj = 1;
                if (obj == null) {
                    this.mPropagation.a(amVar);
                }
            }
        }
    }

    Transition setSceneRoot(ViewGroup viewGroup) {
        this.mSceneRoot = viewGroup;
        return this;
    }

    void setCanRemoveViews(boolean z) {
        this.mCanRemoveViews = z;
    }

    public String toString() {
        return toString("");
    }

    public androidx.transition.Transition clone() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.transition.Transition.clone():androidx.transition.Transition. bs: []
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
        r3 = this;
        r0 = 0;
        r1 = super.clone();	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r1 = (androidx.transition.Transition) r1;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r2 = new java.util.ArrayList;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r2.<init>();	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r1.mAnimators = r2;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r2 = new androidx.transition.an;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r2.<init>();	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r1.mStartValues = r2;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r2 = new androidx.transition.an;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r2.<init>();	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r1.mEndValues = r2;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r1.mStartValuesList = r0;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        r1.mEndValuesList = r0;	 Catch:{ CloneNotSupportedException -> 0x0021 }
        return r1;
    L_0x0021:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.clone():androidx.transition.Transition");
    }

    public String getName() {
        return this.mName;
    }

    String toString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append("@");
        stringBuilder.append(Integer.toHexString(hashCode()));
        stringBuilder.append(": ");
        str = stringBuilder.toString();
        if (this.mDuration != -1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("dur(");
            stringBuilder.append(this.mDuration);
            stringBuilder.append(") ");
            str = stringBuilder.toString();
        }
        if (this.mStartDelay != -1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("dly(");
            stringBuilder.append(this.mStartDelay);
            stringBuilder.append(") ");
            str = stringBuilder.toString();
        }
        if (this.mInterpolator != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("interp(");
            stringBuilder.append(this.mInterpolator);
            stringBuilder.append(") ");
            str = stringBuilder.toString();
        }
        if (this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) {
            return str;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("tgts(");
        str = stringBuilder.toString();
        if (this.mTargetIds.size() > 0) {
            String str2 = str;
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                StringBuilder stringBuilder2;
                if (i > 0) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str2);
                    stringBuilder2.append(", ");
                    str2 = stringBuilder2.toString();
                }
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(this.mTargetIds.get(i));
                str2 = stringBuilder2.toString();
            }
            str = str2;
        }
        if (this.mTargets.size() > 0) {
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                if (i2 > 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(", ");
                    str = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(this.mTargets.get(i2));
                str = stringBuilder.toString();
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
