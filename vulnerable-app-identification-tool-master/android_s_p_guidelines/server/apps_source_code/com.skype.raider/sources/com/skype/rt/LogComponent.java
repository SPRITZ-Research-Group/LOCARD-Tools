package com.skype.rt;

import com.skype.rt.Spl.Pii;

public final class LogComponent {
    private LogLevel level = LogLevel.fromInt(nativeGetLogLevel(this.nativePtr));
    private final String name;
    private final long nativePtr;

    private native long nativeCreate(String str);

    private static native void nativeDispose(long j);

    private static native int nativeGetLogLevel(long j);

    private static native boolean nativeIsSafe(long j);

    private static native void nativeLog(long j, int i, String str, int i2, long j2);

    private static native int nativeSetLogLevel(long j, int i);

    private static native void nativeSetSafe(long j, boolean z);

    public final void log(LogLevel level, String fmt) {
        if (this.level.value() <= level.value()) {
            nativeLog(this.nativePtr, level.value(), fmt, 0, 0);
        }
    }

    public final void log(LogLevel level, String fmt, Object arg) {
        if (this.level.value() <= level.value()) {
            Pii.updateAnonymizeStatus();
            if (arg instanceof Pii) {
                arg = ((Pii) arg).getValue();
            }
            nativeLog(this.nativePtr, level.value(), String.format(fmt, new Object[]{arg}), 0, 0);
        }
    }

    public final void log(LogLevel level, String fmt, Object arg1, Object arg2) {
        if (this.level.value() <= level.value()) {
            Pii.updateAnonymizeStatus();
            if (arg1 instanceof Pii) {
                arg1 = ((Pii) arg1).getValue();
            }
            if (arg2 instanceof Pii) {
                arg2 = ((Pii) arg2).getValue();
            }
            nativeLog(this.nativePtr, level.value(), String.format(fmt, new Object[]{arg1, arg2}), 0, 0);
        }
    }

