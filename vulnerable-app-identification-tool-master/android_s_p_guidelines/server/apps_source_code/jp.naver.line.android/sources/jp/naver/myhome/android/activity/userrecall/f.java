package jp.naver.myhome.android.activity.userrecall;

import android.app.Activity;
import android.os.AsyncTask.Status;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.bj;
import com.linecorp.rxeventbus.IntervalFilter;
import com.linecorp.rxeventbus.IntervalFilterType;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import com.linecorp.square.chat.ui.view.mention.NeedMoreSquareMentionListEvent;
import com.linecorp.square.chat.ui.view.mention.SquareMentionDataItem;
import com.linecorp.square.chat.ui.view.mention.SquareMentionItemClickEvent;
import com.linecorp.square.chat.ui.view.mention.SquarePostMentionAdapter;
import com.linecorp.square.chat.ui.view.mention.SquarePostMentionLoader;
import com.linecorp.square.chat.ui.view.mention.SquarePostMentionLoaderListener;
import com.linecorp.square.group.SquareGroupUtils;
import defpackage.tiq;
import defpackage.tmc;
import defpackage.tmd;
import defpackage.wce;
import defpackage.wcf;
import defpackage.wcg;
import defpackage.wch;
import java.util.HashMap;
import java.util.List;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.chathistory.autosuggestion.AutoSuggestionHelper;
import jp.naver.line.android.activity.chathistory.autosuggestion.h;
import jp.naver.line.android.util.at;
import jp.naver.myhome.android.model2.am;

public final class f {
    private final a a = new a(at.b());
    private final Activity b;
    private final View c;
    private final RecyclerView d;
    private final View e;
    private final View f;
    private int g = -1;
    private final b h;
    private final SquarePostMentionAdapter i;
    private final UserRecallEditText j;
    private String k;
    private h l;
    private final HashMap<String, List<am>> m = new HashMap();
    private i n;
    private SquarePostMentionLoader o;

    public f(boolean z, UserRecallEditText userRecallEditText, View view) {
        this.a.b(this);
        this.b = (Activity) view.getContext();
        this.c = view;
        this.j = userRecallEditText;
        this.h = new b(this.a, view.getContext(), userRecallEditText, z);
        this.i = new SquarePostMentionAdapter(this.a, view.getContext(), z);
        this.d = (RecyclerView) view.findViewById(R.id.home_writing_suggestion_listview);
        bj linearLayoutManager = new LinearLayoutManager();
        linearLayoutManager.b(1);
        this.d.setLayoutManager(linearLayoutManager);
        this.d.setAdapter(this.h);
        this.e = view.findViewById(R.id.home_writing_suggestion_listview_layout);
        this.f = view.findViewById(R.id.home_writing_hashtag_searching_layout);
    }

    public final a a() {
        return this.a;
    }

    @Subscribe(a = SubscriberType.MAIN)
    @IntervalFilter(a = IntervalFilterType.DEBOUNCE, b = 500)
    public final void onLoadMentionSuggestionTargetEvent(g gVar) {
        if (!(this.n == null || this.n.getStatus() == Status.FINISHED)) {
            this.n.cancel(false);
            this.n = null;
        }
        if (!tiq.a(this.b)) {
            List list = gVar.a;
            String str = gVar.b;
            SquareGroupUtils squareGroupUtils = SquareGroupUtils.a;
            if (SquareGroupUtils.a(gVar.b)) {
                this.o = new SquarePostMentionLoader(((LineApplication) this.b.getApplication()).g(), gVar.b, new SquarePostMentionLoaderListener(this.b, this.i, this.a, this.j));
            }
            if (tmc.a(list)) {
                if (!TextUtils.isEmpty(str)) {
                    this.n = new i(this.j);
                    this.n.a(str);
                    this.n.executeOnExecutor(at.b(), new Void[0]);
                }
                return;
            }
            this.n = new i(this.j);
            this.n.a(list);
            this.n.executeOnExecutor(at.b(), new Void[0]);
        }
    }

    public final void a(List<Long> list, String str) {
        this.k = str;
        if (tmc.a(list) && TextUtils.isEmpty(str)) {
            this.j.setFilteredMidList(null);
        } else {
            this.a.a(new g(list, str));
        }
    }

    public final void a(h hVar) {
        this.l = hVar;
    }

