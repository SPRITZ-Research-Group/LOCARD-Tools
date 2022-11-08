package com.skype;

import java.lang.ref.ReferenceQueue;

interface a {
    NativeWeakRef<?> createNativeWeakRef(ObjectInterfaceFactory objectInterfaceFactory, ReferenceQueue<a> referenceQueue);
}
