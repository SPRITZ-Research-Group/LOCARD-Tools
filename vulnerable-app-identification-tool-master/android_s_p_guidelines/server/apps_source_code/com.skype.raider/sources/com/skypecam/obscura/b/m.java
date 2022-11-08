package com.skypecam.obscura.b;

import android.hardware.Camera;

final class m implements e {
    m() {
    }

    public final Camera a(int id) {
        return Camera.open(id);
    }
}
