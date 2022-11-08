package com.facebook.share.internal;

import com.facebook.n;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import java.util.List;
import java.util.Locale;

class aj {
    private boolean a;

    private aj() {
        this.a = false;
    }

    /* synthetic */ aj(byte b) {
        this();
    }

    public final void a(ShareOpenGraphContent shareOpenGraphContent) {
        this.a = true;
        ai.a(shareOpenGraphContent, this);
    }

    public void a(SharePhoto sharePhoto) {
        ai.a(sharePhoto, this);
    }

    public final boolean a() {
        return this.a;
    }

    public void a(ShareVideoContent shareVideoContent) {
        ai.a(shareVideoContent.d());
        SharePhoto c = shareVideoContent.c();
        if (c != null) {
            a(c);
        }
    }

    public void a(ShareMediaContent shareMediaContent) {
        List<ShareMedia> a = shareMediaContent.a();
        if (a == null || a.isEmpty()) {
            throw new n("Must specify at least one medium in ShareMediaContent.");
        } else if (a.size() <= 6) {
            for (ShareMedia shareMedia : a) {
                if (shareMedia instanceof SharePhoto) {
                    a((SharePhoto) shareMedia);
                } else if (shareMedia instanceof ShareVideo) {
                    ai.a((ShareVideo) shareMedia);
                } else {
                    throw new n(String.format(Locale.ROOT, "Invalid media type: %s", new Object[]{shareMedia.getClass().getSimpleName()}));
                }
            }
        } else {
            throw new n(String.format(Locale.ROOT, "Cannot add more than %d media.", new Object[]{Integer.valueOf(6)}));
        }
    }
}
