package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

@Class(creator = "BitmapTeleporterCreator")
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<BitmapTeleporter> CREATOR = new a();
    @VersionField(id = 1)
    private final int a;
    @Field(id = 2)
    private ParcelFileDescriptor b;
    @Field(id = 3)
    private final int c;
    private Bitmap d = null;
    private boolean e = false;
    private File f;

    @Constructor
    BitmapTeleporter(@Param(id = 1) int i, @Param(id = 2) ParcelFileDescriptor parcelFileDescriptor, @Param(id = 3) int i2) {
        this.a = i;
        this.b = parcelFileDescriptor;
        this.c = i2;
    }

    private final FileOutputStream a() {
        if (this.f == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File createTempFile = File.createTempFile("teleporter", FileType.TEMP, this.f);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                this.b = ParcelFileDescriptor.open(createTempFile, ErrorDialogData.BINDER_CRASH);
                createTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (Throwable e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    private static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.b == null) {
            Bitmap bitmap = this.d;
            Buffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            Closeable dataOutputStream = new DataOutputStream(new BufferedOutputStream(a()));
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(array);
                a(dataOutputStream);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                a(dataOutputStream);
            }
        }
        int i2 = i | 1;
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b, i2);
        b.a(parcel, 3, this.c);
        b.a(parcel, a);
        this.b = null;
    }
}
