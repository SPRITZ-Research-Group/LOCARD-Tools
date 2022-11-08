package com.appboy.ui.feed.listeners;

import android.content.Context;
import com.appboy.e.a.c;
import com.appboy.ui.actions.IAction;

public class AppboyDefaultFeedClickActionListener implements IFeedClickActionListener {
    public boolean onFeedCardClicked(Context context, c card, IAction cardAction) {
        return false;
    }
}
