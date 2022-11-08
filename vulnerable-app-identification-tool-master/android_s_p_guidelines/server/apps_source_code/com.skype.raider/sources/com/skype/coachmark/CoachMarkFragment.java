package com.skype.coachmark;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;

public class CoachMarkFragment extends Fragment {
    private CoachMarkModule a;

    public final void a(CoachMarkModule module) {
        this.a = module;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = new RelativeLayout(container.getContext());
        layout.setLayoutParams(new LayoutParams(-1, -1));
        layout.setBackgroundColor(0);
        layout.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ CoachMarkFragment a;

            {
                this.a = this$0;
            }

            public final boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    ar pointerCoordinates = new WritableNativeMap();
                    pointerCoordinates.putInt("x", (int) ((160.0f * event.getX()) / ((float) this.a.getResources().getDisplayMetrics().densityDpi)));
                    pointerCoordinates.putInt("y", (int) ((160.0f * event.getY()) / ((float) this.a.getResources().getDisplayMetrics().densityDpi)));
                    CoachMarkFragment.a(this.a, pointerCoordinates);
                }
                return false;
            }
        });
        return layout;
    }

    static /* synthetic */ void a(CoachMarkFragment x0, ar x1) {
        if (x0.a != null) {
            x0.a.emitNativeUserEvent(x1);
        }
    }
}
