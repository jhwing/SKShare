package stark.skshare.wechat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXEmojiObject;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXMusicObject;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

import stark.skshare.Platform;
import stark.skshare.SKShare;
import stark.skshare.SKShareConfig;

/**
 * Created by jihongwen on 16/8/4.
 */

public class WeChatShare implements SKShare.IShare<WeChatShare> {

    private static final String TAG = WeChatShare.class.getSimpleName();

    public static final String APP_ID_KEY = "wx_app_key";
    
    public static String APP_ID = "wxd6e53b1e821b0ec5";

    IWXAPI wxApi;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public WeChatShare init(Context context, SKShareConfig shareConfig) {
        APP_ID = shareConfig.getProperty(APP_ID_KEY);
        wxApi = WXAPIFactory.createWXAPI(context, APP_ID);
        wxApi.registerApp(APP_ID);
        return this;
    }

    @Override
    public int requestCode() {
        return Platform.WECHAT;
    }

    /**
     * 分享文本
     *
     * @param content
     * @param isToFriend
     */
    public void shareText(WeChatSKShareContent content, boolean isToFriend) {
        WXTextObject textObject = new WXTextObject();
        textObject.text = "微信分享测试";
        WXMediaMessage mediaMessage = new WXMediaMessage(textObject);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        mediaMessage.title = "微信分享测试";
        req.transaction = buildTransaction("text");
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    /**
     * 分享图片
     *
     * @param context
     * @param content
     * @param isToFriend
     */
    public void shareImage(Context context, WeChatSKShareContent content, boolean isToFriend) {
        // 原图压缩

        Bitmap bitmap = null;
        if (content.thumb != null) {
            bitmap = WeChatCompress.getImageBitmap(context, content.thumb);
        } else if (content.thumbImage != null) {
            bitmap = WeChatCompress.getImageBitmap(content.thumbImage);
        }
        WXImageObject imageObject = new WXImageObject(bitmap);
        WXMediaMessage mediaMessage = new WXMediaMessage(imageObject);
        mediaMessage.title = content.title;
        mediaMessage.description = content.content;

        if (content.thumb != null) {
            // 缩略图压缩
            mediaMessage.thumbData = WeChatCompress.getThumbImageData(context, content.thumb);
        } else if (content.thumbImage != null) {
            mediaMessage.thumbData = WeChatCompress.getThumbImageData(content.thumbImage);
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    /**
     * 分享表情
     *
     * @param context
     * @param content
     * @param isToFriend
     */
    public void shareEmoji(Context context, WeChatSKShareContent content, boolean isToFriend) {
        WXEmojiObject emojiObject = new WXEmojiObject();
        if (content.thumb != null) {
            emojiObject.emojiData = WeChatCompress.getImageData(context, content.thumb);
        } else if (content.thumbImage != null) {
            emojiObject.emojiData = WeChatCompress.getImageData(content.thumbImage);
        }
        WXMediaMessage mediaMessage = new WXMediaMessage(emojiObject);
        mediaMessage.title = content.title;
        mediaMessage.description = content.content;
        if (content.thumb != null) {
            mediaMessage.thumbData = WeChatCompress.getThumbImageData(context, content.thumb);
        } else if (content.thumbImage != null) {
            mediaMessage.thumbData = WeChatCompress.getThumbImageData(content.thumbImage);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("emoji");
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    public void shareMusic(Context context, WeChatSKShareContent content, boolean isToFriend) {
        WXMusicObject musicObject = new WXMusicObject();
        musicObject.musicUrl = content.url;
    }

    /**
     * 分享链接
     *
     * @param context
     * @param content
     * @param isToFriend
     */
    public void shareWebPage(Context context, WeChatSKShareContent content, boolean isToFriend) {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = content.url;
        WXMediaMessage mediaMessage = new WXMediaMessage(webPage);
        mediaMessage.title = content.title;
        mediaMessage.description = content.content;
        mediaMessage.thumbData = WeChatCompress.getThumbImageData(context, content.thumb);
        Log.i(TAG, "CompressBitmap thumb length:" + mediaMessage.thumbData.length);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    /**
     * 构建一个唯一标志
     *
     * @param type
     * @return
     * @author YOLANDA
     */
    private String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : (type + System.currentTimeMillis());
    }
}
