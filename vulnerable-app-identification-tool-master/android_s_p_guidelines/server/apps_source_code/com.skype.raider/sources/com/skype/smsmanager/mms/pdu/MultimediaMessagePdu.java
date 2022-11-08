package com.skype.smsmanager.mms.pdu;

import com.skype.smsmanager.mms.InvalidHeaderValueException;

public class MultimediaMessagePdu extends GenericPdu {
    private PduBody b;

    public MultimediaMessagePdu(PduHeaders header, PduBody body) {
        super(header);
        this.b = body;
    }

    public final PduBody c() {
        return this.b;
    }

    public final void a(PduBody body) {
        this.b = body;
    }

    public final void d() throws InvalidHeaderValueException {
        this.a.a(129, 143);
    }
}
