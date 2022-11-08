package com.google.android.exoplayer.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class CuePainter {
    private static final float DEFAULT_BOTTOM_PADDING_FRACTION = 0.08f;
    private static final float INNER_PADDING_RATIO = 0.125f;
    private static final float LINE_HEIGHT_FRACTION = 0.0533f;
    private static final String TAG = "CuePainter";
    private int backgroundColor;
    private final float cornerRadius;
    private Alignment cueAlignment;
    private int cuePosition;
    private CharSequence cueText;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final RectF lineBounds = new RectF();
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private int textTop;
    private int windowColor;

    public CuePainter(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{16843287, 16843288}, 0, 0);
        this.spacingAdd = (float) obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        float round = (float) Math.round((((float) context.getResources().getDisplayMetrics().densityDpi) * 2.0f) / 160.0f);
        this.cornerRadius = round;
        this.outlineWidth = round;
        this.shadowRadius = round;
        this.shadowOffset = round;
        this.textPaint = new TextPaint();
        this.textPaint.setAntiAlias(true);
        this.textPaint.setSubpixelText(true);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Style.FILL);
    }

    public final void draw(Cue cue, CaptionStyleCompat captionStyleCompat, float f, Canvas canvas, int i, int i2, int i3, int i4) {
        CuePainter cuePainter = this;
        Cue cue2 = cue;
        CaptionStyleCompat captionStyleCompat2 = captionStyleCompat;
        Canvas canvas2 = canvas;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (!TextUtils.isEmpty(cue2.text)) {
            if (TextUtils.equals(cuePainter.cueText, cue2.text) && cuePainter.cuePosition == cue2.position && Util.areEqual(cuePainter.cueAlignment, cue2.alignment) && cuePainter.foregroundColor == captionStyleCompat2.foregroundColor && cuePainter.backgroundColor == captionStyleCompat2.backgroundColor && cuePainter.windowColor == captionStyleCompat2.windowColor && cuePainter.edgeType == captionStyleCompat2.edgeType && cuePainter.edgeColor == captionStyleCompat2.edgeColor && Util.areEqual(cuePainter.textPaint.getTypeface(), captionStyleCompat2.typeface) && cuePainter.parentLeft == i5 && cuePainter.parentTop == i6 && cuePainter.parentRight == i7 && cuePainter.parentBottom == i8) {
                drawLayout(canvas2);
                return;
            }
            cuePainter.cueText = cue2.text;
            cuePainter.cuePosition = cue2.position;
            cuePainter.cueAlignment = cue2.alignment;
            cuePainter.foregroundColor = captionStyleCompat2.foregroundColor;
            cuePainter.backgroundColor = captionStyleCompat2.backgroundColor;
            cuePainter.windowColor = captionStyleCompat2.windowColor;
            cuePainter.edgeType = captionStyleCompat2.edgeType;
            cuePainter.edgeColor = captionStyleCompat2.edgeColor;
            cuePainter.textPaint.setTypeface(captionStyleCompat2.typeface);
            cuePainter.parentLeft = i5;
            cuePainter.parentTop = i6;
            cuePainter.parentRight = i7;
            cuePainter.parentBottom = i8;
            int i9 = cuePainter.parentRight - cuePainter.parentLeft;
            i5 = cuePainter.parentBottom - cuePainter.parentTop;
            float f2 = (float) i5;
            float f3 = (LINE_HEIGHT_FRACTION * f2) * f;
            cuePainter.textPaint.setTextSize(f3);
            i6 = (int) ((f3 * INNER_PADDING_RATIO) + 0.5f);
            i8 = i6 * 2;
            int i10 = i9 - i8;
            if (i10 <= 0) {
                Log.w(TAG, "Skipped drawing subtitle cue (insufficient space)");
                return;
            }
            int i11;
            Alignment alignment = cuePainter.cueAlignment == null ? Alignment.ALIGN_CENTER : cuePainter.cueAlignment;
            StaticLayout staticLayout = r8;
            StaticLayout staticLayout2 = new StaticLayout(cuePainter.cueText, cuePainter.textPaint, i10, alignment, cuePainter.spacingMult, cuePainter.spacingAdd, true);
            cuePainter.textLayout = staticLayout;
            int height = cuePainter.textLayout.getHeight();
            int lineCount = cuePainter.textLayout.getLineCount();
            int i12 = 0;
            for (i11 = 0; i11 < lineCount; i11++) {
                i12 = Math.max((int) Math.ceil((double) cuePainter.textLayout.getLineWidth(i11)), i12);
            }
            i12 += i8;
            i8 = (i9 - i12) / 2;
            lineCount = i8 + i12;
            i11 = (cuePainter.parentBottom - height) - ((int) (f2 * DEFAULT_BOTTOM_PADDING_FRACTION));
            if (cue2.position != -1) {
                if (cue2.alignment == Alignment.ALIGN_OPPOSITE) {
                    lineCount = ((i9 * cue2.position) / 100) + cuePainter.parentLeft;
                    i8 = Math.max(lineCount - i12, cuePainter.parentLeft);
                } else {
                    i8 = ((i9 * cue2.position) / 100) + cuePainter.parentLeft;
                    lineCount = Math.min(i12 + i8, cuePainter.parentRight);
                }
            }
            if (cue2.line != -1) {
                i11 = ((i5 * cue2.line) / 100) + cuePainter.parentTop;
                if (i11 + height > cuePainter.parentBottom) {
                    i11 = cuePainter.parentBottom - height;
                }
            }
            cuePainter.textLayout = new StaticLayout(cuePainter.cueText, cuePainter.textPaint, lineCount - i8, alignment, cuePainter.spacingMult, cuePainter.spacingAdd, true);
            cuePainter.textLeft = i8;
            cuePainter.textTop = i11;
            cuePainter.textPaddingX = i6;
            drawLayout(canvas);
        }
    }

    private void drawLayout(Canvas canvas) {
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout != null) {
            int i;
            int save = canvas.save();
            canvas.translate((float) this.textLeft, (float) this.textTop);
            if (Color.alpha(this.windowColor) > 0) {
                this.paint.setColor(this.windowColor);
                canvas.drawRect((float) (-this.textPaddingX), BitmapDescriptorFactory.HUE_RED, (float) (staticLayout.getWidth() + this.textPaddingX), (float) staticLayout.getHeight(), this.paint);
            }
            if (Color.alpha(this.backgroundColor) > 0) {
                this.paint.setColor(this.backgroundColor);
                float lineTop = (float) staticLayout.getLineTop(0);
                int lineCount = staticLayout.getLineCount();
                float f = lineTop;
                for (i = 0; i < lineCount; i++) {
                    this.lineBounds.left = staticLayout.getLineLeft(i) - ((float) this.textPaddingX);
                    this.lineBounds.right = staticLayout.getLineRight(i) + ((float) this.textPaddingX);
                    this.lineBounds.top = f;
                    this.lineBounds.bottom = (float) staticLayout.getLineBottom(i);
                    f = this.lineBounds.bottom;
                    canvas.drawRoundRect(this.lineBounds, this.cornerRadius, this.cornerRadius, this.paint);
                }
            }
            Object obj = 1;
            if (this.edgeType == 1) {
                this.textPaint.setStrokeJoin(Join.ROUND);
                this.textPaint.setStrokeWidth(this.outlineWidth);
                this.textPaint.setColor(this.edgeColor);
                this.textPaint.setStyle(Style.FILL_AND_STROKE);
                staticLayout.draw(canvas);
            } else if (this.edgeType == 2) {
                this.textPaint.setShadowLayer(this.shadowRadius, this.shadowOffset, this.shadowOffset, this.edgeColor);
            } else if (this.edgeType == 3 || this.edgeType == 4) {
                int i2;
                if (this.edgeType != 3) {
                    obj = null;
                }
                i = -1;
                if (obj != null) {
                    i2 = -1;
                } else {
                    i2 = this.edgeColor;
                }
                if (obj != null) {
                    i = this.edgeColor;
                }
                float f2 = this.shadowRadius / 2.0f;
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Style.FILL);
                float f3 = -f2;
                this.textPaint.setShadowLayer(this.shadowRadius, f3, f3, i2);
                staticLayout.draw(canvas);
                this.textPaint.setShadowLayer(this.shadowRadius, f2, f2, i);
            }
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Style.FILL);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
            canvas.restoreToCount(save);
        }
    }
}
