package kotlin.reflect.jvm.internal.impl.protobuf;

public class LazyFieldLite {
    private ByteString bytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile boolean isDirty;
    protected volatile MessageLite value;

    public MessageLite getValue(MessageLite messageLite) {
        ensureInitialized(messageLite);
        return this.value;
    }

    public MessageLite setValue(MessageLite messageLite) {
        MessageLite messageLite2 = this.value;
        this.value = messageLite;
        this.bytes = null;
        this.isDirty = true;
        return messageLite2;
    }

    public int getSerializedSize() {
        if (this.isDirty) {
            return this.value.getSerializedSize();
        }
        return this.bytes.size();
    }

    protected void ensureInitialized(kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:kotlin.reflect.jvm.internal.impl.protobuf.LazyFieldLite.ensureInitialized(kotlin.reflect.jvm.internal.impl.protobuf.MessageLite):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r0 = r2.value;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        monitor-enter(r2);
        r0 = r2.value;	 Catch:{ all -> 0x0025 }
        if (r0 == 0) goto L_0x000c;	 Catch:{ all -> 0x0025 }
    L_0x000a:
        monitor-exit(r2);	 Catch:{ all -> 0x0025 }
        return;
    L_0x000c:
        r0 = r2.bytes;	 Catch:{ IOException -> 0x0023 }
        if (r0 == 0) goto L_0x0021;	 Catch:{ IOException -> 0x0023 }
    L_0x0010:
        r3 = r3.getParserForType();	 Catch:{ IOException -> 0x0023 }
        r0 = r2.bytes;	 Catch:{ IOException -> 0x0023 }
        r1 = r2.extensionRegistry;	 Catch:{ IOException -> 0x0023 }
        r3 = r3.parseFrom(r0, r1);	 Catch:{ IOException -> 0x0023 }
        r3 = (kotlin.reflect.jvm.internal.impl.protobuf.MessageLite) r3;	 Catch:{ IOException -> 0x0023 }
        r2.value = r3;	 Catch:{ IOException -> 0x0023 }
        goto L_0x0023;	 Catch:{ IOException -> 0x0023 }
    L_0x0021:
        r2.value = r3;	 Catch:{ IOException -> 0x0023 }
    L_0x0023:
        monitor-exit(r2);	 Catch:{ all -> 0x0025 }
        return;	 Catch:{ all -> 0x0025 }
    L_0x0025:
        r3 = move-exception;	 Catch:{ all -> 0x0025 }
        monitor-exit(r2);	 Catch:{ all -> 0x0025 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.protobuf.LazyFieldLite.ensureInitialized(kotlin.reflect.jvm.internal.impl.protobuf.MessageLite):void");
    }
}
