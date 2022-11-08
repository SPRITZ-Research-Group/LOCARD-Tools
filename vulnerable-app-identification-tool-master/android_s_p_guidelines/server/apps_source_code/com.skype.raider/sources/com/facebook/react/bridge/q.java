package com.facebook.react.bridge;

import com.facebook.systrace.b;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public final class q implements com.facebook.react.bridge.NativeModule.a {
    private static final a<Boolean> a = new a<Boolean>() {
        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return Boolean.valueOf(readableNativeArray.getBoolean(i));
        }
    };
    private static final a<Double> b = new a<Double>() {
        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return Double.valueOf(readableNativeArray.getDouble(i));
        }
    };
    private static final a<Float> c = new a<Float>() {
        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return Float.valueOf((float) readableNativeArray.getDouble(i));
        }
    };
    private static final a<Integer> d = new a<Integer>() {
        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return Integer.valueOf((int) readableNativeArray.getDouble(i));
        }
    };
    private static final a<String> e = new a<String>() {
        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return readableNativeArray.getString(i);
        }
    };
    private static final a<ReadableNativeArray> f = new a<ReadableNativeArray>() {
        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return readableNativeArray.getArray(i);
        }
    };
    private static final a<g> g = new a<g>() {
        public final /* bridge */ /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return h.a(readableNativeArray, i);
        }
    };
    private static final a<am> h = new a<am>() {
        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return readableNativeArray.getMap(i);
        }
    };
    private static final a<d> i = new a<d>() {
        @Nullable
        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            if (readableNativeArray.isNull(i)) {
                return null;
            }
            return new e(pVar, (int) readableNativeArray.getDouble(i));
        }
    };
    private static final a<ae> j = new a<ae>() {
        public final int a() {
            return 2;
        }

        public final /* synthetic */ Object a(p pVar, ReadableNativeArray readableNativeArray, int i) {
            return new af((d) q.i.a(pVar, readableNativeArray, i), (d) q.i.a(pVar, readableNativeArray, i + 1));
        }
    };
    private final Method k;
    private final Class[] l;
    private final int m;
    private final JavaModuleWrapper n;
    private final ak o;
    private String p = BaseJavaModule.METHOD_TYPE_ASYNC;
    private boolean q = false;
    @Nullable
    private a[] r;
    @Nullable
    private String s;
    @Nullable
    private Object[] t;
    @Nullable
    private int u;

    private static abstract class a<T> {
        @Nullable
        public abstract T a(p pVar, ReadableNativeArray readableNativeArray, int i);

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public int a() {
            return 1;
        }
    }

    private static char a(Class typeClass) {
        if (typeClass == Boolean.TYPE) {
            return 'z';
        }
        if (typeClass == Boolean.class) {
            return 'Z';
        }
        if (typeClass == Integer.TYPE) {
            return 'i';
        }
        if (typeClass == Integer.class) {
            return 'I';
        }
        if (typeClass == Double.TYPE) {
            return 'd';
        }
        if (typeClass == Double.class) {
            return 'D';
        }
        if (typeClass == Float.TYPE) {
            return 'f';
        }
        if (typeClass == Float.class) {
            return 'F';
        }
        if (typeClass == String.class) {
            return 'S';
        }
        return 0;
    }

    public q(JavaModuleWrapper module, Method method, boolean isSync) {
        this.n = module;
        this.k = method;
        this.k.setAccessible(true);
        this.l = this.k.getParameterTypes();
        this.m = this.l.length;
        this.o = new ak();
        if (isSync) {
            this.p = BaseJavaModule.METHOD_TYPE_SYNC;
        } else if (this.m > 0 && this.l[this.m - 1] == ae.class) {
            this.p = BaseJavaModule.METHOD_TYPE_PROMISE;
        }
    }

    private void d() {
        if (!this.q) {
            b.a();
            new StringBuilder().append(this.n.getName()).append(".").append(this.k.getName());
            try {
                this.q = true;
                this.r = a(this.l);
                this.s = a(this.k, this.l, this.p.equals(BaseJavaModule.METHOD_TYPE_SYNC));
                this.t = new Object[this.l.length];
                this.u = e();
            } finally {
                b.b();
            }
        }
    }

    public final String a() {
        if (!this.q) {
            d();
        }
        return (String) com.facebook.infer.annotation.a.a(this.s);
    }

    private static String a(Method method, Class[] paramTypes, boolean isSync) {
        char a;
        StringBuilder builder = new StringBuilder(paramTypes.length + 2);
        if (isSync) {
            Class returnType = method.getReturnType();
            a = a(returnType);
            if (a == 0) {
                if (returnType == Void.TYPE) {
                    a = 'v';
                } else if (returnType == ar.class) {
                    a = 'M';
                } else if (returnType == aq.class) {
                    a = 'A';
                } else {
                    throw new RuntimeException("Got unknown return class: " + returnType.getSimpleName());
                }
            }
            builder.append(a);
            builder.append('.');
        } else {
            builder.append("v.");
        }
        for (int i = 0; i < paramTypes.length; i++) {
            Class paramClass = paramTypes[i];
            if (paramClass == ae.class) {
                boolean z;
                if (i == paramTypes.length - 1) {
                    z = true;
                } else {
                    z = false;
                }
                com.facebook.infer.annotation.a.a(z, "Promise must be used as last parameter only");
            }
            a = a(paramClass);
            if (a == 0) {
                if (paramClass == d.class) {
                    a = 'X';
                } else if (paramClass == ae.class) {
                    a = 'P';
                } else if (paramClass == am.class) {
                    a = 'M';
                } else if (paramClass == al.class) {
                    a = 'A';
                } else if (paramClass == g.class) {
                    a = 'Y';
                } else {
                    throw new RuntimeException("Got unknown param class: " + paramClass.getSimpleName());
                }
            }
            builder.append(a);
        }
        return builder.toString();
    }

    private static a[] a(Class[] paramTypes) {
        a[] argumentExtractors = new a[paramTypes.length];
        int i = 0;
        while (i < paramTypes.length) {
            Class argumentClass = paramTypes[i];
            if (argumentClass == Boolean.class || argumentClass == Boolean.TYPE) {
                argumentExtractors[i] = a;
            } else if (argumentClass == Integer.class || argumentClass == Integer.TYPE) {
                argumentExtractors[i] = d;
            } else if (argumentClass == Double.class || argumentClass == Double.TYPE) {
                argumentExtractors[i] = b;
            } else if (argumentClass == Float.class || argumentClass == Float.TYPE) {
                argumentExtractors[i] = c;
            } else if (argumentClass == String.class) {
                argumentExtractors[i] = e;
            } else if (argumentClass == d.class) {
                argumentExtractors[i] = i;
            } else if (argumentClass == ae.class) {
                argumentExtractors[i] = j;
                com.facebook.infer.annotation.a.a(i == paramTypes.length + -1, "Promise must be used as last parameter only");
            } else if (argumentClass == am.class) {
                argumentExtractors[i] = h;
            } else if (argumentClass == al.class) {
                argumentExtractors[i] = f;
            } else if (argumentClass == g.class) {
                argumentExtractors[i] = g;
            } else {
                throw new RuntimeException("Got unknown argument class: " + argumentClass.getSimpleName());
            }
            i += argumentExtractors[i].a();
        }
        return argumentExtractors;
    }

    private int e() {
        int n = 0;
        for (a extractor : (a[]) com.facebook.infer.annotation.a.a(this.r)) {
            n += extractor.a();
        }
        return n;
    }

    public final void a(p jsInstance, ReadableNativeArray parameters) {
        this.o.b();
        String traceName = this.n.getName() + "." + this.k.getName();
        b.a();
        int i;
        int jsArgumentsConsumed;
        try {
            if (!this.q) {
                d();
            }
            if (this.t == null || this.r == null) {
                throw new Error("processArguments failed");
            } else if (this.u != parameters.size()) {
                throw new z(traceName + " got " + parameters.size() + " arguments, expected " + this.u);
            } else {
                i = 0;
                jsArgumentsConsumed = 0;
                while (i < this.r.length) {
                    this.t[i] = this.r[i].a(jsInstance, parameters, jsArgumentsConsumed);
                    jsArgumentsConsumed += this.r[i].a();
                    i++;
                }
                this.k.invoke(this.n.getModule(), this.t);
                b.b();
                this.o.a(this.n.getName(), this.k.getName());
            }
        } catch (IllegalArgumentException ie) {
            throw new RuntimeException("Could not invoke " + traceName, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException("Could not invoke " + traceName, iae);
        } catch (InvocationTargetException ite) {
            if (ite.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) ite.getCause());
            }
            throw new RuntimeException("Could not invoke " + traceName, ite);
        } catch (UnexpectedNativeTypeException e) {
            String str;
            StringBuilder append = new StringBuilder().append(e.getMessage()).append(" (constructing arguments for ").append(traceName).append(" at argument index ");
            int a = this.r[i].a();
            if (a > 1) {
                str = jsArgumentsConsumed + "-" + ((a + jsArgumentsConsumed) - 1);
            } else {
                str = String.valueOf(jsArgumentsConsumed);
            }
            throw new z(append.append(str).append(")").toString(), e);
        } catch (Throwable th) {
            b.b();
            this.o.a(this.n.getName(), this.k.getName());
        }
    }

    public final String b() {
        return this.p;
    }
}
