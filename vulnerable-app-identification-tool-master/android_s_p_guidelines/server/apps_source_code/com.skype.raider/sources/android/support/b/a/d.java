package android.support.b.a;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.animation.b;
import android.support.v4.view.animation.c;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({a.LIBRARY_GROUP})
public final class d {
    public static Interpolator a(Context context, int id) throws NotFoundException {
        NotFoundException rnf;
        if (VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, id);
        }
        XmlResourceParser parser = null;
        if (id == 17563663) {
            try {
                return new android.support.v4.view.animation.a();
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
        } else if (id == 17563661) {
            return new b();
        } else {
            if (id == 17563662) {
                return new c();
            }
            parser = context.getResources().getAnimation(id);
            context.getResources();
            context.getTheme();
            Interpolator interpolator = null;
            int depth = parser.getDepth();
            while (true) {
                int next = parser.next();
                if ((next != 3 || parser.getDepth() > depth) && next != 1) {
                    if (next == 2) {
                        AttributeSet asAttributeSet = Xml.asAttributeSet(parser);
                        String name = parser.getName();
                        if (name.equals("linearInterpolator")) {
                            interpolator = new LinearInterpolator();
                        } else if (name.equals("accelerateInterpolator")) {
                            interpolator = new AccelerateInterpolator(context, asAttributeSet);
                        } else if (name.equals("decelerateInterpolator")) {
                            interpolator = new DecelerateInterpolator(context, asAttributeSet);
                        } else if (name.equals("accelerateDecelerateInterpolator")) {
                            interpolator = new AccelerateDecelerateInterpolator();
                        } else if (name.equals("cycleInterpolator")) {
                            interpolator = new CycleInterpolator(context, asAttributeSet);
                        } else if (name.equals("anticipateInterpolator")) {
                            interpolator = new AnticipateInterpolator(context, asAttributeSet);
                        } else if (name.equals("overshootInterpolator")) {
                            interpolator = new OvershootInterpolator(context, asAttributeSet);
                        } else if (name.equals("anticipateOvershootInterpolator")) {
                            interpolator = new AnticipateOvershootInterpolator(context, asAttributeSet);
                        } else if (name.equals("bounceInterpolator")) {
                            interpolator = new BounceInterpolator();
                        } else if (name.equals("pathInterpolator")) {
                            interpolator = new g(context, asAttributeSet, parser);
                        } else {
                            throw new RuntimeException("Unknown interpolator name: " + parser.getName());
                        }
                    }
                }
            }
            if (parser == null) {
                return interpolator;
            }
            parser.close();
            return interpolator;
        }
    }
}
