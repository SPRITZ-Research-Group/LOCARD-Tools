package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.g;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaWrap;
import java.util.Locale;
import javax.annotation.Nullable;

public class h extends w {
    private final a a = new a();

    private static class a {
        float a;
        YogaUnit b;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        final void a(g dynamic) {
            if (dynamic.a()) {
                this.b = YogaUnit.UNDEFINED;
                this.a = Float.NaN;
            } else if (dynamic.d() == ReadableType.String) {
                String s = dynamic.c();
                if (s.equals("auto")) {
                    this.b = YogaUnit.AUTO;
                    this.a = Float.NaN;
                } else if (s.endsWith("%")) {
                    this.b = YogaUnit.PERCENT;
                    this.a = Float.parseFloat(s.substring(0, s.length() - 1));
                } else {
                    throw new IllegalArgumentException("Unknown value: " + s);
                }
            } else {
                this.b = YogaUnit.POINT;
                this.a = p.a((float) dynamic.b());
            }
        }
    }

    @ReactProp(name = "width")
    public void setWidth(g width) {
        if (!a()) {
            this.a.a(width);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    a(this.a.a);
                    break;
                case AUTO:
                    T();
                    break;
                case PERCENT:
                    d(this.a.a);
                    break;
            }
            width.e();
        }
    }

    @ReactProp(name = "minWidth")
    public void setMinWidth(g minWidth) {
        if (!a()) {
            this.a.a(minWidth);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    e(this.a.a);
                    break;
                case PERCENT:
                    f(this.a.a);
                    break;
            }
            minWidth.e();
        }
    }

    @ReactProp(name = "maxWidth")
    public void setMaxWidth(g maxWidth) {
        if (!a()) {
            this.a.a(maxWidth);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    g(this.a.a);
                    break;
                case PERCENT:
                    h(this.a.a);
                    break;
            }
            maxWidth.e();
        }
    }

    @ReactProp(name = "height")
    public void setHeight(g height) {
        if (!a()) {
            this.a.a(height);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    b(this.a.a);
                    break;
                case AUTO:
                    V();
                    break;
                case PERCENT:
                    i(this.a.a);
                    break;
            }
            height.e();
        }
    }

    @ReactProp(name = "minHeight")
    public void setMinHeight(g minHeight) {
        if (!a()) {
            this.a.a(minHeight);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    j(this.a.a);
                    break;
                case PERCENT:
                    k(this.a.a);
                    break;
            }
            minHeight.e();
        }
    }

    @ReactProp(name = "maxHeight")
    public void setMaxHeight(g maxHeight) {
        if (!a()) {
            this.a.a(maxHeight);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    l(this.a.a);
                    break;
                case PERCENT:
                    m(this.a.a);
                    break;
            }
            maxHeight.e();
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flex")
    public void setFlex(float flex) {
        if (!a()) {
            super.setFlex(flex);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexGrow")
    public void setFlexGrow(float flexGrow) {
        if (!a()) {
            super.setFlexGrow(flexGrow);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexShrink")
    public void setFlexShrink(float flexShrink) {
        if (!a()) {
            super.setFlexShrink(flexShrink);
        }
    }

    @ReactProp(name = "flexBasis")
    public void setFlexBasis(g flexBasis) {
        if (!a()) {
            this.a.a(flexBasis);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    n(this.a.a);
                    break;
                case AUTO:
                    W();
                    break;
                case PERCENT:
                    o(this.a.a);
                    break;
            }
            flexBasis.e();
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "aspectRatio")
    public void setAspectRatio(float aspectRatio) {
        p(aspectRatio);
    }

    @ReactProp(name = "flexDirection")
    public void setFlexDirection(@Nullable String flexDirection) {
        if (!a()) {
            if (flexDirection == null) {
                a(YogaFlexDirection.COLUMN);
                return;
            }
            Object obj = -1;
            switch (flexDirection.hashCode()) {
                case -1448970769:
                    if (flexDirection.equals("row-reverse")) {
                        obj = 3;
                        break;
                    }
                    break;
                case -1354837162:
                    if (flexDirection.equals("column")) {
                        obj = null;
                        break;
                    }
                    break;
                case 113114:
                    if (flexDirection.equals("row")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1272730475:
                    if (flexDirection.equals("column-reverse")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    a(YogaFlexDirection.COLUMN);
                    return;
                case 1:
                    a(YogaFlexDirection.COLUMN_REVERSE);
                    return;
                case 2:
                    a(YogaFlexDirection.ROW);
                    return;
                case 3:
                    a(YogaFlexDirection.ROW_REVERSE);
                    return;
                default:
                    throw new n("invalid value for flexDirection: " + flexDirection);
            }
        }
    }

    @ReactProp(name = "flexWrap")
    public void setFlexWrap(@Nullable String flexWrap) {
        if (!a()) {
            if (flexWrap == null) {
                a(YogaWrap.NO_WRAP);
                return;
            }
            Object obj = -1;
            switch (flexWrap.hashCode()) {
                case -1039592053:
                    if (flexWrap.equals("nowrap")) {
                        obj = null;
                        break;
                    }
                    break;
                case 3657802:
                    if (flexWrap.equals("wrap")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    a(YogaWrap.NO_WRAP);
                    return;
                case 1:
                    a(YogaWrap.WRAP);
                    return;
                default:
                    throw new n("invalid value for flexWrap: " + flexWrap);
            }
        }
    }

    @ReactProp(name = "alignSelf")
    public void setAlignSelf(@Nullable String alignSelf) {
        if (!a()) {
            if (alignSelf == null) {
                a(YogaAlign.AUTO);
                return;
            }
            Object obj = -1;
            switch (alignSelf.hashCode()) {
                case -1881872635:
                    if (alignSelf.equals("stretch")) {
                        obj = 4;
                        break;
                    }
                    break;
                case -1720785339:
                    if (alignSelf.equals("baseline")) {
                        obj = 5;
                        break;
                    }
                    break;
                case -1364013995:
                    if (alignSelf.equals("center")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (alignSelf.equals("flex-start")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3005871:
                    if (alignSelf.equals("auto")) {
                        obj = null;
                        break;
                    }
                    break;
                case 441309761:
                    if (alignSelf.equals("space-between")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 1742952711:
                    if (alignSelf.equals("flex-end")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 1937124468:
                    if (alignSelf.equals("space-around")) {
                        obj = 7;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    a(YogaAlign.AUTO);
                    return;
                case 1:
                    a(YogaAlign.FLEX_START);
                    return;
                case 2:
                    a(YogaAlign.CENTER);
                    return;
                case 3:
                    a(YogaAlign.FLEX_END);
                    return;
                case 4:
                    a(YogaAlign.STRETCH);
                    return;
                case 5:
                    a(YogaAlign.BASELINE);
                    return;
                case 6:
                    a(YogaAlign.SPACE_BETWEEN);
                    return;
                case 7:
                    a(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new n("invalid value for alignSelf: " + alignSelf);
            }
        }
    }

    @ReactProp(name = "alignItems")
    public void setAlignItems(@Nullable String alignItems) {
        if (!a()) {
            if (alignItems == null) {
                b(YogaAlign.STRETCH);
                return;
            }
            Object obj = -1;
            switch (alignItems.hashCode()) {
                case -1881872635:
                    if (alignItems.equals("stretch")) {
                        obj = 4;
                        break;
                    }
                    break;
                case -1720785339:
                    if (alignItems.equals("baseline")) {
                        obj = 5;
                        break;
                    }
                    break;
                case -1364013995:
                    if (alignItems.equals("center")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (alignItems.equals("flex-start")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3005871:
                    if (alignItems.equals("auto")) {
                        obj = null;
                        break;
                    }
                    break;
                case 441309761:
                    if (alignItems.equals("space-between")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 1742952711:
                    if (alignItems.equals("flex-end")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 1937124468:
                    if (alignItems.equals("space-around")) {
                        obj = 7;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    b(YogaAlign.AUTO);
                    return;
                case 1:
                    b(YogaAlign.FLEX_START);
                    return;
                case 2:
                    b(YogaAlign.CENTER);
                    return;
                case 3:
                    b(YogaAlign.FLEX_END);
                    return;
                case 4:
                    b(YogaAlign.STRETCH);
                    return;
                case 5:
                    b(YogaAlign.BASELINE);
                    return;
                case 6:
                    b(YogaAlign.SPACE_BETWEEN);
                    return;
                case 7:
                    b(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new n("invalid value for alignItems: " + alignItems);
            }
        }
    }

    @ReactProp(name = "alignContent")
    public void setAlignContent(@Nullable String alignContent) {
        if (!a()) {
            if (alignContent == null) {
                c(YogaAlign.FLEX_START);
                return;
            }
            Object obj = -1;
            switch (alignContent.hashCode()) {
                case -1881872635:
                    if (alignContent.equals("stretch")) {
                        obj = 4;
                        break;
                    }
                    break;
                case -1720785339:
                    if (alignContent.equals("baseline")) {
                        obj = 5;
                        break;
                    }
                    break;
                case -1364013995:
                    if (alignContent.equals("center")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (alignContent.equals("flex-start")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3005871:
                    if (alignContent.equals("auto")) {
                        obj = null;
                        break;
                    }
                    break;
                case 441309761:
                    if (alignContent.equals("space-between")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 1742952711:
                    if (alignContent.equals("flex-end")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 1937124468:
                    if (alignContent.equals("space-around")) {
                        obj = 7;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    c(YogaAlign.AUTO);
                    return;
                case 1:
                    c(YogaAlign.FLEX_START);
                    return;
                case 2:
                    c(YogaAlign.CENTER);
                    return;
                case 3:
                    c(YogaAlign.FLEX_END);
                    return;
                case 4:
                    c(YogaAlign.STRETCH);
                    return;
                case 5:
                    c(YogaAlign.BASELINE);
                    return;
                case 6:
                    c(YogaAlign.SPACE_BETWEEN);
                    return;
                case 7:
                    c(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new n("invalid value for alignContent: " + alignContent);
            }
        }
    }

    @ReactProp(name = "justifyContent")
    public void setJustifyContent(@Nullable String justifyContent) {
        if (!a()) {
            if (justifyContent == null) {
                a(YogaJustify.FLEX_START);
                return;
            }
            Object obj = -1;
            switch (justifyContent.hashCode()) {
                case -1364013995:
                    if (justifyContent.equals("center")) {
                        obj = 1;
                        break;
                    }
                    break;
                case -46581362:
                    if (justifyContent.equals("flex-start")) {
                        obj = null;
                        break;
                    }
                    break;
                case 441309761:
                    if (justifyContent.equals("space-between")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 1742952711:
                    if (justifyContent.equals("flex-end")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1937124468:
                    if (justifyContent.equals("space-around")) {
                        obj = 4;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    a(YogaJustify.FLEX_START);
                    return;
                case 1:
                    a(YogaJustify.CENTER);
                    return;
                case 2:
                    a(YogaJustify.FLEX_END);
                    return;
                case 3:
                    a(YogaJustify.SPACE_BETWEEN);
                    return;
                case 4:
                    a(YogaJustify.SPACE_AROUND);
                    return;
                default:
                    throw new n("invalid value for justifyContent: " + justifyContent);
            }
        }
    }

    @ReactProp(name = "overflow")
    public void setOverflow(@Nullable String overflow) {
        if (!a()) {
            a(overflow == null ? YogaOverflow.HIDDEN : YogaOverflow.valueOf(overflow.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "display")
    public void setDisplay(@Nullable String display) {
        if (!a()) {
            if (display == null) {
                a(YogaDisplay.FLEX);
                return;
            }
            Object obj = -1;
            switch (display.hashCode()) {
                case 3145721:
                    if (display.equals("flex")) {
                        obj = null;
                        break;
                    }
                    break;
                case 3387192:
                    if (display.equals("none")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    a(YogaDisplay.FLEX);
                    return;
                case 1:
                    a(YogaDisplay.NONE);
                    return;
                default:
                    throw new n("invalid value for display: " + display);
            }
        }
    }

    @ReactPropGroup(names = {"margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom"})
    public void setMargins(int index, g margin) {
        if (!a()) {
            this.a.a(margin);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    d(ar.b[index], this.a.a);
                    break;
                case AUTO:
                    f(ar.b[index]);
                    break;
                case PERCENT:
                    e(ar.b[index], this.a.a);
                    break;
            }
            margin.e();
        }
    }

    @ReactPropGroup(names = {"padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom"})
    public void setPaddings(int index, g padding) {
        if (!a()) {
            this.a.a(padding);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    a(ar.b[index], this.a.a);
                    break;
                case PERCENT:
                    b(ar.b[index], this.a.a);
                    break;
            }
            padding.e();
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidths(int index, float borderWidth) {
        if (!a()) {
            c(ar.a[index], p.a(borderWidth));
        }
    }

    @ReactPropGroup(names = {"left", "right", "top", "bottom"})
    public void setPositionValues(int index, g position) {
        if (!a()) {
            this.a.a(position);
            switch (this.a.b) {
                case POINT:
                case UNDEFINED:
                    g(ar.c[index], this.a.a);
                    break;
                case PERCENT:
                    h(ar.c[index], this.a.a);
                    break;
            }
            position.e();
        }
    }

    @ReactProp(name = "position")
    public void setPosition(@Nullable String position) {
        if (!a()) {
            if (position == null) {
                a(YogaPositionType.RELATIVE);
                return;
            }
            Object obj = -1;
            switch (position.hashCode()) {
                case -554435892:
                    if (position.equals("relative")) {
                        obj = null;
                        break;
                    }
                    break;
                case 1728122231:
                    if (position.equals("absolute")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    a(YogaPositionType.RELATIVE);
                    return;
                case 1:
                    a(YogaPositionType.ABSOLUTE);
                    return;
                default:
                    throw new n("invalid value for position: " + position);
            }
        }
    }

    @ReactProp(name = "onLayout")
    public void setShouldNotifyOnLayout(boolean shouldNotifyOnLayout) {
        super.setShouldNotifyOnLayout(shouldNotifyOnLayout);
    }
}
