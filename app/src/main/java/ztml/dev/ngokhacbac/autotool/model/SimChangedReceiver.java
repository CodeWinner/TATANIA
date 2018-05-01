package ztml.dev.ngokhacbac.autotool.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import ztml.dev.ngokhacbac.autotool.presenter.Mainpresneter;

public class SimChangedReceiver extends BroadcastReceiver {
    public static int NUM_RECEIVE = 0;
    public static final String BUNDEL_DATA = "AUTO_BROADCARD";
    public static final String IS_CARD_SIM = "IS_CARD_SIM";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("XXX", "SimChangedReceiver");
        if (intent.getAction().equals("android.intent.action.SIM_STATE_CHANGED")) {
            try {

                if (isSimSupport(context) == true) {
                    NUM_RECEIVE++;

                    if (NUM_RECEIVE == 3) {
                        Toast.makeText(context, " có sim ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(BUNDEL_DATA);
                        i.putExtra(IS_CARD_SIM, true);
                        context.sendBroadcast(i);
                        NUM_RECEIVE = 0;
                    }
                } else {
                    Intent i = new Intent(BUNDEL_DATA);
                    i.putExtra(IS_CARD_SIM, false);
                    context.sendBroadcast(i);
                    Toast.makeText(context, " không có sim", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {

            }
        }
    }

    public static boolean isSimSupport(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);  //gets the current TelephonyManager
        return !(tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT);

    }
}
