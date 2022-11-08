package com.microsoft.react.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.d;
import android.support.v4.app.NotificationCompat.g;
import android.support.v4.app.NotificationCompat.h;
import android.support.v4.app.NotificationCompat.q;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.ac;
import android.support.v4.content.a;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.c;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.b;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

public final class f implements d {
    private static final AtomicInteger b = new AtomicInteger(1);
    private static final String c = f.class.getSimpleName();
    private static final Random d = new Random();
    private static c e;

    private f() {
    }

    public static int a(Context context) {
        int pushId = b.getAndIncrement();
        a(context, pushId, "com.microsoft.react.push.PushConstants.ACTION_START_PUSH_HANDLING", pushId);
        return pushId;
    }

    public static void a(Context context, int pushId) {
        a(context, pushId, "com.microsoft.react.push.PushConstants.ACTION_STOP_PUSH_HANDLING", pushId * -1);
    }

    public static synchronized void a(c actions) {
        synchronized (f.class) {
            e = actions;
        }
    }

    public static Bitmap a(Context context, a iconDetails) {
        Resources resources = context.getResources();
        int width = resources.getDimensionPixelSize(17104901);
        int height = resources.getDimensionPixelSize(17104902);
        boolean isGroup = iconDetails.a();
        String initials = iconDetails.d();
        int color = iconDetails.b();
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int cx = width / 2;
        int cy = height / 2;
        Paint bgPaint = new Paint();
        bgPaint.setColor(color);
        canvas.drawCircle((float) cx, (float) cy, (float) (Math.min(width, height) / 2), bgPaint);
        if (isGroup || TextUtils.isEmpty(initials)) {
            int identifier;
            if (isGroup) {
                identifier = resources.getIdentifier("avatar_group", "drawable", context.getPackageName());
            } else {
                identifier = resources.getIdentifier("avatar_user", "drawable", context.getPackageName());
            }
            Drawable image = a.a(context, identifier);
            image.setBounds(0, 0, width, height);
            image.draw(canvas);
        } else {
            Paint textPaint = new Paint(1);
            textPaint.setAlpha(76);
            textPaint.setColor(a.c(context, 17170443));
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
            textPaint.setTextSize((float) (height / 3));
            textPaint.setTextAlign(Align.CENTER);
            Rect textRect = new Rect();
            textPaint.getTextBounds(initials, 0, initials.length(), textRect);
            canvas.drawText(initials, (float) cx, (float) ((textRect.height() / 2) + cy), textPaint);
        }
        return output;
    }

    public static Bitmap a(Context context, Bitmap bitmap) {
        Resources resources = context.getResources();
        if (bitmap == null) {
            return null;
        }
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(a.c(context, i.a.grey));
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return Bitmap.createScaledBitmap(output, resources.getDimensionPixelSize(17104901), resources.getDimensionPixelSize(17104902), true);
    }

    private static String b(Context context) {
        return context.getString(context.getApplicationInfo().labelRes);
    }

