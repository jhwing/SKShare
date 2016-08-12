package stark.skshare.utlis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by jihongwen on 16/8/10.
 */

public class SKShareUtil {

    private static final String TAG = SKShareUtil.class.getSimpleName();

    public static Bitmap getCompressBitmap(Bitmap bitmap, float maxSize, boolean isthum) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float rate = (float) width / (float) height;
        if (isthum) {
            width = 250;
            height = (int) (width / rate);
        }

        int step = 10;
        int newWidth = width;
        int newHeight = height;

        bitmap = ImageUtil.getScaledBitmap(bitmap, newWidth, newHeight);
        while (bitmap2Bytes(bitmap).length > maxSize) {
            if (newWidth < 20) {
                break;
            }
            newWidth = newWidth - step;
            newHeight = (int) (newWidth / rate);
            bitmap = ImageUtil.getScaledBitmap(bitmap, newWidth, newHeight);
            Log.d("jihongwen", "bitmap.getByteCount():" + bitmap.getByteCount());
            Log.d("jihongwen", "newWidth:" + newWidth + "  newHeight:" + newHeight);
        }
        return bitmap;
    }

    public static Bitmap getCompressBitmap(Bitmap bitmap, int width, int height) {
        int scale = Math.max(width, height);
        bitmap.getByteCount();
        return null;
    }

    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 80, baos);
        return baos.toByteArray();
    }

    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    public static byte[] getThumbImage(Bitmap bitmap, float maxSize) {
        int options = 100;
        byte[] bytes = compressBitmapByQuality(bitmap, options);
        while (bytes.length > maxSize) {
            options -= 5;
            if (options < 5) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                Bitmap tempBitmap = BitmapFactory.decodeStream(inputStream, null, null);
                bitmap = getCompressBitmap(tempBitmap, maxSize, true);
                return compressBitmapByQuality(bitmap, 100);
            }
            bytes = compressBitmapByQuality(bitmap, options);
        }
        return bytes;
    }

    public static byte[] compressBitmapByQuality(Bitmap bitmap, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        return outputStream.toByteArray();
    }

    public static Bitmap getBitmapByQuality(Bitmap bitmap, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        return getBitmap(outputStream);
    }

    private static Bitmap getBitmap(ByteArrayOutputStream outputStream) {
        ByteArrayInputStream isBm = new ByteArrayInputStream(outputStream.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    private static int getImageSize(Bitmap image) {
        if (image != null) {
            return image.getRowBytes() * image.getHeight();
        } else {
            return 0;
        }
    }
}
