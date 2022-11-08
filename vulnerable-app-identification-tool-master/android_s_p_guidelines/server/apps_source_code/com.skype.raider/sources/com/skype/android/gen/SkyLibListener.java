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
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;
import com.skype.msrtc.QualityEventType;
import com.skype.msrtc.QualityLevel;

public class SkyLibListener implements SkyLibIListener {
    final EventBus eventBus = EventBusInstance.a();

    public static class OnAvailableDeviceListChange {
        private SkyLib _sender;

        public OnAvailableDeviceListChange(SkyLib sender) {
            this._sender = sender;
        }

        public SkyLib getSender() {
            return this._sender;
        }
    }

    public static class OnAvailableVideoDeviceListChange {
        private SkyLib _sender;

        public OnAvailableVideoDeviceListChange(SkyLib sender) {
            this._sender = sender;
        }

        public SkyLib getSender() {
            return this._sender;
        }
    }

    public static class OnE911InfoChanged {
        private String _json;
        private SkyLib _sender;

        public OnE911InfoChanged(SkyLib sender, String json) {
            this._sender = sender;
            this._json = json;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public String getJson() {
            return this._json;
        }
    }

    public static class OnEcsEvent {
        private ECS_CALLBACK_EVENT_TYPE _eventType;
        private SkyLib _sender;

        public OnEcsEvent(SkyLib sender, ECS_CALLBACK_EVENT_TYPE eventType) {
            this._sender = sender;
            this._eventType = eventType;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public ECS_CALLBACK_EVENT_TYPE getEventType() {
            return this._eventType;
        }
    }

    public static class OnLoggingEvent {
        private String _auxiliaryPayload;
        private String _message;
        private SkyLib _sender;

        public OnLoggingEvent(SkyLib sender, String message, String auxiliaryPayload) {
            this._sender = sender;
            this._message = message;
            this._auxiliaryPayload = auxiliaryPayload;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public String getMessage() {
            return this._message;
        }

        public String getAuxiliaryPayload() {
            return this._auxiliaryPayload;
        }
    }

    public static class OnMediaStatusChanged {
        private MEDIASTATUS _newStatus;
        private SkyLib _sender;

        public OnMediaStatusChanged(SkyLib sender, MEDIASTATUS newStatus) {
            this._sender = sender;
            this._newStatus = newStatus;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public MEDIASTATUS getNewStatus() {
            return this._newStatus;
        }
    }

    public static class OnObjectDelete {
        private int _objectID;
        private OBJECTTYPE _objectType;
        private SkyLib _sender;

        public OnObjectDelete(SkyLib sender, OBJECTTYPE objectType, int objectID) {
            this._sender = sender;
            this._objectType = objectType;
            this._objectID = objectID;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public OBJECTTYPE getObjectType() {
            return this._objectType;
        }

        public int getObjectID() {
            return this._objectID;
        }
    }

    public static class OnObjectPropertyChangeWithValue {
        private int _objectID;
        private PROPKEY _propKey;
        private Metatag _property;
        private SkyLib _sender;

        public OnObjectPropertyChangeWithValue(SkyLib sender, int objectID, PROPKEY propKey, Metatag property) {
            this._sender = sender;
            this._objectID = objectID;
            this._propKey = propKey;
            this._property = property;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public int getObjectID() {
            return this._objectID;
        }

        public PROPKEY getPropKey() {
            return this._propKey;
        }

        public Metatag getProperty() {
            return this._property;
        }
    }

    public static class OnOperationModeChanged {
        private int _level;
        private SkyLib _sender;

        public OnOperationModeChanged(SkyLib sender, int level) {
            this._sender = sender;
            this._level = level;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public int getLevel() {
            return this._level;
        }
    }

    public static class OnProxiedPushNotification {
        private int _eventId;
        private String _payload;
        private SkyLib _sender;

        public OnProxiedPushNotification(SkyLib sender, int eventId, String payload) {
            this._sender = sender;
            this._eventId = eventId;
            this._payload = payload;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public int getEventId() {
            return this._eventId;
        }

        public String getPayload() {
            return this._payload;
        }
    }

    public static class OnPushHandlingComplete {
        private PUSHHANDLINGRESULT _result;
        private SkyLib _sender;
        private int _token;

        public OnPushHandlingComplete(SkyLib sender, int token, PUSHHANDLINGRESULT result) {
            this._sender = sender;
            this._token = token;
            this._result = result;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public int getToken() {
            return this._token;
        }

        public PUSHHANDLINGRESULT getResult() {
            return this._result;
        }
    }

    public static class OnQualityChanged {
        private QualityLevel _level;
        private QUALITY_MEDIATYPE _mediaType;
        private int _objectId;
        private SkyLib _sender;
        private QualityEventType _type;

        public OnQualityChanged(SkyLib sender, int objectId, QualityEventType type, QualityLevel level, QUALITY_MEDIATYPE mediaType) {
            this._sender = sender;
            this._objectId = objectId;
            this._type = type;
            this._level = level;
            this._mediaType = mediaType;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public int getObjectId() {
            return this._objectId;
        }

        public QualityEventType getType() {
            return this._type;
        }

        public QualityLevel getLevel() {
            return this._level;
        }

        public QUALITY_MEDIATYPE getMediaType() {
            return this._mediaType;
        }
    }

    public static class OnRegisterContextsComplete {
        private int _requestId;
        private SkyLib _sender;
        private PNM_REGISTER_CONTEXTS_RESULT _success;

