package com.skype.react.activationExperiment.models;

import android.content.Context;
import com.skype.react.activationExperiment.Utils;
import java.util.List;

public class NotificationMeta {
    private List<NotificationAction> actions;
    private NotificationImageInfo imageInfo;
    private String imageStyle;
    private String imageUri;
    private String notificationStyle;
    private String notificationTemplateType;
    private String subtitle;
    private String title;

    public final List<NotificationAction> a() {
        return this.actions;
    }

    public final String c() {
        return this.notificationTemplateType;
    }

    public final String d() {
        return this.notificationStyle;
    }

    public final String e() {
        return this.imageUri;
    }

    public final String f() {
        return this.title;
    }

    public final String g() {
        return this.subtitle;
    }

    public final NotificationImageInfo h() {
        return this.imageInfo;
    }

    public final boolean i() {
        if (this.imageStyle == null || (!NotificationImageType.BigPicture1.name().equals(this.imageStyle) && !NotificationImageType.BigPicture2.name().equals(this.imageStyle) && !NotificationImageType.BigPicture3.name().equals(this.imageStyle) && !NotificationImageType.BigPicture4.name().equals(this.imageStyle) && !NotificationImageType.BigPictureDynamic.name().equals(this.imageStyle))) {
            return false;
        }
        return true;
    }

    public static int a(Context context) {
        return Utils.a(context, "notification_icon");
    }

    public static int b(Context context) {
        return Utils.a(context, "splash_logo_static");
    }

    public static CharSequence c(Context context) {
        return context.getResources().getText(Utils.b(context, "app_name"));
    }

    public final void b() {
        String a = NotificationImageType.BigPicture1.name().equals(this.imageStyle) ? "https://secure.skypeassets.com/content/dam/scom/app/notifications/upgrade_notification_1.jpg" : NotificationImageType.BigPicture2.name().equals(this.imageStyle) ? "https://secure.skypeassets.com/content/dam/scom/app/notifications/upgrade_notification_2.jpg" : NotificationImageType.BigPicture3.name().equals(this.imageStyle) ? "https://secure.skypeassets.com/content/dam/scom/app/notifications/upgrade_notification_3.jpg" : NotificationImageType.BigPicture4.name().equals(this.imageStyle) ? "https://secure.skypeassets.com/content/dam/scom/app/notifications/upgrade_notification_4.jpg" : NotificationImageType.BigPictureDynamic.name().equals(this.imageStyle) ? this.imageInfo != null ? this.imageInfo.a() : "" : "https://secure.skypeassets.com/content/dam/scom/app/notifications/ensure_network_image.png";
        this.imageUri = a;
    }
}
