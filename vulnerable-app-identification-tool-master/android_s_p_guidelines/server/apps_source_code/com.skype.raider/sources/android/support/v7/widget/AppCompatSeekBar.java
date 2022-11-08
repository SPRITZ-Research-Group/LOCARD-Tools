package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.a.a;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class AppCompatSeekBar extends SeekBar {
    private final l a;

    public AppCompatSeekBar(Context context) {
        this(context, null);
    }

    public AppCompatSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, a.seekBarStyle);
    }

    public AppCompatSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.a = new l(this);
        this.a.a(attrs, defStyleAttr);
    }

    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a.a(canvas);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.a.c();
    }

    @RequiresApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.a.b();
    }
}
