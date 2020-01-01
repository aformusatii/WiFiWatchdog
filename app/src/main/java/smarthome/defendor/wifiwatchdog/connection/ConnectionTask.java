package smarthome.defendor.wifiwatchdog.connection;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

import smarthome.defendor.wifiwatchdog.persistance.EventLog;
import smarthome.defendor.wifiwatchdog.persistance.EventLogService;
import smarthome.defendor.wifiwatchdog.utils.PowerManagerHelper;

public class ConnectionTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    public ConnectionTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... none) {
        Log.i("ConnectionTask", "Start check connectivity");

        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            ConnectionTester conTester = new ConnectionTester(context);
            ConnectionResult connectionResult = conTester.connectionStatus();

            EventLog eventLog = new EventLog(connectionResult.toString());
            EventLogService.saveEventLog(context, eventLog);

            if (!connectionResult.isOk()) {
                EventLogService.saveEventLog(context, new EventLog(String.format("Wifi enabled: %s", wifiManager.isWifiEnabled())));

                // toggle wifi
                boolean result = wifiManager.setWifiEnabled(false);
                EventLogService.saveEventLog(context, new EventLog(String.format("Turn off Wifi: %s", result)));

                result = wifiManager.setWifiEnabled(true);
                EventLogService.saveEventLog(context, new EventLog(String.format("Turn on Wifi: %s", result)));

                result = wifiManager.reassociate();
                EventLogService.saveEventLog(context, new EventLog(String.format("Reconnect to Wifi: %s", result)));

                PowerManagerHelper powerManagerHelper = new PowerManagerHelper(context);
                powerManagerHelper.wake();
            }
        } catch (Exception e) {
            e.printStackTrace();
            EventLogService.saveEventLog(context, new EventLog("Unknown exception during con check: [%s]", e));
        }

        return null;
    }
}