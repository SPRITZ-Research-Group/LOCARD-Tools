package com.facebook.fbui.textlayoutbuilder;

import android.support.v4.text.e;
import android.support.v4.text.f;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import java.lang.reflect.Field;

final class b {
    public static StaticLayout a(CharSequence text, int end, TextPaint paint, int width, Alignment alignment, float spacingMult, float spacingAdd, boolean includePadding, TruncateAt ellipsize, int ellipsisWidth, int maxLines, e textDirection) {
        StaticLayout layout = b(text, end, paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines, textDirection);
        if (maxLines > 0) {
            while (layout.getLineCount() > maxLines) {
                int newEnd = layout.getLineStart(maxLines);
                if (newEnd >= end) {
                    break;
                }
                while (newEnd > 0 && Character.isSpace(text.charAt(newEnd - 1))) {
                    newEnd--;
                }
                end = newEnd;
                layout = b(text, end, paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines, textDirection);
                if (layout.getLineCount() >= maxLines && layout.getEllipsisCount(maxLines - 1) == 0) {
                    CharSequence charSequence = text.subSequence(0, end) + " …";
                    layout = b(charSequence, charSequence.length(), paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines, textDirection);
                }
            }
        }
        do {
        } while (!a(layout));
        return layout;
    }

    private static boolean a(StaticLayout layout) {
        int lineStart = layout.getLineStart(0);
        int i = 0;
        int lineCount = layout.getLineCount();
        while (i < lineCount) {
            int lineEnd = layout.getLineEnd(i);
            if (lineEnd < lineStart) {
                try {
                    Field mLinesField = StaticLayout.class.getDeclaredField("mLines");
                    mLinesField.setAccessible(true);
                    Field mColumnsField = StaticLayout.class.getDeclaredField("mColumns");
                    mColumnsField.setAccessible(true);
                    int[] mLines = (int[]) mLinesField.get(layout);
                    int mColumns = mColumnsField.getInt(layout);
                    for (int j = 0; j < mColumns; j++) {
                        int i2 = (mColumns * i) + j;
                        int i3 = ((mColumns * i) + j) + mColumns;
                        int i4 = mLines[i2];
                        mLines[i2] = mLines[i3];
                        mLines[i3] = i4;
                    }
                    return false;
                } catch (Exception e) {
                }
            } else {
                lineStart = lineEnd;
                i++;
            }
        }
        return true;
    }

    private static StaticLayout b(CharSequence text, int end, TextPaint paint, int width, Alignment alignment, float spacingMult, float spacingAdd, boolean includePadding, TruncateAt ellipsize, int ellipsisWidth, int maxLines, e textDirection) {
        try {
            TextDirectionHeuristic textDirectionHeuristic;
            if (textDirection == f.a) {
                textDirectionHeuristic = TextDirectionHeuristics.LTR;
            } else if (textDirection == f.b) {
                textDirectionHeuristic = TextDirectionHeuristics.RTL;
            } else {
                if (textDirection != f.c) {
                    if (textDirection == f.d) {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    } else if (textDirection == f.e) {
                        textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                    } else if (textDirection == f.f) {
                        textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                    }
                }
                textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            }
            return new StaticLayout(text, 0, end, paint, width, alignment, textDirectionHeuristic, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines);
        } catch (LinkageError e) {
            return new StaticLayout(text, 0, end, paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth);
        }
    }
}
