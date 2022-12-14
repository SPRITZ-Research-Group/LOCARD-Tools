package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.views.viewpager.ReactViewPager;
import com.facebook.react.views.viewpager.ReactViewPagerManager;
import java.util.List;

public class RCTViewPagerManager extends ReactViewPagerManager {
    static final String REACT_CLASS = "AndroidViewPager";

    public void addViews(ReactViewPager parent, List<View> views) {
        parent.setViews(views);
    }

    public void removeAllViews(ReactViewPager parent) {
        parent.f();
    }
}
