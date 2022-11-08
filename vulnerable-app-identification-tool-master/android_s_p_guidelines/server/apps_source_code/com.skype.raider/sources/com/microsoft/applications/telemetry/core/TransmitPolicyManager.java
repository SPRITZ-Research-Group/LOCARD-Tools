package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.TransmitProfile;
import com.microsoft.applications.telemetry.datamodels.NetworkCost;
import com.microsoft.applications.telemetry.datamodels.NetworkType;
import com.microsoft.applications.telemetry.datamodels.PowerSource;
import com.microsoft.applications.telemetry.pal.hardware.DeviceInformation;
import com.microsoft.applications.telemetry.pal.hardware.HardwareInformationReceiver;
import com.microsoft.applications.telemetry.pal.hardware.NetworkInformation;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class TransmitPolicyManager implements IDeviceObserver {
    private static final String LOG_TAG = ("[ACT]:" + TransmitPolicyManager.class.getSimpleName().toUpperCase());
    private static final int SEC_TO_MS = 1000;
    private static final String THREAD_PREFIX = "Aria-TPM";
    private int backoffCount = 0;
    private final Lock backoffLock = new ReentrantLock();
    private final EventsHandler eventsHandler;
    private AtomicBoolean hasNetwork = new AtomicBoolean(true);
    private final IHttpClientManager httpClientManager;
    private boolean isBackedoff = false;
    private boolean isMessengerPaused = false;
    private boolean isPaused = false;
    private NetworkCost networkCost = NetworkCost.UNMETERED;
    private final Lock pauseTPMLock = new ReentrantLock();
    private PowerSource powerSource = PowerSource.AC;
    private String profileName = TransmitProfile.REAL_TIME.toString();
    private final IRecordClassifier recordClassifier;
    private int schedulePeriodSecPriHigh;
    private int schedulePeriodSecPriLow;
    private int schedulePeriodSecPriNormal;
    private final ScheduledExecutorService scheduledThreadPoolExecutor;
    private final SendLoop sendLoop;
    private boolean sendTaskScheduled = false;
    private TransmitCondition transmitCondition = TransmitCondition.UNKNOWN;
    private final TransmitPolicy transmitPolicy;

    protected class SendLoop implements Runnable {
        long count = 0;
        long lowSendMultiple;
        long normalSendMultiple;
        ScheduledFuture<?> scheduledFutureTask;
        long scheduledMillis;
        boolean stopped = true;

        public void run() {
            TransmitPolicyManager.this.isBackedoff = false;
            if (TransmitPolicyManager.this.httpClientManager.canAcceptRequests()) {
                this.count++;
                EventPriority priority = EventPriority.HIGH;
                if (this.lowSendMultiple > 0 && this.count % this.lowSendMultiple == 0) {
                    priority = EventPriority.LOW;
                    this.count = 0;
                } else if (this.normalSendMultiple > 0 && this.count % this.normalSendMultiple == 0) {
                    priority = EventPriority.NORMAL;
                    if (this.lowSendMultiple < 0) {
                        this.count = 0;
                    }
                }
                TraceHelper.TraceDebug(TransmitPolicyManager.LOG_TAG, "processing priority = " + priority.name());
                if (!TransmitPolicyManager.this.recordClassifier.processForPriorityAndAbove(priority)) {
                    TransmitPolicyManager.this.pauseTPM(false, false);
                }
            }
        }

        public boolean isStopped() {
            return this.stopped;
        }

        public long getScheduledMillis() {
            return this.scheduledMillis;
        }

        public void setSchedulePeriod(long scheduleSecs) {
            this.scheduledMillis = 1000 * scheduleSecs;
        }

        public void setNormalSendMultiple(long normalSendMultiple) {
            this.normalSendMultiple = normalSendMultiple;
        }

        public void setLowSendMultiple(long lowSendMultiple) {
            this.lowSendMultiple = lowSendMultiple;
        }

        public synchronized void startLoop() {
            if (this.stopped) {
                this.stopped = false;
                if (this.scheduledMillis <= 0) {
                    Log.w(TransmitPolicyManager.LOG_TAG, "Schedule period must be set to a value greater than 0");
                } else {
                    this.scheduledFutureTask = TransmitPolicyManager.this.scheduledThreadPoolExecutor.scheduleAtFixedRate(this, this.scheduledMillis, this.scheduledMillis, TimeUnit.MILLISECONDS);
                }
            }
        }

        public synchronized void stopLoop() {
            if (!this.stopped) {
                this.stopped = true;
                this.count = 0;
                this.scheduledFutureTask.cancel(false);
            }
        }
    }

    protected TransmitPolicyManager(IRecordClassifier recordClassifier, IHttpClientManager httpClientManager, EventsHandler eventsHandler) {
        this.recordClassifier = (IRecordClassifier) Preconditions.isNotNull(recordClassifier, "recordClassifier cannot be null.");
        this.httpClientManager = (IHttpClientManager) Preconditions.isNotNull(httpClientManager, "httpClientManager cannot be null.");
        this.eventsHandler = (EventsHandler) Preconditions.isNotNull(eventsHandler, "eventsHandler cannot be null.");
        this.scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1, new AriaThreadFactory(THREAD_PREFIX));
        this.transmitPolicy = new TransmitPolicy();
        this.sendLoop = new SendLoop();
    }

    protected final synchronized void start() {
        HardwareInformationReceiver.addObserver(this);
        PowerSource source = PalInfo.getPowerSrc();
        if (source != PowerSource.UNKNOWN) {
            this.powerSource = source;
        }
        if (PalInfo.hasNetworkStateAccess() && PalInfo.getNetworkType() == NetworkType.UNKNOWN) {
            this.hasNetwork.set(false);
            pauseTPM(false, true);
        } else {
            NetworkCost cost = PalInfo.getNetworkCost();
            if (cost != NetworkCost.UNKNOWN) {
                this.networkCost = cost;
            }
            startProcessingWithTransmitCondition(TransmitPolicy.getTransmitCondition(this.networkCost, this.powerSource), this.profileName);
        }
    }

    protected final void pause() {
        pauseTPM(true, true);
    }

    protected final void pauseTPM(boolean isMessengerPaused, boolean pauseHCM) {
        try {
            this.pauseTPMLock.lock();
            if (isMessengerPaused) {
                this.isMessengerPaused = true;
            }
            if (this.sendTaskScheduled && !this.isPaused) {
                this.sendLoop.stopLoop();
                this.isPaused = true;
            }
            if (pauseHCM) {
                pauseHCM();
            }
            this.pauseTPMLock.unlock();
        } catch (Throwable th) {
            this.pauseTPMLock.unlock();
        }
    }

    private void pauseHCM() {
        this.httpClientManager.transmissionPaused();
    }

    protected final void resume(boolean calledFromMessengerResume) {
        resumeTPM(calledFromMessengerResume);
    }

    protected final void resumeTPM(boolean isMessengerResumed) {
        try {
            this.pauseTPMLock.lock();
            if (isMessengerResumed) {
                this.isMessengerPaused = false;
            }
            if (!this.isMessengerPaused && this.sendTaskScheduled && this.hasNetwork.get()) {
                resumeHCM();
                if (this.isPaused) {
                    this.sendLoop.setSchedulePeriod(((long) this.schedulePeriodSecPriHigh) * ((long) Math.pow(2.0d, (double) this.backoffCount)));
                    this.sendLoop.startLoop();
                    this.isPaused = false;
                }
            }
            this.pauseTPMLock.unlock();
        } catch (Throwable th) {
            this.pauseTPMLock.unlock();
        }
    }

    private void resumeHCM() {
        this.httpClientManager.transmissionResumed();
    }

    protected final boolean isPaused() {
        return this.isPaused;
    }

    protected final boolean hasNetwork() {
        return this.hasNetwork.get();
    }

    protected final synchronized void stop() {
        this.sendLoop.stopLoop();
        this.scheduledThreadPoolExecutor.shutdown();
        HardwareInformationReceiver.removeObserver(this);
        this.sendTaskScheduled = false;
    }

    protected final void backoff() {
        try {
            this.backoffLock.lock();
            if (!this.isBackedoff) {
                this.sendLoop.stopLoop();
                if (this.backoffCount < 4) {
                    this.backoffCount++;
                }
                this.sendLoop.setSchedulePeriod(((long) this.schedulePeriodSecPriHigh) * ((long) Math.pow(2.0d, (double) this.backoffCount)));
                if (!this.isPaused) {
                    this.sendLoop.startLoop();
                }
                this.isBackedoff = true;
            }
            this.backoffLock.unlock();
        } catch (Throwable th) {
            this.backoffLock.unlock();
        }
    }

    protected final void clearBackoff() {
        try {
            this.backoffLock.lock();
            if (this.isBackedoff) {
                this.backoffCount = 0;
                this.sendLoop.stopLoop();
                this.sendLoop.setSchedulePeriod(((long) this.schedulePeriodSecPriHigh) * ((long) Math.pow(2.0d, (double) this.backoffCount)));
                if (!this.isPaused) {
                    this.sendLoop.startLoop();
                }
                this.isBackedoff = false;
            }
            this.backoffLock.unlock();
        } catch (Throwable th) {
            this.backoffLock.unlock();
        }
    }

    final synchronized boolean loadTransmitProfiles(String profilesJson) {
        resetTransmitProfiles();
        return this.transmitPolicy.loadTransmitProfiles(profilesJson);
    }

    final synchronized boolean setTransmitProfile(String profileName) {
        boolean z;
        if (this.transmitPolicy.containsProfile(profileName)) {
            startProcessingWithTransmitCondition(this.transmitCondition, profileName);
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    final synchronized void resetTransmitProfiles() {
        this.transmitPolicy.resetTransmitProfiles();
        if (!this.transmitPolicy.containsProfile(this.profileName)) {
            startProcessingWithTransmitCondition(this.transmitCondition, TransmitProfile.REAL_TIME.toString());
        }
    }

    private synchronized void startProcessingWithTransmitCondition(TransmitCondition condition, String profileName) {
        if (!(this.transmitCondition == condition && this.profileName == profileName)) {
            TraceHelper.TraceDebug(LOG_TAG, "startProcessingWithTransmitCondition : " + condition.name() + ", profile: " + profileName);
            if (this.sendTaskScheduled) {
                try {
                    this.sendLoop.stopLoop();
                } catch (Exception e) {
                    TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception while trying to cancel send loop.", new Object[0]), e);
                }
            }
            updateSchedulePeriods(condition, profileName);
            this.sendLoop.setSchedulePeriod(((long) this.schedulePeriodSecPriHigh) * ((long) Math.pow(2.0d, (double) this.backoffCount)));
            int normalSendMultiple = -1;
            if (this.schedulePeriodSecPriNormal > 0) {
                normalSendMultiple = (int) Math.ceil(((double) this.schedulePeriodSecPriNormal) / ((double) this.schedulePeriodSecPriHigh));
            }
            this.sendLoop.setNormalSendMultiple((long) normalSendMultiple);
            int lowSendMultiple = -1;
            if (this.schedulePeriodSecPriLow > 0) {
                lowSendMultiple = ((int) Math.ceil(((double) this.schedulePeriodSecPriLow) / ((double) this.schedulePeriodSecPriNormal))) * normalSendMultiple;
            }
            this.sendLoop.setLowSendMultiple((long) lowSendMultiple);
            if (!this.isPaused) {
                this.sendLoop.startLoop();
            }
            this.sendTaskScheduled = true;
            this.transmitCondition = condition;
            this.profileName = profileName;
            this.eventsHandler.logTransmitProfile(profileName, this.schedulePeriodSecPriHigh, this.schedulePeriodSecPriNormal, this.schedulePeriodSecPriLow, this.powerSource.getValue());
        }
        return;
    }

    final String getProfile() {
        return this.profileName;
    }

    final SendLoop getSendLoop() {
        return this.sendLoop;
    }

    public final boolean isBackedoff() {
        return this.isBackedoff;
    }

    public final long getBackoffCount() {
        return (long) this.backoffCount;
    }

    final boolean isSendTaskScheduled() {
        return this.sendTaskScheduled;
    }

    private void updateSchedulePeriods(TransmitCondition condition, String profileName) {
        String transmitProfile;
        TransmitCondition transmitCondition;
        if (profileName == null) {
            transmitProfile = this.profileName;
        } else {
            transmitProfile = profileName;
        }
        if (condition == null) {
            transmitCondition = this.transmitCondition;
        } else {
            transmitCondition = condition;
        }
        this.schedulePeriodSecPriHigh = this.transmitPolicy.getTransmitSchedule(transmitProfile, transmitCondition, EventPriority.HIGH);
        this.schedulePeriodSecPriNormal = this.transmitPolicy.getTransmitSchedule(transmitProfile, transmitCondition, EventPriority.NORMAL);
        this.schedulePeriodSecPriLow = this.transmitPolicy.getTransmitSchedule(transmitProfile, transmitCondition, EventPriority.LOW);
    }

    public final void onNetworkStateChanged() {
        if (PalInfo.getNetworkType() != NetworkType.UNKNOWN) {
            TraceHelper.TraceVerbose(LOG_TAG, "NetworkStateChanged. Internet access.");
            this.hasNetwork.set(true);
            this.networkCost = NetworkInformation.getNetworkCost();
            startProcessingWithTransmitCondition(TransmitPolicy.getTransmitCondition(this.networkCost, this.powerSource), this.profileName);
            if (this.isPaused) {
                resumeTPM(false);
                return;
            }
            return;
        }
        TraceHelper.TraceVerbose(LOG_TAG, "NetworkStateChanged. No Internet access.");
        this.hasNetwork.set(false);
        pauseTPM(false, true);
    }

    public final void onPowerStateChanged() {
        this.powerSource = DeviceInformation.getPowerSource();
        startProcessingWithTransmitCondition(TransmitPolicy.getTransmitCondition(this.networkCost, this.powerSource), this.profileName);
    }
}
