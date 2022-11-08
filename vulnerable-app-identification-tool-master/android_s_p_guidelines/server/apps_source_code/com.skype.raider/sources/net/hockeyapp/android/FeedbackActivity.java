package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import net.hockeyapp.android.d.c;
import net.hockeyapp.android.e.h;
import net.hockeyapp.android.e.i;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.j;
import net.hockeyapp.android.h.d;
import net.hockeyapp.android.views.AttachmentListView;
import net.hockeyapp.android.views.AttachmentView;

public class FeedbackActivity extends Activity implements OnClickListener, OnFocusChangeListener {
    private String a;
    private String b;
    private String c;
    private String d;
    private List<Uri> e = new ArrayList();
    private Context f;
    private TextView g;
    private EditText h;
    private EditText i;
    private EditText j;
    private EditText k;
    private Button l;
    private ListView m;
    private AttachmentListView n;
    private i o;
    private Handler p;
    private h q;
    private Handler r;
    private String s;
    private net.hockeyapp.android.a.a t;
    private boolean u;
    private boolean v;
    private boolean w;
    private String x;

    private static class a extends Handler {
        private final WeakReference<FeedbackActivity> a;

        a(FeedbackActivity feedbackActivity) {
            this.a = new WeakReference(feedbackActivity);
        }

        public final void handleMessage(Message msg) {
            boolean success = false;
            int errorMessage = 0;
            FeedbackActivity feedbackActivity = (FeedbackActivity) this.a.get();
            if (feedbackActivity != null) {
                if (msg == null || msg.getData() == null) {
                    errorMessage = d.hockeyapp_feedback_send_generic_error;
                } else {
                    Bundle bundle = msg.getData();
                    String responseString = bundle.getString("feedback_response");
                    String statusCode = bundle.getString("feedback_status");
                    String requestType = bundle.getString("request_type");
                    if ("send".equals(requestType) && (responseString == null || Integer.parseInt(statusCode) != 201)) {
                        errorMessage = d.hockeyapp_feedback_send_generic_error;
                    } else if ("fetch".equals(requestType) && statusCode != null && (Integer.parseInt(statusCode) == 404 || Integer.parseInt(statusCode) == 422)) {
                        FeedbackActivity.b(feedbackActivity);
                        success = true;
                    } else if (responseString != null) {
                        FeedbackActivity.a(feedbackActivity, responseString, requestType);
                        if ("send".equals(requestType)) {
                            feedbackActivity.e.removeAll(feedbackActivity.n.a());
                            Toast.makeText(feedbackActivity, d.hockeyapp_feedback_sent_toast, 1).show();
                        }
                        success = true;
                    } else {
                        errorMessage = d.hockeyapp_feedback_send_network_error;
                    }
                }
                if (!success) {
                    new Builder(feedbackActivity).setTitle(d.hockeyapp_dialog_error_title).setMessage(errorMessage).setCancelable(false).setPositiveButton(d.hockeyapp_dialog_positive_button, null).create().show();
                }
                FeedbackActivity.a();
            }
        }
    }

    private static class b extends Handler {
        private final WeakReference<FeedbackActivity> a;

        b(FeedbackActivity feedbackActivity) {
            this.a = new WeakReference(feedbackActivity);
        }

