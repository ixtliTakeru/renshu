package g.takeru.renshu.util;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 * Created by takeru on 2017/2/15.
 */

public class JsonUtil {

    // loading json file from raw folder, return target class
    // resId ex: R.raw.json
    public static <T> T loadFromRaw(Context context, int resId, Class<T> clz){
        String jsonString = loadFile(context, resId);
//        Timber.d("JsonString:\n" + jsonString);

        if (TextUtils.isEmpty(jsonString)) {
            return null;
        } else {
            return new Gson().fromJson(jsonString, clz);
        }
    }

    public static <T> List<T> LoadListFromRaw(Context context, int resId, Class<T> clz) {
        String jsonString = loadFile(context, resId);
//        Timber.d("JsonString:\n" + jsonString);

        if (TextUtils.isEmpty(jsonString)) {
            return null;
        } else {
            return new Gson().fromJson(jsonString, new TypeToken<List<T>>() {}.getType());
        }
    }

    private static String loadFile(Context context, int resId) {
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

            return writer.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
