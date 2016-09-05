package stark.skshare;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import stark.skshare.utlis.ImageUtil;

/**
 * Created by jihongwen on 16/8/4.
 * 图片压缩
 */

public class ImageCompress {

    private static final int THUMB_SIZE = 500;

    public static byte[] getImageData(Bitmap bitmap, int maxLength) {
        Bitmap scaledBitmap = ImageUtil.getScaledBitmap(bitmap, 720, 960);
        return getImageDataByQuality(scaledBitmap, maxLength);
    }

    public static byte[] getThumbImageData(Bitmap bitmap, int maxLength, int thumbWidth, int thumbHeight) {
        Bitmap scaledBitmap = ImageUtil.getScaledBitmap(bitmap, thumbWidth, thumbHeight);
        return getScaledImageBytes(scaledBitmap, maxLength);
    }

    public static byte[] getImageData(Context context, File file, int maxLength) {
        String filePath = file.getAbsolutePath();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        Bitmap bitmap = ImageUtil.getScaledBitmap(context, Uri.fromFile(file), options.outWidth, options.outHeight);

        return getImageDataByQuality(bitmap, maxLength);
    }

    private static byte[] getImageDataByQuality(Bitmap bitmap, int maxLength) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int quality = 80;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        while (outputStream.toByteArray().length > maxLength) {
            if (quality < 20) {
                break;
            }
            outputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            quality -= 10;
        }
        return outputStream.toByteArray();
    }

    public static byte[] getThumbImageData(Context context, File file, int maxLength, int thumbWidth, int thumbHeight) {
        Bitmap bitmap = ImageUtil.getScaledBitmap(context, Uri.fromFile(file), thumbWidth, thumbHeight);
        return getScaledImageBytes(bitmap, maxLength);
    }

    private static byte[] getScaledImageBytes(Bitmap bitmap, int maxLength) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int quality = 80;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        while (outputStream.toByteArray().length > maxLength) {
            if (quality < 20) {
                break;
            }
            outputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            quality -= 5;
        }
        if (outputStream.toByteArray().length > maxLength) {
            return getImageDataByScale(outputStream, maxLength);
        }
        return outputStream.toByteArray();
    }

    private static byte[] getImageDataByScale(ByteArrayOutputStream outStream, int maxLength) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(outStream.toByteArray(), 0, outStream.toByteArray().length);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int scale = Math.max(w, h);
        int step = 0;
        while (bitmap.getByteCount() > maxLength) {
            int rate = scale - (step += 20) / scale;
            int nw = w * rate;
            int nh = h * rate;
            Bitmap.createScaledBitmap(bitmap, nw, nh, true);
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }

    public static Bitmap getImageBitmap(Bitmap bitmap, int maxLength) {
        byte[] bytes = getImageData(bitmap, maxLength);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return BitmapFactory.decodeStream(inputStream, null, null);
    }

    public static Bitmap getThumbImageBitmap(Bitmap bitmap, int maxLength, int thumbWidth, int thumbHeight) {
        byte[] bytes = getThumbImageData(bitmap, maxLength, thumbWidth, thumbHeight);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return BitmapFactory.decodeStream(inputStream, null, null);
    }

    public static Bitmap getImageBitmap(Context context, File file, int maxLength) {
        byte[] bytes = getImageData(context, file, maxLength);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return BitmapFactory.decodeStream(inputStream, null, null);
    }
}
