package com.google.android.gms.internal.measurement;

import android.net.Uri;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class al {
    public static am<Long> A = am.a("measurement.upload.refresh_blacklisted_config_interval", 604800000);
    public static am<Long> B = am.a("measurement.upload.initial_upload_delay_time", 15000);
    public static am<Long> C = am.a("measurement.upload.retry_time", 1800000);
    public static am<Integer> D = am.a("measurement.upload.retry_count", 6);
    public static am<Long> E = am.a("measurement.upload.max_queue_time", 2419200000L);
    public static am<Integer> F = am.a("measurement.lifetimevalue.max_currency_tracked", 4);
    public static am<Integer> G = am.a("measurement.audience.filter_result_max_count", 200);
    public static am<Long> H = am.a("measurement.service_client.idle_disconnect_millis", 5000);
    public static am<Boolean> I = am.a("measurement.test.boolean_flag", false);
    public static am<String> J = am.a("measurement.test.string_flag", "---");
    public static am<Long> K = am.a("measurement.test.long_flag", -1);
    public static am<Integer> L = am.a("measurement.test.int_flag", -2);
    public static am<Double> M = am.a("measurement.test.double_flag");
    public static am<Boolean> N = am.a("measurement.lifetimevalue.user_engagement_tracking_enabled", false);
    public static am<Boolean> O = am.a("measurement.audience.complex_param_evaluation", false);
    public static am<Boolean> P = am.a("measurement.validation.internal_limits_internal_event_params", false);
    public static am<Boolean> Q = am.a("measurement.quality.unsuccessful_update_retry_counter", false);
    public static am<Boolean> R = am.a("measurement.iid.disable_on_collection_disabled", true);
    public static am<Boolean> S = am.a("measurement.app_launch.call_only_when_enabled", true);
    public static am<Boolean> T = am.a("measurement.run_on_worker_inline", true);
    public static am<Boolean> U = am.a("measurement.reset_analytics.persist_time", false);
    private static final gk V;
    private static am<Boolean> W = am.a("measurement.log_third_party_store_events_enabled", false);
    private static am<Boolean> X = am.a("measurement.log_installs_enabled", false);
    private static am<Boolean> Y = am.a("measurement.log_upgrades_enabled", false);
    private static am<Boolean> Z = am.a("measurement.log_androidId_enabled", false);
    public static am<Boolean> a = am.a("measurement.upload_dsid_enabled", false);
    private static am<Boolean> aa = am.a("measurement.audience.dynamic_filters", false);
    public static am<String> b = am.a("measurement.log_tag", "FA");
    public static am<Long> c = am.a("measurement.ad_id_cache_time", 10000);
    public static am<Long> d = am.a("measurement.monitoring.sample_period_millis", 86400000);
    public static am<Long> e = am.a("measurement.config.cache_time", 86400000);
    public static am<String> f = am.a("measurement.config.url_scheme", Constants.SCHEME);
    public static am<String> g = am.a("measurement.config.url_authority", "app-measurement.com");
    public static am<Integer> h = am.a("measurement.upload.max_bundles", 100);
    public static am<Integer> i = am.a("measurement.upload.max_batch_size", 65536);
    public static am<Integer> j = am.a("measurement.upload.max_bundle_size", 65536);
    public static am<Integer> k = am.a("measurement.upload.max_events_per_bundle", (int) Constants.ONE_SECOND);
    public static am<Integer> l = am.a("measurement.upload.max_events_per_day", 100000);
    public static am<Integer> m = am.a("measurement.upload.max_error_events_per_day", (int) Constants.ONE_SECOND);
    public static am<Integer> n = am.a("measurement.upload.max_public_events_per_day", 50000);
    public static am<Integer> o = am.a("measurement.upload.max_conversions_per_day", 500);
    public static am<Integer> p = am.a("measurement.upload.max_realtime_events_per_day", 10);
    public static am<Integer> q = am.a("measurement.store.max_stored_events_per_app", 100000);
    public static am<String> r = am.a("measurement.upload.url", "https://app-measurement.com/a");
    public static am<Long> s = am.a("measurement.upload.backoff_period", 43200000);
    public static am<Long> t = am.a("measurement.upload.window_interval", 3600000);
    public static am<Long> u = am.a("measurement.upload.interval", 3600000);
    public static am<Long> v = am.a("measurement.upload.realtime_upload_interval", 10000);
    public static am<Long> w = am.a("measurement.upload.debug_upload_interval", 1000);
    public static am<Long> x = am.a("measurement.upload.minimum_delay", 500);
    public static am<Long> y = am.a("measurement.alarm_manager.minimum_interval", 60000);
    public static am<Long> z = am.a("measurement.upload.stale_data_deletion_interval", 86400000);

    static {
        String str = "content://com.google.android.gms.phenotype/";
        String valueOf = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
        V = new gk(Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)));
    }
}
