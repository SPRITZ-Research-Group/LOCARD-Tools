package com.skype;

import com.skype.Term.CONDITION;

public class SkypeFactory implements ObjectInterfaceFactory {
    private static volatile SkypeFactory instance;

    private native int createExampleInMemoryObjectString(byte[] bArr);

    private native int createExampleInMemoryObjectString(byte[] bArr, int[] iArr);

    private native int createMetatagString(int i, byte[] bArr);

    private native int createSkyLibString(byte[] bArr, boolean z, boolean z2);

    private native int createSkyLibString(byte[] bArr, byte[] bArr2, boolean z, boolean z2);

    private native int createTermString(int i, CONDITION condition, byte[] bArr);

    public native int createAccount();

    public native int createAddParticipantParameters();

    public native int createCall();

    public native int createCallHandler();

    public native int createContentSharing();

    public native int createDataChannel();

    public native int createExampleInMemoryObject();

    public native int createInMemoryObject();

    public native int createMetatag();

    public native int createMetatag(int i, long j);

    public native int createMetatag(int i, byte[] bArr);

    public native int createObjectInterface();

    public native int createSessionParameters();

    public native int createTerm();

    public native int createTerm(int i, CONDITION condition, long j);

    public native int createTerm(int i, CONDITION condition, byte[] bArr);

    public native int createVideo();

    public native int createmsrtc();

    public native void destroyAccount(int i);

    public native void destroyAddParticipantParameters(int i);

    public native void destroyCall(int i);

    public native void destroyCallHandler(int i);

    public native void destroyContentSharing(int i);

    public native void destroyDataChannel(int i);

    public native void destroyExampleInMemoryObject(int i);

    public native void destroyGI(int i);

    public native void destroyInMemoryObject(int i);

    public native void destroyListener(int i);

    public native void destroyMetatag(int i);

    public native void destroyObjectInterface(int i);

    public native void destroySessionParameters(int i);

    public native void destroySetup(int i);

    public native void destroySkyLib(int i);

    public native void destroyTerm(int i);

    public native void destroyVideo(int i);

    public native void destroymsrtc(int i);

    public static SkypeFactory getInstance() {
        if (instance == null) {
            synchronized (SkypeFactory.class) {
                instance = new SkypeFactory();
            }
        }
        return instance;
    }

    public int createMetatag(int key, String value) {
        return createMetatagString(key, NativeStringConvert.ConvertToNativeBytes(value));
    }

    public int createExampleInMemoryObject(String name) {
        return createExampleInMemoryObjectString(NativeStringConvert.ConvertToNativeBytes(name));
    }

    public int createExampleInMemoryObject(String name, int[] children) {
        return createExampleInMemoryObjectString(NativeStringConvert.ConvertToNativeBytes(name), children);
    }

    public int createSkyLib(String uiVersionString, boolean isMemOnlyMode, boolean useEventPolling) {
        return createSkyLibString(NativeStringConvert.ConvertToNativeBytes(uiVersionString), isMemOnlyMode, useEventPolling);
    }

    public int createSkyLib(String uiVersionString, String dbPath, boolean isRemovableDbPath, boolean useEventPolling) {
        return createSkyLibString(NativeStringConvert.ConvertToNativeBytes(uiVersionString), NativeStringConvert.ConvertToNativeBytes(dbPath), isRemovableDbPath, useEventPolling);
    }

    public int createTerm(int key, CONDITION cond, String value) {
        return createTermString(key, cond, NativeStringConvert.ConvertToNativeBytes(value));
    }

    public void initializeListener(NativeListenable nativeListenable) {
        nativeListenable.initializeListener();
    }
}
