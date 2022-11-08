package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class bu implements OnCompletionListener {
    final /* synthetic */ az a;

    bu(az azVar) {
        this.a = azVar;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.h();
    }
}
