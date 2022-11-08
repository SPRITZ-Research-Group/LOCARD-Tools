package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class bs implements OnErrorListener {
    final /* synthetic */ br a;

    bs(br brVar) {
        this.a = brVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.a.w.post(new bt(this, i, i2));
        return true;
    }
}
