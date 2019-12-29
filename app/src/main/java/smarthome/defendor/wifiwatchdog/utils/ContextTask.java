package smarthome.defendor.wifiwatchdog.utils;

import android.content.Context;
import android.os.AsyncTask;

public class ContextTask<T> extends AsyncTask<T, Void, Void> {

    private Context context;
    private ContextTask.TheTask task;
    private T[] args;

    public ContextTask(Context context) {
        this.context = context;
    }

    protected Void doInBackground(T... args) {
        task.execute(context, args);
        return null;
    }

    public void execute(ContextTask.TheTask task, T... args) {
        this.task = task;
        this.args = args;
        super.execute(args);
    }

    public interface TheTask<T> {
        void execute(Context context, T... args);
    }

}