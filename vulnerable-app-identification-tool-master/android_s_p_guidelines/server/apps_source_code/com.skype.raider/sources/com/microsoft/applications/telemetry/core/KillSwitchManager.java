package com.microsoft.applications.telemetry.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KillSwitchManager {
    protected static final String KILL_DURATION_SECOND_HEADER = "kill-duration-seconds";
    protected static final String KILL_SWITCH_THIS_REQUEST_ONLY = "this-request-only";
    protected static final String KILL_TOKENS_HEADER = "kill-tokens";
    private static final String LOG_TAG = ("[ACT]:" + KillSwitchManager.class.getSimpleName().toUpperCase());
    private static final int SECOND_TO_MILLI_MULTIPLIER = 1000;
    protected HashMap<String, Long> _tenantKillSwitchMap = new HashMap();
    private final Object syncLock = new Object();

    KillSwitchManager() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public List<String> setKillSwitchTenants(Map<String, List<String>> responseHeaders) {
        synchronized (this.syncLock) {
            if (responseHeaders != null) {
                if (!responseHeaders.isEmpty()) {
                    List<String> killTokens;
                    String killSwitchDurationSeconds;
                    if (responseHeaders.containsKey(KILL_TOKENS_HEADER)) {
                        killTokens = (List) responseHeaders.get(KILL_TOKENS_HEADER);
                    } else {
                        killTokens = null;
                    }
                    if (responseHeaders.containsKey(KILL_DURATION_SECOND_HEADER)) {
                        killSwitchDurationSeconds = (String) ((List) responseHeaders.get(KILL_DURATION_SECOND_HEADER)).get(0);
                    } else {
                        killSwitchDurationSeconds = null;
                    }
                    if (killTokens == null || killTokens.isEmpty() || killSwitchDurationSeconds == null || killSwitchDurationSeconds.isEmpty()) {
                    } else if (killSwitchDurationSeconds.equals(KILL_SWITCH_THIS_REQUEST_ONLY)) {
                        return killTokens;
                    } else {
                        long duration = 0;
                        try {
                            duration = Long.valueOf(killSwitchDurationSeconds).longValue();
                        } catch (NumberFormatException e) {
                            TraceHelper.TraceVerbose(LOG_TAG, String.format("Invalid kill switch duration returned by collector. Duration=%s", new Object[]{killSwitchDurationSeconds}));
                        }
                        for (String token : killTokens) {
                            this._tenantKillSwitchMap.put(token, Long.valueOf(System.currentTimeMillis() + (1000 * duration)));
                        }
                        return null;
                    }
                }
            }
        }
    }

    public boolean isTenantKilled(String tenantToken) {
        synchronized (this.syncLock) {
            if (!this._tenantKillSwitchMap.containsKey(tenantToken)) {
                return false;
            } else if (((Long) this._tenantKillSwitchMap.get(tenantToken)).longValue() > System.currentTimeMillis()) {
                return true;
            } else {
                this._tenantKillSwitchMap.remove(tenantToken);
                return false;
            }
        }
    }
}
