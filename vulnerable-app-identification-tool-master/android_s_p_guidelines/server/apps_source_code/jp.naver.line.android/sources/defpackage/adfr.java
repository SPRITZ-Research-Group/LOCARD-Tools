package defpackage;

import java.io.Serializable;
import org.apache.thrift.protocol.h;

/* renamed from: adfr */
public interface adfr<T extends adfr<?, ?>, F extends adga> extends Serializable, Comparable<T> {
    adfr<T, F> deepCopy();

    void read(h hVar) throws adfz;

    void write(h hVar) throws adfz;
}
