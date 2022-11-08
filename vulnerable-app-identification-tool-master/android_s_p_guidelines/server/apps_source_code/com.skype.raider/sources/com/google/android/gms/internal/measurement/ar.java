package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import java.util.ArrayList;
import java.util.List;

public final class ar extends ct {
    private final as a = new as(this, k(), "google_app_measurement_local.db");
    private boolean b;

    ar(bx bxVar) {
        super(bxVar);
    }

    @WorkerThread
    private final boolean a(int i, byte[] bArr) {
        c();
        if (this.b) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 5;
        int i3 = 0;
        while (true) {
            int i4 = i2;
            if (i3 < 5) {
                SQLiteDatabase sQLiteDatabase = null;
                Cursor cursor = null;
                try {
                    sQLiteDatabase = w();
                    if (sQLiteDatabase == null) {
                        this.b = true;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        return false;
                    }
                    sQLiteDatabase.beginTransaction();
                    long j = 0;
                    cursor = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                    if (cursor != null && cursor.moveToFirst()) {
                        j = cursor.getLong(0);
                    }
                    if (j >= 100000) {
                        q().v().a("Data loss, local db full");
                        j = (100000 - j) + 1;
                        long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j)});
                        if (delete != j) {
                            q().v().a("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j), Long.valueOf(delete), Long.valueOf(j - delete));
                        }
                    }
                    sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return true;
                } catch (SQLiteFullException e) {
                    q().v().a("Error writing entry to local database", e);
                    this.b = true;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (SQLiteDatabaseLockedException e2) {
                    SystemClock.sleep((long) i4);
                    i4 += 20;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (SQLiteException e3) {
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    q().v().a("Error writing entry to local database", e3);
                    this.b = true;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                }
            } else {
                q().y().a("Failed to write entry to local database");
                return false;
            }
            i2 = i3 + 1;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase w() throws SQLiteException {
        if (this.b) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.b = true;
        return null;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final boolean a(zzed zzed) {
        n();
        byte[] a = ew.a((Parcelable) zzed);
        if (a.length <= 131072) {
            return a(2, a);
        }
        q().y().a("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean a(zzeu zzeu) {
        Parcel obtain = Parcel.obtain();
        zzeu.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return a(0, marshall);
        }
        q().y().a("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean a(zzjx zzjx) {
        Parcel obtain = Parcel.obtain();
        zzjx.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return a(1, marshall);
        }
        q().y().a("User property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    public final /* bridge */ /* synthetic */ af g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ di h() {
        return super.h();
    }

    public final /* bridge */ /* synthetic */ df i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ e j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ Context k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ar l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ at m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ ew n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ee o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ bs p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ av q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ bf r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    protected final boolean t() {
        return false;
    }

    public final List<AbstractSafeParcelable> v() {
        int i;
        int i2;
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor;
        Cursor cursor2;
        Throwable th;
        SQLiteException sQLiteException;
        Object e;
        Throwable th2;
        c();
        if (this.b) {
            return null;
        }
        List<AbstractSafeParcelable> arrayList = new ArrayList();
        if (!k().getDatabasePath("google_app_measurement_local.db").exists()) {
            return arrayList;
        }
        i = 0;
        while (true) {
            i2 = i;
            if (i2 < 5) {
                sQLiteDatabase = null;
                try {
                    SQLiteDatabase w = w();
                    if (w == null) {
                        try {
                            this.b = true;
                            if (w != null) {
                                w.close();
                            }
                            return null;
                        } catch (SQLiteFullException e2) {
                            sQLiteDatabase2 = w;
                            SQLiteFullException e3 = e2;
                            cursor = null;
                        } catch (SQLiteDatabaseLockedException e4) {
                            cursor2 = null;
                            sQLiteDatabase = w;
                            try {
                                SystemClock.sleep((long) 5);
                                i = 5 + 20;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                i2++;
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (SQLiteException e5) {
                            cursor2 = null;
                            sQLiteException = e5;
                            sQLiteDatabase = w;
                            SQLiteException e6 = sQLiteException;
                            if (sQLiteDatabase != null) {
                                if (sQLiteDatabase.inTransaction()) {
                                    sQLiteDatabase.endTransaction();
                                }
                            }
                            q().v().a("Error reading entries from local database", e6);
                            this.b = true;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                                i = 5;
                                i2++;
                            }
                            i = 5;
                            i2++;
                        } catch (Throwable th4) {
                            cursor2 = null;
                            th2 = th4;
                            sQLiteDatabase = w;
                            th = th2;
                        }
                    } else {
                        w.beginTransaction();
                        cursor2 = w.query("messages", new String[]{"rowid", "type", "entry"}, null, null, null, null, "rowid asc", Integer.toString(100));
                        long j = -1;
                        while (cursor2.moveToNext()) {
                            try {
                                j = cursor2.getLong(0);
                                int i3 = cursor2.getInt(1);
                                byte[] blob = cursor2.getBlob(2);
                                Parcel obtain;
                                Object obj;
                                if (i3 == 0) {
                                    Parcel obtain2 = Parcel.obtain();
                                    try {
                                        obtain2.unmarshall(blob, 0, blob.length);
                                        obtain2.setDataPosition(0);
                                        zzeu zzeu = (zzeu) zzeu.CREATOR.createFromParcel(obtain2);
                                        obtain2.recycle();
                                        if (zzeu != null) {
                                            arrayList.add(zzeu);
                                        }
                                    } catch (a e7) {
                                        q().v().a("Failed to load event from local database");
                                        obtain2.recycle();
                                    } catch (Throwable th42) {
                                        obtain2.recycle();
                                        throw th42;
                                    }
                                } else if (i3 == 1) {
                                    obtain = Parcel.obtain();
                                    try {
                                        obtain.unmarshall(blob, 0, blob.length);
                                        obtain.setDataPosition(0);
                                        obj = (zzjx) zzjx.CREATOR.createFromParcel(obtain);
                                        obtain.recycle();
                                    } catch (a e8) {
                                        q().v().a("Failed to load user property from local database");
                                        obtain.recycle();
                                        obj = null;
                                    } catch (Throwable th422) {
                                        obtain.recycle();
                                        throw th422;
                                    }
                                    if (obj != null) {
                                        arrayList.add(obj);
                                    }
                                } else if (i3 == 2) {
                                    obtain = Parcel.obtain();
                                    try {
                                        obtain.unmarshall(blob, 0, blob.length);
                                        obtain.setDataPosition(0);
                                        obj = (zzed) zzed.CREATOR.createFromParcel(obtain);
                                        obtain.recycle();
                                    } catch (a e9) {
                                        q().v().a("Failed to load user property from local database");
                                        obtain.recycle();
                                        obj = null;
                                    } catch (Throwable th4222) {
                                        obtain.recycle();
                                        throw th4222;
                                    }
                                    if (obj != null) {
                                        arrayList.add(obj);
                                    }
                                } else {
                                    q().v().a("Unknown record type in local database");
                                }
                            } catch (SQLiteFullException e22) {
                                SQLiteFullException sQLiteFullException = e22;
                                cursor = cursor2;
                                sQLiteDatabase2 = w;
                                e6 = sQLiteFullException;
                            } catch (SQLiteDatabaseLockedException e10) {
                                sQLiteDatabase = w;
                            } catch (SQLiteException e52) {
                                sQLiteException = e52;
                                sQLiteDatabase = w;
                                e6 = sQLiteException;
                            } catch (Throwable th42222) {
                                th2 = th42222;
                                sQLiteDatabase = w;
                                th = th2;
                            }
                        }
                        if (w.delete("messages", "rowid <= ?", new String[]{Long.toString(j)}) < arrayList.size()) {
                            q().v().a("Fewer entries removed from local database than expected");
                        }
                        w.setTransactionSuccessful();
                        w.endTransaction();
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (w != null) {
                            w.close();
                        }
                        return arrayList;
                    }
                } catch (SQLiteFullException e11) {
                    e6 = e11;
                    sQLiteDatabase2 = null;
                    cursor = null;
                } catch (SQLiteDatabaseLockedException e12) {
                    cursor2 = null;
                    SystemClock.sleep((long) 5);
                    i = 5 + 20;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    i2++;
                } catch (SQLiteException e13) {
                    e6 = e13;
                    cursor2 = null;
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    q().v().a("Error reading entries from local database", e6);
                    this.b = true;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                        i = 5;
                        i2++;
                    }
                    i = 5;
                    i2++;
                } catch (Throwable th5) {
                    th = th5;
                    cursor2 = null;
                }
            } else {
                q().y().a("Failed to read events from database in reasonable time");
                return null;
            }
            i2++;
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        throw th;
        try {
            q().v().a("Error reading entries from local database", e6);
            this.b = true;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase2 != null) {
                sQLiteDatabase2.close();
                i = 5;
                i2++;
            }
            i = 5;
            i2++;
        } catch (Throwable th6) {
            th = th6;
            Cursor cursor3 = cursor;
            sQLiteDatabase = sQLiteDatabase2;
            cursor2 = cursor3;
        }
    }
}
