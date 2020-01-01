package smarthome.defendor.wifiwatchdog.connection;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ConnectionResult {

    private boolean ok = false;
    private long connectionTime;
    private long downloadTime;
    private long payloadSize;
    private Exception exception;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public long getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(long connectionTime) {
        this.connectionTime = connectionTime;
    }

    public long getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(long downloadTime) {
        this.downloadTime = downloadTime;
    }

    public long getPayloadSize() {
        return payloadSize;
    }

    public void setPayloadSize(long payloadSize) {
        this.payloadSize = payloadSize;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        String exMsg = (exception == null) ? null : ExceptionUtils.getStackTrace(exception);
        return String.format("status: [%s], conn: [%d], down: [%d], size: [%d], ex: [%s]", ok, connectionTime, downloadTime, payloadSize, exMsg);
    }
}
