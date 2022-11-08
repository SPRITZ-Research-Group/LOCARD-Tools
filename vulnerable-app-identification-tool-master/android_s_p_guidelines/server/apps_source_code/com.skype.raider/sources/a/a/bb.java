package a.a;

import com.adjust.sdk.Constants;
import com.appboy.f.c;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import org.json.JSONException;
import org.json.JSONObject;

public final class bb implements cc {
    private static final String a = c.a(bb.class);
    private final int b = 5000;

    public final JSONObject a(URI uri, Map<String, String> map) {
        return a(uri, null, (Map) map, fu.GET);
    }

    public final JSONObject a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        return a(uri, jSONObject, (Map) map, fu.POST);
    }

    private JSONObject a(URI uri, JSONObject jSONObject, Map<String, String> map, fu fuVar) {
        URL a = ct.a(uri);
        if (a == null) {
            return null;
        }
        try {
            return a(a, jSONObject, (Map) map, fuVar);
        } catch (IOException e) {
            try {
                return a(a, jSONObject, (Map) map, fuVar);
            } catch (Throwable e2) {
                throw new r("Experienced IOException twice during request to [" + a.toString() + "], failing: [" + e2.getMessage() + "]", e2);
            }
        }
    }

    private JSONObject a(URL url, JSONObject jSONObject, Map<String, String> map, fu fuVar) {
        HttpURLConnection b;
        Throwable th;
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        if (url != null) {
            try {
                b = b(url, jSONObject, map, fuVar);
            } catch (Throwable th2) {
                th = th2;
                httpURLConnection = null;
            }
        } else {
            b = null;
        }
        InputStream gZIPInputStream;
        if (b != null) {
            try {
                b.connect();
                if (b.getResponseCode() / 100 == 2) {
                    if ("gzip".equalsIgnoreCase(b.getContentEncoding())) {
                        gZIPInputStream = new GZIPInputStream(b.getInputStream());
                    } else {
                        gZIPInputStream = new BufferedInputStream(b.getInputStream());
                    }
                    try {
                        JSONObject jSONObject2 = new JSONObject(a(new BufferedReader(new InputStreamReader(gZIPInputStream, Constants.ENCODING))));
                        if (b != null) {
                            b.disconnect();
                        }
                        try {
                            gZIPInputStream.close();
                            return jSONObject2;
                        } catch (Throwable e) {
                            c.d(a, "Caught an error trying to close the inputStream in getResult", e);
                            return jSONObject2;
                        }
                    } catch (IOException e2) {
                        c.g(a, "Could not read from response stream [" + e2.getMessage() + "]");
                    } catch (JSONException e3) {
                        c.g(a, "Unable to parse response [" + e3 + "]");
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream = gZIPInputStream;
                        httpURLConnection = b;
                    }
                } else {
                    throw new r("Bad Http response code from Appboy: [" + b.getResponseCode() + "]");
                }
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection = b;
            }
        } else {
            gZIPInputStream = null;
            if (b != null) {
                b.disconnect();
            }
            if (gZIPInputStream != null) {
                try {
                    gZIPInputStream.close();
                } catch (Throwable th5) {
                    c.d(a, "Caught an error trying to close the inputStream in getResult", th5);
                }
            }
            c.f(a, "Failed to get result from [" + url + "]. Returning null.");
            return null;
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable e4) {
                c.d(a, "Caught an error trying to close the inputStream in getResult", e4);
            }
        }
        throw th5;
        throw th5;
    }

    private HttpURLConnection b(URL url, JSONObject jSONObject, Map<String, String> map, fu fuVar) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) fg.a(url);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(this.b);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod(fuVar.toString());
            for (Entry entry : map.entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            if (fuVar == fu.POST) {
                httpURLConnection.setDoOutput(true);
                OutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(jSONObject.toString().getBytes(Constants.ENCODING));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            return httpURLConnection;
        } catch (Throwable e) {
            throw new r("Could not set up connection [" + url.toString() + "] [" + e.getMessage() + "].  Appboy will try to reconnect periodically.", e);
        }
    }

    private static String a(BufferedReader bufferedReader) {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuilder.toString();
            }
            stringBuilder.append(readLine);
        }
    }
}
