package com.adjust.sdk;

import android.net.Uri;
import android.net.Uri.Builder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public class UtilNetworking {
    private static String userAgent;

    public interface IConnectionOptions {
        void applyConnectionOptions(HttpsURLConnection httpsURLConnection, String str);
    }

    static class a implements IConnectionOptions {
        a() {
        }

        public final void applyConnectionOptions(HttpsURLConnection connection, String clientSdk) {
            connection.setRequestProperty("Client-SDK", clientSdk);
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(60000);
            if (UtilNetworking.userAgent != null) {
                connection.setRequestProperty("User-Agent", UtilNetworking.userAgent);
            }
        }
    }

    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }

    public static void setUserAgent(String userAgent) {
        userAgent = userAgent;
    }

    public static ResponseData createPOSTHttpsURLConnection(String urlString, ActivityPackage activityPackage, int queueSize) throws Exception {
        Exception e;
        Throwable th;
        DataOutputStream wr = null;
        try {
            HttpsURLConnection connection = AdjustFactory.getHttpsURLConnection(new URL(urlString));
            Map<String, String> parameters = new HashMap(activityPackage.getParameters());
            AdjustFactory.getConnectionOptions().applyConnectionOptions(connection, activityPackage.getClientSdk());
            String authorizationHeader = buildAuthorizationHeader(parameters, extractAppSecret(parameters), extractSecretId(parameters), activityPackage.getActivityKind().toString());
            if (authorizationHeader != null) {
                connection.setRequestProperty("Authorization", authorizationHeader);
            }
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            DataOutputStream wr2 = new DataOutputStream(connection.getOutputStream());
            try {
                wr2.writeBytes(getPostDataString(parameters, queueSize));
                ResponseData readHttpResponse = readHttpResponse(connection, activityPackage);
                try {
                    wr2.flush();
                    wr2.close();
                } catch (Exception e2) {
                }
                return readHttpResponse;
            } catch (Exception e3) {
                e = e3;
                wr = wr2;
            } catch (Throwable th2) {
                th = th2;
                wr = wr2;
                if (wr != null) {
                    try {
                        wr.flush();
                        wr.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            try {
                throw e;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public static ResponseData createGETHttpsURLConnection(ActivityPackage activityPackage, String basePath) throws Exception {
        try {
            Map<String, String> parameters = new HashMap(activityPackage.getParameters());
            String appSecret = extractAppSecret(parameters);
            String secretId = extractSecretId(parameters);
            HttpsURLConnection connection = AdjustFactory.getHttpsURLConnection(new URL(buildUri(activityPackage.getPath(), parameters, basePath).toString()));
            AdjustFactory.getConnectionOptions().applyConnectionOptions(connection, activityPackage.getClientSdk());
            String authorizationHeader = buildAuthorizationHeader(parameters, appSecret, secretId, activityPackage.getActivityKind().toString());
            if (authorizationHeader != null) {
                connection.setRequestProperty("Authorization", authorizationHeader);
            }
            connection.setRequestMethod("GET");
            return readHttpResponse(connection, activityPackage);
        } catch (Exception e) {
            throw e;
        }
    }

    private static ResponseData readHttpResponse(HttpsURLConnection connection, ActivityPackage activityPackage) throws Exception {
        String message;
        StringBuffer sb = new StringBuffer();
        ILogger logger = getLogger();
        ResponseData responseData = ResponseData.buildResponseData(activityPackage);
        try {
            InputStream inputStream;
            connection.connect();
            Integer responseCode = Integer.valueOf(connection.getResponseCode());
            if (responseCode.intValue() >= 400) {
                inputStream = connection.getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            if (connection != null) {
                connection.disconnect();
            }
            String stringResponse = sb.toString();
            logger.verbose("Response: %s", stringResponse);
            if (!(stringResponse == null || stringResponse.length() == 0)) {
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(stringResponse);
                } catch (JSONException e) {
                    message = Util.formatString("Failed to parse json response. (%s)", e.getMessage());
                    logger.error(message, new Object[0]);
                    responseData.message = message;
                }
                if (jsonResponse != null) {
                    responseData.jsonResponse = jsonResponse;
                    message = jsonResponse.optString("message", null);
                    responseData.message = message;
                    responseData.timestamp = jsonResponse.optString("timestamp", null);
                    responseData.adid = jsonResponse.optString("adid", null);
                    if (message == null) {
                        message = "No message found";
                    }
                    if (responseCode == null || responseCode.intValue() != 200) {
                        logger.error("%s", message);
                    } else {
                        logger.info("%s", message);
                        responseData.success = true;
                    }
                }
            }
            return responseData;
        } catch (Exception e2) {
            logger.error("Failed to read response. (%s)", e2.getMessage());
            throw e2;
        } catch (Throwable th) {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String getPostDataString(Map<String, String> body, int queueSize) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : body.entrySet()) {
            String encodedName = URLEncoder.encode((String) entry.getKey(), Constants.ENCODING);
            String value = (String) entry.getValue();
            String encodedValue = value != null ? URLEncoder.encode(value, Constants.ENCODING) : "";
            if (result.length() > 0) {
                result.append("&");
            }
            result.append(encodedName);
            result.append("=");
            result.append(encodedValue);
        }
        String dateString = Util.dateFormatter.format(Long.valueOf(System.currentTimeMillis()));
        result.append("&");
        result.append(URLEncoder.encode("sent_at", Constants.ENCODING));
        result.append("=");
        result.append(URLEncoder.encode(dateString, Constants.ENCODING));
        if (queueSize > 0) {
            result.append("&");
            result.append(URLEncoder.encode("queue_size", Constants.ENCODING));
            result.append("=");
            result.append(URLEncoder.encode(String.valueOf(queueSize), Constants.ENCODING));
        }
        return result.toString();
    }

    private static Uri buildUri(String path, Map<String, String> parameters, String basePath) {
        Builder uriBuilder = new Builder();
        String scheme = Constants.SCHEME;
        String authority = Constants.AUTHORITY;
        String initialPath = "";
        try {
            String url = AdjustFactory.getBaseUrl();
            if (basePath != null) {
                url = url + basePath;
            }
            URL baseUrl = new URL(url);
            scheme = baseUrl.getProtocol();
            authority = baseUrl.getAuthority();
            initialPath = baseUrl.getPath();
        } catch (MalformedURLException e) {
            getLogger().error("Unable to parse endpoint (%s)", e.getMessage());
        }
        uriBuilder.scheme(scheme);
        uriBuilder.encodedAuthority(authority);
        uriBuilder.path(initialPath);
        uriBuilder.appendPath(path);
        for (Entry<String, String> entry : parameters.entrySet()) {
            uriBuilder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        uriBuilder.appendQueryParameter("sent_at", Util.dateFormatter.format(Long.valueOf(System.currentTimeMillis())));
        return uriBuilder.build();
    }

    private static String extractAppSecret(Map<String, String> parameters) {
        return (String) parameters.remove("app_secret");
    }

    private static String extractSecretId(Map<String, String> parameters) {
        return (String) parameters.remove("secret_id");
    }

    private static String buildAuthorizationHeader(Map<String, String> parameters, String appSecret, String secretId, String activityKind) {
        if (appSecret == null || appSecret.length() == 0) {
            return null;
        }
        Map<String, String> signatureDetails = getSignature(parameters, activityKind, appSecret);
        String signature = Util.sha256((String) signatureDetails.get("clear_signature"));
        String fields = (String) signatureDetails.get("fields");
        String secretIdHeader = Util.formatString("secret_id=\"%s\"", secretId);
        String signatureHeader = Util.formatString("signature=\"%s\"", signature);
        String algorithmHeader = Util.formatString("algorithm=\"%s\"", "sha256");
        String fieldsHeader = Util.formatString("headers=\"%s\"", fields);
        getLogger().verbose("authorizationHeader: %s", Util.formatString("Signature %s,%s,%s,%s", secretIdHeader, signatureHeader, algorithmHeader, fieldsHeader));
        return Util.formatString("Signature %s,%s,%s,%s", secretIdHeader, signatureHeader, algorithmHeader, fieldsHeader);
    }

    private static Map<String, String> getSignature(Map<String, String> parameters, String activityKind, String appSecret) {
        String activityKindValue = activityKind;
        String createdAtName = "created_at";
        String createdAt = (String) parameters.get(createdAtName);
        String deviceIdentifierName = getValidIdentifier(parameters);
        String deviceIdentifier = (String) parameters.get(deviceIdentifierName);
        Map<String, String> signatureParams = new HashMap();
        signatureParams.put("app_secret", appSecret);
        signatureParams.put(createdAtName, createdAt);
        signatureParams.put("activity_kind", activityKindValue);
        signatureParams.put(deviceIdentifierName, deviceIdentifier);
        String fields = "";
        String clearSignature = "";
        for (Entry<String, String> entry : signatureParams.entrySet()) {
            if (entry.getValue() != null) {
                fields = fields + ((String) entry.getKey()) + " ";
                clearSignature = clearSignature + ((String) entry.getValue());
            }
        }
        fields = fields.substring(0, fields.length() - 1);
        HashMap<String, String> signature = new HashMap();
        signature.put("clear_signature", clearSignature);
        signature.put("fields", fields);
        return signature;
    }

    private static String getValidIdentifier(Map<String, String> parameters) {
        String googleAdIdName = "gps_adid";
        String fireAdIdName = "fire_adid";
        String androidIdName = "android_id";
        String macSha1Name = "mac_sha1";
        String macMd5Name = "mac_md5";
        String androidUUIDName = "android_uuid";
        if (parameters.get(googleAdIdName) != null) {
            return googleAdIdName;
        }
        if (parameters.get(fireAdIdName) != null) {
            return fireAdIdName;
        }
        if (parameters.get(androidIdName) != null) {
            return androidIdName;
        }
        if (parameters.get(macSha1Name) != null) {
            return macSha1Name;
        }
        if (parameters.get(macMd5Name) != null) {
            return macMd5Name;
        }
        if (parameters.get(androidUUIDName) != null) {
            return androidUUIDName;
        }
        return null;
    }
}
