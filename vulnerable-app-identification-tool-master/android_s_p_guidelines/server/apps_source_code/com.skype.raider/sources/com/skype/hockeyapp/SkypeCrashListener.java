package com.skype.hockeyapp;

import android.app.Application;
import com.adjust.sdk.Constants;
import com.facebook.common.logging.FLog;
import com.skype.utils.FileUtil;
import com.slowpath.hockeyapp.RNHockeyAppModule;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import net.hockeyapp.android.b;
import org.json.JSONException;
import org.json.JSONObject;

public class SkypeCrashListener extends b {
    private boolean a = true;
    private Application b;
    private String c;
    private String d;
    private JSONObject e;

    public static SkypeCrashListener a(Application context) {
        return new SkypeCrashListener(context);
    }

    private SkypeCrashListener(Application context) {
        this.b = context;
        this.e = f();
        e();
    }

    private void e() {
        this.d = b("contactId");
        this.c = b("userId");
    }

    private String b(String id) {
        String result = null;
        if (!this.e.has(id)) {
            return result;
        }
        try {
            return this.e.getString(id);
        } catch (Throwable e) {
            FLog.e(RNHockeyAppModule.TAG, "Impossible json exception", e);
            return result;
        }
    }

    public final void a(boolean autoSend) {
        this.a = autoSend;
    }

    public final String a() {
        return this.e.toString();
    }

    public final String b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final void a(String metadata) {
        Throwable th;
        Throwable e;
        try {
            JSONObject newMetadata = new JSONObject(metadata);
            boolean dirty = false;
            Iterator<String> keys = newMetadata.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                Object newValue = newMetadata.get(key);
                if (this.e.has(key)) {
                    Object oldValue = this.e.get(key);
                    if (!oldValue.equals(newValue)) {
                        dirty = true;
                        if (key.equals("applicationTrace")) {
                            newValue = oldValue + " -> " + newValue;
                        }
                        this.e.put(key, newValue);
                    }
                } else {
                    dirty = true;
                    this.e.put(key, newValue);
                }
            }
            if (dirty) {
                Closeable stream = null;
                try {
                    stream = this.b.openFileOutput("HockeyAppCrashMetadata.json", 0);
                    Closeable stream2 = new BufferedOutputStream(stream, 8192);
                    try {
                        stream2.write(this.e.toString().getBytes("UTF8"));
                        e();
                        FileUtil.a(stream2);
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        stream = stream2;
                        FileUtil.a(stream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    FileUtil.a(stream);
                    throw th;
                }
            }
            return;
        } catch (IOException e2) {
            e = e2;
        } catch (JSONException e3) {
            e = e3;
        }
        FLog.w(RNHockeyAppModule.TAG, e, "RNHockeyApp: Failed to add metadata", new Object[0]);
    }

    public final void d() {
        this.b.deleteFile("HockeyAppCrashMetadata.json");
        this.e = new JSONObject();
    }

    private JSONObject f() {
        Throwable e;
        Throwable e2;
        Closeable stream = null;
        try {
            Closeable stream2 = new BufferedInputStream(this.b.openFileInput("HockeyAppCrashMetadata.json"), 8192);
            try {
                StringBuilder builder = new StringBuilder(8192);
                byte[] buffer = new byte[8192];
                int bytesRead;
                do {
                    bytesRead = stream2.read(buffer);
                    if (bytesRead > 0) {
                        builder.append(new String(buffer, 0, bytesRead, Constants.ENCODING));
                        continue;
                    }
                } while (bytesRead > 0);
                JSONObject jSONObject = new JSONObject(builder.toString());
                FileUtil.a(stream2);
                stream = stream2;
                return jSONObject;
            } catch (IOException e3) {
                e = e3;
                stream = stream2;
                e2 = e;
                try {
                    FLog.w(RNHockeyAppModule.TAG, e2, "RNHockeyApp: Failed to get existing metadata", new Object[0]);
                    FileUtil.a(stream);
                    return new JSONObject();
                } catch (Throwable th) {
                    e = th;
                    FileUtil.a(stream);
                    throw e;
                }
            } catch (JSONException e4) {
                e = e4;
                stream = stream2;
                e2 = e;
                FLog.w(RNHockeyAppModule.TAG, e2, "RNHockeyApp: Failed to get existing metadata", new Object[0]);
                FileUtil.a(stream);
                return new JSONObject();
            } catch (Throwable th2) {
                e = th2;
                stream = stream2;
                FileUtil.a(stream);
                throw e;
            }
        } catch (IOException e5) {
            e = e5;
            e2 = e;
            FLog.w(RNHockeyAppModule.TAG, e2, "RNHockeyApp: Failed to get existing metadata", new Object[0]);
            FileUtil.a(stream);
            return new JSONObject();
        } catch (JSONException e6) {
            e = e6;
            e2 = e;
            FLog.w(RNHockeyAppModule.TAG, e2, "RNHockeyApp: Failed to get existing metadata", new Object[0]);
            FileUtil.a(stream);
            return new JSONObject();
        }
    }
}
