package com.skype.android.jipc;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.nio.charset.Charset;

public class Transactor {
    public static final Charset a = Charset.forName("US-ASCII");
    static final a b = new a() {
        public final int a(byte first, byte second, byte third, byte last) {
            return ((((((first << 24) >>> 8) | (second << 24)) >>> 8) | (third << 24)) >>> 8) | (last << 24);
        }
    };
    private static final Parcel c;

    public interface In {
        void a(Parcel parcel);
    }

    public interface Out<T> {
        T c(Parcel parcel);
    }

    interface a {
        int a(byte b, byte b2, byte b3, byte b4);
    }

    public interface What {
        int a();
    }

    static {
        Parcel obtain = Parcel.obtain();
        c = obtain;
        obtain.unmarshall(new byte[4], 0, 4);
    }

    public static <T> T a(IBinder media, What opCode, In argPreparer, Out<T> out) {
        Parcel reply = Parcel.obtain();
        Parcel obtain;
        T c;
        try {
            obtain = Parcel.obtain();
            try {
                argPreparer.a(obtain);
                media.transact(opCode.a(), obtain, reply, 0);
                c = out.c(reply);
                reply.recycle();
                return c;
            } catch (RemoteException re) {
                throw new RuntimeException(re);
            } catch (Throwable th) {
                obtain.recycle();
            }
        } finally {
            reply = 
/*
Method generation error in method: com.skype.android.jipc.Transactor.a(android.os.IBinder, com.skype.android.jipc.Transactor$What, com.skype.android.jipc.Transactor$In, com.skype.android.jipc.Transactor$Out):T, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: MERGE  (r2_2 'reply' android.os.Parcel) = (r2_0 'reply' android.os.Parcel), (r0_2 'obtain' android.os.Parcel) in method: com.skype.android.jipc.Transactor.a(android.os.IBinder, com.skype.android.jipc.Transactor$What, com.skype.android.jipc.Transactor$In, com.skype.android.jipc.Transactor$Out):T, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:203)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:100)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:50)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:298)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:186)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: MERGE can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 21 more

*/

            public static String a(Parcel reply, byte[] bytes) {
                int length = reply.readInt();
                if (length == 0) {
                    return "";
                }
                int offset = reply.dataPosition();
                try {
                    String str = new String(bytes, offset, length, a);
                    return str;
                } finally {
                    reply.setDataPosition((offset + length) + 1);
                    a(reply);
                }
            }

            private static byte a(CharSequence cs, int index, int length) {
                return index < length ? (byte) cs.charAt(index) : (byte) 0;
            }

            public static void a(Parcel in, CharSequence string) {
                int length = string.length();
                int sizeCeil = (length + 4) & -4;
                int i = 0;
                while (i < sizeCeil) {
                    int i2 = i + 1;
                    byte f = a(string, i, length);
                    i = i2 + 1;
                    byte s = a(string, i2, length);
                    i2 = i + 1;
                    byte t = a(string, i, length);
                    i = i2 + 1;
                    in.writeInt(b.a(f, s, t, a(string, i2, length)));
                }
            }

            private static void a(Parcel data) {
                int dataPosition = data.dataPosition();
                if ((dataPosition & 3) != 0) {
                    data.setDataPosition((dataPosition | 3) + 1);
                }
            }
        }
