package smarthome.defendor.wifiwatchdog.persistance;

import android.content.Context;

import java.util.Date;
import java.util.List;

import smarthome.defendor.wifiwatchdog.utils.ContextTask;

public class EventLogService {

    public static void saveEventLog(Context context, EventLog eventLog) {
        new ContextTask<EventLog>(context).execute(new ContextTask.TheTask<EventLog>() {
                @Override
                public void execute(Context context, EventLog... args) {
                    AppDatabase.getInstance(context).eventLogDao().insertAll(args);
                }

            }, eventLog);

    }

    public static List<EventLog> getEventLogs(Context context) {
        return AppDatabase.getInstance(context).eventLogDao().getAll();
    }

    public static void deleteAll(Context context) {
        AppDatabase.getInstance(context).eventLogDao().deleteAll();
    }

    public static void deleteOlderThan(Context context, Date date) {
        new ContextTask<Date>(context).execute(new ContextTask.TheTask<Date>() {
            @Override
            public void execute(Context context, Date... args) {
                AppDatabase.getInstance(context).eventLogDao().deleteOlderThan(args[0]);
            }

        }, date);
    }

}
