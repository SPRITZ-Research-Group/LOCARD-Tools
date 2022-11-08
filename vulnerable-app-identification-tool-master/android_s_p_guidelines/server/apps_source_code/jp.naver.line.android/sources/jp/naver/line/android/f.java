package jp.naver.line.android;

import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import defpackage.acqq;
import defpackage.acrz;
import defpackage.acsi;
import defpackage.acso;
import defpackage.acuo;
import defpackage.dcq;
import defpackage.kqz;
import defpackage.lzd;
import defpackage.mee;
import defpackage.rzu;
import defpackage.rzx;
import defpackage.rzy;
import defpackage.saa;
import defpackage.sbq;
import defpackage.sol;
import defpackage.soo;
import defpackage.sop;
import defpackage.srp;
import defpackage.stw;
import defpackage.stz;
import defpackage.sui;
import defpackage.swj;
import defpackage.swm;
import defpackage.swu;
import defpackage.swv;
import defpackage.sww;
import defpackage.tei;
import defpackage.tel;
import defpackage.ten;
import defpackage.twj;
import defpackage.twm;
import defpackage.tyq;
import defpackage.uew;
import defpackage.umo;
import defpackage.ump;
import defpackage.umr;
import defpackage.uoa;
import defpackage.uob;
import defpackage.usr;
import defpackage.usw;
import defpackage.utw;
import defpackage.uuk;
import defpackage.vkk;
import defpackage.vkx;
import defpackage.vlc;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import jp.naver.line.android.util.aj;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000î\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u00107\u001a\u0002062\b\u0010\u0001\u001a\u00030\u0001J\u0010\u0010_\u001a\u00020^2\b\u0010\u0001\u001a\u00030\u0001J\u0011\u0010\u0001\u001a\u00020s2\b\u0010\u0001\u001a\u00030\u0001R\u001b\u0010\u0007\u001a\u00020\b8FX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8FX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138FX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188FX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\f\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8FX\u0002¢\u0006\f\n\u0004\b \u0010\f\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\"8FX\u0002¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b#\u0010$R\u001b\u0010&\u001a\u00020'8FX\u0002¢\u0006\f\n\u0004\b*\u0010\f\u001a\u0004\b(\u0010)R\u001b\u0010+\u001a\u00020,8FX\u0002¢\u0006\f\n\u0004\b/\u0010\f\u001a\u0004\b-\u0010.R\u001b\u00100\u001a\u0002018FX\u0002¢\u0006\f\n\u0004\b4\u0010\f\u001a\u0004\b2\u00103R\u001b\u00105\u001a\u0002068BX\u0002¢\u0006\f\n\u0004\b9\u0010\f\u001a\u0004\b7\u00108R\u001b\u0010:\u001a\u00020;8FX\u0002¢\u0006\f\n\u0004\b>\u0010\f\u001a\u0004\b<\u0010=R\u001b\u0010?\u001a\u00020@8FX\u0002¢\u0006\f\n\u0004\bC\u0010\f\u001a\u0004\bA\u0010BR\u001b\u0010D\u001a\u00020E8FX\u0002¢\u0006\f\n\u0004\bH\u0010\f\u001a\u0004\bF\u0010GR\u001d\u0010I\u001a\u0004\u0018\u00010J8FX\u0002¢\u0006\f\n\u0004\bM\u0010\f\u001a\u0004\bK\u0010LR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010N\u001a\u00020O8FX\u0002¢\u0006\f\n\u0004\bR\u0010\f\u001a\u0004\bP\u0010QR\u001b\u0010S\u001a\u00020T8FX\u0002¢\u0006\f\n\u0004\bW\u0010\f\u001a\u0004\bU\u0010VR\u001b\u0010X\u001a\u00020Y8FX\u0002¢\u0006\f\n\u0004\b\\\u0010\f\u001a\u0004\bZ\u0010[R\u001b\u0010]\u001a\u00020^8BX\u0002¢\u0006\f\n\u0004\ba\u0010\f\u001a\u0004\b_\u0010`R\u001b\u0010b\u001a\u00020c8FX\u0002¢\u0006\f\n\u0004\bf\u0010\f\u001a\u0004\bd\u0010eR\u001b\u0010g\u001a\u00020h8FX\u0002¢\u0006\f\n\u0004\bk\u0010\f\u001a\u0004\bi\u0010jR\u001b\u0010l\u001a\u0002068BX\u0002¢\u0006\f\n\u0004\bn\u0010\f\u001a\u0004\bm\u00108R\u001b\u0010o\u001a\u00020^8BX\u0002¢\u0006\f\n\u0004\bq\u0010\f\u001a\u0004\bp\u0010`R\u001b\u0010r\u001a\u00020s8BX\u0002¢\u0006\f\n\u0004\bv\u0010\f\u001a\u0004\bt\u0010uR\u001b\u0010w\u001a\u00020x8FX\u0002¢\u0006\f\n\u0004\b{\u0010\f\u001a\u0004\by\u0010zR\u001c\u0010|\u001a\u00020}8FX\u0002¢\u0006\r\n\u0005\b\u0001\u0010\f\u001a\u0004\b~\u0010R \u0010\u0001\u001a\u00030\u00018FX\u0002¢\u0006\u000f\n\u0005\b\u0001\u0010\f\u001a\u0006\b\u0001\u0010\u0001R \u0010\u0001\u001a\u00030\u00018FX\u0002¢\u0006\u000f\n\u0005\b\u0001\u0010\f\u001a\u0006\b\u0001\u0010\u0001R \u0010\u0001\u001a\u00030\u00018FX\u0002¢\u0006\u000f\n\u0005\b\u0001\u0010\f\u001a\u0006\b\u0001\u0010\u0001R\u001e\u0010\u0001\u001a\u00020s8BX\u0002¢\u0006\u000e\n\u0005\b\u0001\u0010\f\u001a\u0005\b\u0001\u0010u¨\u0006\u0001"}, d2 = {"Ljp/naver/line/android/DataManagerHolder;", "", "applicationContext", "Landroid/content/Context;", "eventBus", "Lcom/linecorp/rxeventbus/EventBus;", "(Landroid/content/Context;Lcom/linecorp/rxeventbus/EventBus;)V", "appRatingLock", "Ljava/util/concurrent/locks/Lock;", "getAppRatingLock", "()Ljava/util/concurrent/locks/Lock;", "appRatingLock$delegate", "Lkotlin/Lazy;", "autoSuggestionDictionaryDataManager", "Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionDictionaryDataManager;", "getAutoSuggestionDictionaryDataManager", "()Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionDictionaryDataManager;", "autoSuggestionDictionaryDataManager$delegate", "autoSuggestionStickerDataManager", "Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionStickerDataManager;", "getAutoSuggestionStickerDataManager", "()Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionStickerDataManager;", "autoSuggestionStickerDataManager$delegate", "autoSuggestionStickerShowcaseDataManager", "Ljp/naver/line/android/autosuggestion2/updater/AutoSuggestionStickerShowcaseDataManager;", "getAutoSuggestionStickerShowcaseDataManager", "()Ljp/naver/line/android/autosuggestion2/updater/AutoSuggestionStickerShowcaseDataManager;", "autoSuggestionStickerShowcaseDataManager$delegate", "autoSuggestionSticonDataManager", "Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionSticonDataManager;", "getAutoSuggestionSticonDataManager", "()Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionSticonDataManager;", "autoSuggestionSticonDataManager$delegate", "beaconPlatformManager", "Ljp/naver/line/android/beacon/BeaconPlatformManager;", "getBeaconPlatformManager", "()Ljp/naver/line/android/beacon/BeaconPlatformManager;", "beaconPlatformManager$delegate", "bluetoothLeServiceManager", "Lcom/linecorp/linethings/BluetoothLeServiceManager;", "getBluetoothLeServiceManager", "()Lcom/linecorp/linethings/BluetoothLeServiceManager;", "bluetoothLeServiceManager$delegate", "buddyDataManager", "Ljp/naver/line/android/buddy/BuddyDataManager;", "getBuddyDataManager", "()Ljp/naver/line/android/buddy/BuddyDataManager;", "buddyDataManager$delegate", "chatAppDataManager", "Ljp/naver/line/android/chatapp/ChatAppDataManager;", "getChatAppDataManager", "()Ljp/naver/line/android/chatapp/ChatAppDataManager;", "chatAppDataManager$delegate", "chatDataManager", "Ljp/naver/line/android/chat/ChatDataManager;", "getChatDataManager", "()Ljp/naver/line/android/chat/ChatDataManager;", "chatDataManager$delegate", "chatGallerySelectedItemDataManager", "Ljp/naver/line/android/chathistory/ChatGallerySelectedItemDataManager;", "getChatGallerySelectedItemDataManager", "()Ljp/naver/line/android/chathistory/ChatGallerySelectedItemDataManager;", "chatGallerySelectedItemDataManager$delegate", "chatRoomBgmDataManager", "Ljp/naver/line/android/music/bgm/ChatRoomBgmDataManager;", "getChatRoomBgmDataManager", "()Ljp/naver/line/android/music/bgm/ChatRoomBgmDataManager;", "chatRoomBgmDataManager$delegate", "chatTextSearchManager", "Ljp/naver/line/android/service/search/ChatTextSearchManager;", "getChatTextSearchManager", "()Ljp/naver/line/android/service/search/ChatTextSearchManager;", "chatTextSearchManager$delegate", "dateUtilsWorkaroundCachedStringDataManager", "Ljp/naver/line/android/util/DateUtilsWorkaroundCachedStringDataManager;", "getDateUtilsWorkaroundCachedStringDataManager", "()Ljp/naver/line/android/util/DateUtilsWorkaroundCachedStringDataManager;", "dateUtilsWorkaroundCachedStringDataManager$delegate", "extendedMyProfileManager", "Ljp/naver/line/android/extendedprofile/ExtendedMyProfileManager;", "getExtendedMyProfileManager", "()Ljp/naver/line/android/extendedprofile/ExtendedMyProfileManager;", "extendedMyProfileManager$delegate", "instantNewsDataManager", "Lcom/linecorp/instantnews/InstantNewsDataManager;", "getInstantNewsDataManager", "()Lcom/linecorp/instantnews/InstantNewsDataManager;", "instantNewsDataManager$delegate", "locationPlatformCoordinator", "Lcom/linecorp/location/LocationPlatformCoordinator;", "getLocationPlatformCoordinator", "()Lcom/linecorp/location/LocationPlatformCoordinator;", "locationPlatformCoordinator$delegate", "messageDataManager", "Ljp/naver/line/android/chathistory/MessageDataManager;", "getMessageDataManager", "()Ljp/naver/line/android/chathistory/MessageDataManager;", "messageDataManager$delegate", "myProfileManager", "Ljp/naver/line/android/myprofile/MyProfileManager;", "getMyProfileManager", "()Ljp/naver/line/android/myprofile/MyProfileManager;", "myProfileManager$delegate", "readCountManager", "Ljp/naver/line/android/readcount/ReadCountManager;", "getReadCountManager", "()Ljp/naver/line/android/readcount/ReadCountManager;", "readCountManager$delegate", "squareChatDataManager", "getSquareChatDataManager", "squareChatDataManager$delegate", "squareMessageDataManager", "getSquareMessageDataManager", "squareMessageDataManager$delegate", "squareUserDataManager", "Ljp/naver/line/android/chathistory/user/ChatHistoryUserDataManager;", "getSquareUserDataManager", "()Ljp/naver/line/android/chathistory/user/ChatHistoryUserDataManager;", "squareUserDataManager$delegate", "stickerDataManager", "Lcom/linecorp/shop/sticker/StickerDataManager;", "getStickerDataManager", "()Lcom/linecorp/shop/sticker/StickerDataManager;", "stickerDataManager$delegate", "sticonBO", "Ljp/naver/line/android/bo/shop/sticon/SticonBO;", "getSticonBO", "()Ljp/naver/line/android/bo/shop/sticon/SticonBO;", "sticonBO$delegate", "sticonDataManager", "Lcom/linecorp/shop/sticon/SticonDataManager;", "getSticonDataManager", "()Lcom/linecorp/shop/sticon/SticonDataManager;", "sticonDataManager$delegate", "sticonImageCache", "Ljp/naver/line/android/cache/sticon/SticonImageCache;", "getSticonImageCache", "()Ljp/naver/line/android/cache/sticon/SticonImageCache;", "sticonImageCache$delegate", "sticonInfoCache", "Ljp/naver/line/android/bo/shop/sticon/SticonInfoCache;", "getSticonInfoCache", "()Ljp/naver/line/android/bo/shop/sticon/SticonInfoCache;", "sticonInfoCache$delegate", "userDataManager", "getUserDataManager", "userDataManager$delegate", "isSquare", "", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class f {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsi(acso.a(f.class), "readCountManager", "getReadCountManager()Ljp/naver/line/android/readcount/ReadCountManager;")), acso.a(new acsi(acso.a(f.class), "chatDataManager", "getChatDataManager()Ljp/naver/line/android/chat/ChatDataManager;")), acso.a(new acsi(acso.a(f.class), "squareChatDataManager", "getSquareChatDataManager()Ljp/naver/line/android/chat/ChatDataManager;")), acso.a(new acsi(acso.a(f.class), "chatGallerySelectedItemDataManager", "getChatGallerySelectedItemDataManager()Ljp/naver/line/android/chathistory/ChatGallerySelectedItemDataManager;")), acso.a(new acsi(acso.a(f.class), "beaconPlatformManager", "getBeaconPlatformManager()Ljp/naver/line/android/beacon/BeaconPlatformManager;")), acso.a(new acsi(acso.a(f.class), "bluetoothLeServiceManager", "getBluetoothLeServiceManager()Lcom/linecorp/linethings/BluetoothLeServiceManager;")), acso.a(new acsi(acso.a(f.class), "extendedMyProfileManager", "getExtendedMyProfileManager()Ljp/naver/line/android/extendedprofile/ExtendedMyProfileManager;")), acso.a(new acsi(acso.a(f.class), "buddyDataManager", "getBuddyDataManager()Ljp/naver/line/android/buddy/BuddyDataManager;")), acso.a(new acsi(acso.a(f.class), "locationPlatformCoordinator", "getLocationPlatformCoordinator()Lcom/linecorp/location/LocationPlatformCoordinator;")), acso.a(new acsi(acso.a(f.class), "chatTextSearchManager", "getChatTextSearchManager()Ljp/naver/line/android/service/search/ChatTextSearchManager;")), acso.a(new acsi(acso.a(f.class), "chatAppDataManager", "getChatAppDataManager()Ljp/naver/line/android/chatapp/ChatAppDataManager;")), acso.a(new acsi(acso.a(f.class), "autoSuggestionDictionaryDataManager", "getAutoSuggestionDictionaryDataManager()Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionDictionaryDataManager;")), acso.a(new acsi(acso.a(f.class), "autoSuggestionStickerDataManager", "getAutoSuggestionStickerDataManager()Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionStickerDataManager;")), acso.a(new acsi(acso.a(f.class), "autoSuggestionStickerShowcaseDataManager", "getAutoSuggestionStickerShowcaseDataManager()Ljp/naver/line/android/autosuggestion2/updater/AutoSuggestionStickerShowcaseDataManager;")), acso.a(new acsi(acso.a(f.class), "autoSuggestionSticonDataManager", "getAutoSuggestionSticonDataManager()Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionSticonDataManager;")), acso.a(new acsi(acso.a(f.class), "dateUtilsWorkaroundCachedStringDataManager", "getDateUtilsWorkaroundCachedStringDataManager()Ljp/naver/line/android/util/DateUtilsWorkaroundCachedStringDataManager;")), acso.a(new acsi(acso.a(f.class), "sticonDataManager", "getSticonDataManager()Lcom/linecorp/shop/sticon/SticonDataManager;")), acso.a(new acsi(acso.a(f.class), "stickerDataManager", "getStickerDataManager()Lcom/linecorp/shop/sticker/StickerDataManager;")), acso.a(new acsi(acso.a(f.class), "sticonInfoCache", "getSticonInfoCache()Ljp/naver/line/android/bo/shop/sticon/SticonInfoCache;")), acso.a(new acsi(acso.a(f.class), "chatRoomBgmDataManager", "getChatRoomBgmDataManager()Ljp/naver/line/android/music/bgm/ChatRoomBgmDataManager;")), acso.a(new acsi(acso.a(f.class), "messageDataManager", "getMessageDataManager()Ljp/naver/line/android/chathistory/MessageDataManager;")), acso.a(new acsi(acso.a(f.class), "squareMessageDataManager", "getSquareMessageDataManager()Ljp/naver/line/android/chathistory/MessageDataManager;")), acso.a(new acsi(acso.a(f.class), "userDataManager", "getUserDataManager()Ljp/naver/line/android/chathistory/user/ChatHistoryUserDataManager;")), acso.a(new acsi(acso.a(f.class), "squareUserDataManager", "getSquareUserDataManager()Ljp/naver/line/android/chathistory/user/ChatHistoryUserDataManager;")), acso.a(new acsi(acso.a(f.class), "sticonImageCache", "getSticonImageCache()Ljp/naver/line/android/cache/sticon/SticonImageCache;")), acso.a(new acsi(acso.a(f.class), "sticonBO", "getSticonBO()Ljp/naver/line/android/bo/shop/sticon/SticonBO;")), acso.a(new acsi(acso.a(f.class), "myProfileManager", "getMyProfileManager()Ljp/naver/line/android/myprofile/MyProfileManager;")), acso.a(new acsi(acso.a(f.class), "appRatingLock", "getAppRatingLock()Ljava/util/concurrent/locks/Lock;")), acso.a(new acsi(acso.a(f.class), "instantNewsDataManager", "getInstantNewsDataManager()Lcom/linecorp/instantnews/InstantNewsDataManager;"))};
    private final kotlin.e A = h.a(y.a);
    private final kotlin.e B = h.a(s.a);
    private final kotlin.e C = h.a(a.a);
    private final kotlin.e D = h.a(new p(this));
    private final Context E;
    private final com.linecorp.rxeventbus.a F;
    private final kotlin.e b = h.a(new t(this));
    private final kotlin.e c = h.a(new j(this));
    private final kotlin.e d = h.a(new u(this));
    private final kotlin.e e = h.a(k.a);
    private final kotlin.e f = h.a(new f(this));
    private final kotlin.e g = h.a(new g(this));
    private final kotlin.e h = h.a(new o(this));
    private final kotlin.e i = h.a(h.a);
    private final kotlin.e j = h.a(new q(this));
    private final kotlin.e k = h.a(new m(this));
    private final kotlin.e l = h.a(new i(this));
    private final kotlin.e m = h.a(new b(this));
    private final kotlin.e n = h.a(c.a);
    private final kotlin.e o = h.a(d.a);
    private final kotlin.e p = h.a(new e(this));
    private final kotlin.e q = h.a(n.a);
    private final kotlin.e r = h.a(new z(this));
    private final kotlin.e s = h.a(new x(this));
    private final kotlin.e t = h.a(ab.a);
    private final kotlin.e u = h.a(new l(this));
    private final kotlin.e v = h.a(new r(this));
    private final kotlin.e w = h.a(new v(this));
    private final kotlin.e x = h.a(ac.a);
    private final kotlin.e y = h.a(w.a);
    private final kotlin.e z = h.a(new aa(this));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/util/concurrent/locks/ReentrantLock;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class a extends acrz implements acqq<ReentrantLock> {
        public static final a a = new a();

        a() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new ReentrantLock();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/cache/sticon/SticonImageCache;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class aa extends acrz implements acqq<stw> {
        final /* synthetic */ f a;

        aa(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            MemoryInfo memoryInfo = new MemoryInfo();
            org.jetbrains.anko.p.a(this.a.E).getMemoryInfo(memoryInfo);
            Resources resources = this.a.E.getResources();
            sop sop = soo.a;
            return new stw(new stz(resources, sop.a()), memoryInfo.lowMemory ^ 1, (byte) 0);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/bo/shop/sticon/SticonInfoCache;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class ab extends acrz implements acqq<soo> {
        public static final ab a = new ab();

        ab() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new soo();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/chathistory/user/main/ChatHistoryUserDataManagerImpl;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class ac extends acrz implements acqq<tel> {
        public static final ac a = new ac();

        ac() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new tel();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionDictionaryDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class b extends acrz implements acqq<rzu> {
        final /* synthetic */ f a;

        b(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new rzu(new rzx(this.a.E), this.a.F);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionStickerDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class c extends acrz implements acqq<rzy> {
        public static final c a = new c();

        c() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new rzy();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/autosuggestion2/updater/AutoSuggestionStickerShowcaseDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class d extends acrz implements acqq<sbq> {
        public static final d a = new d();

        d() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new sbq();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionSticonDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class e extends acrz implements acqq<saa> {
        final /* synthetic */ f a;

        e(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new saa(this.a.E);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/beacon/BeaconPlatformManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class f extends acrz implements acqq<jp.naver.line.android.beacon.c> {
        final /* synthetic */ f a;

        f(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            jp.naver.line.android.beacon.c cVar = new jp.naver.line.android.beacon.c(this.a.E, this.a.F);
            jp.naver.line.android.beacon.c.c();
            this.a.F.b(cVar);
            return cVar;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/linethings/BluetoothLeServiceManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class g extends acrz implements acqq<com.linecorp.linethings.a> {
        final /* synthetic */ f a;

        g(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new com.linecorp.linethings.a(this.a.E);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/buddy/BuddyDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class h extends acrz implements acqq<srp> {
        public static final h a = new h();

        h() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new srp();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/chatapp/ChatAppDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class i extends acrz implements acqq<swj> {
        final /* synthetic */ f a;

        i(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new swj(this.a.F, vkx.I(), new swm(this.a.E), uuk.a());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/chat/ChatDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class j extends acrz implements acqq<sui> {
        final /* synthetic */ f a;

        j(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new sui(this.a.E, twj.a(twm.MAIN), new tyq());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/chathistory/ChatGallerySelectedItemDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class k extends acrz implements acqq<swu> {
        public static final k a = new k();

        k() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new swu();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/music/bgm/ChatRoomBgmDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class l extends acrz implements acqq<ump> {
        final /* synthetic */ f a;

        l(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            tyq tyq = new tyq(twm.MAIN);
            vlc a = vkx.a();
            uob uob = uoa.a;
            return new ump(tyq, new umo(a, uoa.h, vkk.a()), new umr(this.a.E));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/service/search/ChatTextSearchManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class m extends acrz implements acqq<utw> {
        final /* synthetic */ f a;

        m(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new utw(this.a.E);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/util/DateUtilsWorkaroundCachedStringDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class n extends acrz implements acqq<aj> {
        public static final n a = new n();

        n() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            if (VERSION.SDK_INT >= 23) {
                return null;
            }
            return new aj();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/extendedprofile/ExtendedMyProfileManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class o extends acrz implements acqq<uew> {
        final /* synthetic */ f a;

        o(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new uew(this.a.E, this.a.F);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/instantnews/InstantNewsDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class p extends acrz implements acqq<dcq> {
        final /* synthetic */ f a;

        p(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new dcq(this.a.E, AnonymousClass1.a, new acqq<String>(this) {
                final /* synthetic */ p a;

                {
                    this.a = r1;
                }

                public final /* synthetic */ Object invoke() {
                    return this.a.a.t().a().g();
                }
            });
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/location/LocationPlatformCoordinator;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class q extends acrz implements acqq<kqz> {
        final /* synthetic */ f a;

        q(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return (kqz) kqz.b.a(this.a.E);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/chathistory/MessageDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class r extends acrz implements acqq<swv> {
        final /* synthetic */ f a;

        r(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return sww.a(this.a.E, jp.naver.line.android.model.j.MAIN);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/myprofile/MyProfileManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class s extends acrz implements acqq<uoa> {
        public static final s a = new s();

        s() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            uob uob = uoa.a;
            return uoa.h;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/readcount/impl/ReadCountManagerImpl;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class t extends acrz implements acqq<usw> {
        final /* synthetic */ f a;

        t(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new usw(this.a.F);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/chat/ChatDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class u extends acrz implements acqq<sui> {
        final /* synthetic */ f a;

        u(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new sui(this.a.E, twj.a(twm.MAIN), new tyq(twm.SQUARE));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/chathistory/MessageDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class v extends acrz implements acqq<swv> {
        final /* synthetic */ f a;

        v(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return sww.a(this.a.E, jp.naver.line.android.model.j.SQUARE);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/chathistory/user/square/SquareChatHistoryUserDataManagerImpl;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class w extends acrz implements acqq<ten> {
        public static final w a = new w();

        w() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new ten();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/shop/sticker/StickerDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class x extends acrz implements acqq<lzd> {
        final /* synthetic */ f a;

        x(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new lzd(this.a.F);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/bo/shop/sticon/SticonBO;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class y extends acrz implements acqq<sol> {
        public static final y a = new y();

        y() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new sol();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/shop/sticon/SticonDataManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class z extends acrz implements acqq<mee> {
        final /* synthetic */ f a;

        z(f fVar) {
            this.a = fVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new mee(this.a.E, this.a.F);
        }
    }

    public final usr a() {
        return (usr) this.b.d();
    }

    public final swu b() {
        return (swu) this.e.d();
    }

    public final jp.naver.line.android.beacon.c c() {
        return (jp.naver.line.android.beacon.c) this.f.d();
    }

    public final com.linecorp.linethings.a d() {
        return (com.linecorp.linethings.a) this.g.d();
    }

    public final uew e() {
        return (uew) this.h.d();
    }

    public final kqz f() {
        return (kqz) this.j.d();
    }

    public final utw g() {
        return (utw) this.k.d();
    }

    public final swj h() {
        return (swj) this.l.d();
    }

    public final rzu i() {
        return (rzu) this.m.d();
    }

    public final rzy j() {
        return (rzy) this.n.d();
    }

    public final sbq k() {
        return (sbq) this.o.d();
    }

    public final saa l() {
        return (saa) this.p.d();
    }

    public final aj m() {
        return (aj) this.q.d();
    }

    public final mee n() {
        return (mee) this.r.d();
    }

    public final lzd o() {
        return (lzd) this.s.d();
    }

    public final soo p() {
        return (soo) this.t.d();
    }

    public final ump q() {
        return (ump) this.u.d();
    }

    public final stw r() {
        return (stw) this.z.d();
    }

    public final sol s() {
        return (sol) this.A.d();
    }

    public final uoa t() {
        return (uoa) this.B.d();
    }

    public final Lock u() {
        return (Lock) this.C.d();
    }

    public final dcq v() {
        return (dcq) this.D.d();
    }

    public f(Context context, com.linecorp.rxeventbus.a aVar) {
        this.E = context;
        this.F = aVar;
    }

    public final sui a(boolean z) {
        return z ? (sui) this.d.d() : (sui) this.c.d();
    }

    public final swv b(boolean z) {
        return z ? (swv) this.w.d() : (swv) this.v.d();
    }

    public final tei c(boolean z) {
        return z ? (tei) this.y.d() : (tei) this.x.d();
    }
}
