package com.microsoft.applications.telemetry.core;

import com.adjust.sdk.Constants;
import com.microsoft.a.b.b;
import com.microsoft.a.b.e;
import com.microsoft.a.d;
import com.microsoft.a.f;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

final class Serializer {
    private Serializer() {
        throw new AssertionError();
    }

    public static void serialize(d objToSerialize, e outStream) throws IOException {
        objToSerialize.write(f.a((b) outStream));
    }

    public static void deserialize(d objToDeserialize, byte[] buffer) throws IOException {
        objToDeserialize.read(com.microsoft.a.e.a(buffer));
    }

    public static byte[] serializeWithHmac(d objToSerialize, String authenicationKey) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String algo = "HmacSHA256";
        SecretKeySpec key = new SecretKeySpec(authenicationKey.getBytes(Charset.forName(Constants.ENCODING)), algo);
        Mac hmac = Mac.getInstance(algo);
        hmac.init(key);
        int hashSizeInBytes = hmac.getMacLength();
        e outStream = new e();
        serialize(objToSerialize, outStream);
        byte[] request = new byte[(outStream.b() + hashSizeInBytes)];
        byte[] outStreamArray = outStream.a();
        outStream.close();
        System.arraycopy(hmac.doFinal(outStreamArray), 0, request, 0, hashSizeInBytes);
        System.arraycopy(outStreamArray, 0, request, hashSizeInBytes, outStreamArray.length);
        return request;
    }
}
