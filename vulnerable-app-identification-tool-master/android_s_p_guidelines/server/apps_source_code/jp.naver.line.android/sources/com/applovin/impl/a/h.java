package com.applovin.impl.a;

import org.apache.http.HttpStatus;

public enum h {
    UNSPECIFIED(-1),
    XML_PARSING(100),
    GENERAL_WRAPPER_ERROR(HttpStatus.SC_MULTIPLE_CHOICES),
    TIMED_OUT(HttpStatus.SC_MOVED_PERMANENTLY),
    WRAPPER_LIMIT_REACHED(HttpStatus.SC_MOVED_TEMPORARILY),
    NO_WRAPPER_RESPONSE(HttpStatus.SC_SEE_OTHER),
    GENERAL_LINEAR_ERROR(HttpStatus.SC_BAD_REQUEST),
    NO_MEDIA_FILE_PROVIDED(HttpStatus.SC_UNAUTHORIZED),
    MEDIA_FILE_TIMEOUT(402),
    MEDIA_FILE_ERROR(HttpStatus.SC_METHOD_NOT_ALLOWED),
    GENERAL_COMPANION_AD_ERROR(600),
    UNABLE_TO_FETCH_COMPANION_AD_RESOURCE(603),
    CAN_NOT_FIND_COMPANION_AD_RESOURCE(604);
    
    private final int n;

    private h(int i) {
        this.n = i;
    }

    public final int a() {
        return this.n;
    }
}
