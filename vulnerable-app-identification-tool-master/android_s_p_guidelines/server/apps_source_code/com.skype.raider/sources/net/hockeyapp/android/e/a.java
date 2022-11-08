package net.hockeyapp.android.e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.g;
import net.hockeyapp.android.views.AttachmentView;

public final class a {
    private Queue<c> a;
    private boolean b;
    private final Handler c;

    private static class a {
        static final a a = new a();
    }

    private static class b extends Handler {
        private final a a;

        b(a downloader) {
            this.a = downloader;
        }

        public final void handleMessage(Message msg) {
            final c retryCandidate = (c) this.a.a.poll();
            if (!retryCandidate.c() && retryCandidate.e()) {
                postDelayed(new Runnable(this) {
                    final /* synthetic */ b b;

                    public final void run() {
                        this.b.a.a.add(retryCandidate);
                        this.b.a.b();
                    }
                }, 3000);
            }
            this.a.b = false;
            this.a.b();
        }
    }

    private static class c {
        private final net.hockeyapp.android.d.b a;
        private final AttachmentView b;
        private boolean c;
        private int d;

        /* synthetic */ c(net.hockeyapp.android.d.b x0, AttachmentView x1, byte b) {
            this(x0, x1);
        }

        private c(net.hockeyapp.android.d.b feedbackAttachment, AttachmentView attachmentView) {
            this.a = feedbackAttachment;
            this.b = attachmentView;
            this.c = false;
            this.d = 2;
        }

        final net.hockeyapp.android.d.b a() {
            return this.a;
        }

        final AttachmentView b() {
            return this.b;
        }

        final boolean c() {
            return this.c;
        }

        final void a(boolean success) {
            this.c = success;
        }

        final boolean d() {
            return this.d > 0;
        }

        final boolean e() {
            int i = this.d - 1;
            this.d = i;
            return i >= 0;
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    private static class d extends AsyncTask<Void, Integer, Boolean> {
        private final c a;
        private final Handler b;
        private final Context c;
        private Bitmap d = null;
        private int e = 1;

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            Boolean bool = (Boolean) obj;
            AttachmentView b = this.a.b();
            this.a.a(bool.booleanValue());
            if (bool.booleanValue()) {
                b.setImage(this.d, this.e);
            } else if (!this.a.d()) {
                b.i();
            }
            this.b.sendEmptyMessage(0);
        }

        protected final /* bridge */ /* synthetic */ void onProgressUpdate(Object[] objArr) {
        }

        d(c downloadJob, Handler handler) {
            this.a = downloadJob;
            this.b = handler;
            this.c = downloadJob.b().getContext();
        }

        protected final void onPreExecute() {
        }

        private Boolean a() {
            net.hockeyapp.android.d.b attachment = this.a.a();
            File file = new File(net.hockeyapp.android.a.b(this.c), attachment.c());
            if (file.exists()) {
                e.e();
                a(file);
                return Boolean.valueOf(true);
            }
            e.e();
            boolean success = a(attachment.b(), file);
            if (success) {
                a(file);
            }
            return Boolean.valueOf(success);
        }

        private void a(File file) {
            try {
                AttachmentView attachmentView = this.a.b();
                this.e = g.a(file);
                this.d = g.a(file, this.e == 0 ? attachmentView.d() : attachmentView.b(), this.e == 0 ? attachmentView.e() : attachmentView.c());
            } catch (IOException e) {
                e.f();
                this.d = null;
            }
        }

        private boolean a(String url, File file) {
            Throwable th;
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                httpURLConnection.addRequestProperty("User-Agent", "HockeySDK/Android 5.1.0");
                httpURLConnection.setInstanceFollowRedirects(true);
                connection = httpURLConnection;
                connection.connect();
                int lengthOfFile = connection.getContentLength();
                String status = connection.getHeaderField("Status");
                if (status == null || status.startsWith("200")) {
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
                                publishProgress(new Integer[]{Integer.valueOf((int) ((100 * total) / ((long) lengthOfFile)))});
                                output2.write(data, 0, count);
                            }
                            output2.flush();
                            boolean z = total > 0;
                            try {
                                output2.close();
                                input2.close();
                            } catch (IOException e) {
                            }
                            if (connection != null) {
                                connection.disconnect();
                            }
                            output = output2;
                            input = input2;
                            return z;
                        } catch (IOException e2) {
                            output = output2;
                            input = input2;
                            try {
                                new StringBuilder("Failed to download attachment to ").append(file);
                                e.f();
                                if (output != null) {
                                    try {
                                        output.close();
                                    } catch (IOException e3) {
                                        if (connection != null) {
                                            connection.disconnect();
                                        }
                                        return false;
                                    }
                                }
                                if (input != null) {
                                    input.close();
                                }
                                if (connection != null) {
                                    connection.disconnect();
                                }
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                if (output != null) {
                                    try {
                                        output.close();
                                    } catch (IOException e4) {
                                        if (connection != null) {
                                            connection.disconnect();
                                        }
                                        throw th;
                                    }
                                }
                                if (input != null) {
                                    input.close();
                                }
                                if (connection != null) {
                                    connection.disconnect();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            output = output2;
                            input = input2;
                            if (output != null) {
                                output.close();
                            }
                            if (input != null) {
                                input.close();
                            }
                            if (connection != null) {
                                connection.disconnect();
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        input = input2;
                        new StringBuilder("Failed to download attachment to ").append(file);
                        e.f();
                        if (output != null) {
                            output.close();
                        }
                        if (input != null) {
                            input.close();
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                        return false;
                    } catch (Throwable th4) {
                        th = th4;
                        input = input2;
                        if (output != null) {
                            output.close();
                        }
                        if (input != null) {
                            input.close();
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                        throw th;
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
                return false;
            } catch (IOException e6) {
                new StringBuilder("Failed to download attachment to ").append(file);
                e.f();
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
                return false;
            }
        }
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public static a a() {
        return a.a;
    }

    private a() {
        this.c = new b(this);
        this.a = new LinkedList();
        this.b = false;
    }

    public final void a(net.hockeyapp.android.d.b feedbackAttachment, AttachmentView attachmentView) {
        this.a.add(new c(feedbackAttachment, attachmentView, (byte) 0));
        b();
    }

    private void b() {
        if (!this.b) {
            c downloadJob = (c) this.a.peek();
            if (downloadJob != null) {
                this.b = true;
                net.hockeyapp.android.f.a.a(new d(downloadJob, this.c));
            }
        }
    }
}
