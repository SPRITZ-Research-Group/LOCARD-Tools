package com.facebook.ads.internal.f;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.q.a.n;
import com.facebook.ads.internal.q.a.o;
import com.facebook.ads.internal.q.a.r;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private static final String a = e.class.getName();
    private static final Object b = new Object();
    private static final Set<String> c = Collections.synchronizedSet(new HashSet());
    private static final Map<String, Integer> d = Collections.synchronizedMap(new HashMap());

    public static d a(Exception exception, Context context, Map<String, String> map) {
        try {
            d dVar = new d(n.b(), n.c(), new b(o.a((Throwable) exception), map, true).a());
            try {
                a(dVar, context);
                return dVar;
            } catch (Exception e) {
                return dVar;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    @WorkerThread
    public static JSONArray a(Context context) {
        return a(context, -1);
    }

    @WorkerThread
    public static JSONArray a(Context context, int i) {
        InputStreamReader inputStreamReader;
        FileInputStream fileInputStream;
        Throwable th;
        Throwable th2;
        BufferedReader bufferedReader = null;
        JSONArray jSONArray = new JSONArray();
        synchronized (b) {
            FileInputStream openFileInput;
            InputStreamReader inputStreamReader2;
            BufferedReader bufferedReader2;
            try {
                if (new File(context.getFilesDir(), "debuglogs").exists()) {
                    openFileInput = context.openFileInput("debuglogs");
                    try {
                        inputStreamReader2 = new InputStreamReader(openFileInput);
                        try {
                            bufferedReader2 = new BufferedReader(inputStreamReader2);
                            int i2 = i;
                            while (true) {
                                try {
                                    String readLine = bufferedReader2.readLine();
                                    if (readLine == null || i2 == 0) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(readLine);
                                    if (!jSONObject.has("attempt")) {
                                        jSONObject.put("attempt", 0);
                                    }
                                    readLine = jSONObject.getString("id");
                                    if (!c.contains(readLine)) {
                                        int i3 = jSONObject.getInt("attempt");
                                        if (d.containsKey(readLine)) {
                                            jSONObject.put("attempt", d.get(readLine));
                                        } else if (c.contains(readLine)) {
                                            throw new RuntimeException("finished event should not be updated to OngoingEvent.");
                                        } else {
                                            if (d.containsKey(readLine)) {
                                                d.remove(readLine);
                                            }
                                            d.put(readLine, Integer.valueOf(i3));
                                        }
                                        jSONArray.put(jSONObject);
                                        if (i2 > 0) {
                                            i2--;
                                        }
                                    }
                                } catch (IOException e) {
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader2;
                                    fileInputStream = openFileInput;
                                } catch (JSONException e2) {
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader2;
                                    fileInputStream = openFileInput;
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            }
                        } catch (IOException e3) {
                            inputStreamReader = inputStreamReader2;
                            fileInputStream = openFileInput;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                }
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return jSONArray;
                        } catch (JSONException e5) {
                            inputStreamReader = inputStreamReader2;
                            fileInputStream = openFileInput;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return jSONArray;
                        } catch (Throwable th4) {
                            th2 = th4;
                            bufferedReader2 = null;
                            th = th2;
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e6) {
                                    throw th;
                                }
                            }
                            if (inputStreamReader2 != null) {
                                inputStreamReader2.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            throw th;
                        }
                    } catch (IOException e7) {
                        inputStreamReader = null;
                        fileInputStream = openFileInput;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return jSONArray;
                    } catch (JSONException e8) {
                        inputStreamReader = null;
                        fileInputStream = openFileInput;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return jSONArray;
                    } catch (Throwable th42) {
                        inputStreamReader2 = null;
                        th = th42;
                        bufferedReader2 = null;
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        throw th;
                    }
                }
                bufferedReader2 = null;
                inputStreamReader2 = null;
                openFileInput = null;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e9) {
                    }
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
            } catch (IOException e10) {
                inputStreamReader = null;
                fileInputStream = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return jSONArray;
            } catch (JSONException e11) {
                inputStreamReader = null;
                fileInputStream = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return jSONArray;
            } catch (Throwable th422) {
                inputStreamReader2 = null;
                openFileInput = null;
                th2 = th422;
                bufferedReader2 = null;
                th = th2;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                throw th;
            }
        }
        return jSONArray;
    }

    public static void a(d dVar, Context context) {
        if (context != null) {
            synchronized (b) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", UUID.randomUUID().toString());
                    jSONObject.put("type", dVar.a());
                    jSONObject.put("time", r.a(dVar.b()));
                    jSONObject.put("session_time", r.a(dVar.c()));
                    jSONObject.put("session_id", dVar.d());
                    jSONObject.put("data", dVar.e() != null ? new JSONObject(dVar.e()) : new JSONObject());
                    jSONObject.put("attempt", 0);
                    FileOutputStream openFileOutput = context.openFileOutput("debuglogs", 32768);
                    openFileOutput.write((jSONObject.toString() + "\n").getBytes());
                    openFileOutput.close();
                    b(context, context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).getInt("EventCount", 0) + 1);
                } catch (Exception e) {
                }
            }
        }
    }

    public static void a(String str) {
        Integer num = (Integer) d.get(str);
        if (num == null) {
            num = Integer.valueOf(0);
        } else {
            d.remove(str);
        }
        d.put(str, Integer.valueOf(num.intValue() + 1));
    }

    public static int b(Context context) {
        return context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).getInt("EventCount", 0) - c.size();
    }

    private static void b(Context context, int i) {
        Editor edit = context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).edit();
        String str = "EventCount";
        if (i < 0) {
            i = 0;
        }
        edit.putInt(str, i).apply();
    }

    public static void b(String str) {
        if (d.containsKey(str)) {
            d.remove(str);
        }
        c.add(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @WorkerThread
    public static boolean c(Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        FileOutputStream fileOutputStream;
        Throwable th2;
        Object obj;
        Object bufferedReader2;
        FileOutputStream fileOutputStream2;
        Object inputStreamReader2;
        FileOutputStream fileOutputStream3 = null;
        JSONArray jSONArray = new JSONArray();
        synchronized (b) {
            FileInputStream openFileInput;
            InputStreamReader inputStreamReader3;
            BufferedReader bufferedReader3;
            try {
                if (new File(context.getFilesDir(), "debuglogs").exists()) {
                    openFileInput = context.openFileInput("debuglogs");
                    try {
                        inputStreamReader3 = new InputStreamReader(openFileInput);
                        try {
                            bufferedReader3 = new BufferedReader(inputStreamReader3);
                            while (true) {
                                try {
                                    String readLine = bufferedReader3.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(readLine);
                                    readLine = jSONObject.getString("id");
                                    if (!c.contains(readLine)) {
                                        if (d.containsKey(readLine)) {
                                            jSONObject.put("attempt", d.get(readLine));
                                        }
                                        jSONArray.put(jSONObject);
                                    }
                                } catch (IOException e) {
                                } catch (JSONException e2) {
                                } catch (Throwable th3) {
                                    th = th3;
                                    fileInputStream = openFileInput;
                                    inputStreamReader2 = inputStreamReader3;
                                    bufferedReader2 = bufferedReader3;
                                    fileOutputStream = fileOutputStream3;
                                    th2 = th;
                                    if (bufferedReader2 != null) {
                                        try {
                                            bufferedReader2.close();
                                        } catch (IOException e3) {
                                            c.clear();
                                            d.clear();
                                            throw th2;
                                        }
                                    }
                                    if (inputStreamReader2 != null) {
                                        inputStreamReader2.close();
                                    }
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    c.clear();
                                    d.clear();
                                    throw th2;
                                }
                            }
                        } catch (IOException e4) {
                            obj = fileOutputStream3;
                            if (bufferedReader3 != null) {
                                try {
                                    bufferedReader3.close();
                                } catch (IOException e5) {
                                    c.clear();
                                    d.clear();
                                    return false;
                                }
                            }
                            if (inputStreamReader3 != null) {
                                inputStreamReader3.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            if (fileOutputStream3 != null) {
                                fileOutputStream3.close();
                            }
                            c.clear();
                            d.clear();
                            return false;
                        } catch (JSONException e6) {
                            obj = fileOutputStream3;
                            if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            if (inputStreamReader3 != null) {
                                inputStreamReader3.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            if (fileOutputStream3 != null) {
                                fileOutputStream3.close();
                            }
                            c.clear();
                            d.clear();
                            return false;
                        } catch (Throwable th4) {
                            fileInputStream = openFileInput;
                            inputStreamReader2 = inputStreamReader3;
                            bufferedReader2 = fileOutputStream3;
                            fileOutputStream2 = fileOutputStream3;
                            th2 = th4;
                            fileOutputStream = fileOutputStream2;
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            if (inputStreamReader2 != null) {
                                inputStreamReader2.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            c.clear();
                            d.clear();
                            throw th2;
                        }
                    } catch (IOException e7) {
                        obj = fileOutputStream3;
                        bufferedReader2 = fileOutputStream3;
                        if (bufferedReader3 != null) {
                            bufferedReader3.close();
                        }
                        if (inputStreamReader3 != null) {
                            inputStreamReader3.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        if (fileOutputStream3 != null) {
                            fileOutputStream3.close();
                        }
                        c.clear();
                        d.clear();
                        return false;
                    } catch (JSONException e8) {
                        obj = fileOutputStream3;
                        bufferedReader2 = fileOutputStream3;
                        if (bufferedReader3 != null) {
                            bufferedReader3.close();
                        }
                        if (inputStreamReader3 != null) {
                            inputStreamReader3.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        if (fileOutputStream3 != null) {
                            fileOutputStream3.close();
                        }
                        c.clear();
                        d.clear();
                        return false;
                    } catch (Throwable th42) {
                        bufferedReader2 = fileOutputStream3;
                        fileInputStream = openFileInput;
                        inputStreamReader2 = fileOutputStream3;
                        th = th42;
                        fileOutputStream = fileOutputStream3;
                        th2 = th;
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        c.clear();
                        d.clear();
                        throw th2;
                    }
                }
                obj = fileOutputStream3;
                bufferedReader2 = fileOutputStream3;
                inputStreamReader2 = fileOutputStream3;
                b(context, b(context));
                if (bufferedReader3 != null) {
                    try {
                        bufferedReader3.close();
                    } catch (IOException e9) {
                    }
                }
                if (inputStreamReader3 != null) {
                    inputStreamReader3.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream3 != null) {
                    fileOutputStream3.close();
                }
                c.clear();
                d.clear();
                return true;
            } catch (IOException e10) {
                obj = fileOutputStream3;
                bufferedReader2 = fileOutputStream3;
                inputStreamReader2 = fileOutputStream3;
                if (bufferedReader3 != null) {
                    bufferedReader3.close();
                }
                if (inputStreamReader3 != null) {
                    inputStreamReader3.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream3 != null) {
                    fileOutputStream3.close();
                }
                c.clear();
                d.clear();
                return false;
            } catch (JSONException e11) {
                obj = fileOutputStream3;
                bufferedReader2 = fileOutputStream3;
                inputStreamReader2 = fileOutputStream3;
                if (bufferedReader3 != null) {
                    bufferedReader3.close();
                }
                if (inputStreamReader3 != null) {
                    inputStreamReader3.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream3 != null) {
                    fileOutputStream3.close();
                }
                c.clear();
                d.clear();
                return false;
            } catch (Throwable th422) {
                bufferedReader2 = fileOutputStream3;
                inputStreamReader2 = fileOutputStream3;
                fileInputStream = fileOutputStream3;
                fileOutputStream2 = fileOutputStream3;
                th2 = th422;
                fileOutputStream = fileOutputStream2;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                c.clear();
                d.clear();
                throw th2;
            }
        }
    }

    public static boolean c(String str) {
        return c.contains(str) || d.containsKey(str);
    }
}
