package com.microsoft.urlrequest;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.content.c;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.modules.network.e;
import com.facebook.react.modules.network.f;
import com.facebook.react.modules.network.g;
import com.facebook.react.modules.network.h;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.hockeyapp.android.j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

public class RNUrlRequestService extends JobService {
    private static final Map<String, c> a = new HashMap();
    private static a b;

    public interface a {
        void a(Bundle bundle);
    }

    static /* synthetic */ void a(RNUrlRequestService x0, String x1, long x2, long x3, double[] x4) {
        synchronized (a) {
            if (a.containsKey(x1)) {
                ((c) a.get(x1)).a();
            }
        }
        double d = x3 > 0 ? ((double) x2) / ((double) x3) : 0.0d;
        if (d - x4[0] >= 0.01d || d == 1.0d) {
            Bundle a = a.a(x1, d);
            FLog.i("RNUrlRequestService", "progress: " + x1 + ", percent: " + d);
            x0.a(a);
            x4[0] = d;
        }
    }

    public static void a(a resultCallback) {
        b = resultCallback;
    }

    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public boolean onStartJob(JobParameters params) {
        boolean z;
        PersistableBundle bundle = params.getExtras();
        synchronized (a) {
            final String id = bundle.getString("id");
            String kind = bundle.getString("kind");
            FLog.i("RNUrlRequestService", "onStartCommand: " + id + ", kind: " + kind);
            if ("cancel".equals(kind)) {
                c(id);
                b(id);
                z = false;
            } else if ("request".equals(kind)) {
                boolean asStringResult;
                boolean downloadPublicFolderUseSkypeFolder;
                boolean withCredentials;
                Integer completeTimeout;
                Integer progressTimeout;
                boolean resumable;
                int startOffset;
                String uploadStatusUrl;
                int i;
                int intValue;
                Builder newBuilder;
                final OkHttpClient build;
                AnonymousClass1 anonymousClass1;
                final String str;
                final JobParameters jobParameters;
                final AnonymousClass1 anonymousClass12;
                Callback anonymousClass2;
                Object anonymousClass22;
                Request.Builder a;
                Call newCall;
                final String url = bundle.getString(j.FRAGMENT_URL);
                final String method = bundle.getString("method");
                final PersistableBundle headers = bundle.getPersistableBundle("headers");
                String sendFileUri = null;
                if (bundle.containsKey("sendFileUri")) {
                    sendFileUri = bundle.getString("sendFileUri");
                }
                String sendData = null;
                if (bundle.containsKey("sendData")) {
                    sendData = bundle.getString("sendData");
                }
                String str2 = "yes";
                Object obj = null;
                if (bundle.containsKey("downloadToFile")) {
                    obj = bundle.getString("downloadToFile");
                }
                if (str2.equals(obj)) {
                    asStringResult = false;
                } else {
                    asStringResult = true;
                }
                String downloadToPublicFolderWithName = null;
                if (bundle.containsKey("downloadToPublicFolderWithName")) {
                    downloadToPublicFolderWithName = bundle.getString("downloadToPublicFolderWithName");
                }
                if (bundle.containsKey("downloadPublicFolderUseSkypeFolder")) {
                    if (bundle.getBoolean("downloadPublicFolderUseSkypeFolder")) {
                        downloadPublicFolderUseSkypeFolder = true;
                        withCredentials = bundle.getBoolean("withCredentials", false);
                        if (bundle.containsKey("completeTimeout")) {
                            completeTimeout = null;
                        } else {
                            completeTimeout = Integer.valueOf(bundle.getInt("completeTimeout"));
                        }
                        if (bundle.containsKey("progressTimeout")) {
                            progressTimeout = null;
                        } else {
                            progressTimeout = Integer.valueOf(bundle.getInt("progressTimeout"));
                        }
                        if (bundle.containsKey("resumable")) {
                            if (bundle.getBoolean("resumable")) {
                                resumable = true;
                                if (bundle.containsKey("startOffset")) {
                                    startOffset = 0;
                                } else {
                                    startOffset = bundle.getInt("startOffset");
                                }
                                if (bundle.containsKey("uploadStatusUrl")) {
                                    uploadStatusUrl = null;
                                } else {
                                    uploadStatusUrl = bundle.getString("uploadStatusUrl");
                                }
                                if (bundle.containsKey("attemptsSoFar")) {
                                    i = 0;
                                } else {
                                    i = bundle.getInt("attemptsSoFar");
                                }
                                intValue = Integer.valueOf(i).intValue();
                                newBuilder = e.a().newBuilder();
                                if (!withCredentials) {
                                    newBuilder.cookieJar(CookieJar.NO_COOKIES);
                                }
                                build = newBuilder.build();
                                anonymousClass1 = new Callback(this) {
                                    final /* synthetic */ RNUrlRequestService e;

                                    public final void onFailure(@NonNull Call request, @NonNull IOException e) {
                                        this.e.a(id, e, "onFailure");
                                    }

                                    public final void onResponse(@NonNull Call call, @NonNull Response response) {
                                        RNUrlRequestService.a(this.e, id, response, asStringResult, downloadToPublicFolderWithName, downloadPublicFolderUseSkypeFolder);
                                    }
                                };
                                str = id;
                                jobParameters = params;
                                anonymousClass12 = anonymousClass1;
                                anonymousClass22 = new Callback(this) {
                                    final /* synthetic */ RNUrlRequestService n;

                                    public final void onFailure(@NonNull Call request, @NonNull IOException e) {
                                        this.n.a(str, e, "onFailure");
                                    }

                                    public final void onResponse(@NonNull Call call, @NonNull Response response) {
                                        try {
                                            synchronized (RNUrlRequestService.a) {
                                                int startOffset = RNUrlRequestService.b(str, response);
                                                FLog.w("RNUrlRequestService", "Resuming upload from reported status offset: " + String.valueOf(startOffset));
                                                Call sendCall = build.newCall(this.n.a(str, url, method, headers, sendFileUri, sendData, resumable, startOffset, uploadStatusUrl).build());
                                                if (RNUrlRequestService.a.containsKey(str)) {
                                                    this.n.a(str, completeTimeout, progressTimeout, sendCall, jobParameters);
                                                    sendCall.enqueue(anonymousClass12);
                                                }
                                            }
                                        } catch (Exception e) {
                                            this.n.a(str, e, "send.body");
                                        } catch (Exception e2) {
                                            this.n.a(str, e2, "resumable.upload.status");
                                        }
                                    }
                                };
                                if (resumable || intValue <= 0) {
                                    anonymousClass22 = anonymousClass1;
                                    a = a(id, url, method, headers, sendFileUri, sendData, resumable, startOffset, uploadStatusUrl);
                                } else {
                                    FLog.w("RNUrlRequestService", "Getting resumable upload status: " + uploadStatusUrl);
                                    a = new Request.Builder().url(uploadStatusUrl).headers(Headers.of(a(headers))).get();
                                }
                                newCall = build.newCall(a.build());
                                a(id, completeTimeout, progressTimeout, newCall, params);
                                newCall.enqueue(anonymousClass22);
                                z = true;
                            }
                        }
                        resumable = false;
                        if (bundle.containsKey("startOffset")) {
                            startOffset = 0;
                        } else {
                            startOffset = bundle.getInt("startOffset");
                        }
                        if (bundle.containsKey("uploadStatusUrl")) {
                            uploadStatusUrl = null;
                        } else {
                            uploadStatusUrl = bundle.getString("uploadStatusUrl");
                        }
                        if (bundle.containsKey("attemptsSoFar")) {
                            i = 0;
                        } else {
                            i = bundle.getInt("attemptsSoFar");
                        }
                        intValue = Integer.valueOf(i).intValue();
                        newBuilder = e.a().newBuilder();
                        if (withCredentials) {
                            newBuilder.cookieJar(CookieJar.NO_COOKIES);
                        }
                        build = newBuilder.build();
                        anonymousClass1 = /* anonymous class already generated */;
                        str = id;
                        jobParameters = params;
                        anonymousClass12 = anonymousClass1;
                        anonymousClass22 = /* anonymous class already generated */;
                        if (resumable) {
                        }
                        anonymousClass22 = anonymousClass1;
                        a = a(id, url, method, headers, sendFileUri, sendData, resumable, startOffset, uploadStatusUrl);
                        newCall = build.newCall(a.build());
                        a(id, completeTimeout, progressTimeout, newCall, params);
                        newCall.enqueue(anonymousClass22);
                        z = true;
                    }
                }
                downloadPublicFolderUseSkypeFolder = false;
                withCredentials = bundle.getBoolean("withCredentials", false);
                if (bundle.containsKey("completeTimeout")) {
                    completeTimeout = null;
                } else {
                    completeTimeout = Integer.valueOf(bundle.getInt("completeTimeout"));
                }
                if (bundle.containsKey("progressTimeout")) {
                    progressTimeout = null;
                } else {
                    progressTimeout = Integer.valueOf(bundle.getInt("progressTimeout"));
                }
                if (bundle.containsKey("resumable")) {
                    if (bundle.getBoolean("resumable")) {
                        resumable = true;
                        if (bundle.containsKey("startOffset")) {
                            startOffset = bundle.getInt("startOffset");
                        } else {
                            startOffset = 0;
                        }
                        if (bundle.containsKey("uploadStatusUrl")) {
                            uploadStatusUrl = bundle.getString("uploadStatusUrl");
                        } else {
                            uploadStatusUrl = null;
                        }
                        if (bundle.containsKey("attemptsSoFar")) {
                            i = bundle.getInt("attemptsSoFar");
                        } else {
                            i = 0;
                        }
                        intValue = Integer.valueOf(i).intValue();
                        newBuilder = e.a().newBuilder();
                        if (withCredentials) {
                            newBuilder.cookieJar(CookieJar.NO_COOKIES);
                        }
                        build = newBuilder.build();
                        anonymousClass1 = /* anonymous class already generated */;
                        str = id;
                        jobParameters = params;
                        anonymousClass12 = anonymousClass1;
                        anonymousClass22 = /* anonymous class already generated */;
                        if (resumable) {
                        }
                        anonymousClass22 = anonymousClass1;
                        a = a(id, url, method, headers, sendFileUri, sendData, resumable, startOffset, uploadStatusUrl);
                        newCall = build.newCall(a.build());
                        a(id, completeTimeout, progressTimeout, newCall, params);
                        newCall.enqueue(anonymousClass22);
                        z = true;
                    }
                }
                resumable = false;
                if (bundle.containsKey("startOffset")) {
                    startOffset = 0;
                } else {
                    startOffset = bundle.getInt("startOffset");
                }
                if (bundle.containsKey("uploadStatusUrl")) {
                    uploadStatusUrl = null;
                } else {
                    uploadStatusUrl = bundle.getString("uploadStatusUrl");
                }
                if (bundle.containsKey("attemptsSoFar")) {
                    i = 0;
                } else {
                    i = bundle.getInt("attemptsSoFar");
                }
                intValue = Integer.valueOf(i).intValue();
                try {
                    newBuilder = e.a().newBuilder();
                    if (withCredentials) {
                        newBuilder.cookieJar(CookieJar.NO_COOKIES);
                    }
                    build = newBuilder.build();
                    anonymousClass1 = /* anonymous class already generated */;
                    str = id;
                    jobParameters = params;
                    anonymousClass12 = anonymousClass1;
                    anonymousClass22 = /* anonymous class already generated */;
                    if (resumable) {
                    }
                    anonymousClass22 = anonymousClass1;
                    a = a(id, url, method, headers, sendFileUri, sendData, resumable, startOffset, uploadStatusUrl);
                    newCall = build.newCall(a.build());
                    a(id, completeTimeout, progressTimeout, newCall, params);
                    newCall.enqueue(anonymousClass22);
                } catch (Exception e) {
                    a(id, e, "send.body");
                } catch (Exception e2) {
                    a(id, e2, "send.catch");
                }
                z = true;
            } else {
                FLog.e("RNUrlRequestService", "onStartCommand: " + id + ", Ignoring unknown kind: " + kind);
                z = false;
            }
        }
        return z;
    }

