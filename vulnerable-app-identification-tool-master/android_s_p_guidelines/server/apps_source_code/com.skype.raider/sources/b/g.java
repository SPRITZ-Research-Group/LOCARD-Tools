package b;

public final class g extends RuntimeException {
    public g(Exception e) {
        super("An exception was thrown by an Executor", e);
    }
}
