package ztml.dev.ngokhacbac.autotool.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    public static final String BUNDEL_DATA = "AUTO_BROADCARD";
    public static final String SMS = "SMS";
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    String mobile, body;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            // Retrieves a map of extended data from the intent.
            final Bundle bundle = intent.getExtras();
            try {
                if (bundle != null) {
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                        String senderNum = phoneNumber;
                        String message = currentMessage.getDisplayMessageBody();
                        mobile = senderNum.replaceAll("\\s", "");
                        body = message.replaceAll("\\s", "+");
                        Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + body);
                        // Show Alert
                        int duration = Toast.LENGTH_LONG;
                        Intent intent1 = new Intent(BUNDEL_DATA);
                        intent1.putExtra(SMS, message);
                        Toast toast = Toast.makeText(context,
                                "senderNum: " + mobile + ", message: " + message, duration);
                        toast.show();
                        context.sendBroadcast(intent1);
                    } // end for loop
                } // bundle is null

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" + e);

            }
        }
    }
}
