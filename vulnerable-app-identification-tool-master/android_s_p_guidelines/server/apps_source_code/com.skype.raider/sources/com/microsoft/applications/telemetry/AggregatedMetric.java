package com.microsoft.applications.telemetry;

import com.adjust.sdk.Constants;
import com.microsoft.applications.telemetry.core.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class AggregatedMetric {
    private static final String LOG_TAG = ("[ACT]:" + AggregatedMetric.class.getSimpleName().toUpperCase());
    private String aggName;
    private EventProperties eventProps;
    private String instName;
    private long intervalInMillis;
    private AtomicBoolean isStarted;
    private Object lock;
    private ILogger logger;
    private String objClass;
    private String objId;
    private List<Double> samples;
    private SendAggregationTimerTask sendAggregationTimerTask;
    private long startTimeMillis;
    private Timer timer;
    private String units;

    class SendAggregationTimerTask extends TimerTask {
        List<Double> samplesCopy;

        SendAggregationTimerTask() {
        }

        public void run() {
            long durationInMillis = System.currentTimeMillis() - AggregatedMetric.this.startTimeMillis;
            synchronized (AggregatedMetric.this.lock) {
                this.samplesCopy = new ArrayList(AggregatedMetric.this.samples);
                AggregatedMetric.this.isStarted.set(false);
                AggregatedMetric.this.samples.clear();
                AggregatedMetric.this.startTimeMillis = System.currentTimeMillis();
            }
            performAggregation(durationInMillis);
        }

        private void performAggregation(long durationInMillis) {
            AggregatedMetricData result = new AggregatedMetricData(AggregatedMetric.this.aggName, durationInMillis, (long) this.samplesCopy.size());
            result.units = AggregatedMetric.this.units;
            result.objectClass = AggregatedMetric.this.objClass;
            result.objectId = AggregatedMetric.this.objId;
            result.instanceName = AggregatedMetric.this.instName;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            double sum = 0.0d;
            double sumOfSquaredSamples = 0.0d;
            for (Double currentSample : this.samplesCopy) {
                if (currentSample.doubleValue() < min) {
                    min = currentSample.doubleValue();
                }
                if (currentSample.doubleValue() > max) {
                    max = currentSample.doubleValue();
                }
                sum += currentSample.doubleValue();
                sumOfSquaredSamples += currentSample.doubleValue() * currentSample.doubleValue();
            }
            double sumOfSquares = sumOfSquaredSamples - ((sum * sum) / ((double) this.samplesCopy.size()));
            result.aggregates.put(AggregateType.SUM, Double.valueOf(sum));
            result.aggregates.put(AggregateType.MAXIMUM, Double.valueOf(max));
            result.aggregates.put(AggregateType.MINIMUM, Double.valueOf(min));
            result.aggregates.put(AggregateType.SUM_OF_SQUARES, Double.valueOf(sumOfSquares));
            (AggregatedMetric.this.logger == null ? LogManager.getLogger() : AggregatedMetric.this.logger).logAggregatedMetric(result, AggregatedMetric.this.eventProps);
        }
    }

    public AggregatedMetric(String name, String units, int intervalInSec, EventProperties eventProperties, ILogger logger) {
        this.objClass = null;
        this.objId = null;
        this.instName = null;
        this.isStarted = new AtomicBoolean(false);
        this.lock = new Object();
        this.sendAggregationTimerTask = new SendAggregationTimerTask();
        Log.v(LOG_TAG, String.format("AggregatedMetric|name: %s|units: %s|intervalInSec: %d|properties: %s|logger: %s", new Object[]{name, units, Integer.valueOf(intervalInSec), eventProperties, logger}));
        this.aggName = name;
        this.units = units;
        this.logger = logger;
        this.eventProps = eventProperties;
        this.intervalInMillis = (long) (intervalInSec * Constants.ONE_SECOND);
        this.timer = new Timer();
        this.samples = new ArrayList();
    }

    public AggregatedMetric(String name, String units, int intervalInSec, String instanceName, String objectClass, String objectId, EventProperties properties, ILogger logger) {
        this(name, units, intervalInSec, properties, logger);
        Log.v(LOG_TAG, String.format("AggregatedMetric|instanceName: %s|objectClass: %s|objectId: %s", new Object[]{instanceName, objectClass, objectId}));
        this.instName = instanceName;
        this.objClass = objectClass;
        this.objId = objectId;
    }

    public void pushMetric(double value) {
        Log.v(LOG_TAG, String.format("pushMetric: %s", new Object[]{Double.valueOf(value)}));
        if (!this.isStarted.get()) {
            this.timer.schedule(this.sendAggregationTimerTask, this.intervalInMillis);
            this.isStarted.set(true);
            this.startTimeMillis = System.currentTimeMillis();
        }
        synchronized (this.lock) {
            this.samples.add(Double.valueOf(value));
        }
    }
}
