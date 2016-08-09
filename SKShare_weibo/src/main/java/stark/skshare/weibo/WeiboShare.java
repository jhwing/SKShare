package stark.skshare.weibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

import stark.skshare.Platform;
import stark.skshare.SKShare;
import stark.skshare.ShareContent;


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

    @Override
    public WeiboShare init(Context context) {
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context, APP_ID);
        mWeiboShareAPI.registerApp();
        return this;
    }

    @Override
    public int requestCode() {
        return Platform.WEIBO;
    }

    public void share(ShareContent content, Activity activity, SKShare.ShareCallback callback) {
        WeiboMessage weiboMessage = new WeiboMessage();
        weiboMessage.mediaObject = getTextObj();
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = weiboMessage;
        mWeiboShareAPI.sendRequest(activity, request);
    }

    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        return textObject;
    }

    private String getSharedText() {
        return "新浪分享测试";
    }

    private ImageObject getImageObject(Bitmap bitmap) {
        ImageObject imageObject = new ImageObject();
        imageObject.setImageObject(bitmap);
        return imageObject;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult  " + data);
    }
}
