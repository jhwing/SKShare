package stark.skshare.wechat;

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
 * 微信压缩
 */

public class WeChatCompress {

    private static final int THUMB_SIZE = 500;

    private static final int MAX_IMAGE_DATA = 1024 * 1024 * 10;
    private static final int MAX_THUMB_IMAGE_DATA = 1024 * 32;

    public static byte[] getImageData(Context context, File file) {
        String filePath = file.getAbsolutePath();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        Bitmap bitmap = ImageUtil.getScaledBitmap(context, Uri.fromFile(file), options.outWidth, options.outHeight);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int quality = 80;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        while (outputStream.toByteArray().length > MAX_IMAGE_DATA) {
            if (quality < 20) {
                break;
            }
            outputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            quality -= 10;
        }
        return outputStream.toByteArray();
    }

    public static byte[] getThumbImageData(Context context, File file) {
        Bitmap bitmap = ImageUtil.getScaledBitmap(context, Uri.fromFile(file), THUMB_SIZE, THUMB_SIZE);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int quality = 80;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        while (outputStream.toByteArray().length > MAX_THUMB_IMAGE_DATA) {
            if (quality < 20) {
                break;
            }
            outputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            quality -= 5;
        }
        return outputStream.toByteArray();
    }

    public static Bitmap getImageBitmap(Context context, File file) {
        byte[] bytes = getImageData(context, file);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return BitmapFactory.decodeStream(inputStream, null, null);
    }

    public static Bitmap getThumbImageBitmap(Context context, File file) {
        byte[] bytes = getThumbImageData(context, file);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return BitmapFactory.decodeStream(inputStream, null, null);
    }
}
