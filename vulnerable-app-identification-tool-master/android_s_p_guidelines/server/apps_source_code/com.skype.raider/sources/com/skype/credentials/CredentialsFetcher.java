package com.skype.credentials;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.facebook.common.logging.FLog;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class CredentialsFetcher {
    public static String a(Context context) {
        Throwable e;
        Throwable e2;
        InputStream input = null;
        String result = null;
        try {
            InputStream input2 = new BufferedInputStream(new FileInputStream(new File(context.getFilesDir(), "shared.xml")));
            try {
                XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                parser.setInput(input2, Constants.ENCODING);
                int eventType = parser.getEventType();
                while (eventType != 1) {
                    eventType = parser.next();
                    if (eventType == 2) {
                        String tagName = parser.getName();
                        if (tagName.equals("Account")) {
                            while (true) {
                                if (eventType == 3 && tagName.equals("Account")) {
                                    break;
                                }
                                eventType = parser.next();
                                if (eventType == 3 || eventType == 2) {
                                    tagName = parser.getName();
                                    if (eventType == 2 && tagName.equals("Default")) {
                                        result = parser.nextText();
                                        break;
                                    }
                                }
                            }
                        }
                        if (result == null) {
                        }
                    }
                }
                try {
                    input2.close();
                    input = input2;
                } catch (IOException e3) {
                    input = input2;
                }
            } catch (XmlPullParserException e4) {
                e = e4;
                input = input2;
                e2 = e;
                try {
                    FLog.e("CredentialsFetcher", "Can't parse xml", e2);
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e5) {
                        }
                    }
                    return result;
                } catch (Throwable th) {
                    e = th;
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw e;
                }
            } catch (IOException e7) {
                e = e7;
                input = input2;
                e2 = e;
                FLog.e("CredentialsFetcher", "Can't parse xml", e2);
                if (input != null) {
                    input.close();
                }
                return result;
            } catch (Throwable th2) {
                e = th2;
                input = input2;
                if (input != null) {
                    input.close();
                }
                throw e;
            }
        } catch (XmlPullParserException e8) {
            e = e8;
            e2 = e;
            FLog.e("CredentialsFetcher", "Can't parse xml", e2);
            if (input != null) {
                input.close();
            }
            return result;
        } catch (IOException e9) {
            e = e9;
            e2 = e;
            FLog.e("CredentialsFetcher", "Can't parse xml", e2);
            if (input != null) {
                input.close();
            }
            return result;
        }
        return result;
    }

    public static String a(Context context, String username) {
        Throwable e;
        Throwable e2;
        InputStream input = null;
        String credentialsHash = null;
        try {
            InputStream input2 = new BufferedInputStream(new FileInputStream(new File(new File(context.getFilesDir(), username), "config.xml")));
            try {
                XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                parser.setInput(input2, Constants.ENCODING);
                int eventType = parser.getEventType();
                while (eventType != 1) {
                    eventType = parser.next();
                    if (eventType == 2) {
                        String tagName = parser.getName();
                        if (tagName.equals("Account")) {
                            while (true) {
                                if (eventType == 3 && tagName.equals("Account")) {
                                    break;
                                }
                                eventType = parser.next();
                                if (eventType == 3 || eventType == 2) {
                                    tagName = parser.getName();
                                    if (eventType == 2 && tagName.equals("Credentials3")) {
                                        credentialsHash = parser.nextText();
                                        break;
                                    }
                                }
                            }
                        }
                        if (credentialsHash == null) {
                        }
                    }
                }
                try {
                    input2.close();
                    input = input2;
                } catch (IOException e3) {
                    input = input2;
                }
            } catch (XmlPullParserException e4) {
                e = e4;
                input = input2;
                e2 = e;
                try {
                    FLog.e("CredentialsFetcher", "Can't parse xml", e2);
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e5) {
                        }
                    }
                    return a(credentialsHash);
                } catch (Throwable th) {
                    e = th;
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw e;
                }
            } catch (IOException e7) {
                e = e7;
                input = input2;
                e2 = e;
                FLog.e("CredentialsFetcher", "Can't parse xml", e2);
                if (input != null) {
                    input.close();
                }
                return a(credentialsHash);
            } catch (Throwable th2) {
                e = th2;
                input = input2;
                if (input != null) {
                    input.close();
                }
                throw e;
            }
        } catch (XmlPullParserException e8) {
            e = e8;
            e2 = e;
            FLog.e("CredentialsFetcher", "Can't parse xml", e2);
            if (input != null) {
                input.close();
            }
            return a(credentialsHash);
        } catch (IOException e9) {
            e = e9;
            e2 = e;
            FLog.e("CredentialsFetcher", "Can't parse xml", e2);
            if (input != null) {
                input.close();
            }
            return a(credentialsHash);
        }
        return a(credentialsHash);
    }

    private static String a(String string) {
        if (string == null) {
            FLog.i("CredentialsFetcher", "Hash is empty");
            return null;
        } else if (string.length() < 32) {
            FLog.i("CredentialsFetcher", "credentials hash is too small: " + string.length());
            return null;
        } else {
            byte[] data = new byte[16];
            for (int i = 0; i < 32; i += 2) {
                data[i / 2] = (byte) ((Character.digit(string.charAt(i), 16) << 4) + Character.digit(string.charAt(i + 1), 16));
            }
            return Base64.encodeToString(data, 0, 16, 2);
        }
    }

    public static String b(Context context, String userId) {
        SharedPreferences pref = context.getSharedPreferences("t2_update", 0);
        if (userId.equals(pref.getString("skypeId", null))) {
            return pref.getString("oAuthRefreshToken", null);
        }
        FLog.i("CredentialsFetcher", "Msa account owner doesn't matches with last logged in user. Ignore");
        return null;
    }

    public static void b(Context context) {
        context.getSharedPreferences("t2_update", 0).edit().putBoolean("migrated", true).apply();
    }

    public static boolean c(Context context) {
        return context.getSharedPreferences("t2_update", 0).getBoolean("migrated", false);
    }
}
