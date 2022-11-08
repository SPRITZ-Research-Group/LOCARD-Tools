package com.skype.smsmanager.mms.pdu;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

public class PduComposer {
    static final /* synthetic */ boolean c;
    private static HashMap<String, Integer> h;
    protected ByteArrayOutputStream a = null;
    protected int b = 0;
    private GenericPdu d = null;
    private a e = null;
    private final ContentResolver f;
    private PduHeaders g = null;

    private class a {
        int a;
        final /* synthetic */ PduComposer b;
        private b c;
        private b d;

        private a(PduComposer pduComposer) {
            this.b = pduComposer;
            this.c = null;
            this.d = null;
            this.a = 0;
        }

        /* synthetic */ a(PduComposer x0, byte b) {
            this(x0);
        }

        final void a() {
            if (this.d != null) {
                throw new RuntimeException("BUG: Invalid newbuf() before copy()");
            }
            b temp = new b();
            temp.a = this.b.a;
            temp.b = this.b.b;
            temp.c = this.c;
            this.c = temp;
            this.a++;
            this.b.a = new ByteArrayOutputStream();
            this.b.b = 0;
        }

        final void b() {
            ByteArrayOutputStream currentMessage = this.b.a;
            int currentPosition = this.b.b;
            this.b.a = this.c.a;
            this.b.b = this.c.b;
            this.d = this.c;
            this.c = this.c.c;
            this.a--;
            this.d.a = currentMessage;
            this.d.b = currentPosition;
        }

        final void c() {
            this.b.a(this.d.a.toByteArray(), this.d.b);
            this.d = null;
        }

        final c d() {
            c m = new c(this.b, (byte) 0);
            m.b = this.b.b;
            m.c = this.a;
            return m;
        }
    }

    private static class b {
        ByteArrayOutputStream a;
        public int b;
        public b c;

        private b() {
            this.a = null;
            this.b = 0;
            this.c = null;
        }

        /* synthetic */ b(byte b) {
            this();
        }
    }

    private class c {
        final /* synthetic */ PduComposer a;
        private int b;
        private int c;

        private c(PduComposer pduComposer) {
            this.a = pduComposer;
        }

        /* synthetic */ c(PduComposer x0, byte b) {
            this(x0);
        }

        final int a() {
            if (this.c == this.a.e.a) {
                return this.a.b - this.b;
            }
            throw new RuntimeException("BUG: Invalid call to getLength()");
        }
    }

