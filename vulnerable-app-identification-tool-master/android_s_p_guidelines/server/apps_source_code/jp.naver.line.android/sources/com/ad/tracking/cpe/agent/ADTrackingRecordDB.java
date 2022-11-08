package com.ad.tracking.cpe.agent;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ADTrackingRecordDB extends ContentProvider {
    private static String DATABASE_NAME = "MissionRecord";
    private static final String DATABASE_TABLE = "MissionStatus";
    private static int DATABASE_VERSION = 1;
    private Context mContext;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase readableDatabase;

    class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, ADTrackingRecordDB.DATABASE_NAME, null, ADTrackingRecordDB.DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS MissionStatus");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MissionStatus(id INTEGER PRIMARY KEY AUTOINCREMENT,app_id TEXT,status TEXT,msg TEXT,error TEXT,err TEXT);");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i < i2) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS MissionStatus");
                onCreate(sQLiteDatabase);
            }
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public ADTrackingRecordDB(Context context) {
        this.mContext = context;
    }

    public ADTrackingRecordDB open() throws SQLException {
        this.mDbHelper = new DatabaseHelper(this.mContext);
        try {
            this.readableDatabase = this.mDbHelper.getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isComplete() {
        Exception e;
        StringBuilder stringBuilder;
        Throwable th;
        if (this.readableDatabase != null) {
            Cursor query;
            Cursor cursor = null;
            try {
                query = this.readableDatabase.query(DATABASE_TABLE, new String[]{"app_id", "status", "msg", "error", "err"}, null, null, null, null, null);
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    stringBuilder = new StringBuilder("[Error] isComplete SQLException");
                    stringBuilder.append(e.getMessage());
                    ADTLogUtil.e(stringBuilder.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            try {
                if (query.isAfterLast()) {
                    ADTLogUtil.v("DB: No Record");
                    if (query != null) {
                        query.close();
                    }
                    return false;
                }
                query.moveToNext();
                if (query != null) {
                    query.close();
                }
                return true;
            } catch (Exception e3) {
                Cursor cursor2 = query;
                e = e3;
                cursor = cursor2;
                e.printStackTrace();
                stringBuilder = new StringBuilder("[Error] isComplete SQLException");
                stringBuilder.append(e.getMessage());
                ADTLogUtil.e(stringBuilder.toString());
                if (cursor != null) {
                    cursor.close();
                }
                return true;
            } catch (Throwable th3) {
                th = th3;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return true;
    }

    public void showDB() {
        Exception e;
        Throwable th;
        if (this.readableDatabase != null) {
            Cursor query;
            try {
                query = this.readableDatabase.query(DATABASE_TABLE, new String[]{"app_id", "status", "msg", "error", "err"}, null, null, null, null, null);
                while (!query.isAfterLast() && query.moveToNext()) {
                    try {
                        String string = query.getString(query.getColumnIndex("status"));
                        String string2 = query.getString(query.getColumnIndex("app_id"));
                        String string3 = query.getString(query.getColumnIndex("msg"));
                        String string4 = query.getString(query.getColumnIndex("error"));
                        String string5 = query.getString(query.getColumnIndex("err"));
                        StringBuilder stringBuilder = new StringBuilder("showDB :status:");
                        stringBuilder.append(string);
                        stringBuilder.append("   app_id:");
                        stringBuilder.append(string2);
                        stringBuilder.append("   msg:");
                        stringBuilder.append(string3);
                        stringBuilder.append("   error:");
                        stringBuilder.append(string4);
                        stringBuilder.append("   err:");
                        stringBuilder.append(string5);
                        ADTLogUtil.v(stringBuilder.toString());
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                Exception exception = e3;
                query = null;
                e = exception;
                try {
                    e.printStackTrace();
                    StringBuilder stringBuilder2 = new StringBuilder("[Error] showDB Exception:");
                    stringBuilder2.append(e.getMessage());
                    ADTLogUtil.e(stringBuilder2.toString());
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                query = null;
                th = th4;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    public boolean updateDB(String str, String str2, String str3, String str4, String str5) {
        if (this.readableDatabase != null) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("status", str2);
                contentValues.put("app_id", str);
                contentValues.put("msg", str3);
                contentValues.put("error", str4);
                contentValues.put("err", str5);
                this.readableDatabase.execSQL("delete from MissionStatus");
                if (this.readableDatabase.insert(DATABASE_TABLE, null, contentValues) != -1) {
                    return true;
                }
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
                StringBuilder stringBuilder = new StringBuilder("updateDB SQLException");
                stringBuilder.append(e.getMessage());
                ADTLogUtil.e(stringBuilder.toString());
            }
        }
        return false;
    }

    public void close() {
        if (this.readableDatabase != null) {
            this.readableDatabase.close();
            SQLiteDatabase.releaseMemory();
        }
        if (this.mDbHelper != null) {
            this.mDbHelper.close();
        }
    }

    public void closeDB() {
        if (this.readableDatabase != null) {
            this.readableDatabase.close();
            SQLiteDatabase.releaseMemory();
            this.readableDatabase = null;
        }
    }
}
