package androidx.multidex;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class g {
    static void a(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
        Object obj = a.b(classLoader, "pathList").get(classLoader);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        a.a(obj, "dexElements", (Object[]) a.b(obj, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(obj, new Object[]{arrayList2, file, arrayList}));
        if (arrayList.size() > 0) {
            Object obj2;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Log.w("MultiDex", "Exception in makeDexElement", (IOException) it.next());
            }
            Field a = a.b(obj, "dexElementsSuppressedExceptions");
            IOException[] iOExceptionArr = (IOException[]) a.get(obj);
            if (iOExceptionArr == null) {
                obj2 = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
            } else {
                Object obj3 = new IOException[(arrayList.size() + iOExceptionArr.length)];
                arrayList.toArray(obj3);
                System.arraycopy(iOExceptionArr, 0, obj3, arrayList.size(), iOExceptionArr.length);
                obj2 = obj3;
            }
            a.set(obj, obj2);
            IOException iOException = new IOException("I/O exception during makeDexElement");
            iOException.initCause((Throwable) arrayList.get(0));
            throw iOException;
        }
    }
}
