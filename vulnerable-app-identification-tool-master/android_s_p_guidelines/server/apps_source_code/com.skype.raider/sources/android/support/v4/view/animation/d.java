package android.support.v4.view.animation;

import android.view.animation.Interpolator;

abstract class d implements Interpolator {
    private final float[] a;
    private final float b = (1.0f / ((float) (this.a.length - 1)));

    public d(float[] values) {
        this.a = values;
    }

    public float getInterpolation(float input) {
        if (input >= 1.0f) {
            return 1.0f;
        }
        if (input <= 0.0f) {
            return 0.0f;
        }
        int position = Math.min((int) (((float) (this.a.length - 1)) * input), this.a.length - 2);
        return this.a[position] + ((this.a[position + 1] - this.a[position]) * ((input - (((float) position) * this.b)) / this.b));
    }
}
