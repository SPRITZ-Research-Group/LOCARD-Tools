package defpackage;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000¨\u0006\u000f"}, d2 = {"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* renamed from: acov */
public final class acov {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @acpi(b = "SlidingWindow.kt", c = {33, 39, 46, 52, 55}, d = "invokeSuspend", e = "kotlin/collections/SlidingWindowKt$windowedIterator$1")
    /* renamed from: acov$a */
    final class a extends acpn implements acrc<adbq<? super List<? extends T>>, acpd<? super y>, Object> {
        Object a;
        Object b;
        Object c;
        Object d;
        int e;
        int f;
        int g;
        final /* synthetic */ int h;
        final /* synthetic */ int i;
        final /* synthetic */ Iterator j;
        final /* synthetic */ boolean k;
        final /* synthetic */ boolean l;
        private adbq m;

        a(int i, int i2, Iterator it, boolean z, boolean z2, acpd acpd) {
            this.h = i;
            this.i = i2;
            this.j = it;
            this.k = z;
            this.l = z2;
            super(acpd);
        }

        public final acpd<y> a(Object obj, acpd<?> acpd) {
            acpd aVar = new a(this.h, this.i, this.j, this.k, this.l, acpd);
            aVar.m = (adbq) obj;
            return aVar;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((a) a(obj, (acpd) obj2)).a(y.a);
        }

