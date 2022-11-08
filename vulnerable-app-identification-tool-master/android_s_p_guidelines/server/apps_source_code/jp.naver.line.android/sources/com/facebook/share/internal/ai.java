package com.facebook.share.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import com.facebook.n;
import com.facebook.s;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareMessengerActionButton;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareMessengerURLActionButton;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.ShareOpenGraphValueContainer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import java.util.List;
import java.util.Locale;

public final class ai {
    private static aj a;
    private static aj b;

    public static void a(ShareContent shareContent) {
        a(shareContent, a());
    }

    public static void b(ShareContent shareContent) {
        a(shareContent, a());
    }

    private static aj a() {
        if (b == null) {
            b = new aj();
        }
        return b;
    }

    private static void a(ShareContent shareContent, aj ajVar) throws n {
        if (shareContent == null) {
            throw new n("Must provide non-null content to share");
        } else if (shareContent instanceof ShareLinkContent) {
            a((ShareLinkContent) shareContent);
        } else if (shareContent instanceof SharePhotoContent) {
            a((SharePhotoContent) shareContent, ajVar);
        } else if (shareContent instanceof ShareVideoContent) {
            ajVar.a((ShareVideoContent) shareContent);
        } else if (shareContent instanceof ShareOpenGraphContent) {
            ajVar.a((ShareOpenGraphContent) shareContent);
        } else if (shareContent instanceof ShareMediaContent) {
            ajVar.a((ShareMediaContent) shareContent);
        } else if (shareContent instanceof ShareCameraEffectContent) {
            a((ShareCameraEffectContent) shareContent);
        } else if (shareContent instanceof ShareMessengerOpenGraphMusicTemplateContent) {
            a((ShareMessengerOpenGraphMusicTemplateContent) shareContent);
        } else if (shareContent instanceof ShareMessengerMediaTemplateContent) {
            a((ShareMessengerMediaTemplateContent) shareContent);
        } else {
            if (shareContent instanceof ShareMessengerGenericTemplateContent) {
                a((ShareMessengerGenericTemplateContent) shareContent);
            }
        }
    }

    private static void b(SharePhoto sharePhoto) {
        if (sharePhoto != null) {
            Bitmap c = sharePhoto.c();
            Uri d = sharePhoto.d();
            if (c == null && d == null) {
                throw new n("SharePhoto does not have a Bitmap or ImageUrl specified");
            }
            return;
        }
        throw new n("Cannot share a null SharePhoto");
    }

    private static void a(ShareMessengerActionButton shareMessengerActionButton) {
        if (shareMessengerActionButton != null) {
            if (bj.a(shareMessengerActionButton.a())) {
                throw new n("Must specify title for ShareMessengerActionButton");
            } else if ((shareMessengerActionButton instanceof ShareMessengerURLActionButton) && ((ShareMessengerURLActionButton) shareMessengerActionButton).b() == null) {
                throw new n("Must specify url for ShareMessengerURLActionButton");
            }
        }
    }

    private static void a(Object obj, aj ajVar) {
        if (obj instanceof ShareOpenGraphObject) {
            ShareOpenGraphObject shareOpenGraphObject = (ShareOpenGraphObject) obj;
            if (shareOpenGraphObject != null) {
                a(shareOpenGraphObject, ajVar, true);
                return;
            }
            throw new n("Cannot share a null ShareOpenGraphObject");
        }
        if (obj instanceof SharePhoto) {
            ajVar.a((SharePhoto) obj);
        }
    }

    public static void c(ShareContent shareContent) {
        if (a == null) {
            a = new ak();
        }
        a(shareContent, a);
    }

    private static /* synthetic */ void a(ShareLinkContent shareLinkContent) {
        Uri c = shareLinkContent.c();
        if (c != null && !bj.b(c)) {
            throw new n("Image Url must be an http:// or https:// url");
        }
    }

    private static /* synthetic */ void a(SharePhotoContent sharePhotoContent, aj ajVar) {
        List<SharePhoto> a = sharePhotoContent.a();
        if (a == null || a.isEmpty()) {
            throw new n("Must specify at least one Photo in SharePhotoContent.");
        } else if (a.size() <= 6) {
            for (SharePhoto a2 : a) {
                ajVar.a(a2);
            }
        } else {
            throw new n(String.format(Locale.ROOT, "Cannot add more than %d photos.", new Object[]{Integer.valueOf(6)}));
        }
    }

    private static /* synthetic */ void a(ShareCameraEffectContent shareCameraEffectContent) {
        if (bj.a(shareCameraEffectContent.a())) {
            throw new n("Must specify a non-empty effectId");
        }
    }

