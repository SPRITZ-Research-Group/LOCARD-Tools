package com.microsoft.applications.telemetry.core;

import java.util.List;
import java.util.Map;

class HttpSenderResponse {
    Map<String, List<String>> ResponseHeaders;
    int StatusCode;

    HttpSenderResponse(int statusCode, Map<String, List<String>> responseHeaders) {
        this.StatusCode = statusCode;
        this.ResponseHeaders = responseHeaders;
    }
}
