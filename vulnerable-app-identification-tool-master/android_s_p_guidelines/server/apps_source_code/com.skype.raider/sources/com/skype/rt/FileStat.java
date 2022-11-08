package com.skype.rt;

public class FileStat {
    boolean isDirectory;
    long size;

    FileStat(boolean isDirectory, long size) {
        this.isDirectory = isDirectory;
        this.size = size;
    }
}
