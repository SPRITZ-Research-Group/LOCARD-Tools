package com.skype.android.jipc.omx.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor;
import com.skype.android.jipc.Transactor.Out;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutCodecs implements Out<Map<String, Collection<String>>> {
    public final /* synthetic */ Object c(Parcel parcel) {
        int readInt = parcel.readInt();
        Map linkedHashMap = new LinkedHashMap(readInt);
        byte[] marshall = parcel.marshall();
        for (int i = 0; i < readInt; i++) {
            String a = Transactor.a(parcel, marshall);
            int readInt2 = parcel.readInt();
            List arrayList = new ArrayList(readInt2);
            for (int i2 = 0; i2 < readInt2; i2++) {
                arrayList.add(Transactor.a(parcel, marshall));
            }
            linkedHashMap.put(a, arrayList);
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
