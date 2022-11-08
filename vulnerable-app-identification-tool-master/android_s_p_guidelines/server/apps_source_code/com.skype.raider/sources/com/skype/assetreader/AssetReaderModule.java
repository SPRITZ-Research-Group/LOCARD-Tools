package com.skype.assetreader;

import com.adjust.sdk.Constants;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AssetReaderModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "AssetReader";
    private static final String TAG = "AssetReader";

    public AssetReaderModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "AssetReader";
    }

    @ReactMethod
    public void readAssetFile(String assetName, ae promise) {
        try {
            InputStream assetStream = getReactApplicationContext().getAssets().open(assetName);
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[100000];
            while (true) {
                int length = assetStream.read(buffer);
                if (length != -1) {
                    result.write(buffer, 0, length);
                } else {
                    Object fileAsString = result.toString(Constants.ENCODING);
                    assetStream.close();
                    promise.a(fileAsString);
                    return;
                }
            }
        } catch (Throwable e) {
            FLog.e("AssetReader", "Failed to read from asset name " + assetName + ". Error: ", e);
            promise.a(e);
        }
    }
}
