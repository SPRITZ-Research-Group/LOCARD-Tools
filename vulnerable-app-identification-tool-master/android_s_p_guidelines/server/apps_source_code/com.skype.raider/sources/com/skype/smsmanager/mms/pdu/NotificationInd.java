package com.skype.smsmanager.mms.pdu;

import com.skype.smsmanager.mms.InvalidHeaderValueException;

public class NotificationInd extends GenericPdu {
    public NotificationInd() throws InvalidHeaderValueException {
        a(130);
    }

    NotificationInd(PduHeaders headers) {
        super(headers);
    }

    public final void a(EncodedStringValue value) {
        this.a.a(value, 137);
    }
}
