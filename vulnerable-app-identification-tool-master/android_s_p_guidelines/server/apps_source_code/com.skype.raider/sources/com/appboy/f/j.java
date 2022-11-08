package com.appboy.f;

import a.a.ck;
import android.support.annotation.NonNull;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Set;

public final class j {
    private static final String a = c.a(j.class);

    public static boolean a(String emailAddress) {
        return emailAddress != null && emailAddress.toLowerCase(Locale.US).matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public static boolean b(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^[0-9 .\\(\\)\\+\\-]+$");
    }

    public static String c(String field) {
        field = field.trim();
        if (field.length() <= 255) {
            return field;
        }
        c.f(a, "Provided string field is too long [" + field.length() + "]. The max length is 255, truncating provided field.");
        return field.substring(0, 255);
    }

    public static boolean a(String productId, @NonNull String currencyCode, BigDecimal price, int quantity, ck serverConfigStorageProvider, Set<String> validCurrencyCodes) {
        if (i.c(productId)) {
            c.f(a, "The productId is empty, not logging in-app purchase to Appboy.");
            return false;
        } else if (serverConfigStorageProvider.n().contains(productId)) {
            c.f(a, "The productId is a blacklisted productId: " + productId);
            return false;
        } else if (currencyCode == null) {
            c.f(a, "The currencyCode is null. Expected one of " + validCurrencyCodes);
            return false;
        } else if (!validCurrencyCodes.contains(currencyCode)) {
            c.f(a, "The currencyCode " + currencyCode + " is invalid. Expected one of " + validCurrencyCodes);
            return false;
        } else if (price == null) {
            c.f(a, "The price is null.");
            return false;
        } else if (quantity <= 0) {
            c.f(a, "The requested purchase quantity of " + quantity + " is less than zero.");
            return false;
        } else if (quantity <= 100) {
            return true;
        } else {
            c.f(a, "The requested purchase quantity of " + quantity + " is greater than the maximum of 100");
            return false;
        }
    }

    public static boolean a(String eventName, ck serverConfigStorageProvider) {
        if (i.c(eventName)) {
            c.f(a, "The custom event name cannot be null or contain only whitespaces. Invalid custom event.");
            return false;
        } else if (!serverConfigStorageProvider.l().contains(eventName)) {
            return true;
        } else {
            c.f(a, "The custom event is a blacklisted custom event: " + eventName + ". Invalid custom event.");
            return false;
        }
    }

    public static boolean a(String campaignId, String pageId) {
        if (i.c(campaignId)) {
            c.f(a, "Campaign ID cannot be null or blank");
            return false;
        } else if (!i.c(pageId)) {
            return true;
        } else {
            c.f(a, "Push story page ID cannot be null or blank");
            return false;
        }
    }
}
