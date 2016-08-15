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
        }
        return bitmap;
    }

    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 80, baos);
        return baos.toByteArray();
    }
}
