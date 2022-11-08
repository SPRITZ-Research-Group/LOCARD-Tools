package com.skype.react.activationExperiment;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.b;
import android.support.v4.app.NotificationCompat.c;
import android.support.v4.app.NotificationCompat.d;
import android.support.v4.app.NotificationCompat.q;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.facebook.common.logging.FLog;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.react.activationExperiment.ImageFetcher.ImageFetcherCallback;
import com.skype.react.activationExperiment.NotificationImageFetcher.NotificationImageFetcherCallback;
import com.skype.react.activationExperiment.models.ActivationOptions;
import com.skype.react.activationExperiment.models.NotificationAction;
import com.skype.react.activationExperiment.models.NotificationActionType;
import com.skype.react.activationExperiment.models.NotificationError;
import com.skype.react.activationExperiment.models.NotificationMeta;
import com.skype.react.activationExperiment.models.OEMNotificationStyle;
import com.skype.react.activationExperiment.models.OEMNotificationTemplateType;
import com.skype.react.common.NotificationStatusCallback;
import com.skype.react.upgrade.R;
import com.skype.react.upgrade.UpgradeConstants;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

final class a implements UpgradeConstants {
    private static final Random b = new Random();

    /* renamed from: com.skype.react.activationExperiment.a$2 */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            b = new int[OEMNotificationStyle.values().length];
            try {
                b[OEMNotificationStyle.TextWithEmojiCallAnyone.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[OEMNotificationStyle.TextWithEmojiTalkWithWorld.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[OEMNotificationStyle.TextOnlyFreeCallIndiaChatFriends.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[OEMNotificationStyle.TextWithEmojiFreeCallIndiaStayConnected.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[OEMNotificationStyle.TextIconCallUSMobLandBhangra.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[OEMNotificationStyle.TextIconCallUSMobLandCallMe.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[OEMNotificationStyle.TextOnlySendMoney.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[OEMNotificationStyle.TextOnlySkypeTranslator.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[OEMNotificationStyle.DynamicContent.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[a.NetworkError.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[a.NetworkTimeout.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[a.InvalidImageUri.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[a.Unknown.ordinal()] = 4;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    static /* synthetic */ Notification a(Context x0, NotificationMeta x1, Bitmap x2, Bitmap x3, int x4) {
        OEMNotificationTemplateType a = OEMNotificationTemplateType.a(x1.c());
        if (VERSION.SDK_INT < 24 || OEMNotificationTemplateType.CustomBackground != a) {
            return a(x0, x1, x4, x3, x2);
        }
        Builder builder = new Builder(x0);
        builder.setSmallIcon(NotificationMeta.a(x0));
        builder.setTicker(x1.f());
        builder.setAutoCancel(true);
        RemoteViews remoteViews = new RemoteViews(x0.getPackageName(), R.layout.notification_base);
        remoteViews.setTextViewText(R.id.app_name, NotificationMeta.c(x0));
        remoteViews.setImageViewResource(R.id.app_icon, NotificationMeta.b(x0));
        remoteViews.setTextViewText(R.id.title, x1.f());
        remoteViews.setTextViewText(R.id.subtitle, x1.g());
        if (VERSION.SDK_INT >= 24) {
            builder.setCustomContentView(remoteViews);
        }
        builder.setContentIntent(a(x0, "WakeEventReceiver.ACTION_NOTIFICATION_CLICKED", x4));
        return builder.build();
    }

    static void a(final Context context, final NotificationMeta notificationMeta, final NotificationStatusCallback callback) {
        FLog.i("NotificationHelper", "displayNotification");
        if (notificationMeta == null) {
            FLog.d("NotificationHelper", "displayNotification:Error, notification meta is null");
            callback.a(NotificationError.InvalidNotificationMeta);
            return;
        }
        String b;
        String notificationImageUri = ActivationOptions.NOTIFICATION_PLACEHOLDER_IMAGE_URL;
        boolean isDummyImageUri = true;
        if (notificationMeta.i() && !TextUtils.isEmpty(notificationMeta.e())) {
            notificationImageUri = notificationMeta.e();
            isDummyImageUri = false;
        }
        final boolean isDummyImageUriToEnsureNetwork = isDummyImageUri;
        if (notificationMeta.h() != null) {
            b = notificationMeta.h().b();
        } else {
            b = "";
        }
        new NotificationImageFetcher(context, notificationImageUri, b).a(new NotificationImageFetcherCallback() {
            public final void a(Bitmap bigPicture, Bitmap icon) {
                if (bigPicture == null) {
                    callback.a(NotificationError.NetworkError);
                    return;
                }
                Bitmap bigPictureBitmap;
                if (isDummyImageUriToEnsureNetwork) {
                    bigPictureBitmap = null;
                } else {
                    bigPictureBitmap = bigPicture;
                }
                int notificationId = a.b.nextInt();
                Notification notification = a.a(context, notificationMeta, icon, bigPictureBitmap, notificationId);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (!(notificationManager == null || notification == null)) {
                    notificationManager.notify(notificationId, notification);
                }
                callback.a(notificationId);
            }

            public final void a(a fetchError) {
                switch (AnonymousClass2.a[fetchError.ordinal()]) {
                    case 1:
                        callback.a(NotificationError.NetworkError);
                        return;
                    case 2:
                        callback.a(NotificationError.NetworkTimeout);
                        return;
                    case 3:
                        callback.a(NotificationError.InvalidImageUri);
                        return;
                    default:
                        callback.a(NotificationError.Unknown);
                        return;
                }
            }
        });
    }

    private static PendingIntent a(Context context, String action, int notificationId) {
        Intent launch = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launch == null) {
            return null;
        }
        launch.setAction(action);
        launch.putExtra("notificationId", notificationId);
        launch.addFlags(ErrorDialogData.DYNAMITE_CRASH);
        return PendingIntent.getActivity(context, notificationId, launch, 134217728);
    }

    private static Notification a(Context context, NotificationMeta notificationMeta, int id, Bitmap bigPictureBitmap, Bitmap iconBitmap) {
        q style;
        d builder = new d(context, "default");
        int colorArgb = android.support.v4.content.a.c(context, Utils.c(context, "sxBlue"));
        builder.a(Utils.a(context, "notification_icon"));
        builder.c(colorArgb);
        builder.a(notificationMeta.f());
        builder.b(notificationMeta.g());
        builder.d(notificationMeta.f());
        builder.b();
        if (iconBitmap != null) {
            builder.g = iconBitmap;
        }
        builder.a(a(context, "WakeEventReceiver.ACTION_NOTIFICATION_CLICKED", id));
        OEMNotificationStyle notificationStyle = OEMNotificationStyle.a(notificationMeta.d());
        if (notificationMeta.i()) {
            style = new b().a(bigPictureBitmap).b(notificationMeta.g());
        } else {
            style = new c().c(notificationMeta.g());
        }
        if (notificationMeta.a() != null) {
            for (NotificationAction action : notificationMeta.a()) {
                android.support.v4.app.NotificationCompat.a notificationAction;
                CharSequence a = action.a();
                if (!TextUtils.isEmpty(a)) {
                    Object obj;
                    if (action.b() == NotificationActionType.LearnMore) {
                        switch (notificationStyle) {
                            case TextWithEmojiCallAnyone:
                            case TextWithEmojiTalkWithWorld:
                                obj = "https://secure.skype.com/calling-rates";
                                break;
                            case TextOnlyFreeCallIndiaChatFriends:
                            case TextWithEmojiFreeCallIndiaStayConnected:
                            case TextIconCallUSMobLandBhangra:
                            case TextIconCallUSMobLandCallMe:
                                obj = "https://www.skype.com/en/offers/free-calls-from-india/";
                                break;
                            case TextOnlySendMoney:
                                obj = "https://support.skype.com/faq/FA34766/";
                                break;
                            case TextOnlySkypeTranslator:
                                obj = "https://support.skype.com/faq/FA34542/";
                                break;
                            case DynamicContent:
                                obj = action.c();
                                break;
                            default:
                                obj = null;
                                break;
                        }
                    }
                    obj = null;
                    if (!(obj == null || TextUtils.isEmpty(obj))) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(obj));
                        notificationAction = new android.support.v4.app.NotificationCompat.a(0, a, PendingIntent.getActivity(context.getApplicationContext(), 0, intent, 134217728));
                        if (notificationAction != null) {
                            builder.a(notificationAction);
                        }
                    }
                }
                notificationAction = null;
                if (notificationAction != null) {
                    builder.a(notificationAction);
                }
            }
        }
        builder.a(style);
        return builder.e();
    }

    static void a(Context context, ImageFetcherCallback callback) {
        FLog.i("NotificationHelper", "ensureNetwork with placeholder Image");
        new ImageFetcher(context, ActivationOptions.NOTIFICATION_PLACEHOLDER_IMAGE_URL).a(callback);
    }

    public static boolean a(Date startDate) {
        if (startDate != null) {
            return new Date().compareTo(startDate) < 0;
        } else {
            return false;
        }
    }

    public static boolean b(Date endDate) {
        if (endDate != null) {
            return new Date().compareTo(endDate) > 0;
        } else {
            return false;
        }
    }

    public static boolean a() {
        int hours = Calendar.getInstance().get(11);
        return ((long) hours) <= 9 || ((long) hours) >= 22;
    }

    public static String b() {
        if (((long) Calendar.getInstance().get(11)) <= 9) {
            return String.format(Locale.US, "Can't show notification before %d hour, current hour %d", new Object[]{Long.valueOf(9), Integer.valueOf(Calendar.getInstance().get(11))});
        }
        return String.format(Locale.US, "Can't show notification after %d hour", new Object[]{Long.valueOf(22)});
    }
}