    @Subscribe(a = SubscriberType.MAIN)
    @IntervalFilter(a = IntervalFilterType.DEBOUNCE, b = 1000)
    public final void onQueryHashTag(wcf wcf) {
        if (this.j.e()) {
            if (this.g == 2 && b()) {
                a(3, null);
            }
            this.d.setAdapter(this.h);
            final String a = wcf.a();
            at.a(new Runnable(this) {
                final /* synthetic */ f b;

                public final void run() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.myhome.android.activity.userrecall.f.1.run():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
                    /*
                    r13 = this;
                    r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01d2 }
                    r0.<init>();	 Catch:{ Exception -> 0x01d2 }
                    r1 = new java.util.HashSet;	 Catch:{ Exception -> 0x01d2 }
                    r1.<init>();	 Catch:{ Exception -> 0x01d2 }
                    r2 = r13.b;	 Catch:{ Exception -> 0x01d2 }
                    r2 = r2.k;	 Catch:{ Exception -> 0x01d2 }
                    r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x01d2 }
                    r3 = -1;	 Catch:{ Exception -> 0x01d2 }
                    r4 = 2;	 Catch:{ Exception -> 0x01d2 }
                    r5 = 3;	 Catch:{ Exception -> 0x01d2 }
                    r6 = 20;	 Catch:{ Exception -> 0x01d2 }
                    r7 = 1;	 Catch:{ Exception -> 0x01d2 }
                    r8 = 0;	 Catch:{ Exception -> 0x01d2 }
                    if (r2 == 0) goto L_0x009e;	 Catch:{ Exception -> 0x01d2 }
                L_0x001d:
                    r2 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r9 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r9 = r9.length();	 Catch:{ Exception -> 0x01d2 }
                    if (r9 > r7) goto L_0x0029;	 Catch:{ Exception -> 0x01d2 }
                L_0x0027:
                    r5 = 20;	 Catch:{ Exception -> 0x01d2 }
                L_0x0029:
                    r2 = defpackage.whh.a(r2, r5);	 Catch:{ Exception -> 0x01d2 }
                    r2 = r2.iterator();	 Catch:{ Exception -> 0x01d2 }
                L_0x0031:
                    r5 = r2.hasNext();	 Catch:{ Exception -> 0x01d2 }
                    if (r5 == 0) goto L_0x005c;	 Catch:{ Exception -> 0x01d2 }
                L_0x0037:
                    r5 = r2.next();	 Catch:{ Exception -> 0x01d2 }
                    r5 = (java.lang.String) r5;	 Catch:{ Exception -> 0x01d2 }
                    r6 = "#";	 Catch:{ Exception -> 0x01d2 }
                    r9 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x01d2 }
                    r6 = r6.concat(r9);	 Catch:{ Exception -> 0x01d2 }
                    r6 = jp.naver.myhome.android.activity.userrecall.a.a(r6, r3, r7);	 Catch:{ Exception -> 0x01d2 }
                    r0.add(r6);	 Catch:{ Exception -> 0x01d2 }
                    r6 = "#";	 Catch:{ Exception -> 0x01d2 }
                    r5 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x01d2 }
                    r5 = r6.concat(r5);	 Catch:{ Exception -> 0x01d2 }
                    r1.add(r5);	 Catch:{ Exception -> 0x01d2 }
                    goto L_0x0031;	 Catch:{ Exception -> 0x01d2 }
                L_0x005c:
                    r2 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r2 = r2.length();	 Catch:{ Exception -> 0x01d2 }
                    if (r2 < r4) goto L_0x0194;
                L_0x0064:
                    r2 = r13.b;	 Catch:{ Exception -> 0x0194 }
                    r2 = r2.k;	 Catch:{ Exception -> 0x0194 }
                    r2 = defpackage.whx.a(r2);	 Catch:{ Exception -> 0x0194 }
                    r4 = r3;	 Catch:{ Exception -> 0x0194 }
                    r2 = r2.d(r4);	 Catch:{ Exception -> 0x0194 }
                    r2 = r2.iterator();	 Catch:{ Exception -> 0x0194 }
                L_0x0078:
                    r4 = r2.hasNext();	 Catch:{ Exception -> 0x0194 }
                    if (r4 == 0) goto L_0x0194;	 Catch:{ Exception -> 0x0194 }
                L_0x007e:
                    r4 = r2.next();	 Catch:{ Exception -> 0x0194 }
                    r4 = (jp.naver.myhome.android.model2.am) r4;	 Catch:{ Exception -> 0x0194 }
                    r5 = r4.a();	 Catch:{ Exception -> 0x0194 }
                    r5 = r1.contains(r5);	 Catch:{ Exception -> 0x0194 }
                    if (r5 != 0) goto L_0x0078;	 Catch:{ Exception -> 0x0194 }
                L_0x008e:
                    r5 = r4.a();	 Catch:{ Exception -> 0x0194 }
                    r4 = r4.b();	 Catch:{ Exception -> 0x0194 }
                    r4 = jp.naver.myhome.android.activity.userrecall.a.a(r5, r4, r8);	 Catch:{ Exception -> 0x0194 }
                    r0.add(r4);	 Catch:{ Exception -> 0x0194 }
                    goto L_0x0078;
                L_0x009e:
                    r2 = r13.b;	 Catch:{ Exception -> 0x01d2 }
                    r2 = r2.m;	 Catch:{ Exception -> 0x01d2 }
                    r9 = r13.b;	 Catch:{ Exception -> 0x01d2 }
                    r9 = r9.k;	 Catch:{ Exception -> 0x01d2 }
                    r2 = r2.get(r9);	 Catch:{ Exception -> 0x01d2 }
                    r2 = (java.util.List) r2;	 Catch:{ Exception -> 0x01d2 }
                    if (r2 != 0) goto L_0x00d8;
                L_0x00b2:
                    r9 = r13.b;	 Catch:{ Exception -> 0x00d7 }
                    r9 = r9.k;	 Catch:{ Exception -> 0x00d7 }
                    r9 = defpackage.whx.a(r9);	 Catch:{ Exception -> 0x00d7 }
                    r10 = r13.b;	 Catch:{ Exception -> 0x00d7 }
                    r10 = r10.k;	 Catch:{ Exception -> 0x00d7 }
                    r9 = r9.e(r10);	 Catch:{ Exception -> 0x00d7 }
                    r2 = r13.b;	 Catch:{ Exception -> 0x00d5 }
                    r2 = r2.m;	 Catch:{ Exception -> 0x00d5 }
                    r10 = r13.b;	 Catch:{ Exception -> 0x00d5 }
                    r10 = r10.k;	 Catch:{ Exception -> 0x00d5 }
                    r2.put(r10, r9);	 Catch:{ Exception -> 0x00d5 }
                L_0x00d5:
                    r2 = r9;
                    goto L_0x00d8;
                L_0x00d8:
                    if (r2 == 0) goto L_0x0123;
                L_0x00da:
                    r9 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r9 = r9.length();	 Catch:{ Exception -> 0x01d2 }
                    if (r9 > r7) goto L_0x00e4;	 Catch:{ Exception -> 0x01d2 }
                L_0x00e2:
                    r5 = 20;	 Catch:{ Exception -> 0x01d2 }
                L_0x00e4:
                    r6 = 0;	 Catch:{ Exception -> 0x01d2 }
                    r9 = 0;	 Catch:{ Exception -> 0x01d2 }
                L_0x00e6:
                    r10 = r2.size();	 Catch:{ Exception -> 0x01d2 }
                    if (r6 >= r10) goto L_0x0123;	 Catch:{ Exception -> 0x01d2 }
                L_0x00ec:
                    r10 = r2.get(r6);	 Catch:{ Exception -> 0x01d2 }
                    r10 = (jp.naver.myhome.android.model2.am) r10;	 Catch:{ Exception -> 0x01d2 }
                    r11 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r11 = android.text.TextUtils.isEmpty(r11);	 Catch:{ Exception -> 0x01d2 }
                    if (r11 != 0) goto L_0x0106;	 Catch:{ Exception -> 0x01d2 }
                L_0x00fa:
                    r11 = r10.a();	 Catch:{ Exception -> 0x01d2 }
                    r12 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r11 = r11.contains(r12);	 Catch:{ Exception -> 0x01d2 }
                    if (r11 == 0) goto L_0x0120;	 Catch:{ Exception -> 0x01d2 }
                L_0x0106:
                    r11 = r10.a();	 Catch:{ Exception -> 0x01d2 }
                    r12 = r10.b();	 Catch:{ Exception -> 0x01d2 }
                    r11 = jp.naver.myhome.android.activity.userrecall.a.a(r11, r12, r8);	 Catch:{ Exception -> 0x01d2 }
                    r0.add(r11);	 Catch:{ Exception -> 0x01d2 }
                    r10 = r10.a();	 Catch:{ Exception -> 0x01d2 }
                    r1.add(r10);	 Catch:{ Exception -> 0x01d2 }
                    r9 = r9 + 1;	 Catch:{ Exception -> 0x01d2 }
                    if (r9 >= r5) goto L_0x0123;	 Catch:{ Exception -> 0x01d2 }
                L_0x0120:
                    r6 = r6 + 1;	 Catch:{ Exception -> 0x01d2 }
                    goto L_0x00e6;	 Catch:{ Exception -> 0x01d2 }
                L_0x0123:
                    r2 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r2 = r2.length();	 Catch:{ Exception -> 0x01d2 }
                    if (r2 < r4) goto L_0x0165;
                L_0x012b:
                    r2 = r13.b;	 Catch:{ Exception -> 0x0165 }
                    r2 = r2.k;	 Catch:{ Exception -> 0x0165 }
                    r2 = defpackage.whx.a(r2);	 Catch:{ Exception -> 0x0165 }
                    r4 = r3;	 Catch:{ Exception -> 0x0165 }
                    r2 = r2.d(r4);	 Catch:{ Exception -> 0x0165 }
                    r2 = r2.iterator();	 Catch:{ Exception -> 0x0165 }
                L_0x013f:
                    r4 = r2.hasNext();	 Catch:{ Exception -> 0x0165 }
                    if (r4 == 0) goto L_0x0165;	 Catch:{ Exception -> 0x0165 }
                L_0x0145:
                    r4 = r2.next();	 Catch:{ Exception -> 0x0165 }
                    r4 = (jp.naver.myhome.android.model2.am) r4;	 Catch:{ Exception -> 0x0165 }
                    r5 = r4.a();	 Catch:{ Exception -> 0x0165 }
                    r5 = r1.contains(r5);	 Catch:{ Exception -> 0x0165 }
                    if (r5 != 0) goto L_0x013f;	 Catch:{ Exception -> 0x0165 }
                L_0x0155:
                    r5 = r4.a();	 Catch:{ Exception -> 0x0165 }
                    r4 = r4.b();	 Catch:{ Exception -> 0x0165 }
                    r4 = jp.naver.myhome.android.activity.userrecall.a.a(r5, r4, r8);	 Catch:{ Exception -> 0x0165 }
                    r0.add(r4);	 Catch:{ Exception -> 0x0165 }
                    goto L_0x013f;
                L_0x0165:
                    r1 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r1 = r1.isEmpty();	 Catch:{ Exception -> 0x01d2 }
                    if (r1 == 0) goto L_0x0194;	 Catch:{ Exception -> 0x01d2 }
                L_0x016d:
                    r1 = r0.isEmpty();	 Catch:{ Exception -> 0x01d2 }
                    if (r1 != 0) goto L_0x0194;	 Catch:{ Exception -> 0x01d2 }
                L_0x0173:
                    r1 = r13.b;	 Catch:{ Exception -> 0x01d2 }
                    r1 = r1.k;	 Catch:{ Exception -> 0x01d2 }
                    r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x01d2 }
                    if (r1 != 0) goto L_0x018c;	 Catch:{ Exception -> 0x01d2 }
                L_0x017f:
                    r1 = com.linecorp.square.group.SquareGroupUtils.a;	 Catch:{ Exception -> 0x01d2 }
                    r1 = r13.b;	 Catch:{ Exception -> 0x01d2 }
                    r1 = r1.k;	 Catch:{ Exception -> 0x01d2 }
                    r1 = com.linecorp.square.group.SquareGroupUtils.a(r1);	 Catch:{ Exception -> 0x01d2 }
                    goto L_0x018d;	 Catch:{ Exception -> 0x01d2 }
                L_0x018c:
                    r1 = 0;	 Catch:{ Exception -> 0x01d2 }
                L_0x018d:
                    r1 = jp.naver.myhome.android.activity.userrecall.a.a(r1);	 Catch:{ Exception -> 0x01d2 }
                    r0.add(r8, r1);	 Catch:{ Exception -> 0x01d2 }
                L_0x0194:
                    r1 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r1 = r1.length();	 Catch:{ Exception -> 0x01d2 }
                    if (r1 != r7) goto L_0x01c3;	 Catch:{ Exception -> 0x01d2 }
                L_0x019c:
                    r1 = r0.isEmpty();	 Catch:{ Exception -> 0x01d2 }
                    if (r1 == 0) goto L_0x01c3;	 Catch:{ Exception -> 0x01d2 }
                L_0x01a2:
                    r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01d2 }
                    r2 = "#";	 Catch:{ Exception -> 0x01d2 }
                    r1.<init>(r2);	 Catch:{ Exception -> 0x01d2 }
                    r2 = r3;	 Catch:{ Exception -> 0x01d2 }
                    r1.append(r2);	 Catch:{ Exception -> 0x01d2 }
                    r1 = r1.toString();	 Catch:{ Exception -> 0x01d2 }
                    r2 = defpackage.wtl.b(r1);	 Catch:{ Exception -> 0x01d2 }
                    r2 = r2.size();	 Catch:{ Exception -> 0x01d2 }
                    if (r2 <= 0) goto L_0x01c3;	 Catch:{ Exception -> 0x01d2 }
                L_0x01bc:
                    r1 = jp.naver.myhome.android.activity.userrecall.a.a(r1, r3, r8);	 Catch:{ Exception -> 0x01d2 }
                    r0.add(r1);	 Catch:{ Exception -> 0x01d2 }
                L_0x01c3:
                    r1 = r13.b;	 Catch:{ Exception -> 0x01d2 }
                    r1 = r1.b;	 Catch:{ Exception -> 0x01d2 }
                    r2 = new jp.naver.myhome.android.activity.userrecall.f$1$1;	 Catch:{ Exception -> 0x01d2 }
                    r2.<init>(r13, r0);	 Catch:{ Exception -> 0x01d2 }
                    r1.runOnUiThread(r2);	 Catch:{ Exception -> 0x01d2 }
                    return;
                L_0x01d2:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: jp.naver.myhome.android.activity.userrecall.f.1.run():void");
                }
            });
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onQueryUserMention(wcg wcg) {
        final String a = wcg.a();
        this.d.setAdapter(d() ? this.i : this.h);
        at.a(new Runnable(this) {
            final /* synthetic */ f b;

            public final void run() {
                final List a = this.b.j.a(a);
                this.b.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public final void run() {
                        if (this.b.b.d()) {
                            f.a(this.b.b, a);
                            return;
                        }
                        if (!tiq.a(this.b.b.b)) {
                            if (a.isEmpty()) {
                                this.b.b.c();
                            } else {
                                this.b.b.a(2, Boolean.FALSE);
                            }
                            this.b.b.h.a(a);
                            this.b.b.h.a();
                            this.b.b.h.a(a);
                            this.b.b.h.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    public final boolean b() {
        return this.c.getVisibility() == 0;
    }

    public final void c() {
        if (b()) {
            this.c.setVisibility(8);
            if (this.l != null) {
                this.l.b();
            }
        }
    }

    private void a(int i, Boolean bool) {
        int i2 = 0;
        if (!b()) {
            this.c.setVisibility(0);
            if (this.l != null) {
                this.l.a();
            }
        }
        if (this.g != i) {
            this.g = i;
            View view = this.e;
            int i3 = (i == 2 || i == 3) ? 0 : 8;
            view.setVisibility(i3);
            view = this.f;
            if (i != 3) {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
        if (bool != null) {
            this.d.setPadding(this.d.getPaddingLeft(), tmd.a(this.c.getContext(), bool.booleanValue() ? 7.5f : 5.0f), this.d.getPaddingRight(), this.d.getPaddingBottom());
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onHideSuggestionWindow(wce wce) {
        c();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onShowSuggestionWindow(wch wch) {
        a(2, Boolean.FALSE);
    }

    public final void a(AutoSuggestionHelper autoSuggestionHelper) {
        autoSuggestionHelper.a(new h(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public final boolean a(String str) {
                if (this.a.b() || "#".equals(str) || "@".equals(str)) {
                    return true;
                }
                return false;
            }
        });
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onNeedMoreMentionList(NeedMoreSquareMentionListEvent needMoreSquareMentionListEvent) {
        if (this.o != null) {
            this.o.d();
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onSquareMentionItemClick(SquareMentionItemClickEvent squareMentionItemClickEvent) {
        SquareMentionDataItem a = squareMentionItemClickEvent.a();
        this.j.a(a.a().a(), a.a().b(), true);
        c();
    }

    private boolean d() {
        SquareGroupUtils squareGroupUtils = SquareGroupUtils.a;
        return SquareGroupUtils.a(this.k);
    }

    static /* synthetic */ void a(f fVar, String str) {
        if (str.contains(" ")) {
            fVar.c();
            return;
        }
        fVar.a(2, Boolean.FALSE);
        fVar.i.ax_();
        fVar.o.a(str);
        fVar.i.a(str);
    }
}
