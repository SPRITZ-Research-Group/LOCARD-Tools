package com.google.a.a.a;

import com.google.a.b.a;
import com.google.a.b.b;

public final class c {
    private static final int[] a = new int[]{4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    public static a a(byte[] data, int minECCPercent, int userSpecifiedLayers) {
        boolean compact;
        int layers;
        int totalBitsInLayer;
        int wordSize;
        a stuffedBits;
        int i;
        a modeMessage;
        int matrixSize;
        int j;
        int k;
        a bits = new d(data).a();
        int eccBits = ((bits.a() * minECCPercent) / 100) + 11;
        int totalSizeBits = bits.a() + eccBits;
        int usableBitsInLayers;
        if (userSpecifiedLayers != 0) {
            compact = userSpecifiedLayers < 0;
            layers = Math.abs(userSpecifiedLayers);
            if (layers > (compact ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[]{Integer.valueOf(userSpecifiedLayers)}));
            }
            totalBitsInLayer = a(layers, compact);
            wordSize = a[layers];
            usableBitsInLayers = totalBitsInLayer - (totalBitsInLayer % wordSize);
            stuffedBits = a(bits, wordSize);
            if (stuffedBits.a() + eccBits > usableBitsInLayers) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            } else if (compact && stuffedBits.a() > (wordSize << 6)) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        }
        wordSize = 0;
        stuffedBits = null;
        i = 0;
        while (i <= 32) {
            compact = i <= 3;
            if (compact) {
                layers = i + 1;
            } else {
                layers = i;
            }
            totalBitsInLayer = a(layers, compact);
            if (totalSizeBits <= totalBitsInLayer) {
                if (wordSize != a[layers]) {
                    wordSize = a[layers];
                    stuffedBits = a(bits, wordSize);
                }
                usableBitsInLayers = totalBitsInLayer - (totalBitsInLayer % wordSize);
                if ((!compact || stuffedBits.a() <= (wordSize << 6)) && stuffedBits.a() + eccBits <= usableBitsInLayers) {
                }
            }
            i++;
        }
        throw new IllegalArgumentException("Data too large for an Aztec code");
        a messageBits = a(stuffedBits, totalBitsInLayer, wordSize);
        int messageSizeInWords = stuffedBits.a() / wordSize;
        a aVar = new a();
        if (compact) {
            aVar.a(layers - 1, 2);
            aVar.a(messageSizeInWords - 1, 6);
            modeMessage = a(aVar, 28, 4);
        } else {
            aVar.a(layers - 1, 5);
            aVar.a(messageSizeInWords - 1, 11);
            modeMessage = a(aVar, 40, 4);
        }
        int baseMatrixSize = (compact ? 11 : 14) + (layers << 2);
        int[] alignmentMap = new int[baseMatrixSize];
        if (compact) {
            matrixSize = baseMatrixSize;
            for (i = 0; i < alignmentMap.length; i++) {
                alignmentMap[i] = i;
            }
        } else {
            matrixSize = (baseMatrixSize + 1) + ((((baseMatrixSize / 2) - 1) / 15) * 2);
            int origCenter = baseMatrixSize / 2;
            int center = matrixSize / 2;
            for (i = 0; i < origCenter; i++) {
                int newOffset = i + (i / 15);
                alignmentMap[(origCenter - i) - 1] = (center - newOffset) - 1;
                alignmentMap[origCenter + i] = (center + newOffset) + 1;
            }
        }
        b matrix = new b(matrixSize);
        int rowOffset = 0;
        for (i = 0; i < layers; i++) {
            int rowSize = ((layers - i) << 2) + (compact ? 9 : 12);
            for (j = 0; j < rowSize; j++) {
                int columnOffset = j << 1;
                for (k = 0; k < 2; k++) {
                    if (messageBits.a((rowOffset + columnOffset) + k)) {
                        matrix.b(alignmentMap[(i << 1) + k], alignmentMap[(i << 1) + j]);
                    }
                    if (messageBits.a((((rowSize << 1) + rowOffset) + columnOffset) + k)) {
                        matrix.b(alignmentMap[(i << 1) + j], alignmentMap[((baseMatrixSize - 1) - (i << 1)) - k]);
                    }
                    if (messageBits.a((((rowSize << 2) + rowOffset) + columnOffset) + k)) {
                        matrix.b(alignmentMap[((baseMatrixSize - 1) - (i << 1)) - k], alignmentMap[((baseMatrixSize - 1) - (i << 1)) - j]);
                    }
                    if (messageBits.a((((rowSize * 6) + rowOffset) + columnOffset) + k)) {
                        matrix.b(alignmentMap[((baseMatrixSize - 1) - (i << 1)) - j], alignmentMap[(i << 1) + k]);
                    }
                }
            }
            rowOffset += rowSize << 3;
        }
        a(matrix, compact, matrixSize, modeMessage);
        if (compact) {
            a(matrix, matrixSize / 2, 5);
        } else {
            a(matrix, matrixSize / 2, 7);
            i = 0;
            j = 0;
            while (i < (baseMatrixSize / 2) - 1) {
                for (k = (matrixSize / 2) & 1; k < matrixSize; k += 2) {
                    matrix.b((matrixSize / 2) - j, k);
                    matrix.b((matrixSize / 2) + j, k);
                    matrix.b(k, (matrixSize / 2) - j);
                    matrix.b(k, (matrixSize / 2) + j);
                }
                i += 15;
                j += 16;
            }
        }
        a aztec = new a();
        aztec.a(compact);
        aztec.a(matrixSize);
        aztec.b(layers);
        aztec.c(messageSizeInWords);
        aztec.a(matrix);
        return aztec;
    }

    private static void a(b matrix, int center, int size) {
        for (int i = 0; i < size; i += 2) {
            for (int j = center - i; j <= center + i; j++) {
                matrix.b(j, center - i);
                matrix.b(j, center + i);
                matrix.b(center - i, j);
                matrix.b(center + i, j);
            }
        }
        matrix.b(center - size, center - size);
        matrix.b((center - size) + 1, center - size);
        matrix.b(center - size, (center - size) + 1);
        matrix.b(center + size, center - size);
        matrix.b(center + size, (center - size) + 1);
        matrix.b(center + size, (center + size) - 1);
    }

    private static void a(b matrix, boolean compact, int matrixSize, a modeMessage) {
        int center = matrixSize / 2;
        int i;
        int offset;
        if (compact) {
            for (i = 0; i < 7; i++) {
                offset = (center - 3) + i;
                if (modeMessage.a(i)) {
                    matrix.b(offset, center - 5);
                }
                if (modeMessage.a(i + 7)) {
                    matrix.b(center + 5, offset);
                }
                if (modeMessage.a(20 - i)) {
                    matrix.b(offset, center + 5);
                }
                if (modeMessage.a(27 - i)) {
                    matrix.b(center - 5, offset);
                }
            }
            return;
        }
        for (i = 0; i < 10; i++) {
            offset = ((center - 5) + i) + (i / 5);
            if (modeMessage.a(i)) {
                matrix.b(offset, center - 7);
            }
            if (modeMessage.a(i + 10)) {
                matrix.b(center + 7, offset);
            }
            if (modeMessage.a(29 - i)) {
                matrix.b(offset, center + 7);
            }
            if (modeMessage.a(39 - i)) {
                matrix.b(center - 7, offset);
            }
        }
    }

    private static a a(a bitArray, int totalBits, int wordSize) {
        com.google.a.b.a.a aVar;
        int messageSizeInWords = bitArray.a() / wordSize;
        switch (wordSize) {
            case 4:
                aVar = com.google.a.b.a.a.d;
                break;
            case 6:
                aVar = com.google.a.b.a.a.c;
                break;
            case 8:
                aVar = com.google.a.b.a.a.g;
                break;
            case 10:
                aVar = com.google.a.b.a.a.b;
                break;
            case 12:
                aVar = com.google.a.b.a.a.a;
                break;
            default:
                throw new IllegalArgumentException("Unsupported word size " + wordSize);
        }
        com.google.a.b.a.c rs = new com.google.a.b.a.c(aVar);
        int totalWords = totalBits / wordSize;
        int[] messageWords = b(bitArray, wordSize, totalWords);
        rs.a(messageWords, totalWords - messageSizeInWords);
        int startPad = totalBits % wordSize;
        a messageBits = new a();
        messageBits.a(0, startPad);
        for (int messageWord : messageWords) {
            messageBits.a(messageWord, wordSize);
        }
        return messageBits;
    }

    private static int[] b(a stuffedBits, int wordSize, int totalWords) {
        int[] message = new int[totalWords];
        int n = stuffedBits.a() / wordSize;
        for (int i = 0; i < n; i++) {
            int value = 0;
            for (int j = 0; j < wordSize; j++) {
                value |= stuffedBits.a((i * wordSize) + j) ? 1 << ((wordSize - j) - 1) : 0;
            }
            message[i] = value;
        }
        return message;
    }

    private static a a(a bits, int wordSize) {
        a out = new a();
        int n = bits.a();
        int mask = (1 << wordSize) - 2;
        int i = 0;
        while (i < n) {
            int word = 0;
            int j = 0;
            while (j < wordSize) {
                if (i + j >= n || bits.a(i + j)) {
                    word |= 1 << ((wordSize - 1) - j);
                }
                j++;
            }
            if ((word & mask) == mask) {
                out.a(word & mask, wordSize);
                i--;
            } else if ((word & mask) == 0) {
                out.a(word | 1, wordSize);
                i--;
            } else {
                out.a(word, wordSize);
            }
            i += wordSize;
        }
        return out;
    }

    private static int a(int layers, boolean compact) {
        return ((compact ? 88 : 112) + (layers << 4)) * layers;
    }
}
