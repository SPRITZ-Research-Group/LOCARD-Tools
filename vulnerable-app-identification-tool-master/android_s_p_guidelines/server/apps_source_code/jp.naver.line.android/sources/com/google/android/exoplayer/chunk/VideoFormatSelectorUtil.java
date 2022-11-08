package com.google.android.exoplayer.chunk;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer.MediaCodecUtil;
import com.google.android.exoplayer.MediaCodecUtil.DecoderQueryException;
import com.google.android.exoplayer.util.Util;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import com.linecorp.yuki.sensetime.SenseTimeSlam;
import java.util.ArrayList;
import java.util.List;

public final class VideoFormatSelectorUtil {
    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;

    public static int[] selectVideoFormatsForDefaultDisplay(Context context, List<? extends FormatWrapper> list, String[] strArr, boolean z) throws DecoderQueryException {
        Point displaySize = getDisplaySize(((WindowManager) context.getSystemService("window")).getDefaultDisplay());
        return selectVideoFormats(list, strArr, z, true, displaySize.x, displaySize.y);
    }

    public static int[] selectVideoFormats(List<? extends FormatWrapper> list, String[] strArr, boolean z, boolean z2, int i, int i2) throws DecoderQueryException {
        List<? extends FormatWrapper> list2 = list;
        List arrayList = new ArrayList();
        int maxH264DecodableFrameSize = MediaCodecUtil.maxH264DecodableFrameSize();
        int size = list.size();
        int i3 = BaseClientBuilder.API_PRIORITY_OTHER;
        for (int i4 = 0; i4 < size; i4++) {
            Format format = ((FormatWrapper) list2.get(i4)).getFormat();
            if (isFormatPlayable(format, strArr, z, maxH264DecodableFrameSize)) {
                arrayList.add(Integer.valueOf(i4));
                if (format.width > 0 && format.height > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z2, i, i2, format.width, format.height);
                    int i5 = format.width * format.height;
                    if (format.width >= ((int) (((float) maxVideoSizeInViewport.x) * FRACTION_TO_CONSIDER_FULLSCREEN)) && format.height >= ((int) (((float) maxVideoSizeInViewport.y) * FRACTION_TO_CONSIDER_FULLSCREEN)) && i5 < i3) {
                        i3 = i5;
                    }
                }
            }
            boolean z3 = z2;
            int i6 = i;
            int i7 = i2;
        }
        for (maxH264DecodableFrameSize = arrayList.size() - 1; maxH264DecodableFrameSize >= 0; maxH264DecodableFrameSize--) {
            Format format2 = ((FormatWrapper) list2.get(maxH264DecodableFrameSize)).getFormat();
            if (format2.width > 0 && format2.height > 0 && format2.width * format2.height > i3) {
                arrayList.remove(maxH264DecodableFrameSize);
            }
        }
        return Util.toArray(arrayList);
    }

    private static boolean isFormatPlayable(Format format, String[] strArr, boolean z, int i) {
        if (strArr != null && !Util.contains(strArr, format.mimeType)) {
            return false;
        }
        if (z && (format.width >= SenseTimeSlam.MAX_PREVIEW_WIDTH_UPPER_S || format.height >= 720)) {
            return false;
        }
        if (format.width <= 0 || format.height <= 0 || format.width * format.height <= i) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static Point getMaxVideoSizeInViewport(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            Object obj = null;
            Object obj2 = i3 > i4 ? 1 : null;
            if (i > i2) {
                obj = 1;
            }
        }
        int i5 = i2;
        i2 = i;
        i = i5;
        int i6 = i3 * i;
        int i7 = i4 * i2;
        if (i6 >= i7) {
            return new Point(i2, Util.ceilDivide(i7, i3));
        }
        return new Point(Util.ceilDivide(i6, i4), i);
    }

    private static Point getDisplaySize(Display display) {
        Point point = new Point();
        if (Util.SDK_INT >= 17) {
            getDisplaySizeV17(display, point);
        } else if (Util.SDK_INT >= 16) {
            getDisplaySizeV16(display, point);
        } else {
            getDisplaySizeV9(display, point);
        }
        return point;
    }

    @TargetApi(17)
    private static void getDisplaySizeV17(Display display, Point point) {
        display.getRealSize(point);
    }

    @TargetApi(16)
    private static void getDisplaySizeV16(Display display, Point point) {
        display.getSize(point);
    }

    private static void getDisplaySizeV9(Display display, Point point) {
        point.x = display.getWidth();
        point.y = display.getHeight();
    }

    private VideoFormatSelectorUtil() {
    }
}
