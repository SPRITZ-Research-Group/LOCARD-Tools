package com.google.a.f.b;

final class d {
    static int a(b matrix) {
        return a(matrix, true) + a(matrix, false);
    }

    static int b(b matrix) {
        int numPenalties = 0;
        byte[][] array = matrix.c();
        int width = matrix.b();
        int height = matrix.a();
        int y = 0;
        while (y < height) {
            int x = 0;
            while (x < width) {
                byte[] arrayY = array[y];
                if (x + 6 < width && arrayY[x] == (byte) 1 && arrayY[x + 1] == (byte) 0 && arrayY[x + 2] == (byte) 1 && arrayY[x + 3] == (byte) 1 && arrayY[x + 4] == (byte) 1 && arrayY[x + 5] == (byte) 0 && arrayY[x + 6] == (byte) 1 && (a(arrayY, x - 4, x) || a(arrayY, x + 7, x + 11))) {
                    numPenalties++;
                }
                if (y + 6 < height && array[y][x] == (byte) 1 && array[y + 1][x] == (byte) 0 && array[y + 2][x] == (byte) 1 && array[y + 3][x] == (byte) 1 && array[y + 4][x] == (byte) 1 && array[y + 5][x] == (byte) 0 && array[y + 6][x] == (byte) 1 && (a(array, x, y - 4, y) || a(array, x, y + 7, y + 11))) {
                    numPenalties++;
                }
                x++;
            }
            y++;
        }
        return numPenalties * 40;
    }

    private static boolean a(byte[] rowArray, int from, int to) {
        from = Math.max(from, 0);
        to = Math.min(to, rowArray.length);
        for (int i = from; i < to; i++) {
            if (rowArray[i] == (byte) 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(byte[][] array, int col, int from, int to) {
        from = Math.max(from, 0);
        to = Math.min(to, array.length);
        for (int i = from; i < to; i++) {
            if (array[i][col] == (byte) 1) {
                return false;
            }
        }
        return true;
    }

    private static int a(b matrix, boolean isHorizontal) {
        int penalty = 0;
        int iLimit = isHorizontal ? matrix.a() : matrix.b();
        int jLimit = isHorizontal ? matrix.b() : matrix.a();
        byte[][] array = matrix.c();
        int i = 0;
        while (i < iLimit) {
            int numSameBitCells = 0;
            int prevBit = -1;
            int j = 0;
            while (j < jLimit) {
                int bit = isHorizontal ? array[i][j] : array[j][i];
                if (bit == prevBit) {
                    numSameBitCells++;
                } else {
                    if (numSameBitCells >= 5) {
                        penalty += (numSameBitCells - 5) + 3;
                    }
                    numSameBitCells = 1;
                    prevBit = bit;
                }
                j++;
            }
            if (numSameBitCells >= 5) {
                penalty += (numSameBitCells - 5) + 3;
            }
            i++;
        }
        return penalty;
    }
}
