package android.support.b.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.b.b;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class e {

    private static class a implements TypeEvaluator<b[]> {
        private b[] a;

        /* synthetic */ a(byte b) {
            this();
        }

        public final /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
            b[] bVarArr = (b[]) obj;
            b[] bVarArr2 = (b[]) obj2;
            if (android.support.v4.graphics.b.a(bVarArr, bVarArr2)) {
                if (this.a == null || !android.support.v4.graphics.b.a(this.a, bVarArr)) {
                    this.a = android.support.v4.graphics.b.a(bVarArr);
                }
                for (int i = 0; i < bVarArr.length; i++) {
                    this.a[i].a(bVarArr[i], bVarArr2[i], f);
                }
                return this.a;
            }
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }

        private a() {
        }
    }

    public static Animator a(Context context, Resources resources, Theme theme, @AnimatorRes int id) throws NotFoundException {
        NotFoundException rnf;
        XmlResourceParser parser = null;
        try {
            parser = resources.getAnimation(id);
            Animator a = a(context, resources, theme, parser, Xml.asAttributeSet(parser), null, 0, 1.0f);
            if (parser != null) {
                parser.close();
            }
            return a;
        } catch (XmlPullParserException ex) {
            rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(ex);
            throw rnf;
        } catch (IOException ex2) {
            rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(ex2);
            throw rnf;
        } catch (Throwable th) {
            if (parser != null) {
                parser.close();
            }
        }
    }

    private static PropertyValuesHolder a(TypedArray styledAttributes, int valueType, int valueFromId, int valueToId, String propertyName) {
        TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
        boolean hasFrom = tvFrom != null;
        int fromType = hasFrom ? tvFrom.type : 0;
        TypedValue tvTo = styledAttributes.peekValue(valueToId);
        boolean hasTo = tvTo != null;
        int toType = hasTo ? tvTo.type : 0;
        if (valueType == 4) {
            if ((hasFrom && a(fromType)) || (hasTo && a(toType))) {
                valueType = 3;
            } else {
                valueType = 0;
            }
        }
        boolean getFloats = valueType == 0;
        PropertyValuesHolder returnValue = null;
        TypeEvaluator evaluator;
        if (valueType == 2) {
            String fromString = styledAttributes.getString(valueFromId);
            String toString = styledAttributes.getString(valueToId);
            b[] nodesFrom = android.support.v4.graphics.b.b(fromString);
            b[] nodesTo = android.support.v4.graphics.b.b(toString);
            if (nodesFrom == null && nodesTo == null) {
                return null;
            }
            if (nodesFrom != null) {
                evaluator = new a((byte) 0);
                if (nodesTo == null) {
                    return PropertyValuesHolder.ofObject(propertyName, evaluator, new Object[]{nodesFrom});
                } else if (android.support.v4.graphics.b.a(nodesFrom, nodesTo)) {
                    return PropertyValuesHolder.ofObject(propertyName, evaluator, new Object[]{nodesFrom, nodesTo});
                } else {
                    throw new InflateException(" Can't morph from " + fromString + " to " + toString);
                }
            } else if (nodesTo == null) {
                return null;
            } else {
                return PropertyValuesHolder.ofObject(propertyName, new a((byte) 0), new Object[]{nodesTo});
            }
        }
        evaluator = null;
        if (valueType == 3) {
            evaluator = f.a();
        }
        int valueTo;
        if (getFloats) {
            float valueTo2;
            if (hasFrom) {
                float valueFrom;
                if (fromType == 5) {
                    valueFrom = styledAttributes.getDimension(valueFromId, 0.0f);
                } else {
                    valueFrom = styledAttributes.getFloat(valueFromId, 0.0f);
                }
                if (hasTo) {
                    if (toType == 5) {
                        valueTo2 = styledAttributes.getDimension(valueToId, 0.0f);
                    } else {
                        valueTo2 = styledAttributes.getFloat(valueToId, 0.0f);
                    }
                    returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueFrom, valueTo2});
                } else {
                    returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueFrom});
                }
            } else {
                if (toType == 5) {
                    valueTo2 = styledAttributes.getDimension(valueToId, 0.0f);
                } else {
                    valueTo2 = styledAttributes.getFloat(valueToId, 0.0f);
                }
                returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueTo2});
            }
        } else if (hasFrom) {
            int valueFrom2;
            if (fromType == 5) {
                valueFrom2 = (int) styledAttributes.getDimension(valueFromId, 0.0f);
            } else if (a(fromType)) {
                valueFrom2 = styledAttributes.getColor(valueFromId, 0);
            } else {
                valueFrom2 = styledAttributes.getInt(valueFromId, 0);
            }
            if (hasTo) {
                if (toType == 5) {
                    valueTo = (int) styledAttributes.getDimension(valueToId, 0.0f);
                } else if (a(toType)) {
                    valueTo = styledAttributes.getColor(valueToId, 0);
                } else {
                    valueTo = styledAttributes.getInt(valueToId, 0);
                }
                returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueFrom2, valueTo});
            } else {
                returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueFrom2});
            }
        } else if (hasTo) {
            if (toType == 5) {
                valueTo = (int) styledAttributes.getDimension(valueToId, 0.0f);
            } else if (a(toType)) {
                valueTo = styledAttributes.getColor(valueToId, 0);
            } else {
                valueTo = styledAttributes.getInt(valueToId, 0);
            }
            returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueTo});
        }
        if (returnValue == null || evaluator == null) {
            return returnValue;
        }
        returnValue.setEvaluator(evaluator);
        return returnValue;
    }

    private static void a(ValueAnimator anim, TypedArray arrayAnimator, TypedArray arrayObjectAnimator, float pixelSize, XmlPullParser parser) {
        long duration = (long) android.support.v4.content.res.b.a(arrayAnimator, parser, "duration", 1, 300);
        long startDelay = (long) android.support.v4.content.res.b.a(arrayAnimator, parser, "startOffset", 2, 0);
        int valueType = android.support.v4.content.res.b.a(arrayAnimator, parser, "valueType", 7, 4);
        if (android.support.v4.content.res.b.a(parser, "valueFrom") && android.support.v4.content.res.b.a(parser, "valueTo")) {
            if (valueType == 4) {
                TypedValue peekValue = arrayAnimator.peekValue(5);
                Object obj = peekValue != null ? 1 : null;
                int i = obj != null ? peekValue.type : 0;
                TypedValue peekValue2 = arrayAnimator.peekValue(6);
                Object obj2 = peekValue2 != null ? 1 : null;
                int i2 = obj2 != null ? peekValue2.type : 0;
                if ((obj == null || !a(i)) && (obj2 == null || !a(i2))) {
                    valueType = 0;
                } else {
                    valueType = 3;
                }
            }
            if (a(arrayAnimator, valueType, 5, 6, "") != null) {
                anim.setValues(new PropertyValuesHolder[]{a(arrayAnimator, valueType, 5, 6, "")});
            }
        }
        anim.setDuration(duration);
        anim.setStartDelay(startDelay);
        anim.setRepeatCount(android.support.v4.content.res.b.a(arrayAnimator, parser, "repeatCount", 3, 0));
        anim.setRepeatMode(android.support.v4.content.res.b.a(arrayAnimator, parser, "repeatMode", 4, 1));
        if (arrayObjectAnimator != null) {
            ObjectAnimator objectAnimator = (ObjectAnimator) anim;
            String b = android.support.v4.content.res.b.b(arrayObjectAnimator, parser, "pathData", 1);
            if (b != null) {
                String b2 = android.support.v4.content.res.b.b(arrayObjectAnimator, parser, "propertyXName", 2);
                String b3 = android.support.v4.content.res.b.b(arrayObjectAnimator, parser, "propertyYName", 3);
                if (b2 == null && b3 == null) {
                    throw new InflateException(arrayObjectAnimator.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
                }
                a(android.support.v4.graphics.b.a(b), objectAnimator, 0.5f * pixelSize, b2, b3);
                return;
            }
            objectAnimator.setPropertyName(android.support.v4.content.res.b.b(arrayObjectAnimator, parser, "propertyName", 0));
        }
    }

    private static void a(Path path, ObjectAnimator oa, float precision, String propertyXName, String propertyYName) {
        PathMeasure measureForTotalLength = new PathMeasure(path, false);
        float totalLength = 0.0f;
        ArrayList<Float> contourLengths = new ArrayList();
        contourLengths.add(Float.valueOf(0.0f));
        do {
            totalLength += measureForTotalLength.getLength();
            contourLengths.add(Float.valueOf(totalLength));
        } while (measureForTotalLength.nextContour());
        PathMeasure pathMeasure = new PathMeasure(path, false);
        int numPoints = Math.min(100, ((int) (totalLength / precision)) + 1);
        float[] mX = new float[numPoints];
        float[] mY = new float[numPoints];
        float[] position = new float[2];
        int contourIndex = 0;
        float step = totalLength / ((float) (numPoints - 1));
        float currentDistance = 0.0f;
        for (int i = 0; i < numPoints; i++) {
            pathMeasure.getPosTan(currentDistance, position, null);
            pathMeasure.getPosTan(currentDistance, position, null);
            mX[i] = position[0];
            mY[i] = position[1];
            currentDistance += step;
            if (contourIndex + 1 < contourLengths.size() && currentDistance > ((Float) contourLengths.get(contourIndex + 1)).floatValue()) {
                currentDistance -= ((Float) contourLengths.get(contourIndex + 1)).floatValue();
                contourIndex++;
                pathMeasure.nextContour();
            }
        }
        PropertyValuesHolder x = null;
        PropertyValuesHolder y = null;
        if (propertyXName != null) {
            x = PropertyValuesHolder.ofFloat(propertyXName, mX);
        }
        if (propertyYName != null) {
            y = PropertyValuesHolder.ofFloat(propertyYName, mY);
        }
        if (x == null) {
            oa.setValues(new PropertyValuesHolder[]{y});
        } else if (y == null) {
            oa.setValues(new PropertyValuesHolder[]{x});
        } else {
            oa.setValues(new PropertyValuesHolder[]{x, y});
        }
    }

    private static Animator a(Context context, Resources res, Theme theme, XmlPullParser parser, AttributeSet attrs, AnimatorSet parent, int sequenceOrdering, float pixelSize) throws XmlPullParserException, IOException {
        Animator anim = null;
        ArrayList<Animator> childAnims = null;
        int depth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if ((type != 3 || parser.getDepth() > depth) && type != 1) {
                if (type == 2) {
                    String name = parser.getName();
                    boolean gotValues = false;
                    if (name.equals("objectAnimator")) {
                        anim = new ObjectAnimator();
                        a(context, res, theme, attrs, anim, pixelSize, parser);
                    } else {
                        if (name.equals("animator")) {
                            anim = a(context, res, theme, attrs, null, pixelSize, parser);
                        } else {
                            if (name.equals("set")) {
                                anim = new AnimatorSet();
                                TypedArray a = android.support.v4.content.res.b.a(res, theme, attrs, a.h);
                                AnimatorSet animatorSet = (AnimatorSet) anim;
                                Context context2 = context;
                                Resources resources = res;
                                Theme theme2 = theme;
                                XmlPullParser xmlPullParser = parser;
                                AttributeSet attributeSet = attrs;
                                a(context2, resources, theme2, xmlPullParser, attributeSet, animatorSet, android.support.v4.content.res.b.a(a, parser, "ordering", 0, 0), pixelSize);
                                a.recycle();
                            } else {
                                if (name.equals("propertyValuesHolder")) {
                                    PropertyValuesHolder[] values = a(context, res, theme, parser, Xml.asAttributeSet(parser));
                                    if (!(values == null || anim == null || !(anim instanceof ValueAnimator))) {
                                        ((ValueAnimator) anim).setValues(values);
                                    }
                                    gotValues = true;
                                } else {
                                    throw new RuntimeException("Unknown animator name: " + parser.getName());
                                }
                            }
                        }
                    }
                    if (!(parent == null || gotValues)) {
                        if (childAnims == null) {
                            childAnims = new ArrayList();
                        }
                        childAnims.add(anim);
                    }
                }
            }
        }
        if (!(parent == null || childAnims == null)) {
            Animator[] animsArray = new Animator[childAnims.size()];
            int index = 0;
            Iterator it = childAnims.iterator();
            while (it.hasNext()) {
                int index2 = index + 1;
                animsArray[index] = (Animator) it.next();
                index = index2;
            }
            if (sequenceOrdering == 0) {
                parent.playTogether(animsArray);
            } else {
                parent.playSequentially(animsArray);
            }
        }
        return anim;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static PropertyValuesHolder[] a(Context context, Resources res, Theme theme, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        PropertyValuesHolder[] valuesArray;
        ArrayList<PropertyValuesHolder> values = null;
        while (true) {
            int type = parser.getEventType();
            if (type == 3 || type == 1) {
                valuesArray = null;
            } else if (type != 2) {
                parser.next();
            } else {
                if (parser.getName().equals("propertyValuesHolder")) {
                    int next;
                    int i;
                    Keyframe keyframe;
                    int size;
                    Keyframe keyframe2;
                    float fraction;
                    float fraction2;
                    Keyframe[] keyframeArr;
                    int i2;
                    TypedArray a = android.support.v4.content.res.b.a(res, theme, attrs, a.i);
                    String propertyName = android.support.v4.content.res.b.b(a, parser, "propertyName", 3);
                    int valueType = android.support.v4.content.res.b.a(a, parser, "valueType", 2, 4);
                    PropertyValuesHolder pvh = null;
                    ArrayList arrayList = null;
                    int i3 = valueType;
                    while (true) {
                        next = parser.next();
                        if (next != 3 && next != 1) {
                            ArrayList arrayList2;
                            if (parser.getName().equals("keyframe")) {
                                Keyframe keyframe3;
                                if (i3 == 4) {
                                    TypedArray a2 = android.support.v4.content.res.b.a(res, theme, Xml.asAttributeSet(parser), a.j);
                                    TypedValue a3 = android.support.v4.content.res.b.a(a2, parser, PropertiesEntry.COLUMN_NAME_VALUE);
                                    if ((a3 != null ? 1 : null) == null || !a(a3.type)) {
                                        next = 0;
                                    } else {
                                        next = 3;
                                    }
                                    a2.recycle();
                                    i = next;
                                } else {
                                    i = i3;
                                }
                                TypedArray a4 = android.support.v4.content.res.b.a(res, theme, Xml.asAttributeSet(parser), a.j);
                                keyframe = null;
                                float a5 = android.support.v4.content.res.b.a(a4, parser, "fraction", 3, -1.0f);
                                TypedValue a6 = android.support.v4.content.res.b.a(a4, parser, PropertiesEntry.COLUMN_NAME_VALUE);
                                Object obj = a6 != null ? 1 : null;
                                if (i != 4) {
                                    i3 = i;
                                } else if (obj == null || !a(a6.type)) {
                                    i3 = 0;
                                } else {
                                    i3 = 3;
                                }
                                if (obj != null) {
                                    switch (i3) {
                                        case 0:
                                            keyframe = Keyframe.ofFloat(a5, android.support.v4.content.res.b.a(a4, parser, PropertiesEntry.COLUMN_NAME_VALUE, 0, 0.0f));
                                            break;
                                        case 1:
                                        case 3:
                                            keyframe = Keyframe.ofInt(a5, android.support.v4.content.res.b.a(a4, parser, PropertiesEntry.COLUMN_NAME_VALUE, 0, 0));
                                            break;
                                    }
                                }
                                keyframe = i3 == 0 ? Keyframe.ofFloat(a5) : Keyframe.ofInt(a5);
                                keyframe3 = keyframe;
                                next = android.support.v4.content.res.b.a(a4, parser, "interpolator", 1);
                                if (next > 0) {
                                    keyframe3.setInterpolator(d.a(context, next));
                                }
                                a4.recycle();
                                if (keyframe3 != null) {
                                    if (arrayList == null) {
                                        arrayList2 = new ArrayList();
                                    } else {
                                        arrayList2 = arrayList;
                                    }
                                    arrayList2.add(keyframe3);
                                } else {
                                    arrayList2 = arrayList;
                                }
                                parser.next();
                            } else {
                                arrayList2 = arrayList;
                                i = i3;
                            }
                            arrayList = arrayList2;
                            i3 = i;
                        } else if (arrayList != null) {
                            size = arrayList.size();
                            if (size > 0) {
                                keyframe = (Keyframe) arrayList.get(0);
                                keyframe2 = (Keyframe) arrayList.get(size - 1);
                                fraction = keyframe2.getFraction();
                                if (fraction < 1.0f) {
                                    i = size;
                                } else if (fraction >= 0.0f) {
                                    keyframe2.setFraction(1.0f);
                                    i = size;
                                } else {
                                    arrayList.add(arrayList.size(), a(keyframe2, 1.0f));
                                    i = size + 1;
                                }
                                fraction2 = keyframe.getFraction();
                                if (fraction2 != 0.0f) {
                                    if (fraction2 >= 0.0f) {
                                        keyframe.setFraction(0.0f);
                                    } else {
                                        arrayList.add(0, a(keyframe, 0.0f));
                                        i++;
                                    }
                                }
                                keyframeArr = new Keyframe[i];
                                arrayList.toArray(keyframeArr);
                                for (size = 0; size < i; size++) {
                                    keyframe = keyframeArr[size];
                                    if (keyframe.getFraction() >= 0.0f) {
                                        if (size == 0) {
                                            keyframe.setFraction(0.0f);
                                        } else if (size != i - 1) {
                                            keyframe.setFraction(1.0f);
                                        } else {
                                            next = size + 1;
                                            i2 = size;
                                            while (next < i - 1 && keyframeArr[next].getFraction() < 0.0f) {
                                                i2 = next;
                                                next++;
                                            }
                                            a(keyframeArr, keyframeArr[i2 + 1].getFraction() - keyframeArr[size - 1].getFraction(), size, i2);
                                        }
                                    }
                                }
                                pvh = PropertyValuesHolder.ofKeyframe(propertyName, keyframeArr);
                                if (i3 == 3) {
                                    pvh.setEvaluator(f.a());
                                }
                            }
                        }
                    }
                    if (arrayList != null) {
                        size = arrayList.size();
                        if (size > 0) {
                            keyframe = (Keyframe) arrayList.get(0);
                            keyframe2 = (Keyframe) arrayList.get(size - 1);
                            fraction = keyframe2.getFraction();
                            if (fraction < 1.0f) {
                                i = size;
                            } else if (fraction >= 0.0f) {
                                arrayList.add(arrayList.size(), a(keyframe2, 1.0f));
                                i = size + 1;
                            } else {
                                keyframe2.setFraction(1.0f);
                                i = size;
                            }
                            fraction2 = keyframe.getFraction();
                            if (fraction2 != 0.0f) {
                                if (fraction2 >= 0.0f) {
                                    arrayList.add(0, a(keyframe, 0.0f));
                                    i++;
                                } else {
                                    keyframe.setFraction(0.0f);
                                }
                            }
                            keyframeArr = new Keyframe[i];
                            arrayList.toArray(keyframeArr);
                            for (size = 0; size < i; size++) {
                                keyframe = keyframeArr[size];
                                if (keyframe.getFraction() >= 0.0f) {
                                    if (size == 0) {
                                        keyframe.setFraction(0.0f);
                                    } else if (size != i - 1) {
                                        next = size + 1;
                                        i2 = size;
                                        while (next < i - 1) {
                                            i2 = next;
                                            next++;
                                        }
                                        a(keyframeArr, keyframeArr[i2 + 1].getFraction() - keyframeArr[size - 1].getFraction(), size, i2);
                                    } else {
                                        keyframe.setFraction(1.0f);
                                    }
                                }
                            }
                            pvh = PropertyValuesHolder.ofKeyframe(propertyName, keyframeArr);
                            if (i3 == 3) {
                                pvh.setEvaluator(f.a());
                            }
                        }
                    }
                    if (pvh == null) {
                        pvh = a(a, valueType, 0, 1, propertyName);
                    }
                    if (pvh != null) {
                        if (values == null) {
                            values = new ArrayList();
                        }
                        values.add(pvh);
                    }
                    a.recycle();
                }
                parser.next();
            }
        }
        valuesArray = null;
        if (values != null) {
            int count = values.size();
            valuesArray = new PropertyValuesHolder[count];
            for (int i4 = 0; i4 < count; i4++) {
                valuesArray[i4] = (PropertyValuesHolder) values.get(i4);
            }
        }
        return valuesArray;
    }

    private static Keyframe a(Keyframe sampleKeyframe, float fraction) {
        if (sampleKeyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(fraction);
        }
        if (sampleKeyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(fraction);
        }
        return Keyframe.ofObject(fraction);
    }

    private static void a(Keyframe[] keyframes, float gap, int startIndex, int endIndex) {
        float increment = gap / ((float) ((endIndex - startIndex) + 2));
        for (int i = startIndex; i <= endIndex; i++) {
            keyframes[i].setFraction(keyframes[i - 1].getFraction() + increment);
        }
    }

    private static ValueAnimator a(Context context, Resources res, Theme theme, AttributeSet attrs, ValueAnimator anim, float pathErrorScale, XmlPullParser parser) throws NotFoundException {
        TypedArray arrayAnimator = android.support.v4.content.res.b.a(res, theme, attrs, a.g);
        TypedArray arrayObjectAnimator = android.support.v4.content.res.b.a(res, theme, attrs, a.k);
        if (anim == null) {
            anim = new ValueAnimator();
        }
        a(anim, arrayAnimator, arrayObjectAnimator, pathErrorScale, parser);
        int resID = android.support.v4.content.res.b.a(arrayAnimator, parser, "interpolator", 0);
        if (resID > 0) {
            anim.setInterpolator(d.a(context, resID));
        }
        arrayAnimator.recycle();
        if (arrayObjectAnimator != null) {
            arrayObjectAnimator.recycle();
        }
        return anim;
    }

    private static boolean a(int type) {
        return type >= 28 && type <= 31;
    }
}
