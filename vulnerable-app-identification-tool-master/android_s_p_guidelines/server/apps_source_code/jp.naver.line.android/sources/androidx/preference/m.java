package androidx.preference;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final class m implements OnCheckedChangeListener {
    final /* synthetic */ SwitchPreference a;

    m(SwitchPreference switchPreference) {
        this.a = switchPreference;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SwitchPreference switchPreference = this.a;
        Boolean.valueOf(z);
        if (switchPreference.l()) {
            this.a.d(z);
        } else {
            compoundButton.setChecked(z ^ 1);
        }
    }
}
