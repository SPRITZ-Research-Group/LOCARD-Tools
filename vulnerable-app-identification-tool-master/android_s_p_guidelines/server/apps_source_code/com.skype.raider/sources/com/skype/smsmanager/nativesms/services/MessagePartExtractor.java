package com.skype.smsmanager.nativesms.services;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.adjust.sdk.Constants;
import com.skype.smsmanager.mms.ContentType;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.models.MmsMediaItem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class MessagePartExtractor {
    private static final Uri a = Uri.parse("content://mms/part");
    private static final String[] b = new String[]{"_id", "text", "ct"};
    private static final String[] c = new String[]{"_id", "cl", "ct", "name"};
    private final HashSet<String> d = new HashSet<String>(this) {
        final /* synthetic */ MessagePartExtractor a;

        {
            this.a = this$0;
            a("text/plain");
        }

        public final /* synthetic */ boolean add(Object obj) {
            return a((String) obj);
        }

        private boolean a(String s) {
            return super.add(s.toLowerCase());
        }

        public final boolean contains(Object s) {
            if (s == null || !(s instanceof String)) {
                return false;
            }
            return super.contains(((String) s).toLowerCase());
        }
    };
    private final Map<String, String> e = new HashMap<String, String>(this) {
        final /* synthetic */ MessagePartExtractor a;

        {
            this.a = this$0;
            a("image/jpeg", "jpg");
            a("image/jpg", "jpg");
            a("image/gif", "gif");
            a("image/png", "png");
            a("video/mp4", "mp4");
            a("video/3gpp", "3gp");
            a("video/3gpp2", "3g2");
            a("audio/mpg", "mpa");
            a("audio/mpeg", "mpa");
            a("audio/mp3", "mp3");
            a("audio/mp4", "mp4");
            a("audio/3gpp", "3gp");
            a("text/x-vCard", "vcf");
            a("text/x-vCalendar", "ics");
        }

        public final /* synthetic */ Object put(Object obj, Object obj2) {
            return a((String) obj, (String) obj2);
        }

        private String a(String key, String value) {
            return (String) super.put(key.toLowerCase(), value);
        }

        public final /* synthetic */ Object get(Object obj) {
            if (obj == null || !(obj instanceof String)) {
                return null;
            }
            return (String) super.get(((String) obj).toLowerCase());
        }
    };
    private final ContentResolver f;
    private final File g;

    public MessagePartExtractor(@NonNull ContentResolver resolver, @NonNull File storage) {
        this.f = resolver;
        this.g = storage;
    }

    public final String a(long id) {
        Cursor cursor = this.f.query(a, b, "mid=?", new String[]{Long.toString(id)}, null);
        if (cursor == null) {
            SmsMmsLogger.c("MessagePartExtractor", "extractMessageText() no cursor for message " + id);
            return null;
        }
        StringBuilder builder = new StringBuilder();
        boolean textExists = false;
        try {
            CursorHelper helper = new CursorHelper(cursor);
            while (cursor.moveToNext()) {
                String contentType = helper.a("ct");
                if (this.d.contains(contentType)) {
                    String text = helper.a("text");
                    SmsMmsLogger.a("MessagePartExtractor", "extractMessageText() found text part. size: " + text.length());
                    builder.append(text);
                    textExists = true;
                } else {
                    SmsMmsLogger.a("MessagePartExtractor", "extractMessageText() skipping non-text part: " + contentType);
                }
            }
            if (textExists) {
                return builder.toString();
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    public final ArrayList<MmsMediaItem> b(long id) {
        Cursor cursor = this.f.query(a, c, "mid=?", new String[]{Long.toString(id)}, null);
        if (cursor == null) {
            SmsMmsLogger.c("MessagePartExtractor", "extractContentToFiles() no cursor for message " + id);
            return new ArrayList();
        }
        try {
            CursorHelper cursorHelper = new CursorHelper(cursor);
            ArrayList<MmsMediaItem> data = new ArrayList();
            while (cursor.moveToNext()) {
                String contentType = cursorHelper.a("ct");
                if (this.d.contains(contentType)) {
                    SmsMmsLogger.a("MessagePartExtractor", "extractContentToFiles() skipping text part: " + contentType);
                } else {
                    String extensionWithDot;
                    String partId;
                    File file;
                    int bytesCopied;
                    String path;
                    String filename;
                    String str = (String) this.e.get(contentType);
                    if (str == null) {
                        Object obj = (ContentType.a(contentType) || ContentType.c(contentType) || ContentType.b(contentType)) ? 1 : null;
                        if (obj == null) {
                            extensionWithDot = null;
                            if (extensionWithDot != null) {
                                SmsMmsLogger.a("MessagePartExtractor", "extractContentToFiles() skipping unrecognized part: " + contentType);
                            } else {
                                partId = cursorHelper.a("_id");
                                file = new File(this.g, UUID.randomUUID().toString() + extensionWithDot);
                                bytesCopied = a(partId, file);
                                if (bytesCopied > 0) {
                                    path = Uri.fromFile(file).toString();
                                    filename = cursorHelper.a("name");
                                    if (filename == null) {
                                        filename = cursorHelper.a("cl");
                                        if (filename == null) {
                                            filename = String.format("%d%s", new Object[]{Integer.valueOf(new Random().nextInt(Constants.ONE_SECOND) + 10000), extensionWithDot});
                                        }
                                    }
                                    if (filename.lastIndexOf(".") < 0) {
                                        filename = filename + extensionWithDot;
                                    }
                                    data.add(new MmsMediaItem(path, filename, contentType, (long) bytesCopied));
                                }
                                SmsMmsLogger.b("MessagePartExtractor", "extractContentToFiles() accepted: " + contentType + " bytes: " + bytesCopied);
                            }
                        } else {
                            str = a(cursorHelper.a("name"));
                            if (str == null) {
                                str = a(cursorHelper.a("cl"));
                            }
                        }
                    }
                    if (str != null) {
                        extensionWithDot = "." + str;
                    } else {
                        extensionWithDot = "";
                    }
                    if (extensionWithDot != null) {
                        partId = cursorHelper.a("_id");
                        file = new File(this.g, UUID.randomUUID().toString() + extensionWithDot);
                        bytesCopied = a(partId, file);
                        if (bytesCopied > 0) {
                            path = Uri.fromFile(file).toString();
                            filename = cursorHelper.a("name");
                            if (filename == null) {
                                filename = cursorHelper.a("cl");
                                if (filename == null) {
                                    filename = String.format("%d%s", new Object[]{Integer.valueOf(new Random().nextInt(Constants.ONE_SECOND) + 10000), extensionWithDot});
                                }
                            }
                            if (filename.lastIndexOf(".") < 0) {
                                filename = filename + extensionWithDot;
                            }
                            data.add(new MmsMediaItem(path, filename, contentType, (long) bytesCopied));
                        }
                        SmsMmsLogger.b("MessagePartExtractor", "extractContentToFiles() accepted: " + contentType + " bytes: " + bytesCopied);
                    } else {
                        SmsMmsLogger.a("MessagePartExtractor", "extractContentToFiles() skipping unrecognized part: " + contentType);
                    }
                }
            }
            return data;
        } finally {
            cursor.close();
        }
    }

    private int a(String partId, File file) {
        try {
            InputStream contentStream = this.f.openInputStream(Uri.withAppendedPath(a, partId));
            if (contentStream != null) {
                OutputStream fileStream;
                try {
                    fileStream = new FileOutputStream(file);
                    int a = a(contentStream, fileStream);
                    fileStream.close();
                    contentStream.close();
                    return a;
                } catch (IOException e) {
                    try {
                        SmsMmsLogger.a("MessagePartExtractor", "saveImageFromPart(" + partId + ") unable to copy content to disk.", e);
                    } finally {
                        contentStream.close();
                    }
                } catch (Throwable th) {
                    fileStream.close();
                }
            }
        } catch (IOException e2) {
            SmsMmsLogger.a("MessagePartExtractor", "saveImageFromPart(" + partId + ") open input stream failed.", e2);
        }
        return -1;
    }

    private static int a(InputStream in, OutputStream out) throws IOException {
        int total = 0;
        byte[] buffer = new byte[8192];
        while (true) {
            int len = in.read(buffer);
            if (len <= 0) {
                return total;
            }
            total += len;
            out.write(buffer, 0, len);
        }
    }

    private static String a(String file) {
        if (file == null) {
            SmsMmsLogger.a("MessagePartExtractor", "Filename was null");
            return null;
        }
        int index = file.lastIndexOf(".");
        if (index > 0) {
            return file.substring(index - 1);
        }
        return null;
    }
}