        public OnRegisterContextsComplete(SkyLib sender, PNM_REGISTER_CONTEXTS_RESULT success, int requestId) {
            this._sender = sender;
            this._success = success;
            this._requestId = requestId;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public PNM_REGISTER_CONTEXTS_RESULT getSuccess() {
            return this._success;
        }

        public int getRequestId() {
            return this._requestId;
        }
    }

    public static class OnSkypeTokenRequired {
        private String _invalidToken;
        private SkyLib _sender;

        public OnSkypeTokenRequired(SkyLib sender, String invalidToken) {
            this._sender = sender;
            this._invalidToken = invalidToken;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public String getInvalidToken() {
            return this._invalidToken;
        }
    }

    public static class OnTrouterCheckConnectionComplete {
        private boolean _isConnected;
        private SkyLib _sender;

        public OnTrouterCheckConnectionComplete(SkyLib sender, boolean isConnected) {
            this._sender = sender;
            this._isConnected = isConnected;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public boolean getIsConnected() {
            return this._isConnected;
        }
    }

    public static class OnTrouterConnectionStateChanged {
        private TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE _eventType;
        private SkyLib _sender;

        public OnTrouterConnectionStateChanged(SkyLib sender, TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE eventType) {
            this._sender = sender;
            this._eventType = eventType;
        }

        public SkyLib getSender() {
            return this._sender;
        }

        public TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE getEventType() {
            return this._eventType;
        }
    }

    public static class OnTrouterSuspendComplete {
        private SkyLib _sender;

        public OnTrouterSuspendComplete(SkyLib sender) {
            this._sender = sender;
        }

        public SkyLib getSender() {
            return this._sender;
        }
    }

    public static class OnTrouterSuspendReady {
        private SkyLib _sender;

        public OnTrouterSuspendReady(SkyLib sender) {
            this._sender = sender;
        }

        public SkyLib getSender() {
            return this._sender;
        }
    }

    public void onAvailableDeviceListChange(SkyLib sender) {
        try {
            this.eventBus.sendEvent(new OnAvailableDeviceListChange(sender));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onAvailableVideoDeviceListChange(SkyLib sender) {
        try {
            this.eventBus.sendEvent(new OnAvailableVideoDeviceListChange(sender));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onE911InfoChanged(SkyLib sender, String json) {
        try {
            this.eventBus.sendEvent(new OnE911InfoChanged(sender, json));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onEcsEvent(SkyLib sender, ECS_CALLBACK_EVENT_TYPE eventType) {
        try {
            this.eventBus.sendEvent(new OnEcsEvent(sender, eventType));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onLoggingEvent(SkyLib sender, String message, String auxiliaryPayload) {
        try {
            this.eventBus.sendEvent(new OnLoggingEvent(sender, message, auxiliaryPayload));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onMediaStatusChanged(SkyLib sender, MEDIASTATUS newStatus) {
        try {
            this.eventBus.sendEvent(new OnMediaStatusChanged(sender, newStatus));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onObjectDelete(SkyLib sender, OBJECTTYPE objectType, int objectID) {
        try {
            this.eventBus.sendEvent(new OnObjectDelete(sender, objectType, objectID));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onObjectPropertyChangeWithValue(SkyLib sender, int objectID, PROPKEY propKey, Metatag property) {
        try {
            this.eventBus.sendEvent(new OnObjectPropertyChangeWithValue(sender, objectID, propKey, property));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(propKey, t));
        }
    }

    public void onOperationModeChanged(SkyLib sender, int level) {
        try {
            this.eventBus.sendEvent(new OnOperationModeChanged(sender, level));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onProxiedPushNotification(SkyLib sender, int eventId, String payload) {
        try {
            this.eventBus.sendEvent(new OnProxiedPushNotification(sender, eventId, payload));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onPushHandlingComplete(SkyLib sender, int token, PUSHHANDLINGRESULT result) {
        try {
            this.eventBus.sendEvent(new OnPushHandlingComplete(sender, token, result));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onQualityChanged(SkyLib sender, int objectId, QualityEventType type, QualityLevel level, QUALITY_MEDIATYPE mediaType) {
        try {
            this.eventBus.sendEvent(new OnQualityChanged(sender, objectId, type, level, mediaType));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onRegisterContextsComplete(SkyLib sender, PNM_REGISTER_CONTEXTS_RESULT success, int requestId) {
        try {
            this.eventBus.sendEvent(new OnRegisterContextsComplete(sender, success, requestId));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onSkypeTokenRequired(SkyLib sender, String invalidToken) {
        try {
            this.eventBus.sendEvent(new OnSkypeTokenRequired(sender, invalidToken));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onTrouterCheckConnectionComplete(SkyLib sender, boolean isConnected) {
        try {
            this.eventBus.sendEvent(new OnTrouterCheckConnectionComplete(sender, isConnected));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onTrouterConnectionStateChanged(SkyLib sender, TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE eventType) {
        try {
            this.eventBus.sendEvent(new OnTrouterConnectionStateChanged(sender, eventType));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onTrouterSuspendComplete(SkyLib sender) {
        try {
            this.eventBus.sendEvent(new OnTrouterSuspendComplete(sender));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onTrouterSuspendReady(SkyLib sender) {
        try {
            this.eventBus.sendEvent(new OnTrouterSuspendReady(sender));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }
}