    static /* synthetic */ void a(ShareOpenGraphContent shareOpenGraphContent, aj ajVar) {
        ShareOpenGraphValueContainer a = shareOpenGraphContent.a();
        if (a == null) {
            throw new n("Must specify a non-null ShareOpenGraphAction");
        } else if (bj.a(a.b("og:type"))) {
            throw new n("ShareOpenGraphAction must have a non-empty actionType");
        } else {
            a(a, ajVar, false);
            String b = shareOpenGraphContent.b();
            if (bj.a(b)) {
                throw new n("Must specify a previewPropertyName.");
            } else if (shareOpenGraphContent.a().a(b) == null) {
                StringBuilder stringBuilder = new StringBuilder("Property \"");
                stringBuilder.append(b);
                stringBuilder.append("\" was not found on the action. The name of the preview property must match the name of an action property.");
                throw new n(stringBuilder.toString());
            }
        }
    }

    private static /* synthetic */ void a(ShareOpenGraphValueContainer shareOpenGraphValueContainer, aj ajVar, boolean z) {
        for (String str : shareOpenGraphValueContainer.b()) {
            if (z) {
                String[] split = str.split(":");
                if (split.length >= 2) {
                    for (String isEmpty : split) {
                        if (isEmpty.isEmpty()) {
                            throw new n("Invalid key found in Open Graph dictionary: %s", str);
                        }
                    }
                } else {
                    throw new n("Open Graph keys must be namespaced: %s", str);
                }
            }
            Object a = shareOpenGraphValueContainer.a(str);
            if (a instanceof List) {
                for (Object next : (List) a) {
                    if (next != null) {
                        a(next, ajVar);
                    } else {
                        throw new n("Cannot put null objects in Lists in ShareOpenGraphObjects and ShareOpenGraphActions");
                    }
                }
                continue;
            } else {
                a(a, ajVar);
            }
        }
    }

    static /* synthetic */ void a(SharePhoto sharePhoto, aj ajVar) {
        b(sharePhoto);
        Bitmap c = sharePhoto.c();
        Uri d = sharePhoto.d();
        if (c == null && bj.b(d) && !ajVar.a()) {
            throw new n("Cannot set the ImageUrl of a SharePhoto to the Uri of an image on the web when sharing SharePhotoContent");
        } else if (sharePhoto.c() != null || !bj.b(sharePhoto.d())) {
            bn.d(s.f());
        }
    }

    static /* synthetic */ void a(ShareVideo shareVideo) {
        if (shareVideo != null) {
            Uri c = shareVideo.c();
            if (c == null) {
                throw new n("ShareVideo does not have a LocalUrl specified");
            } else if (!bj.c(c) && !bj.d(c)) {
                throw new n("ShareVideo must reference a video that is on the device");
            } else {
                return;
            }
        }
        throw new n("Cannot share a null ShareVideo");
    }

    private static /* synthetic */ void a(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) {
        if (bj.a(shareMessengerOpenGraphMusicTemplateContent.k())) {
            throw new n("Must specify Page Id for ShareMessengerOpenGraphMusicTemplateContent");
        } else if (shareMessengerOpenGraphMusicTemplateContent.a() != null) {
            a(shareMessengerOpenGraphMusicTemplateContent.b());
        } else {
            throw new n("Must specify url for ShareMessengerOpenGraphMusicTemplateContent");
        }
    }

    private static /* synthetic */ void a(ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) {
        if (bj.a(shareMessengerGenericTemplateContent.k())) {
            throw new n("Must specify Page Id for ShareMessengerGenericTemplateContent");
        } else if (shareMessengerGenericTemplateContent.c() == null) {
            throw new n("Must specify element for ShareMessengerGenericTemplateContent");
        } else if (bj.a(shareMessengerGenericTemplateContent.c().a())) {
            throw new n("Must specify title for ShareMessengerGenericTemplateElement");
        } else {
            a(shareMessengerGenericTemplateContent.c().e());
        }
    }

    private static /* synthetic */ void a(ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) {
        if (bj.a(shareMessengerMediaTemplateContent.k())) {
            throw new n("Must specify Page Id for ShareMessengerMediaTemplateContent");
        } else if (shareMessengerMediaTemplateContent.c() == null && bj.a(shareMessengerMediaTemplateContent.b())) {
            throw new n("Must specify either attachmentId or mediaURL for ShareMessengerMediaTemplateContent");
        } else {
            a(shareMessengerMediaTemplateContent.d());
        }
    }
}
