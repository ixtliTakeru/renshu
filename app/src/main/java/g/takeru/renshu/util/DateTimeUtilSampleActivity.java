package g.takeru.renshu.util;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import g.takeru.renshu.R;
import timber.log.Timber;

import static g.takeru.renshu.util.DateTimeUtil.FORMAT_T;

/**
 * Created by takeru on 2018/4/12.
 */

public class DateTimeUtilSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // time stamp (long)
        long timeStamp = (long) 1523428331;
        Date date1 = DateTimeUtil.getDate(timeStamp);

        // dateStyle: java.text.DateFormat.MEDIUM, TimeStyle:java.text.DateFormat.SHORT
        // return Apr 11, 2018 14:32
        Timber.d(DateTimeUtil.getDateTimeString(this, date1));
        // dateStyle: MM/dd
        // return 04/11
        Timber.d(DateTimeUtil.convertDateFormat(date1, "MM/dd"));
        // dateStyle: MMMM, yyyy
        // return April, 2018
        Timber.d(DateTimeUtil.convertDateFormat(date1, "MMMM, yyyy"));
        // dateStyle: yyyy
        // return 2018
        Timber.d(DateTimeUtil.convertDateFormat(date1, "yyyy"));



        // T format
        String dateString1 = "2017-06-23T02:35:42Z";
        Date date2 = DateTimeUtil.getDate(dateString1, FORMAT_T);

        // dateStyle: java.text.DateFormat.MEDIUM, TimeStyle:java.text.DateFormat.SHORT
        // return Jun 23, 2017 10:35
        Timber.d(DateTimeUtil.getDateTimeString(this, date2));
        // dateStyle: java.text.DateFormat.MEDIUM
        // return Jun 23, 2017
        Timber.d(DateTimeUtil.getDateStringByMediumStyle(this, date2));
        // dateStyle: yyyy/MM/dd
        // return 2018/06/23
        Timber.d(DateTimeUtil.convertDateStringFormat(dateString1, FORMAT_T, "yyyy/MM/dd"));



        // compare "2017-06-23T02:35:42Z" and "2017-09-26T03:08:18Z"
        // return "2017-09-26T03:08:18Z"
        String dateString2 = "2017-09-26T03:08:18Z";
        Timber.d(DateTimeUtil.compareTFormatDate(dateString1, dateString2));
    }
}
