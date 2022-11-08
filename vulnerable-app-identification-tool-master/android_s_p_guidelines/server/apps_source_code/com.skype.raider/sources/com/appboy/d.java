package com.appboy;

import a.a.ag;
import a.a.ap;
import a.a.be;
import a.a.ck;
import a.a.cm;
import com.appboy.b.e;
import com.appboy.b.f;
import com.appboy.b.g;
import com.appboy.f.c;
import com.appboy.f.j;

public class d {
    private static final String a = c.a(d.class);
    private final cm b;
    private final ck c;
    private final Object d = new Object();
    private final ap e;
    private volatile String f;
    private final ag g;

    d(cm userCache, ag appboyManager, String userId, ap appboyLocationManager, ck serverConfigStorageProvider) {
        this.f = userId;
        this.b = userCache;
        this.e = appboyLocationManager;
        this.c = serverConfigStorageProvider;
        this.g = appboyManager;
    }

    public final boolean a(String firstName) {
        try {
            this.b.b(firstName);
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to set first name to: " + firstName, e);
            return false;
        }
    }

    public final boolean b(String lastName) {
        try {
            this.b.c(lastName);
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to set last name to: " + lastName, e);
            return false;
        }
    }

    public final boolean c(String email) {
        try {
            return this.b.d(email);
        } catch (Throwable e) {
            c.c(a, "Failed to set email to: " + email, e);
            return false;
        }
    }

    public final boolean a(e gender) {
        try {
            this.b.a(gender);
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to set gender to: " + gender, e);
            return false;
        }
    }

    public final boolean a(int year, f month, int day) {
        try {
            return this.b.a(year, month, day);
        } catch (Throwable e) {
            c.c(a, "Failed to set date of birth to: " + year + "-" + month.a() + "-" + day, e);
            return false;
        }
    }

    public final boolean d(String country) {
        try {
            this.b.e(country);
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to set country to: " + country, e);
            return false;
        }
    }

    public final boolean e(String homeCity) {
        try {
            this.b.f(homeCity);
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to set home city to: " + homeCity, e);
            return false;
        }
    }

    public final boolean f(String language) {
        try {
            this.b.g(language);
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to set language to: " + language, e);
            return false;
        }
    }

    public final boolean a(g emailNotificationSubscriptionType) {
        try {
            this.b.a(emailNotificationSubscriptionType);
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to set email notification subscription to: " + emailNotificationSubscriptionType, e);
            return false;
        }
    }

    public final boolean b(g pushNotificationSubscriptionType) {
        try {
            this.b.b(pushNotificationSubscriptionType);
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to set push notification subscription to: " + pushNotificationSubscriptionType, e);
            return false;
        }
    }

    public final boolean g(String phoneNumber) {
        try {
            return this.b.h(phoneNumber);
        } catch (Throwable e) {
            c.c(a, "Failed to set phone number to: " + phoneNumber, e);
            return false;
        }
    }

    public final boolean a(String key, boolean value) {
        try {
            return this.b.a(key, Boolean.valueOf(value));
        } catch (Throwable e) {
            c.c(a, "Failed to set custom boolean attribute " + key + ".", e);
            return false;
        }
    }

    public final boolean a(String key, int value) {
        try {
            return this.b.a(key, Integer.valueOf(value));
        } catch (Throwable e) {
            c.c(a, "Failed to set custom integer attribute " + key + ".", e);
            return false;
        }
    }

    public final boolean a(String key, float value) {
        try {
            return this.b.a(key, Float.valueOf(value));
        } catch (Throwable e) {
            c.c(a, "Failed to set custom float attribute " + key + ".", e);
            return false;
        }
    }

    public final boolean a(String key, String value) {
        try {
            return this.b.a(key, (Object) value);
        } catch (Throwable e) {
            c.c(a, "Failed to set custom string attribute " + key + ".", e);
            return false;
        }
    }

    public final boolean b(String key, String value) {
        try {
            if (!com.appboy.f.e.a(key, this.c.m())) {
                c.f(a, "Custom attribute key cannot be null.");
                return false;
            } else if (!com.appboy.f.e.a(value)) {
                return false;
            } else {
                return this.g.a(be.d(j.c(key), j.c(value)));
            }
        } catch (Throwable e) {
            c.c(a, "Failed to add custom attribute with key '" + key + "'.", e);
            return false;
        }
    }

    public final boolean c(String key, String value) {
        try {
            if (!com.appboy.f.e.a(key, this.c.m())) {
                c.f(a, "Custom attribute key cannot be null.");
                return false;
            } else if (!com.appboy.f.e.a(value)) {
                return false;
            } else {
                return this.g.a(be.e(j.c(key), j.c(value)));
            }
        } catch (Throwable e) {
            c.c(a, "Failed to remove custom attribute with key '" + key + "'.", e);
            return false;
        }
    }

    public final boolean a(String key, String[] values) {
        try {
            if (!com.appboy.f.e.a(key, this.c.m())) {
                return false;
            }
            key = j.c(key);
            if (values != null) {
                values = com.appboy.f.e.a(values);
            }
            return this.g.a(be.a(key, values));
        } catch (Exception e) {
            c.f(a, "Failed to set custom attribute array with key: '" + key + "'.");
            return false;
        }
    }

    public final boolean h(String key) {
        try {
            if (!com.appboy.f.e.a(key, this.c.m())) {
                return false;
            }
            return this.g.a(be.e(j.c(key)));
        } catch (Throwable e) {
            c.c(a, "Failed to increment custom attribute " + key + " by 1.", e);
            return false;
        }
    }

    public final String a() {
        String str;
        synchronized (this.d) {
            str = this.f;
        }
        return str;
    }

    final void i(String str) {
        synchronized (this.d) {
            if (this.f.equals("") || this.f.equals(str)) {
                this.f = str;
                this.b.a(str);
            } else {
                throw new IllegalArgumentException("setExternalId can not be used to change the external ID of a UserCache from a non-empty value to a new value. Was: [" + this.f + "], tried to change to: [" + str + "]");
            }
        }
    }
}
