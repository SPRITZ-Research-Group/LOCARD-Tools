package kotlin.reflect.jvm.internal.impl.renderer;

import defpackage.addb;

public enum RenderingFormat {
    ;

    final class HTML extends RenderingFormat {
        HTML(String str, int i) {
            super(str, i, null);
        }

        public final String escape(String str) {
            return addb.a(addb.a(str, "<", "&lt;"), ">", "&gt;");
        }
    }

    final class PLAIN extends RenderingFormat {
        public final String escape(String str) {
            return str;
        }

        PLAIN(String str, int i) {
            super(str, i, null);
        }
    }

    public abstract String escape(String str);
}
