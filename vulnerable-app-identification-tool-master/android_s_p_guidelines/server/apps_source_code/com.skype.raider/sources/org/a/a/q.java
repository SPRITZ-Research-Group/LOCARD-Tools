package org.a.a;

public final class q extends t {
    public String a;
    public int b;
    public int c;

    public q(String grammarDecisionDescription, int decisionNumber, int stateNumber, k input) {
        super(input);
        this.a = grammarDecisionDescription;
        this.b = decisionNumber;
        this.c = stateNumber;
    }

    public final String toString() {
        if (this.d instanceof e) {
            return "NoViableAltException('" + ((char) a()) + "'@[" + this.a + "])";
        }
        return "NoViableAltException(" + a() + "@[" + this.a + "])";
    }
}
