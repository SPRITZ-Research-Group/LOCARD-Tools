package net.hockeyapp.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.hockeyapp.android.d.b;
import net.hockeyapp.android.f.a;
import net.hockeyapp.android.f.g;
import net.hockeyapp.android.f.j;
import net.hockeyapp.android.h;
import net.hockeyapp.android.h.d;

@SuppressLint({"ViewConstructor"})
public class AttachmentView extends FrameLayout {
    private final Context a;
    private final ViewGroup b;
    private final b c;
    private final Uri d;
    private final String e;
    private ImageView f;
    private TextView g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;

    @SuppressLint({"StaticFieldLeak"})
    public AttachmentView(Context context, ViewGroup parent, Uri attachmentUri) {
        super(context);
        this.a = context;
        this.b = parent;
        this.c = null;
        this.d = attachmentUri;
        this.e = attachmentUri.getLastPathSegment();
        a(10);
        a(context, true);
        this.g.setText(this.e);
        this.g.setContentDescription(this.g.getText());
        a.a(new AsyncTask<Void, Void, Bitmap>(this) {
            final /* synthetic */ AttachmentView a;

            {
                this.a = this$0;
            }

            protected final /* synthetic */ void onPostExecute(Object obj) {
                Bitmap bitmap = (Bitmap) obj;
                if (bitmap != null) {
                    this.a.a(bitmap, false);
                } else {
                    this.a.a(false);
                }
            }

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                return this.a.j();
            }
        });
    }

    public AttachmentView(Context context, ViewGroup parent, b attachment) {
        super(context);
        this.a = context;
        this.b = parent;
        this.c = attachment;
        this.d = null;
        this.e = attachment.a();
        a(40);
        a(context, false);
        this.m = 1;
        this.g.setText(d.hockeyapp_feedback_attachment_loading);
        this.g.setContentDescription(this.g.getText());
        a(false);
    }

    public final Uri a() {
        return this.d;
    }

    public final int b() {
        return this.h;
    }

    public final int c() {
        return this.i;
    }

    public final int d() {
        return this.j;
    }

    public final int e() {
        return this.k;
    }

    public final int f() {
        return this.l;
    }

    public final int g() {
        return this.m == 0 ? this.k : this.i;
    }

    public final void h() {
        j.a(this.b, this.a.getString(d.hockeyapp_feedback_attachment_removed));
        this.b.removeView(this);
    }

    public void setImage(Bitmap bitmap, int orientation) {
        this.g.setText(this.e);
        this.g.setContentDescription(this.g.getText());
        this.m = orientation;
        if (bitmap == null) {
            a(true);
        } else {
            a(bitmap, true);
        }
    }

    public final void i() {
        this.g.setText(d.hockeyapp_feedback_attachment_error);
        this.g.setContentDescription(this.g.getText());
    }

    private void a(int marginDip) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        this.l = Math.round(TypedValue.applyDimension(1, 10.0f, metrics));
        int layoutMargin = Math.round(TypedValue.applyDimension(1, (float) marginDip, metrics));
        int displayWidth = metrics.widthPixels;
        int parentWidthLandscape = (displayWidth - (layoutMargin * 2)) - this.l;
        this.h = ((displayWidth - (layoutMargin * 2)) - (this.l * 2)) / 3;
        this.j = parentWidthLandscape / 2;
        this.i = this.h * 2;
        this.k = this.j;
    }

    private void a(Context context, boolean removable) {
        setLayoutParams(new LayoutParams(-2, -2, 80));
        setPadding(0, this.l, 0, 0);
        j.a(this.b, this.a.getString(d.hockeyapp_feedback_attachment_added));
        this.f = new ImageView(context);
        LinearLayout bottomView = new LinearLayout(context);
        bottomView.setLayoutParams(new LayoutParams(-1, -2, 80));
        bottomView.setGravity(8388611);
        bottomView.setOrientation(1);
        bottomView.setBackgroundColor(Color.parseColor("#80262626"));
        this.g = new TextView(context);
        this.g.setLayoutParams(new LayoutParams(-1, -2, 17));
        this.g.setGravity(17);
        this.g.setTextColor(context.getResources().getColor(h.a.hockeyapp_text_white));
        this.g.setSingleLine();
        this.g.setEllipsize(TruncateAt.MIDDLE);
        if (removable) {
            ImageButton imageButton = new ImageButton(context);
            imageButton.setLayoutParams(new LayoutParams(-1, -2, 80));
            imageButton.setAdjustViewBounds(true);
            imageButton.setImageDrawable(a("ic_menu_delete"));
            imageButton.setBackgroundResource(0);
            imageButton.setContentDescription(context.getString(d.hockeyapp_feedback_attachment_remove_description));
            imageButton.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AttachmentView a;

                {
                    this.a = this$0;
                }

                public final void onClick(View v) {
                    this.a.h();
                }
            });
            imageButton.setOnFocusChangeListener(new OnFocusChangeListener(this) {
                final /* synthetic */ AttachmentView a;

                {
                    this.a = this$0;
                }

                public final void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        j.a(this.a.g, this.a.g.getText());
                    }
                }
            });
            bottomView.addView(imageButton);
        }
        bottomView.addView(this.g);
        addView(this.f);
        addView(bottomView);
    }

    private void a(Bitmap bitmap, final boolean openOnClick) {
        int width = this.m == 0 ? this.j : this.h;
        int height = this.m == 0 ? this.k : this.i;
        this.g.setMaxWidth(width);
        this.g.setMinWidth(width);
        this.f.setLayoutParams(new LayoutParams(-2, -2));
        this.f.setAdjustViewBounds(true);
        this.f.setMinimumWidth(width);
        this.f.setMaxWidth(width);
        this.f.setMaxHeight(height);
        this.f.setScaleType(ScaleType.CENTER_INSIDE);
        this.f.setImageBitmap(bitmap);
        this.f.setContentDescription(this.g.getText());
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AttachmentView b;

            public final void onClick(View v) {
                if (openOnClick) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setDataAndType(this.b.d, "image/*");
                    this.b.a.startActivity(intent);
                }
            }
        });
    }

    private void a(final boolean openOnClick) {
        this.g.setMaxWidth(this.h);
        this.g.setMinWidth(this.h);
        this.f.setLayoutParams(new LayoutParams(-2, -2));
        this.f.setAdjustViewBounds(false);
        this.f.setBackgroundColor(Color.parseColor("#eeeeee"));
        this.f.setMinimumHeight((int) (((float) this.h) * 1.2f));
        this.f.setMinimumWidth(this.h);
        this.f.setScaleType(ScaleType.FIT_CENTER);
        this.f.setImageDrawable(a("ic_menu_attachment"));
        this.f.setContentDescription(this.g.getText());
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AttachmentView b;

            public final void onClick(View v) {
                if (openOnClick) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setDataAndType(this.b.d, "*/*");
                    this.b.a.startActivity(intent);
                }
            }
        });
    }

    private Bitmap j() {
        try {
            this.m = g.a(this.a, this.d);
            return g.a(this.a, this.d, this.m == 0 ? this.j : this.h, this.m == 0 ? this.k : this.i);
        } catch (Throwable th) {
            return null;
        }
    }

    private Drawable a(String name) {
        if (VERSION.SDK_INT >= 21) {
            return getResources().getDrawable(getResources().getIdentifier(name, "drawable", "android"), this.a.getTheme());
        }
        return getResources().getDrawable(getResources().getIdentifier(name, "drawable", "android"));
    }
}
