package smarthome.defendor.wifiwatchdog.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import smarthome.defendor.wifiwatchdog.services.CleanUpService;
import smarthome.defendor.wifiwatchdog.services.WiFiCheckerService;
import smarthome.defendor.wifiwatchdog.utils.SchedulerHelper;

public class BootAutostart extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            startService(context, new Intent(context, WiFiCheckerService.class));
            SchedulerHelper.scheduleCleanUpServiceExecution(context);

            Log.i("WiFiWatchdog", "Autostart-Started");
        }

    }

    private void startService(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

}