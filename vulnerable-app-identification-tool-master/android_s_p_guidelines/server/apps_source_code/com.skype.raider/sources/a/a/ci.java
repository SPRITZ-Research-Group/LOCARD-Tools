package a.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.appboy.f.c;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;

public class ci implements cg {
    private static final String a = c.a(ci.class);
    private static final String[] b = new String[]{"session_id", "user_id", "event_type", "event_data", "event_guid", "timestamp"};
    private SQLiteDatabase c;
    private boolean d = false;
    private final bz e;

    public ci(bz bzVar) {
        this.e = bzVar;
    }

    private synchronized SQLiteDatabase c() {
        if (this.c == null || !this.c.isOpen()) {
            this.c = this.e.getWritableDatabase();
        }
        return this.c;
    }

    public final void a(av avVar) {
        if (this.d) {
            c.f(a, "Storage provider is closed. Not adding event: " + avVar);
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_type", avVar.b().a());
        contentValues.put("event_data", avVar.c().toString());
        contentValues.put("timestamp", Double.valueOf(avVar.a()));
        if (avVar.f() != null) {
            contentValues.put("session_id", avVar.f().toString());
        }
        if (avVar.e() != null) {
            contentValues.put("user_id", avVar.e());
        }
        if (avVar.d() != null) {
            contentValues.put("event_guid", avVar.d());
        }
        if (c().insert("ab_events", null, contentValues) == -1) {
            c.f(a, "Failed to add event [" + avVar.toString() + "] to storage");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @NonNull
    public final Collection<av> a() {
        Cursor cursor = null;
        if (this.d) {
            c.f(a, "Storage provider is closed. Not getting all events.");
            return null;
        }
        try {
            cursor = c().query("ab_events", b, null, null, null, null, null);
            Collection<av> a = a(cursor);
            if (cursor == null) {
                return a;
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final void b(av avVar) {
        if (this.d) {
            c.f(a, "Storage provider is closed. Not deleting event: " + avVar);
            return;
        }
        c().beginTransaction();
        try {
            String[] strArr = new String[]{avVar.d()};
            c.c(a, "Deleting event with uid " + avVar.d() + " removed " + c().delete("ab_events", "event_guid = ?", strArr) + " row.");
            c().setTransactionSuccessful();
        } finally {
            c().endTransaction();
        }
    }

    public final void b() {
        c.f(a, "Closing SQL database and setting this provider to closed.");
        this.d = true;
        c().close();
    }

    private static Collection<av> a(Cursor cursor) {
        Collection arrayList = new ArrayList();
        int columnIndex = cursor.getColumnIndex("session_id");
        int columnIndex2 = cursor.getColumnIndex("user_id");
        int columnIndex3 = cursor.getColumnIndex("event_type");
        int columnIndex4 = cursor.getColumnIndex("event_data");
        int columnIndex5 = cursor.getColumnIndex("event_guid");
        int columnIndex6 = cursor.getColumnIndex("timestamp");
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndex3);
            String string2 = cursor.getString(columnIndex4);
            double d = cursor.getDouble(columnIndex6);
            String string3 = cursor.getString(columnIndex5);
            String string4 = cursor.getString(columnIndex2);
            String string5 = cursor.getString(columnIndex);
            try {
                arrayList.add(be.a(string, string2, d, string3, string4, string5));
            } catch (JSONException e) {
                c.g(a, "Could not create AppboyEvent from [type=" + string + ", data=" + string2 + ", timestamp=" + d + ", uniqueId=" + string3 + ", userId=" + string4 + ", sessionId=" + string5 + "] ... Skipping");
            }
        }
        return arrayList;
    }
}
