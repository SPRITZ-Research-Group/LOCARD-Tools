package com.microsoft.tokenshare;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import com.microsoft.tokenshare.j.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class l implements n {
    l() {
    }

    @NonNull
    public List<e> a(@NonNull Context context) {
        ArrayList<e> approvedSignatures = new ArrayList();
        TypedArray signaturesIds = context.getResources().obtainTypedArray(a.tokenshare_signatures);
        for (int i = 0; i < signaturesIds.length(); i++) {
            e signatureList = new e();
            signatureList.a = Arrays.asList(context.getResources().getStringArray(signaturesIds.getResourceId(i, 0)));
            approvedSignatures.add(signatureList);
        }
        signaturesIds.recycle();
        return approvedSignatures;
    }

    @NonNull
    public List<String> b(@NonNull Context context) {
        return Arrays.asList(context.getResources().getStringArray(a.tokenshare_package_names));
    }
}
