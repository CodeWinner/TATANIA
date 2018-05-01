package ztml.dev.ngokhacbac.autotool.model;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class ServiceListerServer extends Service implements RequestResponeListenner {
    public int numSecon = 0;
    private static String API = "http://run.blogtinhoc.vn/code/api.php?action=getStatus&phoneNumber=";
    private Num10S num10S;

    public ServiceListerServer() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RequestResponeApi.getInstance(this).setOnRequestResponeListenner(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        num10S = new Num10S(this);
        num10S.start();
        return START_STICKY;
    }

    @Override
    public void RequestSuccess(String response) {
        Log.i("SERVICE111", "Sv : " + response);
    }

    @Override
    public void RequestError() {
        Log.i("SERVICE111", "Sv : faill");
    }

    class Num10S extends Thread {
        Context context;

        public Num10S(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    numSecon++;
                    Log.i("SERVICE111", numSecon + "s");
                    if (numSecon == 10) {
                        numSecon = 0;
                        if (Access.PHONE_NUMBER != "") {
                            RequestResponeApi.getInstance(context).sendRequest(API + Access.PHONE_NUMBER);
                            Log.i("SERVICE111", numSecon + "s" + " Đã gửi ");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
