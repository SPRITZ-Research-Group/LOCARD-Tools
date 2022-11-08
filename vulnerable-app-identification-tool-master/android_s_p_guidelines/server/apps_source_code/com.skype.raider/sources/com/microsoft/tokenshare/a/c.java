package com.microsoft.tokenshare.a;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.google.gson.e;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class c {
    private static final Pattern a = Pattern.compile("([^\\.]*)\\.([^\\.]*)\\.([^\\.]*)");
    private Key b;

    static class b {
        b() {
        }
    }

    static class a extends b {
        @SerializedName("alg")
        public String a;

        a() {
        }
    }

    public final c a(@NonNull Key key) {
        this.b = key;
        return this;
    }

    public final <T> T a(@NonNull String jwtEncodedPayload, @NonNull Class<T> classOfT) throws e {
        if (TextUtils.isEmpty(jwtEncodedPayload)) {
            throw new e("Stream is empty");
        }
        Matcher matcher = a.matcher(jwtEncodedPayload);
        if (matcher.matches()) {
            String base64EncodedHeader = matcher.group(1);
            String base64Body = matcher.group(2);
            String base64Digest = matcher.group(3);
            String header = a(base64EncodedHeader);
            String body = a(base64Body);
            e gson = new e();
            if (this.b != null) {
                if (TextUtils.isEmpty(base64Digest)) {
                    throw new e("Signature expected, but not present");
                } else if (TextUtils.isEmpty(header)) {
                    throw new e("JWS is empty");
                } else {
                    try {
                        a jwsHeader = (a) gson.a(header, a.class);
                        if (TextUtils.isEmpty(jwsHeader.a)) {
                            throw new e("JWS doesn't contain algorithm in the header");
                        }
                        b jwsAlgorithm = b.a(jwsHeader.a);
                        if (jwsAlgorithm == null || jwsAlgorithm == b.NONE) {
                            throw new e("Unsupported crypto algorithm");
                        }
                        if (!jwsAlgorithm.a().a(this.b, (base64EncodedHeader + "." + base64Body).getBytes(Charset.forName(Constants.ENCODING)), Base64.decode(base64Digest, 0))) {
                            throw new e("Signature validation failed");
                        }
                    } catch (Throwable e) {
                        throw new e(e);
                    }
                }
            }
            try {
                return gson.a(body, (Class) classOfT);
            } catch (Throwable e2) {
                throw new e(e2);
            }
        }
        throw new e("Can't extract JWT payload");
    }

    private static String a(String base64EncodedString) {
        if (base64EncodedString == null) {
            return null;
        }
        try {
            return new String(Base64.decode(base64EncodedString, 8), Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
        } catch (IllegalArgumentException e2) {
        }
        return null;
    }
}
