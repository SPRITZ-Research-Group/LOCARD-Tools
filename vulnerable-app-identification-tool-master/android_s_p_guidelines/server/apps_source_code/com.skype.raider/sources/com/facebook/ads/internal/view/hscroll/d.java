package com.facebook.ads.internal.view.hscroll;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.g;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class d extends RecyclerView implements OnTouchListener {
    protected final int I = t();
    protected int J = 0;
    private int K = 0;
    private boolean L = true;
    private boolean M = false;
    private LinearLayoutManager N;
    private a O;

    public interface a {
        int g(int i);
    }

    public d(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnTouchListener(this);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnTouchListener(this);
    }

    private int t() {
        return ((int) getContext().getResources().getDisplayMetrics().density) * 10;
    }

    protected void a(int i, boolean z) {
        if (c() != null) {
            this.J = i;
            if (z) {
                d(i);
            } else {
                b(i);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 6 || actionMasked == 3 || actionMasked == 4) {
            if (this.M) {
                rawX = this.K - rawX;
                actionMasked = this.O.g(rawX);
                if (rawX > this.I) {
                    actionMasked += this.J;
                    if (c() == null) {
                        rawX = 0;
                    } else {
                        rawX = c().a();
                    }
                    rawX = Math.min(actionMasked, rawX - 1);
                } else {
                    rawX = rawX < (-this.I) ? Math.max(this.J - actionMasked, 0) : this.J;
                }
                a(rawX, true);
            }
            this.L = true;
            this.M = false;
            return true;
        } else if (actionMasked != 0 && actionMasked != 5 && (!this.L || actionMasked != 2)) {
            return false;
        } else {
            this.K = rawX;
            if (this.L) {
                this.L = false;
            }
            this.M = true;
            return false;
        }
    }

    public void setLayoutManager(g gVar) {
        if (gVar instanceof LinearLayoutManager) {
            super.setLayoutManager(gVar);
            this.N = (LinearLayoutManager) gVar;
            return;
        }
        throw new IllegalArgumentException("SnapRecyclerView only supports LinearLayoutManager");
    }

    public void setSnapDelegate(a aVar) {
        this.O = aVar;
    }
}