    private void b(String id) {
        synchronized (a) {
            c info = (c) a.remove(id);
            if (info != null) {
                info.a(false);
                jobFinished(info.c(), false);
            }
        }
    }

    public void onCreate() {
        FLog.d("RNUrlRequestService", "onCreate");
        super.onCreate();
    }

    public void onDestroy() {
        FLog.d("RNUrlRequestService", "onDestroy");
        super.onDestroy();
    }

    private static void c(String id) {
        synchronized (a) {
            if (a.containsKey(id)) {
                ((c) a.get(id)).a(true);
            } else {
                FLog.w("RNUrlRequestService", "onStartCommand: " + id + ", Cannot cancel unknown id");
            }
        }
    }

    private Request.Builder a(String id, String url, String method, PersistableBundle headers, String sendFileUri, String sendData, boolean resumable, int startOffset, String uploadStatusUrl) throws FileNotFoundException, IllegalArgumentException {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if ("GET".equalsIgnoreCase(method)) {
            requestBuilder = requestBuilder.get();
        } else if ("HEAD".equalsIgnoreCase(method)) {
            requestBuilder = requestBuilder.head();
        } else {
            RequestBody body;
            final double[] previouslyReportedPercent = new double[1];
            MediaType mediaType = MediaType.parse(headers.getString("Content-Type"));
            if (sendFileUri != null) {
                File data;
                if (sendFileUri.startsWith("file:")) {
                    data = new File(Uri.parse(sendFileUri).getPath());
                } else {
                    data = new File(sendFileUri);
                }
                if (data.exists()) {
                    InputStream input = new FileInputStream(data);
                    int length = 0;
                    try {
                        length = input.available();
                        FLog.w("RNUrlRequestService", "bytes to upload from file: " + String.valueOf(length));
                    } catch (IOException e) {
                        FLog.w("RNUrlRequestService", "Unable to determine file size, will send as non-resumable upload");
                    }
                    if (!resumable || length <= 0) {
                        body = RequestBody.create(mediaType, data);
                    } else {
                        String contentRange = String.format(Locale.getDefault(), "bytes %d-%d/%d", new Object[]{Integer.valueOf(startOffset), Integer.valueOf(length - 1), Integer.valueOf(length)});
                        FLog.w("RNUrlRequestService", "Resuming upload: " + contentRange + " with status url: " + uploadStatusUrl);
                        headers.putString("X-AMS-Resumable-Upload-Supported", "true");
                        headers.putString("X-Full-Content-Length", String.valueOf(length));
                        headers.putString("Content-Range", contentRange);
                        body = new d(input, length, startOffset, length);
                    }
                } else {
                    throw new FileNotFoundException("File does not exist");
                }
            }
            body = RequestBody.create(mediaType, sendData);
            final String str = id;
            requestBuilder = requestBuilder.method(method, new g(body, new f(this) {
                final /* synthetic */ RNUrlRequestService c;

                public final void a(long bytesWritten, long contentLength, boolean done) {
                    RNUrlRequestService.a(this.c, str, bytesWritten, contentLength, previouslyReportedPercent);
                }
            }));
        }
        requestBuilder.headers(Headers.of(a(headers)));
        return requestBuilder;
    }

