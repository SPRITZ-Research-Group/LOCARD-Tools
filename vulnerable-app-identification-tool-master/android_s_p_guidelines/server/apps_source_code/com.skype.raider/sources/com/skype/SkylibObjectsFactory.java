package com.skype;

public class SkylibObjectsFactory {
    public static <T extends ObjectInterface> T getSkylibObject(Class<T> clazz, int objectId, SkyLib lib) {
        Account rval;
        if (Account.class.equals(clazz) || AccountImpl.class.equals(clazz)) {
            rval = new AccountImpl();
            return lib.getAccount(objectId, rval) ? rval : null;
        } else if (Video.class.equals(clazz) || VideoImpl.class.equals(clazz)) {
            rval = new VideoImpl();
            if (lib.getVideo(objectId, rval)) {
                return rval;
            }
            return null;
        } else if (ContentSharing.class.equals(clazz) || ContentSharingImpl.class.equals(clazz)) {
            rval = new ContentSharingImpl();
            if (lib.getContentSharing(objectId, rval)) {
                return rval;
            }
            return null;
        } else if (DataChannel.class.equals(clazz) || DataChannelImpl.class.equals(clazz)) {
            rval = new DataChannelImpl();
            if (lib.getDataChannel(objectId, rval)) {
                return rval;
            }
            return null;
        } else if (Call.class.equals(clazz) || CallImpl.class.equals(clazz)) {
            rval = new CallImpl();
            if (lib.getCall(objectId, rval)) {
                return rval;
            }
            return null;
        } else if (!CallHandler.class.equals(clazz) && !CallHandlerImpl.class.equals(clazz)) {
            return null;
        } else {
            rval = new CallHandlerImpl();
            if (lib.getCallHandler(objectId, rval)) {
                return rval;
            }
            return null;
        }
    }
}
