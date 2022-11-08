package com.facebook.ads.internal.e;

import android.support.annotation.Nullable;

abstract class f<T> {
    private a a;

    public enum a {
        UNKNOWN(9000, "An unknown error has occurred."),
        DATABASE_SELECT(3001, "Failed to read from database."),
        DATABASE_INSERT(3002, "Failed to insert row into database."),
        DATABASE_UPDATE(3003, "Failed to update row in database."),
        DATABASE_DELETE(3004, "Failed to delete row from database.");
        
        private final int f;
        private final String g;

        private a(int i, String str) {
            this.f = i;
            this.g = str;
        }

        public final int a() {
            return this.f;
        }

        public final String b() {
            return this.g;
        }
    }

    f() {
    }

    @Nullable
    abstract T a();

    protected final void a(a aVar) {
        this.a = aVar;
    }

    public final a b() {
        return this.a;
    }
}
