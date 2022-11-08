package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.common.i.f;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.producers.af.a;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import okhttp3.internal.http.StatusLine;

public final class t extends c<s> {
    private int a;
    private final ExecutorService b;

    public t() {
        this(Executors.newFixedThreadPool(3));
    }

    public t(int httpConnectionTimeout) {
        this(Executors.newFixedThreadPool(3));
        this.a = httpConnectionTimeout;
    }

    @VisibleForTesting
    private t(ExecutorService executorService) {
        this.b = executorService;
    }

    public final s a(Consumer<e> consumer, ak context) {
        return new s(consumer, context);
    }

    public final void a(final s fetchState, final a callback) {
        final Future<?> future = this.b.submit(new Runnable(this) {
            final /* synthetic */ t c;

            public final void run() {
                this.c.b(fetchState, callback);
            }
        });
        fetchState.b().a(new e(this) {
            final /* synthetic */ t c;

            public final void a() {
                if (future.cancel(false)) {
                    callback.a();
                }
            }
        });
    }

    @VisibleForTesting
    final void b(s fetchState, a callback) {
        IOException e;
        Throwable th;
        HttpURLConnection connection = null;
        InputStream is = null;
        try {
            int responseCode;
            String headerField;
            Uri e2 = fetchState.e();
            int i = 5;
            while (true) {
                Object obj;
                HttpURLConnection connection2 = (HttpURLConnection) f.a(e2).openConnection();
                connection2.setConnectTimeout(this.a);
                responseCode = connection2.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj != null) {
                    if (connection2 != null) {
                        try {
                            is = connection2.getInputStream();
                            callback.a(is, -1);
                        } catch (IOException e3) {
                            e = e3;
                            connection = connection2;
                            try {
                                callback.a(e);
                                if (is != null) {
                                    try {
                                        is.close();
                                    } catch (IOException e4) {
                                    }
                                }
                                if (connection != null) {
                                    connection.disconnect();
                                    return;
                                }
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                                if (is != null) {
                                    try {
                                        is.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                if (connection != null) {
                                    connection.disconnect();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            connection = connection2;
                            if (is != null) {
                                is.close();
                            }
                            if (connection != null) {
                                connection.disconnect();
                            }
                            throw th;
                        }
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e6) {
                        }
                    }
                    if (connection2 != null) {
                        connection2.disconnect();
                        connection = connection2;
                        return;
                    }
                    return;
                }
                switch (responseCode) {
                    case 300:
                    case 301:
                    case 302:
                    case 303:
                    case StatusLine.HTTP_TEMP_REDIRECT /*307*/:
                    case StatusLine.HTTP_PERM_REDIRECT /*308*/:
                        int i2 = 1;
                        break;
                    default:
                        obj = null;
                        break;
                }
                if (obj != null) {
                    headerField = connection2.getHeaderField("Location");
                    connection2.disconnect();
                    Uri parse = headerField == null ? null : Uri.parse(headerField);
                    String scheme = e2.getScheme();
                    if (i > 0 && parse != null && !parse.getScheme().equals(scheme)) {
                        i--;
                        e2 = parse;
                    } else if (i != 0) {
                        headerField = a("URL %s follows too many redirects", e2.toString());
                    } else {
                        headerField = a("URL %s returned %d without a valid redirect", e2.toString(), Integer.valueOf(responseCode));
                    }
                } else {
                    connection2.disconnect();
                    throw new IOException(String.format("Image URL %s returned HTTP code %d", new Object[]{e2.toString(), Integer.valueOf(responseCode)}));
                }
            }
            if (i != 0) {
                headerField = a("URL %s returned %d without a valid redirect", e2.toString(), Integer.valueOf(responseCode));
            } else {
                headerField = a("URL %s follows too many redirects", e2.toString());
            }
            throw new IOException(headerField);
        } catch (IOException e7) {
            e = e7;
        }
    }

    private static String a(String format, Object... args) {
        return String.format(Locale.getDefault(), format, args);
    }
}
