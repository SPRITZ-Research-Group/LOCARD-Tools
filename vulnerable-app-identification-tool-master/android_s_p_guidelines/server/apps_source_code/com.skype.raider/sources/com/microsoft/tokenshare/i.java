package com.microsoft.tokenshare;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

final class i {
    private static final AtomicInteger a = new AtomicInteger(-1);

    static String a(@NonNull Context context, @NonNull String appId) {
        String defaultValue = "Unknown";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(appId, 128);
            if (applicationInfo.metaData != null) {
                return applicationInfo.metaData.getString("token_share_build_version", defaultValue);
            }
            return defaultValue;
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
    }

    static boolean b(@NonNull Context context, @NonNull String appId) {
        if (a.get() < 0) {
            a.set(e(context, context.getPackageName()));
        }
        return a.get() == e(context, appId);
    }

    static boolean c(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        List<String> packageSignatures = d(context, packageName);
        if (packageSignatures.size() > 0) {
            for (e eVar : c.a.d().a(context)) {
                if (Arrays.equals(packageSignatures.toArray(), eVar.a.toArray())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> d(Context context, String appId) throws NameNotFoundException {
        List<String> hexSignatures = new LinkedList();
        try {
            MessageDigest sha256digester = MessageDigest.getInstance("SHA256");
            Signature[] signatures = context.getPackageManager().getPackageInfo(appId, 64).signatures;
            StringBuilder stringBuilder = new StringBuilder();
            if (signatures.length == 0) {
                stringBuilder.append("getPackageSignature returned empty list for ").append(appId);
            }
            for (Signature signature : signatures) {
                String hexSignature = a(sha256digester.digest(signature.toByteArray()));
                hexSignatures.add(hexSignature);
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("Package ").append(appId).append(" is signed with ");
                } else {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(hexSignature);
            }
            h.a("PackageUtils");
        } catch (NoSuchAlgorithmException ignore) {
            h.a("PackageUtils", "SHA256 failure ", ignore);
        }
        return hexSignatures;
    }

    private static int e(Context context, String appId) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(appId, 128);
            if (applicationInfo.metaData != null) {
                return applicationInfo.metaData.getInt("token_share_sdk_version", -1);
            }
            return -1;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    private static String a(@NonNull byte[] input) {
        StringBuilder stringBuilder = new StringBuilder(input.length * 2);
        for (byte b : input) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(":");
            }
            stringBuilder.append(String.format(Locale.ROOT, "%02x", new Object[]{Integer.valueOf(b & 255)}));
        }
        return stringBuilder.toString();
    }
}