    private static Map<String, String> a(PersistableBundle bundle) {
        HashMap<String, String> map = new HashMap();
        for (String key : bundle.keySet()) {
            map.put(key, (String) bundle.get(key));
        }
        return map;
    }

    private void a(String id, Integer completeTimeout, Integer progressTimeout, Call call, JobParameters jobParameters) {
        synchronized (a) {
            c request = (c) a.get(id);
            if (request != null) {
                request.a(false);
                jobFinished(request.c(), false);
            }
            a.put(id, new c(id, call, completeTimeout, progressTimeout, new a(this) {
                final /* synthetic */ RNUrlRequestService a;

                {
                    this.a = this$0;
                }

                public final void a(String id) {
                    FLog.i("RNUrlRequestService", "timeout: " + id);
                    RNUrlRequestService.c(id);
                }
            }, jobParameters));
        }
    }

    private static int b(String id, Response response) throws Exception {
        int statusCode = response.code();
        if (statusCode == 404) {
            return 0;
        }
        if (statusCode != 200) {
            throw new Exception("Unable to get upload status id: " + id);
        }
        try {
            int length = new JSONObject(response.body().string()).getInt("length");
            FLog.w("RNUrlRequestService", "Resumable upload status: " + String.valueOf(length));
            return length;
        } catch (JSONException e) {
            return 0;
        }
    }

