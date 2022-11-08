package com.skype;

import com.skype.SkyLib.DecryptPayload_Result;
import com.skype.SkyLib.ECS_CALLBACK_EVENT_TYPE;
import com.skype.SkyLib.FindObjects_Result;
import com.skype.SkyLib.GetAudioDeviceCapabilities_Result;
import com.skype.SkyLib.GetAvailableCompositeAudioDevices_Result;
import com.skype.SkyLib.GetAvailableOutputDevices_Result;
import com.skype.SkyLib.GetAvailableRecordingDevices_Result;
import com.skype.SkyLib.GetAvailableVideoDevices_Result;
import com.skype.SkyLib.GetCoexistenceCredentials_Result;
import com.skype.SkyLib.GetDefaultAccountOldCredentials_Result;
import com.skype.SkyLib.GetVideoDeviceVendorProductIds_Result;
import com.skype.SkyLib.INTENT;
import com.skype.SkyLib.IsMicrophoneMuted_Result;
import com.skype.SkyLib.IsSpeakerMuted_Result;
import com.skype.SkyLib.MEDIASTATUS;
import com.skype.SkyLib.OBJECTTYPE;
import com.skype.SkyLib.PNM_REGISTER_CONTEXTS_RESULT;
import com.skype.SkyLib.PUSHHANDLINGRESULT;
import com.skype.SkyLib.QUALITY_MEDIATYPE;
import com.skype.SkyLib.SERVICE_TYPE;
import com.skype.SkyLib.SkyLibIListener;
import com.skype.SkyLib.TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE;
import com.skype.SkyLib.VIDEO_DEVICE_FACING;
import com.skype.SkyLib.VIDEO_DEVICE_TYPE;
import com.skype.Video.MEDIATYPE;
import com.skype.Video.VIDEO_DEVICE_CAPABILITY;
import com.skype.msrtc.QualityEventType;
import com.skype.msrtc.QualityLevel;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class SkyLibImpl extends GIImpl implements NativeListenable, SkyLib {
    private final Set<SkyLibIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroySkyLib(this.nativeObject);
        }
    }

    private native int createLocalVideo(MEDIATYPE mediatype, byte[] bArr, byte[] bArr2);

    private native int createPreviewVideo(MEDIATYPE mediatype, byte[] bArr, byte[] bArr2);

    private native void ecsAddQueryParameter(byte[] bArr, byte[] bArr2);

    private native byte[] ecsGetETagNativeString();

    private native boolean ecsGetSettingsAsBool(byte[] bArr, byte[] bArr2, boolean z);

    private native int ecsGetSettingsAsInt(byte[] bArr, byte[] bArr2, int i);

    private native byte[] ecsGetSettingsAsStringNativeString(byte[] bArr, byte[] bArr2, byte[] bArr3);

    private native void ecsRemoveQueryParameter(byte[] bArr);

    private native void fireIntent(INTENT intent, byte[] bArr, int i);

    private native boolean getAccount(byte[] bArr, Account account);

    private native GetAudioDeviceCapabilities_Result getAudioDeviceCapabilities(byte[] bArr);

    private native byte[] getBuildNameNativeString();

    private native byte[] getBuildVersionNativeString();

    private native byte[] getDefaultDBPathNativeString(boolean z);

    private native byte[] getEcsQueryParametersNativeString();

    private native int getNrgLevelsForDeviceTuner(byte[] bArr);

    private native byte[] getRegistrationIdNativeString(byte[] bArr);

    private native VIDEO_DEVICE_FACING getVideoDeviceFacing(byte[] bArr, byte[] bArr2);

    private native VIDEO_DEVICE_TYPE getVideoDeviceType(byte[] bArr, byte[] bArr2);

    private native GetVideoDeviceVendorProductIds_Result getVideoDeviceVendorProductIds(byte[] bArr, byte[] bArr2);

    private native void handleLoggingEvent(byte[] bArr, byte[] bArr2);

    private native int handlePushNotification(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i2, byte[] bArr6);

    private native boolean hasVideoDeviceCapability(byte[] bArr, byte[] bArr2, VIDEO_DEVICE_CAPABILITY video_device_capability);

    private native int insertRegistrationTransports(SERVICE_TYPE[] service_typeArr, byte[][] bArr, byte[][] bArr2, int[] iArr, byte[] bArr3, byte[] bArr4);

    private native int registerContexts(SERVICE_TYPE service_type, byte[] bArr, byte[] bArr2, byte[][] bArr3, byte[][] bArr4, int[] iArr);

    private native int registerContextsEx(SERVICE_TYPE[] service_typeArr, byte[] bArr, byte[] bArr2, byte[][] bArr3, byte[][] bArr4, int[] iArr, byte[] bArr5, byte[] bArr6);

    private native int registerContextsEx2(SERVICE_TYPE[] service_typeArr, byte[] bArr, byte[] bArr2, byte[][] bArr3, byte[][] bArr4, int[] iArr, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8);

    private native int reregisterContextsEx2(byte[] bArr, byte[] bArr2);

    private native void selectSoundDevices(byte[] bArr, byte[] bArr2, byte[] bArr3);

    private native void setAndroidId(byte[] bArr);

    private native int setClientDescription(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6);

    private native void setEcsConfig(byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z);

    private native void setIMEI(byte[] bArr);

    private native void setMediaConfig(byte[] bArr);

    private native boolean setRegistrationId(byte[] bArr);

    private native boolean trouterCheckConnection(byte[] bArr);

    private native boolean trouterReceiveData(byte[] bArr);

    private native int unregisterAllContexts(byte[] bArr, byte[] bArr2);

    private native int unregisterContexts(byte[][] bArr);

    private native int unregisterContextsEx2(byte[][] bArr, byte[] bArr2, byte[] bArr3);

    public native void changeOperationMode(int i);

    public native boolean createExampleObject(ExampleInMemoryObject exampleInMemoryObject);

    public native long createHrtfAudioProcessor(int i);

    public native DecryptPayload_Result decryptPayload(byte[] bArr);

    public native boolean deleteHrtfAudioProcessor(long j);

    public native void enableAGC(boolean z);

    public native boolean enableHrtfAudioProcessor(long j, boolean z);

    public native void enableMediaQoS(boolean z);

    public native void enableTtySupport(boolean z);

    public native FindObjects_Result findObjects(OBJECTTYPE objecttype);

    public native boolean getAccount(int i, Account account);

    public native long getAriaLogManager();

    public native GetAvailableCompositeAudioDevices_Result getAvailableCompositeAudioDevices();

    public native GetAvailableOutputDevices_Result getAvailableOutputDevices();

    public native GetAvailableRecordingDevices_Result getAvailableRecordingDevices();

    public native GetAvailableVideoDevices_Result getAvailableVideoDevices();

    public native boolean getCall(int i, Call call);

    public native boolean getCallHandler(int i, int i2, CallHandler callHandler);

    public native boolean getCallHandler(int i, CallHandler callHandler);

    public native GetCoexistenceCredentials_Result getCoexistenceCredentials();

    public native boolean getContentSharing(int i, ContentSharing contentSharing);

    public native boolean getDataChannel(int i, DataChannel dataChannel);

    public native GetDefaultAccountOldCredentials_Result getDefaultAccountOldCredentials();

    public native boolean getExampleObject(int i, ExampleInMemoryObject exampleInMemoryObject);

    public native long getMediaExtension(int i);

    public native MEDIASTATUS getMediaStatus();

    public native int getMicVolume();

    public native boolean getObjectInterface(int i, ObjectInterface objectInterface);

    public native OBJECTTYPE getObjectType(int i);

    public native int getSpeakerSystemVolume();

    public native int getSpeakerVolume();

    public native Object getTrouterInstance();

    public native boolean getVideo(int i, Video video);

    public native void initializeListener();

    public native IsMicrophoneMuted_Result isMicrophoneMuted();

    public native IsSpeakerMuted_Result isSpeakerMuted();

    public native boolean muteMicrophone(boolean z);

    public native boolean muteSpeakers(boolean z);

    public native int reregisterContexts();

    public native void setDeviceOrientation(int i);

    public native void setMediaPortRanges(int i, int i2, int i3, int i4, int i5, int i6);

    public native void setMicVolume(int i);

    public native void setOpenCameraInMaxResolution(boolean z);

    public native void setSpeakerSystemVolume(int i);

    public native void setSpeakerVolume(int i);

    public native boolean start(boolean z);

    public native void stop(boolean z);

    public native boolean trouterConnect();

    public native boolean trouterSuspend();

    public native void unmuteMicrophone();

    public native void unmuteSpeaker();

    public native boolean updateHrtf3DSourcePosition(long j, int i, int i2, int i3, int i4);

    public SkyLibImpl(String uiVersionString, boolean isMemOnlyMode, boolean useEventPolling) {
        this(SkypeFactory.getInstance(), uiVersionString, isMemOnlyMode, useEventPolling);
    }

    public SkyLibImpl(ObjectInterfaceFactory factory, String uiVersionString, boolean isMemOnlyMode, boolean useEventPolling) {
        super(factory, factory.createSkyLib(uiVersionString, isMemOnlyMode, useEventPolling));
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public SkyLibImpl(String uiVersionString) {
        this(uiVersionString, false, false);
    }

    public SkyLibImpl(String uiVersionString, boolean isMemOnlyMode) {
        this(uiVersionString, isMemOnlyMode, false);
    }

    public SkyLibImpl(String uiVersionString, String dbPath, boolean isRemovableDbPath, boolean useEventPolling) {
        this(SkypeFactory.getInstance(), uiVersionString, dbPath, isRemovableDbPath, useEventPolling);
    }

    public SkyLibImpl(ObjectInterfaceFactory factory, String uiVersionString, String dbPath, boolean isRemovableDbPath, boolean useEventPolling) {
        super(factory, factory.createSkyLib(uiVersionString, dbPath, isRemovableDbPath, useEventPolling));
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public SkyLibImpl(String uiVersionString, String dbPath, boolean isRemovableDbPath) {
        this(uiVersionString, dbPath, isRemovableDbPath, false);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public void addListener(SkyLibIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(SkyLibIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onAvailableDeviceListChange() {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onAvailableDeviceListChange : this.m_listeners) {
                onAvailableDeviceListChange.onAvailableDeviceListChange(this);
            }
        }
    }

    public void onAvailableVideoDeviceListChange() {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onAvailableVideoDeviceListChange : this.m_listeners) {
                onAvailableVideoDeviceListChange.onAvailableVideoDeviceListChange(this);
            }
        }
    }

    public void onE911InfoChanged(byte[] json) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onE911InfoChanged : this.m_listeners) {
                onE911InfoChanged.onE911InfoChanged(this, NativeStringConvert.ConvertFromNativeBytes(json));
            }
        }
    }

    public void onEcsEvent(ECS_CALLBACK_EVENT_TYPE eventType) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onEcsEvent : this.m_listeners) {
                onEcsEvent.onEcsEvent(this, eventType);
            }
        }
    }

    public void onLoggingEvent(byte[] message, byte[] auxiliaryPayload) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onLoggingEvent : this.m_listeners) {
                onLoggingEvent.onLoggingEvent(this, NativeStringConvert.ConvertFromNativeBytes(message), NativeStringConvert.ConvertFromNativeBytes(auxiliaryPayload));
            }
        }
    }

    public void onMediaStatusChanged(MEDIASTATUS newStatus) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onMediaStatusChanged : this.m_listeners) {
                onMediaStatusChanged.onMediaStatusChanged(this, newStatus);
            }
        }
    }

    public void onObjectDelete(OBJECTTYPE objectType, int objectID) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onObjectDelete : this.m_listeners) {
                onObjectDelete.onObjectDelete(this, objectType, objectID);
            }
        }
    }

    public void onObjectPropertyChangeWithValue(int objectID, PROPKEY propKey, Metatag property) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onObjectPropertyChangeWithValue : this.m_listeners) {
                onObjectPropertyChangeWithValue.onObjectPropertyChangeWithValue(this, objectID, propKey, property);
            }
        }
    }

    public void onOperationModeChanged(int level) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onOperationModeChanged : this.m_listeners) {
                onOperationModeChanged.onOperationModeChanged(this, level);
            }
        }
    }

    public void onProxiedPushNotification(int eventId, byte[] payload) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onProxiedPushNotification : this.m_listeners) {
                onProxiedPushNotification.onProxiedPushNotification(this, eventId, NativeStringConvert.ConvertFromNativeBytes(payload));
            }
        }
    }

    public void onPushHandlingComplete(int token, PUSHHANDLINGRESULT result) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onPushHandlingComplete : this.m_listeners) {
                onPushHandlingComplete.onPushHandlingComplete(this, token, result);
            }
        }
    }

    public void onQualityChanged(int objectId, QualityEventType type, QualityLevel level, QUALITY_MEDIATYPE mediaType) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onQualityChanged : this.m_listeners) {
                onQualityChanged.onQualityChanged(this, objectId, type, level, mediaType);
            }
        }
    }

    public void onRegisterContextsComplete(PNM_REGISTER_CONTEXTS_RESULT success, int requestId) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onRegisterContextsComplete : this.m_listeners) {
                onRegisterContextsComplete.onRegisterContextsComplete(this, success, requestId);
            }
        }
    }

    public void onSkypeTokenRequired(byte[] invalidToken) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onSkypeTokenRequired : this.m_listeners) {
                onSkypeTokenRequired.onSkypeTokenRequired(this, NativeStringConvert.ConvertFromNativeBytes(invalidToken));
            }
        }
    }

    public void onTrouterCheckConnectionComplete(boolean isConnected) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onTrouterCheckConnectionComplete : this.m_listeners) {
                onTrouterCheckConnectionComplete.onTrouterCheckConnectionComplete(this, isConnected);
            }
        }
    }

    public void onTrouterConnectionStateChanged(TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE eventType) {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onTrouterConnectionStateChanged : this.m_listeners) {
                onTrouterConnectionStateChanged.onTrouterConnectionStateChanged(this, eventType);
            }
        }
    }

    public void onTrouterSuspendComplete() {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onTrouterSuspendComplete : this.m_listeners) {
                onTrouterSuspendComplete.onTrouterSuspendComplete(this);
            }
        }
    }

    public void onTrouterSuspendReady() {
        synchronized (this.m_listeners) {
            for (SkyLibIListener onTrouterSuspendReady : this.m_listeners) {
                onTrouterSuspendReady.onTrouterSuspendReady(this);
            }
        }
    }

    public int createLocalVideo(MEDIATYPE type, String name, String path) {
        return createLocalVideo(type, NativeStringConvert.ConvertToNativeBytes(name), NativeStringConvert.ConvertToNativeBytes(path));
    }

    public int createLocalVideo(MEDIATYPE type) {
        return createLocalVideo(type, "", "");
    }

    public int createLocalVideo(MEDIATYPE type, String name) {
        return createLocalVideo(type, name, "");
    }

    public int createPreviewVideo(MEDIATYPE type, String deviceName, String devicePath) {
        return createPreviewVideo(type, NativeStringConvert.ConvertToNativeBytes(deviceName), NativeStringConvert.ConvertToNativeBytes(devicePath));
    }

    public int createPreviewVideo(MEDIATYPE type) {
        return createPreviewVideo(type, "", "");
    }

    public int createPreviewVideo(MEDIATYPE type, String deviceName) {
        return createPreviewVideo(type, deviceName, "");
    }

    public void ecsAddQueryParameter(String key, String value) {
        ecsAddQueryParameter(NativeStringConvert.ConvertToNativeBytes(key), NativeStringConvert.ConvertToNativeBytes(value));
    }

    public String ecsGetETag() {
        return NativeStringConvert.ConvertFromNativeBytes(ecsGetETagNativeString());
    }

    public boolean ecsGetSettingsAsBool(String agent, String path, boolean defaultValue) {
        return ecsGetSettingsAsBool(NativeStringConvert.ConvertToNativeBytes(agent), NativeStringConvert.ConvertToNativeBytes(path), defaultValue);
    }

    public int ecsGetSettingsAsInt(String agent, String path, int defaultValue) {
        return ecsGetSettingsAsInt(NativeStringConvert.ConvertToNativeBytes(agent), NativeStringConvert.ConvertToNativeBytes(path), defaultValue);
    }

    public String ecsGetSettingsAsString(String agent, String path, String defaultValue) {
        return NativeStringConvert.ConvertFromNativeBytes(ecsGetSettingsAsStringNativeString(NativeStringConvert.ConvertToNativeBytes(agent), NativeStringConvert.ConvertToNativeBytes(path), NativeStringConvert.ConvertToNativeBytes(defaultValue)));
    }

    public void ecsRemoveQueryParameter(String key) {
        ecsRemoveQueryParameter(NativeStringConvert.ConvertToNativeBytes(key));
    }

    public void fireIntent(INTENT intent, String arguments, int objectID) {
        fireIntent(intent, NativeStringConvert.ConvertToNativeBytes(arguments), objectID);
    }

    public void fireIntent(INTENT intent) {
        fireIntent(intent, "", 0);
    }

    public void fireIntent(INTENT intent, String arguments) {
        fireIntent(intent, arguments, 0);
    }

    public boolean getAccount(String identity, Account account) {
        return getAccount(NativeStringConvert.ConvertToNativeBytes(identity), account);
    }

    public GetAudioDeviceCapabilities_Result getAudioDeviceCapabilities(String deviceHandle) {
        return getAudioDeviceCapabilities(NativeStringConvert.ConvertToNativeBytes(deviceHandle));
    }

    public String getBuildName() {
        return NativeStringConvert.ConvertFromNativeBytes(getBuildNameNativeString());
    }

    public String getBuildVersion() {
        return NativeStringConvert.ConvertFromNativeBytes(getBuildVersionNativeString());
    }

    public String getDefaultDBPath(boolean createDir) {
        return NativeStringConvert.ConvertFromNativeBytes(getDefaultDBPathNativeString(createDir));
    }

    public String getDefaultDBPath() {
        return getDefaultDBPath(true);
    }

    public String getEcsQueryParameters() {
        return NativeStringConvert.ConvertFromNativeBytes(getEcsQueryParametersNativeString());
    }

    public int getNrgLevelsForDeviceTuner(String microphone) {
        return getNrgLevelsForDeviceTuner(NativeStringConvert.ConvertToNativeBytes(microphone));
    }

    public String getRegistrationId(String accountIdentity) {
        return NativeStringConvert.ConvertFromNativeBytes(getRegistrationIdNativeString(NativeStringConvert.ConvertToNativeBytes(accountIdentity)));
    }

    public String getRegistrationId() {
        return getRegistrationId("");
    }

    public VIDEO_DEVICE_FACING getVideoDeviceFacing(String deviceName, String devicePath) {
        return getVideoDeviceFacing(NativeStringConvert.ConvertToNativeBytes(deviceName), NativeStringConvert.ConvertToNativeBytes(devicePath));
    }

    public VIDEO_DEVICE_TYPE getVideoDeviceType(String deviceName, String devicePath) {
        return getVideoDeviceType(NativeStringConvert.ConvertToNativeBytes(deviceName), NativeStringConvert.ConvertToNativeBytes(devicePath));
    }

    public GetVideoDeviceVendorProductIds_Result getVideoDeviceVendorProductIds(String deviceName, String devicePath) {
        return getVideoDeviceVendorProductIds(NativeStringConvert.ConvertToNativeBytes(deviceName), NativeStringConvert.ConvertToNativeBytes(devicePath));
    }

    public void handleLoggingEvent(String message, String auxiliaryPayload) {
        handleLoggingEvent(NativeStringConvert.ConvertToNativeBytes(message), NativeStringConvert.ConvertToNativeBytes(auxiliaryPayload));
    }

    public int handlePushNotification(int eventType, byte[] nodeSpecificNotificationPayload, byte[] genericNotificationPayload, String servicePayload, String correlationIdsJson, String callKey, int connectionType, String accountIdentity) {
        return handlePushNotification(eventType, nodeSpecificNotificationPayload, genericNotificationPayload, NativeStringConvert.ConvertToNativeBytes(servicePayload), NativeStringConvert.ConvertToNativeBytes(correlationIdsJson), NativeStringConvert.ConvertToNativeBytes(callKey), connectionType, NativeStringConvert.ConvertToNativeBytes(accountIdentity));
    }

    public int handlePushNotification(int eventType, byte[] nodeSpecificNotificationPayload, byte[] genericNotificationPayload) {
        return handlePushNotification(eventType, nodeSpecificNotificationPayload, genericNotificationPayload, "", "", "", 0, "");
    }

    public int handlePushNotification(int eventType, byte[] nodeSpecificNotificationPayload, byte[] genericNotificationPayload, String servicePayload) {
        return handlePushNotification(eventType, nodeSpecificNotificationPayload, genericNotificationPayload, servicePayload, "", "", 0, "");
    }

    public int handlePushNotification(int eventType, byte[] nodeSpecificNotificationPayload, byte[] genericNotificationPayload, String servicePayload, String correlationIdsJson) {
        return handlePushNotification(eventType, nodeSpecificNotificationPayload, genericNotificationPayload, servicePayload, correlationIdsJson, "", 0, "");
    }

    public int handlePushNotification(int eventType, byte[] nodeSpecificNotificationPayload, byte[] genericNotificationPayload, String servicePayload, String correlationIdsJson, String callKey) {
        return handlePushNotification(eventType, nodeSpecificNotificationPayload, genericNotificationPayload, servicePayload, correlationIdsJson, callKey, 0, "");
    }

    public int handlePushNotification(int eventType, byte[] nodeSpecificNotificationPayload, byte[] genericNotificationPayload, String servicePayload, String correlationIdsJson, String callKey, int connectionType) {
        return handlePushNotification(eventType, nodeSpecificNotificationPayload, genericNotificationPayload, servicePayload, correlationIdsJson, callKey, connectionType, "");
    }

    public boolean hasVideoDeviceCapability(String deviceName, String devicePath, VIDEO_DEVICE_CAPABILITY cap) {
        return hasVideoDeviceCapability(NativeStringConvert.ConvertToNativeBytes(deviceName), NativeStringConvert.ConvertToNativeBytes(devicePath), cap);
    }

    public int insertRegistrationTransports(SERVICE_TYPE[] serviceTypes, String[] contexts, String[] registrationTokens, int[] registrationTTLs, String activityId, String reason) {
        return insertRegistrationTransports(serviceTypes, NativeStringConvert.ConvertArrToNativeByteArr(contexts), NativeStringConvert.ConvertArrToNativeByteArr(registrationTokens), registrationTTLs, NativeStringConvert.ConvertToNativeBytes(activityId), NativeStringConvert.ConvertToNativeBytes(reason));
    }

    public int registerContextsEx2(SERVICE_TYPE[] serviceTypes, String platform, String templateKey, String[] contexts, String[] registrationTokens, int[] registrationTTLs, String activityId, String reason, String app, String xuid) {
        return registerContextsEx2(serviceTypes, NativeStringConvert.ConvertToNativeBytes(platform), NativeStringConvert.ConvertToNativeBytes(templateKey), NativeStringConvert.ConvertArrToNativeByteArr(contexts), NativeStringConvert.ConvertArrToNativeByteArr(registrationTokens), registrationTTLs, NativeStringConvert.ConvertToNativeBytes(activityId), NativeStringConvert.ConvertToNativeBytes(reason), NativeStringConvert.ConvertToNativeBytes(app), NativeStringConvert.ConvertToNativeBytes(xuid));
    }

    public int registerContextsEx2(SERVICE_TYPE[] serviceTypes, String platform, String templateKey, String[] contexts, String[] registrationTokens, int[] registrationTTLs, String activityId, String reason, String app) {
        return registerContextsEx2(serviceTypes, platform, templateKey, contexts, registrationTokens, registrationTTLs, activityId, reason, app, "");
    }

    public int registerContextsEx(SERVICE_TYPE[] serviceTypes, String platform, String templateKey, String[] contexts, String[] registrationTokens, int[] registrationTTLs, String app, String xuid) {
        return registerContextsEx(serviceTypes, NativeStringConvert.ConvertToNativeBytes(platform), NativeStringConvert.ConvertToNativeBytes(templateKey), NativeStringConvert.ConvertArrToNativeByteArr(contexts), NativeStringConvert.ConvertArrToNativeByteArr(registrationTokens), registrationTTLs, NativeStringConvert.ConvertToNativeBytes(app), NativeStringConvert.ConvertToNativeBytes(xuid));
    }

    public int registerContextsEx(SERVICE_TYPE[] serviceTypes, String platform, String templateKey, String[] contexts, String[] registrationTokens, int[] registrationTTLs) {
        return registerContextsEx(serviceTypes, platform, templateKey, contexts, registrationTokens, registrationTTLs, "", "");
    }

    public int registerContextsEx(SERVICE_TYPE[] serviceTypes, String platform, String templateKey, String[] contexts, String[] registrationTokens, int[] registrationTTLs, String app) {
        return registerContextsEx(serviceTypes, platform, templateKey, contexts, registrationTokens, registrationTTLs, app, "");
    }

    public int registerContexts(SERVICE_TYPE serviceType, String platform, String templateKey, String[] contexts, String[] registrationTokens, int[] registrationTTLs) {
        return registerContexts(serviceType, NativeStringConvert.ConvertToNativeBytes(platform), NativeStringConvert.ConvertToNativeBytes(templateKey), NativeStringConvert.ConvertArrToNativeByteArr(contexts), NativeStringConvert.ConvertArrToNativeByteArr(registrationTokens), registrationTTLs);
    }

    public int reregisterContextsEx2(String activityId, String reason) {
        return reregisterContextsEx2(NativeStringConvert.ConvertToNativeBytes(activityId), NativeStringConvert.ConvertToNativeBytes(reason));
    }

    public void selectSoundDevices(String callInDevice, String callOutDevice, String waveOutDevice) {
        selectSoundDevices(NativeStringConvert.ConvertToNativeBytes(callInDevice), NativeStringConvert.ConvertToNativeBytes(callOutDevice), NativeStringConvert.ConvertToNativeBytes(waveOutDevice));
    }

    public void setAndroidId(String androidId) {
        setAndroidId(NativeStringConvert.ConvertToNativeBytes(androidId));
    }

    public int setClientDescription(String app, String platform, String templateKey, String activityId, String reason, String xuid) {
        return setClientDescription(NativeStringConvert.ConvertToNativeBytes(app), NativeStringConvert.ConvertToNativeBytes(platform), NativeStringConvert.ConvertToNativeBytes(templateKey), NativeStringConvert.ConvertToNativeBytes(activityId), NativeStringConvert.ConvertToNativeBytes(reason), NativeStringConvert.ConvertToNativeBytes(xuid));
    }

    public int setClientDescription(String app, String platform, String templateKey, String activityId, String reason) {
        return setClientDescription(app, platform, templateKey, activityId, reason, "");
    }

    public void setEcsConfig(String ecsBlob, String userIdentity, String etag, boolean isCached) {
        setEcsConfig(NativeStringConvert.ConvertToNativeBytes(ecsBlob), NativeStringConvert.ConvertToNativeBytes(userIdentity), NativeStringConvert.ConvertToNativeBytes(etag), isCached);
    }

    public void setEcsConfig(String ecsBlob, String userIdentity, String etag) {
        setEcsConfig(ecsBlob, userIdentity, etag, false);
    }

    public void setIMEI(String imei) {
        setIMEI(NativeStringConvert.ConvertToNativeBytes(imei));
    }

    public void setMediaConfig(String configurationJson) {
        setMediaConfig(NativeStringConvert.ConvertToNativeBytes(configurationJson));
    }

    public boolean setRegistrationId(String endpointId) {
        return setRegistrationId(NativeStringConvert.ConvertToNativeBytes(endpointId));
    }

    public boolean start() {
        return start(true);
    }

    public void stop() {
        stop(true);
    }

    public boolean trouterCheckConnection(String socketId) {
        return trouterCheckConnection(NativeStringConvert.ConvertToNativeBytes(socketId));
    }

    public boolean trouterCheckConnection() {
        return trouterCheckConnection("");
    }

    public boolean trouterReceiveData(String socketId) {
        return trouterReceiveData(NativeStringConvert.ConvertToNativeBytes(socketId));
    }

    public boolean trouterReceiveData() {
        return trouterReceiveData("");
    }

    public int unregisterAllContexts(String activityId, String reason) {
        return unregisterAllContexts(NativeStringConvert.ConvertToNativeBytes(activityId), NativeStringConvert.ConvertToNativeBytes(reason));
    }

    public int unregisterContextsEx2(String[] contexts, String activityId, String reason) {
        return unregisterContextsEx2(NativeStringConvert.ConvertArrToNativeByteArr(contexts), NativeStringConvert.ConvertToNativeBytes(activityId), NativeStringConvert.ConvertToNativeBytes(reason));
    }

    public int unregisterContexts(String[] contexts) {
        return unregisterContexts(NativeStringConvert.ConvertArrToNativeByteArr(contexts));
    }
}
