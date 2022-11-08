package com.appboy.f;

import a.a.fg;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appboy.a;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class b {
    private static final String a = c.a(b.class);

    public static Bitmap a(Uri uri) {
        return a(null, uri, null);
    }

    public static Bitmap a(Context context, Uri uri, com.appboy.b.b viewBounds) {
        if (uri == null) {
            c.d(a, "Null Uri received. Not getting image.");
            return null;
        } else if (a.b(uri)) {
            return b(uri);
        } else {
            if (!a.a(uri)) {
                c.f(a, "Uri with unknown scheme received. Not getting image.");
                return null;
            } else if (context == null || viewBounds == null) {
                return a(uri, 0, 0);
            } else {
                DisplayMetrics a = a(context);
                return a(uri, a(a.densityDpi, viewBounds.b()), a(a.densityDpi, viewBounds.a()));
            }
        }
    }

    public static DisplayMetrics a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int a(int dpi, int dp) {
        return Math.abs((dpi * dp) / 160);
    }

    private static Bitmap a(Uri uri, int i, int i2) {
        Throwable e;
        Throwable th;
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        String uri2 = uri.toString();
        if (a.h()) {
            c.d(a, "SDK is in offline mode, not downloading remote bitmap with uri: " + uri2);
            return null;
        }
        InputStream inputStream2;
        try {
            URL url = new URL(uri2);
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) fg.a(url);
            try {
                int responseCode = httpURLConnection3.getResponseCode();
                if (responseCode != 200) {
                    c.f(a, "HTTP response code was " + responseCode + ". Bitmap with url " + url + " could not be downloaded.");
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                inputStream2 = httpURLConnection3.getInputStream();
                Bitmap decodeStream;
                if (i == 0 || i2 == 0) {
                    decodeStream = BitmapFactory.decodeStream(inputStream2);
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Throwable e2) {
                            c.d(a, "IOException during closing of bitmap metadata download stream.", e2);
                        }
                    }
                    return decodeStream;
                }
                try {
                    c.b(a, "Sampling bitmap with destination image bounds: (height " + i2 + " width " + i + ")");
                    Options options = new Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(inputStream2, null, options);
                    httpURLConnection3.disconnect();
                    httpURLConnection3 = (HttpURLConnection) fg.a(url);
                    inputStream2 = httpURLConnection3.getInputStream();
                    if (options.outHeight == 0 || options.outWidth == 0) {
                        c.f(a, "The bitmap metadata with image url " + url + " had bounds: (height " + options.outHeight + " width " + options.outWidth + "). Returning a bitmap with no sampling.");
                        decodeStream = BitmapFactory.decodeStream(inputStream2);
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (Throwable e22) {
                                c.d(a, "IOException during closing of bitmap metadata download stream.", e22);
                            }
                        }
                        return decodeStream;
                    }
                    options.inSampleSize = a(options, i, i2);
                    options.inJustDecodeBounds = false;
                    decodeStream = BitmapFactory.decodeStream(inputStream2, null, options);
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Throwable e222) {
                            c.d(a, "IOException during closing of bitmap metadata download stream.", e222);
                        }
                    }
                    return decodeStream;
                } catch (Throwable e3) {
                    th = e3;
                    inputStream = inputStream2;
                    httpURLConnection = httpURLConnection3;
                    e222 = th;
                } catch (Throwable e32) {
                    th = e32;
                    httpURLConnection2 = httpURLConnection3;
                    e222 = th;
                    try {
                        c.d(a, "Unknown Host Exception in image bitmap download for Uri: " + uri2 + ". Device may be offline.", e222);
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (Throwable e2222) {
                                c.d(a, "IOException during closing of bitmap metadata download stream.", e2222);
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        e2222 = th2;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (Throwable e322) {
                                c.d(a, "IOException during closing of bitmap metadata download stream.", e322);
                            }
                        }
                        throw e2222;
                    }
                } catch (Throwable e3222) {
                    th = e3222;
                    httpURLConnection2 = httpURLConnection3;
                    e2222 = th;
                    c.d(a, "Malformed URL Exception in image bitmap download for Uri: " + uri2 + ". Image Uri may be corrupted.", e2222);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Throwable e22222) {
                            c.d(a, "IOException during closing of bitmap metadata download stream.", e22222);
                        }
                    }
                    return null;
                } catch (Throwable e32222) {
                    th = e32222;
                    httpURLConnection2 = httpURLConnection3;
                    e22222 = th;
                    c.d(a, "Exception in image bitmap download for Uri: " + uri2, e22222);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Throwable e222222) {
                            c.d(a, "IOException during closing of bitmap metadata download stream.", e222222);
                        }
                    }
                    return null;
                } catch (Throwable e322222) {
                    th = e322222;
                    httpURLConnection2 = httpURLConnection3;
                    e222222 = th;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    throw e222222;
                }
            } catch (Throwable e3222222) {
                httpURLConnection = httpURLConnection3;
                e222222 = e3222222;
                inputStream = null;
            } catch (Throwable e32222222) {
                inputStream2 = null;
                th = e32222222;
                httpURLConnection2 = httpURLConnection3;
                e222222 = th;
                c.d(a, "Unknown Host Exception in image bitmap download for Uri: " + uri2 + ". Device may be offline.", e222222);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                return null;
            } catch (Throwable e322222222) {
                inputStream2 = null;
                th = e322222222;
                httpURLConnection2 = httpURLConnection3;
                e222222 = th;
                c.d(a, "Malformed URL Exception in image bitmap download for Uri: " + uri2 + ". Image Uri may be corrupted.", e222222);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                return null;
            } catch (Throwable e3222222222) {
                inputStream2 = null;
                th = e3222222222;
                httpURLConnection2 = httpURLConnection3;
                e222222 = th;
                c.d(a, "Exception in image bitmap download for Uri: " + uri2, e222222);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                return null;
            } catch (Throwable e32222222222) {
                inputStream2 = null;
                th = e32222222222;
                httpURLConnection2 = httpURLConnection3;
                e222222 = th;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                throw e222222;
            }
        } catch (OutOfMemoryError e4) {
            e222222 = e4;
            inputStream = null;
            httpURLConnection = null;
            try {
                c.d(a, "Out of Memory Error in image bitmap download for Uri: " + uri2 + ".", e222222);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2222222) {
                        c.d(a, "IOException during closing of bitmap metadata download stream.", e2222222);
                    }
                }
                return null;
            } catch (Throwable th3) {
                e2222222 = th3;
                InputStream inputStream3 = inputStream;
                httpURLConnection2 = httpURLConnection;
                inputStream2 = inputStream3;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                throw e2222222;
            }
        } catch (UnknownHostException e5) {
            e2222222 = e5;
            inputStream2 = null;
            httpURLConnection2 = null;
            c.d(a, "Unknown Host Exception in image bitmap download for Uri: " + uri2 + ". Device may be offline.", e2222222);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            return null;
        } catch (MalformedURLException e6) {
            e2222222 = e6;
            inputStream2 = null;
            httpURLConnection2 = null;
            c.d(a, "Malformed URL Exception in image bitmap download for Uri: " + uri2 + ". Image Uri may be corrupted.", e2222222);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            return null;
        } catch (Exception e7) {
            e2222222 = e7;
            inputStream2 = null;
            httpURLConnection2 = null;
            c.d(a, "Exception in image bitmap download for Uri: " + uri2, e2222222);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            return null;
        } catch (Throwable th4) {
            e2222222 = th4;
            inputStream2 = null;
            httpURLConnection2 = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            throw e2222222;
        }
    }

    private static Bitmap b(Uri uri) {
        try {
            File file = new File(uri.getPath());
            if (file.exists()) {
                c.d(a, "Retrieving image from path: " + file.getAbsolutePath());
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }
        } catch (Throwable e) {
            c.d(a, "Out of Memory Error in local bitmap file retrieval for Uri: " + uri.toString() + ".", e);
        } catch (Throwable e2) {
            c.d(a, "Exception occurred when attempting to retrieve local bitmap.", e2);
        } catch (Throwable e22) {
            c.d(a, "Throwable caught in local bitmap file retrieval for Uri: " + uri.toString(), e22);
        }
        return null;
    }

    public static int a() {
        return Math.max(1024, Math.min((int) Math.min(Runtime.getRuntime().maxMemory() / 8, 2147483647L), 33554432));
    }

    @VisibleForTesting
    private static int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        c.b(a, "Calculating sample size for source image bounds: (height " + options.outHeight + " width " + options.outWidth + ") and destination image bounds: (height " + i2 + " width " + i + ")");
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i3 /= 2;
            i4 /= 2;
            while (i3 / i5 >= i2 && i4 / i5 >= i) {
                i5 *= 2;
            }
        }
        c.b(a, "Using image sample size of " + i5);
        return i5;
    }
}
