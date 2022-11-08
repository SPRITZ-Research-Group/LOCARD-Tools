package com.facebook.soloader;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

public class d extends j {
    protected final File c;
    protected final String d;

    protected class b extends e {
        @Nullable
        private a[] a;
        final /* synthetic */ d b;
        private final ZipFile c;
        private final j d;

        private final class a extends d {
            final /* synthetic */ b a;
            private int b;

            private a(b bVar) {
                this.a = bVar;
            }

            /* synthetic */ a(b x0, byte b) {
                this(x0);
            }

            public final boolean a() {
                this.a.a();
                return this.b < this.a.a.length;
            }

            public final c b() throws IOException {
                this.a.a();
                a[] a = this.a.a;
                int i = this.b;
                this.b = i + 1;
                a zipDso = a[i];
                InputStream is = this.a.c.getInputStream(zipDso.a);
                try {
                    return new c(zipDso, is);
                } catch (Throwable th) {
                    if (is != null) {
                        is.close();
                    }
                }
            }
        }

        b(d this$0, j soSource) throws IOException {
            this.b = this$0;
            this.c = new ZipFile(this$0.c);
            this.d = soSource;
        }

        final a[] a() {
            if (this.a == null) {
                int i;
                a zd;
                Set<String> librariesAbiSet = new LinkedHashSet();
                HashMap<String, a> providedLibraries = new HashMap();
                Pattern zipSearchPattern = Pattern.compile(this.b.d);
                String[] supportedAbis = VERSION.SDK_INT < 21 ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : LollipopSysdeps.getSupportedAbis();
                Enumeration<? extends ZipEntry> entries = this.c.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = (ZipEntry) entries.nextElement();
                    Matcher m = zipSearchPattern.matcher(entry.getName());
                    if (m.matches()) {
                        String libraryAbi = m.group(1);
                        String soName = m.group(2);
                        int abiScore = SysUtil.a(supportedAbis, libraryAbi);
                        if (abiScore >= 0) {
                            librariesAbiSet.add(libraryAbi);
                            a so = (a) providedLibraries.get(soName);
                            if (so == null || abiScore < so.b) {
                                providedLibraries.put(soName, new a(soName, entry, abiScore));
                            }
                        }
                    }
                }
                this.d.a((String[]) librariesAbiSet.toArray(new String[librariesAbiSet.size()]));
                a[] dsos = (a[]) providedLibraries.values().toArray(new a[providedLibraries.size()]);
                Arrays.sort(dsos);
                int nrFilteredDsos = 0;
                for (i = 0; i < dsos.length; i++) {
                    zd = dsos[i];
                    if (a(zd.a, zd.c)) {
                        nrFilteredDsos++;
                    } else {
                        dsos[i] = null;
                    }
                }
                a[] filteredDsos = new a[nrFilteredDsos];
                int j = 0;
                for (a zd2 : dsos) {
                    if (zd2 != null) {
                        int j2 = j + 1;
                        filteredDsos[j] = zd2;
                        j = j2;
                    }
                }
                this.a = filteredDsos;
            }
            return this.a;
        }

        protected boolean a(ZipEntry ze, String soName) {
            return true;
        }

        public void close() throws IOException {
            this.c.close();
        }

        protected final com.facebook.soloader.j.b b() throws IOException {
            return new com.facebook.soloader.j.b(a());
        }

        protected final d c() throws IOException {
            return new a();
        }
    }

    private static final class a extends com.facebook.soloader.j.a implements Comparable {
        final ZipEntry a;
        final int b;

        public final int compareTo(Object other) {
            return this.c.compareTo(((a) other).c);
        }

        a(String name, ZipEntry backingEntry, int abiScore) {
            super(name, String.format("pseudo-zip-hash-1-%s-%s-%s-%s", new Object[]{backingEntry.getName(), Long.valueOf(backingEntry.getSize()), Long.valueOf(backingEntry.getCompressedSize()), Long.valueOf(backingEntry.getCrc())}));
            this.a = backingEntry;
            this.b = abiScore;
        }
    }

    public d(Context context, String name, File zipFileName, String zipSearchPattern) {
        super(context, name);
        this.c = zipFileName;
        this.d = zipSearchPattern;
    }

    protected e a() throws IOException {
        return new b(this, this);
    }
}
