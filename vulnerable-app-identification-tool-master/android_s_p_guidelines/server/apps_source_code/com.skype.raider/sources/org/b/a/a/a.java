package org.b.a.a;

public final class a {
    public static a[] a = new a[]{null, new a("load_str", b.STRING), new a("load_attr", b.STRING), new a("load_local", b.INT), new a("load_prop", b.STRING), new a("load_prop_ind"), new a("store_option", b.INT), new a("store_arg", b.STRING), new a("new", b.STRING, b.INT), new a("new_ind", b.INT), new a("new_box_args", b.STRING), new a("super_new", b.STRING, b.INT), new a("super_new_box_args", b.STRING), new a("write"), new a("write_opt"), new a("map"), new a("rot_map", b.INT), new a("zip_map", b.INT), new a("br", b.ADDR), new a("brf", b.ADDR), new a("options"), new a("args"), new a("passthru", b.STRING), null, new a("list"), new a("add"), new a("tostr"), new a("first"), new a("last"), new a("rest"), new a("trunc"), new a("strip"), new a("trim"), new a("length"), new a("strlen"), new a("reverse"), new a("not"), new a("or"), new a("and"), new a("indent", b.STRING), new a("dedent"), new a("newline"), new a("noop"), new a("pop"), new a("null"), new a("true"), new a("false"), new a("write_str", b.STRING), new a("write_local", b.INT)};

    public static class a {
        public String a;
        public b[] b;
        public int c;

        public a(String name) {
            this(name, b.NONE, b.NONE);
            this.c = 0;
        }

        public a(String name, b a) {
            this(name, a, b.NONE);
            this.c = 1;
        }

        public a(String name, b a, b b) {
            this.b = new b[2];
            this.c = 0;
            this.a = name;
            this.b[0] = a;
            this.b[1] = b;
            this.c = 2;
        }
    }

    public enum b {
        NONE,
        STRING,
        ADDR,
        INT
    }
}
