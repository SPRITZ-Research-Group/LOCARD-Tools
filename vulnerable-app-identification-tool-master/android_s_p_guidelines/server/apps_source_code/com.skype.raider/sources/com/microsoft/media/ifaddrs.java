package com.microsoft.media;

import java.net.InetAddress;

public class ifaddrs {
    private InetAddress ifa_addr;
    private int ifa_flags;
    private int ifa_index;
    private String ifa_name;
    private int ifa_prefixlen;
    private int ifa_type;

    public ifaddrs(String name, int flags, InetAddress address, int prefixLength, int index, int type) {
        this.ifa_name = name;
        this.ifa_flags = flags;
        this.ifa_addr = address;
        this.ifa_prefixlen = prefixLength;
        this.ifa_index = index;
        this.ifa_type = type;
    }

    public String toString() {
        return "Name:" + this.ifa_name + ", Addr:" + this.ifa_addr.toString() + ", prefix:" + this.ifa_prefixlen + ", index:" + this.ifa_index + ", type:" + this.ifa_type;
    }
}
