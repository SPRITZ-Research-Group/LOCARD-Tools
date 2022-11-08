package com.google.android.exoplayer2.drm;

import android.media.DeniedByServerException;
import android.media.MediaCryptoException;
import android.media.NotProvisionedException;
import android.media.ResourceBusyException;
import java.util.Map;

public interface d<T extends c> {

    public interface a {
    }

    public interface b {
    }

    byte[] a() throws NotProvisionedException, ResourceBusyException;

    a b() throws NotProvisionedException;

    byte[] c() throws NotProvisionedException, DeniedByServerException;

    b d();

    Map<String, String> e();

    T f() throws MediaCryptoException;
}
