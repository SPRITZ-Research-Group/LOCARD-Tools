package com.google.android.gms.ads.identifier;

import android.net.Uri;
import android.net.Uri.Builder;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

final class a extends Thread {
    private final /* synthetic */ Map a;

    a(Map map) {
        this.a = map;
    }

    public final void run() {
        String message;
        Exception e;
        c cVar = new c();
        Map map = this.a;
        Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        for (String message2 : map.keySet()) {
            buildUpon.appendQueryParameter(message2, (String) map.get(message2));
        }
        String uri = buildUpon.build().toString();
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) new URL(uri).openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                new StringBuilder(String.valueOf(uri).length() + 65).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(uri);
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e2) {
            message2 = e2.getMessage();
            new StringBuilder((String.valueOf(uri).length() + 32) + String.valueOf(message2).length()).append("Error while parsing ping URL: ").append(uri).append(". ").append(message2);
        } catch (IOException e3) {
            e = e3;
            message2 = e.getMessage();
            new StringBuilder((String.valueOf(uri).length() + 27) + String.valueOf(message2).length()).append("Error while pinging URL: ").append(uri).append(". ").append(message2);
        } catch (RuntimeException e4) {
            e = e4;
            message2 = e.getMessage();
            new StringBuilder((String.valueOf(uri).length() + 27) + String.valueOf(message2).length()).append("Error while pinging URL: ").append(uri).append(". ").append(message2);
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }
}
