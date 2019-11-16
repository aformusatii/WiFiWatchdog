package smarthome.defendor.wifiwatchdog.webview.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    public static final String KEY_URL = "URL";
    public static final String KEY_CHECK_INTERVAL = "CHECK_INTERVAL";
    public static final String KEY_DOWNLOAD_TIMEOUT = "DOWNLOAD_TIMEOUT";
    public static final String KEY_CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT";
    public static final String KEY_READ_TIMEOUT = "READ_TIMEOUT";
    // public static final String DEFAULT_TEST_PAGE = "http://ipv4.download.thinkbroadband.com/5MB.zip";
    public static final String DEFAULT_TEST_PAGE = "http://htmlsketcher.com/connection-test.html";

    private SharedPreferences preferences;

    public PreferencesHelper(Context context) {
        this.preferences = context.getSharedPreferences("DEFAULT_", Context.MODE_PRIVATE);
    }

    public String getUrl() {
        return preferences.getString(KEY_URL, DEFAULT_TEST_PAGE);
    }

    public void setUrl(String url) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(KEY_URL, url);
        edit.commit();
    }

    public int getCheckInterval() {
        return preferences.getInt(KEY_CHECK_INTERVAL, 30);
    }

    public void setCheckInterval(int value) {
        putInt(KEY_CHECK_INTERVAL, value);
    }

    public int getDownloadTimeout() {
        return preferences.getInt(KEY_DOWNLOAD_TIMEOUT, 10000);
    }

    public void setDownloadTimeout(int value) {
        putInt(KEY_DOWNLOAD_TIMEOUT, value);
    }

    public int getConnectionTimeout() {
        return preferences.getInt(KEY_CONNECTION_TIMEOUT, 5000);
    }

    public void setReadTimeout(int value) {
        putInt(KEY_CONNECTION_TIMEOUT, value);
    }

    public int getReadTimeout() {
        return preferences.getInt(KEY_READ_TIMEOUT, 5000);
    }

    public void setConnectionTimeout(int value) {
        putInt(KEY_READ_TIMEOUT, value);
    }

    private void putInt(String key, int value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

}
