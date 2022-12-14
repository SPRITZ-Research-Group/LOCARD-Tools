package com.skype.smsmanager.mms.pdu;

import android.net.Uri;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.Mms.Sent;
import com.skype.smsmanager.mms.util.PduCache;
import java.util.HashMap;

public class PduPersister {
    static final /* synthetic */ boolean a;
    private static final PduCache b = PduCache.a();
    private static final int[] c = new int[]{129, 130, 137, 151};
    private static final String[] d = new String[]{"_id", "msg_box", "thread_id", "retr_txt", "sub", "ct_l", "ct_t", "m_cls", "m_id", "resp_txt", "tr_id", "ct_cls", "d_rpt", "m_type", "v", "pri", "rr", "read_status", "rpt_a", "retr_st", "st", "date", "d_tm", "exp", "m_size", "sub_cs", "retr_txt_cs"};
    private static final String[] e = new String[]{"_id", "chset", "cd", "cid", "cl", "ct", "fn", "name", "text"};
    private static final HashMap<Uri, Integer> f;
    private static final HashMap<Integer, Integer> g;
    private static final HashMap<Integer, Integer> h;
    private static final HashMap<Integer, Integer> i;
    private static final HashMap<Integer, Integer> j;
    private static final HashMap<Integer, Integer> k;
    private static final HashMap<Integer, String> l;
    private static final HashMap<Integer, String> m;
    private static final HashMap<Integer, String> n;
    private static final HashMap<Integer, String> o;
    private static final HashMap<Integer, String> p;

    static {
        boolean z;
        if (PduPersister.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
        HashMap hashMap = new HashMap();
        f = hashMap;
        hashMap.put(Inbox.CONTENT_URI, Integer.valueOf(1));
        f.put(Sent.CONTENT_URI, Integer.valueOf(2));
        f.put(Draft.CONTENT_URI, Integer.valueOf(3));
        f.put(Outbox.CONTENT_URI, Integer.valueOf(4));
        hashMap = new HashMap();
        g = hashMap;
        hashMap.put(Integer.valueOf(150), Integer.valueOf(25));
        g.put(Integer.valueOf(154), Integer.valueOf(26));
        hashMap = new HashMap();
        l = hashMap;
        hashMap.put(Integer.valueOf(150), "sub_cs");
        l.put(Integer.valueOf(154), "retr_txt_cs");
        hashMap = new HashMap();
        h = hashMap;
        hashMap.put(Integer.valueOf(154), Integer.valueOf(3));
        h.put(Integer.valueOf(150), Integer.valueOf(4));
        hashMap = new HashMap();
        m = hashMap;
        hashMap.put(Integer.valueOf(154), "retr_txt");
        m.put(Integer.valueOf(150), "sub");
        hashMap = new HashMap();
        i = hashMap;
        hashMap.put(Integer.valueOf(131), Integer.valueOf(5));
        i.put(Integer.valueOf(132), Integer.valueOf(6));
        i.put(Integer.valueOf(138), Integer.valueOf(7));
        i.put(Integer.valueOf(139), Integer.valueOf(8));
        i.put(Integer.valueOf(147), Integer.valueOf(9));
        i.put(Integer.valueOf(152), Integer.valueOf(10));
        hashMap = new HashMap();
        n = hashMap;
        hashMap.put(Integer.valueOf(131), "ct_l");
        n.put(Integer.valueOf(132), "ct_t");
        n.put(Integer.valueOf(138), "m_cls");
        n.put(Integer.valueOf(139), "m_id");
        n.put(Integer.valueOf(147), "resp_txt");
        n.put(Integer.valueOf(152), "tr_id");
        hashMap = new HashMap();
        j = hashMap;
        hashMap.put(Integer.valueOf(186), Integer.valueOf(11));
        j.put(Integer.valueOf(134), Integer.valueOf(12));
        j.put(Integer.valueOf(140), Integer.valueOf(13));
        j.put(Integer.valueOf(141), Integer.valueOf(14));
        j.put(Integer.valueOf(143), Integer.valueOf(15));
        j.put(Integer.valueOf(144), Integer.valueOf(16));
        j.put(Integer.valueOf(155), Integer.valueOf(17));
        j.put(Integer.valueOf(145), Integer.valueOf(18));
        j.put(Integer.valueOf(153), Integer.valueOf(19));
        j.put(Integer.valueOf(149), Integer.valueOf(20));
        hashMap = new HashMap();
        o = hashMap;
        hashMap.put(Integer.valueOf(186), "ct_cls");
        o.put(Integer.valueOf(134), "d_rpt");
        o.put(Integer.valueOf(140), "m_type");
        o.put(Integer.valueOf(141), "v");
        o.put(Integer.valueOf(143), "pri");
        o.put(Integer.valueOf(144), "rr");
        o.put(Integer.valueOf(155), "read_status");
        o.put(Integer.valueOf(145), "rpt_a");
        o.put(Integer.valueOf(153), "retr_st");
        o.put(Integer.valueOf(149), "st");
        hashMap = new HashMap();
        k = hashMap;
        hashMap.put(Integer.valueOf(133), Integer.valueOf(21));
        k.put(Integer.valueOf(135), Integer.valueOf(22));
        k.put(Integer.valueOf(136), Integer.valueOf(23));
        k.put(Integer.valueOf(142), Integer.valueOf(24));
        hashMap = new HashMap();
        p = hashMap;
        hashMap.put(Integer.valueOf(133), "date");
        p.put(Integer.valueOf(135), "d_tm");
        p.put(Integer.valueOf(136), "exp");
        p.put(Integer.valueOf(142), "m_size");
    }
}
