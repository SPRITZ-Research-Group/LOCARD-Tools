package com.facebook.react.animated;

import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.r;
import com.facebook.react.bridge.s;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.ArrayList;
import java.util.List;

final class p extends b {
    private final l e;
    private final List<c> f;

    private class c {
        public String c;
        final /* synthetic */ p d;

        private c(p pVar) {
            this.d = pVar;
        }

        /* synthetic */ c(p x0, byte b) {
            this(x0);
        }
    }

    private class a extends c {
        public int a;
        final /* synthetic */ p b;

        private a(p pVar) {
            this.b = pVar;
            super(pVar, (byte) 0);
        }

        /* synthetic */ a(p x0, byte b) {
            this(x0);
        }
    }

    private class b extends c {
        public double a;
        final /* synthetic */ p b;

        private b(p pVar) {
            this.b = pVar;
            super(pVar, (byte) 0);
        }

        /* synthetic */ b(p x0, byte b) {
            this(x0);
        }
    }

    p(am config, l nativeAnimatedNodesManager) {
        al transforms = config.getArray("transforms");
        this.f = new ArrayList(transforms.size());
        for (int i = 0; i < transforms.size(); i++) {
            am transformConfigMap = transforms.getMap(i);
            String property = transformConfigMap.getString("property");
            if (transformConfigMap.getString("type").equals("animated")) {
                a transformConfig = new a();
                transformConfig.c = property;
                transformConfig.a = transformConfigMap.getInt("nodeTag");
                this.f.add(transformConfig);
            } else {
                b transformConfig2 = new b();
                transformConfig2.c = property;
                transformConfig2.a = transformConfigMap.getDouble(PropertiesEntry.COLUMN_NAME_VALUE);
                this.f.add(transformConfig2);
            }
        }
        this.e = nativeAnimatedNodesManager;
    }

    public final void a(s propsMap) {
        List<s> transforms = new ArrayList(this.f.size());
        for (c transformConfig : this.f) {
            double value;
            if (transformConfig instanceof a) {
                b node = this.e.a(((a) transformConfig).a);
                if (node == null) {
                    throw new IllegalArgumentException("Mapped style node does not exists");
                } else if (node instanceof q) {
                    value = ((q) node).b();
                } else {
                    throw new IllegalArgumentException("Unsupported type of node used as a transform child node " + node.getClass());
                }
            }
            value = ((b) transformConfig).a;
            transforms.add(s.a(transformConfig.c, Double.valueOf(value)));
        }
        propsMap.putArray("transform", r.a(transforms));
    }
}