    private static String a(String filename) {
        int pos = filename.lastIndexOf(".");
        if (pos > 0) {
            filename = filename.substring(0, pos);
        }
        return filename.toLowerCase();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(Context context, am details) {
        String id = details.hasKey("id") ? details.getString("id") : null;
        String category = details.hasKey("category") ? details.getString("category") : "msg";
        String soundName = details.hasKey("sound") ? details.getString("sound") : null;
        String message = details.getString("message");
        String messageThumbnailUrl = details.hasKey("messageThumbnailUrl") ? details.getString("messageThumbnailUrl") : null;
        String title = details.hasKey("title") ? details.getString("title") : b(context);
        int priority = details.hasKey(EventsEntry.COLUMN_NAME_PRIORITY) ? (int) details.getDouble(EventsEntry.COLUMN_NAME_PRIORITY) : 0;
        boolean enableFullScreenIncomingCall = details.hasKey("enableFullScreenIncomingCall") ? details.getBoolean("enableFullScreenIncomingCall") : false;
        al groupedNotifications = details.hasKey("groupedNotifications") ? details.getArray("groupedNotifications") : null;
        am serviceSpecificData = details.hasKey("serviceSpecificData") ? details.getMap("serviceSpecificData") : null;
        boolean shouldVibrate = details.hasKey("enableVibration") ? details.getBoolean("enableVibration") : false;
        boolean shouldShowLight = details.hasKey("enableLight") ? details.getBoolean("enableLight") : false;
        Long fireDateMillis = details.hasKey("fireDate") ? Long.valueOf((long) details.getDouble("fireDate")) : null;
        boolean enableExpandedNotifications = details.hasKey("enableExpandedNotifications") ? details.getBoolean("enableExpandedNotifications") : false;
        Long messageTimestamp = details.hasKey("messageTimestamp") ? Long.valueOf((long) details.getDouble("messageTimestamp")) : null;
        al previousMessages = details.hasKey("previousMessages") ? details.getArray("previousMessages") : null;
        String conversationTitle = details.hasKey("conversationTitle") ? details.getString("conversationTitle") : null;
        boolean isMessagingStyle = previousMessages != null && previousMessages.size() > 1;
        boolean shouldGroup = (isMessagingStyle || groupedNotifications == null || groupedNotifications.size() < (enableExpandedNotifications ? 1 : 2)) ? false : true;
        Resources resources = context.getResources();
        List<b> actions = null;
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        long now = System.currentTimeMillis();
        if (fireDateMillis == null || fireDateMillis.longValue() <= now) {
            boolean isFullScreenIncomingCall = enableFullScreenIncomingCall && "CallCategoryIdentifier".equalsIgnoreCase(category);
            boolean isAuthBackgroundRefresh = "AuthBackgroundRefreshIdentifier".equalsIgnoreCase(category);
            if (isFullScreenIncomingCall) {
                Intent launch = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                launch.setAction("LocalNotificationActionReceived");
                launch.addFlags(ErrorDialogData.DYNAMITE_CRASH);
                bundle.putString("identifier", "_default_");
                bundle.putBoolean("enableFullScreenIncomingCall", enableFullScreenIncomingCall);
                if (serviceSpecificData != null) {
                    bundle.putBundle("serviceSpecificData", b.a(serviceSpecificData));
                }
                launch.putExtras(bundle);
                context.startActivity(launch);
            } else if (!isAuthBackgroundRefresh) {
                String str;
                Object obj;
                int i;
                StringBuilder stringBuilder = new StringBuilder();
                if (soundName == null) {
                    str = "no_sound";
                } else {
                    str = soundName;
                }
                stringBuilder = stringBuilder.append(str);
                if (shouldVibrate) {
                    str = "_v";
                } else {
                    str = "";
                }
                String channelId = stringBuilder.append(str).toString();
                if (a()) {
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                    if (notificationManager == null) {
                        FLog.e(c, "Notification Manager is NULL! Can not create channel");
                    } else {
                        if ((notificationManager.getNotificationChannel(channelId) == null ? 1 : null) != null) {
                            NotificationChannel notificationChannel = new NotificationChannel(channelId, context.getString(i.b.notification_default_channel_name), 4);
                            if (soundName == null) {
                                notificationChannel.setSound(null, null);
                            } else {
                                notificationChannel.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + context.getResources().getIdentifier(a(soundName), "raw", context.getPackageName())), new Builder().setContentType(4).setUsage(5).build());
                            }
                            if (shouldVibrate) {
                                notificationChannel.setVibrationPattern(new long[]{0, 400, 100, 400});
                            } else {
                                notificationChannel.enableVibration(false);
                            }
                            notificationManager.createNotificationChannel(notificationChannel);
                        }
                    }
                }
                Resources resources2 = context.getResources();
                final d notificationBuilder = new d(context, channelId).b(category).a(resources2.getIdentifier("notification_icon", "drawable", context.getPackageName())).c(a.c(context, resources2.getIdentifier("sxBlue", "color", context.getPackageName()))).a(messageTimestamp == null ? System.currentTimeMillis() : messageTimestamp.longValue()).a(false).b().e(enableExpandedNotifications ? 2 : 0).a((CharSequence) title);
                if (!TextUtils.isEmpty(message)) {
                    notificationBuilder.b((CharSequence) message);
                }
                if (VERSION.SDK_INT >= 21) {
                    obj = -1;
                    switch (category.hashCode()) {
                        case -815680955:
                            if (category.equals("CallActionAcceptIdentifier")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 595162591:
                            if (category.equals("ChatCategoryIdentifier")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1188257820:
                            if (category.equals("CallActionRejectIdentifier")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 1374414693:
                            if (category.equals("CallCategoryIdentifier")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            str = "msg";
                            break;
                        case 1:
                            str = "call";
                            break;
                        case 2:
                        case 3:
                            str = "status";
                            break;
                        default:
                            str = "msg";
                            break;
                    }
                    notificationBuilder.a(str);
                }
                notificationBuilder.b(priority);
                if (shouldGroup) {
                    notificationBuilder.d();
                    q inboxStyle = new g();
                    if (enableExpandedNotifications) {
                        notificationBuilder.c((CharSequence) title);
                    } else {
                        notificationBuilder.i = groupedNotifications.size();
                        inboxStyle.a((CharSequence) "");
                    }
                    for (i = 0; i < groupedNotifications.size(); i++) {
                        am localNotificationMap = groupedNotifications.getMap(i);
                        if (localNotificationMap != null) {
                            CharSequence string;
                            String body = localNotificationMap.getString("message");
                            String topic = localNotificationMap.getString("title");
                            if (TextUtils.isEmpty(body)) {
                                obj = topic;
                            } else {
                                string = resources.getString(i.b.text_format_message, new Object[]{topic, body});
                            }
                            CharSequence spannableStringBuilder = new SpannableStringBuilder(string);
                            spannableStringBuilder.setSpan(new StyleSpan(1), 0, topic.length(), 0);
                            inboxStyle.b(spannableStringBuilder);
                        }
                    }
                    notificationBuilder.a(inboxStyle);
                } else {
                    if (serviceSpecificData != null) {
                        bundle.putBundle("serviceSpecificData", b.a(serviceSpecificData));
                    }
                    if (e != null) {
                        actions = e.a(category);
                    }
                }
                String definitiveId = ((!enableExpandedNotifications || shouldGroup) && "ChatCategoryIdentifier".equalsIgnoreCase(category)) ? category : id != null ? id : Integer.toString(d.nextInt());
                int notificationId = definitiveId.hashCode();
                if (!enableExpandedNotifications) {
                    serviceSpecificData = null;
                }
                Bundle bundle2 = new Bundle();
                Intent intent = new Intent(context, PushReceiver.class);
                intent.setAction("DeleteNotificationActionReceived");
                bundle2.putString("category", "ChatCategoryIdentifier");
                if (serviceSpecificData != null) {
                    bundle2.putString("identifier", "DeleteNotificationIdentifier");
                    bundle2.putBundle("serviceSpecificData", b.a(serviceSpecificData));
                }
                intent.putExtras(bundle2);
                PendingIntent deleteIntent = PendingIntent.getBroadcast(context, notificationId, intent, ErrorDialogData.BINDER_CRASH);
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                launchIntentForPackage.setAction("LocalNotificationActionReceived");
                launchIntentForPackage.addFlags(ErrorDialogData.DYNAMITE_CRASH);
                bundle.putString("identifier", "_default_");
                launchIntentForPackage.putExtras(bundle);
                notificationBuilder.a(PendingIntent.getActivity(context, notificationId, launchIntentForPackage, 134217728));
                notificationBuilder.b(deleteIntent);
                if (actions != null) {
                    for (b action : actions) {
                        int identifier;
                        PendingIntent activity;
                        NotificationCompat.a androidAction;
                        if (enableExpandedNotifications) {
                            str = definitiveId + action.a();
                        } else {
                            str = category + action.a();
                        }
                        int reqCode = str.hashCode();
                        Bundle bundle3 = new Bundle(bundle);
                        bundle3.putString("identifier", action.a());
                        bundle3.putInt("notificationId", notificationId);
                        String f = action.f();
                        Resources resources3 = context.getResources();
                        if (f != null) {
                            identifier = resources3.getIdentifier(f, "drawable", context.getPackageName());
                        } else {
                            identifier = 0;
                        }
                        if (action.c() == a.FOREGROUND) {
                            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                            launchIntentForPackage.setAction("LocalNotificationActionReceived");
                            launchIntentForPackage.addFlags(ErrorDialogData.DYNAMITE_CRASH);
                            launchIntentForPackage.putExtras(bundle3);
                            activity = PendingIntent.getActivity(context, reqCode, launchIntentForPackage, 134217728);
                        } else if (action.c() == a.BACKGROUND) {
                            launchIntentForPackage = new Intent(context, BackgroundActionService.class);
                            launchIntentForPackage.putExtras(bundle3);
                            activity = PendingIntent.getService(context, reqCode, launchIntentForPackage, 134217728);
                        } else {
                            androidAction = null;
                            if (androidAction != null) {
                                notificationBuilder.a(androidAction);
                            }
                        }
                        NotificationCompat.a.a aVar = new NotificationCompat.a.a(identifier, action.b(), activity);
                        if (action.e() == b.TEXT_INPUT && "ChatActionReplyIdentifier".equals(action.a())) {
                            aVar.a(new ac.a("key_text_reply").a(action.d()).a());
                        }
                        androidAction = aVar.a();
                        if (androidAction != null) {
                            notificationBuilder.a(androidAction);
                        }
                    }
                }
                if (soundName != null) {
                    if (!a()) {
                        notificationBuilder.a(Uri.parse("android.resource://" + context.getPackageName() + "/" + resources.getIdentifier(a(soundName), "raw", context.getPackageName())));
                    }
                    notificationBuilder.d(resources.getString(i.b.ticker_format_message, new Object[]{b(context), title, message}));
                }
                if (!a()) {
                    if (shouldVibrate) {
                        notificationBuilder.a(new long[]{0, 400, 100, 400});
                    } else {
                        notificationBuilder.a(new long[]{0});
                    }
                }
                if (shouldShowLight) {
                    int i2;
                    int colorArgb = a.c(context, resources.getIdentifier("sxBlue", "color", context.getPackageName()));
                    int onMs = resources.getInteger(resources.getIdentifier("config_defaultNotificationLedOn", "integer", "android"));
                    int offMs = resources.getInteger(resources.getIdentifier("config_defaultNotificationLedOff", "integer", "android"));
                    notificationBuilder.L.ledARGB = colorArgb;
                    notificationBuilder.L.ledOnMS = onMs;
                    notificationBuilder.L.ledOffMS = offMs;
                    obj = (notificationBuilder.L.ledOnMS == 0 || notificationBuilder.L.ledOffMS == 0) ? null : 1;
                    Notification notification = notificationBuilder.L;
                    int i3 = notificationBuilder.L.flags & -2;
                    if (obj != null) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    notification.flags = i2 | i3;
                }
                if (isMessagingStyle || !shouldGroup) {
                    final c<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> dataSource;
                    final Context context2;
                    boolean fetchMessageFromNetwork;
                    q hVar;
                    am previousMessageMap;
                    q bigTextStyle;
                    final Context context3 = context;
                    final int i4 = notificationId;
                    final com.microsoft.react.push.b.b completion = new com.microsoft.react.push.b.b(new com.microsoft.react.push.c.a() {
                        public final void a() {
                            f.b(context3, i4, notificationBuilder);
                        }
                    });
                    final a iconDetails = new a(details);
                    String iconUri = iconDetails.c();
                    if (iconUri != null) {
                        if (!iconUri.startsWith("http://")) {
                        }
                        obj = 1;
                        if (obj == null) {
                            dataSource = Fresco.b().a(com.facebook.imagepipeline.k.c.a(Uri.parse(iconUri)).q(), (Object) context);
                            context2 = context;
                            dataSource.a(new com.facebook.imagepipeline.e.b() {
                                protected final void a(@Nullable Bitmap bitmap) {
                                    notificationBuilder.g = f.a(context2, bitmap);
                                    completion.b();
                                    dataSource.h();
                                }

                                protected final void f(c<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> dataSource) {
                                    if (dataSource != null) {
                                        dataSource.h();
                                    }
                                    notificationBuilder.g = f.a(context2, iconDetails);
                                    completion.b();
                                }
                            }, com.facebook.common.b.a.a());
                        } else {
                            notificationBuilder.g = a(context, iconDetails);
                            completion.b();
                        }
                        if (messageThumbnailUrl != null) {
                            if (!messageThumbnailUrl.startsWith("http://")) {
                            }
                            fetchMessageFromNetwork = true;
                            if (!isMessagingStyle) {
                                hVar = new h("You");
                                for (i = 0; i < previousMessages.size(); i++) {
                                    previousMessageMap = previousMessages.getMap(i);
                                    if (previousMessageMap == null) {
                                        hVar.a(previousMessageMap.getString("message"), Long.valueOf((long) previousMessageMap.getDouble("receivedTime")).longValue(), previousMessageMap.getString("userDisplayName"));
                                    }
                                }
                                if (conversationTitle != null) {
                                    hVar.a((CharSequence) conversationTitle);
                                }
                                notificationBuilder.a(hVar);
                                completion.a();
                                return;
                            } else if (fetchMessageFromNetwork) {
                                bigTextStyle = new NotificationCompat.c();
                                bigTextStyle.c(message);
                                notificationBuilder.a(bigTextStyle);
                                completion.a();
                                return;
                            } else {
                                dataSource = Fresco.b().a(com.facebook.imagepipeline.k.c.a(Uri.parse(messageThumbnailUrl)).q(), (Object) context);
                                dataSource.a(new com.facebook.imagepipeline.e.b() {
                                    protected final void a(@Nullable Bitmap bitmap) {
                                        if (dataSource.b() && bitmap != null) {
                                            q bigPictureStyle = new NotificationCompat.b();
                                            bigPictureStyle.a(bitmap);
                                            notificationBuilder.a(bigPictureStyle);
                                            completion.a();
                                            dataSource.h();
                                        }
                                    }

                                    protected final void f(c<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> dataSource) {
                                        if (dataSource != null) {
                                            dataSource.h();
                                        }
                                        completion.a();
                                        FLog.i(f.c, "Failed to fetch the image from network for notification.");
                                    }
                                }, com.facebook.common.b.a.a());
                                return;
                            }
                        }
                        fetchMessageFromNetwork = false;
                        if (!isMessagingStyle) {
                            hVar = new h("You");
                            for (i = 0; i < previousMessages.size(); i++) {
                                previousMessageMap = previousMessages.getMap(i);
                                if (previousMessageMap == null) {
                                    hVar.a(previousMessageMap.getString("message"), Long.valueOf((long) previousMessageMap.getDouble("receivedTime")).longValue(), previousMessageMap.getString("userDisplayName"));
                                }
                            }
                            if (conversationTitle != null) {
                                hVar.a((CharSequence) conversationTitle);
                            }
                            notificationBuilder.a(hVar);
                            completion.a();
                            return;
                        } else if (fetchMessageFromNetwork) {
                            bigTextStyle = new NotificationCompat.c();
                            bigTextStyle.c(message);
                            notificationBuilder.a(bigTextStyle);
                            completion.a();
                            return;
                        } else {
                            dataSource = Fresco.b().a(com.facebook.imagepipeline.k.c.a(Uri.parse(messageThumbnailUrl)).q(), (Object) context);
                            dataSource.a(/* anonymous class already generated */, com.facebook.common.b.a.a());
                            return;
                        }
                    }
                    obj = null;
                    if (obj == null) {
                        notificationBuilder.g = a(context, iconDetails);
                        completion.b();
                    } else {
                        dataSource = Fresco.b().a(com.facebook.imagepipeline.k.c.a(Uri.parse(iconUri)).q(), (Object) context);
                        context2 = context;
                        dataSource.a(/* anonymous class already generated */, com.facebook.common.b.a.a());
                    }
                    if (messageThumbnailUrl != null) {
                        if (messageThumbnailUrl.startsWith("http://")) {
                        }
                        fetchMessageFromNetwork = true;
                        if (!isMessagingStyle) {
                            hVar = new h("You");
                            for (i = 0; i < previousMessages.size(); i++) {
                                previousMessageMap = previousMessages.getMap(i);
                                if (previousMessageMap == null) {
                                    hVar.a(previousMessageMap.getString("message"), Long.valueOf((long) previousMessageMap.getDouble("receivedTime")).longValue(), previousMessageMap.getString("userDisplayName"));
                                }
                            }
                            if (conversationTitle != null) {
                                hVar.a((CharSequence) conversationTitle);
                            }
                            notificationBuilder.a(hVar);
                            completion.a();
                            return;
                        } else if (fetchMessageFromNetwork) {
                            dataSource = Fresco.b().a(com.facebook.imagepipeline.k.c.a(Uri.parse(messageThumbnailUrl)).q(), (Object) context);
                            dataSource.a(/* anonymous class already generated */, com.facebook.common.b.a.a());
                            return;
                        } else {
                            bigTextStyle = new NotificationCompat.c();
                            bigTextStyle.c(message);
                            notificationBuilder.a(bigTextStyle);
                            completion.a();
                            return;
                        }
                    }
                    fetchMessageFromNetwork = false;
                    if (!isMessagingStyle) {
                        hVar = new h("You");
                        for (i = 0; i < previousMessages.size(); i++) {
                            previousMessageMap = previousMessages.getMap(i);
                            if (previousMessageMap == null) {
                                hVar.a(previousMessageMap.getString("message"), Long.valueOf((long) previousMessageMap.getDouble("receivedTime")).longValue(), previousMessageMap.getString("userDisplayName"));
                            }
                        }
                        if (conversationTitle != null) {
                            hVar.a((CharSequence) conversationTitle);
                        }
                        notificationBuilder.a(hVar);
                        completion.a();
                        return;
                    } else if (fetchMessageFromNetwork) {
                        bigTextStyle = new NotificationCompat.c();
                        bigTextStyle.c(message);
                        notificationBuilder.a(bigTextStyle);
                        completion.a();
                        return;
                    } else {
                        dataSource = Fresco.b().a(com.facebook.imagepipeline.k.c.a(Uri.parse(messageThumbnailUrl)).q(), (Object) context);
                        dataSource.a(/* anonymous class already generated */, com.facebook.common.b.a.a());
                        return;
                    }
                }
                b(context, notificationId, notificationBuilder);
            } else if (id == null) {
                throw new IllegalArgumentException("Notification Id can't be null for the background auth refresh notification");
            } else {
                Intent intent2 = new Intent(context, PushReceiver.class);
                intent2.setAction("LocalNotificationActionReceived");
                intent2.putExtras(bundle);
                context.sendBroadcast(intent2);
            }
        } else if (id == null) {
            throw new IllegalArgumentException("Notification Id can't be null when fireDate is specified.");
        } else {
            ScheduledNotificationReceiver.a(context, id, fireDateMillis.longValue(), details);
        }
    }

    public static boolean a() {
        return VERSION.SDK_INT >= 26;
    }

    private static void b(Context context, int notificationId, d notificationBuilder) {
        NotificationManagerCompat.from(context).notify(notificationId, notificationBuilder.e());
    }

    private static Integer a(JobScheduler jobScheduler, Class theClass) {
        for (JobInfo job : jobScheduler.getAllPendingJobs()) {
            if (job.getService().getClassName().equalsIgnoreCase(theClass.getName())) {
                return Integer.valueOf(job.getId());
            }
        }
        return null;
    }

    private static void a(Context context, int pushId, String action, int jobId) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        FLog.i(c, "Initial state - " + "Dump jobs:");
        List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
        if (allPendingJobs != null) {
            for (JobInfo jobInfo : allPendingJobs) {
                FLog.i(c, "Job: " + jobInfo.getId() + " " + jobInfo.getService().getClassName());
            }
        }
        while (true) {
            Integer existingJobId = a(jobScheduler, PushHandlingService.class);
            if (existingJobId != null) {
                FLog.i(c, "Will cancel existing job " + existingJobId + " before replacing it with a new one.");
                jobScheduler.cancel(existingJobId.intValue());
            } else {
                PersistableBundle bundle = new PersistableBundle();
                bundle.putString("com.microsoft.react.push.PushConstants.ACTION", action);
                bundle.putInt("com.microsoft.react.push.PushConstants.extra.pushId", pushId);
                bundle.putLong("com.microsoft.react.push.PushConstants.extra.pushHandlingLifetime", PushHandlingService.a);
                jobScheduler.schedule(new JobInfo.Builder(jobId, new ComponentName(context, PushHandlingService.class)).setExtras(bundle).setOverrideDeadline(100).build());
                return;
            }
        }
    }
}
