package com.microsoft.react.sqlite.a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.al;
import com.microsoft.b.a.k;
import com.microsoft.react.sqlite.b.c;
import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class e extends c {
    private static final com.a.a.a.a h = new com.a.a.a.a();
    @NonNull
    final c c = new c((byte) 0);
    @NonNull
    com.a.a.a.c d;
    @NonNull
    final String e;
    @NonNull
    final com.microsoft.react.sqlite.b.a f;
    @Nullable
    final al g;

    public enum a {
        UPDATE,
        INSERT,
        DELETE,
        RAW_QUERY,
        SELECT;
        
        private static final Pattern f = null;

        static {
            f = Pattern.compile("^\\s*(\\S+)", 2);
        }

        public static a a(String sql) {
            Matcher matcher = f.matcher(sql);
            if (matcher.find()) {
                try {
                    return valueOf(matcher.group(1).toUpperCase(Locale.US));
                } catch (IllegalArgumentException e) {
                }
            }
            return RAW_QUERY;
        }
    }

    public e(ae promise, h transactionContext, String sql, al parameters) {
        super(promise, transactionContext);
        try {
            this.d = h.a(this.c);
        } catch (IOException e) {
        }
        this.e = sql;
        this.g = parameters;
        this.f = new com.microsoft.react.sqlite.b.a(sql);
    }

    public final String c() {
        StringBuilder builder = new StringBuilder(this.e);
        builder.append(": ");
        String[] params = a(this.g);
        int length = params.length;
        int lastIndex = length - 1;
        for (int i = 0; i < length; i++) {
            builder.append(params[i]);
            if (i != lastIndex) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public final Object a(com.microsoft.b.a.a db) {
        String result;
        this.f.a();
        a type = a.a(this.e);
        switch (type) {
            case UPDATE:
                result = c(db);
                break;
            case INSERT:
                result = d(db);
                break;
            case DELETE:
                result = c(db);
                break;
            case RAW_QUERY:
            case SELECT:
                result = e(db);
                break;
            default:
                throw new IllegalStateException("Unknown query type: " + type);
        }
        this.f.a("end", true);
        return result;
    }

    private String c(com.microsoft.b.a.a db) {
        Closeable myStatement = null;
        try {
            this.d.c();
            myStatement = db.b(this.e);
            this.f.a("statement compiled");
            a(myStatement, this.g);
            int rowsAffected = myStatement.b();
            this.f.a("query executed");
            if (rowsAffected != -1) {
                this.d.a("rowsAffected", rowsAffected);
            }
            this.d.d();
            this.d.close();
        } catch (IOException e) {
        } finally {
            a(myStatement);
        }
        return this.c.toString();
    }

    private String d(com.microsoft.b.a.a db) {
        Closeable myStatement = db.b(this.e);
        this.f.a("statement compiled");
        a(myStatement, this.g);
        try {
            this.d.c();
            long insertId = myStatement.a();
            this.f.a("query executed");
            if (insertId != -1) {
                this.d.a("insertId", insertId);
                this.d.a("rowsAffected", 1);
            } else {
                this.d.a("rowsAffected", 0);
            }
            this.d.d();
            this.d.close();
        } catch (IOException e) {
        } finally {
            a(myStatement);
        }
        return this.c.toString();
    }

    private String e(com.microsoft.b.a.a db) {
        Closeable cur = null;
        try {
            String[] params = a(this.g);
            this.f.a("parameters serialized");
            cur = db.a(this.e, params);
            this.f.a("query executed");
            this.d.c();
            if (cur.a()) {
                com.a.a.a.c cVar = this.d;
                cVar.a("rows");
                cVar.a();
                do {
                    int c = cur.c();
                    this.d.c();
                    for (int i = 0; i < c; i++) {
                        String a = cur.a(i);
                        int b = cur.b(i);
                        com.a.a.a.c cVar2;
                        switch (b) {
                            case 0:
                                cVar2 = this.d;
                                cVar2.a(a);
                                cVar2.e();
                                break;
                            case 1:
                                this.d.a(a, cur.c(i));
                                break;
                            case 2:
                                try {
                                    cVar2 = this.d;
                                    double d = cur.d(i);
                                    cVar2.a(a);
                                    cVar2.a(d);
                                    break;
                                } catch (IOException e) {
                                    break;
                                }
                            case 3:
                                this.d.a(a, cur.e(i));
                                break;
                            case 4:
                                this.d.a(a, new String(Base64.encode(cur.f(i), 0)));
                                break;
                            default:
                                throw new IllegalStateException("Unsupported data type in database: " + b);
                        }
                    }
                    this.d.d();
                } while (cur.b());
                this.d.b();
                this.f.a("data read and serialized");
            }
            this.d.d();
            this.d.close();
            a(cur);
            return this.c.toString();
        } catch (Throwable th) {
            a(cur);
        }
    }

    private static String[] a(al parameters) {
        if (parameters == null) {
            return new String[0];
        }
        int size = parameters.size();
        String[] result = new String[size];
        for (int j = 0; j < size; j++) {
            ReadableType type = parameters.getType(j);
            switch (type) {
                case Null:
                    result[j] = "";
                    break;
                case Number:
                    result[j] = Double.toString(parameters.getDouble(j));
                    break;
                case String:
                    result[j] = parameters.getString(j);
                    break;
                case Boolean:
                    result[j] = Boolean.toString(parameters.getBoolean(j));
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected type in query parameters: " + type);
            }
        }
        return result;
    }

    private void a(k statement, al parameters) {
        if (parameters != null) {
            int size = parameters.size();
            for (int i = 0; i < size; i++) {
                ReadableType type = parameters.getType(i);
                switch (type) {
                    case Null:
                        statement.a(i + 1);
                        break;
                    case Number:
                        statement.a(i + 1, parameters.getDouble(i));
                        break;
                    case String:
                        statement.a(i + 1, parameters.getString(i));
                        break;
                    default:
                        throw new IllegalStateException("Can't bind argument: " + type);
                }
            }
        }
        this.f.a("arguments bound");
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
