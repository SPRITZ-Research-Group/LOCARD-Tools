package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;

public final class d {
    private Bundle a = new Bundle();

    public final d a(Parcel parcel) {
        CameraEffectArguments cameraEffectArguments = (CameraEffectArguments) parcel.readParcelable(CameraEffectArguments.class.getClassLoader());
        if (cameraEffectArguments != null) {
            this.a.putAll(cameraEffectArguments.a);
        }
        return this;
    }
}
