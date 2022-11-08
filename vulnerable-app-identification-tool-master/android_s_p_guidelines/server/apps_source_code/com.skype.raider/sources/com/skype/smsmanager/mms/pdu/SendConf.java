package com.skype.smsmanager.mms.pdu;

import com.skype.smsmanager.mms.InvalidHeaderValueException;

public class SendConf extends GenericPdu {
    public SendConf() throws InvalidHeaderValueException {
        a(129);
    }

    SendConf(PduHeaders headers) {
        super(headers);
    }

    public final int c() {
        return this.a.a(146);
    }
}
