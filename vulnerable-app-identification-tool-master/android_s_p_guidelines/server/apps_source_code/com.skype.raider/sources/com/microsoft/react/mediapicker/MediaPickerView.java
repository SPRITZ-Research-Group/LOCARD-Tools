package com.microsoft.react.mediapicker;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.f;
import android.support.v7.widget.RecyclerView.s;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.common.e;
import com.facebook.react.bridge.ai;
import com.skype.Defines;
import java.util.HashSet;
import java.util.Set;

public class MediaPickerView extends RecyclerView {
    private final ai I;
    private int J;
    private int K;
    private Set<com.microsoft.react.a.c> L;
    private boolean M;
    private int N;
    private boolean O;
    private boolean P;
    private int Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private String V;
    private GridLayoutManager W;
    private boolean aa;
    private a ab;
    private boolean ac;

    private class a extends f {
        final /* synthetic */ MediaPickerView a;

        private a(MediaPickerView mediaPickerView) {
            this.a = mediaPickerView;
        }

        /* synthetic */ a(MediaPickerView x0, byte b) {
            this(x0);
        }

        public final void a(Rect outRect, View view) {
            outRect.top = 1;
            outRect.left = 1;
            outRect.bottom = 1;
            outRect.right = 1;
        }
    }

    private class b extends s {
        final OnClickListener n = new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = this$1;
            }

