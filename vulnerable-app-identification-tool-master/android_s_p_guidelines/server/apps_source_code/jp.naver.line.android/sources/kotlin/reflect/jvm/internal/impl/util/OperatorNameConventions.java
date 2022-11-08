package kotlin.reflect.jvm.internal.impl.util;

import defpackage.acot;
import defpackage.adcn;
import java.util.Set;
import jp.naver.android.npush.network.NPushProtocol;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class OperatorNameConventions {
    public static final Name AND = Name.identifier("and");
    public static final Set<Name> ASSIGNMENT_OPERATIONS = acot.a((Object[]) new Name[]{TIMES_ASSIGN, DIV_ASSIGN, MOD_ASSIGN, REM_ASSIGN, PLUS_ASSIGN, MINUS_ASSIGN});
    public static final Set<Name> BINARY_OPERATION_NAMES = acot.a((Object[]) new Name[]{TIMES, PLUS, MINUS, DIV, MOD, REM, RANGE_TO});
    public static final Name COMPARE_TO = Name.identifier("compareTo");
    public static final adcn COMPONENT_REGEX = new adcn("component\\d+");
    public static final Name CONTAINS = Name.identifier("contains");
    public static final Name DEC = Name.identifier("dec");
    public static final Name DIV = Name.identifier(TtmlNode.TAG_DIV);
    public static final Name DIV_ASSIGN = Name.identifier("divAssign");
    public static final Name EQUALS = Name.identifier("equals");
    public static final Name GET = Name.identifier("get");
    public static final Name GET_VALUE = Name.identifier("getValue");
    public static final Name HAS_NEXT = Name.identifier("hasNext");
    public static final Name INC = Name.identifier("inc");
    public static final OperatorNameConventions INSTANCE = new OperatorNameConventions();
    public static final Name INVOKE = Name.identifier("invoke");
    public static final Name ITERATOR = Name.identifier("iterator");
    public static final Name MINUS = Name.identifier("minus");
    public static final Name MINUS_ASSIGN = Name.identifier("minusAssign");
    public static final Name MOD = Name.identifier(NPushProtocol.PROTOCOL_KEY_MOD);
    public static final Name MOD_ASSIGN = Name.identifier("modAssign");
    public static final Name NEXT = Name.identifier("next");
    public static final Name NOT = Name.identifier("not");
    public static final Name OR = Name.identifier("or");
    public static final Name PLUS = Name.identifier("plus");
    public static final Name PLUS_ASSIGN = Name.identifier("plusAssign");
    public static final Name PROVIDE_DELEGATE = Name.identifier("provideDelegate");
    public static final Name RANGE_TO = Name.identifier("rangeTo");
    public static final Name REM = Name.identifier("rem");
    public static final Name REM_ASSIGN = Name.identifier("remAssign");
    public static final Name SET = Name.identifier("set");
    public static final Name SET_VALUE = Name.identifier("setValue");
    public static final Set<Name> SIMPLE_UNARY_OPERATION_NAMES = acot.a((Object[]) new Name[]{UNARY_PLUS, UNARY_MINUS, NOT});
    public static final Name TIMES = Name.identifier("times");
    public static final Name TIMES_ASSIGN = Name.identifier("timesAssign");
    public static final Name UNARY_MINUS = Name.identifier("unaryMinus");
    public static final Set<Name> UNARY_OPERATION_NAMES = acot.a((Object[]) new Name[]{INC, DEC, UNARY_PLUS, UNARY_MINUS, NOT});
    public static final Name UNARY_PLUS = Name.identifier("unaryPlus");

    private OperatorNameConventions() {
    }
}