        @SuppressLint({"StaticFieldLeak"})
        public final void handleMessage(Message msg) {
            boolean success = false;
            final FeedbackActivity feedbackActivity = (FeedbackActivity) this.a.get();
            if (feedbackActivity != null) {
                if (!(msg == null || msg.getData() == null)) {
                    final net.hockeyapp.android.d.d feedbackResponse = (net.hockeyapp.android.d.d) msg.getData().getSerializable("parse_feedback_response");
                    if (feedbackResponse != null) {
                        if (feedbackResponse.a().equalsIgnoreCase("success")) {
                            success = true;
                            if (feedbackResponse.c() != null) {
                                feedbackActivity.x = feedbackResponse.c();
                                net.hockeyapp.android.f.a.a(new AsyncTask<Void, Object, Object>(this) {
                                    final /* synthetic */ b c;

                                    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                                        net.hockeyapp.android.f.h.a().a(feedbackActivity, feedbackResponse.c());
                                        return null;
                                    }
                                });
                                FeedbackActivity.a(feedbackActivity, feedbackResponse);
                                feedbackActivity.u = false;
                            }
                        } else {
                            success = false;
                        }
                    }
                }
                if (!success) {
                    new Builder(feedbackActivity).setTitle(d.hockeyapp_dialog_error_title).setMessage(d.hockeyapp_dialog_error_message).setCancelable(false).setPositiveButton(d.hockeyapp_dialog_positive_button, null).create().show();
                }
                feedbackActivity.a(true);
            }
        }
    }

    static /* synthetic */ void a(FeedbackActivity x0, net.hockeyapp.android.d.d x1) {
        x0.b(true);
        if (x1 != null && x1.b() != null && x1.b().a() != null && x1.b().a().size() > 0) {
            ArrayList a = x1.b().a();
            Collections.reverse(a);
            try {
                DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(3, 3);
                Date parse = simpleDateFormat.parse(((c) a.get(0)).b());
                x0.g.setText(String.format(x0.getString(d.hockeyapp_feedback_last_updated_text), new Object[]{dateTimeInstance.format(parse)}));
                x0.g.setContentDescription(x0.g.getText());
                x0.g.setVisibility(0);
            } catch (ParseException e) {
                e.f();
            }
            if (x0.t == null) {
                x0.t = new net.hockeyapp.android.a.a(x0.f, a);
            } else {
                x0.t.a();
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    x0.t.a((c) it.next());
                }
                x0.t.notifyDataSetChanged();
            }
            x0.m.setAdapter(x0.t);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(h.c.hockeyapp_activity_feedback, null));
        setTitle(d.hockeyapp_feedback_title);
        this.f = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.s = extras.getString(j.FRAGMENT_URL);
            this.x = extras.getString("token");
            this.v = extras.getBoolean("forceNewThread");
            this.a = extras.getString("initialUserName");
            this.b = extras.getString("initialUserEmail");
            this.c = extras.getString("initialUserSubject");
            this.d = extras.getString("userId");
            Parcelable[] initialAttachmentsArray = extras.getParcelableArray("initialAttachments");
            if (initialAttachmentsArray != null) {
                this.e.clear();
                for (Parcelable parcelable : initialAttachmentsArray) {
                    this.e.add((Uri) parcelable);
                }
            }
        }
        if (savedInstanceState != null) {
            this.w = savedInstanceState.getBoolean("feedbackViewInitialized");
            this.u = savedInstanceState.getBoolean("inSendFeedback");
            this.x = savedInstanceState.getString("token");
        } else {
            this.u = false;
            this.w = false;
        }
        j.b((Context) this);
        this.p = new a(this);
        this.r = new b(this);
        Object lastNonConfigurationInstance = getLastNonConfigurationInstance();
        if (lastNonConfigurationInstance != null && (lastNonConfigurationInstance instanceof i)) {
            this.o = (i) lastNonConfigurationInstance;
            this.o.a(this.p);
        }
        b();
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            ArrayList<Uri> attachmentsUris = savedInstanceState.getParcelableArrayList("attachments");
            if (attachmentsUris != null) {
                Iterator it = attachmentsUris.iterator();
                while (it.hasNext()) {
                    Uri attachmentUri = (Uri) it.next();
                    if (!this.e.contains(attachmentUri)) {
                        this.n.addView(new AttachmentView((Context) this, this.n, attachmentUri));
                    }
                }
            }
            this.w = savedInstanceState.getBoolean("feedbackViewInitialized");
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("attachments", this.n.a());
        outState.putBoolean("feedbackViewInitialized", this.w);
        outState.putBoolean("inSendFeedback", this.u);
        outState.putString("token", this.x);
        super.onSaveInstanceState(outState);
    }

    protected void onStart() {
        super.onStart();
        if (this.o != null) {
            this.o.a((Context) this);
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.o != null) {
            this.o.a();
        }
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.o != null) {
            this.o.a();
        }
        return this.o;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        if (this.u) {
            this.u = false;
            b();
        } else {
            finish();
        }
        return true;
    }

    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == net.hockeyapp.android.h.b.button_send) {
            if (j.c((Context) this)) {
                a(false);
                String str = (!this.v || this.u) ? this.x : null;
                final String trim = this.h.getText().toString().trim();
                final String trim2 = this.i.getText().toString().trim();
                final String trim3 = this.j.getText().toString().trim();
                Object trim4 = this.k.getText().toString().trim();
                if (TextUtils.isEmpty(trim3)) {
                    this.j.setVisibility(0);
                    a(this.j, d.hockeyapp_feedback_validate_subject_error);
                    return;
                } else if (c.b() == net.hockeyapp.android.d.e.REQUIRED && TextUtils.isEmpty(trim)) {
                    a(this.h, d.hockeyapp_feedback_validate_name_error);
                    return;
                } else if (c.c() == net.hockeyapp.android.d.e.REQUIRED && TextUtils.isEmpty(trim2)) {
                    a(this.i, d.hockeyapp_feedback_validate_email_empty);
                    return;
                } else if (TextUtils.isEmpty(trim4)) {
                    a(this.k, d.hockeyapp_feedback_validate_text_error);
                    return;
                } else if (c.c() != net.hockeyapp.android.d.e.REQUIRED || j.b(trim2)) {
                    net.hockeyapp.android.f.a.a(new AsyncTask<Void, Object, Object>(this) {
                        final /* synthetic */ FeedbackActivity d;

                        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                            net.hockeyapp.android.f.h.a().a(this.d.f, trim, trim2, trim3);
                            return null;
                        }
                    });
                    a(this.s, trim, trim2, trim3, trim4, this.d, this.n.a(), str, this.p, false);
                    c();
                    return;
                } else {
                    a(this.i, d.hockeyapp_feedback_validate_email_error);
                    return;
                }
            }
            Toast.makeText(this, d.hockeyapp_error_no_network_message, 1).show();
        } else if (viewId == net.hockeyapp.android.h.b.button_attachment) {
            if (this.n.getChildCount() >= 3) {
                Toast.makeText(this, getString(d.hockeyapp_feedback_max_attachments_allowed, new Object[]{Integer.valueOf(3)}), 0).show();
            } else {
                openContextMenu(v);
            }
        } else if (viewId == net.hockeyapp.android.h.b.button_add_response) {
            this.u = true;
            b(false);
        } else if (viewId == net.hockeyapp.android.h.b.button_refresh) {
            a(this.s, null, null, null, null, null, null, this.x, this.p, true);
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        if (v instanceof EditText) {
            ((InputMethodManager) getSystemService("input_method")).showSoftInput(v, 1);
        } else if ((v instanceof Button) || (v instanceof ImageButton)) {
            c();
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 2, 0, getString(d.hockeyapp_feedback_attach_file));
        menu.add(0, 1, 0, getString(d.hockeyapp_feedback_attach_picture));
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
            case 2:
                int itemId = item.getItemId();
                Intent intent;
                if (itemId == 2) {
                    intent = new Intent();
                    intent.setType("*/*");
                    intent.setAction("android.intent.action.GET_CONTENT");
                    startActivityForResult(Intent.createChooser(intent, getString(d.hockeyapp_feedback_select_file)), 2);
                    return true;
                } else if (itemId != 1) {
                    return false;
                } else {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction("android.intent.action.GET_CONTENT");
                    startActivityForResult(Intent.createChooser(intent, getString(d.hockeyapp_feedback_select_picture)), 1);
                    return true;
                }
            default:
                return super.onContextItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            Uri uri;
            if (requestCode == 2) {
                uri = data.getData();
                if (uri != null) {
                    this.n.addView(new AttachmentView((Context) this, this.n, uri));
                    j.a(this.n, getString(d.hockeyapp_feedback_attachment_added));
                }
            } else if (requestCode == 1) {
                uri = data.getData();
                if (uri != null) {
                    try {
                        Intent intent = new Intent(this, PaintActivity.class);
                        intent.putExtra("imageUri", uri);
                        startActivityForResult(intent, 3);
                    } catch (ActivityNotFoundException e) {
                        e.f();
                    }
                }
            } else if (requestCode == 3) {
                uri = (Uri) data.getParcelableExtra("imageUri");
                if (uri != null) {
                    this.n.addView(new AttachmentView((Context) this, this.n, uri));
                    j.a(this.n, getString(d.hockeyapp_feedback_attachment_added));
                }
            }
        }
    }

    public final void a(boolean isEnable) {
        if (this.l != null) {
            this.l.setEnabled(isEnable);
        }
    }

    private void b(boolean haveToken) {
        ScrollView feedbackScrollView = (ScrollView) findViewById(net.hockeyapp.android.h.b.wrapper_feedback_scroll);
        LinearLayout wrapperLayoutFeedbackAndMessages = (LinearLayout) findViewById(net.hockeyapp.android.h.b.wrapper_messages);
        this.m = (ListView) findViewById(net.hockeyapp.android.h.b.list_feedback_messages);
        this.n = (AttachmentListView) findViewById(net.hockeyapp.android.h.b.wrapper_attachments);
        if (haveToken) {
            wrapperLayoutFeedbackAndMessages.setVisibility(0);
            feedbackScrollView.setVisibility(8);
            this.g = (TextView) findViewById(net.hockeyapp.android.h.b.label_last_updated);
            this.g.setVisibility(4);
            Button addResponseButton = (Button) findViewById(net.hockeyapp.android.h.b.button_add_response);
            addResponseButton.setOnClickListener(this);
            addResponseButton.setOnFocusChangeListener(this);
            Button refreshButton = (Button) findViewById(net.hockeyapp.android.h.b.button_refresh);
            refreshButton.setOnClickListener(this);
            refreshButton.setOnFocusChangeListener(this);
            return;
        }
        int i;
        wrapperLayoutFeedbackAndMessages.setVisibility(8);
        feedbackScrollView.setVisibility(0);
        this.h = (EditText) findViewById(net.hockeyapp.android.h.b.input_name);
        this.h.setOnFocusChangeListener(this);
        this.i = (EditText) findViewById(net.hockeyapp.android.h.b.input_email);
        this.i.setOnFocusChangeListener(this);
        this.j = (EditText) findViewById(net.hockeyapp.android.h.b.input_subject);
        this.j.setOnFocusChangeListener(this);
        this.k = (EditText) findViewById(net.hockeyapp.android.h.b.input_message);
        this.k.setOnFocusChangeListener(this);
        if (c.b() == net.hockeyapp.android.d.e.REQUIRED) {
            this.h.setHint(getString(d.hockeyapp_feedback_name_hint_required));
        }
        if (c.c() == net.hockeyapp.android.d.e.REQUIRED) {
            this.i.setHint(getString(d.hockeyapp_feedback_email_hint_required));
        }
        this.j.setHint(getString(d.hockeyapp_feedback_subject_hint_required));
        this.k.setHint(getString(d.hockeyapp_feedback_message_hint_required));
        if (!this.w) {
            this.h.setText(this.a);
            this.i.setText(this.b);
            this.j.setText(this.c);
            if (TextUtils.isEmpty(this.a)) {
                this.h.requestFocus();
            } else if (TextUtils.isEmpty(this.b)) {
                this.i.requestFocus();
            } else if (TextUtils.isEmpty(this.c)) {
                this.j.requestFocus();
            } else {
                this.k.requestFocus();
            }
            this.w = true;
        }
        EditText editText = this.h;
        if (c.b() == net.hockeyapp.android.d.e.DONT_SHOW) {
            i = 8;
        } else {
            i = 0;
        }
        editText.setVisibility(i);
        editText = this.i;
        if (c.c() == net.hockeyapp.android.d.e.DONT_SHOW) {
            i = 8;
        } else {
            i = 0;
        }
        editText.setVisibility(i);
        this.k.setText("");
        if ((!this.v || this.u) && this.x != null) {
            this.j.setVisibility(8);
        } else {
            this.j.setVisibility(0);
        }
        this.n.removeAllViews();
        for (Uri attachmentUri : this.e) {
            this.n.addView(new AttachmentView((Context) this, this.n, attachmentUri));
        }
        Button addAttachmentButton = (Button) findViewById(net.hockeyapp.android.h.b.button_attachment);
        addAttachmentButton.setOnClickListener(this);
        addAttachmentButton.setOnFocusChangeListener(this);
        registerForContextMenu(addAttachmentButton);
        this.l = (Button) findViewById(net.hockeyapp.android.h.b.button_send);
        this.l.setOnClickListener(this);
        addAttachmentButton.setOnFocusChangeListener(this);
    }

    protected static void a() {
    }

    private void b() {
        if (this.x == null || this.u) {
            b(false);
            return;
        }
        b(true);
        a(this.s, null, null, null, null, null, null, this.x, this.p, true);
    }

    private void c() {
        if (this.k != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.k.getWindowToken(), 0);
        }
    }

    private void a(final EditText inputField, int feedbackStringId) {
        inputField.setError(getString(feedbackStringId));
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ FeedbackActivity b;

            public final void run() {
                inputField.requestFocus();
            }
        });
        a(true);
    }

    private void a(String url, String name, String email, String subject, String text, String userId, List<Uri> attachmentUris, String token, Handler feedbackHandler, boolean isFetchMessages) {
        this.o = new i(this.f, url, name, email, subject, text, userId, attachmentUris, token, feedbackHandler, isFetchMessages);
        net.hockeyapp.android.f.a.a(this.o);
    }

    static /* synthetic */ void b(FeedbackActivity x0) {
        x0.x = null;
        net.hockeyapp.android.f.a.a(new AsyncTask<Void, Object, Object>(x0) {
            final /* synthetic */ FeedbackActivity a;

            {
                this.a = this$0;
            }

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                net.hockeyapp.android.f.h.a().a(this.a, null);
                this.a.getSharedPreferences("net.hockeyapp.android.feedback", 0).edit().remove("idLastMessageSend").remove("idLastMessageProcessed").apply();
                return null;
            }
        });
        x0.b(false);
    }

    static /* synthetic */ void a(FeedbackActivity x0, String x1, String x2) {
        x0.q = new h(x0, x1, x0.r, x2);
        net.hockeyapp.android.f.a.a(x0.q);
    }
}
