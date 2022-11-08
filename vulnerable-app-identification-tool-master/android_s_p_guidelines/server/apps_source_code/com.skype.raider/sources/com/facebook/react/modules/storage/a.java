package com.facebook.react.modules.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.facebook.react.bridge.al;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.Arrays;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    static String a(int selectionCount) {
        String[] list = new String[selectionCount];
        Arrays.fill(list, "?");
        return "key IN (" + TextUtils.join(", ", list) + ")";
    }

    static String[] a(al keys, int start, int count) {
        String[] selectionArgs = new String[count];
        for (int keyIndex = 0; keyIndex < count; keyIndex++) {
            selectionArgs[keyIndex] = keys.getString(start + keyIndex);
        }
        return selectionArgs;
    }

    @Nullable
    private static String a(SQLiteDatabase db, String key) {
        String str = null;
        SQLiteDatabase sQLiteDatabase = db;
        Cursor cursor = sQLiteDatabase.query("catalystLocalStorage", new String[]{PropertiesEntry.COLUMN_NAME_VALUE}, "key=?", new String[]{key}, str, str, str);
        try {
            if (cursor.moveToFirst()) {
                str = cursor.getString(0);
                cursor.close();
            }
            return str;
        } finally {
            cursor.close();
        }
    }

    static boolean a(SQLiteDatabase db, String key, String value) throws JSONException {
        String newValue;
        String oldValue = a(db, key);
        if (oldValue == null) {
            newValue = value;
        } else {
            JSONObject oldJSON = new JSONObject(oldValue);
            a(oldJSON, new JSONObject(value));
            newValue = oldJSON.toString();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(PropertiesEntry.COLUMN_NAME_KEY, key);
        contentValues.put(PropertiesEntry.COLUMN_NAME_VALUE, newValue);
        if (-1 != db.insertWithOnConflict("catalystLocalStorage", null, contentValues, 5)) {
            return true;
        }
        return false;
    }

    private static void a(JSONObject oldJSON, JSONObject newJSON) throws JSONException {
        Iterator<?> keys = newJSON.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject newJSONObject = newJSON.optJSONObject(key);
            JSONObject oldJSONObject = oldJSON.optJSONObject(key);
            if (newJSONObject == null || oldJSONObject == null) {
                oldJSON.put(key, newJSON.get(key));
            } else {
                a(oldJSONObject, newJSONObject);
                oldJSON.put(key, oldJSONObject);
            }
        }
    }
}
