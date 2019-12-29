package smarthome.defendor.wifiwatchdog.connection;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import smarthome.defendor.wifiwatchdog.utils.PreferencesHelper;

public class ConnectionTester {

    private Context context;
    private PreferencesHelper preferencesHelper;

    public ConnectionTester(Context context) {
        this.context = context;
        this.preferencesHelper = new PreferencesHelper(context);
    }

    public ConnectionResult connectionStatus() {

        ConnectionResult result = new ConnectionResult();

        try {
            return new ConnectionTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            result.setException(e);
        }

        return result;
    }

    public class ConnectionTask extends AsyncTask<String, Void, ConnectionResult> {

        private Exception exception;

        protected ConnectionResult doInBackground(String... urls) {

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

            } catch (IOException e) {
                result.setException(e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return result;
        }

        protected void onPostExecute(Void feed) {
        }
    }

}
