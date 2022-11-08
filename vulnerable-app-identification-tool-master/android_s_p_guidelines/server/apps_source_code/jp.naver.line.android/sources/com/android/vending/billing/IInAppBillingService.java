package com.android.vending.billing;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

public interface IInAppBillingService extends IInterface {
    int a(int i, String str, String str2) throws RemoteException;

    Bundle a(int i, String str, String str2, String str3) throws RemoteException;

    Bundle a(int i, String str, String str2, String str3, String str4) throws RemoteException;

    Bundle a(int i, String str, List<String> list, String str2, String str3, String str4) throws RemoteException;

    int b(int i, String str, String str2) throws RemoteException;

    int c(int i, String str, String str2) throws RemoteException;

    Bundle getSkuDetails(int i, String str, String str2, Bundle bundle) throws RemoteException;
}
