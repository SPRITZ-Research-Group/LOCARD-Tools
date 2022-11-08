package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.internal.bj;
import com.facebook.internal.bl;
import com.facebook.n;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import org.json.JSONObject;

public final class an {
    public static Bundle a(ShareLinkContent shareLinkContent) {
        Bundle a = a((ShareContent) shareLinkContent);
        bj.a(a, "href", shareLinkContent.h());
        bj.a(a, "quote", shareLinkContent.d());
        return a;
    }

    public static Bundle a(ShareOpenGraphContent shareOpenGraphContent) {
        Bundle a = a((ShareContent) shareOpenGraphContent);
        bj.a(a, "action_type", shareOpenGraphContent.a().b("og:type"));
        try {
            JSONObject a2 = am.a(am.a(shareOpenGraphContent), false);
            if (a2 != null) {
                bj.a(a, "action_properties", a2.toString());
            }
            return a;
        } catch (Throwable e) {
            throw new n("Unable to serialize the ShareOpenGraphContent to JSON", e);
        }
    }

    public static Bundle a(SharePhotoContent sharePhotoContent) {
        Bundle a = a((ShareContent) sharePhotoContent);
        String[] strArr = new String[sharePhotoContent.a().size()];
        bj.a(sharePhotoContent.a(), new bl<SharePhoto, String>() {
            public final /* synthetic */ Object a(Object obj) {
                return ((SharePhoto) obj).d().toString();
            }
        }).toArray(strArr);
        a.putStringArray("media", strArr);
        return a;
    }

    private static Bundle a(ShareContent shareContent) {
        Bundle bundle = new Bundle();
        ShareHashtag m = shareContent.m();
        if (m != null) {
            bj.a(bundle, "hashtag", m.a());
        }
        return bundle;
    }
}
