package com.google.android.exoplayer.text.ttml;

import android.text.SpannableStringBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

final class TtmlNode {
    public static final String TAG_BODY = "body";
    public static final String TAG_BR = "br";
    public static final String TAG_DIV = "div";
    public static final String TAG_HEAD = "head";
    public static final String TAG_LAYOUT = "layout";
    public static final String TAG_METADATA = "metadata";
    public static final String TAG_P = "p";
    public static final String TAG_REGION = "region";
    public static final String TAG_SMPTE_DATA = "smpte:data";
    public static final String TAG_SMPTE_IMAGE = "smpte:image";
    public static final String TAG_SMPTE_INFORMATION = "smpte:information";
    public static final String TAG_SPAN = "span";
    public static final String TAG_STYLE = "style";
    public static final String TAG_STYLING = "styling";
    public static final String TAG_TT = "tt";
    public static final long UNDEFINED_TIME = -1;
    private List<TtmlNode> children;
    public final long endTimeUs;
    public final boolean isTextNode;
    public final long startTimeUs;
    public final String tag;
    public final String text;

    public static TtmlNode buildTextNode(String str) {
        return new TtmlNode(null, applyTextElementSpacePolicy(str), -1, -1);
    }

    public static TtmlNode buildNode(String str, long j, long j2) {
        return new TtmlNode(str, null, j, j2);
    }

    private TtmlNode(String str, String str2, long j, long j2) {
        this.tag = str;
        this.text = str2;
        this.isTextNode = str2 != null;
        this.startTimeUs = j;
        this.endTimeUs = j2;
    }

    public final boolean isActive(long j) {
        return (this.startTimeUs == -1 && this.endTimeUs == -1) || ((this.startTimeUs <= j && this.endTimeUs == -1) || ((this.startTimeUs == -1 && j < this.endTimeUs) || (this.startTimeUs <= j && j < this.endTimeUs)));
    }

    public final void addChild(TtmlNode ttmlNode) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(ttmlNode);
    }

    public final TtmlNode getChild(int i) {
        if (this.children != null) {
            return (TtmlNode) this.children.get(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public final int getChildCount() {
        return this.children == null ? 0 : this.children.size();
    }

    public final long[] getEventTimesUs() {
        TreeSet treeSet = new TreeSet();
        int i = 0;
        getEventTimes(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            jArr[i] = ((Long) it.next()).longValue();
            i = i2;
        }
        return jArr;
    }

    private void getEventTimes(TreeSet<Long> treeSet, boolean z) {
        boolean equals = TAG_P.equals(this.tag);
        if (z || equals) {
            if (this.startTimeUs != -1) {
                treeSet.add(Long.valueOf(this.startTimeUs));
            }
            if (this.endTimeUs != -1) {
                treeSet.add(Long.valueOf(this.endTimeUs));
            }
        }
        if (this.children != null) {
            for (int i = 0; i < this.children.size(); i++) {
                TtmlNode ttmlNode = (TtmlNode) this.children.get(i);
                boolean z2 = z || equals;
                ttmlNode.getEventTimes(treeSet, z2);
            }
        }
    }

    public final CharSequence getText(long j) {
        int i;
        int i2;
        SpannableStringBuilder text = getText(j, new SpannableStringBuilder(), false);
        int length = text.length();
        for (i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                i2 = i + 1;
                int i3 = i2;
                while (i3 < text.length() && text.charAt(i3) == ' ') {
                    i3++;
                }
                i3 -= i2;
                if (i3 > 0) {
                    text.delete(i, i + i3);
                    length -= i3;
                }
            }
        }
        if (length > 0 && text.charAt(0) == ' ') {
            text.delete(0, 1);
            length--;
        }
        i = 0;
        while (true) {
            i2 = length - 1;
            if (i >= i2) {
                break;
            }
            if (text.charAt(i) == 10) {
                i2 = i + 1;
                if (text.charAt(i2) == ' ') {
                    text.delete(i2, i + 2);
                    length--;
                }
            }
            i++;
        }
        if (length > 0 && text.charAt(i2) == ' ') {
            text.delete(i2, length);
            length--;
        }
        i = 0;
        while (true) {
            i2 = length - 1;
            if (i >= i2) {
                break;
            }
            if (text.charAt(i) == ' ') {
                i2 = i + 1;
                if (text.charAt(i2) == 10) {
                    text.delete(i, i2);
                    length--;
                }
            }
            i++;
        }
        if (length > 0 && text.charAt(i2) == 10) {
            text.delete(i2, length);
            length--;
        }
        return text.subSequence(0, length);
    }

    private SpannableStringBuilder getText(long j, SpannableStringBuilder spannableStringBuilder, boolean z) {
        if (this.isTextNode && z) {
            spannableStringBuilder.append(this.text);
        } else if ("br".equals(this.tag) && z) {
            spannableStringBuilder.append(10);
        } else if (!TAG_METADATA.equals(this.tag) && isActive(j)) {
            boolean equals = TAG_P.equals(this.tag);
            for (int i = 0; i < getChildCount(); i++) {
                TtmlNode child = getChild(i);
                boolean z2 = z || equals;
                child.getText(j, spannableStringBuilder, z2);
            }
            if (equals) {
                endParagraph(spannableStringBuilder);
            }
        }
        return spannableStringBuilder;
    }

    private static void endParagraph(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length >= 0 && spannableStringBuilder.charAt(length) != 10) {
            spannableStringBuilder.append(10);
        }
    }

    private static String applyTextElementSpacePolicy(String str) {
        return str.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " ");
    }
}
