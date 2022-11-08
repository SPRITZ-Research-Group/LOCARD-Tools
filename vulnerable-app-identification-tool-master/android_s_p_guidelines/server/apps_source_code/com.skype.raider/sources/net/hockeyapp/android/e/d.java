package net.hockeyapp.android.e;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import net.hockeyapp.android.f.j;

public abstract class d<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    protected static String a(HttpURLConnection connection) throws IOException {
        InputStream inputStream = new BufferedInputStream(connection.getInputStream());
        String jsonString = j.a(inputStream);
        inputStream.close();
        return jsonString;
    }
}
