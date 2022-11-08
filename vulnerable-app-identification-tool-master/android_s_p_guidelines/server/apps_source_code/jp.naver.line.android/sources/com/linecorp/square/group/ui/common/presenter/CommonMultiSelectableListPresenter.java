package com.linecorp.square.group.ui.common.presenter;

import androidx.recyclerview.widget.az;
import java.util.List;
import jp.naver.myhome.android.view.ah;

public interface CommonMultiSelectableListPresenter {

    public enum PresenterType {
        JOIN_REQUEST_LIST,
        ADD_CO_ADMIN_LIST
    }

    public interface View {

        public enum ViewMode {
            CONTENT,
            LOADING,
            EMPTY,
            RETRY
        }

        void a();

        void a(int i);

        void a(az azVar);

        void a(ViewMode viewMode, boolean z);

        void a(String str);

        void a(List<ah> list);

        void a(ah ahVar);

        void b();

        void b(int i);

        void b(String str);

        void b(ah ahVar);

        void c();

        void c(int i);

        void c(String str);

        void d();

        void d(int i);

        void e();

        void f();

        void g();
    }

    void a();

    void a(int i);

    void a(String str);

    void a(ah ahVar);

    void b();

    void c();

    void d();

    void e();

    void f();

    void g();

    boolean h();
}
