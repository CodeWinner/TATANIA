package ztml.dev.ngokhacbac.autotool.model;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class RecService extends Service {
    private MediaRecorder recorder;
    private File file;
    private String path = "sdcard/alarms";

    public RecService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS);
        Date date = new Date();

        return START_STICKY;
    }
}
