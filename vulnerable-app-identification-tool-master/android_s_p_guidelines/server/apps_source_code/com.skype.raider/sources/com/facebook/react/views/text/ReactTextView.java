package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.p;
import com.facebook.react.uimanager.t;
import com.facebook.react.views.view.b;
import com.facebook.react.views.view.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.Nullable;

public class ReactTextView extends TextView implements t {
    private static final LayoutParams a = new LayoutParams(0, 0);
    private boolean b;
    private int c = (getGravity() & 8388615);
    private int d = (getGravity() & 112);
    private boolean e;
    private float f = Float.NaN;
    private int g = 0;
    private int h = Integer.MAX_VALUE;
    private TruncateAt i = TruncateAt.END;
    private boolean j;
    private e k;
    private b l = new b();

    public ReactTextView(Context context) {
        super(context);
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        boolean didTakeFocus = super.requestFocus(direction, previouslyFocusedRect);
        this.l.b(didTakeFocus);
        return didTakeFocus;
    }

    public void clearFocus() {
        super.clearFocus();
        this.l.b();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.l.a((View) this, keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.l.a((View) this);
    }

    protected void onLayout(boolean changed, int textViewLeft, int textViewTop, int textViewRight, int textViewBottom) {
        if (getText() instanceof Spanned) {
            UIManagerModule uiManager = (UIManagerModule) ((ai) getContext()).b(UIManagerModule.class);
            Spanned text = (Spanned) getText();
            Layout layout = getLayout();
            l[] placeholders = (l[]) text.getSpans(0, text.length(), l.class);
            ArrayList inlineViewInfoArray = this.j ? new ArrayList(placeholders.length) : null;
            int textViewWidth = textViewRight - textViewLeft;
            int textViewHeight = textViewBottom - textViewTop;
            int length = placeholders.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                l placeholder = placeholders[i2];
                View child = uiManager.resolveView(placeholder.a());
                int start = text.getSpanStart(placeholder);
                int line = layout.getLineForOffset(start);
                if (((layout.getEllipsisCount(line) > 0 ? 1 : null) == null || start < layout.getLineStart(line) + layout.getEllipsisStart(line)) && line < this.h && start < layout.getLineEnd(line)) {
                    int placeholderHorizontalPosition;
                    int leftRelativeToTextView;
                    int width = placeholder.b();
                    int height = placeholder.c();
                    int marginBottom = placeholder.g();
                    int marginLeft = placeholder.e();
                    int marginRight = placeholder.f();
                    boolean isRtlChar = layout.isRtlCharAt(start);
                    boolean isRtlParagraph = layout.getParagraphDirection(line) == -1;
                    if (start != text.length() - 1) {
                        if ((isRtlParagraph == isRtlChar ? 1 : null) != null) {
                            placeholderHorizontalPosition = (int) layout.getPrimaryHorizontal(start);
                        } else {
                            placeholderHorizontalPosition = (int) layout.getSecondaryHorizontal(start);
                        }
                        if (isRtlParagraph) {
                            placeholderHorizontalPosition = textViewWidth - (((int) layout.getLineRight(line)) - placeholderHorizontalPosition);
                        }
                        if (isRtlChar) {
                            placeholderHorizontalPosition -= placeholder.d();
                        }
                    } else if (isRtlParagraph) {
                        placeholderHorizontalPosition = textViewWidth - ((int) layout.getLineWidth(line));
                    } else {
                        placeholderHorizontalPosition = ((int) layout.getLineRight(line)) - placeholder.d();
                    }
                    if (isRtlChar) {
                        leftRelativeToTextView = (getTotalPaddingRight() + placeholderHorizontalPosition) + marginRight;
                    } else {
                        leftRelativeToTextView = (getTotalPaddingLeft() + placeholderHorizontalPosition) + marginLeft;
                    }
                    int left = textViewLeft + leftRelativeToTextView;
                    int topRelativeToTextView = (getTotalPaddingTop() + layout.getLineBaseline(line)) - (height + marginBottom);
                    int top = textViewTop + topRelativeToTextView;
                    Object obj = (textViewWidth <= leftRelativeToTextView || textViewHeight <= topRelativeToTextView) ? 1 : null;
                    int layoutVisibility = obj != null ? 8 : 0;
                    int layoutLeft = left;
                    int layoutTop = top;
                    int layoutRight = left + width;
                    int layoutBottom = top + height;
                    child.setVisibility(layoutVisibility);
                    child.layout(layoutLeft, layoutTop, layoutRight, layoutBottom);
                    if (this.j) {
                        inlineViewInfoArray.add(a(layoutVisibility, start, layoutLeft, layoutTop, layoutRight, layoutBottom));
                    }
                } else {
                    child.setVisibility(8);
                    if (this.j) {
                        inlineViewInfoArray.add(a(8, start, -1, -1, -1, -1));
                    }
                }
                i = i2 + 1;
            }
            if (this.j) {
                Collections.sort(inlineViewInfoArray, new Comparator(this) {
                    final /* synthetic */ ReactTextView a;

                    {
                        this.a = this$0;
                    }

                    public final int compare(Object o1, Object o2) {
                        return ((ar) o1).getInt("index") - ((ar) o2).getInt("index");
                    }
                });
                aq inlineViewInfoArray2 = new WritableNativeArray();
                Iterator it = inlineViewInfoArray.iterator();
                while (it.hasNext()) {
                    inlineViewInfoArray2.pushMap((ar) it.next());
                }
                ar event = new WritableNativeMap();
                event.putArray("inlineViews", inlineViewInfoArray2);
                ((RCTEventEmitter) ((ai) getContext()).a(RCTEventEmitter.class)).receiveEvent(getId(), "topInlineViewLayout", event);
            }
        }
    }

