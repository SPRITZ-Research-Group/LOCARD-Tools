package jp.naver.android.npush.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import java.lang.Thread.UncaughtExceptionHandler;
import jp.naver.android.npush.common.Logger;
import jp.naver.android.npush.common.NPushConstant;
import jp.naver.android.npush.common.NPushIntent;
import jp.naver.android.npush.network.NPushNetworkConfig;
import jp.naver.android.npush.network.NPushNetworkController;

public class NPushMessageService extends Service {
    private NPushNetworkController npushNetworkController;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                Logger.e("Main Thread is going to Die!!!", th);
                Process.killProcess(Process.myPid());
            }
        });
        NPushConstant.initClientType();
        NPushNetworkConfig.initServerAddress();
        NPushConstant.getClientType(getApplicationContext());
        NPushNetworkController.initInstance();
        StringBuilder stringBuilder = new StringBuilder("NPushMessageService onCreate : ");
        stringBuilder.append(getApplication().getPackageName());
        Logger.d(stringBuilder.toString());
        Logger.d("----------------------- NNI Service Start -----------------------");
        nniStart();
    }

    private void nniStart() {
        this.npushNetworkController = NPushNetworkController.getInstance();
        procOnCreate();
    }

    private void procOnCreate() {
        Logger.d("NPushMessageService procOnCreate");
        this.npushNetworkController.setServiceContext(this);
        NPushConstant.getServicePackageName();
        this.npushNetworkController.SERVICE_procOnCreate();
    }

    private void handleRequestIntent(Intent intent) {
        if (intent != null && this.npushNetworkController != null) {
            this.npushNetworkController.SERVICE_handleRequestIntent(intent);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            StringBuilder stringBuilder = new StringBuilder("NPushMessageService onStartCommand : intent.getAction()=");
            stringBuilder.append(intent.getAction());
            Logger.d(stringBuilder.toString());
        }
        if (intent != null) {
            if (NPushIntent.REQUEST_GETVERSION_INTENT.equals(intent.getAction())) {
                informVersion(intent);
            } else {
                handleRequestIntent(intent);
            }
        }
        return 1;
    }

    public void onDestroy() {
        Logger.d("NPushMessageService onDestroy");
        this.npushNetworkController.setShutdown(true);
        this.npushNetworkController.SERVICE_onDestroy();
        Intent action = new Intent().setAction(NPushIntent.RESPONSE_GETSTATE_INTENT);
        action.putExtra(NPushIntent.EXTRA_STATE, "Service onDestroy");
        sendBroadcast(action);
    }

    private void informVersion(Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(NPushIntent.EXTRA_APPLICATION_PENDING_INTENT);
        String stringExtra = intent.getStringExtra(NPushIntent.EXTRA_APPLICATION_SERVICE_ID);
        if (stringExtra == null) {
            stringExtra = pendingIntent.getCreatorPackage();
        }
        intent = new Intent().setAction(NPushIntent.RESPONSE_GETVERSION_INTENT).addCategory(stringExtra);
        intent.putExtra(NPushIntent.EXTRA_VERSION, String.format("service jar version: %d", new Object[]{Integer.valueOf(7)}));
        sendBroadcast(intent);
    }
}
