package com.skype.android.gen;

import com.skype.Metatag;
import com.skype.PROPKEY;
import com.skype.SkyLib;
import com.skype.SkyLib.ECS_CALLBACK_EVENT_TYPE;
import com.skype.SkyLib.MEDIASTATUS;
import com.skype.SkyLib.OBJECTTYPE;
import com.skype.SkyLib.PNM_REGISTER_CONTEXTS_RESULT;
import com.skype.SkyLib.PUSHHANDLINGRESULT;
import com.skype.SkyLib.QUALITY_MEDIATYPE;
import com.skype.SkyLib.SkyLibIListener;
import com.skype.SkyLib.TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE;
import com.skype.msrtc.QualityEventType;
import com.skype.msrtc.QualityLevel;

public class SkyLibLogListener implements SkyLibIListener {
    public void onAvailableDeviceListChange(SkyLib sender) {
    }

    public void onAvailableVideoDeviceListChange(SkyLib sender) {
    }

    public void onE911InfoChanged(SkyLib sender, String json) {
    }

    public void onEcsEvent(SkyLib sender, ECS_CALLBACK_EVENT_TYPE eventType) {
    }

    public void onLoggingEvent(SkyLib sender, String message, String auxiliaryPayload) {
    }

    public void onMediaStatusChanged(SkyLib sender, MEDIASTATUS newStatus) {
    }

    public void onObjectDelete(SkyLib sender, OBJECTTYPE objectType, int objectID) {
    }

    public void onObjectPropertyChangeWithValue(SkyLib sender, int objectID, PROPKEY propKey, Metatag property) {
    }

    public void onOperationModeChanged(SkyLib sender, int level) {
    }

    public void onProxiedPushNotification(SkyLib sender, int eventId, String payload) {
    }

    public void onPushHandlingComplete(SkyLib sender, int token, PUSHHANDLINGRESULT result) {
    }

    public void onQualityChanged(SkyLib sender, int objectId, QualityEventType type, QualityLevel level, QUALITY_MEDIATYPE mediaType) {
    }

    public void onRegisterContextsComplete(SkyLib sender, PNM_REGISTER_CONTEXTS_RESULT success, int requestId) {
    }

    public void onSkypeTokenRequired(SkyLib sender, String invalidToken) {
    }

    public void onTrouterCheckConnectionComplete(SkyLib sender, boolean isConnected) {
    }

    public void onTrouterConnectionStateChanged(SkyLib sender, TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE eventType) {
    }

    public void onTrouterSuspendComplete(SkyLib sender) {
    }

    public void onTrouterSuspendReady(SkyLib sender) {
    }
}
