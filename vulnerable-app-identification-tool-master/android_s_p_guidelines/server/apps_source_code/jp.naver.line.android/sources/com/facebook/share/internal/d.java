package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import com.facebook.n;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public final class d {
    public static Bundle a(UUID uuid, ShareContent shareContent, boolean z) {
        bn.a((Object) shareContent, "shareContent");
        bn.a((Object) uuid, "callId");
        Bundle a;
        if (shareContent instanceof ShareLinkContent) {
            ShareLinkContent shareLinkContent = (ShareLinkContent) shareContent;
            a = a(shareLinkContent, z);
            bj.a(a, "com.facebook.platform.extra.TITLE", shareLinkContent.b());
            bj.a(a, "com.facebook.platform.extra.DESCRIPTION", shareLinkContent.a());
            bj.a(a, "com.facebook.platform.extra.IMAGE", shareLinkContent.c());
            return a;
        } else if (shareContent instanceof SharePhotoContent) {
            SharePhotoContent sharePhotoContent = (SharePhotoContent) shareContent;
            Collection a2 = am.a(sharePhotoContent, uuid);
            a = a(sharePhotoContent, z);
            a.putStringArrayList("com.facebook.platform.extra.PHOTOS", new ArrayList(a2));
            return a;
        } else if ((shareContent instanceof ShareVideoContent) || !(shareContent instanceof ShareOpenGraphContent)) {
            return null;
        } else {
            ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent) shareContent;
            try {
                JSONObject a3 = am.a(uuid, shareOpenGraphContent);
                a = a(shareOpenGraphContent, z);
                bj.a(a, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", shareOpenGraphContent.b());
                bj.a(a, "com.facebook.platform.extra.ACTION_TYPE", shareOpenGraphContent.a().b("og:type"));
                bj.a(a, "com.facebook.platform.extra.ACTION", a3.toString());
                return a;
            } catch (JSONException e) {
                StringBuilder stringBuilder = new StringBuilder("Unable to create a JSON Object from the provided ShareOpenGraphContent: ");
                stringBuilder.append(e.getMessage());
                throw new n(stringBuilder.toString());
            }
        }
    }

    private static Bundle a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        bj.a(bundle, "com.facebook.platform.extra.LINK", shareContent.h());
        bj.a(bundle, "com.facebook.platform.extra.PLACE", shareContent.j());
        bj.a(bundle, "com.facebook.platform.extra.REF", shareContent.l());
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", z);
        Collection i = shareContent.i();
        if (!bj.a(i)) {
            bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", new ArrayList(i));
        }
        return bundle;
    }
}
