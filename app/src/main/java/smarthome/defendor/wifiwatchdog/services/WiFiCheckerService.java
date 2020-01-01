package smarthome.defendor.wifiwatchdog.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import smarthome.defendor.wifiwatchdog.connection.ConnectionTask;
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

        ConnectionTask task = new ConnectionTask(this.getApplicationContext());
        task.execute();

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
