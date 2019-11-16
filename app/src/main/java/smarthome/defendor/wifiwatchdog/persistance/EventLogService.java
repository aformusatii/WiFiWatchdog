package smarthome.defendor.wifiwatchdog.persistance;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class EventLogService {

    public static void saveEventLog(Context context, EventLog eventLog) {
        new SaveTask(context).execute(eventLog);
    }

    public static class SaveTask extends AsyncTask<EventLog, Void, Void> {

        private Context context;

        public SaveTask(Context context) {
            this.context = context;
        }

        protected Void doInBackground(EventLog... eventLogs) {
            AppDatabase.getInstance(context).eventLogDao().insertAll(eventLogs);
            return null;
        }
    }

    public static List<EventLog> getEventLogs(Context context) {
        return AppDatabase.getInstance(context).eventLogDao().getAll();
    }

    public static void deleteAll(Context context) {
        AppDatabase.getInstance(context).eventLogDao().deleteAll();
    }

}
