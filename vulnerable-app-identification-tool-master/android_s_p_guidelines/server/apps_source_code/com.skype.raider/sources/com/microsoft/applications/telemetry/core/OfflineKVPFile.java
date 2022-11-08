package com.microsoft.applications.telemetry.core;

import java.io.Serializable;
import java.util.HashMap;

class OfflineKVPFile implements Serializable {
    HashMap<String, byte[]> KVPMap = new HashMap();

    OfflineKVPFile() {
    }
}
