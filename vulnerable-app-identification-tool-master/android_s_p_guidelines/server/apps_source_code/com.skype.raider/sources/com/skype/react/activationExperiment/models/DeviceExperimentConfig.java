package com.skype.react.activationExperiment.models;

import android.os.Build;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DeviceExperimentConfig {
    private static List<DeviceExperimentConfig> cfgList = new ArrayList();
    public final String deviceName;
    public final String experimentId;
    public final String language;
    private final int[] osVersionList;

    private DeviceExperimentConfig(String experimentId, String deviceName, int[] osVersionList, String language) {
        this.experimentId = experimentId;
        this.deviceName = deviceName;
        this.osVersionList = osVersionList;
        this.language = language;
    }

    static DeviceExperimentConfig a(List<String> activeExperimentIdList) {
        if (activeExperimentIdList == null) {
            return null;
        }
        for (String str : activeExperimentIdList) {
            DeviceExperimentConfig config;
            if (cfgList.size() <= 0 && cfgList.size() <= 0) {
                cfgList.add(new DeviceExperimentConfig("on7xelte", "on7xelte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("j5xnlte", "j5xnlte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("j7xelte", "j7xelte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("j7elte", "j7elte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("hero2lte", "hero2lte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("j5lte", "j5lte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("dreamlte", "dreamlte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("on5xelte", "on5xelte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("zeroflte", "zeroflte", new int[]{24, 25}, "en"));
                cfgList.add(new DeviceExperimentConfig("j7xeltexx", "j7xeltexx", new int[]{24, 25}, "en"));
            }
            for (DeviceExperimentConfig deviceExperimentConfig : cfgList) {
                if (deviceExperimentConfig.experimentId.equals(str)) {
                    config = deviceExperimentConfig;
                    break;
                }
            }
            config = null;
            if (config != null) {
                Object obj;
                if (config == null) {
                    obj = null;
                } else if (Build.PRODUCT.equalsIgnoreCase(config.deviceName)) {
                    int i;
                    int[] iArr = config.osVersionList;
                    if (iArr != null) {
                        for (int i2 : iArr) {
                            if (i2 == VERSION.SDK_INT) {
                                obj = 1;
                                break;
                            }
                        }
                    }
                    obj = null;
                    if (obj == null) {
                        obj = null;
                    } else if (config.language == null || !Locale.getDefault().getLanguage().equals(config.language)) {
                        obj = null;
                    } else {
                        i = 1;
                    }
                } else {
                    obj = null;
                }
                if (obj != null) {
                    return config;
                }
            }
        }
        return null;
    }
}
