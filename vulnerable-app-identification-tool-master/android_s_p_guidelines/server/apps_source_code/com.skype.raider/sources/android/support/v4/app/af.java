package android.support.v4.app;

import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ae.a;
import java.util.ArrayList;
import java.util.Set;

@RequiresApi(16)
final class af {
    static Bundle[] a(a[] remoteInputs) {
        if (remoteInputs == null) {
            return null;
        }
        Bundle[] bundles = new Bundle[remoteInputs.length];
        for (int i = 0; i < remoteInputs.length; i++) {
            a aVar = remoteInputs[i];
            Bundle bundle = new Bundle();
            bundle.putString("resultKey", aVar.a());
            bundle.putCharSequence("label", aVar.b());
            bundle.putCharSequenceArray("choices", aVar.c());
            bundle.putBoolean("allowFreeFormInput", aVar.e());
            bundle.putBundle("extras", aVar.f());
            Set<String> d = aVar.d();
            if (!(d == null || d.isEmpty())) {
                ArrayList arrayList = new ArrayList(d.size());
                for (String add : d) {
                    arrayList.add(add);
                }
                bundle.putStringArrayList("allowedDataTypes", arrayList);
            }
            bundles[i] = bundle;
        }
        return bundles;
    }
}
