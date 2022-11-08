package com.skype.android.video.hw.format;

interface SliqOmxMapping<TypeSliq, TypeOmx> {
    String getName();

    TypeOmx getOmxValue();

    TypeSliq getSliqValue();
}
