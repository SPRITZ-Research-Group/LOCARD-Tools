package com.linecorp.square.group.ui.common.presenter;

public interface CommonInputPresenter {

    public enum PresenterType {
        DEFAULT,
        UPDATE_SQUARE_MEMBER_NAME,
        UPDATE_SQUARE_GROUP_NAME,
        UPDATE_SQUARE_GROUP_DESCRIPTION,
        UPDATE_SQUARE_CHAT_NAME
    }

    public interface View {
        void a();

        void b();

        void c();
    }

    void a(String str);
}
