package smarthome.defendor.wifiwatchdog.persistance;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface EventLogDao {

    @Query("SELECT * FROM event_logs ORDER BY insert_timestamp DESC, ID DESC LIMIT 1000")
    List<EventLog> getAll();

    @Insert
    void insertAll(EventLog... users);

    @Query("DELETE FROM event_logs")
    void deleteAll();

    @Query("DELETE FROM event_logs WHERE insert_timestamp < :date")
    void deleteOlderThan(Date date);

}
