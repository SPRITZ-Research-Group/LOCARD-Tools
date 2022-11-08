package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;

public final class a extends d {
    private final int g;

    protected class a extends b {
        final /* synthetic */ a a;
        private File c;
        private final int d;

        a(a this$0, d soSource) throws IOException {
            this.a = this$0;
            super(this$0, soSource);
            this.c = new File(this$0.e.getApplicationInfo().nativeLibraryDir);
            this.d = this$0.g;
        }

        protected final boolean a(ZipEntry ze, String soName) {
            String zipPath = ze.getName();
            if (soName.equals(this.a.f)) {
                this.a.f = null;
                String.format("allowing consideration of corrupted lib %s", new Object[]{soName});
                return true;
            } else if ((this.d & 1) == 0) {
                new StringBuilder("allowing consideration of ").append(zipPath).append(": self-extraction preferred");
                return true;
            } else {
                File sysLibFile = new File(this.c, soName);
                if (sysLibFile.isFile()) {
                    if (sysLibFile.length() != ze.getSize()) {
                        String.format("allowing consideration of %s: sysdir file length is %s, but the file is %s bytes long in the APK", new Object[]{sysLibFile, Long.valueOf(sysLibFile.length()), Long.valueOf(ze.getSize())});
                        return true;
                    }
                    new StringBuilder("not allowing consideration of ").append(zipPath).append(": deferring to libdir");
                    return false;
                }
                String.format("allowing considering of %s: %s not in system lib dir", new Object[]{zipPath, soName});
                return true;
            }
        }
    }

    public a(Context context, String name, int flags) {
        super(context, name, new File(context.getApplicationInfo().sourceDir), "^lib/([^/]+)/([^/]+\\.so)$");
        this.g = flags;
    }

    protected final e a() throws IOException {
        return new a(this, this);
    }

    protected final byte[] b() throws IOException {
        File apkFile = this.c.getCanonicalFile();
        Parcel parcel = Parcel.obtain();
        try {
            byte[] marshall;
            parcel.writeByte((byte) 2);
            parcel.writeString(apkFile.getPath());
            parcel.writeLong(apkFile.lastModified());
            parcel.writeInt(SysUtil.a(this.e));
            if ((this.g & 1) == 0) {
                parcel.writeByte((byte) 0);
                marshall = parcel.marshall();
            } else {
                String nativeLibraryDir = this.e.getApplicationInfo().nativeLibraryDir;
                if (nativeLibraryDir == null) {
                    parcel.writeByte((byte) 1);
                    marshall = parcel.marshall();
                    parcel.recycle();
                } else {
                    File canonicalFile = new File(nativeLibraryDir).getCanonicalFile();
                    if (canonicalFile.exists()) {
                        parcel.writeByte((byte) 2);
                        parcel.writeString(canonicalFile.getPath());
                        parcel.writeLong(canonicalFile.lastModified());
                        marshall = parcel.marshall();
                        parcel.recycle();
                    } else {
                        parcel.writeByte((byte) 1);
                        marshall = parcel.marshall();
                        parcel.recycle();
                    }
                }
            }
            return marshall;
        } finally {
            parcel.recycle();
        }
    }
}
