package com.skype.react.activationExperiment.models;

public enum OEMNotificationStyle {
    PictureWithText1,
    PictureWithText2,
    PictureWithText3,
    PictureWithoutText1,
    TextOnly1,
    TextOnly2,
    TextIcon2,
    TextOnlyGroupVideoCallFriends,
    TextOnlyBringTogetherFriendsGroupVideoCall,
    TextOnlyVideoCallFriends,
    TextOnlyGroupVideoCallFamily,
    TextOnlyReconnectFriends,
    TextOnlyMakeDaySpecial,
    TextOnlyTooBusy,
    TextOnlyTryAddIns1,
    TextOnlyTryAddIns2,
    TextOnlyTryHighlights,
    TextOnlySignedOutFriendsMissYou,
    TextOnlyFreeCallIndiaChatFriends,
    TextOnlySkypeExperienceTogether,
    TextOnlySSOOnceClickNewSkype,
    TextOnlySSOOnceClickMemorableMoment,
    TextOnlySendMoney,
    TextOnlySkypeTranslator,
    TextOnlyNeverMissLogBack,
    TextOnlyJumpLogBack,
    TextOnlyNewSkypeTapAway,
    TextOnlyLaunchedFriendsMissYou,
    TextOnlyRcntlyLnchedDntFrgtSignin1Step,
    TextOnlyFriendsTapAway,
    TextWithEmojiTripPlan,
    TextWithEmojiAdv,
    TextWithEmojiSelfie,
    TextwithEmojiSelfieAndPersonalize,
    TextwithEmojiFriendsFamilyFun,
    TextwithEmojiPersonalizewitHueAndGIF,
    TextWithEmojiCallAnyone,
    TextWithEmojiTalkWithWorld,
    TextWithEmojiTryMessaging,
    TextWithEmojiTrySelfie,
    TextWithEmojiCallYourMom,
    TextWithEmojiItsMothersDay,
    TextWithEmojiFreeCallIndiaStayConnected,
    TextWithEmojiSkypeReachFriendAnyPlatform,
    TextWithEmojiSSOOnceClickConnectNetwork,
    TextWithEmojiReachFriendAnyPlatform,
    TextIconMothersDaySpecialCall,
    TextIconWithEmojiItsMothersDay,
    TextOnlyMothersDayComing,
    TextOnlyMomSmileLoveYou,
    TextIconMothersDayVideoMessage,
    TextIconMothersDayGroupVideoCall,
    TextIconMothersDayMakeSpecial,
    TextOnlyMothersDayVideoCall,
    TextIconShortenMiles,
    TextIconCallUSMobLandBhangra,
    TextIconCallUSMobLandCallMe,
    DynamicContent,
    Unknown;

    public static OEMNotificationStyle a(String notificationStyle) {
        if (notificationStyle == null) {
            return Unknown;
        }
        try {
            return valueOf(notificationStyle);
        } catch (IllegalArgumentException e) {
            return Unknown;
        }
    }
}
