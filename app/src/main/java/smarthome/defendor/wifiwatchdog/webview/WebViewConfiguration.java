package smarthome.defendor.wifiwatchdog.webview;

import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import smarthome.defendor.wifiwatchdog.R;

public class WebViewConfiguration {

    private AppCompatActivity activity;

    public WebViewConfiguration(AppCompatActivity activity) {
        this.activity = activity;
    }

    public WebView setupWebView() {
        final WebView wv = (WebView) activity.findViewById(R.id.MainWebView);
        // wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setUseWideViewPort(true);
        // wv.setInitialScale(1);

        wv.clearCache(true);
        wv.clearHistory();

        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.addJavascriptInterface(new WebViewJsObject(activity.getApplicationContext()), "AndroidObj");

        wv.loadUrl("file:///android_asset/index.html");

        wv.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int vis) {
                wv.loadUrl("javascript:{};");
            }
        });

        return wv;
    }

}
