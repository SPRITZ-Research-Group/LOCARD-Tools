package com.skype.android.video.hw.format;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

class FormatMapper<TypeSliq, TypeOmx, Format extends Enum<Format> & SliqOmxMapping<TypeSliq, TypeOmx>> {
    private final Map<String, Format> fromName = new HashMap();
    private final Map<TypeOmx, Format> fromOmx = new HashMap();
    private final Map<TypeSliq, Format> fromSliq = new HashMap();

    public FormatMapper(Format[] all) {
        for (Format f : all) {
            this.fromSliq.put(((SliqOmxMapping) f).getSliqValue(), f);
            this.fromOmx.put(((SliqOmxMapping) f).getOmxValue(), f);
            this.fromName.put(((SliqOmxMapping) f).getName(), f);
        }
    }

    public Format fromSliq(TypeSliq value) {
        Enum format = (Enum) this.fromSliq.get(value);
        if (format != null) {
            return format;
        }
        throw new NoSuchElementException(String.valueOf(value));
    }

    public Format fromOmx(TypeOmx value) {
        Enum format = (Enum) this.fromOmx.get(value);
        if (format != null) {
            return format;
        }
        throw new NoSuchElementException(String.valueOf(value));
    }

    public Format fromName(String name) {
        Enum format = (Enum) this.fromName.get(name);
        if (format != null) {
            return format;
        }
        throw new NoSuchElementException(name);
    }
}
