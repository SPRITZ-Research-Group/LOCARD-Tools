package com.linecorp.android.offlinelink.ble.api;

import android.content.Context;
import android.os.ParcelUuid;
import com.linecorp.android.offlinelink.ble.service.BluetoothBroadcastReceiver;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import defpackage.ceg;
import defpackage.ceh;
import defpackage.cei;
import defpackage.cej;
import defpackage.ceo;
import defpackage.cer;
import defpackage.cfb;
import defpackage.che;
import defpackage.chh;
import defpackage.chi;
import defpackage.chj;
import defpackage.chk;
import defpackage.chl;
import defpackage.cho;
import defpackage.chp;
import defpackage.chq;
import defpackage.cht;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Deprecated
public final class e {
    private final Context a;
    private final a b;
    private final ExecutorService c;
    private i d;
    private final ceh e;
    private final che f;
    private final BluetoothBroadcastReceiver g;
    private final Map<String, LeDevice> h;
    private final cei i;

    public e(Context context) {
        this(context, Executors.newSingleThreadExecutor(), cej.a());
    }

    private e(Context context, ExecutorService executorService, ceh ceh) {
        this(context, new a(executorService), ceh, executorService);
    }

    private e(Context context, a aVar, ceh ceh, ExecutorService executorService) {
        this(context, aVar, ceh, executorService, new che(context, aVar), new f(aVar));
    }

    private e(Context context, a aVar, ceh ceh, ExecutorService executorService, che che, BluetoothBroadcastReceiver bluetoothBroadcastReceiver) {
        this.h = new HashMap();
        this.i = new cei(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public final void a(ceo ceo) {
                chq a = cht.a(ceo);
                if (this.a.f.a(a.a(), a.b())) {
                    this.a.h.put(a.a(), new LeDevice(a.a()));
                }
            }
        };
        this.a = context;
        this.b = aVar;
        this.e = ceh;
        this.c = executorService;
        this.f = che;
        this.g = bluetoothBroadcastReceiver;
    }

    @Deprecated
    public final boolean a(final m mVar) {
        if (!a.a(this.a) || !cfb.a(this.a)) {
            return false;
        }
        this.g.a(this.a);
        this.b.b(this);
        this.c.execute(new Runnable(this) {
            final /* synthetic */ e b;

            public final void run() {
                mVar.a();
            }
        });
        return true;
    }

    @Deprecated
    public final void a() {
        this.b.c(this);
        this.g.a();
    }

    public final boolean a(GattService gattService, i iVar) {
        if (!a.a(this.a) || this.d != null) {
            return false;
        }
        this.d = iVar;
        List arrayList = new ArrayList();
        arrayList.add(new ceg().a(gattService.a()).a());
        this.e.a(arrayList);
        b();
        return true;
    }

    private void b() {
        if (cer.a()) {
            this.e.a(this.i);
            this.d.a(true);
        }
    }

    public final void a(i iVar) {
        if (this.d != null && this.d == iVar) {
            c();
            this.d = null;
        }
    }

    private void c() {
        this.e.a();
        this.f.a();
        this.h.clear();
        this.d.a(false);
    }

    public final boolean a(LeDevice leDevice) {
        return this.f.c(leDevice.a());
    }

    public final boolean a(LeDevice leDevice, boolean z) {
        return this.f.a(leDevice.a(), z);
    }

    @Deprecated
    public final boolean a(LeDevice leDevice, ParcelUuid parcelUuid, ParcelUuid parcelUuid2) {
        return this.f.b(leDevice.a(), parcelUuid.getUuid(), parcelUuid2.getUuid());
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onCharacteristicReadEvent(chi chi) {
        if (chi.a() == 0) {
            LeDevice leDevice = (LeDevice) this.h.get(chi.b());
            if (leDevice != null) {
                GattCharacteristic gattCharacteristic = new GattCharacteristic(new ParcelUuid(chi.d()), 0);
                if (this.d != null) {
                    this.d.a(leDevice, chi.e());
                }
            }
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onCharacteristicWroteEvent(chj chj) {
        if (chj.a() == 0 && ((LeDevice) this.h.get(chj.b())) != null) {
            GattCharacteristic gattCharacteristic = new GattCharacteristic(new ParcelUuid(chj.d()), 0);
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onCharacteristicChangedEvent(chh chh) {
        if (((LeDevice) this.h.get(chh.a())) != null) {
            GattCharacteristic gattCharacteristic = new GattCharacteristic(new ParcelUuid(chh.c()), 0);
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onConnectionStateChangedEvent(chk chk) {
        if (chk.a() == 0) {
            this.h.get(chk.b());
            if (chk.c() != chl.CONNECTED) {
                chl chl = chl.DISCONNECTED;
                this.f.c(chk.b());
            } else if (!this.f.d(chk.b())) {
            }
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onServicesDiscoveredEvent(cho cho) {
        if (cho.a() == 0 && this.d != null) {
            this.d.a((LeDevice) this.h.get(cho.b()));
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onTouchDetectedEvent(chp chp) {
        if (this.d != null) {
            this.d.b((LeDevice) this.h.get(chp.a()));
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onBluetoothStateChangedEvent(g gVar) {
        if (gVar == g.TURN_OFF || gVar == g.TURNING_OFF) {
            c();
            this.e.a();
            return;
        }
        if (gVar == g.TURN_ON && this.d != null) {
            b();
        }
    }
}
