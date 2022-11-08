package android.support.v7.view;

import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window.Callback;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

@RestrictTo({a.LIBRARY_GROUP})
public class i implements Callback {
    final Callback e;

    public i(Callback wrapped) {
        if (wrapped == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.e = wrapped;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.e.dispatchKeyEvent(event);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return this.e.dispatchKeyShortcutEvent(event);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        return this.e.dispatchTouchEvent(event);
    }

    public boolean dispatchTrackballEvent(MotionEvent event) {
        return this.e.dispatchTrackballEvent(event);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return this.e.dispatchGenericMotionEvent(event);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return this.e.dispatchPopulateAccessibilityEvent(event);
    }

    public View onCreatePanelView(int featureId) {
        return this.e.onCreatePanelView(featureId);
    }

    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return this.e.onCreatePanelMenu(featureId, menu);
    }

    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return this.e.onPreparePanel(featureId, view, menu);
    }

    public boolean onMenuOpened(int featureId, Menu menu) {
        return this.e.onMenuOpened(featureId, menu);
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return this.e.onMenuItemSelected(featureId, item);
    }

    public void onWindowAttributesChanged(LayoutParams attrs) {
        this.e.onWindowAttributesChanged(attrs);
    }

    public void onContentChanged() {
        this.e.onContentChanged();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        this.e.onWindowFocusChanged(hasFocus);
    }

    public void onAttachedToWindow() {
        this.e.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        this.e.onDetachedFromWindow();
    }

    public void onPanelClosed(int featureId, Menu menu) {
        this.e.onPanelClosed(featureId, menu);
    }

    @RequiresApi(23)
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.e.onSearchRequested(searchEvent);
    }

    public boolean onSearchRequested() {
        return this.e.onSearchRequested();
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.e.onWindowStartingActionMode(callback);
    }

    @RequiresApi(23)
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return this.e.onWindowStartingActionMode(callback, type);
    }

    public void onActionModeStarted(ActionMode mode) {
        this.e.onActionModeStarted(mode);
    }

    public void onActionModeFinished(ActionMode mode) {
        this.e.onActionModeFinished(mode);
    }

    @RequiresApi(24)
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        this.e.onProvideKeyboardShortcuts(data, menu, deviceId);
    }

    @RequiresApi(26)
    public void onPointerCaptureChanged(boolean hasCapture) {
        this.e.onPointerCaptureChanged(hasCapture);
    }
}
