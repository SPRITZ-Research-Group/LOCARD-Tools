package com.microsoft.applications.telemetry.core;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

interface IHttpSender {
    HttpSenderResponse sendToCollector(DataPackageCollection dataPackageCollection, boolean z) throws IOException, InvalidKeyException, NoSuchAlgorithmException;
}