    private void a(String id, Exception e, String errorSource) {
        boolean isTimedOut;
        synchronized (a) {
            isTimedOut = a.containsKey(id) && ((c) a.get(id)).b();
        }
        String error = isTimedOut ? "Timeout" : e != null ? e.getMessage() : "";
        Bundle result = a.a(id, error);
        FLog.i("RNUrlRequestService", "clientError: " + id + ", errorSource: " + errorSource + ", httpStatusCode: 0, error: " + error, (Throwable) e);
        a(result);
        b(id);
    }

    private void a(Bundle result) {
        try {
            FLog.i("RNUrlRequestService", "Will try to emit event directly from service");
            b.a(result);
        } catch (Exception e) {
            FLog.i("RNUrlRequestService", "Unable to emit event from service, will use local broadcast to notify module.");
            Intent intent = new Intent("com.microsoft.s4l.UrlRequest.REQUEST_RESULT_INTENT_LOCAL_BROADCASE");
            intent.putExtra("com.microsoft.s4l.UrlRequest.EXTRA_RESULT", result);
            c.a(getApplicationContext()).b(intent);
        }
    }

    private String a(String id, Response response, String downloadToPublicFolderWithName, boolean downloadPublicFolderUseSkypeFolder) throws IOException {
        Throwable exception;
        boolean fileWasDeleted;
        Throwable th;
        Closeable output;
        final double[] previouslyReportedPercent = new double[1];
        final String str = id;
        ResponseBody body = new h(response.body(), new f(this) {
            final /* synthetic */ RNUrlRequestService c;

            public final void a(long bytesWritten, long contentLength, boolean done) {
                RNUrlRequestService.a(this.c, str, bytesWritten, contentLength, previouslyReportedPercent);
            }
        });
        String responseData = null;
        Closeable input = null;
        Closeable output2 = null;
        File outputFile = null;
        if (downloadToPublicFolderWithName == null) {
            try {
                outputFile = File.createTempFile("Download", null, getCacheDir());
            } catch (IOException e) {
                exception = e;
                a(output2);
                a(input);
                if (exception == null) {
                    return responseData;
                }
                fileWasDeleted = false;
                fileWasDeleted = outputFile.delete();
                FLog.i("RNUrlRequestService", "downloadBodyToFile: " + id + ", Exception while downloading, fileWasDeleted: " + fileWasDeleted, exception);
                throw exception;
            } catch (Throwable th2) {
                th = th2;
                a(output2);
                a(input);
                throw th;
            }
        }
        File file;
        File file2;
        if (downloadPublicFolderUseSkypeFolder) {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "Skype Files");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            file2 = file;
        } else {
            file2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
        File file3 = new File(file2, downloadToPublicFolderWithName);
        if (file3.exists()) {
            int lastIndexOf = downloadToPublicFolderWithName.lastIndexOf(46);
            String substring = lastIndexOf == -1 ? downloadToPublicFolderWithName : downloadToPublicFolderWithName.substring(0, lastIndexOf);
            String substring2 = lastIndexOf == -1 ? "" : downloadToPublicFolderWithName.substring(lastIndexOf);
            lastIndexOf = 1;
            while (file3.exists()) {
                File file4 = new File(file2, substring + "-" + lastIndexOf + substring2);
                lastIndexOf++;
                file3 = file4;
            }
            file = file3;
        } else {
            file = file3;
        }
        outputFile = file;
        Closeable input2 = new BufferedInputStream(body.byteStream());
        try {
            output = new BufferedOutputStream(new FileOutputStream(outputFile));
        } catch (IOException e2) {
            exception = e2;
            input = input2;
            a(output2);
            a(input);
            if (exception == null) {
                return responseData;
            }
            fileWasDeleted = false;
            fileWasDeleted = outputFile.delete();
            FLog.i("RNUrlRequestService", "downloadBodyToFile: " + id + ", Exception while downloading, fileWasDeleted: " + fileWasDeleted, exception);
            throw exception;
        } catch (Throwable th3) {
            th = th3;
            input = input2;
            a(output2);
            a(input);
            throw th;
        }
        try {
            com.facebook.common.internal.a.a(input2, output);
            output.flush();
            responseData = a(outputFile);
            Throwable outputEx = a(output);
            Throwable inputEx = a(input2);
            if (outputEx != null) {
                exception = outputEx;
            } else {
                exception = inputEx;
            }
            output2 = output;
            input = input2;
        } catch (IOException e3) {
            exception = e3;
            output2 = output;
            input = input2;
            a(output2);
            a(input);
            if (exception == null) {
                return responseData;
            }
            fileWasDeleted = false;
            fileWasDeleted = outputFile.delete();
            FLog.i("RNUrlRequestService", "downloadBodyToFile: " + id + ", Exception while downloading, fileWasDeleted: " + fileWasDeleted, exception);
            throw exception;
        } catch (Throwable th4) {
            th = th4;
            output2 = output;
            input = input2;
            a(output2);
            a(input);
            throw th;
        }
        if (exception == null) {
            return responseData;
        }
        fileWasDeleted = false;
        if (outputFile != null && outputFile.exists()) {
            fileWasDeleted = outputFile.delete();
        }
        FLog.i("RNUrlRequestService", "downloadBodyToFile: " + id + ", Exception while downloading, fileWasDeleted: " + fileWasDeleted, exception);
        throw exception;
    }

