package smarthome.defendor.wifiwatchdog.persistance;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_logs")
public class EventLog {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int uid;

    @ColumnInfo(name = "insert_timestamp")
    public Date time = new Date(System.currentTimeMillis());

    @ColumnInfo(name = "log")
    public String log;

    public EventLog(String log) {
        this.log = log;
    }

    public EventLog(String msg, Throwable exception) {
        this.log = String.format(msg, ExceptionUtils.getStackTrace(exception));
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
