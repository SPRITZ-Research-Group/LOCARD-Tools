package com.google.android.gms.ads.mediation;

import android.os.Bundle;

public interface MediationAdapter {

    public final class zza {
        private int zzdfl;

        public final zza zzaj(int i) {
            this.zzdfl = 1;
            return this;
        }

        public final Bundle zzvx() {
            Bundle bundle = new Bundle();
            bundle.putInt("capabilities", this.zzdfl);
            return bundle;
        }
    }

    void onDestroy();

    void onPause();

    void onResume();
}
