package kotlin.reflect.jvm.internal.impl.incremental.components;

import defpackage.acru;
import java.io.Serializable;

public final class Position implements Serializable {
    public static final Companion Companion = new Companion();
    private static final Position NO_POSITION = new Position(-1, -1);
    private final int column;
    private final int line;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final Position getNO_POSITION() {
            return Position.NO_POSITION;
        }
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Position) {
                Position position = (Position) obj;
                if ((this.line == position.line ? 1 : null) != null) {
                    if ((this.column == position.column ? 1 : null) != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (this.line * 31) + this.column;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("Position(line=");
        stringBuilder.append(this.line);
        stringBuilder.append(", column=");
        stringBuilder.append(this.column);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public Position(int i, int i2) {
        this.line = i;
        this.column = i2;
    }
}
