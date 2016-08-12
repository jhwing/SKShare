package stark.skshare.wechat;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXEmojiObject;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

import stark.skshare.Platform;
import stark.skshare.SKShare;

/**
 * Created by jihongwen on 16/8/4.
 */

public class WeChatShare implements SKShare.IShare<WeChatShare> {

    private static final String TAG = WeChatShare.class.getSimpleName();

    public static final String APP_ID = "wx15049dc26bd17949";

    IWXAPI wxApi;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public WeChatShare init(Context context) {
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
        req.transaction = buildTransaction("text");
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    /**
     * 分享图片
     * @param context
     * @param content
     * @param isToFriend
     */
    public void shareImage(Context context, WeChatSKShareContent content, boolean isToFriend) {
        // 原图压缩
        WXImageObject imageObject = new WXImageObject(WeChatCompress.getImageBitmap(context, content.thumb));
        WXMediaMessage mediaMessage = new WXMediaMessage(imageObject);
        mediaMessage.title = content.title;
        mediaMessage.description = content.content;
        // 缩略图压缩
        mediaMessage.thumbData = WeChatCompress.getThumbImageData(context, content.thumb);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    /**
     * 分享表情
     * @param context
     * @param content
     * @param isToFriend
     */
    public void shareEmoji(Context context, WeChatSKShareContent content, boolean isToFriend) {
        WXEmojiObject emojiObject = new WXEmojiObject(WeChatCompress.getImageData(context, content.thumb));
        WXMediaMessage mediaMessage = new WXMediaMessage(emojiObject);
        mediaMessage.title = content.title;
        mediaMessage.description = content.content;
        mediaMessage.thumbData = WeChatCompress.getThumbImageData(context, content.thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("emoji");
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    /**
     * 分享链接
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
