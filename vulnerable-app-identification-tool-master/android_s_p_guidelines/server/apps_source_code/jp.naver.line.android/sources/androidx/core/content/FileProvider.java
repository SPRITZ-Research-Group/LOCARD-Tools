package androidx.core.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class FileProvider extends ContentProvider {
    private static final String[] a = new String[]{"_display_name", "_size"};
    private static final File b = new File("/");
    private static HashMap<String, b> c = new HashMap();
    private b d;

    public boolean onCreate() {
        return true;
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            this.d = a(context, providerInfo.authority);
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    public static Uri a(Context context, String str, File file) {
        return a(context, str).a(file);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        File a = this.d.a(uri);
        if (strArr == null) {
            strArr = a;
        }
        Object obj = new String[strArr.length];
        Object obj2 = new Object[strArr.length];
        int i = 0;
        for (Object obj3 : strArr) {
            int i2;
            if ("_display_name".equals(obj3)) {
                obj[i] = "_display_name";
                i2 = i + 1;
                obj2[i] = a.getName();
            } else if ("_size".equals(obj3)) {
                obj[i] = "_size";
                i2 = i + 1;
                obj2[i] = Long.valueOf(a.length());
            } else {
            }
            i = i2;
        }
        Object obj4 = new String[i];
        System.arraycopy(obj, 0, obj4, 0, i);
        Object obj5 = new Object[i];
        System.arraycopy(obj2, 0, obj5, 0, i);
        Cursor matrixCursor = new MatrixCursor(obj4, 1);
        matrixCursor.addRow(obj5);
        return matrixCursor;
    }

    public String getType(Uri uri) {
        File a = this.d.a(uri);
        int lastIndexOf = a.getName().lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(a.getName().substring(lastIndexOf + 1));
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
        }
        return "application/octet-stream";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return this.d.a(uri).delete() ? 1 : 0;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        int i;
        File a = this.d.a(uri);
        if ("r".equals(str)) {
            i = 268435456;
        } else if ("w".equals(str) || "wt".equals(str)) {
            i = 738197504;
        } else if ("wa".equals(str)) {
            i = 704643072;
        } else if ("rw".equals(str)) {
            i = 939524096;
        } else if ("rwt".equals(str)) {
            i = 1006632960;
        } else {
            throw new IllegalArgumentException("Invalid mode: ".concat(String.valueOf(str)));
        }
        return ParcelFileDescriptor.open(a, i);
    }

    private static b a(Context context, String str) {
        b bVar;
        synchronized (c) {
            bVar = (b) c.get(str);
            if (bVar == null) {
                try {
                    bVar = new c(str);
                    XmlResourceParser loadXmlMetaData = context.getPackageManager().resolveContentProvider(str, 128).loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
                    if (loadXmlMetaData != null) {
                        while (true) {
                            int next = loadXmlMetaData.next();
                            if (next == 1) {
                                break;
                            } else if (next == 2) {
                                String name = loadXmlMetaData.getName();
                                File file = null;
                                String attributeValue = loadXmlMetaData.getAttributeValue(null, "name");
                                String attributeValue2 = loadXmlMetaData.getAttributeValue(null, "path");
                                File[] a;
                                if ("root-path".equals(name)) {
                                    file = b;
                                } else if ("files-path".equals(name)) {
                                    file = context.getFilesDir();
                                } else if ("cache-path".equals(name)) {
                                    file = context.getCacheDir();
                                } else if ("external-path".equals(name)) {
                                    file = Environment.getExternalStorageDirectory();
                                } else if ("external-files-path".equals(name)) {
                                    a = a.a(context);
                                    if (a.length > 0) {
                                        file = a[0];
                                    }
                                } else if ("external-cache-path".equals(name)) {
                                    a = a.b(context);
                                    if (a.length > 0) {
                                        file = a[0];
                                    }
                                } else if (VERSION.SDK_INT >= 21 && "external-media-path".equals(name)) {
                                    a = context.getExternalMediaDirs();
                                    if (a.length > 0) {
                                        file = a[0];
                                    }
                                }
                                if (file != null) {
                                    bVar.a(attributeValue, a(file, attributeValue2));
                                }
                            }
                        }
                        c.put(str, bVar);
                    } else {
                        throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
                    }
                } catch (Throwable e) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);
                } catch (Throwable e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                }
            }
        }
        return bVar;
    }

    private static File a(File file, String... strArr) {
        File file2 = file;
        for (int i = 0; i <= 0; i++) {
            String str = strArr[0];
            if (str != null) {
                file2 = new File(file2, str);
            }
        }
        return file2;
    }
}
