package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

final class ao {
    private final Messenger a;
    private final zzi b;

    ao(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.a = new Messenger(iBinder);
            this.b = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.b = new zzi(iBinder);
            this.a = null;
        } else {
            String str = "Invalid interface descriptor: ";
            interfaceDescriptor = String.valueOf(interfaceDescriptor);
            if (interfaceDescriptor.length() != 0) {
                str.concat(interfaceDescriptor);
            } else {
                interfaceDescriptor = new String(str);
            }
            throw new RemoteException();
        }
    }

    final void a(Message message) throws RemoteException {
        if (this.a != null) {
            this.a.send(message);
        } else if (this.b != null) {
            this.b.a(message);
        } else {
            throw new IllegalStateException("Both messengers are null");
        }
    }
}
