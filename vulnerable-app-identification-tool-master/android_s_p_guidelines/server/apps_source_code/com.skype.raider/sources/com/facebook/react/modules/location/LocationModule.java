package com.facebook.react.modules.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import javax.annotation.Nullable;

@ReactModule(name = "LocationObserver")
public class LocationModule extends ReactContextBaseJavaModule {
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private final LocationListener mLocationListener = new LocationListener(this) {
        final /* synthetic */ LocationModule a;

        {
            this.a = this$0;
        }

        public final void onLocationChanged(Location location) {
            ((RCTDeviceEventEmitter) this.a.getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("geolocationDidChange", LocationModule.locationToMap(location));
        }

        public final void onStatusChanged(String provider, int status, Bundle extras) {
            if (status == 0) {
                this.a.emitError(a.b, "Provider " + provider + " is out of service.");
            } else if (status == 1) {
                this.a.emitError(a.c, "Provider " + provider + " is temporarily unavailable.");
            }
        }

        public final void onProviderEnabled(String provider) {
        }

        public final void onProviderDisabled(String provider) {
        }
    };
    @Nullable
    private String mWatchedProvider;

    private static class a {
        private final long a;
        private final double b;
        private final boolean c;
        private final float d;

        private a(long timeout, double maximumAge, boolean highAccuracy, float distanceFilter) {
            this.a = timeout;
            this.b = maximumAge;
            this.c = highAccuracy;
            this.d = distanceFilter;
        }

        static /* synthetic */ a a(am x0) {
            long j = x0.hasKey("timeout") ? (long) x0.getDouble("timeout") : Long.MAX_VALUE;
            double d = x0.hasKey("maximumAge") ? x0.getDouble("maximumAge") : Double.POSITIVE_INFINITY;
            boolean z = x0.hasKey("enableHighAccuracy") && x0.getBoolean("enableHighAccuracy");
            return new a(j, d, z, x0.hasKey("distanceFilter") ? (float) x0.getDouble("distanceFilter") : LocationModule.RCT_DEFAULT_LOCATION_ACCURACY);
        }
    }

    private static class b {
        private final d a;
        private final d b;
        private final LocationManager c;
        private final String d;
        private final long e;
        private Location f;
        private final Handler g;
        private final Runnable h;
        private final LocationListener i;
        private boolean j;

        /* synthetic */ b(LocationManager x0, String x1, long x2, d x3, d x4, byte b) {
            this(x0, x1, x2, x3, x4);
        }

        private b(LocationManager locationManager, String provider, long timeout, d success, d error) {
            this.g = new Handler();
            this.h = new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    synchronized (this.a) {
                        if (!this.a.j) {
                            this.a.b.invoke(a.a(a.c, "Location request timed out"));
                            this.a.c.removeUpdates(this.a.i);
                            FLog.i("React", "LocationModule: Location request timed out");
                            this.a.j = true;
                        }
                    }
                }
            };
            this.i = new LocationListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = this$0;
                }

                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final void onLocationChanged(Location location) {
                    Object obj = 1;
                    synchronized (this.a) {
                        if (!this.a.j) {
                            Location f = this.a.f;
                            if (f != null) {
                                Object obj2;
                                Object obj3;
                                long time = location.getTime() - f.getTime();
                                Object obj4 = time > 120000 ? 1 : null;
                                if (time < -120000) {
                                    obj2 = 1;
                                } else {
                                    obj2 = null;
                                }
                                if (time > 0) {
                                    obj3 = 1;
                                } else {
                                    obj3 = null;
                                }
                                if (obj4 == null) {
                                    if (obj2 == null) {
                                        Object obj5;
                                        int accuracy = (int) (location.getAccuracy() - f.getAccuracy());
                                        Object obj6 = accuracy > 0 ? 1 : null;
                                        if (accuracy < 0) {
                                            obj5 = 1;
                                        } else {
                                            obj5 = null;
                                        }
                                        if (accuracy > 200) {
                                            obj4 = 1;
                                        } else {
                                            obj4 = null;
                                        }
                                        String provider = location.getProvider();
                                        String provider2 = f.getProvider();
                                        boolean equals;
                                        if (provider != null) {
                                            equals = provider.equals(provider2);
                                        } else if (provider2 == null) {
                                            equals = true;
                                        } else {
                                            equals = false;
                                        }
                                        if (obj5 == null) {
                                            if (obj3 != null) {
                                            }
                                            if (obj3 != null) {
                                                if (obj4 == null) {
                                                }
                                            }
                                        }
                                    }
                                    obj = null;
                                }
                            }
                            if (obj != null) {
                                this.a.a.invoke(LocationModule.locationToMap(location));
                                this.a.g.removeCallbacks(this.a.h);
                                this.a.j = true;
                                this.a.c.removeUpdates(this.a.i);
                            }
                        }
                        this.a.f = location;
                    }
                }

