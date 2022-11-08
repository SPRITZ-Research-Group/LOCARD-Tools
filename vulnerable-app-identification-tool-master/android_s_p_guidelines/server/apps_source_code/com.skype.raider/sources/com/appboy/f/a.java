package com.appboy.f;

import a.a.fg;
import android.content.res.AssetManager;
import android.net.Uri;
import com.adjust.sdk.Constants;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class a {
    public static final List<String> a = Collections.unmodifiableList(Arrays.asList(new String[]{"http", Constants.SCHEME, "ftp", "ftps", "about", "javascript"}));
    private static final String b = c.a(a.class);

    public static void a(File fileOrDirectory) {
        if (fileOrDirectory != null) {
            try {
                if (fileOrDirectory.exists()) {
                    if (fileOrDirectory.isDirectory()) {
                        for (String file : fileOrDirectory.list()) {
                            a(new File(fileOrDirectory, file));
                        }
                    }
                    fileOrDirectory.delete();
                }
            } catch (Throwable e) {
                c.d(b, "Caught exception while trying to delete file or directory " + fileOrDirectory.getName(), e);
            }
        }
    }

    public static boolean a(Uri uri) {
        if (uri == null) {
            c.d(b, "Null Uri received.");
            return false;
        }
        String scheme = uri.getScheme();
        if (!i.c(scheme)) {
            return a.contains(scheme);
        }
        c.d(b, "Null or blank Uri scheme.");
        return false;
    }

    public static boolean b(Uri uri) {
        if (uri == null) {
            c.d(b, "Null Uri received.");
            return false;
        }
        String scheme = uri.getScheme();
        if (i.c(scheme) || scheme.equals("file")) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static File a(String downloadDirectoryAbsolutePath, String remoteFileUrl, String outputFilename, String extension) {
        File file;
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        DataInputStream dataInputStream;
        HttpURLConnection httpURLConnection;
        Throwable e;
        HttpURLConnection httpURLConnection2;
        BufferedOutputStream bufferedOutputStream2 = null;
        if (com.appboy.a.h()) {
            c.d(b, "SDK is offline. File not downloaded for url: " + remoteFileUrl);
            return null;
        } else if (i.c(downloadDirectoryAbsolutePath)) {
            c.d(b, "Download directory null or blank. File not downloaded.");
            return null;
        } else if (i.c(remoteFileUrl)) {
            c.d(b, "Zip file url null or blank. File not downloaded.");
            return null;
        } else if (i.c(outputFilename)) {
            c.d(b, "Output filename null or blank. File not downloaded.");
            return null;
        } else {
            DataInputStream dataInputStream2;
            BufferedOutputStream bufferedOutputStream3;
            try {
                new File(downloadDirectoryAbsolutePath).mkdirs();
                if (!i.c(extension)) {
                    outputFilename = outputFilename + extension;
                }
                file = new File(downloadDirectoryAbsolutePath, outputFilename);
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) fg.a(new URL(remoteFileUrl));
                try {
                    int responseCode = httpURLConnection3.getResponseCode();
                    if (responseCode != 200) {
                        c.b(b, "HTTP response code was " + responseCode + ". File with url " + remoteFileUrl + " could not be downloaded.");
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        return null;
                    }
                    byte[] bArr = new byte[8192];
                    dataInputStream2 = new DataInputStream(httpURLConnection3.getInputStream());
                    try {
                        bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(file));
                        while (true) {
                            try {
                                int read = dataInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                bufferedOutputStream3.write(bArr, 0, read);
                            } catch (Throwable e2) {
                                th = e2;
                                bufferedOutputStream = bufferedOutputStream3;
                                dataInputStream = dataInputStream2;
                                httpURLConnection = httpURLConnection3;
                                e = th;
                            } catch (Throwable th2) {
                                httpURLConnection2 = httpURLConnection3;
                                e = th2;
                                bufferedOutputStream2 = bufferedOutputStream3;
                            }
                        }
                    } catch (Throwable e22) {
                        dataInputStream = dataInputStream2;
                        httpURLConnection = httpURLConnection3;
                        e = e22;
                        bufferedOutputStream = null;
                        try {
                            c.d(b, "Exception during download of file from url.", e);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                } catch (Throwable e3) {
                                    c.d(b, "Exception during closing of file download streams.", e3);
                                    return null;
                                }
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            return null;
                        } catch (Throwable th3) {
                            e3 = th3;
                            bufferedOutputStream2 = bufferedOutputStream;
                            httpURLConnection2 = httpURLConnection;
                            dataInputStream2 = dataInputStream;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (Throwable th22) {
                                    c.d(b, "Exception during closing of file download streams.", th22);
                                    throw e3;
                                }
                            }
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.close();
                            }
                            throw e3;
                        }
                    } catch (Throwable e222) {
                        th = e222;
                        httpURLConnection2 = httpURLConnection3;
                        e3 = th;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        throw e3;
                    }
                } catch (Throwable e2222) {
                    dataInputStream = null;
                    httpURLConnection = httpURLConnection3;
                    e3 = e2222;
                    bufferedOutputStream = null;
                    c.d(b, "Exception during download of file from url.", e3);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    return null;
                } catch (Throwable e22222) {
                    dataInputStream2 = null;
                    th = e22222;
                    httpURLConnection2 = httpURLConnection3;
                    e3 = th;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    throw e3;
                }
            } catch (Exception e4) {
                e3 = e4;
                bufferedOutputStream = null;
                dataInputStream = null;
                httpURLConnection = null;
                c.d(b, "Exception during download of file from url.", e3);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return null;
            } catch (Throwable th4) {
                e3 = th4;
                dataInputStream2 = null;
                httpURLConnection2 = null;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                throw e3;
            }
        }
        return file;
    }

    public static String a(AssetManager assetManager, String assetPath) {
        Throwable e;
        Object obj;
        Throwable th;
        String str = null;
        InputStream open;
        BufferedReader obj2;
        try {
            open = assetManager.open(assetPath);
            try {
                obj2 = new BufferedReader(new InputStreamReader(open, Constants.ENCODING));
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (true) {
                        String readLine = obj2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine).append(10);
                    }
                    str = stringBuilder.toString();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable e2) {
                            c.d(b, "Exception attempting to close file download streams for path:" + assetPath, e2);
                        }
                    }
                    obj2.close();
                } catch (Exception e3) {
                    e2 = e3;
                }
            } catch (Exception e4) {
                e2 = e4;
                obj2 = str;
                try {
                    c.d(b, "Exception attempting to get asset content for " + assetPath, e2);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable e22) {
                            c.d(b, "Exception attempting to close file download streams for path:" + assetPath, e22);
                        }
                    }
                    if (obj2 != null) {
                        obj2.close();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable e222) {
                            c.d(b, "Exception attempting to close file download streams for path:" + assetPath, e222);
                            throw th;
                        }
                    }
                    if (obj2 != null) {
                        obj2.close();
                    }
                    throw th;
                }
            } catch (Throwable e2222) {
                obj2 = str;
                th = e2222;
                if (open != null) {
                    open.close();
                }
                if (obj2 != null) {
                    obj2.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            e2222 = e5;
            obj2 = str;
            Object obj3 = str;
        } catch (Throwable e22222) {
            obj2 = str;
            open = str;
            th = e22222;
            if (open != null) {
                open.close();
            }
            if (obj2 != null) {
                obj2.close();
            }
            throw th;
        }
        return str;
    }
}
