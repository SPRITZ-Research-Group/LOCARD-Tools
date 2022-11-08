package defpackage;

import android.database.sqlite.SQLiteDatabase;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jp.naver.line.android.activity.moremenu.e;
import jp.naver.line.android.bo.bj;
import jp.naver.line.android.bo.s;
import jp.naver.line.android.db.main.model.ao;
import jp.naver.line.android.model.cl;
import jp.naver.line.android.stickershop.model.StickerInfo;
import jp.naver.line.android.stickershop.model.f;
import jp.naver.line.android.util.w;
import jp.naver.line.shop.protocol.thrift.jq;
import kotlin.Metadata;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¨\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001e¢\u0006\u0002\u0010\u001fJ@\u00102\u001a<\u0012*\u0012( 7*\u0013\u0018\u00010\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(60\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\f\u0012\n 7*\u0004\u0018\u00010(0(03J\u0006\u00108\u001a\u000209J,\u0010:\u001a(\u0012\u0004\u0012\u00020;\u0012\u001e\u0012\u001c\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020>0=j\u0002`?\u0012\u0004\u0012\u00020@0<j\u0002`A03J,\u0010B\u001a(\u0012\u0004\u0012\u00020;\u0012\u001e\u0012\u001c\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020>0=j\u0002`?\u0012\u0004\u0012\u00020@0<j\u0002`A03JL\u0010C\u001aH\u0012*\u0012( 7*\u0013\u0018\u00010\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(60\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020E 7*\n\u0012\u0004\u0012\u00020E\u0018\u00010D0D03J\u0006\u0010F\u001a\u00020GJL\u0010H\u001aH\u00126\u00124\u0012\u0004\u0012\u00020\" 7*\u0019\u0012\u0004\u0012\u00020\"\u0018\u00010%¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(I0%¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(I\u0012\f\u0012\n 7*\u0004\u0018\u00010J0J03J\u0010\u0010K\u001a\u00020(2\u0006\u00106\u001a\u00020\"H\u0007J\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020N0M2\u0006\u0010O\u001a\u00020PJ\u0010\u0010Q\u001a\u0004\u0018\u00010#2\u0006\u00106\u001a\u00020\"J$\u0010R\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020@0<2\u0006\u00106\u001a\u00020\"2\u0006\u0010S\u001a\u00020TH\u0007J\u0010\u0010U\u001a\u0004\u0018\u00010V2\u0006\u0010W\u001a\u00020\"J\u0014\u0010X\u001a\b\u0012\u0004\u0012\u00020Y0M2\u0006\u00106\u001a\u00020\"J\u0010\u0010Z\u001a\u0004\u0018\u00010[2\u0006\u00106\u001a\u00020\"J\u000e\u0010\\\u001a\u00020(2\u0006\u00106\u001a\u00020\"J\u000e\u0010]\u001a\b\u0012\u0004\u0012\u00020&0%H\u0007J\u0016\u0010^\u001a\b\u0012\u0004\u0012\u00020E0D2\u0006\u00106\u001a\u00020\"H\u0003J\u0010\u0010_\u001a\u00020(2\u0006\u0010`\u001a\u00020EH\u0002J\u0010\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020dH\u0007J\u0006\u0010e\u001a\u00020bJ\u0018\u0010f\u001a\u00020b2\u0006\u00106\u001a\u00020\"2\b\u0010g\u001a\u0004\u0018\u00010#J\b\u0010h\u001a\u00020(H\u0002J\b\u0010i\u001a\u00020(H\u0007J\b\u0010j\u001a\u00020(H\u0007J\b\u0010k\u001a\u00020bH\u0007J8\u0010l\u001a\u00020m2\u0006\u00106\u001a\u00020\"2\u0006\u0010n\u001a\u00020\"2\u0006\u0010W\u001a\u00020\"2\b\u0010o\u001a\u0004\u0018\u00010p2\u0006\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020rJ\u0016\u0010t\u001a\u00020b2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\"0%H\u0003R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0006\u0012\u0004\u0018\u00010#0!X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\u00020(8BX\u0004¢\u0006\u0006\u001a\u0004\b'\u0010)R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b,\u0010-R\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u000e\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b0\u00101¨\u0006u"}, d2 = {"Lcom/linecorp/shop/sticker/StickerDataManager;", "", "eventBus", "Lcom/linecorp/rxeventbus/EventBus;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "dao", "Ljp/naver/line/android/db/main/dao/DeprecatedStickerPackageDao;", "stickerPackageDao", "Lcom/linecorp/shop/sticker/db/dao/StickerPackageDao;", "fileManager", "Ljp/naver/line/android/stickershop/StickerFileManager;", "shopApiClient", "Lcom/linecorp/shop/serverapi/ShopApiClient;", "stickerFileManager", "stickerTabImageUpdater", "Lcom/linecorp/shop/sticker/updater/StickerTabImageUpdater;", "stickerPackageDataUpdater", "Lcom/linecorp/shop/sticker/updater/StickerPackageDataUpdater;", "stickerProductSynchronizer", "Lcom/linecorp/shop/sticker/updater/StickerProductSynchronizer;", "settingDao", "Ljp/naver/line/android/db/main/dao/SettingDao;", "cmsMenuBo", "Ljp/naver/line/android/bo/CmsMenuBo;", "allianceCarrierApi", "Ljp/naver/line/android/bo/alliance/AllianceCarrierApi;", "stickerInfoCache", "Ljp/naver/line/android/bo/StickerInfoCache;", "stickerPackageZipDownloadQueue", "Ljp/naver/line/android/stickershop/service/StickerPackageZipDownloadQueue;", "(Lcom/linecorp/rxeventbus/EventBus;Landroid/database/sqlite/SQLiteDatabase;Ljp/naver/line/android/db/main/dao/DeprecatedStickerPackageDao;Lcom/linecorp/shop/sticker/db/dao/StickerPackageDao;Ljp/naver/line/android/stickershop/StickerFileManager;Lcom/linecorp/shop/serverapi/ShopApiClient;Ljp/naver/line/android/stickershop/StickerFileManager;Lcom/linecorp/shop/sticker/updater/StickerTabImageUpdater;Lcom/linecorp/shop/sticker/updater/StickerPackageDataUpdater;Lcom/linecorp/shop/sticker/updater/StickerProductSynchronizer;Ljp/naver/line/android/db/main/dao/SettingDao;Ljp/naver/line/android/bo/CmsMenuBo;Ljp/naver/line/android/bo/alliance/AllianceCarrierApi;Ljp/naver/line/android/bo/StickerInfoCache;Ljp/naver/line/android/stickershop/service/StickerPackageZipDownloadQueue;)V", "cachedCustomStickerTextImageDatas", "", "", "Lcom/linecorp/shop/sticker/model/CustomStickerTextImageData;", "cachedStickerPackageKeyboardData", "", "Lcom/linecorp/shop/sticker/model/StickerPackageKeyboardData;", "isStickerShopEnabled", "", "()Z", "stickerImageScale", "", "getStickerImageScale", "()F", "getStickerPackageDataUpdater", "()Lcom/linecorp/shop/sticker/updater/StickerPackageDataUpdater;", "getStickerTabImageUpdater", "()Lcom/linecorp/shop/sticker/updater/StickerTabImageUpdater;", "createDeleteStickerPackageTask", "Ljp/naver/line/android/util/BackgroundTask;", "Lkotlin/ParameterName;", "name", "packageId", "kotlin.jvm.PlatformType", "createGetMyStickerPackagesUseCase", "Lcom/linecorp/shop/sticker/usecase/GetMyStickerPackagesUseCase;", "createGetReceivedPresentsTask", "Lcom/linecorp/shop/serverapi/model/PaginationData;", "Lcom/linecorp/collection/ResultOrError;", "Lcom/linecorp/shop/serverapi/model/ListChunk;", "Lcom/linecorp/shop/serverapi/model/PresentRecordServerData;", "Lcom/linecorp/shop/model/PresentRecordListChunk;", "Lcom/linecorp/shop/ShopServiceError;", "Lcom/linecorp/shop/model/PresentRecordListChunkOrError;", "createGetSentPresentsTask", "createLoadSinglePackageTask", "Lcom/linecorp/collection/Optional;", "Ljp/naver/line/android/db/main/model/StickerPackageData;", "createUpdateCustomStickerTextDataUseCase", "Lcom/linecorp/shop/sticker/usecase/UpdateCustomStickerTextDataUseCase;", "createUpdateStickerPackageOrderTask", "orderedIdList", "Ljava/lang/Void;", "deleteStickerPackage", "downloadStickerPackageMetaDataAsync", "Lio/reactivex/Single;", "Ljp/naver/line/android/stickershop/StickerPackageMetaData;", "request", "Ljp/naver/line/android/stickershop/model/DeprecatedStickerResourceData;", "getCachedCustomStickerTextImageData", "getCustomizedPreviewTextImageData", "customizedText", "", "getStickerInfo", "Ljp/naver/line/android/stickershop/model/StickerInfo;", "stickerId", "getStickerPackageDownloadStatusAsync", "Ljp/naver/line/android/db/main/schema/StickerPackageSchema$DownloadStatus;", "getTabImagePair", "Lcom/linecorp/shop/model/ProductTabImagePair;", "isDownloadRunning", "loadKeyboardData", "loadSinglePackage", "markAsDeleted", "stickerPackage", "onStickerPackageChanged", "", "event", "Ljp/naver/line/android/stickershop/event/StickerPackageChangedEvent;", "requestSyncOwnedStickerProductSummariesWithServer", "setCachedCustomStickerTextImageData", "data", "shouldShowStickerShopNewBadge", "syncOwnedStickerForFirstInstall", "syncOwnedStickerProductSummariesWithServer", "syncStickerShopNewBadge", "updateStickerInfo", "Lio/reactivex/Completable;", "packageVersion", "stickerResourceSecretData", "Ljp/naver/line/android/stickershop/model/StickerResourceSecretData;", "width", "", "height", "updateStickerPackageOrder", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
/* renamed from: lzd */
public final class lzd {
    private List<lzv> a;
    private final Map<Long, lzs> b;
    private final a c;
    private final SQLiteDatabase d;
    private final tzd e;
    private final lzh f;
    private final uvy g;
    private final lyj h;
    private final uvy i;
    private final mco j;
    private final mcd k;
    private final mcm l;
    private final uac m;
    private final s n;
    private final shh o;
    private final bj p;
    private final uwu q;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/linecorp/collection/ResultOrError;", "Lcom/linecorp/shop/serverapi/model/ListChunk;", "Lcom/linecorp/shop/serverapi/model/PresentRecordServerData;", "Lcom/linecorp/shop/ShopServiceError;", "paginationData", "Lcom/linecorp/shop/serverapi/model/PaginationData;", "apply"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$b */
    final class b<T, R> implements cpn<P, R> {
        final /* synthetic */ lzd a;

        b(lzd lzd) {
            this.a = lzd;
        }

        public final /* synthetic */ Object apply(Object obj) {
            return this.a.h.c(new lzc("stickershop", (lyp) obj)).a();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/linecorp/collection/ResultOrError;", "Lcom/linecorp/shop/serverapi/model/ListChunk;", "Lcom/linecorp/shop/serverapi/model/PresentRecordServerData;", "Lcom/linecorp/shop/ShopServiceError;", "paginationData", "Lcom/linecorp/shop/serverapi/model/PaginationData;", "apply"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$c */
    final class c<T, R> implements cpn<P, R> {
        final /* synthetic */ lzd a;

        c(lzd lzd) {
            this.a = lzd;
        }

        public final /* synthetic */ Object apply(Object obj) {
            return this.a.h.b(new lzc("stickershop", (lyp) obj)).a();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/database/sqlite/SQLiteDatabase;", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$f */
    final class f extends acrz implements acqr<SQLiteDatabase, Boolean> {
        final /* synthetic */ lzd a;
        final /* synthetic */ long b;

        f(lzd lzd, long j) {
            this.a = lzd;
            this.b = j;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            this.a.e;
            boolean z = true;
            ao aoVar = (ao) acnz.f(tzd.a(this.a.d, this.b));
            if (aoVar != null) {
                if (!aoVar.b() || aoVar.n()) {
                    z = lzd.a(this.a, aoVar);
                } else {
                    this.a.e;
                    int b = tzd.b(this.a.d, this.b);
                    if (b == 1) {
                        this.a.c.a(new uwg(this.b));
                    }
                    if (b != 1) {
                        z = false;
                    }
                }
            }
            return Boolean.valueOf(z);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljp/naver/line/android/stickershop/StickerPackageMetaData;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$g */
    final class g extends acrz implements acqq<uwb> {
        final /* synthetic */ jp.naver.line.android.stickershop.model.a a;

        g(jp.naver.line.android.stickershop.model.a aVar) {
            this.a = aVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return uwb.a(uwl.a(this.a));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/SingleEmitter;", "Ljp/naver/line/android/db/main/schema/StickerPackageSchema$DownloadStatus;", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$h */
    final class h extends acrz implements acqr<ozb<ubp>, y> {
        final /* synthetic */ lzd a;
        final /* synthetic */ long b;

        h(lzd lzd, long j) {
            this.a = lzd;
            this.b = j;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            Object obj2;
            ozb ozb = (ozb) obj;
            ao a = this.a.f.a(this.a.d, (lzi) new lzj(this.b));
            if (this.a.d(this.b)) {
                obj2 = ubp.DOWNLOADING;
            } else if (a == null) {
                obj2 = ubp.NEED_DOWNLOAD;
            } else {
                obj2 = a.l();
            }
            ozb.a(obj2);
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/database/sqlite/SQLiteDatabase;", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$j */
    final class j extends acrz implements acqr<SQLiteDatabase, y> {
        final /* synthetic */ lzd a;
        final /* synthetic */ List b;

        j(lzd lzd, List list) {
            this.a = lzd;
            this.b = list;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            int i = 0;
            for (Object next : this.b) {
                int i2 = i + 1;
                if (i < 0) {
                    acnr.a();
                }
                long longValue = ((Number) next).longValue();
                this.a.e;
                tzd.b(this.a.d, longValue, i2);
                i = i2;
            }
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "", "Lkotlin/ParameterName;", "name", "packageId", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$a */
    final class a extends acrx implements acqr<Long, Boolean> {
        a(lzd lzd) {
            super(1, lzd);
        }

        public final String getName() {
            return "deleteStickerPackage";
        }

        public final acuc getOwner() {
            return acso.a(lzd.class);
        }

        public final String getSignature() {
            return "deleteStickerPackage(J)Z";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            return Boolean.valueOf(((lzd) this.receiver).b(((Number) obj).longValue()));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0015\u0010\u0003\u001a\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "Lcom/linecorp/collection/Optional;", "Ljp/naver/line/android/db/main/model/StickerPackageData;", "p1", "", "Lkotlin/ParameterName;", "name", "packageId", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$d */
    final class d extends acrx implements acqr<Long, cmi<ao>> {
        d(lzd lzd) {
            super(1, lzd);
        }

        public final String getName() {
            return "loadSinglePackage";
        }

        public final acuc getOwner() {
            return acso.a(lzd.class);
        }

        public final String getSignature() {
            return "loadSinglePackage(J)Lcom/linecorp/collection/Optional;";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            return lzd.a((lzd) this.receiver, ((Number) obj).longValue());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0002\u001a\u0017\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "p1", "", "", "Lkotlin/ParameterName;", "name", "orderedIdList", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$e */
    final class e extends acrx implements acqr<List<? extends Long>, y> {
        e(lzd lzd) {
            super(1, lzd);
        }

        public final String getName() {
            return "updateStickerPackageOrder";
        }

        public final acuc getOwner() {
            return acso.a(lzd.class);
        }

        public final String getSignature() {
            return "updateStickerPackageOrder(Ljava/util/List;)V";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            lzd.a((lzd) this.receiver, (List) obj);
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: lzd$i */
    final class i extends acrx implements acqq<Boolean> {
        i(lzd lzd) {
            super(0, lzd);
        }

        public final String getName() {
            return "syncOwnedStickerProductSummariesWithServer";
        }

        public final acuc getOwner() {
            return acso.a(lzd.class);
        }

        public final String getSignature() {
            return "syncOwnedStickerProductSummariesWithServer()Z";
        }

        public final /* synthetic */ Object invoke() {
            return Boolean.valueOf(((lzd) this.receiver).k());
        }
    }

    private lzd(a aVar, SQLiteDatabase sQLiteDatabase, tzd tzd, lzh lzh, uvy uvy, lyj lyj, uvy uvy2, mco mco, mcd mcd, mcm mcm, uac uac, s sVar, shh shh, bj bjVar, uwu uwu) {
        this.c = aVar;
        this.d = sQLiteDatabase;
        this.e = tzd;
        this.f = lzh;
        this.g = uvy;
        this.h = lyj;
        this.i = uvy2;
        this.j = mco;
        this.k = mcd;
        this.l = mcm;
        this.m = uac;
        this.n = sVar;
        this.o = shh;
        this.p = bjVar;
        this.q = uwu;
        this.b = new LinkedHashMap();
    }

    public /* synthetic */ lzd(a aVar) {
        a aVar2 = aVar;
        SQLiteDatabase a = twj.a(twm.MAIN);
        tzd tzd = new tzd();
        lzh lzh = new lzh();
        uvy uvy = new uvy();
        lyj lyj = new lyj();
        uvy uvy2 = new uvy();
        mco mco = new mco(uvy2);
        mcd mcd = new mcd(a, lzh, aVar2);
        this(aVar2, a, tzd, lzh, uvy, lyj, uvy2, mco, mcd, new mcm(aVar2, mco, mcd), uac.a(), s.a(), jp.naver.line.android.bo.a.a().b(), bj.a(), uwu.a());
    }

    public final mco n() {
        return this.j;
    }

    public final mcd o() {
        return this.k;
    }

    public final float a() {
        return this.p.b();
    }

    public final lzs a(long j) {
        return (lzs) this.b.get(Long.valueOf(j));
    }

    public final void a(long j, lzs lzs) {
        this.b.put(Long.valueOf(j), lzs);
    }

    public final w<Long, cmi<ao>> b() {
        return w.a((cpn) new lzf(new d(this)));
    }

    public final w<List<Long>, Void> c() {
        return w.a((cpm) new lze(new e(this)));
    }

    public final w<Long, Boolean> d() {
        return w.a((cpn) new lzf(new a(this)));
    }

    public final w<lyp, cmm<lym<lyq>, ljg>> e() {
        return w.a((cpn) new c(this));
    }

    public final w<lyp, cmm<lym<lyq>, ljg>> f() {
        return w.a((cpn) new b(this));
    }

    public final mcu g() {
        return new mcu(this.h, this.k);
    }

    public final mct h() {
        return new mct(this.d, this.e);
    }

    public final void i() {
        w.a((cpu) new lzg(new i(this))).a();
    }

    public final cmm<lzs, ljg> a(long j, String str) {
        return this.h.a(j, str).a();
    }

    public final boolean j() {
        if (!this.l.b()) {
            tzd.a(this.d);
        }
        return this.l.a();
    }

    public final boolean k() {
        return this.l.a();
    }

    public final boolean b(long j) {
        if (uvy.j(j)) {
            return ((Boolean) txn.a(this.d, new f(this, j))).booleanValue();
        }
        return false;
    }

    public final List<lzv> l() {
        List<lzv> list = this.a;
        if (list != null) {
            return list;
        }
        list = tzd.e(this.d);
        this.a = list;
        return list;
    }

    public final void m() {
        vkm c = this.h.c("stickershop");
        if (!(c instanceof vkp)) {
            c = null;
        }
        vkp vkp = (vkp) c;
        if (vkp != null) {
            long j = ((jq) vkp.b()).b;
            this.m.a(cl.STICKER_SHOP_LATEST_RELEASE_TIMESTAMP, j);
            if (this.m.a(cl.STICKER_SHOP_LAST_UPDATED_RELEASE_TIMESTAMP) == null) {
                this.m.a(cl.STICKER_SHOP_LAST_UPDATED_RELEASE_TIMESTAMP, j);
                return;
            }
            boolean z;
            uac uac = this.m;
            cl clVar = cl.STICKER_SHOP_NEW_BUTTON_BADGE;
            if (this.o.a(shi.PAY_SERVICE)) {
                Long g = adda.g(this.m.a(cl.STICKER_SHOP_LATEST_RELEASE_TIMESTAMP));
                if (g != null) {
                    long longValue = g.longValue();
                    Long g2 = adda.g(this.m.a(cl.STICKER_SHOP_LAST_UPDATED_RELEASE_TIMESTAMP));
                    if (longValue > (g2 != null ? g2.longValue() : 0)) {
                        z = true;
                        uac.a(clVar, String.valueOf(z));
                        s.a(ube.STICKER_SHOP);
                        this.c.a(e.a());
                    }
                }
            }
            z = false;
            uac.a(clVar, String.valueOf(z));
            s.a(ube.STICKER_SHOP);
            this.c.a(e.a());
        }
    }

    public final StickerInfo c(long j) {
        return this.p.b(j);
    }

    public final oxw a(long j, long j2, long j3, f fVar, int i, int i2) {
        return this.p.a(j, j2, j3, fVar, i, i2);
    }

    public final boolean d(long j) {
        return this.q.b(j);
    }

    public static oyz<uwb> a(jp.naver.line.android.stickershop.model.a aVar) {
        return liv.a(qaa.b(), (acqq) new g(aVar));
    }

    public final oyz<ubp> e(long j) {
        return liv.a(qaa.b(), (acqr) new h(this, j));
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onStickerPackageChanged(uwf uwf) {
        this.a = null;
    }

    public static final /* synthetic */ cmi a(lzd lzd, long j) {
        List a = tzd.a(lzd.d, j);
        if (a.isEmpty()) {
            cmj cmj = cmi.a;
            return cmj.a();
        }
        cmj cmj2 = cmi.a;
        return cmj.a(acnz.e(a));
    }

    public static final /* synthetic */ void a(lzd lzd, List list) {
        lzd.a = null;
        txn.a(lzd.d, new j(lzd, list));
    }

    public static final /* synthetic */ boolean a(lzd lzd, ao aoVar) {
        long f = aoVar.f();
        boolean z = true;
        if (tzd.a(lzd.d, f, ubp.DELETED) != 1) {
            z = false;
        }
        if (z) {
            lzd.c.a(new uwg(f));
        }
        return z;
    }
}
