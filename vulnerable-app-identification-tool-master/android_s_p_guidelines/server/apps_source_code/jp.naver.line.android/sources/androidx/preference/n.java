package androidx.preference;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final class n implements OnCheckedChangeListener {
    final /* synthetic */ SwitchPreferenceCompat a;

    n(SwitchPreferenceCompat switchPreferenceCompat) {
        this.a = switchPreferenceCompat;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SwitchPreferenceCompat switchPreferenceCompat = this.a;
        Boolean.valueOf(z);
        if (switchPreferenceCompat.l()) {
            this.a.d(z);
        } else {
            compoundButton.setChecked(z ^ 1);
        }
    }
}
