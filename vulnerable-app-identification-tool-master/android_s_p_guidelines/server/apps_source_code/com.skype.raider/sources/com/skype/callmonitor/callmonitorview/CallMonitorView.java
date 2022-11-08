package com.skype.callmonitor.callmonitorview;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.facebook.common.logging.FLog;
import com.skype.callmonitor.CallMonitorStorage;
import com.skype.callmonitor.R;
import com.skype.callmonitor.symbolview.SymbolElement.SymbolCode;
import com.skype.callmonitor.symbolview.SymbolView;
import com.skype.slimcore.video.RNCallingVideoView;
import com.skype.slimcore.video.VideoViewManagerProvider;
import java.util.HashMap;

public class CallMonitorView extends FrameLayout implements OnClickListener, OnTouchListener {
    private static int a = 200;
    private SymbolView b;
    private ImageView c = ((ImageView) findViewById(R.id.avatar_image));
    private SymbolView d = ((SymbolView) findViewById(R.id.user_icon));
    private SymbolView e;
    private SymbolView f;
    private RNCallingVideoView g = ((RNCallingVideoView) findViewById(R.id.video_view));
    private WindowManager h;
    private CallMonitorEventHandler i;
    private boolean j;
    private boolean k;
    private int l;
    private int m;
    private float n;
    private float o;
    private LayoutParams p;
    private HashMap<String, String> q;

    public CallMonitorView(CallMonitorEventHandler handler, Context context, boolean microphoneEnabled, boolean videoEnabled, LayoutParams layoutParams) {
        super(context, null);
        this.i = handler;
        this.p = layoutParams;
        this.j = microphoneEnabled;
        this.k = videoEnabled;
        this.h = (WindowManager) context.getSystemService("window");
        inflate(getContext(), R.layout.call_monitor, this);
        setOnTouchListener(this);
        this.g.setBorderRadius(15.0f);
        this.b = (SymbolView) findViewById(R.id.microphone);
        this.b.setOnClickListener(this);
        this.f = (SymbolView) findViewById(R.id.call_end_button);
        this.f.setOnClickListener(this);
        this.e = (SymbolView) findViewById(R.id.call_control_video);
        this.e.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.microphone) {
            this.i.a(this.j);
            if (this.j) {
                this.j = false;
                this.b.setSymbolCode(SymbolCode.MicrophoneOff);
                a(this.b, "unmute");
                return;
            }
            this.j = true;
            this.b.setSymbolCode(SymbolCode.Microphone);
            a(this.b, "mute");
        } else if (view.getId() == R.id.call_control_video) {
            this.i.b(this.k);
            if (this.k) {
                this.k = false;
                this.e.setSymbolCode(SymbolCode.VideoOff);
                a(this.e, "videoStart");
                return;
            }
            this.k = true;
            this.e.setSymbolCode(SymbolCode.Video);
            a(this.e, "videoEnd");
        } else if (view.getId() == R.id.call_end_button) {
            this.i.a();
        } else if (view.getId() == R.id.call_monitor_view) {
            this.i.b();
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.l = this.p.x;
                this.m = this.p.y;
                this.n = event.getRawX();
                this.o = event.getRawY();
                break;
            case 1:
                if (event.getEventTime() - event.getDownTime() < ((long) a)) {
                    this.i.b();
                    break;
                }
                break;
            case 2:
                this.p.x = this.l + ((int) (event.getRawX() - this.n));
                this.p.y = this.m + ((int) (event.getRawY() - this.o));
                this.h.updateViewLayout(view, this.p);
                break;
        }
        return true;
    }

    public final void a(int videoObjectId, boolean fit) {
        ((VideoViewManagerProvider) CallMonitorStorage.b().a().get()).e().attachVideo(this.g, videoObjectId, fit);
        this.g.setVisibility(0);
    }

    public final void a(int videoObjectId) {
        ((VideoViewManagerProvider) CallMonitorStorage.b().a().get()).e().detachVideo(videoObjectId, null, null);
        this.g.setVisibility(4);
    }

    public void setUserAvatarAndShowImage(Bitmap bitmap) {
        if (bitmap != null) {
            this.c.setImageBitmap(bitmap);
            this.c.setVisibility(0);
            this.d.setVisibility(4);
        }
    }

    public void setMicrophoneState(boolean microphoneEnabled) {
        this.j = microphoneEnabled;
        if (microphoneEnabled) {
            this.b.setSymbolCode(SymbolCode.Microphone);
        } else {
            this.b.setSymbolCode(SymbolCode.MicrophoneOff);
        }
        a(microphoneEnabled);
    }

    public void setVideoState(boolean videoEnabled) {
        this.k = videoEnabled;
        if (videoEnabled) {
            this.e.setSymbolCode(SymbolCode.Video);
        } else {
            this.e.setSymbolCode(SymbolCode.VideoOff);
        }
        b(videoEnabled);
    }

    public final void a(HashMap<String, String> map) {
        if (map == null) {
            FLog.w("CallMonitorService", "CallMonitorView:updateStrings map is null");
            return;
        }
        this.q = map;
        a(this.j);
        b(this.k);
        a(this.f, "endCall");
    }

    public final void a(int width, int height) {
        LayoutParams layoutParams = this.p;
        layoutParams.x -= (height - this.p.width) / 2;
        layoutParams = this.p;
        layoutParams.y -= (width - this.p.height) / 2;
        this.p.width = width;
        this.p.height = height;
        this.h.updateViewLayout(this, this.p);
    }

    private void a(boolean microphoneEnabled) {
        if (microphoneEnabled) {
            a(this.b, "mute");
        } else {
            a(this.b, "unmute");
        }
    }

    private void b(boolean videoEnabled) {
        if (videoEnabled) {
            a(this.e, "videoEnd");
        } else {
            a(this.e, "videoStart");
        }
    }

    private void a(SymbolView view, String key) {
        if (this.q != null) {
            CharSequence contentDescription = (CharSequence) this.q.get(key);
            if (contentDescription != null) {
                view.setContentDescription(contentDescription);
            }
        }
    }
}
