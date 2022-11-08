package net.hockeyapp.android.e;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy;
import android.os.StrictMode.VmPolicy.Builder;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import net.hockeyapp.android.b.a;
import net.hockeyapp.android.h.d;

@SuppressLint({"StaticFieldLeak"})
public class e extends AsyncTask<Void, Integer, Long> {
    protected Context a;
    protected a b;
    protected String c;
    protected String d = (UUID.randomUUID() + ".apk");
    protected File e;
    protected ProgressDialog f;
    private String g;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Long) obj);
    }

    protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
        a((Integer[]) objArr);
    }

    public e(Context context, String urlString, a notifier) {
        this.a = context;
        this.c = urlString;
        this.e = new File(context.getExternalFilesDir(null), "Download");
        this.b = notifier;
        this.g = null;
    }

    protected Long a() {
        Throwable th;
        InputStream input = null;
        OutputStream output = null;
        Long valueOf;
        try {
            URLConnection connection = a(new URL(b()), 6);
            connection.connect();
            int lengthOfFile = connection.getContentLength();
            String contentType = connection.getContentType();
            if (contentType != null && contentType.contains("text")) {
                this.g = "The requested download does not appear to be a file.";
                return Long.valueOf(0);
            } else if (this.e.mkdirs() || this.e.exists()) {
                File file = new File(this.e, this.d);
                InputStream input2 = new BufferedInputStream(connection.getInputStream());
                try {
                    OutputStream output2 = new FileOutputStream(file);
                    try {
                        byte[] data = new byte[1024];
                        long total = 0;
                        while (true) {
                            int count = input2.read(data);
                            if (count == -1) {
                                break;
                            }
                            total += (long) count;
                            publishProgress(new Integer[]{Integer.valueOf(Math.round((((float) total) * 100.0f) / ((float) lengthOfFile)))});
                            output2.write(data, 0, count);
                        }
                        output2.flush();
                        valueOf = Long.valueOf(total);
                        try {
                            output2.close();
                            input2.close();
                        } catch (IOException e) {
                        }
                        output = output2;
                        input = input2;
                        return valueOf;
                    } catch (IOException e2) {
                        output = output2;
                        input = input2;
                        try {
                            new StringBuilder("Failed to download ").append(this.c);
                            net.hockeyapp.android.f.e.f();
                            valueOf = Long.valueOf(0);
                            if (output != null) {
                                try {
                                    output.close();
                                } catch (IOException e3) {
                                    return valueOf;
                                }
                            }
                            if (input != null) {
                                return valueOf;
                            }
                            input.close();
                            return valueOf;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        output = output2;
                        input = input2;
                        if (output != null) {
                            try {
                                output.close();
                            } catch (IOException e4) {
                                throw th;
                            }
                        }
                        if (input != null) {
                            input.close();
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    input = input2;
                    new StringBuilder("Failed to download ").append(this.c);
                    net.hockeyapp.android.f.e.f();
                    valueOf = Long.valueOf(0);
                    if (output != null) {
                        output.close();
                    }
                    if (input != null) {
                        return valueOf;
                    }
                    input.close();
                    return valueOf;
                } catch (Throwable th4) {
                    th = th4;
                    input = input2;
                    if (output != null) {
                        output.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                    throw th;
                }
            } else {
                throw new IOException("Could not create the dir(s):" + this.e.getAbsolutePath());
            }
        } catch (IOException e6) {
            new StringBuilder("Failed to download ").append(this.c);
            net.hockeyapp.android.f.e.f();
            valueOf = Long.valueOf(0);
            if (output != null) {
                output.close();
            }
            if (input != null) {
                return valueOf;
            }
            input.close();
            return valueOf;
        }
    }

    protected static URLConnection a(URL url, int remainingRedirects) throws IOException {
        HttpURLConnection connection;
        while (true) {
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "HockeySDK/Android 5.1.0");
            connection.setInstanceFollowRedirects(true);
            int code = connection.getResponseCode();
            if ((code != 301 && code != 302 && code != 303) || remainingRedirects == 0) {
                break;
            }
            URL movedUrl = new URL(connection.getHeaderField("Location"));
            if (url.getProtocol().equals(movedUrl.getProtocol())) {
                break;
            }
            connection.disconnect();
            remainingRedirects--;
            url = movedUrl;
        }
        return connection;
    }

    protected void a(Integer... args) {
        try {
            if (this.f == null) {
                this.f = new ProgressDialog(this.a);
                this.f.setProgressStyle(1);
                this.f.setMessage(this.a.getString(d.hockeyapp_update_loading));
                this.f.setCancelable(false);
                this.f.show();
            }
            this.f.setProgress(args[0].intValue());
        } catch (Exception e) {
        }
    }

    protected void a(Long result) {
        if (this.f != null) {
            try {
                this.f.dismiss();
            } catch (Exception e) {
            }
        }
        if (result.longValue() > 0) {
            this.b.a(this);
            Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
            intent.setDataAndType(Uri.fromFile(new File(this.e, this.d)), "application/vnd.android.package-archive");
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
            VmPolicy oldVmPolicy = null;
            if (VERSION.SDK_INT >= 24) {
                oldVmPolicy = StrictMode.getVmPolicy();
                StrictMode.setVmPolicy(new Builder().penaltyLog().build());
            }
            this.a.startActivity(intent);
            if (oldVmPolicy != null) {
                StrictMode.setVmPolicy(oldVmPolicy);
                return;
            }
            return;
        }
        try {
            String message;
            AlertDialog.Builder builder = new AlertDialog.Builder(this.a);
            builder.setTitle(d.hockeyapp_download_failed_dialog_title);
            if (this.g == null) {
                message = this.a.getString(d.hockeyapp_download_failed_dialog_message);
            } else {
                message = this.g;
            }
            builder.setMessage(message);
            builder.setNegativeButton(d.hockeyapp_download_failed_dialog_negative_button, new OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = this$0;
                }

                public final void onClick(DialogInterface dialog, int which) {
                    this.a.b.a(Boolean.valueOf(false));
                }
            });
            builder.setPositiveButton(d.hockeyapp_download_failed_dialog_positive_button, new OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = this$0;
                }

                public final void onClick(DialogInterface dialog, int which) {
                    this.a.b.a(Boolean.valueOf(true));
                }
            });
            builder.create().show();
        } catch (Exception e2) {
        }
    }

    protected final String b() {
        return this.c + "&type=apk";
    }
}
