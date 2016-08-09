package stark.skshare.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

import stark.skshare.Platform;
import stark.skshare.SKShare;
import stark.skshare.ShareContent;


/**
 * Created by jihongwen on 16/8/4.
 */

public class SKTencentShare implements SKShare.IShare<SKTencentShare> {

    public static final String APP_ID = "1105530560";

    private Tencent mTencent;

    private BaseUiListener iUiListener;

    private SKShare.ShareCallback callback;

    private Activity mActivity;

    @Override
    public SKTencentShare init(Context context) {
        iUiListener = new BaseUiListener();
        mTencent = Tencent.createInstance(APP_ID, context.getApplicationContext());
        return this;
    }

    @Override
    public int requestCode() {
        return Platform.QQ;
    }

    public void shareToQQ(ShareContent content, Activity activity, final SKShare.ShareCallback callback) {
        mActivity = activity;
        this.callback = callback;
        Bundle bundle = new Bundle();
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, content.title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, content.content);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, content.url);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, content.imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, content.appName);
        mTencent.shareToQQ(activity, bundle, iUiListener);
    }

    public void shareToQzone(ShareContent content, Activity activity, final SKShare.ShareCallback callback) {
        mActivity = activity;
        this.callback = callback;
        Bundle bundle = new Bundle();
        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, content.title);
        bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, content.content);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, content.url);
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls.add(content.imageUrl);
        bundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        mTencent.shareToQzone(activity, bundle, iUiListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, iUiListener);
        SKShare.removeListener(this);
    }

    class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            Toast.makeText(mActivity, "onComplete", Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onSuccess();
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(mActivity, "onError", Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onError();
        }

        @Override
        public void onCancel() {
            Toast.makeText(mActivity, "onCancel", Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onCancel();
        }
    }
}
