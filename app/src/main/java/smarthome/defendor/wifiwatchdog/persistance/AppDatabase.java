package smarthome.defendor.wifiwatchdog.persistance;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {EventLog.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase APP_DATABASE;

    public abstract EventLogDao eventLogDao();

    public synchronized static AppDatabase getInstance(@NonNull Context context) {
        if (APP_DATABASE == null) {
            APP_DATABASE = Room.databaseBuilder(context, AppDatabase.class, "DEFAULT_DB").build();
        }
        return APP_DATABASE;
    }

}
