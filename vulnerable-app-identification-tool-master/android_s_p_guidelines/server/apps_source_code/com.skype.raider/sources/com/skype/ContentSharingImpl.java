package com.skype;

import com.skype.ContentSharing.ContentSharingIListener;
import com.skype.ContentSharing.FAILUREREASON;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class ContentSharingImpl extends ObjectInterfaceImpl implements ContentSharing, NativeListenable, ObjectInterface {
    private final Set<ContentSharingIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyContentSharing(this.nativeObject);
        }
    }

    private native boolean updateContentSharingSessionState(byte[] bArr, byte[] bArr2);

    public native void initializeListener();

    public native boolean startContentSharing();

    public native void stopContentSharing();

    public native boolean takeContentSharingControl();

    public native boolean updateContentSharingParticipantState();

    public ContentSharingImpl() {
        this(SkypeFactory.getInstance());
    }

    public ContentSharingImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createContentSharing());
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public int getCallIdProp() {
        return getIntProperty(PROPKEY.CONTENTSHARING_CALL_ID);
    }

    public String getIdentityProp() {
        return getStrProperty(PROPKEY.CONTENTSHARING_IDENTITY);
    }

    public int getStatusProp() {
        return getIntProperty(PROPKEY.CONTENTSHARING_STATUS);
    }

    public String getSharingIdProp() {
        return getStrProperty(PROPKEY.CONTENTSHARING_SHARING_ID);
    }

    public String getStateProp() {
        return getStrProperty(PROPKEY.CONTENTSHARING_STATE);
    }

    public int getFailureReasonProp() {
        return getIntProperty(PROPKEY.CONTENTSHARING_FAILUREREASON);
    }

    public int getFailurecodeProp() {
        return getIntProperty(PROPKEY.CONTENTSHARING_FAILURECODE);
    }

    public int getFailuresubcodeProp() {
        return getIntProperty(PROPKEY.CONTENTSHARING_FAILURESUBCODE);
    }

    public void addListener(ContentSharingIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(ContentSharingIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onJoinContentSharingResult(FAILUREREASON failureReason, int code, int subCode) {
        synchronized (this.m_listeners) {
            for (ContentSharingIListener onJoinContentSharingResult : this.m_listeners) {
                onJoinContentSharingResult.onJoinContentSharingResult(this, failureReason, code, subCode);
            }
        }
    }

    public void onTakeContentSharingControlResult(FAILUREREASON failureReason, int code, int subCode) {
        synchronized (this.m_listeners) {
            for (ContentSharingIListener onTakeContentSharingControlResult : this.m_listeners) {
                onTakeContentSharingControlResult.onTakeContentSharingControlResult(this, failureReason, code, subCode);
            }
        }
    }

    public void onUpdateContentSharingParticipantStateResult(FAILUREREASON failureReason, int code, int subCode) {
        synchronized (this.m_listeners) {
            for (ContentSharingIListener onUpdateContentSharingParticipantStateResult : this.m_listeners) {
                onUpdateContentSharingParticipantStateResult.onUpdateContentSharingParticipantStateResult(this, failureReason, code, subCode);
            }
        }
    }

    public void onUpdateContentSharingSessionStateResult(byte[] id, FAILUREREASON failureReason, int code, int subCode) {
        synchronized (this.m_listeners) {
            for (ContentSharingIListener onUpdateContentSharingSessionStateResult : this.m_listeners) {
                onUpdateContentSharingSessionStateResult.onUpdateContentSharingSessionStateResult(this, NativeStringConvert.ConvertFromNativeBytes(id), failureReason, code, subCode);
            }
        }
    }

    public boolean updateContentSharingSessionState(String id, String sessionState) {
        return updateContentSharingSessionState(NativeStringConvert.ConvertToNativeBytes(id), NativeStringConvert.ConvertToNativeBytes(sessionState));
    }
}
