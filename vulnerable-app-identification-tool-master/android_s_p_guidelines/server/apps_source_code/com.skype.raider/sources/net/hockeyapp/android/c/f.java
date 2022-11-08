package net.hockeyapp.android.c;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPOutputStream;
import net.hockeyapp.android.f.a;
import net.hockeyapp.android.f.e;

public final class f {
    protected WeakReference<e> a;
    private AtomicInteger b = new AtomicInteger(0);
    private String c;

    protected f() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void b() {
        Throwable th;
        Writer writer = null;
        if (d() != null) {
            String persistedData;
            File fileToSend = d().b();
            if (d() == null || fileToSend == null) {
                persistedData = null;
            } else {
                persistedData = d().a(fileToSend);
                if (persistedData != null && persistedData.isEmpty()) {
                    d().b(fileToSend);
                }
            }
            HttpURLConnection connection = c();
            if (!(persistedData == null || connection == null || connection == null || fileToSend == null || persistedData == null)) {
                try {
                    this.b.getAndIncrement();
                    if (!(connection == null || persistedData == null)) {
                        Writer outputStreamWriter;
                        try {
                            e.b("HockeyApp-Metrics");
                            new StringBuilder("Using URL:").append(connection.getURL().toString());
                            e.b("HockeyApp-Metrics");
                            if (VERSION.SDK_INT >= 19) {
                                connection.addRequestProperty("Content-Encoding", "gzip");
                                connection.setRequestProperty("Content-Type", "application/x-json-stream");
                                outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(connection.getOutputStream(), true), Constants.ENCODING);
                            } else {
                                outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), Constants.ENCODING);
                            }
                            try {
                                outputStreamWriter.write(persistedData);
                                outputStreamWriter.flush();
                                writer = outputStreamWriter;
                            } catch (Throwable th2) {
                                th = th2;
                                if (outputStreamWriter != null) {
                                    outputStreamWriter.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            Throwable th4 = th3;
                            outputStreamWriter = null;
                            th = th4;
                        }
                    }
                    if (writer != null) {
                        writer.close();
                    }
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    this.b.getAndDecrement();
                    new StringBuilder("response code ").append(Integer.toString(responseCode));
                    e.b("HockeyApp-Metrics");
                    if (Arrays.asList(new Integer[]{Integer.valueOf(408), Integer.valueOf(429), Integer.valueOf(500), Integer.valueOf(503), Integer.valueOf(511)}).contains(Integer.valueOf(responseCode))) {
                        e.b("HockeyApp-Metrics");
                        if (d() != null) {
                            d().c(fileToSend);
                        }
                    } else {
                        Object obj;
                        if (d() != null) {
                            d().b(fileToSend);
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        if (200 > responseCode || responseCode > 203) {
                            obj = null;
                        } else {
                            obj = 1;
                        }
                        if (obj != null) {
                            InputStream inputStream = connection.getInputStream();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            a();
                        } else {
                            stringBuilder.append(String.format(Locale.ROOT, "Unexpected response code: %d", new Object[]{Integer.valueOf(responseCode)}));
                            stringBuilder.append("\n");
                            e.f("HockeyApp-Metrics");
                            a(connection);
                        }
                    }
                } catch (IOException e) {
                    new StringBuilder("Couldn't close writer with: ").append(e.toString());
                    e.f("HockeyApp-Metrics");
                    throw th;
                } catch (SecurityException e2) {
                    new StringBuilder("Couldn't send data with ").append(e2.toString());
                    e.b("HockeyApp-Metrics");
                    this.b.getAndDecrement();
                    if (d() != null) {
                        e.b("HockeyApp-Metrics");
                        d().c(fileToSend);
                    }
                } catch (Exception e3) {
                    new StringBuilder("Couldn't send data with ").append(e3.toString());
                    e.b("HockeyApp-Metrics");
                    this.b.getAndDecrement();
                    if (d() != null) {
                        e.b("HockeyApp-Metrics");
                        d().c(fileToSend);
                    }
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection c() {
        try {
            URL url;
            if (this.c == null) {
                url = new URL("https://gate.hockeyapp.net/v2/track");
            } else {
                url = new URL(this.c);
            }
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-json-stream");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            return connection;
        } catch (IOException e) {
            e.g("HockeyApp-Metrics");
            return null;
        }
    }

    private static void a(HttpURLConnection connection) {
        StringBuilder buffer = new StringBuilder();
        InputStream inputStream = null;
        try {
            String result;
            inputStream = connection.getErrorStream();
            if (inputStream == null) {
                inputStream = connection.getInputStream();
            }
            if (inputStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Constants.ENCODING));
                while (true) {
                    String inputLine = br.readLine();
                    if (inputLine == null) {
                        break;
                    }
                    buffer.append(inputLine);
                }
                result = buffer.toString();
            } else {
                result = connection.getResponseMessage();
            }
            if (TextUtils.isEmpty(result)) {
                e.a("HockeyApp-Metrics");
            } else {
                e.a();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.toString();
                    e.f("HockeyApp-Metrics");
                }
            }
        } catch (IOException e2) {
            e2.toString();
            e.f("HockeyApp-Metrics");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e22) {
                    e22.toString();
                    e.f("HockeyApp-Metrics");
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e222) {
                    e222.toString();
                    e.f("HockeyApp-Metrics");
                }
            }
        }
    }

    private e d() {
        if (this.a != null) {
            return (e) this.a.get();
        }
        return null;
    }

    protected final void a(e persistence) {
        this.a = new WeakReference(persistence);
    }

    @SuppressLint({"StaticFieldLeak"})
    protected final void a() {
        if (this.b.get() < 10) {
            try {
                a.a(new AsyncTask<Void, Void, Void>(this) {
                    final /* synthetic */ f a;

                    {
                        this.a = this$0;
                    }

                    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                        this.a.b();
                        return null;
                    }
                });
                return;
            } catch (RejectedExecutionException e) {
                e.f();
                return;
            }
        }
        e.b("HockeyApp-Metrics");
    }
}
