package stark.skshare.wechat;

import android.content.Context;
import android.content.Intent;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXEmojiObject;
import com.tencent.mm.sdk.openapi.WXFileObject;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXMusicObject;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

import stark.skshare.ImageCompress;
import stark.skshare.Platform;
import stark.skshare.SKShare;
import stark.skshare.SKShareConfig;

/**
 * Created by jihongwen on 16/8/4.
 */

public class WeChatShare implements SKShare.IShare<WeChatShare> {

    private static final String TAG = WeChatShare.class.getSimpleName();

    static final String APP_ID_KEY = "wx_app_key";

    static String APP_ID = "wxd6e53b1e821b0ec5";

    IWXAPI wxApi;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public WeChatShare init(Context context, SKShareConfig shareConfig) {
        APP_ID = shareConfig.getProperty(APP_ID_KEY) == null ? APP_ID : shareConfig.getProperty(APP_ID_KEY);
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
     */
    public void shareText(Context context, WeChatSKShareContent shareContent, boolean isToFriend) {
        WXTextObject textObject = new WXTextObject();
        textObject.text = "微信分享测试";
        send(context, shareContent, isToFriend, textObject, "text");
    }

    /**
     * 分享图片
     */
    public void shareImage(Context context, WeChatSKShareContent shareContent, boolean isToFriend) {
        WXImageObject imageObject = new WXImageObject();
        imageObject.imageData = getCompressImageData(shareContent, context);
        send(context, shareContent, isToFriend, imageObject, "img");
    }


    /**
     * 分享表情
     */
    public void shareEmoji(Context context, WeChatSKShareContent shareContent, boolean isToFriend) {
        WXEmojiObject emojiObject = new WXEmojiObject();
        emojiObject.emojiPath = shareContent.filePath;
        //emojiObject.emojiData = getCompressImageData(shareContent, context);
        send(context, shareContent, isToFriend, emojiObject, "emoji");
    }

    /**
     * 分享音乐
     */
    public void shareMusic(Context context, WeChatSKShareContent content, boolean isToFriend) {
        WXMusicObject musicObject = new WXMusicObject();
        musicObject.musicUrl = content.musicUrl;
        send(context, content, isToFriend, musicObject, "music");
    }

    /**
     * 分享链接
     */
    public void shareWebPage(Context context, WeChatSKShareContent shareContent, boolean isToFriend) {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = shareContent.url;
        send(context, shareContent, isToFriend, webPage, "webpage");
    }

    /**
     * 文件分享
     */
    public void shareFile(Context context, WeChatSKShareContent shareContent, boolean isToFriend) {
        WXFileObject fileObject = new WXFileObject();
        fileObject.fileData = shareContent.fileData;
        fileObject.filePath = shareContent.filePath;
        send(context, shareContent, isToFriend, fileObject, "file");
    }

    private void send(Context context, WeChatSKShareContent shareContent, boolean isToFriend, WXMediaMessage.IMediaObject mediaObject, String type) {
        WXMediaMessage mediaMessage = new WXMediaMessage(mediaObject);
        mediaMessage.title = shareContent.title;
        mediaMessage.description = shareContent.content;
        mediaMessage.thumbData = getCompressThumbImageData(shareContent, context);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction(type);
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    /*
     * 获取缩略图二进制数据
     */
    private byte[] getCompressThumbImageData(WeChatSKShareContent shareContent, Context context) {
        if (shareContent.thumbData != null) {
            return shareContent.thumbData;
        }

        if (shareContent.thumbFile != null) {
            return ImageCompress.getThumbImageData(context, shareContent.thumbFile, WeChatCompress.MAX_THUMB_IMAGE_DATA_LENGTH, WeChatCompress.THUMB_SIZE, WeChatCompress.THUMB_SIZE);
        }

        if (shareContent.imageBitmap != null) {
            return ImageCompress.getThumbImageData(shareContent.imageBitmap, WeChatCompress.MAX_THUMB_IMAGE_DATA_LENGTH, WeChatCompress.THUMB_SIZE, WeChatCompress.THUMB_SIZE);
        }
        return null;
    }

    private byte[] getCompressImageData(WeChatSKShareContent shareContent, Context context) {
        if (shareContent.imageData != null) {
            return shareContent.imageData;
        }

        if (shareContent.imageFile != null) {
            return ImageCompress.getImageData(context, shareContent.imageFile, WeChatCompress.MAX_IMAGE_DATA_LENGTH);
        }

        if (shareContent.imageBitmap != null) {
            return ImageCompress.getImageData(shareContent.imageBitmap, WeChatCompress.MAX_IMAGE_DATA_LENGTH);
        }
        return null;
    }

    /**
     * 构建一个唯一标志
     *
     * @param type 分享类型
     * @return 唯一标示
     */
    private String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : (type + System.currentTimeMillis());
    }
}
