package com.adjust.sdk;

import java.util.Arrays;
import java.util.Locale;

public class Logger implements ILogger {
    private static String formatErrorMessage = "Error formating log message: %s, with params: %s";
    private boolean isProductionEnvironment = false;
    private LogLevel logLevel;
    private boolean logLevelLocked = false;

    public Logger() {
        setLogLevel(LogLevel.INFO, this.isProductionEnvironment);
    }

    public void setLogLevel(LogLevel logLevel, boolean isProductionEnvironment) {
        if (!this.logLevelLocked) {
            this.logLevel = logLevel;
            this.isProductionEnvironment = isProductionEnvironment;
        }
    }

    public void setLogLevelString(String logLevelString, boolean isProductionEnvironment) {
        if (logLevelString != null) {
            try {
                setLogLevel(LogLevel.valueOf(logLevelString.toUpperCase(Locale.US)), isProductionEnvironment);
            } catch (IllegalArgumentException e) {
                error("Malformed logLevel '%s', falling back to 'info'", logLevelString);
            }
        }
    }

    public void verbose(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 2) {
            try {
                Util.formatString(message, parameters);
            } catch (Exception e) {
                Util.formatString(formatErrorMessage, message, Arrays.toString(parameters));
            }
        }
    }

    public void debug(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 3) {
            try {
                Util.formatString(message, parameters);
            } catch (Exception e) {
                Util.formatString(formatErrorMessage, message, Arrays.toString(parameters));
            }
        }
    }

    public void info(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 4) {
            try {
                Util.formatString(message, parameters);
            } catch (Exception e) {
                Util.formatString(formatErrorMessage, message, Arrays.toString(parameters));
            }
        }
    }

    public void warn(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 5) {
            try {
                Util.formatString(message, parameters);
            } catch (Exception e) {
                Util.formatString(formatErrorMessage, message, Arrays.toString(parameters));
            }
        }
    }

    public void warnInProduction(String message, Object... parameters) {
        if (this.logLevel.androidLogLevel <= 5) {
            try {
                Util.formatString(message, parameters);
            } catch (Exception e) {
                Util.formatString(formatErrorMessage, message, Arrays.toString(parameters));
            }
        }
    }

    public void error(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 6) {
            try {
                Util.formatString(message, parameters);
            } catch (Exception e) {
                Util.formatString(formatErrorMessage, message, Arrays.toString(parameters));
            }
        }
    }

    public void Assert(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 7) {
            try {
                Util.formatString(message, parameters);
            } catch (Exception e) {
                Util.formatString(formatErrorMessage, message, Arrays.toString(parameters));
            }
        }
    }

    public void lockLogLevel() {
        this.logLevelLocked = true;
    }
}
