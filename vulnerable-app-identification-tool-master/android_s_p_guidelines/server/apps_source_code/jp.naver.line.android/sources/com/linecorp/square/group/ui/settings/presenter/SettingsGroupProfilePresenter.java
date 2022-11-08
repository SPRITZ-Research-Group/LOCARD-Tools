package com.linecorp.square.group.ui.settings.presenter;

import android.content.Intent;
import jp.naver.line.android.customview.settings.b;

public interface SettingsGroupProfilePresenter {

    public interface View extends BaseSettingsView {
        void a(String str);

        void a(b bVar);
    }

    void a();

    void a(int i, int i2, Intent intent);

    void b();

    void c();

    void d();

    void e();
}
