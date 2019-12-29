package smarthome.defendor.wifiwatchdog.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;

import androidx.annotation.Nullable;
import smarthome.defendor.wifiwatchdog.connection.ConnectionResult;
import smarthome.defendor.wifiwatchdog.connection.ConnectionTester;
import smarthome.defendor.wifiwatchdog.connection.PowerManagerHelper;
import smarthome.defendor.wifiwatchdog.persistance.EventLog;
import smarthome.defendor.wifiwatchdog.persistance.EventLogService;
import smarthome.defendor.wifiwatchdog.utils.PreferencesHelper;

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

        EventLog eventLog = new EventLog(connectionResult.toString());
        EventLogService.saveEventLog(this.getApplicationContext(), eventLog);

        if (!connectionResult.isOk()) {
            EventLogService.saveEventLog(this.getApplicationContext(), new EventLog(String.format("Wifi enabled: %s", wifiManager.isWifiEnabled())));

            // toggle wifi
            boolean result = wifiManager.setWifiEnabled(false);
            EventLogService.saveEventLog(this.getApplicationContext(), new EventLog(String.format("Turn off Wifi: %s", result)));

            result = wifiManager.setWifiEnabled(true);
            EventLogService.saveEventLog(this.getApplicationContext(), new EventLog(String.format("Turn on Wifi: %s", result)));

            result = wifiManager.reassociate();
            EventLogService.saveEventLog(this.getApplicationContext(), new EventLog(String.format("Reconnect to Wifi: %s", result)));

            PowerManagerHelper powerManagerHelper = new PowerManagerHelper(this);
            powerManagerHelper.wake();
        }

        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getService(this,
                0,
                new Intent(this, WiFiCheckerService.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarm.cancel(pendingIntent);

        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (preferencesHelper.getCheckInterval() * 1000),
                pendingIntent
        );
    }

}
