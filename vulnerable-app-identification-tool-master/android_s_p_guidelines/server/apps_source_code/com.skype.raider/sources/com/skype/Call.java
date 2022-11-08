package com.skype;

import android.support.v4.util.m;

public interface Call extends ObjectInterface {

    public interface CallIListener {
    }

    public enum ORIGIN_TYPE {
        UNSPECIFIED(0),
        TRANSFER(1),
        PARK(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<ORIGIN_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            ORIGIN_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                ORIGIN_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private ORIGIN_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static ORIGIN_TYPE fromInt(int i) {
            ORIGIN_TYPE tmpVar = (ORIGIN_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    void addListener(CallIListener callIListener);

    String getAccessTokenProp();

    int getActiveMembersProp();

    int getBeginTimestampProp();

    String getBroadcastMetadataProp();

    String getCallerMriIdentityProp();

    String getConsultativeTransferCallIdProp();

    boolean getContentSharingSessionCountChangedProp();

    String getConversationTypeProp();

    String getCurrentVideoAudienceProp();

    int getDatachannelObjectIdProp();

    int getDurationProp();

    String getEndDiagnosticsCodeProp();

    String getEndpointDetailsProp();

    int getFailureReasonProp();

    String getForwardingDestinationTypeProp();

    String getHostIdentityProp();

    String getIncomingTypeProp();

    String getInvitationDataProp();

    boolean getIsActiveProp();

    int getIsConferenceProp();

    boolean getIsHostlessProp();

    boolean getIsIncomingOneOnOneVideoCallProp();

    boolean getIsIncomingProp();

    boolean getIsMutedProp();

    boolean getIsMutedSpeakerProp();

    boolean getIsOnHoldProp();

    boolean getIsPremiumVideoSponsorProp();

    boolean getIsServerMutedProp();

    boolean getIsUnseenMissedProp();

    boolean getJoinedExistingProp();

    String getLegIdProp();

    int getMaxVideoconfcallParticipantsProp();

    String getMeetingDetailsProp();

    boolean getMemberCountChangedProp();

    String getMessageIdProp();

    int getMikeStatusProp();

    String getNameProp();

    String getOnbehalfofMriProp();

    int getOptimalRemoteVideosInConferenceProp();

    int getOriginProp();

    int getParkFailureReasonProp();

    String getParkPickupCodeProp();

    int getParkStatusProp();

    boolean getPremiumVideoIsGracePeriodProp();

    String getPremiumVideoSponsorListProp();

    int getPremiumVideoStatusProp();

    String getProgressStatusProp();

    String getQualityProblemsProp();

    String getQueueInfoProp();

    String getRoleProp();

    String getServerHoldLocationProp();

    String getServerIdentityProp();

    int getSoundlevelProp();

    int getStartTimestampProp();

    int getStatusProp();

    String getTenantIdProp();

    String getThreadIdProp();

    String getTopicProp();

    int getTransferFailureReasonProp();

    int getTransferStatusProp();

    String getTransferorDisplaynameProp();

    String getTransferorMriProp();

    String getTransferorTypeProp();

    int getVaaInputStatusProp();

    boolean getVideoDisabledProp();

    void removeListener(CallIListener callIListener);
}
