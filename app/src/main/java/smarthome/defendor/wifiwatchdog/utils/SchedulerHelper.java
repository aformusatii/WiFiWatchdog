package smarthome.defendor.wifiwatchdog.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import smarthome.defendor.wifiwatchdog.services.CleanUpService;

public class SchedulerHelper {

    public static void scheduleCleanUpServiceExecution(Context context) {
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getService(context,
                0,
                new Intent(context, CleanUpService.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarm.setInexactRepeating(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis(),
                (24 * 60 *  60 * 1000), // once a day
                pendingIntent
        );
    }

}
