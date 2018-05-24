package g.takeru.renshu.util;

import android.content.Context;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by takeru on 2018/4/11.
 */

public class DateTimeUtil {

    public static final String FORMAT_T = "yyyy-MM-dd'T'hh:mm:ss";

    public static Date getDate(String dateString, String sourceFormat) {
        // from "2017-06-23T02:35:42Z" to "2017/10/16"
        SimpleDateFormat sdf = new SimpleDateFormat(sourceFormat, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;

        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDate(long timeStamp) {
        timeStamp = timeStamp * 1000; //ms to s
        return new Date(timeStamp);
    }

    // convert date from T format
    // return "MM/dd", "dd", "MMMM, yyyy" or others
    public static String convertDateStringFormat(String dateString, String sourceFormat, String destFormat) {
//        SimpleDateFormat sdfStep1 = new SimpleDateFormat(sourceFormat, Locale.getDefault());
//        sdfStep1.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date date = null;
//        try {
//            date = sdfStep1.parse(dateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        Date date = getDate(dateString, sourceFormat);
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(destFormat);
            return sdf.format(date);
        }
    }

    public static String convertDateFormat(Date date, String destFormat) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(destFormat);
            return sdf.format(date);
        }
    }

    // return date style is medium and time style is short
    public static String getDateTimeString(Context context, Date date){
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String dateString = dateFormat.getDateTimeInstance(java.text.DateFormat.MEDIUM, java.text.DateFormat.SHORT).format(date);
        return dateString;
    }

    // return date style is medium
    public static String getDateStringByMediumStyle(Context context, Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String dateString = dateFormat.getDateInstance(java.text.DateFormat.MEDIUM).format(date);
        return dateString;
    }

    // compare two date of T format, return latest one
    public static String compareTFormatDate(String date1, String date2) {
        if (TextUtils.isEmpty(date1) && TextUtils.isEmpty(date2))
            return "";

        if (TextUtils.isEmpty(date1)) return date2;
        if (TextUtils.isEmpty(date2)) return date1;

        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_T);
        try {
            if (sdf.parse(date1).before(sdf.parse(date2))){
                return date2;
            } else {
                return date1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
