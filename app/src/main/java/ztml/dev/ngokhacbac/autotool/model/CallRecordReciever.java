package ztml.dev.ngokhacbac.autotool.model;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Time;



public class CallRecordReciever extends BroadcastReceiver {

    private MediaRecorder recorder;
    Time today;
    TelephonyManager telManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        try {
            Toast.makeText(context, " Receiver start ", Toast.LENGTH_SHORT).show();
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//            Intent answer = new Intent(Intent.ACTION_MEDIA_BUTTON);
//            answer.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
//            context.sendOrderedBroadcast(answer, null);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(context, "Số đang gọi tới :  " + incomingNumber, Toast.LENGTH_SHORT).show();
                // acceptCall(context);

            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
                Toast.makeText(context, "Chấp nhận", Toast.LENGTH_SHORT).show();
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context, "Kết thúc", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void acceptCall(final Context context) {
        Intent buttonDown = new Intent(Intent.ACTION_MEDIA_BUTTON);
        buttonDown.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK));

        Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);
        buttonDown.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));


        context.sendOrderedBroadcast(buttonDown, "android.permission.CALL_PRIVILEGED");
        context.sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");

    }

    private void rejectCall(Context context) {

        Intent buttonDown = new Intent(Intent.ACTION_MEDIA_BUTTON);
        buttonDown.putExtra(Intent.EXTRA_KEY_EVENT,
                new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK));
        context.sendOrderedBroadcast(buttonDown, "android.permission.CALL_PRIVILEGED");
    }

}
