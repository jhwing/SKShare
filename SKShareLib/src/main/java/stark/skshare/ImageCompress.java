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

    public static byte[] getImageData(Bitmap bitmap, int maxLength) {
        // TODO: 16/9/5 等比缩放
        Bitmap scaledBitmap = ImageUtil.getScaledBitmap(bitmap, 720, 960);
        return getScaledImageBytes(scaledBitmap, maxLength);
    }

    public static byte[] getImageData(Context context, File file, int maxLength) {
        String filePath = file.getAbsolutePath();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        Bitmap bitmap = ImageUtil.getScaledBitmap(context, Uri.fromFile(file), options.outWidth, options.outHeight);

        return getScaledImageBytes(bitmap, maxLength);
    }

    public static byte[] getThumbImageData(Bitmap bitmap, int maxLength, int thumbWidth, int thumbHeight) {
        Bitmap scaledBitmap = ImageUtil.getScaledBitmap(bitmap, thumbWidth, thumbHeight);
        return getScaledImageBytes(scaledBitmap, maxLength);
    }

    public static byte[] getThumbImageData(Context context, File file, int maxLength, int thumbWidth, int thumbHeight) {
        Bitmap bitmap = ImageUtil.getScaledBitmap(context, Uri.fromFile(file), thumbWidth, thumbHeight);
        return getScaledImageBytes(bitmap, maxLength);
    }

    /**
     * 对缩放过的图片进行质量压缩,并检验是否符合要求,如果不符合在进行最后的尺寸压缩
     *
     * @param bitmap
     * @param maxLength
     * @return
     */
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

    /**
     * 缩放压缩
     *
     * @param outStream
     * @param maxLength
     * @return
     */
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
}
