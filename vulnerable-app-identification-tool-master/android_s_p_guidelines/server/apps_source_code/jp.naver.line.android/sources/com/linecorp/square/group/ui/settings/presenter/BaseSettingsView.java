package com.linecorp.square.group.ui.settings.presenter;

public interface BaseSettingsView {

    public enum ViewMode {
        LOADING,
        ERROR,
        CONTENT,
        EMPTY
    }

    void a();

    void a(ViewMode viewMode);

    void b();

    void c();
}
