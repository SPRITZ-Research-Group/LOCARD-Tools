package com.microsoft.skypemessagetextinput.a;

import android.text.Editable;
import com.microsoft.skypemessagetextinput.a.g.a;
import java.util.Vector;

public final class f {
    public static Vector<g> a(Editable forText, int editStartPos, int aboutToReplaceCount, int aboutToInsertCount) {
        Vector<g> spanEditOperations = new Vector();
        for (com.microsoft.skypemessagetextinput.e.f span : (com.microsoft.skypemessagetextinput.e.f[]) forText.getSpans(editStartPos, editStartPos + aboutToReplaceCount, com.microsoft.skypemessagetextinput.e.f.class)) {
            int editEndPos = editStartPos + aboutToReplaceCount;
            int spanStart = forText.getSpanStart(span);
            int spanEnd = forText.getSpanEnd(span);
            a deletionType = aboutToReplaceCount == 0 ? a.NoDeletion : (editStartPos < spanStart || editStartPos + aboutToReplaceCount != spanEnd) ? a.OtherTextFragmentDeletion : a.TextTailDeletion;
            a action;
            if (editStartPos >= spanStart && editStartPos < spanEnd) {
                action = a(deletionType, aboutToInsertCount > 0, span.b());
                if (action != null) {
                    spanEditOperations.add(new g(span, action, Integer.valueOf(editStartPos - spanStart)));
                }
            } else if (editStartPos < spanStart && editEndPos > spanStart) {
                action = a(deletionType, false, span.b());
                if (action != null) {
                    spanEditOperations.add(new g(span, action, null));
                }
            }
        }
        return spanEditOperations;
    }

    private static a a(a deletionType, boolean hasInsertions, com.microsoft.skypemessagetextinput.e.f.a editSupport) {
        switch (editSupport) {
            case NotEditable:
                switch (deletionType) {
                    case NoDeletion:
                        if (hasInsertions) {
                            return a.RestoreContent;
                        }
                        return null;
                    case TextTailDeletion:
                        return a.Delete;
                    case OtherTextFragmentDeletion:
                        return hasInsertions ? a.RestoreContent : a.Delete;
                }
                break;
            case ConvertToPlainText:
                break;
            case WordWiseConvertionToPlainText:
                break;
            default:
                return null;
        }
        switch (deletionType) {
            case NoDeletion:
                if (hasInsertions) {
                    return a.ConvertToPlainText;
                }
                return null;
            case TextTailDeletion:
            case OtherTextFragmentDeletion:
                return a.ConvertToPlainText;
        }
        switch (deletionType) {
            case NoDeletion:
                if (hasInsertions) {
                    return a.WordWiseConvertionToPlainText;
                }
                return null;
            case TextTailDeletion:
            case OtherTextFragmentDeletion:
                return a.WordWiseConvertionToPlainText;
            default:
                return null;
        }
    }
}
