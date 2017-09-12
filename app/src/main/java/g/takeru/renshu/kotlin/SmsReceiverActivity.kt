package g.takeru.renshu.kotlin

import android.Manifest
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import g.takeru.renshu.R
import g.takeru.renshu.receiver.SmsReceiver
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


/**
 * Created by takeru on 2017/9/8.
 */
class SmsReceiverActivity : AppCompatActivity(){

    private lateinit var smsReceiver: SmsReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.setText("why")
    }

    override fun onResume() {
        super.onResume()
        // request permission if sdk is M
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            registerSmsReceiver()
        } else {
            checkPermission()
        }
    }

    override fun onPause() {
        super.onPause()
//        unregisterReceiver(smsReceiver)
    }

    //register SmsReceiver
    private fun registerSmsReceiver() {
        Timber.d("registerSmsReceiver")
        setSmsReceiver()
        var intentFilter = IntentFilter(SmsReceiver.ACTION_SMS_RECEIVED)
        registerReceiver(smsReceiver, intentFilter)
    }

    private fun setSmsReceiver() {
        smsReceiver = SmsReceiver()
        smsReceiver.setOnReceivedMessageListener { code ->  text.setText(code)}
    }

    private fun checkPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.RECEIVE_SMS)
                .withListener(permissionListener)
                .check()
    }

    private val permissionListener = object: PermissionListener {
        override fun onPermissionGranted(response: PermissionGrantedResponse) {
            registerSmsReceiver()
        }

        override fun onPermissionDenied(response: PermissionDeniedResponse) {
        }

        override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {/* ... */
        }
    }

}
