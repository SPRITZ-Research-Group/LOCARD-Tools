package com.google.android.exoplayer2.scheduler;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.PowerManager;
import defpackage.beg;

public final class a {
    private static final String[] a = null;
    private final int b;

    public a() {
        this(1);
    }

    public a(int i) {
        this.b = i;
    }

    public final String toString() {
        return super.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(Context context) {
        boolean isActiveNetworkMetered;
        Intent registerReceiver;
        Object obj;
        PowerManager powerManager;
        boolean isDeviceIdleMode;
        int i = this.b & 7;
        if (i != 0) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                Object obj2;
                int type;
                if (beg.a >= 23) {
                    Network activeNetwork = connectivityManager.getActiveNetwork();
                    if (activeNetwork != null) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                        obj2 = (networkCapabilities == null || !networkCapabilities.hasCapability(16)) ? 1 : null;
                    }
                    obj2 = null;
                    if (obj2 != null) {
                        if (i != 1) {
                            if (i == 3) {
                                if (beg.a < 16) {
                                    isActiveNetworkMetered = connectivityManager.isActiveNetworkMetered();
                                } else {
                                    type = activeNetworkInfo.getType();
                                    isActiveNetworkMetered = (type != 1 || type == 7 || type == 9) ? false : true;
                                }
                                if (i == 2) {
                                    if (i != 4) {
                                        throw new IllegalStateException();
                                    }
                                    if (isActiveNetworkMetered) {
                                        if (((this.b & 16) == 0 ? 1 : null) != null) {
                                            registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                                            if (registerReceiver != null) {
                                                i = registerReceiver.getIntExtra("status", -1);
                                                if (i != 2) {
                                                }
                                            }
                                            obj = null;
                                            if (obj != null) {
                                                if (((this.b & 8) == 0 ? 1 : null) != null) {
                                                    powerManager = (PowerManager) context.getSystemService("power");
                                                    if (beg.a < 23) {
                                                        isDeviceIdleMode = powerManager.isDeviceIdleMode();
                                                    } else if (beg.a < 20 ? powerManager.isScreenOn() : powerManager.isInteractive()) {
                                                        isDeviceIdleMode = false;
                                                    }
                                                    if (isDeviceIdleMode) {
                                                    }
                                                }
                                                isDeviceIdleMode = true;
                                                return isDeviceIdleMode;
                                            }
                                        }
                                        obj = 1;
                                        if (obj != null) {
                                            if ((this.b & 8) == 0) {
                                            }
                                            if (((this.b & 8) == 0 ? 1 : null) != null) {
                                                powerManager = (PowerManager) context.getSystemService("power");
                                                if (beg.a < 23) {
                                                    isDeviceIdleMode = false;
                                                } else {
                                                    isDeviceIdleMode = powerManager.isDeviceIdleMode();
                                                }
                                                if (isDeviceIdleMode) {
                                                }
                                            }
                                            isDeviceIdleMode = true;
                                            if (isDeviceIdleMode) {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                obj2 = 1;
                if (obj2 != null) {
                    if (i != 1) {
                        if (i == 3) {
                            if (beg.a < 16) {
                                type = activeNetworkInfo.getType();
                                if (type != 1) {
                                }
                            } else {
                                isActiveNetworkMetered = connectivityManager.isActiveNetworkMetered();
                            }
                            if (i == 2) {
                                if (i != 4) {
                                    throw new IllegalStateException();
                                }
                                if (isActiveNetworkMetered) {
                                    if ((this.b & 16) == 0) {
                                    }
                                    if (((this.b & 16) == 0 ? 1 : null) != null) {
                                        registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                                        if (registerReceiver != null) {
                                            i = registerReceiver.getIntExtra("status", -1);
                                            if (i != 2) {
                                            }
                                        }
                                        obj = null;
                                        if (obj != null) {
                                            if ((this.b & 8) == 0) {
                                            }
                                            if (((this.b & 8) == 0 ? 1 : null) != null) {
                                                powerManager = (PowerManager) context.getSystemService("power");
                                                if (beg.a < 23) {
                                                    isDeviceIdleMode = powerManager.isDeviceIdleMode();
                                                } else {
                                                    isDeviceIdleMode = false;
                                                }
                                                if (isDeviceIdleMode) {
                                                }
                                            }
                                            isDeviceIdleMode = true;
                                            if (isDeviceIdleMode) {
                                            }
                                        }
                                    }
                                    obj = 1;
                                    if (obj != null) {
                                        if ((this.b & 8) == 0) {
                                        }
                                        if (((this.b & 8) == 0 ? 1 : null) != null) {
                                            powerManager = (PowerManager) context.getSystemService("power");
                                            if (beg.a < 23) {
                                                isDeviceIdleMode = false;
                                            } else {
                                                isDeviceIdleMode = powerManager.isDeviceIdleMode();
                                            }
                                            if (isDeviceIdleMode) {
                                            }
                                        }
                                        isDeviceIdleMode = true;
                                        if (isDeviceIdleMode) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            isActiveNetworkMetered = false;
            if (isActiveNetworkMetered) {
                if ((this.b & 16) == 0) {
                }
                if (((this.b & 16) == 0 ? 1 : null) != null) {
                    registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                    if (registerReceiver != null) {
                        i = registerReceiver.getIntExtra("status", -1);
                        if (i != 2) {
                        }
                    }
                    obj = null;
                    if (obj != null) {
                        if ((this.b & 8) == 0) {
                        }
                        if (((this.b & 8) == 0 ? 1 : null) != null) {
                            powerManager = (PowerManager) context.getSystemService("power");
                            if (beg.a < 23) {
                                isDeviceIdleMode = powerManager.isDeviceIdleMode();
                            } else {
                                isDeviceIdleMode = false;
                            }
                            if (isDeviceIdleMode) {
                            }
                        }
                        isDeviceIdleMode = true;
                        if (isDeviceIdleMode) {
                        }
                    }
                }
                obj = 1;
                if (obj != null) {
                    if ((this.b & 8) == 0) {
                    }
                    if (((this.b & 8) == 0 ? 1 : null) != null) {
                        powerManager = (PowerManager) context.getSystemService("power");
                        if (beg.a < 23) {
                            isDeviceIdleMode = false;
                        } else {
                            isDeviceIdleMode = powerManager.isDeviceIdleMode();
                        }
                        if (isDeviceIdleMode) {
                        }
                    }
                    isDeviceIdleMode = true;
                    if (isDeviceIdleMode) {
                    }
                }
            }
        }
        isActiveNetworkMetered = true;
        if (isActiveNetworkMetered) {
            if ((this.b & 16) == 0) {
            }
            if (((this.b & 16) == 0 ? 1 : null) != null) {
                registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (registerReceiver != null) {
                    i = registerReceiver.getIntExtra("status", -1);
                    if (i != 2) {
                    }
                }
                obj = null;
                if (obj != null) {
                    if ((this.b & 8) == 0) {
                    }
                    if (((this.b & 8) == 0 ? 1 : null) != null) {
                        powerManager = (PowerManager) context.getSystemService("power");
                        if (beg.a < 23) {
                            isDeviceIdleMode = powerManager.isDeviceIdleMode();
                        } else {
                            isDeviceIdleMode = false;
                        }
                        if (isDeviceIdleMode) {
                        }
                    }
                    isDeviceIdleMode = true;
                    if (isDeviceIdleMode) {
                    }
                }
            }
            obj = 1;
            if (obj != null) {
                if ((this.b & 8) == 0) {
                }
                if (((this.b & 8) == 0 ? 1 : null) != null) {
                    powerManager = (PowerManager) context.getSystemService("power");
                    if (beg.a < 23) {
                        isDeviceIdleMode = false;
                    } else {
                        isDeviceIdleMode = powerManager.isDeviceIdleMode();
                    }
                    if (isDeviceIdleMode) {
                    }
                }
                isDeviceIdleMode = true;
                if (isDeviceIdleMode) {
                }
            }
        }
    }
}
