package smarthome.defendor.wifiwatchdog.connection;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;

import androidx.annotation.Nullable;
import smarthome.defendor.wifiwatchdog.persistance.EventLog;
import smarthome.defendor.wifiwatchdog.persistance.EventLogService;
import smarthome.defendor.wifiwatchdog.webview.util.PreferencesHelper;

public class WiFiCheckerService extends Service {

    private PreferencesHelper preferencesHelper;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.preferencesHelper = new PreferencesHelper(this.getApplicationContext());

        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        ConnectionTester tester = new ConnectionTester(this.getApplicationContext());
        ConnectionResult connectionResult = tester.connectionStatus();

        if (connectionResult.getDownloadTime() > preferencesHelper.getDownloadTimeout()) {
            connectionResult.setOk(false);
        }

        if (!connectionResult.isOk()) {
            // toggle wifi
            wifiManager.setWifiEnabled(false);
            wifiManager.setWifiEnabled(true);
        }

        EventLog eventLog = new EventLog(connectionResult.toString());
        EventLogService.saveEventLog(this.getApplicationContext(), eventLog);

        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (preferencesHelper.getCheckInterval() * 1000),
                PendingIntent.getService(this, 0, new Intent(this, WiFiCheckerService.class), 0)
        );
    }

}
