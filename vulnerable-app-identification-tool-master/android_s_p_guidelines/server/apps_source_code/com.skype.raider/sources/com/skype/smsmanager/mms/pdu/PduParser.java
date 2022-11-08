package com.skype.smsmanager.mms.pdu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;

public class PduParser {
    static final /* synthetic */ boolean a;
    private static byte[] e = null;
    private static byte[] f = null;
    private ByteArrayInputStream b = null;
    private PduHeaders c = null;
    private PduBody d = null;
    private final boolean g;

    static {
        boolean z;
        if (PduParser.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
    }

    public PduParser(byte[] pduDataStream, boolean parseContentDisposition) {
        this.b = new ByteArrayInputStream(pduDataStream);
        this.g = parseContentDisposition;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final GenericPdu a() {
        if (this.b == null) {
            return null;
        }
        this.c = a(this.b);
        if (this.c == null) {
            return null;
        }
        int i;
        int messageType = this.c.a(140);
        PduHeaders pduHeaders = this.c;
        if (pduHeaders != null) {
            int a = pduHeaders.a(140);
            if (pduHeaders.a(141) != 0) {
                switch (a) {
                    case 128:
                        if (pduHeaders.b(132) != null) {
                            if (pduHeaders.c(137) != null) {
                                if (pduHeaders.b(152) == null) {
                                    i = 0;
                                    break;
                                }
                            }
                            i = 0;
                            break;
                        }
                        i = 0;
                        break;
                    case 129:
                        if (pduHeaders.a(146) != 0) {
                            if (pduHeaders.b(152) == null) {
                                i = 0;
                                break;
                            }
                        }
                        i = 0;
                        break;
                    case 130:
                        if (pduHeaders.b(131) != null) {
                            if (-1 != pduHeaders.e(136)) {
                                if (pduHeaders.b(138) != null) {
                                    if (-1 != pduHeaders.e(142)) {
                                        if (pduHeaders.b(152) == null) {
                                            i = 0;
                                            break;
                                        }
                                    }
                                    i = 0;
                                    break;
                                }
                                i = 0;
                                break;
                            }
                            i = 0;
                            break;
                        }
                        i = 0;
                        break;
                    case 131:
                        if (pduHeaders.a(149) != 0) {
                            if (pduHeaders.b(152) == null) {
                                i = 0;
                                break;
                            }
                        }
                        i = 0;
                        break;
                    case 132:
                        if (pduHeaders.b(132) != null) {
                            if (-1 == pduHeaders.e(133)) {
                                i = 0;
                                break;
                            }
                        }
                        i = 0;
                        break;
                    case 133:
                        if (pduHeaders.b(152) == null) {
                            i = 0;
                            break;
                        }
                    case 134:
                        if (-1 != pduHeaders.e(133)) {
                            if (pduHeaders.b(139) != null) {
                                if (pduHeaders.a(149) != 0) {
                                    if (pduHeaders.d(151) == null) {
                                        i = 0;
                                        break;
                                    }
                                }
                                i = 0;
                                break;
                            }
                            i = 0;
                            break;
                        }
                        i = 0;
                        break;
                    case 135:
                        if (pduHeaders.c(137) != null) {
                            if (pduHeaders.b(139) != null) {
                                if (pduHeaders.a(155) != 0) {
                                    if (pduHeaders.d(151) == null) {
                                        i = 0;
                                        break;
                                    }
                                }
                                i = 0;
                                break;
                            }
                            i = 0;
                            break;
                        }
                        i = 0;
                        break;
                    case 136:
                        if (-1 != pduHeaders.e(133)) {
                            if (pduHeaders.c(137) != null) {
                                if (pduHeaders.b(139) != null) {
                                    if (pduHeaders.a(155) != 0) {
                                        if (pduHeaders.d(151) == null) {
                                            i = 0;
                                            break;
                                        }
                                    }
                                    i = 0;
                                    break;
                                }
                                i = 0;
                                break;
                            }
                            i = 0;
                            break;
                        }
                        i = 0;
                        break;
                    default:
                        i = 0;
                        break;
                }
            }
            i = 0;
        } else {
            i = 0;
        }
        if (i == 0) {
            return null;
        }
        if (128 == messageType || 132 == messageType) {
            this.d = b(this.b);
            if (this.d == null) {
                return null;
            }
        }
        switch (messageType) {
            case 128:
                return new SendReq(this.c, this.d);
            case 129:
                return new SendConf(this.c);
            case 130:
                return new NotificationInd(this.c);
            case 131:
                return new NotifyRespInd(this.c);
            case 132:
                GenericPdu retrieveConf = new RetrieveConf(this.c, this.d);
                byte[] contentType = retrieveConf.e();
                if (contentType == null) {
                    return null;
                }
                String ctTypeStr = new String(contentType);
                if (ctTypeStr.equals("application/vnd.wap.multipart.mixed") || ctTypeStr.equals("application/vnd.wap.multipart.related") || ctTypeStr.equals("application/vnd.wap.multipart.alternative")) {
                    return retrieveConf;
                }
                if (!ctTypeStr.equals("application/vnd.wap.multipart.alternative")) {
                    return null;
                }
                PduPart firstPart = this.d.a(0);
                this.d.a();
                this.d.b(firstPart);
                return retrieveConf;
            case 133:
                return new AcknowledgeInd(this.c);
            case 134:
                return new DeliveryInd(this.c);
            case 135:
                return new ReadRecInd(this.c);
            case 136:
                return new ReadOrigInd(this.c);
            default:
                return null;
        }
    }

    private static com.skype.smsmanager.mms.pdu.PduHeaders a(java.io.ByteArrayInputStream r28) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r17_0 'str' java.lang.String) in PHI: PHI: (r17_2 'str' java.lang.String) = (r17_0 'str' java.lang.String), (r17_1 'str' java.lang.String) binds: {(r17_0 'str' java.lang.String)=B:16:0x0057, (r17_1 'str' java.lang.String)=B:17:0x0059}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        if (r28 != 0) goto L_0x0004;
    L_0x0002:
        r8 = 0;
    L_0x0003:
        return r8;
    L_0x0004:
        r9 = 1;
        r8 = new com.skype.smsmanager.mms.pdu.PduHeaders;
        r8.<init>();
    L_0x000a:
        if (r9 == 0) goto L_0x0003;
    L_0x000c:
        r24 = r28.available();
        if (r24 <= 0) goto L_0x0003;
    L_0x0012:
        r24 = 1;
        r0 = r28;
        r1 = r24;
        r0.mark(r1);
        r7 = f(r28);
        r24 = 32;
        r0 = r24;
        if (r7 < r0) goto L_0x0038;
    L_0x0025:
        r24 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r24;
        if (r7 > r0) goto L_0x0038;
    L_0x002b:
        r28.reset();
        r24 = 0;
        r0 = r28;
        r1 = r24;
        a(r0, r1);
        goto L_0x000a;
    L_0x0038:
        switch(r7) {
            case 129: goto L_0x003c;
            case 130: goto L_0x003c;
            case 131: goto L_0x012f;
            case 132: goto L_0x040c;
            case 133: goto L_0x00f1;
            case 134: goto L_0x00b4;
            case 135: goto L_0x0195;
            case 136: goto L_0x0195;
            case 137: goto L_0x01e1;
            case 138: goto L_0x0264;
            case 139: goto L_0x012f;
            case 140: goto L_0x0076;
            case 141: goto L_0x0319;
            case 142: goto L_0x00f1;
            case 143: goto L_0x00b4;
            case 144: goto L_0x00b4;
            case 145: goto L_0x00b4;
            case 146: goto L_0x00b4;
            case 147: goto L_0x0159;
            case 148: goto L_0x00b4;
            case 149: goto L_0x00b4;
            case 150: goto L_0x0159;
            case 151: goto L_0x003c;
            case 152: goto L_0x012f;
            case 153: goto L_0x00b4;
            case 154: goto L_0x0159;
            case 155: goto L_0x00b4;
            case 156: goto L_0x00b4;
            case 157: goto L_0x0195;
            case 158: goto L_0x012f;
            case 159: goto L_0x00f1;
            case 160: goto L_0x035a;
            case 161: goto L_0x039c;
            case 162: goto L_0x00b4;
            case 163: goto L_0x00b4;
            case 164: goto L_0x03d7;
            case 165: goto L_0x00b4;
            case 166: goto L_0x0159;
            case 167: goto L_0x00b4;
            case 168: goto L_0x003b;
            case 169: goto L_0x00b4;
            case 170: goto L_0x03e2;
            case 171: goto L_0x00b4;
            case 172: goto L_0x03e2;
            case 173: goto L_0x0110;
            case 174: goto L_0x003b;
            case 175: goto L_0x0110;
            case 176: goto L_0x003b;
            case 177: goto L_0x00b4;
            case 178: goto L_0x0401;
            case 179: goto L_0x0110;
            case 180: goto L_0x00b4;
            case 181: goto L_0x0159;
            case 182: goto L_0x0159;
            case 183: goto L_0x012f;
            case 184: goto L_0x012f;
            case 185: goto L_0x012f;
            case 186: goto L_0x00b4;
            case 187: goto L_0x00b4;
            case 188: goto L_0x00b4;
            case 189: goto L_0x012f;
            case 190: goto L_0x012f;
            case 191: goto L_0x00b4;
            default: goto L_0x003b;
        };
    L_0x003b:
        goto L_0x000a;
    L_0x003c:
        r22 = e(r28);
        if (r22 == 0) goto L_0x000a;
    L_0x0042:
        r2 = r22.b();
        r17 = new java.lang.String;
        r0 = r17;
        r0.<init>(r2);
        r24 = "/";
        r0 = r17;
        r1 = r24;
        r4 = r0.indexOf(r1);
        if (r4 <= 0) goto L_0x0063;
    L_0x0059:
        r24 = 0;
        r0 = r17;
        r1 = r24;
        r17 = r0.substring(r1, r4);
    L_0x0063:
        r24 = r17.getBytes();	 Catch:{ NullPointerException -> 0x017d }
        r0 = r22;	 Catch:{ NullPointerException -> 0x017d }
        r1 = r24;	 Catch:{ NullPointerException -> 0x017d }
        r0.a(r1);	 Catch:{ NullPointerException -> 0x017d }
        r0 = r22;	 Catch:{ NullPointerException -> 0x0074, RuntimeException -> 0x0181 }
        r8.b(r0, r7);	 Catch:{ NullPointerException -> 0x0074, RuntimeException -> 0x0181 }
        goto L_0x000a;
    L_0x0074:
        r24 = move-exception;
        goto L_0x000a;
    L_0x0076:
        r13 = f(r28);
        switch(r13) {
            case 137: goto L_0x009d;
            case 138: goto L_0x009d;
            case 139: goto L_0x009d;
            case 140: goto L_0x009d;
            case 141: goto L_0x009d;
            case 142: goto L_0x009d;
            case 143: goto L_0x009d;
            case 144: goto L_0x009d;
            case 145: goto L_0x009d;
            case 146: goto L_0x009d;
            case 147: goto L_0x009d;
            case 148: goto L_0x009d;
            case 149: goto L_0x009d;
            case 150: goto L_0x009d;
            case 151: goto L_0x009d;
            default: goto L_0x007d;
        };
    L_0x007d:
        r8.a(r13, r7);	 Catch:{ InvalidHeaderValueException -> 0x0081, RuntimeException -> 0x00a0 }
        goto L_0x000a;
    L_0x0081:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r25 = "Set invalid Octet value: ";
        r24.<init>(r25);
        r0 = r24;
        r24 = r0.append(r13);
        r25 = " into the header filed: ";
        r24 = r24.append(r25);
        r0 = r24;
        r0.append(r7);
        r8 = 0;
        goto L_0x0003;
    L_0x009d:
        r8 = 0;
        goto L_0x0003;
    L_0x00a0:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Octet header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x00b4:
        r22 = f(r28);
        r0 = r22;	 Catch:{ InvalidHeaderValueException -> 0x00bf, RuntimeException -> 0x00dd }
        r8.a(r0, r7);	 Catch:{ InvalidHeaderValueException -> 0x00bf, RuntimeException -> 0x00dd }
        goto L_0x000a;
    L_0x00bf:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r25 = "Set invalid Octet value: ";
        r24.<init>(r25);
        r0 = r24;
        r1 = r22;
        r24 = r0.append(r1);
        r25 = " into the header filed: ";
        r24 = r24.append(r25);
        r0 = r24;
        r0.append(r7);
        r8 = 0;
        goto L_0x0003;
    L_0x00dd:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Octet header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x00f1:
        r22 = h(r28);	 Catch:{ RuntimeException -> 0x00fc }
        r0 = r22;	 Catch:{ RuntimeException -> 0x00fc }
        r8.a(r0, r7);	 Catch:{ RuntimeException -> 0x00fc }
        goto L_0x000a;
    L_0x00fc:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Long-Integer header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0110:
        r22 = i(r28);	 Catch:{ RuntimeException -> 0x011b }
        r0 = r22;	 Catch:{ RuntimeException -> 0x011b }
        r8.a(r0, r7);	 Catch:{ RuntimeException -> 0x011b }
        goto L_0x000a;
    L_0x011b:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Long-Integer header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x012f:
        r24 = 0;
        r0 = r28;
        r1 = r24;
        r22 = a(r0, r1);
        if (r22 == 0) goto L_0x000a;
    L_0x013b:
        r0 = r22;	 Catch:{ NullPointerException -> 0x0142, RuntimeException -> 0x0145 }
        r8.a(r0, r7);	 Catch:{ NullPointerException -> 0x0142, RuntimeException -> 0x0145 }
        goto L_0x000a;
    L_0x0142:
        r24 = move-exception;
        goto L_0x000a;
    L_0x0145:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Text-String header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0159:
        r22 = e(r28);
        if (r22 == 0) goto L_0x000a;
    L_0x015f:
        r0 = r22;	 Catch:{ NullPointerException -> 0x0166, RuntimeException -> 0x0169 }
        r8.a(r0, r7);	 Catch:{ NullPointerException -> 0x0166, RuntimeException -> 0x0169 }
        goto L_0x000a;
    L_0x0166:
        r24 = move-exception;
        goto L_0x000a;
    L_0x0169:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Encoded-String-Value header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x017d:
        r24 = move-exception;
        r8 = 0;
        goto L_0x0003;
    L_0x0181:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Encoded-String-Value header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0195:
        d(r28);
        r20 = f(r28);
        r18 = h(r28);	 Catch:{ RuntimeException -> 0x01cd }
        r24 = 129; // 0x81 float:1.81E-43 double:6.37E-322;
        r0 = r24;
        r1 = r20;
        if (r0 != r1) goto L_0x01b2;
    L_0x01a8:
        r24 = java.lang.System.currentTimeMillis();
        r26 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r24 = r24 / r26;
        r18 = r18 + r24;
    L_0x01b2:
        r0 = r18;	 Catch:{ RuntimeException -> 0x01b9 }
        r8.a(r0, r7);	 Catch:{ RuntimeException -> 0x01b9 }
        goto L_0x000a;
    L_0x01b9:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Long-Integer header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x01cd:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Long-Integer header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x01e1:
        d(r28);
        r6 = f(r28);
        r24 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = r24;
        if (r0 != r6) goto L_0x022e;
    L_0x01ee:
        r5 = e(r28);
        if (r5 == 0) goto L_0x021e;
    L_0x01f4:
        r2 = r5.b();
        r17 = new java.lang.String;
        r0 = r17;
        r0.<init>(r2);
        r24 = "/";
        r0 = r17;
        r1 = r24;
        r4 = r0.indexOf(r1);
        if (r4 <= 0) goto L_0x0215;
    L_0x020b:
        r24 = 0;
        r0 = r17;
        r1 = r24;
        r17 = r0.substring(r1, r4);
    L_0x0215:
        r24 = r17.getBytes();	 Catch:{ NullPointerException -> 0x022a }
        r0 = r24;	 Catch:{ NullPointerException -> 0x022a }
        r5.a(r0);	 Catch:{ NullPointerException -> 0x022a }
    L_0x021e:
        r24 = 137; // 0x89 float:1.92E-43 double:6.77E-322;
        r0 = r24;	 Catch:{ NullPointerException -> 0x0227, RuntimeException -> 0x0250 }
        r8.a(r5, r0);	 Catch:{ NullPointerException -> 0x0227, RuntimeException -> 0x0250 }
        goto L_0x000a;
    L_0x0227:
        r24 = move-exception;
        goto L_0x000a;
    L_0x022a:
        r24 = move-exception;
        r8 = 0;
        goto L_0x0003;
    L_0x022e:
        r5 = new com.skype.smsmanager.mms.pdu.EncodedStringValue;	 Catch:{ NullPointerException -> 0x023c }
        r24 = "insert-address-token";	 Catch:{ NullPointerException -> 0x023c }
        r24 = r24.getBytes();	 Catch:{ NullPointerException -> 0x023c }
        r0 = r24;	 Catch:{ NullPointerException -> 0x023c }
        r5.<init>(r0);	 Catch:{ NullPointerException -> 0x023c }
        goto L_0x021e;
    L_0x023c:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Encoded-String-Value header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0250:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Encoded-String-Value header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0264:
        r24 = 1;
        r0 = r28;
        r1 = r24;
        r0.mark(r1);
        r11 = f(r28);
        r24 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = r24;
        if (r11 < r0) goto L_0x02ea;
    L_0x0277:
        r24 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = r24;
        if (r0 != r11) goto L_0x0291;
    L_0x027d:
        r24 = "personal";	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r24 = r24.getBytes();	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r25 = 138; // 0x8a float:1.93E-43 double:6.8E-322;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r0 = r24;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r1 = r25;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r8.a(r0, r1);	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        goto L_0x000a;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
    L_0x028e:
        r24 = move-exception;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        goto L_0x000a;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
    L_0x0291:
        r24 = 129; // 0x81 float:1.81E-43 double:6.37E-322;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r0 = r24;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        if (r0 != r11) goto L_0x02bc;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
    L_0x0297:
        r24 = "advertisement";	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r24 = r24.getBytes();	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r25 = 138; // 0x8a float:1.93E-43 double:6.8E-322;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r0 = r24;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r1 = r25;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r8.a(r0, r1);	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        goto L_0x000a;
    L_0x02a8:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Text-String header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x02bc:
        r24 = 130; // 0x82 float:1.82E-43 double:6.4E-322;
        r0 = r24;
        if (r0 != r11) goto L_0x02d3;
    L_0x02c2:
        r24 = "informational";	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r24 = r24.getBytes();	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r25 = 138; // 0x8a float:1.93E-43 double:6.8E-322;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r0 = r24;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r1 = r25;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r8.a(r0, r1);	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        goto L_0x000a;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
    L_0x02d3:
        r24 = 131; // 0x83 float:1.84E-43 double:6.47E-322;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r0 = r24;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        if (r0 != r11) goto L_0x000a;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
    L_0x02d9:
        r24 = "auto";	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r24 = r24.getBytes();	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r25 = 138; // 0x8a float:1.93E-43 double:6.8E-322;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r0 = r24;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r1 = r25;	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        r8.a(r0, r1);	 Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x02a8 }
        goto L_0x000a;
    L_0x02ea:
        r28.reset();
        r24 = 0;
        r0 = r28;
        r1 = r24;
        r12 = a(r0, r1);
        if (r12 == 0) goto L_0x000a;
    L_0x02f9:
        r24 = 138; // 0x8a float:1.93E-43 double:6.8E-322;
        r0 = r24;	 Catch:{ NullPointerException -> 0x0302, RuntimeException -> 0x0305 }
        r8.a(r12, r0);	 Catch:{ NullPointerException -> 0x0302, RuntimeException -> 0x0305 }
        goto L_0x000a;
    L_0x0302:
        r24 = move-exception;
        goto L_0x000a;
    L_0x0305:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Text-String header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0319:
        r21 = g(r28);
        r24 = 141; // 0x8d float:1.98E-43 double:6.97E-322;
        r0 = r21;	 Catch:{ InvalidHeaderValueException -> 0x0328, RuntimeException -> 0x0346 }
        r1 = r24;	 Catch:{ InvalidHeaderValueException -> 0x0328, RuntimeException -> 0x0346 }
        r8.a(r0, r1);	 Catch:{ InvalidHeaderValueException -> 0x0328, RuntimeException -> 0x0346 }
        goto L_0x000a;
    L_0x0328:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r25 = "Set invalid Octet value: ";
        r24.<init>(r25);
        r0 = r24;
        r1 = r21;
        r24 = r0.append(r1);
        r25 = " into the header filed: ";
        r24 = r24.append(r25);
        r0 = r24;
        r0.append(r7);
        r8 = 0;
        goto L_0x0003;
    L_0x0346:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Octet header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x035a:
        d(r28);
        i(r28);	 Catch:{ RuntimeException -> 0x0374 }
        r16 = e(r28);
        if (r16 == 0) goto L_0x000a;
    L_0x0366:
        r24 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r0 = r16;	 Catch:{ NullPointerException -> 0x0371, RuntimeException -> 0x0388 }
        r1 = r24;	 Catch:{ NullPointerException -> 0x0371, RuntimeException -> 0x0388 }
        r8.a(r0, r1);	 Catch:{ NullPointerException -> 0x0371, RuntimeException -> 0x0388 }
        goto L_0x000a;
    L_0x0371:
        r24 = move-exception;
        goto L_0x000a;
    L_0x0374:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = " is not Integer-Value";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0388:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Encoded-String-Value header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x039c:
        d(r28);
        i(r28);	 Catch:{ RuntimeException -> 0x03c3 }
        r14 = h(r28);	 Catch:{ RuntimeException -> 0x03af }
        r24 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;	 Catch:{ RuntimeException -> 0x03af }
        r0 = r24;	 Catch:{ RuntimeException -> 0x03af }
        r8.a(r14, r0);	 Catch:{ RuntimeException -> 0x03af }
        goto L_0x000a;
    L_0x03af:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Long-Integer header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x03c3:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = " is not Integer-Value";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x03d7:
        d(r28);
        f(r28);
        e(r28);
        goto L_0x000a;
    L_0x03e2:
        d(r28);
        f(r28);
        i(r28);	 Catch:{ RuntimeException -> 0x03ed }
        goto L_0x000a;
    L_0x03ed:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = " is not Integer-Value";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0401:
        r24 = 0;
        r0 = r28;
        r1 = r24;
        a(r0, r1);
        goto L_0x000a;
    L_0x040c:
        r10 = new java.util.HashMap;
        r10.<init>();
        r0 = r28;
        r3 = a(r0, r10);
        if (r3 == 0) goto L_0x0420;
    L_0x0419:
        r24 = 132; // 0x84 float:1.85E-43 double:6.5E-322;
        r0 = r24;	 Catch:{ NullPointerException -> 0x0457, RuntimeException -> 0x0443 }
        r8.a(r3, r0);	 Catch:{ NullPointerException -> 0x0457, RuntimeException -> 0x0443 }
    L_0x0420:
        r24 = 153; // 0x99 float:2.14E-43 double:7.56E-322;
        r24 = java.lang.Integer.valueOf(r24);
        r0 = r24;
        r24 = r10.get(r0);
        r24 = (byte[]) r24;
        f = r24;
        r24 = 131; // 0x83 float:1.84E-43 double:6.47E-322;
        r24 = java.lang.Integer.valueOf(r24);
        r0 = r24;
        r24 = r10.get(r0);
        r24 = (byte[]) r24;
        e = r24;
        r9 = 0;
        goto L_0x000a;
    L_0x0443:
        r24 = move-exception;
        r24 = new java.lang.StringBuilder;
        r24.<init>();
        r0 = r24;
        r24 = r0.append(r7);
        r25 = "is not Text-String header field!";
        r24.append(r25);
        r8 = 0;
        goto L_0x0003;
    L_0x0457:
        r24 = move-exception;
        goto L_0x0420;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.skype.smsmanager.mms.pdu.PduParser.a(java.io.ByteArrayInputStream):com.skype.smsmanager.mms.pdu.PduHeaders");
    }

