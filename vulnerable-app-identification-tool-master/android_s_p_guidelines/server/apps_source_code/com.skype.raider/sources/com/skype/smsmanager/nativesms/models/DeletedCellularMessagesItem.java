package com.skype.smsmanager.nativesms.models;

import android.os.Bundle;
import com.skype.smsmanager.nativesms.SmsManagerConstants;
import java.util.ArrayList;

public final class DeletedCellularMessagesItem implements SmsManagerConstants {
    private final ArrayList<String> a;

    public DeletedCellularMessagesItem(Bundle bundle) {
        this.a = bundle.getStringArrayList("DeletedMessageIds");
    }

    public final ArrayList<String> a() {
        return this.a;
    }
}
