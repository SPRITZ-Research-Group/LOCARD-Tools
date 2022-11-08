package com.skype.android.gen;

import com.skype.GI;
import com.skype.GI.CONNSTATUS;
import com.skype.GI.FILEERROR;
import com.skype.GI.GIIListener;
import com.skype.GI.LIBSTATUS;
import com.skype.GI.PROXYTYPE;

public class GILogListener implements GIIListener {
    public void onConnStatusChange(GI sender, CONNSTATUS connStatus) {
    }

    public void onFileError(GI sender, FILEERROR error) {
    }

    public void onLibStatusChange(GI sender, LIBSTATUS newStatus) {
    }

    public void onNodeinfoChange(GI sender, byte[] nodeinfo) {
    }

    public void onProxyAuthenticationFailure(GI sender, PROXYTYPE proxyType) {
    }
}
