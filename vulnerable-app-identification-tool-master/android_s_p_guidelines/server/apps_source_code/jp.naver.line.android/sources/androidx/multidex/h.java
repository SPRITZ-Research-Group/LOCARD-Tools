package androidx.multidex;

import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipFile;

final class h {
    static void a(ClassLoader classLoader, List<? extends File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
        int size = list.size();
        Field a = a.b(classLoader, "path");
        StringBuilder stringBuilder = new StringBuilder((String) a.get(classLoader));
        Object[] objArr = new String[size];
        Object[] objArr2 = new File[size];
        Object[] objArr3 = new ZipFile[size];
        Object[] objArr4 = new DexFile[size];
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            File file = (File) listIterator.next();
            String absolutePath = file.getAbsolutePath();
            stringBuilder.append(':');
            stringBuilder.append(absolutePath);
            int previousIndex = listIterator.previousIndex();
            objArr[previousIndex] = absolutePath;
            objArr2[previousIndex] = file;
            objArr3[previousIndex] = new ZipFile(file);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(absolutePath);
            stringBuilder2.append(".dex");
            objArr4[previousIndex] = DexFile.loadDex(absolutePath, stringBuilder2.toString(), 0);
        }
        a.set(classLoader, stringBuilder.toString());
        a.a((Object) classLoader, "mPaths", objArr);
        a.a((Object) classLoader, "mFiles", objArr2);
        a.a((Object) classLoader, "mZips", objArr3);
        a.a((Object) classLoader, "mDexs", objArr4);
    }
}
