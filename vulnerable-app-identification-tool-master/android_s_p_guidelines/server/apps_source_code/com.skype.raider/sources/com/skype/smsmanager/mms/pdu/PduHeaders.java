package com.skype.smsmanager.mms.pdu;

import com.skype.smsmanager.mms.InvalidHeaderValueException;
import java.util.ArrayList;
import java.util.HashMap;

public class PduHeaders {
    private HashMap<Integer, Object> a;

    public PduHeaders() {
        this.a = null;
        this.a = new HashMap();
    }

    protected final int a(int field) {
        Integer octet = (Integer) this.a.get(Integer.valueOf(field));
        if (octet == null) {
            return 0;
        }
        return octet.intValue();
    }

    protected final void a(int value, int field) throws InvalidHeaderValueException {
        switch (field) {
            case 134:
            case 144:
            case 145:
            case 148:
            case 162:
            case 167:
            case 169:
            case 171:
            case 177:
            case 187:
            case 188:
                if (!(128 == value || 129 == value)) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            case 140:
                if (value < 128 || value > 151) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            case 141:
                if (value < 16 || value > 19) {
                    value = 18;
                    break;
                }
            case 143:
                if (value < 128 || value > 130) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            case 146:
                if (value <= 196 || value >= 224) {
                    if ((value > 235 && value <= 255) || value < 128 || ((value > 136 && value < 192) || value > 255)) {
                        value = 224;
                        break;
                    }
                }
                value = 192;
                break;
                break;
            case 149:
                if (value < 128 || value > 135) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            case 153:
                if (value <= 194 || value >= 224) {
                    if (value <= 227 || value > 255) {
                        if (value < 128 || ((value > 128 && value < 192) || value > 255)) {
                            value = 224;
                            break;
                        }
                    }
                    value = 224;
                    break;
                }
                value = 192;
                break;
                break;
            case 155:
                if (!(128 == value || 129 == value)) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            case 156:
                if (value < 128 || value > 131) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            case 163:
                if (value < 128 || value > 132) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            case 165:
                if (value <= 193 || value >= 224) {
                    if (value <= 228 || value > 255) {
                        if (value < 128 || ((value > 128 && value < 192) || value > 255)) {
                            value = 224;
                            break;
                        }
                    }
                    value = 224;
                    break;
                }
                value = 192;
                break;
                break;
            case 180:
                if (128 != value) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
                break;
            case 186:
                if (value < 128 || value > 135) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            case 191:
                if (!(128 == value || 129 == value)) {
                    throw new InvalidHeaderValueException("Invalid Octet value!");
                }
            default:
                throw new RuntimeException("Invalid header field!");
        }
        this.a.put(Integer.valueOf(field), Integer.valueOf(value));
    }

    protected final byte[] b(int field) {
        return (byte[]) this.a.get(Integer.valueOf(field));
    }

    protected final void a(byte[] value, int field) {
        if (value == null) {
            throw new NullPointerException();
        }
        switch (field) {
            case 131:
            case 132:
            case 138:
            case 139:
            case 152:
            case 158:
            case 183:
            case 184:
            case 185:
            case 189:
            case 190:
                this.a.put(Integer.valueOf(field), value);
                return;
            default:
                throw new RuntimeException("Invalid header field!");
        }
    }

    protected final EncodedStringValue c(int field) {
        return (EncodedStringValue) this.a.get(Integer.valueOf(field));
    }

    protected final EncodedStringValue[] d(int field) {
        ArrayList<EncodedStringValue> list = (ArrayList) this.a.get(Integer.valueOf(field));
        if (list == null) {
            return null;
        }
        return (EncodedStringValue[]) list.toArray(new EncodedStringValue[list.size()]);
    }

    protected final void a(EncodedStringValue value, int field) {
        if (value == null) {
            throw new NullPointerException();
        }
        switch (field) {
            case 137:
            case 147:
            case 150:
            case 154:
            case 160:
            case 164:
            case 166:
            case 181:
            case 182:
                this.a.put(Integer.valueOf(field), value);
                return;
            default:
                throw new RuntimeException("Invalid header field!");
        }
    }

    protected final void a(EncodedStringValue[] value) {
        if (value == null) {
            throw new NullPointerException();
        }
        ArrayList<EncodedStringValue> list = new ArrayList();
        for (Object add : value) {
            list.add(add);
        }
        this.a.put(Integer.valueOf(151), list);
    }

    protected final void b(EncodedStringValue value, int field) {
        if (value == null) {
            throw new NullPointerException();
        }
        switch (field) {
            case 129:
            case 130:
            case 151:
                ArrayList<EncodedStringValue> list = (ArrayList) this.a.get(Integer.valueOf(field));
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(value);
                this.a.put(Integer.valueOf(field), list);
                return;
            default:
                throw new RuntimeException("Invalid header field!");
        }
    }

    protected final long e(int field) {
        Long longInteger = (Long) this.a.get(Integer.valueOf(field));
        if (longInteger == null) {
            return -1;
        }
        return longInteger.longValue();
    }

    protected final void a(long value, int field) {
        switch (field) {
            case 133:
            case 135:
            case 136:
            case 142:
            case 157:
            case 159:
            case 161:
            case 173:
            case 175:
            case 179:
                this.a.put(Integer.valueOf(field), Long.valueOf(value));
                return;
            default:
                throw new RuntimeException("Invalid header field!");
        }
    }
}
