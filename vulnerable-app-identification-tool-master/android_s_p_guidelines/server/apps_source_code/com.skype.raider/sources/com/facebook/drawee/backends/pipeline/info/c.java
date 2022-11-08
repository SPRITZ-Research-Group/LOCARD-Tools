package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.h.a;
import javax.annotation.Nullable;

public final class c extends a {
    private String a;
    @Nullable
    private final b b;

    public c(String controllerId, @Nullable b imageOriginLister) {
        this.b = imageOriginLister;
        this.a = controllerId;
    }

    public final void a(String controllerId) {
        this.a = controllerId;
    }

    public final void a(String requestId, String producerName, boolean successful) {
        int i = 5;
        if (this.b != null) {
            b bVar = this.b;
            String str = this.a;
            int i2 = -1;
            switch (producerName.hashCode()) {
                case -1914072202:
                    if (producerName.equals("BitmapMemoryCacheGetProducer")) {
                        i2 = 0;
                        break;
                    }
                    break;
                case -1683996557:
                    if (producerName.equals("LocalResourceFetchProducer")) {
                        i2 = 7;
                        break;
                    }
                    break;
                case -1579985851:
                    if (producerName.equals("LocalFileFetchProducer")) {
                        i2 = 6;
                        break;
                    }
                    break;
                case -1307634203:
                    if (producerName.equals("EncodedMemoryCacheProducer")) {
                        i2 = 2;
                        break;
                    }
                    break;
                case -1224383234:
                    if (producerName.equals("NetworkFetchProducer")) {
                        i2 = 4;
                        break;
                    }
                    break;
                case 656304759:
                    if (producerName.equals("DiskCacheProducer")) {
                        i2 = 3;
                        break;
                    }
                    break;
                case 957714404:
                    if (producerName.equals("BitmapMemoryCacheProducer")) {
                        i2 = 1;
                        break;
                    }
                    break;
                case 1019542023:
                    if (producerName.equals("LocalAssetFetchProducer")) {
                        i2 = 8;
                        break;
                    }
                    break;
                case 1721672898:
                    if (producerName.equals("DataFetchProducer")) {
                        i2 = 5;
                        break;
                    }
                    break;
                case 1793127518:
                    if (producerName.equals("LocalContentUriThumbnailFetchProducer")) {
                        i2 = 10;
                        break;
                    }
                    break;
                case 2113652014:
                    if (producerName.equals("LocalContentUriFetchProducer")) {
                        i2 = 9;
                        break;
                    }
                    break;
            }
            switch (i2) {
                case 0:
                case 1:
                    break;
                case 2:
                    i = 4;
                    break;
                case 3:
                    i = 3;
                    break;
                case 4:
                    i = 2;
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    i = 6;
                    break;
                default:
                    i = 1;
                    break;
            }
            bVar.a(str, i, successful);
        }
    }
}
