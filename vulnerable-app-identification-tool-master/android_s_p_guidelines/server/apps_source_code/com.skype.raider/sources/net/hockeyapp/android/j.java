package net.hockeyapp.android;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
import net.hockeyapp.android.e.e;
import net.hockeyapp.android.e.f;
import net.hockeyapp.android.f.a;
import net.hockeyapp.android.f.k;
import net.hockeyapp.android.h.b;
import net.hockeyapp.android.h.c;
import net.hockeyapp.android.h.d;

public class j extends DialogFragment implements OnClickListener, k {
    public static final String FRAGMENT_DIALOG = "dialog";
    public static final String FRAGMENT_TAG = "hockey_update_dialog";
    public static final String FRAGMENT_URL = "url";
    public static final String FRAGMENT_VERSION_INFO = "versionInfo";
    private String mUrlString;
    private String mVersionInfo;

    public static j newInstance(String versionInfo, String urlString, boolean dialog) {
        Bundle arguments = new Bundle();
        arguments.putString(FRAGMENT_URL, urlString);
        arguments.putString(FRAGMENT_VERSION_INFO, versionInfo);
        arguments.putBoolean(FRAGMENT_DIALOG, dialog);
        j fragment = new j();
        fragment.setArguments(arguments);
        return fragment;
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout(-1, -1);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle arguments = getArguments();
        this.mUrlString = arguments.getString(FRAGMENT_URL);
        this.mVersionInfo = arguments.getString(FRAGMENT_VERSION_INFO);
        setShowsDialog(arguments.getBoolean(FRAGMENT_DIALOG));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutView();
        k versionHelper = new k(getActivity(), this.mVersionInfo, this);
        TextView nameLabel = (TextView) view.findViewById(b.label_title);
        nameLabel.setText(net.hockeyapp.android.f.j.d(getActivity()));
        nameLabel.setContentDescription(nameLabel.getText());
        final TextView versionLabel = (TextView) view.findViewById(b.label_version);
        final String versionString = String.format(getString(d.hockeyapp_update_version), new Object[]{versionHelper.a()});
        final String fileDate = versionHelper.b();
        String appSizeString = getString(d.hockeyapp_update_unknown_size);
        if (versionHelper.c() >= 0) {
            appSizeString = String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(((float) appSize) / 1048576.0f)}) + " MB";
        } else {
            a.a(new f(getActivity(), this.mUrlString, new net.hockeyapp.android.b.a(this) {
                final /* synthetic */ j d;

                public final void a(e task) {
                    if (task instanceof f) {
                        long appSize = ((f) task).c();
                        String appSizeString = String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(((float) appSize) / 1048576.0f)}) + " MB";
                        versionLabel.setText(this.d.getString(d.hockeyapp_update_version_details_label, new Object[]{versionString, fileDate, appSizeString}));
                    }
                }
            }));
        }
        versionLabel.setText(getString(d.hockeyapp_update_version_details_label, new Object[]{versionString, fileDate, appSizeString}));
        ((Button) view.findViewById(b.button_update)).setOnClickListener(this);
        WebView webView = (WebView) view.findViewById(b.web_update_details);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL("https://sdk.hockeyapp.net/", versionHelper.d(), "text/html", "utf-8", null);
        return view;
    }

    public void onDestroyView() {
        Dialog dialog = getDialog();
        if (dialog != null && getRetainInstance()) {
            dialog.setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void onClick(View view) {
        prepareDownload();
    }

    public int getCurrentVersionCode() {
        int currentVersionCode = -1;
        try {
            return getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 128).versionCode;
        } catch (NameNotFoundException e) {
            return currentVersionCode;
        } catch (NullPointerException e2) {
            return currentVersionCode;
        }
    }

    private void showError(int message) {
        new Builder(getActivity()).setTitle(d.hockeyapp_dialog_error_title).setMessage(message).setCancelable(false).setPositiveButton(d.hockeyapp_dialog_positive_button, null).create().show();
    }

    private static String[] requiredPermissions() {
        ArrayList<String> permissions = new ArrayList();
        if (VERSION.SDK_INT < 19) {
            permissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        return (String[]) permissions.toArray(new String[0]);
    }

    protected void prepareDownload() {
        boolean z = false;
        Context context = getActivity();
        if (net.hockeyapp.android.f.j.c(context)) {
            boolean z2;
            String[] permissions = requiredPermissions();
            int[] iArr;
            if (permissions == null) {
                iArr = null;
            } else {
                int[] iArr2 = new int[permissions.length];
                for (int i = 0; i < permissions.length; i++) {
                    iArr2[i] = context.checkCallingOrSelfPermission(permissions[i]);
                }
                iArr = iArr2;
            }
            for (int i2 : iArr) {
                if (i2 != 0) {
                    z2 = false;
                    break;
                }
            }
            z2 = true;
            if (z2) {
                if (VERSION.SDK_INT >= 26) {
                    if (context.getApplicationInfo().targetSdkVersion < 26 || context.getPackageManager().canRequestPackageInstalls()) {
                        z = true;
                    }
                } else if (VERSION.SDK_INT < 17 || VERSION.SDK_INT >= 21) {
                    z = "1".equals(Secure.getString(context.getContentResolver(), "install_non_market_apps"));
                } else {
                    z = "1".equals(Global.getString(context.getContentResolver(), "install_non_market_apps"));
                }
                if (z) {
                    startDownloadTask();
                    if (getShowsDialog()) {
                        dismiss();
                        return;
                    }
                    return;
                } else if (VERSION.SDK_INT >= 26) {
                    Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
                    intent.setData(Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);
                    return;
                } else {
                    showError(d.hockeyapp_error_install_form_unknown_sources_disabled);
                    return;
                }
            }
            showError(d.hockeyapp_error_no_external_storage_permission);
            return;
        }
        showError(d.hockeyapp_error_no_network_message);
    }

    protected void startDownloadTask() {
        a.a(new e(getActivity(), this.mUrlString, new net.hockeyapp.android.b.a(this) {
            final /* synthetic */ j a;

            {
                this.a = this$0;
            }

            public final void a(Boolean userWantsRetry) {
                if (userWantsRetry.booleanValue()) {
                    this.a.startDownloadTask();
                }
            }

            public final void a(e task) {
            }
        }));
    }

    public View getLayoutView() {
        LinearLayout layout = new LinearLayout(getActivity());
        LayoutInflater.from(getActivity()).inflate(c.hockeyapp_fragment_update, layout);
        return layout;
    }
}