    public final void log(LogLevel level, String fmt, Object arg1, Object arg2, Object arg3) {
        if (this.level.value() <= level.value()) {
            String msg;
            int fmtHash = 0;
            long lineNum = 0;
            if (arg3.equals(LogFactory.getInstance())) {
                fmtHash = ((Integer) arg1).intValue();
                lineNum = ((Long) arg2).longValue();
                msg = fmt;
            } else {
                Pii.updateAnonymizeStatus();
                if (arg1 instanceof Pii) {
                    arg1 = ((Pii) arg1).getValue();
                }
                if (arg2 instanceof Pii) {
                    arg2 = ((Pii) arg2).getValue();
                }
                if (arg3 instanceof Pii) {
                    arg3 = ((Pii) arg3).getValue();
                }
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3});
            }
            nativeLog(this.nativePtr, level.value(), msg, fmtHash, lineNum);
        }
    }

    public final void log(LogLevel level, String fmt, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (this.level.value() <= level.value()) {
            String msg;
            int fmtHash = 0;
            long lineNum = 0;
            Pii.updateAnonymizeStatus();
            if (arg1 instanceof Pii) {
                arg1 = ((Pii) arg1).getValue();
            }
            if (arg4.equals(LogFactory.getInstance())) {
                fmtHash = ((Integer) arg2).intValue();
                lineNum = ((Long) arg3).longValue();
                msg = String.format(fmt, new Object[]{arg1});
            } else {
                if (arg2 instanceof Pii) {
                    arg2 = ((Pii) arg2).getValue();
                }
                if (arg3 instanceof Pii) {
                    arg3 = ((Pii) arg3).getValue();
                }
                if (arg4 instanceof Pii) {
                    arg4 = ((Pii) arg4).getValue();
                }
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3, arg4});
            }
            nativeLog(this.nativePtr, level.value(), msg, fmtHash, lineNum);
        }
    }

    public final void log(LogLevel level, String fmt, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
        if (this.level.value() <= level.value()) {
            String msg;
            int fmtHash = 0;
            long lineNum = 0;
            Pii.updateAnonymizeStatus();
            if (arg1 instanceof Pii) {
                arg1 = ((Pii) arg1).getValue();
            }
            if (arg2 instanceof Pii) {
                arg2 = ((Pii) arg2).getValue();
            }
            if (arg5.equals(LogFactory.getInstance())) {
                fmtHash = ((Integer) arg3).intValue();
                lineNum = ((Long) arg4).longValue();
                msg = String.format(fmt, new Object[]{arg1, arg2});
            } else {
                if (arg3 instanceof Pii) {
                    arg3 = ((Pii) arg3).getValue();
                }
                if (arg4 instanceof Pii) {
                    arg4 = ((Pii) arg4).getValue();
                }
                if (arg5 instanceof Pii) {
                    arg5 = ((Pii) arg5).getValue();
                }
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3, arg4, arg5});
            }
            nativeLog(this.nativePtr, level.value(), msg, fmtHash, lineNum);
        }
    }

    public final void log(LogLevel level, String fmt, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
        if (this.level.value() <= level.value()) {
            String msg;
            int fmtHash = 0;
            long lineNum = 0;
            Pii.updateAnonymizeStatus();
            if (arg1 instanceof Pii) {
                arg1 = ((Pii) arg1).getValue();
            }
            if (arg2 instanceof Pii) {
                arg2 = ((Pii) arg2).getValue();
            }
            if (arg3 instanceof Pii) {
                arg3 = ((Pii) arg3).getValue();
            }
            if (arg6.equals(LogFactory.getInstance())) {
                fmtHash = ((Integer) arg4).intValue();
                lineNum = ((Long) arg5).longValue();
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3});
            } else {
                if (arg4 instanceof Pii) {
                    arg4 = ((Pii) arg4).getValue();
                }
                if (arg5 instanceof Pii) {
                    arg5 = ((Pii) arg5).getValue();
                }
                if (arg6 instanceof Pii) {
                    arg6 = ((Pii) arg6).getValue();
                }
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3, arg4, arg5, arg6});
            }
            nativeLog(this.nativePtr, level.value(), msg, fmtHash, lineNum);
        }
    }

    public final void log(LogLevel level, String fmt, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        if (this.level.value() <= level.value()) {
            String msg;
            int fmtHash = 0;
            long lineNum = 0;
            Pii.updateAnonymizeStatus();
            if (arg1 instanceof Pii) {
                arg1 = ((Pii) arg1).getValue();
            }
            if (arg2 instanceof Pii) {
                arg2 = ((Pii) arg2).getValue();
            }
            if (arg3 instanceof Pii) {
                arg3 = ((Pii) arg3).getValue();
            }
            if (arg4 instanceof Pii) {
                arg4 = ((Pii) arg4).getValue();
            }
            if (arg7.equals(LogFactory.getInstance())) {
                fmtHash = ((Integer) arg5).intValue();
                lineNum = ((Long) arg6).longValue();
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3, arg4});
            } else {
                if (arg5 instanceof Pii) {
                    arg5 = ((Pii) arg5).getValue();
                }
                if (arg6 instanceof Pii) {
                    arg6 = ((Pii) arg6).getValue();
                }
                if (arg7 instanceof Pii) {
                    arg7 = ((Pii) arg7).getValue();
                }
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3, arg4, arg5, arg6, arg7});
            }
            nativeLog(this.nativePtr, level.value(), msg, fmtHash, lineNum);
        }
    }

    public final void log(LogLevel level, String fmt, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8) {
        if (this.level.value() <= level.value()) {
            String msg;
            int fmtHash = 0;
            long lineNum = 0;
            Pii.updateAnonymizeStatus();
            if (arg1 instanceof Pii) {
                arg1 = ((Pii) arg1).getValue();
            }
            if (arg2 instanceof Pii) {
                arg2 = ((Pii) arg2).getValue();
            }
            if (arg3 instanceof Pii) {
                arg3 = ((Pii) arg3).getValue();
            }
            if (arg4 instanceof Pii) {
                arg4 = ((Pii) arg4).getValue();
            }
            if (arg5 instanceof Pii) {
                arg5 = ((Pii) arg5).getValue();
            }
            if (arg8.equals(LogFactory.getInstance())) {
                fmtHash = ((Integer) arg6).intValue();
                lineNum = ((Long) arg7).longValue();
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3, arg4, arg5});
            } else {
                if (arg6 instanceof Pii) {
                    arg6 = ((Pii) arg6).getValue();
                }
                if (arg7 instanceof Pii) {
                    arg7 = ((Pii) arg7).getValue();
                }
                if (arg8 instanceof Pii) {
                    arg8 = ((Pii) arg8).getValue();
                }
                msg = String.format(fmt, new Object[]{arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8});
            }
            nativeLog(this.nativePtr, level.value(), msg, fmtHash, lineNum);
        }
    }

    public final void log(LogLevel level, String fmt, Object... args) {
        if (this.level.value() <= level.value()) {
            int fmtHash = 0;
            long lineNum = 0;
            int argsLen = args.length;
            if (argsLen >= 3 && args[argsLen - 1].equals(LogFactory.getInstance())) {
                fmtHash = ((Integer) args[argsLen - 3]).intValue();
                lineNum = ((Long) args[argsLen - 2]).longValue();
                argsLen -= 3;
            }
            Pii.updateAnonymizeStatus();
            for (int i = 0; i != argsLen; i++) {
                if (args[i] instanceof Pii) {
                    args[i] = ((Pii) args[i]).getValue();
                }
            }
            nativeLog(this.nativePtr, level.value(), String.format(fmt, args), fmtHash, lineNum);
        }
    }

    public final String name() {
        return this.name;
    }

    public final LogLevel level() {
        return this.level;
    }

    public final boolean isSafe() {
        return nativeIsSafe(this.nativePtr);
    }

    public final void setLevel(LogLevel level) {
        nativeSetLogLevel(this.nativePtr, level.value());
    }

    LogComponent(String name) {
        this.name = name;
        this.nativePtr = nativeCreate(name);
    }

    final void setSafe(boolean safe) {
        nativeSetSafe(this.nativePtr, safe);
    }

    final void setOnlyJavaLL(int level) {
        this.level = LogLevel.fromInt(level);
    }
}
