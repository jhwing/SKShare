package stark.skshare.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tencent.connect.share.QQShare;

import stark.skshare.SKShare;
import stark.skshare.SKShareContent;
import stark.skshare.tencent.SKQQShare;

/**
 * Created by jihongwen on 16/8/25.
 */

public class QQShareActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_share);

    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("QQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void testShare(View view) {
        SKQQShare share = SKShare.create(SKQQShare.class, this);
        share.shareToQQ(
                new SKShareContent.Builder()
                        .setContent("qq测试分享")
                        .build(),
                this,
                new SKShare.ShareCallback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
