package smarthome.defendor.wifiwatchdog.persistance;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface EventLogDao {

    @Query("SELECT * FROM event_logs ORDER BY insert_timestamp DESC LIMIT 1000")
    List<EventLog> getAll();

    @Insert
    void insertAll(EventLog... users);

    @Query("DELETE FROM event_logs")
    void deleteAll();

}