    public void setText(h update) {
        this.b = update.c();
        if (getLayoutParams() == null) {
            setLayoutParams(a);
        }
        setText(update.a());
        setPadding((int) Math.floor((double) update.d()), (int) Math.floor((double) update.e()), (int) Math.floor((double) update.f()), (int) Math.floor((double) update.g()));
        int nextTextAlign = update.h();
        if (this.g != nextTextAlign) {
            this.g = nextTextAlign;
        }
        int i = this.g;
        if (i == 0) {
            i = this.c;
        }
        setGravity(i | ((getGravity() & -8) & -8388616));
        if (VERSION.SDK_INT >= 23 && getBreakStrategy() != update.i()) {
            setBreakStrategy(update.i());
        }
        requestLayout();
    }

    public final int a(float touchX, float touchY) {
        CharSequence text = getText();
        int target = getId();
        int x = (int) touchX;
        int y = (int) touchY;
        Layout layout = getLayout();
        if (layout == null) {
            return target;
        }
        int line = layout.getLineForVertical(y);
        int lineStartX = (int) layout.getLineLeft(line);
        int lineEndX = (int) layout.getLineRight(line);
        if ((text instanceof Spanned) && x >= lineStartX && x <= lineEndX) {
            Spanned spannedText = (Spanned) text;
            int index = layout.getOffsetForHorizontal(line, (float) x);
            e[] spans = (e[]) spannedText.getSpans(index, index, e.class);
            if (spans != null) {
                int targetSpanTextLength = text.length();
                for (int i = 0; i < spans.length; i++) {
                    int spanStart = spannedText.getSpanStart(spans[i]);
                    int spanEnd = spannedText.getSpanEnd(spans[i]);
                    if (spanEnd > index && spanEnd - spanStart <= targetSpanTextLength) {
                        target = spans[i].a();
                        targetSpanTextLength = spanEnd - spanStart;
                    }
                }
            }
        }
        return target;
    }

    public void setTextIsSelectable(boolean selectable) {
        this.e = selectable;
        super.setTextIsSelectable(selectable);
    }

    protected boolean verifyDrawable(Drawable drawable) {
        int i = 0;
        if (this.b && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                if (kVarArr[i].a() == drawable) {
                    return true;
                }
                i++;
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        int i = 0;
        if (this.b && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                if (kVarArr[i].a() == drawable) {
                    invalidate();
                }
                i++;
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        int i = 0;
        super.onDetachedFromWindow();
        if (this.b && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                kVarArr[i].b();
                i++;
            }
        }
    }

    public void onStartTemporaryDetach() {
        int i = 0;
        super.onStartTemporaryDetach();
        if (this.b && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                kVarArr[i].c();
                i++;
            }
        }
    }

    public void onAttachedToWindow() {
        int i = 0;
        super.onAttachedToWindow();
        if (this.b && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                kVarArr[i].d();
                i++;
            }
        }
    }

    public void onFinishTemporaryDetach() {
        int i = 0;
        super.onFinishTemporaryDetach();
        if (this.b && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            k[] kVarArr = (k[]) text.getSpans(0, text.length(), k.class);
            int length = kVarArr.length;
            while (i < length) {
                kVarArr[i].e();
                i++;
            }
        }
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.k != null) {
            b().a(color);
        }
    }

    final void a(int gravityVertical) {
        if (gravityVertical == 0) {
            gravityVertical = this.d;
        }
        setGravity((getGravity() & -113) | gravityVertical);
    }

    public void setNumberOfLines(int numberOfLines) {
        boolean z = true;
        if (numberOfLines == 0) {
            numberOfLines = Integer.MAX_VALUE;
        }
        this.h = numberOfLines;
        if (this.h != 1) {
            z = false;
        }
        setSingleLine(z);
        setMaxLines(this.h);
    }

    public void setEllipsizeLocation(TruncateAt ellipsizeLocation) {
        this.i = ellipsizeLocation;
    }

    public void setNotifyOnInlineViewLayout(boolean notifyOnInlineViewLayout) {
        this.j = notifyOnInlineViewLayout;
    }

    public final void a() {
        setEllipsize(this.h == Integer.MAX_VALUE ? null : this.i);
    }

    public void setBorderWidth(int position, float width) {
        b().a(position, width);
    }

    public void setBorderColor(int position, float color, float alpha) {
        b().a(position, color, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        b().a(borderRadius);
    }

    public void setBorderRadius(float borderRadius, int position) {
        b().a(borderRadius, position);
    }

    public void setBorderStyle(@Nullable String style) {
        b().a(style);
    }

    private e b() {
        if (this.k == null) {
            this.k = new e();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.k);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.k, backgroundDrawable}));
            }
        }
        return this.k;
    }

    private static ar a(int visibility, int index, int left, int top, int right, int bottom) {
        ar json = new WritableNativeMap();
        if (visibility == 8) {
            json.putString("visibility", "gone");
            json.putInt("index", index);
        } else if (visibility == 0) {
            json.putString("visibility", "visible");
            json.putInt("index", index);
            json.putDouble("left", (double) p.b((float) left));
            json.putDouble("top", (double) p.b((float) top));
            json.putDouble("right", (double) p.b((float) right));
            json.putDouble("bottom", (double) p.b((float) bottom));
        } else {
            json.putString("visibility", "unknown");
            json.putInt("index", index);
        }
        return json;
    }
}
