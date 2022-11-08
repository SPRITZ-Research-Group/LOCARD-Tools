package com.appboy.ui.actions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.appboy.b.d;
import com.appboy.f.c;
import com.appboy.ui.activities.AppboyFeedActivity;

public class NewsfeedAction implements IAction {
    private final d mChannel;
    private final Bundle mExtras;

    public NewsfeedAction(Bundle extras, d channel) {
        this.mExtras = extras;
        this.mChannel = channel;
    }

    public void execute(Context context) {
        try {
            Intent intent = new Intent(context, AppboyFeedActivity.class);
            if (this.mExtras != null) {
                intent.putExtras(this.mExtras);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            c.d("ContentValues", "AppboyFeedActivity was not opened successfully.", e);
        }
    }
}
