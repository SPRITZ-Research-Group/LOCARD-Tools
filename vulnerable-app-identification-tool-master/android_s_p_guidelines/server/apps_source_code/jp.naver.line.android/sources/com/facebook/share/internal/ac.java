package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.internal.at;
import com.facebook.internal.au;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import com.facebook.n;
import com.facebook.share.internal.am.AnonymousClass5;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public final class ac {
    public static Bundle a(UUID uuid, ShareContent shareContent, boolean z) {
        bn.a((Object) shareContent, "shareContent");
        bn.a((Object) uuid, "callId");
        String str = null;
        Bundle a;
        Collection a2;
        if (shareContent instanceof ShareLinkContent) {
            shareContent = (ShareLinkContent) shareContent;
            a = a(shareContent, z);
            bj.a(a, "TITLE", shareContent.b());
            bj.a(a, "DESCRIPTION", shareContent.a());
            bj.a(a, "IMAGE", shareContent.c());
            bj.a(a, "QUOTE", shareContent.d());
            bj.a(a, "MESSENGER_LINK", shareContent.h());
            bj.a(a, "TARGET_DISPLAY", shareContent.h());
            return a;
        } else if (shareContent instanceof SharePhotoContent) {
            shareContent = (SharePhotoContent) shareContent;
            a2 = am.a((SharePhotoContent) shareContent, uuid);
            a = a(shareContent, z);
            a.putStringArrayList("PHOTOS", new ArrayList(a2));
            return a;
        } else if (shareContent instanceof ShareVideoContent) {
            shareContent = (ShareVideoContent) shareContent;
            if (!(shareContent == null || shareContent.d() == null)) {
                au a3 = at.a(uuid, shareContent.d().c());
                Collection arrayList = new ArrayList(1);
                arrayList.add(a3);
                at.a(arrayList);
                str = a3.a();
            }
            Bundle a4 = a(shareContent, z);
            bj.a(a4, "TITLE", shareContent.b());
            bj.a(a4, "DESCRIPTION", shareContent.a());
            bj.a(a4, "VIDEO", str);
            return a4;
        } else if (shareContent instanceof ShareOpenGraphContent) {
            shareContent = (ShareOpenGraphContent) shareContent;
            try {
                JSONObject a5 = am.a(am.a(uuid, (ShareOpenGraphContent) shareContent), false);
                a = a(shareContent, z);
                bj.a(a, "PREVIEW_PROPERTY_NAME", (String) am.a(shareContent.b()).second);
                bj.a(a, "ACTION_TYPE", shareContent.a().b("og:type"));
                bj.a(a, "ACTION", a5.toString());
                return a;
            } catch (JSONException e) {
                StringBuilder stringBuilder = new StringBuilder("Unable to create a JSON Object from the provided ShareOpenGraphContent: ");
                stringBuilder.append(e.getMessage());
                throw new n(stringBuilder.toString());
            }
        } else if (shareContent instanceof ShareMediaContent) {
            shareContent = (ShareMediaContent) shareContent;
            if (shareContent != null) {
                List a6 = shareContent.a();
                if (a6 != null) {
                    Collection arrayList2 = new ArrayList();
                    a2 = bj.a(a6, new AnonymousClass5(uuid, arrayList2));
                    at.a(arrayList2);
                    a = a(shareContent, z);
                    a.putParcelableArrayList("MEDIA", new ArrayList(a2));
                    return a;
                }
            }
            a2 = null;
            a = a(shareContent, z);
            a.putParcelableArrayList("MEDIA", new ArrayList(a2));
            return a;
        } else if (shareContent instanceof ShareCameraEffectContent) {
            ShareCameraEffectContent shareCameraEffectContent = (ShareCameraEffectContent) shareContent;
            return a(shareCameraEffectContent, am.a(shareCameraEffectContent, uuid), z);
        } else if (shareContent instanceof ShareMessengerGenericTemplateContent) {
            return a((ShareMessengerGenericTemplateContent) shareContent, z);
        } else {
            if (shareContent instanceof ShareMessengerOpenGraphMusicTemplateContent) {
                return a((ShareMessengerOpenGraphMusicTemplateContent) shareContent, z);
            }
            if (shareContent instanceof ShareMessengerMediaTemplateContent) {
                return a((ShareMessengerMediaTemplateContent) shareContent, z);
            }
            return null;
        }
    }

    private static Bundle a(ShareCameraEffectContent shareCameraEffectContent, Bundle bundle, boolean z) {
        Bundle a = a((ShareContent) shareCameraEffectContent, z);
        bj.a(a, "effect_id", shareCameraEffectContent.a());
        if (bundle != null) {
            a.putBundle("effect_textures", bundle);
        }
        try {
            JSONObject a2 = b.a(shareCameraEffectContent.b());
            if (a2 != null) {
                bj.a(a, "effect_arguments", a2.toString());
            }
            return a;
        } catch (JSONException e) {
            StringBuilder stringBuilder = new StringBuilder("Unable to create a JSON Object from the provided CameraEffectArguments: ");
            stringBuilder.append(e.getMessage());
            throw new n(stringBuilder.toString());
        }
    }

    private static Bundle a(ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent, boolean z) {
        Bundle a = a((ShareContent) shareMessengerGenericTemplateContent, z);
        try {
            ab.a(a, shareMessengerGenericTemplateContent);
            return a;
        } catch (JSONException e) {
            StringBuilder stringBuilder = new StringBuilder("Unable to create a JSON Object from the provided ShareMessengerGenericTemplateContent: ");
            stringBuilder.append(e.getMessage());
            throw new n(stringBuilder.toString());
        }
    }

    private static Bundle a(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent, boolean z) {
        Bundle a = a((ShareContent) shareMessengerOpenGraphMusicTemplateContent, z);
        try {
            ab.a(a, shareMessengerOpenGraphMusicTemplateContent);
            return a;
        } catch (JSONException e) {
            StringBuilder stringBuilder = new StringBuilder("Unable to create a JSON Object from the provided ShareMessengerOpenGraphMusicTemplateContent: ");
            stringBuilder.append(e.getMessage());
            throw new n(stringBuilder.toString());
        }
    }

    private static Bundle a(ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent, boolean z) {
        Bundle a = a((ShareContent) shareMessengerMediaTemplateContent, z);
        try {
            ab.a(a, shareMessengerMediaTemplateContent);
            return a;
        } catch (JSONException e) {
            StringBuilder stringBuilder = new StringBuilder("Unable to create a JSON Object from the provided ShareMessengerMediaTemplateContent: ");
            stringBuilder.append(e.getMessage());
            throw new n(stringBuilder.toString());
        }
    }

    private static Bundle a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        bj.a(bundle, "LINK", shareContent.h());
        bj.a(bundle, "PLACE", shareContent.j());
        bj.a(bundle, "PAGE", shareContent.k());
        bj.a(bundle, "REF", shareContent.l());
        bundle.putBoolean("DATA_FAILURES_FATAL", z);
        Collection i = shareContent.i();
        if (!bj.a(i)) {
            bundle.putStringArrayList("FRIENDS", new ArrayList(i));
        }
        ShareHashtag m = shareContent.m();
        if (m != null) {
            bj.a(bundle, "HASHTAG", m.a());
        }
        return bundle;
    }
}
