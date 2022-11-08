package net.hockeyapp.android.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import net.hockeyapp.android.d.c;
import net.hockeyapp.android.views.FeedbackMessageView;

public final class a extends BaseAdapter {
    private Context a;
    private ArrayList<c> b;

    public a(Context context, ArrayList<c> messagesList) {
        this.a = context;
        this.b = messagesList;
    }

    public final int getCount() {
        return this.b.size();
    }

    public final View getView(int position, View convertView, ViewGroup parent) {
        FeedbackMessageView view;
        c feedbackMessage = (c) this.b.get(position);
        if (convertView == null) {
            view = new FeedbackMessageView(this.a, null);
        } else {
            view = (FeedbackMessageView) convertView;
        }
        if (feedbackMessage != null) {
            view.setFeedbackMessage(feedbackMessage);
        }
        view.setIndex(position);
        return view;
    }

    public final Object getItem(int position) {
        return this.b.get(position);
    }

    public final long getItemId(int position) {
        return (long) position;
    }

    public final void a() {
        if (this.b != null) {
            this.b.clear();
        }
    }

    public final void a(c message) {
        if (message != null && this.b != null) {
            this.b.add(message);
        }
    }
}
