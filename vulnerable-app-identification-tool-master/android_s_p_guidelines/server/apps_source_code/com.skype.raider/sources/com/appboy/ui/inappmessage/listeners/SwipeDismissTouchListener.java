package com.appboy.ui.inappmessage.listeners;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import com.adjust.sdk.Constants;

public class SwipeDismissTouchListener implements OnTouchListener {
    private long mAnimationTime;
    private DismissCallbacks mCallbacks;
    private float mDownX;
    private float mDownY;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;
    private int mSlop;
    private boolean mSwiping;
    private int mSwipingSlop;
    private Object mToken;
    private float mTranslationX;
    private VelocityTracker mVelocityTracker;
    private View mView;
    private int mViewWidth = 1;

    public interface DismissCallbacks {
        boolean canDismiss(Object obj);

        void onDismiss(View view, Object obj);
    }

    public SwipeDismissTouchListener(View view, Object token, DismissCallbacks callbacks) {
        ViewConfiguration vc = ViewConfiguration.get(view.getContext());
        this.mSlop = vc.getScaledTouchSlop();
        this.mMinFlingVelocity = vc.getScaledMinimumFlingVelocity() * 16;
        this.mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity();
        this.mAnimationTime = (long) view.getContext().getResources().getInteger(17694720);
        this.mView = view;
        this.mToken = token;
        this.mCallbacks = callbacks;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        motionEvent.offsetLocation(this.mTranslationX, 0.0f);
        if (this.mViewWidth < 2) {
            this.mViewWidth = this.mView.getWidth();
        }
        float deltaX;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.mDownX = motionEvent.getRawX();
                this.mDownY = motionEvent.getRawY();
                if (this.mCallbacks.canDismiss(this.mToken)) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                    this.mVelocityTracker.addMovement(motionEvent);
                }
                return false;
            case 1:
                if (this.mVelocityTracker != null) {
                    deltaX = motionEvent.getRawX() - this.mDownX;
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(Constants.ONE_SECOND);
                    float velocityX = this.mVelocityTracker.getXVelocity();
                    float absVelocityX = Math.abs(velocityX);
                    float absVelocityY = Math.abs(this.mVelocityTracker.getYVelocity());
                    boolean dismiss = false;
                    boolean dismissRight = false;
                    if (Math.abs(deltaX) > ((float) (this.mViewWidth / 2)) && this.mSwiping) {
                        dismiss = true;
                        dismissRight = deltaX > 0.0f;
                    } else if (((float) this.mMinFlingVelocity) <= absVelocityX && absVelocityX <= ((float) this.mMaxFlingVelocity) && absVelocityY < absVelocityX && this.mSwiping) {
                        dismiss = ((velocityX > 0.0f ? 1 : (velocityX == 0.0f ? 0 : -1)) < 0 ? 1 : null) == ((deltaX > 0.0f ? 1 : (deltaX == 0.0f ? 0 : -1)) < 0 ? 1 : null);
                        dismissRight = this.mVelocityTracker.getXVelocity() > 0.0f;
                    }
                    if (dismiss) {
                        this.mView.animate().translationX(dismissRight ? (float) this.mViewWidth : (float) (-this.mViewWidth)).alpha(0.0f).setDuration(this.mAnimationTime).setListener(new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator animation) {
                                SwipeDismissTouchListener.this.performDismiss();
                            }
                        });
                    } else if (this.mSwiping) {
                        this.mView.animate().translationX(0.0f).alpha(1.0f).setDuration(this.mAnimationTime).setListener(null);
                    }
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    this.mTranslationX = 0.0f;
                    this.mDownX = 0.0f;
                    this.mDownY = 0.0f;
                    this.mSwiping = false;
                    break;
                }
                break;
            case 2:
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.addMovement(motionEvent);
                    deltaX = motionEvent.getRawX() - this.mDownX;
                    float deltaY = motionEvent.getRawY() - this.mDownY;
                    if (Math.abs(deltaX) > ((float) this.mSlop) && Math.abs(deltaY) < Math.abs(deltaX) / 2.0f) {
                        int i;
                        this.mSwiping = true;
                        if (deltaX > 0.0f) {
                            i = this.mSlop;
                        } else {
                            i = -this.mSlop;
                        }
                        this.mSwipingSlop = i;
                        this.mView.getParent().requestDisallowInterceptTouchEvent(true);
                        MotionEvent cancelEvent = MotionEvent.obtain(motionEvent);
                        cancelEvent.setAction((motionEvent.getActionIndex() << 8) | 3);
                        this.mView.onTouchEvent(cancelEvent);
                        cancelEvent.recycle();
                    }
                    if (this.mSwiping) {
                        this.mTranslationX = deltaX;
                        this.mView.setTranslationX(deltaX - ((float) this.mSwipingSlop));
                        return true;
                    }
                }
                break;
            case 3:
                if (this.mVelocityTracker != null) {
                    this.mView.animate().translationX(0.0f).alpha(1.0f).setDuration(this.mAnimationTime).setListener(null);
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    this.mTranslationX = 0.0f;
                    this.mDownX = 0.0f;
                    this.mDownY = 0.0f;
                    this.mSwiping = false;
                    break;
                }
                break;
        }
        return false;
    }

    @TargetApi(12)
    public void performDismiss() {
        final LayoutParams lp = this.mView.getLayoutParams();
        final int originalHeight = this.mView.getHeight();
        ValueAnimator animator = ValueAnimator.ofInt(new int[]{originalHeight, 1}).setDuration(this.mAnimationTime);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                SwipeDismissTouchListener.this.mCallbacks.onDismiss(SwipeDismissTouchListener.this.mView, SwipeDismissTouchListener.this.mToken);
                SwipeDismissTouchListener.this.mView.setAlpha(1.0f);
                SwipeDismissTouchListener.this.mView.setTranslationX(0.0f);
                lp.height = originalHeight;
                SwipeDismissTouchListener.this.mView.setLayoutParams(lp);
            }
        });
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lp.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                SwipeDismissTouchListener.this.mView.setLayoutParams(lp);
            }
        });
        animator.start();
    }
}
