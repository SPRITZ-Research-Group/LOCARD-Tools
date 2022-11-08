package com.skype.android.video.render;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

public class GLHelpers {
    @SuppressLint({"UseSparseArrays"})
    private static final Map<Integer, EGLError> EGL_ERRORS = new HashMap();
    private final String tag;

    public class ConfigSelector {
        private final int alpha;
        private final int[] attributes;
        private final int blue;
        private final int depth;
        private final int green;
        private final int red;
        private final int stencil;

        private class EGLConfigurations implements Iterable<Item> {
            private final EGLConfig[] configs = getConfigs();
            private final EGLDisplay display;
            private final EGL10 egl;

            private class Item {
                private final EGLConfig config;

                public Item(com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations r1, javax.microedition.khronos.egl.EGLConfig r2) {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
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
                    r0 = this;
                    com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.this = r1;
                    r0.<init>();
                    r0.config = r2;
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.Item.<init>(com.skype.android.video.render.GLHelpers$ConfigSelector$EGLConfigurations, javax.microedition.khronos.egl.EGLConfig):void");
                }

                public int getAttribute(int r6) {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
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
                    r5 = this;
                    r1 = 0;
                    r2 = 1;
                    r0 = new int[r2];
                    r2 = com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.this;
                    r2 = r2.egl;
                    r3 = com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.this;
                    r3 = r3.display;
                    r4 = r5.config;
                    r2 = r2.eglGetConfigAttrib(r3, r4, r6, r0);
                    if (r2 == 0) goto L_0x001a;
                L_0x0018:
                    r1 = r0[r1];
                L_0x001a:
                    return r1;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.Item.getAttribute(int):int");
                }

                public javax.microedition.khronos.egl.EGLConfig getEGLConfig() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
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
                    r1 = this;
                    r0 = r1.config;
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.Item.getEGLConfig():javax.microedition.khronos.egl.EGLConfig");
                }
            }

            public EGLConfigurations(com.skype.android.video.render.GLHelpers.ConfigSelector r2, javax.microedition.khronos.egl.EGL10 r3, javax.microedition.khronos.egl.EGLDisplay r4) throws com.skype.android.video.render.GLHelpers.EGLException {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
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
                r1 = this;
                com.skype.android.video.render.GLHelpers.ConfigSelector.this = r2;
                r1.<init>();
                r1.egl = r3;
                r1.display = r4;
                r0 = r1.getConfigs();
                r1.configs = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.<init>(com.skype.android.video.render.GLHelpers$ConfigSelector, javax.microedition.khronos.egl.EGL10, javax.microedition.khronos.egl.EGLDisplay):void");
            }

            private javax.microedition.khronos.egl.EGLConfig[] getConfigs() throws com.skype.android.video.render.GLHelpers.EGLException {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
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
                r6 = this;
                r4 = 0;
                r0 = 1;
                r5 = new int[r0];
                r0 = r6.egl;
                r1 = r6.display;
                r2 = com.skype.android.video.render.GLHelpers.ConfigSelector.this;
                r2 = r2.attributes;
                r3 = 0;
                r0 = r0.eglChooseConfig(r1, r2, r3, r4, r5);
                if (r0 != 0) goto L_0x0021;
            L_0x0015:
                r0 = new com.skype.android.video.render.GLHelpers$EGLException;
                r1 = r6.egl;
                r1 = r1.eglGetError();
                r0.<init>(r1);
                throw r0;
            L_0x0021:
                r0 = r5[r4];
                r3 = new javax.microedition.khronos.egl.EGLConfig[r0];
                r0 = r6.egl;
                r1 = r6.display;
                r2 = com.skype.android.video.render.GLHelpers.ConfigSelector.this;
                r2 = r2.attributes;
                r4 = r3.length;
                r0 = r0.eglChooseConfig(r1, r2, r3, r4, r5);
                if (r0 != 0) goto L_0x0042;
            L_0x0036:
                r0 = new com.skype.android.video.render.GLHelpers$EGLException;
                r1 = r6.egl;
                r1 = r1.eglGetError();
                r0.<init>(r1);
                throw r0;
            L_0x0042:
                return r3;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.getConfigs():javax.microedition.khronos.egl.EGLConfig[]");
            }

