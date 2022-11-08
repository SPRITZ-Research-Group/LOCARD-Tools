package com.skype;

import com.skype.Video.FAILUREREASON;
import com.skype.Video.GetCurrentVideoDevice_Result;
import com.skype.Video.MEDIATYPE;
import com.skype.Video.STATUS;
import com.skype.Video.VideoIListener;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class VideoImpl extends ObjectInterfaceImpl implements NativeListenable, ObjectInterface, Video {
    private final Set<VideoIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyVideo(this.nativeObject);
        }
    }

    private native void start(byte[] bArr);

    private native void stop(byte[] bArr);

    public native void createBinding(int i, int i2);

    public native GetCurrentVideoDevice_Result getCurrentVideoDevice();

    public native void initializeListener();

    public native void releaseBinding(int i);

    public native void releaseBindingEx(int i, int i2);

    public native void setScreenCaptureRectangle(int i, int i2, int i3, int i4, int i5, int i6);

    public VideoImpl() {
        this(SkypeFactory.getInstance());
    }

    public VideoImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createVideo());
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public STATUS getStatusProp() {
        return STATUS.fromInt(getIntProperty(PROPKEY.VIDEO_STATUS));
    }

    public String getErrorProp() {
        return getStrProperty(PROPKEY.VIDEO_ERROR);
    }

    public String getDebuginfoProp() {
        return getStrProperty(PROPKEY.VIDEO_DEBUGINFO);
    }

    public String getDimensionsProp() {
        return getStrProperty(PROPKEY.VIDEO_DIMENSIONS);
    }

    public MEDIATYPE getMediaTypeProp() {
        return MEDIATYPE.fromInt(getIntProperty(PROPKEY.VIDEO_MEDIA_TYPE));
    }

    public int getDuration1080Prop() {
        return getIntProperty(PROPKEY.VIDEO_DURATION_1080);
    }

    public int getDuration720Prop() {
        return getIntProperty(PROPKEY.VIDEO_DURATION_720);
    }

    public int getDurationHqvProp() {
        return getIntProperty(PROPKEY.VIDEO_DURATION_HQV);
    }

    public int getDurationVgad2Prop() {
        return getIntProperty(PROPKEY.VIDEO_DURATION_VGAD2);
    }

    public int getDurationLtvgad2Prop() {
        return getIntProperty(PROPKEY.VIDEO_DURATION_LTVGAD2);
    }

    public int getTimestampProp() {
        return getIntProperty(PROPKEY.VIDEO_TIMESTAMP);
    }

    public int getHqPresentProp() {
        return getIntProperty(PROPKEY.VIDEO_HQ_PRESENT);
    }

    public int getDurationSsProp() {
        return getIntProperty(PROPKEY.VIDEO_DURATION_SS);
    }

    public int getSsTimestampProp() {
        return getIntProperty(PROPKEY.VIDEO_SS_TIMESTAMP);
    }

    public int getConvoIdProp() {
        return getIntProperty(PROPKEY.VIDEO_CONVO_ID);
    }

    public String getDevicePathProp() {
        return getStrProperty(PROPKEY.VIDEO_DEVICE_PATH);
    }

    public String getDeviceNameProp() {
        return getStrProperty(PROPKEY.VIDEO_DEVICE_NAME);
    }

    public int getParticipantIdProp() {
        return getIntProperty(PROPKEY.VIDEO_PARTICIPANT_ID);
    }

    public int getRankProp() {
        return getIntProperty(PROPKEY.VIDEO_RANK);
    }

    public FAILUREREASON getFailureReasonProp() {
        return FAILUREREASON.fromInt(getIntProperty(PROPKEY.VIDEO_FAILUREREASON));
    }

    public String getEndpointIdProp() {
        return getStrProperty(PROPKEY.VIDEO_ENDPOINT_ID);
    }

    public String getParticipantLegIdProp() {
        return getStrProperty(PROPKEY.VIDEO_PARTICIPANT_LEG_ID);
    }

    public String getParticipantMriProp() {
        return getStrProperty(PROPKEY.VIDEO_PARTICIPANT_MRI);
    }

    public String getNegotiationTagProp() {
        return getStrProperty(PROPKEY.VIDEO_NEGOTIATION_TAG);
    }

    public void addListener(VideoIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(VideoIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void setScreenCaptureRectangle(int x0, int y0, int width, int height) {
        setScreenCaptureRectangle(x0, y0, width, height, 0, 0);
    }

    public void setScreenCaptureRectangle(int x0, int y0, int width, int height, int monitorNumber) {
        setScreenCaptureRectangle(x0, y0, width, height, monitorNumber, 0);
    }

    public void start(String negotiationTag) {
        start(NativeStringConvert.ConvertToNativeBytes(negotiationTag));
    }

    public void start() {
        start("");
    }

    public void stop(String negotiationTag) {
        stop(NativeStringConvert.ConvertToNativeBytes(negotiationTag));
    }

    public void stop() {
        stop("");
    }
}
