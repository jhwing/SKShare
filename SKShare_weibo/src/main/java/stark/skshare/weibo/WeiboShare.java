package stark.skshare.weibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.sina.weibo.sdk.api.BaseMediaObject;
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

import stark.skshare.ImageCompress;
import stark.skshare.SKShare;
import stark.skshare.SKShareConfig;
import stark.skshare.SKShareContent;
import stark.skshare.utlis.SKShareUtil;

/**
 * Created by jihongwen on 16/8/4.
 */

public class WeiboShare implements SKShare.IShare<WeiboShare> {

    private static final String TAG = WeiboShare.class.getSimpleName();

    public static final String APP_ID_KEY = "weibo_app_key";

    public static String APP_ID = "575596140";

    public static final int MAX_SIZE = 2097152;

    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    IWeiboShareAPI mWeiboShareAPI;

    @Override
    public WeiboShare init(Context context, SKShareConfig shareConfig) {
        APP_ID = shareConfig.getProperty(APP_ID_KEY);
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context, APP_ID);
        mWeiboShareAPI.registerApp();
        return this;
    }

    @Override
    public int requestCode() {
        return 765;
    }

    /**
     * 文本分享
     *
     * @param content
     * @param activity
     */
    public void shareText(WeiboSKShareContent content, Activity activity) {
        TextObject textObject = new TextObject();
        textObject.text = content.content;
        textObject.title = content.title;
        textObject.actionUrl = content.url;
        textObject.identify = Utility.generateGUID();
        send(textObject, activity);
    }

    public void shareImage(WeiboSKShareContent shareContent, Activity activity) {
        // 微博分享图片可以不分享缩略图
        ImageObject imageObject = new ImageObject();
        //imageObject.thumbData = content.thumbData;
        imageObject.title = shareContent.title;
        imageObject.description = shareContent.content;
        imageObject.imageData = getCompressImageData(shareContent, activity);
        imageObject.actionUrl = shareContent.url;
        imageObject.identify = Utility.generateGUID();
        send(imageObject, activity);
    }

    public void shareWebpage(WeiboSKShareContent shareContent, Activity activity) {
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.actionUrl = shareContent.url;
        webpageObject.description = shareContent.content;
        webpageObject.title = shareContent.title;
        webpageObject.thumbData = getCompressThumbImageData(shareContent, activity);
        webpageObject.identify = Utility.generateGUID();
        send(webpageObject, activity);
    }

    public void shareMusic(WeiboSKShareContent shareContent, Activity activity) {
        MusicObject musicObject = new MusicObject();
        musicObject.actionUrl = shareContent.url;
        musicObject.defaultText = shareContent.title;
        musicObject.title = shareContent.title;
        musicObject.description = shareContent.content;
        musicObject.duration = shareContent.duration;
        musicObject.dataUrl = shareContent.dataUrl;
        musicObject.thumbData = getCompressThumbImageData(shareContent, activity);
        musicObject.identify = Utility.generateGUID();
        send(musicObject, activity);
    }

    public void shareVideo(WeiboSKShareContent shareContent, Activity activity) {
        VideoObject videoObject = new VideoObject();
        videoObject.actionUrl = shareContent.url;
        videoObject.defaultText = shareContent.title;
        videoObject.title = shareContent.title;
        videoObject.description = shareContent.content;
        videoObject.duration = shareContent.duration;
        videoObject.dataUrl = shareContent.dataUrl;
        videoObject.thumbData = getCompressThumbImageData(shareContent, activity);
        videoObject.identify = Utility.generateGUID();
        send(videoObject, activity);
    }

    public void shareVoice(WeiboSKShareContent shareContent, Activity activity) {
        VoiceObject voiceObject = new VoiceObject();
        voiceObject.actionUrl = shareContent.url;
        voiceObject.defaultText = shareContent.title;
        voiceObject.title = shareContent.title;
        voiceObject.description = shareContent.content;
        voiceObject.duration = shareContent.duration;
        voiceObject.dataUrl = shareContent.dataUrl;
        voiceObject.thumbData = getCompressThumbImageData(shareContent, activity);
        voiceObject.identify = Utility.generateGUID();
        send(voiceObject, activity);
    }

    private void send(BaseMediaObject mediaObject, Activity activity) {
        WeiboMessage weiboMessage = new WeiboMessage();
        weiboMessage.mediaObject = mediaObject;
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = weiboMessage;
        mWeiboShareAPI.sendRequest(activity, request);
    }

    private byte[] getCompressThumbImageData(WeiboSKShareContent shareContent, Context context) {
        if (shareContent.imageData != null) {
            return shareContent.imageData;
        }

        if (shareContent.imageFile != null) {
            return ImageCompress.getThumbImageData(context, shareContent.imageFile, WeiboCompress.MAX_THUMB_IMAGE_DATA_LENGTH, WeiboCompress.THUMB_SIZE, WeiboCompress.THUMB_SIZE);
        }

        if (shareContent.imageBitmap != null) {
            return ImageCompress.getThumbImageData(shareContent.imageBitmap, WeiboCompress.MAX_THUMB_IMAGE_DATA_LENGTH, WeiboCompress.THUMB_SIZE, WeiboCompress.THUMB_SIZE);
        }
        return null;
    }

    private byte[] getCompressImageData(WeiboSKShareContent shareContent, Context context) {
        if (shareContent.imageData != null) {
            return shareContent.imageData;
        }

        if (shareContent.imageFile != null) {
            return ImageCompress.getImageData(context, shareContent.imageFile, WeiboCompress.MAX_IMAGE_DATA_LENGTH);
        }

        if (shareContent.imageBitmap != null) {
            return ImageCompress.getImageData(shareContent.imageBitmap, WeiboCompress.MAX_IMAGE_DATA_LENGTH);
        }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult  " + data);
    }
}
