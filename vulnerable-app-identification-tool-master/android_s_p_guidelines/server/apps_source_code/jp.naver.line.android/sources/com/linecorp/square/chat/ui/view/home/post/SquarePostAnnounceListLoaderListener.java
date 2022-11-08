package com.linecorp.square.chat.ui.view.home.post;

import jp.naver.myhome.android.model2.bt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/linecorp/square/chat/ui/view/home/post/SquarePostAnnounceListLoaderListener;", "", "onAnnouncementListLoadFailed", "", "throwable", "", "onAnnouncementListLoadFinished", "postList", "Ljp/naver/myhome/android/model2/PostList;", "requestMoreList", "", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public interface SquarePostAnnounceListLoaderListener {
    void a(Throwable th);

    void a(bt btVar, boolean z);
}
