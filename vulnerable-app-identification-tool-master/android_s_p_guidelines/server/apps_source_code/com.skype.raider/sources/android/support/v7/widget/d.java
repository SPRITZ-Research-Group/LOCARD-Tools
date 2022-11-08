package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Xml;
import com.adjust.sdk.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class d extends DataSetObservable {
    static final String a = d.class.getSimpleName();
    private static final Object e = new Object();
    private static final Map<String, d> f = new HashMap();
    final Context b;
    final String c;
    boolean d;
    private final Object g;
    private final List<a> h;
    private final List<c> i;
    private Intent j;
    private b k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private d p;

    public static final class a implements Comparable<a> {
        public final ResolveInfo a;
        public float b;

        public final /* synthetic */ int compareTo(Object obj) {
            return Float.floatToIntBits(((a) obj).b) - Float.floatToIntBits(this.b);
        }

        public a(ResolveInfo resolveInfo) {
            this.a = resolveInfo;
        }

        public final int hashCode() {
            return Float.floatToIntBits(this.b) + 31;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            if (Float.floatToIntBits(this.b) != Float.floatToIntBits(((a) obj).b)) {
                return false;
            }
            return true;
        }

        public final String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            builder.append("resolveInfo:").append(this.a.toString());
            builder.append("; weight:").append(new BigDecimal((double) this.b));
            builder.append("]");
            return builder.toString();
        }
    }

    public interface b {
    }

    public static final class c {
        public final ComponentName a;
        public final long b;
        public final float c;

        public c(String activityName, long time, float weight) {
            this(ComponentName.unflattenFromString(activityName), time, weight);
        }

        public c(ComponentName activityName, long time, float weight) {
            this.a = activityName;
            this.b = time;
            this.c = weight;
        }

        public final int hashCode() {
            return (((((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + Float.floatToIntBits(this.c);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            c other = (c) obj;
            if (this.a == null) {
                if (other.a != null) {
                    return false;
                }
            } else if (!this.a.equals(other.a)) {
                return false;
            }
            if (this.b != other.b) {
                return false;
            }
            if (Float.floatToIntBits(this.c) != Float.floatToIntBits(other.c)) {
                return false;
            }
            return true;
        }

        public final String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            builder.append("; activity:").append(this.a);
            builder.append("; time:").append(this.b);
            builder.append("; weight:").append(new BigDecimal((double) this.c));
            builder.append("]");
            return builder.toString();
        }
    }

    public interface d {
        boolean a();
    }

    private final class e extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ d a;

        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        e(d dVar) {
            this.a = dVar;
        }

        private Void a(Object... args) {
            String str;
            List<c> historicalRecords = args[0];
            try {
                FileOutputStream fos = this.a.b.openFileOutput(args[1], 0);
                XmlSerializer serializer = Xml.newSerializer();
                try {
                    serializer.setOutput(fos, null);
                    serializer.startDocument(Constants.ENCODING, Boolean.valueOf(true));
                    serializer.startTag(null, "historical-records");
                    int recordCount = historicalRecords.size();
                    for (int i = 0; i < recordCount; i++) {
                        c record = (c) historicalRecords.remove(0);
                        serializer.startTag(null, "historical-record");
                        serializer.attribute(null, "activity", record.a.flattenToString());
                        serializer.attribute(null, "time", String.valueOf(record.b));
                        serializer.attribute(null, "weight", String.valueOf(record.c));
                        serializer.endTag(null, "historical-record");
                    }
                    serializer.endTag(null, "historical-records");
                    serializer.endDocument();
                    this.a.d = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (IllegalArgumentException e2) {
                    str = d.a;
                    new StringBuilder("Error writing historical record file: ").append(this.a.c);
                    this.a.d = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (IllegalStateException e4) {
                    str = d.a;
                    new StringBuilder("Error writing historical record file: ").append(this.a.c);
                    this.a.d = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (IOException e6) {
                    str = d.a;
                    new StringBuilder("Error writing historical record file: ").append(this.a.c);
                    this.a.d = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e7) {
                        }
                    }
                } catch (Throwable th) {
                    this.a.d = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e8) {
                        }
                    }
                }
            } catch (FileNotFoundException e9) {
                str = d.a;
            }
            return null;
        }
    }

    public final int a() {
        int size;
        synchronized (this.g) {
            d();
            size = this.h.size();
        }
        return size;
    }

    public final ResolveInfo a(int index) {
        ResolveInfo resolveInfo;
        synchronized (this.g) {
            d();
            resolveInfo = ((a) this.h.get(index)).a;
        }
        return resolveInfo;
    }

    public final int a(ResolveInfo activity) {
        int i;
        synchronized (this.g) {
            d();
            List<a> activities = this.h;
            int activityCount = activities.size();
            i = 0;
            while (i < activityCount) {
                if (((a) activities.get(i)).a == activity) {
                    break;
                }
                i++;
            }
            i = -1;
        }
        return i;
    }

    public final Intent b(int index) {
        synchronized (this.g) {
            if (this.j == null) {
                return null;
            }
            d();
            a chosenActivity = (a) this.h.get(index);
            ComponentName chosenName = new ComponentName(chosenActivity.a.activityInfo.packageName, chosenActivity.a.activityInfo.name);
            Intent choiceIntent = new Intent(this.j);
            choiceIntent.setComponent(chosenName);
            if (this.p != null) {
                Intent intent = new Intent(choiceIntent);
                if (this.p.a()) {
                    return null;
                }
            }
            a(new c(chosenName, System.currentTimeMillis(), 1.0f));
            return choiceIntent;
        }
    }

    public final ResolveInfo b() {
        synchronized (this.g) {
            d();
            if (this.h.isEmpty()) {
                return null;
            }
            ResolveInfo resolveInfo = ((a) this.h.get(0)).a;
            return resolveInfo;
        }
    }

    public final void c(int index) {
        synchronized (this.g) {
            float weight;
            d();
            a newDefaultActivity = (a) this.h.get(index);
            a oldDefaultActivity = (a) this.h.get(0);
            if (oldDefaultActivity != null) {
                weight = (oldDefaultActivity.b - newDefaultActivity.b) + 5.0f;
            } else {
                weight = 1.0f;
            }
            a(new c(new ComponentName(newDefaultActivity.a.activityInfo.packageName, newDefaultActivity.a.activityInfo.name), System.currentTimeMillis(), weight));
        }
    }

    public final int c() {
        int size;
        synchronized (this.g) {
            d();
            size = this.i.size();
        }
        return size;
    }

    private void d() {
        int i;
        int i2 = 1;
        if (!this.o || this.j == null) {
            i = 0;
        } else {
            this.o = false;
            this.h.clear();
            List queryIntentActivities = this.b.getPackageManager().queryIntentActivities(this.j, 0);
            int size = queryIntentActivities.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.h.add(new a((ResolveInfo) queryIntentActivities.get(i3)));
            }
            i = 1;
        }
        if (this.d && this.n && !TextUtils.isEmpty(this.c)) {
            this.d = false;
            this.m = true;
            g();
        } else {
            i2 = 0;
        }
        boolean stateChanged = i | i2;
        f();
        if (stateChanged) {
            e();
            notifyChanged();
        }
    }

    private boolean e() {
        if (this.k == null || this.j == null || this.h.isEmpty() || this.i.isEmpty()) {
            return false;
        }
        Collections.unmodifiableList(this.i);
        return true;
    }

    private boolean a(c historicalRecord) {
        boolean added = this.i.add(historicalRecord);
        if (added) {
            this.n = true;
            f();
            if (this.m) {
                if (this.n) {
                    this.n = false;
                    if (!TextUtils.isEmpty(this.c)) {
                        new e(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{new ArrayList(this.i), this.c});
                    }
                }
                e();
                notifyChanged();
            } else {
                throw new IllegalStateException("No preceding call to #readHistoricalData");
            }
        }
        return added;
    }

    private void f() {
        int pruneCount = this.i.size() - this.l;
        if (pruneCount > 0) {
            this.n = true;
            for (int i = 0; i < pruneCount; i++) {
                this.i.remove(0);
            }
        }
    }

    private void g() {
        try {
            FileInputStream fis = this.b.openFileInput(this.c);
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(fis, Constants.ENCODING);
                int type = 0;
                while (type != 1 && type != 2) {
                    type = parser.next();
                }
                if ("historical-records".equals(parser.getName())) {
                    List<c> historicalRecords = this.i;
                    historicalRecords.clear();
                    while (true) {
                        type = parser.next();
                        if (type != 1) {
                            if (!(type == 3 || type == 4)) {
                                if ("historical-record".equals(parser.getName())) {
                                    historicalRecords.add(new c(parser.getAttributeValue(null, "activity"), Long.parseLong(parser.getAttributeValue(null, "time")), Float.parseFloat(parser.getAttributeValue(null, "weight"))));
                                } else {
                                    throw new XmlPullParserException("Share records file not well-formed.");
                                }
                            }
                        } else if (fis != null) {
                            try {
                                fis.close();
                                return;
                            } catch (IOException e) {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                throw new XmlPullParserException("Share records file does not start with historical-records tag.");
            } catch (XmlPullParserException e2) {
                new StringBuilder("Error reading historical recrod file: ").append(this.c);
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (IOException e4) {
                new StringBuilder("Error reading historical recrod file: ").append(this.c);
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (Throwable th) {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e6) {
                    }
                }
            }
        } catch (FileNotFoundException e7) {
        }
    }
}
