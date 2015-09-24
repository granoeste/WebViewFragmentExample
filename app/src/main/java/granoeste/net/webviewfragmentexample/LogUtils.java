package granoeste.net.webviewfragmentexample;

import android.util.Log;

import java.text.MessageFormat;

public class LogUtils {
    public static boolean DEBUG = BuildConfig.DEBUG; // BuildConfig.DEBUG
    public static String LOG_PREFIX = "GR_";

    // IllegalArgumentException is thrown if the tag.length() > 23.
    private static final int MAX_LOG_TAG_LENGTH = 23;

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX.length()) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX.length() - 1);
        }

        return LOG_PREFIX + str;
    }

    public static String makeLogTag(Class<?> cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void LOGD(final String tag, String message) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, message);
        }
    }

    public static void LOGD(final String tag, String message, Object... args) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, MessageFormat.format(message, args));
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause, Object... args) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, MessageFormat.format(message, args), cause);
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, message, cause);
        }
    }

    public static void LOGV(final String tag, String message) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (DEBUG || Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, message);
        }
    }

    public static void LOGV(final String tag, String message, Object... args) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (DEBUG || Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, MessageFormat.format(message, args));
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause, Object... args) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (DEBUG || Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, MessageFormat.format(message, args), cause);
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (DEBUG || Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, message, cause);
        }
    }

    public static void LOGI(final String tag, String message) {
        Log.i(tag, message);
    }

    public static void LOGI(final String tag, String message, Object... args) {
        Log.i(tag, MessageFormat.format(message, args));
    }

    public static void LOGI(final String tag, String message, Throwable cause, Object... args) {
        Log.i(tag, MessageFormat.format(message, args), cause);
    }

    public static void LOGI(final String tag, String message, Throwable cause) {
        Log.i(tag, message, cause);
    }

    public static void LOGW(final String tag, String message) {
        Log.w(tag, message);
    }

    public static void LOGW(final String tag, String message, Object... args) {
        Log.w(tag, MessageFormat.format(message, args));
    }

    public static void LOGW(final String tag, String message, Throwable cause, Object... args) {
        Log.w(tag, MessageFormat.format(message, args), cause);
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        Log.w(tag, message, cause);
    }

    public static void LOGE(final String tag, String message) {
        Log.e(tag, message);
    }

    public static void LOGE(final String tag, String message, Object... args) {
        Log.e(tag, MessageFormat.format(message, args));
    }

    public static void LOGE(final String tag, String message, Throwable cause, Object... args) {
        Log.e(tag, MessageFormat.format(message, args), cause);
    }

    public static void LOGE(final String tag, String message, Throwable cause) {
        Log.e(tag, message, cause);
    }

}
