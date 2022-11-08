package com.microsoft.dl.video.capture.impl;

import com.microsoft.dl.video.capture.api.FpsRange;
import java.util.Comparator;

public class FpsRangeComparator implements Comparator<FpsRange> {
    private final int a;

    public FpsRangeComparator(int targetFps) {
        this.a = targetFps;
    }

    public int compare(FpsRange left, FpsRange right) {
        boolean isInLeftRange;
        int leftDiffMin = this.a - left.getMin();
        int leftDiffMax = left.getMax() - this.a;
        int rightDiffMin = this.a - right.getMin();
        int rightDiffMax = right.getMax() - this.a;
        if (leftDiffMin < 0 || leftDiffMax < 0) {
            isInLeftRange = false;
        } else {
            isInLeftRange = true;
        }
        boolean isInRightRange;
        if (rightDiffMin < 0 || rightDiffMax < 0) {
            isInRightRange = false;
        } else {
            isInRightRange = true;
        }
        if (isInLeftRange && !isInRightRange) {
            return -1;
        }
        if (!isInLeftRange && isInRightRange) {
            return 1;
        }
        if (!isInLeftRange) {
            int leftDiff = leftDiffMin < 0 ? leftDiffMin : leftDiffMax < 0 ? -leftDiffMax : 0;
            int rightDiff = rightDiffMin < 0 ? rightDiffMin : rightDiffMax < 0 ? -rightDiffMax : 0;
            if (leftDiff == 0 || rightDiff == 0) {
                throw new AssertionError("leftDiff=" + leftDiff + ", rightDiff=" + rightDiff + ", targetFps=" + this.a + ", left=" + left + ", right=" + right);
            } else if (leftDiff < 0 && rightDiff > 0) {
                return -1;
            } else {
                if (leftDiff > 0 && rightDiff < 0) {
                    return 1;
                }
                if (leftDiff >= 0 || rightDiff >= 0) {
                    if (leftDiffMax > rightDiffMax) {
                        return -1;
                    }
                    if (leftDiffMax < rightDiffMax) {
                        return 1;
                    }
                    if (leftDiffMin <= rightDiffMin) {
                        return leftDiffMin < rightDiffMin ? 1 : 0;
                    } else {
                        return -1;
                    }
                } else if (leftDiffMin > rightDiffMin) {
                    return -1;
                } else {
                    if (leftDiffMin < rightDiffMin) {
                        return 1;
                    }
                    if (leftDiffMax >= rightDiffMax) {
                        return leftDiffMax > rightDiffMax ? 1 : 0;
                    } else {
                        return -1;
                    }
                }
            }
        } else if (leftDiffMin > rightDiffMin) {
            return -1;
        } else {
            if (leftDiffMin < rightDiffMin) {
                return 1;
            }
            if (leftDiffMax >= rightDiffMax) {
                return leftDiffMax > rightDiffMax ? 1 : 0;
            } else {
                return -1;
            }
        }
    }
}
