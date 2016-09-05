package stark.skshare.tencent;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.connect.share.QzoneShare;

import java.util.ArrayList;

import stark.skshare.SKShare;
import stark.skshare.SKShareContent;

/**
 * Created by jihongwen on 16/8/9.
 */

public class SKQzoneShare extends SKTencentShare {

    public void shareImageToQzone(Activity activity, SKShareContent content, final SKShare.ShareCallback callback) {
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
    public int requestCode() {
        return 10104;
    }
}
