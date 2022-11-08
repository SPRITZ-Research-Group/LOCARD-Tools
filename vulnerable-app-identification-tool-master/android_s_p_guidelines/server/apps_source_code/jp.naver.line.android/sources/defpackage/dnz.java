package defpackage;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.text.style.ImageSpan;
import com.google.android.exoplayer.hls.HlsChunkSource;
import java.util.Map;
import jp.naver.line.android.BuildConfig;
import jp.naver.line.android.R;
import jp.naver.line.android.b;
import kotlin.Metadata;
import kotlin.m;
import kotlin.u;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J,\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00042\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e01H\u0002J,\u00102\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00042\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e01H\u0002J,\u00103\u001a\u00020-2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e012\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0004H\u0002J \u00104\u001a\u00020-2\u0006\u00105\u001a\u00020)2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0004H\u0002J\u001c\u00106\u001a\u00020)2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e01H\u0002J,\u00107\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00042\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e01H\u0002J\u0010\u00108\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020+H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/linecorp/line/ad/MoreMenuAdSpecBuilder;", "", "()V", "BACKGROUND_COLOR", "", "BOTTOM_VDO_HEIGHT", "", "BOTTOM_VDO_WIDTH", "DEFAULT_MIN_INTERVAL_IN_MILLIS", "", "DEFAULT_VIDEO_AUTO_MODE", "DEFAULT_VIDEO_AUTO_RATE", "SERVICE_NAME", "actionButtonAsset", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec$XmlViewSpec;", "actionButtonAssetOfExImage", "actionButtonAssetOfExVideo", "badgeAssetOfEx", "descAsset", "deviderAsset", "imageAsset", "imageAssetOfExImage", "imageOnlyAsset", "muteButtonAssetOfExVideo", "playButtonAssetOfExVideo", "replayAndActionButtonAsset", "replayButtonAsset", "thumbnailAssetOfExVideo", "topDeviderAsset", "videoAssetOfExVideo", "videoEqualizerAsset", "videoPlayButtonAsset", "videoPlayerAsset", "videoProgressBarAsset", "videoProgressBarAssetOfExVideo", "videoThumbnailAsset", "build", "Lcom/linecorp/line/ad/MoreMenuAdServiceSpec;", "application", "Landroid/app/Application;", "buildThumbnailLayoutSpec", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec$LayoutSpec;", "context", "Landroid/content/Context;", "getExpandImageInventorySpec", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec;", "viewType", "key", "spec", "", "getExpandVideoInventorySpec", "getImageLayoutSpec", "getInventorySpec", "layoutSpec", "getThumbnailLayoutSpec", "getVideoInventorySpec", "titleWithBadge", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
/* renamed from: dnz */
public final class dnz {
    public static final dnz a = new dnz();
    private static final eah b = new eah(R.id.infeed_sub_title, eat.Text);
    private static final eah c = new eah(R.id.infeed_item_icon, eat.Image);
    private static final eah d = new eah(R.id.infeed_divider, eat.View);
    private static final eah e = new eah(R.id.ad_image, eat.Image, new dzl[]{new dzg()});
    private static final eah f = new eah(R.id.exp_ad_content_image_view, eat.Image, new dzl[0]);
    private static final eah g = new eah(R.id.exp_ad_content_actionbar_view, eat.Text, new dzl[0]);
    private static final eah h = new eah(R.id.exp_ad_overlay_mark, eat.Image, new dzl[]{new eac(true, true, true, true, true, true)});
    private static final eah i = new eah(R.id.exp_ad_overlay_mute, eat.Image, new dzl[0]);
    private static final eah j = new eah(R.id.exp_ad_content_video_view, eat.Video, new dzl[]{new eac(true, true, true, true, true, true)});
    private static final eah k = new eah(R.id.exp_ad_content_actionbar_view, eat.Text, new dzl[]{new eac(true, true, true, true, true, true)});
    private static final eah l = new eah(R.id.exp_ad_content_image_view, eat.Image, new dzl[0]);
    private static final eah m = new eah(R.id.exp_ad_content_video_loading_view, eat.View, new dzl[]{new eac(false, true, false, false, false, false)});
    private static final eah n = new eah(R.id.exp_ad_overlay_play, eat.View, new dzl[]{new eac(false, false, false, true, true, true)});
    private static final eah o = new eah(R.id.divider, eat.View);
    private static final eah p = new eah(R.id.video_ad_content_thumbnail_view, eat.Image, new dzl[]{new eac(true, true, false, true, true, true)});
    private static final eah q = new eah(R.id.video_ad_content_video_view, eat.Video, new dzl[]{new eac(true, true, true, true, true, true)});
    private static final eah r = new eah(R.id.video_ad_content_play_button, eat.Image, new dzl[]{new eac(false, false, false, true, true, false)});
    private static final eah s = new eah(R.id.video_ad_content_equalizer_icon, eat.Image, new dzl[]{new ead()});
    private static final eah t = new eah(R.id.video_ad_content_video_loading_view, eat.View, new dzl[]{new eac(false, true, false, false, false, false)});
    private static final eah u = new eah(R.id.video_ad_content_replay_button, eat.Text, new dzl[]{new eac(false, false, false, false, false, true)});
    private static final eah v = new eah(R.id.video_ad_content_action_while_playing_button, eat.Text, new dzl[]{new eac(false, false, false, false, false, true)});
    private static final eah w = new eah(R.id.video_ad_content_finish_layout, eat.ViewGroup, new dzl[]{new eac(false, false, false, false, false, true)});

