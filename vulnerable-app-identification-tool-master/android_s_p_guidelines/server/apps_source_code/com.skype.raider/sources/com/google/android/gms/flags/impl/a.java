package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;

public abstract class a<T> {

    public static class a extends a<Boolean> {
        public static Boolean a(SharedPreferences sharedPreferences, String str, Boolean bool) {
            try {
                return (Boolean) com.google.android.gms.flags.impl.a.a.a(new c(sharedPreferences, str, bool));
            } catch (Exception e) {
                String str2 = "Flag value not available, returning default: ";
                String valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() != 0) {
                    str2.concat(valueOf);
                } else {
                    valueOf = new String(str2);
                }
                return bool;
            }
        }
    }

    public static class b extends a<Integer> {
        public static Integer a(SharedPreferences sharedPreferences, String str, Integer num) {
            try {
                return (Integer) com.google.android.gms.flags.impl.a.a.a(new d(sharedPreferences, str, num));
            } catch (Exception e) {
                String str2 = "Flag value not available, returning default: ";
                String valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() != 0) {
                    str2.concat(valueOf);
                } else {
                    valueOf = new String(str2);
                }
                return num;
            }
        }
    }

    public static class c extends a<Long> {
        public static Long a(SharedPreferences sharedPreferences, String str, Long l) {
            try {
                return (Long) com.google.android.gms.flags.impl.a.a.a(new e(sharedPreferences, str, l));
            } catch (Exception e) {
                String str2 = "Flag value not available, returning default: ";
                String valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() != 0) {
                    str2.concat(valueOf);
                } else {
                    valueOf = new String(str2);
                }
                return l;
            }
        }
    }

    public static class d extends a<String> {
        public static String a(SharedPreferences sharedPreferences, String str, String str2) {
            try {
                return (String) com.google.android.gms.flags.impl.a.a.a(new f(sharedPreferences, str, str2));
            } catch (Exception e) {
                String str3 = "Flag value not available, returning default: ";
                String valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() != 0) {
                    str3.concat(valueOf);
                } else {
                    valueOf = new String(str3);
                }
                return str2;
            }
        }
    }
}
