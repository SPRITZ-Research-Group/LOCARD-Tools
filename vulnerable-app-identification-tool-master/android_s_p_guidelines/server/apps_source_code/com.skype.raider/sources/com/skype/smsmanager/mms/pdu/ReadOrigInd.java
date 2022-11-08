package com.skype.smsmanager.mms.pdu;

import com.skype.smsmanager.mms.InvalidHeaderValueException;

public class ReadOrigInd extends GenericPdu {
    public ReadOrigInd() throws InvalidHeaderValueException {
        a(136);
    }

    ReadOrigInd(PduHeaders headers) {
        super(headers);
    }

    public final void a(EncodedStringValue value) {
        this.a.a(value, 137);
    }
}
