package com.linecorp.db.sqlite.ord.annotation;

import android.content.ContentValues;
import android.database.Cursor;
import java.lang.reflect.Field;

public @interface DB {

    public interface ColumnValueConverter {
        void getFromCursor(Cursor cursor, Field field, Object obj, int i) throws IllegalAccessException;

        void putToContentValues(ContentValues contentValues, String str, Object obj);
    }

    public class DummyDbValueConverter implements ColumnValueConverter {
        public void getFromCursor(Cursor cursor, Field field, Object obj, int i) throws IllegalAccessException {
        }

        public void putToContentValues(ContentValues contentValues, String str, Object obj) {
        }
    }
}
