package ztml.dev.ngokhacbac.autotool.model;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.net.PortUnreachableException;

public class XXXX extends AccessibilityService {
    private static final String TODO = "TODO";
    public static String TAG = "XXX";
    public static final String PHONE_NUMBER = "message";
    public static final String BUDLE_PHONE_NUMBER = "ztml.dev.ngokhacbac.autotool.model";

    public static final String OPERATOR_NAME_SIM_VIETNAMMOBILE = "Vietnamobile";
    public static final String OPERATOR_NAME_SIM_VINAPHONE = "VINAPHONE";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent");
        String text = event.getText().toString();

        if (event.getClassName().equals("android.app.AlertDialog")) {
            performGlobalAction(GLOBAL_ACTION_BACK);
            Log.i(TAG, "SDT " + text);
            Log.i(TAG, "SIM INFOR : " + getNameSim(getApplicationContext()));
            //   Toast.makeText(getApplicationContext(), "TEXT : " + text, Toast.LENGTH_SHORT);
            final Intent intent = new Intent(BUDLE_PHONE_NUMBER);
            if(getNameSim(getApplicationContext()).contains(OPERATOR_NAME_SIM_VIETNAMMOBILE))
            {
                String SDT = (String) event.getText().get(0);
                intent.putExtra(PHONE_NUMBER, SDT);
            }
            if(getNameSim(getApplicationContext()).contains(OPERATOR_NAME_SIM_VINAPHONE)){
                intent.putExtra(PHONE_NUMBER, text.substring(15, 25));
            }
            //     intent.putExtra(PHONE_NUMBER, text.substring(15, 25));
            sendBroadcast(intent);
        }
    }

    private String getNameSim(Context context) {
        TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        String mPhoneNumber = tMgr.getSimOperatorName();
        return mPhoneNumber;
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.packageNames = new String[]{"com.android.phone"};
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;

        setServiceInfo(info);
    }
}
