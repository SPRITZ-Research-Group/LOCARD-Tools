package com.facebook.react.views.slider;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View.MeasureSpec;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.facebook.react.bridge.ai;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.h;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.b;
import java.util.Map;

public class ReactSliderManager extends SimpleViewManager<ReactSlider> {
    private static final OnSeekBarChangeListener ON_CHANGE_LISTENER = new OnSeekBarChangeListener() {
        public final void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
            ((UIManagerModule) ((ai) seekbar.getContext()).b(UIManagerModule.class)).getEventDispatcher().a(new a(seekbar.getId(), ((ReactSlider) seekbar).a(progress), fromUser));
        }

        public final void onStartTrackingTouch(SeekBar seekbar) {
        }

        public final void onStopTrackingTouch(SeekBar seekbar) {
            ((UIManagerModule) ((ai) seekbar.getContext()).b(UIManagerModule.class)).getEventDispatcher().a(new b(seekbar.getId(), ((ReactSlider) seekbar).a(seekbar.getProgress())));
        }
    };
    private static final String REACT_CLASS = "RCTSlider";
    private static final int STYLE = 16842875;

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
                SeekBar reactSlider = new ReactSlider(E(), null, ReactSliderManager.STYLE);
                int spec = MeasureSpec.makeMeasureSpec(-2, 0);
                reactSlider.measure(spec, spec);
                this.a = reactSlider.getMeasuredWidth();
                this.b = reactSlider.getMeasuredHeight();
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

    protected ReactSlider createViewInstance(ae context) {
        return new ReactSlider(context, null, STYLE);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSlider view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(defaultDouble = 0.0d, name = "value")
    public void setValue(ReactSlider view, double value) {
        view.setOnSeekBarChangeListener(null);
        view.c(value);
        view.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @ReactProp(defaultDouble = 0.0d, name = "minimumValue")
    public void setMinimumValue(ReactSlider view, double value) {
        view.b(value);
    }

    @ReactProp(defaultDouble = 1.0d, name = "maximumValue")
    public void setMaximumValue(ReactSlider view, double value) {
        view.a(value);
    }

    @ReactProp(defaultDouble = 0.0d, name = "step")
    public void setStep(ReactSlider view, double value) {
        view.d(value);
    }

    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(ReactSlider view, Integer color) {
        if (color == null) {
            view.getThumb().clearColorFilter();
        } else {
            view.getThumb().setColorFilter(color.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(customType = "Color", name = "minimumTrackTintColor")
    public void setMinimumTrackTintColor(ReactSlider view, Integer color) {
        Drawable background = ((LayerDrawable) view.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908288);
        if (color == null) {
            background.clearColorFilter();
        } else {
            background.setColorFilter(color.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(customType = "Color", name = "maximumTrackTintColor")
    public void setMaximumTrackTintColor(ReactSlider view, Integer color) {
        Drawable progress = ((LayerDrawable) view.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908301);
        if (color == null) {
            progress.clearColorFilter();
        } else {
            progress.setColorFilter(color.intValue(), Mode.SRC_IN);
        }
    }

    protected void addEventEmitters(ae reactContext, ReactSlider view) {
        view.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("topSlidingComplete", e.a("registrationName", "onSlidingComplete"));
    }
}
