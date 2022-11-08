package com.facebook.login.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ai;
import com.facebook.internal.ae;
import com.facebook.internal.aj;
import com.facebook.internal.ak;
import com.facebook.internal.al;
import com.facebook.internal.am;
import com.facebook.internal.ar;
import com.facebook.internal.bj;
import com.facebook.login.o;
import com.facebook.login.p;
import com.facebook.login.u;
import com.facebook.n;
import com.google.android.exoplayer.SampleSource;

public class ProfilePictureView extends FrameLayout {
    public static final String a = "ProfilePictureView";
    private String b;
    private int c = 0;
    private int d = 0;
    private boolean e = true;
    private Bitmap f;
    private ImageView g;
    private int h = -1;
    private aj i;
    private e j;
    private Bitmap k = null;

    public ProfilePictureView(Context context) {
        super(context);
        a(context);
    }

    public ProfilePictureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        a(attributeSet);
    }

    public ProfilePictureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
        a(attributeSet);
    }

    public final void setPresetSize(int i) {
        switch (i) {
            case SampleSource.FORMAT_READ /*-4*/:
            case SampleSource.SAMPLE_READ /*-3*/:
            case -2:
            case -1:
                this.h = i;
                requestLayout();
                return;
            default:
                throw new IllegalArgumentException("Must use a predefined preset size");
        }
    }

    public final void setCropped(boolean z) {
        this.e = z;
        a(false);
    }

    public final void setProfileId(String str) {
        boolean z;
        if (bj.a(this.b) || !this.b.equalsIgnoreCase(str)) {
            a();
            z = true;
        } else {
            z = false;
        }
        this.b = str;
        a(z);
    }

    public final void setOnErrorListener(e eVar) {
        this.j = eVar;
    }

    public final void setDefaultProfilePicture(Bitmap bitmap) {
        this.k = bitmap;
    }

    protected void onMeasure(int i, int i2) {
        Object obj;
        LayoutParams layoutParams = getLayoutParams();
        int size = MeasureSpec.getSize(i2);
        int size2 = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i2) == 1073741824 || layoutParams.height != -2) {
            obj = null;
        } else {
            size = c(true);
            i2 = MeasureSpec.makeMeasureSpec(size, 1073741824);
            obj = 1;
        }
        if (MeasureSpec.getMode(i) != 1073741824 && layoutParams.width == -2) {
            size2 = c(true);
            i = MeasureSpec.makeMeasureSpec(size2, 1073741824);
            obj = 1;
        }
        if (obj != null) {
            setMeasuredDimension(size2, size);
            measureChildren(i, i2);
            return;
        }
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(false);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Parcelable bundle = new Bundle();
        bundle.putParcelable("ProfilePictureView_superState", onSaveInstanceState);
        bundle.putString("ProfilePictureView_profileId", this.b);
        bundle.putInt("ProfilePictureView_presetSize", this.h);
        bundle.putBoolean("ProfilePictureView_isCropped", this.e);
        bundle.putInt("ProfilePictureView_width", this.d);
        bundle.putInt("ProfilePictureView_height", this.c);
        bundle.putBoolean("ProfilePictureView_refresh", this.i != null);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable.getClass() != Bundle.class) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("ProfilePictureView_superState"));
        this.b = bundle.getString("ProfilePictureView_profileId");
        this.h = bundle.getInt("ProfilePictureView_presetSize");
        this.e = bundle.getBoolean("ProfilePictureView_isCropped");
        this.d = bundle.getInt("ProfilePictureView_width");
        this.c = bundle.getInt("ProfilePictureView_height");
        a(true);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i = null;
    }

    private void a(Context context) {
        removeAllViews();
        this.g = new ImageView(context);
        this.g.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.g.setScaleType(ScaleType.CENTER_INSIDE);
        addView(this.g);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, u.com_facebook_profile_picture_view);
        setPresetSize(obtainStyledAttributes.getInt(u.com_facebook_profile_picture_view_com_facebook_preset_size, -1));
        this.e = obtainStyledAttributes.getBoolean(u.com_facebook_profile_picture_view_com_facebook_is_cropped, true);
        obtainStyledAttributes.recycle();
    }

    private void a(boolean z) {
        boolean b = b();
        if (this.b == null || this.b.length() == 0 || (this.d == 0 && this.c == 0)) {
            a();
            return;
        }
        if (b || z) {
            b(true);
        }
    }

    private void a() {
        if (this.i != null) {
            ae.b(this.i);
        }
        if (this.k == null) {
            a(BitmapFactory.decodeResource(getResources(), this.e ? p.com_facebook_profile_picture_blank_square : p.com_facebook_profile_picture_blank_portrait));
            return;
        }
        b();
        a(Bitmap.createScaledBitmap(this.k, this.d, this.c, false));
    }

    private void a(Bitmap bitmap) {
        if (this.g != null && bitmap != null) {
            this.f = bitmap;
            this.g.setImageBitmap(bitmap);
        }
    }

    private void b(boolean z) {
        aj a = new ak(getContext(), aj.a(this.b, this.d, this.c)).a(z).a((Object) this).a(new al(this) {
            final /* synthetic */ ProfilePictureView a;

            {
                this.a = r1;
            }

            public final void a(am amVar) {
                ProfilePictureView.a(this.a, amVar);
            }
        }).a();
        if (this.i != null) {
            ae.b(this.i);
        }
        this.i = a;
        ae.a(a);
    }

    private boolean b() {
        int height = getHeight();
        int width = getWidth();
        boolean z = false;
        if (width <= 0 || height <= 0) {
            return false;
        }
        int c = c(false);
        if (c != 0) {
            height = c;
            width = height;
        }
        if (width <= height) {
            height = this.e ? width : 0;
        } else {
            width = this.e ? height : 0;
        }
        if (!(width == this.d && height == this.c)) {
            z = true;
        }
        this.d = width;
        this.c = height;
        return z;
    }

    private int c(boolean z) {
        int i;
        switch (this.h) {
            case SampleSource.FORMAT_READ /*-4*/:
                i = o.com_facebook_profilepictureview_preset_size_large;
                break;
            case SampleSource.SAMPLE_READ /*-3*/:
                i = o.com_facebook_profilepictureview_preset_size_normal;
                break;
            case -2:
                i = o.com_facebook_profilepictureview_preset_size_small;
                break;
            case -1:
                if (z) {
                    i = o.com_facebook_profilepictureview_preset_size_normal;
                    break;
                }
                return 0;
            default:
                return 0;
        }
        return getResources().getDimensionPixelSize(i);
    }

    static /* synthetic */ void a(ProfilePictureView profilePictureView, am amVar) {
        if (amVar.a() == profilePictureView.i) {
            profilePictureView.i = null;
            Bitmap c = amVar.c();
            Throwable b = amVar.b();
            if (b != null) {
                if (profilePictureView.j != null) {
                    StringBuilder stringBuilder = new StringBuilder("Error in downloading profile picture for profileId: ");
                    stringBuilder.append(profilePictureView.b);
                    n nVar = new n(stringBuilder.toString(), b);
                    return;
                }
                ar.a(ai.REQUESTS, 6, a, b.toString());
            } else if (c != null) {
                profilePictureView.a(c);
                if (amVar.d()) {
                    profilePictureView.b(false);
                }
            }
        }
    }
}
