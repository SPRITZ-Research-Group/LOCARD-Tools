package com.projectseptember.RNGL;

public final class a {
    String a;
    String b;
    String c;
    String d;
    Double e;

    public a(String format, String type, String filePath, Double quality, String filePathBackup) {
        this.a = format;
        this.b = type;
        this.c = filePath;
        this.e = quality;
        this.d = filePathBackup;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        a that = (a) o;
        if (this.a == null ? that.a != null : !this.a.equals(that.a)) {
            return false;
        }
        if (this.b == null ? that.b != null : !this.b.equals(that.b)) {
            return false;
        }
        if (this.c == null ? that.c != null : !this.c.equals(that.c)) {
            return false;
        }
        if (this.d == null ? that.d != null : !this.d.equals(that.d)) {
            return false;
        }
        if (this.e != null) {
            if (this.e.equals(that.e)) {
                return true;
            }
        } else if (that.e == null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.a != null) {
            result = this.a.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.c != null) {
            hashCode = this.c.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.e != null) {
            hashCode = this.e.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return i + hashCode;
    }
}
