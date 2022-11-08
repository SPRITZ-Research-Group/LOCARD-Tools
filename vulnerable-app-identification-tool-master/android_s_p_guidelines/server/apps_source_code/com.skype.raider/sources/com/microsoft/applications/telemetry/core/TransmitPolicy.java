package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.TransmitProfile;
import com.microsoft.applications.telemetry.datamodels.NetworkCost;
import com.microsoft.applications.telemetry.datamodels.PowerSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class TransmitPolicy {
    private static final String LOG_TAG = ("[ACT]:" + TransmitPolicy.class.getSimpleName().toUpperCase());
    private static final String PROFILE_NAME = "name";
    private static final String PROFILE_NETCOST = "netCost";
    private static final String PROFILE_NETCOST_HIGH = "high";
    private static final String PROFILE_NETCOST_LOW = "low";
    private static final String PROFILE_POWERSTATE = "powerState";
    private static final String PROFILE_POWERSTATE_BATTERY = "battery";
    private static final String PROFILE_POWERSTATE_CHARGING = "charging";
    private static final String PROFILE_RULES = "rules";
    private static final String PROFILE_TIMERS = "timers";
    private static String defaultProfilesJSON = "[{\"name\":\"RealTime\",\"rules\":[{\"netCost\":\"low\",\"powerState\":\"charging\",\"timers\":[1,2,4]},{\"netCost\":\"low\",\"powerState\":\"battery\",\"timers\":[2,4,8]},{\"netCost\":\"high\",\"powerState\":\"charging\",\"timers\":[3,6,12]},{\"netCost\":\"high\",\"powerState\":\"battery\",\"timers\":[4,8,16]}]},{\"name\":\"NearRealTime\",\"rules\":[{\"netCost\":\"low\",\"powerState\":\"charging\",\"timers\":[3,6,12]},{\"netCost\":\"low\",\"powerState\":\"battery\",\"timers\":[6,12,24]},{\"netCost\":\"high\",\"powerState\":\"charging\",\"timers\":[9,18,-1]},{\"netCost\":\"high\",\"powerState\":\"battery\",\"timers\":[12,24,-1]}]},{\"name\":\"BestEffort\",\"rules\":[{\"netCost\":\"low\",\"powerState\":\"charging\",\"timers\":[9,18,36]},{\"netCost\":\"low\",\"powerState\":\"battery\",\"timers\":[18,36,72]},{\"netCost\":\"high\",\"powerState\":\"charging\",\"timers\":[27,54,-1]},{\"netCost\":\"high\",\"powerState\":\"battery\",\"timers\":[36,72,-1]}]}]";
    private HashSet<String> defaultProfiles = new HashSet();
    private Map<String, Map<TransmitCondition, Map<EventPriority, Integer>>> policies = new HashMap();

    TransmitPolicy() {
        this.defaultProfiles.add(TransmitProfile.REAL_TIME.toString());
        this.defaultProfiles.add(TransmitProfile.NEAR_REAL_TIME.toString());
        this.defaultProfiles.add(TransmitProfile.BEST_EFFORT.toString());
        loadTransmitProfiles(defaultProfilesJSON);
    }

    public synchronized int getTransmitSchedule(String profileName, TransmitCondition condition, EventPriority priority) {
        int intValue;
        validateInputs(profileName, condition, priority);
        if (this.policies.containsKey(profileName)) {
            Map<TransmitCondition, Map<EventPriority, Integer>> conditionMap = (Map) this.policies.get(profileName);
            if (condition != null && conditionMap.containsKey(condition)) {
                Map<EventPriority, Integer> priorityMap = (Map) conditionMap.get(condition);
                if (priorityMap != null && priorityMap.containsKey(priority)) {
                    intValue = ((Integer) priorityMap.get(priority)).intValue();
                }
            }
        }
        Log.w(LOG_TAG, String.format("The transmission policy manager does not contain a schedule for the given properties|TransmitProfile:%s|TransmitCondition:%s|EventPriority:%s", new Object[]{profileName, condition, priority}));
        intValue = -1;
        return intValue;
    }

    public synchronized boolean loadTransmitProfiles(String profilesJson) {
        boolean z;
        JSONArray profiles = new JSONArray(profilesJson);
        for (int i = 0; i < profiles.length(); i++) {
            JSONObject profile = profiles.getJSONObject(i);
            String profileName = profile.getString(PROFILE_NAME);
            if (!this.defaultProfiles.contains(profileName) || !this.policies.containsKey(profileName)) {
                this.policies.put(profileName, new HashMap());
                JSONArray rules = profile.getJSONArray(PROFILE_RULES);
                for (int j = 0; j < rules.length(); j++) {
                    JSONObject rule = rules.getJSONObject(j);
                    TransmitCondition condition = getTransmitCondition(getNetworkCostFromString(rule.getString(PROFILE_NETCOST)), getPowerSourceFromString(rule.getString(PROFILE_POWERSTATE)));
                    ((Map) this.policies.get(profileName)).put(condition, new HashMap());
                    ((Map) ((Map) this.policies.get(profileName)).get(condition)).put(EventPriority.HIGH, Integer.valueOf(-1));
                    ((Map) ((Map) this.policies.get(profileName)).get(condition)).put(EventPriority.NORMAL, Integer.valueOf(-1));
                    ((Map) ((Map) this.policies.get(profileName)).get(condition)).put(EventPriority.LOW, Integer.valueOf(-1));
                    JSONArray timers = rule.getJSONArray(PROFILE_TIMERS);
                    for (int k = 0; k < timers.length(); k++) {
                        int timerValue = timers.getInt(k);
                        if (timerValue > 0) {
                            switch (k) {
                                case 0:
                                    ((Map) ((Map) this.policies.get(profileName)).get(condition)).put(EventPriority.HIGH, Integer.valueOf(timerValue));
                                    break;
                                case 1:
                                    try {
                                        if (((Integer) ((Map) ((Map) this.policies.get(profileName)).get(condition)).get(EventPriority.HIGH)).intValue() > 0) {
                                            if (timerValue < ((Integer) ((Map) ((Map) this.policies.get(profileName)).get(condition)).get(EventPriority.HIGH)).intValue()) {
                                                ((Map) ((Map) this.policies.get(profileName)).get(condition)).put(EventPriority.NORMAL, ((Map) ((Map) this.policies.get(profileName)).get(condition)).get(EventPriority.HIGH));
                                                break;
                                            }
                                            ((Map) ((Map) this.policies.get(profileName)).get(condition)).put(EventPriority.NORMAL, Integer.valueOf(timerValue));
                                            break;
                                        }
                                        continue;
                                    } catch (JSONException e) {
                                        Log.e(LOG_TAG, "Profiles could not be loaded due to bad JSON.", e);
                                        z = false;
                                        break;
                                    }
                                case 2:
                                    if (((Integer) ((Map) ((Map) this.policies.get(profileName)).get(condition)).get(EventPriority.NORMAL)).intValue() > 0) {
                                        if (timerValue < ((Integer) ((Map) ((Map) this.policies.get(profileName)).get(condition)).get(EventPriority.NORMAL)).intValue()) {
                                            ((Map) ((Map) this.policies.get(profileName)).get(condition)).put(EventPriority.LOW, ((Map) ((Map) this.policies.get(profileName)).get(condition)).get(EventPriority.NORMAL));
                                            break;
                                        }
                                        ((Map) ((Map) this.policies.get(profileName)).get(condition)).put(EventPriority.LOW, Integer.valueOf(timerValue));
                                        break;
                                    }
                                    break;
                                default:
                                    continue;
                            }
                        }
                    }
                }
                continue;
            }
        }
        z = true;
        return z;
    }

    public synchronized boolean containsProfile(String profileName) {
        return this.policies.containsKey(profileName);
    }

    public synchronized void resetTransmitProfiles() {
        this.policies = new HashMap();
        loadTransmitProfiles(defaultProfilesJSON);
    }

    public static TransmitCondition getTransmitCondition(NetworkCost cost, PowerSource state) {
        switch (cost) {
            case OVER_DATA_LIMIT:
            case METERED:
                if (state == PowerSource.BATTERY) {
                    return TransmitCondition.METERED_BATTERY;
                }
                return TransmitCondition.METERED_AC;
            case UNKNOWN:
            case UNMETERED:
                if (state == PowerSource.BATTERY) {
                    return TransmitCondition.UNMETERED_BATTERY;
                }
                return TransmitCondition.UNMETERED_AC;
            default:
                throw new IllegalArgumentException("The NetworkCost argument is invalid: " + cost);
        }
    }

    private NetworkCost getNetworkCostFromString(String cost) {
        Object obj = -1;
        switch (cost.hashCode()) {
            case 107348:
                if (cost.equals("low")) {
                    obj = 1;
                    break;
                }
                break;
            case 3202466:
                if (cost.equals("high")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return NetworkCost.METERED;
            case 1:
                return NetworkCost.UNMETERED;
            default:
                return NetworkCost.UNKNOWN;
        }
    }

    private PowerSource getPowerSourceFromString(String source) {
        Object obj = -1;
        switch (source.hashCode()) {
            case -331239923:
                if (source.equals(PROFILE_POWERSTATE_BATTERY)) {
                    obj = null;
                    break;
                }
                break;
            case 1436115569:
                if (source.equals(PROFILE_POWERSTATE_CHARGING)) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return PowerSource.BATTERY;
            case 1:
                return PowerSource.AC;
            default:
                return PowerSource.UNKNOWN;
        }
    }

    private void validateInputs(String profileName, TransmitCondition condition, EventPriority priority) {
        Preconditions.isNotNullOrEmpty(profileName, "TransmitProfile cannot be null or empty");
        Preconditions.isNotNull(condition, "TransmitCondition cannot be null");
        Preconditions.isNotNull(priority, "EventPriority cannot be null");
    }
}
