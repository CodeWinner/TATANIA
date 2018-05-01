package ztml.dev.ngokhacbac.autotool.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import ztml.dev.ngokhacbac.autotool.MainActivity;
import ztml.dev.ngokhacbac.autotool.model.Access;
import ztml.dev.ngokhacbac.autotool.model.RequestResponeApi;
import ztml.dev.ngokhacbac.autotool.model.RequestResponeListenner;
import ztml.dev.ngokhacbac.autotool.model.ServiceListerServer;
import ztml.dev.ngokhacbac.autotool.model.SimChangedReceiver;
import ztml.dev.ngokhacbac.autotool.model.SmsReceiver;
import ztml.dev.ngokhacbac.autotool.model.XXXX;

import static android.content.Context.TELEPHONY_SERVICE;

public class Mainpresneter implements RequestResponeListenner {
    private XXXX xxxx;
    /**
     * Lần đầu tiên khở chạy app cần nhấn nút start
     * FIRST_START = false : chưa chạy lần nào
     * FIRST_START = true : đã chạy
     */
    private static boolean FIRST_START = false;
    private boolean IS_CARR_SIM = true;

    private static final String CODE_USSD_VINAPHONE = "110";
    private static final String CODE_USSD_VIETNAMMOBILE = "123";
    private static final String CODE_USSD_MOBI = "0";
    private static String API_UPDATE_PHONE_NUMBER = "http://run.blogtinhoc.vn/code/api.php?action=newPhone&phoneNumber=";
    private static String API_UPDATE_SMS = "http://run.blogtinhoc.vn/code/api.php?action=newSMS&phoneNumber=0123456789&from=8180&content=";
    private Context context;

    public Mainpresneter(Context context) {
        xxxx = new XXXX();
        this.context = context;
        RequestResponeApi.getInstance(context).setOnRequestResponeListenner(this);
    }

    public void registerThaoLap(Context context) {
        context.registerReceiver(broadcastReceiver, new IntentFilter(SimChangedReceiver.BUNDEL_DATA));
    }

    public void registerUSSD(Context context) {
        context.registerReceiver(broadcastReceiverUSSD, new IntentFilter(XXXX.BUDLE_PHONE_NUMBER));
    }

    public void registerSMS(Context context) {
        context.registerReceiver(broadcastSMS, new IntentFilter(SmsReceiver.BUNDEL_DATA));
    }

    public String apiUpdateSms(String phoneNumber, String smsContent) {
        return "http://run.blogtinhoc.vn/code/api.php?action=newSMS&phoneNumber=" + phoneNumber + "&from=8180&content=" + smsContent;
    }

    public static String base64Encode(String token) {
        byte[] data = new byte[0];
        try {
            data = token.getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            boolean isSim = bundle.getBoolean(SimChangedReceiver.IS_CARD_SIM);
            if (isSim == true) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
                String nameSim = tm.getSimOperatorName();
                if (FIRST_START == false) {
                    IS_CARR_SIM = isSim;
                } else {
                    if (nameSim.contains(XXXX.OPERATOR_NAME_SIM_VIETNAMMOBILE)) {
                        callUSSD(context, CODE_USSD_VIETNAMMOBILE);
                    }
                    if (nameSim.contains(XXXX.OPERATOR_NAME_SIM_VINAPHONE)) {
                        callUSSD(context, CODE_USSD_VINAPHONE);
                    }
                }

                Log.i("YYY", "IS_CARD_SIM_MAIL " + isSim + "   nameSim : " + nameSim);
            }

        }
    };
    BroadcastReceiver broadcastReceiverUSSD = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String phonenumber = bundle.getString(XXXX.PHONE_NUMBER);
            Access.PHONE_NUMBER = phonenumber;
            //    Log.i("YYY", "IS_CARD_SIM_MAIL   " + phonenumber);
            RequestResponeApi.getInstance(context).sendRequest(API_UPDATE_PHONE_NUMBER + phonenumber);
            Toast.makeText(context, phonenumber, Toast.LENGTH_SHORT).show();

        }
    };
    BroadcastReceiver broadcastSMS = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String sms = bundle.getString(SmsReceiver.SMS);
            Log.i("YYY", "sms   " + sms);
            String code = sms.substring(0, 8);

            Log.i("YYY", "code:  " + code + "   base : " + base64Encode(code));
            Log.i("YYY", "api   " + apiUpdateSms(Access.PHONE_NUMBER, base64Encode(code)));
            RequestResponeApi.getInstance(context).sendRequest(apiUpdateSms(Access.PHONE_NUMBER, base64Encode(code)));
        }
    };

    public void setFirstStart(Context context, boolean firstStart) {
        FIRST_START = firstStart;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        String nameSim = tm.getSimOperatorName();
        if (nameSim.contains(XXXX.OPERATOR_NAME_SIM_VIETNAMMOBILE)) {
            callUSSD(context, CODE_USSD_VIETNAMMOBILE);
        }
        if (nameSim.contains(XXXX.OPERATOR_NAME_SIM_VINAPHONE)) {
            callUSSD(context, CODE_USSD_VINAPHONE);
        }
    }

    public void callUSSD(Context context, String code) {
        dailNumber(context, code);
    }

    // Get vadafone balance(*111*2for vadafone)
    private void dailNumber(Context context, String code) {
        String ussdCode = "*" + code + Uri.encode("#");
        Log.i("XXXX", "ussdcode : " + ussdCode);
        context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode)));
    }

    @Override
    public void RequestSuccess(String response) {
        Log.i("YYY", "RESPONE :   " + response);
    }

    @Override
    public void RequestError() {
        Log.i("YYY", "RESPONE :  error ");
    }
}
