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

    static final int THUMB_SIZE = 500;

    static final int MAX_IMAGE_DATA_LENGTH = 1024 * 1024 * 10;

    static final int MAX_THUMB_IMAGE_DATA_LENGTH = 1024 * 32;

}