    private dnz() {
    }

    private static dza a(Map<Integer, eah> map, int i, String str) {
        dza dza = new dza(str, eai.AllInOne, new eag(eaj.Linear, R.layout.moremenu_top_image_ad, Color.parseColor("#ffffff"), map, 0, 0, 112), new dzj(i), new dyu(true, true, true, false, null, 88));
        dza.a();
        return dza;
    }

    private static dza a(dzk dzk, int i, String str) {
        int i2 = i;
        dza dza = new dza(str, eai.AllInOne, dzk, new dzj(i), new dyu(true, true, true, false, null, 88));
        dza.a();
        return dza;
    }

    public static dnx a(Application application) {
        dxp dxp;
        ear ear = new ear(eal.MediaAccessor, 1);
        ear.b = "";
        ear.a = "";
        eao eao = new eao(HlsChunkSource.DEFAULT_PLAYLIST_BLACKLIST_MS, new dri(50, 3));
        qce qce = b.g;
        if (qce != null) {
            switch (doa.a[qce.ordinal()]) {
                case 1:
                    dxp = dxp.Beta;
                    break;
                case 2:
                    dxp = dxp.Staging;
                    break;
                case 3:
                    dxp = dxp.Release;
                    break;
                case 4:
                case 5:
                    dxp = dxp.Test;
                    break;
            }
        }
        dox.b("Abnormal situation is detected");
        dxp = dxp.Test;
        eam dnx = new dnx("Moretab", ear, eao);
        Context context = application;
        m[] mVarArr = new m[4];
        Integer valueOf = Integer.valueOf(dpx.TITLE_WITH_BADGE.a());
        eat eat = eat.Text;
        dzl[] dzlArr = new dzl[1];
        dzlArr[0] = new dzv(acom.a(u.a("NEW", new ImageSpan(context, R.drawable.more_banner_img_new02, 0)), u.a("UPDATE", new ImageSpan(context, R.drawable.more_banner_img_update, 0)), u.a("EVENT", new ImageSpan(context, R.drawable.more_banner_img_event, 0)), u.a("SALE", new ImageSpan(context, R.drawable.more_banner_img_sale, 0)), u.a("PR", new ImageSpan(context, R.drawable.more_banner_img_pr, 0)), u.a("AD", new ImageSpan(context, R.drawable.more_banner_img_ad, 0))));
        mVarArr[0] = u.a(valueOf, new eah(R.id.infeed_title, eat, dzlArr));
        mVarArr[1] = u.a(Integer.valueOf(dpx.DESCRIPTION.a()), b);
        mVarArr[2] = u.a(Integer.valueOf(dpx.IMAGE.a()), c);
        mVarArr[3] = u.a(Integer.valueOf(dpx.DIVIDER.a()), d);
        dzk eag = new eag(eaj.Linear, R.layout.moremenu_thumbnail_ad, Color.parseColor("#ffffff"), acom.a(mVarArr), 0, 0, 112);
        dnx.f = acom.a(u.a(BuildConfig.MORETAB_EXPAND_AD_KEY, new dwe(BuildConfig.MORETAB_EXPAND_AD_KEY)), u.a(BuildConfig.MORETAB_UPPER_BANNER_KEY, new dwe(BuildConfig.MORETAB_UPPER_BANNER_KEY)), u.a(BuildConfig.MORETAB_THUMBNAIL01_KEY, new dwe(BuildConfig.MORETAB_THUMBNAIL01_KEY)), u.a(BuildConfig.MORETAB_THUMBNAIL02_KEY, new dwe(BuildConfig.MORETAB_THUMBNAIL02_KEY)), u.a(BuildConfig.MORETAB_THUMBNAIL03_KEY, new dwe(BuildConfig.MORETAB_THUMBNAIL03_KEY)), u.a(BuildConfig.MORETAB_DOWN_BANNER_IMAGE_KEY, new dwe(BuildConfig.MORETAB_DOWN_BANNER_IMAGE_KEY)), u.a(BuildConfig.MORETAB_DOWN_BANNER_VIDEO_KEY, new dwe(BuildConfig.MORETAB_DOWN_BANNER_VIDEO_KEY)));
        dza[] dzaArr = new dza[8];
        dzaArr[0] = dnz.a(acol.a(u.a(Integer.valueOf(dpx.IMAGE.a()), e)), 2, BuildConfig.MORETAB_UPPER_BANNER_KEY);
        String str = BuildConfig.MORETAB_EXPAND_AD_KEY;
        Map a = acom.a(u.a(Integer.valueOf(dpx.IMAGE.a()), f), u.a(Integer.valueOf(dpx.TEXT_BUTTON.a()), g), u.a(Integer.valueOf(dpx.EMBEDED_BADGE.a()), h));
        eai eai = eai.AllInOne;
        dzk eag2 = new eag(eaj.Relative, R.layout.moremenu_top_expandable_ad, Color.parseColor("#ffffff"), a, 0, 0, 112);
        dzi dzj = new dzj(0);
        dyt[] dytArr = new dyt[5];
        dyv dyv = dyu.a;
        dytArr[0] = dyv.a();
        dyv = dyu.a;
        dytArr[1] = dyv.b();
        dyv = dyu.a;
        dytArr[2] = dyv.d();
        dyv = dyu.a;
        dyt f = dyv.f();
        f.b = "exImageActionButtonClick";
        f.r();
        f.d(dpx.TEXT_BUTTON.a());
        f.d(true);
        f.u();
        dytArr[3] = f;
        dyv = dyu.a;
        dytArr[4] = dyv.f();
        dza dza = new dza(str, eai, eag2, dzj, new dyu(true, true, true, false, acnr.d(dytArr), 24));
        dza.a();
        dza.e();
        dzaArr[1] = dza;
        String str2 = BuildConfig.MORETAB_EXPAND_AD_KEY;
        Map a2 = acom.a(u.a(Integer.valueOf(dpx.VIDEO_PLAYER.a()), j), u.a(Integer.valueOf(dpx.EMBEDED_BADGE.a()), h), u.a(Integer.valueOf(dpx.THUMBNAIL.a()), l), u.a(Integer.valueOf(dpx.EMBEDED_MUTE_BUTTON.a()), i), u.a(Integer.valueOf(dpx.TEXT_BUTTON.a()), k), u.a(Integer.valueOf(dpx.PROGRESS_BAR.a()), m), u.a(Integer.valueOf(dpx.EMBEDED_PLAY_BUTTON.a()), n));
        eai = eai.AllInOne;
        dzk eag3 = new eag(eaj.Relative, R.layout.moremenu_top_expandable_ad, Color.parseColor("#ffffff"), a2, 0, 0, 112);
        dzi dzj2 = new dzj(1);
        dytArr = new dyt[8];
        dyv = dyu.a;
        dytArr[0] = dyv.a();
        dyv = dyu.a;
        dytArr[1] = dyv.b();
        dyv = dyu.a;
        dytArr[2] = dyv.c();
        dyv = dyu.a;
        dytArr[3] = dyv.d();
        dyv = dyu.a;
        dytArr[4] = dyv.e();
        dyv = dyu.a;
        f = dyv.f();
        f.b = "exVideoThumbnailClick";
        f.r();
        f.d(dpx.THUMBNAIL.a());
        f.d(true);
        f.u();
        f.w();
        dytArr[5] = f;
        dyv = dyu.a;
        f = dyv.f();
        f.b = "exVideoActionButtonClick";
        f.r();
        f.d(dpx.TEXT_BUTTON.a());
        f.w();
        dytArr[6] = f;
        dyv = dyu.a;
        f = dyv.g();
        f.a = new dyr(dys.Click, true);
        f.b = "exVideoPlayClick";
        f.r();
        f.d(dpx.VIDEO_PLAYER.a());
        f.w();
        f.c(false);
        dytArr[7] = f;
        dza dza2 = new dza(str2, eai, eag3, dzj2, new dyu(true, true, true, false, acnr.d(dytArr), 24));
        dza2.a();
        dza2.e();
        dzaArr[2] = dza2;
        dzaArr[3] = dnz.a(eag, 3, BuildConfig.MORETAB_THUMBNAIL01_KEY);
        dzaArr[4] = dnz.a(eag, 4, BuildConfig.MORETAB_THUMBNAIL02_KEY);
        dzaArr[5] = dnz.a(eag, 5, BuildConfig.MORETAB_THUMBNAIL03_KEY);
        dzaArr[6] = dnz.a(acol.a(u.a(Integer.valueOf(dpx.IMAGE.a()), e)), 6, BuildConfig.MORETAB_DOWN_BANNER_IMAGE_KEY);
        String str3 = BuildConfig.MORETAB_DOWN_BANNER_VIDEO_KEY;
        Map a3 = acom.a(u.a(Integer.valueOf(dpx.VIDEO_PLAYER.a()), q), u.a(Integer.valueOf(dpx.THUMBNAIL.a()), p), u.a(Integer.valueOf(dpx.EMBEDED_PLAY_BUTTON.a()), r), u.a(Integer.valueOf(dpx.EQUALIZER.a()), s), u.a(Integer.valueOf(dpx.PROGRESS_BAR.a()), t), u.a(Integer.valueOf(dpx.REPLAY_BUTTON.a()), u), u.a(Integer.valueOf(dpx.ACTION_BUTTON.a()), v), u.a(Integer.valueOf(dpx.REPLAY_ACTION_BUTTON_CONTAINER.a()), w));
        eai eai2 = eai.AllInOne;
        dzk eag4 = new eag(eaj.Relative, R.layout.moremenu_bottom_video_ad, Color.parseColor("#ffffff"), a3, 960, 540, 16);
        dzi dzj3 = new dzj(7);
        r10 = new dyt[10];
        dyv dyv2 = dyu.a;
        r10[0] = dyv.a();
        dyv2 = dyu.a;
        r10[1] = dyv.b();
        dyv2 = dyu.a;
        r10[2] = dyv.c();
        dyv2 = dyu.a;
        r10[3] = dyv.d();
        dyv dyv3 = dyu.a;
        r10[4] = dyv.e();
        dyv3 = dyu.a;
        dyt f2 = dyv.f();
        f2.b = "videoThumbnailClick";
        f2.r();
        f2.d(dpx.THUMBNAIL.a());
        f2.d(true);
        f2.u();
        f2.w();
        r10[5] = f2;
        dyv3 = dyu.a;
        f2 = dyv.f();
        f2.b = "videoActionButtonClick";
        f2.r();
        f2.d(dpx.ACTION_BUTTON.a());
        f2.w();
        r10[6] = f2;
        dyv3 = dyu.a;
        f2 = dyv.f();
        f2.a = new dyr(dys.Click, true);
        f2.b = "videoReplyButtonClick";
        f2.r();
        f2.d(dpx.REPLAY_BUTTON.a());
        f2.w();
        f2.c(false);
        r10[7] = f2;
        dyv3 = dyu.a;
        f2 = dyv.f();
        f2.a = new dyr(dys.Click, true);
        f2.b = "videoPlyButtonClick";
        f2.r();
        f2.d(dpx.EMBEDED_PLAY_BUTTON.a());
        f2.w();
        f2.c(false);
        r10[8] = f2;
        dyv dyv4 = dyu.a;
        r10[9] = dyv.g();
        dza dza3 = new dza(str3, eai2, eag4, dzj3, new dyu(true, true, true, false, acnr.d(r10), 24));
        dza3.a();
        dza3.c();
        dzaArr[7] = dza3;
        dnx.e = acnr.d(dzaArr);
        dnx.g = dxp;
        dnx.d();
        return dnx;
    }
}
