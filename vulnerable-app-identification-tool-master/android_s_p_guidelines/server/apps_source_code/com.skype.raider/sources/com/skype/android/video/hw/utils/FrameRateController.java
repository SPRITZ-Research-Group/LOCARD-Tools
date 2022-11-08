package com.skype.android.video.hw.utils;

import com.skype.android.video.hw.Commons;

public class FrameRateController {
    boolean curFrameDropFlag;
    private boolean isInit = false;
    private float targetFps;
    private float tsDeltaTarget;
    private float tsError;
    private long tsLast;

    public FrameRateController(float targetFps) {
        Reset(targetFps, 0, true);
    }

    public void Reset(float targetFps, long tsCurr, boolean resetTsLast) {
        this.targetFps = targetFps;
        this.tsDeltaTarget = 1000.0f / targetFps;
        if (resetTsLast) {
            this.tsLast = tsCurr;
            this.tsError = 0.0f;
            this.curFrameDropFlag = false;
        }
    }

    public float GetTargetFps() {
        return this.targetFps;
    }

    public boolean GetDropFlag(long tsCurr) {
        boolean z = true;
        if (this.isInit) {
            if (tsCurr == this.tsLast) {
                z = false;
            }
            this.curFrameDropFlag = z;
            if (tsCurr < this.tsLast) {
                if (Long.signum(tsCurr) == -1) {
                    this.tsLast = tsCurr;
                }
                return this.curFrameDropFlag;
            }
            float timePassed = ((float) (tsCurr - this.tsLast)) + this.tsError;
            if (this.tsDeltaTarget - 5.0f < timePassed) {
                this.curFrameDropFlag = false;
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, FrameRateController.class.getSimpleName() + " timePassed " + timePassed + " tsDeltaTarget " + this.tsDeltaTarget + " tsError " + this.tsError);
            }
            return this.curFrameDropFlag;
        }
        this.isInit = true;
        this.tsLast = tsCurr;
        this.curFrameDropFlag = false;
        this.tsError = 0.0f;
        return false;
    }

    public void Update(long tsCurr) {
        if (!this.curFrameDropFlag && tsCurr != this.tsLast) {
            this.tsError += ((float) (tsCurr - this.tsLast)) - this.tsDeltaTarget;
            if (this.tsError > 100.0f || this.tsError < -100.0f) {
                this.tsError = 0.0f;
            }
            this.tsLast = tsCurr;
        }
    }
}
