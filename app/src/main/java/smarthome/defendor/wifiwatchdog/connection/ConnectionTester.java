package smarthome.defendor.wifiwatchdog.connection;

import android.content.Context;

import org.apache.commons.io.IOUtils;

import java.net.HttpURLConnection;
import java.net.URL;

import smarthome.defendor.wifiwatchdog.utils.PreferencesHelper;

public class ConnectionTester {

    private PreferencesHelper preferencesHelper;

    public ConnectionTester(Context context) {
        this.preferencesHelper = new PreferencesHelper(context);
    }

    public ConnectionResult connectionStatus() {
        ConnectionResult result = new ConnectionResult();

        HttpURLConnection urlConnection = null;

        long start = System.currentTimeMillis();

        try {
            URL url = new URL(preferencesHelper.getUrl());

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(preferencesHelper.getConnectionTimeout());
            urlConnection.setReadTimeout(preferencesHelper.getReadTimeout());

            urlConnection.connect();

            long connectionElapsed = System.currentTimeMillis() - start;
            start = System.currentTimeMillis();

            byte[] data = IOUtils.toByteArray(urlConnection.getInputStream());

            long downloadElapsed = System.currentTimeMillis() - start;

            //Log.i("ConnectionTester", "Connection time: " + connectionElapsed);
            //Log.i("ConnectionTester", "Download time: " + downloadElapsed);
            //Log.i("ConnectionTester", "Bytes: " + data.length);

            result.setConnectionTime(connectionElapsed);
            result.setDownloadTime(downloadElapsed);
            result.setPayloadSize(data.length);
            result.setOk(true);

        } catch (Exception e) {
            result.setException(e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        if (result.getDownloadTime() > preferencesHelper.getDownloadTimeout()) {
            result.setOk(false);
        }

        return result;
    }

}
