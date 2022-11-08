package com.linecorp.square.group.ui.main.presenter;

import android.content.Intent;
import androidx.fragment.app.aa;
import androidx.recyclerview.widget.az;
import defpackage.qhp;

public interface MainPresenter {

    public interface View {

        public enum ViewMode {
            LOADING,
            ERROR,
            CONTENT,
            EMPTY
        }

        void a(int i);

        void a(aa aaVar, int i);

        void a(az azVar);

        void a(ViewMode viewMode);

        void a(qhp qhp);
    }

    void a();

    void a(int i);

    void a(Intent intent);

    void b();

    void b(int i);

    void c();

    void d();
}
