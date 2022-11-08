package com.airbnb.android.react.maps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ah;
import com.facebook.react.uimanager.l;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.annotation.Nullable;

public class AirMapModule extends ReactContextBaseJavaModule {
    private static final String SNAPSHOT_FORMAT_JPG = "jpg";
    private static final String SNAPSHOT_FORMAT_PNG = "png";
    private static final String SNAPSHOT_RESULT_BASE64 = "base64";
    private static final String SNAPSHOT_RESULT_FILE = "file";

    public AirMapModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "AirMapModule";
    }

    public Activity getActivity() {
        return getCurrentActivity();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    @ReactMethod
    public void animateToCoordinate(int tag, am args, ae promise) {
        am coordinate = args.getMap("latLng");
        final int duration = args.getInt("duration");
        final double lng = coordinate.getDouble("longitude");
        final double lat = coordinate.getDouble("latitude");
        final int i = tag;
        final ae aeVar = promise;
        ((UIManagerModule) getReactApplicationContext().b(UIManagerModule.class)).addUIBlock(new ah(this) {
            final /* synthetic */ AirMapModule f;

            public final void a(l nativeViewHierarchyManager) {
                AirMapView view = (AirMapView) nativeViewHierarchyManager.a(i);
                if (view == null) {
                    aeVar.a("MAP_VIEW_NULL", "AirMapView not found");
                } else if (view.a == null) {
                    aeVar.a("MAP_VIEW_NULL", "AirMapView.map is not valid");
                } else {
                    view.a(new LatLng(lat, lng), duration, aeVar);
                }
            }
        });
    }

    @ReactMethod
    public void animateToRegion(int tag, am args, ae promise) {
        am region = args.getMap("region");
        final int duration = args.getInt("duration");
        double lng = region.getDouble("longitude");
        double lat = region.getDouble("latitude");
        double lngDelta = region.getDouble("longitudeDelta");
        double latDelta = region.getDouble("latitudeDelta");
        final LatLngBounds bounds = new LatLngBounds(new LatLng(lat - (latDelta / 2.0d), lng - (lngDelta / 2.0d)), new LatLng((latDelta / 2.0d) + lat, (lngDelta / 2.0d) + lng));
        final int i = tag;
        final ae aeVar = promise;
        ((UIManagerModule) getReactApplicationContext().b(UIManagerModule.class)).addUIBlock(new ah(this) {
            final /* synthetic */ AirMapModule e;

            public final void a(l nativeViewHierarchyManager) {
                AirMapView view = (AirMapView) nativeViewHierarchyManager.a(i);
                if (view == null) {
                    aeVar.a("MAP_VIEW_NULL", "AirMapView not found");
                } else if (view.a == null) {
                    aeVar.a("MAP_VIEW_NULL", "AirMapView.map is not valid");
                } else {
                    view.a(bounds, duration, aeVar);
                }
            }
        });
    }

    @ReactMethod
    public void takeSnapshot(int tag, am options, ae promise) {
        final ag context = getReactApplicationContext();
        final String format = options.hasKey("format") ? options.getString("format") : SNAPSHOT_FORMAT_PNG;
        CompressFormat compressFormat = format.equals(SNAPSHOT_FORMAT_PNG) ? CompressFormat.PNG : format.equals(SNAPSHOT_FORMAT_JPG) ? CompressFormat.JPEG : null;
        final double quality = options.hasKey("quality") ? options.getDouble("quality") : 1.0d;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final Integer width = Integer.valueOf(options.hasKey("width") ? (int) (((double) displayMetrics.density) * options.getDouble("width")) : 0);
        final Integer height = Integer.valueOf(options.hasKey("height") ? (int) (((double) displayMetrics.density) * options.getDouble("height")) : 0);
        final String result = options.hasKey("result") ? options.getString("result") : SNAPSHOT_RESULT_FILE;
        final int i = tag;
        final ae aeVar = promise;
        ((UIManagerModule) context.b(UIManagerModule.class)).addUIBlock(new ah(this) {
            final /* synthetic */ AirMapModule j;

            public final void a(l nvhm) {
                AirMapView view = (AirMapView) nvhm.a(i);
                if (view == null) {
                    aeVar.a("AirMapView not found");
                } else if (view.a == null) {
                    aeVar.a("AirMapView.map is not valid");
                } else {
                    view.a.a(new c.l(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = this$1;
                        }

                        public final void a(@Nullable Bitmap snapshot) {
                            if (snapshot == null) {
                                aeVar.a("Failed to generate bitmap, snapshot = null");
                                return;
                            }
                            if (!(width.intValue() == 0 || height.intValue() == 0 || (width.intValue() == snapshot.getWidth() && height.intValue() == snapshot.getHeight()))) {
                                snapshot = Bitmap.createScaledBitmap(snapshot, width.intValue(), height.intValue(), true);
                            }
                            if (result.equals(AirMapModule.SNAPSHOT_RESULT_FILE)) {
                                try {
                                    File tempFile = File.createTempFile("AirMapSnapshot", "." + format, context.getCacheDir());
                                    FileOutputStream outputStream = new FileOutputStream(tempFile);
                                    snapshot.compress(compressFormat, (int) (quality * 100.0d), outputStream);
                                    AirMapModule.closeQuietly(outputStream);
                                    aeVar.a(Uri.fromFile(tempFile).toString());
                                } catch (Throwable e) {
                                    aeVar.a(e);
                                }
                            } else if (result.equals(AirMapModule.SNAPSHOT_RESULT_BASE64)) {
                                ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
                                snapshot.compress(compressFormat, (int) (quality * 100.0d), outputStream2);
                                AirMapModule.closeQuietly(outputStream2);
                                aeVar.a(Base64.encodeToString(outputStream2.toByteArray(), 2));
                            }
                        }
                    });
                }
            }
        });
    }
}
