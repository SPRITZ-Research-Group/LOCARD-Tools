package net.hockeyapp.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import net.hockeyapp.android.e.a;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.h;
import net.hockeyapp.android.h.b;
import net.hockeyapp.android.h.c;

public class FeedbackMessageView extends LinearLayout {
    private TextView a = ((TextView) findViewById(b.label_author));
    private TextView b = ((TextView) findViewById(b.label_date));
    private TextView c = ((TextView) findViewById(b.label_text));
    private AttachmentListView d = ((AttachmentListView) findViewById(b.list_attachments));
    private final Context e;

    public FeedbackMessageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.e = context;
        LayoutInflater.from(context).inflate(c.hockeyapp_view_feedback_message, this);
    }

    public void setFeedbackMessage(net.hockeyapp.android.d.c feedbackMessage) {
        try {
            DateFormat dateFormatIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            dateFormatIn.setTimeZone(TimeZone.getTimeZone("UTC"));
            DateFormat dateFormatOut = DateFormat.getDateTimeInstance(3, 3);
            Date date = dateFormatIn.parse(feedbackMessage.b());
            this.b.setText(dateFormatOut.format(date));
            this.b.setContentDescription(dateFormatOut.format(date));
        } catch (ParseException e) {
            e.f();
        }
        this.a.setText(feedbackMessage.d());
        this.a.setContentDescription(feedbackMessage.d());
        this.c.setText(feedbackMessage.a());
        this.c.setContentDescription(feedbackMessage.a());
        this.d.removeAllViews();
        for (net.hockeyapp.android.d.b feedbackAttachment : feedbackMessage.e()) {
            AttachmentView attachmentView = new AttachmentView(this.e, this.d, feedbackAttachment);
            a.a().a(feedbackAttachment, attachmentView);
            this.d.addView(attachmentView);
        }
    }

    public void setIndex(int index) {
        if (index % 2 == 0) {
            setBackgroundColor(getResources().getColor(h.a.hockeyapp_background_light));
        } else {
            setBackgroundColor(getResources().getColor(h.a.hockeyapp_background_white));
        }
    }
}
