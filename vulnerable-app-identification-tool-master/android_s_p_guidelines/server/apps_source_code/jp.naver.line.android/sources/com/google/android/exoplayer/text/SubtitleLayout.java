package com.google.android.exoplayer.text;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public final class SubtitleLayout extends View {
    private List<Cue> cues;
    private float fontScale;
    private final List<CuePainter> painters;
    private CaptionStyleCompat style;

    public SubtitleLayout(Context context) {
        this(context, null);
    }

    public SubtitleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.painters = new ArrayList();
        this.fontScale = 1.0f;
        this.style = CaptionStyleCompat.DEFAULT;
    }

    public final void setCues(List<Cue> list) {
        if (this.cues != list) {
            int i;
            this.cues = list;
            if (list == null) {
                i = 0;
            } else {
                i = list.size();
            }
            while (this.painters.size() < i) {
                this.painters.add(new CuePainter(getContext()));
            }
            invalidate();
        }
    }

    public final void setFontScale(float f) {
        if (this.fontScale != f) {
            this.fontScale = f;
            invalidate();
        }
    }

    public final void setStyle(CaptionStyleCompat captionStyleCompat) {
        if (this.style != captionStyleCompat) {
            this.style = captionStyleCompat;
            invalidate();
        }
    }

    public final void dispatchDraw(Canvas canvas) {
        int i = 0;
        int size = this.cues == null ? 0 : this.cues.size();
        while (i < size) {
            ((CuePainter) this.painters.get(i)).draw((Cue) this.cues.get(i), this.style, this.fontScale, canvas, getLeft(), getTop(), getRight(), getBottom());
            i++;
        }
    }
}
