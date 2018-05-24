package g.takeru.renshu.util;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import timber.log.Timber;

/**
 * Created by takeru on 2017/2/15.
 */

public class JsonUtil {

    // loading json file from raw folder, return target class
    // resId ex: R.raw.json
    public static <T> T loadFileFromRaw(Context context, int resId, Class<T> clz){
        String jsonString;
        try {
            InputStream is = context.getResources().openRawResource(resId);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }

            jsonString = writer.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Timber.d("JsonString:\n" + jsonString);
        return new Gson().fromJson(jsonString, clz);
    }
}
