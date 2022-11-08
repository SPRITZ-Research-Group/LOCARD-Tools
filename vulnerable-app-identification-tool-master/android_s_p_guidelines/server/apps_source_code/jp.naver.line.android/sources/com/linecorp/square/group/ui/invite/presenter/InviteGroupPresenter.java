package com.linecorp.square.group.ui.invite.presenter;

import android.graphics.Bitmap;

public interface InviteGroupPresenter {

    public enum PresenterType {
        INVITE_SQUARE_GROUP,
        INVITE_SQUARE_CHAT
    }

    public interface View {
        void a();

        void a(Bitmap bitmap);

        void a(String str);

        void b(String str);

        void finish();
    }

    void a();

    void a(int i, String[] strArr, int[] iArr);

    void b();

    void c();

    void d();

    void e();
}
