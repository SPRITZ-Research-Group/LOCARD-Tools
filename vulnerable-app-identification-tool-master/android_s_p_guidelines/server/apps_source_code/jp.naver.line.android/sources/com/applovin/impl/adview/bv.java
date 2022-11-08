package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class bv implements OnErrorListener {
    final /* synthetic */ az a;

    bv(az azVar) {
        this.a = azVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.w.post(new bw(this, i, i2));
        return true;
    }
}
