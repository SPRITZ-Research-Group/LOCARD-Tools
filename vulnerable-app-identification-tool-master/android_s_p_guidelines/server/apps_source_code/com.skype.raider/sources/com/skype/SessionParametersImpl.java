package com.skype;

import com.skype.SessionParameters.SessionParametersIListener;
import com.skype.SkyLib.IN_MEMORY_OBJECTTYPE;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class SessionParametersImpl extends InMemoryObjectImpl implements NativeListenable, SessionParameters {
    private final Set<SessionParametersIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroySessionParameters(this.nativeObject);
        }
    }

    private native void setBroadcastContext(byte[] bArr);

    private native void setCallKey(byte[] bArr);

    private native void setConversationType(byte[] bArr);

    private native void setDebugInfo(byte[] bArr);

    private native void setEmergencyContent(byte[] bArr);

    private native void setEncryptedKey(byte[] bArr);

    private native void setEndpointMetadata(byte[] bArr);

    private native void setMeetingInfo(byte[] bArr);

    private native void setMessageId(byte[] bArr);

    private native void setNegotiationTag(byte[] bArr);

    private native void setOnBehalfOf(byte[] bArr);

    private native void setParticipantLegId(byte[] bArr);

    private native void setRoutingFlags(byte[][] bArr);

    private native void setScenario(byte[] bArr);

    private native void setSubject(byte[] bArr);

    private native void setThreadId(byte[] bArr);

    public native IN_MEMORY_OBJECTTYPE getInMemObjectType();

    public native int getObjectID();

    public native void initializeListener();

    public native void setAllowHostless(boolean z);

    public native void setConnectionType(int i);

    public native void setEnableGroupCallMeetupGeneration(boolean z);

    public native void setEnableLightWeightMeeting(boolean z);

    public native void setInvitationType(int i);

    public native void setIsGoLive(boolean z);

    public native void setIsVideoEnabled(boolean z);

    public native void setMaxVideoChannels(int i);

    public native void setMediaPeerType(int i);

    public native void setModalityDirection(int i, int i2);

    public native void setMuteFlags(int i);

    public SessionParametersImpl() {
        this(SkypeFactory.getInstance());
    }

    public SessionParametersImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createSessionParameters());
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public void addListener(SessionParametersIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(SessionParametersIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void setBroadcastContext(String broadcastContext) {
        setBroadcastContext(NativeStringConvert.ConvertToNativeBytes(broadcastContext));
    }

    public void setCallKey(String callKey) {
        setCallKey(NativeStringConvert.ConvertToNativeBytes(callKey));
    }

    public void setConversationType(String conversationType) {
        setConversationType(NativeStringConvert.ConvertToNativeBytes(conversationType));
    }

    public void setDebugInfo(String debugInfo) {
        setDebugInfo(NativeStringConvert.ConvertToNativeBytes(debugInfo));
    }

    public void setEmergencyContent(String emergencyContent) {
        setEmergencyContent(NativeStringConvert.ConvertToNativeBytes(emergencyContent));
    }

    public void setEncryptedKey(String encryptedKey) {
        setEncryptedKey(NativeStringConvert.ConvertToNativeBytes(encryptedKey));
    }

    public void setEndpointMetadata(String endpointMetadata) {
        setEndpointMetadata(NativeStringConvert.ConvertToNativeBytes(endpointMetadata));
    }

    public void setMeetingInfo(String meetingInfo) {
        setMeetingInfo(NativeStringConvert.ConvertToNativeBytes(meetingInfo));
    }

    public void setMessageId(String messageId) {
        setMessageId(NativeStringConvert.ConvertToNativeBytes(messageId));
    }

    public void setNegotiationTag(String negotiationTag) {
        setNegotiationTag(NativeStringConvert.ConvertToNativeBytes(negotiationTag));
    }

    public void setOnBehalfOf(String onBehalfOf) {
        setOnBehalfOf(NativeStringConvert.ConvertToNativeBytes(onBehalfOf));
    }

    public void setParticipantLegId(String participantLegId) {
        setParticipantLegId(NativeStringConvert.ConvertToNativeBytes(participantLegId));
    }

    public void setRoutingFlags(String[] routingFlags) {
        setRoutingFlags(NativeStringConvert.ConvertArrToNativeByteArr(routingFlags));
    }

    public void setScenario(String scenario) {
        setScenario(NativeStringConvert.ConvertToNativeBytes(scenario));
    }

    public void setSubject(String subject) {
        setSubject(NativeStringConvert.ConvertToNativeBytes(subject));
    }

    public void setThreadId(String threadId) {
        setThreadId(NativeStringConvert.ConvertToNativeBytes(threadId));
    }
}
