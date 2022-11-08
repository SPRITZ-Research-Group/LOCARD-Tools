package androidx.work;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class h {
    public static final h a = new i().a();
    private static final String c = p.a("Data");
    Map<String, Object> b;

    h() {
    }

    public h(h hVar) {
        this.b = new HashMap(hVar.b);
    }

    h(Map<String, ?> map) {
        this.b = new HashMap(map);
    }

    public final String a(String str) {
        Object obj = this.b.get(str);
        return obj instanceof String ? (String) obj : null;
    }

    public static byte[] a(h hVar) throws IllegalStateException {
        Throwable e;
        byte[] toByteArray;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream2.writeInt(hVar.b.size());
                for (Entry entry : hVar.b.entrySet()) {
                    objectOutputStream2.writeUTF((String) entry.getKey());
                    objectOutputStream2.writeObject(entry.getValue());
                }
                try {
                    objectOutputStream2.close();
                } catch (Throwable e2) {
                    Log.e(c, "Error in Data#toByteArray: ", e2);
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable e22) {
                    Log.e(c, "Error in Data#toByteArray: ", e22);
                }
                if (byteArrayOutputStream.size() <= 10240) {
                    return byteArrayOutputStream.toByteArray();
                }
                throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
            } catch (IOException e3) {
                e22 = e3;
                objectOutputStream = objectOutputStream2;
                try {
                    Log.e(c, "Error in Data#toByteArray: ", e22);
                    toByteArray = byteArrayOutputStream.toByteArray();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (Throwable e4) {
                            Log.e(c, "Error in Data#toByteArray: ", e4);
                        }
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e5) {
                        Log.e(c, "Error in Data#toByteArray: ", e5);
                    }
                    return toByteArray;
                } catch (Throwable th) {
                    e22 = th;
                    objectOutputStream2 = objectOutputStream;
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (Throwable e42) {
                            Log.e(c, "Error in Data#toByteArray: ", e42);
                        }
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e52) {
                        Log.e(c, "Error in Data#toByteArray: ", e52);
                    }
                    throw e22;
                }
            } catch (Throwable th2) {
                e22 = th2;
                if (objectOutputStream2 != null) {
                    objectOutputStream2.close();
                }
                byteArrayOutputStream.close();
                throw e22;
            }
        } catch (IOException e6) {
            e22 = e6;
            Log.e(c, "Error in Data#toByteArray: ", e22);
            toByteArray = byteArrayOutputStream.toByteArray();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            byteArrayOutputStream.close();
            return toByteArray;
        }
    }

    public static h a(byte[] bArr) throws IllegalStateException {
        Throwable e;
        if (bArr.length <= 10240) {
            Map hashMap = new HashMap();
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ObjectInputStream objectInputStream;
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    for (int readInt = objectInputStream.readInt(); readInt > 0; readInt--) {
                        hashMap.put(objectInputStream.readUTF(), objectInputStream.readObject());
                    }
                    try {
                        objectInputStream.close();
                    } catch (Throwable e2) {
                        Log.e(c, "Error in Data#fromByteArray: ", e2);
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        Log.e(c, "Error in Data#fromByteArray: ", e2);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable e22) {
                                Log.e(c, "Error in Data#fromByteArray: ", e22);
                            }
                        }
                        byteArrayInputStream.close();
                        return new h(hashMap);
                    } catch (Throwable th) {
                        e22 = th;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable e4) {
                                Log.e(c, "Error in Data#fromByteArray: ", e4);
                            }
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable e42) {
                            Log.e(c, "Error in Data#fromByteArray: ", e42);
                        }
                        throw e22;
                    }
                }
                try {
                    byteArrayInputStream.close();
                } catch (Throwable e222) {
                    Log.e(c, "Error in Data#fromByteArray: ", e222);
                }
            } catch (Throwable e5) {
                Throwable th2 = e5;
                objectInputStream = null;
                e222 = th2;
                Log.e(c, "Error in Data#fromByteArray: ", e222);
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                byteArrayInputStream.close();
                return new h(hashMap);
            } catch (Throwable e422) {
                objectInputStream = null;
                e222 = e422;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                byteArrayInputStream.close();
                throw e222;
            }
            return new h(hashMap);
        }
        throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b.equals(((h) obj).b);
    }

    public final int hashCode() {
        return this.b.hashCode() * 31;
    }

    static Boolean[] a(boolean[] zArr) {
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    static Integer[] a(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    static Long[] a(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    static Float[] a(float[] fArr) {
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    static Double[] a(double[] dArr) {
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }
}
