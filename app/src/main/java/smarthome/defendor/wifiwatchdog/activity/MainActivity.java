package smarthome.defendor.wifiwatchdog.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import smarthome.defendor.wifiwatchdog.R;
import smarthome.defendor.wifiwatchdog.services.WiFiCheckerService;
import smarthome.defendor.wifiwatchdog.utils.SchedulerHelper;
import smarthome.defendor.wifiwatchdog.webview.WebViewConfiguration;

// https://ncona.com/2014/04/schedule-your-android-app-to-do-something-periodically/
// https://android.jlelse.eu/schedule-tasks-and-jobs-intelligently-in-android-e0b0d9201777

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebViewConfiguration webViewConfig = new WebViewConfiguration(this);
        webViewConfig.setupWebView();

        startWiFiCheckerService();

        SchedulerHelper.scheduleCleanUpServiceExecution(this.getApplicationContext());
    }

    private void startWiFiCheckerService() {
        startService(new Intent(this, WiFiCheckerService.class));
    }
}
