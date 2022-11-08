package com.microsoft.applications.telemetry;

public enum PiiKind {
    NONE(0),
    DISTINGUISHED_NAME(1),
    GENERIC_DATA(2),
    IPV4_ADDRESS(3),
    IPV6_ADDRESS(4),
    MAIL_SUBJECT(5),
    PHONE_NUMBER(6),
    QUERY_STRING(7),
    SIP_ADDRESS(8),
    SMTP_ADDRESS(9),
    IDENTITY(10),
    URI(11),
    FQDN(12),
    IPV4_ADDRESS_LEGACY(13);
    
    private final int val;

    private PiiKind(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }

    public static PiiKind fromValue(int value) {
        switch (value) {
            case 0:
                return NONE;
            case 1:
                return DISTINGUISHED_NAME;
            case 2:
                return GENERIC_DATA;
            case 3:
                return IPV4_ADDRESS;
            case 4:
                return IPV6_ADDRESS;
            case 5:
                return MAIL_SUBJECT;
            case 6:
                return PHONE_NUMBER;
            case 7:
                return QUERY_STRING;
            case 8:
                return SIP_ADDRESS;
            case 9:
                return SMTP_ADDRESS;
            case 10:
                return IDENTITY;
            case 11:
                return URI;
            case 12:
                return FQDN;
            case 13:
                return IPV4_ADDRESS_LEGACY;
            default:
                throw new IllegalArgumentException("No such PiiKind value: " + value);
        }
    }
}
