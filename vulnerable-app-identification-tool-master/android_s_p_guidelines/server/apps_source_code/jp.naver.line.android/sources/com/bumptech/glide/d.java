package com.bumptech.glide;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.l;
import com.bumptech.glide.load.m;
import com.bumptech.glide.manager.e;
import com.bumptech.glide.manager.n;
import defpackage.aac;
import defpackage.abc;
import defpackage.abh;
import defpackage.ack;
import defpackage.acw;
import defpackage.acz;
import defpackage.ada;
import defpackage.adc;
import defpackage.adf;
import defpackage.adg;
import defpackage.adj;
import defpackage.adn;
import defpackage.adq;
import defpackage.adt;
import defpackage.adu;
import defpackage.aeb;
import defpackage.aei;
import defpackage.aet;
import defpackage.aeu;
import defpackage.aev;
import defpackage.aew;
import defpackage.aex;
import defpackage.aez;
import defpackage.afa;
import defpackage.afb;
import defpackage.afd;
import defpackage.afg;
import defpackage.afh;
import defpackage.afj;
import defpackage.afl;
import defpackage.afn;
import defpackage.afp;
import defpackage.afr;
import defpackage.aft;
import defpackage.afv;
import defpackage.afy;
import defpackage.afz;
import defpackage.aga;
import defpackage.age;
import defpackage.agi;
import defpackage.agv;
import defpackage.agz;
import defpackage.ahf;
import defpackage.ahh;
import defpackage.ahl;
import defpackage.ahn;
import defpackage.aht;
import defpackage.ahz;
import defpackage.aia;
import defpackage.aib;
import defpackage.aid;
import defpackage.aih;
import defpackage.aij;
import defpackage.aiq;
import defpackage.ais;
import defpackage.ait;
import defpackage.aiu;
import defpackage.aiv;
import defpackage.aiw;
import defpackage.aix;
import defpackage.ajd;
import defpackage.ajf;
import defpackage.ajx;
import defpackage.akj;
import defpackage.akm;
import defpackage.alt;
import defpackage.alu;
import defpackage.xz;
import defpackage.yx;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class d implements ComponentCallbacks2 {
    private static volatile d a;
    private static volatile boolean b;
    private final aac c;
    private final abh d;
    private final ack e;
    private final acw f;
    private final f g;
    private final p h;
    private final abc i;
    private final n j;
    private final e k;
    private final List<w> l = new ArrayList();
    private l m = l.NORMAL;

    public void onConfigurationChanged(Configuration configuration) {
    }

    public static d a(Context context) {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    if (b) {
                        throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
                    }
                    b = true;
                    a(context, new e());
                    b = false;
                }
            }
        }
        return a;
    }

    private static void a(Context context, e eVar) {
        context = context.getApplicationContext();
        a h = h();
        List emptyList = Collections.emptyList();
        if (h == null || h.c()) {
            emptyList = new ajf(context).a();
        }
        if (!(h == null || h.a().isEmpty())) {
            Set a = h.a();
            Iterator it = emptyList.iterator();
            while (it.hasNext()) {
                ajd ajd = (ajd) it.next();
                if (a.contains(ajd.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        new StringBuilder("AppGlideModule excludes manifest GlideModule: ").append(ajd);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            for (ajd ajd2 : emptyList) {
                new StringBuilder("Discovered GlideModule from manifest: ").append(ajd2.getClass());
            }
        }
        eVar.a(h != null ? h.b() : null);
        for (ajd ajd22 : emptyList) {
            ajd22.a(context, eVar);
        }
        if (h != null) {
            h.a(context, eVar);
        }
        Object a2 = eVar.a(context);
        for (ajd a3 : emptyList) {
            a3.a(context, a2, a2.h);
        }
        if (h != null) {
            h.a(context, a2, a2.h);
        }
        context.registerComponentCallbacks(a2);
        a = a2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static a h() {
        try {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
        } catch (Exception e) {
            a(e);
        } catch (Exception e2) {
            a(e2);
        } catch (Exception e22) {
            a(e22);
        } catch (Exception e222) {
            a(e222);
        }
        return null;
    }

    private static void a(Exception exception) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exception);
    }

    d(Context context, aac aac, ack ack, abh abh, abc abc, n nVar, e eVar, int i, ajx ajx, Map<Class<?>, z<?, ?>> map) {
        Context context2 = context;
        ack ack2 = ack;
        abh abh2 = abh;
        abc abc2 = abc;
        this.c = aac;
        this.d = abh2;
        this.i = abc2;
        this.e = ack2;
        this.j = nVar;
        this.k = eVar;
        this.f = new acw(ack2, abh2, (b) ajx.v().a(agv.a));
        Resources resources = context.getResources();
        this.h = new p();
        if (VERSION.SDK_INT >= 27) {
            r0.h.a(new agz());
        }
        r0.h.a(new agi());
        agv agv = new agv(r0.h.a(), resources.getDisplayMetrics(), abh2, abc2);
        l aid = new aid(context2, r0.h.a(), abh2, abc2);
        l b = ahn.b(abh);
        l age = new age(agv);
        l ahh = new ahh(agv, abc2);
        l ahz = new ahz(context2);
        aei aev = new aev(resources);
        aew aew = new aew(resources);
        aeu aeu = new aeu(resources);
        aet aet = new aet(resources);
        m aga = new aga(abc2);
        ait ait = new ait();
        aiw aiw = new aiw();
        aei aei = aeu;
        aei = aew;
        aei aei2 = aet;
        Context context3 = context;
        ContentResolver contentResolver = context.getContentResolver();
        aix aix = ait;
        aix aix2 = aiw;
        r0.h.a(ByteBuffer.class, new adg()).a(InputStream.class, new aex(abc2)).a("Bitmap", ByteBuffer.class, Bitmap.class, age).a("Bitmap", InputStream.class, Bitmap.class, ahh).a("Bitmap", ParcelFileDescriptor.class, Bitmap.class, b).a("Bitmap", AssetFileDescriptor.class, Bitmap.class, ahn.a(abh)).a(Bitmap.class, Bitmap.class, afd.a()).a("Bitmap", Bitmap.class, Bitmap.class, new ahl()).a(Bitmap.class, aga).a("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new afy(resources, age)).a("BitmapDrawable", InputStream.class, BitmapDrawable.class, new afy(resources, ahh)).a("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new afy(resources, b)).a(BitmapDrawable.class, new afz(abh2, aga)).a("Gif", InputStream.class, aih.class, new ais(r0.h.a(), aid, abc2)).a("Gif", ByteBuffer.class, aih.class, aid).a(aih.class, new aij()).a(xz.class, xz.class, afd.a()).a("Bitmap", xz.class, Bitmap.class, new aiq(abh2)).a(Uri.class, Drawable.class, ahz).a(Uri.class, Bitmap.class, new ahf(ahz, abh2)).a(new aht()).a(File.class, ByteBuffer.class, new adj()).a(File.class, InputStream.class, new adt()).a(File.class, File.class, new aib()).a(File.class, ParcelFileDescriptor.class, new adq()).a(File.class, File.class, afd.a()).a(new yx(abc2)).a(Integer.TYPE, InputStream.class, aev).a(Integer.TYPE, ParcelFileDescriptor.class, aei).a(Integer.class, InputStream.class, aev).a(Integer.class, ParcelFileDescriptor.class, aei).a(Integer.class, Uri.class, aei).a(Integer.TYPE, AssetFileDescriptor.class, aei2).a(Integer.class, AssetFileDescriptor.class, aei2).a(Integer.TYPE, Uri.class, aei).a(String.class, InputStream.class, new adn()).a(Uri.class, InputStream.class, new adn()).a(String.class, InputStream.class, new afb()).a(String.class, ParcelFileDescriptor.class, new afa()).a(String.class, AssetFileDescriptor.class, new aez()).a(Uri.class, InputStream.class, new afp()).a(Uri.class, InputStream.class, new ada(context.getAssets())).a(Uri.class, ParcelFileDescriptor.class, new acz(context.getAssets())).a(Uri.class, InputStream.class, new afr(context3)).a(Uri.class, InputStream.class, new aft(context3)).a(Uri.class, InputStream.class, new afj(contentResolver)).a(Uri.class, ParcelFileDescriptor.class, new afh(contentResolver)).a(Uri.class, AssetFileDescriptor.class, new afg(contentResolver)).a(Uri.class, InputStream.class, new afl()).a(URL.class, InputStream.class, new afv()).a(Uri.class, File.class, new aeb(context3)).a(adu.class, InputStream.class, new afn()).a(byte[].class, ByteBuffer.class, new adc()).a(byte[].class, InputStream.class, new adf()).a(Uri.class, Uri.class, afd.a()).a(Drawable.class, Drawable.class, afd.a()).a(Drawable.class, Drawable.class, new aia()).a(Bitmap.class, BitmapDrawable.class, new aiu(resources)).a(Bitmap.class, byte[].class, aix).a(Drawable.class, byte[].class, new aiv(abh2, aix, aix2)).a(aih.class, byte[].class, aix2);
        r0.g = new f(context, abc, r0.h, new akj(), ajx, map, aac, i);
    }

    public final abh a() {
        return this.d;
    }

    public final abc b() {
        return this.i;
    }

    public final Context c() {
        return this.g.getBaseContext();
    }

    final e d() {
        return this.k;
    }

    final f e() {
        return this.g;
    }

    public final n f() {
        return this.j;
    }

    private static n c(Context context) {
        alt.a((Object) context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return a(context).j;
    }

    public static w b(Context context) {
        return c(context).a(context);
    }

    public static w a(Activity activity) {
        return c(activity).a(activity);
    }

    public static w a(FragmentActivity fragmentActivity) {
        return c(fragmentActivity).a(fragmentActivity);
    }

    public static w a(Fragment fragment) {
        return c(fragment.getActivity()).a(fragment);
    }

    public static w a(View view) {
        return c(view.getContext()).a(view);
    }

    public final p g() {
        return this.h;
    }

    final boolean a(akm<?> akm) {
        synchronized (this.l) {
            for (w b : this.l) {
                if (b.b((akm) akm)) {
                    return true;
                }
            }
            return false;
        }
    }

    final void a(w wVar) {
        synchronized (this.l) {
            if (this.l.contains(wVar)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.l.add(wVar);
        }
    }

    final void b(w wVar) {
        synchronized (this.l) {
            if (this.l.contains(wVar)) {
                this.l.remove(wVar);
            } else {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
        }
    }

    public void onTrimMemory(int i) {
        alu.a();
        this.e.a(i);
        this.d.a(i);
        this.i.a(i);
    }

    public void onLowMemory() {
        alu.a();
        this.e.a();
        this.d.a();
        this.i.a();
    }
}
