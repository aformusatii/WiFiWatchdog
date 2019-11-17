package smarthome.defendor.wifiwatchdog.connection;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.PowerManager;

public class PowerManagerHelper {

    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;

    public PowerManagerHelper(ContextWrapper context) {
        this.powerManager = ((PowerManager) context.getSystemService(Context.POWER_SERVICE));
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "WiFiWatchdog:TAG");
    }

    public void wake() {
        try {
            wakeLock.acquire();
        } finally {
            wakeLock.release();
        }
    }

}
