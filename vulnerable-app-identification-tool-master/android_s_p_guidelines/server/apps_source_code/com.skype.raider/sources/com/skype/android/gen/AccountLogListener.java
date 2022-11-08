package com.skype.android.gen;

import com.skype.Account;
import com.skype.Account.AccountIListener;
import com.skype.ObjectInterface;
import com.skype.ObjectInterface.ObjectInterfaceIListener;
import com.skype.PROPKEY;

public class AccountLogListener implements AccountIListener, ObjectInterfaceIListener {
    public void onSkypeTokenRequired(Account sender, String invalidToken) {
    }

    public void onPropertyChange(ObjectInterface sender, PROPKEY propKey) {
    }
}
