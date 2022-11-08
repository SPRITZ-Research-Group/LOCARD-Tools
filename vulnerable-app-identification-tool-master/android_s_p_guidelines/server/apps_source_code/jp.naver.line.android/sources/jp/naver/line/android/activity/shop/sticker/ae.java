package jp.naver.line.android.activity.shop.sticker;

import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JZ\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\fH\u0007¨\u0006\u0011"}, d2 = {"Ljp/naver/line/android/activity/shop/sticker/StickerDetailActivityIntentFactory;", "", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "packageId", "", "isShopButtonRequired", "", "referenceId", "", "isAutoSuggestionShowcasePackage", "isPresentedItem", "presentRecipientMid", "serialNumber", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class ae {
    public static final ae a = new ae();

    public static final Intent a(Context context, long j) {
        return a(context, j, false, null, false, false, null, null, 252);
    }

    public static final Intent a(Context context, long j, String str) {
        return a(context, j, true, str, false, false, null, null, 240);
    }

    public static final Intent a(Context context, long j, boolean z) {
        return a(context, j, z, null, false, false, null, null, 248);
    }

    private ae() {
    }

    public static /* synthetic */ Intent a(Context context, long j, boolean z, String str, boolean z2, boolean z3, String str2, String str3, int i) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            str = null;
        }
        if ((i & 16) != 0) {
            z2 = false;
        }
        if ((i & 32) != 0) {
            z3 = false;
        }
        if ((i & 64) != 0) {
            str2 = null;
        }
        if ((i & 128) != 0) {
            str3 = null;
        }
        Intent intent = new Intent(context, ShopStickerDetailActivity.class);
        intent.putExtra("packageId", j);
        intent.putExtra("isShopButtonRequired", z);
        intent.putExtra("isAutoSuggestionShowcasePackage", z2);
        intent.putExtra("isPresentedItem", z3);
        if (str2 != null) {
            intent.putExtra("presentRecipientMid", str2);
        }
        if (str3 != null) {
            intent.putExtra("serialNumber", str3);
        }
        if (str != null) {
            intent.putExtra("referenceId", str);
        }
        return intent;
    }
}
