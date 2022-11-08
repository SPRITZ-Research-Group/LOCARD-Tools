package com.airbnb.lottie.d;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.airbnb.lottie.a;
import com.airbnb.lottie.e;
import com.airbnb.lottie.h;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;
import org.b.a.b;
import org.b.a.c;
import org.b.a.d;
import org.b.a.i;
import org.json.JSONObject;

public final class f extends AsyncTask<InputStream, Void, e> implements a {
    private final JSONObject a;
    private final h b;

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((InputStream[]) objArr);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        this.b.a((e) obj);
    }

    public f(@NonNull JSONObject parameters, h loadedListener) {
        this.a = parameters;
        this.b = loadedListener;
    }

    private e a(InputStream... params) {
        InputStream stream = params[0];
        try {
            StringBuilder builder = new StringBuilder(4096);
            byte[] buffer = new byte[4096];
            while (true) {
                int read = stream.read(buffer);
                if (read == -1) {
                    break;
                }
                builder.append(new String(buffer, 0, read, Charset.defaultCharset()));
            }
            org.b.a.f template = new org.b.a.f(builder.toString());
            Iterator<String> keys = this.a.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                template.a(key, this.a.optString(key));
            }
            Locale locale = Locale.getDefault();
            Writer stringWriter = new StringWriter();
            i bVar = new b(stringWriter);
            bVar.a();
            new d(template.d, locale, template.b.k.r).a(bVar, new c(null, template));
            e a = e.a.a(stringWriter.toString());
            try {
                stream.close();
                return a;
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (Throwable th) {
            try {
                stream.close();
            } catch (IOException ex2) {
                throw new IllegalStateException(ex2);
            }
        }
    }

    public final void a() {
        cancel(true);
    }
}
