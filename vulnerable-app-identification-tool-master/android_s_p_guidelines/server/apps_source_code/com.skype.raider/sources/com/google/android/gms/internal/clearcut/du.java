package com.google.android.gms.internal.clearcut;

public final class du {

    public static final class a extends ah<a, a> implements bm {
        private static volatile bu<a> zzbg;
        private static final a zztx = new a();
        private int zzbb;
        private int zztu;
        private String zztv = "";
        private String zztw = "";

        public static final class a extends com.google.android.gms.internal.clearcut.ah.a<a, a> implements bm {
            private a() {
                super(a.zztx);
            }

            /* synthetic */ a(byte b) {
                this();
            }
        }

        static {
            ah.a(a.class, zztx);
        }

        private a() {
        }

        protected final Object b(int i) {
            switch (dv.a[i - 1]) {
                case 1:
                    return new a();
                case 2:
                    return new a();
                case 3:
                    Object[] objArr = new Object[]{"zzbb", "zztu", "zztv", "zztw"};
                    return ah.a(zztx, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\b\u0002", objArr);
                case 4:
                    return zztx;
                case 5:
                    bu buVar = zzbg;
                    if (buVar != null) {
                        return buVar;
                    }
                    Object obj;
                    synchronized (a.class) {
                        obj = zzbg;
                        if (obj == null) {
                            obj = new com.google.android.gms.internal.clearcut.ah.b(zztx);
                            zzbg = obj;
                        }
                    }
                    return obj;
                case 6:
                    return Byte.valueOf((byte) 1);
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class b extends ah<b, a> implements bm {
        private static final b zzbfc = new b();
        private static volatile bu<b> zzbg;
        private int zzbb;
        private int zzbfa = -1;
        private int zzbfb;

        public static final class a extends com.google.android.gms.internal.clearcut.ah.a<b, a> implements bm {
            private a() {
                super(b.zzbfc);
            }

            /* synthetic */ a(byte b) {
                this();
            }
        }

        public enum b implements ak {
            UNKNOWN_MOBILE_SUBTYPE(0),
            GPRS(1),
            EDGE(2),
            UMTS(3),
            CDMA(4),
            EVDO_0(5),
            EVDO_A(6),
            RTT(7),
            HSDPA(8),
            HSUPA(9),
            HSPA(10),
            IDEN(11),
            EVDO_B(12),
            LTE(13),
            EHRPD(14),
            HSPAP(15),
            GSM(16),
            TD_SCDMA(17),
            IWLAN(18),
            LTE_CA(19),
            COMBINED(100);
            
            private static final al<b> v = null;
            private final int w;

            static {
                v = new dw();
            }

            private b(int i) {
                this.w = i;
            }

            public static al<b> b() {
                return v;
            }

            public final int a() {
                return this.w;
            }
        }

        public enum c implements ak {
            NONE(-1),
            MOBILE(0),
            WIFI(1),
            MOBILE_MMS(2),
            MOBILE_SUPL(3),
            MOBILE_DUN(4),
            MOBILE_HIPRI(5),
            WIMAX(6),
            BLUETOOTH(7),
            DUMMY(8),
            ETHERNET(9),
            MOBILE_FOTA(10),
            MOBILE_IMS(11),
            MOBILE_CBS(12),
            WIFI_P2P(13),
            MOBILE_IA(14),
            MOBILE_EMERGENCY(15),
            PROXY(16),
            VPN(17);
            
            private static final al<c> t = null;
            private final int u;

            static {
                t = new dx();
            }

            private c(int i) {
                this.u = i;
            }

            public static al<c> b() {
                return t;
            }

            public final int a() {
                return this.u;
            }
        }

        static {
            ah.a(b.class, zzbfc);
        }

        private b() {
        }

        protected final Object b(int i) {
            switch (dv.a[i - 1]) {
                case 1:
                    return new b();
                case 2:
                    return new a();
                case 3:
                    Object[] objArr = new Object[]{"zzbb", "zzbfa", c.b(), "zzbfb", b.b()};
                    return ah.a(zzbfc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001", objArr);
                case 4:
                    return zzbfc;
                case 5:
                    bu buVar = zzbg;
                    if (buVar != null) {
                        return buVar;
                    }
                    Object obj;
                    synchronized (b.class) {
                        obj = zzbg;
                        if (obj == null) {
                            obj = new com.google.android.gms.internal.clearcut.ah.b(zzbfc);
                            zzbg = obj;
                        }
                    }
                    return obj;
                case 6:
                    return Byte.valueOf((byte) 1);
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
