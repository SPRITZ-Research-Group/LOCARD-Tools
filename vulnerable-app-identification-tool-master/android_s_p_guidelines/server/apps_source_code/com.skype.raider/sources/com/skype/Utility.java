package com.skype;

import android.content.Context;
import com.skype.rt.Spl;

public class Utility {
    public static native void enableDeadlockMonitor();

    public static native void initMedia();

    private static native void initRootTools(Context context, byte[] bArr, byte[] bArr2);

    private static native void initializeEnums();

    public static void initialize(Context context, String tempPath, String appDataPath) {
        initialize(context, null, tempPath, appDataPath);
    }

    public static void initialize(Context context, String skyLibVersion, String tempPath, String appDataPath) {
        try {
            System.loadLibrary("gnustl_shared");
        } catch (UnsatisfiedLinkError e) {
        }
        try {
            System.loadLibrary("skypert");
        } catch (UnsatisfiedLinkError e2) {
        }
        if (skyLibVersion == null) {
            System.loadLibrary("SkyLib");
        } else {
            tryToLoadLibrary(context, "SkyLib-Version-" + skyLibVersion.replaceAll("\\.", "-"));
        }
        initializeManuallyLoadedLibrary(context, tempPath, appDataPath);
    }

    private static void tryToLoadLibrary(Context context, String libraryName) {
        LibraryLoaderHelper helper = new LibraryLoaderHelper(context, new String[]{libraryName});
        try {
            System.loadLibrary(libraryName);
            helper.cleanup(libraryName);
        } catch (UnsatisfiedLinkError e) {
            unpackAndLoadLibrary(helper, libraryName);
        }
    }

    private static void unpackAndLoadLibrary(LibraryLoaderHelper helper, String libraryName) {
        if (!helper.loadNativeLibrary(libraryName)) {
            throw new RuntimeException("Cannot dynamically load " + libraryName);
        }
    }

    public static void initializeManuallyLoadedLibrary(Context context, String tempPath, String appDataPath) {
        Spl.setContext(context.getApplicationContext());
        initializeEnums();
        initRootTools(context, NativeStringConvert.ConvertToNativeBytes(tempPath), NativeStringConvert.ConvertToNativeBytes(appDataPath));
    }
}
