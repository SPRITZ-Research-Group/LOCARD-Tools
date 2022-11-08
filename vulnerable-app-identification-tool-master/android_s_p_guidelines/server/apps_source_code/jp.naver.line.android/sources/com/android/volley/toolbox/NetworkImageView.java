package com.android.volley.toolbox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class NetworkImageView extends ImageView {
    private String a;
    private int b;
    private int c;
    private a d;
    private d e;

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageUrl(String str, a aVar) {
        this.a = str;
        this.d = aVar;
        a(false);
    }

    public void setDefaultImageResId(int i) {
        this.b = i;
    }

    public void setErrorImageResId(int i) {
        this.c = i;
    }

    private void a(final boolean z) {
        Object obj;
        Object obj2;
        int width = getWidth();
        int height = getHeight();
        ScaleType scaleType = getScaleType();
        Object obj3 = 1;
        if (getLayoutParams() != null) {
            obj = getLayoutParams().width == -2 ? 1 : null;
            if (getLayoutParams().height == -2) {
                obj2 = 1;
                if (obj == null || obj2 == null) {
                    obj3 = null;
                }
                if (width == 0 || height != 0 || obj3 != null) {
                    if (!TextUtils.isEmpty(this.a)) {
                        if (!(this.e == null || this.e.c() == null)) {
                            if (this.e.c().equals(this.a)) {
                                this.e.a();
                                a();
                            }
                        }
                        if (obj != null) {
                            width = 0;
                        }
                        this.e = this.d.a(this.a, new e(this) {
                            final /* synthetic */ NetworkImageView b;

                            public final void a(final d dVar, boolean z) {
                                if (z && z) {
                                    this.b.post(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 b;

                                        public final void run() {
                                            this.b.a(dVar, false);
                                        }
                                    });
                                } else if (dVar.b() != null) {
                                    this.b.setImageBitmap(dVar.b());
                                } else {
                                    if (this.b.b != 0) {
                                        this.b.setImageResource(this.b.b);
                                    }
                                }
                            }
                        }, width, obj2 == null ? 0 : height, scaleType);
                    }
                    if (this.e != null) {
                        this.e.a();
                        this.e = null;
                    }
                    a();
                }
                return;
            }
        }
        obj = null;
        obj2 = null;
        obj3 = null;
        if (width == 0) {
        }
        if (!TextUtils.isEmpty(this.a)) {
            if (this.e != null) {
                this.e.a();
                this.e = null;
            }
            a();
        } else if (this.e.c().equals(this.a)) {
            this.e.a();
            a();
            if (obj != null) {
                width = 0;
            }
            if (obj2 == null) {
            }
            this.e = this.d.a(this.a, /* anonymous class already generated */, width, obj2 == null ? 0 : height, scaleType);
        }
    }

    private void a() {
        if (this.b != 0) {
            setImageResource(this.b);
        } else {
            setImageBitmap(null);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(true);
    }

    protected void onDetachedFromWindow() {
        if (this.e != null) {
            this.e.a();
            setImageBitmap(null);
            this.e = null;
        }
        super.onDetachedFromWindow();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
