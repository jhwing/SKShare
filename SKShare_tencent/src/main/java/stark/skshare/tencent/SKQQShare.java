package stark.skshare.tencent;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.connect.share.QQShare;

import stark.skshare.SKShare;
import stark.skshare.SKShareContent;

/**
 * Created by jihongwen on 16/8/9.
 */

public class SKQQShare extends SKTencentShare {

    public void shareImageToQQ(Activity activity, SKShareContent content, final SKShare.ShareCallback callback) {
        mActivity = activity;
        this.callback = callback;
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, content.title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, content.content);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, content.url);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, content.imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, content.appName);
        mTencent.shareToQQ(activity, bundle, iUiListener);
    }

    public void shareAppToQQ(Activity activity, SKShareContent content, final SKShare.ShareCallback callback) {
        mActivity = activity;
        this.callback = callback;
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, content.title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, content.content);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, content.url);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, content.imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, content.imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, content.appName);
        mTencent.shareToQQ(activity, bundle, iUiListener);
    }

    @Override
    public int requestCode() {
        return 10103;
    }
}
