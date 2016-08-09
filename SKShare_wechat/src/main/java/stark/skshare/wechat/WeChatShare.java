package stark.skshare.wechat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

import stark.skshare.STShare;
import stark.skshare.ShareContent;


/**
 * Created by jihongwen on 16/8/4.
 */

public class WeChatShare implements STShare.IShare<WeChatShare> {

    public static final String APP_ID = "wxc822b4a6f144b0dd";

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

    public void share(ShareContent content, Activity activity) {


    }

    public void shareText(ShareContent content, boolean isToFriend) {
        WXTextObject textObject = new WXTextObject();
        textObject.text = "微信分享测试";
        WXMediaMessage mediaMessage = new WXMediaMessage(textObject);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = mediaMessage;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        wxApi.sendReq(req);
    }

    public void shareWebPage(ShareContent content, boolean isToFriend) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = content.url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = content.title;
        msg.description = content.content;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = isToFriend ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
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
