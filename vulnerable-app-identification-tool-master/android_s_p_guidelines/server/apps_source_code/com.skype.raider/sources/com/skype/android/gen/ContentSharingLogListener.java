package com.skype.android.gen;

import com.skype.ContentSharing;
import com.skype.ContentSharing.ContentSharingIListener;
import com.skype.ContentSharing.FAILUREREASON;
import com.skype.ObjectInterface;
import com.skype.ObjectInterface.ObjectInterfaceIListener;
import com.skype.PROPKEY;

public class ContentSharingLogListener implements ContentSharingIListener, ObjectInterfaceIListener {
    public void onJoinContentSharingResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
    }

    public void onTakeContentSharingControlResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
    }

    public void onUpdateContentSharingParticipantStateResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
    }

    public void onUpdateContentSharingSessionStateResult(ContentSharing sender, String id, FAILUREREASON failureReason, int code, int subCode) {
    }

    public void onPropertyChange(ObjectInterface sender, PROPKEY propKey) {
    }
}
