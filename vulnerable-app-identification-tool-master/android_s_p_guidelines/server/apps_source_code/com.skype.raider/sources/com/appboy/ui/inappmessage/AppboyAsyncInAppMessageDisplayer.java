package com.appboy.ui.inappmessage;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import com.appboy.a;
import com.appboy.b.a.e;
import com.appboy.e.b;
import com.appboy.e.i;
import com.appboy.e.j;
import com.appboy.e.l;
import com.appboy.e.m;
import com.appboy.f.c;
import com.appboy.f.k;
import com.appboy.ui.support.FrescoLibraryUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.g;
import java.io.File;

public class AppboyAsyncInAppMessageDisplayer extends AsyncTask<b, Integer, b> {
    private static final String TAG = c.a(AppboyAsyncInAppMessageDisplayer.class);

    protected b doInBackground(b... inAppMessages) {
        try {
            b inAppMessage = inAppMessages[0];
            if (inAppMessage.D()) {
                c.b(TAG, "Skipping in-app message preparation for control in-app message.");
                return inAppMessage;
            }
            c.b(TAG, "Starting asynchronous in-app message preparation.");
            Context applicationContext = AppboyInAppMessageManager.getInstance().getApplicationContext();
            if (!(inAppMessage instanceof j)) {
                boolean imageDownloadSucceeded;
                if (FrescoLibraryUtils.canUseFresco(applicationContext)) {
                    imageDownloadSucceeded = prepareInAppMessageWithFresco(inAppMessage);
                } else {
                    imageDownloadSucceeded = prepareInAppMessageWithBitmapDownload(inAppMessage);
                }
                if (imageDownloadSucceeded) {
                    return inAppMessage;
                }
                inAppMessage.a(e.IMAGE_DOWNLOAD);
                return null;
            } else if (prepareInAppMessageWithHtml(inAppMessage)) {
                return inAppMessage;
            } else {
                inAppMessage.a(e.ZIP_ASSET_DOWNLOAD);
                return null;
            }
        } catch (Exception e) {
            c.d(TAG, "Error running AsyncInAppMessageDisplayer", e);
            return null;
        }
    }

    protected void onPostExecute(final b inAppMessage) {
        if (inAppMessage != null) {
            try {
                c.b(TAG, "Finished asynchronous in-app message preparation. Attempting to display in-app message.");
                new Handler(AppboyInAppMessageManager.getInstance().getApplicationContext().getMainLooper()).post(new Runnable() {
                    public void run() {
                        c.b(AppboyAsyncInAppMessageDisplayer.TAG, "Displaying in-app message.");
                        AppboyInAppMessageManager.getInstance().displayInAppMessage(inAppMessage, false);
                    }
                });
                return;
            } catch (Exception e) {
                c.d(TAG, "Error running onPostExecute", e);
                return;
            }
        }
        c.g(TAG, "Cannot display the in-app message because the in-app message was null.");
    }

    boolean prepareInAppMessageWithHtml(b inAppMessage) {
        i inAppMessageHtml = (i) inAppMessage;
        String localAssets = inAppMessageHtml.E();
        if (!com.appboy.f.i.c(localAssets) && new File(localAssets).exists()) {
            c.d(TAG, "Local assets for html in-app message are already populated. Not downloading assets.");
            return true;
        } else if (com.appboy.f.i.c(inAppMessageHtml.F())) {
            c.d(TAG, "Html in-app message has no remote asset zip. Continuing with in-app message preparation.");
            return true;
        } else {
            String localWebContentUrl = k.a(k.a(AppboyInAppMessageManager.getInstance().getApplicationContext()), inAppMessageHtml.F());
            if (com.appboy.f.i.c(localWebContentUrl)) {
                c.f(TAG, "Download of html content to local directory failed for remote url: " + inAppMessageHtml.F() + " . Returned local url is: " + localWebContentUrl);
                return false;
            }
            c.b(TAG, "Local url for html in-app message assets is " + localWebContentUrl);
            inAppMessageHtml.c(localWebContentUrl);
            return true;
        }
    }

    boolean prepareInAppMessageWithFresco(b inAppMessage) {
        boolean downloadSucceeded = true;
        String localImageUrl = inAppMessage.k();
        if (com.appboy.f.i.c(localImageUrl) || !new File(localImageUrl).exists()) {
            inAppMessage.y();
            String remoteImageUrl = inAppMessage.j();
            if (com.appboy.f.i.c(remoteImageUrl)) {
                c.f(TAG, "In-app message has no remote image url. Not downloading image.");
            } else {
                com.facebook.imagepipeline.k.b imageRequest;
                g imagePipeline = Fresco.b();
                if (remoteImageUrl == null || remoteImageUrl.length() == 0) {
                    imageRequest = null;
                } else {
                    imageRequest = com.facebook.imagepipeline.k.b.a(Uri.parse(remoteImageUrl));
                }
                com.facebook.datasource.c dataSource = imagePipeline.b(imageRequest, new Object());
                do {
                } while (!dataSource.b());
                if (dataSource.e()) {
                    downloadSucceeded = false;
                }
                if (downloadSucceeded) {
                    inAppMessage.z();
                } else if (dataSource.f() == null) {
                    c.f(TAG, "Fresco disk prefetch failed with null cause for remote image url:" + remoteImageUrl);
                } else {
                    c.f(TAG, "Fresco disk prefetch failed with cause: " + dataSource.f().getMessage() + " with remote image url: " + remoteImageUrl);
                }
                dataSource.h();
            }
        } else {
            c.d(TAG, "In-app message has local image url for Fresco display. Not downloading image.");
            inAppMessage.z();
        }
        return downloadSucceeded;
    }

    boolean prepareInAppMessageWithBitmapDownload(b inAppMessage) {
        if (inAppMessage.p() != null) {
            c.d(TAG, "In-app message already contains image bitmap. Not downloading image from URL.");
            inAppMessage.z();
            return true;
        }
        String localImageUrl = inAppMessage.k();
        if (!com.appboy.f.i.c(localImageUrl) && new File(localImageUrl).exists()) {
            c.d(TAG, "In-app message has local image url.");
            inAppMessage.a(com.appboy.f.b.a(Uri.parse(localImageUrl)));
        }
        if (inAppMessage.p() == null) {
            String remoteImageUrl = inAppMessage.j();
            if (com.appboy.f.i.c(remoteImageUrl)) {
                c.f(TAG, "In-app message has no remote image url. Not downloading image.");
                return true;
            }
            c.d(TAG, "In-app message has remote image url. Downloading.");
            com.appboy.b.b viewBounds = com.appboy.b.b.NO_BOUNDS;
            if (inAppMessage instanceof m) {
                viewBounds = com.appboy.b.b.IN_APP_MESSAGE_SLIDEUP;
            } else if (inAppMessage instanceof l) {
                viewBounds = com.appboy.b.b.IN_APP_MESSAGE_MODAL;
            }
            Context applicationContext = AppboyInAppMessageManager.getInstance().getApplicationContext();
            inAppMessage.a(a.a(applicationContext).g().a(applicationContext, remoteImageUrl, viewBounds));
        }
        if (inAppMessage.p() == null) {
            return false;
        }
        inAppMessage.z();
        return true;
    }
}
