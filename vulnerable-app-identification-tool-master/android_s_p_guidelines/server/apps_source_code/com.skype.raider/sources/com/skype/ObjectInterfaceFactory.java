package com.skype;

import com.skype.Term.CONDITION;

public interface ObjectInterfaceFactory {
    int createAccount();

    int createAddParticipantParameters();

    int createCall();

    int createCallHandler();

    int createContentSharing();

    int createDataChannel();

    int createExampleInMemoryObject();

    int createExampleInMemoryObject(String str);

    int createExampleInMemoryObject(String str, int[] iArr);

    int createInMemoryObject();

    int createMetatag();

    int createMetatag(int i, long j);

    int createMetatag(int i, String str);

    int createMetatag(int i, byte[] bArr);

    int createObjectInterface();

    int createSessionParameters();

    int createSkyLib(String str, String str2, boolean z, boolean z2);

    int createSkyLib(String str, boolean z, boolean z2);

    int createTerm();

    int createTerm(int i, CONDITION condition, long j);

    int createTerm(int i, CONDITION condition, String str);

    int createTerm(int i, CONDITION condition, byte[] bArr);

    int createVideo();

    int createmsrtc();

    void destroyAccount(int i);

    void destroyAddParticipantParameters(int i);

    void destroyCall(int i);

    void destroyCallHandler(int i);

    void destroyContentSharing(int i);

    void destroyDataChannel(int i);

    void destroyExampleInMemoryObject(int i);

    void destroyGI(int i);

    void destroyInMemoryObject(int i);

    void destroyListener(int i);

    void destroyMetatag(int i);

    void destroyObjectInterface(int i);

    void destroySessionParameters(int i);

    void destroySetup(int i);

    void destroySkyLib(int i);

    void destroyTerm(int i);

    void destroyVideo(int i);

    void destroymsrtc(int i);

    void initializeListener(NativeListenable nativeListenable);
}
