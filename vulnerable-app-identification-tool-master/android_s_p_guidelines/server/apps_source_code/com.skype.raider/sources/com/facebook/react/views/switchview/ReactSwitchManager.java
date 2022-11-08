package com.facebook.react.views.switchview;

import android.graphics.PorterDuff.Mode;
import android.view.View.MeasureSpec;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.facebook.react.bridge.ai;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.h;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.b;

public class ReactSwitchManager extends SimpleViewManager<a> {
    private static final OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new OnCheckedChangeListener() {
        public final void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            ((UIManagerModule) ((ai) buttonView.getContext()).b(UIManagerModule.class)).getEventDispatcher().a(new b(buttonView.getId(), isChecked));
        }
    };
    private static final String REACT_CLASS = "AndroidSwitch";

    static class a extends h implements YogaMeasureFunction {
        private int a;
        private int b;
        private boolean c;

        /* synthetic */ a(byte b) {
            this();
        }

        private a() {
            a((YogaMeasureFunction) this);
        }

        public long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
            if (!this.c) {
                a reactSwitch = new a(E());
                int spec = MeasureSpec.makeMeasureSpec(-2, 0);
                reactSwitch.measure(spec, spec);
                this.a = reactSwitch.getMeasuredWidth();
                this.b = reactSwitch.getMeasuredHeight();
                this.c = true;
            }
            return b.a((float) this.a, (float) this.b);
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    public h createShadowNodeInstance() {
        return new a();
    }

    public Class getShadowNodeClass() {
        return a.class;
    }

    protected a createViewInstance(ae context) {
        a view = new a(context);
        view.setShowText(false);
        return view;
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(a view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(name = "on")
    public void setOn(a view, boolean on) {
        view.setOnCheckedChangeListener(null);
        view.a(on);
        view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(a view, Integer color) {
        if (color == null) {
            view.b().clearColorFilter();
        } else {
            view.b().setColorFilter(color.intValue(), Mode.MULTIPLY);
        }
    }

    @ReactProp(customType = "Color", name = "trackTintColor")
    public void setTrackTintColor(a view, Integer color) {
        if (color == null) {
            view.a().clearColorFilter();
        } else {
            view.a().setColorFilter(color.intValue(), Mode.MULTIPLY);
        }
    }

    protected void addEventEmitters(ae reactContext, a view) {
        view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }
}
