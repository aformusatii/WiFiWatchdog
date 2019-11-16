package smarthome.defendor.wifiwatchdog.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;

import java.util.List;

import smarthome.defendor.wifiwatchdog.persistance.EventLog;
import smarthome.defendor.wifiwatchdog.persistance.EventLogService;
import smarthome.defendor.wifiwatchdog.webview.util.PreferencesHelper;

public class WebViewJsObject {

    private Context context;
    private PreferencesHelper preferencesHelper;

    public WebViewJsObject(Context context) {
        this.context = context;
        this.preferencesHelper = new PreferencesHelper(context);
    }

    @JavascriptInterface
    public String getEventLogs() {
        List<EventLog> logs = EventLogService.getEventLogs(context);
        return new Gson().toJson(logs);
    }

    @JavascriptInterface
    public void deleteAll() {
        EventLogService.deleteAll(context);
    }

    @JavascriptInterface
    public String getSettingsUrl() {
        return preferencesHelper.getUrl();
    }

    @JavascriptInterface
    public void setSettingsUrl(String value) {
        preferencesHelper.setUrl(value);
    }

    @JavascriptInterface
    public int getSettingsCheckInterval() {
        return preferencesHelper.getCheckInterval();
    }

    @JavascriptInterface
    public void setSettingsCheckInterval(int value) {
        preferencesHelper.setCheckInterval(value);
    }

    @JavascriptInterface
    public int getSettingsDownloadTimeout() {
        return preferencesHelper.getDownloadTimeout();
    }

    @JavascriptInterface
    public void setSettingsDownloadTimeout(int value) {
        preferencesHelper.setDownloadTimeout(value);
    }

    @JavascriptInterface
    public int getSettingsConnectionTimeout() {
        return preferencesHelper.getConnectionTimeout();
    }

    @JavascriptInterface
    public void setSettingsConnectionTimeout(int value) {
        preferencesHelper.setConnectionTimeout(value);
    }

    @JavascriptInterface
    public int getSettingsReadTimeout() {
        return preferencesHelper.getReadTimeout();
    }

    @JavascriptInterface
    public void setSettingsReadTimeout(int value) {
        preferencesHelper.setReadTimeout(value);
    }

}
