package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public class d extends b {
    @Nullable
    private am d;
    private int f = 0;

    @ReactProp(name = "frame")
    public void setFrame(@Nullable am frame) {
        this.d = frame;
    }

    @ReactProp(defaultInt = 0, name = "alignment")
    public void setAlignment(int alignment) {
        this.f = alignment;
    }

    public final void a(Canvas canvas, Paint paint, float opacity) {
        if (this.d != null) {
            opacity *= this.b;
            if (opacity > 0.01f && this.d.hasKey("lines")) {
                al linesProp = this.d.getArray("lines");
                if (linesProp != null && linesProp.size() != 0) {
                    a(canvas);
                    String[] lines = new String[linesProp.size()];
                    for (int i = 0; i < lines.length; i++) {
                        lines[i] = linesProp.getString(i);
                    }
                    String text = TextUtils.join("\n", lines);
                    if (a(paint, opacity)) {
                        a(paint);
                        if (this.a == null) {
                            canvas.drawText(text, 0.0f, -paint.ascent(), paint);
                        } else {
                            canvas.drawTextOnPath(text, this.a, 0.0f, 0.0f, paint);
                        }
                    }
                    if (b(paint, opacity)) {
                        a(paint);
                        if (this.a == null) {
                            canvas.drawText(text, 0.0f, -paint.ascent(), paint);
                        } else {
                            canvas.drawTextOnPath(text, this.a, 0.0f, 0.0f, paint);
                        }
                    }
                    canvas.restore();
                    v();
                }
            }
        }
    }

    private void a(Paint paint) {
        switch (this.f) {
            case 0:
                paint.setTextAlign(Align.LEFT);
                break;
            case 1:
                paint.setTextAlign(Align.RIGHT);
                break;
            case 2:
                paint.setTextAlign(Align.CENTER);
                break;
        }
        if (this.d != null && this.d.hasKey("font")) {
            am font = this.d.getMap("font");
            if (font != null) {
                boolean isBold;
                boolean isItalic;
                int fontStyle;
                float fontSize = 12.0f;
                if (font.hasKey("fontSize")) {
                    fontSize = (float) font.getDouble("fontSize");
                }
                paint.setTextSize(this.c * fontSize);
                if (font.hasKey("fontWeight") && "bold".equals(font.getString("fontWeight"))) {
                    isBold = true;
                } else {
                    isBold = false;
                }
                if (font.hasKey("fontStyle") && "italic".equals(font.getString("fontStyle"))) {
                    isItalic = true;
                } else {
                    isItalic = false;
                }
                if (isBold && isItalic) {
                    fontStyle = 3;
                } else if (isBold) {
                    fontStyle = 1;
                } else if (isItalic) {
                    fontStyle = 2;
                } else {
                    fontStyle = 0;
                }
                paint.setTypeface(Typeface.create(font.getString("fontFamily"), fontStyle));
            }
        }
    }
}
