package g.takeru.renshu.util;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import g.takeru.renshu.R;
import g.takeru.renshu.RenShuApplication;
import timber.log.Timber;

/**
 * Created by takeru on 2018/4/11.
 */

public class FileUtil {

    public static final String IMAGE_DIRECTORY_NAME =
            RenShuApplication.getInstance().getResources().getString(R.string.app_name);

    public static File getOutputMediaFile(){
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Timber.d("Oops! Failed create %s directory", IMAGE_DIRECTORY_NAME);
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile =  new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

        return  mediaFile;
    }

    public static Uri getUriFromFile(Context context, File file){
        return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
    }

    public static String getUriStringFromFile(Context context, File file){
        return getUriFromFile(context, file).toString();
    }
}