        public final java.lang.Object a(java.lang.Object r12) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:29)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r11 = this;
            r0 = defpackage.acpg.COROUTINE_SUSPENDED;
            r1 = r11.g;
            r2 = 1;
            switch(r1) {
                case 0: goto L_0x0071;
                case 1: goto L_0x0056;
                case 2: goto L_0x004b;
                case 3: goto L_0x0031;
                case 4: goto L_0x001b;
                case 5: goto L_0x0010;
                default: goto L_0x0008;
            };
        L_0x0008:
            r12 = new java.lang.IllegalStateException;
            r0 = "call to 'resume' before 'invoke' with coroutine";
            r12.<init>(r0);
            throw r12;
        L_0x0010:
            r0 = r12 instanceof kotlin.p;
            if (r0 != 0) goto L_0x0016;
        L_0x0014:
            goto L_0x018f;
        L_0x0016:
            r12 = (kotlin.p) r12;
            r12 = r12.a;
            throw r12;
        L_0x001b:
            r1 = r11.b;
            r1 = (defpackage.acoq) r1;
            r3 = r11.e;
            r4 = r11.a;
            r4 = (defpackage.adbq) r4;
            r5 = r12 instanceof kotlin.p;
            if (r5 != 0) goto L_0x002c;
        L_0x0029:
            r12 = r11;
            goto L_0x0171;
        L_0x002c:
            r12 = (kotlin.p) r12;
            r12 = r12.a;
            throw r12;
        L_0x0031:
            r1 = r11.d;
            r1 = (java.util.Iterator) r1;
            r3 = r11.b;
            r3 = (defpackage.acoq) r3;
            r4 = r11.e;
            r5 = r11.a;
            r5 = (defpackage.adbq) r5;
            r6 = r12 instanceof kotlin.p;
            if (r6 != 0) goto L_0x0046;
        L_0x0043:
            r12 = r11;
            goto L_0x013a;
        L_0x0046:
            r12 = (kotlin.p) r12;
            r12 = r12.a;
            throw r12;
        L_0x004b:
            r0 = r12 instanceof kotlin.p;
            if (r0 != 0) goto L_0x0051;
        L_0x004f:
            goto L_0x018f;
        L_0x0051:
            r12 = (kotlin.p) r12;
            r12 = r12.a;
            throw r12;
        L_0x0056:
            r1 = r11.d;
            r1 = (java.util.Iterator) r1;
            r3 = r11.b;
            r3 = (java.util.ArrayList) r3;
            r4 = r11.e;
            r5 = r11.a;
            r5 = (defpackage.adbq) r5;
            r6 = r12 instanceof kotlin.p;
            if (r6 != 0) goto L_0x006c;
        L_0x0068:
            r12 = r11;
            r6 = r0;
            r0 = r4;
            goto L_0x00bc;
        L_0x006c:
            r12 = (kotlin.p) r12;
            r12 = r12.a;
            throw r12;
        L_0x0071:
            r1 = r12 instanceof kotlin.p;
            if (r1 != 0) goto L_0x0192;
        L_0x0075:
            r12 = r11.m;
            r1 = r11.h;
            r3 = r11.i;
            r1 = r1 - r3;
            if (r1 < 0) goto L_0x00f3;
        L_0x007e:
            r3 = new java.util.ArrayList;
            r4 = r11.i;
            r3.<init>(r4);
            r4 = 0;
            r5 = r11.j;
            r6 = r0;
            r0 = r1;
            r1 = r5;
            r5 = r12;
            r12 = r11;
        L_0x008d:
            r7 = r1.hasNext();
            if (r7 == 0) goto L_0x00cd;
        L_0x0093:
            r7 = r1.next();
            if (r4 <= 0) goto L_0x009c;
        L_0x0099:
            r4 = r4 + -1;
            goto L_0x008d;
        L_0x009c:
            r3.add(r7);
            r8 = r3.size();
            r9 = r12.i;
            if (r8 != r9) goto L_0x008d;
        L_0x00a7:
            r12.a = r5;
            r12.e = r0;
            r12.b = r3;
            r12.f = r4;
            r12.c = r7;
            r12.d = r1;
            r12.g = r2;
            r4 = r5.a(r3, r12);
            if (r4 != r6) goto L_0x00bc;
        L_0x00bb:
            return r6;
        L_0x00bc:
            r4 = r12.k;
            if (r4 == 0) goto L_0x00c4;
        L_0x00c0:
            r3.clear();
            goto L_0x00cb;
        L_0x00c4:
            r3 = new java.util.ArrayList;
            r4 = r12.i;
            r3.<init>(r4);
        L_0x00cb:
            r4 = r0;
            goto L_0x008d;
        L_0x00cd:
            r1 = r3;
            r1 = (java.util.Collection) r1;
            r1 = r1.isEmpty();
            r1 = r1 ^ r2;
            if (r1 == 0) goto L_0x018f;
        L_0x00d7:
            r1 = r12.l;
            if (r1 != 0) goto L_0x00e3;
        L_0x00db:
            r1 = r3.size();
            r2 = r12.i;
            if (r1 != r2) goto L_0x018f;
        L_0x00e3:
            r12.e = r0;
            r12.a = r3;
            r12.f = r4;
            r0 = 2;
            r12.g = r0;
            r12 = r5.a(r3, r12);
            if (r12 != r6) goto L_0x018f;
        L_0x00f2:
            return r6;
        L_0x00f3:
            r3 = new acoq;
            r4 = r11.i;
            r3.<init>(r4);
            r4 = r11.j;
            r5 = r12;
            r12 = r11;
            r10 = r4;
            r4 = r1;
            r1 = r10;
        L_0x0101:
            r6 = r1.hasNext();
            if (r6 == 0) goto L_0x0140;
        L_0x0107:
            r6 = r1.next();
            r3.a(r6);
            r7 = r3.b();
            if (r7 == 0) goto L_0x0101;
        L_0x0114:
            r7 = r12.k;
            if (r7 == 0) goto L_0x011c;
        L_0x0118:
            r7 = r3;
            r7 = (java.util.List) r7;
            goto L_0x0126;
        L_0x011c:
            r7 = new java.util.ArrayList;
            r8 = r3;
            r8 = (java.util.Collection) r8;
            r7.<init>(r8);
            r7 = (java.util.List) r7;
        L_0x0126:
            r12.a = r5;
            r12.e = r4;
            r12.b = r3;
            r12.c = r6;
            r12.d = r1;
            r6 = 3;
            r12.g = r6;
            r6 = r5.a(r7, r12);
            if (r6 != r0) goto L_0x013a;
        L_0x0139:
            return r0;
        L_0x013a:
            r6 = r12.h;
            r3.a(r6);
            goto L_0x0101;
        L_0x0140:
            r1 = r12.l;
            if (r1 == 0) goto L_0x018f;
        L_0x0144:
            r1 = r3;
            r3 = r4;
            r4 = r5;
        L_0x0147:
            r5 = r1.size();
            r6 = r12.h;
            if (r5 <= r6) goto L_0x0177;
        L_0x014f:
            r5 = r12.k;
            if (r5 == 0) goto L_0x0157;
        L_0x0153:
            r5 = r1;
            r5 = (java.util.List) r5;
            goto L_0x0161;
        L_0x0157:
            r5 = new java.util.ArrayList;
            r6 = r1;
            r6 = (java.util.Collection) r6;
            r5.<init>(r6);
            r5 = (java.util.List) r5;
        L_0x0161:
            r12.a = r4;
            r12.e = r3;
            r12.b = r1;
            r6 = 4;
            r12.g = r6;
            r5 = r4.a(r5, r12);
            if (r5 != r0) goto L_0x0171;
        L_0x0170:
            return r0;
        L_0x0171:
            r5 = r12.h;
            r1.a(r5);
            goto L_0x0147;
        L_0x0177:
            r5 = r1;
            r5 = (java.util.Collection) r5;
            r5 = r5.isEmpty();
            r2 = r2 ^ r5;
            if (r2 == 0) goto L_0x018f;
        L_0x0181:
            r12.e = r3;
            r12.a = r1;
            r2 = 5;
            r12.g = r2;
            r12 = r4.a(r1, r12);
            if (r12 != r0) goto L_0x018f;
        L_0x018e:
            return r0;
        L_0x018f:
            r12 = kotlin.y.a;
            return r12;
        L_0x0192:
            r12 = (kotlin.p) r12;
            r12 = r12.a;
            throw r12;
            */
            throw new UnsupportedOperationException("Method not decompiled: acov.a.a(java.lang.Object):java.lang.Object");
        }
    }

    public static final void a(int i, int i2) {
        Object obj = (i <= 0 || i2 <= 0) ? null : 1;
        if (obj == null) {
            Object stringBuilder;
            if (i != i2) {
                StringBuilder stringBuilder2 = new StringBuilder("Both size ");
                stringBuilder2.append(i);
                stringBuilder2.append(" and step ");
                stringBuilder2.append(i2);
                stringBuilder2.append(" must be greater than zero.");
                stringBuilder = stringBuilder2.toString();
            } else {
                StringBuilder stringBuilder3 = new StringBuilder("size ");
                stringBuilder3.append(i);
                stringBuilder3.append(" must be greater than zero.");
                stringBuilder = stringBuilder3.toString();
            }
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
