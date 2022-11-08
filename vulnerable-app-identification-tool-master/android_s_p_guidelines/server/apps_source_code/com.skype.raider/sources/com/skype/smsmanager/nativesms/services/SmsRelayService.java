package com.skype.smsmanager.nativesms.services;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms;
import android.support.annotation.Nullable;
import com.microsoft.skype.a.a;
import com.microsoft.skype.a.a.b;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.models.CellularMessagesMap;
import com.skype.smsmanager.nativesms.utils.PhoneUtils;
import java.util.ArrayList;

public class SmsRelayService extends Service {
    private Handler a;
    private ContentObserver b;
    private ContentObserver c;
    private volatile boolean d;
    private String e;
    private a f;
    private CellularMessagesMap g;

    public final class MmsObserver extends ContentObserver {
        final /* synthetic */ SmsRelayService a;
        private final GetCellularMessages b;
        private final AddCellularMessages c;
        private final DeleteCellularMessages d;
        private final CellularMessagesMap e;

        public MmsObserver(SmsRelayService this$0, GetCellularMessages getCellularMessages, AddCellularMessages addCellularMessage, DeleteCellularMessages deleteCellularMessages, CellularMessagesMap cellularMessagesMap) {
            this.a = this$0;
            super(this$0.a);
            SmsMmsLogger.a("SmsRelayService", "Starting SmsObserver");
            this.b = getCellularMessages;
            this.e = cellularMessagesMap;
            this.c = addCellularMessage;
            this.d = deleteCellularMessages;
        }

        public final void onChange(boolean selfChange, Uri uri) {
            SmsMmsLogger.a("SmsRelayService", "MmsObserver - onChange() selfChange: " + selfChange + " Uri:" + uri);
            this.a.f.a(new Runnable(this) {
                final /* synthetic */ MmsObserver a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    MmsObserver.a(this.a);
                }
            });
        }

        static /* synthetic */ void a(MmsObserver x0) {
            ArrayList b = x0.b.b();
            ArrayList b2 = x0.e.b();
            SmsMmsLogger.a("SmsRelayService", "currentMmsMessages:" + b.size() + ", originalMmsMessages:" + b2.size());
            if (b.size() > b2.size()) {
                x0.c.a(x0.e, x0.a.e);
            } else if (b.size() < b2.size()) {
                x0.d.b(x0.e, b);
            }
        }
    }

    public final class SmsObserver extends ContentObserver {
        final /* synthetic */ SmsRelayService a;
        private final GetCellularMessages b;
        private final AddCellularMessages c;
        private final DeleteCellularMessages d;
        private final CellularMessagesMap e;

        public SmsObserver(SmsRelayService this$0, GetCellularMessages getCellularMessages, AddCellularMessages addCellularMessage, DeleteCellularMessages deleteCellularMessages, CellularMessagesMap cellularMessagesMap) {
            this.a = this$0;
            super(this$0.a);
            SmsMmsLogger.a("SmsRelayService", "Starting SmsObserver");
            this.b = getCellularMessages;
            this.e = cellularMessagesMap;
            this.c = addCellularMessage;
            this.d = deleteCellularMessages;
        }

        public final void onChange(boolean selfChange, Uri uri) {
            SmsMmsLogger.a("SmsRelayService", "SmsObserver - onChange() selfChange: " + selfChange + " Uri:" + uri);
            this.a.f.a(new Runnable(this) {
                final /* synthetic */ SmsObserver a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    SmsObserver.a(this.a);
                }
            });
        }

        static /* synthetic */ void a(SmsObserver x0) {
            ArrayList a = x0.b.a();
            ArrayList a2 = x0.e.a();
            SmsMmsLogger.a("SmsRelayService", "currentSmsMessages:" + a.size() + ", originalSmsMessages:" + a2.size());
            if (a.size() > a2.size()) {
                x0.c.a(x0.e);
            } else if (a.size() < a2.size()) {
                x0.d.a(x0.e, a);
            }
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        SmsMmsLogger.a("SmsRelayService", "onStartCommand() isRunning: " + this.d);
        int startResult = super.onStartCommand(intent, flags, startId);
        if (this.d) {
            String newSelfPhone = intent.getStringExtra("SELF_PHONE_NUMBER");
            if (newSelfPhone != null) {
                SmsMmsLogger.a("SmsRelayService", "onStartCommand() update SelfPhone");
                SmsMmsLogger.b("SmsRelayService", "onStartCommand() selfPhone: " + newSelfPhone);
                this.e = newSelfPhone;
            }
        } else {
            this.d = true;
            SmsMmsLogger.a("SmsRelayService", "startContentObserver()");
            this.a = new Handler();
            this.e = PhoneUtils.a(this, "SmsRelayService", "startContentObserver");
            GetCellularMessages getCellularMessages = new GetCellularMessages(this);
            this.g = getCellularMessages.c();
            AddCellularMessages addCellularMessages = new AddCellularMessages(this);
            DeleteCellularMessages deleteCellularMessages = new DeleteCellularMessages(this);
            this.b = new SmsObserver(this, getCellularMessages, addCellularMessages, deleteCellularMessages, this.g);
            this.c = new MmsObserver(this, getCellularMessages, addCellularMessages, deleteCellularMessages, this.g);
            this.f = a.a("SmsRelayService", b.DEFAULT);
            if (this.b != null) {
                getContentResolver().registerContentObserver(Sms.CONTENT_URI, true, this.b);
            }
            if (this.c != null) {
                getContentResolver().registerContentObserver(MmsSms.CONTENT_URI, true, this.c);
            }
            SmsMmsLogger.a("SmsRelayService", "smsObserver:" + this.b + " mmsObserver:" + this.c);
        }
        return startResult;
    }

    public void onDestroy() {
        SmsMmsLogger.a("SmsRelayService", "onDestroy()");
        SmsMmsLogger.a("SmsRelayService", "stopContentObserver()");
        if (this.b != null) {
            getContentResolver().unregisterContentObserver(this.b);
        }
        if (this.c != null) {
            getContentResolver().unregisterContentObserver(this.c);
        }
        this.d = false;
        super.onDestroy();
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
}
