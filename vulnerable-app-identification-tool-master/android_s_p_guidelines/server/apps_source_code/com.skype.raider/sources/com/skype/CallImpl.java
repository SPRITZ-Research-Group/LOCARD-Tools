package com.skype;

import com.skype.Call.CallIListener;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class CallImpl extends ObjectInterfaceImpl implements Call, NativeListenable, ObjectInterface {
    private final Set<CallIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyCall(this.nativeObject);
        }
    }

    public native void initializeListener();

    public CallImpl() {
        this(SkypeFactory.getInstance());
    }

    public CallImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createCall());
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public int getBeginTimestampProp() {
        return getIntProperty(PROPKEY.CALL_BEGIN_TIMESTAMP);
    }

    public String getTopicProp() {
        return getStrProperty(PROPKEY.CALL_TOPIC);
    }

    public boolean getIsMutedProp() {
        return getIntProperty(PROPKEY.CALL_IS_MUTED) != 0;
    }

    public boolean getIsServerMutedProp() {
        return getIntProperty(PROPKEY.CALL_IS_SERVER_MUTED) != 0;
    }

    public boolean getIsMutedSpeakerProp() {
        return getIntProperty(PROPKEY.CALL_IS_MUTED_SPEAKER) != 0;
    }

    public boolean getIsUnseenMissedProp() {
        return getIntProperty(PROPKEY.CALL_IS_UNSEEN_MISSED) != 0;
    }

    public String getHostIdentityProp() {
        return getStrProperty(PROPKEY.CALL_HOST_IDENTITY);
    }

    public boolean getIsHostlessProp() {
        return getIntProperty(PROPKEY.CALL_IS_HOSTLESS) != 0;
    }

    public int getMikeStatusProp() {
        return getIntProperty(PROPKEY.CALL_MIKE_STATUS);
    }

    public int getDurationProp() {
        return getIntProperty(PROPKEY.CALL_DURATION);
    }

    public int getSoundlevelProp() {
        return getIntProperty(PROPKEY.CALL_SOUNDLEVEL);
    }

    public String getAccessTokenProp() {
        return getStrProperty(PROPKEY.CALL_ACCESS_TOKEN);
    }

    public int getActiveMembersProp() {
        return getIntProperty(PROPKEY.CALL_ACTIVE_MEMBERS);
    }

    public boolean getIsActiveProp() {
        return getIntProperty(PROPKEY.CALL_IS_ACTIVE) != 0;
    }

    public String getNameProp() {
        return getStrProperty(PROPKEY.CALL_NAME);
    }

    public boolean getVideoDisabledProp() {
        return getIntProperty(PROPKEY.CALL_VIDEO_DISABLED) != 0;
    }

    public boolean getJoinedExistingProp() {
        return getIntProperty(PROPKEY.CALL_JOINED_EXISTING) != 0;
    }

    public String getServerIdentityProp() {
        return getStrProperty(PROPKEY.CALL_SERVER_IDENTITY);
    }

    public int getVaaInputStatusProp() {
        return getIntProperty(PROPKEY.CALL_VAA_INPUT_STATUS);
    }

    public boolean getIsIncomingProp() {
        return getIntProperty(PROPKEY.CALL_IS_INCOMING) != 0;
    }

    public int getIsConferenceProp() {
        return getIntProperty(PROPKEY.CALL_IS_CONFERENCE);
    }

    public boolean getIsOnHoldProp() {
        return getIntProperty(PROPKEY.CALL_IS_ON_HOLD) != 0;
    }

    public int getStartTimestampProp() {
        return getIntProperty(PROPKEY.CALL_START_TIMESTAMP);
    }

    public String getQualityProblemsProp() {
        return getStrProperty(PROPKEY.CALL_QUALITY_PROBLEMS);
    }

    public String getCurrentVideoAudienceProp() {
        return getStrProperty(PROPKEY.CALL_CURRENT_VIDEO_AUDIENCE);
    }

    public int getPremiumVideoStatusProp() {
        return getIntProperty(PROPKEY.CALL_PREMIUM_VIDEO_STATUS);
    }

    public boolean getPremiumVideoIsGracePeriodProp() {
        return getIntProperty(PROPKEY.CALL_PREMIUM_VIDEO_IS_GRACE_PERIOD) != 0;
    }

    public boolean getIsPremiumVideoSponsorProp() {
        return getIntProperty(PROPKEY.CALL_IS_PREMIUM_VIDEO_SPONSOR) != 0;
    }

    public String getPremiumVideoSponsorListProp() {
        return getStrProperty(PROPKEY.CALL_PREMIUM_VIDEO_SPONSOR_LIST);
    }

    public int getMaxVideoconfcallParticipantsProp() {
        return getIntProperty(PROPKEY.CALL_MAX_VIDEOCONFCALL_PARTICIPANTS);
    }

    public int getOptimalRemoteVideosInConferenceProp() {
        return getIntProperty(PROPKEY.CALL_OPTIMAL_REMOTE_VIDEOS_IN_CONFERENCE);
    }

    public String getMessageIdProp() {
        return getStrProperty(PROPKEY.CALL_MESSAGE_ID);
    }

    public int getStatusProp() {
        return getIntProperty(PROPKEY.CALL_STATUS);
    }

    public String getThreadIdProp() {
        return getStrProperty(PROPKEY.CALL_THREAD_ID);
    }

    public String getLegIdProp() {
        return getStrProperty(PROPKEY.CALL_LEG_ID);
    }

    public String getConversationTypeProp() {
        return getStrProperty(PROPKEY.CALL_CONVERSATION_TYPE);
    }

    public int getDatachannelObjectIdProp() {
        return getIntProperty(PROPKEY.CALL_DATACHANNEL_OBJECT_ID);
    }

    public String getEndpointDetailsProp() {
        return getStrProperty(PROPKEY.CALL_ENDPOINT_DETAILS);
    }

    public String getCallerMriIdentityProp() {
        return getStrProperty(PROPKEY.CALL_CALLER_MRI_IDENTITY);
    }

    public boolean getMemberCountChangedProp() {
        return getIntProperty(PROPKEY.CALL_MEMBER_COUNT_CHANGED) != 0;
    }

    public int getTransferStatusProp() {
        return getIntProperty(PROPKEY.CALL_TRANSFER_STATUS);
    }

    public int getTransferFailureReasonProp() {
        return getIntProperty(PROPKEY.CALL_TRANSFER_FAILURE_REASON);
    }

    public String getForwardingDestinationTypeProp() {
        return getStrProperty(PROPKEY.CALL_FORWARDING_DESTINATION_TYPE);
    }

    public String getIncomingTypeProp() {
        return getStrProperty(PROPKEY.CALL_INCOMING_TYPE);
    }

    public String getOnbehalfofMriProp() {
        return getStrProperty(PROPKEY.CALL_ONBEHALFOF_MRI);
    }

    public String getTransferorMriProp() {
        return getStrProperty(PROPKEY.CALL_TRANSFEROR_MRI);
    }

    public boolean getIsIncomingOneOnOneVideoCallProp() {
        return getIntProperty(PROPKEY.CALL_IS_INCOMING_ONE_ON_ONE_VIDEO_CALL) != 0;
    }

    public String getQueueInfoProp() {
        return getStrProperty(PROPKEY.CALL_QUEUE_INFO);
    }

    public String getTransferorTypeProp() {
        return getStrProperty(PROPKEY.CALL_TRANSFEROR_TYPE);
    }

    public String getTransferorDisplaynameProp() {
        return getStrProperty(PROPKEY.CALL_TRANSFEROR_DISPLAYNAME);
    }

    public String getInvitationDataProp() {
        return getStrProperty(PROPKEY.CALL_INVITATION_DATA);
    }

    public String getMeetingDetailsProp() {
        return getStrProperty(PROPKEY.CALL_MEETING_DETAILS);
    }

    public String getRoleProp() {
        return getStrProperty(PROPKEY.CALL_ROLE);
    }

    public String getTenantIdProp() {
        return getStrProperty(PROPKEY.CALL_TENANT_ID);
    }

    public String getBroadcastMetadataProp() {
        return getStrProperty(PROPKEY.CALL_BROADCAST_METADATA);
    }

    public boolean getContentSharingSessionCountChangedProp() {
        return getIntProperty(PROPKEY.CALL_CONTENT_SHARING_SESSION_COUNT_CHANGED) != 0;
    }

    public int getFailureReasonProp() {
        return getIntProperty(PROPKEY.CALL_FAILURE_REASON);
    }

    public String getProgressStatusProp() {
        return getStrProperty(PROPKEY.CALL_PROGRESS_STATUS);
    }

    public String getConsultativeTransferCallIdProp() {
        return getStrProperty(PROPKEY.CALL_CONSULTATIVE_TRANSFER_CALL_ID);
    }

    public int getParkStatusProp() {
        return getIntProperty(PROPKEY.CALL_PARK_STATUS);
    }

    public int getParkFailureReasonProp() {
        return getIntProperty(PROPKEY.CALL_PARK_FAILURE_REASON);
    }

    public String getParkPickupCodeProp() {
        return getStrProperty(PROPKEY.CALL_PARK_PICKUP_CODE);
    }

    public int getOriginProp() {
        return getIntProperty(PROPKEY.CALL_ORIGIN);
    }

    public String getEndDiagnosticsCodeProp() {
        return getStrProperty(PROPKEY.CALL_END_DIAGNOSTICS_CODE);
    }

    public String getServerHoldLocationProp() {
        return getStrProperty(PROPKEY.CALL_SERVER_HOLD_LOCATION);
    }

    public void addListener(CallIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(CallIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }
}
