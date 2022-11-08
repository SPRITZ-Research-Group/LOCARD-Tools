package defpackage;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* renamed from: adcs */
final class adcs {
    public static final adcn a;
    public static final adcs b = new adcs();

    static {
        String str = "(\\p{Digit}+)";
        String str2 = "(\\p{XDigit}+)";
        String concat = "[eE][+-]?".concat(String.valueOf(str));
        StringBuilder stringBuilder = new StringBuilder("(0[xX]");
        stringBuilder.append(str2);
        stringBuilder.append("(\\.)?)|(0[xX]");
        stringBuilder.append(str2);
        stringBuilder.append("?(\\.)");
        stringBuilder.append(str2);
        stringBuilder.append(')');
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder("(");
        stringBuilder3.append(str);
        stringBuilder3.append("(\\.)?(");
        stringBuilder3.append(str);
        stringBuilder3.append("?)(");
        stringBuilder3.append(concat);
        stringBuilder3.append(")?)|(\\.(");
        stringBuilder3.append(str);
        stringBuilder3.append(")(");
        stringBuilder3.append(concat);
        stringBuilder3.append(")?)|((");
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(")[pP][+-]?");
        stringBuilder3.append(str);
        stringBuilder3.append(')');
        str = stringBuilder3.toString();
        StringBuilder stringBuilder4 = new StringBuilder("[\\x00-\\x20]*[+-]?(NaN|Infinity|((");
        stringBuilder4.append(str);
        stringBuilder4.append(")[fFdD]?))[\\x00-\\x20]*");
        a = new adcn(stringBuilder4.toString());
    }

    private adcs() {
    }
}
