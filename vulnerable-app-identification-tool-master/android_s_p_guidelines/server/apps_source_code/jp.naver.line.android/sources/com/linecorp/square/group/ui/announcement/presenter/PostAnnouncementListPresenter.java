package com.linecorp.square.group.ui.announcement.presenter;

import android.content.Intent;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001:\u0001\u0015J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u000bH&J\b\u0010\u000e\u001a\u00020\u000bH&J\b\u0010\u000f\u001a\u00020\u000bH&J\b\u0010\u0010\u001a\u00020\u000bH&J\b\u0010\u0011\u001a\u00020\u000bH&J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u000bH&¨\u0006\u0016"}, d2 = {"Lcom/linecorp/square/group/ui/announcement/presenter/PostAnnouncementListPresenter;", "", "handleActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onDestroyActivity", "", "onPauseActivity", "onRefresh", "onResumeActivity", "onStartActivity", "onStopActivity", "refresh", "setIsJoinedSquare", "isJoined", "setResult", "View", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public interface PostAnnouncementListPresenter {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H&J\b\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/linecorp/square/group/ui/announcement/presenter/PostAnnouncementListPresenter$View;", "", "hideProgressDialog", "", "setSwipeRefresh", "isRefresh", "", "setVisibleEmptyView", "visible", "setVisibleErrorView", "showProgressDialog", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public interface View {
        void a();

        void a(boolean z);

        void b();

        void b(boolean z);

        void c(boolean z);
    }

    void a();

    void a(boolean z);

    boolean a(int i, int i2, Intent intent);

    void b();

    void c();

    void d();

    void f();

    boolean g();

    void h();
}