    private static String a(File file) {
        try {
            JSONObject json = new JSONObject();
            json.put(ReactVideoViewManager.PROP_SRC_URI, Uri.fromFile(file).getPath());
            json.put("size", file.length());
            return json.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static IOException a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                return e;
            }
        }
        return null;
    }

    static /* synthetic */ void a(RNUrlRequestService x0, String x1, Response x2, boolean x3, String x4, boolean x5) {
        try {
            int i;
            Bundle a;
            Headers headers = x2.headers();
            HashMap hashMap = new HashMap();
            int size = headers.size();
            for (i = 0; i < size; i++) {
                Object name = headers.name(i);
                if ("Content-Type".equalsIgnoreCase(name)) {
                    name = "Content-Type";
                }
                hashMap.put(name, headers.value(i));
            }
            i = x2.code();
            String method = x2.request().method();
            if (!x3) {
                try {
                    if ("GET".equalsIgnoreCase(method) || "HEAD".equalsIgnoreCase(method)) {
                        method = x0.a(x1, x2, x4, x5);
                        hashMap.put("Content-Type", "application/json");
                        a = a.a(x1, i, hashMap, method);
                        FLog.i("RNUrlRequestService", "serverResponse: " + x1 + ", httpStatusCode: " + i + ", result: " + a);
                        x0.a(a);
                        x0.b(x1);
                    }
                } catch (Exception e) {
                    x0.a(x1, e, "serverResponse.catch(io)");
                    return;
                }
            }
            method = x2.body().string();
            a = a.a(x1, i, hashMap, method);
            FLog.i("RNUrlRequestService", "serverResponse: " + x1 + ", httpStatusCode: " + i + ", result: " + a);
            x0.a(a);
            x0.b(x1);
        } catch (Exception e2) {
            x0.a(x1, e2, "serverResponse.catch");
        }
    }
}
