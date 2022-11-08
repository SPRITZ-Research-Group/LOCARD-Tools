package com.skype.rt;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.os.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SysInfo extends RtContext {
    private static String currentProcName = null;

    static String readProperty(String name) {
        Throwable th;
        Process p = null;
        BufferedReader br = null;
        String ret = "";
        try {
            p = new ProcessBuilder(new String[]{"/system/bin/getprop", name}).redirectErrorStream(true).start();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (true) {
                try {
                    String line = br2.readLine();
                    if (line != null) {
                        ret = line;
                    } else {
                        try {
                            br2.close();
                        } catch (IOException e) {
                        }
                        if (p != null) {
                            p.destroy();
                            br = br2;
                        }
                    }
                } catch (IOException e2) {
                    br = br2;
                } catch (Throwable th2) {
                    th = th2;
                    br = br2;
                }
            }
        } catch (IOException e3) {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e4) {
                }
            }
            if (p != null) {
                p.destroy();
            }
            return ret;
        } catch (Throwable th3) {
            th = th3;
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e5) {
                }
            }
            if (p != null) {
                p.destroy();
            }
            throw th;
        }
        return ret;
    }

    static String getAppName() {
        Throwable th;
        int pid = Process.myPid();
        File cmdline = new File("/proc/" + pid + "/cmdline");
        if (currentProcName != null) {
            return currentProcName;
        }
        BufferedReader br = null;
        String readLine;
        ActivityManager manager;
        try {
            BufferedReader br2 = new BufferedReader(new FileReader(cmdline));
            try {
                readLine = br2.readLine();
                currentProcName = readLine;
                if (readLine != null) {
                    readLine = currentProcName.trim();
                    currentProcName = readLine;
                    try {
                        br2.close();
                        return readLine;
                    } catch (IOException e) {
                        return readLine;
                    }
                }
                try {
                    br2.close();
                    br = br2;
                } catch (IOException e2) {
                    br = br2;
                }
                manager = (ActivityManager) RtContext.getContext().getSystemService("activity");
                if (manager.getRunningAppProcesses() != null) {
                    for (RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
                        if (processInfo.pid == pid) {
                            readLine = processInfo.processName;
                            currentProcName = readLine;
                            return readLine;
                        }
                    }
                }
                readLine = "";
                currentProcName = readLine;
                return readLine;
            } catch (IOException e3) {
                br = br2;
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e4) {
                    }
                }
                manager = (ActivityManager) RtContext.getContext().getSystemService("activity");
                if (manager.getRunningAppProcesses() != null) {
                    for (RunningAppProcessInfo processInfo2 : manager.getRunningAppProcesses()) {
                        if (processInfo2.pid == pid) {
                            readLine = processInfo2.processName;
                            currentProcName = readLine;
                            return readLine;
                        }
                    }
                }
                readLine = "";
                currentProcName = readLine;
                return readLine;
            } catch (Throwable th2) {
                th = th2;
                br = br2;
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e5) {
                    }
                }
                throw th;
            }
        } catch (IOException e6) {
            if (br != null) {
                br.close();
            }
            manager = (ActivityManager) RtContext.getContext().getSystemService("activity");
            if (manager.getRunningAppProcesses() != null) {
                for (RunningAppProcessInfo processInfo22 : manager.getRunningAppProcesses()) {
                    if (processInfo22.pid == pid) {
                        readLine = processInfo22.processName;
                        currentProcName = readLine;
                        return readLine;
                    }
                }
            }
            readLine = "";
            currentProcName = readLine;
            return readLine;
        } catch (Throwable th3) {
            th = th3;
            if (br != null) {
                br.close();
            }
            throw th;
        }
    }
}
