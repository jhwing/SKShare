package stark.skshare.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import stark.skshare.STShare;
import stark.skshare.ShareContent;


/**
 * Created by jihongwen on 16/8/4.
 */

public class TencentShare implements STShare.IShare<TencentShare> {

    public static final String APP_ID = "1105530560";

    private Tencent mTencent;

    private BaseUiListener iUiListener;

    private STShare.ShareCallback callback;

    private Activity mActivity;

    @Override
    public TencentShare init(Context context) {
        iUiListener = new BaseUiListener();
        mTencent = Tencent.createInstance(APP_ID, context.getApplicationContext());
        return this;
    }

    public void share(ShareContent content, Activity activity, final STShare.ShareCallback callback) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, iUiListener);
        STShare.removeListener(this);
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
