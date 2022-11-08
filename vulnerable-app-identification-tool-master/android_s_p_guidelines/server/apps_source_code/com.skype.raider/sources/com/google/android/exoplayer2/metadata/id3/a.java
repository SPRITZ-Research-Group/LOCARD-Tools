package com.google.android.exoplayer2.metadata.id3;

import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.d;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class a implements com.google.android.exoplayer2.metadata.a {
    public static final int a = s.e("ID3");
    private final a b;

    public interface a {
        boolean a(int i, int i2, int i3, int i4, int i5);
    }

    private static final class b {
        private final int a;
        private final boolean b;
        private final int c;

        public b(int majorVersion, boolean isUnsynchronized, int framesSize) {
            this.a = majorVersion;
            this.b = isUnsynchronized;
            this.c = framesSize;
        }
    }

    public a() {
        this(null);
    }

    public a(a framePredicate) {
        this.b = framePredicate;
    }

    public final Metadata a(d inputBuffer) {
        ByteBuffer buffer = inputBuffer.b;
        return a(buffer.array(), buffer.limit());
    }

    public final Metadata a(byte[] data, int size) {
        b id3Header;
        List id3Frames = new ArrayList();
        k id3Data = new k(data, size);
        if (id3Data.b() < 10) {
            id3Header = null;
        } else if (id3Data.k() != a) {
            id3Header = null;
        } else {
            int i;
            int g = id3Data.g();
            id3Data.d(1);
            int g2 = id3Data.g();
            int s = id3Data.s();
            if (g == 2) {
                if (((g2 & 64) != 0 ? 1 : null) != null) {
                    id3Header = null;
                } else {
                    i = s;
                }
            } else if (g == 3) {
                if (((g2 & 64) != 0 ? 1 : null) != null) {
                    i = id3Data.n();
                    id3Data.d(i);
                    s -= i + 4;
                }
                i = s;
            } else if (g == 4) {
                if (((g2 & 64) != 0 ? 1 : null) != null) {
                    i = id3Data.s();
                    id3Data.d(i - 4);
                    s -= i;
                }
                if (((g2 & 16) != 0 ? 1 : null) != null) {
                    s -= 10;
                }
                i = s;
            } else {
                id3Header = null;
            }
            boolean z = g < 4 && (g2 & 128) != 0;
            id3Header = new b(g, z, i);
        }
        if (id3Header == null) {
            return null;
        }
        int startPosition = id3Data.d();
        int frameHeaderSize = id3Header.a == 2 ? 6 : 10;
        int framesSize = id3Header.c;
        if (id3Header.b) {
            framesSize = a(id3Data, id3Header.c);
        }
        id3Data.b(startPosition + framesSize);
        boolean unsignedIntFrameSizeHack = false;
        if (!a(id3Data, id3Header.a, frameHeaderSize, false)) {
            if (id3Header.a == 4 && a(id3Data, 4, frameHeaderSize, true)) {
                unsignedIntFrameSizeHack = true;
            } else {
                new StringBuilder("Failed to validate ID3 tag with majorVersion=").append(id3Header.a);
                return null;
            }
        }
        while (id3Data.b() >= frameHeaderSize) {
            Id3Frame frame = a(id3Header.a, id3Data, unsignedIntFrameSizeHack, frameHeaderSize, this.b);
            if (frame != null) {
                id3Frames.add(frame);
            }
        }
        return new Metadata(id3Frames);
    }

    private static boolean a(k id3Data, int majorVersion, int frameHeaderSize, boolean unsignedIntFrameSizeHack) {
        int startPosition = id3Data.d();
        while (id3Data.b() >= frameHeaderSize) {
            int id;
            long frameSize;
            int flags;
            if (majorVersion >= 3) {
                id = id3Data.n();
                frameSize = id3Data.l();
                flags = id3Data.h();
            } else {
                id = id3Data.k();
                frameSize = (long) id3Data.k();
                flags = 0;
            }
            if (id == 0 && frameSize == 0 && flags == 0) {
                id3Data.c(startPosition);
                return true;
            }
            if (majorVersion == 4 && !unsignedIntFrameSizeHack) {
                if ((8421504 & frameSize) != 0) {
                    id3Data.c(startPosition);
                    return false;
                }
                frameSize = (((255 & frameSize) | (((frameSize >> 8) & 255) << 7)) | (((frameSize >> 16) & 255) << 14)) | (((frameSize >> 24) & 255) << 21);
            }
            boolean hasGroupIdentifier = false;
            boolean hasDataLength = false;
            if (majorVersion == 4) {
                hasGroupIdentifier = (flags & 64) != 0;
                hasDataLength = (flags & 1) != 0;
            } else if (majorVersion == 3) {
                hasGroupIdentifier = (flags & 32) != 0;
                hasDataLength = (flags & 128) != 0;
            }
            int minimumFrameSize = 0;
            if (hasGroupIdentifier) {
                minimumFrameSize = 0 + 1;
            }
            if (hasDataLength) {
                minimumFrameSize += 4;
            }
            if (frameSize < ((long) minimumFrameSize)) {
                id3Data.c(startPosition);
                return false;
            }
            try {
                if (((long) id3Data.b()) < frameSize) {
                    return false;
                }
                id3Data.d((int) frameSize);
            } finally {
                id3Data.c(startPosition);
            }
        }
        id3Data.c(startPosition);
        return true;
    }

    private static Id3Frame a(int majorVersion, k id3Data, boolean unsignedIntFrameSizeHack, int frameHeaderSize, a framePredicate) {
        int frameSize;
        int frameId0 = id3Data.g();
        int frameId1 = id3Data.g();
        int frameId2 = id3Data.g();
        int frameId3 = majorVersion >= 3 ? id3Data.g() : 0;
        if (majorVersion == 4) {
            frameSize = id3Data.t();
            if (!unsignedIntFrameSizeHack) {
                frameSize = (((frameSize & 255) | (((frameSize >> 8) & 255) << 7)) | (((frameSize >> 16) & 255) << 14)) | (((frameSize >> 24) & 255) << 21);
            }
        } else if (majorVersion == 3) {
            frameSize = id3Data.t();
        } else {
            frameSize = id3Data.k();
        }
        int flags = majorVersion >= 3 ? id3Data.h() : 0;
        if (frameId0 == 0 && frameId1 == 0 && frameId2 == 0 && frameId3 == 0 && frameSize == 0 && flags == 0) {
            id3Data.c(id3Data.c());
            return null;
        }
        int nextFramePosition = id3Data.d() + frameSize;
        if (nextFramePosition > id3Data.c()) {
            id3Data.c(id3Data.c());
            return null;
        } else if (framePredicate == null || framePredicate.a(majorVersion, frameId0, frameId1, frameId2, frameId3)) {
            boolean isCompressed = false;
            boolean isEncrypted = false;
            boolean isUnsynchronized = false;
            boolean hasDataLength = false;
            boolean hasGroupIdentifier = false;
            if (majorVersion == 3) {
                isCompressed = (flags & 128) != 0;
                isEncrypted = (flags & 64) != 0;
                if ((flags & 32) != 0) {
                    hasGroupIdentifier = true;
                } else {
                    hasGroupIdentifier = false;
                }
                hasDataLength = isCompressed;
            } else if (majorVersion == 4) {
                hasGroupIdentifier = (flags & 64) != 0;
                isCompressed = (flags & 8) != 0;
                isEncrypted = (flags & 4) != 0;
                isUnsynchronized = (flags & 2) != 0;
                hasDataLength = (flags & 1) != 0;
            }
            if (isCompressed || isEncrypted) {
                id3Data.c(nextFramePosition);
                return null;
            }
            Id3Frame frame;
            if (hasGroupIdentifier) {
                frameSize--;
                id3Data.d(1);
            }
            if (hasDataLength) {
                frameSize -= 4;
                id3Data.d(4);
            }
            if (isUnsynchronized) {
                frameSize = a(id3Data, frameSize);
            }
            int g;
            String a;
            byte[] bArr;
            int a2;
            String str;
            String str2;
            String id;
            int b;
            byte[] bArr2;
            int a3;
            String a4;
            if (frameId0 == 84 && frameId1 == 88 && frameId2 == 88 && (majorVersion == 2 || frameId3 == 88)) {
                if (frameSize <= 0) {
                    frame = null;
                } else {
                    g = id3Data.g();
                    a = a(g);
                    bArr = new byte[(frameSize - 1)];
                    id3Data.a(bArr, 0, frameSize - 1);
                    a2 = a(bArr, 0, g);
                    str = new String(bArr, 0, a2, a);
                    a2 += b(g);
                    if (a2 < bArr.length) {
                        str2 = new String(bArr, a2, a(bArr, a2, g) - a2, a);
                    } else {
                        str2 = "";
                    }
                    frame = new TextInformationFrame("TXXX", str, str2);
                }
            } else if (frameId0 == 84) {
                id = a(majorVersion, frameId0, frameId1, frameId2, frameId3);
                if (frameSize <= 0) {
                    frame = null;
                } else {
                    g = id3Data.g();
                    a = a(g);
                    bArr = new byte[(frameSize - 1)];
                    id3Data.a(bArr, 0, frameSize - 1);
                    frame = new TextInformationFrame(id, null, new String(bArr, 0, a(bArr, 0, g), a));
                }
            } else if (frameId0 == 87 && frameId1 == 88 && frameId2 == 88 && (majorVersion == 2 || frameId3 == 88)) {
                if (frameSize <= 0) {
                    frame = null;
                } else {
                    g = id3Data.g();
                    a = a(g);
                    bArr = new byte[(frameSize - 1)];
                    id3Data.a(bArr, 0, frameSize - 1);
                    a2 = a(bArr, 0, g);
                    str = new String(bArr, 0, a2, a);
                    b = a2 + b(g);
                    if (b < bArr.length) {
                        str2 = new String(bArr, b, b(bArr, b) - b, "ISO-8859-1");
                    } else {
                        str2 = "";
                    }
                    frame = new UrlLinkFrame("WXXX", str, str2);
                }
            } else if (frameId0 == 87) {
                id = a(majorVersion, frameId0, frameId1, frameId2, frameId3);
                bArr2 = new byte[frameSize];
                id3Data.a(bArr2, 0, frameSize);
                frame = new UrlLinkFrame(id, null, new String(bArr2, 0, b(bArr2, 0), "ISO-8859-1"));
            } else if (frameId0 == 80 && frameId1 == 82 && frameId2 == 73 && frameId3 == 86) {
                bArr2 = new byte[frameSize];
                id3Data.a(bArr2, 0, frameSize);
                b = b(bArr2, 0);
                String str3 = new String(bArr2, 0, b, "ISO-8859-1");
                b++;
                if (b < bArr2.length) {
                    bArr2 = Arrays.copyOfRange(bArr2, b, bArr2.length);
                } else {
                    bArr2 = new byte[0];
                }
                frame = new PrivFrame(str3, bArr2);
            } else if (frameId0 == 71 && frameId1 == 69 && frameId2 == 79 && (frameId3 == 66 || majorVersion == 2)) {
                g = id3Data.g();
                a = a(g);
                bArr = new byte[(frameSize - 1)];
                id3Data.a(bArr, 0, frameSize - 1);
                a2 = b(bArr, 0);
                str = new String(bArr, 0, a2, "ISO-8859-1");
                a2++;
                a3 = a(bArr, a2, g);
                String str4 = new String(bArr, a2, a3 - a2, a);
                a2 = b(g) + a3;
                a3 = a(bArr, a2, g);
                frame = new GeobFrame(str, str4, new String(bArr, a2, a3 - a2, a), Arrays.copyOfRange(bArr, b(g) + a3, bArr.length));
            } else if (!majorVersion != 2 ? !(frameId0 == 80 && frameId1 == 73 && frameId2 == 67) : !(frameId0 == 65 && frameId1 == 80 && frameId2 == 73 && frameId3 == 67)) {
                int g2 = id3Data.g();
                a4 = a(g2);
                byte[] bArr3 = new byte[(frameSize - 1)];
                id3Data.a(bArr3, 0, frameSize - 1);
                if (majorVersion == 2) {
                    g = 2;
                    a = "image/" + s.d(new String(bArr3, 0, 3, "ISO-8859-1"));
                    if (a.equals("image/jpg")) {
                        a = "image/jpeg";
                    }
                } else {
                    g = b(bArr3, 0);
                    a = s.d(new String(bArr3, 0, g, "ISO-8859-1"));
                    if (a.indexOf(47) == -1) {
                        a = "image/" + a;
                    }
                }
                a3 = bArr3[g + 1] & 255;
                g += 2;
                int a5 = a(bArr3, g, g2);
                frame = new ApicFrame(a, new String(bArr3, g, a5 - g, a4), a3, Arrays.copyOfRange(bArr3, b(g2) + a5, bArr3.length));
            } else if (frameId0 == 67 && frameId1 == 79 && frameId2 == 77 && (frameId3 == 77 || majorVersion == 2)) {
                if (frameSize < 4) {
                    frame = null;
                } else {
                    g = id3Data.g();
                    a = a(g);
                    bArr = new byte[3];
                    id3Data.a(bArr, 0, 3);
                    a4 = new String(bArr, 0, 3);
                    bArr = new byte[(frameSize - 4)];
                    id3Data.a(bArr, 0, frameSize - 4);
                    int a6 = a(bArr, 0, g);
                    String str5 = new String(bArr, 0, a6, a);
                    a6 += b(g);
                    if (a6 < bArr.length) {
                        str2 = new String(bArr, a6, a(bArr, a6, g) - a6, a);
                    } else {
                        str2 = "";
                    }
                    frame = new CommentFrame(a4, str5, str2);
                }
            } else if (frameId0 == 67 && frameId1 == 72 && frameId2 == 65 && frameId3 == 80) {
                frame = a(id3Data, frameSize, majorVersion, unsignedIntFrameSizeHack, frameHeaderSize, framePredicate);
            } else if (frameId0 == 67 && frameId1 == 84 && frameId2 == 79 && frameId3 == 67) {
                frame = b(id3Data, frameSize, majorVersion, unsignedIntFrameSizeHack, frameHeaderSize, framePredicate);
            } else {
                id = a(majorVersion, frameId0, frameId1, frameId2, frameId3);
                bArr2 = new byte[frameSize];
                id3Data.a(bArr2, 0, frameSize);
                frame = new BinaryFrame(id, bArr2);
            }
            if (frame == null) {
                try {
                    new StringBuilder("Failed to decode frame: id=").append(a(majorVersion, frameId0, frameId1, frameId2, frameId3)).append(", frameSize=").append(frameSize);
                } catch (UnsupportedEncodingException e) {
                    id3Data.c(nextFramePosition);
                    return null;
                } catch (Throwable th) {
                    id3Data.c(nextFramePosition);
                }
            }
            id3Data.c(nextFramePosition);
            return frame;
        } else {
            id3Data.c(nextFramePosition);
            return null;
        }
    }

    private static ChapterFrame a(k id3Data, int frameSize, int majorVersion, boolean unsignedIntFrameSizeHack, int frameHeaderSize, a framePredicate) throws UnsupportedEncodingException {
        int framePosition = id3Data.d();
        int chapterIdEndIndex = b(id3Data.a, framePosition);
        String chapterId = new String(id3Data.a, framePosition, chapterIdEndIndex - framePosition, "ISO-8859-1");
        id3Data.c(chapterIdEndIndex + 1);
        int startTime = id3Data.n();
        int endTime = id3Data.n();
        long startOffset = id3Data.l();
        if (startOffset == 4294967295L) {
            startOffset = -1;
        }
        long endOffset = id3Data.l();
        if (endOffset == 4294967295L) {
            endOffset = -1;
        }
        ArrayList<Id3Frame> subFrames = new ArrayList();
        int limit = framePosition + frameSize;
        while (id3Data.d() < limit) {
            Id3Frame frame = a(majorVersion, id3Data, unsignedIntFrameSizeHack, frameHeaderSize, framePredicate);
            if (frame != null) {
                subFrames.add(frame);
            }
        }
        Id3Frame[] subFrameArray = new Id3Frame[subFrames.size()];
        subFrames.toArray(subFrameArray);
        return new ChapterFrame(chapterId, startTime, endTime, startOffset, endOffset, subFrameArray);
    }

    private static ChapterTocFrame b(k id3Data, int frameSize, int majorVersion, boolean unsignedIntFrameSizeHack, int frameHeaderSize, a framePredicate) throws UnsupportedEncodingException {
        int framePosition = id3Data.d();
        int elementIdEndIndex = b(id3Data.a, framePosition);
        String elementId = new String(id3Data.a, framePosition, elementIdEndIndex - framePosition, "ISO-8859-1");
        id3Data.c(elementIdEndIndex + 1);
        int ctocFlags = id3Data.g();
        boolean isRoot = (ctocFlags & 2) != 0;
        boolean isOrdered = (ctocFlags & 1) != 0;
        int childCount = id3Data.g();
        String[] children = new String[childCount];
        for (int i = 0; i < childCount; i++) {
            int startIndex = id3Data.d();
            int endIndex = b(id3Data.a, startIndex);
            children[i] = new String(id3Data.a, startIndex, endIndex - startIndex, "ISO-8859-1");
            id3Data.c(endIndex + 1);
        }
        ArrayList<Id3Frame> subFrames = new ArrayList();
        int limit = framePosition + frameSize;
        while (id3Data.d() < limit) {
            Id3Frame frame = a(majorVersion, id3Data, unsignedIntFrameSizeHack, frameHeaderSize, framePredicate);
            if (frame != null) {
                subFrames.add(frame);
            }
        }
        Id3Frame[] subFrameArray = new Id3Frame[subFrames.size()];
        subFrames.toArray(subFrameArray);
        return new ChapterTocFrame(elementId, isRoot, isOrdered, children, subFrameArray);
    }

    private static int a(k data, int length) {
        byte[] bytes = data.a;
        int i = data.d();
        while (i + 1 < length) {
            if ((bytes[i] & 255) == 255 && bytes[i + 1] == (byte) 0) {
                System.arraycopy(bytes, i + 2, bytes, i + 1, (length - i) - 2);
                length--;
            }
            i++;
        }
        return length;
    }

    private static String a(int encodingByte) {
        switch (encodingByte) {
            case 0:
                return "ISO-8859-1";
            case 1:
                return "UTF-16";
            case 2:
                return "UTF-16BE";
            case 3:
                return Constants.ENCODING;
            default:
                return "ISO-8859-1";
        }
    }

    private static String a(int majorVersion, int frameId0, int frameId1, int frameId2, int frameId3) {
        if (majorVersion == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(frameId0), Integer.valueOf(frameId1), Integer.valueOf(frameId2)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(frameId0), Integer.valueOf(frameId1), Integer.valueOf(frameId2), Integer.valueOf(frameId3)});
    }

    private static int a(byte[] data, int fromIndex, int encoding) {
        int terminationPos = b(data, fromIndex);
        if (encoding == 0 || encoding == 3) {
            return terminationPos;
        }
        while (terminationPos < data.length - 1) {
            if (terminationPos % 2 == 0 && data[terminationPos + 1] == (byte) 0) {
                return terminationPos;
            }
            terminationPos = b(data, terminationPos + 1);
        }
        return data.length;
    }

    private static int b(byte[] data, int fromIndex) {
        for (int i = fromIndex; i < data.length; i++) {
            if (data[i] == (byte) 0) {
                return i;
            }
        }
        return data.length;
    }

    private static int b(int encodingByte) {
        return (encodingByte == 0 || encodingByte == 3) ? 1 : 2;
    }
}
