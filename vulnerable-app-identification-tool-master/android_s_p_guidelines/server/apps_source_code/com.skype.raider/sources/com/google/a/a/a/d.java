package com.google.a.a.a;

import com.google.a.b.a;
import com.skype.Defines;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class d {
    static final String[] a = new String[]{"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int[][] b = new int[][]{new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final int[][] c;
    private static final int[][] d;
    private final byte[] e;

    static {
        int c;
        int i;
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{5, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE});
        d = iArr;
        iArr[0][32] = 1;
        for (c = 65; c <= 90; c++) {
            d[0][c] = (c - 65) + 2;
        }
        d[1][32] = 1;
        for (c = 97; c <= 122; c++) {
            d[1][c] = (c - 97) + 2;
        }
        d[2][32] = 1;
        for (c = 48; c <= 57; c++) {
            d[2][c] = (c - 48) + 2;
        }
        d[2][44] = 12;
        d[2][46] = 13;
        int[] mixedTable = new int[]{0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (i = 0; i < 28; i++) {
            d[3][mixedTable[i]] = i;
        }
        int[] punctTable = new int[]{0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (i = 0; i < 31; i++) {
            if (punctTable[i] > 0) {
                d[4][punctTable[i]] = i;
            }
        }
        iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{6, 6});
        c = iArr;
        for (int[] fill : iArr) {
            Arrays.fill(fill, -1);
        }
        c[0][4] = 0;
        c[1][4] = 0;
        c[1][0] = 28;
        c[3][4] = 0;
        c[2][4] = 0;
        c[2][0] = 15;
    }

    public d(byte[] text) {
        this.e = text;
    }

    public final a a() {
        Collection<f> states = Collections.singletonList(f.a);
        int index = 0;
        while (index < this.e.length) {
            int nextChar;
            int pairCode;
            if (index + 1 < this.e.length) {
                nextChar = this.e[index + 1];
            } else {
                nextChar = 0;
            }
            switch (this.e[index]) {
                case (byte) 13:
                    if (nextChar != 10) {
                        pairCode = 0;
                        break;
                    }
                    pairCode = 2;
                    break;
                case (byte) 44:
                    if (nextChar != 32) {
                        pairCode = 0;
                        break;
                    }
                    pairCode = 4;
                    break;
                case (byte) 46:
                    if (nextChar != 32) {
                        pairCode = 0;
                        break;
                    }
                    pairCode = 3;
                    break;
                case (byte) 58:
                    if (nextChar != 32) {
                        pairCode = 0;
                        break;
                    }
                    pairCode = 5;
                    break;
                default:
                    pairCode = 0;
                    break;
            }
            if (pairCode > 0) {
                Iterable linkedList = new LinkedList();
                for (f fVar : states) {
                    f b = fVar.b(index);
                    linkedList.add(b.a(4, pairCode));
                    if (fVar.a() != 4) {
                        linkedList.add(b.b(4, pairCode));
                    }
                    if (pairCode == 3 || pairCode == 4) {
                        linkedList.add(b.a(2, 16 - pairCode).a(2, 1));
                    }
                    if (fVar.b() > 0) {
                        linkedList.add(fVar.a(index).a(index + 1));
                    }
                }
                states = a(linkedList);
                index++;
            } else {
                states = a(states, index);
            }
            index++;
        }
        return ((f) Collections.min(states, new Comparator<f>(this) {
            final /* synthetic */ d a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ int compare(Object obj, Object obj2) {
                return ((f) obj).c() - ((f) obj2).c();
            }
        })).a(this.e);
    }

    private Collection<f> a(Iterable<f> states, int index) {
        Collection<f> result = new LinkedList();
        for (f state : states) {
            a(state, index, result);
        }
        return a(result);
    }

    private void a(f state, int index, Collection<f> result) {
        char ch = (char) (this.e[index] & 255);
        boolean charInCurrentTable = d[state.a()][ch] > 0;
        f stateNoBinary = null;
        int mode = 0;
        while (mode <= 4) {
            int charInMode = d[mode][ch];
            if (charInMode > 0) {
                if (stateNoBinary == null) {
                    stateNoBinary = state.b(index);
                }
                if (!charInCurrentTable || mode == state.a() || mode == 2) {
                    result.add(stateNoBinary.a(mode, charInMode));
                }
                if (!charInCurrentTable && c[state.a()][mode] >= 0) {
                    result.add(stateNoBinary.b(mode, charInMode));
                }
            }
            mode++;
        }
        if (state.b() > 0 || d[state.a()][ch] == 0) {
            result.add(state.a(index));
        }
    }

    private static Collection<f> a(Iterable<f> states) {
        List<f> result = new LinkedList();
        for (f newState : states) {
            boolean add = true;
            Iterator<f> iterator = result.iterator();
            while (iterator.hasNext()) {
                f oldState = (f) iterator.next();
                if (oldState.a(newState)) {
                    add = false;
                    break;
                } else if (newState.a(oldState)) {
                    iterator.remove();
                }
            }
            if (add) {
                result.add(newState);
            }
        }
        return result;
    }
}
