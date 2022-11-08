package com.devfd.RNGeocoder;

import android.location.Address;
import android.location.Geocoder;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import java.util.List;

public class RNGeocoderModule extends ReactContextBaseJavaModule {
    private Geocoder geocoder;

    public RNGeocoderModule(ag reactContext) {
        super(reactContext);
        this.geocoder = new Geocoder(reactContext.getApplicationContext());
    }

    public String getName() {
        return "RNGeocoder";
    }

    @ReactMethod
    public void geocodeAddress(String addressName, ae promise) {
        if (Geocoder.isPresent()) {
            try {
                promise.a(transform(this.geocoder.getFromLocationName(addressName, 20)));
                return;
            } catch (Throwable e) {
                promise.a(e);
                return;
            }
        }
        promise.a("NOT_AVAILABLE", "Geocoder not available for this platform");
    }

    @ReactMethod
    public void geocodePosition(am position, ae promise) {
        if (Geocoder.isPresent()) {
            try {
                promise.a(transform(this.geocoder.getFromLocation(position.getDouble("lat"), position.getDouble("lng"), 20)));
                return;
            } catch (Throwable e) {
                promise.a(e);
                return;
            }
        }
        promise.a("NOT_AVAILABLE", "Geocoder not available for this platform");
    }

    aq transform(List<Address> addresses) {
        aq results = new WritableNativeArray();
        for (Address address : addresses) {
            ar result = new WritableNativeMap();
            ar position = new WritableNativeMap();
            position.putDouble("lat", address.getLatitude());
            position.putDouble("lng", address.getLongitude());
            result.putMap("position", position);
            String feature_name = address.getFeatureName();
            if (feature_name == null || feature_name.equals(address.getSubThoroughfare()) || feature_name.equals(address.getThoroughfare()) || feature_name.equals(address.getLocality())) {
                result.putString("feature", null);
            } else {
                result.putString("feature", feature_name);
            }
            result.putString("locality", address.getLocality());
            result.putString("adminArea", address.getAdminArea());
            result.putString("country", address.getCountryName());
            result.putString("countryCode", address.getCountryCode());
            result.putString("locale", address.getLocale().toString());
            result.putString("postalCode", address.getPostalCode());
            result.putString("subAdminArea", address.getSubAdminArea());
            result.putString("subLocality", address.getSubLocality());
            result.putString("streetNumber", address.getSubThoroughfare());
            result.putString("streetName", address.getThoroughfare());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(address.getAddressLine(i));
            }
            result.putString("formattedAddress", sb.toString());
            results.pushMap(result);
        }
        return results;
    }
}
