package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import javax.annotation.Nullable;

@SuppressLint({"ValidFragment"})
public final class a extends DialogFragment {
    @Nullable
    private OnDateSetListener a;
    @Nullable
    private OnDismissListener b;

    /* renamed from: com.facebook.react.modules.datepicker.a$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.CALENDAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.SPINNER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        return a(getArguments(), getActivity(), this.a);
    }

    static android.app.Dialog a(android.os.Bundle r11, android.content.Context r12, @javax.annotation.Nullable android.app.DatePickerDialog.OnDateSetListener r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r0_3 'dialog' android.app.DatePickerDialog) in PHI: PHI: (r0_4 'dialog' android.app.DatePickerDialog) = (r0_0 'dialog' android.app.DatePickerDialog), (r0_1 'dialog' android.app.DatePickerDialog), (r0_2 'dialog' android.app.DatePickerDialog), (r0_3 'dialog' android.app.DatePickerDialog), (r0_5 'dialog' android.app.DatePickerDialog), (r0_5 'dialog' android.app.DatePickerDialog), (r0_5 'dialog' android.app.DatePickerDialog) binds: {(r0_0 'dialog' android.app.DatePickerDialog)=B:13:0x0052, (r0_1 'dialog' android.app.DatePickerDialog)=B:24:0x00c2, (r0_2 'dialog' android.app.DatePickerDialog)=B:25:0x00db, (r0_3 'dialog' android.app.DatePickerDialog)=B:26:0x00f4, (r0_5 'dialog' android.app.DatePickerDialog)=B:28:0x010e, (r0_5 'dialog' android.app.DatePickerDialog)=B:29:0x0113, (r0_5 'dialog' android.app.DatePickerDialog)=B:30:0x0125}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r7 = java.util.Calendar.getInstance();
        if (r11 == 0) goto L_0x0017;
    L_0x0006:
        r1 = "date";
        r1 = r11.containsKey(r1);
        if (r1 == 0) goto L_0x0017;
    L_0x000e:
        r1 = "date";
        r2 = r11.getLong(r1);
        r7.setTimeInMillis(r2);
    L_0x0017:
        r1 = 1;
        r4 = r7.get(r1);
        r1 = 2;
        r5 = r7.get(r1);
        r1 = 5;
        r6 = r7.get(r1);
        r9 = com.facebook.react.modules.datepicker.b.DEFAULT;
        if (r11 == 0) goto L_0x0043;
    L_0x002a:
        r1 = "mode";
        r2 = 0;
        r1 = r11.getString(r1, r2);
        if (r1 == 0) goto L_0x0043;
    L_0x0033:
        r1 = "mode";
        r1 = r11.getString(r1);
        r2 = java.util.Locale.US;
        r1 = r1.toUpperCase(r2);
        r9 = com.facebook.react.modules.datepicker.b.valueOf(r1);
    L_0x0043:
        r0 = 0;
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 21;
        if (r1 < r2) goto L_0x00fe;
    L_0x004a:
        r1 = com.facebook.react.modules.datepicker.a.AnonymousClass1.a;
        r2 = r9.ordinal();
        r1 = r1[r2];
        switch(r1) {
            case 1: goto L_0x00c2;
            case 2: goto L_0x00db;
            case 3: goto L_0x00f4;
            default: goto L_0x0055;
        };
    L_0x0055:
        r8 = r0.getDatePicker();
        if (r11 == 0) goto L_0x012f;
    L_0x005b:
        r1 = "minDate";
        r1 = r11.containsKey(r1);
        if (r1 == 0) goto L_0x012f;
    L_0x0063:
        r1 = "minDate";
        r2 = r11.getLong(r1);
        r7.setTimeInMillis(r2);
        r1 = 11;
        r2 = 0;
        r7.set(r1, r2);
        r1 = 12;
        r2 = 0;
        r7.set(r1, r2);
        r1 = 13;
        r2 = 0;
        r7.set(r1, r2);
        r1 = 14;
        r2 = 0;
        r7.set(r1, r2);
        r2 = r7.getTimeInMillis();
        r8.setMinDate(r2);
    L_0x008b:
        if (r11 == 0) goto L_0x00c1;
    L_0x008d:
        r1 = "maxDate";
        r1 = r11.containsKey(r1);
        if (r1 == 0) goto L_0x00c1;
    L_0x0095:
        r1 = "maxDate";
        r2 = r11.getLong(r1);
        r7.setTimeInMillis(r2);
        r1 = 11;
        r2 = 23;
        r7.set(r1, r2);
        r1 = 12;
        r2 = 59;
        r7.set(r1, r2);
        r1 = 13;
        r2 = 59;
        r7.set(r1, r2);
        r1 = 14;
        r2 = 999; // 0x3e7 float:1.4E-42 double:4.936E-321;
        r7.set(r1, r2);
        r2 = r7.getTimeInMillis();
        r8.setMaxDate(r2);
    L_0x00c1:
        return r0;
    L_0x00c2:
        r0 = new com.facebook.react.modules.datepicker.DismissableDatePickerDialog;
        r1 = r12.getResources();
        r2 = "CalendarDatePickerDialog";
        r3 = "style";
        r10 = r12.getPackageName();
        r2 = r1.getIdentifier(r2, r3, r10);
        r1 = r12;
        r3 = r13;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        goto L_0x0055;
    L_0x00db:
        r0 = new com.facebook.react.modules.datepicker.DismissableDatePickerDialog;
        r1 = r12.getResources();
        r2 = "SpinnerDatePickerDialog";
        r3 = "style";
        r10 = r12.getPackageName();
        r2 = r1.getIdentifier(r2, r3, r10);
        r1 = r12;
        r3 = r13;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        goto L_0x0055;
    L_0x00f4:
        r0 = new com.facebook.react.modules.datepicker.DismissableDatePickerDialog;
        r1 = r0;
        r2 = r12;
        r3 = r13;
        r1.<init>(r2, r3, r4, r5, r6);
        goto L_0x0055;
    L_0x00fe:
        r0 = new com.facebook.react.modules.datepicker.DismissableDatePickerDialog;
        r1 = r0;
        r2 = r12;
        r3 = r13;
        r1.<init>(r2, r3, r4, r5, r6);
        r1 = com.facebook.react.modules.datepicker.a.AnonymousClass1.a;
        r2 = r9.ordinal();
        r1 = r1[r2];
        switch(r1) {
            case 1: goto L_0x0113;
            case 2: goto L_0x0125;
            default: goto L_0x0111;
        };
    L_0x0111:
        goto L_0x0055;
    L_0x0113:
        r1 = r0.getDatePicker();
        r2 = 1;
        r1.setCalendarViewShown(r2);
        r1 = r0.getDatePicker();
        r2 = 0;
        r1.setSpinnersShown(r2);
        goto L_0x0055;
    L_0x0125:
        r1 = r0.getDatePicker();
        r2 = 0;
        r1.setCalendarViewShown(r2);
        goto L_0x0055;
    L_0x012f:
        r2 = -2208988800001; // 0xfffffdfdae01dbff float:-2.95266E-11 double:NaN;
        r8.setMinDate(r2);
        goto L_0x008b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.datepicker.a.a(android.os.Bundle, android.content.Context, android.app.DatePickerDialog$OnDateSetListener):android.app.Dialog");
    }

    public final void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.b != null) {
            this.b.onDismiss(dialog);
        }
    }

    final void a(@Nullable OnDateSetListener onDateSetListener) {
        this.a = onDateSetListener;
    }

    final void a(@Nullable OnDismissListener onDismissListener) {
        this.b = onDismissListener;
    }
}
