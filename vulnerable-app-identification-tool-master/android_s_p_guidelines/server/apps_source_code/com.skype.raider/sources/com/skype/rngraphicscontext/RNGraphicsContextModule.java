package com.skype.rngraphicscontext;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import com.skype.slimcore.utils.RNObjectHandleHelper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import net.hockeyapp.android.j;

public class RNGraphicsContextModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "RNGraphicsContext";
    private HashMap<Integer, RNGraphicsContextUnit> canvasList = new HashMap();
    private int numOfContextCreated = 0;
    private ag reactContext;

    public String getName() {
        return REACT_CLASS;
    }

    private RNGraphicsContextUnit getRNGraphicsContextUnit(int index) {
        if (index < this.numOfContextCreated) {
            return (RNGraphicsContextUnit) this.canvasList.get(Integer.valueOf(index));
        }
        return null;
    }

    private int putRNGraphicsContextUnit(Canvas canvas, Bitmap bmp, Paint paint, Path path) {
        int index = this.numOfContextCreated;
        this.canvasList.put(Integer.valueOf(this.numOfContextCreated), new RNGraphicsContextUnit(canvas, bmp, paint, path));
        this.numOfContextCreated++;
        return index;
    }

    private void removeRNGraphicsContextUnit(int index) {
        this.canvasList.remove(Integer.valueOf(index));
    }

    public RNGraphicsContextModule(ag reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @ReactMethod
    public void createImageContext(int width, int height, ae promise) {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        promise.a(Integer.valueOf(putRNGraphicsContextUnit(new Canvas(bmp), bmp, new Paint(), new Path())));
    }

    @ReactMethod
    public void createImageContextWithURL(String tsUrl, ae promise) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(tsUrl).openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Options opt = new Options();
            opt.inMutable = true;
            Bitmap bmp = BitmapFactory.decodeStream(input, null, opt);
            int index = putRNGraphicsContextUnit(new Canvas(bmp), bmp, new Paint(), new Path());
            Object result = new WritableNativeMap();
            result.putInt("width", bmp.getWidth());
            result.putInt("height", bmp.getHeight());
            result.putInt("nativeIndex", index);
            promise.a(result);
        } catch (Exception e) {
            FLog.e(REACT_CLASS, e.toString());
            promise.a(null);
        }
    }

    @ReactMethod
    public void destroyContext(int contextRef) {
        removeRNGraphicsContextUnit(contextRef);
    }

    @ReactMethod
    public void isContextValid(int contextRef, ae promise) {
        promise.a(Boolean.valueOf(getRNGraphicsContextUnit(contextRef) != null));
    }

    @ReactMethod
    public void saveContext(int contextRef) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            context.a.save();
        }
    }

    @ReactMethod
    public void restoreContext(int contextRef) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            context.a.restore();
        }
    }

    @ReactMethod
    public void createFileData(int contextRef, String filename, ae promise) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                File file = File.createTempFile("snapshot", ".jpeg", this.reactContext.getCacheDir());
                context.b.compress(CompressFormat.JPEG, 90, new BufferedOutputStream(new FileOutputStream(file)));
                Object result = new WritableNativeMap();
                result.putString(j.FRAGMENT_URL, Uri.fromFile(file).toString());
                result.putInt("width", context.b.getWidth());
                result.putInt("height", context.b.getHeight());
                result.putDouble("size", (double) file.length());
                promise.a(result);
                context.b.recycle();
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
                promise.a(null);
            }
        }
    }

    @ReactMethod
    public void createImageData(int contextRef, float sx, float sy, float sw, float sh, ae promise) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                context.a.clipRect(sx, sy, sx + sw, sy + sh);
                RNObjectHandleHelper obj = RNObjectHandleHelper.a();
                String key = obj.a(context.b);
                FLog.d(REACT_CLASS, "RNObjectHandleHelper key sets:" + obj.b().toString());
                Object result = new WritableNativeMap();
                result.putInt("width", context.b.getWidth());
                result.putInt("height", context.b.getHeight());
                result.putBoolean("mirrored", false);
                result.putInt("angle", 0);
                result.putString(PropertiesEntry.COLUMN_NAME_KEY, key);
                promise.a(result);
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
                promise.a(null);
            }
        }
    }

    @ReactMethod
    public void setFillColor(int contextRef, int red, int green, int blue, float alpha) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            context.c.setColor(Color.argb((int) (255.0f * alpha), red - 1, green - 1, blue - 1));
            context.c.setStyle(Style.FILL);
        }
    }

    @ReactMethod
    public void setStrokeColor(int contextRef, int red, int green, int blue, float alpha) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            context.c.setStyle(Style.STROKE);
            context.c.setColor(Color.argb((int) (255.0f * alpha), red - 1, green - 1, blue - 1));
        }
    }

    @ReactMethod
    public void setLineWidth(int contextRef, float width) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            context.c.setStrokeWidth(width);
        }
    }

    @ReactMethod
    public void setTranslate(int contextRef, float x, float y) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                context.a.translate(x, y);
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
            }
        }
    }

    @ReactMethod
    public void clipPath(int contextRef) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                context.a.clipPath(context.d);
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
            }
        }
    }

    @ReactMethod
    public void drawImage(int contextRef, am image, int srcX, int srcY, int srcWidth, int srcHeight, int dstX, int dstY, int dstWidth, int dstHeight) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                RNObjectHandleHelper obj = RNObjectHandleHelper.a();
                Bitmap bitmap = (Bitmap) obj.a(image.getString(PropertiesEntry.COLUMN_NAME_KEY));
                obj.b(image.getString(PropertiesEntry.COLUMN_NAME_KEY));
                FLog.d(REACT_CLASS, "RNObjectHandleHelper key sets:" + obj.b().toString());
                Rect srcRect = new Rect();
                srcRect.left = srcX;
                srcRect.right = srcX + srcWidth;
                srcRect.top = srcY;
                srcRect.bottom = srcY + srcHeight;
                Rect dstRect = new Rect();
                dstRect.left = dstX;
                dstRect.right = dstX + dstWidth;
                dstRect.top = dstY;
                dstRect.bottom = dstY + dstHeight;
                context.a.drawBitmap(bitmap, srcRect, dstRect, null);
                bitmap.recycle();
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
            }
        }
    }

    @ReactMethod
    public void drawGraphicsContext(int contextRef, int srcContextRef, am srcRect, am dstRect) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            RNGraphicsContextUnit srcContext = getRNGraphicsContextUnit(srcContextRef);
            if (srcContext != null) {
                try {
                    Rect srcRectNew = new Rect();
                    srcRectNew.left = srcRect.getMap("origin").getInt("x");
                    srcRectNew.right = srcRect.getMap("origin").getInt("x") + srcRect.getMap("size").getInt("width");
                    srcRectNew.top = srcRect.getMap("origin").getInt("y");
                    srcRectNew.bottom = srcRect.getMap("origin").getInt("y") + srcRect.getMap("size").getInt("height");
                    Rect dstRectNew = new Rect();
                    dstRectNew.left = dstRect.getMap("origin").getInt("x");
                    dstRectNew.right = dstRect.getMap("origin").getInt("x") + dstRect.getMap("size").getInt("width");
                    dstRectNew.top = dstRect.getMap("origin").getInt("y");
                    dstRectNew.bottom = dstRect.getMap("origin").getInt("y") + dstRect.getMap("size").getInt("height");
                    context.a.drawBitmap(srcContext.b, srcRectNew, dstRectNew, context.c);
                    FLog.i(REACT_CLASS, "drawGraphicsContext");
                    srcContext.b.recycle();
                } catch (Exception e) {
                    FLog.e(REACT_CLASS, e.toString());
                }
            }
        }
    }

    @ReactMethod
    public void drawText(int contextRef, String text, int xPos, int yPos, String fontName, int fontSize, boolean fontBold, int vertical, int horizontal) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                Align align;
                Typeface font;
                context.c.setTextSize((float) fontSize);
                switch (horizontal) {
                    case 1:
                        align = Align.LEFT;
                        break;
                    case 2:
                        align = Align.CENTER;
                        break;
                    case 3:
                        align = Align.RIGHT;
                        break;
                    default:
                        align = Align.LEFT;
                        break;
                }
                if ("sans-serif".equals(fontName)) {
                    font = Typeface.create(Typeface.SANS_SERIF, fontBold ? 1 : 0);
                } else {
                    font = Typeface.createFromAsset(this.reactContext.getAssets(), "fonts/" + fontName + ".ttf");
                }
                context.c.setTypeface(font);
                context.c.setTextAlign(align);
                context.c.setAntiAlias(true);
                Rect textBounds = new Rect();
                context.c.getTextBounds(text, 0, text.length(), textBounds);
                float x = (float) xPos;
                float y = (float) yPos;
                if (vertical == 2) {
                    y -= textBounds.exactCenterY();
                }
                context.a.drawText(text, x, y, context.c);
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
            }
        }
    }

    @ReactMethod
    public void drawLinearGradient(int contextRef, am point1, String color1, am point2, String color2, am rect) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                context.c.setShader(new LinearGradient((float) point1.getInt("x"), (float) point1.getInt("y"), (float) point2.getInt("x"), (float) point2.getInt("y"), Color.parseColor(color1), Color.parseColor(color2), TileMode.MIRROR));
                context.a.drawRect((float) rect.getMap("origin").getInt("x"), (float) rect.getMap("origin").getInt("y"), (float) (rect.getMap("origin").getInt("x") + rect.getMap("size").getInt("width")), (float) (rect.getMap("origin").getInt("y") + rect.getMap("size").getInt("height")), context.c);
                context.c.setAntiAlias(true);
                context.c.reset();
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
            }
        }
    }

    @ReactMethod
    public void strokeArc(int contextRef, am centerPoint, int radius, float beginAngle, float endAngle) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                RectF oval = new RectF();
                oval.set((float) (centerPoint.getInt("x") - radius), (float) (centerPoint.getInt("y") - radius), (float) (centerPoint.getInt("x") + radius), (float) (centerPoint.getInt("y") + radius));
                float sweepAngle = endAngle - beginAngle;
                if (sweepAngle < 0.0f) {
                    sweepAngle += 360.0f;
                }
                context.c.setStyle(Style.STROKE);
                context.c.setAntiAlias(true);
                context.a.drawArc(oval, beginAngle, sweepAngle, false, context.c);
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
            }
        }
    }

    @ReactMethod
    public void fillArc(int contextRef, am centerPoint, int radius, float beginAngle, float endAngle) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            try {
                RectF oval = new RectF();
                oval.set((float) (centerPoint.getInt("x") - radius), (float) (centerPoint.getInt("y") - radius), (float) (centerPoint.getInt("x") + radius), (float) (centerPoint.getInt("y") + radius));
                float sweepAngle = endAngle - beginAngle;
                if (sweepAngle < 0.0f) {
                    sweepAngle += 360.0f;
                }
                context.c.setStyle(Style.FILL);
                context.c.setAntiAlias(true);
                context.a.drawArc(oval, beginAngle, sweepAngle, true, context.c);
            } catch (Exception e) {
                FLog.e(REACT_CLASS, e.toString());
            }
        }
    }

    @ReactMethod
    public void appendArc(int contextRef, am centerPoint, int radius, float beginAngle, float endAngle) {
        RNGraphicsContextUnit context = getRNGraphicsContextUnit(contextRef);
        if (context != null) {
            RectF oval = new RectF();
            oval.set((float) (centerPoint.getInt("x") - radius), (float) (centerPoint.getInt("y") - radius), (float) (centerPoint.getInt("x") + radius), (float) (centerPoint.getInt("y") + radius));
            float sweepAngle = endAngle - beginAngle;
            if (sweepAngle < 0.0f) {
                sweepAngle += 360.0f;
            }
            context.d.addArc(oval, beginAngle, sweepAngle);
        }
    }
}
