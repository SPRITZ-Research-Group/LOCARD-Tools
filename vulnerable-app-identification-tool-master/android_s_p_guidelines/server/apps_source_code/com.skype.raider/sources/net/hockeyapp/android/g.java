package net.hockeyapp.android;

import java.util.LinkedList;
import java.util.List;

public final class g {
    private static List<a> a = new LinkedList();

    public interface a {
    }

    public static void a(a listener) {
        a.add(listener);
    }
}
