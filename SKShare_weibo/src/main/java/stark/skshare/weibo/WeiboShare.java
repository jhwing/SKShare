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

import stark.skshare.SKShare;
import stark.skshare.SKShareContent;
import stark.skshare.utlis.SKShareUtil;

/**
 * Created by jihongwen on 16/8/4.
 */

public class WeiboShare implements SKShare.IShare<WeiboShare> {

    private static final String TAG = WeiboShare.class.getSimpleName();

    public static final String APP_ID = "575596140";

    public static final int MAX_SIZE = 2097152;

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

    public void shareImage(WeiboSKShareContent content, Activity activity) {
        ImageObject imageObject = new ImageObject();
        imageObject.thumbData = content.thumbData;
        imageObject.title = content.title;
        imageObject.description = content.content;
        imageObject.imageData = content.imageData;
        imageObject.actionUrl = content.url;
        imageObject.identify = Utility.generateGUID();
        send(imageObject, activity);
    }

    public void shareWebpage(WeiboSKShareContent content, Activity activity) {
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.actionUrl = content.url;
        webpageObject.description = content.content;
        webpageObject.title = content.content;
        webpageObject.thumbData = content.thumbData;
        webpageObject.identify = Utility.generateGUID();
        send(webpageObject, activity);
    }

    public void shareMusic(WeiboSKShareContent content, Activity activity) {
        MusicObject musicObject = new MusicObject();
        musicObject.thumbData = content.thumbData;
        musicObject.title = content.title;
        musicObject.description = content.content;
        musicObject.actionUrl = content.url;
        musicObject.dataUrl = content.dataUrl;
        musicObject.identify = Utility.generateGUID();
        send(musicObject, activity);
    }

    public void shareVideo(WeiboSKShareContent content, Activity activity) {
        VideoObject videoObject = new VideoObject();
        videoObject.actionUrl = content.url;
        videoObject.duration = content.duration;
        videoObject.description = content.content;
        videoObject.dataUrl = content.dataUrl;
        videoObject.title = content.title;
        videoObject.thumbData = content.thumbData;
        videoObject.identify = Utility.generateGUID();
        send(videoObject, activity);
    }

    public void shareVoice(WeiboSKShareContent content, Activity activity) {
        VoiceObject voiceObject = new VoiceObject();
        voiceObject.defaultText = content.content;
        voiceObject.title = content.title;
        voiceObject.duration = content.duration;
        voiceObject.actionUrl = content.url;
        voiceObject.dataUrl = content.dataUrl;
        voiceObject.thumbData = content.thumbData;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult  " + data);
    }
}