            public java.util.Iterator<com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.Item> iterator() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
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
                r1 = this;
                r0 = new com.skype.android.video.render.GLHelpers$ConfigSelector$EGLConfigurations$1;
                r0.<init>();
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.iterator():java.util.Iterator<com.skype.android.video.render.GLHelpers$ConfigSelector$EGLConfigurations$Item>");
            }
        }

        public ConfigSelector(com.skype.android.video.render.GLHelpers r2, int r3, int r4, int r5, int r6, int r7, int r8, int[] r9) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
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
            r1 = this;
            com.skype.android.video.render.GLHelpers.this = r2;
            r1.<init>();
            r1.red = r3;
            r1.green = r4;
            r1.blue = r5;
            r1.alpha = r6;
            r1.depth = r7;
            r1.stencil = r8;
            r0 = r9.clone();
            r0 = (int[]) r0;
            r1.attributes = r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.<init>(com.skype.android.video.render.GLHelpers, int, int, int, int, int, int, int[]):void");
        }

        public javax.microedition.khronos.egl.EGLConfig selectConfig(javax.microedition.khronos.egl.EGL10 r10, javax.microedition.khronos.egl.EGLDisplay r11) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
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
            r9 = this;
            r4 = 0;
            r8 = 58;
            r7 = 6;
            r5 = com.skype.android.video.render.GLHelpers.this;
            r5.tag;
            r5 = 3;
            r5 = com.skype.android.util2.Log.a(r5);
            if (r5 == 0) goto L_0x001b;
        L_0x0010:
            r5 = com.skype.android.video.render.GLHelpers.this;
            r5 = r5.tag;
            r6 = "selectConfig(): ENTER";
            com.skype.android.util2.Log.a(r5, r6);
        L_0x001b:
            r1 = new com.skype.android.video.render.GLHelpers$ConfigSelector$EGLConfigurations;	 Catch:{ EGLException -> 0x0085 }
            r1.<init>(r10, r11);	 Catch:{ EGLException -> 0x0085 }
            r5 = r1.iterator();
        L_0x0024:
            r6 = r5.hasNext();
            if (r6 == 0) goto L_0x00ae;
        L_0x002a:
            r0 = r5.next();
            r0 = (com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.Item) r0;
            r6 = r9.isMatch(r0);
            if (r6 == 0) goto L_0x0024;
        L_0x0036:
            r4 = com.skype.android.video.render.GLHelpers.this;
            r4.tag;
            r4 = 4;
            r4 = com.skype.android.util2.Log.a(r4);
            if (r4 == 0) goto L_0x0080;
        L_0x0042:
            r4 = 12328; // 0x3028 float:1.7275E-41 double:6.091E-320;
            r3 = r0.getAttribute(r4);
            r4 = com.skype.android.video.render.GLHelpers.this;
            r4 = r4.tag;
            r5 = new java.lang.StringBuilder;
            r6 = "selected config: EGL_CONFIG_ID=";
            r5.<init>(r6);
            r5 = r5.append(r3);
            r6 = ", rgb=";
            r5 = r5.append(r6);
            r6 = r9.red;
            r5 = r5.append(r6);
            r5 = r5.append(r8);
            r6 = r9.green;
            r5 = r5.append(r6);
            r5 = r5.append(r8);
            r6 = r9.blue;
            r5 = r5.append(r6);
            r5 = r5.toString();
            com.skype.android.util2.Log.b(r4, r5);
        L_0x0080:
            r4 = r0.getEGLConfig();
        L_0x0084:
            return r4;
        L_0x0085:
            r2 = move-exception;
            r5 = com.skype.android.video.render.GLHelpers.this;
            r5.tag;
            r5 = com.skype.android.util2.Log.a(r7);
            if (r5 == 0) goto L_0x0084;
        L_0x0091:
            r5 = com.skype.android.video.render.GLHelpers.this;
            r5 = r5.tag;
            r6 = new java.lang.StringBuilder;
            r7 = "Failed to get EGL configurations: ";
            r6.<init>(r7);
            r7 = r2.getMessage();
            r6 = r6.append(r7);
            r6 = r6.toString();
            com.skype.android.util2.Log.c(r5, r6);
            goto L_0x0084;
        L_0x00ae:
            r5 = com.skype.android.video.render.GLHelpers.this;
            r5.tag;
            r5 = com.skype.android.util2.Log.a(r7);
            if (r5 == 0) goto L_0x0084;
        L_0x00b9:
            r5 = com.skype.android.video.render.GLHelpers.this;
            r5 = r5.tag;
            r6 = "no configurations found!";
            com.skype.android.util2.Log.c(r5, r6);
            goto L_0x0084;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.selectConfig(javax.microedition.khronos.egl.EGL10, javax.microedition.khronos.egl.EGLDisplay):javax.microedition.khronos.egl.EGLConfig");
        }

        private boolean isMatch(com.skype.android.video.render.GLHelpers.ConfigSelector.EGLConfigurations.Item r3) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
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
            r2 = this;
            r0 = 12325; // 0x3025 float:1.7271E-41 double:6.0894E-320;
            r0 = r3.getAttribute(r0);
            r1 = r2.depth;
            if (r0 < r1) goto L_0x003e;
        L_0x000a:
            r0 = 12326; // 0x3026 float:1.7272E-41 double:6.09E-320;
            r0 = r3.getAttribute(r0);
            r1 = r2.stencil;
            if (r0 < r1) goto L_0x003e;
        L_0x0014:
            r0 = 12324; // 0x3024 float:1.727E-41 double:6.089E-320;
            r0 = r3.getAttribute(r0);
            r1 = r2.red;
            if (r0 != r1) goto L_0x003e;
        L_0x001e:
            r0 = 12323; // 0x3023 float:1.7268E-41 double:6.0884E-320;
            r0 = r3.getAttribute(r0);
            r1 = r2.green;
            if (r0 != r1) goto L_0x003e;
        L_0x0028:
            r0 = 12322; // 0x3022 float:1.7267E-41 double:6.088E-320;
            r0 = r3.getAttribute(r0);
            r1 = r2.blue;
            if (r0 != r1) goto L_0x003e;
        L_0x0032:
            r0 = 12321; // 0x3021 float:1.7265E-41 double:6.0874E-320;
            r0 = r3.getAttribute(r0);
            r1 = r2.alpha;
            if (r0 != r1) goto L_0x003e;
        L_0x003c:
            r0 = 1;
        L_0x003d:
            return r0;
        L_0x003e:
            r0 = 0;
            goto L_0x003d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.render.GLHelpers.ConfigSelector.isMatch(com.skype.android.video.render.GLHelpers$ConfigSelector$EGLConfigurations$Item):boolean");
        }
    }

    private enum EGLError {
        EGL_SUCCESS(12288),
        EGL_NOT_INITIALIZED(12289),
        EGL_BAD_ACCESS(12290),
        EGL_BAD_ALLOC(12291),
        EGL_BAD_ATTRIBUTE(12292),
        EGL_BAD_CONTEXT(12294),
        EGL_BAD_CONFIG(12293),
        EGL_BAD_CURRENT_SURFACE(12295),
        EGL_BAD_DISPLAY(12296),
        EGL_BAD_SURFACE(12301),
        EGL_BAD_MATCH(12297),
        EGL_BAD_PARAMETER(12300),
        EGL_BAD_NATIVE_PIXMAP(12298),
        EGL_BAD_NATIVE_WINDOW(12299),
        EGL_CONTEXT_LOST(12302);

        private EGLError(int code) {
            GLHelpers.EGL_ERRORS.put(Integer.valueOf(code), this);
        }
    }

    private static class EGLException extends Exception {
        private static final long serialVersionUID = 1;

        public EGLException(int code) {
            super(((EGLError) GLHelpers.EGL_ERRORS.get(Integer.valueOf(code))).name());
        }
    }

    public GLHelpers(String tag) {
        if (tag == null) {
            throw new IllegalArgumentException("tag");
        }
        this.tag = tag;
    }
}
