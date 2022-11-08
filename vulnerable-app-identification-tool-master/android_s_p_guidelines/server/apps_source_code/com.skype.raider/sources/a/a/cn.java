package a.a;

public final class cn {
    public static <Source, Target> Target a(Source source, Class<Target> cls) {
        if (cls.isAssignableFrom(source.getClass())) {
            return cls.cast(source);
        }
        return null;
    }
}
