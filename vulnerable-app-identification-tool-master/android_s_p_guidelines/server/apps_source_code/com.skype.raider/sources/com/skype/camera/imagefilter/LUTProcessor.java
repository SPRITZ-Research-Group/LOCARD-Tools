package com.skype.camera.imagefilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsic3DLUT;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class LUTProcessor {
    private static LUTProcessor a;
    private HashMap<String, SoftReference<int[]>> b = new HashMap();
    private Context c;
    private RenderScript d;
    private Uri e;
    private WeakReference<Bitmap> f = new WeakReference(null);
    private EventReporter g;

    /* renamed from: com.skype.camera.imagefilter.LUTProcessor$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Config.values().length];

        static {
            try {
                a[Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Config.ARGB_4444.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Config.RGB_565.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Config.ARGB_8888.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private class a extends AsyncTask<Void, Void, Bitmap> {
        final /* synthetic */ LUTProcessor a;
        private String b;
        private WeakReference<ImageFilterView> c;
        private long d;

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            Bitmap bitmap = (Bitmap) obj;
            if (bitmap != null) {
                long currentTimeMillis = System.currentTimeMillis() - this.d;
                new StringBuilder("Filter ").append(this.b).append(" rendered in ").append(currentTimeMillis).append("ms");
                ImageFilterView imageFilterView = (ImageFilterView) this.c.get();
                if (imageFilterView != null) {
                    imageFilterView.setImageBitmap(bitmap);
                    a("ColorFilters.RenderTime", String.valueOf(currentTimeMillis));
                    return;
                }
                new StringBuilder("Imageview reference expired, rendered bitmap for filter ").append(this.b).append(" ignored");
                a("ColorFilters.Failure", "Warning_ImageViewReferenceExpired");
                return;
            }
            new StringBuilder("Failed to load image for filter ").append(this.b).append(".png");
        }

        public a(LUTProcessor lUTProcessor, String filter, ImageFilterView view) {
            this.a = lUTProcessor;
            this.b = filter;
            this.c = new WeakReference(view);
        }

        protected final void onPreExecute() {
            super.onPreExecute();
            this.d = System.currentTimeMillis();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Bitmap a() {
            Throwable e;
            ScriptIntrinsic3DLUT scriptLut = ScriptIntrinsic3DLUT.create(this.a.d, Element.U8_4(this.a.d));
            int rotationScaleFactor = 1;
            synchronized (this.a.e) {
                Bitmap srcBitmap;
                if (this.a.f.get() == null) {
                    try {
                        Options options = new Options();
                        options.inJustDecodeBounds = true;
                        InputStream is = this.a.c.getContentResolver().openInputStream(this.a.e);
                        BitmapFactory.decodeStream(is, null, options);
                        if (is != null) {
                            is.close();
                        }
                        int inSampleSize = a(options);
                        options = new Options();
                        options.inSampleSize = inSampleSize;
                        is = this.a.c.getContentResolver().openInputStream(this.a.e);
                        srcBitmap = BitmapFactory.decodeStream(is, null, options);
                        if (is != null) {
                            is.close();
                        }
                        int orientation = new ExifInterface(this.a.e.getPath()).getAttributeInt("Orientation", 0);
                        if (orientation != 0) {
                            int rotation = 0;
                            switch (orientation) {
                                case 3:
                                    rotation = 180;
                                    break;
                                case 6:
                                    rotation = 90;
                                    break;
                                case 8:
                                    rotation = 270;
                                    break;
                            }
                            if (rotation != 0) {
                                int i;
                                new StringBuilder("Current source image orientation: ").append(orientation).append(", rotating by ").append(rotation);
                                Matrix matrix = new Matrix();
                                matrix.postRotate((float) rotation);
                                long width = ((long) srcBitmap.getWidth()) * ((long) srcBitmap.getHeight());
                                switch (AnonymousClass1.a[srcBitmap.getConfig().ordinal()]) {
                                    case 1:
                                        i = 1;
                                        break;
                                    case 2:
                                    case 3:
                                        i = 2;
                                        break;
                                    default:
                                        i = 4;
                                        break;
                                }
                                long requiredMemory = width * ((long) i);
                                long freeMemory = Runtime.getRuntime().freeMemory();
                                if (requiredMemory > freeMemory) {
                                    rotationScaleFactor = (int) Math.pow(2.0d, (double) (64 - Long.numberOfLeadingZeros((((requiredMemory - 1) / freeMemory) + 1) - 1)));
                                } else {
                                    rotationScaleFactor = 1;
                                }
                                if (rotationScaleFactor > 8) {
                                    throw new Exception("Rotation scale factor is bigger than output image max scale factor");
                                }
                                Bitmap rotated = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth() / rotationScaleFactor, srcBitmap.getHeight() / rotationScaleFactor, matrix, true);
                                if (rotated != srcBitmap) {
                                    srcBitmap.recycle();
                                }
                                srcBitmap = rotated;
                            }
                        }
                        this.a.f = new WeakReference(srcBitmap);
                        new StringBuilder("Source image loaded: ").append(srcBitmap.getWidth()).append("x").append(srcBitmap.getHeight()).append(" sampleSize: ").append(options.inSampleSize);
                    } catch (Throwable e2) {
                        e = e2;
                    } catch (Throwable e22) {
                        e = e22;
                    } catch (Throwable th) {
                        r6.addSuppressed(th);
                    }
                } else {
                    srcBitmap = (Bitmap) this.a.f.get();
                }
            }
            new StringBuilder("Unable to load source image: ").append(this.a.e.toString());
            e.printStackTrace();
            a("ColorFilters.Failure", "UnableToLoadSrcImage");
            return null;
        }

        private int[] b() throws IOException, IllegalArgumentException, OutOfMemoryError {
            Bitmap lutBitmap = BitmapFactory.decodeStream(this.a.c.getAssets().open("resources/filters/" + this.b + ".png"));
            int lutWidth = lutBitmap.getWidth();
            int lutHeight = lutBitmap.getHeight();
            if (lutWidth == lutHeight && lutWidth == 512) {
                int[] lutPixels = new int[(lutWidth * lutHeight)];
                lutBitmap.getPixels(lutPixels, 0, lutWidth, 0, 0, lutWidth, lutHeight);
                int[] lut = new int[(lutWidth * lutHeight)];
                int i = 0;
                int gridSize = lutWidth / 64;
                for (int r = 0; r < 64; r++) {
                    int g = 0;
                    while (g < 64) {
                        int i2;
                        int b = 0;
                        while (true) {
                            i2 = i;
                            if (b >= 64) {
                                break;
                            }
                            i = i2 + 1;
                            lut[i2] = lutPixels[((g * lutWidth) + r) + ((((b / gridSize) * lutWidth) * 64) + ((b % gridSize) * 64))];
                            b++;
                        }
                        g++;
                        i = i2;
                    }
                }
                return lut;
            }
            throw new IllegalArgumentException();
        }

        private static int a(Options options) {
            int longEdge = Math.max(options.outHeight, options.outWidth);
            int inSampleSize = 1;
            if (longEdge > 2048) {
                while ((longEdge / 2) / inSampleSize >= 2048) {
                    inSampleSize *= 2;
                }
            }
            return inSampleSize;
        }

        private void a(String name, String payload) {
            if (this.a.g != null) {
                this.a.g.reportEvent(name, payload);
            }
        }
    }

    public static LUTProcessor a(Context context) {
        if (a == null) {
            a = new LUTProcessor(context);
        }
        return a;
    }

    private LUTProcessor(Context context) {
        this.c = context.getApplicationContext();
        this.d = RenderScript.create(context);
    }

    public final void a(Uri src, String filter, ImageFilterView view, EventReporter eventReporter) {
        this.g = eventReporter;
        if (!src.equals(this.e)) {
            this.f.clear();
            this.e = src;
        }
        new a(this, filter, view).execute(new Void[0]);
    }
}
