package com.microsoft.applications.telemetry.core;

import java.util.Random;

final class ExponentialRetryPolicy {
    private int baseBackoffForSendingRetriesMillis;
    private int maxBackoffForSendingRetriesMillis;
    private int maxRetries;
    private Random random;
    private final double randomizationLowerThreshold = 0.8d;
    private final double randomizationUpperThreshold = 1.2d;

    ExponentialRetryPolicy(int maxRetries, int baseBackoffForSendingRetriesMillis, int maxBackoffForSendingRetriesMillis) {
        boolean z;
        boolean z2 = true;
        Preconditions.isTrue(maxRetries >= 0, "maxRetries should be greater than or equal to 0.");
        this.maxRetries = maxRetries;
        if (baseBackoffForSendingRetriesMillis >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.isTrue(z, "baseBackoffForSendingRetriesMillis should be greater than or equal to 0.");
        this.baseBackoffForSendingRetriesMillis = baseBackoffForSendingRetriesMillis;
        if (maxBackoffForSendingRetriesMillis >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.isTrue(z, "maxBackoffForSendingRetriesMillis should be greater than or equal to 0.");
        if (maxBackoffForSendingRetriesMillis < baseBackoffForSendingRetriesMillis) {
            z2 = false;
        }
        Preconditions.isTrue(z2, "maxBackoffForSendingRetriesMillis should be greater than or equal to baseBackoffForSendingRetriesMillis.");
        this.maxBackoffForSendingRetriesMillis = maxBackoffForSendingRetriesMillis;
        this.random = new Random();
    }

    final boolean maxRetriesExceeded(int retriesSoFar) {
        if (retriesSoFar >= this.maxRetries) {
            return true;
        }
        return false;
    }

    final boolean shouldRetryForStatus(int httpStatusCode) {
        Preconditions.isTrue(httpStatusCode != 200, "We should not be retrying for OK.");
        if (httpStatusCode == -1) {
            return true;
        }
        if ((httpStatusCode >= 300 && httpStatusCode < 500 && httpStatusCode != 408) || httpStatusCode == 501 || httpStatusCode == 415) {
            return false;
        }
        return true;
    }

    final int getMillisToBackoffForRetry(int retriesSoFar) {
        int minRandomizedBaseBackoffMillis = (int) (((double) this.baseBackoffForSendingRetriesMillis) * 0.8d);
        return Math.min((int) (Math.pow(2.0d, (double) retriesSoFar) * ((double) (this.random.nextInt((((int) (((double) this.baseBackoffForSendingRetriesMillis) * 1.2d)) - minRandomizedBaseBackoffMillis) + 1) + minRandomizedBaseBackoffMillis))), this.maxBackoffForSendingRetriesMillis);
    }
}
