package org.a.a;

public final class j extends t {
    public String a;
    public String b;

    public j(k input, String ruleName, String predicateText) {
        super(input);
        this.a = ruleName;
        this.b = predicateText;
    }

    public final String toString() {
        return "FailedPredicateException(" + this.a + ",{" + this.b + "}?)";
    }
}
