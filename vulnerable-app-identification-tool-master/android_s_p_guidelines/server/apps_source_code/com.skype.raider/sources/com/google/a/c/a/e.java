package com.google.a.c.a;

import java.util.Arrays;

public final class e {
    private final CharSequence a;
    private final int b;
    private final int c;
    private final byte[] d;

    public e(CharSequence codewords, int numcols, int numrows) {
        this.a = codewords;
        this.c = numcols;
        this.b = numrows;
        this.d = new byte[(numcols * numrows)];
        Arrays.fill(this.d, (byte) -1);
    }

    public final boolean a(int col, int row) {
        return this.d[(this.c * row) + col] == (byte) 1;
    }

    private void a(int col, int row, boolean bit) {
        this.d[(this.c * row) + col] = (byte) (bit ? 1 : 0);
    }

    private boolean b(int col, int row) {
        return this.d[(this.c * row) + col] >= (byte) 0;
    }

    public final void a() {
        int pos = 0;
        int row = 4;
        int col = 0;
        while (true) {
            int pos2;
            if (row == this.b && col == 0) {
                pos2 = pos + 1;
                a(this.b - 1, 0, pos, 1);
                a(this.b - 1, 1, pos, 2);
                a(this.b - 1, 2, pos, 3);
                a(0, this.c - 2, pos, 4);
                a(0, this.c - 1, pos, 5);
                a(1, this.c - 1, pos, 6);
                a(2, this.c - 1, pos, 7);
                a(3, this.c - 1, pos, 8);
                pos = pos2;
            }
            if (row == this.b - 2 && col == 0 && this.c % 4 != 0) {
                pos2 = pos + 1;
                a(this.b - 3, 0, pos, 1);
                a(this.b - 2, 0, pos, 2);
                a(this.b - 1, 0, pos, 3);
                a(0, this.c - 4, pos, 4);
                a(0, this.c - 3, pos, 5);
                a(0, this.c - 2, pos, 6);
                a(0, this.c - 1, pos, 7);
                a(1, this.c - 1, pos, 8);
                pos = pos2;
            }
            if (row == this.b - 2 && col == 0 && this.c % 8 == 4) {
                pos2 = pos + 1;
                a(this.b - 3, 0, pos, 1);
                a(this.b - 2, 0, pos, 2);
                a(this.b - 1, 0, pos, 3);
                a(0, this.c - 2, pos, 4);
                a(0, this.c - 1, pos, 5);
                a(1, this.c - 1, pos, 6);
                a(2, this.c - 1, pos, 7);
                a(3, this.c - 1, pos, 8);
                pos = pos2;
            }
            if (row == this.b + 4 && col == 2 && this.c % 8 == 0) {
                pos2 = pos + 1;
                a(this.b - 1, 0, pos, 1);
                a(this.b - 1, this.c - 1, pos, 2);
                a(0, this.c - 3, pos, 3);
                a(0, this.c - 2, pos, 4);
                a(0, this.c - 1, pos, 5);
                a(1, this.c - 3, pos, 6);
                a(1, this.c - 2, pos, 7);
                a(1, this.c - 1, pos, 8);
                pos = pos2;
            }
            do {
                if (row < this.b && col >= 0 && !b(col, row)) {
                    pos2 = pos + 1;
                    a(row, col, pos);
                    pos = pos2;
                }
                row -= 2;
                col += 2;
                if (row < 0) {
                    break;
                }
            } while (col < this.c);
            row++;
            col += 3;
            pos2 = pos;
            while (true) {
                if (row < 0 || col >= this.c || b(col, row)) {
                    pos = pos2;
                } else {
                    pos = pos2 + 1;
                    a(row, col, pos2);
                }
                row += 2;
                col -= 2;
                if (row >= this.b || col < 0) {
                    row += 3;
                    col++;
                } else {
                    pos2 = pos;
                }
            }
            row += 3;
            col++;
            if (row >= this.b && col >= this.c) {
                break;
            }
        }
        if (!b(this.c - 1, this.b - 1)) {
            a(this.c - 1, this.b - 1, true);
            a(this.c - 2, this.b - 2, true);
        }
    }

    private void a(int row, int col, int pos, int bit) {
        boolean z = true;
        if (row < 0) {
            row += this.b;
            col += 4 - ((this.b + 4) % 8);
        }
        if (col < 0) {
            col += this.c;
            row += 4 - ((this.c + 4) % 8);
        }
        if ((this.a.charAt(pos) & (1 << (8 - bit))) == 0) {
            z = false;
        }
        a(col, row, z);
    }

    private void a(int row, int col, int pos) {
        a(row - 2, col - 2, pos, 1);
        a(row - 2, col - 1, pos, 2);
        a(row - 1, col - 2, pos, 3);
        a(row - 1, col - 1, pos, 4);
        a(row - 1, col, pos, 5);
        a(row, col - 2, pos, 6);
        a(row, col - 1, pos, 7);
        a(row, col, pos, 8);
    }
}
