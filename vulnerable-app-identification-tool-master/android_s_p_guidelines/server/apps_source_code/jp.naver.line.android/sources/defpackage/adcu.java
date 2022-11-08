package defpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
/* renamed from: adcu */
class adcu {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "line", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: adcu$a */
    final class a extends acrz implements acqr<String, String> {
        public static final a a = new a();

        a() {
            super(1);
        }

        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return (String) obj;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "line", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: adcu$b */
    final class b extends acrz implements acqr<String, String> {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            String str = (String) obj;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append(str);
            return stringBuilder.toString();
        }
    }

    private static final int c(String str) {
        CharSequence charSequence = str;
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            if ((adcd.a(charSequence.charAt(i)) ^ 1) != 0) {
                break;
            }
            i++;
        }
        i = -1;
        return i == -1 ? str.length() : i;
    }

    private static final acqr<String, String> d(String str) {
        if ((((CharSequence) str).length() == 0 ? 1 : null) != null) {
            return a.a;
        }
        return new b(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ String a(String str) {
        String str2 = "|";
        String str3 = "";
        if ((addb.a(str2) ^ 1) != 0) {
            List h = addc.h(str);
            int length = str.length() + (str3.length() * h.size());
            acqr d = adcu.d(str3);
            int a = acnr.a(h);
            Collection arrayList = new ArrayList();
            int i = 0;
            for (Object next : h) {
                Object obj;
                int i2 = i + 1;
                if (i < 0) {
                    acnr.a();
                }
                String str4 = (String) next;
                Object obj2 = null;
                if ((i == 0 || i == a) && addb.a(str4)) {
                    obj = null;
                } else {
                    CharSequence charSequence = str4;
                    int length2 = charSequence.length();
                    int i3 = 0;
                    while (i3 < length2) {
                        if ((adcd.a(charSequence.charAt(i3)) ^ 1) != 0) {
                            break;
                        }
                        i3++;
                    }
                    i3 = -1;
                    if (i3 != -1 && str4.startsWith(str2, i3)) {
                        i3 += str2.length();
                        if (str4 != null) {
                            obj2 = str4.substring(i3);
                        } else {
                            throw new v("null cannot be cast to non-null type java.lang.String");
                        }
                    }
                    if (obj2 != null) {
                        obj = (String) d.invoke(obj2);
                    }
                    obj = str4;
                }
                if (obj != null) {
                    arrayList.add(obj);
                }
                i = i2;
            }
            return ((StringBuilder) acnz.a((List) arrayList, new StringBuilder(length), "\n", null, null, 0, null, null, 124)).toString();
        }
        throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
    }

    public static final String b(String str) {
        String str2 = "";
        List h = addc.h(str);
        Iterable iterable = h;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if ((1 ^ addb.a((String) next)) != 0) {
                arrayList.add(next);
            }
        }
        Iterable<String> iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList(acns.a((Iterable) iterable2, 10));
        for (String c : iterable2) {
            arrayList2.add(Integer.valueOf(adcu.c(c)));
        }
        Integer num = (Integer) acnz.r((List) arrayList2);
        int intValue = num != null ? num.intValue() : 0;
        int length = str.length() + (str2.length() * h.size());
        acqr d = adcu.d(str2);
        int a = acnr.a(h);
        Collection arrayList3 = new ArrayList();
        int i = 0;
        for (Object next2 : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                acnr.a();
            }
            Object next22 = (String) next22;
            if ((i == 0 || i == a) && addb.a((CharSequence) next22)) {
                next22 = null;
            } else {
                if ((intValue >= 0 ? 1 : null) != null) {
                    String substring = next22.substring(actx.d(intValue, next22.length()));
                    if (substring != null) {
                        substring = (String) d.invoke(substring);
                        if (substring != null) {
                            next22 = substring;
                        }
                    }
                } else {
                    StringBuilder stringBuilder = new StringBuilder("Requested character count ");
                    stringBuilder.append(intValue);
                    stringBuilder.append(" is less than zero.");
                    throw new IllegalArgumentException(stringBuilder.toString().toString());
                }
            }
            if (next22 != null) {
                arrayList3.add(next22);
            }
            i = i2;
        }
        return ((StringBuilder) acnz.a((List) arrayList3, new StringBuilder(length), "\n", null, null, 0, null, null, 124)).toString();
    }
}