            public final void onClick(View v) {
                boolean checkedState;
                if (this.a.o.L.contains(this.a.v)) {
                    checkedState = false;
                } else {
                    checkedState = true;
                }
                if (this.a.o.M && this.a.o.N != 0) {
                    if (!checkedState) {
                        this.a.o.L.remove(this.a.v);
                        this.a.s.setContentDescription(this.a.a(this.a.w.a(this.a.v), this.a.v.a.c, false));
                    } else if (this.a.o.L.size() != this.a.o.N) {
                        this.a.o.L.add(this.a.v);
                        this.a.s.setContentDescription(this.a.a(this.a.w.a(this.a.v), this.a.v.a.c, true));
                    } else {
                        return;
                    }
                    this.a.b(checkedState);
                    this.a.o.ab.a(this.a.v, checkedState, this.a.w.a());
                } else if (checkedState) {
                    this.a.o.ab.a(this.a.v, true, this.a.w.a());
                }
            }
        };
        final /* synthetic */ MediaPickerView o;
        private final int p = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
        private final e q = new e(Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE);
        private final String r = "00";
        private SimpleDraweeView s;
        private ViewGroup t;
        private TextView u;
        private com.microsoft.react.a.c v;
        private b w;

        public b(MediaPickerView mediaPickerView, View itemView) {
            this.o = mediaPickerView;
            super(itemView);
            this.s = (SimpleDraweeView) itemView.findViewById(com.microsoft.react.mediapicker.e.a.sdvThumbnail);
            this.s.setOnClickListener(this.n);
            this.t = (ViewGroup) itemView.findViewById(com.microsoft.react.mediapicker.e.a.selected_container);
            this.t.setEnabled(false);
            this.u = (TextView) itemView.findViewById(com.microsoft.react.mediapicker.e.a.video_length);
        }

        private String a(int index, boolean isVideo, boolean isSelected) {
            return (isSelected ? this.o.T : this.o.S).replace(MediaPickerViewManager.IndexReplacementKey, Integer.toString(index + 1)).replace(MediaPickerViewManager.TotalReplacementKey, Integer.toString(this.w.a())).replace(MediaPickerViewManager.TypeReplacementKey, isVideo ? this.o.V : this.o.U);
        }

        public final void b(boolean on) {
            this.t.setVisibility(on ? 0 : 4);
        }

        public final void a(com.microsoft.react.a.c media, b gallery) {
            if (media == null || media.a == null || this.v == null || this.v.a == null || media.a != this.v.a) {
                Uri mediaUri;
                this.v = media;
                this.w = gallery;
                boolean thumbnailAvailable = this.v.b != null;
                if (thumbnailAvailable) {
                    mediaUri = this.v.b.a;
                } else {
                    mediaUri = this.v.a.a;
                }
                if (!thumbnailAvailable && this.v.a.c) {
                    FLog.d(MediaPickerViewManager.REACT_CLASS, "Thumbnail unavailable for video, generating");
                    com.microsoft.react.a.e.a(this.o.I, this.v);
                }
                com.facebook.imagepipeline.k.c requestBuilder = com.facebook.imagepipeline.k.c.a(mediaUri).j().a(this.q);
                if (thumbnailAvailable) {
                    requestBuilder.a(new f(this.v));
                } else {
                    requestBuilder.a(RotationOptions.a());
                }
                com.facebook.imagepipeline.k.b request = requestBuilder.q();
                com.facebook.imagepipeline.k.b fallBackRequest = null;
                if (thumbnailAvailable) {
                    fallBackRequest = com.facebook.imagepipeline.k.c.a(this.v.a.a).j().a(this.q).a(RotationOptions.a()).q();
                }
                com.facebook.drawee.backends.pipeline.c controllerBuilder = Fresco.a();
                if (fallBackRequest != null) {
                    controllerBuilder.a((Object[]) new com.facebook.imagepipeline.k.b[]{request, fallBackRequest});
                } else {
                    controllerBuilder.b((Object) request);
                }
                this.s.setController(((com.facebook.drawee.backends.pipeline.c) controllerBuilder.a(this.s.getController())).h());
                if (this.v.a.c) {
                    this.u.setVisibility(0);
                    String duration = DateUtils.formatElapsedTime(this.v.a.d);
                    if (duration.startsWith("00")) {
                        duration = duration.substring(1);
                    }
                    this.u.setText(duration);
                } else {
                    this.u.setVisibility(4);
                }
                if (this.o.S != null) {
                    this.s.setContentDescription(a(this.w.a(this.v), this.v.a.c, false));
                }
                b(this.o.L.contains(this.v));
            }
        }
    }

    private class c extends android.support.v7.widget.RecyclerView.a<b> {
        final /* synthetic */ MediaPickerView a;
        private b b;

        c(MediaPickerView mediaPickerView, b gallery) {
            this.a = mediaPickerView;
            this.b = gallery;
            super.d();
        }

        public final long a(int position) {
            return this.b.a(position).a.f;
        }

        public final int a() {
            return this.b.a();
        }

        public final /* synthetic */ s a(ViewGroup viewGroup, int i) {
            return new b(this.a, LayoutInflater.from(this.a.I).inflate(com.microsoft.react.mediapicker.e.b.gallery_item, null));
        }
    }

    public MediaPickerView(Context context) {
        this(context, null, 0);
    }

    public MediaPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediaPickerView(Context context, AttributeSet attrs, int defStyle) {
        boolean isLandscape;
        super(context, attrs, defStyle);
        this.L = new HashSet();
        this.M = true;
        this.N = 5;
        this.O = true;
        this.P = false;
        this.Q = 2;
        this.R = "";
        this.aa = false;
        this.ac = true;
        if (getResources().getConfiguration().orientation == 2) {
            isLandscape = true;
        } else {
            isLandscape = false;
        }
        this.K = isLandscape ? 4 : 3;
        this.I = (ai) context;
        this.W = new GridLayoutManager(this.I, this.K);
        this.ab = new a(this.I, this);
        setLayoutManager(this.W);
        setNestedScrollingEnabled(true);
        a(new a());
    }

    private void u() {
        b gallery = new b(this.I, this.R, this.O, this.P);
        a((android.support.v7.widget.RecyclerView.a) new c(this, gallery));
        scrollBy(0, 1);
        gallery.a(new com.microsoft.react.mediapicker.b.a(this) {
            final /* synthetic */ MediaPickerView a;

            {
                this.a = this$0;
            }

            public final void a(b gallery) {
                MediaPickerView.a(this.a);
                this.a.ab.a(gallery.a());
                FLog.v(MediaPickerViewManager.REACT_CLASS, "Gallery loaded");
            }
        });
        gallery.b();
    }

    public final void t() {
        this.ab.a(this.L);
    }

    public void setAllowMultipleSelection(boolean allow) {
        this.M = allow;
    }

    public void setMaxSelectionCount(int count) {
        this.N = count;
    }

    public void setAllowVideo(boolean allowVideo) {
        if (allowVideo != this.O) {
            this.O = allowVideo;
            u();
        }
    }

    public void setDisableGifs(boolean disableGifs) {
        if (disableGifs != this.P) {
            this.P = disableGifs;
            u();
        }
    }

    public void setGridPadding(int padding) {
        this.Q = padding;
    }

    public void setMaxThumbnailSize(int size) {
        this.J = size;
        this.K = v();
    }

    private int v() {
        DisplayMetrics displayMetrics = this.I.getResources().getDisplayMetrics();
        return (int) Math.ceil(((double) (((float) displayMetrics.widthPixels) / displayMetrics.density)) / ((double) this.J));
    }

    public void setAlbum(String album) {
        if (album == null) {
            return;
        }
        if (this.ac || !TextUtils.equals(album, this.R)) {
            this.R = album;
            this.ac = false;
            this.L.clear();
            this.ab.a(null, false, 0);
            u();
        }
    }

    public void setAccessibilityLabelSelected(String label) {
        this.T = label;
    }

    public void setAccessibilityLabelDefault(String label) {
        this.S = label;
    }

    public void setVideoAccessibilityLabel(String label) {
        this.V = label;
    }

    public void setPhotoAccessibilityLabel(String label) {
        this.U = label;
    }

    public void setDisableScrolling(boolean disableScrolling) {
        this.aa = disableScrolling;
    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        return !this.aa && super.onInterceptTouchEvent(e);
    }

    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.K = v();
        this.W.a(this.K);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        u();
    }

    public final void e(int state) {
        super.e(state);
        if (state == 0) {
            if ((this.W.l() == 0 ? 1 : null) != null) {
                this.ab.a();
            }
        }
    }

    static /* synthetic */ void a(MediaPickerView x0) {
        x0.c().f();
        x0.scrollBy(0, 1);
    }
}