    private PduBody b(ByteArrayInputStream pduDataStream) {
        if (pduDataStream == null) {
            return null;
        }
        int count = c(pduDataStream);
        PduBody body = new PduBody();
        int i = 0;
        while (i < count) {
            int headerLength = c(pduDataStream);
            int dataLength = c(pduDataStream);
            PduPart part = new PduPart();
            int startPos = pduDataStream.available();
            if (startPos <= 0) {
                return null;
            }
            HashMap map = new HashMap();
            byte[] contentType = a(pduDataStream, map);
            if (contentType != null) {
                part.e(contentType);
            } else {
                part.e(PduContentTypes.a[0].getBytes());
            }
            byte[] name = (byte[]) map.get(Integer.valueOf(151));
            if (name != null) {
                part.g(name);
            }
            Integer charset = (Integer) map.get(Integer.valueOf(129));
            if (charset != null) {
                part.a(charset.intValue());
            }
            int partHeaderLen = headerLength - (startPos - pduDataStream.available());
            if (partHeaderLen > 0) {
                if (!a(pduDataStream, part, partHeaderLen)) {
                    return null;
                }
            } else if (partHeaderLen < 0) {
                return null;
            }
            if (part.e() == null && part.h() == null && part.i() == null && part.c() == null) {
                part.c(Long.toOctalString(System.currentTimeMillis()).getBytes());
            }
            if (dataLength > 0) {
                byte[] partData = new byte[dataLength];
                String str = new String(part.f());
                pduDataStream.read(partData, 0, dataLength);
                if (str.equalsIgnoreCase("application/vnd.wap.multipart.alternative")) {
                    part = b(new ByteArrayInputStream(partData)).a(0);
                } else {
                    byte[] partDataEncoding = part.g();
                    if (partDataEncoding != null) {
                        String encoding = new String(partDataEncoding);
                        if (encoding.equalsIgnoreCase("base64")) {
                            partData = Base64.a(partData);
                        } else if (encoding.equalsIgnoreCase("quoted-printable")) {
                            partData = QuotedPrintable.a(partData);
                        }
                    }
                    if (partData == null) {
                        return null;
                    }
                    part.a(partData);
                }
            }
            if (a || part != null) {
                Object obj;
                if (!(e == null && f == null)) {
                    byte[] c;
                    if (f != null) {
                        c = part.c();
                        if (c != null && true == Arrays.equals(f, c)) {
                            obj = null;
                            if (obj == null) {
                                body.b(part);
                            } else {
                                body.a(part);
                            }
                            i++;
                        }
                    }
                    if (e != null) {
                        c = part.f();
                        if (c != null && true == Arrays.equals(e, c)) {
                            obj = null;
                            if (obj == null) {
                                body.a(part);
                            } else {
                                body.b(part);
                            }
                            i++;
                        }
                    }
                }
                obj = 1;
                if (obj == null) {
                    body.b(part);
                } else {
                    body.a(part);
                }
                i++;
            } else {
                throw new AssertionError();
            }
        }
        return body;
    }

