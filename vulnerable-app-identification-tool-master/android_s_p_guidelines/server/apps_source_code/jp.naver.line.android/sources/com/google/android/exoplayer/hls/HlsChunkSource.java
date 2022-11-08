package com.google.android.exoplayer.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener;
import com.google.android.exoplayer.chunk.Chunk;
import com.google.android.exoplayer.chunk.DataChunk;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.Format.DecreasingBandwidthComparator;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.HttpDataSource.InvalidResponseCodeException;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.UriUtil;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpStatus;

public class HlsChunkSource {
    private static final String AAC_FILE_EXTENSION = ".aac";
    public static final int ADAPTIVE_MODE_ABRUPT = 3;
    public static final int ADAPTIVE_MODE_NONE = 0;
    public static final int ADAPTIVE_MODE_SPLICE = 1;
    private static final float BANDWIDTH_FRACTION = 0.8f;
    public static final long DEFAULT_MAX_BUFFER_TO_SWITCH_DOWN_MS = 20000;
    public static final long DEFAULT_MIN_BUFFER_TO_SWITCH_UP_MS = 5000;
    public static final long DEFAULT_PLAYLIST_BLACKLIST_MS = 60000;
    private static final String TAG = "HlsChunkSource";
    private final int adaptiveMode;
    private final AudioCapabilities audioCapabilities;
    private final BandwidthMeter bandwidthMeter;
    private final String baseUri;
    private final DataSource dataSource;
    private long durationUs;
    private byte[] encryptionIv;
    private String encryptionIvString;
    private byte[] encryptionKey;
    private Uri encryptionKeyUri;
    private boolean live;
    private final long maxBufferDurationToSwitchDownUs;
    private final int maxHeight;
    private final int maxWidth;
    private final long minBufferDurationToSwitchUpUs;
    private final HlsPlaylistParser playlistParser;
    private byte[] scratchSpace;
    private int selectedVariantIndex;
    private final long[] variantBlacklistTimes;
    private final long[] variantLastPlaylistLoadTimesMs;
    private final HlsMediaPlaylist[] variantPlaylists;
    private final Variant[] variants;

    public interface EventListener extends BaseChunkSampleSourceEventListener {
    }

    class EncryptionKeyChunk extends DataChunk {
        public final String iv;
        private byte[] result;
        public final int variantIndex;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, byte[] bArr, String str, int i) {
            super(dataSource, dataSpec, 3, 0, null, bArr);
            this.iv = str;
            this.variantIndex = i;
        }

