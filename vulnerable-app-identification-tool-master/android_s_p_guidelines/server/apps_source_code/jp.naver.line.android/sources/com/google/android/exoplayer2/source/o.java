package com.google.android.exoplayer2.source;

import java.io.IOException;

public interface o {
    void onDownstreamFormatChanged(int i, m mVar, s sVar);

    void onLoadCanceled(int i, m mVar, r rVar, s sVar);

    void onLoadCompleted(int i, m mVar, r rVar, s sVar);

    void onLoadError(int i, m mVar, r rVar, s sVar, IOException iOException, boolean z);

    void onLoadStarted(int i, m mVar, r rVar, s sVar);

    void onMediaPeriodCreated(int i, m mVar);

    void onMediaPeriodReleased(int i, m mVar);

    void onReadingStarted(int i, m mVar);
}
