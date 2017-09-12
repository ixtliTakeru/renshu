package g.takeru.renshu.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;

import timber.log.Timber;

/**
 * Created by takeru on 16/6/22.
 */
public class SmsReceiver extends BroadcastReceiver {

    /**
     * You can listen sms to do something
     * Get the permission before you start this receiver.
     */

    public static final String ACTION_SMS_RECEIVED = "Telephony.Sms.Intents.SMS_RECEIVED_ACTION";

    private SmsReceiveListener smsReceiveListener;

    public interface SmsReceiveListener {
        void OnMessageReceived(String code);
    }

    public void setOnReceivedMessageListener(SmsReceiveListener listener) {
        this.smsReceiveListener = listener;
    }

    public SmsReceiver() {
        super();
        Timber.d("SmsReceiver");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.d("onReceive");
        if (intent.getAction().equals(ACTION_SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) intent.getExtras().get("pdus");
                for (Object pdu : pdus) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    String content = smsMessage.getMessageBody();
                    Timber.d("content: " + content);

                    smsReceiveListener.OnMessageReceived(content);
                }
            }
        }
    }
}