package com.appboy.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;
import com.appboy.b.d;
import com.appboy.f.a;
import com.appboy.f.c;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.activities.AppboyBaseActivity;
import net.hockeyapp.android.j;

@SuppressLint({"SetJavaScriptEnabled"})
public class AppboyWebViewActivity extends AppboyBaseActivity {
    private static final String TAG = c.a(AppboyWebViewActivity.class);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(2);
        requestWindowFeature(5);
        getWindow().setFlags(16777216, 16777216);
        setContentView(R.layout.com_appboy_webview_activity);
        setProgressBarVisibility(true);
        WebView webView = (WebView) findViewById(R.id.com_appboy_webview_activity_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(false);
        webSettings.setPluginState(PluginState.OFF);
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webView.setLayoutParams(new LayoutParams(-1, -1));
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    AppboyWebViewActivity.this.setProgressBarVisibility(true);
                } else {
                    AppboyWebViewActivity.this.setProgressBarVisibility(false);
                }
            }
        });
        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                AppboyWebViewActivity.this.startActivity(intent);
            }
        });
        webView.getSettings().setCacheMode(2);
        webView.setLayerType(2, null);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    if (!a.a.contains(Uri.parse(url).getScheme())) {
                        ActionFactory.createUriActionFromUrlString(url, AppboyWebViewActivity.this.getIntent().getExtras(), false, d.UNKNOWN).execute(view.getContext());
                        AppboyWebViewActivity.this.finish();
                        return true;
                    }
                } catch (Exception e) {
                    c.b(AppboyWebViewActivity.TAG, "Unexpected exception while processing url " + url + ". Passing url back to WebView.", e);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(j.FRAGMENT_URL)) {
            webView.loadUrl(extras.getString(j.FRAGMENT_URL));
        }
    }
}
