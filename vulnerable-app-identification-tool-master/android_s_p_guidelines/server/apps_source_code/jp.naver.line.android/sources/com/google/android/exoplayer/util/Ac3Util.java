package com.google.android.exoplayer.util;

import com.google.android.exoplayer.MediaFormat;
import com.linecorp.videoplayer.exo.ExoVideoPlayer;
import com.linecorp.yuki.sensetime.model.SegmentationData;
import org.apache.http.HttpStatus;
import org.bouncycastle.crypto.tls.CipherSuite;

public final class Ac3Util {
    private static final int[] BITRATES = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, SegmentationData.MAX_SEGMENTATION_WIDTH, 192, 224, 256, 320, 384, 448, 512, 576, 640};
    private static final int[] CHANNEL_COUNTS = new int[]{2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] FRMSIZECOD_TO_FRAME_SIZE_44_1 = new int[]{69, 87, 104, 121, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA, 174, 208, 243, 278, 348, HttpStatus.SC_EXPECTATION_FAILED, 487, 557, 696, 835, 975, 1114, 1253, 1393};
    private static final int[] SAMPLE_RATES = new int[]{48000, 44100, 32000};

    public static MediaFormat parseAnnexFAc3Format(ParsableByteArray parsableByteArray) {
        int i = SAMPLE_RATES[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNTS[(readUnsignedByte & 56) >> 3];
        if ((readUnsignedByte & 4) != 0) {
            i2++;
        }
        return MediaFormat.createAudioFormat(MimeTypes.AUDIO_AC3, -1, i2, i, null);
    }

    public static MediaFormat parseAnnexFEAc3Format(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(2);
        int i = SAMPLE_RATES[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNTS[(readUnsignedByte & 14) >> 1];
        if ((readUnsignedByte & 1) != 0) {
            i2++;
        }
        return MediaFormat.createAudioFormat(MimeTypes.AUDIO_EC3, -1, i2, i, null);
    }

    public static MediaFormat parseFrameAc3Format(ParsableBitArray parsableBitArray) {
        parsableBitArray.skipBits(32);
        int readBits = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(14);
        int readBits2 = parsableBitArray.readBits(3);
        if (!((readBits2 & 1) == 0 || readBits2 == 1)) {
            parsableBitArray.skipBits(2);
        }
        if ((readBits2 & 4) != 0) {
            parsableBitArray.skipBits(2);
        }
        if (readBits2 == 2) {
            parsableBitArray.skipBits(2);
        }
        return MediaFormat.createAudioFormat(MimeTypes.AUDIO_AC3, -1, CHANNEL_COUNTS[readBits2] + parsableBitArray.readBit(), SAMPLE_RATES[readBits], null);
    }

    public static int parseFrameSize(ParsableBitArray parsableBitArray) {
        parsableBitArray.skipBits(32);
        int readBits = parsableBitArray.readBits(2);
        int readBits2 = parsableBitArray.readBits(6);
        readBits = SAMPLE_RATES[readBits];
        int i = readBits2 / 2;
        int i2 = BITRATES[i];
        if (readBits == 32000) {
            return i2 * 6;
        }
        return readBits == 44100 ? (FRMSIZECOD_TO_FRAME_SIZE_44_1[i] + (readBits2 % 2)) * 2 : i2 * 4;
    }

    public static int getBitrate(int i, int i2) {
        return (((i * 8) * i2) + 768000) / ExoVideoPlayer.MAX_INITIAL_BITRATE;
    }

    private Ac3Util() {
    }
}
