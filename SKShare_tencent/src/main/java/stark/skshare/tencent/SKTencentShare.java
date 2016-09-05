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
import stark.skshare.SKShareConfig;
import stark.skshare.SKShareContent;


/**
 * Created by jihongwen on 16/8/4.
 */

public class SKTencentShare implements SKShare.IShare<SKTencentShare> {

    public static final String APP_ID_KEY = "qq_app_key";

    public static String APP_ID = "1105530560";

    protected Tencent mTencent;

    protected BaseUiListener iUiListener;

    protected SKShare.ShareCallback callback;

    protected Activity mActivity;

    @Override
    public SKTencentShare init(Context context, SKShareConfig shareConfig) {
        APP_ID = shareConfig.getProperty(APP_ID_KEY);
        iUiListener = new BaseUiListener();
        mTencent = Tencent.createInstance(APP_ID, context.getApplicationContext());
        return this;
    }

    @Override
    public int requestCode() {
        return Platform.QQ;
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
