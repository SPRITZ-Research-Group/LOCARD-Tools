package com.appboy.ui.support;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import com.appboy.a.a;
import com.appboy.f.c;
import com.appboy.f.i;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.k.b.b;

public class FrescoLibraryUtils {
    private static final String TAG = c.a(FrescoLibraryUtils.class);
    private static final String[] USED_FRESCO_CLASSES = new String[]{"com.facebook.drawee.backends.pipeline.Fresco", "com.facebook.drawee.interfaces.DraweeController", "com.facebook.drawee.view.SimpleDraweeView", "com.facebook.drawee.backends.pipeline.Fresco", "com.facebook.drawee.controller.BaseControllerListener", "com.facebook.drawee.controller.ControllerListener", "com.facebook.imagepipeline.image.ImageInfo"};
    private static boolean sCanUseFresco = false;
    private static boolean sCanUseFrescoSet = false;

    private static boolean getIsFrescoEnabled(Context context) {
        return new a(context).r();
    }

    public static boolean canUseFresco(Context context) {
        if (sCanUseFrescoSet) {
            return sCanUseFresco;
        }
        if (getIsFrescoEnabled(context.getApplicationContext())) {
            try {
                ClassLoader staticClassLoader = FrescoLibraryUtils.class.getClassLoader();
                sCanUseFresco = true;
                for (String cls : USED_FRESCO_CLASSES) {
                    if (Class.forName(cls, false, staticClassLoader) == null) {
                        sCanUseFresco = false;
                        break;
                    }
                }
            } catch (Exception e) {
                sCanUseFresco = false;
            } catch (NoClassDefFoundError e2) {
                sCanUseFresco = false;
            } catch (Throwable th) {
                sCanUseFresco = false;
            }
            sCanUseFrescoSet = true;
            return sCanUseFresco;
        }
        sCanUseFresco = false;
        sCanUseFrescoSet = true;
        return false;
    }

    public static void setDraweeControllerHelper(SimpleDraweeView simpleDraweeView, String imageUrl, float aspectRatio, boolean respectAspectRatio) {
        setDraweeControllerHelper(simpleDraweeView, imageUrl, aspectRatio, respectAspectRatio, null);
    }

    public static void setDraweeControllerHelper(final SimpleDraweeView simpleDraweeView, String imageUrl, final float aspectRatio, final boolean respectAspectRatio, ControllerListener<ImageInfo> controllerListener) {
        if (i.c(imageUrl)) {
            c.f(TAG, "The url set for the Drawee controller was null. Controller not set.");
        } else if (simpleDraweeView == null) {
            c.f(TAG, "The SimpleDraweeView set for the Drawee controller was null. Controller not set.");
        } else {
            b requestLevel = com.appboy.a.h() ? b.DISK_CACHE : b.FULL_FETCH;
            c.b(TAG, "Setting Fresco image request level to: " + requestLevel);
            if (controllerListener == null) {
                controllerListener = new BaseControllerListener<ImageInfo>() {
                    public final void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        if (imageInfo != null) {
                            float imageAspectRatio;
                            if (respectAspectRatio) {
                                imageAspectRatio = aspectRatio;
                            } else {
                                imageAspectRatio = (float) (imageInfo.a() / imageInfo.b());
                            }
                            simpleDraweeView.post(new Runnable() {
                                public void run() {
                                    simpleDraweeView.setAspectRatio(imageAspectRatio);
                                }
                            });
                        }
                    }
                };
            }
            try {
                Uri uri = getFrescoUri(imageUrl);
                simpleDraweeView.setController(((com.facebook.drawee.backends.pipeline.c) ((com.facebook.drawee.backends.pipeline.c) ((com.facebook.drawee.backends.pipeline.c) ((com.facebook.drawee.backends.pipeline.c) Fresco.a().a(uri).f()).e()).a((ControllerListener) controllerListener)).b((Object) com.facebook.imagepipeline.k.c.a(uri).a(requestLevel).q())).h());
            } catch (NullPointerException e) {
                c.d(TAG, "Fresco controller builder could not be retrieved. Fresco most likely prematurely shutdown.", e);
            } catch (Exception e2) {
                c.d(TAG, "Fresco controller builder could not be retrieved. Fresco most likely prematurely shutdown.", e2);
            }
        }
    }

    static Uri getFrescoUri(String uriString) {
        Uri uri = Uri.parse(uriString);
        if (i.c(uri.getScheme())) {
            return Uri.parse("file://" + uriString);
        }
        return uri;
    }
}
