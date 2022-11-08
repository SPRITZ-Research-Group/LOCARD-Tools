package com.microsoft.b.a;

import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteMisuseException;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import android.database.sqlite.SQLiteTableLockedException;

public final class g extends c {
    public g(Throwable cause) {
        super(cause);
    }

    protected final i a(Throwable cause) {
        i result = i.SQLITE_ERROR;
        if (cause instanceof SQLiteAbortException) {
            return i.SQLITE_ABORT;
        }
        if (cause instanceof SQLiteAccessPermException) {
            return i.SQLITE_PERM;
        }
        if (cause instanceof SQLiteBindOrColumnIndexOutOfRangeException) {
            return i.SQLITE_RANGE;
        }
        if (cause instanceof SQLiteBlobTooBigException) {
            return i.SQLITE_TOOBIG;
        }
        if (cause instanceof SQLiteCantOpenDatabaseException) {
            return i.SQLITE_CANTOPEN;
        }
        if (cause instanceof SQLiteConstraintException) {
            return i.SQLITE_CONSTRAINT;
        }
        if (cause instanceof SQLiteDatabaseCorruptException) {
            return i.SQLITE_CORRUPT;
        }
        if (cause instanceof SQLiteDatabaseLockedException) {
            return i.SQLITE_BUSY;
        }
        if (cause instanceof SQLiteDatatypeMismatchException) {
            return i.SQLITE_MISMATCH;
        }
        if (cause instanceof SQLiteDiskIOException) {
            return i.SQLITE_IOERR;
        }
        if (cause instanceof SQLiteDoneException) {
            return i.SQLITE_DONE;
        }
        if (cause instanceof SQLiteFullException) {
            return i.SQLITE_FULL;
        }
        if (cause instanceof SQLiteMisuseException) {
            return i.SQLITE_MISUSE;
        }
        if (cause instanceof SQLiteOutOfMemoryException) {
            return i.SQLITE_NOMEM;
        }
        if (cause instanceof SQLiteReadOnlyDatabaseException) {
            return i.SQLITE_READONLY;
        }
        if (cause instanceof SQLiteTableLockedException) {
            return i.SQLITE_LOCKED;
        }
        return result;
    }
}
