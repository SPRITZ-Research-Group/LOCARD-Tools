package androidx.preference;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final class a implements OnCheckedChangeListener {
    final /* synthetic */ CheckBoxPreference a;

    a(CheckBoxPreference checkBoxPreference) {
        this.a = checkBoxPreference;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        CheckBoxPreference checkBoxPreference = this.a;
        Boolean.valueOf(z);
        if (checkBoxPreference.l()) {
            this.a.d(z);
        } else {
            compoundButton.setChecked(z ^ 1);
        }
    }
}
