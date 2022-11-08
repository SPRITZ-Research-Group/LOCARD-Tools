package com.skype.android.gen;

import com.skype.ContentSharing;
import com.skype.ContentSharing.ContentSharingIListener;
import com.skype.ContentSharing.FAILUREREASON;
import com.skype.ObjectInterface;
import com.skype.ObjectInterface.ObjectInterfaceIListener;
import com.skype.PROPKEY;
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;

public class ContentSharingListener implements ContentSharingIListener, ObjectInterfaceIListener {
    final EventBus eventBus = EventBusInstance.a();

    public static class OnJoinContentSharingResult {
        private int _code;
        private FAILUREREASON _failureReason;
        private ContentSharing _sender;
        private int _subCode;

        public OnJoinContentSharingResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
            this._sender = sender;
            this._failureReason = failureReason;
            this._code = code;
            this._subCode = subCode;
        }

        public ContentSharing getSender() {
            return this._sender;
        }

        public FAILUREREASON getFailureReason() {
            return this._failureReason;
        }

        public int getCode() {
            return this._code;
        }

        public int getSubCode() {
            return this._subCode;
        }
    }

    public static class OnPropertyChange {
        private PROPKEY _propKey;
        private ObjectInterface _sender;

        public OnPropertyChange(ObjectInterface sender, PROPKEY propKey) {
            this._sender = sender;
            this._propKey = propKey;
        }

        public ObjectInterface getSender() {
            return this._sender;
        }

        public PROPKEY getPropKey() {
            return this._propKey;
        }
    }

    public static class OnTakeContentSharingControlResult {
        private int _code;
        private FAILUREREASON _failureReason;
        private ContentSharing _sender;
        private int _subCode;

        public OnTakeContentSharingControlResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
            this._sender = sender;
            this._failureReason = failureReason;
            this._code = code;
            this._subCode = subCode;
        }

        public ContentSharing getSender() {
            return this._sender;
        }

        public FAILUREREASON getFailureReason() {
            return this._failureReason;
        }

        public int getCode() {
            return this._code;
        }

        public int getSubCode() {
            return this._subCode;
        }
    }

    public static class OnUpdateContentSharingParticipantStateResult {
        private int _code;
        private FAILUREREASON _failureReason;
        private ContentSharing _sender;
        private int _subCode;

        public OnUpdateContentSharingParticipantStateResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
            this._sender = sender;
            this._failureReason = failureReason;
            this._code = code;
            this._subCode = subCode;
        }

        public ContentSharing getSender() {
            return this._sender;
        }

        public FAILUREREASON getFailureReason() {
            return this._failureReason;
        }

        public int getCode() {
            return this._code;
        }

        public int getSubCode() {
            return this._subCode;
        }
    }

    public static class OnUpdateContentSharingSessionStateResult {
        private int _code;
        private FAILUREREASON _failureReason;
        private String _id;
        private ContentSharing _sender;
        private int _subCode;

        public OnUpdateContentSharingSessionStateResult(ContentSharing sender, String id, FAILUREREASON failureReason, int code, int subCode) {
            this._sender = sender;
            this._id = id;
            this._failureReason = failureReason;
            this._code = code;
            this._subCode = subCode;
        }

        public ContentSharing getSender() {
            return this._sender;
        }

        public String getId() {
            return this._id;
        }

        public FAILUREREASON getFailureReason() {
            return this._failureReason;
        }

        public int getCode() {
            return this._code;
        }

        public int getSubCode() {
            return this._subCode;
        }
    }

    public void onJoinContentSharingResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
        try {
            this.eventBus.sendEvent(new OnJoinContentSharingResult(sender, failureReason, code, subCode));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onTakeContentSharingControlResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
        try {
            this.eventBus.sendEvent(new OnTakeContentSharingControlResult(sender, failureReason, code, subCode));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onUpdateContentSharingParticipantStateResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
        try {
            this.eventBus.sendEvent(new OnUpdateContentSharingParticipantStateResult(sender, failureReason, code, subCode));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onUpdateContentSharingSessionStateResult(ContentSharing sender, String id, FAILUREREASON failureReason, int code, int subCode) {
        try {
            this.eventBus.sendEvent(new OnUpdateContentSharingSessionStateResult(sender, id, failureReason, code, subCode));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onPropertyChange(ObjectInterface sender, PROPKEY propKey) {
        try {
            this.eventBus.sendEvent(new OnPropertyChange(sender, propKey));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(propKey, t));
        }
    }
}
