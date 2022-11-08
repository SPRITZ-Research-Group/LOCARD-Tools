package com.facebook.react.views.text;

import android.os.Build.VERSION;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import com.adjust.sdk.Constants;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.f;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.m;
import com.facebook.react.uimanager.p;
import com.facebook.react.uimanager.w;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class g extends h {
    public static final Iterable<w> i = new ArrayList(0);
    private static final TextPaint j = new TextPaint(1);
    @Nullable
    private Spannable A;
    private Map<Integer, w> B;
    protected boolean a = true;
    protected int b = -1;
    protected float c = -1.0f;
    protected float d = -1.0f;
    protected int f = 0;
    protected int g;
    protected boolean h;
    private final YogaMeasureFunction k = new YogaMeasureFunction(this) {
        final /* synthetic */ g a;

        {
            this.a = this$0;
        }

        public final long measure(com.facebook.yoga.YogaNode r24, float r25, com.facebook.yoga.YogaMeasureMode r26, float r27, com.facebook.yoga.YogaMeasureMode r28) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r2_4 'layout' android.text.Layout) in PHI: PHI: (r2_2 'layout' android.text.Layout) = (r2_0 'layout' android.text.Layout), (r2_1 'layout' android.text.Layout), (r2_3 'layout' android.text.Layout), (r2_4 'layout' android.text.Layout), (r2_5 'layout' android.text.Layout) binds: {(r2_0 'layout' android.text.Layout)=B:16:0x0047, (r2_1 'layout' android.text.Layout)=B:24:0x0084, (r2_3 'layout' android.text.Layout)=B:29:0x00be, (r2_4 'layout' android.text.Layout)=B:32:0x00d3, (r2_5 'layout' android.text.Layout)=B:33:0x00ea}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r23 = this;
            r4 = com.facebook.react.views.text.g.j;
            r0 = r23;
            r6 = r0.a;
            r6 = r6.A;
            r7 = "Spannable element has not been prepared in onBeforeLayout";
            r3 = com.facebook.infer.annotation.a.a(r6, r7);
            r3 = (android.text.Spanned) r3;
            r12 = android.text.BoringLayout.isBoring(r3, r4);
            if (r12 != 0) goto L_0x007e;
        L_0x001a:
            r21 = android.text.Layout.getDesiredWidth(r3, r4);
        L_0x001e:
            r6 = com.facebook.yoga.YogaMeasureMode.UNDEFINED;
            r0 = r26;
            if (r0 == r6) goto L_0x0029;
        L_0x0024:
            r6 = 0;
            r6 = (r25 > r6 ? 1 : (r25 == r6 ? 0 : -1));
            if (r6 >= 0) goto L_0x0081;
        L_0x0029:
            r22 = 1;
        L_0x002b:
            if (r12 != 0) goto L_0x00b3;
        L_0x002d:
            if (r22 != 0) goto L_0x0039;
        L_0x002f:
            r6 = com.facebook.yoga.a.a(r21);
            if (r6 != 0) goto L_0x00b3;
        L_0x0035:
            r6 = (r21 > r25 ? 1 : (r21 == r25 ? 0 : -1));
            if (r6 > 0) goto L_0x00b3;
        L_0x0039:
            r0 = r21;
            r6 = (double) r0;
            r6 = java.lang.Math.ceil(r6);
            r5 = (int) r6;
            r6 = android.os.Build.VERSION.SDK_INT;
            r7 = 23;
            if (r6 >= r7) goto L_0x0084;
        L_0x0047:
            r2 = new android.text.StaticLayout;
            r6 = android.text.Layout.Alignment.ALIGN_NORMAL;
            r7 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r8 = 0;
            r9 = 1;
            r2.<init>(r3, r4, r5, r6, r7, r8, r9);
        L_0x0052:
            r0 = r23;
            r6 = r0.a;
            r6 = r6.b;
            r7 = -1;
            if (r6 == r7) goto L_0x011d;
        L_0x005b:
            r0 = r23;
            r6 = r0.a;
            r6 = r6.b;
            r7 = r2.getLineCount();
            if (r6 >= r7) goto L_0x011d;
        L_0x0067:
            r6 = r2.getWidth();
            r0 = r23;
            r7 = r0.a;
            r7 = r7.b;
            r7 = r7 + -1;
            r7 = r2.getLineBottom(r7);
            r6 = (float) r6;
            r7 = (float) r7;
            r6 = com.facebook.yoga.b.a(r6, r7);
        L_0x007d:
            return r6;
        L_0x007e:
            r21 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
            goto L_0x001e;
        L_0x0081:
            r22 = 0;
            goto L_0x002b;
        L_0x0084:
            r6 = 0;
            r7 = r3.length();
            r6 = android.text.StaticLayout.Builder.obtain(r3, r6, r7, r4, r5);
            r7 = android.text.Layout.Alignment.ALIGN_NORMAL;
            r6 = r6.setAlignment(r7);
            r7 = 0;
            r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r6 = r6.setLineSpacing(r7, r8);
            r7 = 1;
            r6 = r6.setIncludePad(r7);
            r0 = r23;
            r7 = r0.a;
            r7 = r7.g;
            r6 = r6.setBreakStrategy(r7);
            r7 = 1;
            r6 = r6.setHyphenationFrequency(r7);
            r2 = r6.build();
            goto L_0x0052;
        L_0x00b3:
            if (r12 == 0) goto L_0x00cd;
        L_0x00b5:
            if (r22 != 0) goto L_0x00be;
        L_0x00b7:
            r6 = r12.width;
            r6 = (float) r6;
            r6 = (r6 > r25 ? 1 : (r6 == r25 ? 0 : -1));
            if (r6 > 0) goto L_0x00cd;
        L_0x00be:
            r8 = r12.width;
            r9 = android.text.Layout.Alignment.ALIGN_NORMAL;
            r10 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r11 = 0;
            r13 = 1;
            r6 = r3;
            r7 = r4;
            r2 = android.text.BoringLayout.make(r6, r7, r8, r9, r10, r11, r12, r13);
            goto L_0x0052;
        L_0x00cd:
            r6 = android.os.Build.VERSION.SDK_INT;
            r7 = 23;
            if (r6 >= r7) goto L_0x00ea;
        L_0x00d3:
            r2 = new android.text.StaticLayout;
            r0 = r25;
            r0 = (int) r0;
            r16 = r0;
            r17 = android.text.Layout.Alignment.ALIGN_NORMAL;
            r18 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r19 = 0;
            r20 = 1;
            r13 = r2;
            r14 = r3;
            r15 = r4;
            r13.<init>(r14, r15, r16, r17, r18, r19, r20);
            goto L_0x0052;
        L_0x00ea:
            r6 = 0;
            r7 = r3.length();
            r0 = r25;
            r8 = (int) r0;
            r6 = android.text.StaticLayout.Builder.obtain(r3, r6, r7, r4, r8);
            r7 = android.text.Layout.Alignment.ALIGN_NORMAL;
            r6 = r6.setAlignment(r7);
            r7 = 0;
            r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r6 = r6.setLineSpacing(r7, r8);
            r7 = 1;
            r6 = r6.setIncludePad(r7);
            r0 = r23;
            r7 = r0.a;
            r7 = r7.g;
            r6 = r6.setBreakStrategy(r7);
            r7 = 1;
            r6 = r6.setHyphenationFrequency(r7);
            r2 = r6.build();
            goto L_0x0052;
        L_0x011d:
            r6 = r2.getWidth();
            r7 = r2.getHeight();
            r6 = (float) r6;
            r7 = (float) r7;
            r6 = com.facebook.yoga.b.a(r6, r7);
            goto L_0x007d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.g.1.measure(com.facebook.yoga.YogaNode, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode):long");
        }
    };
    private boolean l = false;
    private float m = Float.NaN;
    private int n;
    private boolean o = false;
    private int p;
    private float q;
    private float r;
    private float s;
    private int t;
    private boolean u;
    private boolean v;
    private int w;
    private int x;
    @Nullable
    private String y;
    @Nullable
    private String z;

    private static class a {
        protected int a;
        protected int b;
        protected Object c;

        a(int start, int end, Object what) {
            this.a = start;
            this.b = end;
            this.c = what;
        }

        public final void a(SpannableStringBuilder sb) {
            int spanFlags = 34;
            if (this.a == 0) {
                spanFlags = 18;
            }
            sb.setSpan(this.c, this.a, this.b, spanFlags);
        }
    }

    private static void a(g textShadowNode, SpannableStringBuilder sb, List<a> ops, boolean supportsInlineViews, Map<Integer, w> inlineViews, float parentMaxContentSizeMultiplier) {
        int start = sb.length();
        if (textShadowNode.z != null) {
            sb.append(textShadowNode.z);
        }
        int length = textShadowNode.y();
        for (int i = 0; i < length; i++) {
            w child = textShadowNode.c(i);
            if (child instanceof g) {
                float maxContentSizeMultiplier;
                if (textShadowNode.m == 0.0f || textShadowNode.m >= 1.0f) {
                    maxContentSizeMultiplier = textShadowNode.m;
                } else {
                    maxContentSizeMultiplier = parentMaxContentSizeMultiplier;
                }
                a((g) child, sb, ops, supportsInlineViews, inlineViews, maxContentSizeMultiplier);
            } else if (child instanceof f) {
                sb.append("0");
                ops.add(new a(sb.length() - 1, sb.length(), ((f) child).c()));
            } else if (supportsInlineViews) {
                int reactTag = child.A();
                YogaValue width = child.S();
                YogaValue height = child.U();
                if (width.unit == YogaUnit.POINT && height.unit == YogaUnit.POINT) {
                    if (ops.isEmpty()) {
                        sb.append(" ");
                        ops.add(new a(sb.length() - 1, sb.length(), new AbsoluteSizeSpan(10)));
                    }
                    sb.append("0");
                    List<a> list = ops;
                    list.add(new a(sb.length() - 1, sb.length(), new l(reactTag, width.value, height.value, a(child, YogaEdge.START), a(child, YogaEdge.TOP), a(child, YogaEdge.END), a(child, YogaEdge.BOTTOM))));
                    inlineViews.put(Integer.valueOf(reactTag), child);
                } else {
                    throw new f("Views nested within a <Text> must have a width and height specified in pixels");
                }
            } else {
                throw new f("Unexpected view type nested under a <Text> or <TextInput> node: " + child.getClass());
            }
            child.v();
        }
        int end = sb.length();
        if (end >= start) {
            if (textShadowNode.l) {
                ops.add(new a(start, end, new ForegroundColorSpan(textShadowNode.n)));
            }
            if (textShadowNode.o) {
                ops.add(new a(start, end, new BackgroundColorSpan(textShadowNode.p)));
            }
            if (textShadowNode.c != -1.0f) {
                ops.add(new a(start, end, new AbsoluteSizeSpan(textShadowNode.c(parentMaxContentSizeMultiplier))));
            }
            if (!(textShadowNode.w == -1 && textShadowNode.x == -1 && textShadowNode.y == null)) {
                ops.add(new a(start, end, new b(textShadowNode.w, textShadowNode.x, textShadowNode.y, textShadowNode.E().getAssets())));
            }
            if (textShadowNode.u) {
                ops.add(new a(start, end, new UnderlineSpan()));
            }
            if (textShadowNode.v) {
                ops.add(new a(start, end, new StrikethroughSpan()));
            }
            if (!(textShadowNode.q == 0.0f && textShadowNode.r == 0.0f)) {
                ops.add(new a(start, end, new j(textShadowNode.q, textShadowNode.r, textShadowNode.s, textShadowNode.t)));
            }
            if (!Float.isNaN(textShadowNode.d)) {
                float f;
                if (textShadowNode.d == -1.0f) {
                    f = Float.NaN;
                } else if (textShadowNode.a) {
                    f = p.a(textShadowNode.d, textShadowNode.q(parentMaxContentSizeMultiplier));
                } else {
                    f = p.a(textShadowNode.d);
                }
                ops.add(new a(start, end, new a(f)));
            }
            ops.add(new a(start, end, new e(textShadowNode.A())));
        }
    }

    protected static Spannable a(g textCSSNode, m nativeViewHierarchyOptimizer) {
        boolean z;
        if (textCSSNode.c() && nativeViewHierarchyOptimizer == null) {
            z = false;
        } else {
            z = true;
        }
        com.facebook.infer.annotation.a.a(z, "nativeViewHierarchyOptimizer is required when the textCSSNode supports inline views");
        SpannableStringBuilder sb = new SpannableStringBuilder();
        List<a> ops = new ArrayList();
        Map<Integer, w> inlineViews = textCSSNode.c() ? new HashMap() : null;
        a(textCSSNode, sb, ops, textCSSNode.c(), inlineViews, Float.NaN);
        if (textCSSNode.c == -1.0f) {
            sb.setSpan(new AbsoluteSizeSpan(textCSSNode.c(Float.NaN)), 0, sb.length(), 17);
        }
        textCSSNode.h = false;
        textCSSNode.B = inlineViews;
        for (int i = ops.size() - 1; i >= 0; i--) {
            a op = (a) ops.get(i);
            boolean isInlineImage = op.c instanceof k;
            if (isInlineImage || (op.c instanceof l)) {
                if (isInlineImage) {
                    textCSSNode.h = true;
                } else {
                    w childNode = (w) inlineViews.get(Integer.valueOf(op.c.a()));
                    nativeViewHierarchyOptimizer.b(childNode);
                    childNode.c((w) textCSSNode);
                }
            }
            op.a(sb);
        }
        return sb;
    }

    public g() {
        int i = 1;
        if (VERSION.SDK_INT < 23) {
            i = 0;
        }
        this.g = i;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = 1.0f;
        this.t = 1426063360;
        this.u = false;
        this.v = false;
        this.w = -1;
        this.x = -1;
        this.y = null;
        this.z = null;
        this.h = false;
        if (!a()) {
            a(this.k);
        }
    }

    public void a(m nativeViewHierarchyOptimizer) {
        if (!a()) {
            this.A = a(this, nativeViewHierarchyOptimizer);
            i();
        }
    }

    public final void i() {
        super.i();
        if (!a()) {
            super.x();
        }
    }

    @ReactProp(name = "text")
    public void setText(@Nullable String text) {
        this.z = text;
        i();
    }

    @ReactProp(defaultInt = -1, name = "numberOfLines")
    public void setNumberOfLines(int numberOfLines) {
        if (numberOfLines == 0) {
            numberOfLines = -1;
        }
        this.b = numberOfLines;
        i();
    }

    @ReactProp(defaultFloat = -1.0f, name = "lineHeight")
    public void setLineHeight(float lineHeight) {
        this.d = lineHeight;
        i();
    }

    @ReactProp(defaultBoolean = true, name = "allowFontScaling")
    public void setAllowFontScaling(boolean allowFontScaling) {
        if (allowFontScaling != this.a) {
            this.a = allowFontScaling;
            i();
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "maxContentSizeMultiplier")
    public void setMaxContentSizeMultiplier(float maxContentSizeMultiplier) {
        if (maxContentSizeMultiplier == this.m) {
            return;
        }
        if (maxContentSizeMultiplier == 0.0f || maxContentSizeMultiplier >= 1.0f) {
            this.m = maxContentSizeMultiplier;
            i();
            return;
        }
        throw new n("maxContentSizeMultiplier must be NaN, 0, or >= 1");
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(@Nullable String textAlign) {
        if (textAlign == null || "auto".equals(textAlign)) {
            this.f = 0;
        } else if ("left".equals(textAlign)) {
            this.f = 3;
        } else if ("right".equals(textAlign)) {
            this.f = 5;
        } else if ("center".equals(textAlign)) {
            this.f = 1;
        } else if ("justify".equals(textAlign)) {
            this.f = 3;
        } else {
            throw new n("Invalid textAlign: " + textAlign);
        }
        i();
    }

    @ReactProp(defaultFloat = -1.0f, name = "fontSize")
    public void setFontSize(float fontSize) {
        this.c = fontSize;
        i();
    }

    protected final int c(float parentMaxContentSizeMultiplier) {
        float fontSize = this.c;
        if (fontSize == -1.0f) {
            fontSize = 14.0f;
        }
        if (this.a) {
            return (int) Math.ceil((double) p.a(fontSize, q(parentMaxContentSizeMultiplier)));
        }
        return (int) Math.ceil((double) p.a(fontSize));
    }

    private float q(float parentMaxContentSizeMultiplier) {
        if (this.m == 0.0f || this.m >= 1.0f) {
            return this.m;
        }
        return (parentMaxContentSizeMultiplier == 0.0f || parentMaxContentSizeMultiplier >= 1.0f) ? parentMaxContentSizeMultiplier : UIManagerModule.getMaxContentSizeMultiplierInternal();
    }

    @ReactProp(name = "color")
    public void setColor(@Nullable Integer color) {
        this.l = color != null;
        if (this.l) {
            this.n = color.intValue();
        }
        i();
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(Integer color) {
        if (a()) {
            this.o = color != null;
            if (this.o) {
                this.p = color.intValue();
            }
            i();
        }
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(@Nullable String fontFamily) {
        this.y = fontFamily;
        i();
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(@Nullable String fontWeightString) {
        int fontWeightNumeric;
        if (fontWeightString == null) {
            fontWeightNumeric = -1;
        } else if (fontWeightString.length() != 3 || !fontWeightString.endsWith("00") || fontWeightString.charAt(0) > '9' || fontWeightString.charAt(0) < '1') {
            fontWeightNumeric = -1;
        } else {
            fontWeightNumeric = (fontWeightString.charAt(0) - 48) * 100;
        }
        int fontWeight = -1;
        if (fontWeightNumeric >= 500 || "bold".equals(fontWeightString)) {
            fontWeight = 1;
        } else if (Constants.NORMAL.equals(fontWeightString) || (fontWeightNumeric != -1 && fontWeightNumeric < 500)) {
            fontWeight = 0;
        }
        if (fontWeight != this.x) {
            this.x = fontWeight;
            i();
        }
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(@Nullable String fontStyleString) {
        int fontStyle = -1;
        if ("italic".equals(fontStyleString)) {
            fontStyle = 2;
        } else if (Constants.NORMAL.equals(fontStyleString)) {
            fontStyle = 0;
        }
        if (fontStyle != this.w) {
            this.w = fontStyle;
            i();
        }
    }

    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(@Nullable String textDecorationLineString) {
        int i = 0;
        this.u = false;
        this.v = false;
        if (textDecorationLineString != null) {
            String[] split = textDecorationLineString.split(" ");
            int length = split.length;
            while (i < length) {
                String textDecorationLineSubString = split[i];
                if ("underline".equals(textDecorationLineSubString)) {
                    this.u = true;
                } else if ("line-through".equals(textDecorationLineSubString)) {
                    this.v = true;
                }
                i++;
            }
        }
        i();
    }

    @ReactProp(name = "textBreakStrategy")
    public void setTextBreakStrategy(@Nullable String textBreakStrategy) {
        if (VERSION.SDK_INT >= 23) {
            if (textBreakStrategy == null || "highQuality".equals(textBreakStrategy)) {
                this.g = 1;
            } else if ("simple".equals(textBreakStrategy)) {
                this.g = 0;
            } else if ("balanced".equals(textBreakStrategy)) {
                this.g = 2;
            } else {
                throw new n("Invalid textBreakStrategy: " + textBreakStrategy);
            }
            i();
        }
    }

    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(am offsetMap) {
        this.q = 0.0f;
        this.r = 0.0f;
        if (offsetMap != null) {
            if (offsetMap.hasKey("width") && !offsetMap.isNull("width")) {
                this.q = p.a((float) offsetMap.getDouble("width"));
            }
            if (offsetMap.hasKey("height") && !offsetMap.isNull("height")) {
                this.r = p.a((float) offsetMap.getDouble("height"));
            }
        }
        i();
    }

    @ReactProp(defaultInt = 1, name = "textShadowRadius")
    public void setTextShadowRadius(float textShadowRadius) {
        if (textShadowRadius != this.s) {
            this.s = textShadowRadius;
            i();
        }
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(int textShadowColor) {
        if (textShadowColor != this.t) {
            this.t = textShadowColor;
            i();
        }
    }

    protected boolean c() {
        return true;
    }

    public final boolean s() {
        return c();
    }

    public final boolean b() {
        return (c() || a()) ? false : true;
    }

    public void a(al uiViewOperationQueue) {
        int i = 3;
        if (!a()) {
            super.a(uiViewOperationQueue);
            if (this.A != null) {
                Spannable spannable = this.A;
                boolean z = this.h;
                float g = g(4);
                float g2 = g(1);
                float g3 = g(5);
                float g4 = g(3);
                int i2 = this.f;
                if (R() == YogaDirection.RTL) {
                    if (i2 != 5) {
                        if (i2 == 3) {
                            i = 5;
                        }
                    }
                    uiViewOperationQueue.a(A(), new h(spannable, -1, z, g, g2, g3, g4, i, this.g));
                }
                i = i2;
                uiViewOperationQueue.a(A(), new h(spannable, -1, z, g, g2, g3, g4, i, this.g));
            }
        }
    }

    public final Iterable<w> Y() {
        int i = 0;
        if (this.B == null || this.B.isEmpty()) {
            return i;
        }
        Spanned text = (Spanned) com.facebook.infer.annotation.a.a(this.A, "Spannable element has not been prepared in onBeforeLayout");
        l[] placeholders = (l[]) text.getSpans(0, text.length(), l.class);
        Iterable<w> shadowNodes = new ArrayList(placeholders.length);
        int length = placeholders.length;
        while (i < length) {
            w child = (w) this.B.get(Integer.valueOf(placeholders[i].a()));
            child.G();
            shadowNodes.add(child);
            i++;
        }
        return shadowNodes;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static float a(w node, YogaEdge edge) {
        com.facebook.infer.annotation.a.a(edge.intValue() <= YogaEdge.END.intValue(), "Cannot get computed value of multi-edge shorthands");
        YogaValue value = node.e(edge.intValue());
        if (value.unit == YogaUnit.UNDEFINED) {
            if (edge == YogaEdge.TOP || edge == YogaEdge.BOTTOM) {
                value = node.e(YogaEdge.VERTICAL.intValue());
            }
            if (edge == YogaEdge.LEFT || edge == YogaEdge.RIGHT || edge == YogaEdge.START || edge == YogaEdge.END) {
                value = node.e(YogaEdge.HORIZONTAL.intValue());
            }
            value = node.e(YogaEdge.ALL.intValue());
            if (value.unit == YogaUnit.UNDEFINED) {
                value = YogaValue.UNDEFINED;
            }
        }
        if (value.unit == YogaUnit.UNDEFINED) {
            return 0.0f;
        }
        if (value.unit == YogaUnit.POINT) {
            return value.value;
        }
        throw new f("Margins of views nested within a <Text> can only be specified in pixels.");
    }
}