                public final void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public final void onProviderEnabled(String provider) {
                }

                public final void onProviderDisabled(String provider) {
                }
            };
            this.c = locationManager;
            this.d = provider;
            this.e = timeout;
            this.a = success;
            this.b = error;
        }

        public final void a(Location location) {
            this.f = location;
            this.c.requestLocationUpdates(this.d, 100, 1.0f, this.i);
            this.g.postDelayed(this.h, this.e);
        }
    }

    public LocationModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "LocationObserver";
    }

    @ReactMethod
    public void setRNConfiguration(am config) {
    }

    @ReactMethod
    public void getCurrentPosition(am options, d success, d error) {
        a locationOptions = a.a(options);
        try {
            LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService("location");
            String provider = getValidProvider(locationManager, locationOptions.c);
            if (provider == null) {
                error.invoke(a.a(a.a, "No location provider available."));
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            if (location == null || ((double) (System.currentTimeMillis() - location.getTime())) >= locationOptions.b) {
                new b(locationManager, provider, locationOptions.a, success, error, (byte) 0).a(location);
                return;
            }
            success.invoke(locationToMap(location));
        } catch (SecurityException e) {
            throwLocationPermissionMissing(e);
        }
    }

    @ReactMethod
    public void startObserving(am options) {
        if (!"gps".equals(this.mWatchedProvider)) {
            a locationOptions = a.a(options);
            try {
                LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService("location");
                String provider = getValidProvider(locationManager, locationOptions.c);
                if (provider == null) {
                    emitError(a.a, "No location provider available.");
                    return;
                }
                if (!provider.equals(this.mWatchedProvider)) {
                    locationManager.removeUpdates(this.mLocationListener);
                    locationManager.requestLocationUpdates(provider, 1000, locationOptions.d, this.mLocationListener);
                }
                this.mWatchedProvider = provider;
            } catch (SecurityException e) {
                throwLocationPermissionMissing(e);
            }
        }
    }

    @ReactMethod
    public void stopObserving() {
        ((LocationManager) getReactApplicationContext().getSystemService("location")).removeUpdates(this.mLocationListener);
        this.mWatchedProvider = null;
    }

    @Nullable
    private static String getValidProvider(LocationManager locationManager, boolean highAccuracy) {
        String provider = highAccuracy ? "gps" : "network";
        if (!locationManager.isProviderEnabled(provider)) {
            provider = provider.equals("gps") ? "network" : "gps";
            if (!locationManager.isProviderEnabled(provider)) {
                return null;
            }
        }
        return provider;
    }

    private void emitError(int code, String message) {
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("geolocationError", a.a(code, message));
    }

    private static void throwLocationPermissionMissing(SecurityException e) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", e);
    }

    private static ar locationToMap(Location location) {
        ar map = new WritableNativeMap();
        ar coords = new WritableNativeMap();
        coords.putDouble("latitude", location.getLatitude());
        coords.putDouble("longitude", location.getLongitude());
        coords.putDouble("altitude", location.getAltitude());
        coords.putDouble("accuracy", (double) location.getAccuracy());
        coords.putDouble("heading", (double) location.getBearing());
        coords.putDouble("speed", (double) location.getSpeed());
        map.putMap("coords", coords);
        map.putDouble("timestamp", (double) location.getTime());
        if (VERSION.SDK_INT >= 18) {
            map.putBoolean("mocked", location.isFromMockProvider());
        }
        return map;
    }
}
