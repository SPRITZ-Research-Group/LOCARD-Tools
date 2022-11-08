package a.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import com.appboy.f.c;
import com.appboy.f.i;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

public final class bz extends SQLiteOpenHelper {
    private static final String a = c.a(bz.class);
    private static volatile Map<String, bz> b = new HashMap();
    private static final String[] c = new String[]{"ab_events", "ab_sessions", "sessions", "appboy_events"};

    private bz(Context context, String str) {
        super(context, str, null, 3);
    }

    public final void onCreate(SQLiteDatabase database) {
        c.d(a, "Creating ab_events table");
        database.execSQL("CREATE TABLE ab_events (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id TEXT, user_id TEXT, event_type TEXT NOT NULL, event_data TEXT NOT NULL, event_guid TEXT NOT NULL, timestamp INTEGER NOT NULL);");
    }

    public final void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        c.d(a, "Upgrading storage from version " + oldVersion + " to " + newVersion + ". Dropping all current tables.");
        a(database);
        onCreate(database);
    }

    public final void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        c.d(a, "Downgrading storage from version " + oldVersion + " to " + newVersion + ". Dropping all current tables.");
        a(database);
        onCreate(database);
    }

    public static void a(Context context) {
        File parentFile = context.getDatabasePath(" ").getParentFile();
        if (parentFile.exists() && parentFile.isDirectory()) {
            for (File file : parentFile.listFiles(new FilenameFilter() {
                public final boolean accept(File directory, String name) {
                    return name.startsWith("appboy.db");
                }
            })) {
                c.a(a, "Deleting database file at: " + file.getAbsolutePath());
                context.deleteDatabase(file.getName());
            }
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        for (String str : c) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
            c.b(a, "Dropped table with name: " + str);
        }
    }

    @TargetApi(16)
    public final void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        if (VERSION.SDK_INT >= 16) {
            database.setForeignKeyConstraintsEnabled(true);
        } else if (!database.isReadOnly()) {
            database.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    public static bz a(Context context, String str, String str2) {
        String str3;
        if (i.c(str)) {
            str3 = "appboy.db";
        } else {
            str3 = "appboy.db" + i.a(context, str, str2);
        }
        if (!b.containsKey(str3)) {
            synchronized (bz.class) {
                if (!b.containsKey(str3)) {
                    bz bzVar = new bz(context, str3);
                    b.put(str3, bzVar);
                    return bzVar;
                }
            }
        }
        return (bz) b.get(str3);
    }
}
