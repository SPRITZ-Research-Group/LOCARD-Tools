package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzjf;
import com.google.android.gms.internal.ads.zzjm;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzkk;
import com.google.android.gms.internal.ads.zzkn;
import com.google.android.gms.internal.ads.zzlw;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzrx;
import com.google.android.gms.internal.ads.zzry;
import com.google.android.gms.internal.ads.zzrz;
import com.google.android.gms.internal.ads.zzsa;
import com.google.android.gms.internal.ads.zzsb;
import com.google.android.gms.internal.ads.zzsd;
import com.google.android.gms.internal.ads.zzxm;

public class AdLoader {
    private final Context mContext;
    private final zzjm zzuk;
    private final zzkk zzul;

    public class Builder {
        private final Context mContext;
        private final zzkn zzum;

        private Builder(Context context, zzkn zzkn) {
            this.mContext = context;
            this.zzum = zzkn;
        }

        public Builder(Context context, String str) {
            this((Context) Preconditions.checkNotNull(context, "context cannot be null"), zzkb.zzig().zzb(context, str, new zzxm()));
        }

        public AdLoader build() {
            try {
                return new AdLoader(this.mContext, this.zzum.zzdh());
            } catch (Throwable e) {
                zzane.zzb("Failed to build AdLoader.", e);
                return null;
            }
        }

        public Builder forAppInstallAd(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzum.zza(new zzrx(onAppInstallAdLoadedListener));
            } catch (Throwable e) {
                zzane.zzc("Failed to add app install ad listener", e);
            }
            return this;
        }

        public Builder forContentAd(OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzum.zza(new zzry(onContentAdLoadedListener));
            } catch (Throwable e) {
                zzane.zzc("Failed to add content ad listener", e);
            }
            return this;
        }

        public Builder forCustomTemplateAd(String str, OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, OnCustomClickListener onCustomClickListener) {
            try {
                this.zzum.zza(str, new zzsa(onCustomTemplateAdLoadedListener), onCustomClickListener == null ? null : new zzrz(onCustomClickListener));
            } catch (Throwable e) {
                zzane.zzc("Failed to add custom template ad listener", e);
            }
            return this;
        }

        public Builder forPublisherAdView(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener, AdSize... adSizeArr) {
            if (adSizeArr == null || adSizeArr.length <= 0) {
                throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
            }
            try {
                this.zzum.zza(new zzsb(onPublisherAdViewLoadedListener), new zzjn(this.mContext, adSizeArr));
            } catch (Throwable e) {
                zzane.zzc("Failed to add publisher banner ad listener", e);
            }
            return this;
        }

        public Builder forUnifiedNativeAd(OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
            try {
                this.zzum.zza(new zzsd(onUnifiedNativeAdLoadedListener));
            } catch (Throwable e) {
                zzane.zzc("Failed to add google native ad listener", e);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener) {
            try {
                this.zzum.zzb(new zzjf(adListener));
            } catch (Throwable e) {
                zzane.zzc("Failed to set AdListener.", e);
            }
            return this;
        }

        public Builder withCorrelator(Correlator correlator) {
            Preconditions.checkNotNull(correlator);
            try {
                this.zzum.zzb(correlator.zzuu);
            } catch (Throwable e) {
                zzane.zzc("Failed to set correlator.", e);
            }
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions) {
            try {
                this.zzum.zza(new zzpl(nativeAdOptions));
            } catch (Throwable e) {
                zzane.zzc("Failed to specify native ad options", e);
            }
            return this;
        }

        public Builder withPublisherAdViewOptions(PublisherAdViewOptions publisherAdViewOptions) {
            try {
                this.zzum.zza(publisherAdViewOptions);
            } catch (Throwable e) {
                zzane.zzc("Failed to specify DFP banner ad options", e);
            }
            return this;
        }
    }

    AdLoader(Context context, zzkk zzkk) {
        this(context, zzkk, zzjm.zzara);
    }

    private AdLoader(Context context, zzkk zzkk, zzjm zzjm) {
        this.mContext = context;
        this.zzul = zzkk;
        this.zzuk = zzjm;
    }

    private final void zza(zzlw zzlw) {
        try {
            this.zzul.zzd(zzjm.zza(this.mContext, zzlw));
        } catch (Throwable e) {
            zzane.zzb("Failed to load ad.", e);
        }
    }

    @Deprecated
    public String getMediationAdapterClassName() {
        try {
            return this.zzul.zzck();
        } catch (Throwable e) {
            zzane.zzc("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public boolean isLoading() {
        try {
            return this.zzul.isLoading();
        } catch (Throwable e) {
            zzane.zzc("Failed to check if ad is loading.", e);
            return false;
        }
    }

    public void loadAd(AdRequest adRequest) {
        zza(adRequest.zzay());
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        zza(publisherAdRequest.zzay());
    }

    public void loadAds(AdRequest adRequest, int i) {
        try {
            this.zzul.zza(zzjm.zza(this.mContext, adRequest.zzay()), i);
        } catch (Throwable e) {
            zzane.zzb("Failed to load ads.", e);
        }
    }
}