    private static int c(ByteArrayInputStream pduDataStream) {
        if (a || pduDataStream != null) {
            int result = 0;
            int temp = pduDataStream.read();
            if (temp == -1) {
                return temp;
            }
            while ((temp & 128) != 0) {
                result = (result << 7) | (temp & 127);
                temp = pduDataStream.read();
                if (temp == -1) {
                    return temp;
                }
            }
            return (result << 7) | (temp & 127);
        }
        throw new AssertionError();
    }

    private static int d(ByteArrayInputStream pduDataStream) {
        if (a || pduDataStream != null) {
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                int first = temp & 255;
                if (first <= 30) {
                    return first;
                }
                if (first == 31) {
                    return c(pduDataStream);
                }
                throw new RuntimeException("Value length > LENGTH_QUOTE!");
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static EncodedStringValue e(ByteArrayInputStream pduDataStream) {
        if (a || pduDataStream != null) {
            pduDataStream.mark(1);
            int charset = 0;
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                int first = temp & 255;
                if (first == 0) {
                    return new EncodedStringValue("");
                }
                pduDataStream.reset();
                if (first < 32) {
                    d(pduDataStream);
                    charset = g(pduDataStream);
                }
                byte[] textString = a(pduDataStream, 0);
                if (charset == 0) {
                    return new EncodedStringValue(textString);
                }
                try {
                    return new EncodedStringValue(charset, textString);
                } catch (Exception e) {
                    return null;
                }
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static byte[] a(ByteArrayInputStream pduDataStream, int stringType) {
        if (a || pduDataStream != null) {
            pduDataStream.mark(1);
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                if (1 == stringType && 34 == temp) {
                    pduDataStream.mark(1);
                } else if (stringType == 0 && 127 == temp) {
                    pduDataStream.mark(1);
                } else {
                    pduDataStream.reset();
                }
                return b(pduDataStream, stringType);
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static byte[] b(ByteArrayInputStream pduDataStream, int stringType) {
        if (a || pduDataStream != null) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                while (-1 != temp && temp != 0) {
                    Object obj;
                    int obj2;
                    if (stringType == 2) {
                        if (temp >= 33 && temp <= 126) {
                            switch (temp) {
                                case 34:
                                case 40:
                                case 41:
                                case 44:
                                case 47:
                                case 58:
                                case 59:
                                case 60:
                                case 61:
                                case 62:
                                case 63:
                                case 64:
                                case 91:
                                case 92:
                                case 93:
                                case 123:
                                case 125:
                                    obj2 = null;
                                    break;
                                default:
                                    obj2 = 1;
                                    break;
                            }
                        }
                        obj2 = null;
                        if (obj2 != null) {
                            out.write(temp);
                        }
                    } else {
                        if ((temp < 32 || temp > 126) && (temp < 128 || temp > 255)) {
                            switch (temp) {
                                case 9:
                                case 10:
                                case 13:
                                    obj2 = 1;
                                    break;
                                default:
                                    obj2 = null;
                                    break;
                            }
                        }
                        obj2 = 1;
                        if (obj2 != null) {
                            out.write(temp);
                        }
                    }
                    temp = pduDataStream.read();
                    if (!a && -1 == temp) {
                        throw new AssertionError();
                    }
                }
                if (out.size() > 0) {
                    return out.toByteArray();
                }
                return null;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static int f(ByteArrayInputStream pduDataStream) {
        if (a || pduDataStream != null) {
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                return temp & 255;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static int g(ByteArrayInputStream pduDataStream) {
        if (a || pduDataStream != null) {
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                return temp & 127;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static long h(ByteArrayInputStream pduDataStream) {
        if (a || pduDataStream != null) {
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                int count = temp & 255;
                if (count > 8) {
                    throw new RuntimeException("Octet count greater than 8 and I can't represent that!");
                }
                long result = 0;
                int i = 0;
                while (i < count) {
                    temp = pduDataStream.read();
                    if (a || -1 != temp) {
                        result = (result << 8) + ((long) (temp & 255));
                        i++;
                    } else {
                        throw new AssertionError();
                    }
                }
                return result;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static long i(ByteArrayInputStream pduDataStream) {
        if (a || pduDataStream != null) {
            pduDataStream.mark(1);
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                pduDataStream.reset();
                if (temp > 127) {
                    return (long) g(pduDataStream);
                }
                return h(pduDataStream);
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static int c(ByteArrayInputStream pduDataStream, int length) {
        if (a || pduDataStream != null) {
            int readLen = pduDataStream.read(new byte[length], 0, length);
            if (readLen < length) {
                return -1;
            }
            return readLen;
        }
        throw new AssertionError();
    }

    private static void a(ByteArrayInputStream pduDataStream, HashMap<Integer, Object> map, Integer length) {
        if (!a && pduDataStream == null) {
            throw new AssertionError();
        } else if (a || length.intValue() > 0) {
            int startPos = pduDataStream.available();
            int lastLen = length.intValue();
            while (lastLen > 0) {
                int param = pduDataStream.read();
                if (a || -1 != param) {
                    lastLen--;
                    HashMap<Integer, Object> hashMap;
                    switch (param) {
                        case 129:
                            pduDataStream.mark(1);
                            int firstValue = f(pduDataStream);
                            pduDataStream.reset();
                            if ((firstValue <= 32 || firstValue >= 127) && firstValue != 0) {
                                int charset = (int) i(pduDataStream);
                                if (map != null) {
                                    map.put(Integer.valueOf(129), Integer.valueOf(charset));
                                }
                            } else {
                                byte[] charsetStr = a(pduDataStream, 0);
                                try {
                                    hashMap = map;
                                    hashMap.put(Integer.valueOf(129), Integer.valueOf(CharacterSets.a(new String(charsetStr))));
                                } catch (UnsupportedEncodingException e) {
                                    Arrays.toString(charsetStr);
                                    map.put(Integer.valueOf(129), Integer.valueOf(0));
                                }
                            }
                            lastLen = length.intValue() - (startPos - pduDataStream.available());
                            break;
                        case 131:
                        case 137:
                            pduDataStream.mark(1);
                            int first = f(pduDataStream);
                            pduDataStream.reset();
                            if (first > 127) {
                                int index = g(pduDataStream);
                                if (index < PduContentTypes.a.length) {
                                    hashMap = map;
                                    hashMap.put(Integer.valueOf(131), PduContentTypes.a[index].getBytes());
                                }
                            } else {
                                byte[] type = a(pduDataStream, 0);
                                if (!(type == null || map == null)) {
                                    map.put(Integer.valueOf(131), type);
                                }
                            }
                            lastLen = length.intValue() - (startPos - pduDataStream.available());
                            break;
                        case 133:
                        case 151:
                            byte[] name = a(pduDataStream, 0);
                            if (!(name == null || map == null)) {
                                map.put(Integer.valueOf(151), name);
                            }
                            lastLen = length.intValue() - (startPos - pduDataStream.available());
                            break;
                        case 138:
                        case 153:
                            byte[] start = a(pduDataStream, 0);
                            if (!(start == null || map == null)) {
                                map.put(Integer.valueOf(153), start);
                            }
                            lastLen = length.intValue() - (startPos - pduDataStream.available());
                            break;
                        default:
                            if (-1 == c(pduDataStream, lastLen)) {
                                break;
                            }
                            lastLen = 0;
                            break;
                    }
                }
                throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
    }

    private static byte[] a(ByteArrayInputStream pduDataStream, HashMap<Integer, Object> map) {
        if (a || pduDataStream != null) {
            pduDataStream.mark(1);
            int temp = pduDataStream.read();
            if (a || -1 != temp) {
                pduDataStream.reset();
                int cur = temp & 255;
                if (cur < 32) {
                    int length = d(pduDataStream);
                    int startPos = pduDataStream.available();
                    pduDataStream.mark(1);
                    temp = pduDataStream.read();
                    if (a || -1 != temp) {
                        byte[] contentType;
                        pduDataStream.reset();
                        int first = temp & 255;
                        if (first >= 32 && first <= 127) {
                            contentType = a(pduDataStream, 0);
                        } else if (first <= 127) {
                            return PduContentTypes.a[0].getBytes();
                        } else {
                            int index = g(pduDataStream);
                            if (index < PduContentTypes.a.length) {
                                contentType = PduContentTypes.a[index].getBytes();
                            } else {
                                pduDataStream.reset();
                                contentType = a(pduDataStream, 0);
                            }
                        }
                        int parameterLen = length - (startPos - pduDataStream.available());
                        if (parameterLen > 0) {
                            a(pduDataStream, (HashMap) map, Integer.valueOf(parameterLen));
                        }
                        if (parameterLen < 0) {
                            return PduContentTypes.a[0].getBytes();
                        }
                        return contentType;
                    }
                    throw new AssertionError();
                } else if (cur <= 127) {
                    return a(pduDataStream, 0);
                } else {
                    return PduContentTypes.a[g(pduDataStream)].getBytes();
                }
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private boolean a(ByteArrayInputStream pduDataStream, PduPart part, int length) {
        if (!a && pduDataStream == null) {
            throw new AssertionError();
        } else if (a || length > 0) {
            int startPos = pduDataStream.available();
            int lastLen = length;
            while (lastLen > 0) {
                int header = pduDataStream.read();
                if (a || -1 != header) {
                    lastLen--;
                    if (header > 127) {
                        switch (header) {
                            case 142:
                                byte[] contentLocation = a(pduDataStream, 0);
                                if (contentLocation != null) {
                                    part.c(contentLocation);
                                }
                                lastLen = length - (startPos - pduDataStream.available());
                                break;
                            case 174:
                            case 197:
                                if (!this.g) {
                                    break;
                                }
                                int len = d(pduDataStream);
                                pduDataStream.mark(1);
                                int thisStartPos = pduDataStream.available();
                                int value = pduDataStream.read();
                                if (value == 128) {
                                    part.d(PduPart.a);
                                } else if (value == 129) {
                                    part.d(PduPart.b);
                                } else if (value == 130) {
                                    part.d(PduPart.c);
                                } else {
                                    pduDataStream.reset();
                                    part.d(a(pduDataStream, 0));
                                }
                                if (thisStartPos - pduDataStream.available() < len) {
                                    if (pduDataStream.read() == 152) {
                                        part.h(a(pduDataStream, 0));
                                    }
                                    int thisEndPos = pduDataStream.available();
                                    if (thisStartPos - thisEndPos < len) {
                                        int last = len - (thisStartPos - thisEndPos);
                                        pduDataStream.read(new byte[last], 0, last);
                                    }
                                }
                                lastLen = length - (startPos - pduDataStream.available());
                                break;
                            case 192:
                                byte[] contentId = a(pduDataStream, 1);
                                if (contentId != null) {
                                    part.b(contentId);
                                }
                                lastLen = length - (startPos - pduDataStream.available());
                                break;
                            default:
                                if (-1 != c(pduDataStream, lastLen)) {
                                    lastLen = 0;
                                    break;
                                }
                                return false;
                        }
                    } else if (header >= 32 && header <= 127) {
                        byte[] tempHeader = a(pduDataStream, 0);
                        byte[] tempValue = a(pduDataStream, 0);
                        if (true == "Content-Transfer-Encoding".equalsIgnoreCase(new String(tempHeader))) {
                            part.f(tempValue);
                        }
                        lastLen = length - (startPos - pduDataStream.available());
                    } else if (-1 == c(pduDataStream, lastLen)) {
                        return false;
                    } else {
                        lastLen = 0;
                    }
                } else {
                    throw new AssertionError();
                }
            }
            if (lastLen != 0) {
                return false;
            }
            return true;
        } else {
            throw new AssertionError();
        }
    }
}
