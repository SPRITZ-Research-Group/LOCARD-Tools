package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.LogConfiguration;
import com.microsoft.applications.telemetry.datamodels.ClientToCollectorRequest;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import com.microsoft.applications.telemetry.datamodels.Record;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpRetryException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;

final class HttpSender implements IHttpSender {
    private static final String CONNECT = "Unexpected response code for CONNECT: 400";
    private static final String IO_HOST_NOT_FOUND = "Hostname 'mobile.pipe.aria.microsoft.com' was not verified";
    private static final String LOG_TAG = ("[ACT]:" + HttpSender.class.getSimpleName().toUpperCase());
    private final String bondContentType = "application/bond-compact-binary";
    private final ClockSkewManager clockSkewManager;
    private LogConfiguration configuration;
    private final String contentEncodingType = "gzip";
    private final String sdkVersion;

    public HttpSender(LogConfiguration configuration, ClockSkewManager clockSkewManager) {
        this.configuration = (LogConfiguration) Preconditions.isNotNull(configuration, "log configuration cannot be null.");
        this.clockSkewManager = (ClockSkewManager) Preconditions.isNotNull(clockSkewManager, "clock skew manager cannot be null.");
        this.sdkVersion = LibraryInfo.getLibraryName() + "-" + LibraryInfo.getLibraryVersion() + "-" + LibraryInfo.getLibraryExperimentation();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final HttpSenderResponse sendToCollector(DataPackageCollection dataPackageCollection, boolean compress) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        byte[] data;
        int statusCode;
        Map map;
        Throwable th;
        HttpsURLConnection conn = null;
        DataOutputStream dataOs = null;
        try {
            conn = (HttpsURLConnection) new URL(this.configuration.getCollectorUrl()).openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            StringBuilder apiKey = null;
            for (String token : dataPackageCollection.getTokenToDataPackages().keySet()) {
                if (apiKey == null) {
                    apiKey = new StringBuilder();
                    apiKey.append(token);
                } else {
                    apiKey.append(",").append(token);
                }
            }
            conn.setRequestProperty("x-apikey", apiKey.toString());
            conn.setRequestProperty("client-time-epoch-millis", String.valueOf(System.currentTimeMillis()));
            conn.setRequestProperty("Content-Type", "application/bond-compact-binary");
            conn.setRequestProperty("Client-Id", this.configuration.getClientId());
            conn.setRequestProperty("sdk-version", this.sdkVersion);
            if (this.clockSkewManager.isClockSkewEnabled()) {
                conn.setRequestProperty("time-delta-to-apply-millis", dataPackageCollection.getClockSkewHeaderValue());
            }
            data = dataPackageToStream(dataPackageCollection, this.configuration.getClientKey(), dataPackageCollection.getRetryCount(), compress);
            if (compress) {
                conn.setRequestProperty("Content-Encoding", "gzip");
            }
        } catch (IOException e) {
            TraceHelper.TraceError(LOG_TAG, "Compression failed for request id=" + dataPackageCollection.getId());
            data = dataPackageToStream(dataPackageCollection, this.configuration.getClientKey(), dataPackageCollection.getRetryCount(), false);
        } catch (UnknownHostException e2) {
        } catch (SocketTimeoutException e3) {
        } catch (SocketException e4) {
        } catch (SSLException e5) {
        } catch (HttpRetryException e6) {
        }
        for (Entry<String, HashMap<DataPackage, EventPriority>> tokenToDataPackages : dataPackageCollection.getTokenToDataPackages().entrySet()) {
            for (Entry<DataPackage, EventPriority> kvp : ((HashMap) tokenToDataPackages.getValue()).entrySet()) {
                Iterator it = ((DataPackage) kvp.getKey()).getRecords().iterator();
                while (it.hasNext()) {
                    Record record = (Record) it.next();
                    TraceHelper.TraceInformation(LOG_TAG, String.format("Stage Post: event name=%s, event priority=%s, id=%s, tenantId=%s, request id=%s", new Object[]{record.getEventType(), kvp.getValue(), record.getId(), DataModelHelper.getTenantId((String) tokenToDataPackages.getKey()), dataPackageCollection.getId()}));
                }
            }
        }
        conn.setFixedLengthStreamingMode(data.length);
        DataOutputStream dataOs2 = new DataOutputStream(conn.getOutputStream());
        try {
            dataOs2.write(data);
            dataOs2.flush();
            String message = conn.getResponseMessage();
            statusCode = conn.getResponseCode();
            TraceHelper.TraceDebug(LOG_TAG, "Response message: " + message + "|StatusCode: " + statusCode);
            if (conn != null) {
                conn.disconnect();
            }
            dataOs2.close();
            dataOs = dataOs2;
        } catch (UnknownHostException e7) {
            dataOs = dataOs2;
            statusCode = -1;
            if (conn != null) {
                conn.disconnect();
            }
            if (dataOs != null) {
                dataOs.close();
            }
            if (conn != null) {
                map = null;
            } else {
                map = conn.getHeaderFields();
            }
            return new HttpSenderResponse(statusCode, map);
        } catch (SocketTimeoutException e8) {
            dataOs = dataOs2;
            statusCode = -1;
            if (conn != null) {
                conn.disconnect();
            }
            if (dataOs != null) {
                dataOs.close();
            }
            if (conn != null) {
                map = conn.getHeaderFields();
            } else {
                map = null;
            }
            return new HttpSenderResponse(statusCode, map);
        } catch (SocketException e9) {
            dataOs = dataOs2;
            statusCode = -1;
            if (conn != null) {
                conn.disconnect();
            }
            if (dataOs != null) {
                dataOs.close();
            }
            if (conn != null) {
                map = null;
            } else {
                map = conn.getHeaderFields();
            }
            return new HttpSenderResponse(statusCode, map);
        } catch (SSLException e10) {
            dataOs = dataOs2;
            statusCode = -1;
            if (conn != null) {
                conn.disconnect();
            }
            if (dataOs != null) {
                dataOs.close();
            }
            if (conn != null) {
                map = conn.getHeaderFields();
            } else {
                map = null;
            }
            return new HttpSenderResponse(statusCode, map);
        } catch (HttpRetryException e11) {
            dataOs = dataOs2;
            statusCode = -1;
            if (conn != null) {
                conn.disconnect();
            }
            if (dataOs != null) {
                dataOs.close();
            }
            if (conn != null) {
                map = null;
            } else {
                map = conn.getHeaderFields();
            }
            return new HttpSenderResponse(statusCode, map);
        } catch (IOException e12) {
            IOException ex = e12;
            dataOs = dataOs2;
            try {
                if (ex.getMessage() == null || !(ex.getMessage().contains(IO_HOST_NOT_FOUND) || ex.getMessage().contains(CONNECT))) {
                    throw ex;
                } else {
                    statusCode = -1;
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (dataOs != null) {
                        dataOs.close();
                    }
                    if (conn != null) {
                        map = conn.getHeaderFields();
                    } else {
                        map = null;
                    }
                    return new HttpSenderResponse(statusCode, map);
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            dataOs = dataOs2;
            if (conn != null) {
                conn.disconnect();
            }
            if (dataOs != null) {
                dataOs.close();
            }
            throw th;
        }
        if (conn != null) {
            map = conn.getHeaderFields();
        } else {
            map = null;
        }
        return new HttpSenderResponse(statusCode, map);
    }

    public final byte[] dataPackageToStream(DataPackageCollection dataPackageCollection, String authenticationKey, int sendRetryNum, boolean compress) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        ClientToCollectorRequest request = new ClientToCollectorRequest();
        HashMap<String, ArrayList<DataPackage>> allDpsToSend = new HashMap();
        for (Entry<String, HashMap<DataPackage, EventPriority>> tokenToDataPackages : dataPackageCollection.getTokenToDataPackages().entrySet()) {
            ArrayList<DataPackage> dataPackages = new ArrayList();
            for (Entry<DataPackage, EventPriority> kvp : ((HashMap) tokenToDataPackages.getValue()).entrySet()) {
                Iterator it = ((DataPackage) kvp.getKey()).getRecords().iterator();
                while (it.hasNext()) {
                    Record record = (Record) it.next();
                    TraceHelper.TraceInformation(LOG_TAG, String.format("Stage Pack: event name=%s, event priority=%s, id=%s, tenantId=%s, request id=%s", new Object[]{record.getEventType(), kvp.getValue(), record.getId(), DataModelHelper.getTenantId((String) tokenToDataPackages.getKey()), dataPackageCollection.getId()}));
                }
                dataPackages.add(kvp.getKey());
            }
            allDpsToSend.put(tokenToDataPackages.getKey(), dataPackages);
        }
        request.setTokenToDataPackagesMap(allDpsToSend);
        request.setRequestRetryCount(sendRetryNum);
        if (compress) {
            return compressPackageUsingGzip(Serializer.serializeWithHmac(request, authenticationKey));
        }
        return Serializer.serializeWithHmac(request, authenticationKey);
    }

    final byte[] compressPackageUsingGzip(byte[] bytesToCompress) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(bytesToCompress.length);
        GZIPOutputStream zipStream;
        try {
            zipStream = new GZIPOutputStream(byteStream);
            zipStream.write(bytesToCompress);
            zipStream.close();
            byteStream.close();
            return byteStream.toByteArray();
        } catch (Throwable th) {
            byteStream.close();
        }
    }
}
