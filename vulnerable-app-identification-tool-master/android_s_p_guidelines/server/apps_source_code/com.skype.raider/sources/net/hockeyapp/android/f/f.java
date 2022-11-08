package net.hockeyapp.android.f;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.microsoft.applications.telemetry.LogConfiguration;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class f {
    private final String a;
    private String b;
    private String c;
    private i d;
    private int e = LogConfiguration.MAX_BACKOFF_FOR_SENDING_RETRIES_MILLIS;
    private final Map<String, String> f;

    public f(String urlString) {
        this.a = urlString;
        this.f = new HashMap();
        this.f.put("User-Agent", "HockeySDK/Android 5.1.0");
    }

    public final f a(String requestMethod) {
        this.b = requestMethod;
        return this;
    }

    public final f a(Map<String, String> fields) {
        if (fields.size() > 25) {
            throw new IllegalArgumentException("Fields size too large: " + fields.size() + " - max allowed: 25");
        }
        for (String key : fields.keySet()) {
            String value = (String) fields.get(key);
            if (value != null && ((long) value.length()) > 4194304) {
                throw new IllegalArgumentException("Form field " + key + " size too large: " + value.length() + " - max allowed: 4194304");
            }
        }
        try {
            String formString = a((Map) fields, Constants.ENCODING);
            a("Content-Type", "application/x-www-form-urlencoded");
            this.c = formString;
            return this;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public final f a(Map<String, String> fields, Context context, List<Uri> attachmentUris) {
        try {
            this.d = new i(File.createTempFile("multipart", null, context.getCacheDir()));
            this.d.b();
            for (String key : fields.keySet()) {
                this.d.a(key, (String) fields.get(key));
            }
            int i = 0;
            while (i < attachmentUris.size()) {
                Uri attachmentUri = (Uri) attachmentUris.get(i);
                boolean lastFile = i == attachmentUris.size() + -1;
                this.d.a("attachment" + i, attachmentUri.getLastPathSegment(), context.getContentResolver().openInputStream(attachmentUri), "application/octet-stream", lastFile);
                i++;
            }
            this.d.c();
            a("Content-Type", "multipart/form-data; boundary=" + this.d.a());
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final f a(String name, String value) {
        this.f.put(name, value);
        return this;
    }

    public final HttpURLConnection a() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(this.a).openConnection();
        connection.setConnectTimeout(this.e);
        connection.setReadTimeout(this.e);
        if (!TextUtils.isEmpty(this.b)) {
            connection.setRequestMethod(this.b);
            if (!TextUtils.isEmpty(this.c) || this.b.equalsIgnoreCase("POST") || this.b.equalsIgnoreCase("PUT")) {
                connection.setDoOutput(true);
            }
        }
        for (String name : this.f.keySet()) {
            connection.setRequestProperty(name, (String) this.f.get(name));
        }
        if (!TextUtils.isEmpty(this.c)) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), Constants.ENCODING));
            writer.write(this.c);
            writer.flush();
            writer.close();
        }
        if (this.d != null) {
            connection.setRequestProperty("Content-Length", String.valueOf(this.d.d()));
            this.d.a(connection.getOutputStream());
        }
        return connection;
    }

    private static String a(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        List<String> protoList = new ArrayList();
        for (String key : params.keySet()) {
            String value = (String) params.get(key);
            String key2 = URLEncoder.encode(key2, charset);
            protoList.add(key2 + "=" + URLEncoder.encode(value, charset));
        }
        return TextUtils.join("&", protoList);
    }
}