        protected void consume(byte[] bArr, int i) throws IOException {
            this.result = Arrays.copyOf(bArr, i);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    class MediaPlaylistChunk extends DataChunk {
        private final HlsPlaylistParser playlistParser;
        private final String playlistUrl;
        private HlsMediaPlaylist result;
        public final int variantIndex;

        public MediaPlaylistChunk(DataSource dataSource, DataSpec dataSpec, byte[] bArr, HlsPlaylistParser hlsPlaylistParser, int i, String str) {
            super(dataSource, dataSpec, 4, 0, null, bArr);
            this.variantIndex = i;
            this.playlistParser = hlsPlaylistParser;
            this.playlistUrl = str;
        }

        protected void consume(byte[] bArr, int i) throws IOException {
            this.result = (HlsMediaPlaylist) this.playlistParser.parse(this.playlistUrl, new ByteArrayInputStream(bArr, 0, i));
        }

        public HlsMediaPlaylist getResult() {
            return this.result;
        }
    }

    public HlsChunkSource(DataSource dataSource, String str, HlsPlaylist hlsPlaylist, BandwidthMeter bandwidthMeter, int[] iArr, int i, AudioCapabilities audioCapabilities) {
        this(dataSource, str, hlsPlaylist, bandwidthMeter, iArr, i, DEFAULT_MIN_BUFFER_TO_SWITCH_UP_MS, DEFAULT_MAX_BUFFER_TO_SWITCH_DOWN_MS, audioCapabilities);
    }

    public HlsChunkSource(DataSource dataSource, String str, HlsPlaylist hlsPlaylist, BandwidthMeter bandwidthMeter, int[] iArr, int i, long j, long j2, AudioCapabilities audioCapabilities) {
        HlsPlaylist hlsPlaylist2 = hlsPlaylist;
        int i2 = i;
        this.dataSource = dataSource;
        this.bandwidthMeter = bandwidthMeter;
        this.adaptiveMode = i2;
        this.audioCapabilities = audioCapabilities;
        this.minBufferDurationToSwitchUpUs = j * 1000;
        this.maxBufferDurationToSwitchDownUs = 1000 * j2;
        this.baseUri = hlsPlaylist2.baseUri;
        this.playlistParser = new HlsPlaylistParser();
        int i3 = 0;
        int i4 = -1;
        if (hlsPlaylist2.type == 1) {
            r0.variants = new Variant[]{new Variant(0, str, 0, null, -1, -1)};
            r0.variantPlaylists = new HlsMediaPlaylist[1];
            r0.variantLastPlaylistLoadTimesMs = new long[1];
            r0.variantBlacklistTimes = new long[1];
            setMediaPlaylist(0, (HlsMediaPlaylist) hlsPlaylist2);
            r0.maxWidth = -1;
        } else {
            List list = ((HlsMasterPlaylist) hlsPlaylist2).variants;
            r0.variants = buildOrderedVariants(list, iArr);
            r0.variantPlaylists = new HlsMediaPlaylist[r0.variants.length];
            r0.variantLastPlaylistLoadTimesMs = new long[r0.variants.length];
            r0.variantBlacklistTimes = new long[r0.variants.length];
            int i5 = BaseClientBuilder.API_PRIORITY_OTHER;
            int i6 = -1;
            int i7 = -1;
            while (i3 < r0.variants.length) {
                int indexOf = list.indexOf(r0.variants[i3]);
                if (indexOf < i5) {
                    r0.selectedVariantIndex = i3;
                    i5 = indexOf;
                }
                Format format = r0.variants[i3].format;
                i6 = Math.max(format.width, i6);
                i7 = Math.max(format.height, i7);
                i3++;
            }
            if (r0.variants.length <= 1 || i2 == 0) {
                r0.maxWidth = -1;
            } else {
                if (i6 <= 0) {
                    i6 = 1920;
                }
                r0.maxWidth = i6;
                i4 = i7 > 0 ? i7 : 1080;
            }
        }
        r0.maxHeight = i4;
    }

    public long getDurationUs() {
        return this.live ? -1 : this.durationUs;
    }

    public void getMaxVideoDimensions(MediaFormat mediaFormat) {
        if (this.maxWidth != -1 && this.maxHeight != -1) {
            mediaFormat.setMaxVideoDimensions(this.maxWidth, this.maxHeight);
        }
    }

    public com.google.android.exoplayer.chunk.Chunk getChunkOperation(com.google.android.exoplayer.hls.TsChunk r27, long r28, long r30) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r1_13 com.google.android.exoplayer.hls.HlsExtractorWrapper) in PHI: PHI: (r1_15 com.google.android.exoplayer.hls.HlsExtractorWrapper) = (r1_8 com.google.android.exoplayer.hls.HlsExtractorWrapper), (r1_13 com.google.android.exoplayer.hls.HlsExtractorWrapper) binds: {(r1_8 com.google.android.exoplayer.hls.HlsExtractorWrapper)=B:71:0x0129, (r1_13 com.google.android.exoplayer.hls.HlsExtractorWrapper)=B:77:0x0147}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
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
        r26 = this;
        r0 = r26;
        r1 = r27;
        r2 = r0.adaptiveMode;
        r3 = 0;
        r4 = 1;
        if (r2 != 0) goto L_0x000d;
    L_0x000a:
        r2 = r0.selectedVariantIndex;
        goto L_0x0029;
    L_0x000d:
        r5 = r30;
        r2 = r0.getNextVariantIndex(r1, r5);
        if (r1 == 0) goto L_0x0029;
    L_0x0015:
        r5 = r0.variants;
        r5 = r5[r2];
        r5 = r5.format;
        r6 = r1.format;
        r5 = r5.equals(r6);
        if (r5 != 0) goto L_0x0029;
    L_0x0023:
        r5 = r0.adaptiveMode;
        if (r5 != r4) goto L_0x0029;
    L_0x0027:
        r11 = 1;
        goto L_0x002a;
    L_0x0029:
        r11 = 0;
    L_0x002a:
        r5 = r0.variantPlaylists;
        r5 = r5[r2];
        if (r5 != 0) goto L_0x0035;
    L_0x0030:
        r1 = r0.newMediaPlaylistChunk(r2);
        return r1;
    L_0x0035:
        r0.selectedVariantIndex = r2;
        r6 = r0.live;
        if (r6 == 0) goto L_0x0056;
    L_0x003b:
        if (r1 != 0) goto L_0x0042;
    L_0x003d:
        r6 = r0.getLiveStartChunkMediaSequence(r2);
        goto L_0x006e;
    L_0x0042:
        if (r11 == 0) goto L_0x0047;
    L_0x0044:
        r6 = r1.chunkIndex;
        goto L_0x004a;
    L_0x0047:
        r6 = r1.chunkIndex;
        r6 = r6 + r4;
    L_0x004a:
        r7 = r5.mediaSequence;
        if (r6 >= r7) goto L_0x006e;
    L_0x004e:
        r6 = r0.getLiveStartChunkMediaSequence(r2);
        r21 = r6;
        r6 = 1;
        goto L_0x0071;
    L_0x0056:
        if (r1 != 0) goto L_0x0066;
    L_0x0058:
        r6 = r5.segments;
        r7 = java.lang.Long.valueOf(r28);
        r6 = com.google.android.exoplayer.util.Util.binarySearchFloor(r6, r7, r4, r4);
        r7 = r5.mediaSequence;
        r6 = r6 + r7;
        goto L_0x006e;
    L_0x0066:
        if (r11 == 0) goto L_0x006b;
    L_0x0068:
        r6 = r1.chunkIndex;
        goto L_0x006e;
    L_0x006b:
        r6 = r1.chunkIndex;
        r6 = r6 + r4;
    L_0x006e:
        r21 = r6;
        r6 = 0;
    L_0x0071:
        r7 = r5.mediaSequence;
        r7 = r21 - r7;
        r8 = r5.segments;
        r8 = r8.size();
        if (r7 < r8) goto L_0x008e;
    L_0x007d:
        r1 = r5.live;
        if (r1 == 0) goto L_0x008c;
    L_0x0081:
        r1 = r0.shouldRerequestMediaPlaylist(r2);
        if (r1 == 0) goto L_0x008c;
    L_0x0087:
        r1 = r0.newMediaPlaylistChunk(r2);
        return r1;
    L_0x008c:
        r1 = 0;
        return r1;
    L_0x008e:
        r2 = r5.segments;
        r2 = r2.get(r7);
        r2 = (com.google.android.exoplayer.hls.HlsMediaPlaylist.Segment) r2;
        r8 = r5.baseUri;
        r9 = r2.url;
        r8 = com.google.android.exoplayer.util.UriUtil.resolveToUri(r8, r9);
        r9 = r2.isEncrypted;
        if (r9 == 0) goto L_0x00cd;
    L_0x00a2:
        r9 = r5.baseUri;
        r10 = r2.encryptionKeyUri;
        r9 = com.google.android.exoplayer.util.UriUtil.resolveToUri(r9, r10);
        r10 = r0.encryptionKeyUri;
        r10 = r9.equals(r10);
        if (r10 != 0) goto L_0x00bb;
    L_0x00b2:
        r1 = r2.encryptionIV;
        r2 = r0.selectedVariantIndex;
        r1 = r0.newEncryptionKeyChunk(r9, r1, r2);
        return r1;
    L_0x00bb:
        r10 = r2.encryptionIV;
        r12 = r0.encryptionIvString;
        r10 = com.google.android.exoplayer.util.Util.areEqual(r10, r12);
        if (r10 != 0) goto L_0x00d0;
    L_0x00c5:
        r10 = r2.encryptionIV;
        r12 = r0.encryptionKey;
        r0.setEncryptionData(r9, r10, r12);
        goto L_0x00d0;
    L_0x00cd:
        r26.clearEncryptionData();
    L_0x00d0:
        r19 = new com.google.android.exoplayer.upstream.DataSpec;
        r9 = r2.byterangeOffset;
        r14 = (long) r9;
        r9 = r2.byterangeLength;
        r9 = (long) r9;
        r18 = 0;
        r12 = r19;
        r13 = r8;
        r16 = r9;
        r12.<init>(r13, r14, r16, r18);
        r9 = r0.live;
        if (r9 == 0) goto L_0x00f3;
    L_0x00e6:
        if (r1 != 0) goto L_0x00eb;
    L_0x00e8:
        r9 = 0;
        goto L_0x00f5;
    L_0x00eb:
        if (r11 == 0) goto L_0x00f0;
    L_0x00ed:
        r9 = r1.startTimeUs;
        goto L_0x00f5;
    L_0x00f0:
        r9 = r1.endTimeUs;
        goto L_0x00f5;
    L_0x00f3:
        r9 = r2.startTimeUs;
    L_0x00f5:
        r14 = r9;
        r9 = r2.durationSecs;
        r12 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r9 = r9 * r12;
        r9 = (long) r9;
        r22 = r14 + r9;
        r9 = r5.live;
        if (r9 != 0) goto L_0x0110;
    L_0x0106:
        r5 = r5.segments;
        r5 = r5.size();
        r5 = r5 - r4;
        if (r7 != r5) goto L_0x0110;
    L_0x010f:
        r3 = 1;
    L_0x0110:
        r4 = r0.variants;
        r5 = r0.selectedVariantIndex;
        r4 = r4[r5];
        r4 = r4.format;
        if (r1 == 0) goto L_0x012c;
    L_0x011a:
        r2 = r2.discontinuity;
        if (r2 != 0) goto L_0x012c;
    L_0x011e:
        r2 = r1.format;
        r2 = r4.equals(r2);
        if (r2 == 0) goto L_0x012c;
    L_0x0126:
        if (r6 == 0) goto L_0x0129;
    L_0x0128:
        goto L_0x012c;
    L_0x0129:
        r1 = r1.extractorWrapper;
        goto L_0x0150;
    L_0x012c:
        r1 = r8.getLastPathSegment();
        r2 = ".aac";
        r1 = r1.endsWith(r2);
        if (r1 == 0) goto L_0x013f;
    L_0x0138:
        r1 = new com.google.android.exoplayer.extractor.ts.AdtsExtractor;
        r1.<init>(r14);
    L_0x013d:
        r10 = r1;
        goto L_0x0147;
    L_0x013f:
        r1 = new com.google.android.exoplayer.extractor.ts.TsExtractor;
        r2 = r0.audioCapabilities;
        r1.<init>(r14, r2);
        goto L_0x013d;
    L_0x0147:
        r1 = new com.google.android.exoplayer.hls.HlsExtractorWrapper;
        r6 = 0;
        r5 = r1;
        r7 = r4;
        r8 = r14;
        r5.<init>(r6, r7, r8, r10, r11);
    L_0x0150:
        r2 = new com.google.android.exoplayer.hls.TsChunk;
        r13 = r0.dataSource;
        r5 = 0;
        r6 = r0.encryptionKey;
        r7 = r0.encryptionIv;
        r12 = r2;
        r9 = r14;
        r14 = r19;
        r15 = r5;
        r16 = r4;
        r17 = r9;
        r19 = r22;
        r22 = r3;
        r23 = r1;
        r24 = r6;
        r25 = r7;
        r12.<init>(r13, r14, r15, r16, r17, r19, r21, r22, r23, r24, r25);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.hls.HlsChunkSource.getChunkOperation(com.google.android.exoplayer.hls.TsChunk, long, long):com.google.android.exoplayer.chunk.Chunk");
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof MediaPlaylistChunk) {
            MediaPlaylistChunk mediaPlaylistChunk = (MediaPlaylistChunk) chunk;
            this.scratchSpace = mediaPlaylistChunk.getDataHolder();
            setMediaPlaylist(mediaPlaylistChunk.variantIndex, mediaPlaylistChunk.getResult());
            return;
        }
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            setEncryptionData(encryptionKeyChunk.dataSpec.uri, encryptionKeyChunk.iv, encryptionKeyChunk.getResult());
        }
    }

    public boolean onChunkLoadError(Chunk chunk, IOException iOException) {
        if (chunk.bytesLoaded() == 0) {
            boolean z = chunk instanceof TsChunk;
            if ((z || (chunk instanceof MediaPlaylistChunk) || (chunk instanceof EncryptionKeyChunk)) && (iOException instanceof InvalidResponseCodeException)) {
                int i = ((InvalidResponseCodeException) iOException).responseCode;
                if (i == 404 || i == HttpStatus.SC_GONE) {
                    int variantIndex;
                    if (z) {
                        variantIndex = getVariantIndex(((TsChunk) chunk).format);
                    } else if (chunk instanceof MediaPlaylistChunk) {
                        variantIndex = ((MediaPlaylistChunk) chunk).variantIndex;
                    } else {
                        variantIndex = ((EncryptionKeyChunk) chunk).variantIndex;
                    }
                    Object obj = this.variantBlacklistTimes[variantIndex] != 0 ? 1 : null;
                    this.variantBlacklistTimes[variantIndex] = SystemClock.elapsedRealtime();
                    String str;
                    if (obj != null) {
                        str = TAG;
                        StringBuilder stringBuilder = new StringBuilder("Already blacklisted variant (");
                        stringBuilder.append(i);
                        stringBuilder.append("): ");
                        stringBuilder.append(chunk.dataSpec.uri);
                        Log.w(str, stringBuilder.toString());
                        return false;
                    } else if (allVariantsBlacklisted()) {
                        String str2 = TAG;
                        StringBuilder stringBuilder2 = new StringBuilder("Final variant not blacklisted (");
                        stringBuilder2.append(i);
                        stringBuilder2.append("): ");
                        stringBuilder2.append(chunk.dataSpec.uri);
                        Log.w(str2, stringBuilder2.toString());
                        this.variantBlacklistTimes[variantIndex] = 0;
                        return false;
                    } else {
                        str = TAG;
                        StringBuilder stringBuilder3 = new StringBuilder("Blacklisted variant (");
                        stringBuilder3.append(i);
                        stringBuilder3.append("): ");
                        stringBuilder3.append(chunk.dataSpec.uri);
                        Log.w(str, stringBuilder3.toString());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int getNextVariantIndex(TsChunk tsChunk, long j) {
        clearStaleBlacklistedVariants();
        long bitrateEstimate = this.bandwidthMeter.getBitrateEstimate();
        if (this.variantBlacklistTimes[this.selectedVariantIndex] != 0) {
            return getVariantIndexForBandwidth(bitrateEstimate);
        }
        if (tsChunk == null) {
            return this.selectedVariantIndex;
        }
        if (bitrateEstimate == -1) {
            return this.selectedVariantIndex;
        }
        int variantIndexForBandwidth = getVariantIndexForBandwidth(bitrateEstimate);
        if (variantIndexForBandwidth == this.selectedVariantIndex) {
            return this.selectedVariantIndex;
        }
        long j2 = (this.adaptiveMode == 1 ? tsChunk.startTimeUs : tsChunk.endTimeUs) - j;
        return (this.variantBlacklistTimes[this.selectedVariantIndex] != 0 || ((variantIndexForBandwidth > this.selectedVariantIndex && j2 < this.maxBufferDurationToSwitchDownUs) || (variantIndexForBandwidth < this.selectedVariantIndex && j2 > this.minBufferDurationToSwitchUpUs))) ? variantIndexForBandwidth : this.selectedVariantIndex;
    }

    private int getVariantIndexForBandwidth(long j) {
        if (j == -1) {
            j = 0;
        }
        int i = (int) (((float) j) * 0.8f);
        boolean z = false;
        int i2 = -1;
        for (int i3 = 0; i3 < this.variants.length; i3++) {
            if (this.variantBlacklistTimes[i3] == 0) {
                if (this.variants[i3].format.bitrate <= i) {
                    return i3;
                }
                i2 = i3;
            }
        }
        if (i2 != -1) {
            z = true;
        }
        Assertions.checkState(z);
        return i2;
    }

    private boolean shouldRerequestMediaPlaylist(int i) {
        return SystemClock.elapsedRealtime() - this.variantLastPlaylistLoadTimesMs[i] >= ((long) ((this.variantPlaylists[i].targetDurationSecs * 1000) / 2));
    }

    private int getLiveStartChunkMediaSequence(int i) {
        HlsMediaPlaylist hlsMediaPlaylist = this.variantPlaylists[i];
        return (hlsMediaPlaylist.segments.size() > 3 ? hlsMediaPlaylist.segments.size() - 3 : 0) + hlsMediaPlaylist.mediaSequence;
    }

    private MediaPlaylistChunk newMediaPlaylistChunk(int i) {
        Uri resolveToUri = UriUtil.resolveToUri(this.baseUri, this.variants[i].url);
        return new MediaPlaylistChunk(this.dataSource, new DataSpec(resolveToUri, 0, -1, null, 1), this.scratchSpace, this.playlistParser, i, resolveToUri.toString());
    }

    private EncryptionKeyChunk newEncryptionKeyChunk(Uri uri, String str, int i) {
        return new EncryptionKeyChunk(this.dataSource, new DataSpec(uri, 0, -1, null, 1), this.scratchSpace, str, i);
    }

    private void setEncryptionData(Uri uri, String str, byte[] bArr) {
        Object toByteArray = new BigInteger(str.toLowerCase(Locale.getDefault()).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        Object obj = new byte[16];
        int length = toByteArray.length > 16 ? toByteArray.length - 16 : 0;
        System.arraycopy(toByteArray, length, obj, (16 - toByteArray.length) + length, toByteArray.length - length);
        this.encryptionKeyUri = uri;
        this.encryptionKey = bArr;
        this.encryptionIvString = str;
        this.encryptionIv = obj;
    }

    private void clearEncryptionData() {
        this.encryptionKeyUri = null;
        this.encryptionKey = null;
        this.encryptionIvString = null;
        this.encryptionIv = null;
    }

    private void setMediaPlaylist(int i, HlsMediaPlaylist hlsMediaPlaylist) {
        this.variantLastPlaylistLoadTimesMs[i] = SystemClock.elapsedRealtime();
        this.variantPlaylists[i] = hlsMediaPlaylist;
        this.live |= hlsMediaPlaylist.live;
        this.durationUs = hlsMediaPlaylist.durationUs;
    }

    private static Variant[] buildOrderedVariants(List<Variant> list, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        if (iArr != null) {
            for (int i : iArr) {
                arrayList.add(list.get(i));
            }
        } else {
            arrayList.addAll(list);
        }
        ArrayList arrayList2 = new ArrayList();
        Collection arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Variant variant = (Variant) arrayList.get(i2);
            if (variant.format.height > 0 || variantHasExplicitCodecWithPrefix(variant, "avc")) {
                arrayList2.add(variant);
            } else if (variantHasExplicitCodecWithPrefix(variant, "mp4a")) {
                arrayList3.add(variant);
            }
        }
        if (arrayList2.isEmpty()) {
            if (arrayList3.size() < arrayList.size()) {
                arrayList.removeAll(arrayList3);
            }
            arrayList2 = arrayList;
        }
        Variant[] variantArr = new Variant[arrayList2.size()];
        arrayList2.toArray(variantArr);
        Arrays.sort(variantArr, new Comparator<Variant>() {
            private final Comparator<Format> formatComparator = new DecreasingBandwidthComparator();

            public final int compare(Variant variant, Variant variant2) {
                return this.formatComparator.compare(variant.format, variant2.format);
            }
        });
        return variantArr;
    }

    private static boolean variantHasExplicitCodecWithPrefix(Variant variant, String str) {
        Object obj = variant.format.codecs;
        if (TextUtils.isEmpty(obj)) {
            return false;
        }
        String[] split = obj.split("(\\s*,\\s*)|(\\s*$)");
        for (String startsWith : split) {
            if (startsWith.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean allVariantsBlacklisted() {
        for (long j : this.variantBlacklistTimes) {
            if (j == 0) {
                return false;
            }
        }
        return true;
    }

    private void clearStaleBlacklistedVariants() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int i = 0;
        while (i < this.variantBlacklistTimes.length) {
            if (this.variantBlacklistTimes[i] != 0 && elapsedRealtime - this.variantBlacklistTimes[i] > DEFAULT_PLAYLIST_BLACKLIST_MS) {
                this.variantBlacklistTimes[i] = 0;
            }
            i++;
        }
    }

    private int getVariantIndex(Format format) {
        for (int i = 0; i < this.variants.length; i++) {
            if (this.variants[i].format.equals(format)) {
                return i;
            }
        }
        throw new IllegalStateException("Invalid format: ".concat(String.valueOf(format)));
    }
}
