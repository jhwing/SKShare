package stark.skshare.weibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import stark.skshare.SKShare;
import stark.skshare.SKShareContent;
import stark.skshare.utlis.SKShareUtil;

/**
 * Created by jihongwen on 16/8/4.
 */

public class WeiboShare implements SKShare.IShare<WeiboShare> {

    private static final String TAG = WeiboShare.class.getSimpleName();

    public static final String APP_ID = "3172824890";

    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    IWeiboShareAPI mWeiboShareAPI;

    byte[] imageData;

    @Override
    public WeiboShare init(Context context) {
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context, APP_ID);
        mWeiboShareAPI.registerApp();
        return this;
    }

    @Override
    public int requestCode() {
        return 765;
    }

    public void share(WeiboSKShareContent content, Activity activity, SKShare.ShareCallback callback) {
        printImageLength(content.thumbImage);
        int length = content.thumbImage.getByteCount();
        Log.i(TAG, "IMAGE SIZE:" + length);
        Bitmap cBitmap = SKShareUtil.getCompressBitmap(content.thumbImage, 2097152, false);
        Log.i(TAG, "IMAGE SIZE:" + cBitmap.getByteCount());
        printImageLength(cBitmap);

        WeiboMessage weiboMessage = new WeiboMessage();
        weiboMessage.mediaObject = getImageObj(cBitmap);
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = weiboMessage;
        mWeiboShareAPI.sendRequest(activity, request);
    }

    private void printImageLength(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "IMAGE SIZE byte:" + imageData.length);
    }

    public void shareImage(SKShareContent content, Activity activity) {

    }

    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        return textObject;
    }

    private ImageObject getImageObj(Bitmap bitmap) {
        ImageObject imageObject = new ImageObject();
        imageObject.title = "";
        imageObject.description = "";
        imageObject.actionUrl = "";
        //设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb
        imageObject.setImageObject(bitmap);
        imageObject.setThumbImage(bitmap);
        return imageObject;
    }

    private WebpageObject getWebpageObj(Bitmap bitmap) {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = "标题";
        mediaObject.actionUrl = "action url";
        mediaObject.defaultText = "默认文案";
        // 设置 Bitmap 类型的图片到视频对象里      设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        return mediaObject;
    }

    private MusicObject getMusicObj(Bitmap bitmap) {
        MusicObject musicObject = new MusicObject();
        musicObject.identify = Utility.generateGUID();
        musicObject.title = "";
        musicObject.description = "";
        musicObject.actionUrl = "";
        musicObject.dataUrl = "";
        musicObject.dataHdUrl = "";
        musicObject.duration = 10;
        musicObject.defaultText = "";
        // 设置 Bitmap 类型的图片到视频对象里      设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        musicObject.setThumbImage(bitmap);
        return musicObject;
    }

    private VideoObject getVideoObj(Bitmap bitmap) {
        VideoObject videoObject = new VideoObject();
        videoObject.identify = Utility.generateGUID();
        videoObject.title = "";
        videoObject.description = "";
        videoObject.actionUrl = "";
        videoObject.dataUrl = "";
        videoObject.dataHdUrl = "";
        videoObject.duration = 10;
        videoObject.defaultText = "";
        // 设置 Bitmap 类型的图片到视频对象里  设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        videoObject.setThumbImage(bitmap);
        return videoObject;
    }

    private VoiceObject getVoiceObj(Bitmap bitmap) {
        VoiceObject voiceObject = new VoiceObject();
        voiceObject.identify = Utility.generateGUID();
        voiceObject.title = "";
        voiceObject.description = "";
        voiceObject.actionUrl = "";
        voiceObject.dataUrl = "";
        voiceObject.dataHdUrl = "";
        voiceObject.duration = 10;
        voiceObject.defaultText = "";
        // 设置 Bitmap 类型的图片到视频对象里      设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        voiceObject.setThumbImage(bitmap);
        return voiceObject;
    }

    private String getSharedText() {
        return "新浪分享测试";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult  " + data);
    }
}
