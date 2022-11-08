package com.appboy.f;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class k {
    private static final String a = c.a(k.class);

    public static String a(File localDirectory, String remoteZipUrl) {
        if (localDirectory == null) {
            c.f(a, "Internal cache directory is null. No local URL will be created.");
            return null;
        } else if (i.c(remoteZipUrl)) {
            c.f(a, "Remote zip url is null or empty. No local URL will be created.");
            return null;
        } else {
            String absolutePath = localDirectory.getAbsolutePath();
            String valueOf = String.valueOf(f.a());
            absolutePath = absolutePath + "/" + valueOf;
            c.b(a, "Starting download of url: " + remoteZipUrl);
            File a = a.a(absolutePath, remoteZipUrl, valueOf, ".zip");
            if (a == null) {
                c.b(a, "Could not download zip file to local storage.");
                a.a(new File(absolutePath));
                return null;
            }
            c.b(a, "Html content zip downloaded.");
            if (a(absolutePath, a)) {
                c.b(a, "Html content zip unpacked.");
                return absolutePath;
            }
            c.f(a, "Error during the zip unpack.");
            a.a(new File(absolutePath));
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @VisibleForTesting
    private static boolean a(String str, File file) {
        Throwable e;
        ZipInputStream zipInputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream = null;
        if (i.c(str)) {
            c.d(a, "Unpack directory null or blank. Zip file not unpacked.");
            return false;
        } else if (file == null) {
            c.d(a, "Zip file is null. Zip file not unpacked.");
            return false;
        } else {
            new File(str).mkdirs();
            ZipInputStream zipInputStream2;
            try {
                zipInputStream2 = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        ZipEntry nextEntry = zipInputStream2.getNextEntry();
                        if (nextEntry == null) {
                            break;
                        }
                        String name = nextEntry.getName();
                        if (!name.toLowerCase(Locale.US).startsWith("__macosx")) {
                            name = str + "/" + name;
                            if (nextEntry.isDirectory()) {
                                new File(name).mkdirs();
                            } else {
                                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(name));
                                while (true) {
                                    try {
                                        int read = zipInputStream2.read(bArr);
                                        if (read == -1) {
                                            bufferedOutputStream2.close();
                                            zipInputStream2.closeEntry();
                                            bufferedOutputStream = bufferedOutputStream2;
                                            break;
                                        }
                                        bufferedOutputStream2.write(bArr, 0, read);
                                    } catch (FileNotFoundException e2) {
                                        e = e2;
                                        bufferedOutputStream = bufferedOutputStream2;
                                        zipInputStream = zipInputStream2;
                                    } catch (IOException e3) {
                                        e = e3;
                                        bufferedOutputStream = bufferedOutputStream2;
                                    } catch (Exception e4) {
                                        e = e4;
                                        bufferedOutputStream = bufferedOutputStream2;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        bufferedOutputStream = bufferedOutputStream2;
                                    }
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                    zipInputStream = zipInputStream2;
                } catch (IOException e6) {
                    e = e6;
                } catch (Exception e7) {
                    e = e7;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                zipInputStream = null;
                try {
                    c.d(a, "FileNotFoundException during unpack of zip file.", e);
                    if (zipInputStream != null) {
                        try {
                            zipInputStream.close();
                        } catch (Throwable e9) {
                            c.d(a, "IOException during closing of zip file unpacking streams.", e9);
                            return false;
                        }
                    }
                    if (bufferedOutputStream == null) {
                        return false;
                    }
                    bufferedOutputStream.close();
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    zipInputStream2 = zipInputStream;
                    if (zipInputStream2 != null) {
                        try {
                            zipInputStream2.close();
                        } catch (Throwable e92) {
                            c.d(a, "IOException during closing of zip file unpacking streams.", e92);
                            throw th;
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e10) {
                e92 = e10;
                zipInputStream2 = null;
                try {
                    c.d(a, "IOException during unpack of zip file.", e92);
                    if (zipInputStream2 != null) {
                        try {
                            zipInputStream2.close();
                        } catch (Throwable e922) {
                            c.d(a, "IOException during closing of zip file unpacking streams.", e922);
                            return false;
                        }
                    }
                    if (bufferedOutputStream == null) {
                        return false;
                    }
                    bufferedOutputStream.close();
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    if (zipInputStream2 != null) {
                        zipInputStream2.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e11) {
                e922 = e11;
                zipInputStream2 = null;
                c.d(a, "Exception during unpack of zip file.", e922);
                if (zipInputStream2 != null) {
                    try {
                        zipInputStream2.close();
                    } catch (Throwable e9222) {
                        c.d(a, "IOException during closing of zip file unpacking streams.", e9222);
                        return false;
                    }
                }
                if (bufferedOutputStream == null) {
                    return false;
                }
                bufferedOutputStream.close();
                return false;
            } catch (Throwable th5) {
                th = th5;
                zipInputStream2 = null;
                if (zipInputStream2 != null) {
                    zipInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        return true;
    }

    public static File a(Context context) {
        return new File(context.getCacheDir().getPath() + "/appboy-html-inapp-messages");
    }
}
