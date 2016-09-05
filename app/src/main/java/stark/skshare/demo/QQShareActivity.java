package stark.skshare.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import stark.skshare.DefaultShareCallback;
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

    public void textShare(View view) {
    }

    public void imageShare(View view) {
        SKQQShare share = SKShare.create(SKQQShare.class, this);
        share.shareImageToQQ(this, new SKShareContent.Builder()
                .setUrl("http://m.budejie.com")
                .setImageUrl("http://m.budejie.com")
                .setTitle("qq测试分享title")
                .setContent("qq测试分享content")
                .build(), new DefaultShareCallback());
    }

    public void webPageShare(View view) {

    }
}
