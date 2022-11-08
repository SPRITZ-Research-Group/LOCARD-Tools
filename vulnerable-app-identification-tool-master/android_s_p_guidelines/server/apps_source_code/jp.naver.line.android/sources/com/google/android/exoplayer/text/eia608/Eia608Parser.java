package com.google.android.exoplayer.text.eia608;

import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.linecorp.yuki.sensetime.model.FaceData;
import java.util.ArrayList;
import org.apache.http.HttpStatus;

public final class Eia608Parser {
    private static final int[] BASIC_CHARACTER_SET = new int[]{32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
    private static final int COUNTRY_CODE = 181;
    private static final int PAYLOAD_TYPE_CC = 4;
    private static final int PROVIDER_CODE = 49;
    private static final int[] SPECIAL_CHARACTER_SET = new int[]{174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = new int[]{193, HttpStatus.SC_CREATED, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, HttpStatus.SC_ACCEPTED, HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, 235, HttpStatus.SC_PARTIAL_CONTENT, HttpStatus.SC_MULTI_STATUS, 239, FaceData.SENSETIME_SHAPE_SIZE_2D, 217, 249, 219, 171, 187};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = new int[]{195, 227, HttpStatus.SC_RESET_CONTENT, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private static final int USER_DATA_TYPE_CODE = 3;
    private static final int USER_ID = 1195456820;
    private final ArrayList<ClosedCaption> captions = new ArrayList();
    private final ParsableBitArray seiBuffer = new ParsableBitArray();
    private final StringBuilder stringBuilder = new StringBuilder();

    Eia608Parser() {
    }

    final boolean canParse(String str) {
        return str.equals(MimeTypes.APPLICATION_EIA608);
    }

    final ClosedCaptionList parse(SampleHolder sampleHolder) {
        if (sampleHolder.size < 10) {
            return null;
        }
        this.captions.clear();
        int i = 0;
        this.stringBuilder.setLength(0);
        this.seiBuffer.reset(sampleHolder.data.array());
        this.seiBuffer.skipBits(67);
        int readBits = this.seiBuffer.readBits(5);
        this.seiBuffer.skipBits(8);
        while (i < readBits) {
            this.seiBuffer.skipBits(5);
            if (!this.seiBuffer.readBit()) {
                this.seiBuffer.skipBits(18);
            } else if (this.seiBuffer.readBits(2) != 0) {
                this.seiBuffer.skipBits(16);
            } else {
                this.seiBuffer.skipBits(1);
                byte readBits2 = (byte) this.seiBuffer.readBits(7);
                this.seiBuffer.skipBits(1);
                byte readBits3 = (byte) this.seiBuffer.readBits(7);
                if (readBits2 != (byte) 0 || readBits3 != (byte) 0) {
                    if ((readBits2 == ClosedCaptionCtrl.MID_ROW_CHAN_1 || readBits2 == ClosedCaptionCtrl.MID_ROW_CHAN_2) && (readBits3 & 112) == 48) {
                        this.stringBuilder.append(getSpecialChar(readBits3));
                    } else if ((readBits2 == (byte) 18 || readBits2 == (byte) 26) && (readBits3 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedEsFrChar(readBits3));
                    } else if ((readBits2 == (byte) 19 || readBits2 == (byte) 27) && (readBits3 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedPtDeChar(readBits3));
                    } else if (readBits2 < ClosedCaptionCtrl.RESUME_CAPTION_LOADING) {
                        addCtrl(readBits2, readBits3);
                    } else {
                        this.stringBuilder.append(getChar(readBits2));
                        if (readBits3 >= ClosedCaptionCtrl.RESUME_CAPTION_LOADING) {
                            this.stringBuilder.append(getChar(readBits3));
                        }
                    }
                }
            }
            i++;
        }
        addBufferedText();
        if (this.captions.isEmpty()) {
            return null;
        }
        ClosedCaption[] closedCaptionArr = new ClosedCaption[this.captions.size()];
        this.captions.toArray(closedCaptionArr);
        return new ClosedCaptionList(sampleHolder.timeUs, sampleHolder.isDecodeOnly(), closedCaptionArr);
    }

    private static char getChar(byte b) {
        return (char) BASIC_CHARACTER_SET[(b & 127) - 32];
    }

    private static char getSpecialChar(byte b) {
        return (char) SPECIAL_CHARACTER_SET[b & 15];
    }

    private static char getExtendedEsFrChar(byte b) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b & 31];
    }

    private static char getExtendedPtDeChar(byte b) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b & 31];
    }

    private void addBufferedText() {
        if (this.stringBuilder.length() > 0) {
            this.captions.add(new ClosedCaptionText(this.stringBuilder.toString()));
            this.stringBuilder.setLength(0);
        }
    }

    private void addCtrl(byte b, byte b2) {
        addBufferedText();
        this.captions.add(new ClosedCaptionCtrl(b, b2));
    }

    private void backspace() {
        addCtrl(ClosedCaptionCtrl.MISC_CHAN_1, ClosedCaptionCtrl.BACKSPACE);
    }

    public static boolean isSeiMessageEia608(int i, int i2, ParsableByteArray parsableByteArray) {
        if (i != 4 || i2 < 8) {
            return false;
        }
        i = parsableByteArray.getPosition();
        i2 = parsableByteArray.readUnsignedByte();
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readInt = parsableByteArray.readInt();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(i);
        if (i2 == COUNTRY_CODE && readUnsignedShort == 49 && readInt == USER_ID && readUnsignedByte == 3) {
            return true;
        }
        return false;
    }
}
