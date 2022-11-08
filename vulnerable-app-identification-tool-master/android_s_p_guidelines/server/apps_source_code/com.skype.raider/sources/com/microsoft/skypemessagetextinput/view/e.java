package com.microsoft.skypemessagetextinput.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.text.Editable;
import com.facebook.react.uimanager.ae;
import com.microsoft.skypemessagetextinput.e.d;
import com.microsoft.skypemessagetextinput.e.f;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class e {
    public static void a(final Editable inContent, int selectionStart, int selectionEnd, ae context) {
        int startText = selectionStart < selectionEnd ? selectionStart : 0;
        int endText = selectionStart < selectionEnd ? selectionEnd : inContent.length();
        List<f> spans = Arrays.asList(inContent.getSpans(startText, endText, f.class));
        Collections.sort(spans, new Comparator<f>() {
            public final /* synthetic */ int compare(Object obj, Object obj2) {
                return inContent.getSpanStart((f) obj) - inContent.getSpanStart((f) obj2);
            }
        });
        StringBuilder text = new StringBuilder();
        for (f span : spans) {
            int spanStartPos = inContent.getSpanStart(span);
            int spanEndPos = inContent.getSpanEnd(span);
            if (startText < spanStartPos) {
                text.append(inContent, startText, spanStartPos);
            }
            text.append(inContent, Math.max(spanStartPos, startText), Math.min(spanEndPos, endText));
            if (span instanceof d) {
                text.append(8201);
            }
            startText = spanEndPos;
            if (spanEndPos >= endText) {
                break;
            }
        }
        if (startText < endText) {
            text.append(inContent, startText, endText);
        }
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", text));
    }
}
