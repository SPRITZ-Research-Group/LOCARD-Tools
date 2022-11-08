package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import com.skype.android.video.hw.extension.SliqConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import net.hockeyapp.android.f.a;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.g;
import net.hockeyapp.android.h.d;
import net.hockeyapp.android.views.PaintView;

public class PaintActivity extends Activity {
    private PaintView a;
    private Uri b;

    static /* synthetic */ void b(PaintActivity x0) {
        x0.a = new PaintView(x0, x0.b, x0.getResources().getDisplayMetrics().widthPixels, x0.getResources().getDisplayMetrics().heightPixels);
        View linearLayout = new LinearLayout(x0);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        View linearLayout2 = new LinearLayout(x0);
        linearLayout2.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout2.setGravity(17);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        linearLayout2.addView(x0.a);
        x0.setContentView(linearLayout);
        Toast.makeText(x0, d.hockeyapp_paint_indicator_toast, 1).show();
    }

    @SuppressLint({"StaticFieldLeak"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.getParcelable("imageUri") == null) {
            e.e();
            return;
        }
        this.b = (Uri) extras.getParcelable("imageUri");
        a.a(new AsyncTask<Void, Object, Integer>(this) {
            final /* synthetic */ PaintActivity a;

            {
                this.a = this$0;
            }

            protected final /* synthetic */ void onPostExecute(Object obj) {
                Integer num = (Integer) obj;
                this.a.setRequestedOrientation(num.intValue());
                if ((this.a.getResources().getDisplayMetrics().widthPixels > this.a.getResources().getDisplayMetrics().heightPixels ? 0 : 1) != num.intValue()) {
                    e.b();
                } else {
                    PaintActivity.b(this.a);
                }
            }

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                return Integer.valueOf(g.a(this.a, this.a.b));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, getString(d.hockeyapp_paint_menu_save));
        menu.add(0, 2, 0, getString(d.hockeyapp_paint_menu_undo));
        menu.add(0, 3, 0, getString(d.hockeyapp_paint_menu_clear));
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                a();
                return true;
            case 2:
                this.a.b();
                return true;
            case 3:
                this.a.a();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || this.a.c()) {
            return super.onKeyDown(keyCode, event);
        }
        OnClickListener dialogClickListener = new OnClickListener(this) {
            final /* synthetic */ PaintActivity a;

            {
                this.a = this$0;
            }

            public final void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                        this.a.finish();
                        return;
                    case -1:
                        this.a.a();
                        return;
                    default:
                        return;
                }
            }
        };
        new Builder(this).setMessage(d.hockeyapp_paint_dialog_message).setPositiveButton(d.hockeyapp_paint_dialog_positive_button, dialogClickListener).setNegativeButton(d.hockeyapp_paint_dialog_negative_button, dialogClickListener).setNeutralButton(d.hockeyapp_paint_dialog_neutral_button, dialogClickListener).show();
        return true;
    }

    @SuppressLint({"StaticFieldLeak"})
    private void a() {
        this.a.setDrawingCacheEnabled(true);
        final Bitmap bitmap = this.a.getDrawingCache();
        a.a(new AsyncTask<Void, Object, Boolean>(this) {
            File a;
            final /* synthetic */ PaintActivity c;

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                return a();
            }

            protected final /* synthetic */ void onPostExecute(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    Intent intent = new Intent();
                    intent.putExtra("imageUri", Uri.fromFile(this.a));
                    if (this.c.getParent() == null) {
                        this.c.setResult(-1, intent);
                    } else {
                        this.c.getParent().setResult(-1, intent);
                    }
                } else if (this.c.getParent() == null) {
                    this.c.setResult(0);
                } else {
                    this.c.getParent().setResult(0);
                }
                this.c.finish();
            }

            private Boolean a() {
                File hockeyAppCache = new File(this.c.getCacheDir(), "HockeyApp");
                if (!hockeyAppCache.exists() && !hockeyAppCache.mkdir()) {
                    return Boolean.valueOf(false);
                }
                String imageName = this.c.a(this.c.b, this.c.b.getLastPathSegment());
                this.a = new File(hockeyAppCache, imageName + ".jpg");
                int suffix = 1;
                while (this.a.exists()) {
                    this.a = new File(hockeyAppCache, imageName + "_" + suffix + ".jpg");
                    suffix++;
                }
                try {
                    FileOutputStream out = new FileOutputStream(this.a);
                    bitmap.compress(CompressFormat.JPEG, 100, out);
                    out.close();
                    return Boolean.valueOf(true);
                } catch (IOException e) {
                    e.f();
                    return Boolean.valueOf(false);
                }
            }
        });
    }

    private String a(Uri uri, String fallback) {
        String path = null;
        Cursor metaCursor = getApplicationContext().getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (metaCursor != null) {
            try {
                if (metaCursor.moveToFirst()) {
                    path = metaCursor.getString(0);
                }
                metaCursor.close();
            } catch (Throwable th) {
                metaCursor.close();
            }
        }
        return path == null ? fallback : new File(path).getName();
    }
}
