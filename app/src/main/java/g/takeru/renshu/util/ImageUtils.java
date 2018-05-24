package g.takeru.renshu.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

	public static Bitmap reverseBitmap(Bitmap bitmap){
		Matrix matrix = new Matrix();
		matrix.preScale(-1, 1);
		Bitmap reverseBitmap =  Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
		return reverseBitmap;
	}

	public static Bitmap rotateBitmap(Bitmap bitmap, float angle) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	public static Bitmap getBitmapFromUri(Context context, Uri uri){
		try {
			return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Bitmap getBitmapFromUri(Context context, String uriString){
		try {
			Uri uri = Uri.parse(uriString);
			return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

    public static byte[] getImageByteArrayFromUri(Context context, Uri uri){
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            return getBytes(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

//	/**
//	 * Stores an image on the storage
//	 *
//	 * @param image
//	 *            the image to store.
//	 * @param pictureFile
//	 *            the file in which it must be stored
//	 */
//	public static void storeImage(Bitmap image, File pictureFile) {
//		if (pictureFile == null) {
//			Log.d(TAG, "Error creating media file, check storage permissions: ");
//			return;
//		}
//		try {
//			FileOutputStream fos = new FileOutputStream(pictureFile);
//			image.compress(Bitmap.CompressFormat.PNG, 90, fos);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			Log.d(TAG, "File not found: " + e.getMessage());
//		} catch (IOException e) {
//			Log.d(TAG, "Error accessing file: " + e.getMessage());
//		}
//	}
//
//	/**
//	 * Get the screen height.
//	 *
//	 * @param context
//	 * @return the screen height
//	 */
//	@SuppressWarnings("deprecation")
//	@SuppressLint("NewApi")
//	public static int getScreenHeight(Activity context) {
//
//		Display display = context.getWindowManager().getDefaultDisplay();
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//			Point size = new Point();
//			display.getSize(size);
//			return size.y;
//		}
//		return display.getHeight();
//	}
//
//	/**
//	 * Get the screen width.
//	 *
//	 * @param context
//	 * @return the screen width
//	 */
//	@SuppressWarnings("deprecation")
//	@SuppressLint("NewApi")
//	public static int getScreenWidth(Activity context) {
//
//		Display display = context.getWindowManager().getDefaultDisplay();
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//			Point size = new Point();
//			display.getSize(size);
//			return size.x;
//		}
//		return display.getWidth();
//	}
}
