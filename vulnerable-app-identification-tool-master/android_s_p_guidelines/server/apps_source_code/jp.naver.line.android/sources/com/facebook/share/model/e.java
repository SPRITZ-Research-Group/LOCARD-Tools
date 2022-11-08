package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;

public final class e {
    private Bundle a = new Bundle();

    public final e a(Parcel parcel) {
        CameraEffectTextures cameraEffectTextures = (CameraEffectTextures) parcel.readParcelable(CameraEffectTextures.class.getClassLoader());
        if (cameraEffectTextures != null) {
            this.a.putAll(cameraEffectTextures.a);
        }
        return this;
    }
}