    static {
        boolean z;
        if (PduComposer.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        c = z;
        h = null;
        h = new HashMap();
        for (int i = 0; i < PduContentTypes.a.length; i++) {
            h.put(PduContentTypes.a[i], Integer.valueOf(i));
        }
    }

    public PduComposer(Context context, GenericPdu pdu) {
        this.d = pdu;
        this.f = context.getContentResolver();
        this.g = pdu.a;
        this.e = new a();
        this.a = new ByteArrayOutputStream();
        this.b = 0;
    }

    public final byte[] a() {
        int i = 0;
        int i2 = 1;
        switch (this.d.a()) {
            case 128:
                if (this.a == null) {
                    this.a = new ByteArrayOutputStream();
                    this.b = 0;
                }
                a(140);
                a(128);
                a(152);
                byte[] b = this.g.b(152);
                if (b == null) {
                    throw new IllegalArgumentException("Transaction-ID is null.");
                }
                a(b);
                if (c(141) == 0) {
                    c(133);
                    if (c(137) == 0) {
                        if (c(151) != 1) {
                            i = 1;
                        }
                        if (c(130) != 1) {
                            i = 1;
                        }
                        if (c(129) != 1) {
                            i = 1;
                        }
                        if (i != 0) {
                            c(150);
                            c(138);
                            c(136);
                            c(143);
                            c(134);
                            c(144);
                            a(132);
                            i2 = b();
                        }
                    }
                }
                if (i2 != 0) {
                    return null;
                }
                break;
            case 131:
                if (this.a == null) {
                    this.a = new ByteArrayOutputStream();
                    this.b = 0;
                }
                a(140);
                a(131);
                if (c(152) == 0 && c(141) == 0 && c(149) == 0) {
                    i2 = 0;
                }
                if (i2 != 0) {
                    return null;
                }
                break;
            case 133:
                if (this.a == null) {
                    this.a = new ByteArrayOutputStream();
                    this.b = 0;
                }
                a(140);
                a(133);
                if (c(152) == 0 && c(141) == 0) {
                    c(145);
                    i2 = 0;
                }
                if (i2 != 0) {
                    return null;
                }
                break;
            case 135:
                if (this.a == null) {
                    this.a = new ByteArrayOutputStream();
                    this.b = 0;
                }
                a(140);
                a(135);
                if (c(141) == 0 && c(139) == 0 && c(151) == 0 && c(137) == 0) {
                    c(133);
                    if (c(155) == 0) {
                        i2 = 0;
                    }
                }
                if (i2 != 0) {
                    return null;
                }
                break;
            default:
                return null;
        }
        return this.a.toByteArray();
    }

    protected final void a(byte[] buf, int length) {
        this.a.write(buf, 0, length);
        this.b += length;
    }

    private void a(int value) {
        this.a.write(value);
        this.b++;
    }

    private void b(int value) {
        a((value | 128) & 255);
    }

    private void a(long longInt) {
        long temp = longInt;
        int size = 0;
        while (temp != 0 && size < 8) {
            temp >>>= 8;
            size++;
        }
        a(size);
        int shift = (size - 1) * 8;
        for (int i = 0; i < size; i++) {
            a((int) ((longInt >>> shift) & 255));
            shift -= 8;
        }
    }

    private void a(byte[] text) {
        if ((text[0] & 255) > 127) {
            a(127);
        }
        a(text, text.length);
        a(0);
    }

    private void a(EncodedStringValue enStr) {
        if (c || enStr != null) {
            int charset = enStr.a();
            byte[] textString = enStr.b();
            this.e.a();
            c start = this.e.d();
            b(charset);
            a(textString);
            int len = start.a();
            this.e.b();
            d((long) len);
            this.e.c();
            return;
        }
        throw new AssertionError();
    }

    private void b(long value) {
        long max = 127;
        int i = 0;
        while (i < 5 && value >= max) {
            max = (max << 7) | 127;
            i++;
        }
        while (i > 0) {
            a((int) ((128 | ((value >>> (i * 7)) & 127)) & 255));
            i--;
        }
        a((int) (value & 127));
    }

    private void c(long date) {
        a(date);
    }

    private void d(long value) {
        if (value < 31) {
            a((int) value);
            return;
        }
        a(31);
        b(value);
    }

    private void b(byte[] text) {
        a(34);
        a(text, text.length);
        a(0);
    }

    private static EncodedStringValue b(EncodedStringValue address) {
        try {
            int addressType;
            String c = address.c();
            if (c.matches("[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}")) {
                addressType = 3;
            } else if (c.matches("\\+?[0-9|\\.|\\-]+")) {
                addressType = 1;
            } else if (c.matches("[a-zA-Z| ]*\\<{0,1}[a-zA-Z| ]+@{1}[a-zA-Z| ]+\\.{1}[a-zA-Z| ]+\\>{0,1}")) {
                addressType = 2;
            } else if (c.matches("[a-fA-F]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}")) {
                addressType = 4;
            } else {
                addressType = 5;
            }
            EncodedStringValue temp = EncodedStringValue.a(address);
            if (1 == addressType) {
                temp.b("/TYPE=PLMN".getBytes());
                return temp;
            } else if (3 == addressType) {
                temp.b("/TYPE=IPV4".getBytes());
                return temp;
            } else if (4 != addressType) {
                return temp;
            } else {
                temp.b("/TYPE=IPV6".getBytes());
                return temp;
            }
        } catch (NullPointerException e) {
            return null;
        }
    }

    private int c(int field) {
        EncodedStringValue temp;
        switch (field) {
            case 129:
            case 130:
            case 151:
                EncodedStringValue[] addr = this.g.d(field);
                if (addr != null) {
                    for (EncodedStringValue b : addr) {
                        temp = b(b);
                        if (temp == null) {
                            return 1;
                        }
                        a(field);
                        a(temp);
                    }
                    break;
                }
                return 2;
            case 133:
                long date = this.g.e(field);
                if (-1 != date) {
                    a(field);
                    c(date);
                    break;
                }
                return 2;
            case 134:
            case 143:
            case 144:
            case 145:
            case 149:
            case 155:
                int octet = this.g.a(field);
                if (octet != 0) {
                    a(field);
                    a(octet);
                    break;
                }
                return 2;
            case 136:
                long expiry = this.g.e(field);
                if (-1 != expiry) {
                    a(field);
                    this.e.a();
                    c expiryStart = this.e.d();
                    a(129);
                    a(expiry);
                    int expiryLength = expiryStart.a();
                    this.e.b();
                    d((long) expiryLength);
                    this.e.c();
                    break;
                }
                return 2;
            case 137:
                a(field);
                EncodedStringValue from = this.g.c(field);
                if (from != null && !TextUtils.isEmpty(from.c()) && !new String(from.b()).equals("insert-address-token")) {
                    this.e.a();
                    c fstart = this.e.d();
                    a(128);
                    temp = b(from);
                    if (temp != null) {
                        a(temp);
                        int flen = fstart.a();
                        this.e.b();
                        d((long) flen);
                        this.e.c();
                        break;
                    }
                    return 1;
                }
                a(1);
                a(129);
                break;
            case 138:
                byte[] messageClass = this.g.b(field);
                if (messageClass != null) {
                    a(field);
                    if (!Arrays.equals(messageClass, "advertisement".getBytes())) {
                        if (!Arrays.equals(messageClass, "auto".getBytes())) {
                            if (!Arrays.equals(messageClass, "personal".getBytes())) {
                                if (!Arrays.equals(messageClass, "informational".getBytes())) {
                                    a(messageClass);
                                    break;
                                }
                                a(130);
                                break;
                            }
                            a(128);
                            break;
                        }
                        a(131);
                        break;
                    }
                    a(129);
                    break;
                }
                return 2;
            case 139:
            case 152:
                byte[] textString = this.g.b(field);
                if (textString != null) {
                    a(field);
                    a(textString);
                    break;
                }
                return 2;
            case 141:
                a(field);
                int version = this.g.a(field);
                if (version != 0) {
                    b(version);
                    break;
                }
                b(18);
                break;
            case 150:
                EncodedStringValue enString = this.g.c(field);
                if (enString != null) {
                    a(field);
                    a(enString);
                    break;
                }
                return 2;
            default:
                return 3;
        }
        return 0;
    }

    private int b() {
        this.e.a();
        c ctStart = this.e.d();
        Integer contentTypeIdentifier = (Integer) h.get(new String(this.g.b(132)));
        if (contentTypeIdentifier == null) {
            return 1;
        }
        b(contentTypeIdentifier.intValue());
        PduBody body = ((SendReq) this.d).c();
        if (body == null || body.b() == 0) {
            b(0);
            this.e.b();
            this.e.c();
            return 0;
        }
        PduPart part;
        try {
            part = body.a(0);
            byte[] start = part.c();
            if (start != null) {
                a(138);
                if ((byte) 60 == start[0] && (byte) 62 == start[start.length - 1]) {
                    a(start);
                } else {
                    a(("<" + new String(start) + ">").getBytes());
                }
            }
            a(137);
            a(part.f());
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        int ctLength = ctStart.a();
        this.e.b();
        d((long) ctLength);
        this.e.c();
        int partNum = body.b();
        b((long) partNum);
        for (int i = 0; i < partNum; i++) {
            part = body.a(i);
            this.e.a();
            c attachment = this.e.d();
            this.e.a();
            c contentTypeBegin = this.e.d();
            byte[] partContentType = part.f();
            if (partContentType == null) {
                return 1;
            }
            Integer partContentTypeIdentifier = (Integer) h.get(new String(partContentType));
            if (partContentTypeIdentifier == null) {
                a(partContentType);
            } else {
                b(partContentTypeIdentifier.intValue());
            }
            byte[] name = part.h();
            if (name == null) {
                name = part.i();
                if (name == null) {
                    name = part.e();
                    if (name == null) {
                        return 1;
                    }
                }
            }
            a(133);
            a(name);
            int charset = part.d();
            if (charset != 0) {
                a(129);
                b(charset);
            }
            int contentTypeLength = contentTypeBegin.a();
            this.e.b();
            d((long) contentTypeLength);
            this.e.c();
            byte[] contentId = part.c();
            if (contentId != null) {
                a(192);
                if ((byte) 60 == contentId[0] && (byte) 62 == contentId[contentId.length - 1]) {
                    b(contentId);
                } else {
                    b(("<" + new String(contentId) + ">").getBytes());
                }
            }
            byte[] contentLocation = part.e();
            if (contentLocation != null) {
                a(142);
                a(contentLocation);
            }
            int headerLength = attachment.a();
            int dataLength = 0;
            byte[] partData = part.a();
            if (partData != null) {
                a(partData, partData.length);
                dataLength = partData.length;
            } else {
                InputStream cr = null;
                try {
                    byte[] buffer = new byte[1024];
                    cr = this.f.openInputStream(part.b());
                    while (true) {
                        int len = cr.read(buffer);
                        if (len == -1) {
                            break;
                        }
                        this.a.write(buffer, 0, len);
                        this.b += len;
                        dataLength += len;
                    }
                    if (cr != null) {
                        try {
                            cr.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (FileNotFoundException e3) {
                    if (cr != null) {
                        try {
                            cr.close();
                        } catch (IOException e4) {
                        }
                    }
                    return 1;
                } catch (IOException e5) {
                    if (cr != null) {
                        try {
                            cr.close();
                        } catch (IOException e6) {
                        }
                    }
                    return 1;
                } catch (RuntimeException e7) {
                    if (cr != null) {
                        try {
                            cr.close();
                        } catch (IOException e8) {
                        }
                    }
                    return 1;
                } catch (Throwable th) {
                    if (cr != null) {
                        try {
                            cr.close();
                        } catch (IOException e9) {
                        }
                    }
                }
            }
            if (dataLength != attachment.a() - headerLength) {
                throw new RuntimeException("BUG: Length sanity check failed");
            }
            this.e.b();
            b((long) headerLength);
            b((long) dataLength);
            this.e.c();
        }
        return 0;
    }
}
