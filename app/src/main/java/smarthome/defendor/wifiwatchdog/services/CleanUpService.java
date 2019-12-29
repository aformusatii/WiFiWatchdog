package smarthome.defendor.wifiwatchdog.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import smarthome.defendor.wifiwatchdog.persistance.EventLogService;

public class CleanUpService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("WiFiWatchdog", "CleanUpService-Start");

        EventLogService.deleteOlderThan(this.getApplicationContext(), getDeletionDate());

        // stopSelf();
        return START_NOT_STICKY;
    }

    private Date getDeletionDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

}
